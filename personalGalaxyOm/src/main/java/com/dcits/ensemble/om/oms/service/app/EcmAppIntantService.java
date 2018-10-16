package com.dcits.ensemble.om.oms.service.app;


import javax.annotation.Resource;

import com.dcits.dynamic.web.util.dao.PkServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dcits.galaxy.base.util.DateUtils;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.dao.utils.OMSIDao;
import com.dcits.ensemble.om.oms.module.app.EcmAppIntant;
import com.dcits.ensemble.om.oms.module.app.EcmAppinstDeploy;
import com.dcits.ensemble.om.oms.module.app.EcmAppoperInfo;



/**
 * 应用实例部署Service* 
 * @author tangxlf
 * @date 2015-08-26
 */
@Service
public class EcmAppIntantService {

	private  final Logger log = LoggerFactory.getLogger(this.getClass());
	@Resource
	private OMSIDao omsBaseDao;
	@Resource
	PkServiceUtil serviceUtil;
	
	private int max_appDeploy_Id = 0;
	
	private int max_req_no = 0;
	
	/**
     * 部署应用实例后，修改应用实例状态，并添加应用实例部署记录
     * @param EcmAppIntant  intant    应用实例对象	
     * @param int    userId           用户ID 		     
     */
    @Transactional
	public void save(EcmAppIntant  intant,String userId) {
    	EcmAppinstDeploy  deploy = new EcmAppinstDeploy();
    	int appDeployId=serviceUtil.getMaxReqID(max_appDeploy_Id,"ECM_APPINST_DEPLOY","APP_DEPLOY_ID");
    	deploy.setAppDeployId(appDeployId);
    	deploy.setAppDeployDate(DateUtils.getDateTime());
    	deploy.setAppDeployUserid(userId);
    	deploy.setAppIntantId(intant.getAppIntantId());
    	deploy.setAppVerId(1);
    	log.info("deploy = "+deploy);
	    omsBaseDao.insert(deploy);
	    EcmAppIntant verNumUpdatIntant = new EcmAppIntant();
	    verNumUpdatIntant.setAppIntantId(intant.getAppIntantId());
	    verNumUpdatIntant.setAppIntantVer(intant.getNewAppIntantVer());
	    verNumUpdatIntant.setAppIntantStatus(SysConfigConstants.APP_INTANTSTATUS_DEPLOYED);
	    log.info("verNumUpdatIntant = "+verNumUpdatIntant);
	    omsBaseDao.updateByPrimaryKey(verNumUpdatIntant);	    
    }
    /**
     * 停止或启动应用实例后，修改应用实例状态，并添加应用实例操作记录
	 * @param EcmAppIntant  intant    应用实例对象
     * @param String intantStatus     应用实例状态
     * @param int    userId           用户ID 		     
     */
    @Transactional
    public void saveStartOrStopAppIntantStatus(EcmAppIntant  intant,String intantStatus,String userId) {
    	EcmAppoperInfo  operInfo = new EcmAppoperInfo();
    	operInfo.setAppIntantId(intant.getAppIntantId());
    	int ecmAppoperId=serviceUtil.getMaxReqID(max_req_no,"ECM_APPOPER_INFO","ECM_APPOPER_ID");
    	operInfo.setEcmAppoperId(ecmAppoperId);
    	operInfo.setEcmAppoperTime(DateUtils.getDateTime());
    	operInfo.setEcmAppoperType(intantStatus);
    	operInfo.setEcmAppoperUserid(userId);
    	log.info("operInfo = "+operInfo);
    	omsBaseDao.insert(operInfo);
    	//if(intantStatus!=null&&!intantStatus.equals(SysConfigConstants.APP_INTANTSTATUS_RESTART)){
    	if(intantStatus!=null){
    		EcmAppIntant verNumUpdatIntant = new EcmAppIntant();
    	    verNumUpdatIntant.setAppIntantId(intant.getAppIntantId());	   
    	    verNumUpdatIntant.setAppIntantStatus(intantStatus);
    	    verNumUpdatIntant.setAppIntantVer(intant.getAppIntantVer());
    	    omsBaseDao.updateByPrimaryKey(verNumUpdatIntant);
    	}	    
    }
}
