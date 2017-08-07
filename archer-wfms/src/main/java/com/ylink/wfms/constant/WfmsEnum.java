package com.ylink.wfms.constant;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Created by yukunpeng on 2017/7/13.
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum WfmsEnum {
    YES("是",1),NO("否",2),DEPOLYED("已部署",1),NOTDEPOLYED("未部署",2);

    private String name;
    private Integer code;


    WfmsEnum(String name, Integer code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
