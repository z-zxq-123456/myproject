package com.dcits.galaxy.cache.paratab;

import com.dcits.galaxy.cache.paratab.metadata.Column;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局参数配置信息bean  
 * @author tangxlf
 * @date   2015-1-06
 * 
 */
public class ParaTabCacheInfo {
	
    private String tableName;  //表名称
    
    private String dbTableName; //大写+下划线格式表名称

	private String pkStr;   //缓存数据表主键串
	
	private String queryCon; //缓存初始化查询语句	
	
	private String dbQueryCon; //处理过的初始化查询语句
	
	private String hanlderClass; //缓存处理类		

	private String expirePeriod ="forever"; //缓存期限  forever(永远 ) day(12小时) week(一周) month(一月) 或者具体的秒数 默认为forever
	
	private Map<String,Column> columnMap = new HashMap<String,Column>();//表字段MAP	

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getDbTableName() {
		return dbTableName;
	}

	public void setDbTableName(String dbTableName) {
		this.dbTableName = dbTableName;
	}
	
	public String getQueryCon() {
		return queryCon;
	}

	public void setQueryCon(String queryCon) {
		this.queryCon = queryCon;
	}

	public String getDbQueryCon() {
		return dbQueryCon;
	}

	public void setDbQueryCon(String dbQueryCon) {
		this.dbQueryCon = dbQueryCon;
	}
	
	public String getPkStr() {
		return pkStr;
	}

	public void setPkStr(String pkStr) {
		this.pkStr = pkStr;
	}	

	public String getHanlderClass() {
		return hanlderClass;
	}

	public void setHanlderClass(String hanlderClass) {
		this.hanlderClass = hanlderClass;
	}
	
	public String getExpirePeriod() {
		return expirePeriod;
	}

	public void setExpirePeriod(String expirePeriod) {
		this.expirePeriod = expirePeriod;
	}
	
	public Map<String,Column> getColumnMap() {
		return columnMap;
	}

	public void setColumnMap(Map<String,Column> columnMap) {
		this.columnMap = columnMap;
	}
}
