package com.dcits.galaxy.instrument;

import java.lang.instrument.ClassFileTransformer;
import java.util.HashMap;
import java.util.Map;

/**
 * 转换器注册中心 
 * @author yin.weicai
 */
public class TransformerRegistry {
	
	private static Map<String, TransformerContainer> transformers = new HashMap<>();
	
	public static void register(String targetClass,String transferClass){
//		ClassLoader classLoader = getContextLoader();
		String loaderIdentifer = getContextLoaderIdentifer();
		
		TransformerContainer transformerContainer = transformers.get( loaderIdentifer );
		if( transformerContainer == null){
			transformerContainer = new TransformerContainer(); 
			transformers.put(loaderIdentifer, transformerContainer);
		}
		
		if( transformerContainer.contains( targetClass ) ){
			System.out.println("Duplicated Transformer for " + targetClass);
			return;
		}else{
			try {
//				ClassFileTransformer action = (ClassFileTransformer)(Class.forName(transferClass, true, classLoader).newInstance() );
				transformerContainer.put(targetClass, transferClass);
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void unregister(String targetClass){
		String loaderIdentifer = getContextLoaderIdentifer();
		
		TransformerContainer transformerContainer = transformers.get( loaderIdentifer );
		if( transformerContainer != null){
			String className = targetClass;
			transformerContainer.remove(className);
		}
	}
	
	public static void unregister( ClassLoader classLoader ){
		String loaderIdentifer = getContextLoaderIdentifer( classLoader );
		transformers.remove(loaderIdentifer);
	}
	
	public static void unregister( ){
		String loaderIdentifer = getContextLoaderIdentifer();
		transformers.remove(loaderIdentifer);
	}
	
	public static ClassFileTransformer get(String className){
		ClassFileTransformer reulst = null;
		String loaderIdentifer = getContextLoaderIdentifer();
		TransformerContainer transformerContainer = transformers.get( loaderIdentifer );
		if( transformerContainer != null){
			String transferClass = transformerContainer.get( className);
			ClassLoader classLoader = getContextLoader();
			try {
				reulst = (ClassFileTransformer)(Class.forName(transferClass, true, classLoader).newInstance() );
			} catch (Exception e) {
				// ignored
			}
		}
		return reulst;
	}
	
	public static boolean contains(String className){
		boolean reulst = false;
		String loaderIdentifer = getContextLoaderIdentifer();
		TransformerContainer transformerContainer = transformers.get( loaderIdentifer );
		if( transformerContainer != null){
			reulst = transformerContainer.contains( className );
		}
		return reulst;
	}
	
	public static boolean isRegister(){
		String loaderIdentifer = getContextLoaderIdentifer();
		return transformers.containsKey(loaderIdentifer);
	}
	
	public static String getContextLoaderIdentifer(){
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		String loaderClassName = classLoader.getClass().getName();
		String loaderHashCode = classLoader.hashCode() + "";
		String loaderIdentifer = loaderClassName + "_" + loaderHashCode;
		return loaderIdentifer;
	}
	
	public static String getContextLoaderIdentifer( ClassLoader classLoader ){
		String loaderClassName = classLoader.getClass().getName();
		String loaderHashCode = classLoader.hashCode() + "";
		String loaderIdentifer = loaderClassName + "_" + loaderHashCode;
		return loaderIdentifer;
	}
	
	public static ClassLoader getContextLoader(){
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		return classLoader;
	}
}
