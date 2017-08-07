package com.ylink.wfms.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ylink.wfms.constant.WfmsEnum;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by yukunpeng on 2017/7/13.
 * 流程模板VO
 */
public class TemplateVo {
    //模板id
    private String templateId;
    //流程模板名称
    private String templateName;
    //模板创建时间
    private Date createTime;
    //模板最后修改时间
    private Date lastUpdateTime;
    //是否部署
    private WfmsEnum isDeploy;
    //部署时间
    private Date deployTime;
    //版本
    private Integer version;


    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public WfmsEnum getIsDeploy() {
        return isDeploy;
    }

    public void setIsDeploy(WfmsEnum isDeploy) {
        this.isDeploy = isDeploy;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getDeployTime() {
        return deployTime;
    }

    public void setDeployTime(Date deployTime) {
        this.deployTime = deployTime;
    }
}
