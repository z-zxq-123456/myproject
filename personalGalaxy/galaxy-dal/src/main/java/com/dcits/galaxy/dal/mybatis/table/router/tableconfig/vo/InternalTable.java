package com.dcits.galaxy.dal.mybatis.table.router.tableconfig.vo;

import java.util.List;

/**
 * @author huang.zhe
 *
 */
public class InternalTable {

	private String table;
	List<InternalTableShard> tableShards;



	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public List<InternalTableShard> getTableShards() {
		return tableShards;
	}

	public void setTableShards(List<InternalTableShard> tableShards) {
		this.tableShards = tableShards;
	}

	@Override
	public String toString() {
		return "InternalTable [logicTable=" + table + ", tableShards="
				+ tableShards + "]";
	}

}
