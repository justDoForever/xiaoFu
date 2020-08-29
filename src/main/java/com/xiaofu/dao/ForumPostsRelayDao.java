package com.xiaofu.dao;

import org.springframework.stereotype.Repository;

import com.xiaofu.domain.dto.ForumPostsRelay;
@Repository
public interface ForumPostsRelayDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ForumPostsRelay record);

    int insertSelective(ForumPostsRelay record);

    ForumPostsRelay selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ForumPostsRelay record);

    int updateByPrimaryKeyWithBLOBs(ForumPostsRelay record);

    int updateByPrimaryKey(ForumPostsRelay record);
}