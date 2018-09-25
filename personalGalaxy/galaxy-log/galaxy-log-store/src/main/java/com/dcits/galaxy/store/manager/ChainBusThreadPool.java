package com.dcits.galaxy.store.manager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.dcits.galaxy.store.dao.LogPlatDao;
import com.dcits.galaxy.store.queue.thread.ChainBusThread;



/**
 * 调用链业务字段批量插入线程池
 * <p>Created on 2016/11/15.</p>
 *
 * @author tangxlf <tangxlf@dcits.com> 
 * @since 1.7
 */
public class ChainBusThreadPool {
	
	private final ExecutorService pool;
	 
	public  ChainBusThreadPool(LogPlatDao  logPlatDao,Integer poolSize){
		pool =Executors.newFixedThreadPool(poolSize);
		for(int i = 0 ; i < poolSize ; i++){
			 pool.execute(new ChainBusThread(logPlatDao));
		}
    }
}
