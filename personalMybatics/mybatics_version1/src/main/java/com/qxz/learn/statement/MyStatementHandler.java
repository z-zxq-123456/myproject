package com.qxz.learn.statement;

import com.qxz.learn.mapping.MyBoundSql;
import com.qxz.learn.parameter.MyParameterHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/9/27
 */
public interface MyStatementHandler {

    Statement prepare(Connection connection,Integer timeout)
        throws SQLException;

    int update(Statement statement)
        throws SQLException;

    <E>List<E> query(Statement statement,MyResultHandler handler)
        throws SQLException;

    MyBoundSql getBoundSql();

    MyParameterHandler getParamenterHandler();

    void parameterize(Statement statement);
}
