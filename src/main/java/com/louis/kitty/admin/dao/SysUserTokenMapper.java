package com.louis.kitty.admin.dao;

import com.louis.kitty.admin.model.SysUserToken;

public interface SysUserTokenMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_token
     *
     * @mbg.generated Mon Oct 29 15:38:25 CST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_token
     *
     * @mbg.generated Mon Oct 29 15:38:25 CST 2018
     */
    int insert(SysUserToken record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_token
     *
     * @mbg.generated Mon Oct 29 15:38:25 CST 2018
     */
    int insertSelective(SysUserToken record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_token
     *
     * @mbg.generated Mon Oct 29 15:38:25 CST 2018
     */
    SysUserToken selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_token
     *
     * @mbg.generated Mon Oct 29 15:38:25 CST 2018
     */
    int updateByPrimaryKeySelective(SysUserToken record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_token
     *
     * @mbg.generated Mon Oct 29 15:38:25 CST 2018
     */
    int updateByPrimaryKey(SysUserToken record);
}