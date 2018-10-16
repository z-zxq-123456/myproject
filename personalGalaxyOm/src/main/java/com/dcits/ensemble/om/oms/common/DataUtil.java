package com.dcits.ensemble.om.oms.common;


import com.dcits.galaxy.base.exception.GalaxyException;
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
	* 非空检查
	* @param  String   dataName   待检查数据 名称
	* @param  String   dataValue  待检查数据  
    * @return String   返回值
	*/		
	public static String checkNull(String dataName,String dataValue){
		  if(dataValue==null||dataValue.trim().equals("")){
			  throw new GalaxyException(dataName+"值为空,请检查!");
		  }
		  return dataValue.trim();
	}
	
	/**
	* 非空检查	
	* @param  String   dataValue  待检查数据  
    * @return String   返回值
	*/		
	public static String checkNull(String dataValue) {
		 return  checkNull("参数",dataValue);
	}
	
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
		ByteArrayOutputStream buf = new ByteArrayOutputStream();
		e.printStackTrace(new PrintWriter(buf,true));
		String expMessage = buf.toString();
		try {
			buf.close();
		} catch (IOException e1) {

		}
		return expMessage;
	}
}
