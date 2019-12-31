package com.qxz.learn.statement;

import com.qxz.learn.mapping.MyBoundSql;
import com.qxz.learn.parameter.MyParameterHandler;

import java.sql.*;
import java.util.List;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/9/27
 */
public interface MyStatementHandler {

    PreparedStatement prepare(Connection connection,Integer timeout) throws SQLException;

    int update(PreparedStatement statement)
        throws SQLException;

    ResultSet query(PreparedStatement statement)
        throws SQLException;

    MyBoundSql getBoundSql();

    MyParameterHandler getParamenterHandler();

    void parameterize(Statement statement);
}
