package com.qxz.learn.executor;

import com.qxz.learn.configuration.MyConfiguration;
import com.qxz.learn.exception.MySqlException;
import com.qxz.learn.mapping.MyMappedStatement;

import java.util.List;

public class MySimpleExecutor implements MyExecutor {

    private MyConfiguration myConfiguration;

    public MySimpleExecutor(MyConfiguration myConfiguration) {
        this.myConfiguration = myConfiguration;
    }

    @Override
    public void close(boolean forceRollBack) {

    }

    @Override
    public void commit(boolean required) throws MySqlException {

    }

    @Override
    public void rollback(boolean required) throws MySqlException {

    }

    @Override
    public List doQuery(String boundSql) throws MySqlException {
        return null;
    }

    @Override
    public int update(MyMappedStatement mappedStatement, Object params) throws MySqlException {
        return 0;
    }
}
