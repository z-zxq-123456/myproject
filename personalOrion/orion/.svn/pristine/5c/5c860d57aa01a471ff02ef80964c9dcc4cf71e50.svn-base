package com.dcits.galaxy.orion;

import com.dcits.orion.api.IZkThread;

/**
 * Created by lixbb on 2016/2/17.
 */
public class TestThread implements IZkThread {
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
        while (true)
        {
            System.out.println("线程执行中。。。");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setArgs(String[] args) {

    }
}
