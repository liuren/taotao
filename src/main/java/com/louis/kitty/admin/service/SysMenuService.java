package com.louis.kitty.admin.service;

import com.louis.kitty.admin.model.SysMenu;
import com.louis.kitty.admin.page.PageRequest;
import com.louis.kitty.admin.page.PageResult;
import com.louis.kitty.core.service.CurdService;

public interface SysMenuService extends CurdService<SysMenu>{

    /** 
     * @Title: findPage 
     * @Description: 分页查询接口 
     * @param @param pageQuery
     * @param @return  参数说明 
     * @return Object    返回类型 
     * @throws 
     */
    PageResult findPage(PageRequest pageRequest);

}
