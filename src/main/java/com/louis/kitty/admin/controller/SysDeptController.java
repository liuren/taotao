package com.louis.kitty.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.louis.kitty.admin.model.SysDept;
import com.louis.kitty.admin.result.HttpResult;
import com.louis.kitty.admin.service.SysDeptService;

@RestController
@RequestMapping(value = "dept")
public class SysDeptController {

    @Autowired
    private SysDeptService sysDeptService;
    
    @PostMapping(value = "/save")
    public HttpResult save(@RequestBody SysDept record) {
        return HttpResult.ok(this.sysDeptService.save(record));
    }
    
    @PostMapping(value = "/delete")
    public HttpResult delete(@RequestBody List<SysDept> record) {
        return HttpResult.ok(this.sysDeptService.delete(record));
    }
    
    @GetMapping(value="/findTree")
    public HttpResult findTree() {
            return HttpResult.ok(this.sysDeptService.findTree());
    }
}
