/**
 * <p>Title: BeanTools.java</p>
 * <p>Description: 实现将java bean中参数，以键值拼接字符串方式实现</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 *
 * @author Tim
 * @update 2014年9月15日 下午2:02:19
 * @version V1.0
 */
package com.dcits.galaxy.base.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.dcits.galaxy.base.BaseGenerator;
import com.dcits.galaxy.base.CopierManager;
import com.dcits.galaxy.base.exception.GalaxyException;
import org.springframework.cglib.beans.BeanCopier;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Tim
 * @version V1.0
 * @description Bean的工具类
 * @update 2014年9月15日 下午2:03:59
 */

public class BeanUtils {


    private static Map<Class, Map<String, PropertyDescriptor>> classPropertyDescriptors = new ConcurrentHashMap<Class, Map<String, PropertyDescriptor>>();

    /**
     * 获取javabean的toString
     *
     * @param o
     * @return
     * @description
     * @version 1.0
     * @author Tim
     * @update 2015年2月3日 上午10:33:21
     */
    public static String getString(Object o) {
        try {
            JSONObject printJson = new JSONObject();
            JSONObject objJson = new JSONObject();
            objJson.put("clazz", o.getClass().getName());
            printJson.put("obj", objJson);
            printJson.put("bean", o);
            // 通过JSON将bean的属性输出
            return JSON.toJSONString(printJson, SerializerFeature.PrettyFormat);
        } catch (Throwable t) {
            return t.toString();
        }
    }

    /**
     * 使用cglib对javabean进行拷贝
     *
     * @param source
     * @param target
     * @description
     * @version 1.0
     * @author Tim
     * @update 2015年2月3日 上午10:32:58
     */
    public static <T, V> void copy(T source, V target) {
        if (null == source)
            return;
        //优化使用缓存策略，将create对象的过程进行缓存处理，后续通过缓存获取。
//       BeanCopier cp = BeanCopier.create(source.getClass(), target.getClass(),
//               false);
        BeanCopier cp = CopierManager.getBeanCopier(source.getClass(), target.getClass(),
                false);
        cp.copy(source, target, null);
    }

    public static <T, V> V copyByClass(T source, Class<V> targetClazz) {
        if (null == source)
            return null;
        V v = (V) BaseGenerator.create(targetClazz).next();
        copy(source, v);
        return v;
    }

    public static <T, V> List<V> listCopyByClass(List<T> source, Class<V> targetClazz) {
        List<V> target = new ArrayList<V>();
        listCopy(source, target, targetClazz);
        return target;
    }

    /**
     * 使用cglib对javabean进行拷贝
     *
     * @param source
     * @param target
     * @param clazz
     * @description
     * @version 1.0
     * @author Tim
     * @update 2015年2月5日 下午6:09:36
     */
    public static <T, V> void listCopy(List<T> source, List<V> target,
                                       Class<V> clazz) {
        if (null == target)
            target = new ArrayList<V>();
        if (null != source) {
            int i = 0;
            for (T s : source) {
                V v;
                if (target.size() > 0 && i + 1 <= target.size()) {
                    v = target.get(i);
                    BeanUtils.copy(s, v);
                } else {
                    v = (V) BaseGenerator.create(clazz).next();
                    BeanUtils.copy(s, v);
                    target.add(v);
                }
                i++;
            }
        }
    }

    public static String[] splitByFirst(String fieldName) {
        return fieldName.split("\\.", 2);
    }

    public static Object getFieldValue(Object object, String fieldName) {
        if (object == null)
            return null;
        try {
            if (fieldName.indexOf(".") < 0) {
                return getProperty(object, fieldName);
            } else {
                String[] field = splitByFirst(fieldName);
                return getFieldValue(getProperty(object, field[0]), field[1]);
            }
        } catch (Exception e) {
            throw new GalaxyException("对象没有对应的属性：" + fieldName);
        }


    }

    public static void setFieldValue(Object object, String fieldName, Object fieldValue) {
        if (object == null) {
            return;
        }
        if (fieldName.indexOf(".") < 0) {
            setProperty(object, fieldName, fieldValue);
        } else {
            String[] field = splitByFirst(fieldName);
            setFieldValue(getProperty(object, field[0]), field[1], fieldValue);
        }
    }

