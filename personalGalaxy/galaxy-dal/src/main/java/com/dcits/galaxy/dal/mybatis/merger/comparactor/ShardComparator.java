package com.dcits.galaxy.dal.mybatis.merger.comparactor;

import java.util.Comparator;

import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dcits.galaxy.dal.mybatis.proactor.ProactorUtil;

/**
 * 比较器，包含排序(降序/升序)
 * @author yin.weicai
 */
public class ShardComparator<T> implements Comparator<T> {
	
	private static Logger logger = LoggerFactory.getLogger(ShardComparator.class);
	
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
	 * 下划线格式转驼峰格式
	 */
	private boolean mapUnderscoreToCamelCase = false;
	
	/**
	 * 下一个比较器
	 */
	private ShardComparator<T> nextComparator = null;
	
	/**
	 * 升序/降序
	 */
	private boolean asc = true;

	@SuppressWarnings("rawtypes")
	public static ShardComparator newInstance( Class objectClass ) {
		if( objectClass != null){
			return new ShardComparator(objectClass);
		}
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	private ShardComparator( Class objectClass ) {
		this.objectClass = objectClass;
	}
	
	public int compare(T o1, T o2) {
		int result = -1;
		try {
			int bigger = doCompare(o1, o2);
			if( bigger == 0){
				if( hasNextComparator()){
					result = getNextComparator().compare(o1, o2);
				}else{					
					result = 0;
				}
			}else if( (bigger > 0 ) & isAsc()  ||  ( bigger < 0) & !isAsc() ) {
				result = 1;
			}
		} catch (Exception e) {
			if (logger.isWarnEnabled())
				logger.warn("occur exception when compare : " + o1 + ":" + o2 , e);
		} 
		return result;
	}

	@SuppressWarnings("unchecked")
	protected int doCompare(T o1, T o2) throws Exception{
		int result = 0;
		Object v1 = o1, v2 = o2;
		if ( !ProactorUtil.isWrapper(getObjectClass()) ){
			MetaObject metaObject1 = ProactorUtil.newMetaObject( o1 );
			MetaObject metaObject2 = ProactorUtil.newMetaObject( o2 );
			
			String propertyName = column;
			if( hasMappedColumn() ){
				propertyName = mappedColumn;
			}else if( isMapUnderscoreToCamelCase() ){
				if( !ProactorUtil.isMap(objectClass) && column.contains("_")){					
					propertyName = metaObject1.findProperty(column, isMapUnderscoreToCamelCase());
				}
			}
			v1 = metaObject1.getValue( propertyName );
			v2 = metaObject2.getValue( propertyName );
		}
		if( v1 != null && v2 != null){
			Comparable<Object> c1 = (Comparable<Object>)v1;
			Comparable<Object> c2 = (Comparable<Object>)v2;
			result = c1.compareTo( c2 );
		}else if( v1 == null && v2 != null){
			result = -1;
		}else if( v1 != null && v2 == null){
			result = 1;
		}else if( v1 == null && v2 == null){
			result = 0;
		}
		return result;
	}
	
	public boolean isMapUnderscoreToCamelCase() {
		return mapUnderscoreToCamelCase;
	}
	
	public Class<?> getObjectClass() {
		return objectClass;
	}

	public void setMapUnderscoreToCamelCase(boolean mapUnderscoreToCamelCase) {
		this.mapUnderscoreToCamelCase = mapUnderscoreToCamelCase;
	}

	public boolean isAsc() {
		return asc;
	}

	public void setAsc(boolean asc) {
		this.asc = asc;
	}
	
	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getMappedColumn() {
		return mappedColumn;
	}

	public void setMappedColumn(String mappedColumn) {
		this.mappedColumn = mappedColumn;
	}
	
	public boolean hasMappedColumn(){
		boolean result = false;
		if( mappedColumn != null && !mappedColumn.isEmpty()){
			result = true;
		}
		return result;
	}

	public boolean hasNextComparator() {
		return nextComparator == null ? false : true;
	}

	public ShardComparator<T> getNextComparator() {
		return nextComparator;
	}

	public void setNextComparator(ShardComparator<T> nextComparator) {
		this.nextComparator = nextComparator;
	}
}