package com.dcits.galaxy.dal.route;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.InitializingBean;

/**
 * 时间段路由规则
 * @author yin.weicai
 *
 */
public class DateTimeFunction implements InitializingBean{
	
	private SimpleDateFormat dateFormat = null;
	private String pattern = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 字符串类型的时间
	 * @param beginTime
	 * @param endTime
	 * @param targetTime
	 * @return
	 * @throws ParseException
	 */
	public boolean route(String beginTime, String endTime, String targetTime ) 
			throws ParseException{
		boolean result = false;
		Date begin = dateFormat.parse( beginTime );
		Date end = dateFormat.parse( endTime );
		Date target = dateFormat.parse( targetTime );
		if (target.after( begin ) && target.before( end ) ){
			result = true;
		}
		return result;
	}
	
	/**
	 * 日期类型的时间
	 * @param beginTime
	 * @param endTime
	 * @param targetTime
	 * @return
	 * @throws ParseException
	 */
	public boolean route(String beginTime, String endTime, Date targetTime ) 
			throws ParseException{
		boolean result = false;
		Date begin = dateFormat.parse( beginTime );
		Date end = dateFormat.parse( endTime );
		if (targetTime.after( begin ) && targetTime.before( end ) ){
			result = true;
		}
		return result;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		String pattern = getPattern();
		if( pattern != null && !pattern.isEmpty()){
			dateFormat = new SimpleDateFormat( pattern );
		}
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	
	
}
