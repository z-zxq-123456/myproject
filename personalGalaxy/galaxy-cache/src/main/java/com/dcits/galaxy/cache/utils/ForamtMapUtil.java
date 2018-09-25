package com.dcits.galaxy.cache.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.dcits.galaxy.base.util.JsonUtils;
import com.dcits.galaxy.core.serializer.SerializationUtils;

/**
 * Map转换工具 K/V byte格式到正常格式 互转
 *
 * @author tangxlf
 * @date 2015-1-8
 */
public class ForamtMapUtil {
    /**
     * 正常格式 转换成 byte格式
     *
     * @param Map<String,Object> comMap
     * @return Map<byte[],byte[]>
     */
    public static Map<byte[], byte[]> MapToByte(Map<String, Object> comMap) {
        Map<byte[], byte[]> byteMap = new HashMap<byte[], byte[]>();
        for (Map.Entry<String, Object> entry : comMap.entrySet()) {
            byteMap.put(SerializationUtils.serialize(entry.getKey()), SerializationUtils.serializeObj(entry.getValue()));
        }
        return byteMap;
    }

    /**
     * byte格式 转换成 正常格式
     *
     * @param Map<byte[],byte[]> byteMap
     * @return Map<String,Object>
     */
    public static Map<String, Object> ByteToMap(Map<byte[], byte[]> byteMap) {
        Map<String, Object> comMap = new HashMap<String, Object>();
        for (Map.Entry<byte[], byte[]> entry : byteMap.entrySet()) {
            comMap.put(SerializationUtils.deserialize(entry.getKey()), SerializationUtils.deserializeObj(entry.getValue()));
        }
        return comMap;
    }

    /**
     * key 转换成 驼峰格式
     *
     * @param Map<String,Object> comMap
     * @return Map<String,Object>
     */
    public static Map<String, Object> MapToHump(Map<String, Object> comMap) {
        Map<String, Object> humpMap = new HashMap<String, Object>();
        for (Map.Entry<String, Object> entry : comMap.entrySet()) {
            humpMap.put(JsonUtils.convertHumpCase(entry.getKey()), entry.getValue());
        }
        return humpMap;

    }

    /**
     * key 转换成 单词首字母大写+下划线格式
     *
     * @param Map<String,Object> comMap
     * @return Map<String,Object>
     */
    public static Map<String, Object> MapToUpperCase(Map<String, Object> comMap) {
        Map<String, Object> upperCaseMap = new HashMap<String, Object>();
        for (Map.Entry<String, Object> entry : comMap.entrySet()) {
            upperCaseMap.put(JsonUtils.convertUpperCase(entry.getKey()), entry.getValue());
        }
        return upperCaseMap;

    }

    /**
     * JavaBean转换成map
     *
     * @param Object obj    JavaBean
     * @return Map<String,Object>    转换后的map
     */
    public static Map<String, Object> beanToMap(Object obj) throws Exception {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(obj, new Object[0]);
                if (result != null) {
                    returnMap.put(propertyName, result);
                }
            }
        }
        return returnMap;
    }

    /**
     * map转换成javabean
     *
     * @param Map<String,Object> map
     * @param @param             Object obj    javabean
     */
    public static void mapToBean(Map<String, Object> map, Object obj) throws Exception {
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class") && map.containsKey(propertyName)) {
                Object value = map.get(propertyName);
                Object[] args = new Object[1];
                //args[0] = tranDateToString(value);
                args[0] = valueHandler(value, descriptor.getPropertyType());
                try {
                    descriptor.getWriteMethod().invoke(obj, args);
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    throw new Exception("bean " + obj.getClass() + "中属性  " + propertyName + " 类型为  "
                            + descriptor.getPropertyType() + " 数据库返回类型为  "
                            + (value == null ? null : value.getClass()) + " 错误信息为 " + e.getMessage());
                }
            }
        }
    }

//	private static Object tranDateToString(Object value){
//		if(value==null){
//			return null;
//		}
//		if(value.getClass().getName().indexOf("TimeStamp")>-1){
//		   return ""+value;	
//		}
//		if(value.getClass().getName().indexOf("Date")>-1){
//			   return ""+value;	
//		}
//		if(value.getClass().getName().indexOf("Time")>-1){
//			   return ""+value;	
//		}
//		return value;
//	}

    private static Object valueHandler(Object value, @SuppressWarnings("rawtypes") Class beanClassName) {
        if (beanClassName.getName().indexOf("String") > -1) {
            return strValueHandler(value);
        }
        return value;
    }

    private static Object strValueHandler(Object value) {
        if (value == null) {
            return null;
        }
        return "" + value;
    }
}
