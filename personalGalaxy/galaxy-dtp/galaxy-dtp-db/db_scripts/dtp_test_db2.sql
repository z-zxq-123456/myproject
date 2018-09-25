
drop database if exists `dtp_test_db2`;
create database `dtp_test_db2` default charset=utf8;

use `dtp_test_db2`;

-- ----------------------------
-- Table structure for money
-- ----------------------------
DROP TABLE IF EXISTS `money`;
CREATE TABLE `money` (
  `userId` varchar(30) NOT NULL,
  `amount` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of money
-- ----------------------------
INSERT INTO `money` VALUES ('2', '400');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userId` varchar(10) NOT NULL,
  `userName` varchar(20) NOT NULL,
  `status` varchar(10) NOT NULL,
  `txid` varchar(32) NOT NULL DEFAULT '--',
  `remark` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('2', 'bb', 'UnLock', '--', '');

-- ----------------------------
-- Table structure for stateinfo
-- ----------------------------
DROP TABLE IF EXISTS `stateinfo`;
CREATE TABLE `stateinfo` (
  `id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


