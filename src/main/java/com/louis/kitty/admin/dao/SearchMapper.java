/**   
 * @Title: SearchMapper.java 
 * @Package com.louis.lion.admin.dao 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author lr
 * @date 2018年12月5日 下午5:03:17 
 * @version V1.0.0   
 */
package com.louis.kitty.admin.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.louis.kitty.admin.model.SearchUser;

/** 
 * @ClassName: SearchMapper 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author lr
 * @date 2018年12月5日 下午5:03:17 
 *  
 */
public interface SearchMapper extends ElasticsearchRepository<SearchUser, Long>{

}
