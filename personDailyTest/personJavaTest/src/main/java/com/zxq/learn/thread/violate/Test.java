package com.zxq.learn.thread.violate;

/**
 * Created{ by zhouxqh} on 2017/10/10.
 */
public class Test {
    public static void main(String[]args){

//        List list =  new ArrayList();
//        List list =  new LinkedList(){};
//        list.add("a");
//        list.add("c");
//        list.add("d");
//        list.add("b");
//        System.out.println(list.get(1));
//
//        NumIncrement numIncrement = new NumIncrement(2);
//        MyThread thread  = new MyThread(numIncrement);
//
//        try {
//            for(int i = 0; i<5; i++){
//                new Thread(thread,"Thread"+i).start();
//            }
//            Thread.sleep(200);
//
//            while(Thread.activeCount()>1) {
//                Thread.yield();
//            } //保证前面的线程都执行完
//
//            System.out.println("inc = :"+ numIncrement.inc);
//        }catch (Exception e){}

        System.out.println("1245".contains("1"));
    }
}
