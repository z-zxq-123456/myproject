package com.dcits.ensemble.om.oms.service.fullgalaxy;


import com.dcits.galaxy.base.util.DateUtils;
import com.dcits.ensemble.om.oms.common.ProgressMessageUtil;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.dao.utils.OMSIDao;
import com.dcits.ensemble.om.oms.manager.app.AppPathHelp;
import com.dcits.ensemble.om.oms.manager.app.CommonShellService;
import com.dcits.ensemble.om.oms.module.app.EcmAppIntant;
import com.dcits.ensemble.om.oms.module.app.EcmAppoperInfo;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import com.dcits.dynamic.web.util.dao.PkServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * 清理应用实例部署Service* 
 * @author tangxlf
 * @date 2015-10-29
 */
@Service
public class FullCleanAppIntantService {   
	private  final Logger log = LoggerFactory.getLogger(this.getClass());
	@Resource
	private OMSIDao omsBaseDao;
	@Resource
	CommonShellService commonShellService;
	@Resource
	ParamComboxService paramComboxService;
	@Resource
	PkServiceUtil  serviceUtil;
	
	private int max_req_no = 0;
	/**
     * 全量部署所有应用实例	
     * @param	  String userId  用户ID
     */   
	public void cleanAllAppIntant(String userId){
    	List<EcmAppIntant>  intantList=omsBaseDao.findListByCond(new EcmAppIntant());
    	for(EcmAppIntant rowIntant:intantList){
    		if(rowIntant.getAppIntantVer()!=null)
    		cleanAppIntant(rowIntant,userId);         
    	}
	}
	
	@Transactional
	public void cleanAppIntant(EcmAppIntant intant,String userId){
		ProgressMessageUtil.startProgress("" + userId, intant.getSerIp(), intant.getAppIntantName());
		ProgressMessageUtil.addProgressAction("" + userId, "应用实例清理");
		String appIntant_dir =commonShellService.parseAppIntantWorkName(intant,""+userId);
		intant.setAppWork(appIntant_dir);
		String appIntant_backdir = AppPathHelp.createAppBackName(intant, paramComboxService);
		String path = intant.getAppPath();
		if(commonShellService.isExistFileOrDir(intant, path, appIntant_dir)){
			commonShellService.deleteDir(intant, path, appIntant_dir);
		}
		if(commonShellService.isExistFileOrDir(intant, path, appIntant_backdir)){
			commonShellService.deleteDir(intant, path, appIntant_backdir);
		}
		EcmAppIntant verNumUpdatIntant = new EcmAppIntant();
	    verNumUpdatIntant.setAppIntantId(intant.getAppIntantId());
	    verNumUpdatIntant.setAppIntantVer(null);
	    verNumUpdatIntant.setAppIntantStatus(SysConfigConstants.APP_INTANTSTATUS_NODEPLOY);
	    log.info("verNumUpdatIntant = "+verNumUpdatIntant);
	    omsBaseDao.updateByPrimaryKey(verNumUpdatIntant);
	    EcmAppoperInfo operInfo = new EcmAppoperInfo();
    	operInfo.setAppIntantId(intant.getAppIntantId());
    	int ecmAppoperId=serviceUtil.getMaxReqID(max_req_no,"ECM_APPOPER_INFO","ECM_APPOPER_ID");
    	operInfo.setEcmAppoperId(ecmAppoperId);
    	operInfo.setEcmAppoperTime(DateUtils.getDateTime());
    	operInfo.setEcmAppoperType(SysConfigConstants.APP_INTANTSTATUS_CLEAN);
    	operInfo.setEcmAppoperUserid(userId);
    	log.info("operInfo = "+operInfo);
    	omsBaseDao.insert(operInfo);
    	ProgressMessageUtil.stopProgress("" + userId);
	}
	
	
}
