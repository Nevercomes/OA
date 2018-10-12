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

 Date: 12/10/2018 19:44:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `admin_id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `numbering` varchar(225) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `department` int(2) NULL DEFAULT NULL COMMENT 'department 同时象征了权限等级 0代表全部 特定数字对应特定部门',
  `position` int(2) NULL DEFAULT NULL,
  PRIMARY KEY (`admin_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for admin_pwd
-- ----------------------------
DROP TABLE IF EXISTS `admin_pwd`;
CREATE TABLE `admin_pwd`  (
  `admin_id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `salt` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`admin_id`) USING BTREE,
  CONSTRAINT `admin_pwd_f1` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`admin_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for assess_result
-- ----------------------------
DROP TABLE IF EXISTS `assess_result`;
CREATE TABLE `assess_result`  (
  `result_id` int(11) NOT NULL,
  `department` int(2) NULL DEFAULT NULL,
  `month` int(2) NULL DEFAULT NULL,
  `message` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `modify_time` datetime(0) NULL DEFAULT NULL,
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
  `work_regular` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `work_outPlan` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `work_other` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `work_expanse` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `work_planSimple` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `assess_head_eva` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `assess_head_score` float(3, 1) NOT NULL DEFAULT 0.0,
  `assess_director_eva` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `assess_director_score` float(3, 1) NOT NULL DEFAULT 0.0,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `month` int(2) NOT NULL DEFAULT 0,
  `work_modify_time` datetime(0) NULL DEFAULT NULL,
  `assess_modify_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`assess_id`) USING BTREE,
  INDEX `assessment_f1`(`user_id`) USING BTREE,
  CONSTRAINT `assessment_f1` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

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
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `user_id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `department` int(2) NULL DEFAULT NULL,
  `position` int(2) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

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
-- Table structure for work_plan
-- ----------------------------
DROP TABLE IF EXISTS `work_plan`;
CREATE TABLE `work_plan`  (
  `plan_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `month` int(2) NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `modify_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`plan_id`) USING BTREE,
  INDEX `work_plan_f1`(`user_id`) USING BTREE,
  CONSTRAINT `work_plan_f1` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
