alter table BATCH_STD_JOB add column BATCH_SIZE                 INT;
alter table BATCH_RUN_TASK add column  ERROR_MSG                 VARCHAR(500);
alter table BATCH_RUN_TASK add column  TIME_ELAPSED               BIGINT;
DROP TABLE IF EXISTS BATCH_STATUS_HIST;
create table BATCH_STATUS_HIST
(
	RUN_DATE            VARCHAR(8) ,
    BATCH_CLASS         VARCHAR(4) ,
    BATCH_STATUS        VARCHAR(3) ,
    STOP_BATCH          VARCHAR(1) ,
    START_TIME          VARCHAR(30),
    END_TIME            VARCHAR(30),
    BATCH_IND           VARCHAR(50)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS BATCH_RUN_JOB_HIST;
create table BATCH_RUN_JOB_HIST
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

DROP TABLE IF EXISTS BATCH_RUN_TASK_HIST;
create table BATCH_RUN_TASK_HIST
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
    RUN_DATE                  VARCHAR(8)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;