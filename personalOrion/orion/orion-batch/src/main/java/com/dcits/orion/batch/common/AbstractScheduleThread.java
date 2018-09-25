package com.dcits.orion.batch.common;


import com.alibaba.dubbo.common.utils.ConfigUtils;

import com.dcits.galaxy.base.util.DateUtils;
import com.dcits.orion.batch.api.IBatchLocal;
import com.dcits.orion.batch.api.bean.ITaskParam;
import com.dcits.orion.batch.common.bean.TaskParam;
import com.dcits.orion.batch.common.dao.BatchDao;
import com.dcits.galaxy.base.util.ExceptionUtils;
import com.dcits.galaxy.base.util.StringUtils;
import com.dcits.galaxy.core.spring.SpringApplicationContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.*;

/**
 * Created by lixbb on 2015/11/17.
 */
public abstract class  AbstractScheduleThread implements Runnable{

    private static Logger logger = LoggerFactory.getLogger(AbstractScheduleThread.class);
    protected String batchClass;
    protected String batchInd;
    protected BatchDao batchDao;
    ZkTools zkTools;
    private static boolean run = true;

    public static void init()
    {
        run = true;
    }

    public static void destroy() {
        run = false;
    }
    public AbstractScheduleThread(String batchClass,String batchInd,BatchDao batchDao, ZkTools zkTools)
    {
        this.batchClass = batchClass;
        this.batchInd = batchInd;
        this.batchDao = batchDao;
        this.zkTools = zkTools;
    }

