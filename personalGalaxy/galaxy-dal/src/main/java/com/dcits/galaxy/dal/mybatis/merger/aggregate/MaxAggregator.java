package com.dcits.galaxy.dal.mybatis.merger.aggregate;

import java.util.Collections;
import java.util.List;

import org.apache.ibatis.reflection.MetaObject;

import com.dcits.galaxy.dal.mybatis.merger.comparactor.ShardComparator;
import com.dcits.galaxy.dal.mybatis.proactor.ProactorUtil;

public class MaxAggregator extends ShardAggregator {
	
	private ShardComparator<Object> comparator = null;
	
	@Override
	public <E> E aggregate(List<E> results) {
		if ( ProactorUtil.isWrapper(getObjectClass()) ){
			return Collections.max(results, comparator);
		}else{
			E result = null;
			if( hasNext()){
				ShardAggregator nextAggregator = next();
				result = nextAggregator.aggregate( results );
			}
			E max = Collections.max(results, comparator);
			if( result != null){
				MetaObject metaObjectResult = ProactorUtil.newMetaObject( result );
				MetaObject metaObjectMax = ProactorUtil.newMetaObject( max );
				String propertyName = getColumn();
				if( hasMappedColumn() ){
					propertyName = getMappedColumn();
				}
				Object maxValue = metaObjectMax.getValue(propertyName);
				metaObjectResult.setValue(propertyName, maxValue);
			}else{
				result = ProactorUtil.cloneObject(max);
			}
			return result;
		}
	}
	
	public ShardComparator<Object> getComparator() {
		return comparator;
	}

	public void setComparator(ShardComparator<Object> comparator) {
		this.comparator = comparator;
	}
	
}
