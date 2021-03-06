package com.xiaofu.domain.dto;

import java.util.Date;
import java.util.List;

public class Forums {
    private Integer id;

    private Integer studentId;

    private String viewAccount;

    private String relayAccount;

    private String likeAccount;

    private String deleted;

    private Date createTime;

    private Date updateTime;

    private String content;
    private List<ForumImage> images;
    private ForumLocation location;
    private ForumVideo video;

    public List<ForumImage> getImages() {
        return images;
    }

    public void setImages(List<ForumImage> images) {
        this.images = images;
    }

    public ForumLocation getLocation() {
        return location;
    }

    public void setLocation(ForumLocation location) {
        this.location = location;
    }

    public Forums() {
    }

    public ForumVideo getVideo() {
        return video;
    }

    public void setVideo(ForumVideo video) {
        this.video = video;
    }

    public Forums(Integer id, Integer studentId, String viewAccount, String relayAccount, String likeAccount, String deleted, Date createTime, Date updateTime, String content) {
        this.id = id;
        this.studentId = studentId;
        this.viewAccount = viewAccount;
        this.relayAccount = relayAccount;
        this.likeAccount = likeAccount;
        this.deleted = deleted;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getViewAccount() {
        return viewAccount;
    }

    public void setViewAccount(String viewAccount) {
        this.viewAccount = viewAccount == null ? null : viewAccount.trim();
    }

    public String getRelayAccount() {
        return relayAccount;
    }

    public void setRelayAccount(String relayAccount) {
        this.relayAccount = relayAccount == null ? null : relayAccount.trim();
    }

    public String getLikeAccount() {
        return likeAccount;
    }

    public void setLikeAccount(String likeAccount) {
        this.likeAccount = likeAccount == null ? null : likeAccount.trim();
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted == null ? null : deleted.trim();
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}
