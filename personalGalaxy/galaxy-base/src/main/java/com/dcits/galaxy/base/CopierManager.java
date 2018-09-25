package com.dcits.galaxy.base;

import org.springframework.cglib.beans.BeanCopier;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class CopierManager {

	private static ConcurrentMap<String, BeanCopier> copiers = new ConcurrentHashMap<String, BeanCopier>();

	public static BeanCopier getBeanCopier(Class<?> sourceClass, Class<?> targetClass,boolean useConverter){
		String key = sourceClass.getName() + "_" + targetClass.getName();
		BeanCopier beanCopier = copiers.get(key);
		if( beanCopier == null){
			beanCopier = newBeanCopier(key, sourceClass, targetClass, useConverter);
		}
		return beanCopier;
	}
	
	private static synchronized BeanCopier newBeanCopier(String key,Class<?> sourceClass, Class<?> targetClass,boolean useConverter){
		BeanCopier beanCopier = copiers.get(key);
		if( beanCopier == null){
			beanCopier = BeanCopier.create(sourceClass, targetClass, useConverter);
			copiers.put(key, beanCopier);
		}
		return beanCopier;
	}
}
