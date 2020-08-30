package com.xiaofu.dao;

import com.xiaofu.domain.dto.Forums;
import com.xiaofu.domain.response.ResultInfo;
import org.springframework.stereotype.Repository;

import com.xiaofu.domain.dto.ForumPosts;

import java.util.List;

@Repository
public interface ForumPostsDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ForumPosts record);

    int insertSelective(ForumPosts record);

    ForumPosts selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ForumPosts record);

    int updateByPrimaryKeyWithBLOBs(ForumPosts record);

    int updateByPrimaryKey(ForumPosts record);

    int updateById(String forumId);

    int deleteById(Integer forumId);

    List<Forums> selectForums();
}