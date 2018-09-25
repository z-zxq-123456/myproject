package com.dcits.orion.batch.common;

import com.dcits.orion.batch.common.dao.BatchDao;
import com.dcits.orion.schedule.common.JobContextRequest;
import com.dcits.orion.schedule.context.JobContext;
import com.dcits.galaxy.base.util.ExceptionUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by lixbb on 2015/11/17.
 */
public class ListennerThread extends AbstractScheduleThread {


    private static Logger logger = LoggerFactory.getLogger(ListennerThread.class);

    public ListennerThread(String batchClass, String batchInd,BatchDao batchDao,ZkTools zkTools) {
        super(batchClass, batchInd,batchDao,zkTools);
    }

    public void run() {
        if (logger.isInfoEnabled()) {
            logger.info("ListennerThread：" + batchClass + "启动！batchInd = " + batchInd);
        }
        Thread.currentThread().setName("ListennerThread-" + batchClass);

        JobContextRequest jcr = new JobContextRequest();
        while (true)
        {
            if (!isNeedToRun())
            {
                if (logger.isInfoEnabled()) {
                    logger.info("ListennerThread：" + batchClass + "终止！batchInd = "  + batchInd);
                }
                return;
            }

            List tasks = batchDao.getRunningTasks(batchClass);
            if (tasks != null && tasks.size() > 0)
            {
                Iterator it = tasks.iterator();
                while (it.hasNext())
                {
                    Map task = (Map) it.next();
                    String appID = (String) task.get("APP_ID");
                    JobContext ac = null;
                    try
                    {
                        ac = jcr.getJobContext(appID);
                    }
                    catch (Exception e)
                    {
                        if (logger.isWarnEnabled())
                        {
                            logger.warn(ExceptionUtils.getStackTrace(e));
                        }
                    }

                    if (logger.isInfoEnabled())
                    {
                        logger.info("ac====="+ ac);
                    }
                    if (ac != null && null != ac.getState())
                    {

                        if (logger.isInfoEnabled())
                        {
                            logger.info("ac.getState()="+ ac.getState());
                        }
                        switch (ac.getState())
                        {
                            case "FINISHED":
                                switch (ac.getFinalStatus()) {
                                    case "SUCCEEDED":
                                        task.put("TASK_STATUS", "S");
                                        break;
                                    case "FAILED":
                                        task.put("TASK_STATUS", "F");
                                        if (ac.getException() != null && ac.getException().trim().length()>0)
                                        {
                                            String exception = ac.getException().trim();
                                            if (exception.length() > 4000)
                                            {
                                                exception = exception.substring(1,3999);
                                            }
                                            task.put("ERROR_DESC",exception);
                                        }
                                        else
                                        {
                                            task.put("ERROR_DESC", "FAILED");
                                        }

                                        break;
                                    case "KILLED":
                                        task.put("TASK_STATUS", "F");
                                        task.put("ERROR_DESC", "KILLED");
                                        break;
                                }
                                break;
                            case "FAILED":
                                task.put("TASK_STATUS", "F");
                                if (ac.getException() != null && ac.getException().trim().length()>0)
                                {
                                    String exception = ac.getException().trim();
                                    if (exception.length() > 5000)
                                    {
                                        exception = exception.substring(0,4999);
                                    }
                                    task.put("ERROR_DESC",exception);
                                }
                                else
                                {
                                    task.put("ERROR_DESC", "FAILED");
                                }
                                break;
                            default:
                                Map runJob = batchDao.getRunJob(task);
                                if (Integer.parseInt(runJob.get("SPLIT_CNT").toString())==1)
                                {
                                    runJob.put("PERCENT",ac.getProgress()+"%");
                                    batchDao.updateRunJob(runJob);
                                }
                                break;

                        }
                    }
                    if(!"R".equals(task.get("TASK_STATUS")))
                    {
                        if ("F".equals(task.get("TASK_STATUS")))
                        {
                            Map stdJob = batchDao.getStdJob((String)task.get("JOB_ID"));
                            if ("A".equals(stdJob.get("IS_SKIP")))
                            {
                                task.put("TASK_STATUS","A");
                            }
                        }
                        task.put("END_TIME",BatchUtils.getCurTime());
                        batchDao.updateRunTask(task);
                    }
                }
            }
            sleep(1500);
        }
    }
}
