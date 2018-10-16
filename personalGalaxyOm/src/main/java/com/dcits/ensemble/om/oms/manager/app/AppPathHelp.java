package com.dcits.ensemble.om.oms.manager.app;

import com.dcits.ensemble.om.oms.common.CommonPathHelp;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.module.app.EcmAppIntant;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;

/**
 * 应用路径辅助生成类* 
 * @author tangxlf
 * @date 2015-11-10 
 */
public class AppPathHelp {

	
   /**
    * 生成应用实例临时目录名
    * @param EcmAppIntant        intant              应用实例对象
    * @param ParamComboxService  paramComboxService  参数缓存服务对象 
    * @return String  应用实例临时目录名
    */ 
	public static  String createAppTempName(EcmAppIntant intant,ParamComboxService paramComboxService){
			return  intant.getAppWork()+ SysConfigConstants.DIR_LINK_SIGN+ paramComboxService.getParaRemark1(SysConfigConstants.SYS_APP_ADD_TEMP_WORK);
	}
   /**
	* 生成应用实例目录名
	* @param EcmAppIntant  intant                    应用实例对象	
    * @return String  应用实例目录名
	*/ 
//	public static  String createAppName(EcmAppIntant  intant){
//			return  intant.getAppWork()+"_"+ intant.getAppIntantId();
//	}	
   /**
    * 生成应用实例备份目录名
	* @param EcmAppIntant        intant              应用实例对象
	* @param ParamComboxService  paramComboxService  参数缓存服务对象 	
	* @return String  应用实例备份目录名
    */ 
	public static  String createAppBackName(EcmAppIntant intant,ParamComboxService paramComboxService){
		   return  intant.getAppWork()+ SysConfigConstants.DIR_LINK_SIGN+ paramComboxService.getParaRemark1(SysConfigConstants.APP_BACK_SUFF_NAME);
	}	
   /**
	* 生成应用实例lib路径
	* @param EcmAppIntant        intant              应用实例对象
	* @param ParamComboxService  paramComboxService  参数缓存服务对象 	
	* @return String  应用实例lib路径
	*/ 
	public static  String createAppLibPath(EcmAppIntant intant,ParamComboxService paramComboxService){
		   return  CommonPathHelp.handlePath(intant.getAppPath())+"/"+ intant.getAppWork() + "/"+paramComboxService.getParaRemark1(SysConfigConstants.SYS_LIB_WORK);
	}
	
	/**
	* 生成临时应用实例lib路径
	* @param EcmAppIntant        intant              应用实例对象
	* @param ParamComboxService  paramComboxService  参数缓存服务对象 	
	* @return String  临时应用实例lib路径
	*/ 
	public static  String createTempAppLibPath(EcmAppIntant intant,ParamComboxService paramComboxService){
		   return  CommonPathHelp.handlePath(intant.getAppPath())+"/"+createAppTempName(intant,paramComboxService)+"/"+intant.getAppWork()+"/"+paramComboxService.getParaRemark1(SysConfigConstants.SYS_LIB_WORK);
	}
   /**
	* 生成应用实例bin路径
	* @param EcmAppIntant        intant              应用实例对象
	* @param ParamComboxService  paramComboxService  参数缓存服务对象 	
	* @return String  应用实例bin路径
	*/ 
	public static  String createAppBinPath(EcmAppIntant intant,ParamComboxService paramComboxService){
		  return  CommonPathHelp.handlePath(intant.getAppPath())+"/"+intant.getAppWork()+"/"+paramComboxService.getParaRemark1(SysConfigConstants.SYS_BIN_WORK);
	}		
   /**
	* 生成应用实例Conf路径
	* @param EcmAppIntant        intant              应用实例对象
	* @param ParamComboxService  paramComboxService  参数缓存服务对象 	
	* @return String  应用实例Conf路径
	*/ 
	public static  String createAppConfPath(EcmAppIntant intant,ParamComboxService paramComboxService){
		  return  CommonPathHelp.handlePath(intant.getAppPath())+"/"+intant.getAppWork()+"/"+paramComboxService.getParaRemark1(SysConfigConstants.SYS_CONF_WORK);
	}	
	
   /**
	* 生成应用实例log路径
	* @param EcmAppIntant        intant              应用实例对象
	* @param ParamComboxService  paramComboxService  参数缓存服务对象 	
	* @return String  应用实例log路径
	*/ 
	public static  String createLogPath(EcmAppIntant intant,ParamComboxService paramComboxService){
		   return  CommonPathHelp.handlePath(intant.getAppPath())+"/"+intant.getAppSimpenNm()+"_"+intant.getAppIntantId()+"/"+paramComboxService.getParaRemark1(SysConfigConstants.GALAXY_APP_LOG_DIRCTION)+"/"+paramComboxService.getParaRemark1(SysConfigConstants.GALAXY_APP_LOG_NAME);
	}	
	
	
}
