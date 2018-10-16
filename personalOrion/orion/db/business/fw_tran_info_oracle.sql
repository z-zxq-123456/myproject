BEGIN
   EXECUTE IMMEDIATE 'drop table fw_tran_info';
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END;
/

CREATE TABLE fw_tran_info (
   SERVICE_ID VARCHAR2(100 char) DEFAULT NULL,
   KEY_VALUE VARCHAR2(200 char) DEFAULT NULL,
   TRAN_DATE VARCHAR2(20 char) DEFAULT NULL,
   TRAN_TIME VARCHAR2(30 char) DEFAULT NULL,
   IN_MSG CLOB,
   OUT_MSG CLOB,
   END_TIME VARCHAR2(30 char) DEFAULT NULL,
   BEAN_RESULT blob,
   SOURCE_TYPE  VARCHAR2(2 char),
   SEQ_NO      VARCHAR2(50 char),
   PROGRAM_ID   VARCHAR2(10 char),
   STATUS       VARCHAR2(1 char),
   REFERENCE    VARCHAR2(50 char),
   PLATFORM_ID  VARCHAR2(32 char),
   USER_ID  VARCHAR2(30 char),
   IP_ADDRESS  VARCHAR2(30 char),
   BRANCH_ID  VARCHAR2(20 char),
   REVERSAL_KEY_VALUE VARCHAR2(200 char),
   WEEK_DAY     number(1)
) TABLESPACE FW_DATA;


CREATE UNIQUE INDEX fw_tran_info_pk
   ON fw_tran_info (KEY_VALUE)
   tablespace FW_INDEX NOLOGGING;

CREATE INDEX fw_tran_info_idx1
   ON fw_tran_info (TRAN_DATE)
   tablespace FW_INDEX NOLOGGING;

CREATE INDEX fw_tran_info_idx2
   ON fw_tran_info (TRAN_TIME,END_TIME)
   TABLESPACE FW_INDEX NOLOGGING;

CREATE INDEX fw_tran_info_idx3
   ON fw_tran_info (USER_ID)
   TABLESPACE FW_INDEX NOLOGGING;

CREATE INDEX fw_tran_info_idx4
   ON fw_tran_info (BRANCH_ID)
   TABLESPACE FW_INDEX NOLOGGING;

CREATE INDEX fw_tran_info_idx5
   ON fw_tran_info (IP_ADDRESS)
   TABLESPACE FW_INDEX NOLOGGING;

