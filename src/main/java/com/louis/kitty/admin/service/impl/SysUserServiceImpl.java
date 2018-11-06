package com.louis.kitty.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.louis.kitty.admin.dao.SysUserMapper;
import com.louis.kitty.admin.model.SysUser;
import com.louis.kitty.admin.service.SysUserService;

@Service
public class SysUserServiceImpl implements SysUserService{

    @Autowired
    private SysUserMapper sysUserMapper;
    
    @Override
    public SysUser findByUserId(Long userId) {
        
        return sysUserMapper.selectByPrimaryKey(userId);
    }

    @Override
    public List<SysUser> findAll() {
        return sysUserMapper.selectAll();
    }

}
