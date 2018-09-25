

DROP TABLE IF EXISTS batch_def;
create table IF NOT EXISTS batch_def
(
	BATCH_CLASS  VARCHAR(4) not null,
    EOD_SOD  VARCHAR(3),
    BATCH_DESC VARCHAR(100),
    UNIQUE KEY BATCH_CLASS (BATCH_CLASS)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS batch_status;
create table batch_status
(
	RUN_DATE            VARCHAR(8) ,
    BATCH_CLASS         VARCHAR(4) ,
    BATCH_STATUS        VARCHAR(3) ,
    STOP_BATCH          VARCHAR(1) ,
    START_TIME          VARCHAR(30),
    END_TIME            VARCHAR(30),
    BATCH_IND           VARCHAR(50)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS batch_status_hist;
create table batch_status_hist
(
	RUN_DATE            VARCHAR(8) ,
    BATCH_CLASS         VARCHAR(4) ,
    BATCH_STATUS        VARCHAR(3) ,
    STOP_BATCH          VARCHAR(1) ,
    START_TIME          VARCHAR(30),
    END_TIME            VARCHAR(30),
    BATCH_IND           VARCHAR(50)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




DROP TABLE IF EXISTS batch_std_job;
create table batch_std_job
(

	JOB_ID                     VARCHAR(30) NOT NULL,
    JOB_NAME                   VARCHAR(100)  NOT NULL,
    BATCH_CLASS                VARCHAR(4)  NOT NULL ,
    JOB_TYPE                   VARCHAR(4) NOT NULL ,
    JOB_DESC                   VARCHAR(100),
    MODULE_ID                  VARCHAR(4) NOT NULL,
    SYSTEM_ID                  VARCHAR(30) NOT NULL,
    DAY_END                    VARCHAR(1) NOT NULL DEFAULT 'Y' ,
    WEEK_END                   VARCHAR(1) NOT NULL  DEFAULT 'N',
    MTH_END                    VARCHAR(1) NOT NULL  DEFAULT 'N' ,
    YR_END                     VARCHAR(1) NOT NULL  DEFAULT 'N',
    IS_SKIP                    VARCHAR(1)  NOT NULL  DEFAULT 'N' ,
    GX_CLASS_NAME              VARCHAR(100),
    GX_METHOD                  VARCHAR(30) ,
    STATIC_PARAM               VARCHAR(100),
    SHARD_MANAGER_ID           VARCHAR(30) ,
    BY_SCHEMA                   VARCHAR(1)  NOT NULL DEFAULT 'N' ,
    IS_SPLIT                   VARCHAR(1) NOT NULL  DEFAULT 'N' ,
    STATUS                     VARCHAR(1) NOT NULL  DEFAULT 'Y',
    BATCH_SIZE                 INT,
    DTP_FLAG                   VARCHAR(1),
    JOB_GROUP_ID               VARCHAR(40),
    UNIQUE KEY JOB_ID (JOB_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS batch_job_pos;
create table batch_job_pos
(

	JOB_ID                     VARCHAR(30) NOT NULL,
    TOP_POS                    INT NOT NULL,
    LEFT_POS                   INT NOT NULL,
    WIDTH                      INT NOT NULL,
    HEIGHT                     INT NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS batch_job_split_param;
create table batch_job_split_param
(

		JOB_ID                      VARCHAR(30) NOT NULL,
		SPLIT_CLASS                 VARCHAR(1) NOT NULL DEFAULT 'T',
		SPLIT_TYPE                  VARCHAR(1)  NOT NULL DEFAULT 'S',
		SPLIT_CNT                   INT,
		MAX_PER_SPLIT               INT,
		NAMESPACE                   VARCHAR(150) ,
		SQL_ID                      VARCHAR(50) ,
		FILE_PATH                   VARCHAR(100),
		FILE_NAME                   VARCHAR(100),
		FILE_FORMAT                 VARCHAR(50) ,
		FILE_ROW_NAME               VARCHAR(50)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS batch_run_job;
create table batch_run_job
(
JOB_ID                 VARCHAR(30)  ,
JOB_STATUS             VARCHAR(1)   ,
SPLIT_CNT              INT          ,
FINISH_CNT           INT          ,
START_TIME             VARCHAR(30)         ,
END_TIME               VARCHAR(30),
PERCENT             VARCHAR(30),
FAIL_CNT            INT not null default 0,
SPLIT_FAIL_CNT      INT not null default 0,
UNIQUE KEY BATCH_RUN_JOB_ID (JOB_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS batch_run_job_hist;
create table batch_run_job_hist
(
JOB_ID                 VARCHAR(30)  ,
JOB_STATUS             VARCHAR(1)   ,
SPLIT_CNT              INT          ,
FINISH_CNT           INT          ,
START_TIME             VARCHAR(30)         ,
END_TIME               VARCHAR(30),
PERCENT             VARCHAR(30),
RUN_DATE             VARCHAR(8)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS batch_job_dependency;
create table batch_job_dependency
(
DESCENDENT              VARCHAR(30) NOT NULL ,
PREDECESSOR             VARCHAR(30) NOT NULL ,
DEPENDENCY_TYPE         VARCHAR(1) NOT NULL DEFAULT 'S' ,
STATUS                  VARCHAR(1) NOT NULL  DEFAULT 'Y'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS batch_job_line_type;
create table batch_job_line_type
(
DESCENDENT              VARCHAR(30) NOT NULL ,
PREDECESSOR             VARCHAR(30) NOT NULL ,
LINE_TYPE               VARCHAR(10) NOT NULL DEFAULT 'sl' ,
M_VALUE                 DOUBLE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS batch_run_task;
create table batch_run_task
(
	TASK_ID                   VARCHAR(50)  NOT NULL     ,
    JOB_ID                    VARCHAR(30)   NOT NULL    ,
    TASK_STATUS               VARCHAR(1)  NOT NULL     DEFAULT 'N'    ,
    SEQ_NO                    INT               ,
    START_ROW                 BIGINT               ,
    END_ROW                   BIGINT               ,
    FILE_OFFSET               INTEGER,
    FILE_LIMIT                INTEGER,
    SCHEMA_ID                 VARCHAR(30)       ,
    NODE_IP                   VARCHAR(30)       ,
    ERROR_DESC                VARCHAR(5000)     ,
    START_TIME                VARCHAR(30)       ,
    END_TIME                  VARCHAR(30)       ,
    SPLIT_JOB_ID              VARCHAR(30),
    SYSTEM_ID                 VARCHAR(30),
    APP_ID                    VARCHAR(50),
    RUN_MSG                   VARCHAR (200),
    TASK_IND                  VARCHAR(50),
    START_KEY                 VARCHAR(100),
    END_KEY                   VARCHAR(100),
    ERROR_MSG                 VARCHAR(500),
    TIME_ELAPSED              BIGINT,
    ROW_COUNT                 INTEGER,
    UNIQUE KEY BATCH_RUN_TASK_ID (TASK_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS batch_run_task_hist;
create table batch_run_task_hist
(
	TASK_ID                   VARCHAR(50)  NOT NULL     ,
    JOB_ID                    VARCHAR(30)   NOT NULL    ,
    TASK_STATUS               VARCHAR(1)  NOT NULL     DEFAULT 'N'    ,
    SEQ_NO                    INT               ,
    START_ROW                 BIGINT               ,
    END_ROW                   BIGINT               ,
    FILE_OFFSET               INTEGER,
    FILE_LIMIT                INTEGER,
    SCHEMA_ID                 VARCHAR(30)       ,
    NODE_IP                   VARCHAR(30)       ,
    ERROR_DESC                VARCHAR(5000)     ,
    START_TIME                VARCHAR(30)       ,
    END_TIME                  VARCHAR(30)       ,
    SPLIT_JOB_ID              VARCHAR(30),
    SYSTEM_ID                 VARCHAR(30),
    APP_ID                    VARCHAR(50),
    RUN_MSG                   VARCHAR (200),
    TASK_IND                  VARCHAR(50),
    START_KEY                 VARCHAR(100),
    END_KEY                   VARCHAR(100),
    ERROR_MSG                 VARCHAR(500),
    TIME_ELAPSED              BIGINT,
    RUN_DATE                  VARCHAR(8),
    ROW_COUNT                 INTEGER
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS batch_table_def;
CREATE TABLE batch_table_def (
  BATCH_TABLE_ID varchar(20) DEFAULT NULL,
  TABLE_NAME varchar(30) DEFAULT NULL,
  TARGET_TABLE_NAME varchar(30) DEFAULT NULL,
  SELECT_NAMESPACE varchar(100) DEFAULT NULL,
  SELECT_SQL_ID varchar(50) DEFAULT NULL,
  INSERT_NAMESPACE varchar(100) DEFAULT NULL,
  INSERT_SQL_ID varchar(50) DEFAULT NULL,
  WHERE_SQL varchar(1000) DEFAULT NULL,
  BATCH_SIZE          int(11),
  UNIQUE KEY BATCH_TABLE_ID (BATCH_TABLE_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS batch_machine;
CREATE TABLE batch_machine (
 GROUP_ID varchar(32) NOT NULL,
  IP varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
