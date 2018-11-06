package com.louis.kitty.admin.page;

public class PageRequest {

    /**
     * 当前页码
     */
    private int pageNum;
    /**
     * 每页数量
     */
    private int pageSize;
    
    public int getPageNum() {
        return pageNum;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    
    
}
