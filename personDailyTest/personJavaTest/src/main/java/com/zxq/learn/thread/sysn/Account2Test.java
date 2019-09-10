package com.zxq.learn.thread.sysn;

/**
 * Created{ by zhouxqh} on 2017/10/9.
 */
public class Account2Test {
    public static  void main(String[] args){
        Account2 account2 = new Account2("xiaoming",2000);
        Casher casher = new Casher(account2);
        Thread r1 = new Thread(casher);
        r1.start();
        Thread r2 = new Thread(casher);
        r2.start();
    }
}
