package com.qxz.learn.session;

import com.qxz.learn.configuration.MyConfiguration;
import java.io.IOException;
import java.io.InputStream;


/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/10/8
 */
public class MySqlSessionFactoryBuilder {

    public MySqlSessionFactory build(String filename){
        InputStream inputStream = MySqlSessionFactoryBuilder.class.getClassLoader().getResourceAsStream(filename);
        return build(inputStream);
    }

    public MySqlSessionFactory build(InputStream inputStream){
        try {
            MyConfiguration.variables.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new MyDefaultSqlSessionFactory(new MyConfiguration());
    }
}
