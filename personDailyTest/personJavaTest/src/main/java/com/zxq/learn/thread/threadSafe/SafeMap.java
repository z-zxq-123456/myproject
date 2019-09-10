package com.zxq.learn.thread.threadSafe;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/3/23
 */
public class SafeMap {

    public static void main(String []args){

        Map<String ,AtomicInteger> map = new ConcurrentHashMap<String, AtomicInteger>();
        CountDownLatch downLatch = new CountDownLatch(2);

        Runnable runnable = ()->{

            AtomicInteger atomicInteger;
          for (int i = 0; i < 5; i++){

              atomicInteger = map.get("a");
              if (atomicInteger == null){
                  AtomicInteger zero = new AtomicInteger(0);
                  atomicInteger = map.putIfAbsent("a",zero);
                  if (atomicInteger == null){
                      atomicInteger = zero;
                  }
              }
              atomicInteger.getAndIncrement();
              System.out.println(Thread.currentThread().getName()+"```1``"+map.get("a"));
          }
            System.out.println(Thread.currentThread().getName()+"```2``"+map.get("a"));
            downLatch.countDown();
        };

        new Thread(runnable).start();
        new Thread(runnable).start();

        try {
            downLatch.await();
            System.out.println(map);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
