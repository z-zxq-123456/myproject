package com.zxq.learn.thread.threadSafe;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/3/23
 */
public class UnSafeMap {

    public static void main(String [] agrs){

        Map<String ,Integer> map = new ConcurrentHashMap<String, Integer>();
        CountDownLatch downLatch = new CountDownLatch(2);

        /*Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++){
                    Integer value = (Integer) map.get("a");
                    if (value == null){
                        map.put("a",1);
                    }else {
                        map.put("a",value+1);
                    }
                }
                downLatch.countDown();
            }
        };*/

        Runnable runnable1 = ()->{

            for (int i = 0; i < 5; i++){
                Integer value = map.get("a");
                if (value == null){
                    map.put("a",1);
                }else {
                    map.put("a",value+1);
                }
                System.out.println(Thread.currentThread().getName()+"```1``"+map.get("a"));
            }
            System.out.println(Thread.currentThread().getName()+"```2``"+map.get("a"));
            downLatch.countDown();
        };

        new Thread(runnable1).start();
        new Thread(runnable1).start();

        try {
            downLatch.await();
            System.out.println(map);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
