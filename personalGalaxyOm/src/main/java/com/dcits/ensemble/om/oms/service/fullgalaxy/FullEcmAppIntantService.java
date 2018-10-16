package com.dcits.ensemble.om.oms.service.fullgalaxy;


import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.ensemble.om.oms.common.ProgressMessageUtil;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.dao.utils.OMSIDao;

import com.dcits.ensemble.om.oms.manager.app.AppConfieAutoHandler;
import com.dcits.ensemble.om.oms.manager.app.IContainer;
import com.dcits.ensemble.om.oms.module.app.EcmAppInfo;
import com.dcits.ensemble.om.oms.module.app.EcmAppIntant;
import com.dcits.ensemble.om.oms.module.app.EcmAppVer;
import com.dcits.ensemble.om.oms.module.utils.ContainerCheckResult;
import com.dcits.ensemble.om.oms.service.app.EcmAppIntantService;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 全量应用实例部署Service* 
 * @author tangxlf
 * @date 2015-10-29
 */
@Service
public class FullEcmAppIntantService {
    private static final String  APP_MAX_VER_SQLID = "findMaxVerNumList";
    private static final String  FIND_VER_INFO_SQLID = "findVerInfo";
	@Resource
	private OMSIDao omsBaseDao;
	@Resource
	IContainer appContainer;
	@Resource
	EcmAppIntantService ecmAppIntantService;
	@Resource
	ParamComboxService paramComboxService;
	@Resource
	AppConfieAutoHandler appConfieAutoHandler;
	/**
     * 全量部署时查询全量的应用和应用实例	
     * @param	  String userId  用户ID
     * @return	  List<EcmAppIntant>   
     */    
	public List<EcmAppIntant> findFullAppAndIntantList(String userId) {
    	List<EcmAppIntant>  intantList=magicList(omsBaseDao.findListByCond(new EcmAppIntant()),userId);
    	EcmAppInfo app = new EcmAppInfo();
    	List<EcmAppInfo>  appList=omsBaseDao.findListByCond(app,APP_MAX_VER_SQLID,null);
    	return mergeAppAndIntant(appList,intantList);
    }
   
	//合并app集合和appIntant集合到一个集合中返回页面
	private List<EcmAppIntant> mergeAppAndIntant(List<EcmAppInfo>  appList,List<EcmAppIntant>  intantList){
		List<EcmAppIntant>  resultList = new ArrayList<EcmAppIntant>();
		for(EcmAppInfo app:appList){
			EcmAppIntant intant = new EcmAppIntant();
			intant.setAppId(app.getAppId());
			intant.setAppName(app.getAppName());
			intant.setAppPath(app.getAppPath());
			intant.setAppPort(app.getAppPort());
			intant.setAppWork(app.getAppWork());
			intant.setAppIntantVer(app.getMaxAppVerNum());
			resultList.add(intant);
			for(EcmAppIntant row:intantList){
				if(row.getAppId()==app.getAppId()){
					row.setAppPath(null);
					row.setAppPort(null);
					//row.setAppWork(null);
					row.setAppName(null);
					resultList.add(row);
				}
			}
		}
		return  resultList;
	}
	/**
     * 全量部署所有应用实例	
     * @param	  String userId  用户ID
     * @param	  String isRemainConfig  是否保留配置文件  
     */   
	public void deployFullAppIntant(String userId,String isRemainConfig){
    	List<EcmAppIntant>  intantList=omsBaseDao.findListByCond(new EcmAppIntant());
    	for(EcmAppIntant rowIntant:intantList){
    		 rowIntant.setIsRemainConfig(isRemainConfig);
    		 ProgressMessageUtil.startProgress("" + userId, rowIntant.getSerIp(), rowIntant.getAppIntantName());
    		 Map<String,Object> conMap = new HashMap<String,Object>();
    		 conMap.put("appId",rowIntant.getAppId());
    		 List<EcmAppVer>  appList=omsBaseDao.findListByCond(new EcmAppVer(),FIND_VER_INFO_SQLID,conMap);
    		 if(appList.size()>0){
    			 rowIntant.setAppVerPath(appList.get(0).getAppVerPath());
    			 rowIntant.setAppVerType(appList.get(0).getAppVerType());
    			 rowIntant.setAppVerId(appList.get(0).getAppVerId());
    			 rowIntant.setNewAppIntantVer(appList.get(0).getAppVerNum());
    		 }else{
    			 throw new GalaxyException("应用"+rowIntant.getAppName()+"尚未有安装文件版本!");
    		 }    		 
 		     appContainer.assemContainer(rowIntant,""+userId);		   
             ecmAppIntantService.save(rowIntant, userId); 
             appConfieAutoHandler.handlerAppIntantConfig(rowIntant, userId);
    	}
    	ProgressMessageUtil.stopProgress("" + userId);
	}
	
