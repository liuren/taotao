/**   
 * @Title: MybatisPageHelper.java 
 * @Package com.louis.kitty.admin.page 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author lr
 * @date 2018年11月6日 上午10:59:42 
 * @version V1.0.0   
 */
package com.louis.kitty.admin.page;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.louis.kitty.admin.utils.ReflectionUtils;

/** 
 * @ClassName: MybatisPageHelper 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author lr
 * @date 2018年11月6日 上午10:59:42 
 *  
 */
public class MybatisPageHelper {

    private static final String findPage = "findPage";
    
    public static PageResult findPage(PageRequest pageRequest, Object mapper) {
        return findPage(pageRequest, mapper, findPage);
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static PageResult findPage(PageRequest pageRequest, Object mapper, String queryMethodName, Object... args) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        Object result = ReflectionUtils.invoke(mapper, queryMethodName, args);
        return getPageResult(pageRequest, new PageInfo((List)result));
    }

    /** 
     * @Title: getPageResult 
     * @Description: TODO(这里用一句话描述这个方法的作用) 
     * @param @param pageRequest
     * @param @param pageInfo
     * @param @return  参数说明 
     * @return PageResult    返回类型 
     * @throws 
     */
    private static PageResult getPageResult(PageRequest pageRequest, PageInfo<?> pageInfo) {
        PageResult pageResult = new PageResult();
        pageResult.setPageNum(pageInfo.getPageNum());
        pageResult.setPageSize(pageInfo.getPageSize());
        pageResult.setTotalSize(pageInfo.getTotal());
        pageResult.setTotalPages(pageInfo.getPages());
        pageResult.setContent(pageInfo.getList());
        return pageResult;
    }
}
