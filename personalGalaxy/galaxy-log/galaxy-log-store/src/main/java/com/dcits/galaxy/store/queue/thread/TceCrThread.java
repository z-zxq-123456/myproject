package com.dcits.galaxy.store.queue.thread;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.dcits.galaxy.logcover.common.DataUtil;
import com.dcits.galaxy.logcover.config.LogConfCache;
import com.dcits.galaxy.logcover.model.EcmTcecirCr;
import com.dcits.galaxy.logcover.queue.LogLock;
import com.dcits.galaxy.logcover.queue.PlatLogQueue;
import com.dcits.galaxy.store.dao.LogPlatDao;


/**
 * 调用环客户端结束批量插入线程* 
 * @author tangxlf
 * @date 2016-10-21 
 */
public class TceCrThread extends Thread{
	
	 private  final Logger log = LoggerFactory.getLogger(this.getClass());		 
	 
	 LogPlatDao    logPlatDao;
	 
	 public TceCrThread(LogPlatDao  logPlatDao){
		
		 this.logPlatDao = logPlatDao;
	 }
	 
	 
	 
	 public void run(){			
		 while(true){	 
			 if(PlatLogQueue.getCircrQueueSize()<=LogConfCache.getConfigInfo().getCirStopLen()){ 
				 if (LogLock.tceCrLock.tryLock()){//获取线程锁
					 try {		
						 log.warn("TceCrThread  count is null...thread await");
						 LogLock.tceCrCon.await();
					 } catch (InterruptedException e) {
						 log.error("TceCrThread await:"+DataUtil.printErrorStack(e));
					 }finally{
						 LogLock.tceCrLock.unlock();
					 }
				 }
				 addToDB();	
			 }else{
				 addToDB();				 
			 }
		 }
	 }
	 private void addToDB(){
		 List<EcmTcecirCr>  resultlist = PlatLogQueue.readCircleCR(LogConfCache.getConfigInfo().getQueueBatchSize());
		 log.warn("TceCrThread batch insert start..."+"insert count is"+resultlist.size());		 
		 if(resultlist.size()>0){
			 try{
				 logPlatDao.insertTceCrList(resultlist);
			 }catch (Exception e) {					 
				 log.error("TceCrThread batch insert:"+DataUtil.printErrorStack(e));
			 }
		 }
	 }
}
