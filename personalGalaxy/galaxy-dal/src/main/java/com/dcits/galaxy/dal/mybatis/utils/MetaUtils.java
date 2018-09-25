package com.dcits.galaxy.dal.mybatis.utils;

import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaClass;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;

import com.dcits.galaxy.dal.mybatis.exception.DALException;

public abstract class MetaUtils {

	private final static ObjectFactory objectFactory = new DefaultObjectFactory();
	private final static ObjectWrapperFactory objectWrapperFactory = new DefaultObjectWrapperFactory();
	private final static ReflectorFactory reflectorFactory =new DefaultReflectorFactory();
	
	public static MetaClass forClass(Class<?> type){
		return MetaClass.forClass(type, reflectorFactory);
	}
	
	public static MetaObject forObject(Object obj){
		return MetaObject.forObject(obj, objectFactory, objectWrapperFactory, reflectorFactory);
	}
	
	/**
     * 通过反射获得对应的属性值
     * @param obj 需要反射的对象
     * @param field 属性名
     * @return 若包含则返回对应的属性值
     */
    public static Object getValue(Object obj, String field){
    	Object o = null;
    	try{
    		MetaObject metaObject = forObject(obj);
    		if(metaObject.hasGetter(field)){
    			o = metaObject.getValue(field);
    		}
    	}catch(Exception e){
    		throw new DALException(e.getMessage());
    	}
        return o;
    }
}
