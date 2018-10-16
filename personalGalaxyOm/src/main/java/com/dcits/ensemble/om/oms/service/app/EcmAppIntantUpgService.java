package com.dcits.ensemble.om.oms.service.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.dcits.galaxy.base.exception.GalaxyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dcits.ensemble.om.oms.common.ProgressMessageUtil;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.dao.utils.OMSIDao;

import com.dcits.ensemble.om.oms.manager.app.IContainer;
import com.dcits.ensemble.om.oms.manager.app.UpgNodeCache;
import com.dcits.ensemble.om.oms.manager.dubbo.AppIntRouterHandler;
import com.dcits.ensemble.om.oms.manager.dubbo.UpgAppIntantCache;
import com.dcits.ensemble.om.oms.module.app.EcmAppIntant;
import com.dcits.ensemble.om.oms.module.app.EcmAppUpginfo;
import com.dcits.ensemble.om.oms.module.app.EcmAppVer;
import com.dcits.ensemble.om.oms.module.utils.ContainerCheckResult;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import com.dcits.dynamic.web.util.dao.PkServiceUtil;
import com.dcits.ensemble.om.oms.service.utils.IService;
import com.dcits.ensemble.om.oms.service.utils.ShellExcuteService;
import com.dcits.ensemble.om.oms.shellcmd.CmdFactory;
import com.dcits.ensemble.om.oms.shellcmd.ICmd;


/**
 * 不间断升级管理Service* 
 * @author wangbinaf
 * @date 2016-03-15
 */
@Service
public class EcmAppIntantUpgService {
	private static final String  FIND_BY_VER_SQLID = "findByVer";
	private static final String  FIND_UPG_LIST_SQLID = "findUpgList";
	private  final Logger log = LoggerFactory.getLogger(this.getClass());
	private static final String INTANT_NAME_UPG ="UPG";
	@Resource
	OMSIDao omsBaseDao;
	@Resource
	PkServiceUtil  serviceUtil;
	@Resource
	IService  omsBaseService;
	@Resource
	UpgNodeCache upgNodeCache;
	@Resource
	ShellExcuteService shellService;
	@Resource
	EcmAppUpgforService ecmAppUpgforService;
	@Resource
	IContainer appContainer;	
	@Resource
	EcmAppIntantService ecmAppIntantService;
	@Resource
	CmdFactory  cmdFactory;
	@Resource
	ParamComboxService  paramComboxService;	
	@Resource
	EcmAppServRuleUpgService  ecmAppServRuleUpgService;
	@Resource
	private UpgAppIntantCache upgAppIntantCache;
	
	@Resource
	private AppIntRouterHandler appIntRouterHandler;
	
