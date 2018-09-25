package com.dcits.galaxy.dal.mybatis.merger.comparactor;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dcits.galaxy.dal.mybatis.merger.config.vo.RuleColumn;
import com.dcits.galaxy.dal.mybatis.merger.config.vo.RuleSetting;

/**
 * 比较器工厂
 * @author yin.weicai
 */
public class ShardComparatorFactory {
	
	@SuppressWarnings("rawtypes")
	private static final Map<String, ShardComparator> comparatorMap = new ConcurrentHashMap<String, ShardComparator>();
	
	@SuppressWarnings("rawtypes")
	public static ShardComparator get( String sqlMap,RuleSetting setting, Class objectClass,boolean isMapUnderscoreToCamelCase){
		ShardComparator shardComparator = comparatorMap.get( sqlMap );
		if( shardComparator == null ){
			shardComparator = buildShardComparator(sqlMap, setting, objectClass,isMapUnderscoreToCamelCase);
		}
		return shardComparator;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static synchronized ShardComparator buildShardComparator(String sqlMap,RuleSetting setting, 
			Class objectClass,boolean mapUnderscoreToCamelCase){
		ShardComparator shardComparator = comparatorMap.get( sqlMap );
		if( shardComparator == null ){
			ShardComparator lastComparator = shardComparator;
			List<RuleColumn> columns = setting.getColumns();
			for (RuleColumn column : columns) {
				ShardComparator nextComparator = ShardComparator.newInstance(objectClass);
				String columnName = column.getName();
				nextComparator.setColumn(columnName);
				if(column.hasAlias()){					
					String alias = column.getAlias();
					nextComparator.setMappedColumn( alias );
				}
				boolean isDoAsc = column.isAsc();
				nextComparator.setAsc(isDoAsc);
				nextComparator.setMapUnderscoreToCamelCase( mapUnderscoreToCamelCase );
				
				if( shardComparator == null){
					shardComparator = lastComparator = nextComparator;
				}else{
					lastComparator.setNextComparator( nextComparator );
					lastComparator = nextComparator;
				}
			}
			comparatorMap.put(sqlMap, shardComparator);
		}
		return shardComparator;
	}
	
	@SuppressWarnings("rawtypes")
	public static ShardComparator buildShardComparator(RuleColumn ruleColumn,Class objectClass,
			boolean mapUnderscoreToCamelCase){
		ShardComparator shardComparator = ShardComparator.newInstance(objectClass);
		String columnName = ruleColumn.getName();
		shardComparator.setColumn(columnName);
		if(ruleColumn.hasAlias()){					
			String alias = ruleColumn.getAlias();
			shardComparator.setMappedColumn( alias );
		}
		boolean isDoAsc = ruleColumn.isAsc();
		shardComparator.setAsc(isDoAsc);
		shardComparator.setMapUnderscoreToCamelCase( mapUnderscoreToCamelCase );
		return shardComparator;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static ShardComparator buildShardComparator(RuleSetting setting,Class objectClass,
			boolean mapUnderscoreToCamelCase){
		ShardComparator shardComparator = null;
		ShardComparator lastComparator = shardComparator;
		List<RuleColumn> columns = setting.getColumns();
		for (RuleColumn column : columns) {
			ShardComparator nextComparator = ShardComparator.newInstance(objectClass);
			String columnName = column.getName();
			nextComparator.setColumn(columnName);
			if(column.hasAlias()){					
				String alias = column.getAlias();
				nextComparator.setMappedColumn( alias );
			}
			boolean isDoAsc = column.isAsc();
			nextComparator.setAsc(isDoAsc);
			nextComparator.setMapUnderscoreToCamelCase( mapUnderscoreToCamelCase );
			
			if( shardComparator == null){
				shardComparator = lastComparator = nextComparator;
			}else{
				lastComparator.setNextComparator( nextComparator );
				lastComparator = nextComparator;
			}
		}
		return shardComparator;
	}
}
