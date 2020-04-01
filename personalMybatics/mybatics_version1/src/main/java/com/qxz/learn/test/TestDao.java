package com.qxz.learn.test;

import com.qxz.learn.dao.User;
import com.qxz.learn.dao.UserMapper;
import com.qxz.learn.session.MySqlSession;
import com.qxz.learn.session.MySqlSessionFactory;
import com.qxz.learn.session.MySqlSessionFactoryBuilder;

import java.util.List;

/**
 * @description mybatics  手写框架测试
 * @author: zhouxqh
 * @create: 2020-03-01 20:47
 **/
public class TestDao {

    public static void main(String[] args) {
        MySqlSessionFactory sessionFactory =
                new MySqlSessionFactoryBuilder().build("conf.properties");
        MySqlSession session = sessionFactory.openSqlSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        List<User> user =  userMapper.getUser("zxqfor10");
    }


}
