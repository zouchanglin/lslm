/*
 Navicat Premium Data Transfer

 Source Server         : localhost_mysql
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : buckmoo

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 01/08/2019 20:15:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for activity_info
-- ----------------------------
DROP TABLE IF EXISTS `activity_info`;
CREATE TABLE `activity_info`  (
  `activity_id` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '活动的主键',
  `activity_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '活动名称',
  `activity_main` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '主办方',
  `activity_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '活动地点',
  `activity_start` bigint(20) DEFAULT NULL COMMENT '活动开始时间',
  `activity_end` bigint(20) DEFAULT NULL COMMENT '活动结束时间',
  `activity_max` int(11) DEFAULT NULL COMMENT '活动最大参加人数',
  `activity_mode` tinyint(4) DEFAULT NULL COMMENT '活动模式：(0)学生社团活动  (1)企业组织的活动  (2) 其他',
  `activity_generalize` int(11) DEFAULT NULL COMMENT '数字就代表人数，如果是100那就代表此活动100人左右的推广力度，学生社团活动可不选择',
  `activity_link` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '活动链接：一个跳转链接，跳转至活动页面',
  `activity_abstract` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '活动简介，意义等等',
  `activity_logo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '活动的Logo图片',
  `activity_audit` tinyint(4) DEFAULT NULL COMMENT '活动审核 （0）未审核 （1）通过（2）未通过',
  `activity_update` bigint(20) DEFAULT NULL COMMENT '此信息最后修改时间',
  `activity_create` bigint(20) DEFAULT NULL,
  `activity_apply` int(11) DEFAULT 0 COMMENT '已经报名人数',
  PRIMARY KEY (`activity_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity_info
-- ----------------------------
INSERT INTO `activity_info` VALUES ('1564648889477254410', '钟南山一日游', '1564648889477763274', '终南山', 1564648889477, 1564648889477, 50, 1, 2000, 'http://lslm.jeck', '欣赏终南山美景，品尝终南山的美食！', 'http://lslm.png', 0, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for company_info
-- ----------------------------
DROP TABLE IF EXISTS `company_info`;
CREATE TABLE `company_info`  (
  `company_id` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '企业统一社会信用码，长度为30的varchar',
  `company_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '企业名称',
  `company_describe` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '企业的描述，经营范围之类的',
  `company_license` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '企业营业执照图片路径',
  `company_manger` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '企业法人姓名',
  `company_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '企业法人电话',
  `company_reg_time` bigint(20) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '企业在此平台注册时间',
  `company_update_time` bigint(20) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '企业信息最后修改更新时间',
  `company_status` int(5) NOT NULL DEFAULT 0 COMMENT '企业注册状态 0、未审核 1、通过 2、未通过（暂时设置了3种状态）',
  `company_grade` int(11) DEFAULT NULL COMMENT '信誉积分',
  `login_openid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '企业管理员微信Id',
  `login_password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '管理员密码',
  `company_member` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`company_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of company_info
-- ----------------------------
INSERT INTO `company_info` VALUES ('1560234861488151072', '比特科技', '致力于教育行业设备开发、服务、维护', 'http://license.png', '杨楠', '13724344781', 20190611013421, 20190611143421, 0, 0, 'oxrwq0zPbgTB-gV9Y4Q-hN4g25Fk', '123456', '0');
INSERT INTO `company_info` VALUES ('1560234861488151073', '骊山鹿鸣', '培训C/C++高级工程师，Java工程师，Python工程师', 'http://license.png', '张鹏伟', '13724344782', 20190611013421, 20190620223616, 0, 0, 'oxrwq0zPbgTB-gV9Y4Q-hN4g25Fk', '123456', '0');

-- ----------------------------
-- Table structure for company_order
-- ----------------------------
DROP TABLE IF EXISTS `company_order`;
CREATE TABLE `company_order`  (
  `order_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `order_status` tinyint(11) DEFAULT NULL,
  `order_money` decimal(11, 2) DEFAULT NULL,
  `order_activity` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `activity_status` tinyint(11) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for part_category
-- ----------------------------
DROP TABLE IF EXISTS `part_category`;
CREATE TABLE `part_category`  (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `category_type` int(11) DEFAULT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of part_category
-- ----------------------------
INSERT INTO `part_category` VALUES (1, '代课', 1, '2019-06-11 21:54:35', '2019-06-11 21:56:30');

-- ----------------------------
-- Table structure for part_info
-- ----------------------------
DROP TABLE IF EXISTS `part_info`;
CREATE TABLE `part_info`  (
  `part_id` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `part_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '兼职名称',
  `part_category` int(11) DEFAULT NULL COMMENT '兼职分类',
  `part_address` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '兼职地点',
  `part_overview` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '兼职描述、概述',
  `part_start` bigint(20) DEFAULT NULL COMMENT '兼职开始时间',
  `part_end` bigint(20) DEFAULT NULL COMMENT '兼职结束时间',
  `part_time` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '对兼职时间的一个补充',
  `part_creator` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `part_money` decimal(10, 2) DEFAULT NULL COMMENT '发起方支付金额',
  `part_money_show` decimal(10, 2) DEFAULT NULL,
  `part_status` int(11) DEFAULT NULL COMMENT '此条兼职信息状态， 详情见代码',
  `part_employ` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '兼职接受者（谁去完成这个任务）',
  `employ_sex` tinyint(11) DEFAULT NULL,
  `part_remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `creator_phone` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`part_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of part_info
-- ----------------------------
INSERT INTO `part_info` VALUES ('1564658763535673412', '兼职名称_01', 0, '兼职地点_01', '兼职详细描述信息', 1564658763535, 1564658763535, '兼职时间补充', 'oxrwq0zPbgTB-gV9Y4Q-hN4g25Fk', 0.01, 0.00, 1, NULL, 3, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `open_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '微信的OpenId',
  `user_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `user_icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `user_sex` int(11) DEFAULT NULL,
  `user_city` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `user_phone` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '电话',
  `user_grade` int(11) DEFAULT NULL COMMENT '积分',
  `create_time` bigint(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  `user_member` tinyint(11) DEFAULT NULL,
  PRIMARY KEY (`open_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('oxrwq0zPbgTB-gV9Y4Q-hN4g25Fk', 'Tim', 'http://thirdwx.qlogo.cn/mmopen/vi_32/bxVEQxwmOLibgHtYurJxvW0yicXLVcTCUiaDQDqibEyoIKwS7ZRdOsZL02RibF79vdNt6cFEYU1v53r1plygOAL60hw/132', 1, '泽西岛', NULL, 0, NULL, NULL, 0);

SET FOREIGN_KEY_CHECKS = 1;
