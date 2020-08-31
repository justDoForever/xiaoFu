package com.xiaofu.service;

import com.xiaofu.domain.dto.ForumPostsRelay;
import com.xiaofu.domain.response.ResultInfo;

import java.util.List;

public interface ForumPostsRelayService {
    ResultInfo add(ForumPostsRelay relay);

    ResultInfo delete(String id);

    List<ForumPostsRelay> select();

    ResultInfo update(ForumPostsRelay relay);
}
