package com.zxq.learn.thread.reentrantLock;


/**
 * Created{ by zhouxqh} on 2017/10/10.
 */
public class Test {
    public static void main(String[]args){
       Job job = new Job(new PrintQuene());
        Thread[] thread = new Thread[10];
        for(int i=0;i<10;i++){
            thread[i] = new Thread(job,"Thread"+i);
        }
        for (int i=0;i<10;i++){
            thread[i].start();
        }
    }
}
