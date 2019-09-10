package com.zxq.learn.thread.threadInteraction;


import java.lang.Process;

/**
 * Created{ by zhouxqh} on 2018/2/6.
 */
public class MainThreadDemo {

    public static void main(String[]args){

//        Process process = (msg)-> System.out.println("recevied msg: "+ msg);
        Process process = null;
        TimeHandler timeHandler = new TimeHandler(process);
        Thread t = new Thread(timeHandler);
        t.start();
    }
}
