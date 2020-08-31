package com.xiaofu.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.xiaofu.domain.dto.ForumPostsRelay;

import java.util.List;

@Repository
@Mapper
public interface ForumPostsRelayDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ForumPostsRelay record);

    int insertSelective(ForumPostsRelay record);

    ForumPostsRelay selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ForumPostsRelay record);

    int updateByPrimaryKeyWithBLOBs(ForumPostsRelay record);

    int updateByPrimaryKey(ForumPostsRelay record);

//    @Select("select * from forum_posts_relay")
    List<ForumPostsRelay> getAll();
}