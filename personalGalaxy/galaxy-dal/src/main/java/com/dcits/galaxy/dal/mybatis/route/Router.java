package com.dcits.galaxy.dal.mybatis.route;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

import com.dcits.galaxy.dal.mybatis.exception.DALException;
import com.dcits.galaxy.dal.mybatis.exception.NoShardFoundException;
import com.dcits.galaxy.dal.mybatis.route.rule.RouteBasis;
import com.dcits.galaxy.dal.mybatis.route.rule.RouteRule;
import com.dcits.galaxy.dal.mybatis.route.rule.RouteRuleParse;
import com.dcits.galaxy.dal.mybatis.router.IRouter;
import com.dcits.galaxy.dal.mybatis.shard.Shard;

/**
 * IRouter 接口的默认实现
 * 
 * @author chen.hong
 *
 */
public class Router implements IRouter {

	private transient final Logger logger = LoggerFactory.getLogger(Router.class);
	
	private Map<String,String> aliasMap = new ConcurrentHashMap<>();
	/**
	 * 路由规则路径
	 */
	private Resource configLocation;
	/**
	 * 路由规则路径
	 */
	private Resource[] configLocations;
	/**
	 * 路由函数管理
	 */
	private RouteFunctionManager routeFunctionManager;
	/**
	 * 以sqlmap为key的集合，记录相应的sqlmap对应的Route
	 */
	private Map<String, RouteRule> routes = new HashMap<String, RouteRule>();

	public Router() {

	}

	public Router(Resource configLocation, Resource[] configLocations, RouteFunctionManager routeFunctionManager) {
		this.configLocation = configLocation;
		this.configLocations = configLocations;
		this.routeFunctionManager = routeFunctionManager;
	}


	/**
	 * 初始化加载xml文件中的rule
	 */
	public void init() {
		Map<String, Object> routeFunctionMap = this.getRouteFunctionManager().getFunctionMap();
		Set<RouteRule> routeSet = new HashSet<RouteRule>();
		if (getConfigLocation() != null) {
			logger.debug("start parse route file " + getConfigLocation());
			Set<RouteRule> routes;
			routes = RouteRuleParse.loadRules(configLocation,routeFunctionMap);
			if (routes != null && routes.size() > 0) {
				routeSet.addAll(routes);
			}
			logger.debug("end parse route file " + getConfigLocation());
		}
		if (null != getConfigLocations() && getConfigLocations().length > 0) {
			for (Resource res : getConfigLocations()) {
				logger.debug("start parse route file " + res);
				Set<RouteRule> routes;
				routes = RouteRuleParse.loadRules(res,routeFunctionMap);
				if (routes != null && routes.size() > 0) {
					routeSet.addAll(routes);
				}
				logger.debug("end parse route file " + res);
			}
		}
		initRouter(routeSet);
	}

	/**
	 * 根据routeSet初始化一个实例，其中routeSet会被转换为Map<String, RouteRule>
	 * 
	 * @param routeSet
	 */
	private void initRouter(Set<RouteRule> routeSet) {
		if (!(routeSet == null || routeSet.isEmpty())) {
			for (RouteRule r : routeSet) {
				List<RouteBasis> routeBasis = r.getRouteBasis();
				for (RouteBasis routeBas : routeBasis) {
					if (!routes.containsKey(routeBas.getBasis())) {
						routes.put(routeBas.getBasis(), r);
					} else {
						throw new RouteException(
								"a namespace or sqlmap most one of 'rule' must be given: " + routeBas.getBasis());
					}
				}
			}
		}
	}
	
	/**
	 * 以sqlmap与argument共同确定一组分库id
	 * @param sqlmap
	 * @param argument 当参数为基本类型或String时，请将数据放入Map中
	 * @return Set<String> 返回一组分库id
	 */
	@Override
	public Set<String> route(String sqlmap, Object argument) {
		if (argument instanceof Collection) {
			Collection<?> params = (Collection<?>) argument;
			Set<String> shards = new HashSet<>();
			for (Object param : params) {
				Set<String> tempShardSet = applyRoute(sqlmap, param);
				if (!tempShardSet.isEmpty()) {
					shards.addAll(tempShardSet);
				}
			}
			return shards;
		} else {
			return applyRoute(sqlmap, argument);
		}
	}

	/**
	 * 返回当前sqlmap与argument所匹配的规则
	 */
	@Override
	public String getMatchSqlmap(String sqlmap, Object argument) {
		if (argument instanceof Collection) {
			return null;
		}
		String routeId = getRouteId(sqlmap);
		if (routeId != null && !doRoute(routeId, argument).isEmpty()) {
			return routeId;
		}
		String namespace = getNameSpace(sqlmap);
		routeId = getRouteId(namespace);
		if (routeId != null && !doRoute(routeId, argument).isEmpty()) {
			return routeId;
		}
		return null;
	}

