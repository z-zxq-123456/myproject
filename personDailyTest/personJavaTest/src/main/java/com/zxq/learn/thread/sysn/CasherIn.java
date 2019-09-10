package com.zxq.learn.thread.sysn;


import java.math.BigDecimal;

/**
 * Created{ by zhouxqh} on 2017/10/9.
 * @class 存款
 */
@Deprecated
public class CasherIn implements Runnable {
    Account account;

    public CasherIn(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        while(true){
            synchronized (account){
                double ran = Math.random()*1000;
                BigDecimal bf= new BigDecimal(ran);
                BigDecimal bf2 = new BigDecimal(account.getBalance());
                double randmon = bf.setScale(2,BigDecimal.ROUND_FLOOR).doubleValue();
                double balance = bf2.setScale(2,BigDecimal.ROUND_FLOOR).doubleValue();
                this.account.setBalance(balance+randmon);
                System.out.println("账户余额："+balance+" 存入："+randmon+" 存入后余额："+account.getBalance());
                account.notifyAll();
                try {
                    Thread.sleep(1000);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
          }
      }
}
