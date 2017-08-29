package com.ylink.wfms.sysmng.cfgSysRegistry.entity;

import java.util.Date;

public class CfgSysRegistry {
    /**
     * 
     * 数据库表:WFMS.CFG_SYS_REGISTRY 字段名:ID:接入系统ID
     *
     * @mbggenerated 2017-08-07 09:55:13
     */
    private String id;

    /**
     * 
     * 数据库表:WFMS.CFG_SYS_REGISTRY 字段名:CODE:接入系统代码
     *
     * @mbggenerated 2017-08-07 09:55:13
     */
    private String code;

    /**
     * 
     * 数据库表:WFMS.CFG_SYS_REGISTRY 字段名:NAME:接入系统名称
     *
     * @mbggenerated 2017-08-07 09:55:13
     */
    private String name;

    /**
     * 
     * 数据库表:WFMS.CFG_SYS_REGISTRY 字段名:STATE:状态[1:启用;0:停用]
     *
     * @mbggenerated 2017-08-07 09:55:13
     */
    private Long state;

    /**
     * 
     * 数据库表:WFMS.CFG_SYS_REGISTRY 字段名:DELETE_MARK:删除标志[0:已删除;1:正常]
     *
     * @mbggenerated 2017-08-07 09:55:13
     */
    private Long deleteMark;

    /**
     * 
     * 数据库表:WFMS.CFG_SYS_REGISTRY 字段名:CREATOR_CODE:创建人代码
     *
     * @mbggenerated 2017-08-07 09:55:13
     */
    private String creatorCode;

    /**
     * 
     * 数据库表:WFMS.CFG_SYS_REGISTRY 字段名:CREATOR_NAME:创建人名称
     *
     * @mbggenerated 2017-08-07 09:55:13
     */
    private String creatorName;

    /**
     * 
     * 数据库表:WFMS.CFG_SYS_REGISTRY 字段名:CREATE_TIME:创建时间
     *
     * @mbggenerated 2017-08-07 09:55:13
     */
    private Date createTime;

    /**
     * 
     * 数据库表:WFMS.CFG_SYS_REGISTRY 字段名:LAST_MODIFY_TIME:最后修改时间
     *
     * @mbggenerated 2017-08-07 09:55:13
     */
    private Date lastModifyTime;

    /**
     * 
     * 数据库表:WFMS.CFG_SYS_REGISTRY 字段名:LAST_MODIFIER_CODE:最后修改人代码
     *
     * @mbggenerated 2017-08-07 09:55:13
     */
    private String lastModifierCode;

    /**
     * 
     * 数据库表:WFMS.CFG_SYS_REGISTRY 字段名:LAST_MODIFIER_NAME:最后修改人名称
     *
     * @mbggenerated 2017-08-07 09:55:13
     */
    private String lastModifierName;

    /**
     * 
     * 数据库表:WFMS.CFG_SYS_REGISTRY 字段名:REMARK:备注
     *
     * @mbggenerated 2017-08-07 09:55:13
     */
    private String remark;

    /**
     * 
     * 数据库表:WFMS.CFG_SYS_REGISTRY 字段名:RESERVED1:保留字段1
     *
     * @mbggenerated 2017-08-07 09:55:13
     */
    private String reserved1;

    /**
     * 
     * 数据库表:WFMS.CFG_SYS_REGISTRY 字段名:RESERVED2:保留字段2
     *
     * @mbggenerated 2017-08-07 09:55:13
     */
    private String reserved2;

    /**
     * 数据库表: WFMS.CFG_SYS_REGISTRY 字段名: ID 注释: 接入系统ID
     *
     *
     * @mbggenerated 2017-08-07 09:55:13
     */
    public String getId() {
        return id;
    }

    /**
     * 数据库表: WFMS.CFG_SYS_REGISTRY 字段名: ID 注释: 接入系统ID
     *
     *
     * @mbggenerated 2017-08-07 09:55:13
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 数据库表: WFMS.CFG_SYS_REGISTRY 字段名: CODE 注释: 接入系统代码
     *
     *
     * @mbggenerated 2017-08-07 09:55:13
     */
    public String getCode() {
        return code;
    }

    /**
     * 数据库表: WFMS.CFG_SYS_REGISTRY 字段名: CODE 注释: 接入系统代码
     *
     *
     * @mbggenerated 2017-08-07 09:55:13
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 数据库表: WFMS.CFG_SYS_REGISTRY 字段名: NAME 注释: 接入系统名称
     *
     *
     * @mbggenerated 2017-08-07 09:55:13
     */
    public String getName() {
        return name;
    }

    /**
     * 数据库表: WFMS.CFG_SYS_REGISTRY 字段名: NAME 注释: 接入系统名称
     *
     *
     * @mbggenerated 2017-08-07 09:55:13
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 数据库表: WFMS.CFG_SYS_REGISTRY 字段名: STATE 注释: 状态[1:启用;0:停用]
     *
     *
     * @mbggenerated 2017-08-07 09:55:13
     */
    public Long getState() {
        return state;
    }

    /**
     * 数据库表: WFMS.CFG_SYS_REGISTRY 字段名: STATE 注释: 状态[1:启用;0:停用]
     *
     *
     * @mbggenerated 2017-08-07 09:55:13
     */
    public void setState(Long state) {
        this.state = state;
    }

