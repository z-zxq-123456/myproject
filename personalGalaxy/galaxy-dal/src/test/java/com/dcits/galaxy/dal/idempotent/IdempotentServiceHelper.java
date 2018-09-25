package com.dcits.galaxy.dal.idempotent;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;

/**
 * 远程服务工具类
 * @author Yin.Weicai
 */
public class IdempotentServiceHelper {
	
	@SuppressWarnings("rawtypes")
	private static Map<Class, ReferenceConfig> referenceCache = new HashMap<Class, ReferenceConfig>();
	
	@SuppressWarnings("rawtypes")
	private static Map<Class, Object> serviceCache = new HashMap<Class, Object>();
	
	public static Object getServiceByClass( Class<?> serviceClass){
		if( serviceClass.getName().equals(IdempotentService.class.getName()) ){
			return getIdempotentService();
		}
		return null;
	} 
	
	
	/**
	 * 获取用户服务
	 * @return
	 */
	public static IdempotentService getIdempotentService(){
		IdempotentService userService = null;
		if(serviceCache.containsKey( IdempotentService.class )){
			userService = (IdempotentService)serviceCache.get( IdempotentService.class );
		}else{
			// 当前应用配置
			ApplicationConfig application= new ApplicationConfig();
			application.setName("IdempotentService-Consumer");
			
			// 连接注册中心配置
			RegistryConfig registry = new RegistryConfig();
			String address = "zookeeper://127.0.0.1:2181";
			registry.setAddress( address );
			// 引用远程服务
			ReferenceConfig<IdempotentService> reference = new ReferenceConfig<IdempotentService>(); // 此实例很重，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能造成内存和连接泄漏
			reference.setApplication(application);
			reference.setRegistry(registry); // 多个注册中心可以用setRegistries()
			reference.setInterface(IdempotentService.class);
			reference.setVersion("1.0.0");
			referenceCache.put( IdempotentService.class, reference);
			
			// 和本地bean一样使用xxxService
			userService = (IdempotentService)reference.get(); // 注意：此代理对象内部封装了所有通讯细节，对象较重，请缓存复用 
			System.out.println("从zookeeper获取IdempotentService成功");
			serviceCache.put( IdempotentService.class, userService);
		}
		return userService;
		
	}
}
