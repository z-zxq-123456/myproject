/* Formatted on 2016/1/4 14:31:52 (QP5 v5.115.810.9015) */
/*==============================================================*/

/* Table:  batch_def                             */

/*==============================================================*/

BEGIN
   EXECUTE IMMEDIATE 'drop table batch_def';
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END;
/

CREATE TABLE batch_def
(
   BATCH_CLASS   VARCHAR2 (4 CHAR) NOT NULL,
   EOD_SOD       VARCHAR2 (3 CHAR),
   BATCH_DESC    VARCHAR2 (100 CHAR)
) TABLESPACE FW_DATA;

CREATE UNIQUE INDEX batch_def_pk
   ON batch_def (BATCH_CLASS)
   TABLESPACE FW_INDEX NOLOGGING;

/*==============================================================*/

/* Table:  batch_job_dependency                              */

/*==============================================================*/

BEGIN
   EXECUTE IMMEDIATE 'drop table batch_job_dependency';
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END;
/

CREATE TABLE batch_job_dependency
(
   DESCENDENT        VARCHAR2 (30 CHAR) NOT NULL,
   PREDECESSOR       VARCHAR2 (30 CHAR) NOT NULL,
   DEPENDENCY_TYPE   VARCHAR2 (1 CHAR) DEFAULT 'S' NOT NULL,
   STATUS            VARCHAR2 (1 CHAR) DEFAULT 'Y' NOT NULL
) TABLESPACE FW_DATA;

CREATE UNIQUE INDEX batch_job_dependency_pk
   ON batch_job_dependency (DESCENDENT, PREDECESSOR)
   TABLESPACE FW_INDEX NOLOGGING;


/*==============================================================*/

/* Table: batch_job_line_type                                */

/*==============================================================*/

BEGIN
   EXECUTE IMMEDIATE 'drop table batch_job_line_type';
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END;
/

CREATE TABLE batch_job_line_type
(
   DESCENDENT    VARCHAR2 (30 CHAR) NOT NULL,
   PREDECESSOR   VARCHAR2 (30 CHAR) NOT NULL,
   LINE_TYPE     VARCHAR2 (10 CHAR) DEFAULT 'sl' NOT NULL,
   M_VALUE       NUMBER
) TABLESPACE FW_DATA;

CREATE UNIQUE INDEX batch_job_line_type_pk
   ON batch_job_line_type (DESCENDENT, PREDECESSOR)
   TABLESPACE FW_INDEX NOLOGGING;

/*==============================================================*/

/* Table: "batch_job_pos"                                       */

/*==============================================================*/

BEGIN
   EXECUTE IMMEDIATE 'drop table batch_job_pos';
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END;
/

CREATE TABLE batch_job_pos
(
   JOB_ID     VARCHAR2 (30 CHAR) NOT NULL,
   TOP_POS    INTEGER NOT NULL,
   LEFT_POS   INTEGER NOT NULL,
   WIDTH      INTEGER NOT NULL,
   HEIGHT     INTEGER NOT NULL
) TABLESPACE FW_DATA;

CREATE UNIQUE INDEX batch_job_pos_pk
   ON batch_job_pos (JOB_ID)
   TABLESPACE FW_INDEX NOLOGGING;



/*==============================================================*/

/* Table: "batch_job_split_param"                               */

/*==============================================================*/

BEGIN
   EXECUTE IMMEDIATE 'drop table batch_job_split_param';
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END;
/

CREATE TABLE batch_job_split_param
(
   JOB_ID          VARCHAR2 (30 CHAR) NOT NULL,
   SPLIT_CLASS     VARCHAR2 (1 CHAR) DEFAULT 'T' NOT NULL,
   SPLIT_TYPE      VARCHAR2 (1 CHAR) DEFAULT 'M' NOT NULL,
   SPLIT_CNT       INTEGER,
   MAX_PER_SPLIT   INTEGER,
   NAMESPACE       VARCHAR2 (100 CHAR),
   SQL_ID          VARCHAR2 (50 CHAR),
   FILE_PATH       VARCHAR2 (100 CHAR),
   FILE_NAME       VARCHAR2 (100 CHAR),
   FILE_FORMAT     VARCHAR2 (50 CHAR),
   FILE_ROW_NAME   VARCHAR2 (50 CHAR)
) TABLESPACE FW_DATA;

