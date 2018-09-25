package com.dcits.orion.batch.task.common;

import com.dcits.galaxy.core.access.ThreadLocalManager;
import com.dcits.orion.batch.api.ITaskProcess;
import com.dcits.orion.batch.api.bean.ITaskParam;
import com.dcits.galaxy.base.GalaxyConstants;
import com.dcits.galaxy.base.util.ExceptionUtils;
import com.dcits.galaxy.core.dubbo.rpc.filter.GalaxyMdcFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * Created by lixbb on 2015/11/5.
 */
public class TaskThread implements Runnable {

    private static Logger logger = LoggerFactory.getLogger(TaskThread.class);
    private ITaskParam taskParam;
    private ITaskProcess taskProcess;
    private boolean isLock;
    ZkUtils utils;
    public TaskThread(ITaskParam param,ITaskProcess taskProcess,ZkUtils utils) {
        this.taskParam = param;
        this.taskProcess = taskProcess;
        this.utils = utils;
    }
    @Override
    public void run() {

        try {
            MDC.put(GalaxyConstants.PLATFORM_ID,taskParam.getTaskId());
            MDC.put(GalaxyConstants.USER_ID,taskParam.getTaskId());
            if (!isLock)
            {
                if ( utils.lockTask(taskParam)) {
                    taskProcess.processTask(taskParam);
                    utils.deleteTask(taskParam);
                }
            }
            else {
                taskProcess.processTask(taskParam);
                utils.deleteTask(taskParam);
            }
        }
        catch (Throwable e)
        {

            if (logger.isErrorEnabled())
                logger.error(ExceptionUtils.getStackTrace(e));
            utils.unlockTask(taskParam);
            utils.noteTask(taskParam.getSystemId());
        }
        finally {
            ThreadLocalManager.remove();
            MDC.remove(GalaxyConstants.PLATFORM_ID);
            MDC.remove(GalaxyConstants.USER_ID);
        }
    }

    public boolean isLock() {
        return isLock;
    }

    public void setIsLock(boolean isLock) {
        this.isLock = isLock;
    }
}
