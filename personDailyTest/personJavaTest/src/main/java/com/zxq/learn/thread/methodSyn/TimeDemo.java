package com.zxq.learn.thread.methodSyn;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimeDemo {

	/**�������
	 * @param args
	 */
	public static void main(String[] args) {
			Timer time = new Timer();
			time.schedule(new TimerTask() {
				@Override
				public void run() {
						System.out.println("soooo");					
				}
			},new Date(System.currentTimeMillis()+1000),2000);
				
	}
}


