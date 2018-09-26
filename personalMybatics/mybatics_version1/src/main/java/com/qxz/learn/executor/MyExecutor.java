package com.qxz.learn.executor;

import com.qxz.learn.exception.MySqlException;
import com.qxz.learn.mapping.MyMappedStatement;

import java.util.List;
/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/9/25
 */
public interface MyExecutor {

    void close(boolean forceRollBack);

    void commit(boolean required) throws MySqlException;

    void rollback(boolean required) throws MySqlException;

    List doQuery(String boundSql) throws MySqlException;

    int update(MyMappedStatement mappedStatement,Object params) throws MySqlException;
}