    protected boolean isNeedToRun()
    {
        if (!(run))
            return false;
        boolean ret = true;
        Map BATCH_STATUS = batchDao.getBatchStatus(batchClass);
        if(!batchInd.equals(BATCH_STATUS.get("BATCH_IND")))
        {
            ret = false;
            return ret;
        }
        if (!"R".equals(BATCH_STATUS.get("BATCH_STATUS")))
        {
            if (this instanceof  ScheduleThread)
            {
                try
                {
                    zkTools.unlockBatch(batchClass);
                }
                catch (Exception e)
                {
                    if (logger.isErrorEnabled()) {
                        logger.error(ExceptionUtils.getStackTrace(e));
                    }
                }
            }
            ret = false;
        }
        return ret;
    }
    protected void sleep(long ms)
    {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void updateRunningJobs() {
        List<Map> runningJobs = batchDao.getRunningJobs(batchClass);
        if (runningJobs != null && !runningJobs.isEmpty())
        {
            for (Map runJob : runningJobs)
            {
                String jobId = (String)runJob.get("JOB_ID");
                int splitCnt = BatchUtils.parseInt(runJob.get("SPLIT_CNT"));
                int finishCnt = BatchUtils.parseInt(runJob.get("FINISH_CNT"));
                int childFinishCnt =  BatchUtils.parseInt(runJob.get("CHILD_FINISH_CNT"));

                int splitFailCnt = BatchUtils.parseInt(runJob.get("SPLIT_FAIL_CNT"));

                int failCnt = BatchUtils.parseInt(runJob.get("FAIL_CNT"));
                int childFailCnt = BatchUtils.parseInt(runJob.get("CHILD_FAIL_CNT"));
                failCnt += childFailCnt;
                Map runningJob = new HashMap();
                runningJob.put("JOB_ID",jobId);
                runningJob.put("FINISH_CNT",finishCnt);
                runningJob.put("FAIL_CNT",failCnt);
                runningJob.put("SPLIT_FAIL_CNT",splitFailCnt);
                finishCnt += childFinishCnt;
                if (splitCnt == finishCnt)
                {
                    runningJob.put("JOB_STATUS","S");
                    runningJob.put("END_TIME",BatchUtils.getCurTime());
                }
                batchDao.updateRunJob(runningJob);
            }
        }
    }

    /* 方法重构
    protected void updateRunningJobs() {
        //根据fm_run_task 状态更新fm_run_job的状态
        List runningJobs = batchDao.getRunningJobs(batchClass);
        if (runningJobs != null) {
            for (int i = 0; i < runningJobs.size(); i++) {
                Map runningJob = (Map) runningJobs.get(i);
                String jobID = (String) runningJob.get("JOB_ID");
                int splitCnt = BatchUtils.parseInt( runningJob.get("SPLIT_CNT"));
                long FinishCnt = batchDao.getFinishTaskCnt(jobID);
                if (splitCnt == FinishCnt) {
                    long MCnt = batchDao.getMCnt(jobID);
                    if (MCnt > 0) {
                        runningJob.put("JOB_STATUS", "M");
                    } else {
                        long ACnt = batchDao.getACnt(jobID);
                        if (ACnt > 0) {
                            runningJob.put("JOB_STATUS", "A");
                        } else {
                            runningJob.put("JOB_STATUS", "S");
                        }
                    }
                    runningJob.put("END_TIME", BatchUtils.getCurTime());
                    runningJob.put("FINISH_CNT", FinishCnt);
                    batchDao.updateRunJob(runningJob);
                } else {
                    int lastfinishCnt = BatchUtils.parseInt(runningJob.get("FINISH_CNT"));
                    if (lastfinishCnt != FinishCnt) {
                        runningJob.put("FINISH_CNT", FinishCnt);
                        batchDao.updateRunJob(runningJob);
                    }
                }

            }
        }
    }*/


    private boolean isRunToday(Map stdJob)
    {
        Map batchStatus = batchDao.getBatchStatus(batchClass);
        String RUN_DATE = (String)batchStatus.get("RUN_DATE");
        String DAY_END = (String) stdJob.get("DAY_END");
        if ("Y".equals(DAY_END))
            return true;
        String WEEK_END = (String) stdJob.get("WEEK_END");
        String MTH_END = (String) stdJob.get("MTH_END");
        String YR_END = (String) stdJob.get("YR_END");
        boolean isWeekEnd = false;
        boolean isMthEnd = false;
        boolean isYrEnd = false;
        DateFormat dateFormat = DateUtils.getDateFormat("yyyyMMdd");
        try {
            Date date = dateFormat.parse(RUN_DATE);
            int mth = DateUtils.getMonth(date);
            int yr = DateUtils.getYear(date);
            if(DateUtils.getDayOfWeek(date) == 7)
                isWeekEnd = true;
            Date nextDay = DateUtils.addDays(date,1);
            int nextDayMth = DateUtils.getMonth(nextDay);
            int nextDayYr = DateUtils.getYear(nextDay);
            if (mth != nextDayMth)
                isMthEnd = true;
            if (yr != nextDayYr)
                isYrEnd = true;
        } catch (ParseException e) {
            return true;
        }
        if ((isMthEnd && "Y".equals(MTH_END)) || (isWeekEnd && "Y".equals(WEEK_END))||(isYrEnd && "Y".equals(YR_END)))
            return true;
        else return false;
    }

    //处理task状态是P，但是在ZK上没有的注册的task,将状态改为N，重新分发
    protected void dealDeadTasks()
    {

    }

    protected void dealToRunJobs() {
        //找出要跑的JOB
        List toRunJobs = batchDao.getToRunJobs(batchClass);
        for (int i = 0; i < toRunJobs.size(); i++) {
            Map runJob = (Map) toRunJobs.get(i);
            String JOB_ID = (String) runJob.get("JOB_ID");
            Map job = batchDao.getStdJob(JOB_ID);
            if ("N".equals(job.get("STATUS")) || !isRunToday(job))
            {
                runJob.put("JOB_STATUS", "A");
                runJob.put("START_TIME", BatchUtils.getCurTime());
                runJob.put("END_TIME", runJob.get("START_TIME"));
                batchDao.updateRunJob(runJob);
            }
            else {
                if ("GP".equals(job.get("JOB_TYPE"))) {
                    runJob.put("JOB_STATUS", "P");
                    runJob.put("START_TIME", BatchUtils.getCurTime());
                    runJob.put("FINISH_CNT", 0);
                    runJob.put("SPLIT_CNT", batchDao.getChildJobCount(JOB_ID));
                    batchDao.updateRunJob(runJob);
                } else {
                    Map task = new HashMap();
                    IBatchLocal batchLocal = (IBatchLocal) SpringApplicationContext.getContext().getBean("batchLocal");
                    String runDate = (String) batchLocal.getSystemParam().get("runDate");
                    String TASK_ID = runJob.get("JOB_ID") + "_" + runDate + "_" + StringUtils.pd("0", "L", "0", 6) + "_" + BatchUtils.getCurTimes();
                    task.put("TASK_ID", TASK_ID);
                    task.put("JOB_ID", runJob.get("JOB_ID"));
                    task.put("TASK_STATUS", "N");
                    task.put("SYSTEM_ID", job.get("SYSTEM_ID"));

                    if (job.get("IS_SPLIT").equals("Y") || job.get("BY_SCHEMA").equals("Y") || job.get("BY_SCHEMA").equals("A"))//如果是可分段的
                    {
                        task.put("JOB_ID", "SPLIT_JOB");
                        task.put("SPLIT_JOB_ID", (String) runJob.get("JOB_ID"));
                        runJob.put("SPLIT_CNT", 999999999);
                    } else {
                        task.put("JOB_ID", (String) runJob.get("JOB_ID"));
                        task.put("SEQ_NO", 1);
                        runJob.put("SPLIT_CNT", 1);
                    }

                    runJob.put("JOB_STATUS", "P");
                    runJob.put("START_TIME", BatchUtils.getCurTime());
                    runJob.put("FINISH_CNT", 0);
                    batchDao.updateRunJob(runJob);
                    batchDao.insertRunTask(task);
                }
            }
        }
    }

    protected int dealToRunTask(int dealCount) {


        List toRunTasks = batchDao.getToRunTasks(batchClass, dealCount);
        int realCount = 0;
        if (toRunTasks != null)
            realCount = toRunTasks.size();
        Map toRunTaskMap = BatchUtils.getMapByList(toRunTasks, "TASK_ID");

        if (toRunTasks != null) {
            List toSendTasks = new ArrayList();
            for (int i = 0; i < toRunTasks.size(); i++) {
                Map task = (Map) toRunTasks.get(i);
                String JOB_ID  = (String) task.get("JOB_ID");
                if (JOB_ID.equals("SPLIT_JOB"))
                    JOB_ID = (String)task.get("SPLIT_JOB_ID");
                //Map job = batchDao.getStdJob(JOB_ID);
                ITaskParam taskParam = new TaskParam();
                taskParam.setTaskId((String) task.get("TASK_ID"));
                taskParam.setJobId((String) task.get("JOB_ID"));
                taskParam.setJobName((String) task.get("JOB_NAME"));
                taskParam.setBatchClass(batchClass);
                taskParam.setJobType((String) task.get("JOB_TYPE"));
                taskParam.setModuleId((String) task.get("MODULE_ID"));
                taskParam.setIsSkip((String) task.get("IS_SKIP"));
                taskParam.setIsSplit((String) task.get("IS_SPLIT"));
                taskParam.setDtpFlag((String)task.get("DTP_FLAG"));
                if ("Y".equals(task.get("IS_SPLIT")) && !"SPLIT_JOB".equals(task.get("JOB_ID")))
                {
                    //Map splitParam = batchDao.getJobSplitParam(JOB_ID);
                    if (task != null && task.get("MAX_PER_SPLIT") != null)
                    {
                        try {
                            int maxCount = BatchUtils.parseInt(task.get("MAX_PER_SPLIT"));
                            taskParam.setMaxPerSplit(maxCount);
                        }
                        catch (Exception e)
                        {
                            if (logger.isErrorEnabled())
                            {
                                logger.error(task.get("MAX_PER_SPLIT").toString() + " : " + ExceptionUtils.getStackTrace(e));
                            }
                        }
                    }
                }

                if (task.get("BATCH_SIZE") != null)
                {
                    taskParam.setBatchSize(BatchUtils.parseInt(task.get("BATCH_SIZE")));
                }
                if("SPLIT_JOB".equals(task.get("JOB_ID")))
                {
                    taskParam.setGxClassName("com.dcits.orion.batch.task.service.SplitJob");
                    taskParam.setGxMethod("split");
                }
                else
                {
                    taskParam.setGxClassName((String) task.get("GX_CLASS_NAME"));
                    taskParam.setGxMethod((String) task.get("GX_METHOD"));
                }
                taskParam.setStaticParam((String) task.get("STATIC_PARAM"));
                if (task.get("SEQ_NO")!=null)
                {
                    taskParam.setSeqNo(BatchUtils.parseInt(task.get("SEQ_NO")));
                }


                if (task.get("START_ROW") != null)
                    taskParam.setStartRow(BatchUtils.parseLong(task.get("START_ROW")) );
                if (task.get("END_ROW") != null)
                    taskParam.setEndRow(BatchUtils.parseLong(task.get("END_ROW")));

                if (task.get("ROW_COUNT") != null)
                {
                    taskParam.setRowCount(BatchUtils.parseInt(task.get("ROW_COUNT")));
                }
                if (task.get("FILE_OFFSET") != null)
                    taskParam.setFileOffset(BatchUtils.parseLong(task.get("FILE_OFFSET")));
                if (task.get("FILE_LIMIT") != null)
                    taskParam.setFileLimit(BatchUtils.parseInt(task.get("FILE_LIMIT")));
                taskParam.setSchemaId((String) task.get("SCHEMA_ID"));
                taskParam.setSystemId((String) task.get("SYSTEM_ID"));
                taskParam.setNodeIp((String) task.get("NODE_IP"));
                taskParam.setSplitJobId((String) task.get("SPLIT_JOB_ID"));
                taskParam.setShardManagerId((String) task.get("SHARD_MANAGER_ID"));
                taskParam.setStartKey((String) task.get("START_KEY"));
                taskParam.setEndKey((String)task.get("END_KEY"));
                toSendTasks.add(taskParam);
            }
            if (toSendTasks != null && toSendTasks.size() > 0) {
                zkTools.dispatchTasks(toSendTasks,toRunTaskMap);
            }
        }
        return realCount;
    }


}
