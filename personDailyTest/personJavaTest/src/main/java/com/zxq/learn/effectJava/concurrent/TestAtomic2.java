package com.zxq.learn.effectJava.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 改进TestAtomic
 * Created{ by zhouxqh} on 2017/10/27.
 */
public class TestAtomic2 {

    public static final AtomicInteger nextNum = new AtomicInteger();
    public static int getNum(){
        return nextNum.getAndIncrement();
    }
    public static void main(String[]args){

      MyThread mt = new MyThread();
        Thread thread  = new Thread(mt);
        Thread thread2  = new Thread(mt);
        Thread thread3  = new Thread(mt);
        try {
            TimeUnit.SECONDS.sleep(1);
        }catch (Exception e){}
        thread.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        }catch (Exception e){}
        thread2.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        }catch (Exception e){}
        thread3.start();
    }
}
