package com.dcits.galaxy.store.queue.thread;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.dcits.galaxy.logcover.common.DataUtil;
import com.dcits.galaxy.logcover.config.LogConfCache;
import com.dcits.galaxy.logcover.model.EcmTcecirSs;
import com.dcits.galaxy.logcover.queue.LogLock;
import com.dcits.galaxy.logcover.queue.PlatLogQueue;
import com.dcits.galaxy.store.dao.LogPlatDao;


/**
 * 调用环服务端结束批量插入线程* 
 * @author tangxlf
 * @date 2016-10-21 
 */
public class TceSsThread extends Thread{
	
	 private  final Logger log = LoggerFactory.getLogger(this.getClass());	 
	 
	 LogPlatDao    logPlatDao;
	 
	 public TceSsThread(LogPlatDao  logPlatDao){		 
		 this.logPlatDao = logPlatDao;
	 }
	 	
	 public void run(){			
		 while(true){	 
			 if(PlatLogQueue.getCirssQueueSize()<=LogConfCache.getConfigInfo().getCirStopLen()){ 
				 if (LogLock.tceSsLock.tryLock()){//获取线程锁
					 try {	
						 log.warn("TceSsThread  count is null...thread await");
						 LogLock.tceSsCon.await();
					 } catch (InterruptedException e) {
						 log.error("TceSsThread await:"+DataUtil.printErrorStack(e));
					 }finally{
						 LogLock.tceSsLock.unlock();
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
		 List<EcmTcecirSs>  resultlist = PlatLogQueue.readCircleSS(LogConfCache.getConfigInfo().getQueueBatchSize());
		 log.warn("TceSsThread batch insert start..."+"insert count is"+resultlist.size());
		 if(resultlist.size()>0){
			 try{
				 logPlatDao.insertTceSsList(resultlist);
			 }catch (Exception e) {					 
				 log.error("TceSsThread batch insert:"+DataUtil.printErrorStack(e));
			 } 
		 }
	 }
}
