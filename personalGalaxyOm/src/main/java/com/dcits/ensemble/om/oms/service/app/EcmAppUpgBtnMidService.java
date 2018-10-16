package com.dcits.ensemble.om.oms.service.app;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dcits.ensemble.om.oms.dao.utils.OMSIDao;
import com.dcits.ensemble.om.oms.manager.dubbo.AppIntRouterHandler;
import com.dcits.ensemble.om.oms.manager.dubbo.ServiceRouterHandler;
import com.dcits.ensemble.om.oms.manager.dubbo.UpgAppIntantCache;
import com.dcits.ensemble.om.oms.module.app.EcmAppIntant;
import com.dcits.ensemble.om.oms.module.app.EcmAppUpginfo;
import com.dcits.ensemble.om.oms.service.utils.IService;


/**
 * 不间断升级管理Service* 
 * @author wangbinaf
 * @date 2016-04-01
 */
@Service
public class EcmAppUpgBtnMidService {
	private  final Logger log = LoggerFactory.getLogger(this.getClass());
	@Resource
	OMSIDao omsBaseDao;
	@Resource
	IService  omsBaseService;	
	@Resource
	EcmAppUpgforService ecmAppUpgforService;
	@Resource
	EcmAppIntantUpgService  ecmAppIntantUpgService;
	@Resource
	private UpgAppIntantCache upgAppIntantCache;
	@Resource
	private AppIntRouterHandler appIntRouterHandler;
	@Resource
	private ServiceRouterHandler serviceRouterHandler;
	@Resource
	EcmAppServRuleUpgService  ecmAppServRuleUpgService;
	//根据按钮更新相应的信息，部署完成	
	public void updateProcess(EcmAppUpginfo upgInfo,String  userId  ) {
		log.info("upgInfo:"+upgInfo);
		ecmAppUpgforService.updateProcess(upgInfo,  userId);
	}
	//第三步验证失败回退函数,还原部署之前的状态
	@Transactional
	public void earlyUpgBack(EcmAppUpginfo upgInfo,String  userId) {
		serviceRouterHandler.removeEarlyServiceRouterRule(upgInfo.getAppId(), userId);//清除先升级业务路由
		appIntRouterHandler.clearEarlyAppRule(upgInfo.getAppId());//清除先升级实例路由
		ecmAppUpgforService.updateFail(upgInfo,  userId);
		upgAppIntantCache.removeUpgAppInfo(upgInfo.getAppId());
	}
	//最后一步验证失败回退函数,还原部署之前的状态
	@Transactional
	public void lateUpgBack(EcmAppUpginfo upgInfo,String  userId) {
		log.info("upgInfo:"+upgInfo);
		//serviceRouterHandler.cancelLateServiceRouterRule(upgInfo.getAppId(), userId);//取消业务验证路由guize
		//appIntRouterHandler.clearLateAppRule(upgInfo.getAppId());//清除后升级实例路由
		ecmAppUpgforService.updateProcess(upgInfo,  userId);		
	}
	//先升级启动
//	public void earlyUpgStart(EcmAppUpginfo upgInfo,String  userId  ) {
//		log.info("upgInfo:"+upgInfo);
//		appIntRouterHandler.registerEarlySecondAppRule(upgInfo.getAppId());//针对先升级启动后，可能有新服务，对新服务加实例路由
//		ecmAppUpgforService.updateProcess(upgInfo,  userId);
//	}
	//后升级启动	
//	public void lateUpgStart(EcmAppUpginfo upgInfo,String   userId  ) {
//		log.info("upgInfo:"+upgInfo);
//		ecmAppUpgforService.updateProcess(upgInfo,  userId);
//	}
	//先升级上线	
	public void earlyUpgOnline(EcmAppUpginfo upgInfo,String  userId  ) {
		log.info("upgInfo:"+upgInfo);
		serviceRouterHandler.removeEarlyServiceRouterRule(upgInfo.getAppId(), userId);//清除先升级业务路由
		appIntRouterHandler.swithAppRule(upgInfo.getAppId());//实例路由切换--先升级的可访问，后升级的不可访问
		ecmAppUpgforService.updateProcess(upgInfo,  userId);
	}
	//后升级上线	
	public void lateUpgOnline(EcmAppUpginfo upgInfo,String  userId  ) {
		log.info("upgInfo:"+upgInfo);
		serviceRouterHandler.removeLateServiceRouterRule(upgInfo.getAppId(), userId);//清除后升级业务路由
		appIntRouterHandler.clearLateAppRule(upgInfo.getAppId());//清除后升级实例路由
		ecmAppUpgforService.updateSuccess(upgInfo,  userId);	
		upgAppIntantCache.removeUpgAppInfo(upgInfo.getAppId());
	}
	//第一步部署失败回滚	
	public void earlyFailBackInstant(EcmAppUpginfo upgInfo,String  userId  ) {
		log.info("upgInfo:"+upgInfo);
		appIntRouterHandler.clearEarlyAppRule(upgInfo.getAppId());//清除先升级实例路由
		boolean temp = ecmAppServRuleUpgService.isExistUpginfo(upgInfo.getAppId());//查找是否存在当前升级流
		if(temp){//如果ECM_APP_UPGINFO表存在当前升级的流程，修改版本号并修改表ECM_APP_UPGINFO
			ecmAppIntantUpgService.earlybackAppIntant(upgInfo.getAppId(),userId);
			ecmAppUpgforService.updateFail(upgInfo,  userId);
		}else{
			//如果ECM_APP_UPGINFO表不存在当前升级的流程
			ecmAppIntantUpgService.earlyFailBack(upgInfo.getAppId(),userId);
		}
		upgAppIntantCache.removeUpgAppInfo(upgInfo.getAppId());
	}
	//第四步部署失败回滚	
	public void lateFailBackInstant(EcmAppUpginfo upgInfo,String  userId  ) {
		EcmAppUpginfo ecmAppUpginfo = ecmAppServRuleUpgService.findAppUpginfo(upgInfo.getAppId());//查找正在升级的流程信息，可以拿到部署之前的版本号
		EcmAppIntant  ecmAppIntant  =new EcmAppIntant();
		ecmAppIntant.setAppId(upgInfo.getAppId());
		ecmAppIntant.setAppIntantVer(ecmAppUpginfo.getAppUpgNewverno());
		List<EcmAppIntant>  intantList=omsBaseService.findListByCond(ecmAppIntant);//拿到未部署的所有实例
		List<EcmAppIntant>  lateList=upgAppIntantCache.getAppIntLateList(upgInfo.getAppId()); //从缓存里面取后升级实例列表
		lateList.removeAll(intantList);//在后部署实例列表里面去除未部署的实例，剩余的则是需要回退的实例
		if(lateList.size()>0){//当长度不等于时，说明实例只部署版本号成功时，但整个部署操作失败，必须进行回退到部署之前
			//appIntRouterHandler.clearLateAppRule(upgInfo.getAppId());//清除后升级实例路由
		  	ecmAppIntantUpgService.lateFailBack(lateList,ecmAppUpginfo,upgInfo.getAppId(),userId);
		}	
	}		
}
