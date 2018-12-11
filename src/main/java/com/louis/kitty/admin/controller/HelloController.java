package com.louis.kitty.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.louis.kitty.admin.model.SearchUser;
import com.louis.kitty.admin.model.SysUser;
import com.louis.kitty.admin.result.HttpResult;
import com.louis.kitty.admin.service.SearchService;
import com.louis.kitty.admin.service.SysUserService;
import com.louis.kitty.admin.utils.PasswordUtils;

@RestController
@RequestMapping("hello")
public class HelloController {
    @Autowired
    private SysUserService sysUserService;
    
    @Autowired
    private SearchService searchService;
    
    @GetMapping(value="hello")
    public Object hello() {
        return "hello Lion!";
    }
    
    @PostMapping(value="save")
    public HttpResult saveUser(@RequestBody SysUser record) {
    if(sysUserService.findByName(record.getName()) != null) {
            return HttpResult.error("用户名已存在!");
    }
    String salt = PasswordUtils.getSalt();
    String password = PasswordUtils.encrypte(record.getPassword(), salt);
    record.setSalt(salt);
    record.setPassword(password);
    return HttpResult.ok(sysUserService.save(record));
    }
    
    @GetMapping(value="/findByName")
    public HttpResult findByUserName(@RequestParam String name) {
            return HttpResult.ok(sysUserService.findByName(name));
    }
    
    @PostMapping("/createUser")
    public boolean createUser(@RequestBody SearchUser user) {
        return searchService.insert(user);
    }
    
    @GetMapping("searchName")
    public HttpResult search(@RequestParam(value = "name") String name) {
      List<SearchUser> list = searchService.search(name);
      return HttpResult.ok(list);
    }
}
