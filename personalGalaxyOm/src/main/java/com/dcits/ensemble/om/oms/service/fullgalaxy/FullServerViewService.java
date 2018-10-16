package com.dcits.ensemble.om.oms.service.fullgalaxy;


import com.alibaba.fastjson.JSON;
import com.dcits.ensemble.om.oms.manager.kafka.KafkaIContainer;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareKafkaint;
import com.dcits.ensemble.om.oms.service.middleware.EcmMidwareKafkaintService;
import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.dao.utils.OMSIDao;

import com.dcits.ensemble.om.oms.manager.app.IContainer;
import com.dcits.ensemble.om.oms.manager.db.DBContainer;
import com.dcits.ensemble.om.oms.manager.redis.RedisIContainer;
import com.dcits.ensemble.om.oms.manager.server.AbstractedMonitorServer;
import com.dcits.ensemble.om.oms.manager.server.IMonitorServer;
import com.dcits.ensemble.om.oms.manager.server.MonitorServerFactory;
import com.dcits.ensemble.om.oms.manager.zookeeper.ZkContainer;
import com.dcits.ensemble.om.oms.module.app.EcmAppIntant;
import com.dcits.ensemble.om.oms.module.fullgalaxy.FullServerViewInfo;
import com.dcits.ensemble.om.oms.module.fullgalaxy.ServerUnitInfo;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareDbint;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareRedisint;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareZkint;
import com.dcits.ensemble.om.oms.module.server.EcmServerInfo;
import com.dcits.ensemble.om.oms.module.utils.ContainerCheckResult;
import com.dcits.ensemble.om.oms.service.middleware.FullDataBaseIntantService;
import com.dcits.ensemble.om.oms.service.middleware.FullRedisIntantService;
import com.dcits.ensemble.om.oms.service.middleware.FullZookeeperIntantService;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReadWriteLock;
import com.dcits.ensemble.om.oms.common.DataUtil;
import com.dcits.ensemble.om.oms.common.ProgressMessageUtil;
/**
 * 全景视图Service* 
 * @author tangxlf
 * @date 2015-12-18
 */
@Service
public class FullServerViewService {
	private  final Logger log = LoggerFactory.getLogger(this.getClass());
   
