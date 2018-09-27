package com.qxz.learn.executor;

import com.qxz.learn.configuration.MyConfiguration;
import com.qxz.learn.mapping.MyBoundSql;
import com.qxz.learn.mapping.MyMappedStatement;
import com.qxz.learn.parameter.MyParameterHandler;
import com.qxz.learn.statement.MyResultHandler;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/9/27
 */
public class MyDefaultResultSetHandler implements MyResultSetHandler {

    private final MyExecutor executor;
    private final MyConfiguration configuration;
    private final MyMappedStatement mappedStatement;
    private final MyParameterHandler parameterHandler;
    private final MyResultHandler resultHandler;
    private final MyBoundSql boundSql;

    public MyDefaultResultSetHandler(MyExecutor executor, MyMappedStatement mappedStatement, MyParameterHandler parameterHandler, MyResultHandler resultHandler, MyBoundSql boundSql) {
        this.executor = executor;
        this.configuration = mappedStatement.getConfiguration();
        this.mappedStatement = mappedStatement;
        this.parameterHandler = parameterHandler;
        this.resultHandler = resultHandler;
        this.boundSql = boundSql;
    }

    @Override
    public <E> List<E> handleResultSets(Statement stmt) throws SQLException {
        return null;
    }
}
