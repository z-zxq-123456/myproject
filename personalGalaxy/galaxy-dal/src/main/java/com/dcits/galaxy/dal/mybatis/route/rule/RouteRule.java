package com.dcits.galaxy.dal.mybatis.route.rule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dcits.galaxy.dal.mybatis.route.RouteException;
import com.dcits.galaxy.dal.mybatis.route.rule.exp.Expression;
import com.dcits.galaxy.dal.mybatis.utils.MetaUtils;


/**
 * 封装具体的路由规则
 * 
 * @author chen.hong
 *
 */
public class RouteRule {
	
	private transient final Logger logger = LoggerFactory.getLogger(RouteRule.class);
	/*
	 * 路由表达式
	 */
    private Expression expression;
    /*
     * 路由结果
     */
    private RouteResult routeResult;
    /*
     * 路由依据
     */
    private List<RouteBasis> routeBasis;
    /*
     * 默认路由结果名
     */
    public static final String ROUTE_SHARD_DEFAULT_NAME = "default";
	/*
	 * 路由结果集类型：list
	 */
	public static final String RULE_RESULT_LIST = "list";
	/*
	 * 路由结果集类型：map
	 */
	public static final String RULE_RESULT_MAP = "map";
	/*
	 * 路由结果集为list时，默认分隔符"," 
	 */
	public static final String RULE_RESULT_LIST_DEFAULT_SPARATOR = ",";
	
	public RouteRule(Expression expression, RouteResult routeResult, List<RouteBasis> routeBasis) {
		this.expression = expression;
		this.routeResult = routeResult;
		this.routeBasis = routeBasis;
	}
	/**
     * 根据所传入的sqlmap与argument判断是否符合此条规则
     * @param sqlmap 所执行的sql语句所对应的sqlmap或namespace
     * @param argument 
     * @return 若符合此规则返回结果
     */
    public String apply(String sqlmap, Object argument) {
    	Object temp = argument;
    	if(null!=argument){
    		Map<String, String> alias = findRouteBasis(sqlmap).getActualParamMap();
    		if(alias!=null&&alias.size()>0){
        		Map<String,Object> map = new HashMap<String,Object>();
            	for(Entry<String, String> entry:alias.entrySet()){
    				String name = entry.getKey();
    				String aliastemp = entry.getValue();
    				Object o = MetaUtils.getValue(argument,name);
    				map.put(aliastemp, o);
    			}
            	temp = map;
        	}
    	}
    	return expression.apply(temp);
    }
    /**
     * 根据路由结果获得分库结果集
     * @param obj 路由结果
     * @return 若路由结果存在分库信息则返回，若有默认分库信息则返回，否则返回空
     */
    public Set<String> getShards(String obj){
    	Set<String> result = null;
    	if(this.routeResult.getType().equals(RULE_RESULT_LIST)){
    		List<String> resultList = this.routeResult.getResultList();
    		String separator = this.routeResult.getSeparator();
    		result = new HashSet<String>();
			String[] ids = obj.split("\\"+separator);
			for (String id : ids) {
				int idInt = Integer.parseInt(id);
				if(idInt>resultList.size()-1||idInt<0){
					logger.error("this result:"+idInt+" not config routeRule");
					throw new RouteException("this result:"+idInt+" not config routeRule");
				}else if(idInt == -1){
					result.addAll(resultList);
				}else{
					result.add(resultList.get(idInt));
				}
			}
    	}else if(this.routeResult.getType().equals(RULE_RESULT_MAP)){
    		Map<String, Set<String>> resultMap = this.routeResult.getResultMap();
    		if(null!=resultMap){
        		if(resultMap.containsKey(obj)){
        			result =resultMap.get(obj);
        		}else{
        			if(resultMap.containsKey(ROUTE_SHARD_DEFAULT_NAME)){
        				result =resultMap.get(ROUTE_SHARD_DEFAULT_NAME);
        			}else{
        				logger.error("this result:"+obj+" not config routeRule");
    					throw new RouteException("this result:"+obj+" not config routeRule");
        			}
        		}
        	}
    	}
    	return result;
    }
    /**
     * 判断表达式是否为空
     * @return
     */
    public boolean hasExpression(){
    	return null!=expression;
    }
    /**
     * 根据路由id查找对应的sqlmap，namespace 对象
     * @param sqlMap
     * @return
     */
    private RouteBasis findRouteBasis(String sqlMap){
    	RouteBasis tempSqlMap = null;
    	if(null!=routeBasis){
    		for(RouteBasis target:routeBasis){
    			if(target.getBasis().equals(sqlMap)){
    				tempSqlMap = target;
    				break;
    			}
    		}
    	}
    	return tempSqlMap;
    }
    
    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

	public List<RouteBasis> getRouteBasis() {
		return routeBasis;
	}
	public void setRouteBasis(List<RouteBasis> routeBasis) {
		this.routeBasis = routeBasis;
	}
	public RouteResult getRouteResult() {
		return routeResult;
	}
	public void setRouteResult(RouteResult routeResult) {
		this.routeResult = routeResult;
	}
	@Override
    public String toString() {
        return "Route{" +
                "expression=" + expression +
                '}';
    }
}
