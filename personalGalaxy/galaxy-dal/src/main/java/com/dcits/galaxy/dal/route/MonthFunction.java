package com.dcits.galaxy.dal.route;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 月份路由规则
 * @author yin.weicai
 *
 */
public class MonthFunction {
	
	private static final Logger logger = LoggerFactory.getLogger(MonthFunction.class);
	
	/**
	 * 字符串月份
	 * @param month
	 * @return
	 */
	public int route(String month){
		int result = 0;
		if( month != null && !month.isEmpty() ){
			try {
				result = Integer.parseInt(month);
			} catch (NumberFormatException e) {
				result = 0;
				logger.warn(e.getMessage(), e.getCause());
			}
		}
		return result;
	}
	
	/**
	 * 整型月份
	 * @param month
	 * @return
	 */
	public int route(int month){
		int result = month;
		return result;
	}
	
	/**
	 * Date型月份
	 * @param month
	 * @return
	 */
	public int route(Date date){
		int result = 0;
		if( date != null ){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			result = calendar.get( Calendar.MONTH );
			result += 1;
		}
		return result;
	}
}
