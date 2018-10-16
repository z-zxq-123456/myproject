BEGIN
   EXECUTE IMMEDIATE 'drop TABLE  FW_FLOW_LOG';
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END;
/
CREATE TABLE FW_FLOW_LOG
(
  FLOW_ID VARCHAR2(30 CHAR) NOT NULL,
  KEY_VALUE VARCHAR2(30 CHAR) NOT NULL,
  BEGIN_TIME NUMBER,
  END_TIME NUMBER,
  STATUS VARCHAR2(1 CHAR),
  REVERSAL_STATUS VARCHAR2(1 CHAR),
  REVERSAL_COUNT NUMBER,
  IN_MSG CLOB,
  OUT_MSG CLOB,
  AFTER_STATUS VARCHAR2(1 CHAR),
  APP_ID VARCHAR2(50 CHAR) DEFAULT NULL,
  MQ_ID VARCHAR2(50 CHAR),
  CONSTRAINT FW_FLOW_LOG_PK PRIMARY KEY (FLOW_ID, KEY_VALUE)
);
BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE FW_FLOW_NODE_LOG';
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END;
/
CREATE TABLE FW_FLOW_NODE_LOG
(
  FLOW_ID VARCHAR2(30 CHAR) NOT NULL,
  KEY_VALUE VARCHAR2(100 CHAR) NOT NULL,
  NODE_ID VARCHAR2(30 CHAR) NOT NULL,
  SEQ_NO NUMBER,
  BEGIN_TIME NUMBER,
  END_TIME NUMBER,
  STATUS VARCHAR2(1 CHAR),
  REVERSAL_STATUS VARCHAR2(1 CHAR),
  IN_MSG CLOB,
  OUT_MSG CLOB,
  CONFIRM_MSG CLOB,
  REVERSAL_MSG CLOB,
  CONSTRAINT FW_FLOW_NODE_LOG_PK PRIMARY KEY (FLOW_ID, NODE_ID, KEY_VALUE,SEQ_NO)
);


BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE FW_FLOW_NODE_REVERSAL_LOG';
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END;
/
CREATE TABLE FW_FLOW_NODE_REVERSAL_LOG
(
  FLOW_ID VARCHAR2(30 CHAR) NOT NULL,
  KEY_VALUE VARCHAR2(100 CHAR) NOT NULL,
  NODE_ID VARCHAR2(30 CHAR) NOT NULL,
  SEQ_NO NUMBER,
  BEGIN_TIME NUMBER,
  END_TIME NUMBER,
  REVERSAL_STATUS VARCHAR2(1 CHAR),
  IN_MSG CLOB,
  OUT_MSG CLOB
);

CREATE INDEX fw_flow_node_reversal_log_idx
  ON fw_flow_node_reversal_log (FLOW_ID, NODE_ID, KEY_VALUE,SEQ_NO)
  tablespace FW_INDEX NOLOGGING;

BEGIN
   EXECUTE IMMEDIATE 'drop table FW_FLOW_DEF';
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END;
CREATE TABLE FW_FLOW_DEF
(
  FLOW_ID        VARCHAR2(30 CHAR),
  FLOW_NAME      VARCHAR2(50 CHAR),
  CREATETIME     VARCHAR2(20 CHAR),
  CREATOR        VARCHAR2(20 CHAR),
  EXEC_TYPE      VARCHAR2(20 CHAR),
  RESUME_TYPE    VARCHAR2(20 CHAR),
  SCRIPT         VARCHAR2(200 CHAR),
  REVERSAL_TYPE  VARCHAR2(20 CHAR),
  TIME_OUT       NUMBER(4),
  TIMEOUT_DEAL   VARCHAR2(20 CHAR),
  KEY_FIELDS     VARCHAR2(20 CHAR),
  INIT_NUM       NUMBER(4),
  LOG_FLAG       VARCHAR2(1 CHAR)
)TABLESPACE FW_DATA;

BEGIN
   EXECUTE IMMEDIATE 'drop table FW_FLOW_LINE';
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END;
CREATE TABLE FW_FLOW_LINE
(
  FLOW_ID    VARCHAR2(30 CHAR),
  FROM_NODE  VARCHAR2(20 CHAR),
  TO_NODE    VARCHAR2(20 CHAR),
  EXPR       VARCHAR2(300 CHAR),
  ID         VARCHAR2(20 CHAR),
  TYPE       VARCHAR2(2 CHAR)
)TABLESPACE FW_DATA;

BEGIN
   EXECUTE IMMEDIATE 'drop table FW_FLOW_NODE';
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END;
CREATE TABLE FW_FLOW_NODE
(
  FLOW_ID        VARCHAR2(30 CHAR)              NOT NULL,
  NODE_ID        VARCHAR2(30 CHAR)              NOT NULL,
  NODE_DESC      VARCHAR2(50 CHAR),
  NODE_TYPE      VARCHAR2(1 CHAR),
  NODE_TOP       NUMBER(4),
  NODE_LEFT      NUMBER(4),
  NODE_WIDTH     NUMBER(4),
  NODE_HEIGHT    NUMBER(4),
  EXEC_TYPE      VARCHAR2(1 CHAR),
  SYSTEM_ID      VARCHAR2(30 CHAR),
  ERROR_DEAL     VARCHAR2(1 CHAR),
  UNKNOW_DEAL    VARCHAR2(2 CHAR),
  RETRY_CNT      NUMBER(4),
  BEFORE_SCRIPT  VARCHAR2(20 CHAR),
  AFTER_SCRIPT   VARCHAR2(20 CHAR),
  MAPPER   VARCHAR2(30 CHAR),
  LOOP_IDX VARCHAR2(30 CHAR),
  LOOP_EXPR VARCHAR2(1000 CHAR)
)TABLESPACE FW_DATA;