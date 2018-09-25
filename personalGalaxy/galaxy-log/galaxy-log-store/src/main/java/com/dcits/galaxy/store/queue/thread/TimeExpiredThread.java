package com.dcits.galaxy.store.queue.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.dcits.galaxy.logcover.common.DataUtil;
import com.dcits.galaxy.logcover.config.LogConfCache;
import com.dcits.galaxy.logcover.queue.LogLock;
import com.dcits.galaxy.logcover.queue.PlatLogQueue;

/**
 * 定期执行判断是否队列中有超过指定时间的记录，若有则启动入库线程
 * <p>Created on 2017/01/06.</p>
 *
 * @author tangxlf <tangxlf@dcits.com> 
 * @since 1.7
 */
public class TimeExpiredThread extends Thread{
	
	private  final Logger log = LoggerFactory.getLogger(this.getClass());	
	
	public void run(){			
		 while(true){			 
			 try {
				handlerExpiredQueue();
				Thread.sleep(LogConfCache.getConfigInfo().getExpiredTime());
			} catch (InterruptedException e) {
				log.error(DataUtil.printErrorStack(e));
			}
		 }
	}
	
	private void handlerExpiredQueue(){		
		signalThread(LogLock.chainStartLock,LogLock.chainStartCon,PlatLogQueue.getTceinStartQueueSize(),PlatLogQueue.tceinStartInitTime);
		signalThread(LogLock.chainEndLock,LogLock.chainEndCon,PlatLogQueue.getTceinEndQueueSize(),PlatLogQueue.tceinEndInitTime);
		signalThread(LogLock.chainBusLock,LogLock.chainBusCon,PlatLogQueue.getTceinBusQueueSize(),PlatLogQueue.tceinBusInitTime);
		signalThread(LogLock.tceCsLock,LogLock.tceCsCon,PlatLogQueue.getCircsQueueSize(),PlatLogQueue.circsInitTime);
		signalThread(LogLock.tceSrLock,LogLock.tceSrCon,PlatLogQueue.getCirsrQueueSize(),PlatLogQueue.cirsrInitTime);
		signalThread(LogLock.tceSsLock,LogLock.tceSsCon,PlatLogQueue.getCirssQueueSize(),PlatLogQueue.cirssInitTime);
		signalThread(LogLock.tceCrLock,LogLock.tceCrCon,PlatLogQueue.getCircrQueueSize(),PlatLogQueue.circrInitTime);
		signalThread(LogLock.annotLock,LogLock.annotCon,PlatLogQueue.getAnnotQueueSize(),PlatLogQueue.annotInitTime);
	}
	
	/**
	 * 通知线程  是否开启线程
	 * @param  ReentrantLock lock     线程的锁
	 * @param  Condition     cond     线程启动停止的条件
	 * @param  int currentQueueSize   当前队列的长度	 
	 * @param  long expiredTime       队列为零时的初始时间
	 */
	private  void signalThread(ReentrantLock lock,Condition cond,int currentQueueSize,long expiredTime){
		if(currentQueueSize>0&&isExpired(expiredTime)){			
			 if(lock.tryLock()){
				 try{
					 cond.signal();					
				 }finally{
					 lock.unlock();
				 }
			 }
		}
	}
	/**
	 * 是否超期	 
	 * @return boolean  是否超期	 
	 */
	private  boolean isExpired(long expiredTime){
		return (System.currentTimeMillis() - expiredTime)>LogConfCache.getConfigInfo().getExpiredTime();
	}
}
