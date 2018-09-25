package com.dcits.orion.batch.common;


import com.dcits.orion.batch.common.dao.BatchDao;
import com.dcits.galaxy.base.util.ExceptionUtils;
import com.dcits.galaxy.core.spring.SpringApplicationContext;
import com.dcits.galaxy.dal.mybatis.shard.ShardManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by lixbb on 2015/11/6.
 */
public class ScheduleThread extends AbstractScheduleThread {

    private static Logger logger = LoggerFactory.getLogger(ScheduleThread.class);


    public ScheduleThread(String batchClass, String batchInd,BatchDao batchDao,ZkTools zkTools) {
        super(batchClass, batchInd,batchDao,zkTools);
    }


    @Override
    public void run() {
        if (logger.isInfoEnabled()) {
            logger.info("ScheduleThread：" + batchClass + "启动！batchInd = " + batchInd);
        }

        Thread.currentThread().setName("ScheduleThread-" + batchClass);


        while (true) {
            try {
                if (!isNeedToRun()) {
                    if (logger.isInfoEnabled()) {
                        logger.info("ScheduleThread：" + batchClass + "终止！batchInd = " + batchInd);
                    }

                    return;
                }

                updateRunningJobs();
                dealToRunJobs();
                if (isBatchFinish()) {
                    if (logger.isInfoEnabled()) {
                        logger.info("批处理：" + batchClass + "完成！");
                    }
                    return;
                }
                sleep(500);
            } catch (Exception e) {
                if (logger.isErrorEnabled()) {
                    logger.error(ExceptionUtils.getStackTrace(e));
                }
            }
        }

    }



    private boolean isBatchFinish() {
        boolean ret = false;
        long noFinishJobCnt = batchDao.getNoFinishJobCnt(batchClass);
        if (noFinishJobCnt == 0) {
            Map BATCH_STATUS = batchDao.getBatchStatus(batchClass);
            BATCH_STATUS.put("BATCH_STATUS", "C");
            BATCH_STATUS.put("END_TIME", BatchUtils.getCurTime());
            batchDao.updateBatchStatus(BATCH_STATUS);
            try
            {
                zkTools.unlockBatch(batchClass);
            }
            catch (Exception e)
            {
                if (logger.isErrorEnabled()) {
                    logger.error(ExceptionUtils.getStackTrace(e));
                }
            }
            ret = true;
        }
        return ret;
    }


}
