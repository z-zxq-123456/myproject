package com.qxz.learn.executor;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/9/27
 */
public interface MyResultSetHandler {

    <E> List<E> handleResultSets(Statement stmt) throws SQLException;

}
