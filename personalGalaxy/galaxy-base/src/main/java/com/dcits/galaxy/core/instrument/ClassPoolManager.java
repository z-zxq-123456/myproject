package com.dcits.galaxy.core.instrument;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.dcits.galaxy.instrument.TransformerRegistry;

import javassist.ClassPool;
import javassist.LoaderClassPath;

/**
 * 在多ClassLoader环境下,每个ClassLoader分配一个ClassPool
 * @author yin.weicai
 *
 */
public class ClassPoolManager {

	private volatile static ConcurrentMap<String, ClassPool> classPools = null;
	
	public static ClassPool getClassPool(ClassLoader classLoader){
		if( classLoader == null){
			return ClassPool.getDefault();
		}else{
			String loaderCode = classLoader.hashCode()  + "";
			ClassPool classPool = getClassPools().get(loaderCode);
			if( classPool == null ){
				synchronized( ClassPoolManager.class){
					classPool = getClassPools().get(loaderCode);
					if( classPool == null ){
						ClassPool pool = new ClassPool(true);
						pool.appendClassPath(new LoaderClassPath(classLoader));
						getClassPools().put(loaderCode, pool);
						classPool = pool;
					}
				}
			}
			return classPool;
		}
	}
	
	private static ConcurrentMap<String, ClassPool> getClassPools(){
		if( classPools == null){
			synchronized( ClassPoolManager.class){
				if( classPools == null){					
					classPools = new ConcurrentHashMap<>();
				}
			}
		}
		return classPools;
	}
	
	public static void realse(){
		//卸载与当前ClassLoader关联的类转换器
		TransformerRegistry.unregister( ClassPoolManager.class.getClassLoader() );
		
		if( classPools != null){
			classPools.clear();
			classPools = null;
		}
	}
}
