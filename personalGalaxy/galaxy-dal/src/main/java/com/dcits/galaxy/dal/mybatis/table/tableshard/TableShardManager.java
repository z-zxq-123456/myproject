package com.dcits.galaxy.dal.mybatis.table.tableshard;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
/**
 * 封装所有的分表信息，提供相应的访问方法
 * @author huang.zhe
 *
 */
public class TableShardManager {

	private Map<String, TableShard> tableShardMap = null;

	public TableShardManager() {
	}

	public TableShardManager(Map<String, TableShard> tableShardMap) {
		this.tableShardMap = tableShardMap;
	}

	public TableShardManager(Set<TableShard> tableShards) {
		setTableShards(tableShards);
	}

	public void setShardTableMap(Map<String, TableShard> shardTableMap) {
		this.tableShardMap = shardTableMap;
	}

	public void setTableShards(Set<TableShard> tableShards) {
		if (this.tableShardMap == null)
			this.tableShardMap = new HashMap<String, TableShard>();
		for (TableShard tableShard : tableShards) {
			tableShardMap.put(tableShard.getId(), tableShard);
		}
	}

	/**
	 * 判断是否存在对应id的TableShard
	 * @param id
	 * @return boolean
	 */
	public boolean hasShardTable(String id) {
		return tableShardMap.containsKey(id);
	}

	/**
	 * 判断是否持有此TableShard
	 * @param shard
	 * @return boolean
	 */
	public boolean hasShardTable(TableShard shardTable) {
		return tableShardMap.containsValue(shardTable);
	}

	/**
	 * 通过id获取相应的TableShard
	 * @param id
	 * @return TableShard
	 */
	public TableShard getShardTable(String id) {
		return tableShardMap.get(id);
	}

	/**
	 * 获取包含的TableShard总数
	 * @return int TableShard总数
	 */
	public int getShardTableSize() {
		return tableShardMap.size();
	}

	/**
	 * 返回所有的TableShard的id
	 * @return Set<String>
	 */
	public Set<String> getShardTableIds() {
		return tableShardMap.keySet();
	}

}
