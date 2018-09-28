package com.qxz.learn.mapping;

import com.qxz.learn.configuration.MyConfiguration;
import com.qxz.learn.scripting.MyLanguageDriver;

import java.util.List;

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
    private MySqlSource sqlSource;
    private MyStatementType statementType;
    private MyLanguageDriver lang;
    private List<MyResultMap> resultMaps;



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

    public MySqlSource getSqlSource() {
        return sqlSource;
    }

    public void setSqlSource(MySqlSource sqlSource) {
        this.sqlSource = sqlSource;
    }

    public MyStatementType getStatementType() {
        return statementType;
    }

    public void setStatementType(MyStatementType statementType) {
        this.statementType = statementType;
    }

    public MyLanguageDriver getLang() {
        return lang;
    }

    public void setLang(MyLanguageDriver lang) {
        this.lang = lang;
    }

    public List<MyResultMap> getResultMaps() {
        return resultMaps;
    }

    public MyBoundSql getBoundSql(Object parameterObject){
        MyBoundSql boundSql = sqlSource.getBoundSql(parameterObject);
        List<MyParameterMapping> parameterMappings = boundSql.getMyParameterMappings();
        if (parameterMappings == null || parameterMappings.isEmpty()){
            boundSql = new MyBoundSql(boundSql.getSql(),parameterObject,parameterMappings);
        }
        return boundSql;
    }
}
