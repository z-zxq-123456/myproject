package com.dcits.galaxy.cache.paratab;


import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.dcits.galaxy.cache.colhandle.ColHandleProxy;
import com.dcits.galaxy.cache.exception.CacheException;
import com.dcits.galaxy.dal.mybatis.ShardSqlSessionTemplate;



/**
 * 提供 MAPPER SQL对应的参数  
 * @author tangxlf
 * @date   2015-1-7
 * 
 */
@Component
public class SqlParameterProvider {
	@Resource
	private MusterParaTabCacheConfig musterParaTabCacheConfig;
	@Resource
	private ShardSqlSessionTemplate shardSqlSessionTemplate;	
	
	private final static String TABLE_NAME ="tableName";
	
	private final static String WHERE_CON ="whereCon";	
	
	private final static String INSERT_COL ="insertCol";	
	
	private final static String UPDATE_COL ="updateCol";
	
	private final static String INSERT_VALUE ="insertValue";	
	
	

	/**
	 * 初始化查询MAPPER参数
	 * @param String tableName     缓存表名称          
	 * @return Map<String,String>  MAPPER参数
	 */ 	
	public Map<String,String> initParaProv(String tableName){
		ParaTabCacheInfo info = musterParaTabCacheConfig.getCacheInfo(tableName);
		Map<String,String> initMap = new HashMap<String,String>();			
		if(info==null){
			throw new CacheException(tableName+"该表缓存信息尚未配置!"); 
		}else{
			initMap.put(TABLE_NAME,info.getDbTableName());
			initMap.put(WHERE_CON,info.getDbQueryCon());
		}
		return initMap;
	}
	/**
	 * 主键查询MAPPER参数
	 * @param String tableName     缓存表名称          
	 * @return Map<String,String>  MAPPER参数
	 */ 	
	public Map<String,String> selectPkParaProv(String tableName,Map<String,Object> queryMap){
		ParaTabCacheInfo info = musterParaTabCacheConfig.getCacheInfo(tableName);
		Map<String,String> insertMap = new HashMap<String,String>();			
		if(info==null){
			throw new CacheException(tableName+"该表缓存信息尚未配置!"); 
		}else{
			insertMap.put(TABLE_NAME,info.getDbTableName());
			insertMap.put(WHERE_CON,createPkWhereSql(tableName,queryMap));
		}
		return insertMap;
	}
	/**
	 * 查询列表MAPPER参数
	 * @param String tableName     缓存表名称          
	 * @return Map<String,String>  MAPPER参数
	 */ 	
	public Map<String,String> selectListParaProv(String tableName,Map<String,Object> queryMap){
		return selectPkParaProv(tableName,queryMap);
	}
	/**
	 * 新增MAPPER参数
	 * @param String tableName     缓存表名称          
	 * @return Map<String,String>  MAPPER参数
	 */ 	
	public Map<String,String> insertParaProv(String tableName,Map<String,Object> rowMap){
		ParaTabCacheInfo info = musterParaTabCacheConfig.getCacheInfo(tableName);
		Map<String,String> insertMap = new HashMap<String,String>();			
		if(info==null){
			throw new CacheException(tableName+"该表缓存信息尚未配置!"); 
		}else{
			insertMap.put(TABLE_NAME,info.getDbTableName());
			String[]  mapStrs = createInsertSql(tableName,rowMap);
			insertMap.put(INSERT_COL,mapStrs[0]);
			insertMap.put(INSERT_VALUE,mapStrs[1]);
		}
		return insertMap;
	}
	/**
	 * 新增MAPPER参数
	 * @param String tableName     缓存表名称          
	 * @return Map<String,String>  MAPPER参数
	 */ 	
	public Map<String,String> updateParaProv(String tableName,Map<String,Object> rowMap){
		ParaTabCacheInfo info = musterParaTabCacheConfig.getCacheInfo(tableName);
		Map<String,String> insertMap = new HashMap<String,String>();			
		if(info==null){
			throw new CacheException(tableName+"该表缓存信息尚未配置!"); 
		}else{
			insertMap.put(TABLE_NAME,info.getDbTableName());
			String[]  mapStrs = createUpdateSql(tableName,rowMap);
			insertMap.put(UPDATE_COL,mapStrs[0]);
			insertMap.put(WHERE_CON,mapStrs[1]);
		}
		return insertMap;
	}
	/**
	 * 主键查询MAPPER参数
	 * @param String tableName     缓存表名称          
	 * @return Map<String,String>  MAPPER参数
	 */ 	
	public Map<String,String> deleteParaProv(String tableName,Map<String,Object> deleteMap){		
		return selectPkParaProv(tableName,deleteMap);
	}
	
