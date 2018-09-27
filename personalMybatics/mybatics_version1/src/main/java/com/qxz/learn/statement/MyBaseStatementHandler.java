package com.qxz.learn.statement;

import com.qxz.learn.configuration.MyConfiguration;
import com.qxz.learn.executor.MyErrorContext;
import com.qxz.learn.executor.MyExecutor;
import com.qxz.learn.executor.MyResultSetHandler;
import com.qxz.learn.mapping.MyBoundSql;
import com.qxz.learn.mapping.MyMappedStatement;
import com.qxz.learn.parameter.MyParameterHandler;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/9/27
 */
public abstract class MyBaseStatementHandler implements MyStatementHandler {

     MyConfiguration configuration;
     MyMappedStatement mappedStatement;
     MyBoundSql boundSql;
     MyParameterHandler parameterHandler;
     MyResultSetHandler resultSetHandler;
     MyExecutor executor;

    public MyBaseStatementHandler(MyMappedStatement mappedStatement,Object parameters, MyBoundSql boundSql, MyResultHandler resultHandler, MyExecutor executor) {
        this.configuration = mappedStatement.getConfiguration();
        this.mappedStatement = mappedStatement;
        this.boundSql = boundSql;
        this.executor = executor;
        this.parameterHandler = configuration.newParameterHandler(mappedStatement,parameters,boundSql);
        this.resultSetHandler = configuration.newResultSetHandler(executor,mappedStatement,parameterHandler,resultHandler,boundSql);

    }

    @Override
    public Statement prepare(Connection connection, Integer transactionTimeout) throws SQLException {
        MyErrorContext.instance().sql(boundSql.getSql());
        Statement statement = null;
        try {
            statement = initStatement(connection);
            setStatementTimeOut(statement,transactionTimeout);
        }catch (SQLException e){
            closeStatement(statement);
            throw e;
        }catch (Exception e){
            throw new RuntimeException("error in prepare " + e.getMessage());
        }
        return statement;
    }

    protected abstract Statement initStatement(Connection connection) throws SQLException;

    private void setStatementTimeOut(Statement st,Integer transactionTimeout)throws SQLException{
        Integer qryTimeOut = null;
        if (mappedStatement.getTimeOut() != null){
            qryTimeOut = mappedStatement.getTimeOut();
        }else if (configuration.getDefaultStatementTimeout()!=null){
            qryTimeOut = configuration.getDefaultStatementTimeout();
        }
        if (qryTimeOut != null){
            st.setQueryTimeout(qryTimeOut);
        }
        applyTransactionTimeout(st,qryTimeOut,transactionTimeout);
    }

    private void applyTransactionTimeout(Statement st,Integer queryTimeOut,Integer transactionTimeOut)throws SQLException{
        if (transactionTimeOut==null){
            return;
        }
        Integer timeToLiveQuery = null;
        if (queryTimeOut == null || queryTimeOut == 0){
            timeToLiveQuery = transactionTimeOut;
        }else if(transactionTimeOut < queryTimeOut){
            timeToLiveQuery = transactionTimeOut;
        }
        if (timeToLiveQuery != null){
            st.setQueryTimeout(timeToLiveQuery);
        }
    }


    @Override
    public MyBoundSql getBoundSql() {
        return boundSql;
    }

    private void closeStatement(Statement statement){
        try {
            if (statement != null){
                statement.close();
            }
        }catch (SQLException e){

        }
    }

    @Override
    public MyParameterHandler getParamenterHandler() {
        return parameterHandler;
    }
}
