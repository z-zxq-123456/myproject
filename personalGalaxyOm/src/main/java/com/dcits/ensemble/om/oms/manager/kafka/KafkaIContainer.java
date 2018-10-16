package com.dcits.ensemble.om.oms.manager.kafka;

import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareKafkaint;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareRedisint;
import com.dcits.ensemble.om.oms.module.utils.ContainerCheckResult;

import java.util.List;
import java.util.Map;

/**
 * Kafka容器接口
 * @author luolang
 * @date 2016-12-12
 */
public interface KafkaIContainer {

	/**
	 * 检查容器实例状态 
	 * @param EcmMidwareKafkaint  midwareKafkaint 容器实例对象
	 * @param String        userId 用户ID	
	 * @return  ContainerCheckResult    容器状态检查结果对象
	 */
	public ContainerCheckResult checkContainerStatus(EcmMidwareKafkaint midwareKafkaint, String userId);
	
	
	/**
	 * 容器实例部署
	 * @param Map<String,List<EcmMidwareKafkaint>> kafkaMap Kafka实例集合，key为ip，value为ip所对应的实例列表
	 * @param String        userId 用户ID		 
	 */
	public void assemContainer(Map<String, List<EcmMidwareKafkaint>> kafkaMap, String ip, String userId);
	/**
	 * 启动容器实例
	 * @param EcmMidwareKafkaint  midwareKafkaint 容器实例对象
	 * @param String        userId 用户ID			 
	 */
	public void startContainer(EcmMidwareKafkaint midwareKafkaint, String userId);
	
	
	/**
	 * 停止容器实例
	 * @param EcmMidwareKafkaint  midwareKafkaint 容器实例对象
	 * @param String        userId 用户ID			 
	 */
	public void stopContainer(EcmMidwareKafkaint midwareKafkaint, String userId) ;

	/**
	 *重启容器实例
	 * @param EcmMidwareKafkaint  midwareKafkaint 容器实例对象
	 * @param String        userId 用户ID			 
	 */
	public void reStartContainer(EcmMidwareKafkaint midwareKafkaint, String userId);

	public void clearContainer(EcmMidwareKafkaint midwareKafkaint ,String userId);
	
}