	private String createPkWhereSql(String tableName,Map<String,Object> queryMap){
		StringBuilder whereSb = new StringBuilder(" 1=1 ");
		String pkStr = musterParaTabCacheConfig.getPkStrs(tableName);
		if(pkStr==null||pkStr.trim().equals("")){
			return whereSb.toString();
		}
		boolean isHasWhere = false;
		for(String pkColName : pkStr.split(",")){
			Object dataValue = queryMap.get(pkColName);
			if(dataValue!=null&&!(""+dataValue).trim().equals("")){
				String dbColName = musterParaTabCacheConfig.getDbColName(tableName,pkColName);
				if(dbColName!=null){
					whereSb.append(" and "+dbColName+" = "+ColHandleProxy.handleColValue(musterParaTabCacheConfig.getDbProdName(),
							                                                             musterParaTabCacheConfig.getColType(tableName,pkColName),
							                                                             dataValue));
					isHasWhere = true;
				}
			}
		}
		if(!isHasWhere ){
			throw new CacheException(tableName+"该表主键值传递不全!"); 
		}
		return whereSb.toString();
	}	
	
	private String[] createInsertSql(String tableName,Map<String,Object> rowMap){
		StringBuilder colSb = new StringBuilder("");
		StringBuilder valueSb = new StringBuilder("");		
		for(Map.Entry<String,Object> entry : rowMap.entrySet()){
			Object dataValue = rowMap.get(entry.getKey());
			if(dataValue!=null&&!(""+dataValue).trim().equals("")){
				String dbColName = musterParaTabCacheConfig.getDbColName(tableName,entry.getKey());
				if(dbColName!=null){
					colSb.append(dbColName+",");
					valueSb.append(ColHandleProxy.handleColValue(musterParaTabCacheConfig.getDbProdName(),
                            musterParaTabCacheConfig.getColType(tableName,entry.getKey()),
                            dataValue)+",");					
				}
			}					
		}
		return new String[]{colSb.substring(0,colSb.length()-1),valueSb.substring(0,valueSb.length()-1)};
	}	
	
	private String[] createUpdateSql(String tableName,Map<String,Object> rowMap){		
		StringBuilder setSb = new StringBuilder("");
		StringBuilder whereSb = new StringBuilder(" 1=1 ");	
		String pkStr = musterParaTabCacheConfig.getPkStrs(tableName);
		String[] pks = pkStr.split(",");
		int pkColNum = 0; //主键值不为空数量
		for(Map.Entry<String,Object> entry : rowMap.entrySet()){
			Object dataValue = rowMap.get(entry.getKey());
			if(dataValue!=null){
				String dbColName = musterParaTabCacheConfig.getDbColName(tableName,entry.getKey());
				if(dbColName!=null){
					for(String pkColName:pks){
						if(pkColName.trim().equals(entry.getKey().trim())){
							whereSb.append(" AND "+dbColName+" = "+ColHandleProxy.handleColValue(musterParaTabCacheConfig.getDbProdName(),
                                    musterParaTabCacheConfig.getColType(tableName,pkColName),
                                    dataValue));
							pkColNum ++;
							continue;
						}
					}					
					setSb.append(dbColName+" = "+ColHandleProxy.handleColValue(musterParaTabCacheConfig.getDbProdName(),
                            musterParaTabCacheConfig.getColType(tableName,entry.getKey()),
                            dataValue)+",");					
				}
			}					
		}
		if(pkColNum!=pks.length){
			throw new CacheException(tableName+"该表主键值不全!"+"主键串为"+pkStr+" 主键值为"+whereSb); 
		}
		return new String[]{setSb.substring(0,setSb.length()-1),whereSb.toString()};
	}	
}
