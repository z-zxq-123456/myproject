package com.dcits.ensemble.om.oms.manager.db;


import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareDbint;
import com.dcits.ensemble.om.oms.module.utils.ContainerCheckResult;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 数据库容器* 
 * @author tangxlf
 * @date 2016-04-27 
 */
@Component
public class DBContainer {
	
	private  final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private static final String CHECK_DBSTATUS ="检查数据库实例状态";
	@Resource
	DBStatusCheck dbtatusCheck;
	@Resource
	ParamComboxService paramComboxService;
	/**
	 * 检查容器实例状态 
	 * @param EcmAppIntant  intant 容器实例对象	 
	 * @param String        userId 用户ID	
	 * @return  ContainerCheckResult    容器状态检查结果对象
	 */
	public ContainerCheckResult checkContainerStatus(EcmMidwareDbint intant,String userId) {
		//ProgressMessageUtil.addProgressAction(userId,CHECK_APPSTATUS);
		ContainerCheckResult checkResult = new ContainerCheckResult();
		if (intant != null) {			
			dbtatusCheck.checkDbConnection(intant, checkResult);
			String statusCode = "";
			if(checkResult.getResultNum()>0){
				statusCode = SysConfigConstants.APP_INTANTSTATUS_STOP;
			}else{
				statusCode = SysConfigConstants.APP_INTANTSTATUS_START;
			}
			intant.setDbintCurrentStatus(statusCode);
			intant.setDbintCurrentStatusName(paramComboxService.getParaName(statusCode));
			intant.setHealthMessage(checkResult.getMessage());						
			return checkResult;
		} else {
			log.error(CHECK_DBSTATUS+"出错!");
			throw new GalaxyException(CHECK_DBSTATUS+"出错!");
		}
	}
	/**
	 * 在指定数据库实例查询指定SQL
	 * @param EcmAppIntant  intant 容器实例对象	 
	 * @param String        userId 用户ID	
	 * @return  ContainerCheckResult    容器状态检查结果对象
	 */
	public void selectDb(EcmMidwareDbint intant,String sqlstr,String userId) {
		
	}
}
