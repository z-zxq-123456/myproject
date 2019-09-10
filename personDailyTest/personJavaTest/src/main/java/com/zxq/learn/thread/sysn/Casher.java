package com.zxq.learn.thread.sysn;

/**
 * Created{ by zhouxqh} on 2017/10/9.
 */
public class Casher implements Runnable {
    private Account2 account2;

    public Casher(Account2 account2) {
        this.account2 = account2;
    }

    @Override
    public void run() {
//        synchronized (Account2.class){
            account2.caherIn();
            System.out.println(Thread.currentThread().getName()+"checkInend");
            account2.casheOut();
            System.out.println(Thread.currentThread().getName()+"checkOutend");
            System.out.println(Thread.currentThread().getName() + ":" + account2.getBalance());
//        }
    }
}
