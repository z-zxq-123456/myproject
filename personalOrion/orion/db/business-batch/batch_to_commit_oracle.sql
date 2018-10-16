BEGIN
   EXECUTE IMMEDIATE 'drop table BATCH_TO_COMMIT';
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END;
/
CREATE TABLE BATCH_TO_COMMIT
(
  TASK_ID           VARCHAR2(50 CHAR)           NOT NULL,
  SHARD_MANAGER_ID  VARCHAR2(50 CHAR),
  SHARD_ID          VARCHAR2(50 CHAR),
  COMMIT_DATA       BLOB,
  GROUP_ID          VARCHAR2(50 CHAR),
  BATCH_GROUP_ID    VARCHAR2(50 CHAR),
  IDEM_ID           VARCHAR2(50 CHAR),
  RECORD_DATE       VARCHAR2(20 CHAR)
)TABLESPACE FW_DATA;