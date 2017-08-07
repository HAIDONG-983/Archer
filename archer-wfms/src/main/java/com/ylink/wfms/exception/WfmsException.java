package com.ylink.wfms.exception;

/**
 * Created by yukunpeng on 2017/7/14.
 */
public class WfmsException extends Exception{

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public WfmsException(String message, String code) {
        super(message);
        this.code = code;
    }
}
