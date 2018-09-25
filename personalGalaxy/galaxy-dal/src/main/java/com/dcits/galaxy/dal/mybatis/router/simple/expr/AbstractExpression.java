package com.dcits.galaxy.dal.mybatis.router.simple.expr;

/**
 * @author fan.kaijie
 *
 */
public abstract class AbstractExpression implements Expression {
    protected String expression;

    public AbstractExpression(String expression) {
        this.expression = expression;
    }

    public String expr() {
        return this.expression;
    }
}