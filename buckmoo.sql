/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50727
Source Host           : localhost:3306
Source Database       : buckmoo

Target Server Type    : MYSQL
Target Server Version : 50727
File Encoding         : 65001

Date: 2019-10-01 21:55:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for activity_info
-- ----------------------------
DROP TABLE IF EXISTS `activity_info`;
CREATE TABLE `activity_info` (
  `activity_id` varchar(30) NOT NULL COMMENT '活动信息主键(同时此字段也作为订单Id)',
  `activity_name` varchar(50) DEFAULT NULL COMMENT '活动名称',
  `activity_openid` varchar(30) DEFAULT NULL COMMENT '活动主办方openid',
  `activity_mode` tinyint(4) DEFAULT NULL COMMENT '活动模式：(0)学生社团活动  (1)企业组织的活动  (2) 其他',
  `activity_generalize` int(11) DEFAULT NULL COMMENT '推广力度（即人数）',
  `activity_link` varchar(255) DEFAULT NULL COMMENT '活动链接：跳转至活动页面',
  `activity_abstract` varchar(255) DEFAULT NULL COMMENT '活动简介、意义',
  `activity_logo` varchar(255) DEFAULT NULL COMMENT '活动宣传图',
  `activity_audit` tinyint(4) DEFAULT NULL COMMENT '活动审核 （0）未审核 （1）通过（2）未通过',
  PRIMARY KEY (`activity_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for collection_order
-- ----------------------------
DROP TABLE IF EXISTS `collection_order`;
CREATE TABLE `collection_order` (
  `order_id` varchar(30) NOT NULL COMMENT '订单主键',
  `order_type` tinyint(5) DEFAULT '0' COMMENT '订单的类型（用户支付、企业支付）',
  `order_money` decimal(10,2) DEFAULT NULL COMMENT '订单金额',
  `order_openid` varchar(30) DEFAULT NULL COMMENT '订单支付者openid',
  `order_name` varchar(50) DEFAULT NULL COMMENT '订单名称',
  `order_pay_status` tinyint(5) DEFAULT NULL COMMENT '订单的支付状态',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for company_info
-- ----------------------------
DROP TABLE IF EXISTS `company_info`;
CREATE TABLE `company_info` (
  `company_id` varchar(25) NOT NULL COMMENT '企业统一社会信用码，长度为30的varchar',
  `company_name` varchar(50) DEFAULT NULL COMMENT '企业名称',
  `company_license` varchar(150) DEFAULT NULL COMMENT '企业营业执照图片路径',
  `company_phone` varchar(20) DEFAULT NULL COMMENT '企业法人电话',
  `company_status` int(5) DEFAULT '0' COMMENT '企业注册状态 0、未审核 1、通过 2、未通过（暂时设置了3种状态）',
  `openid` varchar(35) DEFAULT NULL COMMENT '企业管理员openid',
  `company_member` tinyint(11) NOT NULL DEFAULT '0' COMMENT '企业会员等级',
  `member_overdue` bigint(20) NOT NULL DEFAULT '0' COMMENT '企业会员到期时间',
  PRIMARY KEY (`company_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for company_order
-- ----------------------------
DROP TABLE IF EXISTS `company_order`;
CREATE TABLE `company_order` (
  `order_id` varchar(30) NOT NULL COMMENT '企业发布活动订单Id(同时也是订单的Id)',
  `order_money` decimal(11,2) DEFAULT NULL COMMENT '企业发布活动支付金额',
  `order_activity` varchar(25) DEFAULT NULL COMMENT '企业发布活动的Id',
  `activity_status` tinyint(11) DEFAULT NULL COMMENT '企业发布活动的状态',
  `create_time` bigint(20) DEFAULT NULL COMMENT '信息生成时间',
  `update_time` bigint(20) DEFAULT NULL COMMENT '信息更新时间',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for member_order
-- ----------------------------
DROP TABLE IF EXISTS `member_order`;
CREATE TABLE `member_order` (
  `order_id` varchar(30) NOT NULL COMMENT '开通会员的公司订单Id',
  `order_company` varchar(30) DEFAULT NULL COMMENT '开通会员的公司Id',
  `order_money` decimal(5,2) DEFAULT NULL COMMENT '支付费用',
  `pay_status` tinyint(3) DEFAULT NULL COMMENT '支付状态',
  `member_level` tinyint(5) DEFAULT NULL COMMENT '支付的会员等级',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for part_category
-- ----------------------------
DROP TABLE IF EXISTS `part_category`;
CREATE TABLE `part_category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(30) DEFAULT NULL,
  `create_time` bigint(20) NOT NULL,
  `update_time` bigint(20) NOT NULL,
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for part_info
-- ----------------------------
DROP TABLE IF EXISTS `part_info`;
CREATE TABLE `part_info` (
  `part_id` varchar(25) NOT NULL COMMENT '兼职信息主键，同样也是支付订单的Id',
  `part_name` varchar(30) DEFAULT NULL COMMENT '兼职名称',
  `part_category` int(11) DEFAULT NULL COMMENT '兼职分类 Id',
  `part_address` varchar(150) DEFAULT NULL COMMENT '兼职地点',
  `part_overview` varchar(150) DEFAULT NULL COMMENT '兼职描述、概述',
  `part_start` bigint(20) DEFAULT NULL COMMENT '兼职开始时间',
  `part_end` bigint(20) DEFAULT NULL COMMENT '兼职结束时间',
  `part_time` varchar(100) DEFAULT NULL COMMENT '兼职备注信息（可以是任何信息的备注）',
  `part_money` decimal(10,2) DEFAULT NULL COMMENT '发起方支付金额',
  `part_money_show` decimal(10,2) DEFAULT NULL COMMENT '其他人看到的金额 ',
  `part_status` int(11) DEFAULT NULL COMMENT '此条兼职信息状态 (详见代码枚举) ',
  `employ_sex` tinyint(3) DEFAULT NULL COMMENT '性别要求 (详见代码枚举)',
  `part_employ` varchar(30) DEFAULT NULL COMMENT '兼职(任务)接受者openid',
  `employ_phone` varchar(15) DEFAULT NULL COMMENT '兼职(任务)接受者电话',
  `part_creator` varchar(30) DEFAULT NULL COMMENT '兼职发布者openid',
  PRIMARY KEY (`part_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for problem_feedback
-- ----------------------------
DROP TABLE IF EXISTS `problem_feedback`;
CREATE TABLE `problem_feedback` (
  `problem_id` varchar(20) NOT NULL COMMENT '问题反馈Id',
  `problem_content` varchar(500) NOT NULL DEFAULT '' COMMENT '反馈内容',
  `problem_dealwith` tinyint(5) NOT NULL DEFAULT '0' COMMENT '是否已经处理',
  `contact_way` varchar(30) DEFAULT NULL COMMENT '反馈人联系方式',
  PRIMARY KEY (`problem_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for system_config
-- ----------------------------
DROP TABLE IF EXISTS `system_config`;
CREATE TABLE `system_config` (
  `params_id` varchar(30) NOT NULL COMMENT ' 系统参数主键',
  `params_value` decimal(10,2) NOT NULL COMMENT ' 值',
  `params_des` varchar(50) DEFAULT NULL COMMENT '注释',
  PRIMARY KEY (`params_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `open_id` varchar(30) NOT NULL COMMENT '微信openid',
  `user_name` varchar(30) DEFAULT NULL COMMENT '用户昵称',
  `user_icon` varchar(200) DEFAULT NULL COMMENT '用户头像',
  `user_sex` tinyint(4) DEFAULT NULL COMMENT '用户性别(0未知、1男、2女)',
  `user_city` varchar(50) DEFAULT NULL COMMENT '用户所在地区',
  `user_phone` varchar(25) DEFAULT NULL COMMENT '用户电话',
  `user_grade` int(11) DEFAULT NULL COMMENT '用户积分',
  `create_time` bigint(20) DEFAULT NULL COMMENT '首次存储时间',
  `update_time` bigint(20) DEFAULT NULL COMMENT '信息更新时间',
  `user_member` tinyint(11) DEFAULT NULL COMMENT '用户会员等级',
  `company_id` varchar(40) DEFAULT '' COMMENT '如果是企业管理员，这就是企业Id',
  PRIMARY KEY (`open_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
