package com.dcits.galaxy.dal.mybatis.table.router.rule;

import java.util.Set;

import com.dcits.galaxy.dal.mybatis.router.simple.expr.Expression;

/**
 * 
 */
public class TableRoute {

	private String sqlmap;
	private Expression expression;
	private Set<String> tables;

	public TableRoute(String sqlmap, Expression expression, Set<String> tables) {
		this.sqlmap = sqlmap;
		this.expression = expression;
		this.tables = tables;
	}

	/**
	 * 根据所传入的sqlmap与argument判断是否符合此条规则
	 * 
	 * @param sqlmap
	 *            所执行的sql语句所对应的sqlmap或namespace
	 * @param argument
	 * @return 若符合此规则，则返回true
	 */
	public boolean apply(String sqlmap, Object argument) {
		if (this.sqlmap == null)
			return false;
		if (!this.sqlmap.equals(sqlmap))
			return false;
		if (expression == null)
			return true;
		if (argument != null && expression.apply(argument))
			return true;
		return false;
	}

	public Expression getExpression() {
		return expression;
	}

	public void setExpression(Expression expression) {
		this.expression = expression;
	}

	public Set<String> getTables() {
		return tables;
	}

	public void setTables(Set<String> tables) {
		this.tables = tables;
	}

	public String getSqlmap() {
		return sqlmap;
	}

	public void setSqlmap(String sqlmap) {
		this.sqlmap = sqlmap;
	}

	@Override
	public String toString() {
		return "TableRoute [sqlmap=" + sqlmap + ", expression=" + expression
				+ ", tables=" + tables + "]";
	}

}
