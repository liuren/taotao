package com.louis.kitty.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.louis.kitty.admin.model.SysMenu;
import com.louis.kitty.admin.result.HttpResult;
import com.louis.kitty.admin.service.SysMenuService;

@RestController
@RequestMapping("menu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;
    
    @PostMapping(value = "/save")
    public HttpResult save(@RequestBody SysMenu record) {
        return HttpResult.ok(this.sysMenuService.save(record));
    }
    
    @PostMapping(value = "/delete")
    public HttpResult delete(@RequestBody SysMenu record) {
        return HttpResult.ok(this.sysMenuService.delete(record));
    }
    
    @GetMapping(value = "/findNavTree/{userName}")
    public HttpResult findNavTree(@PathVariable("userName") String userName) {
        return HttpResult.ok(this.sysMenuService.findTree(userName,1));
    }
    
    @GetMapping(value = "/findMenuTree")
    public HttpResult findMenuTree() {
        return HttpResult.ok(this.sysMenuService.findTree(null,0));
    }
    
}
