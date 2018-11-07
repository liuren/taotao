package com.louis.kitty.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.louis.kitty.admin.constants.SysConstants;
import com.louis.kitty.admin.dao.SysMenuMapper;
import com.louis.kitty.admin.model.SysMenu;
import com.louis.kitty.admin.page.PageRequest;
import com.louis.kitty.admin.page.PageResult;
import com.louis.kitty.admin.page.PageUtils;
import com.louis.kitty.admin.service.SysMenuService;

@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;
    
    @Override
    public PageResult findPage(PageRequest pageRequest) {
        
        return PageUtils.getPageResult(pageRequest, getPageInfo(pageRequest));
    }

    /**
     * 调用分页插件完成分页
     * @param pageRequest
     * @return
     */
    private PageInfo<SysMenu> getPageInfo(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<SysMenu> sysMenus = this.sysMenuMapper.findPage();
        
        return new PageInfo<SysMenu>(sysMenus);
    }

    @Override
    public int save(SysMenu record) {
        return this.sysMenuMapper.insertSelective(record);
    }

    @Override
    public int delete(SysMenu record) {
        return this.sysMenuMapper.deleteByPrimaryKey(record.getId());
    }

    @Override
    public int delete(List<SysMenu> records) {
        for (SysMenu record : records) {
            delete(record);
        }
        return 1;
    }

    @Override
    public SysMenu findById(Long id) {
        return this.sysMenuMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SysMenu> findTree(String userName, int menuType) {
        List<SysMenu> sysMenus = new ArrayList<>();
        List<SysMenu> menus = findByUser(userName);
        for (SysMenu menu : menus) {
            if (menu.getParentId() == null || menu.getParentId() == 0) {
                menu.setLevel(0);
                sysMenus.add(menu);
            }
        }
        sysMenus.sort((o1, o2) -> o1.getOrderNum().compareTo(o2.getOrderNum()));
        findChildren(sysMenus, menus, menuType);
        return sysMenus;
    }


    @Override
    public List<SysMenu> findByUser(String userName) {
        if(userName == null || "".equals(userName) || SysConstants.ADMIN.equalsIgnoreCase(userName)) {
            return this.sysMenuMapper.findAll();
        }
        return this.sysMenuMapper.findByUserName(userName);
    }

    private void findChildren(List<SysMenu> sysMenus, List<SysMenu> menus, int menuType) {
        for (SysMenu sysMenu : menus) {
            List<SysMenu> children = new ArrayList<>();
            for (SysMenu menu : menus) {
                if (menuType == 1 && menu.getType() == 2) {
                    // 如果是获取类型不需要按钮，且菜单类型是按钮的，直接过滤掉
                    continue;
                }
                if(sysMenu.getId() != null && sysMenu.getId().equals(menu.getParentId())) {
                    menu.setParentName(menu.getName());
                    menu.setLevel(sysMenu.getLevel() + 1);
                    children.add(menu);
                }
            }
            sysMenu.setChildren(children);
            children.sort((o1, o2) -> o1.getOrderNum().compareTo(o2.getOrderNum()));
            findChildren(children, menus, menuType);
        }
    }
}
