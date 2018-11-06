package com.louis.kitty.admin.dao;

import java.util.List;

import com.louis.kitty.admin.model.SysMenu;

public interface SysMenuMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu
     *
     * @mbg.generated Mon Oct 29 15:38:25 CST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu
     *
     * @mbg.generated Mon Oct 29 15:38:25 CST 2018
     */
    int insert(SysMenu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu
     *
     * @mbg.generated Mon Oct 29 15:38:25 CST 2018
     */
    int insertSelective(SysMenu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu
     *
     * @mbg.generated Mon Oct 29 15:38:25 CST 2018
     */
    SysMenu selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu
     *
     * @mbg.generated Mon Oct 29 15:38:25 CST 2018
     */
    int updateByPrimaryKeySelective(SysMenu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu
     *
     * @mbg.generated Mon Oct 29 15:38:25 CST 2018
     */
    int updateByPrimaryKey(SysMenu record);
    
    List<SysMenu> findPage();
}