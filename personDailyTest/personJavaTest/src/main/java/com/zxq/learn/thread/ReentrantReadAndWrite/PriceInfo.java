package com.zxq.learn.thread.ReentrantReadAndWrite;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created{ by zhouxqh} on 2017/10/10.
 */
public class PriceInfo {
    private double price1;
    private double price2;
    private ReadWriteLock readWriteLock;

    public PriceInfo() {
        this.price1 = 1;
        this.price2 = 2;
        this.readWriteLock = new ReentrantReadWriteLock();
    }
    public synchronized double getPrice1(){
//        readWriteLock.readLock().lock();
        double value = this.price1;
//        readWriteLock.readLock().unlock();
        return value;
    }
    public synchronized double getPrice2(){
//        readWriteLock.readLock().lock();
        double value = this.price2;
//        readWriteLock.readLock().unlock();
        return value;
    }
    public synchronized void setPrices(double price1,double price2){
//        readWriteLock.writeLock().lock();
        this.price1 = price1;
        this.price2 = price2;
//        readWriteLock.writeLock().unlock();
    }
}
