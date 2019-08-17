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

 Date: 17/08/2019 20:43:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for activity_info
-- ----------------------------
DROP TABLE IF EXISTS `activity_info`;
CREATE TABLE `activity_info`  (
  `activity_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '活动信息主键(同时此字段也作为订单Id)',
  `activity_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '活动名称',
  `activity_openid` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '活动主办方openid',
  `activity_mode` tinyint(4) DEFAULT NULL COMMENT '活动模式：(0)学生社团活动  (1)企业组织的活动  (2) 其他',
  `activity_generalize` int(11) DEFAULT NULL COMMENT '推广力度（即人数）',
  `activity_link` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '活动链接：跳转至活动页面',
  `activity_abstract` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '活动简介、意义',
  `activity_logo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '活动宣传图',
  `activity_audit` tinyint(4) DEFAULT NULL COMMENT '活动审核 （0）未审核 （1）通过（2）未通过',
  PRIMARY KEY (`activity_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity_info
-- ----------------------------
INSERT INTO `activity_info` VALUES ('1566019993033126194', '活动名称', '1566021126634135097', 0, 1000, 'http://xxx', '活动描述信息', 'http://a.png', 0);
INSERT INTO `activity_info` VALUES ('68942316487236478', '终南山一日游', 'oxrwq0xrKKyqiAGE8O9TM3L1yaQY', 0, 30, 'http://activirty/xxx/xx/..', '终南山一日游', 'http://a.png', 1);

-- ----------------------------
-- Table structure for collection_order
-- ----------------------------
DROP TABLE IF EXISTS `collection_order`;
CREATE TABLE `collection_order`  (
  `order_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单主键',
  `order_type` tinyint(5) DEFAULT 0 COMMENT '订单的类型（用户支付、企业支付）',
  `order_money` decimal(10, 2) DEFAULT NULL COMMENT '订单金额',
  `order_openid` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '订单支付者openid',
  `order_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '订单名称',
  `order_pay_status` tinyint(5) DEFAULT NULL COMMENT '订单的支付状态',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of collection_order
-- ----------------------------
INSERT INTO `collection_order` VALUES ('1566045557401890780', 2, 0.01, NULL, '骊山鹿鸣教育科技有限公司升级为会员', 1);

-- ----------------------------
-- Table structure for company_info
-- ----------------------------
DROP TABLE IF EXISTS `company_info`;
CREATE TABLE `company_info`  (
  `company_id` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '企业统一社会信用码，长度为30的varchar',
  `company_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '企业名称',
  `company_license` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '企业营业执照图片路径',
  `company_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '企业法人电话',
  `company_status` int(5) DEFAULT 0 COMMENT '企业注册状态 0、未审核 1、通过 2、未通过（暂时设置了3种状态）',
  `openid` varchar(35) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '企业管理员openid',
  `company_member` tinyint(11) DEFAULT 0 COMMENT '企业会员等级',
  `member_overdue` bigint(20) DEFAULT NULL COMMENT '企业会员到期时间',
  PRIMARY KEY (`company_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of company_info
-- ----------------------------
INSERT INTO `company_info` VALUES ('1566022871029249125', '骊山鹿鸣教育科技有限公司', 'https://s2.ax1x.com/2019/08/17/muRLwt.png', '15291418231', 1, 'oxrwq0zPbgTB-gV9Y4Q-hN4g25Fk', 1, NULL);

-- ----------------------------
-- Table structure for company_order
-- ----------------------------
DROP TABLE IF EXISTS `company_order`;
CREATE TABLE `company_order`  (
  `order_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '企业发布活动订单Id(同时也是订单的Id)',
  `order_money` decimal(11, 2) DEFAULT NULL COMMENT '企业发布活动支付金额',
  `order_activity` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '企业发布活动的Id',
  `activity_status` tinyint(11) DEFAULT NULL COMMENT '企业发布活动的状态',
  `create_time` bigint(20) DEFAULT NULL COMMENT '信息生成时间',
  `update_time` bigint(20) DEFAULT NULL COMMENT '信息更新时间',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for member_order
-- ----------------------------
DROP TABLE IF EXISTS `member_order`;
CREATE TABLE `member_order`  (
  `order_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '开通会员的公司订单Id',
  `order_company` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '开通会员的公司Id',
  `order_money` decimal(5, 2) DEFAULT NULL COMMENT '支付费用',
  `pay_status` tinyint(3) DEFAULT NULL COMMENT '支付状态',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of member_order
-- ----------------------------
INSERT INTO `member_order` VALUES ('1566045557401890780', '1566022871029249125', 0.01, 1);

-- ----------------------------
-- Table structure for part_category
-- ----------------------------
DROP TABLE IF EXISTS `part_category`;
CREATE TABLE `part_category`  (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `create_time` bigint(20) NOT NULL,
  `update_time` bigint(20) NOT NULL,
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of part_category
-- ----------------------------
INSERT INTO `part_category` VALUES (1, '抄写', 1566023508294, 1566023508294);
INSERT INTO `part_category` VALUES (2, '代课', 1566023543861, 1566023543861);
INSERT INTO `part_category` VALUES (3, '发单', 1566023579624, 1566023579624);
INSERT INTO `part_category` VALUES (4, '辅导', 1566023616102, 1566023616102);

-- ----------------------------
-- Table structure for part_info
-- ----------------------------
DROP TABLE IF EXISTS `part_info`;
CREATE TABLE `part_info`  (
  `part_id` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '兼职信息主键，同样也是支付订单的Id',
  `part_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '兼职名称',
  `part_category` int(11) DEFAULT NULL COMMENT '兼职分类 Id',
  `part_address` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '兼职地点',
  `part_overview` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '兼职描述、概述',
  `part_start` bigint(20) DEFAULT NULL COMMENT '兼职开始时间',
  `part_end` bigint(20) DEFAULT NULL COMMENT '兼职结束时间',
  `part_time` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '兼职备注信息（可以是任何信息的备注）',
  `part_money` decimal(10, 2) DEFAULT NULL COMMENT '发起方支付金额',
  `part_money_show` decimal(10, 2) DEFAULT NULL COMMENT '其他人看到的金额 ',
  `part_status` int(11) DEFAULT NULL COMMENT '此条兼职信息状态 (详见代码枚举) ',
  `employ_sex` tinyint(3) DEFAULT NULL COMMENT '性别要求 (详见代码枚举)',
  `part_employ` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '兼职(任务)接受者openid',
  `employ_phone` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '兼职(任务)接受者电话',
  `part_creator` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '兼职发布者openid',
  PRIMARY KEY (`part_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of part_info
-- ----------------------------
INSERT INTO `part_info` VALUES ('1566039929001477652', '兼职名称', 2, '西安工程大学临潼区', '兼职描述', 1566039796582, NULL, '点名完毕就可以走了，立马确认！', 0.01, 0.00, 3, 2, NULL, NULL, 'oxrwq0zPbgTB-gV9Y4Q-hN4g25Fk');

-- ----------------------------
-- Table structure for system_config
-- ----------------------------
DROP TABLE IF EXISTS `system_config`;
CREATE TABLE `system_config`  (
  `params_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT ' 系统参数主键',
  `params_value` decimal(10, 2) NOT NULL COMMENT ' 值',
  `params_des` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '注释',
  PRIMARY KEY (`params_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_config
-- ----------------------------
INSERT INTO `system_config` VALUES ('member_month_money', 0.01, '月费企业会员');
INSERT INTO `system_config` VALUES ('member_year_money', 0.01, '年费企业会员');
INSERT INTO `system_config` VALUES ('other_param', 0.05, '其他参数');
INSERT INTO `system_config` VALUES ('part_kickback', 0.01, '兼职发布的回扣');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `open_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '微信openid',
  `user_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户昵称',
  `user_icon` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户头像',
  `user_sex` tinyint(4) DEFAULT NULL COMMENT '用户性别(0未知、1男、2女)',
  `user_city` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户所在地区',
  `user_phone` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户电话',
  `user_grade` int(11) DEFAULT NULL COMMENT '用户积分',
  `create_time` bigint(20) DEFAULT NULL COMMENT '首次存储时间',
  `update_time` bigint(20) DEFAULT NULL COMMENT '信息更新时间',
  `user_member` tinyint(11) DEFAULT NULL COMMENT '用户会员等级',
  PRIMARY KEY (`open_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('oxrwq0zPbgTB-gV9Y4Q-hN4g25Fk', 'Tim', 'http://thirdwx.qlogo.cn/mmopen/vi_32/bxVEQxwmOLibgHtYurJxvW0yicXLVcTCUiaDQDqibEyoIKwS7ZRdOsZL02RibF79vdNt6cFEYU1v53r1plygOAL60hw/132', 1, '泽西岛', NULL, 0, NULL, 1566045540344, 0);

SET FOREIGN_KEY_CHECKS = 1;
