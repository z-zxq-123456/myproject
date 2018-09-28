package com.qxz.learn.mapping;

import com.qxz.learn.configuration.MyConfiguration;
import com.qxz.learn.type.MyJdbcType;

import java.util.List;
import java.util.Set;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/9/28
 */
public class MyResultMapping {

    private MyConfiguration configuration;
    private String property;
    private String column;
    private Class<?> javaType;
    private MyJdbcType jdbcType;
    private Set<String> notNullColumns;
    private List<MyResultMapping> composites;
    private String resultSet;
    private String foreignColumn;
    private boolean lazy;
    private String columnPrefix;
    private String nestedResultMapId;


    public MyConfiguration getConfiguration() {
        return configuration;
    }

    public String getProperty() {
        return property;
    }

    public String getColumn() {
        return column;
    }

    public Class<?> getJavaType() {
        return javaType;
    }

    public MyJdbcType getJdbcType() {
        return jdbcType;
    }

    public Set<String> getNotNullColumns() {
        return notNullColumns;
    }

    public List<MyResultMapping> getComposites() {
        return composites;
    }

    public String getResultSet() {
        return resultSet;
    }

    public String getForeignColumn() {
        return foreignColumn;
    }

    public boolean isLazy() {
        return lazy;
    }

    public String getColumnPrefix() {
        return columnPrefix;
    }

    public String getNestedResultMapId() {
        return nestedResultMapId;
    }

    public void setNestedResultMapId(String nestedResultMapId) {
        this.nestedResultMapId = nestedResultMapId;
    }
}
