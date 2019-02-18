package com.zxq.learn.effectJava.concurrent;
import java.util.concurrent.TimeUnit;

/**
 * Created{ by zhouxqh} on 2017/10/27.
 */
public class MyThread implements Runnable {

    @Override
    public void run() {

        System.out.println("Before:Thead: " + Thread.currentThread().getName()+" Num: = "+ TestAtomic.nextNum);
//        System.out.println("Before:Thead: " + Thread.currentThread().getName()+" Num: = "+ TestAtomic2.nextNum);
        int num = TestAtomic.getNextNum();
//        int num = TestAtomic2.getNum();
        System.out.println("After:Thead: " + Thread.currentThread().getName()+" getNum: = "+ num);
        try {
            TimeUnit.SECONDS.sleep(1);
        }catch (Exception e){}
    }
}
