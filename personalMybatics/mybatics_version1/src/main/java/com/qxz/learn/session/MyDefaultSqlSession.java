package com.qxz.learn.session;

import com.qxz.learn.configuration.MyConfiguration;
import com.qxz.learn.executor.MyExecutor;
import com.qxz.learn.executor.MySimpleExecutor;
import com.qxz.learn.mapping.MyMappedStatement;

import java.sql.Connection;
import java.util.List;

public class MyDefaultSqlSession implements MySqlSession {

    private MyConfiguration configuration;

    private MyExecutor executor;

    private boolean autoCommit;

    public MyDefaultSqlSession(MyConfiguration configuration, MyExecutor executor, boolean autoCommit) {
        this.configuration = configuration;
        this.executor = executor;
        this.autoCommit = autoCommit;
    }

    public MyDefaultSqlSession(MyConfiguration configuration) {
        this.configuration = configuration;
        this.executor = new MySimpleExecutor(configuration);
    }

    @Override
    public int insert(String statement, Object parameter) {
        return 0;
    }

    @Override
    public int delete(String statement) {
        return 0;
    }

    @Override
    public <T> List<T> selectList(String statement) {
        return selectList(statement,null);
    }

    @Override
    public <T> List<T> selectOne(String statement) {
        return selectOne(statement,null);
    }

    @Override
    public <T> List<T> selectList(String statement, Object parament) {
      try{
          MyMappedStatement mms = configuration.getMappedStatement(statement);
          return executor.doQuery(mms,parament);
      }catch (Exception e){
          throw new RuntimeException("error in select list");
      }
    }

    @Override
    public <T> List<T> selectOne(String statement, Object parament) {
        return null;
    }

    @Override
    public int update(String statement,Object parameter) {
        MyMappedStatement ms = this.configuration.getMappedStatement(statement);
       return this.executor.update(ms,parameter);
    }

    @Override
    public void commit() {

    }

    @Override
    public void rollback() {

    }

    @Override
    public void close() {

    }

    @Override
    public <T> T getMapper(Class<T> clz) {
        return configuration.getMapper(clz,this);
    }

    @Override
    public MyConfiguration getConfiguration() {
        return this.configuration;
    }

    @Override
    public Connection getConnection() {
        return null;
    }
}
