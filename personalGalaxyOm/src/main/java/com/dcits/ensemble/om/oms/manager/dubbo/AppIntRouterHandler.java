package com.dcits.ensemble.om.oms.manager.dubbo;


import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.registry.Registry;
import com.alibaba.dubbo.registry.RegistryFactory;
import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.ensemble.om.oms.dao.utils.OMSIDao;
import com.dcits.ensemble.om.oms.module.app.EcmAppIntant;
import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 应用实例路由处理类* 
 * @author tangxlf
 * @date 2016-03-15 
 */
@Component
public class AppIntRouterHandler {
	private  final Logger log = LoggerFactory.getLogger(this.getClass());
	private  ExecutorService executorService ;
	private final static int  THREAD_NUM = 200;//并发线程数量	
	@Resource
	private OMSIDao omsBaseDao;
	@Resource
	private UpgAppIntantCache upgAppIntantCache;
	@Resource
	DubboRouterHelp dubboRouterHelp;
	/**
	 * 先升级应用实例启动前注册应用实例路由规则，此处会设置不可访问先升级应用实例的IP和端口
	 * @param Integer appId          应用ID
	 */
	public  void registerEarlyFirstAppRule(Integer appId){
		System.out.println("appId"+appId);
		String zkUrl = upgAppIntantCache.getZkUrl(appId);
		executorService = Executors.newFixedThreadPool(THREAD_NUM);
		ZkClient zkc = new ZkClient(zkUrl,5000);
		String hostAndPorts = upgAppIntantCache.getHostAndPorts(appId);
		List<EcmAppIntant> earlyUpgList = upgAppIntantCache.getAppIntEarlyList(appId);//对应appId的先升级实例
		log.info("zkUrl="+zkUrl);
		Registry registry = dubboRouterHelp.getRegistry(appId);
		if (zkc.exists("/"+DubboUtil.DUBBO_DIR_NAME)){
			List<String> childList =zkc.getChildren("/"+DubboUtil.DUBBO_DIR_NAME);//获取注册中心dubbo服务列表
			for(String service:childList){
				executorService.execute(new EarlyFirstAppRuleThread(registry,zkc,hostAndPorts,service,earlyUpgList));
			}
			executorService.shutdown();

		}
		while(true){
			if(executorService.isTerminated()){
				zkc.close();
				//registry.destroy();
				break;
			}
		}
	}
	/**
	 * 先升级应用实例启动后注册应用实例路由规则，此处会设置启动的实例中的新服务不可访问先升级应用实例的IP和端口
	 * @param Integer appId        应用ID
	 */
	public  void registerEarlySecondAppRule(Integer appId){
		String zkUrl = upgAppIntantCache.getZkUrl(appId);
		executorService = Executors.newFixedThreadPool(THREAD_NUM);
		ZkClient zkc = new ZkClient(zkUrl,5000);
		String earlyHostAndPorts = upgAppIntantCache.getEarlyHostAndPorts(appId);
		List<EcmAppIntant> earlyUpgList = upgAppIntantCache.getAppIntEarlyList(appId);//对应appId的先升级实例
		Registry registry = dubboRouterHelp.getRegistry(appId);
		List<String> childList =zkc.getChildren("/" + DubboUtil.DUBBO_DIR_NAME);//获取注册中心dubbo服务列表
		for(String service:childList){
			executorService.execute(new EarlySecondAppRuleThread(registry,zkc,earlyHostAndPorts,service,earlyUpgList));
		}
		executorService.shutdown();
		while(true){
			if(executorService.isTerminated()){
				zkc.close();
				//registry.destroy();
				break;
			}
		}
	}
	/**
	 * 后升级部署前切换应用实例路由规则，此处会解注先升级应用实例的IP和端口，会注册后升级应用实例的IP和端口
	 * @param Integer appId        应用ID
	 */
	public  void swithAppRule(Integer appId){
		String zkUrl = upgAppIntantCache.getZkUrl(appId);
		executorService = Executors.newFixedThreadPool(THREAD_NUM);
		ZkClient zkc = new ZkClient(zkUrl,5000);
		String hostAndPorts = upgAppIntantCache.getHostAndPorts(appId);
		List<EcmAppIntant> lateUpgList = upgAppIntantCache.getAppIntLateList(appId);//对应appId的后升级实例
		List<EcmAppIntant> earlyUpgList = upgAppIntantCache.getAppIntEarlyList(appId);//对应appId的先升级实例
		Registry registry =dubboRouterHelp.getRegistry(appId);;
		List<String> childList =zkc.getChildren("/"+DubboUtil.DUBBO_DIR_NAME);//获取注册中心dubbo服务列表
		for(String service:childList){
			executorService.execute(new SwithAppRuleThread(registry,zkc,hostAndPorts,service,lateUpgList,earlyUpgList));
		}
		executorService.shutdown();
		while(true){
			if(executorService.isTerminated()){
				zkc.close();
				//registry.destroy();
				break;
			}
		}
	}
	/**
	 * 解注先升级应用实例的IP和端口--升级流程第三步回退时执行
	 * @param Integer appId        应用ID
	 */
	public  void clearEarlyAppRule(Integer appId){
		String zkUrl = upgAppIntantCache.getZkUrl(appId);
		executorService = Executors.newFixedThreadPool(THREAD_NUM);
		ZkClient zkc = new ZkClient(zkUrl,5000);
		String hostAndPorts = upgAppIntantCache.getHostAndPorts(appId);
		List<EcmAppIntant> earlyUpgList = upgAppIntantCache.getAppIntEarlyList(appId);//对应appId的先升级实例
		Registry registry =dubboRouterHelp.getRegistry(appId);
		List<String> childList =zkc.getChildren("/"+DubboUtil.DUBBO_DIR_NAME);//获取注册中心dubbo服务列表
		for(String service:childList){
			System.out.print("service="+service+"appId="+appId+"earlyUpgList="+earlyUpgList);
			executorService.execute(new ClearAppRuleThread(registry,zkc,hostAndPorts,service,earlyUpgList));
		}
		executorService.shutdown();
		while(true){
			if(executorService.isTerminated()){
				zkc.close();
				//registry.destroy();
				break;
			}
		}
	}

