package com.dcits.galaxy.dal.mybatis.merger.orderby;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;

import com.dcits.galaxy.base.util.CollectionUtils;
import com.dcits.galaxy.dal.mybatis.merger.IMergerAdapter;
import com.dcits.galaxy.dal.mybatis.merger.comparactor.ShardComparator;
import com.dcits.galaxy.dal.mybatis.merger.comparactor.ShardComparatorFactory;
import com.dcits.galaxy.dal.mybatis.merger.config.vo.RuleSetting;
import com.dcits.galaxy.dal.mybatis.proactor.ProactorUtil;

public class OrderByMerger<E> extends IMergerAdapter<List<E>, List<E>> {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<E> merge(List<List<E>> entities, RuleSetting setting,String statement, Object parameter, SqlSessionFactory sqlSessionFactory) {
		List<E> resultList = new ArrayList<E>();
		if (CollectionUtils.isNotEmpty(entities)) {
			if (CollectionUtils.isNotEmpty(entities)) {
				 if (entities.size() == 1) {
	            	 resultList.addAll(entities.get(0));
	            } else {
					Class<?> returnType = ProactorUtil.getReturnType(statement, sqlSessionFactory);
	            	
	            	Configuration configuration = sqlSessionFactory.getConfiguration();
	            	boolean isMapUnderscoreToCamelCase = configuration.isMapUnderscoreToCamelCase();
	            	ShardComparator comparator = ShardComparatorFactory.get(statement, setting, 
	            			returnType,isMapUnderscoreToCamelCase);
	            	for (int i = 0; i < entities.size(); i++){
	        			List<E> tempResult = entities.get(i);
	        			if(CollectionUtils.isNotEmpty(tempResult)){
	        				resultList.addAll(tempResult);
	        			}
	        		}
	        		Collections.sort(resultList, comparator);
//	            	if( ProactorUtil.isWrapper( returnType ) ){
//	            		mergeForWrapper( resultList, entities, setting);
//	            	}else if( ProactorUtil.isMap( returnType ) ){
//	            		mergeForMap( resultList, entities, setting);
//	            	}else{
//	            		try {
//							mergeForEntity( resultList, entities, setting,returnType);
//						} catch ( Exception e) {
//							e.printStackTrace();
//						} 
//	            	}
	            }
			}
		}
		return resultList;
	}
	
//	protected E mergeForWrapper(List<E> resultList,List<List<E>> entities,RuleSetting setting){
//		AbstractComparator<Object> comparator = Compatators.buildWrapperComparator(setting);
//		for (int i = 0; i < entities.size(); i++){
//			List<E> tempResult = entities.get(i);
//			if(CollectionUtils.isNotEmpty(tempResult)){
//				resultList.addAll(tempResult);
//			}
//		}
//		Collections.sort(resultList, comparator);
//		return null;
//	}
//	
//	protected E mergeForMap ( List<E> resultList,List<List<E>> entities,RuleSetting setting ){
//		
//		AbstractComparator<Object> comparator = Compatators.buildMapComparator(setting);
//		for (int i = 0; i < entities.size(); i++){
//			List<E> tempResult = entities.get(i);
//			if(CollectionUtils.isNotEmpty(tempResult)){
//				resultList.addAll(tempResult);
//			}
//		}
//		Collections.sort(resultList, comparator);
//		return null;
//	}
//	
//	protected E mergeForEntity(  List<E> resultList, List<List<E>> entities ,RuleSetting setting, Class<?> entityClass ) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
//		AbstractComparator<Object> comparator = Compatators.buildEntityComparator(setting, entityClass);
//		
//		for (int i = 0; i < entities.size(); i++){
//			List<E> tempResult = entities.get(i);
//			if(CollectionUtils.isNotEmpty(tempResult)){
//				resultList.addAll(tempResult);
//			}
//		}
//		Collections.sort(resultList, comparator);
//		return null;
//	}
}
