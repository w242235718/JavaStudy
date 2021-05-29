package com.shopmanager.bean;

import java.sql.Timestamp;
import java.util.Date;

public class GoodsType {
    private Integer id;
    private Integer pid;
    private String typeName;
    private Boolean isParentType;
    private Date createTime;
    private Date updateTime;

    public GoodsType() {
    }

    public GoodsType(Integer pid, String typeName, Boolean isParentType, Timestamp createTime, Timestamp updateTime) {
        this.id = null;
        this.pid = pid;
        this.typeName = typeName;
        this.isParentType = isParentType;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "GoodsType{" +
                "id=" + id +
                ", pid=" + pid +
                ", typeName='" + typeName + '\'' +
                ", isParentType=" + isParentType +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Boolean getIsParentType() {
        return isParentType;
    }

    public void setIsParentType(Boolean isParentType) {
        this.isParentType = isParentType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