    /**
     * 后升级全量部署所有应用实例	
     * @param	  String userId  用户ID
     * @param	  EcmAppIntant intant  实例对象  
     */   
	public void deployLateAppIntant(String userId,EcmAppIntant intant){
		 log.info("intant=:"+intant);
    	 Map<String,Object> conMap = new HashMap<String,Object>();
		 conMap.put("appId",intant.getAppId());
		 conMap.put("appUpgStatus","0027001");
		 List<EcmAppVer>  appList=omsBaseDao.findListByCond(new EcmAppVer(),FIND_BY_VER_SQLID,conMap);//通过表 ECM_APP_UPGINFO ,ECM_APP_VER ，关联APPID和版本号，返回相应的EcmAppVer对象
		 log.info("appListver="+appList);
		 if(appList.size()>0){
		    intant.setAppIntantVer(appList.get(0).getAppVerNum());//把最新的版本号set给实例版本号
		 }else{
			throw new GalaxyException("当前应用不存在最新部署版本，或者不存在当前升级的流程");
		 }
    	 //List<EcmAppIntant>  intantList=omsBaseDao.findListByCond(intant); //通过APPID和版本号去查,返回不是最新版本号的的所有实例
    	 List<EcmAppIntant>  intantList=upgAppIntantCache.getAppIntLateList(intant.getAppId()); //通过APPID和版本号去查,返回不是最新版本号的的所有实例
    	 log.info("intantList:"+intantList);
    	 for(EcmAppIntant rowIntant:intantList){
	    		 rowIntant.setNewAppIntantVer(appList.get(0).getAppVerNum());
	    		 rowIntant.setIsRemainConfig(intant.getIsRemainConfig());
	    		 ProgressMessageUtil.startProgress(""+userId,rowIntant.getSerIp(),rowIntant.getAppIntantName());
	    		 if(appList.size()>0){
	    			 rowIntant.setAppVerPath(appList.get(0).getAppVerPath());
	    			 rowIntant.setAppVerType(appList.get(0).getAppVerType());
	    			 rowIntant.setAppVerId(appList.get(0).getAppVerId());
	    		 }else{
	    			 throw new GalaxyException("应用"+rowIntant.getAppName()+"尚未有安装文件版本!");
	    		 }
	    		 //部署之前，先停掉所有实例
	    		 ContainerCheckResult result = appContainer.checkContainerStatus(rowIntant,null);
	    		 if(result.getCheckStatus()!=null&&result.getCheckStatus()!=SysConfigConstants.APP_INTANTSTATUS_STOP){
	    		     appContainer.stopContainer(rowIntant,userId);
	    			//在记录表里记录一条操作流水
		    		 ecmAppIntantService.saveStartOrStopAppIntantStatus(rowIntant,SysConfigConstants.APP_INTANTSTATUS_STOP,userId);
	    	     }
	 		     appContainer.assemContainer(rowIntant,userId);
	             ecmAppIntantService.save(rowIntant, userId);  
    	}
    	ProgressMessageUtil.stopProgress(""+userId);
	}
	 /**
     * 先升级全量部署所有应用实例	
     * @param	  String userId         用户ID
     * @param	  int demoAppIntantVer   部署的版本号
     * @param     String intantIdString  选中实例的所有Id
     * @param     String  isRemainConfig 是否保留配置文件
     * 
     */ 
	public void deployEarlyAppIntant(EcmAppIntant appIntant,String userId){
		String appIntantIdList = appIntant.getIntantIdString().substring(1, appIntant.getIntantIdString().length()-1);
		Map<String,Object> intantCond =new HashMap<String,Object>();
		intantCond.put("appIntantIdList", appIntantIdList);
		List<EcmAppIntant> upgAppIntantlist = omsBaseService.findListByCond(new EcmAppIntant(),FIND_UPG_LIST_SQLID,intantCond );
		for(EcmAppIntant intant:upgAppIntantlist) {
			    ProgressMessageUtil.startProgress(""+userId,intant.getSerIp(),intant.getAppIntantName());
				intant.setNewAppIntantVer(appIntant.getNewAppIntantVer());
				intant.setIsRemainConfig(appIntant.getIsRemainConfig());
			    log.info("111"+appIntant.getAppVerPath());
				intant.setAppVerPath(appIntant.getAppVerPath());
				intant.setAppVerType(appIntant.getAppVerType());
				intant.setAppVerId(appIntant.getAppVerId());
				ContainerCheckResult result = appContainer.checkContainerStatus(intant,null);
				if(result.getCheckStatus()!=null&&result.getCheckStatus()!=SysConfigConstants.APP_INTANTSTATUS_STOP){
				    appContainer.stopContainer(intant,""+userId);
				    //在记录表里记录一条操作流水
				    ecmAppIntantService.saveStartOrStopAppIntantStatus(intant,SysConfigConstants.APP_INTANTSTATUS_STOP,userId);	  
				}
				appContainer.assemContainer(intant,userId);
			    ecmAppIntantService.save(intant, userId);
		}
		ecmAppUpgforService.saveUpgInfo(upgAppIntantlist.get(0).getAppId(),appIntant.getNewAppIntantVer(),upgAppIntantlist.get(0).getAppIntantVer(),appIntantIdList);
	 	appIntRouterHandler.registerEarlyFirstAppRule(appIntant.getAppId());
	    ProgressMessageUtil.stopProgress(""+userId);
	}
	/**
     * 先升级应用实例启动	
     * @param	  String userId  用户ID
     * @param	  EcmAppIntant intant  实例对象  
     */  
	public void starEarlyAppIntant( EcmAppIntant  appIntant,String userId) {
		List<EcmAppIntant>  intantList=upgAppIntantCache.getAppIntEarlyList(appIntant.getAppId()); //从缓存里面取先升级实例列表
		log.info("先升级启动" + intantList);
		startAppIntantList(intantList,userId);
		appIntRouterHandler.registerEarlySecondAppRule(appIntant.getAppId());//针对先升级启动后，可能有新服务，对新服务加实例路由
    }
	/**
     * 后升级应用实例启动	
     * @param	  String userId  用户ID
     * @param	  EcmAppIntant intant  实例对象  
     */  
	public void startLateAppIntant( EcmAppIntant  appIntant,String userId) {
		List<EcmAppIntant>  intantList=upgAppIntantCache.getAppIntLateList(appIntant.getAppId()); //从缓存里面取后升级实例列表
		log.info("后升级启动"+ intantList);
		startAppIntantList(intantList,userId);
    }
	
