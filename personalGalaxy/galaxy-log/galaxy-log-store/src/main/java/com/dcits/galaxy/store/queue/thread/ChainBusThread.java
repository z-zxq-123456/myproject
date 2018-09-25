package com.dcits.galaxy.store.queue.thread;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.dcits.galaxy.logcover.common.DataUtil;
import com.dcits.galaxy.logcover.config.LogConfCache;
import com.dcits.galaxy.logcover.model.EcmTcecinBus;
import com.dcits.galaxy.logcover.queue.LogLock;
import com.dcits.galaxy.logcover.queue.PlatLogQueue;
import com.dcits.galaxy.store.dao.LogPlatDao;


/**
 * 调用链业务字段批量插入线程* 
 * @author tangxlf
 * @date 2016-10-21 
 */
public class ChainBusThread extends Thread{
	
	 private  final Logger log = LoggerFactory.getLogger(this.getClass());	
	 
	 LogPlatDao    logPlatDao;
	 
	 public ChainBusThread(LogPlatDao  logPlatDao){		 
		 this.logPlatDao = logPlatDao;
	 }
	 
	 public void run(){			
		 while(true){	 
			 if(PlatLogQueue.getTceinBusQueueSize()<=LogConfCache.getConfigInfo().getTceinStopLen()){ 
				 if (LogLock.chainBusLock.tryLock()){//获取线程锁
					 try {		
						 log.warn("ChainBusThread  count is null...thread wait");
						 LogLock.chainBusCon.await();						 
					 } catch (InterruptedException e) {
						 log.error("ChainBusThread await:"+DataUtil.printErrorStack(e));
					 }finally{
						 LogLock.chainBusLock.unlock();
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
		 List<EcmTcecinBus>  resultlist = PlatLogQueue.readBusColTrace(LogConfCache.getConfigInfo().getQueueBatchSize());
		 log.warn("ChainBusThread batch insert start..."+"insert count is"+resultlist.size());		 
		 if(resultlist.size()>0){
			 try{
				 logPlatDao.insertChainBusList(resultlist);
			 }catch (Exception e) {					 
				 log.error("ChainBusThread batch insert:"+DataUtil.printErrorStack(e));
			 }
		 }
	 }
 
}
