package com.dcits.galaxy.dal.mybatis.table.proactor.page.approximate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.session.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dcits.galaxy.base.util.CollectionUtils;
import com.dcits.galaxy.dal.mybatis.merger.comparactor.ShardComparator;
import com.dcits.galaxy.dal.mybatis.merger.comparactor.ShardComparatorFactory;
import com.dcits.galaxy.dal.mybatis.plugins.RowArgs;
import com.dcits.galaxy.dal.mybatis.proactor.ProactorUtil;
import com.dcits.galaxy.dal.mybatis.proactor.config.vo.ProactorSetting;
import com.dcits.galaxy.dal.mybatis.proactor.page.ParameterWrapper;
import com.dcits.galaxy.dal.mybatis.proactor.page.approximate.ApproximateContext;
import com.dcits.galaxy.dal.mybatis.proactor.page.approximate.ApproximateUtil;
import com.dcits.galaxy.dal.mybatis.shard.ShardResultSet;
import com.dcits.galaxy.dal.mybatis.table.TableShardSqlSessionTemplate;
import com.dcits.galaxy.dal.mybatis.table.tableshard.TableShard;

/**
 * 逼近查找分页
 * @author huang.zhe
 */
public class ApproximatePageTable{
	
	private static final Logger logger = LoggerFactory.getLogger(ApproximatePageTable.class);
	
	private TableShardSqlSessionTemplate tableShardSqlSessionTemplate;
	
	private String statement;
	
	private Object parameter;
	
	private ProactorSetting setting;
	
	/**
	 * 启用内存分页的offset的值
	 */
	private int memoryPageOffset = 10;
	
	private String offset_statement;
	
	private String offset_column ; 
	
	private List<TableShard> shardList;
	
	private ShardComparator<Object> comparator;
	private ApproximateContext context = new ApproximateContext();
	
