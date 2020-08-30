package com.xiaofu.service;

import com.xiaofu.domain.dto.ForumInfo;
import com.xiaofu.domain.dto.Forums;
import com.xiaofu.domain.response.ResultInfo;

import java.util.List;

public interface ForumService {

	ResultInfo add(ForumInfo forumInfo);

    ResultInfo delete(String forumId);

    ResultInfo update(ForumInfo forumInfo);

    List<Forums> getForums();
}
