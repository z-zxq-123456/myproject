package com.zxq.learn.thread.counter;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/4/10
 */
public class CasImpl implements Counter {

    private AtomicInteger seed;
    public CasImpl(int seed) {
        this.seed = new AtomicInteger(seed);
    }

    @Override
    public int nextInt(int n) {
       while(true){
           int s = seed.get();
           int next = calcute(s);
           if (seed.compareAndSet(s,next)){
               int remaining = s % n;
               return remaining > 0 ? remaining : remaining + n;
           }
       }
    }

    private int calcute(int seed){
        return 0;
    }
}
