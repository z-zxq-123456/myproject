package com.qxz.learn.type;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.util.*;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/10/8
 */
public class MyTypeAliasRegistry {

    private final Map<String,Class<?>> TYPE_ALIAS = new HashMap<>();


    public MyTypeAliasRegistry() {

        registerAlias("string", String.class);

        registerAlias("byte", Byte.class);
        registerAlias("long", Long.class);
        registerAlias("short", Short.class);
        registerAlias("int", Integer.class);
        registerAlias("integer", Integer.class);
        registerAlias("double", Double.class);
        registerAlias("float", Float.class);
        registerAlias("boolean", Boolean.class);

        registerAlias("byte[]", Byte[].class);
        registerAlias("long[]", Long[].class);
        registerAlias("short[]", Short[].class);
        registerAlias("int[]", Integer[].class);
        registerAlias("integer[]", Integer[].class);
        registerAlias("double[]", Double[].class);
        registerAlias("float[]", Float[].class);
        registerAlias("boolean[]", Boolean[].class);

        registerAlias("_byte", byte.class);
        registerAlias("_long", long.class);
        registerAlias("_short", short.class);
        registerAlias("_int", int.class);
        registerAlias("_integer", int.class);
        registerAlias("_double", double.class);
        registerAlias("_float", float.class);
        registerAlias("_boolean", boolean.class);

        registerAlias("_byte[]", byte[].class);
        registerAlias("_long[]", long[].class);
        registerAlias("_short[]", short[].class);
        registerAlias("_int[]", int[].class);
        registerAlias("_integer[]", int[].class);
        registerAlias("_double[]", double[].class);
        registerAlias("_float[]", float[].class);
        registerAlias("_boolean[]", boolean[].class);

        registerAlias("date", Date.class);
        registerAlias("decimal", BigDecimal.class);
        registerAlias("bigdecimal", BigDecimal.class);
        registerAlias("biginteger", BigInteger.class);
        registerAlias("object", Object.class);

        registerAlias("date[]", Date[].class);
        registerAlias("decimal[]", BigDecimal[].class);
        registerAlias("bigdecimal[]", BigDecimal[].class);
        registerAlias("biginteger[]", BigInteger[].class);
        registerAlias("object[]", Object[].class);

        registerAlias("map", Map.class);
        registerAlias("hashmap", HashMap.class);
        registerAlias("list", List.class);
        registerAlias("arraylist", ArrayList.class);
        registerAlias("collection", Collection.class);
        registerAlias("iterator", Iterator.class);
        registerAlias("ResultSet", ResultSet.class);
    }

    public void registerAliases(String packageName){
        registerAliases(packageName,Object.class);
    }

    private void registerAliases(String packageName,Class<?> superType){

    }

    public void registerAlias(Class<?> type){
       String alias = type.getSimpleName();
       MyAlias aliasAnnotation = type.getAnnotation(MyAlias.class);
       if (aliasAnnotation != null){
           alias = aliasAnnotation.value();
       }
       registerAlias(alias,type);
    }

    public void registerAlias(String alias,Class<?> value){
        String key = alias.toLowerCase(Locale.ENGLISH);

        if(TYPE_ALIAS.containsKey(key) && TYPE_ALIAS.get(key) != null && !TYPE_ALIAS.get(key).equals(value)){
            throw new RuntimeException("type is already registered!");
        }
        TYPE_ALIAS.put(key,value);
    }

   /* public <T> Class<T> resolveAlias(String string) {
        try {
            if (string == null) {
                return null;
            }
            String key = string.toLowerCase(Locale.ENGLISH);
            Class<T> value;
            if (TYPE_ALIAS.containsKey(key)) {
                value = (Class<T>) TYPE_ALIAS.get(key);
            } else {
                value = (Class<T>) ResourcesUtils.classForName(string);
            }
            return value;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Could not resolve type alias '" + string + "'.  Cause: " + e, e);
        }
    }*/

}
