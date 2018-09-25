package com.dcits.galaxy.dtp.trunk;


/**
 * 主事务业务处理工厂类
 * @author Yin.Weicai
 */
public class TrunkServiceFactory {

	private static TrunkService bean = null;
	
	public static TrunkService getBean(){
		return bean;
	}
	
	/**
	 * 注册主事务持久化处理实现
	 * @param trunkService
	 */
	public static void registerBean(TrunkService trunkService){
		TrunkServiceFactory.bean = trunkService;
	}
}
