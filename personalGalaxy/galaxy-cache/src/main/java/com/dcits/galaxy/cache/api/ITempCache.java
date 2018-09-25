package com.dcits.galaxy.cache.api;

public interface ITempCache {
	public static final int  FOREVER_EXPIRE = -1;       //永远
	public static final int  MONTH_EXPIRE = 30*24*3600; //一月
	public static final int  WEEK_EXPIRE = 7*24*3600;   //一周
	public static final int  DAY_EXPIRE = 1*12*3600;   //12小时
}