	/**
	 * 解注后升级应用实例的IP和端口--升级流程完成时执行
	 * @param Integer appId        应用ID
	 */
	public  void clearLateAppRule(Integer appId){
		String zkUrl = upgAppIntantCache.getZkUrl(appId);
		executorService = Executors.newFixedThreadPool(THREAD_NUM);
		ZkClient zkc = new ZkClient(zkUrl,5000);
		String hostAndPorts = upgAppIntantCache.getHostAndPorts(appId);
		List<EcmAppIntant> lateUpgList = upgAppIntantCache.getAppIntLateList(appId);//对应appId的后升级实例
		Registry registry = dubboRouterHelp.getRegistry(appId);
		List<String> childList =zkc.getChildren("/"+DubboUtil.DUBBO_DIR_NAME);//获取注册中心dubbo服务列表
		for(String service:childList){
			executorService.execute(new ClearAppRuleThread(registry,zkc,hostAndPorts,service,lateUpgList));
		}
		executorService.shutdown();
		while(true){
			if(executorService.isTerminated()){
				zkc.close();
				//registry.destroy();
				break;
			}
		}
	}
	//判断是否属于当前实例的服务
	private boolean isAppIntService(List<String> providerList,String hostAndPorts){
		for(String providerUrl:providerList){
			if(hostAndPorts.indexOf(DubboUtil.parserProviderUrl(providerUrl))>-1) return true;
		}
		return false;
	}

	//判断是否属于当前实例的服务
	private String getAppProviderUrl(List<String> providerList,String hostAndPorts){
		for(String providerUrl:providerList){
			if(hostAndPorts.indexOf(DubboUtil.parserProviderUrl(providerUrl))>-1) return providerUrl;
		}
		return null;
	}

	//先升级应用实例启动前注册应用实例路由规则
	private class EarlyFirstAppRuleThread implements Runnable{
		Registry registry ;
		String   service;
		ZkClient zkc;
		String hostAndPorts;
		List<EcmAppIntant> earlyUpgList;//对应appId的先升级实例
		EarlyFirstAppRuleThread(Registry registry,ZkClient zkc,String hostAndPorts,String  service,List<EcmAppIntant> earlyUpgList){
			this.registry = registry;
			this.service = service;
			this.earlyUpgList = earlyUpgList;
			this.zkc = zkc;
			this.hostAndPorts = hostAndPorts;
		}
		@Override
		public void run() {
			String provider_path ="/"+DubboUtil.DUBBO_DIR_NAME+"/"+service+"/"+DubboUtil.PROVIDER_DIR_NAME;
			log.info("zkc="+zkc+"provider_path ="+provider_path);
			if(zkc.exists(provider_path)&&zkc.countChildren(provider_path)>0){//如果存在提供者目录，并且有提供者
				List<String> providerList =zkc.getChildren(provider_path);//获取提供者列表
				String providerUrl = getAppProviderUrl(providerList,hostAndPorts);
				if(providerUrl!=null){
					for(EcmAppIntant intant:earlyUpgList){//注 册先升级应用实例路由规则
						registry.register(IPRouterUrlProvider.createAppIntantRouter(service, intant, providerUrl));//注册路由规则
					}
				}
			}
		}
	}
	//先升级应用实例启动后注册应用实例路由规则
	private class EarlySecondAppRuleThread implements Runnable{
		Registry registry ;
		String   service;
		ZkClient zkc;
		String hostAndPorts;
		List<EcmAppIntant> earlyUpgList;//对应appId的先升级实例
		EarlySecondAppRuleThread(Registry registry,ZkClient zkc,String hostAndPorts,String  service,List<EcmAppIntant> earlyUpgList){
			this.registry = registry;
			this.service = service;
			this.earlyUpgList = earlyUpgList;
			this.zkc = zkc;
			this.hostAndPorts = hostAndPorts;
		}
		@Override
		public void run() {
			String provider_path ="/"+DubboUtil.DUBBO_DIR_NAME+"/"+service+"/"+DubboUtil.PROVIDER_DIR_NAME;
			String router_path ="/"+DubboUtil.DUBBO_DIR_NAME+"/"+service+"/"+DubboUtil.ROUTER_DIR_NAME;
			if(zkc.exists(provider_path)&&zkc.countChildren(provider_path)>0){//如果存在提供者目录，并且有提供者
				List<String> providerList =zkc.getChildren(provider_path);//获取提供者列表
				String providerUrl = getAppProviderUrl(providerList, hostAndPorts);
				//提供者列表中存在先升级实例的IP和端口 的提供者 并且满足 不存在路由规则目录 或 路由规则目录下未有规则---只给启动后的新服务注册规则
				if(providerUrl!=null&&(!zkc.exists(router_path)||zkc.countChildren(router_path)==0)){
					for(EcmAppIntant intant:earlyUpgList){//注 册先升级应用实例路由规则
						registry.register(IPRouterUrlProvider.createAppIntantRouter(service,intant,providerUrl));//注册路由规则
					}
				}
			}
		}
	}


