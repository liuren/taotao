/**   
 * @Title: CurdService.java 
 * @Package com.louis.kitty.core.service 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author lr
 * @date 2018年11月6日 上午10:42:23 
 * @version V1.0.0   
 */
package com.louis.kitty.core.service;

import java.util.List;

import com.louis.kitty.admin.page.PageRequest;
import com.louis.kitty.admin.page.PageResult;

/** 
 * @ClassName: CurdService 
 * @Description: 通用CURD操作
 * @author lr
 * @date 2018年11月6日 上午10:42:23 
 *  
 */
public interface CurdService<T> {
    /**
     * @Title: save 
     * @Description: 保存操作
     * @param @param record
     * @param @return  参数说明 
     * @return int    返回类型 
     * @throws
     */
    int save(T record);
    /**
     * @Title: upate 
     * @Description: 更新操作
     * @param @param record
     * @param @return  参数说明 
     * @return int    返回类型 
     * @throws
     */
    int upate(T record);
    /**
     * @Title: delete 
     * @Description: 删除操作
     * @param @param record
     * @param @return  参数说明 
     * @return int    返回类型 
     * @throws
     */
    int delete(T record);
    /**
     * @Title: delete 
     * @Description: 批量删除操作
     * @param @param records
     * @param @return  参数说明 
     * @return int    返回类型 
     * @throws
     */
    int delete(List<T> records);
    /**
     * @Title: findById 
     * @Description: 根据ID查询
     * @param @param id
     * @param @return  参数说明 
     * @return T    返回类型 
     * @throws
     */
    T findById(Long id);
    
    /**
     * @Title: findPage 
     * @Description: 分页查询
     * @param pageRequest 自定义统一分页查询要求
     * @return PageResult 自定义统一分返回类型 
     * @throws
     */
    PageResult findPage(PageRequest pageRequest);
}
