/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : hope

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-07-27 16:34:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `resourceId` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号，主键，资源表',
  `uresource_uuid` varchar(20) NOT NULL COMMENT '装逼的id',
  `name` varchar(100) NOT NULL COMMENT '资源名称',
  `description` varchar(255) DEFAULT NULL COMMENT '权限描述',
  `url` varchar(255) DEFAULT NULL COMMENT '权限访问路径',
  `type` int(1) NOT NULL COMMENT '资源类型:0目录 1菜单 2按钮',
  `priority` int(3) NOT NULL COMMENT '显示顺序',
  `parent_id` int(11) NOT NULL COMMENT '父编号',
  `parent_ids` varchar(100) NOT NULL COMMENT '父编号列表',
  `permission` varchar(100) NOT NULL COMMENT '权限字符串',
  `icon` varchar(50) DEFAULT '0' COMMENT '图标',
  `available` int(1) NOT NULL COMMENT '是否可用:1有效2删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`resourceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `roleId` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号，主键，角色表',
  `role_uuid` varchar(20) NOT NULL COMMENT '装逼的id',
  `role` varchar(100) NOT NULL COMMENT '角色名称',
  `description` varchar(100) NOT NULL COMMENT '角色描述',
  `resource_ids` varchar(100) NOT NULL COMMENT '授权的资源',
  `available` int(1) NOT NULL COMMENT '是否可用：1有效2删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resource`;
CREATE TABLE `sys_role_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号，主键，角色-资源关联表',
  `roleId` varchar(20) NOT NULL COMMENT '角色id',
  `resourceId` varchar(20) NOT NULL COMMENT '资源id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_resource
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号，主键,用户表',
  `user_uuid` varchar(20) NOT NULL COMMENT '装逼的id',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `salt` varchar(50) DEFAULT NULL COMMENT '盐',
  `role_ids` varchar(100) NOT NULL COMMENT '角色列表',
  `locked` int(1) NOT NULL COMMENT '账户是否锁定:1木有；2你猜啊',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(50) DEFAULT NULL COMMENT '联系方式',
  `sex` int(1) DEFAULT NULL COMMENT '性别：1男2女3变态',
  `age` int(3) DEFAULT NULL COMMENT '年龄',
  `status` int(1) NOT NULL COMMENT '用户状态：1有效2删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登陆时间',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号，主键，用户-角色关联表',
  `userId` varchar(20) NOT NULL COMMENT '用户id',
  `roleId` varchar(20) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
