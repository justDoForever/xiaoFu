CREATE TABLE `forum_image`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `posts_id` int(11) UNSIGNED NULL,
  `image_url` varchar(255) NULL,
  `create_time` datetime(0) NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `forum_location`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `posts_id` int(11) UNSIGNED NULL,
  `longitude` decimal(10, 7) NULL COMMENT '经度',
  `latitude` decimal(10, 7) NULL COMMENT '纬度',
  `create_time` datetime(0) NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `forum_posts`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `student_id` int(11) UNSIGNED NULL,
  `content` text NOT NULL COMMENT '帖子内容',
  `view_account` varchar(255) NOT NULL DEFAULT 0 COMMENT '查看数量',
  `relay_account` varchar(255) NOT NULL DEFAULT 0 COMMENT '回帖数量',
  `like_account` varchar(255) NOT NULL DEFAULT 0 COMMENT '点赞数量',
  `deleted` varchar(255) NOT NULL DEFAULT 0 COMMENT '0-正常，1-删除(只做逻辑删除，不做物理删除)',
  `create_time` datetime(0) NULL,
  `update_time` datetime(0) NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `forum_posts_relay`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `posts_id` int(11) UNSIGNED NULL,
  `student_id` int(11) UNSIGNED NULL,
  `content` text NULL COMMENT '内容',
  `deleted` tinyint(1) NULL COMMENT '0-正常，1-删除(只做逻辑删除，不做物理删除)',
  `create_time` datetime(0) NULL,
  `update_time` datetime(0) NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `forum_video`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `posts_id` int(11) UNSIGNED NULL,
  `video_url` varchar(255) NULL,
  `create_time` datetime(0) NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `grade`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `grade_name` varchar(32) NULL COMMENT '年级名称',
  `create_time` datetime(0) NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `institute`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `institute_name` varchar(32) NULL COMMENT '学院名称',
  `create_time` datetime(0) NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `student`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键无符号自增长',
  `gender` tinyint(1) NULL COMMENT '性别：0-未知、1-男、2-女',
  `mobile` varchar(11) NULL COMMENT '手机号（qq绑定手机号）',
  `nick_name` varchar(32) NULL COMMENT 'QQ昵称',
  `qq_applet_openid` varchar(16) NULL COMMENT 'QQ小程序openid',
  `qq_unionid` varchar(16) NULL COMMENT 'QQunionid',
  `qq_avatar_url` varchar(255) NULL COMMENT 'QQ头像地址',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '学生身份认证状态：0-未认证、1-已认证',
  `create_time` datetime(0) NULL,
  `update_time` datetime(0) NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `student_attachment`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `student_id` int(11) UNSIGNED NULL,
  `student_card` varchar(255) NULL COMMENT '学生证链接',
  `create_time` datetime(0) NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `student_grade_institute`  (
  `student_id` int(11) UNSIGNED NOT NULL,
  `grade_id` int(11) UNSIGNED NOT NULL,
  `institute_id` int(11) UNSIGNED NOT NULL,
  PRIMARY KEY (`student_id`, `grade_id`, `institute_id`)
);

