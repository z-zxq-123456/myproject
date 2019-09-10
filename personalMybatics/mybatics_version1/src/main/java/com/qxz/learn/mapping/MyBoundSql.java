package com.qxz.learn.mapping;

import java.util.List;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/9/27
 */
public class MyBoundSql {

    private String sql;
    private Object parameterObject;
    private List<MyParameterMapping> myParameterMappings;

    public MyBoundSql(String sql, Object parameterObject, List<MyParameterMapping> myParameterMappings) {
        this.sql = sql;
        this.parameterObject = parameterObject;
        this.myParameterMappings = myParameterMappings;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Object getParameterObject() {
        return parameterObject;
    }

    public void setParameterObject(Object parameterObject) {
        this.parameterObject = parameterObject;
    }

    public List<MyParameterMapping> getMyParameterMappings() {
        return myParameterMappings;
    }

    public void setMyParameterMappings(List<MyParameterMapping> myParameterMappings) {
        this.myParameterMappings = myParameterMappings;
    }
}
