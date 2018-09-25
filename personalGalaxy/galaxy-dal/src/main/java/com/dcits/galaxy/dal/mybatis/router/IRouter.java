package com.dcits.galaxy.dal.mybatis.router;

import java.util.Set;

import com.dcits.galaxy.dal.mybatis.shard.Shard;

/**
 * 路由接口，定义路由应所实现的方法
 *  
 * @author fan.kaijie
 */
public interface IRouter {
	
	/**
	 * 以sqlmap与argument共同确定一组分库id
	 * @param sqlmap
	 * @param argument 当参数为基本类型或String时，请将数据放入Map中
	 * @return Set<String> 返回一组分库id
	 */
    Set<String> route(String sqlmap, Object argument);
    
    /**
     * 返回由sqlmap与argument匹配的一组Shard
     * @param sqlmap
     * @param argument 当参数为基本类型或String时，请将数据放入Map中
     * @return Set<Shard>
     */
    Set<Shard> routeShard(String sqlmap, Object argument);
    
    /**
     * 返回当前sqlmap与argument所匹配的规则，其返回值为：
     * sqlid 由当前sqlmap所对应的规则匹配，返回值等于sqlmap
     * namespace 由namespace所对应的规则匹配，返回值等于sqlmap所在的namespace
     * null 没有匹配到任何规则
     * @param sqlmap
     * @param argument  当参数为基本类型或String时，请将数据放入Map中
     * @return
     */
    String getMatchSqlmap(String sqlmap, Object argument);
    
    /**
     * 返回是否存在当前sqlmap的路由规则
     * @param sqlmap
     * @return boolean 路由信息是否存在
     */
	boolean isRouteExist(String sqlmap);
	
	
	/**
	 * 给sourceSqlId生成别名
	 * @param sourceSqlId 原Sqlid
	 * @param alias 别名
	 */
	void alias(String sourceSqlId, String alias);
	
}
