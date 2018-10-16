package com.dcits.ensemble.om.cmc.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dcits.baixin.msoa.support.ConvertUtils;
import java.beans.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/4/18
 */
public class BeanConvertUtils<T,V> {

    private static BeanConvertUtils beanConvertUtils;
    public synchronized static BeanConvertUtils getInstance(){
        if (beanConvertUtils == null){
            beanConvertUtils = new BeanConvertUtils();
        }
        return beanConvertUtils;
    }

    /**
     * listToJsonArray
     */
    public T listToJsonArray(List<V> v){

        JSONArray array = new JSONArray();
        for (V o:v){
            Object object = JSON.toJSON(o);
            array.add(object);
        }
        return (T)array;
    }

    /**
     * 结果响应
     * @param body
     * @param responseType
     * @return
     */
    public Object convertToResult(JSONObject body, Class responseType){

        JSONObject humpObject = ConvertUtils.upper2hump(body);
        String tempStr = JSONObject.toJSONString(humpObject);
        Object obj =  JSON.parseObject(tempStr,responseType);
        return obj;
    }

    /**
     * jsonArray->object
     * @param sourceJson
     * @param targetObject
     */
    private void setterAttrFromJsonArray(Map sourceJson,Object targetObject)
    throws IntrospectionException,
           IllegalAccessException,
           InvocationTargetException{

        BeanInfo beanInfo = Introspector.getBeanInfo(targetObject.getClass());
        PropertyDescriptor[] descriptor = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor de:descriptor){
            String key = de.getName();
            Set keySet =  sourceJson.keySet();
            if (keySet.contains(key)){
                de.getWriteMethod().invoke(targetObject,sourceJson.get(key));
            }
        }

    }

    /**
     * @param sourceModel
     * @param dataTableModel
     * @param flatten
     * @param innerClassName 金融网关返回类 静态内部类名称
     * @return
     */
    public static Object convertToDataTableModel(Object sourceModel,
                                                 Object dataTableModel,
                                                 boolean flatten,
                                                 String innerClassName) {
       try {
           Class source = sourceModel.getClass();
           Class target = dataTableModel.getClass();
           List<String> name = findAttr(target);
           if (flatten){
               Object object = JSON.toJSON(sourceModel);
               JSONObject jsonObject = JSONObject.parseObject(object.toString());
               List<String> sourceName = findAttr(source);
               for (String attr:name){
                   if (attr.equals("class")){
                       continue;
                   }
                   if (sourceName.contains(attr)){
                       new PropertyDescriptor(attr,target).getWriteMethod().invoke(dataTableModel,jsonObject.getString(attr));
                   }
               }
           }else {
               ;
           }
       }catch (Exception e){
           e.printStackTrace();
       }
        return dataTableModel;
    }

    /**
     *
     * @param cls
     * @return
     * @throws IntrospectionException
     */
    private static List<String> findAttr(Class cls) throws IntrospectionException{
        BeanInfo beanInfo = Introspector.getBeanInfo(cls);
        PropertyDescriptor[] propertyDescriptor = beanInfo.getPropertyDescriptors();
        List<String> name = new ArrayList<>();
        for (PropertyDescriptor tp:propertyDescriptor){
            name.add(tp.getName());
        }
        return name;
    }
}
