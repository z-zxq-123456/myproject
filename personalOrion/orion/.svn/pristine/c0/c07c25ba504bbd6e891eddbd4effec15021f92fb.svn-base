package com.dcits.orion.batch.common;

import com.dcits.orion.batch.common.dao.BatchDao;
import com.dcits.galaxy.base.util.ExceptionUtils;
import com.dcits.galaxy.core.spring.SpringApplicationContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lixbb on 2015/12/11.
 */
public class DispatchTaskThread extends AbstractScheduleThread  {


    private static Logger logger = LoggerFactory.getLogger(DispatchTaskThread.class);

    private int dealCount = 200;
    public DispatchTaskThread(String batchClass, String batchInd,BatchDao batchDao,ZkTools zkTools) {
        super(batchClass, batchInd,batchDao,zkTools);
    }
    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p/>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {

        if (logger.isInfoEnabled()) {
            logger.info("DispatchTaskThread：" + batchClass + "启动！batchInd = " + batchInd);
        }
        Thread.currentThread().setName("DispatchTaskThread-" + batchClass);

        while (true) {
            int realCount = 0;
            try {
                if (!isNeedToRun()) {
                    if (logger.isInfoEnabled()) {
                        logger.info("DispatchTaskThread：" + batchClass + "终止！batchInd = " + batchInd);
                    }
                    return;
                }
                realCount = dealToRunTask(dealCount);
            } catch (Exception e) {
                if (logger.isErrorEnabled()) {
                    logger.error(ExceptionUtils.getStackTrace(e));
                }
            }
            if (realCount!=dealCount)
                sleep(500);
        }

    }
}
