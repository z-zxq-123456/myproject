package com.dcits.galaxy.dal.mybatis.extension;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.dcits.galaxy.dal.mybatis.ShardSqlSessionTemplate;
import com.dcits.galaxy.dal.mybatis.exception.DALException;
import com.dcits.galaxy.dal.mybatis.exception.ParseFileException;

public class MapperExtensionLoader {
	
	private static final Logger logger = LoggerFactory.getLogger(MapperExtensionLoader.class);
	
	private static final String HANDLER_SUFFIX = ".handler";
	
	private static final String EXECUTOR_SUFFIX = ".exector";

	private ShardSqlSessionTemplate sqlSessionTemplate;
	private String extesionLocations = "classpath*:META-INF/statements/*.properties";
	
	private Map<String, StatementHandler> handlersMap = new HashMap<>();
	private Map<String, StatementExecutor> executorsMap = new HashMap<>();

	public MapperExtensionLoader(ShardSqlSessionTemplate sqlSessionTemplate, String extesionLocations) {
		this.sqlSessionTemplate = sqlSessionTemplate;
		if(extesionLocations != null && !extesionLocations.isEmpty()){
			this.extesionLocations = extesionLocations;
		}
		init();
	}

	public StatementHandler getHandler(Class<?> clazz){
		return getHandler(clazz.getName());
	}
	
	public StatementHandler getHandler(String className){
		return handlersMap.get(className);
	}
	
	public StatementExecutor getExecutor(Class<?> clazz){
		return getExecutor(clazz.getName());
	}
	
	public StatementExecutor getExecutor(String className){
		return executorsMap.get(className);
	}
	
	public void init(){
		loadFromExternal();
		loadFromAnnotation();
	}
	
	private void loadFromExternal(){
		
		Properties prop = loadPropertiesFromExternal();
		
		for(Entry<?,?> entry : prop.entrySet()){
			String key = ((String) entry.getKey()).trim();
			String value = ((String) entry.getValue()).trim();
			
			if(key.endsWith(EXECUTOR_SUFFIX)) {
				String paramterType = key.substring(0, key.length() - EXECUTOR_SUFFIX.length());
				registerExecutor(paramterType, value);
			} else if (key.endsWith(HANDLER_SUFFIX)){
				String paramterType = key.substring(0, key.length() - HANDLER_SUFFIX.length());
				registerHandler(paramterType, value);
			} else {
				logger.warn("can't parse record:{}={}",key,value);
			}
		}
	}
	
	private Properties loadPropertiesFromExternal(){
		
		Properties properties = new Properties();
		
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource[] resources = null;
		try {
			resources = resolver.getResources(extesionLocations);
		} catch (IOException e) {
			throw new DALException(e);
		}
		
		if(resources == null || resources.length == 0) {
			return properties;
		}
		
		for(Resource resource : resources) {
			Properties prop = new Properties();
			try {
				prop.load(resource.getInputStream());
			} catch (IOException e) {
				logger.error("can't load dal extention config file:{}. please check it.", resource.getFilename());
				throw new ParseFileException(resource);
			}
			
			if(prop.isEmpty()) {
				continue;
			}
			
			for(Entry<?,?> entry : prop.entrySet()){
				String key = (String) entry.getKey();
				String value = (String) entry.getValue();
				if(properties.containsKey(key)){
					throw new ParseFileException("dup config for key:"+key);
				}
				properties.put(key, value);
			}
		}
		
		return properties;
	}
	
	private void loadFromAnnotation() {
		
		Configuration configuration = sqlSessionTemplate.getConfiguration();
		Collection<MappedStatement> mappedStatements = configuration.getMappedStatements();
		
		for(Object obj : mappedStatements) {
			
			if(!(obj instanceof MappedStatement)){
				continue;
			}
			
			MappedStatement ms = (MappedStatement) obj;
			
			Class<?> paramterType = ms.getParameterMap().getType();
			if(paramterType == null) {
				continue;
			}
			
			MappedHandle mappedHandleAnno = paramterType.getAnnotation(MappedHandle.class);
			if(mappedHandleAnno == null)
				continue;
			
			registerHandler(paramterType.getName(), mappedHandleAnno.handler());
			registerExecutor(paramterType.getName(), mappedHandleAnno.executor());
		}
	}
	
	private void registerHandler(String paramterType,String handleClass){
		
		if(handleClass.isEmpty())
			return;
		
		StatementHandler handler = handlersMap.get(paramterType);
		
		if (handler != null)
			return;
		
		handler = newInstance(handleClass, StatementHandler.class);
		
		handlersMap.put(paramterType, handler);
	}
	
	private void registerExecutor(String paramterType,String executorType){
		
		if(executorType.isEmpty())
			return;
		
		StatementExecutor handler = executorsMap.get(paramterType);
		
		if (handler != null)
			return;
		
		handler = newInstance(executorType, StatementExecutor.class);
		
		if(handler != null){
			executorsMap.put(paramterType, handler);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	private <T> T newInstance(String className, Class<T> type){
		Class<?> mappedHandle = loadClass(className);
		if(mappedHandle == null) {
			logger.warn("can't load class :{}", className);
			return null;
		}
		
		T handle = null;
		if(type.isAssignableFrom(mappedHandle)) {
			try {
				handle = (T) mappedHandle.newInstance();
			} catch (Exception e) {
				throw new DALException("can't instance MappedStatementHandler :" + className, e);
			}
		}
		return handle;
	}
	
	private Class<?> loadClass(String className){
		if(className == null||className.isEmpty())
			return null;
		Class<?> clazz = null;
		try {
			clazz = Class.forName(className);
		} catch (Exception e1) {
			try {
				ClassLoader loader = Thread.currentThread().getContextClassLoader();
				clazz = loader.loadClass(className);
			} catch (Exception e2){
				
			}
		}
		return clazz;
	}
}