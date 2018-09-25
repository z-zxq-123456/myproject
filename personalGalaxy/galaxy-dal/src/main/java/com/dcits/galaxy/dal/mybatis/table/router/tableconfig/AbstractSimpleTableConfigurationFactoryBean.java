package com.dcits.galaxy.dal.mybatis.table.router.tableconfig;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.util.ObjectUtils;

import com.dcits.galaxy.dal.mybatis.table.tableshard.TableShard;
import com.dcits.galaxy.dal.mybatis.table.tableshard.TableShardManager;

/**
 * 解析TableShardManager的外部配置文件的抽象类 子类应该实现loadRulesFromExternal以从不同格式的配置文件
 * 中读取并生成TableShardManager实例
 * 
 * @author huang.zhe
 */
public abstract class AbstractSimpleTableConfigurationFactoryBean implements FactoryBean<TableShardManager>, InitializingBean {
	private TableShardManager tableShardManager;

	private Resource configLocation;

	private Resource[] configLocations;

	/**
	 * 通过此方法解析相应的外部配置文件，子类可以实现此方法以从不同格式的文件中读取配置信息
	 * 
	 * @param configLocation
	 *            配置文件路径
	 * @return 所解析路由规则的集合
	 * @throws IOException
	 */
	protected abstract Set<TableShard> loadRulesFromExternal(Resource configLocation)
		throws IOException;

	public void afterPropertiesSet()
		throws IOException {

		Set<TableShard> shardTableSet = new HashSet<TableShard>();
		if (getConfigLocation() != null) {
			Set<TableShard> tableShards = loadRulesFromExternal(getConfigLocation());
			if (tableShards != null && tableShards.size() > 0) {
				shardTableSet.addAll(tableShards);
			}
		}

		if (!ObjectUtils.isEmpty(getConfigLocations())) {
			for (Resource res : getConfigLocations()) {
				Set<TableShard> tableShards = loadRulesFromExternal(res);
				if (tableShards != null && tableShards.size() > 0) {
					shardTableSet.addAll(tableShards);
				}
			}
		}

		this.tableShardManager = new TableShardManager(shardTableSet);

	}

	public TableShardManager getObject() {
		return this.tableShardManager;
	}

	public Class<?> getObjectType() {
		return TableShardManager.class;
	}

	public boolean isSingleton() {
		return true;
	}

	public Resource getConfigLocation() {
		return configLocation;
	}

	public void setConfigLocation(Resource configLocation) {
		this.configLocation = configLocation;
	}

	public Resource[] getConfigLocations() {
		return configLocations;
	}

	public void setConfigLocations(Resource[] configLocations) {
		this.configLocations = configLocations;
	}

}
