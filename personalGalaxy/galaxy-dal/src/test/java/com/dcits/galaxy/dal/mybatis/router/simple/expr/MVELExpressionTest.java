package com.dcits.galaxy.dal.mybatis.router.simple.expr;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import com.dcits.galaxy.dal.demo.entities.User;
import com.dcits.galaxy.dal.mybatis.router.simple.expr.MVELExpression;
import com.dcits.galaxy.dal.route.HashFunction;

public class MVELExpressionTest extends TestCase {

	private MVELExpression expression;
	private Map<String, Object> functionsMap;

	protected void setUp() throws Exception {
		functionsMap = new HashMap<String, Object>();

		functionsMap.put("hash", new HashFunction());
		// functionsMap.put("date", new DateTimeFunction());
		// functionsMap.put("month", new MonthFunction());
	}

	public void testApply() {
		String expr = "hash.apply(sid) == 0";
		expression = new MVELExpression(expr, functionsMap);
		User user = new User();
		for (int i = 0; i < 10; i++) {
			user.setSid((long)i);
			boolean result = expression.apply(user);
			if(i % 2 == 0)
				assertTrue(result);
			else assertTrue(!result);
		}
	}
}
