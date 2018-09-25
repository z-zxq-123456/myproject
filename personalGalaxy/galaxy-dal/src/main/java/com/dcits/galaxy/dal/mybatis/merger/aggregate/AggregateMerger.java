package com.dcits.galaxy.dal.mybatis.merger.aggregate;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;

import com.dcits.galaxy.base.util.CollectionUtils;
import com.dcits.galaxy.dal.mybatis.merger.IMergerAdapter;
import com.dcits.galaxy.dal.mybatis.merger.config.vo.RuleSetting;
import com.dcits.galaxy.dal.mybatis.proactor.ProactorUtil;

/**
 * 集合函数
 * @author yin.weicai
 *
 * @param <E>
 */
public class AggregateMerger<E> extends IMergerAdapter<List<E>, List<E>> {
	
	@Override
	public List<E> merge(List<List<E>> entities, RuleSetting setting, String statement, Object parameter,SqlSessionFactory sqlSessionFactory) {
		List<E> resultList = new ArrayList<E>();
		if (CollectionUtils.isNotEmpty(entities)) {
			 if (entities.size() == 1) {
            	 resultList.addAll(entities.get(0));
            } else {
            	E result = null;
            	
            	List<E> tempEntities = new ArrayList<E>();
            	for(List<E> entity : entities){
        			if(CollectionUtils.isNotEmpty(entity)){
        				E temp = entity.get(0);
        				if( temp != null){
        					tempEntities.add( temp );
        				}
        			}
        		}
            	
            	Class<?> returnType = ProactorUtil.getReturnType(statement, sqlSessionFactory);
            	Configuration configuration = sqlSessionFactory.getConfiguration();
            	boolean isMapUnderscoreToCamelCase = configuration.isMapUnderscoreToCamelCase();
            	ShardAggregator shardAggregator = ShardAggregatorFactory.get(statement, setting, returnType, isMapUnderscoreToCamelCase);
            	result = shardAggregator.aggregate( tempEntities );
            	
//            	Aggregator aggregator = null;
//            	if( ProactorUtil.isWrapper(returnType)){
//            		
//            		aggregator = Aggregators.newWrapperAggregator(setting, returnType);
//            		result = aggregator.aggregateForWrapper(tempEntities);
//            		
//            	}else if( ProactorUtil.isMap(returnType) ){
//            		
//            		aggregator = Aggregators.newMapAggregator(setting, returnType);
//            		result = aggregator.aggregateForMap(tempEntities);
//            		
//            	}else{
//            		try {
//            			aggregator = Aggregators.newEntityAggregator(setting, returnType);
//            			result = aggregator.aggregateForEntity(tempEntities);
//					} catch (Exception e) {
//						e.printStackTrace();
//					} 
//            	}
            	
            	if(result != null){
            		 resultList.add(result);
            	}
            }
		}
		return resultList;
	}
}
