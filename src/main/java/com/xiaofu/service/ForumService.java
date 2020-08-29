package com.xiaofu.service;

import org.springframework.stereotype.Service;

import com.xiaofu.domain.dto.ForumInfo;
import com.xiaofu.domain.response.ResultInfo;

public interface ForumService {

	ResultInfo add(ForumInfo forumInfo);

}
