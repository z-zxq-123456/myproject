package com.dcits.ensemble.om.oms.manager.zookeeper;


import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.dao.utils.OMSIDao;
import com.dcits.ensemble.om.oms.manager.app.ParsePropertiesFileService;
import com.dcits.ensemble.om.oms.module.app.AppConfigInfo;
import com.dcits.ensemble.om.oms.module.app.EcmAppIntant;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareZkint;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 修改配置文件 
 * @author wangbinaf
 * @date 2016-05-13
 */
@Component
public class UpdateZkConf {
	
	private  final Logger log = LoggerFactory.getLogger(this.getClass());	
	
	private final static String ZK_SQL_ID ="findListByAppId";//查询APP对应的zk实例
	@Resource
	OMSIDao omsBaseDao;
	@Resource
	ParsePropertiesFileService parsePropertiesFileService;
	@Resource
	ParamComboxService paramComboxService;
	
	/**
	 * 获取Zookeeper列表
	 * @param Integer   appId   应用ID
	 * @return  List<EcmMidwareZkint>  zk实例列表	
	 */
	public List<EcmMidwareZkint> getZkMidwareInfo(Integer  appId) {
		Map<String,Object>  queryMap =new HashMap<String,Object>();
		queryMap.put("appId",appId);
		List<EcmMidwareZkint> zkIntantList =omsBaseDao.findListByCond(new EcmMidwareZkint(),ZK_SQL_ID,queryMap);
		log.info("zkIntantList"+zkIntantList);
		return zkIntantList;
	}
	
	/**
	 * 获取dubbo使用的ZK串---格式为192.168.165.161：8080?backup=192.168.165.161：8080（举例）.
	 * @param List<EcmMidwareZkint> zkIntantListt ZK实例列表	
	 * @return  String  字符串
	 */
	public String   dubboRegistryUrl( List<EcmMidwareZkint> zkIntantList ){
		if(zkIntantList.isEmpty()){
			throw new GalaxyException("应用登记zk集群没有对应的zk实例!");
		}
		StringBuilder dubboRegstiryStrBuilder = new StringBuilder("zookeeper://");
		for(int i =0 ; i <zkIntantList.size() ; i++) {
			if( i == 0 ){
				dubboRegstiryStrBuilder.append(zkIntantList.get(i).getSerIp()+":"+zkIntantList.get(i).getZkintClientPort());
			}else if( i == 1&&i == zkIntantList.size()-1 ){
				dubboRegstiryStrBuilder.append("?backup="+zkIntantList.get(i).getSerIp()+":"+zkIntantList.get(i).getZkintClientPort());
			}else if( i == 1&&i != zkIntantList.size()-1 ){
				dubboRegstiryStrBuilder.append("?backup="+zkIntantList.get(i).getSerIp()+":"+zkIntantList.get(i).getZkintClientPort()+",");
			}else if( i> 1&&i == zkIntantList.size()-1 ){
				dubboRegstiryStrBuilder.append(zkIntantList.get(i).getSerIp()+":"+zkIntantList.get(i).getZkintClientPort());
			}else if( i> 1&&i != zkIntantList.size()-1 ){
				dubboRegstiryStrBuilder.append(zkIntantList.get(i).getSerIp()+":"+zkIntantList.get(i).getZkintClientPort()+",");
			}
		}
		log.info("dubboRegistryUrl="+dubboRegstiryStrBuilder.substring(0, dubboRegstiryStrBuilder.length()).toString());
		//return dubboRegstiryStrBuilder.deleteCharAt(dubboRegstiryStrBuilder.length()-1).toString();
		return  dubboRegstiryStrBuilder.substring(0, dubboRegstiryStrBuilder.length()).toString();  
	}
	/**
	 * 获取zkUrl---格式为192.168.165.161：8080，192.168.165.11：8080（举例）.
	 * @param List<EcmMidwareZkint> zkIntantListt ZK实例列表	
	 * @return  String  字符串
	 */
	public String zkUrl( List<EcmMidwareZkint> zkIntantList ){
		if(zkIntantList.isEmpty()){
			throw new GalaxyException("应用登记zk集群没有对应的zk实例!");
		}
		StringBuilder zkStrBuilder = new StringBuilder();
		for(int i =0 ; i <zkIntantList.size() ; i++) {
			zkStrBuilder.append(zkIntantList.get(i).getSerIp()+":"+zkIntantList.get(i).getZkintClientPort()+",");	
		}
		return zkStrBuilder.deleteCharAt(zkStrBuilder.length() - 1).toString();
	}
	/**
	 * zkNodeUrl---格式为192.168.165.161：8080，192.168.165.11：8080/wangbinaf（举例）.
	 * @param List<EcmMidwareZkint> zkIntantListt ZK实例列表
	 * @return  String  字符串
	 */
	public String zkNodeUrl( List<EcmMidwareZkint> zkIntantList,String  nodeName ){
		if(zkIntantList.isEmpty()){
			throw new GalaxyException("应用登记zk集群没有对应的zk实例!");
		}
		StringBuilder zkStrBuilder = new StringBuilder();
		for(int i =0 ; i <zkIntantList.size() ; i++) {
			zkStrBuilder.append(zkIntantList.get(i).getSerIp()+":"+zkIntantList.get(i).getZkintClientPort()+",");
		}
		return zkStrBuilder.deleteCharAt(zkStrBuilder.length()-1).toString()+"/"+nodeName;
	}
	
	/**
	 * 修改ZOOKEEPER配置文件
	 * @param EcmAppIntant  intant 容器实例对象	
	 */
	public void zkConfUpdate(EcmAppIntant intant) {
	  AppConfigInfo configInfoA = new AppConfigInfo();
	  configInfoA.setAppIntantId(intant.getAppIntantId());
	  configInfoA.setAppId(intant.getAppId());
	  configInfoA.setFileName(paramComboxService.getParaName(SysConfigConstants.GALAXY_PROTOCOL));
	  configInfoA.setConfigKey(paramComboxService.getParaName(SysConfigConstants.GALAXY_REGISTRY_ADDRESS));
	  List<EcmMidwareZkint> zkIntantList =getZkMidwareInfo(intant.getAppId());//通过APPID查找对应的ZK实例
	  configInfoA.setUpdateConfigValue(dubboRegistryUrl(zkIntantList));
	  parsePropertiesFileService.updateAppIntantFileProperties(configInfoA);
	}
}
