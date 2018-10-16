package com.dcits.ensemble.om.oms.manager.redis;

import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareRedisint;
import com.dcits.ensemble.om.oms.module.utils.ContainerCheckResult;

import java.util.List;
import java.util.Map;

/**
 * Redis容器接口 
 * @author luolang
 * @date 2016-04-27 
 */
public interface RedisIContainer {

	/**
	 * 检查容器实例状态 
	 * @param EcmMidwareRedisint  midwareRedisint 容器实例对象	 
	 * @param String        userId 用户ID	
	 * @return  ContainerCheckResult    容器状态检查结果对象
	 */
	public ContainerCheckResult checkContainerStatus(EcmMidwareRedisint midwareRedisint, String userId);
	
	
	/**
	 * 容器实例部署
	 * @param Map<String,List<EcmMidwareRedisint>> redisMap Redis实例集合，key为ip，value为ip所对应的实例列表
	 * @param String        userId 用户ID		 
	 */
	public void assemContainer(Map<String, List<EcmMidwareRedisint>> redisMap, String ip, String userId);
	/**
	 * 启动容器实例
	 * @param EcmMidwareRedisint  midwareRedisintt 容器实例对象
	 * @param String        userId 用户ID			 
	 */
	public void startContainer(EcmMidwareRedisint midwareRedisint, String userId);
	
	
	/**
	 * 停止容器实例
	 * @param EcmMidwareRedisint  midwareRedisint 容器实例对象
	 * @param String        userId 用户ID			 
	 */
	public void stopContainer(EcmMidwareRedisint midwareRedisint, String userId) ;

	/**
	 *重启容器实例
	 * @param EcmMidwareRedisint  midwareRedisint 容器实例对象
	 * @param String        userId 用户ID			 
	 */
	public void reStartContainer(EcmMidwareRedisint midwareRedisint, String userId);
	
}
