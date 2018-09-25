package com.dcits.galaxy.dal.mybatis.router.index.expr;

/**
 * @author huang.zhe
 *
 */
public abstract class AbstractIndexExpression implements IndexExpression {
    protected String expression;

    public AbstractIndexExpression(String expression) {
        this.expression = expression;
    }

    public String expr() {
        return this.expression;
    }
}