CREATE UNIQUE INDEX batch_job_split_param_pk
   ON batch_job_split_param (JOB_ID)
   TABLESPACE FW_INDEX NOLOGGING;

/*==============================================================*/

/* Table: "batch_run_job"                                       */

/*==============================================================*/

BEGIN
   EXECUTE IMMEDIATE 'drop table batch_run_job';
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END;
/

CREATE TABLE batch_run_job
(
   JOB_ID       VARCHAR2 (30 CHAR),
   JOB_STATUS   VARCHAR2 (1 CHAR),
   SPLIT_CNT    INTEGER,
   FINISH_CNT   INTEGER,
   START_TIME   VARCHAR2 (30 CHAR),
   END_TIME     VARCHAR2 (30 CHAR),
   PERCENT      VARCHAR2 (30 CHAR),
   FAIL_CNT            INTEGER DEFAULT 0 NOT NULL,
   SPLIT_FAIL_CNT      INTEGER DEFAULT 'Y' NOT NULL
) TABLESPACE FW_DATA;

CREATE UNIQUE INDEX batch_run_job_pk
   ON batch_run_job (JOB_ID)
   TABLESPACE FW_INDEX NOLOGGING;


BEGIN
   EXECUTE IMMEDIATE 'drop table batch_run_job_hist';
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END;
/

   CREATE TABLE batch_run_job_hist
   (
      JOB_ID       VARCHAR2 (30 CHAR),
      JOB_STATUS   VARCHAR2 (1 CHAR),
      SPLIT_CNT    INTEGER,
      FINISH_CNT   INTEGER,
      START_TIME   VARCHAR2 (30 CHAR),
      END_TIME     VARCHAR2 (30 CHAR),
      PERCENT      VARCHAR2 (30 CHAR),
      RUN_DATE     VARCHAR2 (8 CHAR)
   ) TABLESPACE FW_DATA;

   CREATE INDEX batch_run_job_hist_idx
      ON batch_run_job_hist (RUN_DATE,JOB_ID)
      TABLESPACE FW_INDEX NOLOGGING;

/*==============================================================*/

/* Table: "batch_run_task"                                      */

/*==============================================================*/

BEGIN
   EXECUTE IMMEDIATE 'drop table batch_run_task';
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END;
/

CREATE TABLE batch_run_task
(
   TASK_ID        VARCHAR2 (50 CHAR) NOT NULL,
   JOB_ID         VARCHAR2 (30 CHAR) NOT NULL,
   TASK_STATUS    VARCHAR2 (1 CHAR) DEFAULT 'N' NOT NULL,
   SEQ_NO         INTEGER,
   START_ROW      NUMBER,
   END_ROW        NUMBER,
   FILE_OFFSET    NUMBER,
   FILE_LIMIT     NUMBER,
   SCHEMA_ID      VARCHAR2 (30 CHAR),
   NODE_IP        VARCHAR2 (30 CHAR),
   ERROR_DESC     VARCHAR2 (4000 CHAR),
   START_TIME     VARCHAR2 (30 CHAR),
   END_TIME       VARCHAR2 (30 CHAR),
   SPLIT_JOB_ID   VARCHAR2 (30 CHAR),
   SYSTEM_ID      VARCHAR2 (30 CHAR),
   APP_ID         VARCHAR2 (50 CHAR),
   RUN_MSG        VARCHAR2 (200 CHAR),
   TASK_IND       VARCHAR2 (50 CHAR),
   START_KEY                 VARCHAR(100),
   END_KEY                   VARCHAR(100),
   ERROR_MSG                 VARCHAR(500 CHAR),
   TIME_ELAPSED              NUMBER,
   ROW_COUNT                 NUMBER
) TABLESPACE FW_DATA;

