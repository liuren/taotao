package com.louis.kitty.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.louis.kitty.admin.config.ColumnFilter;
import com.louis.kitty.admin.constants.SysConstants;
import com.louis.kitty.admin.dao.SysMenuMapper;
import com.louis.kitty.admin.dao.SysRoleMapper;
import com.louis.kitty.admin.dao.SysRoleMenuMapper;
import com.louis.kitty.admin.model.SysMenu;
import com.louis.kitty.admin.model.SysRole;
import com.louis.kitty.admin.model.SysRoleMenu;
import com.louis.kitty.admin.page.MybatisPageHelper;
import com.louis.kitty.admin.page.PageRequest;
import com.louis.kitty.admin.page.PageResult;
import com.louis.kitty.admin.service.SysRoleService;

@Service
public class SysRoleServiceImpl  implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;
    
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;
    
    @Autowired
    private SysMenuMapper sysMenuMapper;
    
    @Override
    public int save(SysRole record) {
        if (record.getId() == null || record.getId() == 0) {
            return this.sysRoleMapper.insertSelective(record);
        }
        return this.sysRoleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int delete(SysRole record) {
        return this.sysRoleMapper.deleteByPrimaryKey(record.getId());
    }

    @Override
    public int delete(List<SysRole> records) {
        for (SysRole record : records) {
            delete(record);
        }
        return 1;
    }

    @Override
    public SysRole findById(Long id) {
        return this.sysRoleMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        ColumnFilter columnFilter = pageRequest.getColumnFilter("name");
        if (columnFilter != null && columnFilter.getValue() != null) {
            return MybatisPageHelper.findPage(pageRequest, sysRoleMapper, "findPageByName", columnFilter.getValue());
        }
        return MybatisPageHelper.findPage(pageRequest, sysRoleMapper);
    }

    @Override
    public List<SysRole> findAll() {
        return this.sysRoleMapper.findAll();
    }

    @Override
    public List<SysMenu> findRoleMenus(Long roleId) {
        SysRole sysRole = this.sysRoleMapper.selectByPrimaryKey(roleId);
        if (SysConstants.ADMIN.equalsIgnoreCase(sysRole.getName())) {
            //如果是超级管理员返回全部菜单
            return this.sysMenuMapper.findAll();
        }
        return this.sysMenuMapper.findRoleMenus(roleId);
    }

    @Transactional
    @Override
    public int saveRoleMenus(List<SysRoleMenu> records) {
        if (records == null || records.isEmpty()) {
            return 1;
        }
        Long roleId = records.get(0).getRoleId();
        this.sysRoleMenuMapper.deleteByRoleId(roleId);
        for (SysRoleMenu record : records) {
            this.sysRoleMenuMapper.insertSelective(record);
        }
        return 1;
    }

    @Override
    public List<SysRole> findByName(String name) {
        return this.sysRoleMapper.findByName(name);
    }
	
}