	@Resource
	private OMSIDao omsBaseDao;
	@Resource
	ParamComboxService paramComboxService;
	@Resource
	IContainer appContainer;
	@Resource
	RedisIContainer redisIContainer;
	@Resource
	ZkContainer zkContainer;
	@Resource
	DBContainer dBContainer;
	@Resource
	KafkaIContainer kafkaIContainer;
	@Resource
	MonitorServerFactory monitorServerFactory;
	@Resource
	FullRedisIntantService fullRedisIntantService;
	@Resource
	EcmMidwareKafkaintService kafkaintService;
	@Resource
	FullZookeeperIntantService fullZookeeperIntantService;
	@Resource
	FullDataBaseIntantService fullDataBaseIntantService;
	@Resource
	FullServerIndexService fullServerIndexService;
	//private static final String[] APP_COLORS = new String[]{"#00FF00","#00FFFF","#999999","#C67171","#BF3EFF",
	//	                                                "#CAE1FF","#C0FF3E","#B0E0E6","#8B8B00","#7CCD7C"};
	private static final String[] APP_COLORS = new String[]{"#4ad1dd","#127fbc","#d82039","#fc6021","#34346d",
        "#0067ee","#6abf40","#5f52a0","#996c33","#920783"};
	private static final Map<Integer,Integer> APP_COLOR_MAP = new HashMap<Integer,Integer>();
	private static final Map<String,Integer> REDIS_COLOR_MAP = new HashMap<String,Integer>();
	private static final Map<Integer,Integer> ZOOKEEPER_COLOR_MAP = new HashMap<Integer,Integer>();
	private static final Map<Integer,Integer> DATABASAE_COLOR_MAP = new HashMap<Integer,Integer>();
	private static final Map<Integer,Integer> KAFKA_COLOR_MAP = new HashMap<Integer,Integer>();
	private String isAutoStartApp;
	//为每一个应用实例自动重启时设置一个锁，当应用实例正在自动重启时不再自动重启
	private static final Map<Integer,ReadWriteLock> AUTO_START_APP_LOCK_MAP = new HashMap<Integer,ReadWriteLock>();
	/**
     * 查询所有服务器以及服务器上的实例	
     * @param	  String userId  用户ID
     * @return	  List<EcmAppIntant>   
     */    
	public List<FullServerViewInfo> findFullServerView(String userId,String isAutoStartApp) {
		fullServerIndexService.startCollSeverIndex();
		this.isAutoStartApp = isAutoStartApp;
		List<FullServerViewInfo>  serverViews = new ArrayList<FullServerViewInfo>();
		List<EcmServerInfo>  servers = findFullServer(userId);
		List<EcmAppIntant> intants = findFullAppIntant(userId);
		List<EcmMidwareRedisint> redisIntants = findFullRedisIntant(userId);
		List<EcmMidwareZkint>    zkIntants = findFullZkIntant(userId);
		List<EcmMidwareDbint>    dbIntants = findFullDatabaseIntant(userId);
		//List<EcmMidwareKafkaint> kafkaIntants = findFullKafkaIntant(userId);//暂时不上kafka中间件
		Map<Integer,List<EcmAppIntant>> serverIntantListMap =splitIntantByServer(intants);
		Map<Integer,List<EcmMidwareRedisint>> serverRedisIntantListMap = splitRedisIntantByServer(redisIntants);
		Map<Integer,List<EcmMidwareZkint>>  serverZkIntantListMap = splitZookeeperIntantByServer(zkIntants);
		Map<Integer,List<EcmMidwareDbint>>  serverDbIntantListMap = splitDatabaseIntantByServer(dbIntants);
		//Map<Integer,List<EcmMidwareKafkaint>>  serverKafkaIntantListMap = splitKafkaIntantByServer(kafkaIntants);
		CountDownLatch latch = new CountDownLatch(servers.size());//多线程实现
		for(EcmServerInfo serverInfo:servers){
			new SingelServerViewThread(serverViews,serverInfo,serverIntantListMap.get(serverInfo.getSerId()),
					serverRedisIntantListMap.get(serverInfo.getSerId()),serverZkIntantListMap.get(serverInfo.getSerId()),
					serverDbIntantListMap.get(serverInfo.getSerId()),latch).start();
		}
		try {
			latch.await();
		} catch (InterruptedException e) {			
			throw new GalaxyException(e.getMessage());
		}		
		log.info(JSON.toJSONString(serverViews));
    	return serverViews;
    }
	
	
    //查询所有服务器
	private List<EcmServerInfo> findFullServer(String userId){
		return omsBaseDao.findPageByCond(new EcmServerInfo(),1,50);
	}
	
	//查询所有应用实例信息
	private List<EcmAppIntant> findFullAppIntant(String userId){
		EcmAppIntant intant = new EcmAppIntant();
		return omsBaseDao.findListByCond(intant);
	}
	
	//查询所有Redis实例信息
	private List<EcmMidwareRedisint> findFullRedisIntant(String userId){
		return fullRedisIntantService.findFullRedisIntant();
	}
	
	//查询所有Zookeeper实例信息
	private List<EcmMidwareZkint> findFullZkIntant(String userId){
		return fullZookeeperIntantService.findFullZookeeperIntant();
	}
		
	//查询所有Database实例信息
	private List<EcmMidwareDbint> findFullDatabaseIntant(String userId){
		return fullDataBaseIntantService.findFullDataBaseIntant();
	}

	//查询所有Kafka实例信息
	private List<EcmMidwareKafkaint> findFullKafkaIntant(String userId){
		return kafkaintService.findAllKafkaIntants();
	}


