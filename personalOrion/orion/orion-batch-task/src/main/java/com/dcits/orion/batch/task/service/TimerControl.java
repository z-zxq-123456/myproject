package com.dcits.orion.batch.task.service;

import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.galaxy.base.util.StringUtils;
import com.dcits.orion.batch.api.IQuartzManager;
import com.dcits.orion.batch.api.bean.ITaskParam;
import com.dcits.orion.batch.common.BatchUtils;
import com.dcits.orion.batch.task.common.ZkUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by lixiaobin on 2017/4/13.
 */
@Repository
public class TimerControl {

    @Resource
    ZkUtils zkUtils;
    public void pauseTimer(ITaskParam taskParam)
    {
        boolean closed = false;
        while (!closed)
        {
            IQuartzManager iQuartzManager = zkUtils.getIQuartzManager(taskParam.getSendGroup());
            if (iQuartzManager != null)
            {
                closed = iQuartzManager.pauseTimer(taskParam.getBatchClass());
            }
            else {
                throw new GalaxyException("定时任务调度未启动，无法关闭定时任务");
            }
            if(closed == false)
            {
                long sleep = 1000;
                String staticParam = taskParam.getStaticParam();
                if (!StringUtils.isBlank(staticParam))
                {
                    try {
                        sleep = Long.parseLong(staticParam)*1000;
                    }
                    catch (Exception e)
                    {

                    }
                    try {
                        Thread.sleep(sleep);
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            }
        }

    }
    public void resumeTimer(ITaskParam taskParam)
    {
        IQuartzManager iQuartzManager = zkUtils.getIQuartzManager(taskParam.getSendGroup());
        if (iQuartzManager != null)
        {
            iQuartzManager.resumeTimer(taskParam.getBatchClass());
        }
        else {
            throw new GalaxyException("定时任务调度未启动，无法启动定时任务");
        }
    }
}
