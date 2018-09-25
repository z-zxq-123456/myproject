package com.dcits.galaxy.dal.mybatis.table.tableshard;
/**
 * 
 * @author huang.zhe
 *
 */
public class TableShard {
	String id;
	String logicTable;
	String executeTable;

	public TableShard() {
		super();
	}

	public TableShard(String logicTable, String id, String executeTable) {
		super();
		this.logicTable = logicTable;
		this.id = id;
		this.executeTable = executeTable;
	}

	public String getLogicTable() {
		return logicTable;
	}

	public String getId() {
		return id;
	}

	public String getExecuteTable() {
		return executeTable;
	}

	public void setLogicTable(String logicTable) {
		this.logicTable = logicTable;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setExecuteTable(String executeTable) {
		this.executeTable = executeTable;
	}

	@Override
	public String toString() {
		return "TableShard [id=" + id + ", logicTable=" + logicTable + ", executeTable=" + executeTable + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((executeTable == null) ? 0 : executeTable.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((logicTable == null) ? 0 : logicTable.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TableShard other = (TableShard) obj;
		if (executeTable == null) {
			if (other.executeTable != null)
				return false;
		} else if (!executeTable.equals(other.executeTable))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (logicTable == null) {
			if (other.logicTable != null)
				return false;
		} else if (!logicTable.equals(other.logicTable))
			return false;
		return true;
	}

}