	//按照IP地址分割应用实例
	private Map<Integer,List<EcmAppIntant>> splitIntantByServer(List<EcmAppIntant> intantList){
		Map<Integer,List<EcmAppIntant>>  serverIntantListMap = new HashMap<Integer,List<EcmAppIntant>>();
		AUTO_START_APP_LOCK_MAP.clear();
		for(EcmAppIntant intant :intantList){
			AUTO_START_APP_LOCK_MAP.put(intant.getAppIntantId(),new ReentrantReadWriteLock());
			Integer serId = intant.getSerId();
			if(serverIntantListMap.containsKey(serId)){
				serverIntantListMap.get(serId).add(intant);
			}else{
				List<EcmAppIntant> serverIntantList = new ArrayList<EcmAppIntant>();
				serverIntantList.add(intant);
				serverIntantListMap.put(serId,serverIntantList);
			}
		}
		return serverIntantListMap;
	}
	//按照IP地址分割Zookeeper实例
	private Map<Integer,List<EcmMidwareZkint>> splitZookeeperIntantByServer(List<EcmMidwareZkint> intantList){
		 Map<Integer,List<EcmMidwareZkint>>  serverIntantListMap = new HashMap<Integer,List<EcmMidwareZkint>>();
		 for(EcmMidwareZkint intant :intantList){
			 Integer serId = intant.getSerId();
			 if(serverIntantListMap.containsKey(serId)){
				 serverIntantListMap.get(serId).add(intant);
			 }else{
				 List<EcmMidwareZkint> serverIntantList = new ArrayList<EcmMidwareZkint>();
				 serverIntantList.add(intant);
				 serverIntantListMap.put(serId,serverIntantList);
			 }
		 }
		 return serverIntantListMap;
	}
	//按照IP地址分割Database实例
	private Map<Integer,List<EcmMidwareDbint>> splitDatabaseIntantByServer(List<EcmMidwareDbint> intantList){
		 Map<Integer,List<EcmMidwareDbint>>  serverIntantListMap = new HashMap<Integer,List<EcmMidwareDbint>>();
		 for(EcmMidwareDbint intant :intantList){
			 Integer serId = intant.getSerId();
			 if(serverIntantListMap.containsKey(serId)){
				serverIntantListMap.get(serId).add(intant);
			 }else{
				List<EcmMidwareDbint> serverIntantList = new ArrayList<EcmMidwareDbint>();
				serverIntantList.add(intant);
				serverIntantListMap.put(serId, serverIntantList);
			 }
		 }
		 return serverIntantListMap;
	 }
	//按照IP地址分割kafka实例
	private Map<Integer,List<EcmMidwareKafkaint>> splitKafkaIntantByServer(List<EcmMidwareKafkaint> kafkaList) {
		Map<Integer,List<EcmMidwareKafkaint>>  serverIntantListMap = new HashMap<Integer,List<EcmMidwareKafkaint>>();
		for(EcmMidwareKafkaint intant :kafkaList) {
			Integer serId =  intant.getSerId();
			if(serverIntantListMap.containsKey(serId)) {
              serverIntantListMap.get(serId).add(intant);
			}else {
				List<EcmMidwareKafkaint> serverIntantList = new ArrayList<EcmMidwareKafkaint>();
				serverIntantList.add(intant);
				serverIntantListMap.put(serId,serverIntantList);
			}
		}
		return serverIntantListMap;
	}
	//按照IP地址分割Redis实例
	private Map<Integer,List<EcmMidwareRedisint>> splitRedisIntantByServer(List<EcmMidwareRedisint> intantList){
			Map<Integer,List<EcmMidwareRedisint>>  serverIntantListMap = new HashMap<Integer,List<EcmMidwareRedisint>>();
			for(EcmMidwareRedisint intant :intantList){
				Integer serId = intant.getSerId();
				if(serverIntantListMap.containsKey(serId)){
					serverIntantListMap.get(serId).add(intant);
				}else{
					List<EcmMidwareRedisint> serverIntantList = new ArrayList<EcmMidwareRedisint>();
					serverIntantList.add(intant);
					serverIntantListMap.put(serId,serverIntantList);
				}
			}
			return serverIntantListMap;
	}
	//设置server展现内容
	private FullServerViewInfo setServerViewInfo(EcmServerInfo serverInfo){
		FullServerViewInfo serverView = new FullServerViewInfo();
		serverView.setUnitId(serverInfo.getSerId());
		serverView.setName(serverInfo.getSerIp());		
		serverView.setUnitType(SysConfigConstants.INTANT_TYPE_SERVER);
		serverView.setImageUrl(paramComboxService.getParaRemark1(SysConfigConstants.INTANT_TYPE_SERVER));
		serverView.addInfo("服务器名称",serverInfo.getSerName());
		serverView.addInfo("操作系统",paramComboxService.getParaName(serverInfo.getSerOs()));		
		try{
		  IMonitorServer monitorServer = monitorServerFactory.getIMonitorServer(serverInfo.getSerOs());
		  Map<String,String>  serverInfoMap =monitorServer.executeMonitor(serverInfo);
		  serverView.addInfo("cpu",serverInfoMap.get(AbstractedMonitorServer.CPU_MARK));
		  serverView.addInfo("内存",serverInfoMap.get(AbstractedMonitorServer.MEM_MARK));
		  serverView.addInfo("IO",serverInfoMap.get(AbstractedMonitorServer.IO_MARK));
		  serverView.addInfo("网络",serverInfoMap.get(AbstractedMonitorServer.NET_MARK));
		  serverView.setStatusCode(SysConfigConstants.HEALTH_STATUS_GREEN);
		  serverView.setStatusImage(paramComboxService.getParaRemark1(SysConfigConstants.HEALTH_STATUS_GREEN));
		}catch(Exception e){
			log.error(DataUtil.printErrorStack(e));
		  serverView.setStatusCode(SysConfigConstants.HEALTH_STATUS_RED);
		  serverView.setStatusImage(paramComboxService.getParaRemark1(SysConfigConstants.HEALTH_STATUS_RED));
		}		
		return serverView;
	}
	