	public ApproximatePageTable(TableShardSqlSessionTemplate tableShardSqlSessionTemplate,String statement, Object parameter,
			ProactorSetting setting) {
		this.tableShardSqlSessionTemplate = tableShardSqlSessionTemplate;
		this.statement = statement;
		this.parameter = parameter;
		this.setting = setting;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void init() throws NoSuchMethodException, SecurityException{		
		Class returnType = ProactorUtil.getReturnType(statement, tableShardSqlSessionTemplate.getSqlSessionFactory());
		Configuration configuration = tableShardSqlSessionTemplate.getConfiguration();
    	boolean isMapUnderscoreToCamelCase = configuration.isMapUnderscoreToCamelCase();
    	comparator = ShardComparatorFactory.buildShardComparator(setting, returnType, isMapUnderscoreToCamelCase);
		
		offset_statement = setting.getProperty( ApproximateUtil.Const_offset_statement_Key );
		offset_column = setting.getProperty( ApproximateUtil.Const_offset_column_Key );
		
		
		shardList = tableShardSqlSessionTemplate.lookupTableShardsByRouter(statement,parameter);
		
		Set<String> shardIdSet = new HashSet<String>();
		for(TableShard ts : shardList)
			shardIdSet.add(ts.getId());
		
		context.initOffset( shardIdSet );
	}
	
	public List<Object> query(){
		try {
			init();
		} catch (Exception e) {
			logger.warn( e.getMessage(), e);
		}
		
		List<Object> resultList = new ArrayList<Object>();
		try {
			List<Object> tempResultList = doQuery();
			if(CollectionUtils.isNotEmpty(tempResultList)){
				resultList.addAll(tempResultList);
			}
		} catch (Exception e) {
			logger.error( e.getMessage(), e);
		}
		return resultList;
	}

	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public List<Object> doQuery() throws NoSuchMethodException, SecurityException{
		List<Object> result = new ArrayList<Object>();
		ParameterWrapper paraWrapper = (ParameterWrapper)parameter;
		RowArgs originalRowArgs = paraWrapper.getRowArgs();
		int offset = originalRowArgs.getOffset();
		int limit = originalRowArgs.getLimit();
		int offsetLimit = offset + limit;
		int shardSize = shardList.size();
		int shardOffset = offset/shardSize;
		paraWrapper.setRowArgs( null );
		int memoryPageLimit = getMemoryPageOffset();
		int approximate_break = offset - memoryPageLimit;
		do{
			Object min = findMinRecord(shardList,shardOffset);
			
			paraWrapper.setExtParam(min);
			Map<String, Integer> offsetResult = null;
			try {
				offsetResult = findOffset( shardList);
			}finally{
				paraWrapper.setExtParam(null);
			}
			
			context.updateOffset(offsetResult);
			int offsetSum = context.sumOffsets();
			if( offsetSum >= approximate_break ){
				break;
			}else{
				shardOffset = (offset - offsetSum)/shardSize;
			}
		}while(true);
		paraWrapper.setRowArgs( originalRowArgs );
		
		int ultimateLimit = limit + ( offset - context.sumOffsets() );
		
		Map<TableShard,Object> shardParameters = new HashMap<TableShard, Object>();
		for (TableShard ts : shardList)  {
			ParameterWrapper shardParameterWrapper = new ParameterWrapper();
			shardParameterWrapper.setBaseParam(paraWrapper);
			int f = context.getOffset(ts.getId());
			RowArgs shardRowArgs = new RowArgs(f, ultimateLimit);
			shardParameterWrapper.setRowArgs(shardRowArgs);
			shardParameters.put(ts, shardParameterWrapper);
		}
		List<ShardResultSet> originalResultList = tableShardSqlSessionTemplate.selectList(statement, shardParameters);
		
		List<Object> tempResultList = new ArrayList<Object>();
		for (ShardResultSet item : originalResultList) {
			Object list = item.getResult();
			tempResultList.addAll( (List)list );
		}
		Collections.sort(tempResultList, comparator);
		
		List<Object> resultList = new ArrayList<Object>();
		int size = tempResultList.size();
		int fromIndex = offset - context.sumOffsets();
		int toIndex =  fromIndex + limit ;
		if( size > fromIndex ){
			if( size < toIndex ){
				toIndex = size;
			}
			resultList = tempResultList.subList(fromIndex, toIndex);
		}
		return resultList;
	}
	
	private Object findMinRecord(List<TableShard> dsMap,int offset){
		Object lastMin = context.getLastMinObject();
		Object min = null;
		Map<TableShard,Object> shardParameters = new HashMap<TableShard, Object>();
		for (TableShard ts : dsMap)  {
			ParameterWrapper shardParameterWrapper = new ParameterWrapper();
			shardParameterWrapper.setBaseParam(parameter);
			int shardOffset = context.getOffset(ts.getId());
			RowArgs shardRowArgs = new RowArgs( (shardOffset + offset), 1);
			shardParameterWrapper.setRowArgs(shardRowArgs);
			shardParameters.put(ts, shardParameterWrapper);
		}
		List<ShardResultSet> originalResultList = tableShardSqlSessionTemplate.selectList(statement, shardParameters);
		for (ShardResultSet concurrentResult : originalResultList) {
			List<Object> list = concurrentResult.getResult();
			if( list == null || list.size() < 1){
				continue;
			}
			Object e = list.get(0);
			if( lastMin != null ){
				int before = comparator.compare( lastMin, e);
				if( before > 0){
					continue;
				}
			}
			if(min == null){
				min = e;
			}else{
				int before = comparator.compare(min,e);
				if( before > 0){
					min = e;
				}
			}
		}
		if( min != null){
			context.setLastMinObject(min);			
		}
		return min;
	}
	
	private Map<String,Integer> findOffset( List<TableShard> dsMap){
		Map<String,Integer> offsetResult = new HashMap<String, Integer>();
		List<ShardResultSet> originalResultList = tableShardSqlSessionTemplate.selectList( offset_statement, parameter, dsMap);
		for (ShardResultSet concurrentResult : originalResultList) {
			String shardId = concurrentResult.getShardId();
			List<Object> list = concurrentResult.getResult();
			Object e = list.get(0);
			if( e != null ){
				int offset = 0;
				boolean isWrapper = ProactorUtil.isWrapper( e.getClass() );
				if( isWrapper ){
					Number number = (Number)e;
					offset = number.intValue();
				}else{
					Number number = (Number)ProactorUtil.getValue(e, offset_column);
					offset = number.intValue();
				}
				offsetResult.put(shardId, offset);
			}
		}
		return offsetResult;
	}

	public int getMemoryPageOffset() {
		return memoryPageOffset;
	}

	public void setMemoryPageOffset(int memoryPageOffset) {
		this.memoryPageOffset = memoryPageOffset;
	}
	
	
}