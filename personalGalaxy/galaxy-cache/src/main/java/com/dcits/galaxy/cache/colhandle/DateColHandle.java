package com.dcits.galaxy.cache.colhandle;

import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 拼装SQL时  日期字段值处理  
 * @author tangxlf
 * @date   2015-1-12
 * 
 */
public class DateColHandle implements IColHandle{

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
		if(dbProdName!=null){
			if(dbProdName.toUpperCase().indexOf(ORACLE_PRDNAME)>-1){
				return oralceDataHandle(colType,colValue);
			}
			if(dbProdName.toUpperCase().indexOf(MYSQL_PRDNAME)>-1){
				return mySqlDataHandle(colType,colValue);
			}
		}
		return "'"+colValue+"'";
	}
	
	private String oralceDataHandle(int colType, Object colValue){
        if(colValue.toString().length()>10){
        	return "TO_DATE('"+dateObjHandle(colType,colValue)+"','yyyy-MM-dd HH24:mm:ss')";
		}else {
			return "TO_DATE('"+dateObjHandle(colType,colValue)+"','yyyy-MM-dd')";
		}
	}
	
	private String mySqlDataHandle(int colType, Object colValue){
		if(colValue.getClass().getName().indexOf("String")>-1){
			return "'"+colValue+"'";
		}		
		return "'"+dateObjHandle(colType,colValue)+"'";
	}
	
	private String dateObjHandle(int colType, Object colValue){
		if(colType==Types.DATE){
			SimpleDateFormat datedf = new SimpleDateFormat("yyyy-MM-dd");
			return datedf.format(colValue);
		}
        if(colType==Types.TIME){
        	SimpleDateFormat timedf = new SimpleDateFormat("HH:mm:ss");
        	return timedf.format(colValue);
		}
        if(colType==Types.TIMESTAMP){
        	SimpleDateFormat datetimedf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	return datetimedf.format(colValue);
		}
        return ""+colValue;
	}
	
	public static void main(String[] args){
		DateColHandle handle = new DateColHandle();
		Date date1 = new Date();
		System.out.println("Types.DATE="+handle.mySqlDataHandle(Types.DATE, date1));
		System.out.println("Types.TIME="+handle.mySqlDataHandle(Types.TIME, date1));
		System.out.println("Types.TIMESTAMP="+handle.mySqlDataHandle(Types.TIMESTAMP, date1));
	}

}
