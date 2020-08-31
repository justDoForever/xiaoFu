package com.xiaofu.domain.dto;

import java.util.Date;

public class ForumPostsRelay {
    private Integer id;

    private Integer postsId;

    private Integer studentId;

    private Boolean deleted;

    private Date createTime;

    private Date updateTime;

    private String content;

    public ForumPostsRelay() {
    }

    public ForumPostsRelay(Integer id, Integer postsId, Integer studentId, Boolean deleted, Date createTime, Date updateTime, String content) {
        this.id = id;
        this.postsId = postsId;
        this.studentId = studentId;
        this.deleted = deleted;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.content = content;
    }

    public ForumPostsRelay(Integer id, Boolean deleted, Date updateTime) {
        this.id = id;
        this.deleted = deleted;
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPostsId() {
        return postsId;
    }

    public void setPostsId(Integer postsId) {
        this.postsId = postsId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
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
        this.content = content;
    }
}