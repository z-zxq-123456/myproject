package com.dcits.ensemble.om.oms.manager.dubbo;


import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.dao.utils.OMSIDao;
import com.dcits.ensemble.om.oms.manager.app.CommonShellService;
import com.dcits.ensemble.om.oms.manager.app.PropertiesCacheService;
import com.dcits.ensemble.om.oms.module.app.EcmAppIntant;
import com.dcits.ensemble.om.oms.module.app.EcmAppUpginfo;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareZkint;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 缓存升级实例信息* 仅能用于升级流程中，先升级部署完成后，必须在升级流程结束时，执行清除方法
 * @author tangxlf
 * @date 2016-03-15 
 */
@Component
public class UpgAppIntantCache {
	//缓存每个应用对应的升级实例信息 K应用ID V应用升级信息
	private static  Map<Integer,UpgAppInfo> upgAppMap = new HashMap<Integer,UpgAppInfo>();
	@Resource
	private OMSIDao omsBaseDao;
	@Resource
	PropertiesCacheService propertiesCacheService;
	@Resource
	ParamComboxService  paramComboxService;
	@Resource
	CommonShellService commonShellService;
	
	private final static String ZK_SQL_ID ="findListByAppId";//查询APP对应的zk实例
   /**	
	 * @return 为一个Map，里面为所有的节点对象组成的Map
	 */
	public synchronized UpgAppInfo getUpgAppInfo(Integer appId) {		
		if(upgAppMap.get(appId)==null){
			initUpgAppInfo(appId);
		}
		return upgAppMap.get(appId);
	}
	//初始化指定AppId的升级实例信息
	private void initUpgAppInfo(Integer appId){
		System.out.print("initUpgAppInfo appId"+appId);
		EcmAppIntant  intant = new EcmAppIntant();
		intant.setAppId(appId);		
		List<EcmAppIntant> totalList =omsBaseDao.findListByCond(intant);//查询该应用ID的所有实例
		List<EcmAppIntant> earlyUpgList = new ArrayList<EcmAppIntant>();
		List<EcmAppIntant> lateUpgList = new ArrayList<EcmAppIntant>();
		Map<String,Object> newNode = new HashMap<String,Object>();
		newNode.put("appUpgStatus",SysConfigConstants.APP_UPGSTATUS_PROGRESS);
		newNode.put("appId",appId);
		List<EcmAppUpginfo> infoList = omsBaseDao.findListByCond(new EcmAppUpginfo(), "findUpdateNode", newNode);//查询该应用ID的当前处于活动中升级信息
		if(infoList.size()==1){
			 for(EcmAppIntant rowIntant:totalList){
				 if(rowIntant.getAppIntantVer()!=null){
					 rowIntant.setAppWork(commonShellService.parseAppIntantWorkName(rowIntant,""));//要设置工作目录，供取端口时使用
					 //获取实例dubbo端口
					 String port = propertiesCacheService.getProperties(paramComboxService.getParaName(SysConfigConstants.GALAXY_PROTOCOL_PORT),rowIntant);
					 rowIntant.setAppPort(port);	
					 if(infoList.get(0).getAppUpgInstidstr().indexOf(""+rowIntant.getAppIntantId())>-1){//如果实例版本号与升级信息中新版本号一致，进先升级实例列表否则进后升级列表
						 earlyUpgList.add(rowIntant);
					 }else{
						 rowIntant.setAppIntantVer(infoList.get(0).getAppUpgNewverno());
						 lateUpgList.add(rowIntant);
					 }
				 }
			 }
		}else if(infoList.size()>1){
		     throw new GalaxyException("存在多个升级的流程，请仔细检查!");
		}else if(infoList.size()<1){
			 throw new GalaxyException("当前不存在升级的流程，请仔细检查!");
		}
		UpgAppInfo upgAppInfo = new UpgAppInfo();
		upgAppInfo.setTotalList(totalList);
		upgAppInfo.setEarlyUpgList(earlyUpgList);
		upgAppInfo.setLateUpgList(lateUpgList);
		deployZkArgs(appId,upgAppInfo);
		initHostAndPorts(upgAppInfo);
		upgAppMap.put(appId, upgAppInfo);
	}
	
	//初始化应用实例的IP及端口串 例:192.168.1.1:20881,192.168.1.2:20881
	private void initHostAndPorts(UpgAppInfo upgAppInfo){
		StringBuilder strBld = new StringBuilder();
		StringBuilder earlyStrBld = new StringBuilder();
		for(EcmAppIntant rowIntant:upgAppInfo.totalList){
			strBld.append(rowIntant.getSerIp()+":"+rowIntant.getAppPort()+",");
		}
		for(EcmAppIntant rowIntant:upgAppInfo.earlyUpgList){
			earlyStrBld.append(rowIntant.getSerIp()+":"+rowIntant.getAppPort()+",");
		}
		upgAppInfo.setHostAndPorts(strBld.toString());
		upgAppInfo.setEarlyHostAndPorts(earlyStrBld.toString());
	}
	
