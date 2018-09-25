package com.dcits.galaxy.dal.mybatis.merger.aggregate;

import java.util.List;


/**
 * 针对分库的聚合函数处理器
 * @author yin.weicai
 *
 */
public abstract class ShardAggregator {
	
	/**
	 * 要比较的对象的类型
	 */
	private Class<?> objectClass = null;
	
	/**
	 * 列名
	 */
	private String column = null;
	
	/**
	 * 映射的字段
	 */
	private String mappedColumn = null;
	
	/**
	 * 下一个聚合函数处理器
	 */
	private ShardAggregator nextAggregator = null;
	
	
	public abstract <E> E aggregate(List<E> results);
	
	public boolean hasNext(){
		return nextAggregator == null ? false : true;
	}
	
	public ShardAggregator next(){
		return nextAggregator;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}
	
	
	public Class<?> getObjectClass() {
		return objectClass;
	}

	public void setObjectClass(Class<?> objectClass) {
		this.objectClass = objectClass;
	}
	
	public boolean hasMappedColumn(){
		boolean result = false;
		if( mappedColumn != null && !mappedColumn.isEmpty()){
			result = true;
		}
		return result;
	}

	public String getMappedColumn() {
		return mappedColumn;
	}

	public void setMappedColumn(String mappedColumn) {
		this.mappedColumn = mappedColumn;
	}

	public ShardAggregator getNextAggregator() {
		return nextAggregator;
	}

	public void setNextAggregator(ShardAggregator nextAggregator) {
		this.nextAggregator = nextAggregator;
	}
}
