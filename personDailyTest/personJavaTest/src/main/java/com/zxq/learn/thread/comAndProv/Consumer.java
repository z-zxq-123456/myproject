package com.zxq.learn.thread.comAndProv;


import java.util.Random;

/**
 * Created{ by zhouxqh} on 2017/10/9.
 */
public class Consumer implements Runnable{
    private Ball ball = null;

    public Consumer(Ball ball) {
        this.ball = ball;
    }

    @Override
    public void run() {
        Random random = new Random();
        for(;;){
            ball.eat();
            try {
                Thread.sleep(10);
            }catch (Exception e){}
        }
    }
}
