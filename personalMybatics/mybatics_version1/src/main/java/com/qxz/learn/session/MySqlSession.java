package com.qxz.learn.session;

import com.qxz.learn.configuration.MyConfiguration;

import java.sql.Connection;
import java.util.List;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/9/25
 */
public interface MySqlSession {

    int insert(String statement);

    int delete(String statement);

    <T>List<T> selectList(String statement);

    <T>List<T> selectOne(String statement);

    <T>List<T> selectList(String statement,Object parament);

    <T>List<T> selectOne(String statement,Object parament);

    int update(String statement);

    void commit();

    void rollback();

    void close();

    <T>T getMapper(Class clz);

    MyConfiguration getConfiguration();

    Connection getConnection();
}
