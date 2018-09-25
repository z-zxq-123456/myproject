package com.dcits.galaxy.dal.route;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.mvel2.MVEL;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.integration.impl.MapVariableResolverFactory;

public class DateTimeFunctionTest extends TestCase {
	
	protected Map<String, Object> functionMap = new HashMap<String, Object>();
	
	protected static String timeRouterName = "timeRouter";
	protected static String monthRouterName = "monthRouter";
	protected static String hashRouterName = "hashRouter";
	
	static class TimeArgument{
		
		private String stringTime;
		
		private Date dateTime;

		public String getStringTime() {
			return stringTime;
		}

		public void setStringTime(String stringTime) {
			this.stringTime = stringTime;
		}

		public Date getDateTime() {
			return dateTime;
		}

		public void setDateTime(Date dateTime) {
			this.dateTime = dateTime;
		}
	}
	
	public DateTimeFunctionTest() throws Exception {
		DateTimeFunction function = new DateTimeFunction();
		function.afterPropertiesSet();
		functionMap.put(timeRouterName, function);
	}
	
	/**
	 * 字符串时间段
	 */
	public void testRouteString(){
		String expression =  timeRouterName + ".route( '2014-11-08 12:30:22', '2014-11-08 12:30:55', stringTime)";
		TimeArgument argument = new TimeArgument();
		argument.setStringTime( "2014-11-08 12:30:40" );
		
		Map<String, Object> vrs = new HashMap<String, Object>();
        vrs.putAll(functionMap);
        vrs.put("$ROOT", argument);
        VariableResolverFactory vrfactory = new MapVariableResolverFactory(vrs);
        boolean result = MVEL.evalToBoolean( expression, argument, vrfactory);
        System.out.println( expression + " is " + result );
	}
	
	/**
	 * 日期类型时间段
	 */
	public void testRouteDate(){
		String expression =  timeRouterName + ".route( '2014-11-08 12:30:22', '2014-11-08 12:30:55', dateTime)";
		TimeArgument argument = new TimeArgument();
		Calendar calendar = Calendar.getInstance();
		calendar.set(2014, 10, 8, 12, 30, 40);
		argument.setDateTime( calendar.getTime() );
		
		Map<String, Object> vrs = new HashMap<String, Object>();
        vrs.putAll(functionMap);
        vrs.put("$ROOT", argument);
        VariableResolverFactory vrfactory = new MapVariableResolverFactory(vrs);
        boolean result = MVEL.evalToBoolean( expression, argument, vrfactory);
        System.out.println( expression + " is " + result );
	}
}
