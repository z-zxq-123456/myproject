package com.zxq.learn.thread.violate;

/**
 * Created{ by zhouxqh} on 2017/10/10.
 */
public class NumIncrement {

    public volatile int inc = 0;

    public NumIncrement(int inc) {
        this.inc = inc;
    }
    public void increment(){
        inc++;
    }
}
