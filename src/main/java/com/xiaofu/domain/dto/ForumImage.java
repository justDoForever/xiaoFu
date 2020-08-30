package com.xiaofu.domain.dto;

import java.util.Date;

public class ForumImage {
    private Integer id;

    private Integer postsId;

    private String imageUrl;

  
	private Boolean deleted;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }
    public ForumImage(Integer postsId, String imageUrl, Boolean deleted, Date createTime, Date updateTime) {
  		super();
  		this.postsId = postsId;
  		this.imageUrl = imageUrl;
  		this.deleted = deleted;
  		this.createTime = createTime;
  		this.updateTime = updateTime;
  	}

    public ForumImage(Integer postsId, Boolean deleted, Date updateTime) {
        this.postsId = postsId;
        this.deleted = deleted;
        this.updateTime = updateTime;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
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