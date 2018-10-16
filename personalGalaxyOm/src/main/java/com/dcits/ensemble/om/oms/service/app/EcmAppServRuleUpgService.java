package com.dcits.ensemble.om.oms.service.app;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import com.dcits.galaxy.base.exception.GalaxyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.dao.utils.OMSIDao;

import com.dcits.ensemble.om.oms.manager.app.UpgNodeCache;
import com.dcits.ensemble.om.oms.manager.dubbo.ServiceRouterHandler;
import com.dcits.ensemble.om.oms.module.app.EcmAppUpginfo;
import com.dcits.ensemble.om.oms.module.app.EcmAppValrule;
import com.dcits.dynamic.web.util.dao.PkServiceUtil;


/**
 * EcmAppServRuleUpgService*
 * @author wangbinaf
 * @date 2016-03-29
 */
@Service
public class EcmAppServRuleUpgService {
	private  final Logger log = LoggerFactory.getLogger(this.getClass());
	@Resource
	OMSIDao omsBaseDao;
	@Resource
	EcmAppUpgforService ecmAppUpgforService;
	@Resource
	PkServiceUtil  serviceUtil;
	@Resource
	UpgNodeCache upgNodeCache;
	@Resource
	ServiceRouterHandler serviceRouterHandler;
	private int max_valRule_Id = 0;

