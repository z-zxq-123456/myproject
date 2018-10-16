package com.dcits.ensemble.om.oms.service.fullgalaxy;


import com.alibaba.dubbo.common.utils.UrlUtils;

import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.ensemble.om.oms.manager.app.PropertiesCacheService;
import com.dcits.ensemble.om.oms.manager.dubbo.DubboUtil;
import com.dcits.ensemble.om.oms.manager.zookeeper.UpdateZkConf;
import com.dcits.ensemble.om.oms.service.middleware.ZookeeperInfoService;
import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * dubbo服务检查Service* 
 * @author tangxlf
 * @date 2016-01-06
 */
@Service
public class FullDubboServiceCheckService {
	private  final Logger log = LoggerFactory.getLogger(this.getClass());	
	@Resource
	PropertiesCacheService propertiesCacheService;
	@Resource
	ZookeeperInfoService zookeeperInfoService;
	@Resource
	UpdateZkConf updateZkConf;

	private final static int  THREAD_NUM = 200;//并发线程数量

	private ExecutorService executorService ;
	/**
     * 检查所有dubbo服务	只检查ensemble内部有消费者，未有服务提供者的情况
     * @param	  String userId  用户ID
     * @param	  Integer midwareId  中间件ID  
     * @return	  List<Map<String,String>> 没有服务提供者的消费者信息   
     */    
	public List<Map<String,String>> checkService(String userId,Integer midwareId) {
		List<Map<String,String>>  noProviderCosumerList =Collections.synchronizedList(new ArrayList<Map<String, String>>());
		String zookeeprUrl = updateZkConf.zkUrl(zookeeperInfoService.findByMid(midwareId));
		if(zookeeprUrl==null){
			log.error("请检查是否没有正在运行的业务应用实例?");
			throw new GalaxyException("请检查是否没有正在运行的业务应用实例?");
		}
		ZkClient zkc = new ZkClient(zookeeprUrl,5000);
		List<String> childList =zkc.getChildren("/" + DubboUtil.DUBBO_DIR_NAME);
		CountDownLatch threadSignal = new CountDownLatch(childList.size());//初始化countDown
		executorService = Executors.newFixedThreadPool(THREAD_NUM);//初始化固定大小的线程池
		for(String dirName:childList){
			executorService.execute(new FullDubboServiceCheckService.FullDubboServiceCheckActionThread(dirName, noProviderCosumerList, zkc, threadSignal));

		}
		try {
			threadSignal.await();//等待所有子线程执行完
			executorService.shutdown();//关闭线程池
			zkc.close();
		} catch(InterruptedException e){
			e.printStackTrace();
		}
    	return noProviderCosumerList;
    }
	//获取dubbo下的节点的数据
	public class FullDubboServiceCheckActionThread implements Runnable{
		String dirName;
		List < Map < String, String >>  noProviderCosumerList;
		ZkClient zkc;
		CountDownLatch threadsSignal;
		FullDubboServiceCheckActionThread(String dirName,List < Map < String, String >>  noProviderCosumerList,ZkClient zkc, CountDownLatch threadsSignal){
			this.dirName = dirName;
			this.noProviderCosumerList = noProviderCosumerList;
			this.zkc = zkc;
			this.threadsSignal = threadsSignal;
		}
		@Override
		public void run() {
			List<Map<String,String>> temp = getDubboServicesThread(dirName,zkc) ;
			noProviderCosumerList.addAll(temp);
			threadsSignal.countDown();//线程结束时计数器减1
		}
	}
	/**
	 * 获取dubbo下的所有子节点
	 * @param String  str  dubbo节点的具体服务名
	 * @param ZkClient  zkc   zk的客户端
	 * @return  List<Map<String,Object>>  子节点列表，以Map封装
	 */
	public   List<Map<String,String>>  getDubboServicesThread(String dirName,ZkClient  zkc) {
		List<Map<String,String>>  list =new ArrayList<Map<String, String>>();
		String provider_path ="/"+ DubboUtil.DUBBO_DIR_NAME+"/"+dirName+"/"+ DubboUtil.PROVIDER_DIR_NAME;
		String consumer_path ="/"+ DubboUtil.DUBBO_DIR_NAME+"/"+dirName+"/"+ DubboUtil.CONSUMER_DIR_NAME;
		if(zkc.exists(consumer_path)&&zkc.countChildren(consumer_path)>0){
			if(!zkc.exists(provider_path)||zkc.countChildren(provider_path)==0){
				List<String> consumerList =zkc.getChildren(consumer_path);
				for(String consumer:consumerList){
					Map<String,String> cousumerMap = new HashMap<String,String>();
					String formatConsumer= DubboUtil.handleDubboStr(consumer);
					String[] firstResult =formatConsumer.split("[?]")[0].split("//");//?是正则表达式中的特殊字符，需要[?]来处理
					cousumerMap.put("dubboSide",firstResult[0].replace(":",""));
					String[] secondResult = firstResult[1].split("/");
					cousumerMap.put("cosumerIP",secondResult[0]);
					cousumerMap.put("dubboService",secondResult[1]);
					list.add(cousumerMap);
				}
			}
		}
		return list;
	}
	
	public static void main(String[] args){
		System.out.println(UrlUtils.parseURL("zookeeper://192.168.165.216:2181",null).getBackupAddress());
		System.out.println(UrlUtils.parseURL("zookeeper://192.168.165.216:2181?backup=192.168.165.217:2181,192.168.165.218:2181",null).getBackupAddress());
	}
}
