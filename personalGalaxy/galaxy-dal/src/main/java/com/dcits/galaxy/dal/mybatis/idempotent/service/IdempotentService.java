package com.dcits.galaxy.dal.mybatis.idempotent.service;

import com.dcits.galaxy.dal.mybatis.shard.Shard;

/**
 * 幂等服务接口定义
 * 
 * @author HuangZhe
 */
public interface IdempotentService {

	/**
	 * 判断shard对应的库中是否存在该idempotent对象
	 * @param idempotent
	 * @param shard
	 * @return boolean
	 */
	boolean exist(Object idempotent, Shard shard);

	/**
	 * 将idempotent对象写入shard对应的库
	 * @param idempotent
	 * @param shard
	 */
	int save(Object idempotent, Shard shard);

}
