package com.qxz.learn.statement;

import com.qxz.learn.executor.MyExecutor;
import com.qxz.learn.mapping.MyBoundSql;
import com.qxz.learn.mapping.MyMappedStatement;
import com.qxz.learn.result.MyResultHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/9/27
 */
public class MyPreparedStatementHandler extends MyBaseStatementHandler {


    public MyPreparedStatementHandler(MyMappedStatement mappedStatement, Object parameters, MyBoundSql boundSql, MyResultHandler resultHandler, MyExecutor executor) {
        super(mappedStatement, parameters, boundSql, resultHandler, executor);
    }

    @Override
    protected Statement initStatement(Connection connection) throws SQLException {
        return null;
    }

    @Override
    public <E> List<E> query(Statement statement, MyResultHandler handler) throws SQLException {
        return null;
    }

    @Override
    public int update(Statement statement) throws SQLException {
        return 0;
    }

    @Override
    public void parameterize(Statement statement){
        parameterHandler.setParamentters((PreparedStatement) statement);
    }
}
