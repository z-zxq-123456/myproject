package com.dcits.ensemble.om.oms.manager.redis;

import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.manager.app.ParsePropertiesFileService;
import com.dcits.ensemble.om.oms.module.app.AppConfigInfo;
import com.dcits.ensemble.om.oms.module.app.EcmAppInfo;
import com.dcits.ensemble.om.oms.module.app.EcmAppIntant;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareRedisint;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import com.dcits.ensemble.om.oms.service.utils.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * EcmAppIntantAction* 
 * @author luolang
 * @date 2016-05-12
 */
@Component
public class RedisPropertiesAutoRevising {
	private  final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private static final String INSULATE_SIGN=":";//隔离符分号
	
	private static final String REDIS_WEIGHT ="10";	
	
	private static final String NOPASSWORD_FLAG ="-";//	redis无密码访问标示
	
	private static final String END_SIGN=";";//结束标示
	
	
	@Resource
	ParsePropertiesFileService parsePropertiesFileService;
	@Resource
	ParamComboxService paramComboxService;
	@Resource
	IService omsBaseService;
	/**
	 * 修改redis配置文件
	 * @param EcmAppIntant  intant 容器实例对象	
	 */
	public void setRedisProperties(EcmAppIntant intant){
		  AppConfigInfo configInfoA = new AppConfigInfo();
		  configInfoA.setAppIntantId(intant.getAppIntantId());
		  configInfoA.setAppId(intant.getAppId());
		  String fileName = paramComboxService.getParaNameByCode(SysConfigConstants.CACHER_FILE_NAME);
		  configInfoA.setFileName(fileName);
		  configInfoA.setConfigKey(paramComboxService.getParaName(SysConfigConstants.REDIS_SERVERS_NAME));
		  configInfoA.setUpdateConfigValue(getRedisPropertiesSerNa(intant));
		  parsePropertiesFileService.updateAppIntantFileProperties(configInfoA);
		  AppConfigInfo configInfoB = new AppConfigInfo();
		  configInfoB.setAppIntantId(intant.getAppIntantId());
		  configInfoB.setAppId(intant.getAppId());
		  configInfoB.setFileName(fileName);
		  configInfoB.setConfigKey(paramComboxService.getParaName(SysConfigConstants.REDIS_SENTINEL_SERVERS));
		  configInfoB.setUpdateConfigValue(getRedisPropertiesSenSer(intant));
		  parsePropertiesFileService.updateAppIntantFileProperties(configInfoB);
	}
	
	/**
	 * 获取Redis配置文件redis.servers.names的属性值
	 * @param EcmAppIntant  intant 容器实例对象	
	 */
	private String getRedisPropertiesSerNa(EcmAppIntant intant) {
		List<EcmMidwareRedisint> masterList = getMidwareInfo(intant, SysConfigConstants.REDIS_MASTER);
		StringBuffer nodInfo = new StringBuffer();
		for(EcmMidwareRedisint master : masterList) {
			nodInfo.append(paramComboxService.getParaRemark1(SysConfigConstants.REDIS_MASTER)+master.getRedisintNodeNum()+
					INSULATE_SIGN+REDIS_WEIGHT+":"+paramComboxService.getParaRemark1(SysConfigConstants.REIDS_CLIENT_TIMEOUT)+INSULATE_SIGN+NOPASSWORD_FLAG+END_SIGN
					);
		}
		String nodString = nodInfo.toString();
		log.info("nodString="+nodString);
		return nodString;
	}
	/**
	 * 获取Redis列表
	 * @param EcmAppIntant  intant 容器实例对象	
	 * @param String redisintType  redis实例类型
	 */
	private List<EcmMidwareRedisint> getMidwareInfo(EcmAppIntant intant,String redisintType) {
		EcmAppInfo app = new EcmAppInfo();
		app.setAppId(intant.getAppId());
		EcmAppInfo newApp = omsBaseService.selectByPrimaryKey(app);
		int midwareId = newApp.getMidwareRedisId();
		Map<String ,Object > midwareMap = new HashMap<String,Object>();
		midwareMap.put("midwareId", midwareId);
		midwareMap.put("redisintType",redisintType);
		List<EcmMidwareRedisint> midList = omsBaseService.findListByCond(new EcmMidwareRedisint(),"findRedisSerList",midwareMap);
		return midList;
	}
	/**
	 * 获取Redis配置文件redis.Sentinel.servers的属性值
	 * @param EcmAppIntant  intant 容器实例对象	
	 */
	private String getRedisPropertiesSenSer(EcmAppIntant intant) {
		List<EcmMidwareRedisint> sentinelList = getMidwareInfo(intant, SysConfigConstants.REDIS_SENTINEL);
		StringBuffer hostAndPort = new StringBuffer();
		for(EcmMidwareRedisint sentinel:sentinelList) {
			hostAndPort.append(sentinel.getSerIp()+INSULATE_SIGN+sentinel.getRedisintPort()+END_SIGN);
		}
		String ipAndPortString ="";
		if(hostAndPort.length()>0) {
			 ipAndPortString = hostAndPort.deleteCharAt(hostAndPort.length()-1).toString();
		}
		log.info("ipAndPortString="+ipAndPortString);
		return ipAndPortString;
	}
}
