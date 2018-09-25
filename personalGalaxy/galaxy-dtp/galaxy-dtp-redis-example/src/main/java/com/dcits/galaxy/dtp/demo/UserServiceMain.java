package com.dcits.galaxy.dtp.demo;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.dubbo.common.utils.ConfigUtils;
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.dcits.galaxy.core.spring.SpringApplicationContext;
import com.dcits.galaxy.dtp.demo.service.user.UserService;
import com.dcits.galaxy.dtp.demo.util.SequenceHelper;
import com.dcits.galaxy.dtp.resume.RecoveryConfig;
import com.dcits.galaxy.dtp.resume.trunk.TrunkLockListener;
import com.dcits.galaxy.dtp.resume.trunk.TrunkResumer;
import com.dcits.galaxy.dtp.zk.DLock;
import com.dcits.galaxy.dtp.zk.DLockListener;
import com.dcits.galaxy.dtp.zk.ZKSingleBuilder;

/**
 * 发布用户服务
 * @author Yin.Weicai
 */
public class UserServiceMain {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		
		// RPC所有属性从 galaxy.properties中读取，
		// 但为了方便测试，基于Spring的PropertyPlaceholderConfigurer和Dubbo的ConfigUtils两者的特性，
		// 将每个应用单独使用的属性在这里配置，其他属性取galaxy.properties中的值
		System.setProperty("galaxy.protocol.port", "20880");
		System.setProperty("galaxy.application.group", "User_Group");
		System.setProperty("galaxy.application.name", "User_Service");
		
		ClassPathXmlApplicationContext context = null;
		context = new ClassPathXmlApplicationContext("classpath*:META-INF/spring/*.xml");
		
		//利用dubbo的工具获取RPC属性，从而保证机制上与Galaxy-core的一致
		String appName = ConfigUtils.getProperty("galaxy.application.name");
		String appGroup = ConfigUtils.getProperty("galaxy.application.group");
		String zkServers = ConfigUtils.getProperty("galaxy.registry.address");
		String zk_sesssion_timeout = ConfigUtils.getProperty("zk.session.timout");
		String zk_connection_timeout = ConfigUtils.getProperty("zk.connection.timeout");
		int sessionTimeout = 5000;
		int connectionTimeout = 5000;
		try {
			int t = Integer.parseInt(zk_sesssion_timeout);
			sessionTimeout = t;
		} catch (NumberFormatException e) {
			
		}
		try {
			int t = Integer.parseInt(zk_connection_timeout);
			connectionTimeout = t;
		} catch (NumberFormatException e) {
			
		}
		
		//查看发布的分支事务管理远程服务
		Object object = context.getBean("com.dcits.galaxy.dtp.branch.BranchManager");
		System.out.println(object);
		
		//初始化事务配置
		SequenceHelper.setServerId("user");

		// 初始化ZooKeeper相关信息
		ZKSingleBuilder.setZkServers(zkServers);
		ZKSingleBuilder.setSessionTimeout(sessionTimeout);
		ZKSingleBuilder.setConnectionTimeout(connectionTimeout);
		RecoveryConfig.init(appGroup);
		
		//启动该Jvm上的针对定时恢复调度
		TrunkResumer trunkResumer = new TrunkResumer();
		trunkResumer.setAppName( appName );
		trunkResumer.setAppGroup( appGroup );
		
		String trunkLockPath = RecoveryConfig.getTrunkLockPath(appGroup);
		DLock trunkLock = new DLock( trunkLockPath );
		DLockListener listener = new TrunkLockListener( trunkResumer );
		trunkLock.addListener(listener);
		trunkLock.tryLock();
		
		//如果是按顺序执行的话，不需要启动分支事务定时恢复调度
//		BranchResumer branchResumer = new BranchResumer();
//		branchResumer.setAppName( appName );
//		branchResumer.start();
		
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
		protocol.setPort(20880);
		protocol.setThreads(200);
		
		// 服务提供者暴露服务配置
		ServiceConfig<UserService> service = new ServiceConfig<UserService>(); // 此实例很重，封装了与注册中心的连接，请自行缓存，否则可能造成内存和连接泄漏
		service.setApplication(application);
		service.setRegistry(registry); // 多个注册中心可以用setRegistries()
		service.setProtocol(protocol); // 多个协议可以用setProtocols()
		service.setInterface(UserService.class);
		UserService userService = (UserService) SpringApplicationContext.getContext().getBean("userServiceImpl");
		service.setRef( userService );
//		service.setVersion("1.0.0");
		
		// 暴露及注册服务
		service.export();
		System.out.println("UserService向zookeeper注册成功");
		
		System.in.read();
		
	}
}
