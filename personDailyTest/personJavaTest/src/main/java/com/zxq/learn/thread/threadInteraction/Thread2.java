package com.zxq.learn.thread.threadInteraction;

/**
 * Created{ by zhouxqh} on 2018/2/7.
 */
public class Thread2 implements Runnable {

    @Override
    public void run() {

        System.out.println("thread2 is stopping!");
        MainThread1.callback();
        System.out.println("thread2 is stopped");
        try {
            Thread.sleep(500);
        }catch (Exception e){}
    }
}
