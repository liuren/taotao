package com.louis.kitty.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.louis.kitty.admin.service.SysUserService;

@RestController
@RequestMapping("user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;
    
    @GetMapping(value = "/findByUserId/{userId}")
    public Object findByUserId(@PathVariable("userId") Long userId) {
        return this.sysUserService.findByUserId(userId);
    }
    
    @GetMapping(value = "/findAll")
    public Object findAll() {
        return this.sysUserService.findAll();
    }
}
