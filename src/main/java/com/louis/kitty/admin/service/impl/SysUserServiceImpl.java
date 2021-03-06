package com.louis.kitty.admin.service.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.louis.kitty.admin.config.ColumnFilter;
import com.louis.kitty.admin.dao.SysRoleMapper;
import com.louis.kitty.admin.dao.SysUserMapper;
import com.louis.kitty.admin.dao.SysUserRoleMapper;
import com.louis.kitty.admin.model.SysMenu;
import com.louis.kitty.admin.model.SysRole;
import com.louis.kitty.admin.model.SysUser;
import com.louis.kitty.admin.model.SysUserRole;
import com.louis.kitty.admin.page.MybatisPageHelper;
import com.louis.kitty.admin.page.PageRequest;
import com.louis.kitty.admin.page.PageResult;
import com.louis.kitty.admin.service.SysMenuService;
import com.louis.kitty.admin.service.SysUserService;

@Service
public class SysUserServiceImpl  implements SysUserService {

        @Autowired
        private SysUserMapper sysUserMapper;
        @Autowired
        private SysMenuService sysMenuService;
        @Autowired
        private SysUserRoleMapper sysUserRoleMapper;
        @Autowired
        private SysRoleMapper sysRoleMapper;

        @Transactional
        @Override
        public int save(SysUser record) {
                Long id = null;
                if(record.getId() == null || record.getId() == 0) {
                        // 新增用户
                        sysUserMapper.insertSelective(record);
                        id = record.getId();
                } else {
                        // 更新用户信息
                        sysUserMapper.updateByPrimaryKeySelective(record);
                }
                // 更新用户角色
                if(id != null && id == 0) {
                        return 1;
                }
                if(id != null) {
                        for(SysUserRole sysUserRole:record.getUserRoles()) {
                                sysUserRole.setUserId(id);
                        }
                } else {
                        sysUserRoleMapper.deleteByUserId(record.getId());
                }
                for(SysUserRole sysUserRole:record.getUserRoles()) {
                        sysUserRoleMapper.insertSelective(sysUserRole);
                }
                return 1;
        }

        @Override
        public int delete(SysUser record) {
                return sysUserMapper.deleteByPrimaryKey(record.getId());
        }

        @Override
        public int delete(List<SysUser> records) {
                for(SysUser record:records) {
                        delete(record);
                }
                return 1;
        }

        @Override
        public SysUser findById(Long id) {
                return sysUserMapper.selectByPrimaryKey(id);
        }
        
        @Override
        public SysUser findByName(String name) {
                return sysUserMapper.findByName(name);
        }
        
        @Override
        public PageResult findPage(PageRequest pageRequest) {
                PageResult pageResult = null;
                ColumnFilter columnFilter = pageRequest.getColumnFilter("name");
                if(columnFilter != null && columnFilter.getValue() != null) {
                        pageResult = MybatisPageHelper.findPage(pageRequest, sysUserMapper, "findPageByName", columnFilter.getValue());
                } else {
                        pageResult = MybatisPageHelper.findPage(pageRequest, sysUserMapper);
                }
                // 加载用户角色信息
                findUserRoles(pageResult);
                return pageResult;
        }

        /**
         * 加载用户角色
         * @param pageResult
         */
        private void findUserRoles(PageResult pageResult) {
                List<?> content = pageResult.getContent();
                for(Object object:content) {
                        SysUser sysUser = (SysUser) object;
                        List<SysUserRole> userRoles = findUserRoles(sysUser.getId());
                        sysUser.setUserRoles(userRoles);
                        sysUser.setRoleNames(getRoleNames(userRoles));
                }
        }

        private String getRoleNames(List<SysUserRole> userRoles) {
                StringBuilder sb = new StringBuilder();
                for(Iterator<SysUserRole> iter=userRoles.iterator(); iter.hasNext();) {
                        SysUserRole userRole = iter.next();
                        SysRole sysRole = sysRoleMapper.selectByPrimaryKey(userRole.getRoleId());
                        if(sysRole == null) {
                                continue ;
                        }
                        sb.append(sysRole.getRemark());
                        if(iter.hasNext()) {
                                sb.append(", ");
                        }
                }
                return sb.toString();
        }

        @Override
        public Set<String> findPermissions(String userName) {   
                Set<String> perms = new HashSet<>();
                List<SysMenu> sysMenus = sysMenuService.findByUser(userName);
                for(SysMenu sysMenu:sysMenus) {
                        perms.add(sysMenu.getPerms());
                }
                return perms;
        }

        @Override
        public List<SysUserRole> findUserRoles(Long userId) {
                return sysUserRoleMapper.findUserRoles(userId);
        }
}