package com.dcits.galaxy.dal.demo.common;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;

/**
 * 日期工具类(线程安全)
 * @author wang.yq
 */
public class DateUtil {

	public static final String PATTERN_ISO_DATE = "yyyy-MM-dd";
	public static final String PATTERN_ISO_TIME = "HH:mm:ss";
	public static final String PATTERN_ISO_DATETIME = "yyyy-MM-dd HH:mm:ss";
	public static final String PATTERN_ISO_FULL = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final String PATTERN_SIMPLE_DATE = "yyyyMMdd";
	public static final String PATTERN_SIMPLE_TIME = "HHmmss";
	public static final String PATTERN_SIMPLE_DATETIME = "yyyyMMddHHmmss";
	public static final String PATTERN_SIMPLE_FULL = "yyyyMMddHHmmssSSS";
	public static final String[] PATTERNS = { PATTERN_ISO_DATE, PATTERN_ISO_TIME, PATTERN_ISO_DATETIME, PATTERN_ISO_FULL, PATTERN_SIMPLE_DATE,
			PATTERN_SIMPLE_TIME, PATTERN_SIMPLE_DATETIME, PATTERN_SIMPLE_FULL };

	/**
	 * 日期转字符串
	 * @param date 日期类型
	 * @param pattern 格式字符串
	 * @return String 格式化后的字符串
	 */
	public static String formatDate(Date date, String pattern) {
		if (StringUtils.isEmpty(pattern)) {
			pattern = PATTERN_ISO_DATE;
		}
		return DateFormatUtils.format(date, pattern);
	}

	/**
	 * 日期转字符串
	 * @param calendar 日历类型
	 * @param pattern 格式字符串
	 * @return String 格式化后的字符串
	 */
	public static String formatDate(Calendar calendar, String pattern) {
		if (StringUtils.isEmpty(pattern)) {
			pattern = PATTERN_ISO_DATE;
		}
		return DateFormatUtils.format(calendar, pattern);
	}

	/**
	 * 字符串转日期
	 * @param strDate 日期字符串
	 * @return Date 解析后的日期类型
	 * @throws ParseException
	 */
	public static Date parseDate(String strDate) throws ParseException {
		return DateUtils.parseDate(strDate, PATTERNS);
	}

	/**
	 * 字符串转日期
	 * @param strDate 日期字符串
	 * @param patterns 格式字符串
	 * @return Date 日期类型
	 * @throws ParseException
	 */
	public static Date parseDate(String strDate, String... patterns) throws ParseException {
		return DateUtils.parseDate(strDate, patterns);
	}
	
	/**
	 * 计算日期间隔天数
	 * @param startDate
	 * @param endDate
	 * @return BigDecimal
	 */
	public static  BigDecimal getDiffDays(Date startDate, Date endDate) {
		long time = endDate.getTime() - startDate.getTime();
		return new BigDecimal(time / 1000 / 60 / 60 / 24);
	}
	
	/**
	 * 计算日期加上天数后得到的日期
	 * @param date
	 * @param i
	 * @return
	 * @throws ParseException
	 */
	public static  Date addDate(Date date, int i)throws ParseException{
		return DateUtils.addDays(date, i);
	}

	/**
	 * 根据变动周期计算下一变动日
	 * @param date 变动起始日期
	 * @param datMth 利率变更周期单位 Y年M月W周R日
	 * @param rollFreq 利率变更周期值
	 * @param rollDay 变更日期
	 * @return
	 */
	public static Date getNextDate(Date date,String datMth, int rollFreq, int rollDay) throws ParseException {
		int Year = getYear(date);
		int Month = getMonth(date);
		int Day = getDay(date);
		if(datMth.equals("Y")){//年
			Year = Year + rollFreq;
			Day = rollDay;
			return parseDate(Year+"-"+Month+"-"+Day);
		}else if(datMth.equals("M")){//月
			int mm = Month + rollFreq;
			int residue = mm/12;//取月份除以12的余数
			int mod = mm%12;//取月份除以12的模
			if(residue > 0){//想加的月份大于12则年加相应的时间
				Year = Year + residue;
				Month = mod;
			}else if(mod > 0){
				Month = mod;
			}
			Day = rollDay;
			return parseDate(Year+"-"+Month+"-"+Day);
		}else if(datMth.equals("W")){//周
			return addDate(date,rollFreq*7);
		}else if(datMth.equals("D")){//日
			return addDate(date,rollFreq);
		}
		return null;
	}
	

	/**
	 * 获取日期的年份
	 * @param date
	 * @return int
	 */
	public static int getYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}
	
	/**
	 * 获取日期的月份
	 * @param date
	 * @return int
	 */
	public static int getMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}
	
	/**
	 * 获取日期的日
	 * @param date
	 * @return int
	 */
	public static int getDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	} 
	
	/**
	 * 根据利率变动周期和利率变更日，计算每段周期的时间段
	 * @param rollDate 下一个利率变更日期，需要在startDate和endDate之间
	 * @param datMth 利率变更周期单位 Y年M月R日
	 * @param rollFreq 利率变更周期
	 * @param rollDay 变更日期
	 */
	public static List<Map<String,Object>> getRollDate(Date startDate, Date endDate,Date rollDate, String datMth, int rollFreq, int rollDay) throws ParseException{
		List<Map<String, Object>> dateArray = new ArrayList<Map<String, Object>>();
		Map<String, Object> dateMap = new HashMap<String, Object>();
		if(getDiffDays(startDate, rollDate).compareTo(BigDecimal.ZERO) <0 ){
			throw new ParseException("下一个利率变更日期出错！",0);
		}
		Date startDate1 = startDate;
		dateMap.put("startDate", startDate1);
		dateMap.put("endDate", rollDate);
		dateArray.add(dateMap);//第一个日期分段
		startDate = rollDate;
		Date endDate1 = startDate;
		//根据利率变更周期和变更日计算需要分段的日期区间
		while(getDiffDays(endDate1,endDate).compareTo(BigDecimal.ZERO) > 0){
			Map<String, Object> mm = new HashMap<String, Object>();
			endDate1 = getNextDate(startDate, datMth, rollFreq, rollDay);
			if(getDiffDays(endDate1,endDate).compareTo(BigDecimal.ZERO) <= 0){
				mm.put("startDate", startDate);
				mm.put("endDate", endDate);
			}else{
				mm.put("startDate", startDate);
				mm.put("endDate", endDate1);
			}
			dateArray.add(mm);
			startDate = endDate1;
		}
		return 	dateArray;
	}
}