package com.qxz.learn.executor;

import com.qxz.learn.configuration.MyConfiguration;
import com.qxz.learn.exception.MySqlException;
import com.qxz.learn.executor.parameter.DefaultParameterHandler;
import com.qxz.learn.executor.parameter.ParameterHandler;
import com.qxz.learn.mapping.MyMappedStatement;
import com.qxz.learn.statement.MySimplerStatementHandler;
import com.qxz.learn.statement.MyStatementHandler;

import java.sql.*;
import java.util.List;

public class MySimpleExecutor implements MyExecutor {

    private MyConfiguration myConfiguration;
    private MyExecutor warpper;
    private static Connection connection;

    public MySimpleExecutor(MyConfiguration myConfiguration) {
        this.myConfiguration = myConfiguration;
    }

    static {

        String driver = MyConfiguration.getProperty("driver");
        String url = MyConfiguration.getProperty("url");
        String username = MyConfiguration.getProperty("username");
        String password = MyConfiguration.getProperty("password");
        try
        {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("数据库连接成功");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
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

        MyMappedStatement mappedStatement = myConfiguration.getMappedStatement(ms.getSqlId());
        MyStatementHandler statementHandler = new MySimplerStatementHandler(mappedStatement);
        PreparedStatement preparedStatement = statementHandler.prepare(connection,0);

        ParameterHandler parameterHandler = new DefaultParameterHandler(parameter);
        parameterHandler.setParameters(preparedStatement);
        ResultSet resultSet = statementHandler.query(preparedStatement);

        MyResultSetHandler resultSetHandler = new MyDefaultResultSetHandler(mappedStatement);
        return resultSetHandler.handleResultSets(resultSet);
    }

    @Override
    public int update(MyMappedStatement mappedStatement, Object params)  {

        MyMappedStatement ms = myConfiguration.getMappedStatement(mappedStatement.getSqlId());
        MyStatementHandler handler = new MySimplerStatementHandler(ms);
        try {
            PreparedStatement preparedStatement = handler.prepare(connection,0);
            ParameterHandler parameterHandler = new DefaultParameterHandler(params);
            parameterHandler.setParameters(preparedStatement);

            handler.update(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
