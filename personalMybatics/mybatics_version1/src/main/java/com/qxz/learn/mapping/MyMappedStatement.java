package com.qxz.learn.mapping;

import com.qxz.learn.configuration.MyConfiguration;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/9/26
 */
public class MyMappedStatement {

    private String id;
    private String resource;
    private String [] resultSet;
    private String databaseId;
    private Integer timeOut;
    private Integer fetchSize;
    private MyConfiguration configuration;
    private String[] keyProperites;
    private String[] keyColumns;
    private boolean hasNestedResultMaps;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String[] getResultSet() {
        return resultSet;
    }

    public void setResultSet(String[] resultSet) {
        this.resultSet = resultSet;
    }

    public String getDatabaseId() {
        return databaseId;
    }

    public void setDatabaseId(String databaseId) {
        this.databaseId = databaseId;
    }

    public Integer getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Integer timeOut) {
        this.timeOut = timeOut;
    }

    public Integer getFetchSize() {
        return fetchSize;
    }

    public void setFetchSize(Integer fetchSize) {
        this.fetchSize = fetchSize;
    }

    public MyConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(MyConfiguration configuration) {
        this.configuration = configuration;
    }

    public String[] getKeyProperites() {
        return keyProperites;
    }

    public void setKeyProperites(String[] keyProperites) {
        this.keyProperites = keyProperites;
    }

    public String[] getKeyColumns() {
        return keyColumns;
    }

    public void setKeyColumns(String[] keyColumns) {
        this.keyColumns = keyColumns;
    }

    public boolean isHasNestedResultMaps() {
        return hasNestedResultMaps;
    }

    public void setHasNestedResultMaps(boolean hasNestedResultMaps) {
        this.hasNestedResultMaps = hasNestedResultMaps;
    }
}
