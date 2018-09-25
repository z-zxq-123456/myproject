package com.dcits.galaxy.logcover.config;

import org.springframework.stereotype.Component;


/**
 * 日志配置缓存初始化* 
 * @author tangxlf
 * @date 2016-11-03 
 */
@Component
public class LogConfCacheInit  { 	
	
	public LogConfCacheInit(){
		LogConfCache.getConfigInfo();
	}
}
