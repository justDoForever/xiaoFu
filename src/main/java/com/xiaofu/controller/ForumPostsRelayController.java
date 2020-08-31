package com.xiaofu.controller;

import com.xiaofu.domain.dto.ForumPostsRelay;
import com.xiaofu.domain.response.ResultInfo;
import com.xiaofu.service.ForumPostsRelayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "ForumPostsRelayController", tags = "回帖")
@RestController
public class ForumPostsRelayController {
    @Autowired
    private ForumPostsRelayService relayService;

    @ApiOperation(value = "回帖",notes = "回帖",produces = "application/json")
    @PostMapping("/relay/relayForum")
    public ResultInfo relayForum(@RequestBody ForumPostsRelay relay)
    {
        return relayService.add(relay);
    }

    @ApiOperation(value ="删除帖子",notes = "删除帖子",produces = "application/json")
    @GetMapping("/relay/deleteForum")
    public ResultInfo deleteForum(@RequestParam("id")String id)
    {
        return relayService.delete(id);
    }

    @ApiOperation(value ="查看帖子",notes = "查看帖子",produces = "application/json")
    @GetMapping("/relay/selectForum")
    public List<ForumPostsRelay> selectForum()
    {
        return relayService.select();
    }

    @ApiOperation(value ="修改帖子",notes = "修改帖子",produces = "application/json")
    @PostMapping("/relay/updateForum")
    public ResultInfo updateForum(@RequestBody ForumPostsRelay relay)
    {
        return relayService.update(relay);
    }
}
