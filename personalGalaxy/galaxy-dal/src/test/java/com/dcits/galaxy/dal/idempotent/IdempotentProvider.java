package com.dcits.galaxy.dal.idempotent;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;

public class IdempotentProvider {

	public static void main(String[] args) throws Exception {
		
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("classpath:META-INF/spring/*.xml");
		
		String appName = "IdempotentService_Provider";
		
		// 当前应用配置
		ApplicationConfig application= new ApplicationConfig();
		application.setName( appName );
		
		// 连接注册中心配置
		RegistryConfig registry = new RegistryConfig();
		String address = "zookeeper://127.0.0.1:2181";
		registry.setAddress( address );
		registry.setTimeout(5000);
		
		// 服务提供者协议配置
		ProtocolConfig protocol = new ProtocolConfig();
		protocol.setName("dubbo");
		protocol.setPort(20881);
		protocol.setThreads(200);
		
		// 服务提供者暴露服务配置
		ServiceConfig<IdempotentService> service = new ServiceConfig<IdempotentService>(); // 此实例很重，封装了与注册中心的连接，请自行缓存，否则可能造成内存和连接泄漏
		service.setApplication(application);
		service.setRegistry(registry); // 多个注册中心可以用setRegistries()
		service.setProtocol(protocol); // 多个协议可以用setProtocols()
		service.setInterface(IdempotentService.class);
		IdempotentService idempotentService = new IdempotentServiceImpl(context);
		service.setRef( idempotentService );
		service.setVersion("1.0.0");
		
		// 暴露及注册服务
		service.export();
		System.out.println("IdempotentService 向 zookeeper 注册成功");
		
		System.in.read();
	}
}
