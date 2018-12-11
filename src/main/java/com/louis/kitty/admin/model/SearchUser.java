/**   
 * @Title: SearchUser.java 
 * @Package com.louis.kitty.admin.model 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author lr
 * @date 2018年12月11日 下午9:37:04 
 * @version V1.0.0   
 */
package com.louis.kitty.admin.model;

import org.springframework.data.elasticsearch.annotations.Document;

/** 
 * @ClassName: SearchUser 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author lr
 * @date 2018年12月11日 下午9:37:04 
 *  
 */
@Document(indexName = "search_user", type = "search_user")
public class SearchUser {
    
    private Long id;
    
    private String name;

    private String password;

    private String salt;

    private String email;
    
    public SearchUser() {
        super();
    }

    public SearchUser(Long id, String name, String password, String salt, String email) {
        super();
        this.id = id;
        this.name = name;
        this.password = password;
        this.salt = salt;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
