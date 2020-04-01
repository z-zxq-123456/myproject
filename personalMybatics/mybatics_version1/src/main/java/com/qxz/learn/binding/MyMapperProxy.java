package com.qxz.learn.binding;

import com.qxz.learn.mapping.MyMappedStatement;
import com.qxz.learn.session.MySqlSession;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Collection;

public class MyMapperProxy<T> implements InvocationHandler, Serializable {

    private final MySqlSession session;
    private final Class<T> mapperInterface;

    public MyMapperProxy(MySqlSession session, Class<T> mapperInterface) {
        this.session = session;
        this.mapperInterface = mapperInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

         if (Object.class.equals(method.getDeclaringClass())){
             return method.invoke(this,args);
         }
         return this.execute(method,args);
    }


    private Object execute(Method method,Object[] args){

        String sqlId = mapperInterface.getName()+"."+method.getName();
        MyMappedStatement mappedStatement = this.session.getConfiguration().getMappedStatement(sqlId);
        Object result = null;
        String command = mappedStatement.getSqlCommandType();
        if ("insert".equals(command)){

        }else if ("select".equals(command)){
            Class resType = method.getReturnType();
            if (Collection.class.isAssignableFrom(resType)){
                result = session.selectList(sqlId,args);
            }else {
                result = session.selectOne(sqlId,args);
            }
        }else if("update".equals(command)){
                session.update(sqlId,args);
        }else if ("delete".equals(command)){


        }

        return result;
    }

}
