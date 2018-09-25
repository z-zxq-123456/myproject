package com.dcits.orion.api;

/**
 * Created by lixbb on 2016/1/14.
 */
public interface IParseString<T> {
    T parse(Class<?> class1,String string);
    String getString(T t);
    int getLength(T t);
    String getType(Class<?> class1);
}
