/**   
 * @Title: PageUtils.java 
 * @Package com.louis.kitty.admin.page 
 * @Description: 将分页信息封装到统一的接口
 * @author lr
 * @date 2018年11月6日 上午10:14:33 
 * @version V1.0.0   
 */
package com.louis.kitty.admin.page;

import com.github.pagehelper.PageInfo;

/** 
 * @ClassName: PageUtils 
 * @Description: 将分页信息封装到统一的接口
 * @author lr
 * @date 2018年11月6日 上午10:14:33 
 *  
 */
public class PageUtils {

    public static PageResult getPageResult(PageRequest pageRequest, PageInfo<?> pageInfo) {
        PageResult pageResult = new PageResult();
        pageResult.setPageNum(pageInfo.getPageNum());
        pageResult.setPageSize(pageInfo.getPageSize());
        pageResult.setTotalSize(pageInfo.getTotal());
        pageResult.setTotalPages(pageInfo.getPages());
        pageResult.setContent(pageInfo.getList());
        return pageResult;
    }
}
