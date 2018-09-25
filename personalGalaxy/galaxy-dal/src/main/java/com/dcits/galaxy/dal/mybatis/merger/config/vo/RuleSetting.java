package com.dcits.galaxy.dal.mybatis.merger.config.vo;

import java.util.List;
import java.util.Properties;

import com.dcits.galaxy.base.util.CollectionUtils;

public abstract class RuleSetting {

	private String beanId;
	
	private List<RuleColumn> columns;
	
	private Properties properties;

	public String getBeanId() {
		return beanId;
	}

	public void setBeanId(String beanId) {
		this.beanId = beanId;
	}

	public List<RuleColumn> getColumns() {
		return columns;
	}

	/**
	 * 获取列名，如果有多列，则返回第一列，如果一个都没有，返回null;
	 * @return
	 */
	public RuleColumn getColumn() {
		if( CollectionUtils.isNotEmpty( columns )){
			return columns.get(0);
		}
		return null;
	}

	public void setColumns(List<RuleColumn> columns) {
		this.columns = columns;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public String getProperty(String key) {
		if( properties != null){
			return properties.getProperty(key);
		}
		return null;
	}

}