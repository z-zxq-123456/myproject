package com.dcits.galaxy.cache.colhandle;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;
/**
 * 拼装SQL时  字段值处理代理类  
 * @author tangxlf
 * @date   2015-1-12
 * 
 */
public class ColHandleProxy {
	
	private static final Map<Integer,IColHandle>  colMap = new HashMap<Integer,IColHandle>();
	private static final IColHandle strHandle  = new StrColHandle();
	private static final IColHandle numHandle  = new NumColHandle();
	private static final IColHandle dateHandle = new DateColHandle();
	private static final IColHandle defaultHandle = strHandle;
	static{
		colMap.put(Types.DATE,dateHandle);
		colMap.put(Types.TIME,dateHandle);
		colMap.put(Types.TIMESTAMP,dateHandle);
		
		colMap.put(Types.BIGINT,numHandle);
		colMap.put(Types.DECIMAL,numHandle);
		colMap.put(Types.DOUBLE,numHandle);
		colMap.put(Types.INTEGER,numHandle);
		colMap.put(Types.NUMERIC,numHandle);
		colMap.put(Types.REAL,numHandle);
		colMap.put(Types.SMALLINT,numHandle);
		
		colMap.put(Types.CHAR,strHandle);
		colMap.put(Types.BOOLEAN,strHandle);
	}
	
	private static final Map<Integer,Object>  defaultMap = new HashMap<Integer,Object>();
	static{
		defaultMap.put(Types.DATE,null);
		defaultMap.put(Types.TIME,null);
		defaultMap.put(Types.TIMESTAMP,null);
		
		defaultMap.put(Types.BIGINT,0);
		defaultMap.put(Types.DECIMAL,null);
		defaultMap.put(Types.DOUBLE,0.0);
		defaultMap.put(Types.INTEGER,0);
		defaultMap.put(Types.NUMERIC,0.0);
		defaultMap.put(Types.REAL,0);
		defaultMap.put(Types.SMALLINT,0);
		
		defaultMap.put(Types.CHAR,null);
		defaultMap.put(Types.BOOLEAN,null);
	}
	
	/**
	 * 拼装SQL时  字段值处理
	 * @param String dbProdName 数据库产品名      
	 * @param int colType       字段数据类型      
	 * @param Object colValue   字段值
	 * @return String      返回处理过的字段值
	 */ 
	public static String handleColValue(String dbProdName,int colType,Object colValue){
		if(colMap.get(colType)!=null){
		return	colMap.get(colType).columnDataHandle(dbProdName, colType, colValue);
		}
		return defaultHandle.columnDataHandle(dbProdName, colType, colValue);
	}
	
	
	
	public static Object getDefaultValue(int colType){		
		return  defaultMap.get(colType);
	}

}
