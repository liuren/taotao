package com.louis.kitty.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.louis.kitty.admin.config.ColumnFilter;
import com.louis.kitty.admin.dao.SysDictMapper;
import com.louis.kitty.admin.model.SysDict;
import com.louis.kitty.admin.page.MybatisPageHelper;
import com.louis.kitty.admin.page.PageRequest;
import com.louis.kitty.admin.page.PageResult;
import com.louis.kitty.admin.service.SysDictService;

@Service
public class SysDictServiceImpl  implements SysDictService {

    @Autowired
    private SysDictMapper sysDictMapper;
    
    @Override
    public int save(SysDict record) {
        if (record.getId() == null || record.getId() == 0) {
            return this.sysDictMapper.insertSelective(record);
        }
        return this.sysDictMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int delete(SysDict record) {
        return this.sysDictMapper.deleteByPrimaryKey(record.getId());
    }

    @Override
    public int delete(List<SysDict> records) {
        for(SysDict record:records) {
            delete(record);
        }
        return 1;
    }

    @Override
    public SysDict findById(Long id) {
        return this.sysDictMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        ColumnFilter columnFilter = pageRequest.getColumnFilter("label");
        if(columnFilter != null) {
            return MybatisPageHelper.findPage(pageRequest, sysDictMapper, "findPageByLabel", columnFilter.getValue());
        }
        return MybatisPageHelper.findPage(pageRequest, sysDictMapper);
    }

    @Override
    public List<SysDict> findByLable(String lable) {
        return this.sysDictMapper.findByLable(lable);
    }

}