	//设置app展现内容
	private ServerUnitInfo setAppViewInfo(EcmAppIntant intant){
		ServerUnitInfo appUnit = new ServerUnitInfo();
		appUnit.setUnitId(intant.getAppIntantId());
		appUnit.setName(intant.getAppIntantName());
		appUnit.setUnitType(SysConfigConstants.INTANT_TYPE_APP);
		appUnit.setBgColor(getAppColor(intant));
		appUnit.setImageUrl(getAppIcon(intant));		
		appUnit.addInfo("最新版本号",""+(intant.getAppIntantVer()==null?"":intant.getAppIntantVer()));
		appUnit.addInfo("最新操作",paramComboxService.getParaName(intant.getAppIntantStatus()));
		if(!intant.getAppIntantStatus().equals(SysConfigConstants.APP_INTANTSTATUS_NODEPLOY)){
			ContainerCheckResult result = appContainer.checkContainerStatus(intant,null);
			appUnit.addInfo("工作目录",intant.getAppWork());
			appUnit.addInfo("安装路径",intant.getAppPath());
			appUnit.addInfo("健康信息",result.getMessage());			
			String statusCode = createStatusCode(result.getResultNum());
			appUnit.setStatusCode(statusCode);
			appUnit.setStatusImage(paramComboxService.getParaRemark1(statusCode));
			//当isAutoStartApp不为空并且设置为自动启动，并且实例最新操作是启动，实例最新状态为停止时自动重启
			if(!DataUtil.isNull(isAutoStartApp)&&isAutoStartApp.equals(SysConfigConstants.IS_DEFAULT)
					&&intant.getAppIntantStatus().equals(SysConfigConstants.APP_INTANTSTATUS_START)
					&&result.getCheckStatus().equals(SysConfigConstants.APP_INTANTSTATUS_STOP)){
				new AppIntantAutoStartThread(intant,AUTO_START_APP_LOCK_MAP.get(intant.getAppIntantId())).start();
			}
		}else{			
			String statusCode = createStatusCode(1);
			appUnit.setStatusCode(statusCode);
			appUnit.setStatusImage(paramComboxService.getParaRemark1(statusCode));
		}
		return appUnit;
	}
	
