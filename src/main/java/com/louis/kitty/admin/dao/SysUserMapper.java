package com.louis.kitty.admin.dao;

import java.util.List;

import com.louis.kitty.admin.model.SysUser;

public interface SysUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbg.generated Mon Oct 29 15:38:25 CST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbg.generated Mon Oct 29 15:38:25 CST 2018
     */
    int insert(SysUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbg.generated Mon Oct 29 15:38:25 CST 2018
     */
    int insertSelective(SysUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbg.generated Mon Oct 29 15:38:25 CST 2018
     */
    SysUser selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbg.generated Mon Oct 29 15:38:25 CST 2018
     */
    int updateByPrimaryKeySelective(SysUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbg.generated Mon Oct 29 15:38:25 CST 2018
     */
    int updateByPrimaryKey(SysUser record);
    
    /**
     * 查询全部
     * @return
     */
    List<SysUser> selectAll();
}