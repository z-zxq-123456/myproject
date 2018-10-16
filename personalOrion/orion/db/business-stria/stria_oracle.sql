/*==============================================================*/

/* Table: "STRIA_FLOW"                                      */

/*==============================================================*/

BEGIN
   EXECUTE IMMEDIATE 'drop table STRIA_FLOW';
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END;
/

CREATE TABLE STRIA_FLOW
(
  FLOWID      VARCHAR2(30 CHAR)              NOT NULL,
  FLOW_TYPE   VARCHAR2(2 CHAR) DEFAULT '01',
  TITLE       VARCHAR2(50 CHAR),
  INIT_NUM    NUMBER(4),
  VERSION     VARCHAR2(10 CHAR),
  STATE       NUMBER(1) DEFAULT '1',
  CREATETIME  VARCHAR2(20 CHAR),
  CREATOR     VARCHAR2(20 CHAR),
  DTP_FLAG    VARCHAR2(1 CHAR),
  TIME_OUT    NUMBER(4),
  PROD_TYPE   VARCHAR2(10 CHAR),
  EVENT_TYPE  VARCHAR2(20 CHAR),
  TX_FLAG  VARCHAR2(1 CHAR) DEFAULT 'Y'
) TABLESPACE FW_DATA;

CREATE UNIQUE INDEX PK_STRIA_FLOW
   ON STRIA_FLOW (FLOWID)
   TABLESPACE FW_INDEX NOLOGGING;

/*==============================================================*/

/* Table: "STRIA_FLOW_NODES"                                      */

/*==============================================================*/

BEGIN
   EXECUTE IMMEDIATE 'drop table STRIA_FLOW_NODES';
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END;
/

CREATE TABLE STRIA_FLOW_NODES
(
  FLOWID         VARCHAR2(30 CHAR)              NOT NULL,
  ID             VARCHAR2(30 CHAR)              NOT NULL,
  NAME           VARCHAR2(50 CHAR)              NOT NULL,
  TYPE           VARCHAR2(20 CHAR)              NOT NULL,
  CANVAS_TOP     NUMBER(4),
  CANVAS_LEFT    NUMBER(4),
  DIV_WIDTH      NUMBER(4),
  DIV_HEIGHT     NUMBER(4),
  SOURCE_TYPE    VARCHAR2(50 CHAR),
  SERVICE_TYPE   VARCHAR2(20 CHAR),
  CLAZZ          VARCHAR2(300 CHAR),
  METHOD_NAME    VARCHAR2(30 CHAR),
  ARGS           VARCHAR2(300 CHAR),
  ARGS_CLAZZ     VARCHAR2(300 CHAR),
  VAR            VARCHAR2(20 CHAR),
  SUB_CONDITION  VARCHAR2(300 CHAR),
  REQUEST        VARCHAR2(20 CHAR),
  SERVICE_CODE   VARCHAR2(20 CHAR),
  MESSAGE_TYPE   VARCHAR2(4 CHAR),
  MESSAGE_CODE   VARCHAR2(4 CHAR),
  EVENT_ID       VARCHAR2(30 CHAR)
) TABLESPACE FW_DATA;

CREATE UNIQUE INDEX PK_STRIA_FLOW_NODES
   ON STRIA_FLOW_NODES (FLOWID, ID)
   TABLESPACE FW_INDEX NOLOGGING;

/*==============================================================*/

/* Table: "STRIA_FLOW_LINES"                                      */

/*==============================================================*/

BEGIN
   EXECUTE IMMEDIATE 'drop table STRIA_FLOW_LINES';
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END;
/

CREATE TABLE STRIA_FLOW_LINES
(
  FLOWID     VARCHAR2(30 CHAR),
  ID         VARCHAR2(30 CHAR),
  NAME       VARCHAR2(50 CHAR),
  TYPE       VARCHAR2(20 CHAR),
  FROM_NODE  VARCHAR2(20 CHAR),
  TO_NODE    VARCHAR2(20 CHAR),
  EXPR       VARCHAR2(300 CHAR)
) TABLESPACE FW_DATA;

CREATE UNIQUE INDEX PK_STRIA_FLOW_LINES
   ON STRIA_FLOW_LINES (FLOWID, ID)
   TABLESPACE FW_INDEX NOLOGGING;