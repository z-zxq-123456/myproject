package com.dcits.galaxy.dal.mybatis.proactor;

import java.util.List;

import com.dcits.galaxy.dal.mybatis.ShardSqlSessionTemplate;
import com.dcits.galaxy.dal.mybatis.proactor.config.vo.ProactorSetting;

/**
 * 查询前置处理器，在执行查询修改查询语句、调整查询参数等操作。
 * @author yin.weicai
 *
 */
public interface Proactor {

	<E> List<E> execute(String statement, Object parameter , ProactorSetting setting, ShardSqlSessionTemplate shardSqlSessionTempalte);
}
