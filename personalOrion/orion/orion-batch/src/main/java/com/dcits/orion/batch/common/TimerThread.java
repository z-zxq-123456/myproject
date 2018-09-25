package com.dcits.orion.batch.common;

import com.dcits.orion.api.IZkThread;
import com.dcits.orion.batch.quartz.TimerStart;
import com.dcits.galaxy.core.spring.SpringApplicationContext;

/**
 * Created by lixbb on 2016/7/26.
 */
public class TimerThread implements IZkThread {

    long sleep = 1000;
    @Override
    public void setArgs(String[] args) {

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

        TimerStart timerStart = SpringApplicationContext.getContext().getBean(TimerStart.class);
        timerStart.initTimer();


    }
}
