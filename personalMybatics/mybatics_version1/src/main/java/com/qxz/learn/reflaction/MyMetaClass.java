package com.qxz.learn.reflaction;

import org.apache.ibatis.reflection.MetaClass;
import org.apache.ibatis.reflection.property.PropertyTokenizer;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/10/9
 */
public class MyMetaClass {

    private MyReflectorFactory reflectorFactory;

    private MyReflector reflector;

    private MyMetaClass(Class<?> type,MyReflectorFactory reflectorFactory){
        this.reflectorFactory = reflectorFactory;
        this.reflector = reflectorFactory.findForClass(type);
    }

    public static MyMetaClass forClass(Class<?> type,MyReflectorFactory reflectorFactory){
        return new MyMetaClass(type,reflectorFactory);
    }

    public boolean hasSetter(String name) {
        PropertyTokenizer prop = new PropertyTokenizer(name);
        if (prop.hasNext()) {
            if (reflector.hasSetter(prop.getName())) {
                MyMetaClass metaProp = metaClassForProperty(prop.getName());
                return metaProp.hasSetter(prop.getChildren());
            } else {
                return false;
            }
        } else {
            return reflector.hasSetter(prop.getName());
        }
    }

    public MyMetaClass metaClassForProperty(String name) {
        Class<?> propType = reflector.getGetterType(name);
        return MyMetaClass.forClass(propType, reflectorFactory);
    }


}