CREATE UNIQUE INDEX batch_run_task_pk
   ON batch_run_task (TASK_ID)
   TABLESPACE FW_INDEX NOLOGGING;

CREATE INDEX batch_run_task_idx1
   ON batch_run_task (JOB_ID)
   TABLESPACE FW_INDEX NOLOGGING;

CREATE INDEX batch_run_task_idx2
   ON batch_run_task (TASK_STATUS)
   TABLESPACE FW_INDEX NOLOGGING;

BEGIN
   EXECUTE IMMEDIATE 'drop table batch_run_task_hist';
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END;
/
CREATE TABLE batch_run_task_hist
(
   TASK_ID        VARCHAR2 (50 CHAR) NOT NULL,
   JOB_ID         VARCHAR2 (30 CHAR) NOT NULL,
   TASK_STATUS    VARCHAR2 (1 CHAR) DEFAULT 'N' NOT NULL,
   SEQ_NO         INTEGER,
   START_ROW      NUMBER,
   END_ROW        NUMBER,
   FILE_OFFSET    NUMBER,
   FILE_LIMIT     NUMBER,
   SCHEMA_ID      VARCHAR2 (30 CHAR),
   NODE_IP        VARCHAR2 (30 CHAR),
   ERROR_DESC     VARCHAR2 (4000 CHAR),
   START_TIME     VARCHAR2 (30 CHAR),
   END_TIME       VARCHAR2 (30 CHAR),
   SPLIT_JOB_ID   VARCHAR2 (30 CHAR),
   SYSTEM_ID      VARCHAR2 (30 CHAR),
   APP_ID         VARCHAR2 (50 CHAR),
   RUN_MSG        VARCHAR2 (200 CHAR),
   TASK_IND       VARCHAR2 (50 CHAR),
   START_KEY                 VARCHAR(100),
   END_KEY                   VARCHAR(100),
   ERROR_MSG                 VARCHAR(500 CHAR),
   TIME_ELAPSED              NUMBER,
   RUN_DATE                VARCHAR2 (8 CHAR),
   ROW_COUNT                 NUMBER
) TABLESPACE FW_DATA;

CREATE INDEX batch_run_task_hist_idx1
   ON batch_run_task_hist (RUN_DATE,JOB_ID)
   TABLESPACE FW_INDEX NOLOGGING;
/*==============================================================*/

/* Table: "batch_status"                                        */

/*==============================================================*/

BEGIN
   EXECUTE IMMEDIATE 'drop table batch_status';
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END;
/

CREATE TABLE batch_status
(
   RUN_DATE       VARCHAR2 (8 CHAR),
   BATCH_CLASS    VARCHAR2 (4 CHAR),
   BATCH_STATUS   VARCHAR2 (3 CHAR),
   STOP_BATCH     VARCHAR2 (1 CHAR),
   START_TIME     VARCHAR2 (30 CHAR),
   END_TIME       VARCHAR2 (30 CHAR),
   BATCH_IND      VARCHAR2 (50 CHAR)
) TABLESPACE FW_DATA;

CREATE UNIQUE INDEX batch_status_pk
   ON batch_status (BATCH_CLASS)
   TABLESPACE FW_INDEX NOLOGGING;

BEGIN
   EXECUTE IMMEDIATE 'drop table batch_status_hist';
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END;
/
CREATE TABLE batch_status_hist
(
   RUN_DATE       VARCHAR2 (8 CHAR),
   BATCH_CLASS    VARCHAR2 (4 CHAR),
   BATCH_STATUS   VARCHAR2 (3 CHAR),
   STOP_BATCH     VARCHAR2 (1 CHAR),
   START_TIME     VARCHAR2 (30 CHAR),
   END_TIME       VARCHAR2 (30 CHAR),
   BATCH_IND      VARCHAR2 (50 CHAR)
) TABLESPACE FW_DATA;

CREATE INDEX  batch_status_hist_idx
   ON batch_status_hist (RUN_DATE,BATCH_CLASS)
   TABLESPACE FW_INDEX NOLOGGING;
