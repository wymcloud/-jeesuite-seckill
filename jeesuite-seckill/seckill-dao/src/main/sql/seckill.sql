/*
Navicat MySQL Data Transfer

Source Server         : aa
Source Server Version : 50711
Source Host           : localhost:3306
Source Database       : seckill

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

Date: 2017-02-22 11:22:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for apps
-- ----------------------------
DROP TABLE IF EXISTS `apps`;
CREATE TABLE `apps` (
  `name` varchar(32) DEFAULT NULL,
  `notify_emails` varchar(255) DEFAULT NULL,
  `master` varchar(16) DEFAULT NULL,
  `master_uid` int(10) DEFAULT NULL,
  `remarks` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of apps
-- ----------------------------

-- ----------------------------
-- Table structure for app_configs
-- ----------------------------
DROP TABLE IF EXISTS `app_configs`;
CREATE TABLE `app_configs` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `app_name` varchar(32) DEFAULT NULL,
  `env` varchar(16) DEFAULT NULL,
  `version` varchar(16) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `type` smallint(1) DEFAULT NULL COMMENT '类型(1:文件，2:配置项)',
  `contents` text,
  `created_at` datetime DEFAULT NULL,
  `created_by` int(10) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `updated_by` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app_configs
-- ----------------------------

-- ----------------------------
-- Table structure for seckill
-- ----------------------------
DROP TABLE IF EXISTS `seckill`;
CREATE TABLE `seckill` (
  `seckill_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品库存ID',
  `name` varchar(120) NOT NULL COMMENT '商品名称',
  `number` int(11) NOT NULL COMMENT '库存数量',
  `start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '秒杀开始时间',
  `end_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '秒杀结束时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`seckill_id`),
  KEY `idx_start_time` (`start_time`),
  KEY `idx_end_time` (`end_time`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=1004 DEFAULT CHARSET=utf8 COMMENT='秒杀库存表';

-- ----------------------------
-- Records of seckill
-- ----------------------------
INSERT INTO `seckill` VALUES ('1000', '1000元秒杀iphone6', '100', '2017-02-21 09:51:23', '2016-03-02 00:00:00', '2017-02-12 14:59:22');
INSERT INTO `seckill` VALUES ('1001', '800元秒杀ipad', '200', '2017-02-21 09:51:25', '2016-03-02 00:00:00', '2017-02-12 14:59:22');
INSERT INTO `seckill` VALUES ('1002', '6600元秒杀mac book pro', '300', '2017-02-21 09:51:29', '2016-03-02 00:00:00', '2017-02-12 14:59:22');
INSERT INTO `seckill` VALUES ('1003', '7000元秒杀iMac', '400', '2017-02-01 00:00:00', '2016-03-02 00:00:00', '2017-02-12 14:59:22');

-- ----------------------------
-- Table structure for success_killed
-- ----------------------------
DROP TABLE IF EXISTS `success_killed`;
CREATE TABLE `success_killed` (
  `seckill_id` bigint(20) NOT NULL COMMENT '秒杀商品ID',
  `user_phone` bigint(20) NOT NULL COMMENT '用户手机号',
  `state` tinyint(4) NOT NULL DEFAULT '-1' COMMENT '状态标识:-1:无效 0:成功 1:已付款 2:已发货',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`seckill_id`,`user_phone`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='秒杀成功明细表';

-- ----------------------------
-- Records of success_killed
-- ----------------------------
INSERT INTO `success_killed` VALUES ('1000', '13476191877', '0', '2017-02-13 11:19:04');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(16) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `mobile` char(11) DEFAULT NULL,
  `email` varchar(32) DEFAULT NULL,
  `type` smallint(1) DEFAULT NULL,
  `status` smallint(1) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', '2', '3', '4', '5', null, null, null, null);
INSERT INTO `users` VALUES ('30', null, '2345678', null, null, null, null, '2017-02-03 11:58:09', null);
INSERT INTO `users` VALUES ('31', null, '2345678', null, null, null, null, '2017-02-03 11:58:51', null);
INSERT INTO `users` VALUES ('32', null, '2345678', null, null, null, null, '2017-02-03 12:09:54', null);
INSERT INTO `users` VALUES ('33', null, '2345678', null, null, null, null, '2017-02-03 12:11:47', null);
INSERT INTO `users` VALUES ('34', null, '2345678', null, null, null, null, '2017-02-03 13:07:40', null);
INSERT INTO `users` VALUES ('35', null, '2345678', null, null, null, null, '2017-02-03 13:53:34', null);
INSERT INTO `users` VALUES ('36', null, '2345678', null, null, null, null, '2017-02-03 13:57:56', null);
INSERT INTO `users` VALUES ('37', null, '2345678', null, null, null, null, '2017-02-03 13:58:28', null);
INSERT INTO `users` VALUES ('38', null, '2345678', null, null, null, null, '2017-02-03 13:58:58', null);
INSERT INTO `users` VALUES ('39', null, '2345678', '15920558210', null, '0', null, '2017-02-03 14:00:23', null);
INSERT INTO `users` VALUES ('40', null, '2345678', '15920558210', null, '0', null, '2017-02-04 12:15:18', null);
INSERT INTO `users` VALUES ('41', null, '2345678', '15920558210', null, '0', null, '2017-02-04 12:55:52', null);
INSERT INTO `users` VALUES ('42', null, '2345678', '15920558210', null, '0', null, '2017-02-04 13:04:34', null);
INSERT INTO `users` VALUES ('43', null, '2345678', '15920558210', null, '0', null, '2017-02-04 13:06:24', null);
INSERT INTO `users` VALUES ('44', null, '2345678', '15920558210', null, '0', null, '2017-02-13 11:09:02', null);
INSERT INTO `users` VALUES ('45', null, '2345678', '15920558210', null, '0', null, '2017-02-13 11:09:47', null);
INSERT INTO `users` VALUES ('46', null, '2345678', '15920558210', null, '0', null, '2017-02-13 11:14:09', null);
INSERT INTO `users` VALUES ('47', null, '2345678', '15920558210', null, '0', null, '2017-02-14 09:50:11', null);
INSERT INTO `users` VALUES ('48', null, '2345678', '15920558210', null, '0', null, '2017-02-14 10:45:21', null);
INSERT INTO `users` VALUES ('49', null, '2345678', '15920558210', null, '0', null, '2017-02-14 16:58:04', null);
