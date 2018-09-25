package com.dcits.galaxy.dal.mybatis.router.index.expr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mvel2.MVEL;
import org.mvel2.integration.impl.MapVariableResolverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基于MVEL的Expression实现
 * @author huang.zhe
 *
 */
public class IndexMVELExpression extends AbstractIndexExpression {

	private transient final Logger logger = LoggerFactory
			.getLogger(IndexMVELExpression.class);

	private Map<String, Object> functions = new HashMap<String, Object>();

	public IndexMVELExpression(String expression) {
		super(expression);
	}

	public IndexMVELExpression(String expression, Map<String, Object> functions) {
		super(expression);
		this.functions.putAll(functions);
	}

	@SuppressWarnings("unchecked")
	public List<Integer> apply(Object context) {
		Map<String, Object> vrs = new HashMap<String, Object>();
		vrs.putAll(this.functions);
		vrs.put("$ROOT", context);
		try {
			return (List<Integer>) MVEL.eval(expression, context,
					new MapVariableResolverFactory(vrs));
		} catch (Throwable t) {
			logger.info(
					"failed to evaluate attribute expression:'{}' with context object:'{}'\n{}",
					new Object[] { this.expression, context, t });
		}
		return null;
	}
}
