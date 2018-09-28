package com.qxz.learn.configuration;

import com.qxz.learn.binding.MyMappedRegistry;
import com.qxz.learn.exception.MySqlException;
import com.qxz.learn.executor.MyDefaultResultSetHandler;
import com.qxz.learn.executor.MyExecutor;
import com.qxz.learn.executor.MyResultSetHandler;
import com.qxz.learn.executor.MySimpleExecutor;
import com.qxz.learn.factory.MyDefaultObjectFactory;
import com.qxz.learn.factory.MyObjectFactory;
import com.qxz.learn.mapping.MyBoundSql;
import com.qxz.learn.mapping.MyEnvironment;
import com.qxz.learn.mapping.MyMappedStatement;
import com.qxz.learn.mapping.MyResultMap;
import com.qxz.learn.parameter.MyParameterHandler;
import com.qxz.learn.session.MySqlSession;
import com.qxz.learn.result.MyResultHandler;
import com.qxz.learn.statement.MyRoutingStatementHandler;
import com.qxz.learn.statement.MyStatementHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/9/26
 */
public class MyConfiguration {

    private MyEnvironment environment;
    private MyMappedRegistry mappedRegistry = new MyMappedRegistry(this);
    private Map<String,MyMappedStatement>  mappedStatement = new HashMap<>();
    protected Integer defaultStatementTimeout;
    protected Integer defaultFetchSize;
    protected boolean useColumnLabel = true;
    protected MyObjectFactory objectFactory = new MyDefaultObjectFactory();

    protected final Map<String, MyResultMap> resultMaps = new StrictMap<>("Result Maps collection");


    public MyObjectFactory getObjectFactory() {
        return objectFactory;
    }

    public void setObjectFactory(MyObjectFactory objectFactory) {
        this.objectFactory = objectFactory;
    }

    public void addResultMap(MyResultMap rm) {
        resultMaps.put(rm.getId(), rm);
    }

    public MyResultMap getResultMap(String id) {
        return resultMaps.get(id);
    }

    public boolean hasResultMap(String id){
        return resultMaps.containsKey(id);
    }

    public boolean isUseColumnLabel() {
        return useColumnLabel;
    }

    public void setUseColumnLabel(boolean useColumnLabel) {
        this.useColumnLabel = useColumnLabel;
    }

    public Integer getDefaultStatementTimeout() {
        return defaultStatementTimeout;
    }

    public void setDefaultStatementTimeout(Integer defaultStatementTimeout) {
        this.defaultStatementTimeout = defaultStatementTimeout;
    }

    public Integer getDefaultFetchSize() {
        return defaultFetchSize;
    }

    public void setDefaultFetchSize(Integer defaultFetchSize) {
        this.defaultFetchSize = defaultFetchSize;
    }

    public <T> void addMapper(Class<T> t) {

    }
    public <T> T getMapper(Class<T> t, MySqlSession sqlSession) {
        return null;
    }

    public MyMappedRegistry getMappedRegistry() {
        return mappedRegistry;
    }

    public void setMappedRegistry(MyMappedRegistry mappedRegistry) {
        this.mappedRegistry = mappedRegistry;
    }

    public MyMappedStatement getMappedStatement(String id) {
        return mappedStatement.get(id);
    }


    public void addMappedStatement(MyMappedStatement mappedStatement) {
        this.mappedStatement.put(mappedStatement.getId(),mappedStatement);
    }

    public MyEnvironment getEnvironment() {
        return environment;
    }

    public void setEnvironment(MyEnvironment environment) {
        this.environment = environment;
    }

    public MyExecutor newExecutor(){
        return new MySimpleExecutor(this);
    }

    public MyStatementHandler newStatementHandler(MyExecutor executor, MyMappedStatement ms, Object parameterObject, MyResultHandler resultHandlerhandler, MyBoundSql boundSql)throws MySqlException{
        MyStatementHandler statementHandler = new MyRoutingStatementHandler(executor,ms,parameterObject,resultHandlerhandler,boundSql);
        return statementHandler;
    }

    public MyParameterHandler newParameterHandler(MyMappedStatement mappedStatement,Object paramaterObject,MyBoundSql boundSql){
        MyParameterHandler parameterHandler = mappedStatement.getLang().createParameterHandler(mappedStatement,paramaterObject,boundSql);
        return parameterHandler;
    }

    public MyResultSetHandler newResultSetHandler(MyExecutor executor,MyMappedStatement mappedStatement,MyParameterHandler parameterHandler,MyResultHandler resultHandler,MyBoundSql boundSql){
        MyResultSetHandler resultSetHandler = new MyDefaultResultSetHandler(executor,mappedStatement,parameterHandler,resultHandler,boundSql);
        return resultSetHandler;
    }

    protected static class StrictMap<V> extends HashMap<String,V>{
        private static final long serialVersionUID = -4950446264854982944L;
        private final String name;

        public StrictMap(int initialCapacity, float loadFactor, String name) {
            super(initialCapacity, loadFactor);
            this.name = name;
        }

        public StrictMap(int initialCapacity, String name) {
            super(initialCapacity);
            this.name = name;
        }

        public StrictMap(String name) {
            this.name = name;
        }

        public StrictMap(Map<? extends String, ? extends V> m, String name) {
            super(m);
            this.name = name;
        }
    }
}
