package com.dcits.galaxy.cache.colhandle;


/**
 * 拼装SQL时  字段值处理  
 * @author tangxlf
 * @date   2015-1-12
 * 
 */
public interface IColHandle {
	public static final String ORACLE_PRDNAME = "ORACLE";
	public static final String MYSQL_PRDNAME = "MYSQL";
	
	/**
	 * 拼装SQL时  字段值处理
	 * @param String dbProdName 数据库产品名      
	 * @param int colType       字段数据类型      
	 * @param Object colValue   字段值
	 * @return String      返回处理过的字段值
	 */ 
	public String columnDataHandle(String dbProdName,int colType,Object colValue);
}
