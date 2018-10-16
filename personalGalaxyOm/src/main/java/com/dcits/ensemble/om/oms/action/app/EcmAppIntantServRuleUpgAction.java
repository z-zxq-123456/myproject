package com.dcits.ensemble.om.oms.action.app;

import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.dynamic.web.util.dao.PkServiceUtil;
import com.dcits.ensemble.om.oms.common.DataUtil;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.dao.utils.OMSIDao;
import com.dcits.ensemble.om.oms.module.app.EcmAppValrule;
import com.dcits.ensemble.om.oms.service.app.EcmAppServRuleUpgService;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import com.dcits.ensemble.om.oms.service.utils.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * EcmAppIntantServRuleUpgAction* 
 * @author wangbinaf
 * @date 2016-03-29
 */
@Controller
public class EcmAppIntantServRuleUpgAction {
	private  final Logger log = LoggerFactory.getLogger(this.getClass());
	@Resource
	IService  omsBaseService;
	@Resource
	PkServiceUtil  serviceUtil;
	@Resource
	ParamComboxService  paramComboxService;	
	@Resource
	EcmAppServRuleUpgService   ecmAppServRuleUpgService;
	@Resource
	private OMSIDao omsBaseDao;
	//先升级新增路由验证规则
	@RequestMapping("saveEarlyValrule")
	public void saveEarly(HttpServletRequest request, PrintWriter printWriter,String servRuleIdList,  String appId ) {
		try {
			String userId = (String) request.getSession().getAttribute("UserName");
			ecmAppServRuleUpgService.saveEarlyValrule(servRuleIdList,Integer.parseInt(appId),userId);
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	//后升级新增路由验证规则
	@RequestMapping("saveLateAppValRule")
	public void saveLate(HttpServletRequest request, PrintWriter printWriter,String servRuleIdList,  String appId ) {
		try {
			String userId = (String) request.getSession().getAttribute("UserName");
			ecmAppServRuleUpgService.saveLateValrule(servRuleIdList, Integer.parseInt(appId), userId);
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	//删除路由验证规则
	@RequestMapping("deleteEcmAppValRule")
	public void delete(HttpServletRequest request, PrintWriter printWriter,EcmAppValrule appValrule,  String appId) {
		try {
			log.info("appValrule="+appValrule+"    "+"appId="+appId);
			String userId = (String) request.getSession().getAttribute("UserName");
			if(appValrule.getAppValruleType().equals(SysConfigConstants.APP_VALRULE_EARLY)){
				ecmAppServRuleUpgService.deleteEarlyValrule(appValrule, Integer.parseInt(appId), userId);
			}else if(appValrule.getAppValruleType().equals(SysConfigConstants.APP_VALRULE_LATE)){
				ecmAppServRuleUpgService.deleteLateValrule(appValrule, Integer.parseInt(appId), userId);
			}
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
  //在点击新增时查询先验证路由规则
   @RequestMapping("findEarlyAppValRule")
   public void findEarlyAppValRule(HttpServletRequest request, PrintWriter printWriter,EcmAppValrule  ecmAppValrule,String appId) {
	   try {
		   List<EcmAppValrule> ruleList = ecmAppServRuleUpgService.findEarlyAppValRule(ecmAppValrule.getAppSerClassName(), ecmAppValrule.getAppSersource(), Integer.parseInt(appId));
		   log.info("ruleList="+ruleList);
		   ActionResultWrite.setReadResult(printWriter, magicList(ruleList));
	   }catch (Exception e){
		   e.printStackTrace();
		   ActionResultWrite.setErrorResult(printWriter,e.getMessage());
	   }

   }	
   //在点击新增时查询后验证路由规则
   @RequestMapping("findLateAppValRule")
   public void findLateAppValRule(HttpServletRequest request, PrintWriter printWriter,EcmAppValrule  ecmAppValrule,String appId) {
	   try {
		   List<EcmAppValrule> ruleList = ecmAppServRuleUpgService.findLateAppValRule(ecmAppValrule.getAppSerClassName(), ecmAppValrule.getAppSersource(), Integer.parseInt(appId));
		   log.info("ruleList="+ruleList);
		   ActionResultWrite.setReadResult(printWriter,magicList(ruleList));
	   }catch (Exception e){
		   e.printStackTrace();
		   ActionResultWrite.setErrorResult(printWriter,e.getMessage());
	   }
   }	
   //查询先验证实例对应的路由规则
   @RequestMapping("findEarlyRule")
   public void findEarlyRule(HttpServletRequest request, PrintWriter printWriter,String appId) {
	   log.info("appId="+appId);
	   try {
		   List<EcmAppValrule> ruleList = ecmAppServRuleUpgService.findEarlyRule(Integer.parseInt(appId));
		   if (ruleList == null) {
			   ruleList = new ArrayList<>();
		   }
		   log.info("ruleList=" + ruleList);
		   ActionResultWrite.setReadResult(printWriter, magicList(ruleList));
	   } catch (Exception e) {
		   e.printStackTrace();
		   ActionResultWrite.setErrorResult(printWriter, e.getMessage());
	   }
   }
   //查询后验证实例对应的路由规则
   @RequestMapping("findLateRule")
   public void findLateRule(HttpServletRequest request, PrintWriter printWriter,String appId) {
	   try {
		   List<EcmAppValrule> ruleList = ecmAppServRuleUpgService.findLateRule(Integer.parseInt(appId));
		   if(ruleList==null)
           {
               ruleList = new ArrayList<EcmAppValrule>();
           }
		   ActionResultWrite.setReadResult(printWriter,magicList(ruleList));
	   }catch (Exception e){
		   e.printStackTrace();
		   ActionResultWrite.setErrorResult(printWriter,e.getMessage());
	   }
   }
	@RequestMapping("findAllRule")
	public void findAllRule(HttpServletRequest request, PrintWriter printWriter,String appUpgId) {
		try {
			Map<String,Object> object = new HashMap<String,Object>();
			object.put("appUpgId",appUpgId);
			List<EcmAppValrule> ruleList = omsBaseDao.findListByCond(new EcmAppValrule(), "findValruleByUpgId", object);
			if(ruleList==null)
			{
				ruleList = new ArrayList<EcmAppValrule>();
			}
			ActionResultWrite.setReadResult(printWriter,magicList(ruleList));
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}

    private List<EcmAppValrule> magicList(List<EcmAppValrule> ruleList){
		for(EcmAppValrule servRule:ruleList){
			if(DataUtil.isNull(servRule.getServRuleSelf())) {
				servRule.setServRuleSelf("");
			}
			servRule.setServRuleTypeName(paramComboxService.getParaName(servRule.getServRuleType()));
			servRule.setAppValruleTypeName(paramComboxService.getParaName(servRule.getAppValruleType()));

		}
		return ruleList;
	}
}