	//根据应用ID配置ZK属性--zkUrl 和  dubbo使用的ZK串
	private void   deployZkArgs(Integer appId,UpgAppInfo upgAppInfo ){
		Map<String,Object>  queryMap =new HashMap<String,Object>();
		queryMap.put("appId",appId);
		System.out.print("appId="+appId);
		List<EcmMidwareZkint> zkIntantList =omsBaseDao.findListByCond(new EcmMidwareZkint(),ZK_SQL_ID,queryMap);
		System.out.print("zkIntantList="+zkIntantList);
		if(zkIntantList.isEmpty()){
			throw new GalaxyException("应用登记zk集群没有对应的zk实例!");
		}
		StringBuilder zkStrBuilder = new StringBuilder();
		StringBuilder dubboRegstiryStrBuilder = new StringBuilder("zookeeper://");
		for(int i =0 ; i <zkIntantList.size() ; i++) {
			zkStrBuilder.append(zkIntantList.get(i).getSerIp()+":"+zkIntantList.get(i).getZkintClientPort()+",");
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
		upgAppInfo.setZkUrl(zkStrBuilder.substring(0,zkStrBuilder.length()-1));
		upgAppInfo.setDubboRegistryUrl(dubboRegstiryStrBuilder.toString());
	}
   /**
	 * 获取该应用的所有应用实例	 * 
	 * @param  Integer appId   应用ID
	 * @return List<EcmAppIntant> 该应用的所有应用实例List
	 */
	public List<EcmAppIntant>  getAppIntTotalList(Integer appId){
		return getUpgAppInfo(appId).getTotalList();
	}
	
   /**
	 * 获取该应用的先升级应用实例	 * 
	 * @param  Integer appId   应用ID
	 * @return List<EcmAppIntant> 先升级应用实例List
	 */
	public List<EcmAppIntant>  getAppIntEarlyList(Integer appId){
		List<EcmAppIntant> earlyUpgList = getUpgAppInfo(appId).getEarlyUpgList();//对应appId的先升级实例
		if (earlyUpgList.size() == 0) {
			throw new GalaxyException("先升级应用实例为空，请检查!");
		}
		return earlyUpgList;
	}
	
	/**
	 * 获取该应用的后升级应用实例	 * 
	 * @param  Integer appId   应用ID
	 * @return List<EcmAppIntant> 后升级应用实例List
	 */
	public List<EcmAppIntant>  getAppIntLateList(Integer appId){
		List<EcmAppIntant> lateUpgList = getUpgAppInfo(appId).getLateUpgList();//对应appId的后升级实例
		if (lateUpgList.size() == 0) {
			throw new GalaxyException("后升级应用实例为空，请检查!");
		}
		return lateUpgList;
	}	
	/**
	 * 获取该应用的先升级的IP及端口串	 * 
	 * @param  Integer appId   应用ID
	 * @return String 该应用的所有实例的IP及端口串
	 */
	public String  getEarlyHostAndPorts(Integer appId){
		return getUpgAppInfo(appId).getEarlyHostAndPorts();
	}
	/**
	 * 获取该应用的所有实例的IP及端口串	 * 
	 * @param  Integer appId   应用ID
	 * @return String 该应用的所有实例的IP及端口串
	 */
	public String  getHostAndPorts(Integer appId){
		return getUpgAppInfo(appId).getHostAndPorts();
	}	
	/**
	 * 获取该应用的zkUrl串	 * 
	 * @param  Integer appId   应用ID
	 * @return String 应用的zkUrl串
	 */
	public String  getZkUrl(Integer appId){
		return getUpgAppInfo(appId).getZkUrl();
	}	
	/**
	 * 获取该应用的zkUrl串	 * 
	 * @param  Integer appId   应用ID
	 * @return String 应用的zkUrl串
	 */
	public String  getDubboRegistryUrl(Integer appId){
		return getUpgAppInfo(appId).getDubboRegistryUrl();
	}	
   /**
	 *升级完成时，调用该方法删除该应用的缓存信息
	 *@param Integer appId   应用ID
	 */
	public void removeUpgAppInfo(Integer appId) {
		upgAppMap.remove(appId);
	}
	//应用升级信息	
	private class UpgAppInfo{
		private List<EcmAppIntant> totalList;//对应appId的全部实例
		private List<EcmAppIntant> earlyUpgList;//对应appId的先升级实例
		private List<EcmAppIntant> lateUpgList;//对应appId的后升级实例
		private String hostAndPorts;//所有实例的IP及端口串 192.168.1.1:20881,所有实例的IP及端口串 192.168.1.2:20881
		private String earlyHostAndPorts;//先升级实例的IP及端口串 192.168.1.1:20881,所有实例的IP及端口串 192.168.1.2:20881
		private String zkUrl;//ZK连接串
		private String dubboRegistryUrl;//dubbo注册中心串
		public List<EcmAppIntant> getTotalList() {
			return totalList;
		}
		public void setTotalList(List<EcmAppIntant> totalList) {
			this.totalList = totalList;
		}
		public List<EcmAppIntant> getEarlyUpgList() {
			return earlyUpgList;
		}
		public void setEarlyUpgList(List<EcmAppIntant> earlyUpgList) {
			this.earlyUpgList = earlyUpgList;
		}
		public List<EcmAppIntant> getLateUpgList() {
			return lateUpgList;
		}
		public void setLateUpgList(List<EcmAppIntant> lateUpgList) {
			this.lateUpgList = lateUpgList;
		}
		public String getHostAndPorts() {
			return hostAndPorts;
		}
		public void setHostAndPorts(String hostAndPorts) {
			this.hostAndPorts = hostAndPorts;
		}
		public String getEarlyHostAndPorts() {
			return earlyHostAndPorts;
		}
		public void setEarlyHostAndPorts(String earlyHostAndPorts) {
			this.earlyHostAndPorts = earlyHostAndPorts;
		}
		public String getZkUrl() {
			return zkUrl;
		}
		public void setZkUrl(String zkUrl) {
			this.zkUrl = zkUrl;
		}
		public String getDubboRegistryUrl() {
			return dubboRegistryUrl;
		}
		public void setDubboRegistryUrl(String dubboRegistryUrl) {
			this.dubboRegistryUrl = dubboRegistryUrl;
		}
		
	}

}
