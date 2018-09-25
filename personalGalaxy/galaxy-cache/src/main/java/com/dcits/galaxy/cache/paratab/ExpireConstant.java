package com.dcits.galaxy.cache.paratab;

import java.util.HashMap;
import java.util.Map;
import com.dcits.galaxy.cache.api.ITempCache;

/**
 * 缓存有效期常量映射  
 * @author tangxlf
 * @date   2015-1-08
 * 
 */
class ExpireConstant {

	public static final String FOREVER= "forever";  //永远 day() week() month(一周) 或者具体的秒数 默认为forever	
	
    public static final String DAY= "day";//12小时
    
    public static final String WEEK ="week";//一周
	
	public static final String MONTH ="month";//一月
	
	private static final Map<String,Integer>  EXPIRE_MAP = new HashMap<String,Integer>();
	
	static{
		EXPIRE_MAP.put(FOREVER, ITempCache.FOREVER_EXPIRE);
		EXPIRE_MAP.put(DAY, ITempCache.DAY_EXPIRE);
		EXPIRE_MAP.put(WEEK, ITempCache.WEEK_EXPIRE);
		EXPIRE_MAP.put(WEEK, ITempCache.MONTH_EXPIRE);
	}
	
	public static int getExpire(String expirePeriod){
		Integer  intExpire = EXPIRE_MAP.get(expirePeriod);
		if(intExpire!= null){
			return intExpire.intValue();
		}
		try{
			return Integer.parseInt(expirePeriod);
		}catch(RuntimeException e){
			return ITempCache.FOREVER_EXPIRE;
		}
	}
	
	public static void main(String[] args){
		System.out.println(ExpireConstant.getExpire("asd"));
		System.out.println(ExpireConstant.getExpire("week"));
		System.out.println(ExpireConstant.getExpire("123"));		
	}
}
