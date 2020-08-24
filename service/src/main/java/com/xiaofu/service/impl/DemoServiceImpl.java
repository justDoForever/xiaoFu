package com.xiaofu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaofu.dao.DemoDao;
import com.xiaofu.enums.ResponseEnum;
import com.xiaofu.response.ResultInfo;
import com.xiaofu.service.DemoService;

/**
 * DemoServiceImpl.
 *
 * @author Yang-o_o 2020-08-24 14:11
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private DemoDao demoDao;

    /**
     * 测试.
     *
     * @return string
     */
    @Override
    public ResultInfo hello() {
        String hello = this.demoDao.hello();
        return new ResultInfo<>(ResponseEnum.SUCCESS, hello);
    }
}
