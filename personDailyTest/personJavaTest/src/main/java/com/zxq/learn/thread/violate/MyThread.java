package com.zxq.learn.thread.violate;


/**
 * Created{ by zhouxqh} on 2017/10/10.
 */
public class MyThread implements Runnable {
    private NumIncrement numIncrement;

    public MyThread(NumIncrement numIncrement) {
        this.numIncrement = numIncrement;
    }
    @Override
    public void run() {

        System.out.println(Thread.currentThread().getName()+" begin");
        try {
            for(int i = 0; i < 10; i++){
                System.out.println(Thread.currentThread().getName()+" processing"+  i);
                numIncrement.increment();
                Thread.sleep(100);
            }
        }catch (Exception e){}
        System.out.println(Thread.currentThread().getName()+" end");
    }
}
