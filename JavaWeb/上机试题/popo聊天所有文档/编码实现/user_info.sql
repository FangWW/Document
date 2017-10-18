/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50067
Source Host           : localhost:3306
Source Database       : webdb

Target Server Type    : MYSQL
Target Server Version : 50067
File Encoding         : 65001

Date: 2011-06-11 21:06:15
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `user_info`
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(50) NOT NULL auto_increment,
  `qq` varchar(12) NOT NULL,
  `nickname` varchar(12) NOT NULL,
  `password` varchar(12) NOT NULL,
  `age` int(12) NOT NULL,
  `sex` varchar(12) NOT NULL,
  `birthday` varchar(12) NOT NULL,
  `sign` varchar(50) ,
  `photo` varchar(1024) default 'lian.jpeg',
  PRIMARY KEY  (`id`,`qq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
`mysql`