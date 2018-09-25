/**
 * <p>Title: ObjectUtils.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 *
 * @author Tim
 * @update 2014年10月30日 下午3:55:34
 * @version V1.0
 */
package com.dcits.galaxy.base.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dcits.galaxy.base.GalaxyConstants;
import com.dcits.galaxy.base.bean.ArgumentBean;
import com.dcits.galaxy.base.exception.BusinessException;
import com.dcits.galaxy.base.exception.GalaxyException;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Tim
 * @version V1.0
 * @description
 * @update 2014年10月30日 下午3:55:34
 */
public class ObjectUtils {

    /**
     * Executes a method of a bean instance.
     *
     * @param instance   the bean instance
     * @param methodName the method to be executed
     * @param args       input parameters for the method
     * @return return value of the method
     */
    @SuppressWarnings("unchecked")
    public static <T> T executeMethod(Object instance, String methodName,
                                      Object[] args) {
        if (instance == null) {
            throw new IllegalArgumentException("Input bean instance is null.");
        }

        T t = null;
        Class<?>[] c;

        try {
            if (null == args)
                c = null;
            else {
                c = new Class<?>[args.length];
                for (int i = 0; i < args.length; i++) {
                    c[i] = args[i].getClass();
                }
            }
            Method m = getMethod(instance, methodName, c);
            t = (T) m.invoke(instance, args);
        } catch (Exception ex) {
            throw new GalaxyException(instance.getClass().getName() + "."
                    + methodName, ex);
        }
        return t;
    }

    /**
     * 获取服务入参
     *
     * @param argsl
     * @return
     * @throws ClassNotFoundException
     */
    public static Object[] getArguments(List<ArgumentBean> argsl)
            throws ClassNotFoundException {
        Object[] args = null;
        if (null == argsl)
            return args;
        int i = 0;
        for (ArgumentBean argbean : argsl) {
            if (null == args) {
                args = new Object[argsl.size()];
            }
            Class<?> c = getBaseDataType(argbean.getArgType());
            if (null == c)
                c = Class.forName(argbean.getArgType());

            Object o = argbean.getArgValue();
            try {
                // 取得具体字符串数据
                if (c == String.class) {
                    // nothing 不做任何处理
                } else if (c == Integer.class) {
                    o = Integer.valueOf((String) o);
                } else if (c == int.class) {
                    o = Integer.parseInt((String) o);
                } else if (c == double.class) {
                    o = Double.parseDouble((String) o);
                } else {
                    // throw 转换参数异常
                    throw new BusinessException(
                            GalaxyConstants.CODE_FAILED,
                            "ArgValue["
                                    + argbean.getArgValue()
                                    + "] ArgType["
                                    + argbean.getArgType()
                                    + "]转换参数异常，入參不是String\\Integer\\int\\Double\\double！");
                }
                args[i] = o;
            } finally {
                i++;
            }
        }
        return args;
    }

