/*
 Navicat MySQL Data Transfer

 Source Server         : prod(buckmoo)
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : lslm.live
 Source Database       : buckmoo

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : utf-8

 Date: 10/11/2019 20:43:48 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `activity_info`
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
--  Table structure for `collection_order`
-- ----------------------------
DROP TABLE IF EXISTS `collection_order`;
CREATE TABLE `collection_order` (
  `order_id` varchar(35) NOT NULL COMMENT '订单主键',
  `order_type` tinyint(5) DEFAULT '0' COMMENT '订单的类型（用户支付、企业支付）',
  `order_money` decimal(10,2) DEFAULT NULL COMMENT '订单金额',
  `order_openid` varchar(50) DEFAULT NULL COMMENT '订单支付者openid',
  `order_name` varchar(50) DEFAULT NULL COMMENT '订单名称',
  `order_pay_status` tinyint(5) DEFAULT NULL COMMENT '订单的支付状态',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Records of `collection_order`
-- ----------------------------
BEGIN;
INSERT INTO `collection_order` VALUES ('1570797620153682574', '3', '0.01', 'oxrwq0zPbgTB-gV9Y4Q-hN4g25Fk', '用户成为会员', '1'), ('1570797784507317443', '3', '0.05', 'oxrwq0zPbgTB-gV9Y4Q-hN4g25Fk', '用户成为会员', '1');
COMMIT;

-- ----------------------------
--  Table structure for `community_info`
-- ----------------------------
DROP TABLE IF EXISTS `community_info`;
CREATE TABLE `community_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `school` varchar(100) NOT NULL,
  `icon` varchar(100) NOT NULL,
  `des` varchar(255) DEFAULT NULL,
  `openid` varchar(50) NOT NULL,
  `member` tinyint(3) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `company_info`
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
--  Table structure for `company_order`
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
--  Table structure for `member_order`
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
--  Table structure for `part_category`
-- ----------------------------
DROP TABLE IF EXISTS `part_category`;
CREATE TABLE `part_category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(30) DEFAULT NULL,
  `create_time` bigint(20) NOT NULL,
  `update_time` bigint(20) NOT NULL,
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Records of `part_category`
-- ----------------------------
BEGIN;
INSERT INTO `part_category` VALUES ('1', 'A', '1570012906386', '1570012906386'), ('2', 'B', '1570012949005', '1570012949005'), ('3', 'C', '1570012949006', '1570012949006'), ('4', 'D', '1570012949006', '1570012949006'), ('5', 'E', '1570012949006', '1570012949006');
COMMIT;

-- ----------------------------
--  Table structure for `part_info`
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
--  Table structure for `problem_feedback`
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
--  Table structure for `system_config`
-- ----------------------------
DROP TABLE IF EXISTS `system_config`;
CREATE TABLE `system_config` (
  `params_id` varchar(30) NOT NULL COMMENT ' 系统参数主键',
  `params_value` decimal(10,2) NOT NULL COMMENT ' 值',
  `params_des` varchar(50) DEFAULT NULL COMMENT '注释',
  PRIMARY KEY (`params_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Records of `system_config`
-- ----------------------------
BEGIN;
INSERT INTO `system_config` VALUES ('activity_generalize', '0.01', '推广一个人多少钱'), ('member_forever_money', '0.01', '永久企业会员'), ('member_forever_user', '0.05', '永久个人会员'), ('member_month_money', '0.05', '月费企业会员'), ('member_month_user', '0.01', '月费个人会员'), ('member_year_money', '0.01', '年费企业会员'), ('member_year_user', '0.03', '年费个人会员'), ('one_activity_money', '0.01', '非会员布活动费用'), ('other_param', '0.05', '其他参数'), ('part_kickback', '0.01', '兼职发布的回扣');
COMMIT;

-- ----------------------------
--  Table structure for `user_info`
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
  `member_past` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`open_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Records of `user_info`
-- ----------------------------
BEGIN;
INSERT INTO `user_info` VALUES ('oxrwq0-7Cvkgy7d3JlcfyaXy-fvg', '₯㎕。Smile°', 'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTL4T1A83vKzEBIgqv8xCZGxgdHHx4P2v70hVjCtWGMs9yEIX62vv6ImYsEE8kb57KsB9eI8uZLAibw/132', '1', '中国平凉', '', '0', '1570060278304', '1570060278304', '0', '', null), ('oxrwq0-7qGU2Y6RnAp7NG-GB9qbg', '困倦的粉红雪碧', 'http://thirdwx.qlogo.cn/mmopen/vi_32/BvFSMkG9gzeda6XBvcmvvmO7cw2oeBc4bw35sk0tsG5SJmo7WDrYPFRm85ukPRickOz4CLFK8pek5klaWS0mtkg/132', '1', '中国昆明', '', '0', '1569945198266', '1569945198266', '0', '', null), ('oxrwq0-7V1_qgHtGttBB-pDNnMWw', '念念', 'http://thirdwx.qlogo.cn/mmopen/vi_32/bLIicAl9Jf7OPwmhGSRZTDYTzO392uiaA94icicmGFx2oEoHszj4cch0QcIN0bkcqIeS3ibrU8NnJMe4vAVk59HQYfA/132', '2', '奥地利', '', '0', '1569981415890', '1569981415890', '0', '', null), ('oxrwq0-g3aLJo3OY_2GI_DFXFzr8', '易军凸起', 'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTI6pRhp2h0ssylmVgeq9cYdDJrwiaicQJaPFgOWqYUfia2krvKyxKeLYG9ngJETpfDKXBPAiaxyicQxMQQ/132', '1', '中国西安', '', '0', '1570111890961', '1570111890961', '0', '', null), ('oxrwq0-VFvhNTRkx3dfYBQTvcn5w', 'Ender', 'http://thirdwx.qlogo.cn/mmopen/vi_32/ySzTU6SjhppfaCC1N6xvDjEaL20mwXGs6M0NMGflWzWwxXc6BiblV315vNricwOECnNwU2fnMnBeJTDCkMDapgfA/132', '1', '中国平凉', '', '0', '1569988275350', '1569988275350', '0', '', null), ('oxrwq0-Y9EXwVjP-NXb9dWFSUtts', '泓', 'http://thirdwx.qlogo.cn/mmopen/vi_32/ZRl6Ak3Wnibve8P6jcGqarJK5ZuKaPMrGWM00k3Cd0Oia6tlBmUw64EcxTM6YvJdrEeJic4AFnrWl6ibiawlYgK9BOw/132', '2', '中国保定', '', '0', '1569942321247', '1569942321247', '0', '', null), ('oxrwq000DsmIaBH6Cv88rD3Cq98s', '隨風而逝', 'http://thirdwx.qlogo.cn/mmopen/vi_32/icZJCCzianSibczJhKicOQxdCrCaM8vZGovFJrzYB0Aia9libzSdpcYSgeDGaObD02BZx2FGPFthdsJtrKQaPb8aP7KQ/132', '1', '中国莆田', '', '0', '1570202581165', '1570202581165', '0', '', null), ('oxrwq000_6Tr0VMz4YyVA84ov7e0', 'N.', 'http://thirdwx.qlogo.cn/mmopen/vi_32/Rgr60EMyax7HhJjY1lWGyicRkenDREWUGIv9duTgUDdbH2iajAR2RsnujwnInerrbFKCEWuQY7upW0HQem9Yrdvw/132', '1', '阿尔巴尼亚', '', '0', '1569965257794', '1569965257794', '0', '', null), ('oxrwq0029xUlJ09Z-Rumv0NX6eHg', 'จุ๊บHu Yan婼绫', 'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJ26xdibLibk37gVmTDX5wyGiaVrcLSvCXjtBHNxUhNWZorhCiaTADlcAgjx23cNTnDQSr8mCcDZN0qjQ/132', '2', '中国鄂尔多斯', '', '0', '1569977571328', '1569977571328', '0', '', null), ('oxrwq00cclLmFkm7sFOrTZnlsBtc', 'JorrayP', 'http://thirdwx.qlogo.cn/mmopen/vi_32/R2kCRgdmP9Dx2nrhkyhCCfxy3jM3BJk7CrC1h2Dgth4cAURUWWOF4UZib8rYDWia5xdfuUP32oM1nXVXJU3Ms9MQ/132', '0', '瑞士', '', '0', '1569910652662', '1569910652662', '0', '', null), ('oxrwq00cqOmnfU7ktuiAxg_rFPa8', '小逸', 'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJicjfY3RywVicWjDsI4iboSSjicOXIoXibVKocf22wMYLqS0e8xeNicjoYG88uZWiaW9FiaYwh8DaVTVFddg/132', '0', '', '', '0', '1569982050725', '1569982050725', '0', '', null), ('oxrwq00FVpinGzQRcSkZqqBxpHwE', 'wuli涛涛', 'http://thirdwx.qlogo.cn/mmopen/vi_32/T9mRSQP0oZdXe9UIGw5KUwWXxaZv4EUPPiaAibZH25vbd3JAjwy2JfSxvgGrQxuaBVwib4o6SvLfX4YeriaibAsp8SA/132', '1', '中国绵阳', '', '0', '1569974334909', '1569974334909', '0', '', null), ('oxrwq00h3SkACfHF4vrlmRIM62X8', '社会主义接班人', 'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJicbjpVGicDmJ5PYewAianxMfYhJ7GqJm5BOLCDfskf7UiaY72iaMgRLbXFmm4OvHWYYEiaHnjuQSMUEEg/132', '0', '', '', '0', '1570453508175', '1570453508175', '0', '', null), ('oxrwq00JcGMjsI2JCM8toOBErEt4', 'Success[愉快]', 'http://thirdwx.qlogo.cn/mmopen/vi_32/OQ7YKpfpbpVicsfjB1dibMZyZADXCRVWudmtyFI64UV3CErQRmbDaz2SZPGiaxkSqfyQdjA8GbViaAotHr7mNiaWvVg/132', '2', '贝宁', '13720619020', '0', '1569817656027', '1569819050166', '0', '', null), ('oxrwq00jxLcHCQOn0tNW6bJIvG-M', '夜愚', 'http://thirdwx.qlogo.cn/mmopen/vi_32/UoCTUtDia0Niaqw3eTPQ9Jsc9yg49SKhOF2YufvFAKPzjqiavlL12JFIxOWJoWwMavTCnxY1nSvfk2bpUpRyToTWA/132', '0', '中国商洛', '', '0', '1569912561921', '1570028650840', '0', '', null), ('oxrwq00mVbJ2qOhZr4WN4qSiomLc', '。。。。。', 'http://thirdwx.qlogo.cn/mmopen/vi_32/xmILkMFmw1C3T1r6vRYa1hsYyxZO7CMmAVOXmkAga3iat2rv7CzicS88ZaUEuuf6hsHtznHY1CY6rlVDtVVBia29Q/132', '0', '', '', '0', '1569910613464', '1569910613464', '0', '', null), ('oxrwq00oNk4qkjDnaRWJYgU4DedE', '乔业宏', 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eraib4qU3K43bwoGODerWe1PsGy6Mk9nLcxicBpK7VDOBPx7UQhGs0Dat6Q3xXDpKzXk7GftXh9gYnA/132', '1', '中国榆林', '', '0', '1569993775452', '1569993775452', '0', '', null), ('oxrwq00Qemm0p-RUppA7T792_Oo0', 'Wo', 'http://thirdwx.qlogo.cn/mmopen/vi_32/L4dsI3zQOpVpEtqeNrlQWmichboqIibDO5sndPBaib4rdo2aYibYPiaX4Y0QzPjibKbmdwUArZKWhX1IxkZ9zYY22Diag/132', '1', '也门', '15229452717', '0', '1570622221757', '1570622456281', '0', '', null), ('oxrwq00soxEnJEl7n5K7pTB20SXw', 'Q Q糖o_O', 'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIFBjsiaQyUUreWYtDVicQIcBLzFzicgKIr5fBlk1LQibsEic1PxtiapXu08FwIt0HvaiaVwkleXuyFBGeGg/132', '2', '中国平凉', '18993328456', '0', '1570093795819', '1570111229365', '0', '', null), ('oxrwq00vDq5tvue-brqneCyK4QUE', '风', 'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJqvLiaoJqMHNwL4ALczFRgkkIaE2VcbEyA16hl5vzyK7HJEbnibSvODASNTNxzJNib78Br5IMKaEPFw/132', '1', '中国濮阳', '', '0', '1569992733964', '1569992733964', '0', '', null), ('oxrwq00X57JCY_JKnROImdzvqLEw', 'Evil witch', 'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJ5ahlbF3RdE6LFYaetxxuRIhfcdrkxiaeHfB81XYR1jY2DUKqAWdPtIhlavCNUokHUJBDXFQ3XKqg/132', '2', '挪威', '', '0', '1569943000120', '1569943000120', '0', '', null), ('oxrwq012dWSW4JYEOvmrv7cUTvFo', 'W', 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eouDIfq73Kq7UTCsxXLsrSSxqLTyr6HoWCB2RZZMbB0Qm3ZnY39J2zLhFsVpDHnpQu0hOU9RZHkHg/132', '1', '中国恩施', '15061880086', '0', '1570684738406', '1570684765196', '0', '', null), ('oxrwq01AkR9K-DxF33Ob46Qqs4R0', '啊？存在与虚无', 'http://thirdwx.qlogo.cn/mmopen/vi_32/PiajxSqBRaEJpbhnurIZycVnPqf9icAUOniaZo7kpLhGRZ4GpJSuaJXdFibzfAcLGcvk0RXw3nGaviaicKCcI0NcYKWw/132', '1', '爱尔兰', '', '0', '1570152720385', '1570152720385', '0', '', null), ('oxrwq01d6hQcZdF6egynp_62-NUE', '果', 'http://thirdwx.qlogo.cn/mmopen/vi_32/we69auvuzzia8Rm7PkrficXHePvJiaCv5LEy9hZtqLfPdHJribkGA6I3uO5Px5W0yNC86Cuzcun6VHGr58JR8D2JUg/132', '2', '中国桂林', '', '0', '1569976263166', '1569976263166', '0', '', null), ('oxrwq01fbT7ocH9PuqMkuQROb3wc', '', 'http://thirdwx.qlogo.cn/mmopen/vi_32/ibVuWHiaxK9ylt9icqvb3ByEiczEponZgjmZ7MERhCiaccVwp3pcqGnW0AyibvuMZjllxxK57oU35gWiaHEcJO0hsYa0w/132', '0', '', '18740503914', '0', '1570113766562', '1570113799568', '0', '', null), ('oxrwq01GPNCmQqjnnwl0MGMT8uc4', '十 จุ๊บ', 'http://thirdwx.qlogo.cn/mmopen/vi_32/o77dXbQHjr3ribkRA0HZyXRNKwwkhuwARyM4ZMUlSvGKdTm40hC6LVQCeWSKLwpbF7brXq2YK51ibXaXwklq0xlg/132', '2', '中国宝鸡', '', '0', '1570145713625', '1570145713625', '0', '', null), ('oxrwq01iwJTDpeifM9aj9QgtaCYs', '沉默是金', 'http://thirdwx.qlogo.cn/mmopen/vi_32/0PiaE2gL7vvLPQFVzHxj16B1FnD6icrI6Z3YDHFOhlgTuuwhJFXouhctel8nicgmmYf4WaBWybPLWVotN8NeyrFew/132', '0', '', '', '0', '1569973151624', '1569973151624', '0', '', null), ('oxrwq01jJ5UFVvvjb5Q5NFyh2sy4', '刘晶', 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eq6P7WFTnoDlfjr058TicUSibr0WCAvn8kJHjgEZmMBt5NHxQRpkmuo1JFc5pA0Gsb6PYHOqaic0lpwg/132', '2', '中国西安', '', '0', '1569989779032', '1569989779032', '0', '', null), ('oxrwq01KA840MfVWOX3UiTeYdeJo', '杨楠-骊山鹿鸣', 'http://thirdwx.qlogo.cn/mmopen/vi_32/kJdTL9xsZXVWjl6tzWLW4UdgR9c3wd45oJeYqTN3ZI2WpZf1IeyG3EV70CicrCGvx4teslTEcuQbxFuqrICGLaQ/132', '1', '中国西安', '17795633226', '0', '1570025822898', '1570025919704', '0', '', null), ('oxrwq01nQNZGzPV9cLSqlfkgO2VI', '宋戈', 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqHWjSvCWKgcoSlM37KghibCCNAbxFzk1vibwn6X2J2fscgRytqGqj0rzFltf1QD3vzibiab4vrzQedJA/132', '2', '中国西安', '', '0', '1570113874283', '1570113874283', '0', '', null), ('oxrwq01ooiIvfp0_kVKK5tu0fPPM', '不会游泳的鱼', 'http://thirdwx.qlogo.cn/mmopen/vi_32/lJBAApwwZicicwmTk5USumcBMvPUgekO5HddTeM64ws6ch3TQFiaaLDFbiakibFibvlXDQqlfoFpMXdlFpRp4WADIzQQ/132', '1', '中国忻州', '', '0', '1569945123374', '1569945123374', '0', '', null), ('oxrwq01P_O6BYdp0ZjeKGrKjT7O4', '。涛', 'http://thirdwx.qlogo.cn/mmopen/vi_32/Q2LT8JFWSghg0ncYTBQ86WkOhA6nynDgPefhNnIlnsRG2f6JNEn0D76FMwOCKapwuJEGEtb2w79bHVvz2TzTNw/132', '1', '中国咸阳', '15596148096', '0', '1570622228174', '1570622286678', '0', '', null), ('oxrwq01Rdp8VOz_LKg-JG-znhS8U', '人間失格', 'http://thirdwx.qlogo.cn/mmopen/vi_32/IMrJYgWApbKxbqxCqqankSUXiaFicT9L7cTSlkB4AGpdjZTQiaaFaQ5dibawen0UEY0ZTa454Ijlom1icMHcoa7M7HQ/132', '2', '中国', '', '0', '1570115682599', '1570115682599', '0', '', null), ('oxrwq01rvTAz2OYm8y3aud2YYYYU', '老来乐', 'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIlwJvMHL0bjmqW2BlvtkibajODib5RJibQicQxEjsuyyfh5vvuAyGRLLbuIUwXoznbgWB8iaoN8AMnBibg/132', '1', '', '', '0', '1569982531752', '1569982531752', '0', '', null), ('oxrwq0236nIi3eHUFNh_KPNsNqhM', '程贤健', 'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJDaSBQoHdsOvFjul4pzTdR0XNIeNuictteMU0Pxs2yr51SOVpIVS0YejbHqPb8VAxGVf3pal1w4vw/132', '1', '中国合肥', '', '0', '1569987330756', '1569987332438', '0', '', null), ('oxrwq028HU6d_lIvyvH5UrRh7Q_o', '..', 'http://thirdwx.qlogo.cn/mmopen/vi_32/wMOIVtSa8ticKd0qaBWsLK3rkseNdAdibZejDeT2gfuiaXeEbAibyX9NcRrd4JczzIhumveYQmX7Cnn6xZ3UWSCSfw/132', '1', '中国巴中', '', '0', '1569943942827', '1569943942827', '0', '', null), ('oxrwq02aySMdggUCNDVFfK6s91Uk', '五哥', 'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJYY3ib957sib6wz0xibFpJezgdIa90pEOm0lf82o4ibzPXDMEwyFo5NQIUX9v4D1UWTTj75DAbsTo5icA/132', '1', '中国西安', '', '0', '1569978274255', '1569978274255', '0', '', null), ('oxrwq02dt9F9NWiYSy4qbz5d2an8', '揽月.', 'http://thirdwx.qlogo.cn/mmopen/vi_32/HZVV6vHyv5BicckkSZBAWD5O2H1HxeI6cWXcdfrgbWmw9ZaXYCaze5iaAelXRLD8GicziaDG7dcB5rEXB5Rlv2hwEg/132', '0', '中国西安', '', '0', '1569942709071', '1569942709071', '0', '', null), ('oxrwq02JV37FHSlK38vZFLGfFOEo', '人在旅途', 'http://thirdwx.qlogo.cn/mmopen/vi_32/dth6gA9IDbYtIe70WNIJtbGorA7ddLxhAB62MaS6Q6icnaP0IHxhCia99MdiaZNkL0AiboHFofJ3uT8Gib8CKEP5VfA/132', '0', '中国宝鸡', '', '0', '1570154537423', '1570154537423', '0', '', null), ('oxrwq02MnA4HlCGlsBXudDJ3mbHg', '王景红13087555772', 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83epbqg7az8ADw4Wqr4zy9urd43vyYM2Zzez6p2JQGVa6kM4NVCAjeW7mgge6ZM1XgVv5kycEtA7XfA/132', '2', '中国西安', '', '0', '1570147525406', '1570147525406', '0', '', null), ('oxrwq02nIOZ-S-VSpxP5M2j09wsE', '骊山鹿鸣-杨楠', 'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIvO0AGP9hMEN6jmeKtan6M9adKdBl2PxEia6DSzPCk6dVOffNwf3WPNgsm3ClNqenIFvyhXulk14Q/132', '1', '中国西安', '13991842426', '0', '1569719301869', '1570338902886', '0', '91610115MA6UAC9Q21', null), ('oxrwq02ofrWWeCARraUnfJepeKoQ', ' 冰雪情', 'http://thirdwx.qlogo.cn/mmopen/vi_32/khSCxaCDAVaFgbTdQU22HwtgfQHv6dKQvbbJiagibLWrf0INbP2NMZ4yym1H0UeGo6uNnnBNMxiaCF0ujxswy6yZQ/132', '2', '中国鄂尔多斯', '', '0', '1569981448553', '1569981449013', '0', '', null), ('oxrwq02RN5MLivo4XSs2uaDTm9sQ', '大海', 'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIk7A88njA5mBIXatYUYQcaknjpbbdVTmUf2HJk8ibHYnk57gdwqTmIcugoaqoVicck46RT8Fp1AynA/132', '1', '中国西安', '', '0', '1569983711045', '1569983711045', '0', '', null), ('oxrwq02U20GO_lQ13p_idKbtoGsw', 'あうけ', 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqoDo1pRKzA4Nob7USf1Pl819X7sdntev30SHPcNN83ywpu9nIZI3wiaViclfykctkvpBEicY93UF6Vw/132', '2', '中国汉中', '17802960022', '0', '1570116011138', '1570116040167', '0', '', null), ('oxrwq03MLhPKlr5F3Xhgb4aib0ys', '不甘堕落i', 'http://thirdwx.qlogo.cn/mmopen/vi_32/dALoLsgJ4LbpzRpsbXnFd5licHMzxCu0nzoW4fk2pE3a2eLQKWXbZsALX8tUOMIoYAN9tgRoe3ZwvtgMiaLU5FwA/132', '1', '马尔代夫', '', '0', '1570113709446', '1570113709446', '0', '', null), ('oxrwq03pifFE_KxUjkvtkvhnXnUM', '路兵', 'http://thirdwx.qlogo.cn/mmopen/vi_32/qgtLNDBN9ENHfZUUnOZ67ibatKc15Cpxtfqhff78Oj4ROx2ABXp3cdzlCsYdibxnXCBZ7poJegTpyq5kVqsLWdBw/132', '1', '中国广州', '', '0', '1569997773374', '1569997773374', '0', '', null), ('oxrwq03rXY5AYUtlRbSPlmkXG9sQ', '赤盈科技-牛路平', 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83epibaKHRiaZAnY31FdqkKj0cWFAt70mjjpLlYkP7GyNYicpCNuUHomAFjNO2icDEwscXOHav3ZjwlQbaw/132', '1', '中国宝鸡', '', '0', '1570245409932', '1570245409932', '0', '', null), ('oxrwq03SVZqtc_C089fR9yOcsIto', '陈沐辰', 'http://thirdwx.qlogo.cn/mmopen/vi_32/zuHeO8va4UFeLa5D5yl2V6KTwMWvpIWCicUcUxQfWAHWWiajJP8oelh71H0VaMGNdmxe5SdZcvXXib8WUp0BOOL5w/132', '1', '百慕大', '', '0', '1569980953379', '1569980953379', '0', '', null), ('oxrwq03YRwTxHpsUCrRhUdSgc-es', 'iiii', 'http://thirdwx.qlogo.cn/mmopen/vi_32/2PibepUGaSlT4bxhuibGTs5zoE4NWzjIWe08svYDpxoK0CBiar3JV2PJdLr7RpmNa82BnUlVssVcLSL03ribkQx6Ig/132', '1', '中国西安', '17629154019', '0', '1569946108322', '1570113604718', '0', '', null), ('oxrwq040Qm3wkhVoc9hY8wMBMxBI', '台頭する', 'http://thirdwx.qlogo.cn/mmopen/vi_32/Jsv4Au0CUwlaqSoibBeIsbVjEcbBHf9NVO2VKFX5GfqUVJmnTIbKspxtOy1X49ib9NBSFAIibLueWCibOXwNuuZOtA/132', '1', '澳大利亚北帕默斯顿', '', '0', '1569976461629', '1569976461629', '0', '', null), ('oxrwq0489P5p9QSmo4JAfvz76tDg', 'HQC', 'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKOjmsCYpuqtNNQEe9eqmfibmx3LeG0oITDSnzFIhIr4NY4ywgTRbI6phUQfejyicB5nSOJ6Od27Jkw/132', '2', '韩国', '', '0', '1570177694055', '1570177694055', '0', '', null), ('oxrwq04asFtwnpvz_BSIt22Bh1is', '海神', 'http://thirdwx.qlogo.cn/mmopen/vi_32/Ws7FnGTEgewvnm6ic9nGh9xIaCRPDd0bhntDrMKlaCT1qibKia3WazuyvRGiaciaSANwII0Hk0S6QHbicluPgrBKlEaw/132', '1', '西班牙', '', '0', '1570270106936', '1570270106936', '0', '', null), ('oxrwq04DmafTAJXPCCWYbOQfLTKg', '平凡', 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoZEhrSIuWxTEia9hVo3c3ADAktI8W5kibRmHUMmtr5R8QV3OyBaiaLbymtJFCnK82Z2bxzrwJMQHv7A/132', '2', '中国咸阳', '', '0', '1569976667552', '1569976667552', '0', '', null), ('oxrwq04fkd-W01fGrw1HZ9Za5Igg', '无忧花开', 'http://thirdwx.qlogo.cn/mmopen/vi_32/hgmUcGJt7QMPw3LmEjVED80IqHCyeGVsJXxQIlQ4enqkNOyztAFwDzC4y029zG7nTriapEibLadVHzaBAWxZ7klw/132', '2', '中国商洛', '', '0', '1569909981217', '1569909981217', '0', '', null), ('oxrwq04KgY2DgQFZ1tuJYnXWEBYM', '小黑', 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoyjYXsXpziaCf7s0waib7EZg7wvI7eKFBSMWCRVK6U8M7ml4DGSaQtIMUfa2b0fVX9NgIG0eicSgJDA/132', '1', '中国渭南', '', '0', '1570622265762', '1570622265762', '0', '', null), ('oxrwq04L3EIPsmyRh-zvcSw1-VPo', 'Gökotta', 'http://thirdwx.qlogo.cn/mmopen/vi_32/PiajxSqBRaEK01zcrIFL3m0JJ8EuZVt50W2EtZic6Qn5MO70adx28tghrq2xwxJPsajK9E01zmGjqHvqFfceBt2w/132', '2', '中国商洛', '', '0', '1569978140143', '1569978140143', '0', '', null), ('oxrwq04NqHxfX4MONjXKyhrc9Bzo', '睿睿', 'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLQD7lFVWLp9wRL75rv4zUYFZUibZUxR4IqMMeDjlpn9FoCv61icOpx3tSsBIU3Mk6B2OM6WyoQ5DjQ/132', '0', '', '', '0', '1570797641090', '1570797641090', '0', '', '1570797641090'), ('oxrwq04NVlpK6iAqgjNbD9ECG8eU', '十四', 'http://thirdwx.qlogo.cn/mmopen/vi_32/3xwfTdiaVejZdm07OqI8IpZWg3HvgShowclL4C8bmRHP6tXYU34nYT2crJLJh3ibiaDHX3dl99ibRggrEibvOl25S0Q/132', '1', '日本', '', '0', '1569990379125', '1569990379125', '0', '', null), ('oxrwq04qsAvL_ww7WXDB2YWspLsU', '伐木磊', 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqAXcbicN2gQuwJ40wgnZibNmbz1yRoh67pcoYNteGL2YkjyYJc4GRVHcoNOISRR3aNVS7doV3Sf7uA/132', '1', '中国西安', '', '0', '1570370026045', '1570370026045', '0', '', null), ('oxrwq04UobtCqS6xCDinqqvBAmEA', 'Doraemon', 'http://thirdwx.qlogo.cn/mmopen/vi_32/yUZQUNkyam2F1uanQTO2m89ic3sL9EJ913D2iak84Htal86pVRZH8Dvqz911z9aRcwhBaUoaGD8xlqhEjogaBHxA/132', '2', '中国', '', '0', '1569975833509', '1569975833509', '0', '', null), ('oxrwq04wwAVDFH_2gb208yDleVCY', '吃苹果的苹果', 'http://thirdwx.qlogo.cn/mmopen/vi_32/l3zJgicDxibiaWmVosqxvS5mHkH4CZjibia03trF34DE0IoLln8fibLn2Ipgzy5ktkOOHicx3psIQYlPbVEn7eYfE68Kg/132', '0', '中国抚州', '', '0', '1570114864077', '1570114864077', '0', '', null), ('oxrwq04_pnAbR4Elv6AEcO31uVF8', '好人一生平安', 'http://thirdwx.qlogo.cn/mmopen/vi_32/wWwqibZ06ibhibyXq1AnQ7rviafpTzIfAiaI38UFZ4RL2lMQ0Oyd3TImib9ERYw0uZmdKsTL11mBmNout7iaNZpWDL37g/132', '2', '中国榆林', '', '0', '1570021058613', '1570021058613', '0', '', null), ('oxrwq056AQ4pE3DDGXSnvxavihyw', 'Carola', 'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIqVbziblqryvQLvDDPeR3s4Smic6ichya2UicRKVHdCPZhwpfSZelFUde1KfibicC4Zlib5fGKElPicDhjNw/132', '2', '中国西安', '', '0', '1570096185064', '1570096185064', '0', '', null), ('oxrwq058ckxXVmi-4uSB3xMC8VOk', 'lv.can', 'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLkCzpkfk33niaVKczH8rZEwBN0zd3mjfKOlrNptJz57CudRfaibAugnCVYmF4fMQAxQAleT9FeDEbg/132', '1', '中国青岛', '', '0', '1569913960157', '1570088362354', '0', '', null), ('oxrwq05AMWLbJ8Gt9izBZ4Qdmovc', 'Aurora。', 'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLS8zaib4RBuZTzV8EicHV3mgCp6nvXtFDuKA2vkwFZIau5BDUOLw9RbxwyHNibbOIxksJLZ0H0COKMg/132', '2', '中国渭南', '', '0', '1569996657421', '1569996657421', '0', '', null), ('oxrwq05EPVzj6nxeoMmRNyHhJ208', '你惯的啊！', 'http://thirdwx.qlogo.cn/mmopen/vi_32/Vbrc0H0zfaGhBfOWdhuxxorIRnyvibOs3gsr1UoMWcTWGomiakFjuYqknnfOnnQsSfaiaLlssNe5Vl6gOepSIzsicg/132', '2', '中国宝鸡', '', '0', '1570193920030', '1570193920030', '0', '', null), ('oxrwq05JnjZxRCHCKkA6P-QS8Skg', 'LE_CHAT', 'http://thirdwx.qlogo.cn/mmopen/vi_32/sWJX4YJIuhibOOScX09NbBiaJzzCRR5kTnuv1lnFaFx7MjFb77DjzibKHKW2dOQ7srvQkicodY3QPovAUeJDOCnV0Q/132', '1', '中国西安', '', '0', '1569661124626', '1569661124626', '0', '', null), ('oxrwq05Wm765n-67P5m7-JhibmgE', '朔方老师', 'http://thirdwx.qlogo.cn/mmopen/vi_32/LYgMBIelpHh8rKcHibnO8oicic2icmDqrrxYDsKibJWKiaUib9gubok7wb0icWOVpxhj0xgslHYtPWFMdn9EJrKlzleajA/132', '1', '中国西安', '', '0', '1570181851461', '1570181851461', '0', '', null), ('oxrwq05YXJiKTQ9RuH9pvXT_Koa4', '紫璐', 'http://thirdwx.qlogo.cn/mmopen/vi_32/RU8ibTkrp1bib1ib5zypF9wdJU4LZvicAuXkVwTMMnC1tfT9zGaGP9bcHEY7pHmc1IKFVVqRsNpHg6WVFbaw211QYw/132', '1', '中国延安', '18740616934', '0', '1570622352242', '1570622409153', '0', '', null), ('oxrwq065CCsOEsGcVsAKbvcENZ2U', '落叶亻孤鸿', 'http://thirdwx.qlogo.cn/mmopen/vi_32/yptqicWUId0zlpmLKDtjmusqNnOKTWiaXz42eVFicSorWPpTiauoHvWL9aDXXZ60xjKt5GlURjScjl0f64QD8hmLZA/132', '1', '冰岛', '', '0', '1570062479216', '1570062479216', '0', '', null), ('oxrwq06m-6yo4oB5T2povH-HnL48', '。', 'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTISZd070icpZWXefibjwb028ghP39U2ew3QYxYqFg6lewKKKmPI8cQZ0ojnpicUTmEj0YlkxkMcHM9kA/132', '2', '阿根廷', '', '0', '1569945719929', '1569945719929', '0', '', null), ('oxrwq06RQK31iS7swUNrRLKV1gEk', '清', 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoxGdJSpjibOhbqM3vam0UlrjGNOoN7Ke6N9icgU3C3daG7ulHEr54DEiaHv4TvOm2LbO4Cf9StAAFMw/132', '2', '中国连云港', '', '0', '1570023825047', '1570023825047', '0', '', null), ('oxrwq06TCRmlrmO1ZnJcwACOImwU', '沐沐月', 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83er0425ylsDibKJlZWicnia2X09gOgq01DG9JdDc4glicUicCJZqLAgf7EgA5xJE08Fq33gWJw53hIWhxWA/132', '2', '', '', '0', '1570109809900', '1570109809900', '0', '', null), ('oxrwq06W6NFgMijbhBz7SpHrV2fo', '田大有', 'http://thirdwx.qlogo.cn/mmopen/vi_32/ALkIxRaHGxmicJWHXradcpMOia0R5Nsh1LS20je5KzKo00zdLkuhkHZ96C1POK4iaK4LLq5Q8A48JY8dibD8OKAqRA/132', '1', '阿富汗', '', '0', '1570116973029', '1570116973029', '0', '', null), ('oxrwq06xp97bxh5CCtbCPqZ1BusE', '孙壮磊18538054999', 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eruvoNDJum39piboSdxsPliaj8zeFAXbVCVYfnPoo7kzqOSHlV0S9KxNpP32Pozv5ohQXk9JJcicMP4A/132', '1', '中国郑州', '', '0', '1570143282328', '1570143282328', '0', '', null), ('oxrwq06_yszi9aP8L-1oC5phSr9o', 'A。毒 特  ', 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eruPvU6dkYFTGeN7IuArfqWFhBgd7KOYGiaTufDbFsZIqBLTBpHibPWWFludJiaX6Oibcf2ic7wibMaVV0w/132', '2', '中国渭南', '', '0', '1570148468573', '1570148468573', '0', '', null), ('oxrwq076lV2nSG9WJoUaBuzLek78', '柠栀', 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eq9Pv583YavEWjrXibMaorOQQQqovTObLAWO72e6YibZhmmpOh2jJk9TaVDp3NdvZhfooiasPhbENSgg/132', '2', '玻利维亚', '', '0', '1569945694978', '1569945694978', '0', '', null), ('oxrwq07e2UCAIP6AGnzAKHmuPdcg', '112微笑', 'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKDPyg90yZtJghibSSIG8ghFuySbzWzHfFHPAQjqQbgvKTCxldIRudb3WoKiarYW5Cd0eYQaHibVwyZg/132', '2', '中国榆林', '', '0', '1569987543819', '1570023469810', '0', '', null), ('oxrwq07jaJdEavwa_0zuwxw048Y0', '소가 기뻐하다.囍', 'http://thirdwx.qlogo.cn/mmopen/vi_32/KjQwgJXsbgWV6clChcqOPNs3eQ3Bib4H9BLWJKibWTMD3ONuoSbglJ6JrrSRbBNs3Ia8d6TyCZZPqoCXklF96OTg/132', '1', '中国榆林', '', '0', '1570622308472', '1570622308472', '0', '', null), ('oxrwq07NnRjV7bSTlf0brsCu1pIE', '眠空', 'http://thirdwx.qlogo.cn/mmopen/vi_32/b1tbAibFj0354ibeSlv1ic84sxTcnhCmXhEoxsIKFibVZV3YFE45b0c0ACTlAJGV4c7v9fnVvW3xoDZ8HJ953NslOA/132', '0', '', '', '0', '1569984154161', '1569984154161', '0', '', null), ('oxrwq07slQ2HaOqyYbhLNQ7jRMlg', '噜噜', 'http://thirdwx.qlogo.cn/mmopen/vi_32/TicIdNMR9b27e7Llq2Z9M3dicygpibXsib6QNQFr0z0pZ7SDX398d6wJh1xLywaS6rsmlYTY4qickG4Uhq5gnWOrWJA/132', '2', '中国徐州', '', '0', '1569910628508', '1569910628508', '0', '', null), ('oxrwq086cIUuiSsyW7lhs3aX6PR4', '小七', 'http://thirdwx.qlogo.cn/mmopen/vi_32/4iajBMbvZRpr7rw7e1Yezvibxmic7VWl7lnksibf1lvBHeElr6kqzmaqXJ4oHPcYjW8kZ0uIuicfethukdPhic2qYicrg/132', '1', '中国', '', '0', '1570075079196', '1570075079196', '0', '', null), ('oxrwq08aFLkgke_caUsWKs9nw168', '张彦', 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqLUsO9BwUlZnVHB0QIGtCJZw9gIQpx8mI2W6l1DXxiaIX6RBxcKj6u4oAKWLxiclAKibsnV4cyfWmrQ/132', '2', '', '', '0', '1570586436583', '1570586436583', '0', '', null), ('oxrwq08apU8G64KaxGvsqWDwOc9Q', '小乖', 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83epgZiapiac4TMOmxX06fkWVq0Qu3OKsNpm1xJHl8ibzUlEddZjk29OOwibSuN7DTAgicaMIo18ts4aayxA/132', '2', '中国常州', '', '0', '1569910202372', '1569910202372', '0', '', null), ('oxrwq08ayovTKLoo4Om3079pb3Fc', '随缘', 'http://thirdwx.qlogo.cn/mmopen/vi_32/nCexxEmgtsNnu7sH5y3r17HibSd1ibe9FVbiaiazaHiawibFSOPskUiasiadzTr8Za83a7HaJpfX29XdrlicZGHvsv8FYRQ/132', '1', '中国咸阳', '', '0', '1569974707991', '1569974707991', '0', '', null), ('oxrwq08CnJliTs93vvvHAeyMqj8Y', 'Lemon Tree', 'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJwiaNTDGWib7hpDE5eTwqIMnCaVVaU0ibvNKj7cL1j7QACE8FGLwHUDyLhjevvB3qYNOATOxM6JVHhw/132', '2', '中国汉中', '', '0', '1569911434341', '1569911434341', '0', '', null), ('oxrwq08LkVbdk7lzrk3BnpFLLwdQ', 'ZJL', 'http://thirdwx.qlogo.cn/mmopen/vi_32/eVbQqO1ibw2VLnicWmiaUOUwvFUztURSYG7GkCgGFT4dQa4mU8L251A0qfm7QMUaViaaDMzuRB2Ne7XRsa4knDpPAw/132', '0', '', '', '0', '1570024409012', '1570024409012', '0', '', null), ('oxrwq08nGxBldWUSFAbPCZwpk6lg', '弥夏', 'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLhN1GvF555srddA5Ru6dG9HoYYHQOpu9WJtRiajJbU6ia6TwRfeKzfjWJn9PX9OVPia3HhtCyKvXtKw/132', '1', '中国苏州', '', '0', '1569946623225', '1569946623225', '0', '', null), ('oxrwq08pJ7pGPUdXOpVyilZyjHWw', '沐凉', 'http://thirdwx.qlogo.cn/mmopen/vi_32/OSicKMDUd9yxT2QvDCmdFHRAqwVibaGtymsfpXcHlXMfNFq6NJMdoZImeW2byqHYATFiaqzyPDRpCyKJYKmhfLHPQ/132', '0', '挪威', '', '0', '1569992462304', '1569992462304', '0', '', null), ('oxrwq08xdS9en4KvgaxQFCZJkhBk', '三步一回首', 'http://thirdwx.qlogo.cn/mmopen/vi_32/PiajxSqBRaELpKHGBvfGRtJ6glh2ibFlAzVqA1jmGGrUeknJ7Zrde4Kkmz3DcHfLMEiap68AArMgEHricEAbXDlqBw/132', '2', '中国汉中', '', '0', '1570088406521', '1570088406521', '0', '', null), ('oxrwq093qmroMdX1ZR3nOBTpWy-o', '绝＄情', 'http://thirdwx.qlogo.cn/mmopen/vi_32/ictPVAIDMLMgo1DLan1vThfwxLn8VSZSZp6gPOl1wZZXWsciaOgWSAym3xYKYHutwZT4jeX9icCt4TXMZC0fiaCZzg/132', '1', '中国延安', '', '0', '1570622296899', '1570622296899', '0', '', null), ('oxrwq09bdKumk0ztwfKteoc25Rm4', '闪现到我怀里', 'http://thirdwx.qlogo.cn/mmopen/vi_32/HyNMZcGbNb14IcoViacTm7QKC4VTfyibrP1Eu1m9N0eZeZ03CjPCC5t3FBhK9RjuXq4I5yBJPTziah8DJicWtn6c1Q/132', '1', '中国商洛', '', '0', '1570622301589', '1570622301589', '0', '', null), ('oxrwq09GqZQAg4_Jo8w4unaKjY7M', '不一样的天空', 'http://thirdwx.qlogo.cn/mmopen/vi_32/d7NUPcwRHo9U9tPBKVQmibCa2MUYBZUVKH6hDj4INH6Jyuwxtc0Yabouicjm7SkrVu1hHdZA604MUmY471ndrWGQ/132', '1', '中国', '', '0', '1569976292471', '1569976292471', '0', '', null), ('oxrwq09KNHBVavf6gv4qEPBzQDtI', '90年的山泉水', 'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJ2QrUG7htvgW5rpUyDibkgBJvuib3tgtZYZg85DD3CEnov1Sk3xatJSUJjwpQ1TnX4e1JEHhazFXtg/132', '1', '中国西安', '', '0', '1569989899602', '1569989899602', '0', '', null), ('oxrwq09nv2B37NAUsoDb3a1cj-a4', '酒满觞', 'http://thirdwx.qlogo.cn/mmopen/vi_32/qrLibiczMaSJcQdJa6piaHSDxo5lm57oA3o7ndyA9UKTbHTsIhO3CN6Pq46t5WOOK62iaT4xHmespmpZ7Vz8FoibZEw/132', '1', '中国西安', '18729446770', '0', '1570768762709', '1570768807907', '0', '', null), ('oxrwq09oHIGqVr0Rvxp2vRKxvamM', '賜', 'http://thirdwx.qlogo.cn/mmopen/vi_32/Otfyvqsib9iaSTZia0puibCSB6Tx7INZVAuneaY4VQKp0hbLlmSd8mnDUXff09kz1SDG6qoj6KtBD3RNGiag5RYDWqg/132', '1', '泽西岛', '', '0', '1569857149182', '1569857149182', '0', '', null), ('oxrwq09psb_yL--ZzmxisNEFl1JE', '陈清丽', 'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJuv322wb37dvwicpZvZZXVsMlTvqxXcZfFbHIowia9jQTmKl1o2WxFX66oLlbSZ8micQlmstHc5FB4A/132', '2', '中国延安', '', '0', '1570071518629', '1570071518629', '0', '', null), ('oxrwq09R8Lf3MnOct4DP1SWbBNCo', 'B', 'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKd3DR6Uyx4c4XqoPBtx0eJzNH9EHs3TN7UHhTXlECQ5bp7ZoNR05vNz3sPQD9rKgdjgqXiaDhtfiag/132', '0', '', '', '0', '1570115586331', '1570115586331', '0', '', null), ('oxrwq09SA677Fh6aaMC5erQ-C6HI', '匆匆。', 'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLKibSO6zNf9MpEHwpS5SibDNk4hA6tNfIMnpZEPy6AoB3FpMiaw9YKvYt4YNWYlbwXr3ecG8O6GgGxA/132', '2', '中国焦作', '', '0', '1570008493857', '1570008493857', '0', '', null), ('oxrwq09uo7-3A3G87l7TiAq4oQ4c', 'capital °故作', 'http://thirdwx.qlogo.cn/mmopen/vi_32/tKvmZ3Vs4t6ibRGlZq9ibRKfMAEwB4vBNtYfvGzgI0z6G8zEaLziateiceY13ArIhSV61eb71Kv5q34KxkSBVoKPiaA/132', '1', '中国贵港', '19829805674', '0', '1569909961500', '1569918991298', '0', '', null), ('oxrwq09x12syfIwQwg9yaVZ3HkUc', 'VV', 'http://thirdwx.qlogo.cn/mmopen/vi_32/yGuwAyZgZ8w9rybLicvuTKyftd9d66hbXcsSmc5v8K0wbUqNZf5FfumTicpQElsW0cVJDrRL7gGDO3N2k76RFMqw/132', '2', '中国', '', '0', '1570150631724', '1570150631724', '0', '', null), ('oxrwq0w0m_2jamnS6H-oGrVfSUD0', '钱先生', 'http://thirdwx.qlogo.cn/mmopen/vi_32/mHh6Oib8uibBsV5jJe8MQpynLV30JA3uAsRxtN5wpISXSP2MXUymWJum0mH9WWrqUdCha2HIR8dTgOlYZYibQNwWw/132', '1', '阿尔巴尼亚', '', '0', '1570622312062', '1570622312062', '0', '', null), ('oxrwq0w1fam126YN4moZ7DTRM19M', 'devil', 'http://thirdwx.qlogo.cn/mmopen/vi_32/DfFEBhr3PrOrDKd3AsgcMDLR2dKJzqicGu72jQXQm35Tf3Mlz3t37mXcHgWqJMlI2XLrgyZL4JD03zMa0qia1YEQ/132', '1', '中国', '', '0', '1570795289731', '1570795289731', '0', '', null), ('oxrwq0wDDOMj3DhrZeYazwHeiuSM', '泡泡', 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83epgDLZXJvAwgdvVVEsOhzp8BbmVXJbA1jXpVyBpEqibsVDzXdqVIwN06gGSroO8icz8j5wfWXCB3dHA/132', '2', '中国晋中', '', '0', '1569715201086', '1569715201086', '0', '', null), ('oxrwq0wifjNCu9K2gpnD9Wt1-k-c', '勿锡', 'http://thirdwx.qlogo.cn/mmopen/vi_32/icXv3LTVtxJVXKUUHhztsfYaAqQJVByouCHzL0RiaPkZEVpyp5wN032ibicDRdrwv1bBEe9WP31PyBT35GYxMuEoIA/132', '1', '中国安康', '', '0', '1570015590002', '1570015590002', '0', '', null), ('oxrwq0wJLpAyOi3Zp_fuYMbwh1bg', '梦一场', 'http://thirdwx.qlogo.cn/mmopen/vi_32/ibpjLLCGEiccFLQgkWSOroibibCs9GQMs4tTk1h9KGsITS2JUItQaZgJ3GoLUdnbBWMM8L3QlDcDovjSZOZLkGVFNw/132', '1', '中国盐城', '', '0', '1569909962253', '1569909962253', '0', '', null), ('oxrwq0wk26nXu198lVCkDhVvhVPE', '倾城，如梦。', 'http://thirdwx.qlogo.cn/mmopen/vi_32/LibicMAgEjDwbQQjmx2aibh5lFsPFu2A2Hq8HDlrzcQZFqaZicY8IsUVNI47XibjazZLNR6fIFa3VRyZsSSibxbggzGA/132', '1', '中国平凉', '', '0', '1569947928014', '1569947928015', '0', '', null), ('oxrwq0wouDBpOWlyIMmdr17joV8c', '安妮', 'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJrOqOKejp0DCyznWJtZdW78HN0iaRZ2xibL9DEUMtJpH0TtXsccDvU8AwtmPpvtSjQs5mGiagicRaCRA/132', '1', '中国西安', '', '0', '1570052149824', '1570052149824', '0', '', null), ('oxrwq0wpQyoAORZSc-X6lP2XNt_U', '胖虎', 'http://thirdwx.qlogo.cn/mmopen/vi_32/kI08S5tNAicB5rNLW5icreHFQoHGgvMiabF48ttZqj07TO1TQWDjEjg9nGs9DXiao0icTiazLDkkFwyiaXN8o2mHNE1Vg/132', '1', '中国西安', '', '0', '1569993081063', '1569993081063', '0', '', null), ('oxrwq0wSTxdyd595CTzXtcLwZgPo', '小李小李 谁都不理', 'http://thirdwx.qlogo.cn/mmopen/vi_32/SpAQVG2eF64lyZHlA4M8yhDWEP5wXRy0Jozq3xXDyk2q6pOBuGKujaXfMvrGicJibhkThduNkT0k1ZAkzcf5iaZEg/132', '1', '爱尔兰', '', '0', '1570187103320', '1570187103320', '0', '', null), ('oxrwq0wtGzR-B0KD7qPVE6osfYcM', 'X', 'http://thirdwx.qlogo.cn/mmopen/vi_32/NlibUd3icjH1fHo3HnbxaoAlAQEDxN1f7lgA7Rco8UnfKVpUx9HPG4NjCYiatuDL7moTOk46Uf7uExPNib4Ll3EZeA/132', '1', '中国安康', '', '0', '1569988557509', '1569988557509', '0', '', null), ('oxrwq0wuEZk_UOl0qqrGemb8ezBw', '孟婆来碗汤', 'http://thirdwx.qlogo.cn/mmopen/vi_32/E5Nte3Rb6zugorr3On9SG1HC512sLB6uG2Pay631TB5o4UbUm9TX3QM36ktpUIanPEsMbWgIXKFBwBjd1pPYZQ/132', '2', '奥地利', '', '0', '1569975852841', '1569975852841', '0', '', null), ('oxrwq0wuOzJ-t5UkCnFI22ZF2vOM', 'Just be myself', 'http://thirdwx.qlogo.cn/mmopen/vi_32/WAKyVtSjxnS92bVSHgBdadw969RTLuT2003lOSUySn8OUd0tM6yZGbItyKBsmLHicOTIVBWdO69kzIdbDW9Vgcg/132', '1', '阿尔巴尼亚', '', '0', '1570121464282', '1570121464282', '0', '', null), ('oxrwq0x1ePdIVfQlq6Ld6ZKYnYNs', '校企资源 项目服务_程', 'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJgT86lpEiajXHNgQ7fb81M34I40OyXHbKWic4Pgicxick9xegHSuGHokmN6jcpbwDakrrCAndmZEtsHw/132', '1', '中国西安', '', '0', '1570451413230', '1570451413230', '0', '', null), ('oxrwq0x1geVHhskY4zqKdUKVr97U', '，，，', 'http://thirdwx.qlogo.cn/mmopen/vi_32/xFN3u7Xxe11cNrQdGwMuicBbib9ybfl4UpEICsoLKmgGnoUrs0C9ww9gJKhAicvtUTeTB82O2qW16MbS7ewRS24AQ/132', '1', '中国西安', '', '0', '1569850911444', '1569850911444', '0', '', null), ('oxrwq0xGbQJjN8F34lsVcahSOJ2E', '。。。。。。', 'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIBPfKibR4ibichwJib8siaTYsRoW81UicMfx1YPnb1z7MrBtzCLHYibYHCbzicn1OY4fE85dZTia752ekvmyw/132', '1', '中国延安', '', '0', '1570184372132', '1570184372132', '0', '', null), ('oxrwq0xrKKyqiAGE8O9TM3L1yaQY', 'ahojcn', 'http://thirdwx.qlogo.cn/mmopen/vi_32/hyCfOptGJaWH4dYwqJNlCSBnPmJqJHBJ32FtxIjia3yQonGLHjQu1BYq9EBQ2BjM5IAmiaPlWxZiajVQ0lEf3G4Tw/132', '1', '中国', '15229720759', '0', '1569720603673', '1570093921379', '0', '', null), ('oxrwq0xTWZSuaSKO9BufeFU0cpK4', '坐山客', 'http://thirdwx.qlogo.cn/mmopen/vi_32/gPibs60Vt5OtCgxFRz2KNiboZqicHoWNHHAY4VHZSMMTO8RUxv455ymRj8NnVaEn2MrX40cVFp3MfSn4FF5z1AJCg/132', '1', '中国莱芜', '', '0', '1570151531575', '1570151531575', '0', '', null), ('oxrwq0xXYJWuu1ViFsPrK8zU-h5E', '喜欢远方。', 'http://thirdwx.qlogo.cn/mmopen/vi_32/mRN8ohAJhHmYZ7dpica8icnpbtFiaeB6knaVCTftuiaZjTqey63loSwiagpp09SbuYsV7ibXxFyRFJib6SquEfNHVKoMw/132', '1', '黎巴嫩', '', '0', '1570622238555', '1570622238555', '0', '', null), ('oxrwq0y48UhAK6zQ1WzSj1n2u29M', '张卿瑜', 'http://thirdwx.qlogo.cn/mmopen/vi_32/9lMyaya8qIbvbfjH9ee1xicjl6AYu7T6zl5E0vABicltHNb6bjyTahwrCgRxLXtf5lIaHEJ7hmpXQebStnxh9hoQ/132', '1', '中国平凉', '', '0', '1569978905441', '1569978905441', '0', '', null), ('oxrwq0y8scBrI00wUNVkVUTDPt2Y', '「一抹夏憂', 'http://thirdwx.qlogo.cn/mmopen/vi_32/FyYngZ1HovzeLl2TAZ2IpDIIRo3vUVSomy1wqCW4eLL3eBhQ9XD9xPW1pr2Ay0TB2oSBSFP0icr0cEUbibxlnJbg/132', '2', '中国榆林', '', '0', '1569977440078', '1569977440078', '0', '', null), ('oxrwq0yasUXdhQCb6SvpMjLMui9E', '七 年', 'http://thirdwx.qlogo.cn/mmopen/vi_32/4VlQFZeVNJaZfgAVVEu7ia8nvibic6y91wEMH7lnN3zxA6PFELe19McQs36zw5Ria4GhmATNspCjVgXfsFTgA3hYgg/132', '2', '阿尔及利亚', '', '0', '1569974420470', '1569974420470', '0', '', null), ('oxrwq0ygXZOLT8HbtwvbZrtCVa1E', 'つ沈挚', 'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJKj3GbvevFib0ZpgtzKOZZSCufpiasa4DEukadFEOspjHYzwRyV9XQ0iaMAVWazBnmwjcL57AIRQtwQ/132', '1', '中国延安', '', '0', '1569914064991', '1569914064991', '0', '', null), ('oxrwq0yjuQt5ZAhXlHZAc3NrR5gE', '( ॑꒳ ॑ )', 'http://thirdwx.qlogo.cn/mmopen/vi_32/m71QBxnfOwxdKh4KvBmzmibwyeXb3sNQE3ll7KIaQDcJymcNaTBuOFFiacwMbL68JiaZwEc9U7JfRnuE86q4udUjw/132', '1', '圣诞岛', '', '0', '1569994217378', '1569994217378', '0', '', null), ('oxrwq0yqbTVQsiZj6WsjW-i6aX7M', '枫', 'http://thirdwx.qlogo.cn/mmopen/vi_32/zN0zNa6WxPp8sya8edbswAbwbyRDz9FR6IxQXZqekH9gUqibsCLaZaKLQtt3ickTvp0wUKk70201Ac081XFBO8icg/132', '1', '中国汉中', '15191611587', '0', '1570622367404', '1570622402565', '0', '', null), ('oxrwq0yXmhSQuFcWAaAPJB47bk58', '樟榕', 'http://thirdwx.qlogo.cn/mmopen/vi_32/IHHXHITHpo0f5I4e9iaSV70kxI2MDuDPeocG4dW6BeicwCNv8C8Dzt6QOTfSRx0HXibLdPPy1JYtRo53UvwuuzAlQ/132', '0', '', '', '0', '1570111733125', '1570111733125', '0', '', null), ('oxrwq0z2o6FxEgeicbTCAa58wnH8', '回一', 'http://thirdwx.qlogo.cn/mmopen/vi_32/v3PzTIpFEhHgAHUvbV9If3Ffic3T6fTZFtamuZicXdXKHmBxGQv9yxX2xWhEp0X7LwSkV2cokAtFAPNEv7hIHqAg/132', '2', '中国咸阳', '', '0', '1569986354729', '1569986354729', '0', '', null), ('oxrwq0zcnqvAiWREYCCIPcZvxfic', '春雨', 'http://thirdwx.qlogo.cn/mmopen/vi_32/zmdicvMlEulia47ZkxIKbg8pDJnhS0eynkcKibV4YPOaXib4CuS7dmjic5CnCHTROZyianC2lbVcrNBxicA8utRxD5micA/132', '2', '中国乌海', '', '0', '1570023304280', '1570023304280', '0', '', null), ('oxrwq0zK3Ii_65mSPZ_NHcNZNanI', '花开花落我一样珍惜', 'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLXiaYTlV5iaq7ETFE8CckbJmzicRNOSZXbRl6nK6sW2Sf49ZJ43ibbD1cMMRrJcFiaLBAYXoppclGAK4Q/132', '1', '伊朗', '', '0', '1569944152623', '1569944152623', '0', '', null), ('oxrwq0zKTH8dldWMkBTaJYCmCxAc', 'Mick.', 'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLEcjPDHPf4JOicLpohOepCqtgc8icia1e6cDBaycic3n7j7n5sicGxcvNd425siaoxZd0L8bbV4HehRkTw/132', '1', '加蓬', '18392012005', '0', '1570158702061', '1570158976741', '0', '', null), ('oxrwq0zLgLoRxas1vkNWNV5DOU5E', '琰风', '', '0', '', '15934846402', '0', '1570511051971', '1570511131467', '0', '', null), ('oxrwq0zoKCHjx7LTSQHNcvya3R1s', 'X', 'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLrdtbxMd0svyFPRFfB88AzUjKm9X7MibBSExiay2gXlaonMApDTj6lo7dkZHHEgwYpIDCrMlysUbEg/132', '2', '中国西安', '', '0', '1569978841551', '1569978841551', '0', '', null), ('oxrwq0zPbgTB-gV9Y4Q-hN4g25Fk', 'Tim', 'http://thirdwx.qlogo.cn/mmopen/vi_32/bxVEQxwmOLibgHtYurJxvW0yicXLVcTCUiaDQDqibEyoIKwS7ZRdOsZL02RibF79vdNt6GgFKMr4fuDNV8T7X3ficTfg/132', '1', '泽西岛', '15291418231', '0', '1569650974592', '1570014746938', '3', '', '-9223370466056978061'), ('oxrwq0zuaETzBm4cJS3v08PVkqE4', 'L', 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83erz18NZy8KuGncWDJSVkMy3b1weSyxHNkgoKEsCViaMP0QkjZzOhAPkeibD4bIVbianb2h3M0XDMpvuQ/132', '2', '中国汉中', '13720619020', '0', '1569720603673', '1569720729430', '0', '', null), ('oxrwq0zw1jIgZ4DPxk6kCQDBdEOo', '西酞普兰', 'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLEKDUAib0Z4LlicckP9lvElUtvqcB6FaBSEeqKicfQMaRGuezAk0TghyoedVSf5s8XNicCqJA5ibLcOWA/132', '1', '中国', '', '0', '1569980811857', '1569980811857', '0', '', null), ('oxrwq0zx8GinCKwFc4OYKeBV1fg8', '既康小寨店轻体管家小强', 'http://thirdwx.qlogo.cn/mmopen/vi_32/hbzeC3naTdEibcQWWdnbNjKA4szPIY4s7nI30N4HDdlSMlsJIvy26Xia7iaEndsPiavYbrySHkgjoDGico13TDoUshA/132', '1', '中国西安', '', '0', '1569997227581', '1569997227581', '0', '', null), ('oxrwq0zXuQmuVqfchM9gZyBEAC6E', '你的名字', 'http://thirdwx.qlogo.cn/mmopen/vi_32/dBU0GfWIWN4F9YumQVYUNGyWbVZyiaib8bDIkYUvWWWyKZBEyVVUAm5Cia4LpwVWM85N0ic8Mic9rpyzb1cHr1NRVBg/132', '0', '', '', '0', '1570061830218', '1570061830218', '0', '', null), ('oxrwq0zYtKkONNOZ6lT-XOxy69_o', '一盏茶香、侵没我半世烛光', 'http://thirdwx.qlogo.cn/mmopen/vi_32/UWsDbwNvZ3OLwzW6vbnOwNUzrDFh85lj0OzGbZkmsBILHdiaGqSH10ibkwBNuzxPTR9xIuxibHhJLBqGemBibIn8Lg/132', '2', '', '', '0', '1569982223409', '1569982223409', '0', '', null), ('oxrwq0_8gVDZGt-kzeKKONOfxlVo', 'Don\'t forget me', 'http://thirdwx.qlogo.cn/mmopen/vi_32/cKGVTk2a7lPMzNJBcSkCbRjnpOIFVKp3ibeHVUibrKG6v33rcRrAQw5A9xCCkbdwsqcz6HfylbAHjFyXbibIJMb6Q/132', '1', '中国达州', '15281890501', '0', '1570343451389', '1570343580596', '0', '', null), ('oxrwq0_bbQM2xe7TZ986XTkqybwA', '君子坦蛋蛋', 'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJR4ibcVMzMz9rhGf124ibUV0vzxPoVs5dRuicuHjjgfMD1ia9XK1xlyjj1Dicy2DbbHZ7s3nr20wrgAaA/132', '1', '', '', '0', '1570062083141', '1570062083141', '0', '', null), ('oxrwq0_EIJzmoQ3Ptz8AwUVsWouE', '天空之城', 'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIegpS0o1C3v3mh4ic28AHPyfRuXwq9DtYHG3St4ptZkibRa49ceJYH7FibMg43NGM4ZsD0AbGwbibZGQ/132', '1', '中国安康', '15091532854', '0', '1570622264160', '1570622614281', '0', '', null), ('oxrwq0_GWdHw6ltakQ7PoZbeOyPg', 'Thirty', 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoSCrMLDBLHuW76XjrB2OaGb3tDHCxIZgHtCKIrU4pMsm9VWHUMiaZH5QsR3T9eqjlrrW7zqcV0fMQ/132', '1', '中国西安', '', '0', '1569978164311', '1569978164311', '0', '', null), ('oxrwq0_nhjp0mMPmrVMB_BcCeCsI', '杨毅', 'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIKHmCmvs59cb6U6SAiaRyRTAlA0o5ty32EVnMQkT2lsKQP9hxKic9lvjVa1r682jK8HoXrCEiciboqCg/132', '1', '中国淮北', '', '0', '1570543119489', '1570543119489', '0', '', null), ('oxrwq0_RSf6Mi_TWlVZi3QvdrwZs', '煦暖', 'http://thirdwx.qlogo.cn/mmopen/vi_32/E2ia9EUcQhM2WMhQV4y3caGHrk4to6HibCboiccAfejgMibk2g658GicbSQVKFw9vr7vqq5uSRLZ1CcnfDJeDdgVRTw/132', '1', '法国', '', '0', '1570061852399', '1570061852399', '0', '', null), ('oxrwq0_TOP5HshwpalRYqqQzJIMw', '空灵一曲为谁奏', 'http://thirdwx.qlogo.cn/mmopen/vi_32/h05yQwCUib8avSic4PLE5nYiaX2pAfmibWuIN8fczCJjJ0QCromkaHAIJlriaEibMJJibEYGZiayyBofYMflMHklkYgV3w/132', '0', '', '', '0', '1569945397066', '1569945397066', '0', '', null), ('oxrwq0_yXZRMeZoOT2bO26Lpd2tk', '南昔烈酒', 'http://thirdwx.qlogo.cn/mmopen/vi_32/FicLmg4rRV90jZ2Fxpj8MzEiaqaOJHII32Cg5mv8uh7VIYmGwibUC3rbbLXB3jTWf322Rg5LS7VSy4RRzFheNENOA/132', '1', '中国西安', '15934846402', '0', '1570072172844', '1570512218033', '0', '', null);
COMMIT;

-- ----------------------------
--  Table structure for `user_member_order`
-- ----------------------------
DROP TABLE IF EXISTS `user_member_order`;
CREATE TABLE `user_member_order` (
  `order_id` varchar(30) NOT NULL,
  `order_collection` varchar(30) NOT NULL,
  `member_type` tinyint(3) NOT NULL,
  `openid` varchar(50) NOT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `user_member_order`
-- ----------------------------
BEGIN;
INSERT INTO `user_member_order` VALUES ('1570797620315738150', '1570797620153682574', '1', 'oxrwq0zPbgTB-gV9Y4Q-hN4g25Fk'), ('1570797784532583173', '1570797784507317443', '3', 'oxrwq0zPbgTB-gV9Y4Q-hN4g25Fk');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
