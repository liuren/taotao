/**   
 * @Title: SearchService.java 
 * @Package com.louis.lion.admin.service 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author lr
 * @date 2018年12月5日 下午5:05:12 
 * @version V1.0.0   
 */
package com.louis.kitty.admin.service;

import java.util.List;

import com.louis.kitty.admin.model.SearchUser;

/** 
 * @ClassName: SearchService 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author lr
 * @date 2018年12月5日 下午5:05:12 
 *  
 */
public interface SearchService{
    boolean insert(SearchUser entity);
    List<SearchUser> search(String name);
}
