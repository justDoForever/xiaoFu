package com.xiaofu.domain.dto;

import java.util.Date;

public class ForumVideo {
    private Integer id;

    private Integer postsId;

    private String videoUrl;

    private Boolean deleted;

    private Date createTime;

    private Date updateTime;

    public ForumVideo(Integer postsId, String videoUrl, Boolean deleted, Date createTime, Date updateTime) {
		super();
		this.postsId = postsId;
		this.videoUrl = videoUrl;
		this.deleted = deleted;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

    public ForumVideo(Integer postsId, Boolean deleted, Date updateTime) {
        this.postsId = postsId;
        this.deleted = deleted;
        this.updateTime = updateTime;
    }

    public ForumVideo() {
    }

    public ForumVideo(Integer id, String videoUrl, Date updateTime) {
        this.id = id;
        this.videoUrl = videoUrl;
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

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl == null ? null : videoUrl.trim();
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
}