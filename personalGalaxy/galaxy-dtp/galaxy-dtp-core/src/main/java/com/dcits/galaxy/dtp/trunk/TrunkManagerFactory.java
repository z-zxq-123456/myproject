package com.dcits.galaxy.dtp.trunk;



/**
 * 主事务管理器工厂类
 * @author Yin.Weicai
 *
 */
public class TrunkManagerFactory {

	private static TrunkManager bean = null;
	
	/**
	 * 获取主事务管理实现
	 * @return
	 */
	public static TrunkManager getBean(){
		return bean;
	}
	    
    /**
	 * 注册主事务管理实现
	 * @param trunkManager
	 */
	public static void registerBean(TrunkManager trunkManager){
		TrunkManagerFactory.bean = trunkManager;
	}
}
