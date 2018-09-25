alter table fw_flow_log add column REVERSAL_COUNT INT;
DROP TABLE IF EXISTS fw_flow_node_reversal_log;
CREATE TABLE fw_flow_node_reversal_log
(
  FLOW_ID VARCHAR(30) NOT NULL,
  KEY_VALUE VARCHAR(100) NOT NULL,
  NODE_ID VARCHAR(30) NOT NULL,
  SEQ_NO INT NOT NULL,
  BEGIN_TIME BIGINT,
  END_TIME BIGINT,
  REVERSAL_STATUS VARCHAR(1),
  IN_MSG TEXT,
  OUT_MSG TEXT,
  KEY (FLOW_ID, NODE_ID, KEY_VALUE,SEQ_NO)
);
alter table batch_timer_def add column IS_SPLIT VARCHAR(1);

alter table batch_run_job add column FAIL_CNT            INT not null default 0;

alter table batch_run_job add column SPLIT_FAIL_CNT      INT not null default 0;