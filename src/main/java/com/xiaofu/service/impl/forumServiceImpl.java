package com.xiaofu.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaofu.dao.ForumImageDao;
import com.xiaofu.dao.ForumLocationDao;
import com.xiaofu.dao.ForumPostsDao;
import com.xiaofu.dao.ForumVideoDao;
import com.xiaofu.domain.dto.ForumImage;
import com.xiaofu.domain.dto.ForumInfo;
import com.xiaofu.domain.dto.ForumLocation;
import com.xiaofu.domain.dto.ForumPosts;
import com.xiaofu.domain.dto.ForumVideo;
import com.xiaofu.domain.dto.Location;
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

}
