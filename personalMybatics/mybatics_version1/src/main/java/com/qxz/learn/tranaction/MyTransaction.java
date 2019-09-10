package com.qxz.learn.tranaction;

import com.qxz.learn.exception.MySqlException;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/9/27
 */
public interface MyTransaction {

    Connection getConnection()throws SQLException,MySqlException ;

    Integer getTimeout() throws MySqlException;

    void close() throws MySqlException;

    void commit() throws MySqlException;

    void rollback() throws MySqlException;

}
