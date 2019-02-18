package com.zxq.learn.thread.threadInteraction;

/**
 * Created{ by zhouxqh} on 2018/2/7.
 */
public class Thread1 implements Runnable {

    @Override
    public void run() {

        System.out.println("thread1 is stopping!");
        MainThread1.callback();
        System.out.println("thread1 is stopped");
        try {
            Thread.sleep(500);
        }catch (Exception e){}

    }
}
