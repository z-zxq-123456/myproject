DROP TABLE IF EXISTS fw_reversal_tran;
create table fw_reversal_tran
(
REVERSAL_ID                      varchar(50) ,
REVERSAL_ARGS                    BLOB        ,
TRAN_DATE                        varchar(20) ,
REVERSAL_DATE                    varchar(20) ,
MAX_REVERSAL_CNT                 INTEGER     ,
REVERSAL_CNT                     INTEGER     ,
REVERSAL_BEAN_ID                 varchar(20) ,
RET_CODE                         varchar(20) ,
RET_MSG                          varchar(100),
STATUS                           varchar(1),
UNIQUE KEY FW_REVERSAL_TRAN_PK (REVERSAL_ID) USING BTREE
);
