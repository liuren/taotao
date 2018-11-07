package com.louis.kitty.admin.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.louis.kitty.admin.dao.SysUserTokenMapper;
import com.louis.kitty.admin.model.SysUserToken;
import com.louis.kitty.admin.page.MybatisPageHelper;
import com.louis.kitty.admin.page.PageRequest;
import com.louis.kitty.admin.page.PageResult;
import com.louis.kitty.admin.service.SysUserTokenService;
import com.louis.kitty.admin.utils.TokenGenerator;


@Service
public class SysUserTokenServiceImpl implements SysUserTokenService {

    @Autowired
    private SysUserTokenMapper sysUserTokenMapper;
    // 12小时后过期
    private final static int EXPIRE = 3600 * 12;
    
    @Override
    public int save(SysUserToken record) {
        if (record.getId() == null || record.getId() == 0) {
            return this.sysUserTokenMapper.insertSelective(record);
        }
        return this.sysUserTokenMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int delete(SysUserToken record) {
        return this.sysUserTokenMapper.deleteByPrimaryKey(record.getId());
    }

    @Override
    public int delete(List<SysUserToken> records) {
        for (SysUserToken record : records) {
            delete(record);
        }
        return 1;
    }

    @Override
    public SysUserToken findById(Long id) {
        return this.sysUserTokenMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest, sysUserTokenMapper);
    }

    @Override
    public SysUserToken findByUserId(Long userId) {
        return this.sysUserTokenMapper.findByUserId(userId);
    }

    @Override
    public SysUserToken findByToken(String token) {
        return this.sysUserTokenMapper.findByToken(token);
    }

    @Override
    public SysUserToken createToken(long userId) {
        //生成一个token
        String token = TokenGenerator.generateToken();
        //当前时间
        Date now = new Date();
        //过期时间
        Date expireTime = new Date(now.getTime() + EXPIRE * 1000);
        //判断是否生成过token
        SysUserToken sysUserToken = findByUserId(userId);
        if (sysUserToken == null) {
            sysUserToken = new SysUserToken();
            sysUserToken.setUserId(userId);
            sysUserToken.setToken(token);
            sysUserToken.setLastUpdateTime(now);
            sysUserToken.setExpireTime(expireTime);
            //保存token，这里选择保存到数据库，也可以放到Redis或者session之类可存储地方
            save(sysUserToken);
        } else {
            sysUserToken.setToken(token);
            sysUserToken.setLastUpdateTime(now);
            sysUserToken.setExpireTime(expireTime);
            save(sysUserToken);
        }
        return sysUserToken;
    }
	
}
