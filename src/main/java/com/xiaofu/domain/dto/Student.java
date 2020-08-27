package com.xiaofu.domain.dto;

import java.util.Date;

public class Student {
    private Integer id;

    private Boolean gender;

    private String mobile;

    private String nickName;

    private String qqAppletOpenid;

    private String qqUnionid;

    private String qqAvatarUrl;

    private Boolean status;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getQqAppletOpenid() {
        return qqAppletOpenid;
    }

    public void setQqAppletOpenid(String qqAppletOpenid) {
        this.qqAppletOpenid = qqAppletOpenid == null ? null : qqAppletOpenid.trim();
    }

    public String getQqUnionid() {
        return qqUnionid;
    }

    public void setQqUnionid(String qqUnionid) {
        this.qqUnionid = qqUnionid == null ? null : qqUnionid.trim();
    }

    public String getQqAvatarUrl() {
        return qqAvatarUrl;
    }

    public void setQqAvatarUrl(String qqAvatarUrl) {
        this.qqAvatarUrl = qqAvatarUrl == null ? null : qqAvatarUrl.trim();
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}