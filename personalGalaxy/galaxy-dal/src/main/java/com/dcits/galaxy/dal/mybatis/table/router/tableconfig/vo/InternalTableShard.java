package com.dcits.galaxy.dal.mybatis.table.router.tableconfig.vo;

/**
 * @author huang.zhe
 *
 */
public class InternalTableShard {
	private String id;
	private String name;

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "TableShard [id=" + id + ", name=" + name + "]";
	}

}
