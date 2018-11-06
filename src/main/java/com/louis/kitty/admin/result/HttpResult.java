/**   
 * @Title: HttpResult.java 
 * @Package com.louis.kitty.admin.result 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author lr
 * @date 2018年11月6日 上午10:32:30 
 * @version V1.0.0   
 */
package com.louis.kitty.admin.result;

/** 
 * @ClassName: HttpResult 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author lr
 * @date 2018年11月6日 上午10:32:30 
 *  
 */
public class HttpResult {

    private int code;
    private String msg;
    private Object data;
    
    public static HttpResult error() {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
    }
    
    public static HttpResult error(String msg) {
        return  error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
    }
    
    public static HttpResult error(int code, String msg) {
        HttpResult r = new HttpResult();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }
    
    public static HttpResult ok() {
        return new HttpResult();
    }
    
    public static HttpResult ok(Object data) {
        HttpResult r = new HttpResult();
        r.setData(data);
        return r;
    }
    
    public static HttpResult ok(String msg) {
        HttpResult r = new HttpResult();
        r.setMsg(msg);
        return r;
    }
    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(Object data) {
        this.data = data;
    }
    
    
}
