package com.qxz.learn.statement;

import com.qxz.learn.executor.MyExecutor;
import com.qxz.learn.mapping.MyBoundSql;
import com.qxz.learn.mapping.MyMappedStatement;
import com.qxz.learn.result.MyResultHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/9/27
 */
public class MySimplerStatementHandler extends MyBaseStatementHandler {

    public MySimplerStatementHandler(MyMappedStatement mappedStatement, Object parameters, MyBoundSql boundSql, MyResultHandler resultHandler, MyExecutor executor) {
        super(mappedStatement, parameters, boundSql, resultHandler, executor);
    }

    @Override
    public int update(Statement statement) throws SQLException {
        return 0;
    }

    @Override
    public <E> List<E> query(Statement statement, MyResultHandler handler) throws SQLException {
        String sql = boundSql.getSql();
        statement.execute(sql);
        return resultSetHandler.handleResultSets(statement);
    }

    @Override
    public void parameterize(Statement statement) {

    }

    @Override
    protected Statement initStatement(Connection connection) throws SQLException {
        return connection.createStatement();
    }
}
