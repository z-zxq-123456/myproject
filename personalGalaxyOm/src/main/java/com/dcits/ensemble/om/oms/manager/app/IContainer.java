package com.dcits.ensemble.om.oms.manager.app;


import com.dcits.ensemble.om.oms.module.app.EcmAppIntant;
import com.dcits.ensemble.om.oms.module.utils.ContainerCheckResult;

/**
 * 容器接口* 
 * @author tangxlf
 * @date 2015-09-22 
 */
public interface IContainer {

	
	/**
	 * 检查容器实例状态 
	 * @param EcmAppIntant  intant 容器实例对象	 
	 * @param String        userId 用户ID	
	 * @return  ContainerCheckResult    容器状态检查结果对象
	 */
	public ContainerCheckResult checkContainerStatus(EcmAppIntant intant, String userId);
	
	
	/**
	 * 容器实例部署
	 * @param EcmAppIntant  intant 容器实例对象	
	 * @param String        userId 用户ID		 
	 */
	public void assemContainer(EcmAppIntant intant, String userId);
	
	
	/**
	 * 启动容器实例
	 * @param EcmAppIntant  intant 容器实例对象
	 * @param String        userId 用户ID			 
	 */
	public void startContainer(EcmAppIntant intant, String userId);
	
	
	
	
	/**
	 * 停止容器实例
	 * @param EcmAppIntant  intant 容器实例对象
	 * @param String        userId 用户ID			 
	 */
	public void stopContainer(EcmAppIntant intant, String userId) ;
	
	/**
	 * 重启动容器实例
	 * @param EcmAppIntant  intant 容器实例对象	
	 * @param String        userId 用户ID		
	 */
	public void reStartContainer(EcmAppIntant intant, String userId) ;

	
}