	/**
     * 应用实例启动	
     * @param	  String userId  用户ID
     * @param	 List<EcmAppIntant> intantList  实例列表对象  
     */  
	private void startAppIntantList(List<EcmAppIntant> intantList,String userId) {
		for(EcmAppIntant rowIntant:intantList){
	   		   ProgressMessageUtil.startProgress(""+userId,rowIntant.getSerIp(),rowIntant.getAppIntantName());
	   		   ContainerCheckResult result = appContainer.checkContainerStatus(rowIntant,null);
	   		   if(result.getCheckStatus()!=null&&result.getCheckStatus()!=SysConfigConstants.APP_INTANTSTATUS_START){
	   			  appContainer.startContainer(rowIntant,""+userId);            
	   	     }	   		 
	   		 ecmAppIntantService.saveStartOrStopAppIntantStatus(rowIntant,SysConfigConstants.APP_INTANTSTATUS_START,userId);     	   
		}
		ProgressMessageUtil.stopProgress(""+userId);
	}
	
	/**
     * (第三步)验证失败回退函数,还原部署之前的状态	
     * @param	  String userId  用户ID
     * @param	  EcmAppIntant intant  实例对象  
     */  
	public void earlybackAppIntant( Integer appId,String userId) {
		List<EcmAppIntant>  intantList=upgAppIntantCache.getAppIntEarlyList(appId); //从缓存里面取先升级实例列表
		EcmAppUpginfo ecmAppUpginfo = ecmAppServRuleUpgService.findAppUpginfo(appId);//查找正在升级的流程信息，可以拿到部署之前的版本号	   
	    log.info("ecmAppUpginfo:"+ecmAppUpginfo);
	    for(EcmAppIntant appIntant:intantList){
	    	 ProgressMessageUtil.startProgress(""+userId,appIntant.getSerIp(),appIntant.getAppIntantName());
			 //回退之前，先停掉所有实例
	   		 ContainerCheckResult result = appContainer.checkContainerStatus(appIntant,null);
	   		 appIntant.setAppIntantVer(ecmAppUpginfo.getAppUpgOldverno());//重新设置版本号
	   		 if(result.getCheckStatus()!=null&&result.getCheckStatus()!=SysConfigConstants.APP_INTANTSTATUS_STOP){
	   		    appContainer.stopContainer(appIntant,""+userId);  
	   	     }
	   		rollBackIntant(appId);//应用实例回退结束第三步之部署版本回滚至BACK版本，并将部署版本置为新的BACK版本	
	   	    //在记录表里记录一条操作流水
		    ecmAppIntantService.saveStartOrStopAppIntantStatus(appIntant,SysConfigConstants.APP_INTANTSTATUS_STOP,userId);
		    appIntant.setAppIntantVer(ecmAppUpginfo.getAppUpgNewverno());//重新设置版本号，保证缓存里的是最新的版本号
		}
	    ProgressMessageUtil.stopProgress(""+userId);
	}
	 /**
	 * 最后一步验证失败回退函数,还原部署之前的状态	
	 * @param	  String userId  用户ID
	 * @param	  EcmAppIntant intant  实例对象  
	 */  
	public void latebackAppIntant( Integer appId,String userId) {
	       List<EcmAppIntant>  intantList=upgAppIntantCache.getAppIntLateList(appId); //从缓存里面取后升级实例列表
	       EcmAppUpginfo ecmAppUpginfo = ecmAppServRuleUpgService.findAppUpginfo(appId);//查找正在升级的流程信息，可以拿到部署之前的版本号
	       log.info("ecmAppUpginfo:"+ecmAppUpginfo);
	       log.info("intantList:"+intantList);
	       for(EcmAppIntant appIntant:intantList){
	    	  ProgressMessageUtil.startProgress(""+userId,appIntant.getSerIp(),appIntant.getAppIntantName());
		      //回退之前，先停掉所有实例
		      ContainerCheckResult result = appContainer.checkContainerStatus(appIntant,null);
		      if(result.getCheckStatus()!=null&&result.getCheckStatus()!=SysConfigConstants.APP_INTANTSTATUS_STOP){
		        appContainer.stopContainer(appIntant,""+userId);  
		      }
		      appIntant.setAppIntantVer(ecmAppUpginfo.getAppUpgOldverno());//重新设置版本号
		      log.info("appIntant:"+appIntant);
		      rollBackIntant(appId);//应用实例回退结束第三步之部署版本回滚至BACK版本，并将部署版本置为新的BACK版本	
		     //在记录表里记录一条操作流水
		     ecmAppIntantService.saveStartOrStopAppIntantStatus(appIntant,SysConfigConstants.APP_INTANTSTATUS_STOP,userId);
		     appIntant.setAppIntantVer(ecmAppUpginfo.getAppUpgNewverno());//重新设置版本号，保证缓存里的是最新的版本号
	       }
	       ProgressMessageUtil.stopProgress(""+userId);
 }
	/**
     * 应用实例回退结束第三步之部署版本回滚至BACK版本，并将部署版本置为新的BACK版本	
     * @param	 List<EcmAppIntant> intantList 被选择的回退实例
     */  
	private  void rollBackIntant(Integer appId) {
		List<EcmAppIntant>  intantList=upgAppIntantCache.getAppIntEarlyList(appId);
		for(EcmAppIntant intant:intantList) {
			String cmd = creatCode(intant);
			shellService.doCmd(intant.getSerIp(),intant.getSerUser(),intant.getSerPwd(),cmd);
    	}
	}
	/**
     * 构造回退第三步命令
     * @param	 EcmAppIntant intant 回退实例
     */  
	private String creatCode(EcmAppIntant intant) {
		ICmd cmdObj = cmdFactory.getCmd(intant.getSerOs());
		String intantArgs = intant.getAppPath()+"/"+intant.getAppWork();
		String cmd = cmdObj.mvCmd(intantArgs+" "+intantArgs+SysConfigConstants.DIR_LINK_SIGN+INTANT_NAME_UPG)+SysConfigConstants.SHELL_LINK_SIGN+
				     cmdObj.mvCmd(intantArgs+SysConfigConstants.DIR_LINK_SIGN+paramComboxService.getParaRemark1(SysConfigConstants.APP_BACK_SUFF_NAME)+" "+intantArgs)+SysConfigConstants.SHELL_LINK_SIGN+
				     cmdObj.mvCmd(intantArgs+SysConfigConstants.DIR_LINK_SIGN+INTANT_NAME_UPG+" "+intantArgs+SysConfigConstants.DIR_LINK_SIGN+paramComboxService.getParaRemark1(SysConfigConstants.APP_BACK_SUFF_NAME));
		return cmd;
	}
	 /**
		 * 第四步失败回退函数,还原部署之前的状态	
		 * @param	  String userId  用户ID
		 * @param	  Integer appId   应用ID  
		 * @param	  List<EcmAppIntant>  intantList intant  实例对象列表  
		 * @param	  EcmAppUpginfo   ecmAppUpginfo  升级应用信息
		 */  
	public void lateFailBack( List<EcmAppIntant>  intantList,EcmAppUpginfo ecmAppUpginfo,Integer appId,String userId) {
		 for(EcmAppIntant appIntant:intantList){
		    	ProgressMessageUtil.startProgress(""+userId,appIntant.getSerIp(),appIntant.getAppIntantName());
			    //回退之前，先停掉所有实例
			    ContainerCheckResult result = appContainer.checkContainerStatus(appIntant,null);
			    if(result.getCheckStatus()!=null&&result.getCheckStatus()!=SysConfigConstants.APP_INTANTSTATUS_STOP){
			      appContainer.stopContainer(appIntant,""+userId);  
			    }
			    appIntant.setAppIntantVer(ecmAppUpginfo.getAppUpgOldverno());//重新设置版本号
			    log.info("appIntant:"+appIntant);
			    rollBackIntant(appId);//应用实例回退结束第三步之部署版本回滚至BACK版本，并将部署版本置为新的BACK版本	
			    //在记录表里记录一条操作流水
			    ecmAppIntantService.saveStartOrStopAppIntantStatus(appIntant,SysConfigConstants.APP_INTANTSTATUS_STOP,userId);
			    appIntant.setAppIntantVer(ecmAppUpginfo.getAppUpgNewverno());//重新设置版本号，保证缓存里的是最新的版本号
		       }
		 ProgressMessageUtil.stopProgress(""+userId);
	 }
	/**
     * 第一步部署失败,还原部署之前的状态	
     * @param	  String userId  用户ID
     * @param	  Integer appId  实例对象 ID 
     */  
	public void earlyFailBack( Integer appId,String userId) {
		Map<String,Object> newNode = new HashMap<String,Object>();
		newNode.put("appId",appId);
		List<EcmAppIntant> infoList = omsBaseDao.findListByCond(new EcmAppIntant(), "findVerList", newNode);
		Integer appIntantVer = infoList.get(0).getAppIntantVer();//拿到部署的版本号
		log.info("appIntantVer="+appIntantVer);
		EcmAppIntant intant = new EcmAppIntant();
		intant.setAppId(appId);
		List<EcmAppIntant> listall =omsBaseService.findListByCond(intant);//查找APPID对应的所有实例		
		List<EcmAppIntant> earlyList = new ArrayList<EcmAppIntant>();//先部署的实例
		List<EcmAppIntant> lateList = new ArrayList<EcmAppIntant>();//未部署的实例,也就是后部署实例
		for(EcmAppIntant   appIntant : listall){
				if(appIntantVer==appIntant.getAppIntantVer()){//当实例的部署版本号等于最新一次部署的版本号时则把该实例ADD到先部署实例LIST里，否则放到后部署实例LIST里
					earlyList.add(appIntant);
				}else{
					lateList.add(appIntant);
				}
		}
		//错误的情况即：本次infoList的不是最新的，此次查出的版本号刚好相等最新部署的版本号则所有实例都在earlyList，如果不相等则所有实例都在lateList。这两种情况都不需要进行实例回退操作
		if(earlyList.size()>0&&lateList.size()>0){
		    for(EcmAppIntant appIntant:earlyList){
		    	 ProgressMessageUtil.startProgress(""+userId,appIntant.getSerIp(),appIntant.getAppIntantName());
				 //回退之前，先停掉所有实例
		   		 ContainerCheckResult result = appContainer.checkContainerStatus(appIntant,null);
		   		 if(result.getCheckStatus()!=null&&result.getCheckStatus()!=SysConfigConstants.APP_INTANTSTATUS_STOP){
		   		    appContainer.stopContainer(appIntant,""+userId); 
		   	     }
		   		appIntant.setAppIntantVer(lateList.get(0).getAppIntantVer());//重新设置版本号
		   		rollFailBack(earlyList);//应用实例回退结束第一步之部署版本回滚至BACK版本，并将部署版本置为新的BACK版本	
		   	     //在记录表里记录一条操作流水
			    ecmAppIntantService.saveStartOrStopAppIntantStatus(appIntant,SysConfigConstants.APP_INTANTSTATUS_STOP,userId);
			}
		    ProgressMessageUtil.stopProgress(""+userId);
		}
	   
	}
	/**
     * 应用实例回退结束第一步之部署版本回滚至BACK版本，并将部署版本置为新的BACK版本	
     * @param	 List<EcmAppIntant> intantList 被选择的回退实例
     */  
	private  void rollFailBack(List<EcmAppIntant>  intantList) {
		for(EcmAppIntant intant:intantList) {
			String cmd = creatCode(intant);
			shellService.doCmd(intant.getSerIp(),intant.getSerUser(),intant.getSerPwd(),cmd);
    	}
	}
}