	//设置Redis展现内容
	private ServerUnitInfo setRedisViewInfo(EcmMidwareRedisint intant){
			ServerUnitInfo redisUnit = new ServerUnitInfo();
			redisUnit.setUnitId(intant.getRedisintId());	
			redisUnit.setName(intant.getRedisintName());
			redisUnit.setUnitType(SysConfigConstants.INTANT_TYPE_REDIS);
			redisUnit.setBgColor(getRedisColor(intant));
			redisUnit.setImageUrl(getRedisIcon(intant));	
			redisUnit.addInfo("集群名称", intant.getMidwareName());
			redisUnit.addInfo("实例类型", paramComboxService.getParaName(intant.getRedisintType()));
			redisUnit.addInfo("节点数", "" + (intant.getRedisintNodeNum() == null ? "" : intant.getRedisintNodeNum()));
			redisUnit.addInfo("分配内存",""+(intant.getRedisintMemory()==null?"":intant.getRedisintMemory()));			
			redisUnit.addInfo("最新操作",paramComboxService.getParaName(intant.getRedisintStatus()));			
			if(!intant.getRedisintStatus().equals(SysConfigConstants.APP_INTANTSTATUS_NODEPLOY)){
				ContainerCheckResult result = redisIContainer.checkContainerStatus(intant,null);
				redisUnit.addInfo("安装路径",intant.getMidwarePath());
				redisUnit.addInfo("健康信息",result.getMessage());			
				String statusCode = createStatusCode(result.getResultNum());
				redisUnit.setStatusCode(statusCode);
				redisUnit.setStatusImage(paramComboxService.getParaRemark1(statusCode));
			}else{			
				String statusCode = createStatusCode(1);
				redisUnit.setStatusCode(statusCode);
				redisUnit.setStatusImage(paramComboxService.getParaRemark1(statusCode));
			}
			return redisUnit;
	}
	//设置Zookeeper展现内容
	private ServerUnitInfo setZookeeperViewInfo(EcmMidwareZkint intant){
			ServerUnitInfo zkUnit = new ServerUnitInfo();
			zkUnit.setUnitId(intant.getZkintId());	
			zkUnit.setName(intant.getZkintName());
			zkUnit.setUnitType(SysConfigConstants.INTANT_TYPE_ZOOKEEPER);
			zkUnit.setBgColor(getZkColor(intant));
			zkUnit.setImageUrl(getZkIcon(intant));	
			zkUnit.addInfo("集群名称", intant.getMidwareName());
			zkUnit.addInfo("节点数", "" + intant.getZkintNodeNum());
			zkUnit.addInfo("客户端口",""+intant.getZkintClientPort());			
			zkUnit.addInfo("最新操作",paramComboxService.getParaName(intant.getZkintStatus()));			
			if(!intant.getZkintStatus().equals(SysConfigConstants.APP_INTANTSTATUS_NODEPLOY)){
				ContainerCheckResult result = zkContainer.checkContainerStatus(intant,null);
				zkUnit.addInfo("安装路径",intant.getMidwarePath());
				zkUnit.addInfo("健康信息",result.getMessage());			
				String statusCode = createStatusCode(result.getResultNum());
				zkUnit.setStatusCode(statusCode);
				zkUnit.setStatusImage(paramComboxService.getParaRemark1(statusCode));
			}else{			
				String statusCode = createStatusCode(1);
				zkUnit.setStatusCode(statusCode);
				zkUnit.setStatusImage(paramComboxService.getParaRemark1(statusCode));
			}
			return zkUnit;
	}
	//设置Database展现内容
	private ServerUnitInfo setDatabaseViewInfo(EcmMidwareDbint intant){
			ServerUnitInfo dbUnit = new ServerUnitInfo();
			dbUnit.setUnitId(intant.getDbintId());
			dbUnit.setName(intant.getDbintName());
			dbUnit.setUnitType(SysConfigConstants.INTANT_TYPE_DATABASE);
			dbUnit.setBgColor(getDataBaseColor(intant));
			dbUnit.setImageUrl(getDataBaseIcon(intant));
			dbUnit.addInfo("集群名称", intant.getMidwareName());
			dbUnit.addInfo("节点数", "" + intant.getDbintNodeNum());
			dbUnit.addInfo("数据库类型", paramComboxService.getParaName(intant.getDbType()));
			dbUnit.addInfo("端口", intant.getDbintPort());
			dbUnit.addInfo("用户名", intant.getDbintUserName());
			dbUnit.addInfo("服务名", intant.getDbintServiceName());
			ContainerCheckResult result = dBContainer.checkContainerStatus(intant,null);
			dbUnit.addInfo("安装路径",intant.getMidwarePath());
			dbUnit.addInfo("健康信息",result.getMessage());
			String statusCode = createStatusCode(result.getResultNum());
			dbUnit.setStatusCode(statusCode);
			dbUnit.setStatusImage(paramComboxService.getParaRemark1(statusCode));
			return dbUnit;
	}
	//设置Kafka展现内容
	private ServerUnitInfo setKafkaViewInfo(EcmMidwareKafkaint intant){
		ServerUnitInfo kafkaUnit = new ServerUnitInfo();
		kafkaUnit.setUnitId(intant.getKafkaintId());
		kafkaUnit.setName(intant.getKafkaintName());
		kafkaUnit.setUnitType(SysConfigConstants.INTANT_TYPE_KAFKA);
		kafkaUnit.setBgColor(getKafkaColor(intant));
		kafkaUnit.setImageUrl(getKafkaIcon(intant));
		kafkaUnit.addInfo("BrokerId", "" + intant.getKafkaintId());
		kafkaUnit.addInfo("集群名称",intant.getMidwareName());
		kafkaUnit.addInfo("端口", intant.getKafkaintPort());
		kafkaUnit.addInfo("最新操作",paramComboxService.getParaName(intant.getKafkaintStatus()));
		ContainerCheckResult result = kafkaIContainer.checkContainerStatus(intant,null);
		kafkaUnit.addInfo("安装路径",intant.getMidwarePath());
		if(!intant.getKafkaintStatus().equals(SysConfigConstants.APP_INTANTSTATUS_NODEPLOY)) {
			kafkaUnit.addInfo("健康信息",result.getMessage());
			String statusCode = createStatusCode(result.getResultNum());
			kafkaUnit.setStatusCode(statusCode);
			kafkaUnit.setStatusImage(paramComboxService.getParaRemark1(statusCode));
		}else {
			String statusCode = createStatusCode(1);
			kafkaUnit.setStatusCode(statusCode);
			kafkaUnit.setStatusImage(paramComboxService.getParaRemark1(statusCode));
		}
		return kafkaUnit;
	}

