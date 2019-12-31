package com.qxz.learn.executor;

import com.qxz.learn.configuration.MyConfiguration;
import com.qxz.learn.exception.MySqlException;
import com.qxz.learn.mapping.MyMappedStatement;
import com.qxz.learn.statement.MySimplerStatementHandler;
import com.qxz.learn.statement.MyStatementHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MySimpleExecutor implements MyExecutor {

    private MyConfiguration myConfiguration;
    private MyExecutor warpper;
    private static Connection connection;

    static {
       /* String driver = Configuration.getProperty(Constant.DB_DRIVER_CONF);
        String url = Configuration.getProperty(Constant.DB_URL_CONF);
        String username = Configuration.getProperty(Constant.DB_USERNAME_CONF);
        String password = Configuration.getProperty(Constant.db_PASSWORD);
        try
        {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("数据库连接成功");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }*/
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
        return null;
    }

    @Override
    public int update(MyMappedStatement mappedStatement, Object params)  {

        MyMappedStatement ms = myConfiguration.getMappedStatement(mappedStatement.getSqlId());
        MyStatementHandler handler = new MySimplerStatementHandler(ms);
        try {
            PreparedStatement preparedStatement = handler.prepare(connection,0);
            handler.update(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
