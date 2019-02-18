package com.zxq.learn.thread.reentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created{ by zhouxqh} on 2017/10/10.
 */
public class PrintQuene {
    private final Lock lock = new ReentrantLock();
//    public synchronized void printJob(Object document){
    public  void printJob(Object document){
        lock.lock();
        System.out.println(Thread.currentThread().getName()+":PrintQuene:printing a job during 1000 seconds");
        try {
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
