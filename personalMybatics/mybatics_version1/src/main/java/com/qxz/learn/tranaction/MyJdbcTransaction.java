package com.qxz.learn.tranaction;

import com.qxz.learn.exception.MySqlException;
import org.apache.ibatis.session.TransactionIsolationLevel;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/9/27
 */
public class MyJdbcTransaction implements MyTransaction {

    Connection connection;
    DataSource dataSource;
    boolean autoCommit;
    TransactionIsolationLevel level;

    public MyJdbcTransaction(DataSource dataSource, boolean autoCommit, TransactionIsolationLevel level) {
        this.dataSource = dataSource;
        this.autoCommit = autoCommit;
        this.level = level;
    }

    public MyJdbcTransaction(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Connection getConnection()throws SQLException,MySqlException {
        if (connection == null){
            openConnection();
        }
        return connection;
    }

    private void openConnection()throws SQLException,MySqlException{
        connection = dataSource.getConnection();
        if (level != null){
            connection.setTransactionIsolation(level.getLevel());
        }
        setDesiredAutoCommit(autoCommit);
    }

    private void setDesiredAutoCommit(boolean autoCommit)throws MySqlException{
        try {
            if (connection.getAutoCommit()!=autoCommit){
                connection.setAutoCommit(autoCommit);
            }
        } catch (SQLException e) {
           throw new MySqlException("error in set autoCommit"+e.getMessage());
        }
    }

    @Override
    public Integer getTimeout() throws MySqlException {
        return null;
    }

    @Override
    public void close() throws MySqlException {

    }

    @Override
    public void commit() throws MySqlException {

    }

    @Override
    public void rollback() throws MySqlException {

    }
}
