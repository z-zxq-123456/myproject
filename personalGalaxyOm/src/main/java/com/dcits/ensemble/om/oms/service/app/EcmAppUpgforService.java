package com.dcits.ensemble.om.oms.service.app;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.dcits.galaxy.base.exception.GalaxyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dcits.galaxy.base.util.DateUtils;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.dao.utils.OMSIDao;

import com.dcits.ensemble.om.oms.manager.app.UpgNodeCache;
import com.dcits.ensemble.om.oms.module.app.EcmAppUpgfor;
import com.dcits.ensemble.om.oms.module.app.EcmAppUpginfo;
import com.dcits.ensemble.om.oms.module.app.EcmAppValrule;
import com.dcits.ensemble.om.oms.module.app.EcmUpgflowNode;
import com.dcits.dynamic.web.util.dao.PkServiceUtil;
import com.dcits.ensemble.om.oms.service.utils.IService;

/**
 * EcmAppUpgforService* 
 * @author wangbinaf
 * @date 2016-03-10
 */
@Service
public class EcmAppUpgforService {
	private  final Logger log = LoggerFactory.getLogger(this.getClass());
	@Resource
	OMSIDao omsBaseDao;
	@Resource
	PkServiceUtil  serviceUtil;
	@Resource
	IService  omsBaseService;
	@Resource
	UpgNodeCache upgNodeCache;
	@Resource
	EcmAppUpgforService ecmAppUpgforService;
	
	private int max_appUpg_Id = 0;
	
	private int max_appUpgfor_Id = 0;
	
	
  /**
	* 获取当前应用升级流程节点信息	 
	* @return  EcmUpgflowNode      
	*/	
	public EcmUpgflowNode findCurrentUpgNodeSeq(Integer appId){
		Map<String,Object> newNode = new HashMap<String,Object>();
		newNode.put("appUpgStatus",SysConfigConstants.APP_UPGSTATUS_PROGRESS);
		newNode.put("appId",appId);
		List<EcmUpgflowNode> infoList = omsBaseService.findListByCond(new EcmUpgflowNode(), "findUpdateNode", newNode);
		if(infoList.size()!=0){
		   return  infoList.get(0);
		}else{
		   return  upgNodeCache.getCurrentUpgNode();
		}
	}
	
	
	/**
     * 新增应用升级信息
     * @param EcmAppUpginfo upgInfo              应用升级对象	
     * @param int    userId                      用户ID 		     
     */
	public void saveUpgInfo(int appId, int demoAppIntantVer,int appUpgOldverno,String appIntantIdList  ) {
		 EcmAppUpginfo upgInfo = new EcmAppUpginfo ();
		 Map<String,Object> newNode = new HashMap<String,Object>();
		 newNode.put("appUpgStatus",SysConfigConstants.APP_UPGSTATUS_PROGRESS);
		 newNode.put("appId",appId);
		 List<EcmAppUpginfo> infoList = omsBaseService.findListByCond(new EcmAppUpginfo(), "findUpdateNode", newNode);
		 if(infoList.size()==0 ){
			 int  appUpgId = serviceUtil.getMaxReqID(max_appUpg_Id,"ecm_app_upginfo","app_upg_id"); //获取当前列表数据最大ID
	    	 EcmUpgflowNode node  = upgNodeCache.getCurrentUpgNode();//在缓存里面获取节点序号最小的升级流程节点列表	
	    	 upgInfo.setAppUpgId(appUpgId); 
	    	 upgInfo.setAppId(appId);
	    	 upgInfo.setAppUpgNewverno(demoAppIntantVer);
	    	 upgInfo.setAppUpgOldverno(appUpgOldverno);
	    	 upgInfo.setUpgflowNodeId(node.getUpgflowNodeId());//获取最小的一个
	    	 upgInfo.setAppUpgStatus(SysConfigConstants.APP_UPGSTATUS_PROGRESS);//插入时状态默认为进行中
	    	 upgInfo.setAppUpgOpertime(DateUtils.getDateTime());
			 upgInfo.setAppUpgInstidstr(appIntantIdList);
	    	 omsBaseService.insert(upgInfo);
		 }else{
			 throw new GalaxyException("已存在当前升级节点，不需要再进行此操作!");
		 }
    	
    }
    
    
	/**  
	 * 当前按钮是正常的下一步时调用
     * 修改应用升级当前节点信息
     * @param EcmAppUpginfo upgInfo              应用升级对象	
     * @param int    userId                      用户ID 		     
     */
    
