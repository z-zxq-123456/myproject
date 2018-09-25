package com.dcits.galaxy.dtp.demo;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.dubbo.common.utils.ConfigUtils;
import com.dcits.galaxy.core.spring.SpringApplicationContext;
import com.dcits.galaxy.dtp.clean.TrunkCleaner;
import com.dcits.galaxy.dtp.demo.service.transfer.TransferService;
import com.dcits.galaxy.dtp.demo.util.SequenceHelper;
import com.dcits.galaxy.dtp.resume.RecoveryConfig;
import com.dcits.galaxy.dtp.resume.trunk.TrunkLockListener;
import com.dcits.galaxy.dtp.resume.trunk.TrunkResumer;
import com.dcits.galaxy.dtp.zk.DLock;
import com.dcits.galaxy.dtp.zk.DLockListener;
import com.dcits.galaxy.dtp.zk.ZKSingleBuilder;

public class DemoMain {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		
		// RPC所有属性从 galaxy.properties中读取，
		// 但为了方便测试，基于Spring的PropertyPlaceholderConfigurer和Dubbo的ConfigUtils两者的特性，
		// 将每个应用单独使用的属性在这里配置，其他属性取galaxy.properties中的值
		System.setProperty("galaxy.protocol.port", "20883");
		System.setProperty("galaxy.application.group", "Transfer_Group");
		System.setProperty("galaxy.application.name", "Transfer_Service");
		
		ClassPathXmlApplicationContext context = null;
		context = new ClassPathXmlApplicationContext("classpath*:META-INF/spring/*.xml");
		
		//利用dubbo的工具获取RPC属性，从而保证机制上与Galaxy-core的一致 galaxy.registry.timeout
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
		SequenceHelper.setServerId("demo");
		
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
//		branchResumer.setAppName( "TransferService_provider" );
//		branchResumer.start();

		//开启过期事务清理机制
		TrunkCleaner cleaner = new TrunkCleaner();
		cleaner.setAppGroup(appGroup);
		cleaner.setFrequency(90);
		cleaner.setCleanCycleTime(180);
		cleaner.start();
		
		TransferService transfer = (TransferService) SpringApplicationContext.getContext().getBean("transferService");
		transfer.transferSuccess("1", "2", 10);
		
		System.in.read();
	}
}
