package com.zxq.learn.effectJava.concurrent;


import java.util.concurrent.TimeUnit;

/**
 * 线程访问同步的数据
 * Created{ by zhouxqh} on 2017/10/27.
 */
public class TestVolatile {

    private static boolean stopedRequest;

    private static synchronized void requestStop(){
        stopedRequest = true;
    }

    private static synchronized boolean isStopedRequest(){
        return stopedRequest;
    }

    public static void main(String[]args){

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (!isStopedRequest()){
                    i++;
                    System.out.println("thread: " + i);
                    try {
                        Thread.sleep(500);
                    }catch (Exception e){}
                }
            }
        });
        thread.start();
        System.out.println("main: ...");
        try {
            TimeUnit.SECONDS.sleep(1);
        }catch (Exception e){}
        requestStop();
    }
}
