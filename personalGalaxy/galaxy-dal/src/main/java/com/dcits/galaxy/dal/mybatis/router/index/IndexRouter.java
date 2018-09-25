package com.dcits.galaxy.dal.mybatis.router.index;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dcits.galaxy.dal.mybatis.router.AbstractRouter;
import com.dcits.galaxy.dal.mybatis.router.index.rule.IndexRoute;
import com.dcits.galaxy.dal.mybatis.router.index.rule.IndexRouteGroup;
import com.dcits.galaxy.dal.mybatis.shard.ShardManager;

/**
 * IRouter 接口实现 若未设置shardManager域，则routeShard方法总是返回null
 * @author huang.zhe
 */
public class IndexRouter extends AbstractRouter {

	/**
	 * 以sqlmap为key的集合，记录相应的sqlmap对应的RouteGroup
	 */
	private Map<String, IndexRouteGroup> routes = new HashMap<String, IndexRouteGroup>();
	
	public IndexRouter(Set<IndexRoute> routeSet) {
		this(routeSet, null);
	}

	/**
	 * 根据routeSet、shardManager初始化一个实例，其中routeSet会被转换为Map<String, RouteGroup>
	 * @param routeSet
	 * @param shardManager
	 */
	public IndexRouter(Set<IndexRoute> routeSet, ShardManager shardManager) {
		super(shardManager);
		if (!(routeSet == null || routeSet.isEmpty())) {
			for (IndexRoute r : routeSet) {
				if (!routes.containsKey(r.getSqlmap()))
					routes.put(r.getSqlmap(), new IndexRouteGroup());
				if (r.getExpression() == null)
					routes.get(r.getSqlmap()).setFallbackRoute(r);
				else
					routes.get(r.getSqlmap()).getSpecificRoutes().add(r);
			}
		}
	}

	public void setRoutes(Map<String, IndexRouteGroup> routes) {
		this.routes = routes;
	}

	@Override
	protected Set<String> doRoute(String sqlmap, Object argument) {
		if (routes.containsKey(sqlmap)) {
			IndexRouteGroup routeGroup = routes.get(sqlmap);
			for (IndexRoute route : routeGroup.getSpecificRoutes()) {
				List<String> r = route.apply(sqlmap, argument);
				if (r != null && !r.isEmpty()) {
					return new HashSet<>(r);
				}
			}
			IndexRoute fallbackRoute=routeGroup.getFallbackRoute();
			if (fallbackRoute != null && fallbackRoute.getShards() != null) {
				return new HashSet<>(fallbackRoute.getShards());
			}
		}
		return Collections.emptySet();
	}

	@Override
	protected boolean routeExist(String sqlmap) {
		return routes.containsKey(sqlmap);
	}
}
