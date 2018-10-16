package com.dcits.ensemble.om.oms.service.middleware;



import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import com.dcits.ensemble.om.oms.action.utils.MenuActionUtil;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.dao.utils.OMSIDao;
import com.dcits.ensemble.om.oms.manager.dubbo.DubboServcieAppIntatNameParse;
import com.dcits.ensemble.om.oms.manager.dubbo.DubboUtil;
import com.dcits.ensemble.om.oms.manager.zookeeper.UpdateZkConf;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareZkint;
import com.dcits.ensemble.om.oms.module.sys.MenuNode;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import com.dcits.galaxy.base.exception.GalaxyException;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkTimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * DubboMinitorService* 
 * @author luolang
 * @createdate 2016-6-7
 */
@Service
public class DubboMinitorService {
	
	@Resource
	private OMSIDao omsBaseDao;
	@Resource
	ZookeeperInfoService zookeeperInfoService;
	@Resource
	UpdateZkConf updateZkConf;
	@Resource
	ParamComboxService paramComboxService;
	@Resource
	DubboServcieAppIntatNameParse dubboServcieAppIntatNameParse;

	private ExecutorService executorService ;

	private final static int  THREAD_NUM = 200;//并发线程数量
	
	private  static final Logger log = LoggerFactory.getLogger(DubboMinitorService.class);
	
	private static final String DIV_SIGN="/";//隔离符号 '/'
	
	private static final String PROVIDERS_RULE="提供者路由规则:";
	
	private static final String CONSUMERS_RULE="消费者路由规则:";

	private static final String RULE_DIV_SIGN=" || ";//路由规则隔离符
	
	private static final String DOUBLE_DIV_SIGN="//";

	Map<Integer, String> model = new HashMap<Integer, String>();

	void setMap() {
		model.put(0, "一");
		model.put(1, "二");
		model.put(2, "三");
		model.put(3, "四");
		model.put(4, "五");
		model.put(5, "六");
		model.put(6, "七");
		model.put(7, "八");
		model.put(8, "九");
		model.put(9, "十");
	}

	private String getChNumber(int number) {
		if(model.isEmpty()){
			setMap();
		}
		return model.get(number);
	}
	//根据中间件id得到集群zk的url
	private  String getZkUrl(int midwareId) {
		 List<EcmMidwareZkint>  zkList = zookeeperInfoService.findByMid(midwareId);//通过中间件查找zk实例列表
	     String zkUrl  = updateZkConf.zkUrl(zkList);//生成zk地址串
	     return zkUrl;
	}
	//得到path子路径的信息列表
	private List<String> serInfo(String path,ZkClient zkc) {
		List<String> childList = zkc.getChildren(path);
		return childList;
	}

	private Integer providesOrConsumersOrRoutersAmount(String serverName ,String obj,ZkClient zkc ) {
		String path =DIV_SIGN+paramComboxService.getParaRemark1(SysConfigConstants.DUBBO_ROOT)+DIV_SIGN+serverName+DIV_SIGN+obj;
		if(zkc.exists(path)) {
           if(zkc.countChildren(path)==0) {
			   return 0;
		   }else {
			   Integer np = zkc.countChildren(path);
               return np;
		   }
		}else return 0;
	}

	//得到ip、port信息
	private String getHostInfo(String oldInfo) {
		return  oldInfo.split(DOUBLE_DIV_SIGN)[1].split(DIV_SIGN)[0];
	}
	//得到服务名称
	private String getHostName(String oldInfo) {
		return oldInfo.split(DOUBLE_DIV_SIGN)[0]+DOUBLE_DIV_SIGN+oldInfo.split(DOUBLE_DIV_SIGN)[1].split(DIV_SIGN)[0];
	}

	/**
	 * 根据父节点的路径得到子节点的信息
	 * @param int midwareId  中间件id
	 * @param String likeServerName 搜索的server名称
	 * @return  List<Map<String,Object>>  子节点列表，以Map封装
	 */

