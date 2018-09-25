package com.dcits.galaxy.logcover.config;

import java.util.HashMap;
import java.util.Map;

/**
 * 存储运行常量
 * <p>Created on 2016/11/15.</p>
 *
 * @author tangxlf <tangxlf@dcits.com> 
 * @since 1.7
 */
public class LogStoreConfInfo {
	 /**
     * 调用链线程数
     */      
	private static final Integer  CHAIN_STORE_THREAD_NUM = 3*2;
	
	
	/**
     * 调用环线程数
     */      
	private static final Integer  CIRCLE_STORE_THREAD_NUM = CHAIN_STORE_THREAD_NUM*5;	
	
	
	
	/**
     * 日志线程数
     */      
	private static final Integer  ANNOT_STORE_THREAD_NUM = CHAIN_STORE_THREAD_NUM*5;
	
	 
	
	public static Integer getChainStoreThreadNum(){
		 return  CHAIN_STORE_THREAD_NUM;
	}
	
	public static Integer getCircleStoreThreadNum(){
		 return  CIRCLE_STORE_THREAD_NUM;
	}
	
	public static Integer getAnnotStoreThreadNum(){
		 return  ANNOT_STORE_THREAD_NUM;
	}
	
	
	
}
