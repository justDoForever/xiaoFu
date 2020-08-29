package com.xiaofu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.xiaofu.domain.response.ResultInfo;
import com.xiaofu.service.DemoService;

/**
 * @author Yang-o_o 2020-08-24 14:10
 */
@Api(value = "DemoController", tags = {"测试接口"})
@RestController
public class DemoController {

    @Autowired
    private DemoService demoService;

    @ApiOperation(value = "test", notes = "test", produces = "application/json")
    @GetMapping("/hello")
    public ResultInfo demo() {
        return this.demoService.hello();

    }
}
