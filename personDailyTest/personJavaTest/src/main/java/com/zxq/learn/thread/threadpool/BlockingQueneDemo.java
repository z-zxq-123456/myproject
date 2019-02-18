package com.zxq.learn.thread.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 线程池与threadPool
 * Created{ by zhouxqh} on 2018/2/8.
 */
public class BlockingQueneDemo {

    public static void main(String[]args){

       try {
           BlockingQueue queue = new LinkedBlockingDeque(8);
           Producer producer1 = new Producer(queue);
           Producer producer2 = new Producer(queue);
           Producer producer3 = new Producer(queue);
           Consumer consumer = new Consumer(queue);

           ExecutorService service = Executors.newCachedThreadPool();
           service.execute(producer1);
           service.execute(producer2);
           service.execute(producer3);
           service.execute(consumer);

           // 执行10s
           Thread.sleep(10 * 1000L);

           producer1.stop();
           producer2.stop();
           producer3.stop();

           // 退出Executor
           service.shutdown();
       }catch (Exception e){
           e.printStackTrace();
       }
    }
}
