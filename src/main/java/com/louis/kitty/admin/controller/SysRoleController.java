package com.louis.kitty.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.louis.kitty.admin.constants.SysConstants;
import com.louis.kitty.admin.dao.SysRoleMapper;
import com.louis.kitty.admin.model.SysRole;
import com.louis.kitty.admin.model.SysRoleMenu;
import com.louis.kitty.admin.page.PageRequest;
import com.louis.kitty.admin.result.HttpResult;
import com.louis.kitty.admin.service.SysRoleService;

@RestController
@RequestMapping("role")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    
    @PostMapping(value = "/save")
    public HttpResult save(@RequestBody SysRole record) {
        SysRole role = this.sysRoleService.findById(record.getId());
        if (role != null) {
            if (SysConstants.ADMIN.equalsIgnoreCase(role.getName())) {
                    return HttpResult.error("超级管理员不允许修改");
            }
        }
        //新增角色
        if (record.getId() == null || record.getId() == 0 && !sysRoleService.findByName(record.getName()).isEmpty()) {
            return HttpResult.error("角色名已存在");
        }
        return HttpResult.ok(this.sysRoleService.save(record));
    }
    
    @PostMapping(value = "/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        return HttpResult.ok(this.sysRoleService.findPage(pageRequest));
    }
    
    @GetMapping(value = "/findAll")
    public HttpResult findAll() {
        return HttpResult.ok(this.sysRoleService.findAll());
    }
    
    @GetMapping(value = "/findRoleMenus/{roleId}")
    public HttpResult findRoleMenus(@PathVariable("roleId") Long roleId) {
        return HttpResult.ok(this.sysRoleService.findRoleMenus(roleId));
    }
    
    @PostMapping(value="/saveRoleMenus")
    public HttpResult saveRoleMenus(@RequestBody List<SysRoleMenu> records) {
        for (SysRoleMenu record : records) {
            SysRole sysRole = this.sysRoleMapper.selectByPrimaryKey(record.getId());
            if (SysConstants.ADMIN.equalsIgnoreCase(sysRole.getName())) {
                //如果是超级用户，不允许超级用户菜单
                return HttpResult.error("超级管理员拥有所有菜单权限，不允许修改！");
            }
        }
        return HttpResult.ok(this.sysRoleService.saveRoleMenus(records));
    }
}
