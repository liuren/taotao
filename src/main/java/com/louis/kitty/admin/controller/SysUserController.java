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
import com.louis.kitty.admin.model.SysUser;
import com.louis.kitty.admin.page.PageRequest;
import com.louis.kitty.admin.result.HttpResult;
import com.louis.kitty.admin.service.SysUserService;
import com.louis.kitty.admin.utils.PasswordUtils;
import com.louis.kitty.admin.utils.ShiroUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "用户控制器")
@RestController
@RequestMapping(value = "user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;
    
    @ApiOperation(value = "获取用户信息", notes = "根据用户ID获取用户信息")
    @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "Long")
    @GetMapping(value = "/findByUserId/{userId}")
    public Object findByUserId(@PathVariable("userId") Long userId) {
        return this.sysUserService.findById(userId);
    }
    
//    @ApiOperation(value = "获取所有用户信息", notes = "获取所有用户信息")
//    @ApiImplicitParam(value = "无参数")
//    @GetMapping(value = "/findAll")
//    public Object findAll() {
//        return this.sysUserService.findAll();
//    }
    
    @PostMapping(value = "/save")
    public HttpResult save(@RequestBody SysUser record) {
        SysUser user = this.sysUserService.findById(record.getId());
        if (user == null) {
            if (SysConstants.ADMIN.equalsIgnoreCase(record.getName())) {
                return HttpResult.error("超级管理员不允许修改！");
            }
        }
        if (record.getPassword() != null) {
            String salt = PasswordUtils.getSalt();
            if (user == null) {
                //新增用户
                if (sysUserService.findByName(record.getName()) != null) {
                    return HttpResult.error("用户名已经存在");
                }
                String password = PasswordUtils.encrypte(record.getPassword(), salt);
                record.setSalt(salt);
                record.setPassword(password);
            } else {
                //用户已经存在，修改用户，并修改密码
                if (!record.getPassword().equals(user.getPassword())) {
                    String password = PasswordUtils.encrypte(record.getPassword(), salt);
                    record.setSalt(salt);
                    record.setPassword(password);
                }
            }
        }
        
        return HttpResult.ok(this.sysUserService.save(record));
    }
    
    @PostMapping(value="/delete")
    public HttpResult delete(@RequestBody List<SysUser> records) {
        for (SysUser record : records) {
            SysUser sysUser = this.sysUserService.findById(record.getId());
            if (sysUser != null && SysConstants.ADMIN.equalsIgnoreCase(sysUser.getName())) {
                return HttpResult.error("超级管理员不允许删除!");
            }
        }
        return HttpResult.ok(this.sysUserService.delete(records));
    }
    
    @ApiOperation(value = "获取用户信息", notes = "根据用户名获取用户信息")
    @ApiImplicitParam(name = "name", value = "用户名", required = true, dataType = "String")
    @GetMapping(value = "/findByUserName/{name}")
    public HttpResult findByUserName(@PathVariable("name") String name) {
        return HttpResult.ok(this.sysUserService.findByName(name));
    }
    
    @ApiOperation(value = "获取权限信息", notes = "根据用户名获取用户信息")
    @ApiImplicitParam(name = "name", value = "用户名", required = true, dataType = "String")
    @GetMapping(value = "/findPermissions/{name}")
    public HttpResult findPermissions(@PathVariable("name") String name) {
        return HttpResult.ok(this.sysUserService.findPermissions(name));
    }
    
    @ApiOperation(value = "获取角色信息", notes = "根据用户ID获取用户信息")
    @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "Long")
    @GetMapping(value = "/findUserRoles/{userId}")
    public HttpResult findUserRoles(@PathVariable("userId") Long userId) {
        return HttpResult.ok(this.sysUserService.findUserRoles(userId));
    }
    
    @PostMapping(value="/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
            return HttpResult.ok(this.sysUserService.findPage(pageRequest));
    }
    
    /**
     * 修改登录用户密码
     */
    @ApiOperation(value = "修改登录用户密码", notes = "修改登录用户密码")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "password", value = "老新密码", required = true, dataType = "String"),
        @ApiImplicitParam(name = "newPassword", value = "新密码", required = true, dataType = "String")
    })
    @GetMapping("/updatePassword/{password}/{newPassword}")
    public HttpResult updatePassword(@PathVariable("password") String password, @PathVariable("newPassword") String newPassword) {
        SysUser user = ShiroUtils.getUser();
        if (user != null && password != null && newPassword != null) {
            String oldPassword = PasswordUtils.encrypte(password, user.getSalt());
            if (!oldPassword.equals(user.getPassword())) {
                return HttpResult.error("原密码不正确");
            }
            user.setPassword(PasswordUtils.encrypte(newPassword, user.getSalt()));
            HttpResult.ok(this.sysUserService.save(user));
        }
        return HttpResult.error();
    }
    
}
