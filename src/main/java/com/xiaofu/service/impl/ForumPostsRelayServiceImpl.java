package com.xiaofu.service.impl;

import com.xiaofu.dao.ForumPostsRelayDao;
import com.xiaofu.domain.dto.ForumPostsRelay;
import com.xiaofu.domain.enums.ResponseEnum;
import com.xiaofu.domain.response.ResultInfo;
import com.xiaofu.service.ForumPostsRelayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ForumPostsRelayServiceImpl implements ForumPostsRelayService {
    @Autowired
    private ForumPostsRelayDao relayDao;
    @Override
    public ResultInfo add(ForumPostsRelay relay) {
        if(relayDao.insertSelective(relay) == 1){
            return new ResultInfo(ResponseEnum.SUCCESS,"回复成功");
        }
        return new ResultInfo(ResponseEnum.CUSTOM_ERROR,"回复失败");
    }

    @Override
    public ResultInfo delete(String id) {
        if(relayDao.updateByPrimaryKeySelective(new ForumPostsRelay(Integer.parseInt(id),true,new Date())) == 1){
            return new ResultInfo(ResponseEnum.SUCCESS,"删除成功");
        }
        return new ResultInfo(ResponseEnum.CUSTOM_ERROR,"删除失败");
    }

    @Override
    public List<ForumPostsRelay> select() {
        return relayDao.getAll();
    }

    @Override
    public ResultInfo update(ForumPostsRelay relay) {
        if(relayDao.updateByPrimaryKeySelective(relay) == 1){
            return new ResultInfo(ResponseEnum.SUCCESS,"修改成功");
        }
        return new ResultInfo(ResponseEnum.CUSTOM_ERROR,"修改失败");
    }
}
