package com.qxz.learn.type;

import java.lang.reflect.Type;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class MyTypeHandlerRegistry {


    private final Map<MyJdbcType, MyTypeHandler<?>> JDBC_TYPE_HANDLER_MAP = new EnumMap<MyJdbcType, MyTypeHandler<?>>(MyJdbcType.class);
    private final Map<Type, Map<MyJdbcType, MyTypeHandler<?>>> TYPE_HANDLER_MAP = new HashMap<Type, Map<MyJdbcType, MyTypeHandler<?>>>();
    private final MyTypeHandler<Object> UNKNOWN_TYPE_HANDLER = new MyUnknownTypeHandler(this);
    private final Map<Class<?>, MyTypeHandler<?>> ALL_TYPE_HANDLERS_MAP = new HashMap<Class<?>, MyTypeHandler<?>>();


    public boolean hasTypeHandler(Class<?> javaType, MyJdbcType jdbcType) {
        return javaType != null && getTypeHandler((Type) javaType, jdbcType) != null;
    }

    public boolean hasTypeHandler(Class<?> javaType) {
        return hasTypeHandler(javaType, null);
    }
    public <T> MyTypeHandler<T> getTypeHandler(Type type) {
        return getTypeHandler(type,null);
    }

    public MyTypeHandler<?> getTypeHandler(MyJdbcType jdbcType) {
        return JDBC_TYPE_HANDLER_MAP.get(jdbcType);
    }


    public <T> MyTypeHandler<T> getTypeHandler(Type type, MyJdbcType jdbcType) {
        Map<MyJdbcType, MyTypeHandler<?>> jdbcHandlerMap = TYPE_HANDLER_MAP.get(type);
        MyTypeHandler<?> handler = null;
        if (jdbcHandlerMap != null) {
            handler = jdbcHandlerMap.get(jdbcType);
            if (handler == null) {
                handler = jdbcHandlerMap.get(null);
            }
            if (handler == null) {
                // #591
                handler = pickSoleHandler(jdbcHandlerMap);
            }
        }
       /* if (handler == null && type != null && type instanceof Class && Enum.class.isAssignableFrom((Class<?>) type)) {
            handler = new EnumTypeHandler((Class<?>) type);
        }*/
        // type drives generics here
        return (MyTypeHandler<T>) handler;
    }

    public MyTypeHandler<?> pickSoleHandler(Map<MyJdbcType, MyTypeHandler<?>> jdbcHandlerMap) {
        MyTypeHandler<?> soleHandler = null;
        for (MyTypeHandler<?> handler : jdbcHandlerMap.values()) {
            if (soleHandler == null) {
                soleHandler = handler;
            } else if (!handler.getClass().equals(soleHandler.getClass())) {
                return null;
            }
        }
        return soleHandler;
    }

}
