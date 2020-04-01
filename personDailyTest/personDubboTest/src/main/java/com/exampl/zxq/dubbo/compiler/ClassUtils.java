package com.exampl.zxq.dubbo.compiler;


public class ClassUtils {


    public static Class<?> forName(String[]packages,String className){
        try {
         return _forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Class<?> _forName(String className) throws ClassNotFoundException{
        switch(className){
            case "boolean":
                return boolean.class;
            case "byte":
                return byte.class;
            case "char":
                return char.class;
            case "short":
                return short.class;
            case "int":
                return int.class;
            case "long":
                return long.class;
            case "float":
                return float.class;
            case "double":
                return double.class;
            case "boolean[]":
                return boolean[].class;
            case "byte[]":
                return byte[].class;
            case "char[]":
                return char[].class;
            case "short[]":
                return short[].class;
            case "int[]":
                return int[].class;
            case "long[]":
                return long[].class;
            case "float[]":
                return float[].class;
            case "double[]":
                return double[].class;
        }

        try {
           return arrayForName(className);
        } catch (ClassNotFoundException e) {
            throw e;
        }
    }

    private static Class<?> arrayForName(String className) throws ClassNotFoundException {
        return Class.forName(className.endsWith("[]")
                ? "[L"+className.substring(0,className.length()-2) +";" : className, true,Thread.currentThread().getContextClassLoader());
    }


}
