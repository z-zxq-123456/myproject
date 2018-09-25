package com.dcits.galaxy.dal.mybatis.router.adapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.dcits.galaxy.dal.mybatis.exception.DALException;
import com.dcits.galaxy.dal.mybatis.router.IRouter;
import com.dcits.galaxy.dal.mybatis.shard.Shard;

/**
 * @author huang.zhe
 */
public class AdapterRouter implements IRouter {

	private List<IRouter> routers = new ArrayList<IRouter>();

	public AdapterRouter() {
	}

	public AdapterRouter(List<IRouter> routerList) {
		this.routers = routerList;
	}

	public void addRouter(IRouter router) {
		this.routers.add(router);
	}

	@Override
	public Set<String> route(String sqlmap, Object argument) {
		Set<String> r = null;
		for (IRouter router : routers) {
			r = router.route(sqlmap, argument);
			if (!r.isEmpty())
				return r;
		}
		return Collections.emptySet();
	}

	@Override
	public Set<Shard> routeShard(String sqlmap, Object argument) {
		Set<Shard> r = new HashSet<Shard>();
		for (IRouter router : routers) {
			r = router.routeShard(sqlmap, argument);
			if (!r.isEmpty()) {
				break;
			}
		}
		return r;
	}

	@Override
	public String getMatchSqlmap(String sqlmap, Object argument) {
		String r = null;
		for (IRouter router : routers) {
			r = router.getMatchSqlmap(sqlmap, argument);
			if (r != null) {
				break;
			}
		}
		return r;
	}

	@Override
	public boolean isRouteExist(String sqlmap) {
		boolean r = false;
		for (IRouter router : routers) {
			r = router.isRouteExist(sqlmap);
			if (r == true)
				break;
		}
		return r;
	}

	@Override
	public void alias(String sourceSqlId, String alias) {
		for (IRouter router : routers) {
			if(router.isRouteExist(sourceSqlId)){
				try {
					router.alias(sourceSqlId, alias);
				} catch (DALException e) {
					// no-do
				}
			}
		}
	}

}
