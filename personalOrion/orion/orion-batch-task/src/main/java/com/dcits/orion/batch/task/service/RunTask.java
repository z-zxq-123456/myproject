package com.dcits.orion.batch.task.service;

import com.alibaba.dubbo.common.utils.ConfigUtils;
import com.dcits.orion.batch.api.IRunTask;
import com.dcits.orion.batch.api.ITaskProcess;
import com.dcits.orion.batch.api.bean.ITaskParam;
import com.dcits.orion.batch.task.common.TaskThread;
import com.dcits.orion.batch.task.common.ZkUtils;
import com.dcits.galaxy.core.threadpool.*;
import com.dcits.galaxy.core.threadpool.support.NamedThreadFactory;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

import java.util.concurrent.*;


/**
 * Created by lixbb on 2015/11/5.
 */
@Repository
public class RunTask implements IRunTask,InitializingBean,DisposableBean {
    @Resource
    ITaskProcess taskProcess;

    @Resource
    ITaskProcess timerTaskProcess;

    @Resource
    ZkUtils zkUtils;


    private ThreadPoolExecutor executor;

    private ThreadPoolExecutor timerExecutor;

    private static org.slf4j.Logger logger  = LoggerFactory.getLogger(RunTask.class);;


    public boolean runTask(ITaskParam bean,boolean isLock) {

        boolean ret = false;
        if (getIdleThreadCount() > 0) {
            TaskThread thread = new TaskThread(bean, taskProcess, zkUtils);
            thread.setIsLock(isLock);
            executor.execute(thread);
            ret = true;
        }
        return ret;
    }

    @Override
    public boolean runTask(ITaskParam bean) {

//        boolean ret = false;
//        if (getIdleThreadCount() > 0) {
//            TaskThread thread = new TaskThread(bean, taskProcess, zkUtils);
//            executor.execute(thread);
//            ret = true;
//        }
//        return ret;
        return runTask(bean,false);
    }

    @Override
    public boolean runTimerTask(ITaskParam bean) {
        return runTimerTask(bean,false);
    }


    public boolean runTimerTask(ITaskParam bean,boolean isLock) {
        TaskThread thread = new TaskThread(bean,timerTaskProcess,zkUtils);
        thread.setIsLock(isLock);
        timerExecutor.execute(thread);
        return true;
    }


    public int getThreadPoolSize()
    {
        int threadPoolSize = Runtime.getRuntime().availableProcessors();

        try
        {
            String maxThread =  ConfigUtils.getProperty("galaxy.batch.maxThread");
            if(maxThread!= null && maxThread.length() != 0)
            {
                if(maxThread.indexOf("*")>0)
                {
                    String [] cpu = maxThread.split("\\*");
                    threadPoolSize = (int)(((double) Runtime.getRuntime().availableProcessors()) *Double.parseDouble(cpu[1]));
                    if (logger.isInfoEnabled())
                        logger.info("Task 执行节点最大线程数为CPU核心数*" + cpu[1]+"=" + threadPoolSize);
                }
                else
                {
                    threadPoolSize = Integer.parseInt(maxThread);
                    if (logger.isInfoEnabled())
                        logger.info("Task 执行节点最大线程数为" + threadPoolSize );
                }
            }
            else
            {
                if (logger.isInfoEnabled())
                    logger.info("Task 执行节点最大线程数为CPU核心数=" + threadPoolSize );
            }
        }
        catch (Exception e)
        {
            if (logger.isInfoEnabled())
                logger.info("Task 执行节点最大线程数为CPU核心数=" + threadPoolSize );

        }
        return threadPoolSize;

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        int threadPoolSize = getThreadPoolSize();


        ThreadFactory namedThreadFactory = new NamedThreadFactory("GalaxyBatchTask");
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();
        executor =  new ThreadPoolExecutor(threadPoolSize,threadPoolSize,60, TimeUnit.SECONDS,queue,namedThreadFactory);


        ThreadFactory timerNamedThreadFactory = new NamedThreadFactory("GalaxyBatchTimerTask");
        BlockingQueue<Runnable> timerQueue = new LinkedBlockingQueue<Runnable>();
        timerExecutor = new ThreadPoolExecutor(threadPoolSize*4,threadPoolSize*4,60, TimeUnit.SECONDS,timerQueue,timerNamedThreadFactory);
    }
    public void submit(Callable callable)
    {
        timerExecutor.submit(callable);
    }
    public int getIdleThreadCount()
    {
        return executor.getCorePoolSize() - executor.getActiveCount();
    }

    @Override
    public void destroy() throws Exception {
        if (executor != null)
        {
            executor.shutdown();
            executor = null;
        }
        if (timerExecutor != null)
        {
            timerExecutor.shutdown();
            timerExecutor = null;
        }
    }
}
