package com.xiaofu.service.impl;

import java.util.Date;
import java.util.List;

import com.xiaofu.domain.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaofu.dao.ForumImageDao;
import com.xiaofu.dao.ForumLocationDao;
import com.xiaofu.dao.ForumPostsDao;
import com.xiaofu.dao.ForumVideoDao;
import com.xiaofu.domain.enums.ResponseEnum;
import com.xiaofu.domain.response.ResultInfo;
import com.xiaofu.service.ForumService;
@Service
public class forumServiceImpl implements ForumService {

	@Autowired
	private ForumPostsDao forumPostsDao;
	@Autowired
	private ForumImageDao forumImageDao;
	@Autowired
	private ForumVideoDao forumVideoDao;
	@Autowired
	private ForumLocationDao forumLocationDao;
	@Override
	public ResultInfo add(ForumInfo forumInfo) {
		Date now = new Date();
		ForumPosts forumPost = new ForumPosts(forumInfo.getStudentId(),forumInfo.getContext(),"0","0","0","0",now,now);
		if(forumPostsDao.insertSelective(forumPost) == 1) {
			Integer id = forumPost.getId();
			for (String imagUrl : forumInfo.getImageUrl()) {
				forumImageDao.insertSelective(new ForumImage(id,imagUrl,false,now,now));
			}
			
			forumVideoDao.insertSelective(new ForumVideo(id,forumInfo.getVideoUrl(),false,now,now));
			forumLocationDao.insertSelective(new ForumLocation(id,forumInfo.getLocation().getLatitude(),forumInfo.getLocation().getLongitude(),false,now,now));
			return new ResultInfo<>(ResponseEnum.SUCCESS,"发帖成功");
		}
		
		return new ResultInfo<>(ResponseEnum.CUSTOM_ERROR, "发帖失败");
	}

	@Override
	public ResultInfo delete(String forumId) {
		//更新时间
		Date now = new Date();
		//根据ID 更新帖子delete为1
		forumPostsDao.updateByPrimaryKeySelective(new ForumPosts(Integer.parseInt(forumId),"1",now));
		//根据ID 更新位置 所有图片 videoURL 的delete为1
		forumLocationDao.updateByPrimaryKeySelective(new ForumLocation(Integer.parseInt(forumId),true,now));
		forumVideoDao.updateByPrimaryKeySelective(new ForumVideo(Integer.parseInt(forumId),true,now));
		forumImageDao.updateByPrimaryKeySelective(new ForumImage(Integer.parseInt(forumId),true,now));
		forumPostsDao.deleteById(Integer.parseInt(forumId));
		return new ResultInfo<>(ResponseEnum.SUCCESS,"删除成功");
	}

	@Override
	public ResultInfo update(ForumInfo forumInfo) {
		//更新时间
		Date now = new Date();
		//更新帖子
		forumPostsDao.updateByPrimaryKeySelective(new ForumPosts(forumInfo.getId(),now,forumInfo.getContext()));
		//更新视频URL
		forumVideoDao.updateByPrimaryKeySelective(new ForumVideo(forumInfo.getId(),forumInfo.getVideoUrl(),now));
		//先删除图片， 再插入图片
		forumImageDao.updateByPrimaryKeySelective(new ForumImage(forumInfo.getId(),true,now));

		for (String imagUrl : forumInfo.getImageUrl()) {
			forumImageDao.insertSelective(new ForumImage(forumInfo.getId(),imagUrl,false,now,now));
		}
		return new ResultInfo<>(ResponseEnum.SUCCESS,"修改成功");
	}

	@Override
	public List<Forums> getForums() {

		return forumPostsDao.selectForums();
	}


}
