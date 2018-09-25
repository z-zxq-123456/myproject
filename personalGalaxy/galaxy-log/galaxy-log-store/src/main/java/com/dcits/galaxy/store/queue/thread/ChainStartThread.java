package com.dcits.galaxy.store.queue.thread;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.dcits.galaxy.logcover.common.DataUtil;
import com.dcits.galaxy.logcover.config.LogConfCache;
import com.dcits.galaxy.logcover.model.EcmTcechainStart;
import com.dcits.galaxy.logcover.queue.LogLock;
import com.dcits.galaxy.logcover.queue.PlatLogQueue;
import com.dcits.galaxy.store.dao.LogPlatDao;


/**
 * 调用链开始批量插入线程* 
 * @author tangxlf
 * @date 2016-10-21 
 */
public class ChainStartThread extends Thread{
	
	 private  final Logger log = LoggerFactory.getLogger(this.getClass());	
	 
	 
	 LogPlatDao    logPlatDao;
	 
	 public ChainStartThread(LogPlatDao  logPlatDao){		 
		 this.logPlatDao = logPlatDao;
	 }
	 
	 
	 public void run(){			
		 while(true){	 
			 if(PlatLogQueue.getTceinStartQueueSize()<=LogConfCache.getConfigInfo().getTceinStopLen()){ 
				 if (LogLock.chainStartLock.tryLock()){//获取线程锁
					 try {		
						 log.warn("ChainStartThread  count is null...thread wait");
						 LogLock.chainStartCon.await();
					 } catch (InterruptedException e) {
						 log.error("ChainStartThread await:"+DataUtil.printErrorStack(e));
					 }finally{
						 LogLock.chainStartLock.unlock();
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
		 List<EcmTcechainStart>  resultlist = PlatLogQueue.readStartTrace(LogConfCache.getConfigInfo().getQueueBatchSize());
		 log.warn("ChainStartThread batch insert start..."+"insert count is"+resultlist.size());		 
		 if(resultlist.size()>0){
			 try{
				 logPlatDao.insertChainStartList(resultlist);
			 }catch (Exception e) {					 
				 log.error("ChainStartThread batch insert:"+DataUtil.printErrorStack(e));
			 }
		 }
	 }
}
