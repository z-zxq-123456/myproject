package com.dcits.ensemble.om.pm.common;

import com.dcits.ensemble.om.pm.util.BigDecimalUtil;
import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;

import java.math.BigDecimal;

public class ExpressHander {

	public static BigDecimal parse(String exp,DefaultContext<String, Object> root){
		BigDecimal r = BigDecimal.ZERO;
		ExpressRunner runner = new ExpressRunner();
			Object amt = null;
			try {
				amt = runner.execute(exp, root, null, false,  false);
			} catch (Exception e) {
				return r;
			}
			if(amt == null){
				return r;
			}
			r = BigDecimalUtil.toBigDecimal(amt);
	
		return r;
	}
	//金额表达式计算方法,此方法专门为参数平台校验使用（参数平台校验表达式是否正确需要通过是否抛异常来判断）
	public static BigDecimal parseExpression(String exp,DefaultContext<String, Object> root) throws Exception{
		BigDecimal r = BigDecimal.ZERO;
		ExpressRunner runner = new ExpressRunner();
			Object amt = null;
			amt = runner.execute(exp, root, null, false,  false);
			if(amt == null){
				return r;
			}
			r = BigDecimalUtil.toBigDecimal(amt);
	
		return r;
	}
}
