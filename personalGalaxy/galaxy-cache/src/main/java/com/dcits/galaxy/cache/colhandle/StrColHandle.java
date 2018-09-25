package com.dcits.galaxy.cache.colhandle;

/**
 * 拼装SQL时  字符串字段值处理  
 * @author tangxlf
 * @date   2015-1-12
 * 
 */
public class StrColHandle implements IColHandle{

	@Override
    /**
	 * 拼装SQL时  字段值处理
	 * @param String dbProdName 数据库产品名    
	 * @param int colType       字段数据类型      
	 * @param Object colValue   字段值
	 * @return String      返回处理过的字段值
	 */ 	   	
	public String columnDataHandle(String dbProdName,int colType, Object colValue) {	
		if(colValue==null){
			return null;
		}
		return  "'"+colValue+"'";
	}

}
