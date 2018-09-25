package com.dcits.galaxy.dal.route;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.mvel2.MVEL;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.integration.impl.MapVariableResolverFactory;

public class MonthFunctionTest extends TestCase {
	
	protected Map<String, Object> functionMap = new HashMap<String, Object>();
	protected static String routerName = "monthRouter";
	
	static class MonthArgument{
		
		private int intMonth;
		
		private String stringMonth;
		
		private Date dateMonth;
		
		public int getIntMonth() {
			return intMonth;
		}
		public void setIntMonth(int intMonth) {
			this.intMonth = intMonth;
		}
		public String getStringMonth() {
			return stringMonth;
		}
		public void setStringMonth(String stringMonth) {
			this.stringMonth = stringMonth;
		}
		public Date getDateMonth() {
			return dateMonth;
		}
		public void setDateMonth(Date dateMonth) {
			this.dateMonth = dateMonth;
		}
	}

	public MonthFunctionTest() {
		functionMap.put(routerName, new MonthFunction());
	}
	
	/**
	 * 整型月份
	 */
	public void testRouteInt(){
		String expression =  routerName + ".route( intMonth ) == 11";
		MonthArgument argument = new MonthArgument();
		argument.setIntMonth( 11 );
		
		MonthFunction function = (MonthFunction)functionMap.get(routerName);
		int month = function.route( argument.getIntMonth() );
		System.out.println( routerName + ".route( intMonth ) = " + month );
		
		Map<String, Object> vrs = new HashMap<String, Object>();
        vrs.putAll(functionMap);
        vrs.put("$ROOT", argument);
        VariableResolverFactory vrfactory = new MapVariableResolverFactory(vrs);
        boolean result = MVEL.evalToBoolean( expression, argument, vrfactory);
        System.out.println( expression + " is " + result );
	}
	
	/**
	 * 字符串月份
	 */
	public void testRouteString(){
		String expression =  routerName + ".route( stringMonth ) == 12";
		MonthArgument argument = new MonthArgument();
		argument.setStringMonth( "11" );
		
		MonthFunction function = (MonthFunction)functionMap.get(routerName);
		int month = function.route( argument.getStringMonth() );
		System.out.println( routerName + ".route( stringMonth ) = " + month );
		
		Map<String, Object> vrs = new HashMap<String, Object>();
        vrs.putAll(functionMap);
        vrs.put("$ROOT", argument);
        VariableResolverFactory vrfactory = new MapVariableResolverFactory(vrs);
        boolean result = MVEL.evalToBoolean( expression, argument, vrfactory);
        System.out.println( expression + " is " + result );
	}
	
	/**
	 * Date类型月份
	 * @throws ParseException
	 */
	public void testRouteDate() throws ParseException{
		String expression =  routerName + ".route( dateMonth ) == 12";
		MonthArgument argument = new MonthArgument();
		Calendar calendar = Calendar.getInstance();
		calendar.set(1982, 11, 8);
		argument.setDateMonth( calendar.getTime() );
		
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//		argument.setDateMonth( format.parse("1982-11-08") );
		
		MonthFunction function = (MonthFunction)functionMap.get(routerName);
		int month = function.route( argument.getDateMonth() );
		System.out.println( routerName + ".route( dateMonth ) = " + month );
		
		Map<String, Object> vrs = new HashMap<String, Object>();
		vrs.putAll(functionMap);
		vrs.put("$ROOT", argument);
		VariableResolverFactory vrfactory = new MapVariableResolverFactory(vrs);
		boolean result = MVEL.evalToBoolean( expression, argument, vrfactory);
		System.out.println( expression + " is " + result );
	}
	
}
