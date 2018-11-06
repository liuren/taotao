package com.louis.kitty.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    /*
     * Title: save
     *Description: 
     * @param record
     * @return 
     * @see com.louis.kitty.core.service.CurdService#save(java.lang.Object) 
     */
    @Override
    public int save(SysMenu record) {
        return this.sysMenuMapper.insertSelective(record);
    }

    /*
     * Title: upate
     *Description: 
     * @param record
     * @return 
     * @see com.louis.kitty.core.service.CurdService#upate(java.lang.Object) 
     */
    @Override
    public int upate(SysMenu record) {
        return this.sysMenuMapper.updateByPrimaryKeySelective(record);
    }

    /*
     * Title: delete
     *Description: 
     * @param record
     * @return 
     * @see com.louis.kitty.core.service.CurdService#delete(java.lang.Object) 
     */
    @Override
    public int delete(SysMenu record) {
        return this.sysMenuMapper.deleteByPrimaryKey(record.getId());
    }

    /*
     * Title: delete
     *Description: 
     * @param records
     * @return 
     * @see com.louis.kitty.core.service.CurdService#delete(java.util.List) 
     */
    @Override
    public int delete(List<SysMenu> records) {
        for (SysMenu record : records) {
            delete(record);
        }
        return 1;
    }

    /*
     * Title: findById
     *Description: 
     * @param id
     * @return 
     * @see com.louis.kitty.core.service.CurdService#findById(java.lang.Long) 
     */
    @Override
    public SysMenu findById(Long id) {
        return this.sysMenuMapper.selectByPrimaryKey(id);
    }

    /*
     * Title: findPage
     *Description: 
     * @param pageResult
     * @return 
     * @see com.louis.kitty.core.service.CurdService#findPage(com.louis.kitty.admin.page.PageRequest) 
     */
    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return PageUtils.getPageResult(pageRequest, pageInfo(pageRequest));
    }

    /** 
     * @Title: pageInfo 
     * @Description: TODO(这里用一句话描述这个方法的作用) 
     * @param @param pageRequest
     * @param @return  参数说明 
     * @return PageInfo<?>    返回类型 
     * @throws 
     */
    private PageInfo<SysMenu> pageInfo(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<SysMenu> sysMenus = this.sysMenuMapper.findPage();
        return new PageInfo<SysMenu>(sysMenus);
    }
}
