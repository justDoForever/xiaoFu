package com.xiaofu.dao;

import org.springframework.stereotype.Repository;

import com.xiaofu.domain.dto.ForumPosts;
@Repository
public interface ForumPostsDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ForumPosts record);

    int insertSelective(ForumPosts record);

    ForumPosts selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ForumPosts record);

    int updateByPrimaryKeyWithBLOBs(ForumPosts record);

    int updateByPrimaryKey(ForumPosts record);
}