update BATCH_STD_JOB set GX_CLASS_NAME = 'com.dcits.orion.batch.task.service.SplitJob' where JOB_ID = 'SPLIT_JOB';
update BATCH_STD_JOB set GX_CLASS_NAME = 'com.dcits.orion.batch.task.service.BatchTableLoad' where GX_CLASS_NAME = 'com.dcits.galaxy.business.batch.service.BatchTableLoad';
update BATCH_STD_JOB set GX_CLASS_NAME = 'com.dcits.orion.batch.task.service.BatchLoadDataByKey' where GX_CLASS_NAME = 'com.dcits.galaxy.business.batch.service.BatchLoadDataByKey';
commit;
alter table BATCH_STD_JOB add (DTP_FLAG varchar2(1));
commit;
alter table BATCH_TIMER_DEF add (PAUSE_BATCH varchar2(4));
alter table BATCH_TIMER_DEF add (REC_FLAG varchar2(1));
alter table BATCH_STD_JOB add (JOB_GROUP_ID varchar2(40));