	//产生健康状态
	private String createStatusCode(int resultNum){
		if(resultNum==0){
			return SysConfigConstants.HEALTH_STATUS_GREEN;
		}
        if(resultNum==1){
        	return SysConfigConstants.HEALTH_STATUS_RED;
		}
        return SysConfigConstants.HEALTH_STATUS_RED;
	}
	//设置应用颜色
	private String getAppColor(EcmAppIntant intant){
			    if(!APP_COLOR_MAP.containsKey(intant.getAppId())){
			    	APP_COLOR_MAP.put(intant.getAppId(),APP_COLOR_MAP.size());
			    }
				return APP_COLORS[APP_COLOR_MAP.get(intant.getAppId())];
	}
	//设置应用图标
	private String getAppIcon(EcmAppIntant intant){
		String iconUrl =paramComboxService.getParaRemark1(SysConfigConstants.INTANT_TYPE_APP);
		return iconUrl.replace(".",(APP_COLOR_MAP.get(intant.getAppId())+1)+".");
	}
	//设置Redis颜色
	private String getRedisColor(EcmMidwareRedisint intant){
				 if(!REDIS_COLOR_MAP.containsKey(createRedisColorPK(intant))){
				    REDIS_COLOR_MAP.put(createRedisColorPK(intant),REDIS_COLOR_MAP.size());
				 }
				 return APP_COLORS[REDIS_COLOR_MAP.get(createRedisColorPK(intant))];
	}
	
