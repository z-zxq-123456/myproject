package com.qxz.learn.type;

import org.apache.ibatis.type.TypeException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/9/30
 */
public abstract class MyTypeReference<T>{

    private final Type rawType;

    protected MyTypeReference() {
        this.rawType = getSuperClassTypeParameter(getClass());
    }

    Type getSuperClassTypeParameter(Class<?> clazz){
        Type genericSuperClass = clazz.getGenericSuperclass();
        if (genericSuperClass instanceof Class){
            if (MyTypeReference.class != genericSuperClass){
                return getSuperClassTypeParameter(clazz.getSuperclass());
            }
            throw new TypeException("'" + getClass() + "' extends TypeReference but misses the type parameter. "
                    + "Remove the extension or add a type parameter to it.");

        }
        Type rawType = ((ParameterizedType)genericSuperClass).getActualTypeArguments()[0];
        if (rawType instanceof ParameterizedType) {
            rawType = ((ParameterizedType) rawType).getRawType();
        }
        return rawType;
    }

    public Type getRawType() {
        return rawType;
    }

    @Override
    public String toString() {
        return "MyTypeReference{" +
                "rawType=" + rawType +
                '}';
    }
}
