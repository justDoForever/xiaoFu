package com.xiaofu.dao;

import org.springframework.stereotype.Repository;

import com.xiaofu.domain.dto.ForumImage;
@Repository
public interface ForumImageDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ForumImage record);

    int insertSelective(ForumImage record);

    ForumImage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ForumImage record);

    int updateByPrimaryKey(ForumImage record);
}