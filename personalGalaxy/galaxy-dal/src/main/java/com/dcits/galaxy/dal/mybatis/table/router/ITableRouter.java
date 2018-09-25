package com.dcits.galaxy.dal.mybatis.table.router;

import java.util.Set;

import com.dcits.galaxy.dal.mybatis.table.tableshard.TableShard;

/**
 * @author huang.zhe
 *
 */
public interface ITableRouter {

	Set<String> route(String sqlmap, Object argument);

	String getLogicTable(String sqlmap, Object argument);

	Set<TableShard> routeTableShard(String sqlmap, Object argument);

}
