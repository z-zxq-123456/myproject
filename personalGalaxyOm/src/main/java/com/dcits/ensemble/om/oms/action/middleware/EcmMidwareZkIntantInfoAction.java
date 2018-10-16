package com.dcits.ensemble.om.oms.action.middleware;

import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.dynamic.web.util.dao.PkServiceUtil;
import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.dao.utils.OMSIDao;
import com.dcits.ensemble.om.oms.manager.zookeeper.ZkContainer;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareZkint;
import com.dcits.ensemble.om.oms.module.utils.PageData;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import com.dcits.ensemble.om.oms.service.utils.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import com.alibaba.fastjson.JSON;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ZkIntantInfoAction* 
 * @author WangbinAf
 * @date 2016-02-24
 */
@Controller
public class EcmMidwareZkIntantInfoAction {
	
    private static final String  FIND_BY_MID = "findByMid";
    
	private int max_req_no = 0;
	@Resource
	OMSIDao omsBaseDao;
	@Resource
	PkServiceUtil  serviceUtil;
	@Resource
	IService omsBaseService;
	@Resource
	ParamComboxService paramComboxService;
	@Resource
	ZkContainer zkContainer;
	private  final Logger log = LoggerFactory.getLogger(this.getClass());
	@RequestMapping("saveZkIntantInfo")
	public void save(HttpServletRequest request, PrintWriter printWriter,EcmMidwareZkint midwareZkint) {
		 log.info("midwareZkint="+midwareZkint);
		 try{
			 if(!isExistZkIntant(midwareZkint.getMidwareId())){//如果该集群下的实例都已经部署，则不能再增加实例
			    int zkintId=serviceUtil.getMaxReqID(max_req_no,"ecm_midware_zkint","zkint_id"); //获取当前列表数据最大ID
			    midwareZkint.setZkintStatus(SysConfigConstants.APP_INTANTSTATUS_NODEPLOY);//ZK实例状态初始化
			    midwareZkint.setZkintId(zkintId);
				omsBaseService.insert(midwareZkint);
				 HashMap result = new HashMap();
				 result.put("success", "success");
				 result.put("id", zkintId);
				 String jsonStr = JSON.toJSONString(result);
				 printWriter.print(jsonStr);
				 printWriter.flush();
				 printWriter.close();
			 }else{
				  throw new GalaxyException("该集群下的实例已经部署，不能增加，请先清理!");
			 }
			// ActionResultWrite.setsuccessfulResult(printWriter)

		 }catch (Exception e) {
			 e.printStackTrace();
			 ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		 }	
	}
	
	@RequestMapping("updateZkIntantInfo")
	public void update(HttpServletRequest request, PrintWriter printWriter,EcmMidwareZkint midwareZkint) {
	   log.info("midwareZkint" + midwareZkint);
	   try{
		   if(isExistZkIntant(midwareZkint.getMidwareId())){//如果已部署，则zookeeper的某些实例的属性是不能修改的
			  if(!isUpdateZkIntant(midwareZkint)){
				  omsBaseService.updateByPrimaryKey(midwareZkint);
			  }else{
				  throw new GalaxyException("该ZK实例已经部署，禁止修改该ZK实例信息!");
			  }
		   }else{
		        omsBaseService.updateByPrimaryKey(midwareZkint);
		   }
		   ActionResultWrite.setsuccessfulResult(printWriter);
	   }catch (Exception e) {
		   e.printStackTrace();
		   ActionResultWrite.setErrorResult(printWriter,e.getMessage());
	 }
	}
	
	@RequestMapping("deleteZkIntantInfo")
	public void delete(HttpServletRequest request, PrintWriter printWriter,EcmMidwareZkint midwareZkint) {
	    log.info("midwareZkint" + midwareZkint);
		try {
			if(!isExistZkIntant(midwareZkint.getMidwareId())){//如果已部署，禁止删除该ZK实例信息
				omsBaseService.deleteByPrimaryKey(midwareZkint);
				ActionResultWrite.setsuccessfulResult(printWriter);
			}else{
				throw new GalaxyException("该ZK实例已经部署，禁止删除该ZK实例信息!");
			}
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}

	}
	

	@RequestMapping("findZkIntantInfo")
	public void find(HttpServletRequest request, PrintWriter printWriter ,EcmMidwareZkint midwareZkint) {
		try {
			int pageNo = ServletRequestUtils.getIntParameter(request, "page", 1);
			int pageSize = ServletRequestUtils.getIntParameter(request, "rows",10);
			PageData<EcmMidwareZkint> pageData =omsBaseService.findPageByCond(midwareZkint, pageNo, pageSize);
			ActionResultWrite.setPageReadResult(printWriter, magicList(pageData.getList()), pageData.getTotalNum());
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}

	}
	private List<EcmMidwareZkint> magicList(List<EcmMidwareZkint> list){
		for(EcmMidwareZkint midwareRedisint:list){
			midwareRedisint.setZkintStatusName(paramComboxService.getParaName(midwareRedisint.getZkintStatus()));
		}
		return list;
	}
	//判断是否修改了ZK实例           false 未修改 ；true  修改  只有名称和描述可以修改，其余的属性都不能修改
	private boolean isUpdateZkIntant(EcmMidwareZkint intant){
		Map<String,Object> conMap = new HashMap<String,Object>();
		conMap.put("zkintId",intant.getZkintId());//通过主键查出修改之前的ZK实例（同时在几张表中查出需要对比的属性）
		EcmMidwareZkint oldZkIntant=omsBaseDao.findListByCond(new EcmMidwareZkint(),FIND_BY_MID,conMap).get(0);//其实查出来的实例只有一个
		if(!intant.getSerId().equals(oldZkIntant.getSerId())){
			return true;
		}
		if(!intant.getZkintClientPort().equals(oldZkIntant.getZkintClientPort())){
			return true;
		}
		if(!intant.getZkintCommPort().equals(oldZkIntant.getZkintCommPort())){
			return true;
		}
		if(!intant.getZkintElectPort().equals(oldZkIntant.getZkintElectPort())){
			return true;
		}
		if(!intant.getZkintNodeNum().equals(oldZkIntant.getZkintNodeNum())){
			return true;
		}
		return false;
  }	
	//判断该集群下的实例是否部署,   false  未部署 ；true 部署  
	private  boolean isExistZkIntant(Integer   midwareId){	
		Map<String,Object> conMap = new HashMap<String,Object>();
		conMap.put("midwareId",midwareId);
		List <EcmMidwareZkint>   zkIntant=omsBaseDao.findListByCond(new EcmMidwareZkint(),FIND_BY_MID,conMap);//通过中间件Id查出集群下的所有ZK实例
	    for(EcmMidwareZkint temp :zkIntant){
	    	if(!temp.getZkintStatus().equals(SysConfigConstants.APP_INTANTSTATUS_NODEPLOY)){
	    		return  true ;
	    	}
	    }
		return false;
    }	
}