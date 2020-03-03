package com.qxz.learn.executor;

import com.qxz.learn.mapping.*;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/9/27
 */
public class MyDefaultResultSetHandler implements MyResultSetHandler {


    private final MyMappedStatement mappedStatement;


    public MyDefaultResultSetHandler(MyMappedStatement mappedStatement) {
        this.mappedStatement = mappedStatement;
    }

    @Override
    public <E> List<E> handleResultSets(ResultSet resultSet) throws SQLException {
        List<E> result = new ArrayList<>();
        if (null == resultSet)
        {
            return null;
        }

        while (resultSet.next()){
            try {
                Class<?> entity = Class.forName(mappedStatement.getResultType());
                E obj = (E)entity.newInstance();
                Field[] declaredFields = obj.getClass().getDeclaredFields();
                for (Field field : declaredFields)
                {
                    // 对成员变量赋值
                    field.setAccessible(true);
                    Class<?> fieldType = field.getType();
                    // 目前只实现了string和int转换
                    if (String.class.equals(fieldType))
                    {
                        field.set(obj, resultSet.getString(field.getName()));
                    }
                    else if (int.class.equals(fieldType) || Integer.class.equals(fieldType))
                    {
                        field.set(obj, resultSet.getInt(field.getName()));
                    }
                    else
                    {
                        // 其他类型自己转换，这里就直接设置了
                        field.set(obj, resultSet.getObject(field.getName()));
                    }
                }
                result.add(obj);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
