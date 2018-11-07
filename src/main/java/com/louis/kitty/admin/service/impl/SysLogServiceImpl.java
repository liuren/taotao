package com.louis.kitty.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.louis.kitty.admin.config.ColumnFilter;
import com.louis.kitty.admin.dao.SysLogMapper;
import com.louis.kitty.admin.model.SysLog;
import com.louis.kitty.admin.page.MybatisPageHelper;
import com.louis.kitty.admin.page.PageRequest;
import com.louis.kitty.admin.page.PageResult;
import com.louis.kitty.admin.service.SysLogService;

@Service
public class SysLogServiceImpl  implements SysLogService {

    @Autowired
    private SysLogMapper sysLogMapper;
    
    @Override
    public int save(SysLog record) {
        if(record.getId() == null || record.getId() == 0) {
            return this.sysLogMapper.insertSelective(record);
        }
        return this.sysLogMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int delete(SysLog record) {
        return this.sysLogMapper.deleteByPrimaryKey(record.getId());
    }

    @Override
    public int delete(List<SysLog> records) {
        for (SysLog record : records) {
            delete(record);
        }
        return 1;
    }

    @Override
    public SysLog findById(Long id) {
        return this.sysLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        ColumnFilter columnFilter = pageRequest.getColumnFilter("userName");
        if (columnFilter != null) {
            return MybatisPageHelper.findPage(pageRequest, sysLogMapper, "findPageByUserName", columnFilter.getValue());
        }
        return MybatisPageHelper.findPage(pageRequest, sysLogMapper);
    }
}
