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


DROP TABLE IF EXISTS fw_tran_info;
CREATE TABLE fw_tran_info (
  SERVICE_ID varchar(100) DEFAULT NULL,
  KEY_VALUE varchar(200) DEFAULT NULL,
  TRAN_DATE varchar(20) DEFAULT NULL,
  TRAN_TIME varchar(30) DEFAULT NULL,
  IN_MSG text,
  OUT_MSG text,
  END_TIME varchar(30) DEFAULT NULL,
  BEAN_RESULT blob,
  SOURCE_TYPE  VARCHAR(2),
  SEQ_NO      VARCHAR(50),
  PROGRAM_ID   VARCHAR(10),
  STATUS       VARCHAR(1),
  REFERENCE    VARCHAR(50),
  PLATFORM_ID  VARCHAR(32),
  USER_ID  VARCHAR(30),
  IP_ADDRESS  VARCHAR(30),
  BRANCH_ID  VARCHAR(20),
  REVERSAL_KEY_VALUE VARCHAR(200),
  WEEK_DAY     INTEGER,
  UNIQUE KEY fw_tran_info_pk (KEY_VALUE) USING BTREE,
  KEY fw_tran_info_idx1 (TRAN_DATE),
  KEY fw_tran_info_idx2 (TRAN_TIME,END_TIME),
  KEY fw_tran_info_idx3 (USER_ID),
  KEY fw_tran_info_idx4 (BRANCH_ID),
  KEY fw_tran_info_idx5 (IP_ADDRESS)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