  /**
	* 先升级新增路由规则
    * @param    Integer appId  实例应用id
    * @param    String servRuleIdList 路由规则Id字符串
    * @param    String userId 用户Id
	* @return   List<EcmServRule>
	*/
	public void saveEarlyValrule(String servRuleIdList,Integer appId,String userId){
		String ruleList =servRuleIdList.substring(1,servRuleIdList.length()-1);
		String [] servRuleList = ruleList.split(",");
		EcmAppValrule valrule= new EcmAppValrule();
		EcmAppUpginfo list = findAppUpginfo(appId);
		serviceRouterHandler.addEarlyServiceRouterRule(appId, userId, ruleList);
		for (String   servRuleId :servRuleList  ){
			int appValruleId=serviceUtil.getMaxReqID(max_valRule_Id,"ECM_APP_VALRULE","APP_VALRULE_ID");
			valrule.setAppValruleId(appValruleId);
			valrule.setServRuleId(Integer.parseInt(servRuleId));
			valrule.setAppUpgId(list.getAppUpgId());
			valrule.setAppValruleType(SysConfigConstants.APP_VALRULE_EARLY);//应用升级验证规则类型--先升级验证规则
			omsBaseDao.insert(valrule);
		}
	}
	 /**
		* 后升级新增路由规则
	    * @param    Integer appId  实例应用id
	    * @param    String servRuleIdList 路由规则Id字符串
	    * @param    String userId 用户Id
		* @return   List<EcmServRule>
		*/
	public void saveLateValrule(String servRuleIdList,Integer appId ,String userId){
		String ruleList =servRuleIdList.substring(1,servRuleIdList.length()-1);
		String [] servRuleList = ruleList.split(",");
		EcmAppValrule valrule= new EcmAppValrule();
		EcmAppUpginfo list = findAppUpginfo(appId);
		serviceRouterHandler.addLateServiceRouterRule(appId, userId, ruleList);
		for (String   servRuleId :servRuleList  ){
			int appValruleId=serviceUtil.getMaxReqID(max_valRule_Id,"ECM_APP_VALRULE","APP_VALRULE_ID");
			valrule.setAppValruleId(appValruleId);
			valrule.setServRuleId(Integer.parseInt(servRuleId));
			valrule.setAppUpgId(list.getAppUpgId());
			valrule.setAppValruleType(SysConfigConstants.APP_VALRULE_LATE);//应用升级验证规则类型--后升级验证规则
			omsBaseDao.insert(valrule);
		}
	}
	/**
	 * 删除单个先升级路由规则
	 * @param    EcmAppValrule appValrule  应用升级验证规则对象
	 * @param    Integer appId  实例应用id
	 * @param    String userId 用户Id
	 * @return   List<EcmServRule>
	 */
	public void deleteEarlyValrule(EcmAppValrule appValrule,Integer appId,String userId){
		   serviceRouterHandler.cancelEarlyServiceRouterRule(appId, userId, "" + appValrule.getServRuleId());
		   omsBaseDao.deleteByPrimaryKey(appValrule);
	}
	/**
	 * 删除单个后升级路由规则
	 * @param    Integer appId  实例应用id
	 * @param    String servRuleIdList 路由规则Id字符串
	 * @return   List<EcmServRule>
	 */
	public void deleteLateValrule(EcmAppValrule appValrule,Integer appId,String userId){
		   serviceRouterHandler.cancelLateServiceRouterRule(appId, userId);
		   omsBaseDao.deleteByPrimaryKey(appValrule);
	}
		/**
		* 查询先验证路由规则
	    * @param    Integer appId  实例应用id
	    * @param    String appSersource    应用服务获取的方式，取值有：全部，上一升级规则，先升级规则
	    * @param    String appSerClassName    应用服务类名
		* @return   List<findInServuleId>
		*/
	public List<EcmAppValrule>  findEarlyAppValRule(String appSerClassName,String appSersource, Integer appId ){
		Map<String,Object> object = new HashMap<String,Object>();
		EcmAppUpginfo list = findAppUpginfo(appId);
		object.put("appUpgId",list.getAppUpgId());
		object.put("appValruleType",SysConfigConstants.APP_VALRULE_EARLY);
	    List<EcmAppValrule> valruleList = omsBaseDao.findListByCond(new EcmAppValrule(), "findByValrule", object);
		Map<String,Object> queryMap = new HashMap<String,Object>();
		StringBuilder ruleListStr = new StringBuilder();
		log.info("valruleList="+valruleList);
		if(valruleList.size()>0){
			for(EcmAppValrule rule:valruleList){
				   ruleListStr.append(rule.getServRuleId()+",");
			}
			queryMap.put("appValRuleList",ruleListStr.substring(0,ruleListStr.length()-1));
		}
		if(SysConfigConstants.APPCONF_EARLYRULE_BACK.equals(appSersource)){
			EcmAppUpginfo result = findLastUpginfo(appId);
			queryMap.put("lastAppUpgId",result.getAppUpgId());
		}
		queryMap.put("appSerClassName",appSerClassName);
		queryMap.put("appSerType",SysConfigConstants.APP_SER_TYPE_CHECKED);
		System.out.println("queryMap:" + queryMap);
		List<EcmAppValrule> ruleList = omsBaseDao.findListByCond(new EcmAppValrule(), "findAllRule",queryMap);
		return ruleList;
	}
		/**
		* 查询后验证路由规则
	    * @param    Integer appId  实例应用id
	    * @param    String appSersource    应用服务获取的方式，取值有：全部，上一升级规则，先升级规则
	    * @param    String appSerClassName    应用服务类名
		* @return   List<findInServuleId>
		*/
		public List<EcmAppValrule>  findLateAppValRule(String appSerClassName,String appSersource, Integer appId ){
			EcmAppUpginfo  list = findAppUpginfo(appId);
		    Map<String,Object> object = new HashMap<String,Object>();
		    object.put("appUpgId",list.getAppUpgId());
		    object.put("appValruleType",SysConfigConstants.APP_VALRULE_LATE);
	    	List<EcmAppValrule> valruleList = omsBaseDao.findListByCond(new EcmAppValrule(), "findByValrule", object);
			Map<String,Object> queryMap = new HashMap<String,Object>();
		    StringBuilder ruleListStr = new StringBuilder();
			log.info("valruleList="+valruleList);
			if(valruleList.size()>0){
			   for(EcmAppValrule rule:valruleList){
				   ruleListStr.append(rule.getServRuleId()+",");
			   }
			   queryMap.put("appValRuleList",ruleListStr.substring(0,ruleListStr.length()-1));
			 }
			 if(SysConfigConstants.APPCONF_LATERULE_EARLY.equals(appSersource)){
			     EcmAppUpginfo  result = findAppUpginfo(appId);
				 queryMap.put("lastAppUpgId",result.getAppUpgId());
			 }
			queryMap.put("appSerType",SysConfigConstants.APP_SER_TYPE_CHECKED);
			 queryMap.put("appSerClassName",appSerClassName);
			 List<EcmAppValrule> ruleList = omsBaseDao.findListByCond(new EcmAppValrule(), "findAllRule",queryMap);
			 return ruleList;

		}
		/**
		* 查询先验证路由规则
	    * @param    Integer appId  实例应用id
		* @return   List<findInServuleId>
		*/
		public List<EcmAppValrule> findEarlyRule(Integer appId ){
			EcmAppUpginfo result = findAppUpginfo(appId);
			log.info("result  findAppUpginfo"+result);
			Map<String,Object> object = new HashMap<String,Object>();
			object.put("appUpgId",result.getAppUpgId());
			object.put("appValruleType",SysConfigConstants.APP_VALRULE_EARLY);
			object.put("appSerType",SysConfigConstants.APP_SER_TYPE_CHECKED);
	  		List<EcmAppValrule> ruleList = omsBaseDao.findListByCond(new EcmAppValrule(),"findByValruleType",object);
	  		return ruleList;
			}
		/**
		* 查询后验证路由规则
	    * @param    Integer appId  实例应用id
		* @return   List<findInServuleId>
		*/
		public List<EcmAppValrule> findLateRule(Integer appId ){
			EcmAppUpginfo result = findAppUpginfo(appId);
			Map<String,Object> object = new HashMap<String,Object>();
			object.put("appUpgId",result.getAppUpgId());
			object.put("appValruleType",SysConfigConstants.APP_VALRULE_LATE);
			object.put("appSerType",SysConfigConstants.APP_SER_TYPE_CHECKED);
	  		List<EcmAppValrule> ruleList = omsBaseDao.findListByCond(new EcmAppValrule(),"findByValruleType",object);
	  		return ruleList;
			}
		/**
		* 查找正在升级的流程信息
	    * @param    Integer appId  实例应用id
		* @return   List<findInServuleId>
		*/
		public EcmAppUpginfo  findAppUpginfo (Integer appId) {
            Map<String, Object> newNode = new HashMap<String, Object>();
            newNode.put("appUpgStatus", SysConfigConstants.APP_UPGSTATUS_PROGRESS);
            newNode.put("appId", appId);
            List<EcmAppUpginfo> infoList = omsBaseDao.findListByCond(new EcmAppUpginfo(), "findUpdateNode", newNode);
            if (infoList.size() > 0) {
                return infoList.get(0);
            } else {
                return new EcmAppUpginfo();
            }
        }
		/**
		* 查找上一次升级的流程信息
	    * @param    Integer appId  实例应用id
		* @return   List<findInServuleId>
		*/
		public  EcmAppUpginfo findLastUpginfo (Integer appId){
			Map<String,Object> newNode = new HashMap<String,Object>();
			newNode.put("appUpgStatus",SysConfigConstants.APP_UPGSTATUS_SUCCEED);
			newNode.put("appId",appId);
			List<EcmAppUpginfo> infoList = omsBaseDao.findListByCond(new EcmAppUpginfo(), "findUpdateNode", newNode);
			if(infoList.size()>0){
			   return infoList.get(0);
			}else{
			   throw new GalaxyException("当前升级是第一次，不存在上一次升级流程!");
			}
	    }
		/**
		* 查找是否存在当前升级的流程
	    * @param    Integer appId  实例应用id
		* @return   List<findInServuleId>
		*/
		public  boolean isExistUpginfo (Integer appId){
			Map<String,Object> newNode = new HashMap<String,Object>();
			newNode.put("appUpgStatus",SysConfigConstants.APP_UPGSTATUS_PROGRESS);
			newNode.put("appId",appId);
			List<EcmAppUpginfo> infoList = omsBaseDao.findListByCond(new EcmAppUpginfo(), "findUpdateNode", newNode);
			if(infoList.size()>0){
			   return true;
			}else{
			   return false;
			}
		}
}
