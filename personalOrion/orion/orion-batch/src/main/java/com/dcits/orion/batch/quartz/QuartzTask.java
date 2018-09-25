package com.dcits.orion.batch.quartz;



import com.dcits.orion.batch.api.bean.ITaskParam;
import com.dcits.orion.batch.common.BatchUtils;
import com.dcits.orion.batch.common.ZkTools;
import com.dcits.galaxy.base.util.ExceptionUtils;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean.StatefulMethodInvokingJob;
/**
 * Created by lixbb on 2016/7/26.
 */
public class QuartzTask extends StatefulMethodInvokingJob {

    private static Logger logger = LoggerFactory.getLogger(QuartzTask.class);
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException
    {

        try
        {
            ITaskParam taskParam = (ITaskParam)context.getJobDetail().getJobDataMap().get("taskParam");
            ZkTools zkTools = (ZkTools)context.getJobDetail().getJobDataMap().get("zkTools");
            taskParam.setSendGroup(zkTools.getGroup());
            boolean ret = zkTools.rpcTimerRunTask(taskParam);
            if (ret)
                logger.info("定时任务：" + taskParam.getJobId() + "已触发，触发时间：" + BatchUtils.getCurTime());
            else zkTools.noteTask(taskParam.getSystemId());
        }
        catch (Exception e)
        {
            logger.error(ExceptionUtils.getStackTrace(e));
        }

    }
}
