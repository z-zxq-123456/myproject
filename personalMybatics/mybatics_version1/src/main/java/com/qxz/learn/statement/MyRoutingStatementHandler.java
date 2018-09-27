package com.qxz.learn.statement;

import com.qxz.learn.exception.MySqlException;
import com.qxz.learn.executor.MyExecutor;
import com.qxz.learn.mapping.MyBoundSql;
import com.qxz.learn.mapping.MyMappedStatement;
import com.qxz.learn.parameter.MyParameterHandler;
import com.qxz.learn.session.MySqlSession;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/9/27
 */
public class MyRoutingStatementHandler implements MyStatementHandler {


    private MyStatementHandler delegate;

    public MyRoutingStatementHandler(MyExecutor executor,
                                     MyMappedStatement mappedStatement,
                                     Object params,
                                     MyResultHandler resultHandler,
                                     MyBoundSql boundSql)throws MySqlException
    {
        switch (mappedStatement.getStatementType()){
            case PREPAERD:
                delegate = new MyPreparedStatementHandler(mappedStatement,params,boundSql,resultHandler,executor);
                break;
            case STATEMENT:
                delegate = new MySimplerStatementHandler(mappedStatement,params,boundSql,resultHandler,executor);
                break;
            default:
                throw new MySqlException("invalidate statement type " + mappedStatement.getStatementType());
        }
    }

    @Override
    public Statement prepare(Connection connection, Integer timeout) throws SQLException {
        return delegate.prepare(connection,timeout);
    }

    @Override
    public int update(Statement statement) throws SQLException {
        return delegate.update(statement);
    }

    @Override
    public <E> List<E> query(Statement statement, MyResultHandler handler) throws SQLException {
        return delegate.query(statement,handler);
    }

    @Override
    public MyBoundSql getBoundSql() {
        return delegate.getBoundSql();
    }

    @Override
    public MyParameterHandler getParamenterHandler() {
        return delegate.getParamenterHandler();
    }

    @Override
    public void parameterize(Statement statement) {
        delegate.parameterize(statement);
    }
}
