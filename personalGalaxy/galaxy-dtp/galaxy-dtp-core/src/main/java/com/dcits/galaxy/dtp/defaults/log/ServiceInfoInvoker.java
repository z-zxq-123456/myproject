package com.dcits.galaxy.dtp.defaults.log;

import java.lang.reflect.Method;
import java.util.Map;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;

import com.dcits.galaxy.core.spring.SpringApplicationContext;
import com.dcits.galaxy.dtp.exception.DTPException;

/**
 * ServiceInfo配套的调用类
 * @author Yin.Weicai
 *
 */
public class ServiceInfoInvoker  {
	
	//private static Logger logger = LoggerFactory.getLogger(ServiceInfoInvoker.class);
	
	/**
	 * 调用指定的service
	 * @param serviceInfo
	 * @return
	 */
	public static boolean invokeService(ServiceInfo serviceInfo){
		boolean result = false;
		try {
			String serviceClassName = serviceInfo.getServiceClass();
			String methodName = serviceInfo.getMethodName();
			Class<?>[] argsType = serviceInfo.getArgsType();
			Object[] args = serviceInfo.getArgs();
			
			Class<?> serviceClass = Class.forName( serviceClassName );
			Method method = serviceClass.getMethod(methodName, argsType);
			
			ServiceType serviceType = serviceInfo.getServiceType();
			Object service =  null;
			if( ServiceType.NORMAL_BEAN == serviceType){
				service = serviceClass.newInstance();
			} else if ( ServiceType.SPRING_BEAN == serviceType){
				Map<String, ?> map = SpringApplicationContext.getContext().getBeansOfType(serviceClass);
				if(map.isEmpty()) {
					throw new NoSuchBeanDefinitionException(serviceClass);
				}
				for(Object obj : map.values()){
					service = obj;
					break;
				}
			}

			method.invoke(service, args);
			result = true;
		} catch (Exception e) {
			throw new DTPException("execute submitlog faild.",e);
		}
		return result;
	}
}
