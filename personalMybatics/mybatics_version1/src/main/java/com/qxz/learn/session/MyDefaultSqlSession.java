package com.qxz.learn.session;

import com.qxz.learn.configuration.MyConfiguration;
import com.qxz.learn.executor.MyExecutor;
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

    @Override
    public int insert(String statement) {
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
          return null;
      }catch (Exception e){
          throw new RuntimeException("error in select list");
      }
    }

    @Override
    public <T> List<T> selectOne(String statement, Object parament) {
        return null;
    }

    @Override
    public int update(String statement) {
        return 0;
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
    public <T> T getMapper(Class clz) {
        return null;
    }

    @Override
    public MyConfiguration getConfiguration() {
        return null;
    }

    @Override
    public Connection getConnection() {
        return null;
    }
}
