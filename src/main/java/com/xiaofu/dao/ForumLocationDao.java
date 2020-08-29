package com.xiaofu.dao;

import org.springframework.stereotype.Repository;

import com.xiaofu.domain.dto.ForumLocation;
@Repository
public interface ForumLocationDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ForumLocation record);

    int insertSelective(ForumLocation record);

    ForumLocation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ForumLocation record);

    int updateByPrimaryKey(ForumLocation record);
}