    public static String firstUpper(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static Object getProperty(Object object, String fieldName) {

        try {
            return getPropertyDescriptor(object, fieldName).getReadMethod().invoke(object);
        } catch (Exception e) {
            throw new GalaxyException(e);
        }


    }

    public static void setProperty(Object object, String fieldName, Object fieldValue) {

        try {
            getPropertyDescriptor(object, fieldName).getWriteMethod().invoke(object, fieldValue);
        } catch (Exception e) {
            throw new GalaxyException(e);
        }
    }

    public static PropertyDescriptor getPropertyDescriptor(Object object, String fieldName) {

       /* try {
            propertyDescriptor = new PropertyDescriptor(fieldName,object.getClass());
        } catch (IntrospectionException e) {
            throw new GalaxyException(e);
        }*/
        Map<String, PropertyDescriptor> map = getPropertyDescriptors(object.getClass());
        PropertyDescriptor propertyDescriptor = map.get(fieldName);
        if (propertyDescriptor == null) {
            throw new GalaxyException(object.getClass().getName() + "类没有" + fieldName + "属性");
        }
        return propertyDescriptor;
    }

    private static Map<String, PropertyDescriptor> getPropertyDescriptors(Class clazz) {
        Map<String, PropertyDescriptor> propertyMap = classPropertyDescriptors.get(clazz);
        if (propertyMap == null) {
            propertyMap = new HashMap<>();
            BeanInfo beanInfo = null;
            try {
                beanInfo = Introspector.getBeanInfo(clazz);
            } catch (IntrospectionException e) {
                e.printStackTrace();
            }
            if (beanInfo == null)
                return null;
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                if (propertyDescriptor.getWriteMethod() != null && propertyDescriptor.getReadMethod() != null) {
                    propertyMap.put(propertyDescriptor.getName(), propertyDescriptor);
                }
            }
            classPropertyDescriptors.put(clazz, propertyMap);
        }
        return propertyMap;
    }


    private static boolean containsProperty(Object object, String[] names) {
        if (object instanceof List)
            return false;
        if (object instanceof Map)
            return false;
        boolean ret = false;
        if (object == null)
            return ret;
        int i = 0;
        int length = names.length;
        for (; i < length; i++) {
            try {
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(names[i], object.getClass());
            } catch (IntrospectionException e) {
                break;
            }
        }
        if (i == length)
            ret = true;
        return ret;
    }

    public static boolean isBasicType(Class clazz) {
        if (clazz == Boolean.TYPE
                || clazz == Boolean.class
                || clazz == Character.TYPE
                || clazz == Character.class
                || clazz == Byte.TYPE
                || clazz == Byte.class
                || clazz == Short.TYPE
                || clazz == Short.class
                || clazz == Integer.TYPE
                || clazz == Integer.class
                || clazz == Long.TYPE
                || clazz == Long.class
                || clazz == Float.TYPE
                || clazz == Float.class
                || clazz == Double.TYPE
                || clazz == Double.class
                || clazz == BigInteger.class
                || clazz == BigDecimal.class
                || clazz == String.class)
            return true;
        else return false;
    }

    private static void pickBeansByProperty(Object obj, String[] names, List list) {
        if (obj == null)
            return;
        if (isBasicType(obj.getClass()))
            return;
        if (obj instanceof Map)
            return;
        if (obj instanceof List) {
            List list1 = (List) obj;
            for (Object object : list1) {
                pickBeansByProperty(object, names, list);
            }
        } else {
            if (containsProperty(obj, names))
                list.add(obj);
            BeanInfo beanInfo = null;
            try {
                beanInfo = Introspector.getBeanInfo(obj.getClass());
            } catch (IntrospectionException e) {
                e.printStackTrace();
            }
            if (beanInfo == null)
                return;
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                if (propertyDescriptor.getWriteMethod() != null && propertyDescriptor.getReadMethod() != null) {
                    try {
                        Object object = propertyDescriptor.getReadMethod().invoke(obj);
                        pickBeansByProperty(object, names, list);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static List pickBeansByProperty(Object obj, String... names) {
        if (obj == null)
            return null;
        List ret = new ArrayList();
        pickBeansByProperty(obj, names, ret);
        return ret;
    }

    public static List pickValuesByProperty(Object obj, String name) {
        List beans = pickBeansByProperty(obj, new String[]{name});
        if (beans == null || beans.size() == 0)
            return null;
        List ret = new ArrayList();
        for (Object bean : beans) {
            Object value = getFieldValue(bean, name);
            ret.add(value);
        }
        return ret;
    }

    public static void mapMerge(Map src, Map tar) {
        if (src == null || src.isEmpty() || tar == null)
            return;
        for (Object key : src.keySet()) {
            Object srcValue = src.get(key);
            if (srcValue != null) {
                Object tarValue = tar.get(key);
                if (srcValue instanceof Map && tarValue instanceof Map) {
                    mapMerge((Map) srcValue, (Map) tarValue);
                } else if (srcValue instanceof Collection && tarValue instanceof Collection) {
                    ((Collection) tarValue).addAll((Collection) srcValue);
                } else {
                    tar.put(key, srcValue);
                }
            }
        }
    }

}
