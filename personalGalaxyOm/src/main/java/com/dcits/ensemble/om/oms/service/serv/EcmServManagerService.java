package com.dcits.ensemble.om.oms.service.serv;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.module.app.EcmAppUpginfo;
import com.dcits.ensemble.om.oms.service.utils.IService;

@Service
public class EcmServManagerService {
	@Resource
	IService  omsBaseService;
	//针对服务路由规则
	public boolean toEcmServRule(Integer ServRuleId ) {
		EcmAppUpginfo  appUpginfo =new EcmAppUpginfo();
		Map<String,Object> tmp =new HashMap<String,Object>();
		tmp.put("ServRuleId", ServRuleId);
		List<EcmAppUpginfo>  appUpginfoList=omsBaseService.findListByCond(appUpginfo,"findListByServRuleId",tmp);
		if(appUpginfoList.size()>0) {
		for(EcmAppUpginfo upgInfo:appUpginfoList) {
			if(upgInfo.getAppUpgStatus().equals(SysConfigConstants.APP_UPGSTATUS_PROGRESS)) {
				  return false;
			   }
		}
		}
		return true;
	}
	
	//针对路由条件
	public boolean toEcmRouterCond(Integer routerCondId ) {
		EcmAppUpginfo  appUpginfo =new EcmAppUpginfo();
		Map<String,Object> tmp =new HashMap<String,Object>();
		tmp.put("routerCondId", routerCondId);
		List<EcmAppUpginfo>  appUpginfoList=omsBaseService.findListByCond(appUpginfo,"findListByRouterCondId",tmp);
	    if(appUpginfoList.size()>0) {
		for(EcmAppUpginfo upgInfo:appUpginfoList) {
			if(upgInfo.getAppUpgStatus().equals(SysConfigConstants.APP_UPGSTATUS_PROGRESS)) {
				  return false;
			   }
		}
		}
		return true;
	}
	//针对路由字段
	public boolean toEcmRouterCol(Integer routerColId ) {
		EcmAppUpginfo  appUpginfo =new EcmAppUpginfo();
		Map<String,Object> tmp =new HashMap<String,Object>();
		tmp.put("routerColId", routerColId);
		List<EcmAppUpginfo>  appUpginfoList=omsBaseService.findListByCond(appUpginfo,"findListByRouterColId",tmp);
		if(appUpginfoList.size()>0) {
		for(EcmAppUpginfo upgInfo:appUpginfoList) {
			if(upgInfo.getAppUpgStatus().equals(SysConfigConstants.APP_UPGSTATUS_PROGRESS)) {
				  return false;
			   }
		}
		}
		return true;
	}
	//针对服务方法信息
	public boolean toEcmSerMtdinfo(Integer serMtdId ) {
		EcmAppUpginfo  appUpginfo =new EcmAppUpginfo();
		Map<String,Object> tmp =new HashMap<String,Object>();
		tmp.put("serMtdId", serMtdId);
		List<EcmAppUpginfo>  appUpginfoList=omsBaseService.findListByCond(appUpginfo,"findListBySerMtdId",tmp);
		if(appUpginfoList.size()>0) {
		for(EcmAppUpginfo upgInfo:appUpginfoList) {
			if(upgInfo.getAppUpgStatus().equals(SysConfigConstants.APP_UPGSTATUS_PROGRESS)) {
				  return false;
			   }
		}
		}
		return true;
	}
	//针对应用服务信息
	public boolean toEcmAppSerinfo(Integer appSerId ) {
		EcmAppUpginfo  appUpginfo =new EcmAppUpginfo();
		Map<String,Object> tmp =new HashMap<String,Object>();
		tmp.put("appSerId", appSerId);
		List<EcmAppUpginfo>  appUpginfoList=omsBaseService.findListByCond(appUpginfo,"findListByAppSerId",tmp);
		if(appUpginfoList.size()>0) {
		for(EcmAppUpginfo upgInfo:appUpginfoList) {
			if(upgInfo.getAppUpgStatus().equals(SysConfigConstants.APP_UPGSTATUS_PROGRESS)) {
				  return false;
			   }
		}
		}
		return true;
	}
}
