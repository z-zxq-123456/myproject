package com.dcits.galaxy.dal.mybatis.idempotent.dao;

import com.dcits.galaxy.dal.mybatis.shard.Shard;

/**
 * 幂等性Dao方法接口定义
 * 
 * @author huang.zhe
 *
 */
public interface IdempotentDao {

	public int selectCount(Object entity, Shard shard);

	public int insert(Object entity, Shard shard);
}