	//设置Redis图标
	private String getRedisIcon(EcmMidwareRedisint intant){
			String iconUrl =paramComboxService.getParaRemark1(SysConfigConstants.INTANT_TYPE_REDIS);
			return iconUrl.replace(".",(REDIS_COLOR_MAP.get(createRedisColorPK(intant))+1)+".");
	}
	
	
	//设置Redis颜色主键
	private String createRedisColorPK(EcmMidwareRedisint intant){
		if(intant.getRedisintNodeNum()==null){
			return intant.getMidwareId()+"_100";
		}
		return intant.getMidwareId()+"_"+intant.getRedisintNodeNum();
	}
	
	//设置zookeeper颜色
	private String getZkColor(EcmMidwareZkint intant){
		if(!ZOOKEEPER_COLOR_MAP.containsKey(intant.getMidwareId())){
			ZOOKEEPER_COLOR_MAP.put(intant.getMidwareId(),APP_COLOR_MAP.size());
		}
		return APP_COLORS[ZOOKEEPER_COLOR_MAP.get(intant.getMidwareId())];
	}
	//设置zookeeper图标
	private String getZkIcon(EcmMidwareZkint intant){
		String iconUrl =paramComboxService.getParaRemark1(SysConfigConstants.INTANT_TYPE_ZOOKEEPER);
		return iconUrl.replace(".",(ZOOKEEPER_COLOR_MAP.get(intant.getMidwareId())+1)+".");
	}
	//设置DataBase颜色
	private String getDataBaseColor(EcmMidwareDbint intant){
		if(!DATABASAE_COLOR_MAP.containsKey(intant.getMidwareId())){
			DATABASAE_COLOR_MAP.put(intant.getMidwareId(),APP_COLOR_MAP.size());
		}
		return APP_COLORS[DATABASAE_COLOR_MAP.get(intant.getMidwareId())];
	}
	//设置DataBase图标
	private String getDataBaseIcon(EcmMidwareDbint intant){
		String iconUrl =paramComboxService.getParaRemark1(SysConfigConstants.INTANT_TYPE_DATABASE);
		return iconUrl.replace(".",(DATABASAE_COLOR_MAP.get(intant.getMidwareId())+1)+".");
	}

	//设置Kafka颜色
	private String getKafkaColor(EcmMidwareKafkaint intant){
		if(!KAFKA_COLOR_MAP.containsKey(intant.getMidwareId())){
			KAFKA_COLOR_MAP.put(intant.getMidwareId(),KAFKA_COLOR_MAP.size());
		}
		return APP_COLORS[KAFKA_COLOR_MAP.get(intant.getMidwareId())];
	}
	//设置Kafka图标
	private String getKafkaIcon(EcmMidwareKafkaint intant){
		String iconUrl =paramComboxService.getParaRemark1(SysConfigConstants.INTANT_TYPE_KAFKA);
		return iconUrl.replace(".",(KAFKA_COLOR_MAP.get(intant.getMidwareId())+1)+".");
	}
	
