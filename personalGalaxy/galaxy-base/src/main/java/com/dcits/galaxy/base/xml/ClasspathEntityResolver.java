package com.dcits.galaxy.base.xml;

import org.springframework.beans.factory.xml.PluggableSchemaResolver;

import com.dcits.galaxy.base.util.ClassLoaderUtils;

public class ClasspathEntityResolver extends PluggableSchemaResolver {

	public static final String DEFAULT_SCHEMA_MAPPINGS_LOCATION = "META-INF/galaxy.schemes";
	
	private static volatile ClasspathEntityResolver instance;
	
	public ClasspathEntityResolver() {
		super(ClassLoaderUtils.getClassLoader(), DEFAULT_SCHEMA_MAPPINGS_LOCATION);
	}
	
	public static ClasspathEntityResolver getInstance(){
		if(instance == null){
			synchronized (ClasspathEntityResolver.class) {
				if(instance == null){
					instance = new ClasspathEntityResolver();
				}
			}
		}
		return instance;
	}

}
