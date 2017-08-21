package com.ylink.wfms.vo;

import java.util.HashMap;
import java.util.List;

/**
 * Created by yukunpeng on 2017/7/13.
 */
public class ActionResult<T> {
    private List<T> rows;//返回结果集
    private String status;//响应状态
    private Integer total;//总页数
    private Long records;//记录数（总数）
    private Integer pageIndex;//当前页
    private String message;//响应信息
    private PageInfo pageInfo;

    private T result;

    private HashMap<String,Object> model = new HashMap<String, Object>();

    public ActionResult(List<T> rows, String status, Long records, PageInfo pageInfo) {
        this.rows = rows;
        this.status = status;
        this.records = records;
        //Long limit=pageInfo.getLimit().longValue();
        //records % limit==0?this.total=new Long(records / limit).intValue():this.total=new Long(records / limit+1).intValue();
        //this.total=  new Long(records / limit+1).intValue();
        if (records!=null && pageInfo!=null) {
            Long limit=pageInfo.getLimit().longValue();
            if (records % limit == 0) {
                this.total = new Long(records / limit).intValue();
            } else {
                this.total = new Long(records / limit + 1).intValue();
            }
            this.pageIndex = pageInfo.getPage();
        }
    }

    public ActionResult<T> put(String key,Object value){
        this.model.put(key,value);
        return this;
    }

    public ActionResult(T t){
        this.result=t;
    }

    public ActionResult(String message, String status) {
        this.message = message;
        this.status = status;
    }

    public ActionResult() {
    }

    public List<T> getRows() { return rows; }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Long getRecords() {
        return records;
    }

    public void setRecords(Long records) {
        this.records = records;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HashMap<String, Object> getModel() {
        return model;
    }

    public void setModel(HashMap<String, Object> model) {
        this.model = model;
    }


    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}

