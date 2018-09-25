package com.dcits.galaxy.dtp.trunk;


/**
 * 主事务管理器
 * @author Yin.Weicai
 */
public interface TrunkManager {

	/**
	 * 开启主事务
	 * @return 开启成功返回新的主事务实例，失败返回null
	 */
	public TrunkTransaction begin();
	
	/**
	 * 使用指定的 主事务标识 开启一个新事务
	 * @param txid  主事务标识
	 * @return
	 */
	public TrunkTransaction begin(String txid);
	
	/**
	 * 确认主事务
	 * @param txid 主事务标识
	 */
	public void confirm(String txid);
	
	/**
	 * 取消主事务
	 * @param txid 主事务标识
	 */
	public void cancel(String txid);
	
	/**
	 * 获取事务状态
	 * @param txid 主事务标识
	 * @return 返回事务状态，如果txid对应的主事务不存在，或者无法获取其状态，则返回null
	 */
	public TrunkStatus getStatus(String txid);
	
	/**
	 * 是否存在未完成的分支事务
	 * @param txid
	 * @return
	 */
	public boolean hasUnCompletedBranches( String txid ) throws Exception;
	
	/**
	 * 释放主事务在准备阶段锁定的资源
	 * @param txid 主事务标识
	 */
	public void release(String txid);
	
	/**
	 * 更新主事务的状态
	 * @param status
	 * @return
	 */
	public boolean updateStatus(String txid, TrunkStatus status);
	
}
