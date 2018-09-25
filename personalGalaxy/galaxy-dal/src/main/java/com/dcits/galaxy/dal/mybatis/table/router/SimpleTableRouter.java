package com.dcits.galaxy.dal.mybatis.table.router;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dcits.galaxy.dal.mybatis.table.router.rule.TableRoute;
import com.dcits.galaxy.dal.mybatis.table.router.rule.TableRouteGroup;
import com.dcits.galaxy.dal.mybatis.table.tableshard.TableShard;
import com.dcits.galaxy.dal.mybatis.table.tableshard.TableShardManager;

/**
 * 表路由的默认实现
 * 
 * @author huang.zhe
 *
 */
public class SimpleTableRouter implements ITableRouter {
	private transient final Logger logger = LoggerFactory.getLogger(SimpleTableRouter.class);
	private TableShardManager tableShardManager;
	private Map<String, TableRouteGroup> routes = new HashMap<String, TableRouteGroup>();

	public SimpleTableRouter(Set<TableRoute> routeSet) {
		this(routeSet, null);
	}

	public SimpleTableRouter(Set<TableRoute> routeSet, TableShardManager tableShardManager) {
		if (!(routeSet == null || routeSet.isEmpty())) {
			for (TableRoute r : routeSet) {
				if (!routes.containsKey(r.getSqlmap()))
					routes.put(r.getSqlmap(), new TableRouteGroup());
				if (r.getExpression() == null)
					routes.get(r.getSqlmap()).setFallbackRoute(r);
				else
					routes.get(r.getSqlmap()).getSpecificRoutes().add(r);
			}
		}
		this.tableShardManager=tableShardManager;
	}

	@Override
	public Set<String> route(String sqlmap, Object argument) {
		TableRoute resultRoute = findRoute(sqlmap, argument);
		if (resultRoute == null) {
			return Collections.emptySet();
		} else {
			return resultRoute.getTables();
		}
	}

	@Override
	public String getLogicTable(String sqlmap, Object argument) {
		String logicTable = null;
		Set<TableShard> TableShards = routeTableShard(sqlmap, argument);
		for (TableShard ts : TableShards) {
			logicTable = ts.getLogicTable();
			return logicTable;
		}
		return logicTable;
	}

	@Override
	public Set<TableShard> routeTableShard(String sqlmap, Object argument) {
		Set<TableShard> shardSet=new HashSet<TableShard>();
		if (tableShardManager != null && tableShardManager.getShardTableSize() > 0) {
			Set<String> ids = route(sqlmap, argument);
			if (!ids.isEmpty()) {
				for (String id : ids) {
					shardSet.add(tableShardManager.getShardTable(id));
				}
			}
		}
		return shardSet;
	}

	protected TableRoute findRoute(String sqlmap, Object argument) {
		if (sqlmap == null)
			return null;
		TableRoute result = null;

		if (routes.containsKey(sqlmap)) {
			TableRouteGroup routeGroup = routes.get(sqlmap);
			for (TableRoute route : routeGroup.getSpecificRoutes()) {
				if (route.apply(sqlmap, argument)) {
					result = route;
				}
			}
			if (result == null)
				result = routeGroup.getFallbackRoute();
		} else {
			if (sqlmap.lastIndexOf(".") == -1) {
				logger.debug("sqlmap未包含'.', 确认sqlmap格式正确?");
				return null;
			}

			String namespace = sqlmap.substring(0, sqlmap.lastIndexOf("."));
			if (routes.containsKey(namespace)) {
				TableRouteGroup routeGroup = routes.get(namespace);
				for (TableRoute route : routeGroup.getSpecificRoutes()) {
					if (route.apply(namespace, argument)) {
						result = route;
					}
				}
				if (result == null)
					result = routeGroup.getFallbackRoute();
			}
		}
		return result;
	}

	public TableShardManager getTableShardManager() {
		return tableShardManager;
	}

	public void setTableShardManager(TableShardManager tableShardManager) {
		this.tableShardManager = tableShardManager;
	}
}
