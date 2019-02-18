package com.zxq.learn.thread.comAndProv;


/**
 * Created{ by zhouxqh} on 2017/10/9.
 */
public class Ball {
    private Apple[] basket = new Apple[4];
    private int index = 0;

    public synchronized void put(Apple a){
        while (index == 2){
            try {
                this.wait();
            }catch (Exception e){}
        }
        this.notifyAll();
        basket[index++] = a;
        System.out.println("生产 "+ a + " 剩余; "+ index);
    }
    public synchronized void eat(){
        while (index == 0){
            try {
                this.wait();
            }catch (Exception e){}
        }
        this.notifyAll();
        System.out.println("吃掉 "+ basket[--index] +" 篮子剩余："+index);
    }
}
