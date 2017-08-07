package com.ylink.wfms.vo;

/**
 * Created by yukunpeng on 2017/7/13.
 */
public class PageInfo {
    private Integer limit;//查询数量
    private Integer page;//页号
    private Integer start;//查询起始位置
    private Integer records;//总数

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getRecords() {
        return records;
    }

    public void setRecords(Integer records) {
        this.records = records;
    }
}
