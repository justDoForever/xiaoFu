package com.xiaofu.domain.dto;

import java.math.BigDecimal;
import java.util.Date;

public class ForumLocation {
    private Integer id;

    private Integer postsId;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private Boolean deleted;

    private Date createTime;

    private Date updateTime;

    public ForumLocation(Integer postsId, BigDecimal longitude, BigDecimal latitude, Boolean deleted, Date createTime,
			Date updateTime) {
		super();
		this.postsId = postsId;
		this.longitude = longitude;
		this.latitude = latitude;
		this.deleted = deleted;
		this.createTime = createTime;
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

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
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