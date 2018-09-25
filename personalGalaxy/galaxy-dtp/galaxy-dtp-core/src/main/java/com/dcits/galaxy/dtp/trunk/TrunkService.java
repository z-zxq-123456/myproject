package com.dcits.galaxy.dtp.trunk;

import java.io.Serializable;
import java.util.List;


/**
 * 主事务信息存储服务接口
 * @author Yin.Weicai
 */
public interface TrunkService extends Serializable {
	
	/**
	 * 保存主事务
	 * @return
	 */
	public boolean saveTransaction(TrunkTransaction trunkTransaction);
	public boolean saveTransaction(String txid);
	
	/**
	 * 将主事务置为 确认态, 设置前先查询主事务状态：
	 * 1、如果主事务状态为 准备态，则将事务状态置为确认态，返回 true
	 * 2、如果主事务状态为 确认态，则不修改状态，直接返回 true
	 * 3、如果主事务状态为 确认完成态，则不修改状态，直接返回 false
	 * 4、如果主事务状态为 取消态或取消完成态，则不修改状态，直接返回false
	 * @param txid 主事务标识
	 * @return 
	 */
	public boolean setConfirm(String txid);
	
	/**
	 * 将主事务置为 确认完成态, 设置前先查询主事务状态：
	 * 1、如果主事务状态为 准备态 或确认态，则将事务状态置为确认完成态，返回 true
	 * 2、如果主事务状态为 确认完成态，则不修改状态，直接返回 true
	 * 4、如果主事务状态为 取消态或取消完成态，则不修改状态，直接返回false
	 * @param txid 主事务标识
	 * @return 
	 */
	public boolean setConfirmCompleted(String txid);
	
	/**
	 * 将主事务置为 取消态, 设置前先查询主事务状态：
	 * 1、如果主事务状态为 准备态，则将事务状态置为 取消态，返回 true
	 * 2、如果主事务状态为 取消态，则不修改状态，直接返回 true
	 * 3、如果主事务状态为  取消完成态，则不修改状态，直接返回 false
	 * 4、如果主事务状态为 确认态或确认完成态，则不修改状态，直接返回false
	 * @param txid 主事务标识
	 * @return 
	 */
	public boolean setCancel(String txid);
	
	/**
	 * 将主事务置为 取消完成态, 设置前先查询主事务状态：
	 * 1、如果主事务状态为 准备态 或确认态，则将事务状态置为确认完成态，返回 true
	 * 2、如果主事务状态为 确认完成态，则不修改状态，直接返回 true
	 * 4、如果主事务状态为 取消态或取消完成态，则不修改状态，直接返回false
	 * @param txid 主事务标识
	 * @return 
	 */
	public boolean setCancelCompleted(String txid);
	
	/**
	 * 获取主事务
	 * @param txid 主事务标识
	 * @return
	 */
	public TrunkTransaction getTransaction(String txid);
	
	/**
	 * 根据应用名获取timeout之前所有未完成的主事务列表
	 * 判定一个主事务是未完成的事务的准则：
	 * 1、主事务的状态为：confirm 或 cancel，则该事务是未完成的事务
	 * 2、主事务的状态为: prepare，需要再判定该事务运行了多长时间(需要事务上加入超时机制)，
	 *   如果超过 90秒则任务该事务失败，属于未完成事务，需要执行取消操作。
	 * @param appGroup 应用组
	 * @return
	 */
	public List<TrunkTransaction> getUnCompletedTrunks(String appGroup, long timeout);
	
	/**
	 * 是否存在未完成的分支事务
	 * @param txid
	 * @return
	 */
	public boolean hasUnCompletedBranches( String txid );
	
	/**
	 * 根据appGroup清除time之前的已完成事务。
	 * 即状态为TrunkStatus.confirm_complete与TrunkStatus.cancel_complete的事务
	 * 
	 * @param appGroup
	 * @param time
	 * @author fan.kaijie
	 */
	public void clean(String appGroup, long time);
}