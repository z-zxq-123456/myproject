package com.zxq.learn.thread.sysn;


/**
 * Created{ by zhouxqh} on 2017/10/9.
 */
@Deprecated
public class AccountTest {
    public static void main(String[] args){
        Account account = new Account();
        account.setName("xiaoming");
//        account.setBalance(1000);
        Runnable cashIn = new CasherIn(account);
        Runnable cashOut = new CasherOut(account);
        Thread r1 = new Thread(cashIn);
        Thread r2 = new Thread(cashOut);
        Thread r3 = new Thread(cashOut);
        r1.start();
        r2.start();
        r3.start();
//        double randmon = Math.random();
//        BigDecimal bf = new BigDecimal(randmon);
//        double ran = bf.setScale(2,BigDecimal.ROUND_FLOOR).doubleValue();
//        System.out.print(ran);
    }
}
