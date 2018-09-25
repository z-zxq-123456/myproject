package com.dcits.galaxy.dal.mybatis.proactor.page.memory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.ibatis.session.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dcits.galaxy.base.util.CollectionUtils;
import com.dcits.galaxy.dal.mybatis.ShardSqlSessionTemplate;
import com.dcits.galaxy.dal.mybatis.merger.comparactor.ShardComparator;
import com.dcits.galaxy.dal.mybatis.merger.comparactor.ShardComparatorFactory;
import com.dcits.galaxy.dal.mybatis.plugins.RowArgs;
import com.dcits.galaxy.dal.mybatis.proactor.ProactorUtil;
import com.dcits.galaxy.dal.mybatis.proactor.config.vo.ProactorSetting;
import com.dcits.galaxy.dal.mybatis.proactor.page.ParameterWrapper;
import com.dcits.galaxy.dal.mybatis.shard.Shard;
import com.dcits.galaxy.dal.mybatis.shard.ShardResultSet;

/**
 * 内存分页
 * @author yin.weicai
 */
public class MemoryPage {
	
	private static Logger logger = LoggerFactory.getLogger(MemoryPage.class);
	
	private ShardSqlSessionTemplate shardSqlSessionTempalte;
	
	private String statement;
	
	private Object parameter;
	
	private ProactorSetting setting;
	
	public MemoryPage(ShardSqlSessionTemplate shardSqlSessionTempalte,String statement, Object parameter, 
			ProactorSetting setting) {
		this.shardSqlSessionTempalte = shardSqlSessionTempalte;
		this.statement = statement;
		this.parameter = parameter;
		this.setting = setting;
	}
	
	public List<Object> query(){
		List<Object> resultList = new ArrayList<Object>();
		try {
			List<Object> tempResultList = doQuery();
			if(CollectionUtils.isNotEmpty(tempResultList)){
				resultList.addAll(tempResultList);
			}
		} catch (Exception e) {
			logger.warn("failed doQuery. cause:", e);
		}
		return resultList;
	}
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Object> doQuery() throws NoSuchMethodException, SecurityException {
		ParameterWrapper paraWrapper = (ParameterWrapper)parameter;
		RowArgs rowAgrs = paraWrapper.getRowArgs();
		int offset = rowAgrs.getOffset();
		int limit = rowAgrs.getLimit();
		int ultimateLimit = offset + limit;
		List<Shard> shardList = shardSqlSessionTempalte.lookupDataSourcesByRouter(statement,parameter);
		
		rowAgrs.setOffset( 0 );
		rowAgrs.setLimit( ultimateLimit );
		List<ShardResultSet> originalResultList = shardSqlSessionTempalte.selectList(statement, parameter, shardList, false);
		
		Class returnType = ProactorUtil.getReturnType(statement, shardSqlSessionTempalte.getSqlSessionFactory());
		Configuration configuration = shardSqlSessionTempalte.getConfiguration();
    	boolean isMapUnderscoreToCamelCase = configuration.isMapUnderscoreToCamelCase();
		ShardComparator<Object> comparator = ShardComparatorFactory.buildShardComparator(setting, returnType, isMapUnderscoreToCamelCase);
		
		List<Object> tempResultList = new ArrayList<Object>();
		for (ShardResultSet item : originalResultList) {
			Object list = item.getResult();
			tempResultList.addAll( (List)list );
		}
		Collections.sort(tempResultList, comparator);
		
		List<Object> resultList = new ArrayList<Object>();
		int size = tempResultList.size();
		int fromIndex = offset ;
		int toIndex =  fromIndex + limit ;
		if( size > offset ){
			if( size < ( toIndex )){
				toIndex = size;
			}
			resultList = tempResultList.subList(fromIndex, toIndex);
		}
		return resultList;
	}
}
