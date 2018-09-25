package com.dcits.galaxy.dal.mybatis.route.rule.exp;


/**
 * 表达式接口
 * @author chen.hong
 *
 */
public interface Expression {
	/**
	 * 获得表达式
	 * @return string 返回表达式字符串
	 */
    String expr();
    /**
     * 执行表达式
     * @param context 
     * @return string 执行结果
     */
    String apply(Object context);
}



