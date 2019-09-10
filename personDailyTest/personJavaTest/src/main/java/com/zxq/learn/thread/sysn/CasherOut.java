package com.zxq.learn.thread.sysn;


import java.math.BigDecimal;

/**
 * Created{ by zhouxqh} on 2017/10/9.
 * @class 取钱
 */
@Deprecated
public class CasherOut implements Runnable{
    Account account;

    public CasherOut(Account account) {
        this.account = account;
    }

    @Override
    public  void run() {
        while(true){
            synchronized (account){
                double ran = Math.random()*1000;
                BigDecimal bf= new BigDecimal(ran);
                double randmon = bf.setScale(2,BigDecimal.ROUND_FLOOR).doubleValue();
                double money = account.getBalance();
                BigDecimal bf2= new BigDecimal(money);
                double balance = bf2.setScale(2,BigDecimal.ROUND_FLOOR).doubleValue();
                if(balance < randmon){
                    try {
                        account.wait();
                    }catch (Exception e){}
                }
                account.setBalance(balance-randmon);
                try {
                    Thread.sleep(1000);
                }catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println("账户余额："+balance+" 取出："+randmon+" 取出后余额："+account.getBalance());
            }
        }
    }
}