	public List<Map<String,Object>> getDubboInfoThread (int midwareId) {
		ZkClient zkc = getZkClient(midwareId);
		String dubboPath = DIV_SIGN+paramComboxService.getParaRemark1(SysConfigConstants.DUBBO_ROOT);
		List<String> serlist = serInfo(dubboPath, zkc);
		List<Map<String,Object>> list =  Collections.synchronizedList(new ArrayList<Map<String,Object>>());//保留返回结果List
		CountDownLatch threadSignal = new CountDownLatch(serlist.size());//初始化countDown
		executorService = Executors.newFixedThreadPool(THREAD_NUM);//初始化固定大小的线程池
		for(String str:serlist) {
			executorService.execute(new DubboMinitorService.DubboMinitorActionThread(str, list, zkc, threadSignal));
		}
		try {
			threadSignal.await();//等待所有子线程执行完
			executorService.shutdown();//关闭线程池
			zkc.close();//关闭zk链接
		} catch(InterruptedException e){
			e.printStackTrace();
		}
		return list;
	}
	//获取dubbo下的节点的数据
	public class DubboMinitorActionThread implements Runnable{
		String str;
		List < Map < String, Object >>  list;
		ZkClient zkc;
		CountDownLatch threadsSignal;
		DubboMinitorActionThread(String str,List < Map < String, Object >>  list,ZkClient zkc, CountDownLatch threadsSignal){
			this.str = str;
			this.list = list;
			this.zkc = zkc;
			this.threadsSignal = threadsSignal;
		}
		@Override
		public void run() {
			List<Map<String,Object>> temp = getDubboServicesThread(str,zkc) ;
			list.addAll(temp);
			threadsSignal.countDown();//线程结束时计数器减1
		}
	}
	/**
	 * 获取dubbo下的所有子节点
	 * @param String  str  dubbo节点的具体服务名
	 * @param ZkClient  zkc   zk的客户端
	 * @return  List<Map<String,Object>>  子节点列表，以Map封装
	 */
	public   List<Map<String,Object>>  getDubboServicesThread(String str,ZkClient  zkc) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String,Object>>  list = new ArrayList<>();
		map.put("name",str);
		map.put("pNum",providesOrConsumersOrRoutersAmount(str,paramComboxService.getParaRemark1(SysConfigConstants.DUBBO_PROVIDERS),zkc));
		map.put("cNum",providesOrConsumersOrRoutersAmount(str,paramComboxService.getParaRemark1(SysConfigConstants.DUBBO_CONSUMERS),zkc));
		map.put("rNum",providesOrConsumersOrRoutersAmount(str, paramComboxService.getParaRemark1(SysConfigConstants.DUBBO_ROUTERS), zkc));
		if(providesOrConsumersOrRoutersAmount(str,paramComboxService.getParaRemark1(SysConfigConstants.DUBBO_PROVIDERS),zkc)>0) {
			map.put(paramComboxService.getParaRemark1(SysConfigConstants.DUBBO_PROVIDERS), providesOrConsumersOrRoutersInfo(str, paramComboxService.getParaRemark1(SysConfigConstants.DUBBO_PROVIDERS), zkc));
		}
		if(providesOrConsumersOrRoutersAmount(str,paramComboxService.getParaRemark1(SysConfigConstants.DUBBO_CONSUMERS),zkc)>0) {
			map.put(paramComboxService.getParaRemark1(SysConfigConstants.DUBBO_CONSUMERS), providesOrConsumersOrRoutersInfo(str, paramComboxService.getParaRemark1(SysConfigConstants.DUBBO_CONSUMERS), zkc));
		}
		if(providesOrConsumersOrRoutersAmount(str, paramComboxService.getParaRemark1(SysConfigConstants.DUBBO_ROUTERS), zkc)>0) {
			map.put(paramComboxService.getParaRemark1(SysConfigConstants.DUBBO_ROUTERS), providesOrConsumersOrRoutersInfo(str, paramComboxService.getParaRemark1(SysConfigConstants.DUBBO_ROUTERS), zkc));
		}
		list.add(map);
		return list;
	}
    //获取详细信息
	private List<Map<String,Object>> providesOrConsumersOrRoutersInfo(String serverName,String obj,ZkClient zkc) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		String dubboPath = DIV_SIGN+paramComboxService.getParaRemark1(SysConfigConstants.DUBBO_ROOT)+DIV_SIGN+serverName+DIV_SIGN+obj;
		List<String> serlist = serInfo(dubboPath, zkc);
		for(String str :serlist) {
			String newString = DubboUtil.handleDubboStr(str);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id",obj);
			map.put("address",getHostName(newString));
			if(obj.equals(paramComboxService.getParaRemark1(SysConfigConstants.DUBBO_PROVIDERS))) {
				String[] hostInfo = getHostInfo(newString).split(":");
				map.put("info",dubboServcieAppIntatNameParse.providerIpParse(hostInfo[0], hostInfo[1]));
			}
			if(obj.equals(paramComboxService.getParaRemark1(SysConfigConstants.DUBBO_CONSUMERS))) {
				String hostInfo = getHostInfo(newString);
				map.put("info", dubboServcieAppIntatNameParse.consumerIpParse(hostInfo));
			}
			if(obj.equals(paramComboxService.getParaRemark1(SysConfigConstants.DUBBO_ROUTERS))) {
				map.put("info",routerHandler(DubboUtil.stringHandler(newString).split("=>")[0],CONSUMERS_RULE)+RULE_DIV_SIGN+routerHandler(DubboUtil.stringHandler(newString).split("=>")[1],PROVIDERS_RULE));
			}
			list.add(map);
		}
		return list;
	}

	//获取zkclient
	public  ZkClient getZkClient(int midwareId) {
		try {
			ZkClient zkc = new ZkClient(getZkUrl(midwareId),5000);
			return zkc;
		}catch (ZkTimeoutException e){
			log.info("错误日志："+e.getMessage());
			throw new GalaxyException("Unable to connect to zookeeper server!");
		}
	}

	//人性化处理路由规则
	private String routerHandler(String str ,String sign) {
		if(str!=null&&str.equals("")) {
			return "";
		}else
			return sign+str;
	}

}