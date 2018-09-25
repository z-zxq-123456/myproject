package com.dcits.galaxy.dal.mybatis.router.simple;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.dcits.galaxy.dal.mybatis.router.AbstractRouter;
import com.dcits.galaxy.dal.mybatis.router.simple.rule.Route;
import com.dcits.galaxy.dal.mybatis.router.simple.rule.RouteGroup;
import com.dcits.galaxy.dal.mybatis.shard.ShardManager;

/**
 * IRouter 接口的默认实现 若未设置shardManager域，则routeShard方法总是返回null
 * @author fan.kaijie
 */
public class SimpleRouter extends AbstractRouter {

	/**
	 * 以sqlmap为key的集合，记录相应的sqlmap对应的RouteGroup
	 */
	private Map<String, RouteGroup> routes = new HashMap<String, RouteGroup>();

	public SimpleRouter(Set<Route> routeSet) {
		this(routeSet, null);
	}

	/**
	 * 根据routeSet、shardManager初始化一个实例，其中routeSet会被转换为Map<String, RouteGroup>
	 * @param routeSet
	 * @param shardManager
	 */
	public SimpleRouter(Set<Route> routeSet, ShardManager shardManager) {
		super(shardManager);
		if (!(routeSet == null || routeSet.isEmpty())) {
			for (Route r : routeSet) {
				if (!routes.containsKey(r.getSqlmap()))
					routes.put(r.getSqlmap(), new RouteGroup());
				if (r.getExpression() == null)
					routes.get(r.getSqlmap()).setFallbackRoute(r);
				else
					routes.get(r.getSqlmap()).getSpecificRoutes().add(r);
			}
		}
	}

	public void setRoutes(Map<String, RouteGroup> routes) {
		this.routes = routes;
	}

	@Override
	protected Set<String> doRoute(String sqlmap, Object argument) {
		Route route = findRoute(sqlmap, argument);
		if(route == null) {
			return Collections.emptySet();
		}
		return route.getShards();
	}

	@Override
	protected boolean routeExist(String sqlmap) {
		return routes.containsKey(sqlmap);
	}
	
	protected Route findRoute(String sqlmap, Object argument) {
		if (sqlmap == null) {
			return null;
		}

		if (!routes.containsKey(sqlmap)) {
			return null;
		}
		
		RouteGroup routeGroup = routes.get(sqlmap);
		for (Route route : routeGroup.getSpecificRoutes()) {
			if (route.apply(sqlmap, argument)) {
				return route;
			}
		}
		return routeGroup.getFallbackRoute();
	}
}
