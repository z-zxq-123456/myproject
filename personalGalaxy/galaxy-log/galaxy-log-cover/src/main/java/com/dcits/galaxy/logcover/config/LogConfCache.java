package com.dcits.galaxy.logcover.config;




import java.util.HashMap;
import java.util.Map;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.common.utils.ConfigUtils;
import com.alibaba.dubbo.common.utils.UrlUtils;
import com.alibaba.fastjson.JSON;
import com.dcits.galaxy.logcover.common.DataUtil;
import com.dcits.galaxy.logcover.logback.UpdateLogbackConf;





/**
 * 日志配置缓存* 
 * @author tangxlf
 * @date 2016-10-08 
 */
public class LogConfCache  { 
	
	private  static EcmLogconfInfo info ;//日志配置信息
	
	private  static boolean isInit = false;//是否初始化
	
    private  static final String  LOG_CONF_INFO_NODE = "/logConfInfoNode";//该节点存放日志所有的参数信息的路径
	
	private  static final int   ZK_SESSION_TIMEOUT  = 10000;//zookeeper的链接超时时间
	
    private static final Logger log = LoggerFactory.getLogger(LogConfCache.class); 
    
    
    
	//初始化日志配置信息
	private static void initLogConfig(){
		info  =new EcmLogconfInfo();
		UpdateLogbackConf.addLogbackNode(info);//修改收集端的logback.xml文件，添加logger节点  名字为cover ，store colletion。防止日志平台的日志流入到日志平台，防止死循环
		isInit = getDataIsSuccess();
	}

    /**	
     * 获取日志配置信息*
	 * @return LogConfigInfo 日志配置信息
	 */
    public static synchronized EcmLogconfInfo getConfigInfo() {		
		if(!isInit){
			initLogConfig();
		}
		return info;
	}
    /**	
     * 获取日志平台是否采集调用链*
	 * @return 日志平台采集调用链true  日志平台不采集调用链false
	 */
    public static Boolean isCollChain(){
    	return getConfigInfo().getIsCollChain();
    }
    /**	
     * 获取日志平台是否采集业务日志*
	 * @return 日志平台采集业务日志true  日志平台不采集业务日志false
	 */
    public static Boolean isCollLog() {
    	return getConfigInfo().getIsCollLog();
	}
    /**	 
     * zk上日志配置发生变化后，改变日志配置缓存*
	 * @param EcmLogconfInfo   updateInfo  日志配置信息
	 */
    public static void  updateConfigInfo( EcmLogconfInfo updateInfo){    	    
    	    info = updateInfo;//把节点数据放到缓存中
    }

    private static void initThreadConf(EcmLogconfInfo updateInfo){    	  
    	   int annotNum = LogStoreConfInfo.getAnnotStoreThreadNum();    	   
    	   int cirNum   = LogStoreConfInfo.getCircleStoreThreadNum();
    	   int chainNum = LogStoreConfInfo.getChainStoreThreadNum();
    	   int queueLen = (annotNum+cirNum*4+chainNum*3)*updateInfo.getQueueBatchSize()*6;
    	   if(queueLen<10000){
    		   queueLen = 10000;
    	   }
    	   updateInfo.setQueueLen(queueLen);
    	   updateInfo.setCirStartLen(updateInfo.getQueueBatchSize()*cirNum*2);    	
		   updateInfo.setAnnotStartLen(updateInfo.getQueueBatchSize()*annotNum*2);
    	   updateInfo.setTceinStartLen(updateInfo.getQueueBatchSize()*chainNum*2);
    	   updateInfo.setCirStopLen(updateInfo.getQueueBatchSize()*cirNum);
    	   updateInfo.setTceinStopLen(updateInfo.getQueueBatchSize()*chainNum);
    	   updateInfo.setAnnotStopLen(updateInfo.getQueueBatchSize()*annotNum);    	 
    	   log.warn(updateInfo.toString());
    }
    /**
	 * 初始化时，访问zk获得配置信息,访问成功返回true,失败返回false;只有连接不上注册中心时返回false
	 * @param EcmLogconfInfo   updateInfo  日志配置信息
	 * @return boolean    true or  false
	 */
	private static boolean  getDataIsSuccess(){
		String router_path =LOG_CONF_INFO_NODE;//节点路径
		String address = ConfigUtils.getProperty("galaxy.registry.address");
		Map<String,String> map = new HashMap<String,String>();
		map.put("protocol", "zookeeper");
		String zkUrl = UrlUtils.parseURL(address, map).getBackupAddress();
		log.info("zkUrl地址："+zkUrl);
		try{
	    	ZkClient zk = new ZkClient(zkUrl,ZK_SESSION_TIMEOUT);//链接zookeeper
	    	subscribeDataChanges(router_path,zk);//订阅节点，不管此时注册中心是否存在该节点
	    	if (zk.exists(router_path)){
		    	String confData = zk.readData(router_path);//客户端在第一次时主动到注册中心去取数据
		    	if(null!=confData){
		    		//info = (EcmLogconfInfo) JSON.parse(confData.toString());
		    		info = JSON.parseObject(confData,EcmLogconfInfo.class);
		    		initThreadConf(info);
	    	    }
		    //	UpdateLogbackConf.updateLogbackConf(info);//修改配置文件
	    	}
	    	UpdateLogbackConf.updateLogbackConf(info);//原来调到外边是为了应对 业务应用停止后，删除ZK上的配置节点，然后重启业务应用后能够恢复logback文件
	    //	zk.close();//断开连接
	    	return true;
		 }catch(Exception e){
			 log.error("初始化时，访问zk获得配置信息:"+DataUtil.printErrorStack(e));
         }
		 return false;//如果zk连接超时，返回false，其他情况都是true
	}
	 
	/**
	 * 监控 LOG_CONF_INFO_NODE节点的数据变化
	 * @param String  router_path 订阅节点的路径
	 * @param ZkClient zk  注册中心客户端
	 */
	private  static  void  subscribeDataChanges(String  router_path,ZkClient zk){
     	zk.subscribeDataChanges(router_path, new IZkDataListener() { 
	            public void handleDataChange(String s, Object o) throws Exception { 
	            	log.info("data is change...");
	            	EcmLogconfInfo newUpdateInfo = JSON.parseObject(o.toString(), EcmLogconfInfo.class);  //把json转成javaBean对象
	            //	EcmLogconfInfo newUpdateInfo = (EcmLogconfInfo) JSON.parse(o.toString()); 
	            //	System.out.print("日志平台配置参数："+newUpdateInfo);
	            	initThreadConf(newUpdateInfo);
	            	updateConfigInfo(newUpdateInfo);
	            	UpdateLogbackConf.updateLogbackConf(newUpdateInfo);//修改配置文件
	            }  
	            public void handleDataDeleted(String s) throws Exception { 
	            	log.info("data is deleted...");
	            	EcmLogconfInfo newUpdateInfo = new EcmLogconfInfo();
	            	updateConfigInfo(newUpdateInfo);
	            	UpdateLogbackConf.updateLogbackConf(newUpdateInfo);//修改配置文件
	            }  
	     });  
    }
	
}
