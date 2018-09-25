package com.dcits.galaxy.dal.mybatis.router.simple.expr;

import java.util.HashMap;
import java.util.Map;

import org.mvel2.MVEL;
import org.mvel2.integration.impl.MapVariableResolverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基于MVEL的Expression实现
 * @author fan.kaijie
 *
 */
public class MVELExpression extends AbstractExpression {

	private transient final Logger logger = LoggerFactory
			.getLogger(MVELExpression.class);

	private Map<String, Object> functions = new HashMap<String, Object>();

	public MVELExpression(String expression) {
		super(expression);
	}

	public MVELExpression(String expression, Map<String, Object> functions) {
		super(expression);
		this.functions.putAll(functions);
	}

	public boolean apply(Object context) {
		Map<String, Object> vrs = new HashMap<String, Object>();
		vrs.putAll(this.functions);
		vrs.put("$ROOT", context);
		try {
			return MVEL.evalToBoolean(expression, context,
					new MapVariableResolverFactory(vrs));
		} catch (Throwable t) {
			logger.info(
					"failed to evaluate attribute expression:'{}' with context object:'{}'\n{}",
					new Object[] { this.expression, context, t });
		}
		return false;
	}
}
