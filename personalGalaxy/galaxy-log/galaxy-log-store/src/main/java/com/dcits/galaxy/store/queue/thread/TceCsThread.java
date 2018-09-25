package com.dcits.galaxy.store.queue.thread;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.dcits.galaxy.logcover.common.DataUtil;
import com.dcits.galaxy.logcover.config.LogConfCache;
import com.dcits.galaxy.logcover.model.EcmTcecirCs;
import com.dcits.galaxy.logcover.queue.LogLock;
import com.dcits.galaxy.logcover.queue.PlatLogQueue;
import com.dcits.galaxy.store.dao.LogPlatDao;


/**
 * 调用环客户端开始批量插入线程* 
 * @author tangxlf
 * @date 2016-10-21 
 */
public class TceCsThread extends Thread{
	
	 private  final Logger log = LoggerFactory.getLogger(this.getClass());
	
	 LogPlatDao   logPlatDao;
	 
	 public TceCsThread(LogPlatDao  logPlatDao){		 
		 this.logPlatDao = logPlatDao;
	 }
	 
	 
	 
	 public void run(){			
		 while(true){	 
			 if(PlatLogQueue.getCircsQueueSize()<=LogConfCache.getConfigInfo().getCirStopLen()){ 
				 if (LogLock.tceCsLock.tryLock()){//获取线程锁
					 try {		
						 log.warn("TceCsThread  count is null...thread await");
						 LogLock.tceCsCon.await();
					 } catch (InterruptedException e) {
						 log.error("TceCsThread await:"+DataUtil.printErrorStack(e));
					 }finally{
						 LogLock.tceCsLock.unlock();
					 }
				 }
				 addToDB();
			 }else{				 
				 addToDB();
			 }
		 }
	 }
 
	 private void addToDB(){
		 //批量 插入数据
		 List<EcmTcecirCs>  resultlist = PlatLogQueue.readCircleCS(LogConfCache.getConfigInfo().getQueueBatchSize());
		 log.warn("TceCsThread batch insert start..."+"insert count is"+resultlist.size());		 
		 if(resultlist.size()>0){
			 try{
				 logPlatDao.insertTceCsList(resultlist);
			 }catch (Exception e) {					 
				 log.error("TceCsThread batch insert:"+DataUtil.printErrorStack(e));
			 }
		 }
	 }
}
