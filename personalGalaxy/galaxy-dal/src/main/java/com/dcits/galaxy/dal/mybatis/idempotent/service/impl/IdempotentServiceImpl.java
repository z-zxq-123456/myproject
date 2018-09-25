package com.dcits.galaxy.dal.mybatis.idempotent.service.impl;

import com.dcits.galaxy.dal.mybatis.idempotent.dao.IdempotentDao;
import com.dcits.galaxy.dal.mybatis.idempotent.service.IdempotentService;
import com.dcits.galaxy.dal.mybatis.shard.Shard;

/**
 * 幂等服务接口实现
 * 
 * @author huang.zhe
 *
 */
public class IdempotentServiceImpl implements IdempotentService {
	private IdempotentDao idempotentDao;
	
	public IdempotentDao getIdempotentDao() {
		return idempotentDao;
	}

	public void setIdempotentDao(IdempotentDao idempotentDao) {
		this.idempotentDao = idempotentDao;
	}

	/**
	 * 将idempotentId写入指定shard对应的表中，shard为null，则为单库情况下写入
	 */
	@Override
	public int save(Object idempotentId, Shard shard) {
		return idempotentDao.insert(idempotentId, shard);
	}

	/**
	 * 根据传入的shard判断其库对应的表中是否存在idempotentId，若shard为null，则为单库，对默认库进行检查
	 */
	public boolean exist(Object idempotentId, Shard shard) {
		int count = idempotentDao.selectCount(idempotentId, shard);
		if (count != 0) {
			return true;
		}
		return false;
	}

}
