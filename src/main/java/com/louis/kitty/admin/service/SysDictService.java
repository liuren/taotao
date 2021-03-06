package com.louis.kitty.admin.service;

import java.util.List;

import com.louis.kitty.admin.model.SysDict;
import com.louis.kitty.core.service.CurdService;

public interface SysDictService extends CurdService<SysDict>{

    /**
     * 根据名称查询
     * @param lable
     * @return
     */
    List<SysDict> findByLable(String lable);

}
