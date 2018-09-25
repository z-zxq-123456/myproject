package com.dcits.galaxy.dtp.branch;


/**
 * 分支事务管理器工厂类
 * @author Yin.Weicai
 *
 */
public class BranchManagerFactory {
	
	private static BranchManager branchManager = null;
	
	public static BranchManager getBean(){
		return branchManager;
	}
	
	/**
	 * 注册具体的分支事务管理器实现
	 * @param branchManager
	 */
	public static void registerBean(BranchManager branchManager){
		BranchManagerFactory.branchManager = branchManager;
	}
}
