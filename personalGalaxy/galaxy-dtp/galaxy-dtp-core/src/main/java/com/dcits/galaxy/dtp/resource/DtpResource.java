package com.dcits.galaxy.dtp.resource;

import java.io.Serializable;

public class DtpResource implements Serializable {

	private static DtpResourceService dtpResourceService = null;

	private static final long serialVersionUID = 1368121548528962058L;

	private String tableName;

	private String columnName;

	private String columnValue;

	private String txid;

	public DtpResource() {
	}

	public DtpResource(String tableName, String columnName, String columnValue) {
		this.tableName = tableName;
		this.columnName = columnName;
		this.columnValue = columnValue;
	}

	public DtpResource(String tableName, String columnName, String columnValue, String txid) {
		this.tableName = tableName;
		this.columnName = columnName;
		this.columnValue = columnValue;
		this.txid = txid;
	}
	
	public static boolean lock(String tableName, String columnName, String columnValue) {
		return lock(tableName, columnName, columnValue,null);
	}
	
	public static boolean lock(String tableName, String columnName, String columnValue, String txid) {
		DtpResource r= new DtpResource(tableName, columnName, columnValue , txid);
		return lock(r);
	}
	
	public static boolean lock(DtpResource r) {
		return dtpResourceService.lockResource(r);
	}
	
    
	public static boolean unLock(String tableName, String columnName, String columnValue) {
		return unLock(tableName, columnName, columnValue, null);
	}
	
	public static boolean unLock(String tableName, String columnName, String columnValue, String txid) {
		DtpResource r = new DtpResource(tableName, columnName, columnValue, txid);
		return unLock(r);
	}
    
	public static boolean unLock(DtpResource r) {
		return dtpResourceService.unLockResource(r);
	}
	
	public static boolean isLocked(String tableName, String columnName, String columnValue) {
		return isLocked(tableName, columnName, columnValue, null);
	}
	
	public static boolean isLocked(String tableName, String columnName, String columnValue, String txid) {
		DtpResource r= new DtpResource(tableName, columnName, columnValue , txid);
		return isLocked(r);
	}

	public static boolean isLocked(DtpResource r) {
		boolean result = false;
		DtpResource t = dtpResourceService.getResource(r);
		if (t != null) {
			result = true;
		}
		return result;
	}
	
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnValue() {
		return columnValue;
	}

	public void setColumnValue(String columnValue) {
		this.columnValue = columnValue;
	}

	public String getTxid() {
		return txid;
	}

	public void setTxid(String txid) {
		this.txid = txid;
	}

	public static void setDtpResourceService(DtpResourceService dtpResourceService) {
		DtpResource.dtpResourceService = dtpResourceService;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((columnName == null) ? 0 : columnName.hashCode());
		result = prime * result + ((columnValue == null) ? 0 : columnValue.hashCode());
		result = prime * result + ((tableName == null) ? 0 : tableName.hashCode());
		result = prime * result + ((txid == null) ? 0 : txid.hashCode());
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
		DtpResource other = (DtpResource) obj;
		if (columnName == null) {
			if (other.columnName != null)
				return false;
		} else if (!columnName.equals(other.columnName))
			return false;
		if (columnValue == null) {
			if (other.columnValue != null)
				return false;
		} else if (!columnValue.equals(other.columnValue))
			return false;
		if (tableName == null) {
			if (other.tableName != null)
				return false;
		} else if (!tableName.equals(other.tableName))
			return false;
		if (txid == null) {
			if (other.txid != null)
				return false;
		} else if (!txid.equals(other.txid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DtpResource [tableName=" + tableName + ", columnName=" + columnName + ", columnValue=" + columnValue + ", txid=" + txid + "]";
	}
	
}
