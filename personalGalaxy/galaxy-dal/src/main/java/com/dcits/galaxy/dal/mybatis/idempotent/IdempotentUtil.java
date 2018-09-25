package com.dcits.galaxy.dal.mybatis.idempotent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.dcits.galaxy.dal.mybatis.exception.UnexpectedTransactionException;
import com.dcits.galaxy.dal.mybatis.idempotent.service.IdempotentService;
import com.dcits.galaxy.dal.mybatis.shard.Shard;

/**
 * 幂等性工具类
 * 
 * @author huang.zhe
 *
 */
public class IdempotentUtil {
	private IdempotentService idempotentService;

	private boolean checkFristShard = false;

	public IdempotentService getIdempotentService() {
		return idempotentService;
	}

	public void setIdempotentService(IdempotentService idempotentService) {
		this.idempotentService = idempotentService;
	}

	public boolean isCheckFristShard() {
		return checkFristShard;
	}

	public void setCheckFristShard(boolean checkFristShard) {
		this.checkFristShard = checkFristShard;
	}

	/**
	 * 多库情况下，以先上下文后数据库的查询顺序判断每个shard对应库中是否存在idempotent对象，
	 * 如数据库中查询不存在，则将idempotent写入shard对应库，同时更改上下文状态，所有查询 完成后，
	 * 返回不存在idempotent对象的shard列表
	 * 
	 * @param shardList
	 * @param idempotentObject
	 * @throws UnexpectedTransactionException 如果不存在事务，抛出UnexpectedTransactionException异常
	 * @return
	 */
	public  List<Shard> existAndSave(Object idempotentObject, List<Shard> shardList) {
		return partitionCheckAndSave(idempotentObject, shardList, checkFristShard);
	}

	/**
	 * 多库情况下， 若checkFisrtShard==false
	 * 则对事务涉及到的每个库进行检查，执行idempotentId的查询与插入，返回需要执行操作的shard列表 checkOne=true
	 * 选取事务首次请求操作的第一个库作为幂等判断库，执行idempotentId的查询与插入，返回需要执行的shard列表
	 * 
	 * @param shardList
	 * @param idempotentObject
	 * @param checkFisrtShard
	 * 是否开启确定幂等判断库的模式
	 * @return
	 * 
	 * @throws UnexpectedTransactionException 如果不存在事务，抛出UnexpectedTransactionException异常
	 */
	private  List<Shard> partitionCheckAndSave(Object idempotentObject, List<Shard> shardList, boolean checkFisrtShard) {
		boolean isActualTransaction = TransactionSynchronizationManager.isActualTransactionActive();
		if(!isActualTransaction) {
			throw new UnexpectedTransactionException("it not in a actual transaction.");
		}
		if (checkFisrtShard != true) {
			return checkAllAndSave(idempotentObject, shardList);
		}
		return checkOneAndSave(idempotentObject, shardList);
	}

	/**
	 * 多库情况下，对事务涉及到的每个库进行检查，执行idempotent对象的查询与插入，同时返回返回不存在idempotent的shard列表
	 * 
	 * @param idempotentObj
	 * @param shardList
	 * @return
	 */
	private List<Shard> checkAllAndSave(Object idempotentObj, List<Shard> shardList) {
		List<Shard> shards = new ArrayList<Shard>();
		for (Shard shard : shardList) {
			String shardId = shard.getId();
			if (IdempotentContext.containsShardId(shardId)) {
				if (IdempotentContext.getState(shardId) == IdempotentState.NONE) {
					shards.add(shard);
				}
			} else {
				if (idempotentService.exist(idempotentObj, shard) != true) {
					IdempotentContext.put(shardId, IdempotentState.NONE);
					idempotentService.save(idempotentObj, shard);
					shards.add(shard);
				} else {
					IdempotentContext.put(shardId, IdempotentState.HAS);
				}
			}
		}
		return shards;
	}

	/**
	 * 多库情况下，选取事务首次请求操作的第一个库作为幂等判断库，执行idempotentId的查询与插入，
	 * 返回不存在idempotent对象的shard列表
	 * 
	 * @param idempotentObject
	 * @param shardList
	 * @return
	 */
	private List<Shard> checkOneAndSave(Object idempotentObject, List<Shard> shardList) {
		if (IdempotentContext.getState() == IdempotentState.NONE) {
			return shardList;
		}
		List<Shard> executeShards = new ArrayList<Shard>();
		if (IdempotentContext.getState() == IdempotentState.DEFAULT) {
			if (shardList.size() != 1) {
				Collections.sort(shardList, new ShardComparable());
			}
			Shard shard = shardList.get(0);
			if (idempotentService.exist(idempotentObject, shard) == false) {
				IdempotentContext.setState(IdempotentState.NONE);
				idempotentService.save(idempotentObject, shard);
				executeShards = shardList;
			} else {
				IdempotentContext.setState(IdempotentState.HAS);
			}
		}
		return executeShards;
	}

	private static class ShardComparable implements Comparator<Shard> {
		@Override
		public int compare(Shard o1, Shard o2) {
			return o1.getId().compareTo(o2.getId());
		}
	}

}
