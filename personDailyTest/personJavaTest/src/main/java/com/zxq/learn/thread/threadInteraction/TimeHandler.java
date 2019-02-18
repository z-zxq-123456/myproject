package com.zxq.learn.thread.threadInteraction;

import java.lang.Process;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author zxq
 * Created{ by zhouxqh} on 2018/2/1.
 */
public class TimeHandler implements Runnable {

    private Process process;

    public TimeHandler(Process process) {
        this.process = process;
    }

    @Override
    public void run() {

      int count = 0;
      while(count < 5){

          count ++ ;
          String current = (new Date()).toString();
          if (process != null){
              System.out.println("observer gain current time");
//              process.process(current);
          }else {
              System.out.println("observer gain current time failed!");
          }
      }
        try {
            // 线程睡眠
            TimeUnit.MILLISECONDS.sleep(3000);
        } catch (InterruptedException e) {
             e.printStackTrace();
        }
    }
}
