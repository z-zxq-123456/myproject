package com.dcits.ensemble.om.oms.manager.zookeeper;

import com.dcits.ensemble.om.oms.common.CommonPathHelp;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareZkint;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;

/**
 * 应用路径辅助生成类* 
 * @author wangbinaf
 * @date 2016-04-29 
 */
public class ZkPathHelp {
	/**
	* 生成应用实例zookeeper-3.4.6路径
	* @param EcmMidwareZkint        intant              应用实例对象
	* @param ParamComboxService  paramComboxService  参数缓存服务对象 	
	* @return String  应用实例bin路径
	*/ 
	public static  String createZookeeperPath(EcmMidwareZkint intant,ParamComboxService paramComboxService){
		return  CommonPathHelp.handlePath(intant.getMidwarePath()+"/"+"zookeeper"+"-"+intant.getMidwareVerNo()+"/");
	}	
	/**
	* 生成临时zk实例bin路径
	* @param EcmMidwareZkint        intant              应用实例对象
	* @param ParamComboxService  paramComboxService  参数缓存服务对象 	
	* @return String  临时应用实例lib路径
	*/ 
	public static  String createZkBinPath(EcmMidwareZkint intant,ParamComboxService paramComboxService){
		return  CommonPathHelp.handlePath(intant.getMidwarePath()+"/"+"zookeeper"+"-"+intant.getMidwareVerNo()+"/"+paramComboxService.getParaRemark1(SysConfigConstants.SYS_ZK_BIN));
	}	
   /**
	* 生成zk实例Conf路径
	* @param EcmMidwareZkint        intant             应用实例对象
	* @param ParamComboxService  paramComboxService  参数缓存服务对象 	
	* @return String  应用实例Conf路径
	*/ 
	public static  String createZkConfPath(EcmMidwareZkint intant,ParamComboxService paramComboxService){
		  return  CommonPathHelp.handlePath(intant.getMidwarePath()+"/"+"zookeeper"+"-"+intant.getMidwareVerNo()+"/"+paramComboxService.getParaRemark1(SysConfigConstants.SYS_ZK_CONF));
	}	
	
   /**
	* 生成应用zk的logs路径
	* @param EcmMidwareZkint        intant              应用实例对象
	* @param ParamComboxService  paramComboxService  参数缓存服务对象 	
	* @return String  应用实例log路径
	*/ 
	public static  String createLogPath(EcmMidwareZkint intant,ParamComboxService paramComboxService){
		  return  CommonPathHelp.handlePath(intant.getMidwarePath()+"/"+"zookeeper"+"-"+intant.getMidwareVerNo()+"/"+paramComboxService.getParaRemark1(SysConfigConstants.SYS_ZK_LOGS));
	}	
	/**
	* 生成应用实例zkdata路径
	* @param EcmMidwareZkint        intant              应用实例对象
	* @param ParamComboxService  paramComboxService  参数缓存服务对象 	
	* @return String  应用实例bin路径
	*/ 
	public static  String createZkdataPath(EcmMidwareZkint intant,ParamComboxService paramComboxService){
		return  CommonPathHelp.handlePath(intant.getMidwarePath()+"/"+"zookeeper"+"-"+intant.getMidwareVerNo()+"/"+paramComboxService.getParaRemark1(SysConfigConstants.SYS_ZK_DATE));
	}		
	
}