    /**
     * 获取服务入参
     *
     * @param argsl
     * @return
     * @throws ClassNotFoundException
     */
    public static Object[] getArguments(JSONObject in, List<ArgumentBean> argsl)
            throws ClassNotFoundException {
        // modify for sonar
        if (null == in)
            return null;
        Object[] args = null;
        int i = 0;
        for (ArgumentBean argbean : argsl) {
            if (null == args) {
                args = new Object[argsl.size()];
            }

            Class<?> c = getBaseDataType(argbean.getArgType());
            if (null == c)
                c = Class.forName(argbean.getArgType());
            try {
                Object o = null;
                if ("null".equals(argbean.getArgValue())) {// 传入参数获为null对象。
                    args[i] = o;
                    continue;
                } else if ("*".equals(argbean.getArgValue())) {// 传入参数获取原始请求报文。
                    // 原始请求保文装载到in的JsonObeject对象中。
                    if (c == String.class) {
                        o = in.toJSONString();
                    } else if (c == JSONObject.class) {
                        o = in;
                    } else {
                        // throw 转换参数异常
                        throw new BusinessException(
                                GalaxyConstants.CODE_FAILED, "ArgValue["
                                + argbean.getArgValue() + "] ArgType["
                                + argbean.getArgType()
                                + "]转换参数异常，入參不是String\\JSONObject！");
                    }
                } else {// 根据jsonPath获取入参数据
                    o = JsonPath.read(in, argbean.getArgValue());
                    // 获取jsonPath对象结果为String
                    if (o instanceof JSONObject) {
                        if (c == JSONObject.class) {
                            // nothing
                        } else if (c == String.class) {
                            o = ((JSONObject) o).toJSONString();
                        } else {
                            o = JSON.parseObject(
                                    ((JSONObject) o).toJSONString(), c);
                        }
                    } else if (o instanceof JSONArray) {
                        if (c == JSONArray.class) {
                            // nothing
                        } else if (c == String.class) {
                            o = ((JSONArray) o).toJSONString();
                        } else {
                            o = JSON.parseObject(
                                    ((JSONArray) o).toJSONString(), c);
                        }
                    } else if (o instanceof String) {
                        // 取得具体字符串数据
                        if (c == String.class) {
                            // nothing 不做任何处理
                        } else if (c == Integer.class) {
                            o = Integer.valueOf((String) o);
                        } else if (c == int.class) {
                            o = Integer.parseInt((String) o);
                        } else if (c == double.class) {
                            o = Double.parseDouble((String) o);
                        } else {
                            // throw 转换参数异常
                            throw new BusinessException(
                                    GalaxyConstants.CODE_FAILED,
                                    "ArgValue["
                                            + argbean.getArgValue()
                                            + "] ArgType["
                                            + argbean.getArgType()
                                            + "]转换参数异常，入參不是String\\Integer\\int\\Double\\double！");
                        }
                    } else if (o instanceof List) {
                        if (c != List.class) {
                            // throw 转换参数异常
                            throw new BusinessException(
                                    GalaxyConstants.CODE_FAILED, "ArgValue["
                                    + argbean.getArgValue()
                                    + "] ArgType["
                                    + argbean.getArgType()
                                    + "]转换参数异常，入參不是List！");
                        }
                    }
                }
                if (null == o)
                    args[i] = argbean.getArgValue();
                else
                    args[i] = o;
            } catch (PathNotFoundException e) {
                args[i] = argbean.getArgValue();
            } finally {
                i++;
            }
        }
        return args;
    }

    /**
     * 获取服务执行方法
     *
     * @param obj
     * @param methodStr
     * @param argsl
     * @return
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public static Method getMethod(Object obj, String methodStr,
                                   List<ArgumentBean> argsl) throws ClassNotFoundException,
            NoSuchMethodException, SecurityException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        Class<?>[] parameterTypes = null;
        if (null == argsl)
            return obj.getClass().getMethod(methodStr, parameterTypes);

        int i = 0;
        for (ArgumentBean argbean : argsl) {
            if (null == parameterTypes) {
                parameterTypes = new Class[argsl.size()];
            }
            Class<?> c = getBaseDataType(argbean.getArgType());
            if (null == c)
                c = Class.forName(argbean.getArgType());
            parameterTypes[i++] = c;
        }
        return obj.getClass().getMethod(methodStr, parameterTypes);
    }

    /**
     * 获取服务执行方法
     *
     * @param obj
     * @param methodStr
     * @param c
     * @return
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public static Method getMethod(Object obj, String methodStr, Class<?>... c)
            throws ClassNotFoundException, NoSuchMethodException,
            SecurityException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        Method method = obj.getClass().getMethod(methodStr, c);
        return method;
    }

    /**
     * @param filter
     * @param field
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @description
     * @version 1.0
     * @author Tim
     * @update 2015年1月16日 下午5:27:36
     */
    public static Object getField(Object filter, String field)
            throws NoSuchMethodException, InvocationTargetException,
            IllegalAccessException {
        String firstLetter = field.substring(0, 1).toUpperCase();
        String getMethodName = "get" + firstLetter + field.substring(1);
        Method getMethod = filter.getClass().getMethod(getMethodName);
        Object returnValue = getMethod.invoke(filter);
        return returnValue;
    }

    public static Class<?> getBaseDataType(String type) {
        switch (type) {
            case "byte":
                return byte.class;
            case "Byte":
                return Byte.class;
            case "short":
                return short.class;
            case "Short":
                return Short.class;
            case "int":
                return int.class;
            case "Integer":
                return Integer.class;
            case "long":
                return long.class;
            case "Long":
                return Long.class;
            case "float":
                return float.class;
            case "Float":
                return Float.class;
            case "double":
                return double.class;
            case "Double":
                return Double.class;
            case "BigDecimal":
                return BigDecimal.class;
            case "char":
                return char.class;
            case "boolean":
                return boolean.class;
            case "Boolean":
                return Boolean.class;
            case "String":
                return String.class;
        }
        return null;
    }
}