	/**
	 * 返回是否存在当前sqlmap的路由规则
	 */
	@Override
	public boolean isRouteExist(String sqlmap) {
		if (getRouteId(sqlmap) != null) {
			return true;
		}
		String namespace = getNameSpace(sqlmap);
		if (getRouteId(namespace) != null) {
			return true;
		}

		return false;
	}
	
	/**
	 * 给sourceSqlId生成别名
	 * @param sourceSqlId 原Sqlid
	 * @param alias 别名
	 */
	@Override
	public void alias(String sourceSqlId, String alias) {
		if (getRouteId(sourceSqlId) == null) {
			throw new DALException("source sql id:" + sourceSqlId + " not exist!");
		}
		if (routeExist(alias)) {
			throw new DALException("sql id:" + alias + "hava exist!");
		}
		if (aliasMap.containsKey(alias)) {
			throw new DALException("alias:" + alias + "hava exist!");
		}
		aliasMap.put(alias, sourceSqlId);
	}
	
	/**
     * 返回由sqlmap与argument匹配的一组Shard
     * @param sqlmap
     * @param argument 当参数为基本类型或String时，请将数据放入Map中
     * @return Set<Shard>
     */
	@Deprecated 
	@Override
	public Set<Shard> routeShard(String sqlmap, Object argument) {
		throw new NoShardFoundException("this method has expired!");
	}
	
	/**
	 * 以sqlmap与argument共同确定一组分库id
	 * 
	 * @param sqlmap
	 * @param argument
	 * @return Set<String>一组分库id
	 */
	private Set<String> applyRoute(String sqlmap, Object argument) {
		Set<String> result = new HashSet<>();
		String routeId = getRouteId(sqlmap);
		if (routeId != null) {
			result = doRoute(routeId, argument);
		}
		if (result.isEmpty()) {
			String namespace = getNameSpace(sqlmap);
			routeId = getRouteId(namespace);
			if (routeId != null) {
				result = doRoute(routeId, argument);
			}
		}
		return result;
	}
	
	/**
	 * 根据路由名进行路由获得分库信息
	 */
	private Set<String> doRoute(String sqlmap, Object argument) {
		Set<String> shards = findRouteShard(sqlmap, argument);
		if (shards == null) {
			return Collections.emptySet();
		}
		return Collections.unmodifiableSet(shards);
	}

	/**
	 * 根据路由规则查找分库结果集
	 * 
	 * @param sqlmap
	 * @param argument
	 * @return 当结果集不为空时返回Set<String>类型；为空则返回null
	 */
	private Set<String> findRouteShard(String sqlmap, Object argument) {
		Set<String> result = null;
		RouteRule routeRule = routes.get(sqlmap);
		if (null != routeRule) {
			String shardName = "";
			if (routeRule.hasExpression()) {
				shardName = routeRule.apply(sqlmap, argument);
			}
			result = routeRule.getShards(shardName);
		}
		return result;
	}
	
	/**
	 * 根据路由名判断路由是否存在
	 */
	private boolean routeExist(String sqlmap) {
		return routes.containsKey(sqlmap);
	}
	
	/**
	 * 根据SqlId 获得路由对应的salmap或者namespace
	 * @param sourceSqlId
	 * @return
	 */
	private String getRouteId(String sourceSqlId) {
		if (sourceSqlId == null) {
			return null;
		}
		if (routeExist(sourceSqlId)) {
			return sourceSqlId;
		}
		if (aliasMap.containsKey(sourceSqlId)) {
			return getRouteId(aliasMap.get(sourceSqlId));
		}
		return null;
	}
	
	/**
	 * 获得sqlmap对应的namespace
	 * @param sqlId
	 * @return
	 */
	private String getNameSpace(String sqlId) {
		if (sqlId.lastIndexOf(".") == -1) {
			return null;
		}
		return sqlId.substring(0, sqlId.lastIndexOf("."));
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

	public Map<String, RouteRule> getRoutes() {
		return routes;
	}
	
	public void setRoutes(Map<String, RouteRule> routes) {
		this.routes = routes;
	}

	public RouteFunctionManager getRouteFunctionManager() {
		return routeFunctionManager;
	}

	public void setRouteFunctionManager(RouteFunctionManager routeFunctionManager) {
		this.routeFunctionManager = routeFunctionManager;
	}
	
	
}
