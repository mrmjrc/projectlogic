/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : projectlogic

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 11/12/2019 15:31:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `c_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `c_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `t_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`c_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('01', '语文', '02');
INSERT INTO `course` VALUES ('02', '数学', '01');
INSERT INTO `course` VALUES ('03', '英语', '03');

-- ----------------------------
-- Table structure for dic_continent
-- ----------------------------
DROP TABLE IF EXISTS `dic_continent`;
CREATE TABLE `dic_continent`  (
  `continent_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `continent_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '洲名称',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `is_del` tinyint(2) NOT NULL DEFAULT 1 COMMENT '是否删除（1否2是）',
  PRIMARY KEY (`continent_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '洲名称表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dic_continent
-- ----------------------------
INSERT INTO `dic_continent` VALUES (1, '亚洲', '2019-10-29 15:38:46', '2019-12-11 14:36:26', 0);
INSERT INTO `dic_continent` VALUES (2, '欧洲', '2019-10-29 15:38:55', '2019-12-11 14:36:28', 0);
INSERT INTO `dic_continent` VALUES (3, '非洲', '2019-10-29 15:39:01', '2019-12-11 14:36:30', 0);
INSERT INTO `dic_continent` VALUES (4, '北美洲', '2019-10-29 15:39:04', '2019-12-11 14:36:31', 0);
INSERT INTO `dic_continent` VALUES (5, '南美洲', '2019-10-29 15:39:07', '2019-12-11 14:36:32', 0);
INSERT INTO `dic_continent` VALUES (6, '大洋洲', '2019-10-29 15:39:13', '2019-12-11 14:36:33', 0);
INSERT INTO `dic_continent` VALUES (7, '南极洲', '2019-10-29 15:39:20', '2019-12-11 14:36:34', 0);
INSERT INTO `dic_continent` VALUES (8, '太平洋', '2019-10-29 15:39:33', '2019-12-11 14:36:35', 0);
INSERT INTO `dic_continent` VALUES (9, '印度洋', '2019-12-03 18:08:56', '2019-12-11 14:36:36', 0);
INSERT INTO `dic_continent` VALUES (10, '北冰洋', '2019-12-03 18:09:01', '2019-12-11 14:36:38', 0);
INSERT INTO `dic_continent` VALUES (11, '大西洋', '2019-12-03 18:09:03', '2019-12-11 14:36:40', 0);

-- ----------------------------
-- Table structure for dic_country
-- ----------------------------
DROP TABLE IF EXISTS `dic_country`;
CREATE TABLE `dic_country`  (
  `country_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `country_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '国家名称',
  `continent_id` int(20) NOT NULL COMMENT '洲id',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `is_del` tinyint(2) NOT NULL DEFAULT 1 COMMENT '是否删除（1否2是）',
  PRIMARY KEY (`country_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '国家表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dic_country
-- ----------------------------
INSERT INTO `dic_country` VALUES (19, '中国', 1, '2019-11-27 08:48:27', '2019-11-27 08:48:27', 1);
INSERT INTO `dic_country` VALUES (20, '韩国', 1, '2019-11-27 08:48:40', '2019-11-27 08:48:40', 1);
INSERT INTO `dic_country` VALUES (21, '日本', 1, '2019-11-27 08:48:48', '2019-11-27 11:14:21', 1);
INSERT INTO `dic_country` VALUES (22, '阿尔及利亚', 2, '2019-11-27 08:49:04', '2019-11-27 09:01:05', 1);
INSERT INTO `dic_country` VALUES (23, '埃及', 2, '2019-11-27 08:49:49', '2019-11-27 08:49:49', 1);
INSERT INTO `dic_country` VALUES (24, '利比亚', 2, '2019-11-27 08:50:13', '2019-11-27 08:50:13', 1);
INSERT INTO `dic_country` VALUES (25, '哥伦比亚', 3, '2019-11-27 08:50:42', '2019-11-27 08:50:42', 1);
INSERT INTO `dic_country` VALUES (26, '巴西', 3, '2019-11-27 08:50:51', '2019-11-27 08:50:51', 1);
INSERT INTO `dic_country` VALUES (27, '阿根廷', 3, '2019-11-27 08:51:04', '2019-11-27 08:51:04', 1);
INSERT INTO `dic_country` VALUES (28, '加拿大', 4, '2019-11-27 08:51:39', '2019-11-27 08:51:39', 1);
INSERT INTO `dic_country` VALUES (29, '美国', 4, '2019-11-27 08:51:51', '2019-11-27 08:51:51', 1);
INSERT INTO `dic_country` VALUES (30, '格陵兰', 4, '2019-11-27 08:52:10', '2019-11-27 08:52:10', 1);
INSERT INTO `dic_country` VALUES (31, '瑞典', 5, '2019-11-27 08:52:37', '2019-11-27 08:52:37', 1);
INSERT INTO `dic_country` VALUES (32, '挪威', 5, '2019-11-27 08:52:50', '2019-11-27 08:52:50', 1);
INSERT INTO `dic_country` VALUES (33, '俄罗斯', 5, '2019-11-27 08:53:06', '2019-11-27 08:53:06', 1);
INSERT INTO `dic_country` VALUES (34, '澳大利亚', 6, '2019-11-27 08:58:22', '2019-11-27 08:58:22', 1);
INSERT INTO `dic_country` VALUES (35, '新西兰', 6, '2019-11-27 08:58:31', '2019-11-27 08:58:31', 1);
INSERT INTO `dic_country` VALUES (36, '图瓦卢', 6, '2019-11-27 08:58:50', '2019-11-27 08:58:50', 1);
INSERT INTO `dic_country` VALUES (37, '澳大利亚', 2, '2019-11-27 09:01:49', '2019-11-28 14:02:12', 1);
INSERT INTO `dic_country` VALUES (38, '印度', 1, '2019-11-27 11:14:39', '2019-11-27 11:14:39', 1);
INSERT INTO `dic_country` VALUES (39, 'test', 2, '2019-11-27 16:16:26', '2019-11-28 14:06:22', 2);
INSERT INTO `dic_country` VALUES (40, 'test', 2, '2019-11-27 16:19:40', '2019-11-27 16:27:59', 2);
INSERT INTO `dic_country` VALUES (41, '朝鲜', 1, '2019-11-28 14:01:12', '2019-11-28 14:01:12', 1);
INSERT INTO `dic_country` VALUES (42, 'test1', 1, '2019-11-28 14:33:23', '2019-11-29 10:52:45', 2);
INSERT INTO `dic_country` VALUES (43, 'test1', 1, '2019-12-02 11:26:19', '2019-12-02 11:26:32', 2);
INSERT INTO `dic_country` VALUES (44, 'test', 1, '2019-12-02 11:26:40', '2019-12-04 18:18:34', 2);
INSERT INTO `dic_country` VALUES (45, '太平洋', 8, '2019-12-03 18:10:23', '2019-12-03 18:10:23', 1);
INSERT INTO `dic_country` VALUES (46, '印度洋', 9, '2019-12-03 18:10:34', '2019-12-03 18:10:34', 1);
INSERT INTO `dic_country` VALUES (47, '北冰洋', 10, '2019-12-03 18:10:47', '2019-12-03 18:10:47', 1);
INSERT INTO `dic_country` VALUES (48, '大西洋', 11, '2019-12-03 18:10:53', '2019-12-03 18:10:53', 1);
INSERT INTO `dic_country` VALUES (49, 'test01', 1, '2019-12-04 14:41:47', '2019-12-04 14:41:47', 1);
INSERT INTO `dic_country` VALUES (50, '测试国家', 2, '2019-12-04 15:01:35', '2019-12-04 15:20:07', 2);

-- ----------------------------
-- Table structure for score
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score`  (
  `s_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `c_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `s_score` int(3) NULL DEFAULT NULL,
  PRIMARY KEY (`s_id`, `c_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `s_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `s_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `s_birth` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `s_sex` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`s_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('01', '赵雷', '1990-01-01', '男');
INSERT INTO `student` VALUES ('02', '钱电', '1990-12-21', '男');
INSERT INTO `student` VALUES ('03', '孙风', '1990-05-20', '男');
INSERT INTO `student` VALUES ('04', '李云', '1990-08-06', '男');
INSERT INTO `student` VALUES ('05', '周梅', '1991-12-01', '女');
INSERT INTO `student` VALUES ('06', '吴兰', '1992-03-01', '女');
INSERT INTO `student` VALUES ('07', '郑竹', '1989-07-01', '女');
INSERT INTO `student` VALUES ('08', '王菊', '1990-01-20', '女');
INSERT INTO `student` VALUES ('09', '张扬', '1993-10-09', '女');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `t_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `t_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`t_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('01', '张三');
INSERT INTO `teacher` VALUES ('02', '李四');
INSERT INTO `teacher` VALUES ('03', '王五');

SET FOREIGN_KEY_CHECKS = 1;
