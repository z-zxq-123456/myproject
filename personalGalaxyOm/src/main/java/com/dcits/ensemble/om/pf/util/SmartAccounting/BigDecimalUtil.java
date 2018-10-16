package com.dcits.ensemble.om.pf.util.SmartAccounting;

import java.math.BigDecimal;

public class BigDecimalUtil {
	
	/**
	 * 将空的BigDecimal类型转换为0
	 * @param big
	 * @return BigDecimal
	 */
	public static BigDecimal nullToZero(BigDecimal big){
		if(big == null){
			return BigDecimal.ZERO;
		}else{
			return big;
		}
	}
	
	/**
	 * 转换为BigDecimal类型
	 * @param obj
	 * @return BigDecimal
	 */
	public static BigDecimal toBigDecimal(Object obj){
		if (obj == null){
			return BigDecimal.ZERO;
		}else if(obj instanceof String){
			String str = (String) obj;
			if("".equals(str)){
				return BigDecimal.ZERO;
			}
		}
		
		return new BigDecimal(String.valueOf(obj));
	}
	
	/**
	 * 转换为字符串
	 * @param big
	 * @return String
	 */
	public static String toString(BigDecimal big){
		return big == null ? "" : big.toString();
	}
}
