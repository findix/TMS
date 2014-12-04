/*
Navicat MySQL Data Transfer

Source Server         : 172.25.71.93_3306
Source Server Version : 50615
Source Host           : 172.25.71.93:3306
Source Database       : tmsdb

Target Server Type    : MYSQL
Target Server Version : 50615
File Encoding         : 65001

Date: 2014-12-04 09:20:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `auth`
-- ----------------------------
DROP TABLE IF EXISTS `auth`;
CREATE TABLE `auth` (
  `type` varchar(1) NOT NULL,
  `aname` varchar(20) NOT NULL,
  PRIMARY KEY (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth
-- ----------------------------
INSERT INTO `auth` VALUES ('0', '学生');
INSERT INTO `auth` VALUES ('1', '校教务处管理员');
INSERT INTO `auth` VALUES ('2', '教学督导组');
INSERT INTO `auth` VALUES ('3', '院管理员');
INSERT INTO `auth` VALUES ('4', '教师');

-- ----------------------------
-- Table structure for `course`
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `cid` varchar(10) NOT NULL,
  `cname` varchar(20) NOT NULL,
  `cenglish` varchar(128) NOT NULL,
  `credit` float(24,0) NOT NULL,
  `mid` varchar(10) NOT NULL,
  `did` varchar(10) DEFAULT NULL,
  `status` varchar(10) DEFAULT '未分配',
  `tid` varchar(10) DEFAULT NULL,
  `tstatus` varchar(10) DEFAULT '未分配',
  `ttid` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`cid`),
  KEY `did` (`did`),
  KEY `tid` (`tid`),
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`tid`) REFERENCES `teacher` (`tid`),
  CONSTRAINT `did` FOREIGN KEY (`did`) REFERENCES `department` (`did`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('2500002', '计算机应用基础', 'Computer Application Foundation', '2', '001', '01', '已指派', '2000000004', '已通过', '2000000004');
INSERT INTO `course` VALUES ('2500082', '计算机科学导论', 'Introduction to Computer Science', '2', '001', '01', '未分配', null, '未分配', null);
INSERT INTO `course` VALUES ('2505003', '数据结构', 'Data Structures', '5', '001', '01', '未分配', null, '未分配', null);
INSERT INTO `course` VALUES ('2505005', '计算机组成原理', 'Principle of Computer Configuration', '4', '001', '01', '未分配', null, '未分配', null);
INSERT INTO `course` VALUES ('2505008', '操作系统原理', 'Principle of Operating Systems', '4', '001', '01', '不通过', '2000000001', '未分配', null);
INSERT INTO `course` VALUES ('2505010', '编译原理', 'Compiler Principles', '3', '001', '01', '已通过', '2000000001', '未分配', null);
INSERT INTO `course` VALUES ('2505013', '数据库原理', 'Database Principles', '4', '001', '01', '待审核', '2000000001', '未分配', null);
INSERT INTO `course` VALUES ('2505019', 'J2EE', 'Java 2 Platform,Enterprise Edition', '3', '001', '01', '待审核', '2000000004', '未分配', null);
INSERT INTO `course` VALUES ('2505020', '.Net技术', '.Net Technology', '3', '001', '01', '未分配', null, '未分配', null);
INSERT INTO `course` VALUES ('2505033', '计算机安全技术', 'Computer Security Technology', '2', '001', '01', '未分配', null, '未分配', null);
INSERT INTO `course` VALUES ('2505317', '高级程序设计（JAVA）（1）', 'Advanced Programming (JAVA) (1)', '3', '001', '01', '未分配', null, '未分配', null);
INSERT INTO `course` VALUES ('2505318', '高级程序设计（JAVA）（2）', 'Advanced Programming (JAVA) (2)', '3', '001', '01', '未分配', null, '未分配', null);
INSERT INTO `course` VALUES ('2505330', 'WEB2.0技术', 'WEB2.0 Technology', '2', '001', '01', '未分配', null, '未分配', null);
INSERT INTO `course` VALUES ('2505416', '高级程序设计(C++)(1)', 'Advanced  Programming(C++)（1）', '3', '001', '01', '未分配', null, '未分配', null);
INSERT INTO `course` VALUES ('2505417', '高级程序设计(C++)(2)', 'Advanced  Programming(C++)（2）', '2', '001', '01', '未分配', null, '未分配', null);
INSERT INTO `course` VALUES ('2522001', '软件工程概论', 'Introduction To Software Engineering', '3', '001', '01', '未分配', null, '未分配', null);
INSERT INTO `course` VALUES ('2522003', '面向对象分析与设计', 'Object Oriented Analysis And Design', '4', '001', '01', '未分配', null, '未分配', null);
INSERT INTO `course` VALUES ('2522005', '计算机网络原理及应用', 'Principle And Application Of Computer Network', '4', '001', '01', '未分配', null, '未分配', null);
INSERT INTO `course` VALUES ('2522006', '软件测试', 'Software Testing', '3', '001', '01', '未分配', null, '未分配', null);
INSERT INTO `course` VALUES ('2522008', 'J2ME', 'Java 2 Platform Micro Edition', '2', '001', '01', '未分配', null, '未分配', null);

-- ----------------------------
-- Table structure for `department`
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `did` varchar(10) NOT NULL,
  `dname` varchar(20) NOT NULL,
  PRIMARY KEY (`did`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('00', '上海电力学院');
INSERT INTO `department` VALUES ('01', '计算机科学与技术学院');
INSERT INTO `department` VALUES ('02', '电子与信息工程学院');
INSERT INTO `department` VALUES ('03', '自动化工程学院');
INSERT INTO `department` VALUES ('04', '外国语学院');
INSERT INTO `department` VALUES ('05', '电气工程学院');
INSERT INTO `department` VALUES ('06', '能源与机械工程学院');
INSERT INTO `department` VALUES ('07', '环境与化学工程学院');
INSERT INTO `department` VALUES ('08', '经济与管理学院');
INSERT INTO `department` VALUES ('09', '国际交流学院');
INSERT INTO `department` VALUES ('10', '数理学院');

-- ----------------------------
-- Table structure for `major`
-- ----------------------------
DROP TABLE IF EXISTS `major`;
CREATE TABLE `major` (
  `mid` varchar(10) NOT NULL,
  `mname` varchar(20) NOT NULL,
  `did` varchar(20) NOT NULL,
  PRIMARY KEY (`mid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of major
-- ----------------------------
INSERT INTO `major` VALUES ('001', '软件工程', '01');
INSERT INTO `major` VALUES ('002', '信息安全', '01');
INSERT INTO `major` VALUES ('003', '计算机科学与技术', '01');

-- ----------------------------
-- Table structure for `student`
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `sid` varchar(10) NOT NULL,
  `sname` varchar(20) NOT NULL,
  `password` varchar(16) DEFAULT '123456',
  `class` varchar(10) NOT NULL,
  `mid` varchar(10) NOT NULL,
  `type` int(11) NOT NULL,
  PRIMARY KEY (`sid`),
  KEY `mid` (`mid`),
  CONSTRAINT `mid` FOREIGN KEY (`mid`) REFERENCES `major` (`mid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('20113092', '凤翔', '123456', '2011221', '001', '0');
INSERT INTO `student` VALUES ('20113094', '冯茗轩', '123456', '2011221', '001', '0');
INSERT INTO `student` VALUES ('20113113', '陈龙龙', '123456', '2011221', '001', '0');
INSERT INTO `student` VALUES ('20113114', '陈伟皓', '123456', '2011221', '001', '0');
INSERT INTO `student` VALUES ('20113121', '林峰', '123456', '2011221', '001', '0');
INSERT INTO `student` VALUES ('20113123', '温海涛', '123456', '2011221', '001', '0');

-- ----------------------------
-- Table structure for `teacher`
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `tid` varchar(10) NOT NULL,
  `tname` varchar(20) NOT NULL,
  `password` varchar(16) DEFAULT '123456',
  `type` varchar(1) DEFAULT NULL,
  `did` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('2000000001', '李四', '123456', '3', '00');
INSERT INTO `teacher` VALUES ('2000000002', '张三', '123456', '2', '00');
INSERT INTO `teacher` VALUES ('2000000003', '王能', '123456', '3', '01');
INSERT INTO `teacher` VALUES ('2000000004', '周平', '123456', '4', '01');
INSERT INTO `teacher` VALUES ('2000000005', '周林', '123456', '4', '01');
INSERT INTO `teacher` VALUES ('2000000006', '张超', '123456', '4', '01');
INSERT INTO `teacher` VALUES ('2000000007', '袁仲雄', '123456', '4', '01');
INSERT INTO `teacher` VALUES ('2000000008', '张挺', '123456', '4', '01');
INSERT INTO `teacher` VALUES ('2000000009', '张安勤', '123456', '4', '01');
INSERT INTO `teacher` VALUES ('2000000010', '李红娇', '123456', '4', '01');
INSERT INTO `teacher` VALUES ('2000000011', '毕忠勤', '123456', '4', '01');
INSERT INTO `teacher` VALUES ('2000000012', '刘小洛', '123456', '4', '01');
INSERT INTO `teacher` VALUES ('2000000013', '刘大明', '123456', '4', '01');
INSERT INTO `teacher` VALUES ('2000000014', '吴涵', '123456', '4', '01');
INSERT INTO `teacher` VALUES ('2000000015', '赵铮', '123456', '4', '01');
INSERT INTO `teacher` VALUES ('2000000016', '李壮', '123456', '4', '01');
INSERT INTO `teacher` VALUES ('2000000017', '刘佳', '123456', '4', '01');
INSERT INTO `teacher` VALUES ('2000000018', '朱佳', '123456', '4', '01');
INSERT INTO `teacher` VALUES ('2000000019', '陈雯', '123456', '4', '01');
INSERT INTO `teacher` VALUES ('2000000020', '杨珺', '123456', '4', '01');
INSERT INTO `teacher` VALUES ('2000000021', '李海明', '123456', '4', '01');
INSERT INTO `teacher` VALUES ('2000000022', '薛梅', '123456', '4', '01');
INSERT INTO `teacher` VALUES ('2000000023', '田秀霞', '123456', '4', '01');
INSERT INTO `teacher` VALUES ('2000000024', '杜海舟', '123456', '4', '01');
