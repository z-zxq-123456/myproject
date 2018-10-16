package com.dcits.ensemble.om.oms.manager.dubbo;

import java.util.List;

import javax.annotation.Resource;

import com.dcits.ensemble.om.oms.common.DataUtil;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.manager.app.CommonShellService;
import com.dcits.ensemble.om.oms.manager.app.PropertiesCacheService;
import com.dcits.ensemble.om.oms.module.app.EcmAppIntant;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import com.dcits.ensemble.om.oms.service.utils.IService;
import org.springframework.stereotype.Component;


/**
 * 解析dubbo服务提供者、消费者对应的应用实例名* 
 * @author tangxlf
 * @date 2016-06-06 
 */
@Component
public class DubboServcieAppIntatNameParse {
	
	@Resource
	private PropertiesCacheService propertiesCacheService;
	@Resource
	ParamComboxService paramComboxService;
	@Resource
	IService omsBaseService;
	@Resource
	CommonShellService commonShellService;
	/**
	 * 根据IP和PORT解析出应用实例名
	 * @param String  ip        
	 * @param String  port     
	 * @return String 对应的应用实例名  若无返回""
	 */
	public  String providerIpParse(String ip,String port){
		List<EcmAppIntant> intantList = getAppIntantByIp(ip);
		for(EcmAppIntant intant : intantList){
			intant.setAppWork(commonShellService.parseAppIntantWorkName(intant,null));	
			String intantPort = propertiesCacheService.getProperties(paramComboxService.getParaName(SysConfigConstants.GALAXY_PROTOCOL_PORT),intant);
			if(!DataUtil.isNull(intantPort)&&intantPort.equals(port)){
				return intant.getAppIntantName();
			}
		}
		return "";
	}
	/**
	 * 根据IP解析出应用实例名
	 * @param String  ip  	     
	 * @return String 对应的应用实例名,多个用"|"分割，  若无返回""
	 */
	public  String consumerIpParse(String ip){
		List<EcmAppIntant> intantList = getAppIntantByIp(ip);
		StringBuilder sbuild = new StringBuilder("");
		for(EcmAppIntant intant:intantList){			
			if(intant.getAppIntantStatus().equals(SysConfigConstants.APP_INTANTSTATUS_START)||intant.getAppIntantStatus().equals(SysConfigConstants.APP_INTANTSTATUS_RESTART)){
				sbuild.append(intant.getAppIntantName()+"|");
			}
		}
		if( sbuild.toString().length()==0) {
            return "";
		}else
		return sbuild.toString().substring(0, sbuild.toString().length()-1);
	}
	
	private List<EcmAppIntant> getAppIntantByIp(String ip){
		EcmAppIntant intant = new EcmAppIntant();
		intant.setSerIp(ip);
		intant.setAppIntantStatus(SysConfigConstants.APP_INTANTSTATUS_NODEPLOY);
		return omsBaseService.findListByCond(intant);
	}
	
}
