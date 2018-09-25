package com.dcits.galaxy.dal.mybatis.router.simple.expr;

/**
 * @author fan.kaijie
 *
 */
public interface Expression {
    String expr();

    boolean apply(Object context);
}



