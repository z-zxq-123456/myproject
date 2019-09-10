package com.zxq.learn.thread.sysn;

/**
 * Created{ by zhouxqh} on 2017/10/9.
 */
public class WaitAndNotify {

    class TestWait extends Thread{
        public void run(){
            test("wait-thread");
        }
    }
    class TestNotify extends  Thread{
        public void run(){
            test("notify-thread");
        }
    }

    public synchronized  void test(String str){
        for (int i=0;i<6;i++){
            if(i == 3){
                try {
                    this.wait();
                }catch (Exception e){}
            }else {
                this.notify();
            }
            System.out.println(str+"------------"+i);
        }
    }
    public void exec(){
        TestNotify testNotify = new TestNotify();
        TestWait testWait = new TestWait();
        testNotify.start();
        testWait.start();
    }
    public static void main(String[]args){
        new WaitAndNotify().exec();
    }
}
