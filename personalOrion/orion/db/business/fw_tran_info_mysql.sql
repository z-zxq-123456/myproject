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