package com.dcits.orion.batch.quartz;

import com.dcits.orion.batch.api.IQuartzManager;
import com.dcits.orion.batch.api.bean.ITaskParam;
import com.dcits.orion.batch.common.ZkTools;
import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.galaxy.base.util.ExceptionUtils;

import com.dcits.orion.batch.common.bean.CommTableObj;
import com.dcits.orion.batch.common.bean.TaskParam;
import com.dcits.orion.batch.common.dao.AutoMapperDao;
import org.quartz.*;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

import java.text.ParseException;
import java.util.*;

/**
 * Created by lixbb on 2016/7/26.
 */
@Repository
public class QuartzManager implements IQuartzManager{

    private static Logger logger = LoggerFactory.getLogger(QuartzManager.class);
    @Resource
    Scheduler scheduler;
    @Resource
    ZkTools zkTools;

    @Resource
    AutoMapperDao autoMapperDao;
    @Override
    public void startTimer(ITaskParam taskParam,String cronExpression)  {
       try {

        CronTriggerImpl trigger = (CronTriggerImpl)scheduler.getTrigger(getTriggerKey(taskParam.getJobId(), taskParam.getSystemId()));
        if (trigger == null)
        {
            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.put("zkTools",zkTools);
            jobDataMap.put("taskParam", taskParam);
            JobDetailImpl jobDetail = new JobDetailImpl();
            jobDetail.setKey(getJobKey(taskParam.getJobId(), taskParam.getSystemId()));
            jobDetail.setJobClass(QuartzTask.class);
            jobDetail.setJobDataMap(jobDataMap);
            jobDetail.setDurability(true);
            trigger = new CronTriggerImpl();
            trigger.setCronExpression(cronExpression);
            trigger.setKey(getTriggerKey(taskParam.getJobId(), taskParam.getSystemId()));
            trigger.setJobKey(getJobKey(taskParam.getJobId(), taskParam.getSystemId()));
            scheduler.addJob(jobDetail, true);
            scheduler.scheduleJob(trigger);
            if (logger.isInfoEnabled())
                logger.info("定时任务"+taskParam.getJobId()+"调度启动成功");
        }
       }
       catch (Exception e)
       {
           logger.error(ExceptionUtils.getStackTrace(e));
           throw new GalaxyException(e);
       }
    }

    @Override
    public boolean pauseTimer(String batchClass) {
        CommTableObj timerDef = new CommTableObj("BATCH_TIMER_DEF");
        timerDef.setCondition("PAUSE_BATCH",batchClass);
        timerDef.setCondition("STATUS","Y");
        timerDef.setField("STATUS","S");
        autoMapperDao.update(timerDef);
        timerDef = new CommTableObj("BATCH_TIMER_DEF");
        timerDef.setCondition("PAUSE_BATCH",batchClass);
        List<Map> timers = autoMapperDao.selectList(timerDef);
        int timerCount = 0;
        int notRunTimerCount = 0;
        for(Map timer : timers)
        {
            String group = (String) timer.get("SYSTEM_ID");
            String timerId = (String)timer.get("TIMER_ID");
            killTimer(group,timerId);
           if(!zkTools.existTimerTask(group,timerId))
               notRunTimerCount ++;
            timerCount++;
        }
        if (timerCount == notRunTimerCount)
            return true;
        return false;
    }

    @Override
    public boolean resumeTimer(String batchClass) {
        CommTableObj timerDef = new CommTableObj("BATCH_TIMER_DEF");
        timerDef.setCondition("PAUSE_BATCH",batchClass);
        timerDef.setCondition("STATUS","S");
        List<Map> timers = autoMapperDao.selectList(timerDef);
        startTimer(timers);
        timerDef = new CommTableObj("BATCH_TIMER_DEF");
        timerDef.setCondition("PAUSE_BATCH",batchClass);
        timerDef.setCondition("STATUS","S");
        timerDef.setField("STATUS","Y");
        autoMapperDao.update(timerDef);
        return true;
    }

    public void startTimer(List<Map> timers)
    {
        if (timers == null || timers.isEmpty())
            return;
        for (Map timer : timers)
        {
            ITaskParam taskParam = new TaskParam();
            taskParam.setJobId((String) timer.get("TIMER_ID"));
            taskParam.setGxClassName((String) timer.get("GX_CLASS_NAME"));
            taskParam.setGxMethod((String) timer.get("GX_METHOD"));
            taskParam.setSystemId((String) timer.get("SYSTEM_ID"));
            taskParam.setStaticParam((String) timer.get("STATIC_PARAM"));
            taskParam.setTaskId((String) timer.get("TIMER_ID"));
            taskParam.setJobType("TIMER");
            taskParam.setIsSplit((String) timer.get("IS_SPLIT"));
            taskParam.setRecFlag((String)timer.get("REC_FLAG"));
            startTimer(taskParam,(String)timer.get("CRON_EXPRESSION"));
        }

    }


    private JobKey getJobKey(String jobID,String group)
    {
        return new JobKey(jobID,group);
    }

    private TriggerKey getTriggerKey(String jobID,String group)
    {
        return new TriggerKey(jobID,group);
    }

    //删除一个定时任务
    @Override
    public boolean killTimer(ITaskParam taskParam){
        return killTimer(taskParam.getSystemId(),taskParam.getJobId());
    }
    private boolean killTimer(String group, String timerId)
    {
        JobKey jobKey = getJobKey(timerId,group);
        try {
            if (scheduler.getJobDetail(jobKey) != null) {
                scheduler.deleteJob(jobKey);
                if (logger.isInfoEnabled())
                    logger.info("定时任务"+timerId+"调度停止成功");
            }
            return true;
        } catch (SchedulerException e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return false;
    }
}
