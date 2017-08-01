
CREATE TABLE `roo_setting` (
  `skey` varchar(50) NOT NULL DEFAULT '' COMMENT '配置键',
  `svalue` text COMMENT '配置值',
  `state` tinyint(2) NOT NULL COMMENT '0禁用 1正常',
  PRIMARY KEY (`skey`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='系统配置表';

CREATE TABLE `roo_user` (
  `uid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(100) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(100) NOT NULL DEFAULT '' COMMENT '用户密码',
  `email` varchar(100) DEFAULT NULL COMMENT '用户邮箱',
  `topics` int(11) NOT NULL DEFAULT '0' COMMENT '发布的主题',
  `comments` int(11) NOT NULL DEFAULT '0' COMMENT '发布的评论',
  `followers` int(11) NOT NULL DEFAULT '0' COMMENT '粉丝数',
  `role` varchar(50) NOT NULL DEFAULT 'member' COMMENT '角色',
  `created` datetime NOT NULL COMMENT '注册时间',
  `last_logined` datetime DEFAULT NULL COMMENT '最后一次登录时间',
  `state` tinyint(2) NOT NULL DEFAULT '0' COMMENT '用户状态 0:未激活 1:正常 2:停用 3:注销',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;