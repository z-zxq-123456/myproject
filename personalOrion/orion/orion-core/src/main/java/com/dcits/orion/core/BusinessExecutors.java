package com.dcits.orion.core;

import com.alibaba.dubbo.common.utils.ConfigUtils;
import com.dcits.galaxy.core.threadpool.Executors;

import java.util.concurrent.ExecutorService;

/**
 * 业务线程池
 * <p/>
 * Created by Tim on 2016/3/1.
 */
public class BusinessExecutors {

    private volatile static BusinessExecutors businessExecutors;

    private ExecutorService executorService;

    private int minThreads;

    private int maxThreads;

    private BusinessExecutors() {
        String minThreadsStr = ConfigUtils.getProperty("galaxy.business.async.minThreads");
        String maxThreadsStr = ConfigUtils.getProperty("galaxy.business.async.maxThreads");
        minThreads = minThreadsStr == null ? 1 : Integer.valueOf(minThreads).intValue();
        maxThreads = maxThreadsStr == null ? 20 : Integer.valueOf(maxThreadsStr).intValue();
        this.executorService = Executors.newCachedThreadPool("business", this.minThreads, this.maxThreads);
    }

    /**
     * volatile + 基于双重检查锁定来实现线程安全的延时初始化
     *
     * @return
     */
    public static BusinessExecutors getInstance() {
        if (null == businessExecutors) {
            synchronized (BusinessExecutors.class) {
                if (null == businessExecutors) {
                    businessExecutors = new BusinessExecutors();
                }
            }
        }
        return businessExecutors;
    }

    public ExecutorService getExecutor() {
        return executorService;
    }
}
