package com.dcits.orion.batch.service;


import com.dcits.orion.batch.api.IBatchLocal;
import com.dcits.orion.batch.api.IBatchManage;
import com.dcits.orion.batch.api.IBatchStartCheck;
import com.dcits.orion.batch.api.bean.ITaskParam;
import com.dcits.orion.batch.api.data.CheckResult;
import com.dcits.orion.batch.common.*;
import com.dcits.orion.batch.common.bean.CommTableObj;
import com.dcits.orion.batch.common.dao.AutoMapperDao;
import com.dcits.orion.batch.common.dao.BatchDao;
import com.dcits.galaxy.base.data.BeanResult;
import com.dcits.galaxy.base.util.ExceptionUtils;
import com.dcits.galaxy.base.util.StringUtils;
import com.dcits.galaxy.core.spring.SpringApplicationContext;
import com.dcits.galaxy.dal.mybatis.plugins.RowArgs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lixbb on 2015/11/6.
 */
@Repository
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class BatchManage  implements IBatchManage,ApplicationListener<ContextRefreshedEvent>
{

    private static Logger logger = LoggerFactory.getLogger(BatchManage.class);
    @Resource
    BatchDao batchDao;

    @Resource
    ZkTools zkTools;

    @Resource
    IBatchLocal batchLocal;

    @Resource
    AutoMapperDao autoMapperDao;

    BatchManage batchManage;

    public void startBatch(ITaskParam taskParam)
    {
        String batchClass = taskParam.getStaticParam();
        batchManage.startBatch(batchClass);
    }


    @Override
    public boolean startBatch(String batchClass) {

        String batchInd = BatchUtils.getBatchInd();
        Map BATCH_STATUS = batchDao.getBatchStatus(batchClass);
        if (BATCH_STATUS == null || BATCH_STATUS.get("BATCH_STATUS").equals("C")) {
            batchManage.newBatch(batchClass, BATCH_STATUS);
            restartBatch(batchClass);
        } else if (BATCH_STATUS.get("BATCH_STATUS").equals("S")) {
            batchManage.resumeBatch(batchClass, BATCH_STATUS);
            restartBatch(batchClass);
        } else if (BATCH_STATUS.get("BATCH_STATUS").equals("R")) {
            batchDao.restartFailTasks(batchClass);
        }
        if(logger.isInfoEnabled())
        {
            logger.info(batchClass + "批处理启动成功，batchInd=" + batchInd);
        }
        return true;
    }

    public void newBatch(String batchClass,Map BATCH_STATUS)
    {

        try
        {
            batchDao.backupHist(batchClass);
        }
        catch (Exception e)//
        {
            if (logger.isErrorEnabled())
                logger.error(ExceptionUtils.getStackTrace(e));
        }
        if (BATCH_STATUS != null)
            batchDao.deleteBatchStatus(BATCH_STATUS);
        BATCH_STATUS = new HashMap();
        BATCH_STATUS.put("RUN_DATE",batchLocal.getSystemParam().get("runDate"));
        BATCH_STATUS.put("BATCH_CLASS", batchClass);
        BATCH_STATUS.put("BATCH_STATUS", "R");
        BATCH_STATUS.put("STOP_BATCH", "N");
        BATCH_STATUS.put("START_TIME", BatchUtils.getCurTime());
        BATCH_STATUS.put("BATCH_IND", "");
        batchDao.insertBatchStatus(BATCH_STATUS);
        batchDao.deleteAllRunTask(batchClass);
        batchDao.deleteRunJobs(batchClass);
        batchDao.genRunJobs(batchClass);//根据STD_JOB生成 RUN_JOB,

    }
    public void resumeBatch(String batchClass,Map BATCH_STATUS)
    {
        BATCH_STATUS.put("BATCH_STATUS", "R");
        BATCH_STATUS.put("STOP_BATCH", "N");
        BATCH_STATUS.put("BATCH_IND","");
        batchDao.updateBatchStatus(BATCH_STATUS);
    }

    public CheckResult startBatchAndCheck(String batchClass) {
        CheckResult checkResult = new CheckResult();
        Map BATCH_STATUS = batchDao.getBatchStatus(batchClass);
        if (BATCH_STATUS == null || BATCH_STATUS.get("BATCH_STATUS").equals("C")) {
            IBatchStartCheck batchStartCheck = null;
            try {
                batchStartCheck = (IBatchStartCheck) SpringApplicationContext.getContext().getBean("batchStartCheck");
            } catch (Exception e) {
                if (logger.isInfoEnabled())
                    logger.info("主调无启动检查");
            }
            if (batchStartCheck != null) {
                if (logger.isInfoEnabled())
                    logger.info("进行主调启动检查");
                checkResult = batchStartCheck.check(batchClass);
            }
            if (!checkResult.isSuccess())
                return checkResult;
        }
        startBatch(batchClass);
        return checkResult;
    }
    public boolean restartBatch(String batchClass)
    {
        boolean ret = restartBatch(batchClass, "");
        if(ret) {
            if (logger.isInfoEnabled()) {
                logger.info(batchClass + "批处理重新启动成功");
            }
        }
        return ret;
    }

    public boolean restartBatch(String batchClass,String batchInd )
    {
        boolean ret = true;
        if (zkTools.lockBatch(batchClass))
        {
            zkTools.startSchedule(batchClass);
        }
        return ret;
    }






    public boolean stopBatch(String batchClass) {

        boolean ret = true;
        Map map = new HashMap();
        map.put("BATCH_CLASS",batchClass);
        Map BATCH_STATUS =  batchDao.getBatchStatus(batchClass);
        if (BATCH_STATUS.get("BATCH_STATUS").equals("R")) {
            BATCH_STATUS.put("BATCH_STATUS", "S");
            BATCH_STATUS.put("STOP_BATCH", "Y");
            batchDao.updateBatchStatus(BATCH_STATUS);
            ZkTools zkTools = SpringApplicationContext.getContext().getBean(ZkTools.class);
            zkTools.unlockBatch(batchClass);
        } else {
            ret = false;
        }
        return ret;
    }

    public boolean reStartTask(String TASK_ID) {

        boolean ret = true;
        Map task = batchDao.getRunTask(TASK_ID);
        if (task.get("TASK_STATUS").equals("F") || task.get("TASK_STATUS").equals("K")) {
            task.put("TASK_STATUS", "N");
            batchDao.updateRunTask(task);
        } else {
            ret = false;
        }
        return ret;
    }

    public boolean reStartTasks(String JOB_ID) {
        boolean ret = true;
        List tasks = batchDao.getRunTasks(JOB_ID);
        if (tasks != null) {
            for (int i = 0; i < tasks.size(); i++) {
                Map task = (Map) tasks.get(i);
                reStartTask((String) task.get("TASK_ID"));
            }
        }
        return ret;
    }

    public boolean stopTask(String TASK_ID) {
        boolean ret = true;
        Map task = batchDao.getRunTask(TASK_ID);
        if (task.get("TASK_STATUS").equals("P")) {
            task.put("TASK_STATUS", "K");
            batchDao.updateRunTask(task);
        } else {
            ret = false;
        }
        return ret;
    }

    public boolean stopTasks(String JOB_ID) {
        List tasks = batchDao.getRunTasks(JOB_ID);
        if (tasks != null) {
            for (int i = 0; i < tasks.size(); i++) {
                Map task = (Map) tasks.get(i);
                stopTask((String) task.get("TASK_ID"));
            }
        }
        return true;
    }

    public boolean skipTask(String TASK_ID) {
        boolean ret = true;
        Map task = batchDao.getRunTask(TASK_ID);

        if (task.get("TASK_STATUS").equals("F")) {
            Map job = batchDao.getStdJob((String) task.get("JOB_ID"));
            if (job.get("IS_SKIP").equals("M")) {
                task.put("TASK_STATUS", "M");
                batchDao.updateRunTask(task);
            } else {
                ret = false;
            }
        } else {
            ret = false;
        }
        return ret;
    }

    public boolean skipTasks(String JOB_ID) {
         List tasks = batchDao.getRunTasks(JOB_ID);
        if (tasks != null) {
            for (int i = 0; i < tasks.size(); i++) {
                Map task = (Map) tasks.get(i);
                skipTask((String) task.get("TASK_ID"));
            }

        }
        return true;
    }

    public Map getRunTask(String TASK_ID)
    {
        Map runTask = batchDao.getRunTask(TASK_ID);
        if (runTask != null)
            return runTask;
        else
        {
            Map map = new HashMap();
            return map;

        }
    }
    public boolean updateRunTask(Map map)
    {
        batchDao.updateRunTask(map);
        return true;
    }

    public Map getJobSplitParam(String JobId )
    {
        return batchDao.getJobSplitParam(JobId);
    }
    public Map getStdJob(String JobId )
    {
        return batchDao.getStdJob(JobId);
    }
    public boolean insertRunTask(Map map)
    {
        batchDao.insertRunTask(map);
        return true;
    }
    public Map getRunJob(Map map)
    {
        return batchDao.getRunJob(map);
    }
    public boolean updateRunJob(Map map)
    {
        batchDao.updateRunJob(map);
        return true;
    }

    @Override
    public boolean saveSplitResult(String JOB_ID,List<Map> tasks) {
        if (tasks == null)
            return true;
        CommTableObj runJobTable = new CommTableObj();
        runJobTable.setTableName("batch_run_job");
        runJobTable.setCondition("JOB_ID", JOB_ID);
        Map runJob = autoMapperDao.selectOne(runJobTable);
        int splitCnt = BatchUtils.parseInt(runJob.get("SPLIT_CNT"));
        if (splitCnt != 999999999)
            return true;

        int i = 1;
        String runDate = (String)batchLocal.getSystemParam().get("runDate");
        for (Map task : tasks)
        {
            String TASK_ID = JOB_ID + "_" + runDate +"_" + StringUtils.pd(i+"","L","0",6)+"_"+BatchUtils.getCurTimes();//modify by lixiaobin taskID的唯一性
            task.put("TASK_ID",TASK_ID);
            i++;
        }

        runJobTable.clear();
        runJobTable.setCondition("JOB_ID", JOB_ID);
        runJobTable.setField("SPLIT_CNT", tasks.size());
        autoMapperDao.update(runJobTable);
        CommTableObj taskTable = new CommTableObj();
        taskTable.setTableName("batch_run_task");
        taskTable.setRecords(tasks);
        autoMapperDao.batchInsert(taskTable);
        return true;
    }

    @Override
    public Map getSystem() {
        return batchLocal.getSystemParam();
    }



    /*
    @Transactional
    public void saveSplit(String JOB_ID,List<Map> tasks)
    {
        Map runJob = new HashMap();
        runJob.put("JOB_ID",JOB_ID);
        runJob = getRunJob(runJob);
        runJob.put("SPLIT_CNT", tasks.size());
        updateRunJob(runJob);
        CommTableObj tableObj = new CommTableObj();
        tableObj.setTableName("batch_run_task");
        tableObj.setRecords(tasks);
        autoMapperDao.batchInsert(tableObj);
    }
    */
    @Override
    public List getFailTasks(String batchClass) {
        return batchDao.getFailTasks(batchClass);
    }


    public String getErrorMsg(String JOB_ID)
    {
        CommTableObj commTableObj = new CommTableObj("batch_run_task");
        commTableObj.put("JOB_ID", JOB_ID);
        commTableObj.setWhereSql("where (SPLIT_JOB_ID = #{JOB_ID} or JOB_ID = #{JOB_ID}) AND TASK_STATUS = 'F'");
        List<Map> maps = autoMapperDao.selectListByPage(commTableObj,new RowArgs());
        if (maps != null && !maps.isEmpty())
        {
            Map runTask = maps.get(0);
            return runTask.get("ERROR_DESC").toString();
        }
        else return "";
    }

    @Override
    public void updateRunMsg(String TASK_ID, String msg) {
        CommTableObj commTableObj = new CommTableObj("batch_run_task");
        commTableObj.setField("RUN_MSG",msg);
        commTableObj.setCondition("TASK_ID", TASK_ID);
        autoMapperDao.update(commTableObj);
    }
    @Override
    public BeanResult updateSystemDate(String lastRunDate, String runDate, String nextRunDate) {
        return batchLocal.updateSystemDate(lastRunDate,runDate,nextRunDate);
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        batchManage = SpringApplicationContext.getContext().getBean(BatchManage.class);
    }



}
