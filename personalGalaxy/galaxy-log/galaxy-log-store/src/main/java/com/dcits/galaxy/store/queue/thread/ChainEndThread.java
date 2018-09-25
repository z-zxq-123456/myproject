package com.dcits.galaxy.store.queue.thread;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.dcits.galaxy.logcover.common.DataUtil;
import com.dcits.galaxy.logcover.config.LogConfCache;
import com.dcits.galaxy.logcover.model.EcmTcecinEnd;
import com.dcits.galaxy.logcover.queue.LogLock;
import com.dcits.galaxy.logcover.queue.PlatLogQueue;
import com.dcits.galaxy.store.dao.LogPlatDao;


/**
 * 调用链结束批量插入线程* 
 * @author tangxlf
 * @date 2016-10-21 
 */
public class ChainEndThread extends Thread{
	
	 private  final Logger log = LoggerFactory.getLogger(this.getClass());
	 
	 LogPlatDao    logPlatDao;
	 
	 public ChainEndThread(LogPlatDao  logPlatDao){		
		 this.logPlatDao = logPlatDao;
	 }
	 
	 public void run(){			
		 while(true){	 
			 if(PlatLogQueue.getTceinEndQueueSize()<=LogConfCache.getConfigInfo().getTceinStopLen()){ 
				 if (LogLock.chainEndLock.tryLock()){//获取线程锁
					 try {		
						 log.warn("ChainEndThread  count is null...thread wait");
						 LogLock.chainEndCon.await();						 
					 } catch (InterruptedException e) {
						 log.error("ChainEndThread await:"+DataUtil.printErrorStack(e));
					 }finally{
						 LogLock.chainEndLock.unlock();
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
		 List<EcmTcecinEnd>  resultlist = PlatLogQueue.readEndTrace(LogConfCache.getConfigInfo().getQueueBatchSize());
		 log.warn("ChainEndThread batch insert start..."+"insert count is"+resultlist.size());		
		 if(resultlist.size()>0){
			 try{
				 logPlatDao.insertChainEndList(resultlist);
			 }catch (Exception e) {					 
				 log.error("ChainEndThread batch insert:"+DataUtil.printErrorStack(e));
			 }
		 }
	 }
}
