package com.zxq.learn.effectJava.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * volatile修饰的变量 任意线程改变他的值，其他线程都能看的到
 * Created{ by zhouxqh} on 2017/10/27.
 */
public class TestVolatile2 {

//    private volatile static boolean isStoped;
    private  static boolean isStoped;

    public static void main(String[]args){

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (!isStoped){
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
        isStoped = true;
    }
}
