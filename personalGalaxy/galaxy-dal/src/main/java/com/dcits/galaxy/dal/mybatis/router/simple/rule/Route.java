package com.dcits.galaxy.dal.mybatis.router.simple.rule;

import java.util.Set;

import com.dcits.galaxy.dal.mybatis.router.simple.expr.Expression;

/**
 * 封装具体的路由规则
 * 
 * @author fan.kaijie
 */
public class Route {

	private String sqlmap;
    private Expression expression;
    private Set<String> shards;

    public Route(String sqlmap, Expression expression, Set<String> shards) {
        this.sqlmap = sqlmap;
        this.expression = expression;
        this.shards = shards;
    }

    /**
     * 根据所传入的sqlmap与argument判断是否符合此条规则
     * @param sqlmap 所执行的sql语句所对应的sqlmap或namespace
     * @param argument 
     * @return 若符合此规则，则返回true
     */
    public boolean apply(String sqlmap, Object argument) {
        if (this.sqlmap == null) return false;
        if (!this.sqlmap.equals(sqlmap)) return false;
        if (expression == null) return true;
        if (argument != null && expression.apply(argument)) return true;
        return false;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public Set<String> getShards() {
        return shards;
    }

    public void setShards(Set<String> shards) {
        this.shards = shards;
    }

    public String getSqlmap() {
        return sqlmap;
    }

    public void setSqlmap(String sqlmap) {
        this.sqlmap = sqlmap;
    }

    @Override
    public String toString() {
        return "Route{" +
                "expression=" + expression +
                ", sqlmap='" + sqlmap + '\'' +
                ", shards=" + shards +
                '}';
    }
}
