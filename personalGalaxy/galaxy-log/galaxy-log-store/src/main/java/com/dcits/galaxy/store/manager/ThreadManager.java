package com.dcits.galaxy.store.manager;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.dcits.galaxy.logcover.config.ConfigConstants;
import com.dcits.galaxy.logcover.config.LogConfCache;
import com.dcits.galaxy.logcover.config.LogStoreConfInfo;
import com.dcits.galaxy.store.dao.LogPlatDao;
import com.dcits.galaxy.store.queue.thread.TimeExpiredThread;


/**
 *  存储线程的整体管理* 
 * @author tangxlf
 * @date 2016-10-21 
 */
@Component
public class ThreadManager implements InitializingBean{
	
	@Resource
	LogPlatDao    logPlatDao;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		//if(LogConfCache.isCollChain()){
			if(LogConfCache.getConfigInfo().getLogPlatMode().equals(ConfigConstants.PLAT_MODE_QUEDB)){	
				int annotPoolSize =  LogStoreConfInfo.getAnnotStoreThreadNum();
				int tcePoolSize = LogStoreConfInfo.getCircleStoreThreadNum();
				int chainPoolSize = LogStoreConfInfo.getChainStoreThreadNum();
				new ChainStartThreadPool(logPlatDao,chainPoolSize);
				new ChainBusThreadPool(logPlatDao,chainPoolSize);
				new ChainEndThreadPool(logPlatDao,chainPoolSize);
				new TceCrThreadPool(logPlatDao,tcePoolSize);
				new TceCsThreadPool(logPlatDao,tcePoolSize);
				new TceSrThreadPool(logPlatDao,tcePoolSize);
				new TceSsThreadPool(logPlatDao,tcePoolSize);
				new AnnotThreadPool(logPlatDao,annotPoolSize);
				new TimeExpiredThread().start();
			}
		//}
	}
}
