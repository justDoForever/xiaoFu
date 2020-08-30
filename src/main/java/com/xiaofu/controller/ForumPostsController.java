package com.xiaofu.controller;

import com.xiaofu.domain.dto.Forums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xiaofu.domain.dto.ForumInfo;
import com.xiaofu.domain.response.ResultInfo;
import com.xiaofu.service.ForumService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

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
    @ApiOperation(value = "删除贴子", notes = "删除帖子", produces = "application/json")
    @PostMapping("/deleteForum")
    public ResultInfo deleteForum(@RequestParam("forumId") String forumId){
        return forumService.delete(forumId);
    }
    @ApiOperation(value = "修改贴子", notes = "修改帖子", produces = "application/json")
    @PostMapping("/updateForum")
    public ResultInfo updateForum(@RequestBody ForumInfo forumInfo){
        return forumService.update(forumInfo);
    }
    @ApiOperation(value = "查询贴子", notes = "查询帖子", produces = "application/json")
    @PostMapping("/getForums")
    public List<Forums> getForum(){
        return forumService.getForums();
    }
}