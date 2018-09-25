
-- ----------------------------
-- Table structure for `BATCH_DATA_SYNC_DEF`
-- ----------------------------
DROP TABLE IF EXISTS `batch_data_sync_def`;
CREATE TABLE `batch_data_sync_def` (
  `SYNC_ID` varchar(30) NOT NULL,
  `SYNC_FLAG` varchar(4) NOT NULL ,
  `SHARD_MANAGE_ID` varchar(50) DEFAULT NULL,
  `TABLE_NAME` varchar(30) NOT NULL,
  `KEY_FIELD` varchar(30) DEFAULT NULL,
  `WHERE_SQL` varchar(1000) DEFAULT NULL ,
  `SELECT_NAMESPACE` varchar(100) DEFAULT NULL ,
  `SELECT_SQL_ID` varchar(50) DEFAULT NULL ,
  `FILE_PATH` varchar(100) DEFAULT NULL,
  `FILE_NAME` varchar(100) DEFAULT NULL,
  `FILE_WRITE_TYPE` varchar(2) DEFAULT NULL ,
  `FILE_FORMAT` varchar(100) DEFAULT NULL,
  `INSERT_SHARD_MANAGE_ID` varchar(50) DEFAULT NULL,
  `TARGET_TABLE_NAME` varchar(30) DEFAULT NULL,
  `MATCH_FLAG` varchar(1) DEFAULT NULL,
  `INSERT_NAMESPACE` varchar(100) DEFAULT NULL,
  `INSERT_SQL_ID` varchar(50) DEFAULT NULL,
  UNIQUE KEY `SYNC_ID_INDEX` (`SYNC_ID`) USING HASH COMMENT 'index'
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
