/*
Navicat MySQL Data Transfer

Source Server         : Company
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : db_shf

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2014-05-23 15:28:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sex` int(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_user` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `update_user` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQUE_USER_USERNAME` (`username`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '21232f297a57a5a743894a0e4a801fc3', '8888@xx.com', '1', '2014-05-23 14:26:13', '2014-05-23 14:26:16', 'NULL', 'NULL');
INSERT INTO `user` VALUES ('2', '胡锦涛', '202cb962ac59075b964b07152d234b70', 'hjt@xx.com', '1', '2014-05-23 14:29:26', '2014-05-23 14:29:26', 'admin', 'admin');
INSERT INTO `user` VALUES ('3', '江泽民', '202cb962ac59075b964b07152d234b70', 'jzm@xx.com', '1', '2014-05-23 14:45:20', '2014-05-23 14:45:20', 'admin', 'admin');
INSERT INTO `user` VALUES ('4', '蒋介石', '202cb962ac59075b964b07152d234b70', 'test@qq.com', '1', '2014-05-23 14:45:45', '2014-05-23 14:45:45', 'admin', 'admin');
INSERT INTO `user` VALUES ('5', '孙中山', '202cb962ac59075b964b07152d234b70', 'test@qq.com', '1', '2014-05-23 14:46:03', '2014-05-23 14:46:03', 'admin', 'admin');
INSERT INTO `user` VALUES ('6', '毛泽东', '202cb962ac59075b964b07152d234b70', 'test@qq.com', '1', '2014-05-23 14:46:33', '2014-05-23 14:46:33', 'admin', 'admin');
INSERT INTO `user` VALUES ('7', '宋庆龄', '202cb962ac59075b964b07152d234b70', 'test@qq.com', '0', '2014-05-23 14:46:50', '2014-05-23 14:46:50', 'admin', 'admin');
INSERT INTO `user` VALUES ('8', '宋美龄', '202cb962ac59075b964b07152d234b70', 'test@qq.com', '0', '2014-05-23 14:47:28', '2014-05-23 14:47:28', 'admin', 'admin');
INSERT INTO `user` VALUES ('9', '习近平', '202cb962ac59075b964b07152d234b70', 'test@qq.com', '1', '2014-05-23 14:47:49', '2014-05-23 14:47:49', 'admin', 'admin');
INSERT INTO `user` VALUES ('10', '彭丽媛', '202cb962ac59075b964b07152d234b70', 'test@qq.com', '0', '2014-05-23 14:48:07', '2014-05-23 14:48:07', 'admin', 'admin');
INSERT INTO `user` VALUES ('11', '张韶涵', '202cb962ac59075b964b07152d234b70', 'zsh@qq.com', '0', '2014-05-23 14:48:33', '2014-05-23 14:48:33', 'admin', 'admin');
INSERT INTO `user` VALUES ('12', '海清', '202cb962ac59075b964b07152d234b70', 'test@qq.com', '0', '2014-05-23 14:49:25', '2014-05-23 14:49:25', 'admin', 'admin');
INSERT INTO `user` VALUES ('13', ' 赵文卓', '202cb962ac59075b964b07152d234b70', 'zhaowenzhuo@xx.com', '1', '2014-05-23 15:20:23', '2014-05-23 15:23:47', 'admin', 'admin');
INSERT INTO `user` VALUES ('16', '韦德', '202cb962ac59075b964b07152d234b70', 'wade@xx.com', '1', '2014-05-23 15:24:16', '2014-05-23 15:24:16', 'admin', 'admin');
INSERT INTO `user` VALUES ('17', 'test', '098f6bcd4621d373cade4e832627b4f6', 'test@qq.com', '2', '2014-05-23 15:24:43', '2014-05-23 15:24:43', 'admin', 'admin');
