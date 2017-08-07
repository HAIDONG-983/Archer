package com.ylink.wfms.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ylink.wfms.constant.WfmsEnum;

import java.util.Date;

import static com.sun.tools.doclets.formats.html.markup.HtmlStyle.description;

/**
 * Created by yukunpeng on 2017/8/1.
 */
public class ProcessInstanceVo extends PageInfo{
    private String processInstanceId;
    private String processDefinitionName;
    private String processDefinitionId;
    private Integer processDefinitionVersion;
    private WfmsEnum isEnded;
    private String parentId;
    private String description;
    private Date startTime;
    private Date endTime;

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getProcessDefinitionName() {
        return processDefinitionName;
    }

    public void setProcessDefinitionName(String processDefinitionName) {
        this.processDefinitionName = processDefinitionName;
    }

    public Integer getProcessDefinitionVersion() {
        return processDefinitionVersion;
    }

    public void setProcessDefinitionVersion(Integer processDefinitionVersion) {
        this.processDefinitionVersion = processDefinitionVersion;
    }

    public WfmsEnum isEnded() {
        return isEnded;
    }

    public void setEnded(WfmsEnum ended) {
        isEnded = ended;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }
}
