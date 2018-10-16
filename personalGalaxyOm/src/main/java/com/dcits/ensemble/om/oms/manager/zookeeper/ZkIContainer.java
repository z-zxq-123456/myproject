package com.dcits.ensemble.om.oms.manager.zookeeper;


import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareZkint;
import com.dcits.ensemble.om.oms.module.utils.ContainerCheckResult;

import java.util.List;

/**
 * 容器接口* 
 * @author tangxlf
 * @date 2015-09-22 
 */
public interface ZkIContainer {
	
	/**
	 * 容器实例部署
	 * @param EcmMidwareZkint  intant 容器实例对象	
	 * @param String        userId 用户ID		 
	 */
	public void assemContainer(EcmMidwareZkint intant, String userId, List<EcmMidwareZkint> list);
	/**
	 * 检查容器实例状态 
	 * @param EcmAppIntant  intant 容器实例对象	 
	 * @param String        userId 用户ID	
	 * @return  ContainerCheckResult    容器状态检查结果对象
	 */
	public ContainerCheckResult checkContainerStatus(EcmMidwareZkint intant, String userId);
	/**
	 * 启动容器实例
	 * @param EcmAppIntant  intant 容器实例对象
	 * @param String        userId 用户ID			 
	 */
	public void startContainer(EcmMidwareZkint intant, String userId);
	
	
	/**
	 * 停止容器实例
	 * @param EcmAppIntant  intant 容器实例对象
	 * @param String        userId 用户ID			 
	 */
	public void stopContainer(EcmMidwareZkint intant, String userId) ;
	/**
	 * 清理ZK实例
	 * @param EcmMidwareZkint  intant 容器实例对象
	 * @param String        userId 用户ID			 
	 */
	public void cleanZkIntant(EcmMidwareZkint intant, String userId);
	
}
