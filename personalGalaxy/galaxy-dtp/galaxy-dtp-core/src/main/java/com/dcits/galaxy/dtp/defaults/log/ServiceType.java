package com.dcits.galaxy.dtp.defaults.log;

/**
 * Created by Tim on 2015/8/20.
 */
public enum ServiceType {
	
	/**
	 * 普通的不受容器管理的Bean
	 */
    NORMAL_BEAN, 
    
    /**
     * 由Spring IOC容器管理的Bean
     */
    SPRING_BEAN
}
