package com.zxq.learn.thread.counter;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/4/10
 */
public class ReentrantLockImpl implements Counter {

    private ReentrantLock lock = new ReentrantLock();
    private int seed;

    public ReentrantLockImpl(int seed) {
        this.seed = seed;
    }

    @Override
    public int nextInt(int n) {
        lock.lock();
        try{
            int s = seed;
            seed = calcute(s);
            int remaining = s % n;
            return remaining > 0 ? remaining : remaining + n;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.lock();
        }
        return 0;
    }

    private int calcute(int seed){
        return 0;
    }
}
