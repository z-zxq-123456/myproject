package com.dcits.galaxy.dal.mybatis.merger.aggregate;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.reflection.MetaObject;

import com.dcits.galaxy.dal.mybatis.proactor.ProactorUtil;

public class SumAggregator extends ShardAggregator {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public <E> E aggregate(List<E> results) {
		if ( ProactorUtil.isWrapper(getObjectClass()) ){
			Object result = ProactorUtil.getDefaultValue( getObjectClass() ) ;
			for(E e : results){
				result = sum(result, e);
			}
			return (E)result;
		}else {
			E result = null;
			if( hasNext()){
				ShardAggregator nextAggregator = next();
				result = nextAggregator.aggregate(results);
			}
			
			if( result == null){
				for (E e : results) {
					if( e != null){
						result = (E)ProactorUtil.cloneObject(e);
					}
				}
			}
			MetaObject metaObject = ProactorUtil.newMetaObject( result );
			String propertyName = getColumn();
			if( hasMappedColumn() ){
				propertyName = getMappedColumn();
			}
			Class classType = metaObject.getSetterType(propertyName);
			Object initValue = ProactorUtil.getDefaultValue( classType ) ;
			metaObject.setValue(propertyName, initValue);
			
			Object resultValue = metaObject.getValue(propertyName);
			for (E e : results) {
				MetaObject metaObjectTemp = ProactorUtil.newMetaObject( e );
				Object tempValue = metaObjectTemp.getValue( propertyName );
				resultValue = sum(resultValue, tempValue);
			}
			metaObject.setValue(propertyName, resultValue);
			return result;
		}
	}
	
	private Object sum( Object rv, Object tv){
		Object count = null;
		if( rv instanceof Long ){
			count = (Long)rv + (Long)tv;
		}else if( rv instanceof Integer ){
			count = (Integer)rv + (Integer)tv;
		}else if( rv instanceof Short ){
			count = (Short)rv + (Short)tv;
		}else if( rv instanceof Byte ){
			count = (Byte)rv + (Byte)tv;
		}else if( rv instanceof Double ){
			count = (Double)rv + (Double)tv;
		}else if( rv instanceof Float ){
			count = (Float)rv + (Float)tv;
		}else if( rv instanceof BigInteger ){
			count = ((BigInteger) rv).add( (BigInteger)tv );
		}else if( rv instanceof BigDecimal ){
			count = ((BigDecimal) rv).add( (BigDecimal)tv );
		}else{
			//TODO
		}
		return count;
	}
}
