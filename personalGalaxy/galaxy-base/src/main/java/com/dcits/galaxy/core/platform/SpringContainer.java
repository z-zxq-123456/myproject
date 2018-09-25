package com.dcits.galaxy.core.platform;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class SpringContainer {
	 	private static final Logger logger = LoggerFactory.getLogger(SpringContainer.class);

	    public static final String SPRING_CONFIG = "dubbo.spring.config";
	    
	    public static final String DEFAULT_SPRING_CONFIG = "classpath*:META-INF/spring/*.xml";

	    static ClassPathXmlApplicationContext context;
	    
	    public static ClassPathXmlApplicationContext getContext() {
			return context;
		}

		public void start() {
	        String configPath = System.getProperty(SPRING_CONFIG);
	        if (configPath == null || configPath.length() == 0) {
	            configPath = DEFAULT_SPRING_CONFIG;
	        }
	        context = new ClassPathXmlApplicationContext(configPath.split("[,\\s]+"));
	        context.start();
	    }

	    public void stop() {
	        try {
	            if (context != null) {
	                context.stop();
	                context.close();
	                context = null;
	            }
	        } catch (Throwable e) {
	            logger.error(e.getMessage(), e);
	        }
	    }
}
