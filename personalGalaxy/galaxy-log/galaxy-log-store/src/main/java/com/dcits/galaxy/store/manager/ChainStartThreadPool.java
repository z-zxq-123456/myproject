package com.dcits.galaxy.store.manager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.dcits.galaxy.store.dao.LogPlatDao;
import com.dcits.galaxy.store.queue.thread.ChainStartThread;



/**
 * 调用链开始批量插入线程线程池
 * <p>Created on 2016/11/15.</p>
 *
 * @author tangxlf <tangxlf@dcits.com> 
 * @since 1.7
 */
public class ChainStartThreadPool {
	
	private final ExecutorService pool;
	 
	public  ChainStartThreadPool(LogPlatDao  logPlatDao,Integer poolSize){
		pool =Executors.newFixedThreadPool(poolSize);
		for(int i = 0 ; i < poolSize ; i++){
			 pool.execute(new ChainStartThread(logPlatDao));
		}
    }
}
