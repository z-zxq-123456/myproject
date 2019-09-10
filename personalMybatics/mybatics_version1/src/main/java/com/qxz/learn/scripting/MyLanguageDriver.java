package com.qxz.learn.scripting;

import com.qxz.learn.mapping.MyBoundSql;
import com.qxz.learn.mapping.MyMappedStatement;
import com.qxz.learn.parameter.MyParameterHandler;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/9/27
 */
public interface MyLanguageDriver {

    /**
     * createParameterHandler
     * @param mappedStatement
     * @param parameterObject
     * @param boundSql
     * @return
     */
    MyParameterHandler createParameterHandler(MyMappedStatement mappedStatement, Object parameterObject, MyBoundSql boundSql);
}