	private class SingelServerViewThread extends Thread{
		  private EcmServerInfo serverInfo;
		  private CountDownLatch latch;
		  private List<FullServerViewInfo>  serverViews;
		  private List<EcmAppIntant> serverIntantList;
		  private List<EcmMidwareRedisint> serverRedisIntantList;
		  private List<EcmMidwareZkint>  serverZkIntantList;
		  private List<EcmMidwareDbint>  serverDbIntantList;
		//  private List<EcmMidwareKafkaint>  serverKafkaIntantList;

		  public SingelServerViewThread(List<FullServerViewInfo>  serverViews,EcmServerInfo serverInfo,List<EcmAppIntant> serverIntantList,
				  List<EcmMidwareRedisint> serverRedisIntantList,List<EcmMidwareZkint>  serverZkIntantList,List<EcmMidwareDbint>  serverDbIntantList,CountDownLatch latch){
			  this.serverInfo = serverInfo;
			  this.latch = latch;
			  this.serverViews = serverViews;			  
			  this.serverIntantList = serverIntantList;
			  this.serverRedisIntantList = serverRedisIntantList;
			  this.serverZkIntantList = serverZkIntantList;
			  this.serverDbIntantList = serverDbIntantList;
          //    this.serverKafkaIntantList = serverKafkaIntantList;
		  }
		  
		  public void run(){
			  FullServerViewInfo serverView = setServerViewInfo(serverInfo);
			  log.info("serId="+serverInfo.getSerId()+" intantList="+serverIntantList);
			  if(serverIntantList!=null){
				  for(EcmAppIntant intant:serverIntantList){
						serverView.addUnit(setAppViewInfo(intant));
				  }
			  }
			  if(serverRedisIntantList!=null){
				  for(EcmMidwareRedisint intant:serverRedisIntantList){
						serverView.addUnit(setRedisViewInfo(intant));
				  }
			  }
			  if(serverZkIntantList!=null){
				  for(EcmMidwareZkint intant:serverZkIntantList){
					   serverView.addUnit(setZookeeperViewInfo(intant));
				  }
			  }
			  if(serverDbIntantList!=null){
				  for(EcmMidwareDbint intant:serverDbIntantList){
					   serverView.addUnit(setDatabaseViewInfo(intant));
				  }
			  }
//			  if(serverKafkaIntantList!=null){
//				  for(EcmMidwareKafkaint intant:serverKafkaIntantList){
//					  serverView.addUnit(setKafkaViewInfo(intant));
//				  }
//			  }
			  serverViews.add(serverView);
			  latch.countDown();
		  }
	}
	//应用自动重启线程
	private class AppIntantAutoStartThread extends Thread {
		private EcmAppIntant  intant;
		private ReadWriteLock  applock;

		public AppIntantAutoStartThread(EcmAppIntant  intant,ReadWriteLock  applock){
			this.intant = intant;
			this.applock = applock;
		}

		public void run(){
			log.error("应用实例ID:"+intant.getAppIntantId()+"应用实例名称:"+intant.getAppIntantName()+" 应用自动重启!");
			Lock lock = applock.writeLock();
			if (lock.tryLock()) {
				try {
					Thread.sleep(15000);
					ProgressMessageUtil.startProgress(""+9999,intant.getSerIp(),intant.getAppIntantName());
					appContainer.startContainer(intant,""+9999);
					ProgressMessageUtil.stopProgress(""+9999);
				} catch(Exception e){
					log.error("应用实例ID:"+intant.getAppIntantId()+"应用实例名称:"+intant.getAppIntantName()+" 应用自动重启出错,错误信息为："+e.getMessage());
				}
				finally {
					lock.unlock();
				}
			}
		}
	}
}
