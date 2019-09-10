package com.zxq.learn.thread.threadpool;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 消费者
 * Created{ by zhouxqh} on 2018/2/8.
 */
public class Consumer implements Runnable {

    private BlockingQueue queue;

    private volatile boolean isRunning = true;

    private static final int SLEEP_TIME = 1000;

    public Consumer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        Random random = new Random();
        System.out.println("start consumer thread!");
        try {
            while (isRunning){
                System.out.println("start consumer data...");
                String data = (String) queue.poll(2,TimeUnit.SECONDS);
              if (data != null){
                  System.out.println("gained：" + data);
                  Thread.sleep(random.nextInt(SLEEP_TIME));
              }else {
                  isRunning = false;
              }
            }
        }catch (InterruptedException e){
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }finally {
            System.out.println("quit consumer!");
        }
    }
}
