package com.dcits.galaxy.dal.mybatis.router.adapter.config;

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

import com.dcits.galaxy.dal.mybatis.router.adapter.AdapterRouter;
import com.dcits.galaxy.dal.mybatis.router.adapter.config.vo.InternalRules;
import com.dcits.galaxy.dal.mybatis.router.index.IndexRouter;
import com.dcits.galaxy.dal.mybatis.router.index.rule.IndexRoute;
import com.dcits.galaxy.dal.mybatis.router.simple.SimpleRouter;
import com.dcits.galaxy.dal.mybatis.router.simple.rule.Route;
import com.dcits.galaxy.dal.mybatis.shard.ShardManager;

/**
 * 解析MixRouter的外部配置文件的抽象类
 * 子类应该实现loadRulesFromExternal以从不同格式的配置文件
 * 中读取并生成MixRouter实例
 * @author huang.zhe
 *
 */
public abstract class AbstractAdapterRouterConfigurationFactoryBean implements
		FactoryBean<AdapterRouter>, InitializingBean {

	private transient final Logger logger = LoggerFactory.getLogger(AbstractAdapterRouterConfigurationFactoryBean.class);

	private AdapterRouter mixRouter;
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
	protected abstract Map<InternalRules.RulesType,Object> loadRulesFromExternal(Resource configLocation)
			throws IOException;

	public void afterPropertiesSet() throws IOException  {

		Set<Route> defaultRoutes =new HashSet<Route>();
		Set<IndexRoute> indexRoutes= new HashSet<IndexRoute>();
		
		if (getConfigLocation() != null) {
			logger.debug("start parse route file " + getConfigLocation().getFilename());
			Map<InternalRules.RulesType,Object> routes = loadRulesFromExternal(getConfigLocation());
			addRoute(routes, defaultRoutes, indexRoutes);
			logger.debug("end parse route file " + getConfigLocation().getFilename());
		}

		if (!ObjectUtils.isEmpty(getConfigLocations())) {
			for (Resource res : getConfigLocations()) {
				logger.debug("start parse route file " + res.getFilename());
				Map<InternalRules.RulesType,Object> routes = loadRulesFromExternal(res);
				addRoute(routes, defaultRoutes, indexRoutes);
				logger.debug("end parse route file " + res.getFilename());

			}
		}
		
		mixRouter =new AdapterRouter();
		
		if (!defaultRoutes.isEmpty()) {
			SimpleRouter simpleRouter = new SimpleRouter(defaultRoutes);
			simpleRouter.setShardManager(shardManager);
			mixRouter.addRouter(simpleRouter);
		}
		if (!indexRoutes.isEmpty()) {
			IndexRouter fieldRouter = new IndexRouter(indexRoutes);
			fieldRouter.setShardManager(shardManager);
			mixRouter.addRouter(fieldRouter);
		}
		
		
	}

	public AdapterRouter getObject() {
		return this.mixRouter;
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
	
	@SuppressWarnings("unchecked")
	private void addRoute(Map<InternalRules.RulesType, Object> routes, Set<Route> defaultRoutes, Set<IndexRoute> multiFieldRoutes) {
		if (routes != null && routes.size() > 0) {
			for (InternalRules.RulesType s : routes.keySet()) {
				if (s == InternalRules.RulesType.SIMPLE_ROUTER) {
					Set<Route> r = (Set<Route>) routes.get(s);
					if (r != null && r.size() > 0) {
						defaultRoutes.addAll(r);
					}
				} else if (s == InternalRules.RulesType.INDEX_ROUTER) {
					Set<IndexRoute> fr = (Set<IndexRoute>) routes.get(s);
					if (fr != null && fr.size() > 0) {
						multiFieldRoutes.addAll(fr);
					}
				}
			}
		}
	}
	
	
}
