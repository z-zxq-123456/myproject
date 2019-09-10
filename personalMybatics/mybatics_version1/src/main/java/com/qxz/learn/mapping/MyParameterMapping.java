package com.qxz.learn.mapping;

import com.qxz.learn.configuration.MyConfiguration;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/9/27
 */
public class MyParameterMapping {

    private MyConfiguration configuration;

    private String property;

    private String resultMapId;

    private String jdbcTypeName;

    public MyConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(MyConfiguration configuration) {
        this.configuration = configuration;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getResultMapId() {
        return resultMapId;
    }

    public void setResultMapId(String resultMapId) {
        this.resultMapId = resultMapId;
    }

    public String getJdbcTypeName() {
        return jdbcTypeName;
    }

    public void setJdbcTypeName(String jdbcTypeName) {
        this.jdbcTypeName = jdbcTypeName;
    }
}
