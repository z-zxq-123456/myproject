DROP TABLE IF EXISTS batch_param_table_def;
CREATE TABLE batch_param_table_def
(
   SEQ_NO              VARCHAR (32),
   TABLE_NAME          VARCHAR (30),
   TARGET_TABLE_NAME   VARCHAR (30),
   DATA_SOURCE         VARCHAR (20),
   SELECT_NAMESPACE    VARCHAR (100),
   SELECT_SQL_ID       VARCHAR (50),
   INSERT_NAMESPACE    VARCHAR (100),
   INSERT_SQL_ID       VARCHAR (50),
   WHERE_SQL           VARCHAR (1000),
   BATCH_SIZE          int(11),
   UNIQUE KEY BATCH_PARAM_TABLE_DEF_PK (SEQ_NO)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
