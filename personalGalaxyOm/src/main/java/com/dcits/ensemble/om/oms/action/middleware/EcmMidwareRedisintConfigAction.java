package com.dcits.ensemble.om.oms.action.middleware;

import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.dynamic.web.util.dao.PkServiceUtil;
import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.dao.utils.OMSIDao;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareRedisint;
import com.dcits.ensemble.om.oms.module.utils.PageData;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import com.dcits.ensemble.om.oms.service.utils.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSON;

/**
 * EcmMidwareRedisintAction* 
 * @author luolang
 * @date 2016-02-23
 */
@Controller
public class EcmMidwareRedisintConfigAction {
	
	private  final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private static final String  FIND_BY_MID = "findByMid";
	
    private static final String  FIND_DEP_LIST = "findDepList";
	private static final String  FIND_REDIS_MEMORY = "findRedisMemory";
	private int max_req_no = 0;
	@Resource
	IService omsBaseService;
	@Resource
	OMSIDao omsBaseDao;
	@Resource
	ParamComboxService paramComboxService;
	@Resource
	PkServiceUtil  serviceUtil;
	@RequestMapping("saveMidwareRedisint")
	public void save(HttpServletRequest request, PrintWriter printWriter,EcmMidwareRedisint midwareRedisint) {
		try{
			if(!isExistRedisIntant(midwareRedisint.getMidwareId())){//如果该集群下的实例都已经部署，则不能再增加实例
				int redisintId= serviceUtil.getMaxReqID(max_req_no,"ecm_midware_redisint","redisint_id");
				midwareRedisint.setRedisintId(redisintId);
				midwareRedisint.setRedisintStatus(SysConfigConstants.APP_INTANTSTATUS_NODEPLOY);
				omsBaseService.insert(midwareRedisint);
				HashMap result = new HashMap();
				result.put("success", "success");
				result.put("id", redisintId);
				String jsonStr = JSON.toJSONString(result);
				printWriter.print(jsonStr);
				printWriter.flush();
				printWriter.close();
			 }else{
				  throw new GalaxyException("该集群下的Redis实例已经部署，不能增加，请先清理!");
			  }
		//	ActionResultWrite.setsuccessfulResult(printWriter);



		}catch (Exception e) {
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	 
	@RequestMapping("updateMidwareRedisint")
	public void update(HttpServletRequest request, PrintWriter printWriter,EcmMidwareRedisint midwareRedisint) {
		try {
			if(isExistRedisIntant(midwareRedisint.getMidwareId())){//如果已部署，则Redisint的某些实例的属性是不能修改的
			   if(!isUpdateRedisint(midwareRedisint)){
				   omsBaseService.updateByPrimaryKey(midwareRedisint);
			   }else{
				   throw new GalaxyException("该Redis实例已经部署，禁止修改该Redis实例信息!");
			   }
		     }else{
		           omsBaseService.updateByPrimaryKey(midwareRedisint);
		     }
			  ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e) {
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
	 }
			
	}
	
	@RequestMapping("deleteMidwareRedisint")
	public void delete(HttpServletRequest request, PrintWriter printWriter,EcmMidwareRedisint midwareRedisint) {
		try {
			if(!isExistRedisIntant(midwareRedisint.getMidwareId())){//如果已部署，则不能删除Redisint
				 omsBaseService.deleteByPrimaryKey(midwareRedisint);
			     ActionResultWrite.setsuccessfulResult(printWriter);
			}
        }catch (Exception e) {
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
        }
	}
	@RequestMapping("findMidwareRedisint")
	public void find(HttpServletRequest request,PrintWriter printWriter,EcmMidwareRedisint midwareRedisint) {
		try {
			int pageNo = ServletRequestUtils.getIntParameter(request, "page", 1);
			int pageSize = ServletRequestUtils.getIntParameter(request, "rows",10);
			log.info("pageNo ="+pageNo +"pageSize ="+ pageSize);
			PageData<EcmMidwareRedisint> pageData =omsBaseService.findPageByCond(midwareRedisint, pageNo, pageSize);
			ActionResultWrite.setPageReadResult(printWriter,magicList(pageData.getList()),pageData.getTotalNum());
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}

	@RequestMapping("findRedisMemory")
	public void findRedisMemory(HttpServletRequest request,PrintWriter printWriter) {
		try {
			String masterName = request.getParameter("masterName");
			String redisintNodeNum = masterName.split("ter")[1];
			Map<String,Object>  queryMap = new HashMap<String,Object>();
			queryMap.put("midwareId",  request.getParameter("midwareId"));
			queryMap.put("redisintType",  SysConfigConstants.REDIS_MASTER);
			queryMap.put("redisintNodeNum", redisintNodeNum);
			log.info("queryMap="+queryMap);
			List <EcmMidwareRedisint>   redisMemory=omsBaseDao.findListByCond(new EcmMidwareRedisint(), FIND_REDIS_MEMORY, queryMap);
			if(redisMemory.size()!=0){
				ActionResultWrite.setReadResult(printWriter, redisMemory.get(0));
			}
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	private List<EcmMidwareRedisint> magicList(List<EcmMidwareRedisint> list){
		for(EcmMidwareRedisint midwareRedisint:list){
			if(null!=midwareRedisint.getRedisintMemory()) {
				midwareRedisint.setRedisintMemoryUnit(midwareRedisint.getRedisintMemory().toString() +"G");//给该字段拼接单位
			}
			midwareRedisint.setRedisintStatusName(paramComboxService.getParaName(midwareRedisint.getRedisintStatus()));
			midwareRedisint.setRedisintTypeName(paramComboxService.getParaName(midwareRedisint.getRedisintType()));
		}
		return list;
	}
	//判断是否修改了Redis实例           false 未修改 ；true  修改  ,只有名称和描述可以修改，其余的属性都不能修改
	private boolean isUpdateRedisint(EcmMidwareRedisint intant){
		Map<String,Object> conMap = new HashMap<String,Object>();
		conMap.put("redisintId",intant.getRedisintId());
		EcmMidwareRedisint oldRedisint=omsBaseDao.findListByCond(new EcmMidwareRedisint(),FIND_BY_MID,conMap).get(0);//通过主键查不出所有信息，所以调用该查询方法
		if(!intant.getMidwareId().equals(oldRedisint.getMidwareId())){
			return true;
		}
		if(!intant.getSerId().equals(oldRedisint.getSerId())){
			return true;
		}
		if(!intant.getRedisintType().equals(oldRedisint.getRedisintType())){
			return true;
		}
		if(!intant.getRedisintPort().equals(oldRedisint.getRedisintPort())){
			return true;
		}
		if(!intant.getRedisintNodeNum().equals(oldRedisint.getRedisintNodeNum())){
			return true;
		}
		if(!intant.getRedisintMemory().equals(oldRedisint.getRedisintMemory())){
			return true;
		}
		return false;
	}	
	//判断该集群下的实例是否部署,   false 未部署 ；true  部署  
	private  boolean isExistRedisIntant(Integer   midwareId){	
		Map<String,Object> conMap = new HashMap<String,Object>();
		conMap.put("midwareId",midwareId);
		List <EcmMidwareRedisint>   oldRedisint=omsBaseDao.findListByCond(new EcmMidwareRedisint(),FIND_DEP_LIST,conMap);
		log.info("oldRedisint"+oldRedisint);
	    for(EcmMidwareRedisint temp :oldRedisint){
	    	if(!temp.getRedisintStatus().equals(SysConfigConstants.APP_INTANTSTATUS_NODEPLOY)){
	    		return  true;
	    	}
	    }
		return false;
    }	
	
}
