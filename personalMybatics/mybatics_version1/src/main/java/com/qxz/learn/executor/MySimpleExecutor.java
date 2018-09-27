package com.qxz.learn.executor;

import com.qxz.learn.configuration.MyConfiguration;
import com.qxz.learn.exception.MySqlException;
import com.qxz.learn.mapping.MyBoundSql;
import com.qxz.learn.mapping.MyMappedStatement;
import com.qxz.learn.statement.MyStatementHandler;
import com.qxz.learn.tranaction.MyTransaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class MySimpleExecutor implements MyExecutor {

    private MyConfiguration myConfiguration;
    private MyExecutor warpper;

    private MyTransaction transaction;
    private boolean closed;

    public MySimpleExecutor(MyConfiguration myConfiguration) {
        this.myConfiguration = myConfiguration;
    }

    public MySimpleExecutor(MyConfiguration myConfiguration, MyTransaction transaction) {
        this.myConfiguration = myConfiguration;
        this.transaction = transaction;
        this.warpper=this;
        this.closed=false;
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
    public <E> List<E> doQuery(MyMappedStatement ms, Object parameter)
            throws SQLException, MySqlException {
        MyBoundSql boundSql = ms.getBoundSql(parameter);
        if (closed){
            throw new MySqlException("Executor was closed!");
        }
        return qryFromDataBase(ms,parameter,boundSql) ;
    }


    private <E>List<E> qryFromDataBase(MyMappedStatement ms,Object parameterObject,MyBoundSql boundSql)
            throws SQLException,MySqlException{
        Statement stmt = null;
        try {
            MyConfiguration configuration = ms.getConfiguration();
            MyStatementHandler handler = configuration.newStatementHandler(this,ms,parameterObject,null,boundSql);
            stmt = prepareStatement(handler);
            return handler.query(stmt,null);
        } finally {
            closeStatement(stmt);
        }
    }

    private Statement prepareStatement(MyStatementHandler statementHandler)
            throws MySqlException,SQLException{
        Statement stmt;
        Connection conn = transaction.getConnection();
        stmt = statementHandler.prepare(conn,transaction.getTimeout());
        statementHandler.parameterize(stmt);
        return stmt;
    }

    private void closeStatement(Statement statement){
        if (statement!=null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int update(MyMappedStatement mappedStatement, Object params) throws MySqlException {
        return 0;
    }
}
