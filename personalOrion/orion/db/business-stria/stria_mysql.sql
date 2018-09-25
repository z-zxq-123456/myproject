/*
Navicat MySQL Data Transfer

Source Server         : 192.168.165.219
Source Server Version : 50619
Source Host           : 192.168.165.219:3306
Source Database       : ensemble_upright

Target Server Type    : MYSQL
Target Server Version : 50619
File Encoding         : 65001

Date: 2016-01-04 16:36:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for stria_flow
-- ----------------------------
DROP TABLE IF EXISTS `stria_flow`;
CREATE TABLE `stria_flow` (
  `FLOWID` varchar(30) NOT NULL COMMENT '流程ID',
  `FLOW_TYPE` varchar(2) NOT NULL DEFAULT '01' COMMENT '流程类型 01-服务流程；02-事件流程',
  `TITLE` varchar(50) DEFAULT NULL COMMENT '流程描述',
  `INIT_NUM` decimal(4,0) DEFAULT NULL COMMENT '初始流程节数',
  `VERSION` varchar(10) DEFAULT NULL COMMENT '版本号',
  `STATE` decimal(1,0) DEFAULT '1' COMMENT '可用开关',
  `CREATETIME` varchar(20) DEFAULT NULL COMMENT '创建时间',
  `CREATOR` varchar(20) DEFAULT NULL COMMENT '创建人',
  `DTP_FLAG` varchar(1) DEFAULT NULL COMMENT '全局事物管理开关',
  `TIME_OUT` decimal(4,0) DEFAULT NULL COMMENT '流程处理超时时间',
  `PROD_TYPE` varchar(10) DEFAULT NULL COMMENT '产品类型',
  `EVENT_TYPE` varchar(20) DEFAULT NULL COMMENT '事件类型',
  `TX_FLAG` varchar(1) DEFAULT 'Y' COMMENT '事务开关',
  PRIMARY KEY (`FLOWID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Stria流程定义';

-- ----------------------------
-- Table structure for stria_flow_lines
-- ----------------------------
DROP TABLE IF EXISTS `stria_flow_lines`;
CREATE TABLE `stria_flow_lines` (
  `FLOWID` varchar(30) NOT NULL COMMENT '流程ID',
  `ID` varchar(30) NOT NULL COMMENT '连接线ID',
  `NAME` varchar(50)  COMMENT '连接线描述',
  `TYPE` varchar(20)  COMMENT '节点类型',
  `FROM_NODE` varchar(20) DEFAULT NULL COMMENT '源节点',
  `TO_NODE` varchar(20) DEFAULT NULL COMMENT '目标节点',
  `EXPR` varchar(300) DEFAULT NULL COMMENT '布尔类型表达式，用于决策节点后的连接线',
  PRIMARY KEY (`FLOWID`,`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Stria流程连接线定义';

-- ----------------------------
-- Table structure for stria_flow_nodes
-- ----------------------------
DROP TABLE IF EXISTS `stria_flow_nodes`;
CREATE TABLE `stria_flow_nodes` (
  `FLOWID` varchar(30) NOT NULL COMMENT '流程ID',
  `ID` varchar(30) NOT NULL COMMENT '节点ID',
  `NAME` varchar(50) NOT NULL COMMENT '节点描述',
  `TYPE` varchar(20) NOT NULL COMMENT '节点类型',
  `CANVAS_TOP` decimal(4,0) DEFAULT NULL COMMENT '据上边界偏移量',
  `CANVAS_LEFT` decimal(4,0) DEFAULT NULL COMMENT '据左边界偏移量',
  `DIV_WIDTH` decimal(4,0) DEFAULT NULL COMMENT '节点图形宽度',
  `DIV_HEIGHT` decimal(4,0) DEFAULT NULL COMMENT '节点图形高度',
  `SOURCE_TYPE` varchar(50) DEFAULT NULL COMMENT '渠道',
  `SERVICE_TYPE` varchar(20) DEFAULT NULL COMMENT '服务类型',
  `CLAZZ` varchar(300) DEFAULT NULL COMMENT '服务执行class',
  `METHOD_NAME` varchar(30) DEFAULT NULL COMMENT '服务执行方法',
  `ARGS` varchar(300) DEFAULT NULL COMMENT '参数取值表达式',
  `ARGS_CLAZZ` varchar(300) DEFAULT NULL COMMENT '参数class',
  `VAR` varchar(20) DEFAULT NULL COMMENT '输出别名定义',
  `SUB_CONDITION` varchar(300) DEFAULT NULL,
  `REQUEST` varchar(20) DEFAULT NULL,
  `SERVICE_CODE` varchar(20) DEFAULT NULL,
  `MESSAGE_TYPE` varchar(4) DEFAULT NULL,
  `MESSAGE_CODE` varchar(4) DEFAULT NULL,
  `EVENT_ID` varchar(30) DEFAULT NULL COMMENT '事件ID',
  PRIMARY KEY (`FLOWID`,`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Stria流程节点定义';
