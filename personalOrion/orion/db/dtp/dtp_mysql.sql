-- ----------------------------
-- Table structure for dtp_trunk
-- ----------------------------
DROP TABLE IF EXISTS `dtp_trunk`;
CREATE TABLE `dtp_trunk` (
  `txid` varchar(32) NOT NULL,
  `needOrder` varchar(8) NOT NULL,
  `status` varchar(20) NOT NULL,
  `appGroup` varchar(50) NOT NULL,
  `startTime` decimal(32,0) NOT NULL DEFAULT '0',
  PRIMARY KEY (`txid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dtp_trunk
-- ----------------------------

-- ----------------------------
-- Table structure for dtp_branch
-- ----------------------------
DROP TABLE IF EXISTS `dtp_branch`;
CREATE TABLE `dtp_branch` (
  `bxid` varchar(32) NOT NULL,
  `parentBxid` varchar(32) DEFAULT NULL,
  `txid` varchar(32) NOT NULL,
  `indexInBranch` int(11) NOT NULL DEFAULT '-1',
  `indexInTrunk` int(11) NOT NULL DEFAULT '-1',
  `status` varchar(20) NOT NULL,
  `appGroup` varchar(50) NOT NULL,
  PRIMARY KEY (`bxid`),
  CONSTRAINT `FK_BRANCH_TRUNK_TXID` FOREIGN KEY (`txid`) REFERENCES `dtp_trunk` (`txid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dtp_branch
-- ----------------------------

-- ----------------------------
-- Table structure for dtp_preparelog
-- ----------------------------
DROP TABLE IF EXISTS `dtp_preparelog`;
CREATE TABLE `dtp_preparelog` (
  `logId` varchar(32) NOT NULL,
  `bxid` varchar(32) NOT NULL,
  `txid` varchar(32) NOT NULL,
  `logIndex` int(11) NOT NULL DEFAULT '-1',
  `content` blob NOT NULL,
  `status` varchar(10) NOT NULL,
  PRIMARY KEY (`logId`),
  CONSTRAINT `FK_PREPARELOG_TRUNK_TXID` FOREIGN KEY (`txid`) REFERENCES `dtp_trunk` (`txid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dtp_preparelog
-- ----------------------------

-- ----------------------------
-- Table structure for dtp_submitlog
-- ----------------------------
DROP TABLE IF EXISTS `dtp_submitlog`;
CREATE TABLE `dtp_submitlog` (
  `logId` varchar(32) NOT NULL,
  `bxid` varchar(32) NOT NULL,
  `txid` varchar(32) NOT NULL,
  `logIndex` int(11) NOT NULL DEFAULT '-1',
  `content` blob NOT NULL,
  `status` varchar(10) NOT NULL ,
  PRIMARY KEY (`logId`),
  CONSTRAINT `FK_SUBMITLOG_TRUNK_TXID` FOREIGN KEY (`txid`) REFERENCES `dtp_trunk` (`txid`) ON DELETE CASCADE,
  CONSTRAINT `FK_SUBMITLOG_BRANCH_BXID` FOREIGN KEY (`bxid`) REFERENCES `dtp_branch` (`bxid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dtp_submitlog
-- ----------------------------

-- ----------------------------
-- Table structure for dtp_resource
-- ----------------------------
DROP TABLE IF EXISTS `dtp_resource`;
CREATE TABLE `dtp_resource` (
  `tableName` varchar(32) NOT NULL,
  `columnName` varchar(32) NOT NULL,
  `columnValue` varchar(32) NOT NULL,
  `txid` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`tableName`,`columnName`,`columnValue`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;