insert into batch_def(BATCH_CLASS,EOD_SOD,BATCH_DESC) values('MAIN','EOD','��������');
insert into batch_def(BATCH_CLASS,EOD_SOD,BATCH_DESC) values('COMM','COM','������');
commit;
insert into batch_std_job(JOB_ID,JOB_NAME,BATCH_CLASS,JOB_TYPE,JOB_DESC,MODULE_ID,SYSTEM_ID,GX_CLASS_NAME,GX_METHOD)
values ('SPLIT_JOB','SPLIT_JOB','COMM','GX','�����ķֶ�JOB','FM','COMM','com.dcits.orion.batch.task.service.SplitJob','split');
commit;