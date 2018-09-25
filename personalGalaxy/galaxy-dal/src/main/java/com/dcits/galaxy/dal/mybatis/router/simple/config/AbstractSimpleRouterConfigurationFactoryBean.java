package com.dcits.galaxy.dal.mybatis.router.simple.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.util.ObjectUtils;

import com.dcits.galaxy.dal.mybatis.router.simple.SimpleRouter;
import com.dcits.galaxy.dal.mybatis.router.simple.rule.Route;
import com.dcits.galaxy.dal.mybatis.shard.ShardManager;

/**
 * 解析SimpleRouter的外部配置文件的抽象类
 * 子类应该实现loadRulesFromExternal以从不同格式的配置文件
 * 中读取并生成SimpleRouter实例
 * @author fan.kaijie
 *
 */
public abstract class AbstractSimpleRouterConfigurationFactoryBean implements
		FactoryBean<SimpleRouter>, InitializingBean {

	private transient final Logger logger = LoggerFactory.getLogger(AbstractSimpleRouterConfigurationFactoryBean.class);

	private SimpleRouter simpleRouter;
	private ShardManager shardManager;
	private Resource configLocation;
	private Resource[] configLocations;

	private Map<String, Object> functionsMap = new HashMap<String, Object>();

	/**
	 * 通过此方法解析相应的外部配置文件，子类可以实现此方法以从不同格式的文件中读取配置信息
	 * @param configLocation 配置文件路径
	 * @return 所解析路由规则的集合
	 * @throws IOException
	 */
	protected abstract Set<Route> loadRulesFromExternal(Resource configLocation)
			throws IOException;

	public void afterPropertiesSet() throws IOException  {

		Set<Route> routeSet = new HashSet<Route>();
		if (getConfigLocation() != null) {
			logger.debug("start parse route file " + getConfigLocation().getFilename());
			Set<Route> routes = loadRulesFromExternal(getConfigLocation());
			if( routes != null && routes.size() > 0){
				routeSet.addAll(routes);
			}
			logger.debug("end parse route file " + getConfigLocation().getFilename());
		}

		if (!ObjectUtils.isEmpty(getConfigLocations())) {
			for (Resource res : getConfigLocations()) {
				logger.debug("start parse route file " + res.getFilename());
				Set<Route> routes = loadRulesFromExternal(res);
				if( routes != null && routes.size() > 0){
					routeSet.addAll(routes);
				}
				logger.debug("end parse route file " + res.getFilename());

			}
		}

		this.simpleRouter = new SimpleRouter(routeSet);
		this.simpleRouter.setShardManager(shardManager);
	}

	public SimpleRouter getObject() {
		return this.simpleRouter;
	}

	public Class<?> getObjectType() {
		return SimpleRouter.class;
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
	public ShardManager getShardManager() {
		return shardManager;
	}

	public void setShardManager(ShardManager shardManager) {
		this.shardManager = shardManager;
	}
}
