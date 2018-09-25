package com.dcits.galaxy.logcover.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;




/**
 * 数据检查 *
 * @author tangxlf
 * @date 2015-08-07
 */
public class DataUtil {	   
	
	
	/**
	* 把NULL转换成空串	
	* @param  String   dataValue  待检查数据  
    * @return String   返回值
	*/		
	public static String changeNull(String dataValue){
		if(dataValue==null){
			return "";  
		}
	    return dataValue.trim();
	}
	
	
	/**
	* 把NULL转换成空串	
	* @param  String   dataValue  待检查数据  
    * @return String   返回值
	*/		
	public static boolean isNull(String dataValue){
		if(dataValue==null||dataValue.trim().equals("")){
			return true;  
		}
	    return false;
	}
	
	/**
	* 打印错误堆栈	
	* @param  String   dataValue  待检查数据  
    * @return String   返回值
	*/		
	public static String printErrorStack(Exception e){
		ByteArrayOutputStream buf = new java.io.ByteArrayOutputStream();
		e.printStackTrace(new PrintWriter(buf,true));
		String expMessage = buf.toString();
		try {
			buf.close();
		} catch (IOException e1) {
			
		}
		return expMessage;
	}
}
