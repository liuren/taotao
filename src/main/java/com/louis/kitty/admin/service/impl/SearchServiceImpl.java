/**   
 * @Title: SearchServiceImpl.java 
 * @Package com.louis.lion.admin.service.impl 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author lr
 * @date 2018年12月5日 下午5:06:03 
 * @version V1.0.0   
 */
package com.louis.kitty.admin.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.louis.kitty.admin.dao.SearchMapper;
import com.louis.kitty.admin.model.SearchUser;
import com.louis.kitty.admin.service.SearchService;

/** 
 * @ClassName: SearchServiceImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author lr
 * @date 2018年12月5日 下午5:06:03 
 *  
 */
@Service
public class SearchServiceImpl implements SearchService{

    @Autowired
    private SearchMapper searchMapper;
    
    @Override
    public boolean insert(SearchUser entity) {
            boolean falg=false;
            try{
                searchMapper.save(entity);
                    falg=true;
            }catch(Exception e){
                    e.printStackTrace();
            }
            return falg;
    }
    
    @Override
    public List<SearchUser> search(String searchContent){
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(searchContent);
        System.out.println("查询的语句:"+builder);
        Iterable<SearchUser> searchResult = searchMapper.search(builder);
        Iterator<SearchUser> iterator = searchResult.iterator();
        List<SearchUser> list=new ArrayList<SearchUser>();
        while (iterator.hasNext()) {
          list.add(iterator.next());
        }
     return list;
    }
}
