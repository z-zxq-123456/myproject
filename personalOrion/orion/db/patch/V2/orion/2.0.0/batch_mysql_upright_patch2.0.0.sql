update batch_std_job set GX_CLASS_NAME = 'com.dcits.orion.batch.task.service.SplitJob' where JOB_ID = 'SPLIT_JOB';
update batch_std_job set GX_CLASS_NAME = 'com.dcits.orion.batch.task.service.BatchTableLoad' where GX_CLASS_NAME = 'com.dcits.galaxy.business.batch.service.BatchTableLoad';
update batch_std_job set GX_CLASS_NAME = 'com.dcits.orion.batch.task.service.BatchLoadDataByKey' where GX_CLASS_NAME = 'com.dcits.galaxy.business.batch.service.BatchLoadDataByKey';
commit;
alter table batch_std_job add column DTP_FLAG varchar(1);
commit;
alter TABLE batch_timer_def add column PAUSE_BATCH varchar(4);
alter TABLE batch_timer_def add column REC_FLAG varchar(1);
alter table batch_std_job add column JOB_GROUP_ID varchar(40);