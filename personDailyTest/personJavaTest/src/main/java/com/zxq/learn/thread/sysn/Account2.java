package com.zxq.learn.thread.sysn;

/**
 * Created{ by zhouxqh} on 2017/10/9.
 */
public class Account2 {
    private String name;
    private double balance;

    public double getBalance() {
        return balance;
    }

    public Account2(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }
    public void caherIn(){
        double ran = Math.random()*1000;
        this.balance += 100;
    }
    public void casheOut(){
        double ran = Math.random()*1000;
        this.balance -= 100;
    }
}
