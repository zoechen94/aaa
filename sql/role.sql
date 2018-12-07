/*
 Navicat Premium Data Transfer

 Source Server         : 87
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : 192.168.1.87:3306
 Source Schema         : db2

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 07/12/2018 21:44:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` VALUES (1, '1ge ');
INSERT INTO `role` VALUES (2, '2ge');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
