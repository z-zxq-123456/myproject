package com.qxz.learn.configuration;

import com.qxz.learn.binding.MyMappedRegistry;
import com.qxz.learn.mapping.MyMappedStatement;
import com.qxz.learn.session.MySqlSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/9/26
 */
public class MyConfiguration {

    private MyMappedRegistry mappedRegistry = new MyMappedRegistry(this);
    private Map<String,MyMappedStatement>  mappedStatement = new HashMap<>();
    public static Properties variables = new Properties();

    public Properties getVariables() {
        return variables;
    }

    public void setVariables(Properties variables) {
        this.variables = variables;
    }

    public <T> void addMapper(Class<T> t) {
       this.mappedRegistry.addMapper(t);
    }

    public <T> T getMapper(Class<T> t, MySqlSession sqlSession) {
        return this.mappedRegistry.getMapper(t,sqlSession);
    }

    public MyMappedRegistry getMappedRegistry() {
        return mappedRegistry;
    }

    public void setMappedRegistry(MyMappedRegistry mappedRegistry) {
        this.mappedRegistry = mappedRegistry;
    }

    public MyMappedStatement getMappedStatement(String id) {
        return mappedStatement.get(id);
    }


    public void addMappedStatement(MyMappedStatement mappedStatement) {
        this.mappedStatement.put(mappedStatement.getSqlId(),mappedStatement);
    }

    public static String getProperty(String key){
        return getProperty(key,"");
    }

    public static String getProperty(String key,String defaultValue){
        return variables.containsKey(key) ? variables.getProperty(key) : defaultValue;
    }
}
