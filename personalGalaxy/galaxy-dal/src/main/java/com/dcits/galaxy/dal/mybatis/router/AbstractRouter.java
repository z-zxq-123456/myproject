package com.dcits.galaxy.dal.mybatis.router;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.dcits.galaxy.dal.mybatis.exception.DALException;
import com.dcits.galaxy.dal.mybatis.exception.NoShardFoundException;
import com.dcits.galaxy.dal.mybatis.shard.Shard;
import com.dcits.galaxy.dal.mybatis.shard.ShardManager;

public abstract class AbstractRouter implements IRouter {

	private ShardManager shardManager = null;
	
	private Map<String,String> aliasMap = new ConcurrentHashMap<>();
	
	public AbstractRouter() {
	}
	
	public AbstractRouter(ShardManager shardManager) {
		this.shardManager = shardManager;
	}

	public void setShardManager(ShardManager shardManager) {
		this.shardManager = shardManager;
	}
	
	protected abstract Set<String> doRoute(String sqlmap, Object argument);
	protected abstract boolean routeExist(String sqlmap);
	
	@Override
	public Set<String> route(String sqlmap, Object argument) {
		
		if (argument instanceof Collection) {
			Collection<?> params = (Collection<?>) argument;
			Set<String> shards = new HashSet<>();
			for (Object param : params) {
				Set<String> tempShardSet = applyRoute(sqlmap, param);
				if (!tempShardSet.isEmpty()) {
					shards.addAll(tempShardSet);
				}
			}
			return shards;
		} else {
			return applyRoute(sqlmap, argument);
		}
	}
	
	private Set<String> applyRoute(String sqlmap, Object argument){
		
		Set<String> result = new HashSet<>();
		String routeId = getRouteId(sqlmap);
		
		if(routeId != null) {
			result = doRoute(routeId, argument);
		}
		
		if(result.isEmpty()) {
			String namespace = getNameSpace(sqlmap);
			routeId = getRouteId(namespace);
			if(routeId != null) {
				result = doRoute(routeId, argument);
			}
		}
		
		return result;
	}

	@Override
	public Set<Shard> routeShard(String sqlmap, Object argument) {
		if (shardManager != null && shardManager.getShardSize() > 0) {
			Set<Shard> shardSet = new HashSet<Shard>();
			Set<String> ids = route(sqlmap, argument);
			if (!ids.isEmpty()) {
				for (String id : ids) {
					Shard s = shardManager.getShard(id);
					if (s == null) {
						throw new NoShardFoundException("No shard found in shardManager which shardId = " + id + " when routing for  \"" + sqlmap
								+ "\"");
					}
					shardSet.add(shardManager.getShard(id));
				}
			}
			return shardSet;
		}
		return Collections.emptySet();
	}
	
	@Override
	public String getMatchSqlmap(String sqlmap, Object argument) {
		
		if(argument instanceof Collection){
			return null;
		}
		
		String routeId = getRouteId(sqlmap);
		
		if(routeId != null && !doRoute(routeId, argument).isEmpty()) {
			return routeId;
		}
		
		String namespace = getNameSpace(sqlmap);
		routeId = getRouteId(namespace);
		if(routeId != null && !doRoute(routeId, argument).isEmpty()) {
			return routeId;
		}
		return null;
	}
	
	@Override
	public boolean isRouteExist(String sqlmap) {
		if(getRouteId(sqlmap)!=null){
			return true;
		}
		String namespace = getNameSpace(sqlmap);
		if(getRouteId(namespace)!=null){
			return true;
		}
		
		return false;
	}
	
	@Override
	public void alias(String sourceSqlId, String alias) {
		if(getRouteId(sourceSqlId) == null){
			throw new DALException("source sql id:" + sourceSqlId + " not exist!");
		}
		
		if(routeExist(alias) ) {
			throw new DALException("sql id:" + alias + "hava exist!");
		}
		
		if(aliasMap.containsKey(alias)) {
			throw new DALException("alias:" + alias + "hava exist!");
		}
		
		aliasMap.put(alias, sourceSqlId);
	}
	
	private String getRouteId(String sourceSqlId){
		if(sourceSqlId == null) {
			return null;
		}
		
		if(routeExist(sourceSqlId)){
			return sourceSqlId;
		}
		
		if(aliasMap.containsKey(sourceSqlId)) {
			return getRouteId(aliasMap.get(sourceSqlId));
		}
		
		return null;
	}
	
	private String getNameSpace(String sqlId){
		if(sqlId.lastIndexOf(".") == -1){
			return null;
		}
		
		return sqlId.substring(0, sqlId.lastIndexOf("."));
	}

}
