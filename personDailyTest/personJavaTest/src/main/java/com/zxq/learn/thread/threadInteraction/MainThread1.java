package com.zxq.learn.thread.threadInteraction;


/**
 * Created{ by zhouxqh} on 2018/2/7.
 */
public class MainThread1 {

    static boolean flag = false;

    public static void main(String []args){

        new Thread(new Thread1()).start();
        new Thread(new Thread1()).start();
        new Thread(new Thread1()).start();
        new Thread(new Thread2()).start();

        while(!flag);

        try {
            Thread.sleep(400);
        }catch (Exception e){}

        System.out.println("mainThread is stopped");
    }

    public static void callback(){
        flag = true;
    }
}
