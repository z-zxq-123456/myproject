package com.dcits.galaxy.logclient.help;

import com.dcits.galaxy.base.util.DateUtils;






/**
 * 日志时间辅助类* 
 * @author tangxlf
 * @date 2016-10-31 
 */
public class DateTimeHelper {
	
	
	/**
	 * 生成当前时间	  
	 */
	public static String getCurrentTime(){
		return DateUtils.getDateTime(DateUtils.PATTERN_ISO_FULL);
	}

}
