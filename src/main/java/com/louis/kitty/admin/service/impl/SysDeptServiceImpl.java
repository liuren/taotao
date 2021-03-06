package com.louis.kitty.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.louis.kitty.admin.dao.SysDeptMapper;
import com.louis.kitty.admin.model.SysDept;
import com.louis.kitty.admin.page.MybatisPageHelper;
import com.louis.kitty.admin.page.PageRequest;
import com.louis.kitty.admin.page.PageResult;
import com.louis.kitty.admin.service.SysDeptService;

@Service
public class SysDeptServiceImpl implements SysDeptService {

    @Autowired
    private SysDeptMapper sysDeptMapper;
    
    @Override
    public int save(SysDept record) {
        if (record.getId() == null || record.getId() == 0) {
            return this.sysDeptMapper.insertSelective(record);
        }
        return this.sysDeptMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int delete(SysDept record) {
        return this.sysDeptMapper.deleteByPrimaryKey(record.getId());
    }

    @Override
    public int delete(List<SysDept> records) {
        for (SysDept record : records) {
            delete(record);
        }
        return 1;
    }

    @Override
    public SysDept findById(Long id) {
        return this.sysDeptMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest, sysDeptMapper);
    }

    @Override
    public List<SysDept> findTree() {
        List<SysDept> sysDepts = new ArrayList<>();
        List<SysDept> depts = this.sysDeptMapper.findAll();
        for (SysDept dept : depts) {
            if (dept.getParentId() == null || dept.getParentId() == 0) {
                dept.setLevel(0);
                sysDepts.add(dept);
            }
        }
        findChildren(sysDepts, depts);
        return sysDepts;
    }
    
    private void findChildren(List<SysDept> sysDepts, List<SysDept> depts) {
        for (SysDept sysDept : sysDepts) {
                List<SysDept> children = new ArrayList<>();
                for (SysDept dept : depts) {
                        if (sysDept.getId() != null && sysDept.getId().equals(dept.getParentId())) {
                                dept.setParentName(dept.getName());
                                dept.setLevel(sysDept.getLevel() + 1);
                                children.add(dept);
                        }
                }
                sysDept.setChildren(children);
                findChildren(children, depts);
        }
}
}