/*==============================================================*/

/* Table: "batch_std_job"                                       */

/*==============================================================*/

BEGIN
   EXECUTE IMMEDIATE 'drop table batch_std_job';
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END;
/

CREATE TABLE batch_std_job
(
   JOB_ID             VARCHAR2 (30 CHAR) NOT NULL,
   JOB_NAME           VARCHAR2 (100 CHAR) NOT NULL,
   BATCH_CLASS        VARCHAR2 (4 CHAR) NOT NULL,
   JOB_TYPE           VARCHAR2 (4 CHAR) NOT NULL,
   JOB_DESC           VARCHAR2 (100 CHAR),
   MODULE_ID          VARCHAR2 (4 CHAR) NOT NULL,
   SYSTEM_ID          VARCHAR2 (30 CHAR) NOT NULL,
   DAY_END            VARCHAR2 (1 CHAR) DEFAULT 'Y' NOT NULL,
   WEEK_END           VARCHAR2 (1 CHAR) DEFAULT 'N' NOT NULL,
   MTH_END            VARCHAR2 (1 CHAR) DEFAULT 'N' NOT NULL,
   YR_END             VARCHAR2 (1 CHAR) DEFAULT 'N' NOT NULL,
   IS_SKIP            VARCHAR2 (1 CHAR) DEFAULT 'N' NOT NULL,
   GX_CLASS_NAME      VARCHAR2 (100 CHAR),
   GX_METHOD          VARCHAR2 (30 CHAR),
   STATIC_PARAM       VARCHAR2 (100 CHAR),
   BY_SCHEMA          VARCHAR2 (1 CHAR) DEFAULT 'N' NOT NULL,
   IS_SPLIT           VARCHAR2 (1 CHAR) DEFAULT 'N' NOT NULL,
   STATUS             VARCHAR2 (1 CHAR) DEFAULT 'Y' NOT NULL,
   SHARD_MANAGER_ID   VARCHAR2 (30 CHAR),
   BATCH_SIZE         NUMBER,
   DTP_FLAG           VARCHAR2(1 CHAR),
   JOB_GROUP_ID       VARCHAR2(40 CHAR)
) TABLESPACE FW_DATA;

CREATE UNIQUE INDEX batch_std_job_pk
   ON batch_std_job (JOB_ID)
   TABLESPACE FW_INDEX NOLOGGING;



/* 批量数据导入导出定义表 */
BEGIN
   EXECUTE IMMEDIATE 'drop table BATCH_TABLE_DEF';
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END;
/

CREATE TABLE BATCH_TABLE_DEF
(
   BATCH_TABLE_ID      VARCHAR2(20 CHAR),
   TABLE_NAME          VARCHAR2 (30 CHAR),
   TARGET_TABLE_NAME   VARCHAR2 (30 CHAR),
   SELECT_NAMESPACE    VARCHAR2 (100 CHAR),
   SELECT_SQL_ID       VARCHAR2 (50 CHAR),
   INSERT_NAMESPACE    VARCHAR2 (100 CHAR),
   INSERT_SQL_ID       VARCHAR2 (50 CHAR),
   WHERE_SQL           VARCHAR2 (1000 CHAR),
   BATCH_SIZE          NUMBER
) TABLESPACE FW_DATA;


CREATE UNIQUE INDEX BATCH_TABLE_DEF_PK
   ON BATCH_TABLE_DEF (BATCH_TABLE_ID)
   TABLESPACE FW_INDEX NOLOGGING;

   BEGIN
      EXECUTE IMMEDIATE 'DROP TABLE BATCH_MACHINE';
   EXCEPTION
      WHEN OTHERS
      THEN
         NULL;
   END;
   /

   CREATE TABLE BATCH_MACHINE (
     GROUP_ID VARCHAR2(32 CHAR) NOT NULL,
     IP VARCHAR2(32 CHAR) NOT NULL
   ) TABLESPACE FW_DATA;


