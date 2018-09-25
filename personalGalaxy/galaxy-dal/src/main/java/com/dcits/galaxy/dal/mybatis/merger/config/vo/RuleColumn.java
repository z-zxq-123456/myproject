package com.dcits.galaxy.dal.mybatis.merger.config.vo;

/**
 * 
 * @author yin.weicai
 */
public class RuleColumn {

	/**
	 * 列名
	 */
	private String name;
	
	/**
	 * 列别名, 或列映射到的属性名
	 */
	private String alias;
	
	/**
	 * 聚合函数
	 */
	private String aggregate;
	
	/**
	 * 降序
	 */
	private boolean desc = false;
	
	public RuleColumn() {
	}
	
	public RuleColumn(String name) {
		this(name, null, null, false);
	}
	
	public RuleColumn(String name, String alias) {
		this(name, alias, null, false);
	}
	
	public RuleColumn(String name, String alias, String aggregate) {
		this(name, alias, aggregate, false);
	}
	
	public RuleColumn(String name, String alias, String aggregate, boolean desc) {
		this.name = name;
		this.alias = alias;
		this.aggregate = aggregate;
		this.desc = desc;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	public boolean hasAlias(){
		boolean result = false;
		if( alias != null && !alias.isEmpty() ){
			result = true;
		}
		return result;
	}
	
	public String getAggregate() {
		return aggregate;
	}
	public void setAggregate(String aggregate) {
		this.aggregate = aggregate;
	}
	public boolean isDesc() {
		return desc;
	}
	public void setDesc(boolean desc) {
		this.desc = desc;
	}
	
	public boolean isAsc(){
		return !desc;
	}
	
	@Override
	public String toString() {
		return "name=" + name + ",alias=" + alias + ",aggregate=" + aggregate+ ", desc=" + desc + ")";
	}
}
