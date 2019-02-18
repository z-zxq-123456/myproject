package com.zxq.learn.effectJava.concurrent;


/**
 * 增量符++不是原子性：首先读取值，然后增加
 * Created{ by zhouxqh} on 2017/10/27.
 */
public class TestAtomic {

    public static volatile int nextNum = 0;
    public static int getNextNum(){
        return nextNum++;
    }

    public static void main(String[]args){

        MyThread mt = new MyThread();
        Thread thread  = new Thread(mt);
        Thread thread2  = new Thread(mt);
        Thread thread3  = new Thread(mt);

        thread.start();
        thread2.start();
        thread3.start();
    }
}
