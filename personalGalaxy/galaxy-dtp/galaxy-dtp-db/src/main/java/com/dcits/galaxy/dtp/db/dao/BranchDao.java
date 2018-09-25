package com.dcits.galaxy.dtp.db.dao;

import java.util.List;

import javax.annotation.Resource;

import com.dcits.galaxy.dal.mybatis.ShardSqlSessionTemplate;
import com.dcits.galaxy.dtp.branch.BranchStatus;
import com.dcits.galaxy.dtp.branch.BranchTransaction;

/**
 * 分支事务数据库访问类
 * @author Yin.Weicai
 */
@SuppressWarnings("unchecked")
public class BranchDao {
	
	@Resource
	private ShardSqlSessionTemplate shardSqlSessionTemplate;

	private final String nameSpace = "com.dcits.galaxy.dtp.branch.BranchTransaction";

	/**
	 * 持久化分支事务
	 * @param branch
	 */
	public void save(BranchTransaction branch) {
		shardSqlSessionTemplate.insert(nameSpace, "save", branch);
	}

	/**
	 * 更新指定的分支事务的状态
	 * @param bxid
	 * @param status
	 */
	public void updateStatus(String bxid, BranchStatus status) {
		BranchTransaction bt = new BranchTransaction();
		bt.setBxid(bxid);
		bt.setStatus(status);
		shardSqlSessionTemplate.update(nameSpace, "updateStatus", bt);
	}

	/**
	 * 获取指定的分支事务
	 * @param bxid
	 * @return
	 */
	public BranchTransaction getBranch(String bxid) {
		BranchTransaction bt = new BranchTransaction();
		bt.setBxid(bxid);
		return (BranchTransaction) shardSqlSessionTemplate.selectOne(nameSpace, "getBranch", bt);
	}

	/**
	 * 获取指定的主事务的指定状态的分支事务
	 * @param txid
	 * @return
	 */
	public List<BranchTransaction> getAllByTxid(String txid) {
		BranchTransaction bt = new BranchTransaction();
		bt.setTxid(txid);
		return shardSqlSessionTemplate.selectList(nameSpace, "getAllByTxid", bt);
	}

	/**
	 * 获取指定分支事务的嵌套的指定状态的所有分支事务
	 * @param bxid
	 * @param status
	 * @return
	 */
	public List<BranchTransaction> getAllByBxid(String bxid) {
		BranchTransaction bt = new BranchTransaction();
		bt.setBxid(bxid);
		return shardSqlSessionTemplate.selectList(nameSpace, "getAllByBxid", bt);
	}

	public List<BranchTransaction> getUnCompletedDisorderByAppGroup(String appGroup) {
		BranchTransaction bt = new BranchTransaction();
		bt.setAppGroup(appGroup);
		return shardSqlSessionTemplate.selectList(nameSpace, "getUnCompletedDisorderByAppGroup", bt);
	}

	public List<BranchTransaction> getNeedSubmitByTxid(String txid) {
		BranchTransaction bt = new BranchTransaction();
		bt.setTxid(txid);
		return shardSqlSessionTemplate.selectList(nameSpace, "getNeedSubmitByTxid", bt);
	}

	public List<BranchTransaction> getNeedSubmitByBxid(String bxid) {
		BranchTransaction bt = new BranchTransaction();
		bt.setBxid(bxid);
		return shardSqlSessionTemplate.selectList(nameSpace, "getNeedSubmitByBxid", bt);
	}
}
