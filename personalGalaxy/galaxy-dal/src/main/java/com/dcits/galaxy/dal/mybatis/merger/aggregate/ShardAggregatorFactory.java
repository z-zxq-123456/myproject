package com.dcits.galaxy.dal.mybatis.merger.aggregate;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dcits.galaxy.dal.mybatis.merger.comparactor.ShardComparator;
import com.dcits.galaxy.dal.mybatis.merger.comparactor.ShardComparatorFactory;
import com.dcits.galaxy.dal.mybatis.merger.config.vo.RuleColumn;
import com.dcits.galaxy.dal.mybatis.merger.config.vo.RuleSetting;

public class ShardAggregatorFactory {
	
	public final static String Aggregate_Max = "max";
	public final static String Aggregate_Min = "min";
	public final static String Aggregate_Sum = "sum";
	public final static String Aggregate_Count = "count";
	
	private static final Map<String, ShardAggregator> comparatorMap = new ConcurrentHashMap<String, ShardAggregator>();
	
	@SuppressWarnings("rawtypes")
	public static ShardAggregator get( String sqlMap,RuleSetting setting, Class objectClass,boolean isMapUnderscoreToCamelCase){
		ShardAggregator shardAggregator = comparatorMap.get( sqlMap );
		if( shardAggregator == null ){
			shardAggregator = buildShardComparator(sqlMap, setting, objectClass,isMapUnderscoreToCamelCase);
		}
		return shardAggregator;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static synchronized ShardAggregator buildShardComparator(String sqlMap,RuleSetting setting, 
			Class objectClass,boolean mapUnderscoreToCamelCase){
		ShardAggregator shardAggregator = comparatorMap.get( sqlMap );
		if( shardAggregator == null ){
			ShardAggregator lastAggregator = null;
			List<RuleColumn> columns = setting.getColumns();
			for (RuleColumn column : columns) {
				String aggregate = column.getAggregate();
				ShardAggregator nextAggregator = null;
				if( Aggregate_Max.equalsIgnoreCase(aggregate)){
					MaxAggregator maxAggregator = new MaxAggregator();
					ShardComparator shardComparator = ShardComparatorFactory.buildShardComparator(column, 
							objectClass, mapUnderscoreToCamelCase);
					maxAggregator.setComparator(shardComparator);
					nextAggregator = maxAggregator;
				}else if( Aggregate_Min.equalsIgnoreCase(aggregate)){
					MinAggregator minAggregator = new MinAggregator();
					ShardComparator shardComparator = ShardComparatorFactory.buildShardComparator(column, 
							objectClass, mapUnderscoreToCamelCase);
					minAggregator.setComparator(shardComparator);
					nextAggregator = minAggregator;
				}else if( Aggregate_Sum.equalsIgnoreCase(aggregate)){
					nextAggregator = new SumAggregator();
				}else if( Aggregate_Count.equalsIgnoreCase(aggregate)){
					nextAggregator = new CountAggregator();
				}else{
					//TODO
				}
				
				if( nextAggregator == null ){
					continue;
				}
				nextAggregator.setObjectClass(objectClass);
				
				String columnName = column.getName();
				nextAggregator.setColumn(columnName);
				
				if(column.hasAlias()){					
					String alias = column.getAlias();
					nextAggregator.setMappedColumn( alias );
				}
				
				if( shardAggregator == null){
					shardAggregator = lastAggregator = nextAggregator;
				}else{
					lastAggregator.setNextAggregator( nextAggregator );
					lastAggregator = nextAggregator;
				}
			}
			comparatorMap.put(sqlMap, shardAggregator);
		}
		return shardAggregator;
	}
}
