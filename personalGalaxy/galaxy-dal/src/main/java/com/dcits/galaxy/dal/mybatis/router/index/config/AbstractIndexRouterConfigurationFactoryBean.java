package com.dcits.galaxy.dal.mybatis.router.index.config;

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

import com.dcits.galaxy.dal.mybatis.router.index.IndexRouter;
import com.dcits.galaxy.dal.mybatis.router.index.rule.IndexRoute;
import com.dcits.galaxy.dal.mybatis.shard.ShardManager;

/**
 * 解析MultiFieldRouter的外部配置文件的抽象类
 * 子类应该实现loadRulesFromExternal以从不同格式的配置文件
 * 中读取并生成MultiFieldRouter实例
 * @author huang.zhe
 *
 */
public abstract class AbstractIndexRouterConfigurationFactoryBean implements
		FactoryBean<IndexRouter>, InitializingBean {

	private transient final Logger logger = LoggerFactory.getLogger(AbstractIndexRouterConfigurationFactoryBean.class);

	private IndexRouter multiFieldRouter;
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
	protected abstract Set<IndexRoute> loadRulesFromExternal(Resource configLocation)
			throws IOException;

	public void afterPropertiesSet() throws IOException  {

		Set<IndexRoute> routeSet = new HashSet<IndexRoute>();
		if (getConfigLocation() != null) {
			logger.debug("start parse route file " + getConfigLocation().getFilename());
			Set<IndexRoute> routes = loadRulesFromExternal(getConfigLocation());
			if( routes != null && routes.size() > 0){
				routeSet.addAll(routes);
			}
			logger.debug("end parse route file " + getConfigLocation().getFilename());
		}

		if (!ObjectUtils.isEmpty(getConfigLocations())) {
			for (Resource res : getConfigLocations()) {
				logger.debug("start parse route file " + res.getFilename());
				Set<IndexRoute> routes = loadRulesFromExternal(res);
				if( routes != null && routes.size() > 0){
					routeSet.addAll(routes);
				}
				logger.debug("end parse route file " + res.getFilename());

			}
		}

		this.multiFieldRouter = new IndexRouter(routeSet);
		this.multiFieldRouter.setShardManager(shardManager);
	}

	public IndexRouter getObject() {
		return this.multiFieldRouter;
	}

	public Class<?> getObjectType() {
		return IndexRouter.class;
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
