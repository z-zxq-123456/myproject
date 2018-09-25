package com.dcits.galaxy.dtp.branch;


/**
 * 分支事务业务逻辑工厂类
 * @author Yin.Weicai
 *
 */
public class BranchServiceFactory {
	
	private static BranchService branchService = null;
	
	public static BranchService getBean(){
		return branchService;
	}
	
	/**
	 * 注册具体的分支事务业务逻辑实现
	 * @param branchManager
	 */
	public static void registerBean(BranchService branchService){
		BranchServiceFactory.branchService = branchService;
	}
}
