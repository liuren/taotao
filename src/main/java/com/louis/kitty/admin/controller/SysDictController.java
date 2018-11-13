package com.louis.kitty.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.louis.kitty.admin.model.SysDict;
import com.louis.kitty.admin.page.PageRequest;
import com.louis.kitty.admin.result.HttpResult;
import com.louis.kitty.admin.service.SysDictService;

@RestController
@RequestMapping("dict")
public class SysDictController {

    @Autowired
    private SysDictService sysDictService;
    
    @PostMapping(value = "/save")
    public HttpResult save(@RequestBody SysDict record) {
        return HttpResult.ok(this.sysDictService.save(record));
    }
    
    @PostMapping(value = "/delete")
    public HttpResult delete(@RequestBody List<SysDict> records) {
        return HttpResult.ok(this.sysDictService.delete(records));
    }
    
    @PostMapping(value = "/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        return HttpResult.ok(this.sysDictService.findPage(pageRequest));
    }
    
    @GetMapping(value = "/findByLable")
    public HttpResult findByLable(@RequestParam String lable) {
        return HttpResult.ok(this.sysDictService.findByLable(lable));
    }
}