	//后升级部署前切换应用实例路由规则，此处会解注先升级应用实例的IP和端口，会注册后升级应用实例的IP和端口
	private class SwithAppRuleThread implements Runnable{
		Registry registry ;
		String   service;
		ZkClient zkc;
		String hostAndPorts;
		List<EcmAppIntant> lateUpgList;//对应appId的后升级实例
		List<EcmAppIntant> earlyUpgList;//对应appId的先升级实例
		SwithAppRuleThread(Registry registry,ZkClient zkc,String hostAndPorts,String  service,List<EcmAppIntant> lateUpgList,List<EcmAppIntant> earlyUpgList){
			this.registry = registry;
			this.service = service;
			this.lateUpgList = lateUpgList;
			this.zkc = zkc;
			this.hostAndPorts = hostAndPorts;
			this.earlyUpgList = earlyUpgList;
		}
		@Override
		public void run() {
			String provider_path ="/"+DubboUtil.DUBBO_DIR_NAME+"/"+service+"/"+DubboUtil.PROVIDER_DIR_NAME;
			if(zkc.exists(provider_path)&&zkc.countChildren(provider_path)>0){//如果存在提供者目录，并且有提供者
				List<String> providerList =zkc.getChildren(provider_path);//获取提供者列表
				String providerUrl = getAppProviderUrl(providerList, hostAndPorts);
				//提供者列表中存在先升级实例的IP和端口 的提供者 并且满足 不存在路由规则目录 或 路由规则目录下未有规则---只给启动后的新服务注册规则
				if(providerUrl!=null){
					for(EcmAppIntant intant:earlyUpgList){//解注先升级应用实例路由规则
						registry.unregister(IPRouterUrlProvider.createAppIntantRouter(service,intant,providerUrl));//解注路由规则
					}
					for(EcmAppIntant intant:lateUpgList){//注册后升级应用实例路由规则
						registry.register(IPRouterUrlProvider.createAppIntantRouter(service,intant,providerUrl));//注册路由规则
					}
				}
			}
		}
	}
	//解注先升级应用实例或者后升级应用实例的IP和端口
	private class ClearAppRuleThread implements Runnable{
		Registry registry ;
		String   service;
		ZkClient zkc;
		String hostAndPorts;
		List<EcmAppIntant> upgList;//对应appId的先升级或者后升级实例
		ClearAppRuleThread(Registry registry,ZkClient zkc,String hostAndPorts,String  service,List<EcmAppIntant> upgList){
			this.registry = registry;
			this.service = service;
			this.upgList = upgList;
			this.zkc = zkc;
			this.hostAndPorts = hostAndPorts;
		}
		@Override
		public void run() {
			String provider_path ="/"+DubboUtil.DUBBO_DIR_NAME+"/"+service+"/"+DubboUtil.PROVIDER_DIR_NAME;
			if(zkc.exists(provider_path)&&zkc.countChildren(provider_path)>0){//如果存在提供者目录，并且有提供者
				List<String> providerList =zkc.getChildren(provider_path);//获取提供者列表
				String providerUrl = getAppProviderUrl(providerList, hostAndPorts);
				//提供者列表中存在先升级实例的IP和端口 的提供者 并且满足 不存在路由规则目录 或 路由规则目录下未有规则---只给启动后的新服务注册规则
				if(providerUrl!=null){
					for(EcmAppIntant intant:upgList){
						registry.unregister(IPRouterUrlProvider.createAppIntantRouter(service,intant,providerUrl));//解注路由规则
					}
				}
			}
		}
	}

}

