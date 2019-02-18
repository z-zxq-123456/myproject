package com.zxq.learn.thread.comAndProv;

import java.util.Random;

/**
 * Created{ by zhouxqh} on 2017/10/9.
 */
public class Provider implements Runnable{
    private Ball ball = null;

    public Provider(Ball ball) {
        this.ball = ball;
    }

    @Override
    public void run() {
        Random random = new Random();
        for(int i=0;i<15;i++){
            ball.put(new Apple(Thread.currentThread().getName()+"#"+i));
            try {
                Thread.sleep(200);
            }catch (Exception e){}
        }
    }
}
