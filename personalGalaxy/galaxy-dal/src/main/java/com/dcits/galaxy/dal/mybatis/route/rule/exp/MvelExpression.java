package com.dcits.galaxy.dal.mybatis.route.rule.exp;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.mvel2.MVEL;
import org.mvel2.integration.impl.MapVariableResolverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dcits.galaxy.dal.mybatis.exception.DALException;
import com.dcits.galaxy.dal.mybatis.route.RouteException;
import com.dcits.galaxy.dal.mybatis.utils.MetaUtils;

/**
 * 表达式接口实现
 * @author chenhongk
 *
 */
public class MvelExpression implements Expression {
	
	private transient final Logger logger = LoggerFactory
			.getLogger(MvelExpression.class);
	/*
	 * 路由表达式
	 */
	private String expression;
	/*
	 * 路由表达式形参集合
	 * sString 为表达式参数名，FormalParam具体参数
	 */
	private Map<String, FormalParam> formalParamMap;
	/*
	 * 路由函数集合
	 */
	private Map<String, Object> functions;
	
	public MvelExpression(String expression) {
		this.expression = expression;
	}
	
	public MvelExpression(String expression, Map<String, FormalParam> formalParamMap, Map<String, Object> functions) {
		this.expression = expression;
		this.formalParamMap = formalParamMap;
		this.functions = functions;
	}


	/**
	 * 获得路由表达式字符串类型
	 */
	@Override
	public String expr() {
		return this.expression;
	}
	/**
	 * 执行路由表达式
	 */
	@Override
	public String apply(Object context) {
		Object temp = context;
		if(hasFormalParams()){
			Map<String,FormalParam> defaultParamMap = new HashMap<>(formalParamMap);
			Map<String,Object> map = new  HashMap<String,Object>();
			if(context instanceof Map){
				Map<String,Object> alias = (Map<String, Object>) context;
				for(Entry<String, FormalParam> entry:defaultParamMap.entrySet()){
					String name = entry.getKey();
					FormalParam param = entry.getValue();
					if(alias.containsKey(name)){
						if(validateClassType(alias.get(name),entry.getValue().getClass())){
							map.put(name, alias.get(name));
						}else{
							logger.error("the formalParam's classType not match :"+name);
							throw new RouteException("the formalParam's classType not match :"+name);
						}
					}
				}
			}else{
				for(Entry<String, FormalParam> entry:defaultParamMap.entrySet()){
					String name = entry.getKey();
					Object o = MetaUtils.getValue(context, name);
					if(null!=o){
						if(validateClassType(o,entry.getValue().getClass())){
							map.put(name, o);
						}else{
							logger.error("the formalParam's classType not match :"+name);
							throw new RouteException("the formalParam's classType not match :"+name);
						}
					}else{
						map.put(name, entry.getValue().getValue());
					}
				}
			}
			temp = map;
		}
		Map<String, Object> vrs = new HashMap<String, Object>();
		vrs.putAll(functions);
		vrs.put("$ROOT", temp);
		try {
			return MVEL.evalToString(expression, temp,
					new MapVariableResolverFactory(vrs));
		}catch (Exception e) {
			logger.error("Mvel execute error!!"+e.getMessage());
			throw new DALException(e);
		}
	}
	/**
	 * 表达式是否存在别名
	 */
	private boolean hasFormalParams(){
		return (null!=formalParamMap)&&!formalParamMap.isEmpty();
	}
	/**
	 * 验证参数类型是否一致
	 * @param source
	 * @param target
	 * @return
	 */
	private boolean validateClassType(Object source,Class<?> type){
		return true;
	}
	
	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public Map<String, FormalParam> getFormalParamMap() {
		return formalParamMap;
	}

	public void setFormalParamMap(Map<String, FormalParam> formalParamMap) {
		this.formalParamMap = formalParamMap;
	}

}
