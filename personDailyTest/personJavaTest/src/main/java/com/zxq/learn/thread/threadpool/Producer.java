package com.zxq.learn.thread.threadpool;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生产者
 * Created{ by zhouxqh} on 2018/2/8.
 */
public class Producer implements Runnable {

    private BlockingQueue queue;
    private static final int SLEEP_TIME = 1000;
    private volatile boolean isRunning = true;
    private static AtomicInteger count = new AtomicInteger();

    public Producer(BlockingQueue queue) {
        this.queue = queue;
    }

    public void stop(){

        this.isRunning = false;
    }

    @Override
    public void run() {
        Random random = new Random();
        System.out.println("start produce thread!");
        String data = null;
    try {
        while (isRunning){
            System.out.println("start create data...");
            Thread.sleep(random.nextInt(SLEEP_TIME));
            data = "data:"+count.incrementAndGet();
            System.out.println("store data "+data+" in quene...");
            if (!queue.offer(data,2, TimeUnit.SECONDS)){
                System.out.println("create data failed!");
            }
         }
       }catch (InterruptedException e){
        e.printStackTrace();
        Thread.currentThread().interrupt();
    }finally {
        System.out.println("quit productor!");
        }
    }
}