    public void updateProcess(EcmAppUpginfo upgInfo,String userId){
    	upgInfo.setAppUpgStatus(SysConfigConstants.APP_UPGSTATUS_PROGRESS);
    	update( upgInfo,userId);
    }
    /**
     * 当前按钮是正常执行完结束时调用
     * 修改应用升级当前节点信息
     * @param EcmAppUpginfo upgInfo              应用升级对象	
     * @param int    userId                      用户ID 		     
     */
    public void updateSuccess(EcmAppUpginfo upgInfo,String userId){
    	upgInfo.setAppUpgStatus(SysConfigConstants.APP_UPGSTATUS_SUCCEED);
    	update( upgInfo,userId);
    }
    
    /**
     * 当前按钮回退时调用
     * 修改应用升级当前节点信息
     * @param EcmAppUpginfo upgInfo              应用升级对象	
     * @param int    userId                      用户ID 		     
     */
    public void updateFail(EcmAppUpginfo upgInfo,String userId){
    	upgInfo.setAppUpgStatus(SysConfigConstants.APP_UPGSTATUS_FAIL);
    	update( upgInfo,userId);
    }
    /**
     * 修改应用升级当前节点信息
     * @param EcmAppUpginfo upgInfo              应用升级对象	
     * @param int    userId                      用户ID 		     
     */
    @Transactional
    private void update(EcmAppUpginfo upgInfo,String userId) {
    	EcmAppUpginfo node =  new EcmAppUpginfo();
    	Map<String,Object> newNode = new HashMap<String,Object>();
		newNode.put("appUpgStatus",SysConfigConstants.APP_UPGSTATUS_PROGRESS);
		newNode.put("appId",upgInfo.getAppId());
		List<EcmAppUpginfo> infoList = omsBaseService.findListByCond(new EcmAppUpginfo(), "findUpdateNode", newNode);
		log.info("infoList:"+infoList);
		if(infoList.size()==1){
		  node=infoList.get(0);
	      upgInfo.setAppUpgId(node.getAppUpgId());
	      log.info("updateUpgInfo:"+upgInfo);
	      omsBaseService.updateByPrimaryKey(upgInfo);
	      saveUpgFor(upgInfo,userId);
		 }else if(infoList.size()>1){
	         throw new GalaxyException("存在多个升级的流程，请仔细检查!");
	     }else if(infoList.size()<1){
		     throw new GalaxyException("当前不存在升级的流程，请仔细检查!");
	     }
 } 
    /**
     * 插入应用升级当前节点信息
     * @param EcmAppUpginfo upgInfo              应用升级对象	
     * @param int    userId                      用户ID 		     
     */
    private void saveUpgFor(EcmAppUpginfo node ,String userId){
    	int  appUpgforId = serviceUtil.getMaxReqID(max_appUpgfor_Id,"ecm_app_upgfor","app_upgfor_id"); //获取当前列表数据最大ID
    	EcmAppUpgfor forNode = new EcmAppUpgfor();
    	forNode.setAppUpgforId(appUpgforId);
    	forNode.setAppUpgId(node.getAppUpgId());
    	forNode.setAppUpgforUserid(userId);
    	forNode.setUpgflowNodeId(node.getOldUpgflowNodeId());
    	String appUpgforTime = DateUtils.getDateTime();  
    	forNode.setAppUpgforTime(appUpgforTime);
    	omsBaseService.insert(forNode);
    }
    
    /**
     * 从数据库中查应用升级信息列表
     */
    public List<EcmAppUpginfo>  getAppUpginfoList(){
    	List<EcmAppUpginfo> infoList = omsBaseService.findListByCond(new EcmAppUpginfo());
        return infoList;	
    }
    
    /**
     * 从数据库中查应用升级扭转信息列表
     */
    public List<EcmAppUpgfor>  getAppUpgforList(){
    	List<EcmAppUpgfor> forList = omsBaseService.findListByCond(new EcmAppUpgfor());	
    	return forList;
    }
    
    /**
     * 从数据库中查应用升级路由验证规则列表
     */
    public List<EcmAppValrule>  getAppValruleList(){
    	List<EcmAppValrule> ruleList = omsBaseService.findListByCond(new EcmAppValrule());	
    	return ruleList;	
    }
    /**
     * 查找应用升级当前节点信息
     * @param int    appId                      应用ID 		     
     */
    public List<EcmAppUpginfo>  getAppUpginfoList(Integer appId){
    	Map<String,Object> newNode = new HashMap<String,Object>();
		newNode.put("appId",appId);
		List<EcmAppUpginfo> infoList = omsBaseService.findListByCond(new EcmAppUpginfo(), "findByAppId", newNode);
        return infoList;	
    }
}
