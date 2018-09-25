package com.dcits.galaxy.dal.mybatis.table.router.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.util.ObjectUtils;

import com.dcits.galaxy.dal.mybatis.table.router.SimpleTableRouter;
import com.dcits.galaxy.dal.mybatis.table.router.rule.TableRoute;
import com.dcits.galaxy.dal.mybatis.table.tableshard.TableShardManager;

/**
 * 解析SimpleTableRouter的外部配置文件的抽象类 子类应该实现loadRulesFromExternal以从不同格式的配置文件
 * 中读取并生成SimpleTableRouter实例
 * 
 * @author huang.zhe
 *
 */
public abstract class AbstractSimpleTableRouterConfigurationFactoryBean implements FactoryBean<SimpleTableRouter>, InitializingBean {
	private SimpleTableRouter simpleTableRouter;

	private Resource configLocation;

	private Resource[] configLocations;

	private Map<String, Object> functionsMap = new HashMap<String, Object>();

	private TableShardManager tableShardManager;

	/**
	 * 通过此方法解析相应的外部配置文件，子类可以实现此方法以从不同格式的文件中读取配置信息
	 * 
	 * @param configLocation
	 *            配置文件路径
	 * @return 所解析路由规则的集合
	 * @throws IOException
	 */
	protected abstract Set<TableRoute> loadRulesFromExternal(Resource configLocation)
		throws IOException;

	public void afterPropertiesSet() throws IOException {

		Set<TableRoute> tableRouteSet = new HashSet<TableRoute>();
		if (getConfigLocation() != null) {
			Set<TableRoute> routes = loadRulesFromExternal(getConfigLocation());
			if (routes != null && routes.size() > 0) {
				tableRouteSet.addAll(routes);
			}
		}

		if (!ObjectUtils.isEmpty(getConfigLocations())) {
			for (Resource res : getConfigLocations()) {
				Set<TableRoute> routes = loadRulesFromExternal(res);
				if (routes != null && routes.size() > 0) {
					tableRouteSet.addAll(routes);
				}
			}
		}

		this.simpleTableRouter = new SimpleTableRouter(tableRouteSet);
		this.simpleTableRouter.setTableShardManager(tableShardManager);

	}

	public SimpleTableRouter getObject() {
		return this.simpleTableRouter;
	}

	public Class<?> getObjectType() {
		return SimpleTableRouter.class;
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

	public Map<String, Object> getFunctionsMap() {
		return functionsMap;
	}

	public void setFunctionsMap(Map<String, Object> functionsMap) {
		this.functionsMap = functionsMap;
	}

	public TableShardManager getTableShardManager() {
		return tableShardManager;
	}

	public void setTableShardManager(TableShardManager tableShardManager) {
		this.tableShardManager = tableShardManager;
	}

}
