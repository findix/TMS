/*
Navicat MySQL Data Transfer

Source Server         : db4free.net
Source Server Version : 50621
Source Host           : db4free.net:3306
Source Database       : tmsdb

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2014-12-02 00:47:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for Course
-- ----------------------------
DROP TABLE IF EXISTS `Course`;
CREATE TABLE `Course` (
  `cid` varchar(10) NOT NULL,
  `cname` varchar(20) NOT NULL,
  `cenghlish` varchar(128) NOT NULL,
  `credit` float(24,0) NOT NULL,
  `mid` varchar(10) NOT NULL,
  `did` varchar(10) NOT NULL,
  PRIMARY KEY (`cid`),
  KEY `did` (`did`),
  CONSTRAINT `did` FOREIGN KEY (`did`) REFERENCES `Department` (`did`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of Course
-- ----------------------------
INSERT INTO `Course` VALUES ('2500082', '计算机科学导论', 'Introduction to Computer Science', '2', '001', '01');
INSERT INTO `Course` VALUES ('2505003', '数据结构', 'Data Structures ', '5', '001', '01');
INSERT INTO `Course` VALUES ('2505005', '计算机组成原理', 'Principle of Computer Configuration', '4', '001', '01');
INSERT INTO `Course` VALUES ('2505008', '操作系统原理', 'Principle of Operating Systems', '4', '001', '01');
INSERT INTO `Course` VALUES ('2505010', '编译原理', 'Compiler Principles', '3', '001', '01');
INSERT INTO `Course` VALUES ('2505013', '数据库原理', 'Database Principles', '4', '001', '01');
INSERT INTO `Course` VALUES ('2505416', '高级程序设计(C++)(1)', 'Advanced  Programming(C++)（1）', '3', '001', '01');
INSERT INTO `Course` VALUES ('2505417', '高级程序设计(C++)(2) ', 'Advanced  Programming(C++)（2）', '2', '001', '01');

-- ----------------------------
-- Table structure for Department
-- ----------------------------
DROP TABLE IF EXISTS `Department`;
CREATE TABLE `Department` (
  `did` varchar(10) NOT NULL,
  `dname` varchar(20) NOT NULL,
  PRIMARY KEY (`did`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of Department
-- ----------------------------
INSERT INTO `Department` VALUES ('01', '计算机科学与技术学院');
INSERT INTO `Department` VALUES ('02', '电子与信息工程学院');
INSERT INTO `Department` VALUES ('03', '自动化工程学院');
INSERT INTO `Department` VALUES ('04', '外国语学院');
INSERT INTO `Department` VALUES ('05', '电气工程学院');
INSERT INTO `Department` VALUES ('06', '能源与机械工程学院');
INSERT INTO `Department` VALUES ('07', '环境与化学工程学院');
INSERT INTO `Department` VALUES ('08', '经济与管理学院');
INSERT INTO `Department` VALUES ('09', '国际交流学院');
INSERT INTO `Department` VALUES ('20', '数理学院');

-- ----------------------------
-- Table structure for Major
-- ----------------------------
DROP TABLE IF EXISTS `Major`;
CREATE TABLE `Major` (
  `mid` varchar(10) NOT NULL,
  `mname` varchar(20) NOT NULL,
  `did` varchar(20) NOT NULL,
  PRIMARY KEY (`mid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of Major
-- ----------------------------
INSERT INTO `Major` VALUES ('001', '软件工程', '01');
INSERT INTO `Major` VALUES ('002', '信息安全', '01');
INSERT INTO `Major` VALUES ('003', '计算机科学与技术', '01');

-- ----------------------------
-- Table structure for Student
-- ----------------------------
DROP TABLE IF EXISTS `Student`;
CREATE TABLE `Student` (
  `sid` varchar(10) NOT NULL,
  `sname` varchar(20) NOT NULL,
  `password` varchar(16) NOT NULL,
  `class` varchar(10) NOT NULL,
  `mid` varchar(10) NOT NULL,
  `type` int(11) NOT NULL,
  PRIMARY KEY (`sid`),
  KEY `mid` (`mid`),
  CONSTRAINT `mid` FOREIGN KEY (`mid`) REFERENCES `Major` (`mid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of Student
-- ----------------------------
INSERT INTO `Student` VALUES ('20113092', '凤翔', '123456', '2011221', '001', '0');
INSERT INTO `Student` VALUES ('20113094', '冯茗轩', '123456', '2011221', '001', '0');
INSERT INTO `Student` VALUES ('20113113', '陈龙龙', '123456', '2011221', '001', '0');
INSERT INTO `Student` VALUES ('20113114', '陈伟皓', '123456', '2011221', '001', '0');
INSERT INTO `Student` VALUES ('20113121', '林峰', '123456', '2011221', '001', '0');
INSERT INTO `Student` VALUES ('20113123', '温海涛', '123456', '2011221', '001', '0');

-- ----------------------------
-- Table structure for Syllabus
-- ----------------------------
DROP TABLE IF EXISTS `Syllabus`;
CREATE TABLE `Syllabus` (
  `syid` varchar(10) NOT NULL,
  `syname` varchar(32) NOT NULL,
  `tid` varchar(10) NOT NULL,
  PRIMARY KEY (`syid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of Syllabus
-- ----------------------------

-- ----------------------------
-- Table structure for Teacher
-- ----------------------------
DROP TABLE IF EXISTS `Teacher`;
CREATE TABLE `Teacher` (
  `tid` varchar(10) NOT NULL,
  `tname` varchar(20) NOT NULL,
  `password` varchar(16) NOT NULL,
  `type` int(11) NOT NULL,
  `did` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of Teacher
-- ----------------------------
INSERT INTO `Teacher` VALUES ('20002001', '李四', '123456', '1', '');
INSERT INTO `Teacher` VALUES ('20002002', '张三', '123456', '2', '');
INSERT INTO `Teacher` VALUES ('20002003', '王能', '123456', '3', '01');
INSERT INTO `Teacher` VALUES ('20002004', '周平', '123456', '4', '01');

-- ----------------------------
-- Table structure for TeachingSchedule
-- ----------------------------
DROP TABLE IF EXISTS `TeachingSchedule`;
CREATE TABLE `TeachingSchedule` (
  `tsid` varchar(10) NOT NULL,
  `tsname` varchar(32) NOT NULL,
  `tid` varchar(10) NOT NULL,
  PRIMARY KEY (`tsid`),
  KEY `tid` (`tid`),
  CONSTRAINT `tid` FOREIGN KEY (`tid`) REFERENCES `Teacher` (`tid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of TeachingSchedule
-- ----------------------------
