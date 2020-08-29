package com.xiaofu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.xiaofu.domain.dto.ForumInfo;
import com.xiaofu.domain.response.ResultInfo;
import com.xiaofu.service.ForumService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "DemoController", tags = {"发帖接口"})
@RestController
public class ForumPostsController {

    @Autowired
    private ForumService forumService;
    @ApiOperation(value = "发一个贴子", notes = "发帖", produces = "application/json")
    @PostMapping("/createForum")
    public ResultInfo createForum(@RequestBody ForumInfo forumInfo){
            return forumService.add(forumInfo);
    }
    
}