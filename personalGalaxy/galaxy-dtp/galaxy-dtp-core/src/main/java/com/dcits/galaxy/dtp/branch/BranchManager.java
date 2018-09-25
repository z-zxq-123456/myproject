package com.dcits.galaxy.dtp.branch;

import com.dcits.galaxy.dtp.branch.BranchPropagate.Propagation;

/**
 * 分支事务管理器,配合主事务管理完成事务的确认或取消。需发布为远程服务
 * @author Yin.Weicai
 *
 */
@BranchPropagate(Propagation.CONTEXT)
public interface BranchManager {
	
	/**
	 * 开启一个分支事务
	 * @param bxid 分支事务标识
	 * @param txid 主事务标识
	 * @return
	 */
	public BranchTransaction begin(String bxid, String txid);
	
	/**
	 * 开启一个分支事务
	 * @param bxid 分支事务标识
	 * @param indexInTrunk 分支事务在主事务中的顺序
	 * @param txid
	 * @return
	 */
	public BranchTransaction begin(String bxid, int indexInTrunk, String txid);
	
	/**
	 * 开启一个嵌套的分支事务
	 * @param bxid 分支事务标识
	 * @param indexInBranch 分支事务在主事务中的顺序
	 * @param txid 主事务标识
	 * @param parentBxid 外层分支事务标识
	 * @return
	 */
	public BranchTransaction begin(String bxid, int indexInBranch, String txid, String parentBxid);
	
	/**
	 * 开启一个嵌套的分支事务
	 * @param bxid 分支事务标识
	 * @param txid 主事务标识
	 * @param parentBxid 外层分支事务标识
	 * @return
	 */
	public BranchTransaction begin(String bxid, String txid, String parentBxid);
	
	/**
	 * 开启一个分支事务
	 * @param branch
	 * @return
	 */
	public BranchTransaction begin(BranchTransaction branch);
	
	/**
	 * 确认提交
	 * @param bxid 事务标识
	 */
	public boolean confirmSubmit(String bxid);
	
	/**
	 * 确认提交
	 * @param bxid 事务标识
	 */
	public boolean confirmSubmit(BranchTransaction branch);
	
	/**
	 * 取消提交
	 * @param bxid 事务标识
	 */
	public boolean cancelSubmit(String bxid);
	
	/**
	 * 取消提交
	 * @param bxid 事务标识
	 */
	public boolean cancelSubmit(BranchTransaction branch);
	
}
