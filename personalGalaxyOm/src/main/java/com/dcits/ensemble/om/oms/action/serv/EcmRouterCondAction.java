package com.dcits.ensemble.om.oms.action.serv;

import com.alibaba.fastjson.JSON;
import com.dcits.dynamic.web.mapper.PkList;
import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.dynamic.web.util.dao.PkServiceUtil;
import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.ensemble.om.oms.module.serv.EcmRouterCond;
import com.dcits.ensemble.om.oms.service.serv.EcmServManagerService;
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

/**
 * EcmRouterCondAction* 
 * @author LuoLang
 * @date 2016-03-8
 */
@Controller
public class EcmRouterCondAction {
	
	private  final Logger log = LoggerFactory.getLogger(this.getClass());
 
	private int max_req_no = 0;
	
	@Resource
	PkServiceUtil  serviceUtil;
	@Resource
	EcmServManagerService servManagerService;
	@Resource
	IService  omsBaseService;
	
	@Resource
	ParamComboxService  paramComboxService;	
	@RequestMapping("saveEcmRouterCond")
	public void save(HttpServletRequest request, PrintWriter printWriter,EcmRouterCond routerCond,String routerColId) {
		log.info("" + routerCond);
		try {
			int routerCondId=serviceUtil.getMaxReqID(max_req_no,"ECM_ROUTER_COND","ROUTER_COND_ID");
			routerCond.setRouterCondId(routerCondId);
			routerCond.setRouterColId(Integer.parseInt(routerColId));
			omsBaseService.insert(routerCond);
			//ActionResultWrite.setsuccessfulResult(printWriter);
			HashMap result = new HashMap();
			result.put("success", "success");
			result.put("id", routerCondId);
			String jsonStr = JSON.toJSONString(result);
			printWriter.print(jsonStr);
			printWriter.flush();
			printWriter.close();

		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	

	@RequestMapping("updateEcmRouterCond")
	public void update(HttpServletRequest request, PrintWriter printWriter,EcmRouterCond routerCond,String routerCondId) {
		try {
			routerCond.setRouterColId(Integer.parseInt(routerCondId));
			if(servManagerService.toEcmRouterCond(routerCond.getRouterCondId())) {
				omsBaseService.updateByPrimaryKey(routerCond);
			}else {
				throw new GalaxyException("存在状态为正在升级的实例在使用此路由条件，禁止修改!");
			}
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}

	}
	
	
	@RequestMapping("deleteEcmRouterCond")
	public void delete(HttpServletRequest request, PrintWriter printWriter,EcmRouterCond routerCond) {
		try {
			if(servManagerService.toEcmRouterCond(routerCond.getRouterCondId())) {
				omsBaseService.deleteByPrimaryKey(routerCond);
			}else {
				throw new GalaxyException("存在状态为正在升级的实例在使用此路由条件，禁止删除!");
			}
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	@RequestMapping("findRouterCond")
	public void findRouterCond(HttpServletRequest request, PrintWriter printWriter) {
		try {
			List<EcmRouterCond> infoList = omsBaseService.findListByCond(new EcmRouterCond(),new HashMap<String,Object>() );
			ActionResultWrite.setReadResult(printWriter,magicList(infoList));
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	@RequestMapping("findEcmRouterCond")
    public void findRole(HttpServletRequest request, PrintWriter printWriter) {
	   List<EcmRouterCond> infoList = omsBaseService.findListByCond(new EcmRouterCond(),new HashMap<String,Object>() );		
	   List<EcmRouterCond> routerConds = magicList(infoList);
		List<PkList> pkLists = new ArrayList<>();
		for (int i=0;i<routerConds.size();i++){
			PkList pkList = new PkList();
			pkList.setPkDesc(routerConds.get(i).getRouterCondName());
			pkList.setPkValue(routerConds.get(i).getRouterCondId().toString());
			pkLists.add(pkList);
		}
		printWriter.print(JSON.toJSONString(pkLists));
		printWriter.flush();
		printWriter.close();
   }
	private List<EcmRouterCond> magicList(List<EcmRouterCond> list){
		for(EcmRouterCond  routerCond:list){
			routerCond.setRouterCondOperName(paramComboxService.getParaName(routerCond.getRouterCondOper()));
		}
		return list;
	}
}
