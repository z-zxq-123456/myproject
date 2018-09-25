package com.dcits.galaxy.store.queue.thread;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.dcits.galaxy.logcover.common.DataUtil;
import com.dcits.galaxy.logcover.config.LogConfCache;
import com.dcits.galaxy.logcover.model.EcmTcecirSr;
import com.dcits.galaxy.logcover.queue.LogLock;
import com.dcits.galaxy.logcover.queue.PlatLogQueue;
import com.dcits.galaxy.store.dao.LogPlatDao;


/**
 * 调用环服务端开始批量插入线程* 
 * @author tangxlf
 * @date 2016-10-21 
 */
public class TceSrThread extends Thread{
	
	 private  final Logger log = LoggerFactory.getLogger(this.getClass());	 
	 
	 LogPlatDao    logPlatDao;
	 
	 public TceSrThread(LogPlatDao  logPlatDao){		 
		 this.logPlatDao = logPlatDao;
	 }
	 
	 
	 public void run(){			
		 while(true){	 
			 if(PlatLogQueue.getCirsrQueueSize()<=LogConfCache.getConfigInfo().getCirStopLen()){ 
				 if (LogLock.tceSrLock.tryLock()){//获取线程锁
					 try {		
						 log.warn("TceSrThread  count is null...thread await");
						 LogLock.tceSrCon.await();
					 } catch (InterruptedException e) {
						 log.error("TceSrThread await:"+DataUtil.printErrorStack(e));
					 }finally{
						 LogLock.tceSrLock.unlock();
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
		 List<EcmTcecirSr>  resultlist = PlatLogQueue.readCircleSR(LogConfCache.getConfigInfo().getQueueBatchSize());
		 log.info("TceSrThread batch insert start..."+"insert count is"+resultlist.size());		 
		 if(resultlist.size()>0){
			 try{
				 logPlatDao.insertTceSrList(resultlist);
			 }catch (Exception e) {					 
				 log.error("TceSrThread batch insert:"+DataUtil.printErrorStack(e));
			 }
		 }
	 }
 
}
