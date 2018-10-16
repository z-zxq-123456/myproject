package com.dcits.ensemble.om.oms.manager.dubbo;



import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.annotation.Resource;

import com.dcits.ensemble.om.oms.manager.zookeeper.UpdateZkConf;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareZkSer;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareZkint;
import com.dcits.ensemble.om.oms.service.middleware.ZookeeperInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.registry.Registry;
import com.alibaba.dubbo.registry.RegistryFactory;

/**
 * dubbo服务启用、禁用处理类* 
 * @author tangxlf
 * @date 2016-06-14 
 */
@Component
public class DubboServiceHandler {
	private  final Logger log = LoggerFactory.getLogger(this.getClass());
	private  ExecutorService executorService ;
	private final static int  THREAD_NUM = 200;//并发线程数量	
	private static final int SERVICE_ENABLE  = 0; //dubbo服务操作--启用
	private static final int SERVICE_DISABLE = 1;//dubbo服务操作--禁用
	@Resource
	ZookeeperInfoService zookeeperInfoService;
	@Resource
	UpdateZkConf updateZkConf;
	
	
	/**
	 * dubbo服务禁用
	 * @param List<EcmMidwareZkSer> dubboServiceList	需要禁用的dubbo服务列表
	 * @param Integer               midwareId	        ZK集群ID
	 */
	public void disableDubboService(List<EcmMidwareZkSer> dubboServiceList,Integer midwareId){
		handleDubboService(dubboServiceList,midwareId,SERVICE_DISABLE);	
	}
	
	/**
	 * dubbo服务启用
	 * @param List<EcmMidwareZkSer> dubboServiceList	需要禁用的dubbo服务列表
	 * @param Integer               midwareId	        ZK集群ID
	 */
	public void enableDubboService(List<EcmMidwareZkSer> dubboServiceList,Integer midwareId){
		handleDubboService(dubboServiceList,midwareId,SERVICE_ENABLE);	
	}
	/**
	 * dubbo服务启用、禁用
	 * @param List<EcmMidwareZkSer> dubboServiceList	需要禁用的dubbo服务列表
	 * @param Integer               midwareId	        ZK集群ID
	 */
	private void handleDubboService(List<EcmMidwareZkSer> dubboServiceList,Integer midwareId,int serAction){
		System.out.println("dubboServiceList" + dubboServiceList + " midwareId=" + midwareId + "serAction"+serAction);
		List<EcmMidwareZkint>  zkList = zookeeperInfoService.findByMid(midwareId);//通过中间件查找zk实例列表
		String dubboRegistryUrl = updateZkConf.dubboRegistryUrl(zkList);
		executorService = Executors.newFixedThreadPool(THREAD_NUM);
		RegistryFactory registryFactory = 
				ExtensionLoader.getExtensionLoader(RegistryFactory.class).getAdaptiveExtension();		
		Registry registry = registryFactory.getRegistry(URL.valueOf(dubboRegistryUrl));				
		for(EcmMidwareZkSer dubboService:dubboServiceList){			
			executorService.execute(new DubboServiceActionThread(registry,dubboService,serAction));
		}	
		executorService.shutdown();	
	}
	
	//dubbo服务启用 禁用线程
	private class DubboServiceActionThread implements Runnable{
		Registry registry ;
		EcmMidwareZkSer dubboService;
		int serAction;
			
		DubboServiceActionThread(Registry registry,EcmMidwareZkSer dubboService,int serAction){
			this.registry = registry;	
			this.dubboService = dubboService;
			this.serAction = serAction;
		}
		@Override
		public void run() {	
			if(serAction==SERVICE_ENABLE){
				System.out.println();
				registry.unregister(SerControlRouterUrlProvider.createDubboServiceRouter(dubboService));//解注路由规则
			}
			if(serAction==SERVICE_DISABLE){
				registry.register(SerControlRouterUrlProvider.createDubboServiceRouter(dubboService));//注册路由规则
			}
		}
	}

}