    /**
     * 数据库表: WFMS.CFG_SYS_REGISTRY 字段名: DELETE_MARK 注释: 删除标志[0:已删除;1:正常]
     *
     *
     * @mbggenerated 2017-08-07 09:55:13
     */
    public Long getDeleteMark() {
        return deleteMark;
    }

    /**
     * 数据库表: WFMS.CFG_SYS_REGISTRY 字段名: DELETE_MARK 注释: 删除标志[0:已删除;1:正常]
     *
     *
     * @mbggenerated 2017-08-07 09:55:13
     */
    public void setDeleteMark(Long deleteMark) {
        this.deleteMark = deleteMark;
    }

    /**
     * 数据库表: WFMS.CFG_SYS_REGISTRY 字段名: CREATOR_CODE 注释: 创建人代码
     *
     *
     * @mbggenerated 2017-08-07 09:55:13
     */
    public String getCreatorCode() {
        return creatorCode;
    }

    /**
     * 数据库表: WFMS.CFG_SYS_REGISTRY 字段名: CREATOR_CODE 注释: 创建人代码
     *
     *
     * @mbggenerated 2017-08-07 09:55:13
     */
    public void setCreatorCode(String creatorCode) {
        this.creatorCode = creatorCode;
    }

    /**
     * 数据库表: WFMS.CFG_SYS_REGISTRY 字段名: CREATOR_NAME 注释: 创建人名称
     *
     *
     * @mbggenerated 2017-08-07 09:55:13
     */
    public String getCreatorName() {
        return creatorName;
    }

    /**
     * 数据库表: WFMS.CFG_SYS_REGISTRY 字段名: CREATOR_NAME 注释: 创建人名称
     *
     *
     * @mbggenerated 2017-08-07 09:55:13
     */
    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    /**
     * 数据库表: WFMS.CFG_SYS_REGISTRY 字段名: CREATE_TIME 注释: 创建时间
     *
     *
     * @mbggenerated 2017-08-07 09:55:13
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 数据库表: WFMS.CFG_SYS_REGISTRY 字段名: CREATE_TIME 注释: 创建时间
     *
     *
     * @mbggenerated 2017-08-07 09:55:13
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 数据库表: WFMS.CFG_SYS_REGISTRY 字段名: LAST_MODIFY_TIME 注释: 最后修改时间
     *
     *
     * @mbggenerated 2017-08-07 09:55:13
     */
    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    /**
     * 数据库表: WFMS.CFG_SYS_REGISTRY 字段名: LAST_MODIFY_TIME 注释: 最后修改时间
     *
     *
     * @mbggenerated 2017-08-07 09:55:13
     */
    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    /**
     * 数据库表: WFMS.CFG_SYS_REGISTRY 字段名: LAST_MODIFIER_CODE 注释: 最后修改人代码
     *
     *
     * @mbggenerated 2017-08-07 09:55:13
     */
    public String getLastModifierCode() {
        return lastModifierCode;
    }

    /**
     * 数据库表: WFMS.CFG_SYS_REGISTRY 字段名: LAST_MODIFIER_CODE 注释: 最后修改人代码
     *
     *
     * @mbggenerated 2017-08-07 09:55:13
     */
    public void setLastModifierCode(String lastModifierCode) {
        this.lastModifierCode = lastModifierCode;
    }

    /**
     * 数据库表: WFMS.CFG_SYS_REGISTRY 字段名: LAST_MODIFIER_NAME 注释: 最后修改人名称
     *
     *
     * @mbggenerated 2017-08-07 09:55:13
     */
    public String getLastModifierName() {
        return lastModifierName;
    }

    /**
     * 数据库表: WFMS.CFG_SYS_REGISTRY 字段名: LAST_MODIFIER_NAME 注释: 最后修改人名称
     *
     *
     * @mbggenerated 2017-08-07 09:55:13
     */
    public void setLastModifierName(String lastModifierName) {
        this.lastModifierName = lastModifierName;
    }

    /**
     * 数据库表: WFMS.CFG_SYS_REGISTRY 字段名: REMARK 注释: 备注
     *
     *
     * @mbggenerated 2017-08-07 09:55:13
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 数据库表: WFMS.CFG_SYS_REGISTRY 字段名: REMARK 注释: 备注
     *
     *
     * @mbggenerated 2017-08-07 09:55:13
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 数据库表: WFMS.CFG_SYS_REGISTRY 字段名: RESERVED1 注释: 保留字段1
     *
     *
     * @mbggenerated 2017-08-07 09:55:13
     */
    public String getReserved1() {
        return reserved1;
    }

    /**
     * 数据库表: WFMS.CFG_SYS_REGISTRY 字段名: RESERVED1 注释: 保留字段1
     *
     *
     * @mbggenerated 2017-08-07 09:55:13
     */
    public void setReserved1(String reserved1) {
        this.reserved1 = reserved1;
    }

    /**
     * 数据库表: WFMS.CFG_SYS_REGISTRY 字段名: RESERVED2 注释: 保留字段2
     *
     *
     * @mbggenerated 2017-08-07 09:55:13
     */
    public String getReserved2() {
        return reserved2;
    }

    /**
     * 数据库表: WFMS.CFG_SYS_REGISTRY 字段名: RESERVED2 注释: 保留字段2
     *
     *
     * @mbggenerated 2017-08-07 09:55:13
     */
    public void setReserved2(String reserved2) {
        this.reserved2 = reserved2;
    }
}
