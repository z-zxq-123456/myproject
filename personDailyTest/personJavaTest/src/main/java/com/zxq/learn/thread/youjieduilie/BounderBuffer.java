package com.zxq.learn.thread.youjieduilie;

import java.util.concurrent.Semaphore;

/**
 * @Description : 基于信号量的有界缓存
 * @Author :zhouxqh
 * @Date : Create on 2018/3/26
 */
public class BounderBuffer<E>
{

    private final Semaphore availbleItems,availableSpaces;
    private final E[] items;
    private int putPostion = 0, takePosition = 0;

    public BounderBuffer(int capacity) {

        availbleItems = new Semaphore(0);
        availableSpaces = new Semaphore(capacity);
        items = (E[])new Object[capacity];
    }

    public boolean isEmpty(){
        return availbleItems.availablePermits() == 0;
    }

    public boolean isFull(){
        return availableSpaces.availablePermits() == 0;
    }

    public void put(E x) throws InterruptedException{
        availableSpaces.acquire();
        doInsert(x);
        availbleItems.release();
    }

    public E take() throws InterruptedException{
        availbleItems.acquire();
        E x = doExtract();
        availableSpaces.release();
        return x;
    }

    private synchronized void doInsert(E x){
        int i = putPostion;
        items[i] = x;
        putPostion = (++i == items.length)?0:i;
    }

    private synchronized E doExtract(){
        int i = takePosition;
        E x = items[i];
        items[i] = null;
        takePosition = (++i == items.length)?0:i;
        return x;
    }

}
