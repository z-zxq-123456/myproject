package com.dcits.galaxy.dal.mybatis.transaction;

import static com.dcits.galaxy.dal.mybatis.transaction.DynamicBestEffortMultiDataSourceTransactionManager.getInstance;

import org.springframework.beans.factory.FactoryBean;

/**
 * 基于一阶段提交的事务管理
 * 与BestEffortMultiDataSourceTransactionManager不同点为：
 * MultiDataSourceTransactionManager只在实际使用某个DataSource时
 * 才开启对应的事务
 *
 * @author fan.kaijie
 */
@Deprecated
public class MultiDataSourceTransactionManager implements FactoryBean<DynamicBestEffortMultiDataSourceTransactionManager> {

	@Override
	public DynamicBestEffortMultiDataSourceTransactionManager getObject() throws Exception {
		if(getInstance() == null) {
			return new DynamicBestEffortMultiDataSourceTransactionManager();
		}
		return getInstance();
	}

	@Override
	public Class<?> getObjectType() {
		return DynamicBestEffortMultiDataSourceTransactionManager.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}
