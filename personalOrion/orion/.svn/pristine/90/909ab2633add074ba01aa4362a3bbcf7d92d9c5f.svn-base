package com.dcits.orion.batch.api;

import com.dcits.orion.batch.api.data.CheckResult;
import com.dcits.galaxy.base.data.BeanResult;

import java.util.List;
import java.util.Map;

/**
 * Created by lixbb on 2015/11/6.
 */
public interface IBatchManage {

    public boolean startBatch(String batchClass);
    public CheckResult startBatchAndCheck(String batchClass);
    public boolean stopBatch(String batchClass);
    public boolean restartBatch(String batchClass);
    public boolean reStartTask(String TASK_ID);
    public boolean reStartTasks(String JOB_ID);
    public boolean stopTask(String TASK_ID);
    public boolean stopTasks(String JOB_ID);
    public boolean skipTask(String TASK_ID);
    public boolean skipTasks(String JOB_ID);
    public Map getRunTask(String TASK_ID);
    public boolean updateRunTask(Map map);
    public Map getJobSplitParam(String JobId );
    public Map getStdJob(String JobId );
    public boolean insertRunTask(Map map);
    public Map getRunJob(Map map);
    public boolean updateRunJob(Map map);
    public boolean saveSplitResult(String JOB_ID,List<Map> tasks);
    public Map getSystem();
    public List getFailTasks(String batchClass);
    public String getErrorMsg(String JOB_ID);
    public void updateRunMsg(String TASK_ID,String msg);
    public BeanResult updateSystemDate(String lastRunDate,String runDate,String nextRunDate);



}
