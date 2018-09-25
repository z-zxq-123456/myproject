package com.dcits.orion.batch.task.common;

import com.dcits.galaxy.core.access.ThreadLocalManager;
import com.dcits.orion.batch.api.IBatchLocal;
import com.dcits.orion.batch.api.ITaskProcess;
import com.dcits.orion.batch.api.bean.ITaskParam;
import com.dcits.orion.batch.common.BatchUtils;
import com.dcits.orion.batch.common.bean.CommTableObj;
import com.dcits.orion.batch.common.dao.AutoMapperDao;
import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.galaxy.base.util.ExceptionUtils;
import com.dcits.galaxy.base.util.ObjectUtils;
import com.dcits.galaxy.base.util.StringUtils;
import com.dcits.galaxy.core.spring.SpringApplicationContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by lixbb on 2016/7/26.
 */

@Repository
public class TimerTaskProcess implements ITaskProcess {

    @Resource
    AutoMapperDao autoMapperDao;

    @Resource
    TimerTaskProcess timerTaskProcess;

    @Resource
    IBatchLocal batchLocal;

    private static Logger logger = LoggerFactory.getLogger(TaskThread.class);

    @Override
    @Transactional
    public void processTask(ITaskParam taskParam) {

        long start = System.currentTimeMillis();
        String TIME_EXE_IND = BatchUtils.getTaskInd();
        String ERROR_DESC = "";
        String EXE_RESULT = "S";
        if ("Y".equals(taskParam.getRecFlag()))
            timerTaskProcess.insertTimerRec(taskParam, TIME_EXE_IND);
        try {
            Object o = SpringApplicationContext.getContext().getBean(Class.forName(taskParam.getGxClassName()));
            Method m = ObjectUtils.getMethod(o, taskParam.getGxMethod(), ITaskParam.class);
            m.invoke(o, taskParam);
        } catch (Exception e) {
            if (e instanceof InvocationTargetException) {
                InvocationTargetException ie = (InvocationTargetException) e;
                Throwable exception = ie.getTargetException();
                if (exception != null) {
                    ERROR_DESC = ExceptionUtils.getStackTrace(exception);
                }

            } else {
                ERROR_DESC = ExceptionUtils.getStackTrace(e);
            }
            logger.error(ERROR_DESC);
            ERROR_DESC = StringUtils.byteSubString(ERROR_DESC, 3999);
            EXE_RESULT = "F";
        } finally {
                ThreadLocalManager.remove();
        }
        long TIME_ELAPSED = System.currentTimeMillis() - start;
        if ("Y".equals(taskParam.getRecFlag()))
            timerTaskProcess.updateTimerRec(TIME_EXE_IND, EXE_RESULT, ERROR_DESC, TIME_ELAPSED);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertTimerRec(ITaskParam taskParam, String TIME_EXE_IND) {
        CommTableObj insert = new CommTableObj("BATCH_TIMER_REC");
        insert.setField("TIME_EXE_IND", TIME_EXE_IND);
        insert.setField("TIMER_ID", taskParam.getTaskId());
        insert.setField("STATIC_PARAM", taskParam.getStaticParam());
        insert.setField("SYSTEM_ID", taskParam.getSystemId());
        insert.setField("GX_CLASS_NAME", taskParam.getGxClassName());
        insert.setField("GX_METHOD", taskParam.getGxMethod());
        insert.setField("EXE_RESULT", "R");
        insert.setField("NODE_IP", BatchUtils.getLocalIP());
        insert.setField("START_TIME", BatchUtils.getCurTime());
        autoMapperDao.insert(insert);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateTimerRec(String TIME_EXE_IND, String EXE_RESULT, String ERROR_DESC, long TIME_ELAPSED) {
        CommTableObj update = new CommTableObj("BATCH_TIMER_REC");

        update.setField("EXE_RESULT", EXE_RESULT);
        update.setField("ERROR_DESC", ERROR_DESC);
        update.setField("END_TIME", BatchUtils.getCurTime());
        update.setField("TIME_ELAPSED", TIME_ELAPSED);
        update.setCondition("TIME_EXE_IND", TIME_EXE_IND);
        autoMapperDao.update(update);
    }
}
