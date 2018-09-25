package com.dcits.galaxy.dtp.branch;

import java.util.List;

/**
 * 分支事务的持久化处理业务
 * @author Yin.Weicai
 */
public interface BranchService {
	
	/**
	 * 持久化分支事务
	 * @param branche
	 */
	public boolean savaBranch(BranchTransaction branch);
	
	/**
	 * 获取指定主事务的所有需要提交的分支事务（注：不包含分支事务内嵌套的分支事务）
	 * @param txid
	 * @return
	 */	
	public List<BranchTransaction> getNeedSubmitByTxid(String txid);
	
	/**
	 * 获取指定主事务的所有需要释放资源的分支事务（注：不包含分支事务内嵌套的分支事务）
	 * @param txid
	 * @return
	 */	
//	public List<BranchTransaction> getNeedReleaseByTxid(String txid);
	
	/**
	 * 获取指定分支事务的所有需要提交的分支事务（注：只获取分支事务内嵌的第一层的分支事务）
	 * @param txid
	 * @return
	 */	
	public List<BranchTransaction> getNeedSubmitByBxid(String bxid);
	
	/**
	 * 获取指定分支事务内嵌的所有需要释放资源的分支事务（注：只获取分支事务内嵌的第一层的分支事务）
	 * @param txid
	 * @return
	 */	
//	public List<BranchTransaction> getNeedReleaseByBxid(String bxid);
	
	/**
	 * 获取指定应用组的所有未完成的分支事务（注：不包含分支事务内嵌套的分支事务）
	 * @param appGroup 应用名
	 * @return
	 */	
	public List<BranchTransaction> getUnCompletedByAppGroup(String appGroup);
	
	/**
	 * 获取指定应用组的所有未完成的不需要执行顺序的分支事务（注：不包含分支事务内嵌套的分支事务）
	 * @param appGroup 应用名
	 * @return
	 */	
	public List<BranchTransaction> getUnCompletedDisorderByAppGroup(String appGroup);
	
	/**
	 * 获取指定主事务的所有未完成的分支事务（注：不包含分支事务内嵌套的分支事务）
	 * @param appGroup 应用名
	 * @return
	 */	
//	public List<BranchTransaction> getUnCompletedByTxid(String txid);

	/**
	 * 获取指定的分支事务
	 * @param bxid
	 * @return
	 */
	public BranchTransaction getByBxid(String bxid);
	
	/**
	 * 更新指定的分支事务的状态
	 * @param bxid
	 * @param status
	 * @return
	 */
	public boolean updateStatus(String bxid, BranchStatus status);
}
