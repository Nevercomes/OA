/*
 Navicat Premium Data Transfer

 Source Server         : Terminal
 Source Server Type    : MySQL
 Source Server Version : 100135
 Source Host           : localhost:3306
 Source Schema         : yunlg_oa

 Target Server Type    : MySQL
 Target Server Version : 100135
 File Encoding         : 65001

 Date: 03/11/2018 00:17:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for assess_record
-- ----------------------------
DROP TABLE IF EXISTS `assess_record`;
CREATE TABLE `assess_record`  (
  `assess_record_id` int(11) NOT NULL AUTO_INCREMENT,
  `year` int(4) NOT NULL DEFAULT 2018,
  `month` int(2) NOT NULL DEFAULT 0,
  `start_time` date NULL DEFAULT NULL,
  `end_time` date NULL DEFAULT NULL,
  `modify_time` date NULL DEFAULT NULL,
  PRIMARY KEY (`assess_record_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of assess_record
-- ----------------------------
INSERT INTO `assess_record` VALUES (1, 2018, 10, '2018-10-24', '2018-10-31', '2018-10-25');

-- ----------------------------
-- Table structure for assess_result
-- ----------------------------
DROP TABLE IF EXISTS `assess_result`;
CREATE TABLE `assess_result`  (
  `result_id` int(11) NOT NULL,
  `department` int(2) NULL DEFAULT NULL,
  `month` int(2) NULL DEFAULT NULL,
  `modify_time` date NULL DEFAULT NULL,
  PRIMARY KEY (`result_id`) USING BTREE,
  INDEX `assess_result_index1`(`department`, `month`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for assessment
-- ----------------------------
DROP TABLE IF EXISTS `assessment`;
CREATE TABLE `assessment`  (
  `assess_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `month` int(2) NOT NULL DEFAULT 0,
  `work_regular` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `work_outPlan` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `work_other` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `work_expanse` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `work_planSimple` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `assess_head_eva` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `assess_head_score` int(4) NOT NULL DEFAULT -1,
  `assess_director_eva` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `assess_director_score` int(4) NOT NULL DEFAULT -1,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `work_modify_time` date NULL DEFAULT NULL,
  `assess_modify_time` date NULL DEFAULT NULL,
  `submit` int(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`assess_id`) USING BTREE,
  INDEX `assessment_index2`(`user_id`, `month`) USING BTREE,
  CONSTRAINT `assessment_f1` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of assessment
-- ----------------------------
INSERT INTO `assessment` VALUES (2, '3901170506', 9, '1. 今天值了一天的班 2. 今天又值了一个班', '今天给cz倒了茶', '敲了10w行代码', '1000', '没有计划', 'nice', 60, 'good', 50, 'cznb！！！', '2018-10-21', '2018-10-13', 0);
INSERT INTO `assessment` VALUES (3, '3901170507', 9, '今天值了一天的班', '今天给cz倒了茶', '敲了10w行代码', '1000', '没有计划', 'nice', 60, NULL, 0, NULL, '2018-10-13', '2018-10-25', 0);
INSERT INTO `assessment` VALUES (5, '100', 10, '又是新的一个月啦啦啦！', '', '', '', '', NULL, -1, NULL, -1, '', '2018-10-25', NULL, 1);

-- ----------------------------
-- Table structure for auth
-- ----------------------------
DROP TABLE IF EXISTS `auth`;
CREATE TABLE `auth`  (
  `auth_id` int(11) NOT NULL AUTO_INCREMENT,
  `auth_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `department` int(2) NOT NULL DEFAULT 1,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`auth_id`) USING BTREE,
  INDEX `auth_code`(`auth_code`(191)) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of auth
-- ----------------------------
INSERT INTO `auth` VALUES (1, 'AA1000', 1, 'AA_Batch_Register');
INSERT INTO `auth` VALUES (2, 'AA1000', 2, 'AA_Batch_Register');
INSERT INTO `auth` VALUES (3, 'AA1000', 3, 'AA_Batch_Register');
INSERT INTO `auth` VALUES (4, 'AA1000', 0, 'AA_Batch_Register');
INSERT INTO `auth` VALUES (5, 'AA1001', 1, 'AA_View_Result');
INSERT INTO `auth` VALUES (6, 'AA1001', 2, 'AA_View_Result');
INSERT INTO `auth` VALUES (7, 'AA1001', 3, 'AA_View_Result');
INSERT INTO `auth` VALUES (8, 'AA1001', 0, 'AA_View_Result');
INSERT INTO `auth` VALUES (9, 'AA1002', 1, 'AA_Public_Result');
INSERT INTO `auth` VALUES (10, 'AA1002', 2, 'AA_Public_Result');
INSERT INTO `auth` VALUES (11, 'AA1002', 3, 'AA_Public_Result');
INSERT INTO `auth` VALUES (12, 'AA1002', 0, 'AA_Public_Result');
INSERT INTO `auth` VALUES (13, 'AA1003', 1, 'AA_Eva_Assessment');
INSERT INTO `auth` VALUES (14, 'AA1003', 2, 'AA_Eva_Assessment');
INSERT INTO `auth` VALUES (15, 'AA1003', 3, 'AA_Eva_Assessment');
INSERT INTO `auth` VALUES (16, 'AA1003', 0, 'AA_Eva_Assessment');
INSERT INTO `auth` VALUES (17, 'AA1004', 1, 'AA_Submit_Eva');
INSERT INTO `auth` VALUES (18, 'AA1004', 2, 'AA_Submit_Eva');
INSERT INTO `auth` VALUES (19, 'AA1004', 3, 'AA_Submit_Eva');
INSERT INTO `auth` VALUES (20, 'AA1004', 0, 'AA_Submit_Eva');
INSERT INTO `auth` VALUES (21, 'AA1005', 0, 'AA_View_Plan');
INSERT INTO `auth` VALUES (24, 'AA1006', 1, 'AA_Reset');
INSERT INTO `auth` VALUES (25, 'AA1006', 2, 'AA_Reset');
INSERT INTO `auth` VALUES (26, 'AA1006', 3, 'AA_Reset');
INSERT INTO `auth` VALUES (27, 'AA1006', 0, 'AA_Reset');
INSERT INTO `auth` VALUES (28, 'AA2000', 0, 'AA_Admin_Register');
INSERT INTO `auth` VALUES (29, 'AA2001', 0, 'AA_Set_Time');
INSERT INTO `auth` VALUES (30, 'AA2002', 0, 'AA_Export_Assess_Info');
INSERT INTO `auth` VALUES (31, 'AA2003', 0, 'AA_Export_Resut');

-- ----------------------------
-- Table structure for file_mission
-- ----------------------------
DROP TABLE IF EXISTS `file_mission`;
CREATE TABLE `file_mission`  (
  `file_mission_id` int(11) NOT NULL AUTO_INCREMENT,
  `assess_id` int(11) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`file_mission_id`) USING BTREE,
  INDEX `file_missinon_f1`(`assess_id`) USING BTREE,
  CONSTRAINT `file_missinon_f1` FOREIGN KEY (`assess_id`) REFERENCES `assessment` (`assess_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for file_other
-- ----------------------------
DROP TABLE IF EXISTS `file_other`;
CREATE TABLE `file_other`  (
  `file_other_id` int(11) NOT NULL,
  `assess_id` int(11) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`file_other_id`) USING BTREE,
  CONSTRAINT `file_other_f1` FOREIGN KEY (`file_other_id`) REFERENCES `assessment` (`assess_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1000 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'center_instructor');
INSERT INTO `role` VALUES (2, 'center_director');
INSERT INTO `role` VALUES (3, 'dep_rd_head');
INSERT INTO `role` VALUES (4, 'dep_networks_head');
INSERT INTO `role` VALUES (5, 'dep_art_head');
INSERT INTO `role` VALUES (999, 'super');

-- ----------------------------
-- Table structure for role_auth_mapping
-- ----------------------------
DROP TABLE IF EXISTS `role_auth_mapping`;
CREATE TABLE `role_auth_mapping`  (
  `role_auth_id` int(11) NOT NULL AUTO_INCREMENT,
  `auth_code` varchar(225) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `role_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`role_auth_id`) USING BTREE,
  INDEX `role_auth_mapping_f1`(`auth_code`(191)) USING BTREE,
  INDEX `role_auth_mapping_f3`(`role_id`) USING BTREE,
  CONSTRAINT `role_auth_mapping_f3` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 76 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role_auth_mapping
-- ----------------------------
INSERT INTO `role_auth_mapping` VALUES (1, 'AA1000', 1);
INSERT INTO `role_auth_mapping` VALUES (2, 'AA1001', 1);
INSERT INTO `role_auth_mapping` VALUES (3, 'AA1002', 1);
INSERT INTO `role_auth_mapping` VALUES (4, 'AA1003', 1);
INSERT INTO `role_auth_mapping` VALUES (5, 'AA1004', 1);
INSERT INTO `role_auth_mapping` VALUES (6, 'AA1005', 1);
INSERT INTO `role_auth_mapping` VALUES (22, 'AA1000', 2);
INSERT INTO `role_auth_mapping` VALUES (23, 'AA1001', 2);
INSERT INTO `role_auth_mapping` VALUES (24, 'AA1002', 2);
INSERT INTO `role_auth_mapping` VALUES (25, 'AA1003', 2);
INSERT INTO `role_auth_mapping` VALUES (26, 'AA1004', 2);
INSERT INTO `role_auth_mapping` VALUES (27, 'AA1005', 2);
INSERT INTO `role_auth_mapping` VALUES (40, 'AA1000', 3);
INSERT INTO `role_auth_mapping` VALUES (41, 'AA1001', 3);
INSERT INTO `role_auth_mapping` VALUES (42, 'AA1002', 3);
INSERT INTO `role_auth_mapping` VALUES (43, 'AA1003', 3);
INSERT INTO `role_auth_mapping` VALUES (44, 'AA1004', 3);
INSERT INTO `role_auth_mapping` VALUES (45, 'AA1005', 3);
INSERT INTO `role_auth_mapping` VALUES (46, 'AA1000', 4);
INSERT INTO `role_auth_mapping` VALUES (47, 'AA1001', 4);
INSERT INTO `role_auth_mapping` VALUES (48, 'AA1002', 4);
INSERT INTO `role_auth_mapping` VALUES (49, 'AA1003', 4);
INSERT INTO `role_auth_mapping` VALUES (50, 'AA1004', 4);
INSERT INTO `role_auth_mapping` VALUES (54, 'AA1005', 4);
INSERT INTO `role_auth_mapping` VALUES (55, 'AA1000', 5);
INSERT INTO `role_auth_mapping` VALUES (56, 'AA1001', 5);
INSERT INTO `role_auth_mapping` VALUES (57, 'AA1002', 5);
INSERT INTO `role_auth_mapping` VALUES (58, 'AA1003', 5);
INSERT INTO `role_auth_mapping` VALUES (59, 'AA1004', 5);
INSERT INTO `role_auth_mapping` VALUES (60, 'AA1005', 5);
INSERT INTO `role_auth_mapping` VALUES (61, 'AA1006', 1);
INSERT INTO `role_auth_mapping` VALUES (62, 'AA1006', 2);
INSERT INTO `role_auth_mapping` VALUES (63, 'AA1006', 3);
INSERT INTO `role_auth_mapping` VALUES (64, 'AA1006', 4);
INSERT INTO `role_auth_mapping` VALUES (65, 'AA1006', 5);
INSERT INTO `role_auth_mapping` VALUES (66, 'AA2000', 1);
INSERT INTO `role_auth_mapping` VALUES (67, 'AA2000', 2);
INSERT INTO `role_auth_mapping` VALUES (68, 'AA1000', 999);
INSERT INTO `role_auth_mapping` VALUES (69, 'AA1001', 999);
INSERT INTO `role_auth_mapping` VALUES (70, 'AA1002', 999);
INSERT INTO `role_auth_mapping` VALUES (71, 'AA1003', 999);
INSERT INTO `role_auth_mapping` VALUES (72, 'AA1004', 999);
INSERT INTO `role_auth_mapping` VALUES (73, 'AA1005', 999);
INSERT INTO `role_auth_mapping` VALUES (74, 'AA1006', 999);
INSERT INTO `role_auth_mapping` VALUES (75, 'AA2000', 999);

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `user_id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `department` int(2) NULL DEFAULT NULL,
  `position` int(2) NULL DEFAULT NULL,
  `admin` int(2) NOT NULL DEFAULT 0,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('100', 'sun', 0, 0, 999);
INSERT INTO `user_info` VALUES ('1234', 'Ted', 1, 3, 0);
INSERT INTO `user_info` VALUES ('123456', 'Jack', 1, 0, 0);
INSERT INTO `user_info` VALUES ('1234560', 'Lucy', 2, 0, 0);
INSERT INTO `user_info` VALUES ('12345600', 'Jone', 3, 0, 0);
INSERT INTO `user_info` VALUES ('3901170506', 'Mike', 1, 0, 0);
INSERT INTO `user_info` VALUES ('3901170507', 'Lily', 2, 3, 0);

-- ----------------------------
-- Table structure for user_pwd
-- ----------------------------
DROP TABLE IF EXISTS `user_pwd`;
CREATE TABLE `user_pwd`  (
  `user_id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `salt` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE,
  CONSTRAINT `user_pwd_f1` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_pwd
-- ----------------------------
INSERT INTO `user_pwd` VALUES ('100', 'AAP4I8pEN6WoV5abDhso0TnM7P1ojH7tKaimRKmnYwjTmTH2KGzzNsR5gEDPPltacwhfH0kUNRi1EoxMC2Oe0nc=', 'prsrzu2hStyIUWyZhELbRobLMMjEmmk2SLpf9Kn1G2E=');
INSERT INTO `user_pwd` VALUES ('1234', 'ADWI9h8B94d87zdjDxS0h1H5Zxcq18m0NbQDeW+wp2GgdiVbRqIWK12E8v4q3WC5z9KPtox8mqVRqqwmR1N+N5w=', '0jGFIfr8MHVwgZSmL1G9yyKukVApooNEPUWPFvcQqAQ=');
INSERT INTO `user_pwd` VALUES ('123456', 'AFN4+aeQg+ajIP1LUHRVXq4FE4qE+CB4muxh7s0z3qXeTWaigSG/sVqfOzPZHh1bygPcagm3KqrKAlWIRRBBTtU=', 'QKM/KFNSB67W52mnSkGPjwPR0oaGZTl01xs9VCShoF4=');
INSERT INTO `user_pwd` VALUES ('1234560', 'AHdItqmY1o9RxjuYlv9V0YTdD+1NngNwIyiga8AUX226ju2zxZibq2VVdE6oMUNWN41Wzgxze0/ZSz6Je708qZ0=', 'PJLsJjOx1/bYRZ0jdPqt7yqCtKt47CmMOedLpw9qri0=');
INSERT INTO `user_pwd` VALUES ('12345600', 'AOehcEXvOH+g8j7qqm7O2M/s+LRnv7Lu9Kgeg6VgIskpR1W5MDW5K1V/7a9e3N5tEEtCG/sW+ow6BD1Sni8oLD0=', 'kgxEVkYZQn/fBZrgJmnC+oWzhXr3bsAHHUaoiyW+E40=');
INSERT INTO `user_pwd` VALUES ('3901170506', 'AKV664LFP5d6a/oy8ZbZo0kk3je6FI8GqmAELKf5oOxnVtINZ4H+b8s/XEDs2S/NFyAyv4CZwt6GYk7mo1NnxN0=', '5bWyjBOBEZvxV+3jW7gJsOZHDbJ5TrTrpFQExTau9aU=');
INSERT INTO `user_pwd` VALUES ('3901170507', 'AAI8QRv07D3g0ScJ4COsG8DzG3Hp779+pgTFx0qnrRE6twOx1wZ/kxsb+RZaOZhmY1/0/zJcQAKigOaB1w/BYpA=', 'TnwQeOuC14nMJRI7Y8VHYRplEHjpGsGoaoh+k2b+Mms=');

-- ----------------------------
-- Table structure for user_role_mapping
-- ----------------------------
DROP TABLE IF EXISTS `user_role_mapping`;
CREATE TABLE `user_role_mapping`  (
  `user_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NULL DEFAULT NULL,
  `user_id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_role_id`) USING BTREE,
  INDEX `user_role_mapping_f1`(`role_id`) USING BTREE,
  INDEX `user_role_mapping_f2`(`user_id`) USING BTREE,
  CONSTRAINT `user_role_mapping_f1` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_role_mapping_f2` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_role_mapping
-- ----------------------------
INSERT INTO `user_role_mapping` VALUES (1, 999, '100');

-- ----------------------------
-- Table structure for work_plan
-- ----------------------------
DROP TABLE IF EXISTS `work_plan`;
CREATE TABLE `work_plan`  (
  `plan_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `month` int(2) NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `modify_time` date NULL DEFAULT NULL,
  PRIMARY KEY (`plan_id`) USING BTREE,
  INDEX `work_plan_f1`(`user_id`) USING BTREE,
  CONSTRAINT `work_plan_f1` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of work_plan
-- ----------------------------
INSERT INTO `work_plan` VALUES (1, '3901170506', 9, '下个月要愉快了培训了，滑稽，嘿嘿', NULL);
INSERT INTO `work_plan` VALUES (4, '100', 9, '下个月要愉快地培训了，滑稽，嘿嘿', NULL);

SET FOREIGN_KEY_CHECKS = 1;
