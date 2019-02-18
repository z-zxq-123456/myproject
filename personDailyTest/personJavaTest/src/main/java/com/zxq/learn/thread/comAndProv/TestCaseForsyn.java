package com.zxq.learn.thread.comAndProv;


/**
 * Created{ by zhouxqh} on 2017/10/9.
 */
public class TestCaseForsyn {
    public  static  void  main(String[]args){
        Ball ball = new Ball();
        Provider provider = new Provider(ball);
        Consumer consumer = new Consumer(ball);
        Thread thread1 = new Thread(provider,"I");
        Thread thread2 = new Thread(provider,"II");
        Thread thread3 = new Thread(provider,"III");
        Thread thread4 = new Thread(consumer,"IV");
        Thread thread5 = new Thread(consumer,"V");
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

    }
}