	/**
     * 全量启动所有应用实例	
     * @param	  String userId  用户ID
     */   
	public void startFullAppIntant(String userId){
    	List<EcmAppIntant>  intantList=omsBaseDao.findListByCond(new EcmAppIntant());
    	for(EcmAppIntant rowIntant:intantList){
    		 ProgressMessageUtil.startProgress("" + userId, rowIntant.getSerIp(), rowIntant.getAppIntantName());
    		 ContainerCheckResult result = appContainer.checkContainerStatus(rowIntant,null);
    		 if(result.getCheckStatus()!=null&&result.getCheckStatus()!= SysConfigConstants.APP_INTANTSTATUS_START){
    			  appContainer.startContainer(rowIntant,""+userId);            
    	     }
    		 ecmAppIntantService.saveStartOrStopAppIntantStatus(rowIntant, SysConfigConstants.APP_INTANTSTATUS_START,userId);
 		}
    	ProgressMessageUtil.stopProgress("" + userId);
	}
	
	/**
     * 全量停止所有应用实例	
     * @param	  String userId  用户ID
     */   
	public void stopFullAppIntant(String userId){
		List<EcmAppIntant>  intantList=omsBaseDao.findListByCond(new EcmAppIntant());
    	for(EcmAppIntant rowIntant:intantList){
    		 ProgressMessageUtil.startProgress("" + userId, rowIntant.getSerIp(), rowIntant.getAppIntantName());
    		 ContainerCheckResult result = appContainer.checkContainerStatus(rowIntant,null);
    		 if(result.getCheckStatus()!=null&&result.getCheckStatus()!= SysConfigConstants.APP_INTANTSTATUS_STOP){
    			  appContainer.stopContainer(rowIntant,""+userId);            
    	     }
    		 ecmAppIntantService.saveStartOrStopAppIntantStatus(rowIntant, SysConfigConstants.APP_INTANTSTATUS_STOP,userId);
 		}
    	ProgressMessageUtil.stopProgress("" + userId);
	}
	
	
	/**
     * 全量重启所有应用实例	
     * @param	  String userId  用户ID
     */   
	public void reStartFullAppIntant(String userId){
		List<EcmAppIntant>  intantList=omsBaseDao.findListByCond(new EcmAppIntant());
    	for(EcmAppIntant rowIntant:intantList){
    		 ProgressMessageUtil.startProgress("" + userId, rowIntant.getSerIp(), rowIntant.getAppIntantName());
    		 appContainer.reStartContainer(rowIntant,""+userId);            
    	     ecmAppIntantService.saveStartOrStopAppIntantStatus(rowIntant, SysConfigConstants.APP_INTANTSTATUS_RESTART,userId);
    	}
    	ProgressMessageUtil.stopProgress("" + userId);
	}
	
	//生成每条记录中的实例最新操作和 实例当前状态
	private List<EcmAppIntant> magicList(List<EcmAppIntant> list,String userId){
		for(EcmAppIntant intant:list){
			intant.setAppIntantStatusName(paramComboxService.getParaName(intant.getAppIntantStatus()));
			ContainerCheckResult result = appContainer.checkContainerStatus(intant,null);
			intant.setCurrentAppIntantStatus(result.getCheckStatusName());
			intant.setHealthMessage(result.getMessage());
		}
		return list;
	}
}
