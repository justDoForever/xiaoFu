package com.xiaofu.dao;

import org.springframework.stereotype.Repository;

import com.xiaofu.domain.dto.ForumVideo;
@Repository
public interface ForumVideoDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ForumVideo record);

    int insertSelective(ForumVideo record);

    ForumVideo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ForumVideo record);

    int updateByPrimaryKey(ForumVideo record);
}