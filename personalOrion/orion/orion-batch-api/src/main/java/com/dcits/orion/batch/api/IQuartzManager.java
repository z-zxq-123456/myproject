package com.dcits.orion.batch.api;

import com.dcits.orion.batch.api.bean.ITaskParam;

/**
 * Created by qiqingshan on 2016/10/25.
 */
public interface IQuartzManager {

    public boolean killTimer(ITaskParam taskParam);

    public void startTimer(ITaskParam taskParam,String cronExpression);

    boolean pauseTimer(String batchClass);
    boolean resumeTimer(String batchClass);

}
