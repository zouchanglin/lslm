/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost
 Source Database       : buckmoo

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : utf-8

 Date: 06/26/2019 14:48:16 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `activity_info`
-- ----------------------------
DROP TABLE IF EXISTS `activity_info`;
CREATE TABLE `activity_info` (
  `activity_id` varchar(25) NOT NULL COMMENT '活动的主键',
  `activity_name` varchar(255) NOT NULL COMMENT '活动名称',
  `activity_main` varchar(30) DEFAULT NULL COMMENT '主办方',
  `activity_unmain` varchar(30) DEFAULT NULL COMMENT '承办方',
  `activity_address` varchar(255) NOT NULL COMMENT '活动地点',
  `activity_start` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '活动开始时间',
  `activity_end` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '活动结束时间',
  `activity_max` int(11) DEFAULT NULL COMMENT '活动最大参加人数',
  `activity_mode` int(4) NOT NULL COMMENT '活动模式：(0)学生社团活动  (1)企业组织的活动  (2) 其他',
  `activity_generalize` int(11) NOT NULL COMMENT '数字就代表人数，如果是100那就代表此活动100人左右的推广力度，学生社团活动可不选择',
  `activity_link` varchar(255) NOT NULL COMMENT '活动链接：一个跳转链接，跳转至活动页面',
  `activity_abstract` varchar(255) NOT NULL COMMENT '活动简介，意义等等',
  `activity_logo` varchar(255) DEFAULT NULL COMMENT '活动的Logo图片',
  `activity_audit` int(4) NOT NULL COMMENT '活动审核 （0）未审核 （1）通过（2）未通过',
  `activity_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '此信息最后修改时间',
  `activity_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `activity_apply` int(11) DEFAULT '0' COMMENT '已经报名人数',
  PRIMARY KEY (`activity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `activity_info`
-- ----------------------------
BEGIN;
INSERT INTO `activity_info` VALUES ('1560234127693562090', '钟南山一日游', '1560234861488151072', '1560234861488151072', '终南山', '2019-06-11 01:22:08', '2019-06-11 01:22:08', '50', '1', '2000', 'https://s2.ax1x.com/2019/06/20/VxE6AS.png', '欣赏终南山美景，品尝终南山的美食！', 'https://s2.ax1x.com/2019/06/20/VxVF9H.png', '1', '2019-06-20 23:09:00', '2019-06-11 14:22:07', '0'), ('1560234127693562091', 'XPU一日游', '1560234861488151073', '1560234861488151072', '西安工程大学', '2019-06-11 01:22:08', '2019-06-11 01:22:08', '50', '1', '50', 'https://s2.ax1x.com/2019/06/20/VxEHNF.png', '欣赏XPU美景，品尝终南山的美食！', 'https://s2.ax1x.com/2019/06/20/VxVF9H.png', '1', '2019-06-20 23:09:01', '2019-06-11 14:22:07', '0');
COMMIT;

-- ----------------------------
--  Table structure for `company_info`
-- ----------------------------
DROP TABLE IF EXISTS `company_info`;
CREATE TABLE `company_info` (
  `company_id` varchar(25) NOT NULL COMMENT '企业统一社会信用码，长度为30的varchar',
  `company_name` varchar(50) DEFAULT NULL COMMENT '企业名称',
  `company_logo` varchar(300) DEFAULT NULL COMMENT '企业Logo的图片路径',
  `company_describe` varchar(300) DEFAULT NULL COMMENT '企业的描述，经营范围之类的',
  `company_license` varchar(300) NOT NULL COMMENT '企业营业执照图片路径',
  `company_manger` varchar(20) DEFAULT NULL COMMENT '企业法人姓名',
  `company_phone` varchar(20) NOT NULL COMMENT '企业法人电话',
  `company_id_card` varchar(20) NOT NULL COMMENT '企业法人身份证号码',
  `company_reg_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '企业在此平台注册时间',
  `company_un_reg_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '企业在此平台注册到期时间',
  `company_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '企业信息最后修改更新时间',
  `company_status` int(5) NOT NULL DEFAULT '0' COMMENT '企业注册状态 0、未审核 1、通过 2、未通过（暂时设置了3种状态）',
  `company_grade` int(11) DEFAULT NULL COMMENT '信誉积分',
  `open_id` varchar(255) DEFAULT NULL COMMENT '企业管理员微信Id',
  `password` varchar(20) DEFAULT NULL COMMENT '管理员密码',
  PRIMARY KEY (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `company_info`
-- ----------------------------
BEGIN;
INSERT INTO `company_info` VALUES ('1560234861488151072', '比特科技', 'http://logo.png', '培训C/C++高级工程师，Java工程师，Python工程师', 'http://license.png', '张鹏伟', '13724344782', '612328198008083417', '2019-06-11 01:34:21', '2019-06-11 01:34:21', '2019-06-11 14:34:21', '0', '0', null, null), ('1560234861488151073', '骊山鹿鸣', 'http://logo.png', '培训C/C++高级工程师，Java工程师，Python工程师', 'http://license.png', '张鹏伟', '13724344782', '612328198008083417', '2019-06-11 01:34:21', '2019-06-11 01:34:21', '2019-06-20 22:36:16', '0', '0', null, null);
COMMIT;

-- ----------------------------
--  Table structure for `part_category`
-- ----------------------------
DROP TABLE IF EXISTS `part_category`;
CREATE TABLE `part_category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(30) DEFAULT NULL,
  `category_type` int(11) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `part_category`
-- ----------------------------
BEGIN;
INSERT INTO `part_category` VALUES ('1', '代课', '1', '2019-06-11 21:54:35', '2019-06-11 21:56:30');
COMMIT;

-- ----------------------------
--  Table structure for `part_info`
-- ----------------------------
DROP TABLE IF EXISTS `part_info`;
CREATE TABLE `part_info` (
  `part_id` varchar(25) NOT NULL,
  `part_name` varchar(30) DEFAULT NULL COMMENT '兼职名称',
  `part_category` int(11) DEFAULT NULL COMMENT '兼职分类',
  `part_address` varchar(150) DEFAULT NULL COMMENT '兼职地点',
  `part_overview` varchar(150) DEFAULT NULL COMMENT '兼职描述、概述',
  `part_start` timestamp NULL DEFAULT NULL COMMENT '兼职开始时间',
  `part_end` timestamp NULL DEFAULT NULL COMMENT '兼职结束时间',
  `part_time` varchar(100) DEFAULT NULL COMMENT '对兼职时间的一个补充',
  `part_money` decimal(10,0) DEFAULT NULL COMMENT '发起方支付金额',
  `part_status` int(11) DEFAULT NULL COMMENT '此条兼职信息状态， 详情见代码',
  `part_employ` varchar(32) DEFAULT NULL COMMENT '兼职接受者（谁去完成这个任务）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`part_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `part_info`
-- ----------------------------
BEGIN;
INSERT INTO `part_info` VALUES ('1560260414182640051', '周三代课', '0', '西安工程大学临潼校区', '这是详细描述信息', '2019-06-11 08:40:14', '2019-06-11 08:40:14', '对兼职时间的一个补充', '40', '0', null, '2019-06-11 21:40:14', '2019-06-11 21:40:14');
COMMIT;

-- ----------------------------
--  Table structure for `user_info`
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `user_id` varchar(25) NOT NULL DEFAULT '' COMMENT '用户Id',
  `open_id` varchar(255) NOT NULL COMMENT '微信的OpenId',
  `user_name` varchar(30) DEFAULT NULL,
  `user_icon` varchar(255) DEFAULT NULL,
  `user_sex` int(11) DEFAULT NULL,
  `user_city` varchar(50) DEFAULT NULL,
  `user_phone` varchar(25) DEFAULT NULL COMMENT '电话',
  `user_grade` int(11) DEFAULT NULL COMMENT '积分',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;
