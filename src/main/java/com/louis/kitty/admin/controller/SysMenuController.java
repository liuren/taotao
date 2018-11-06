package com.louis.kitty.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.louis.kitty.admin.page.PageRequest;
import com.louis.kitty.admin.result.HttpResult;
import com.louis.kitty.admin.service.SysMenuService;

@RestController
@RequestMapping("menu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;
    
    /**
     * @Title: findPage 
     * @Description: TODO(这里用一句话描述这个方法的作用) 
     * @param pagequest 
     * @return HttpResult    返回类型 
     * @throws
     */
    @PostMapping(value = "/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageQuery) {
        return HttpResult.ok(this.sysMenuService.findPage(pageQuery));
    }
}
