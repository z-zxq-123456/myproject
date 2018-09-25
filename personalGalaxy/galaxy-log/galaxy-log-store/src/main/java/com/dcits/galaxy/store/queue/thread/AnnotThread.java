package com.dcits.galaxy.store.queue.thread;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dcits.galaxy.logcover.common.DataUtil;
import com.dcits.galaxy.logcover.config.LogConfCache;
import com.dcits.galaxy.logcover.model.EcmTraceAnnot;
import com.dcits.galaxy.logcover.queue.LogLock;
import com.dcits.galaxy.logcover.queue.PlatLogQueue;
import com.dcits.galaxy.store.dao.LogPlatDao;



/**
 * 日志标注批量插入线程* 
 * @author tangxlf
 * @date 2016-10-21 
 */
public class AnnotThread extends Thread{
	
	 private  final Logger log = LoggerFactory.getLogger(this.getClass());	 
	 
	 LogPlatDao    logPlatDao;
	 
	 public AnnotThread(LogPlatDao  logPlatDao){		 
		 this.logPlatDao = logPlatDao;
	 }
	 
	 public void run(){			
		 while(true){	 
			 if(PlatLogQueue.getAnnotQueueSize()<=LogConfCache.getConfigInfo().getAnnotStopLen()){ 
				 if (LogLock.annotLock.tryLock()){//获取线程锁
					 try {		
						 log.warn("AnnotThread  count is null...thread await"); 
						 LogLock.annotCon.await();						 
					 } catch (InterruptedException e) {
						 log.error("AnnotThread await:"+DataUtil.printErrorStack(e));
					 }finally{
						 LogLock.annotLock.unlock();
					 }
					 addToDB();
				 }
			 }else{
				 addToDB();
			 }
		 }
	 }
	 
	 private void addToDB(){
		 //批量 插入数据
		 List<EcmTraceAnnot>  resultlist = PlatLogQueue.readAnnot(LogConfCache.getConfigInfo().getQueueBatchSize());	
		 log.warn("AnnotThread batch insert start..."+"insert count is"+resultlist.size());
		 if(resultlist.size()>0){
			 try{
				 logPlatDao.insertAnnotList(resultlist);
			 }catch (Exception e) {					 
				 log.error("AnnotThread batch insert:"+DataUtil.printErrorStack(e));
			 }
		 }
	 }
	 
}
