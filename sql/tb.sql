/*
 Navicat Premium Data Transfer

 Source Server         : 221
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : 192.168.1.221:3306
 Source Schema         : db3

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 07/12/2018 21:43:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb
-- ----------------------------
DROP TABLE IF EXISTS `tb`;
CREATE TABLE `tb` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `month` varchar(255) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb
-- ----------------------------
BEGIN;
INSERT INTO `tb` VALUES (1, '201603', 2);
INSERT INTO `tb` VALUES (2, '201604', 1);
INSERT INTO `tb` VALUES (3, '201604', 5);
INSERT INTO `tb` VALUES (4, '201605', 2);
INSERT INTO `tb` VALUES (5, '201603', 2);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
