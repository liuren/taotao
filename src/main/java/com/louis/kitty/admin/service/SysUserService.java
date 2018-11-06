package com.louis.kitty.admin.service;

import java.util.List;

import com.louis.kitty.admin.model.SysUser;

public interface SysUserService {

    /**
     * @Description 根据ID查询用户
     * @param userId
     * @return
     */
    SysUser findByUserId(Long userId);
    
    /**
     * @Description 查询所有用户
     * @return
     */
    List<SysUser> findAll();
}
