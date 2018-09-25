package com.dcits.orion.stria.impl;

import com.dcits.orion.stria.Expression;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Map;

/**
 * Created by Tim on 2015/5/19.
 */
public class SpelExpression implements Expression {

    ExpressionParser parser = new SpelExpressionParser();

    public <T> T eval(Class<T> T, String expr, Map<String, Object> args) {
        return eval(T, expr, args, null);
    }

    public <T> T eval(Class<T> T, String expr, Map<String, Object> args, Object rootObj) {
        EvaluationContext context;
        if (null == rootObj)
            context = new StandardEvaluationContext();
        else
            context = new StandardEvaluationContext(rootObj);

        for (Map.Entry<String, Object> entry : args.entrySet()) {
            context.setVariable(entry.getKey(), entry.getValue());
        }
        return parser.parseExpression(expr).getValue(context, T);
    }
}
