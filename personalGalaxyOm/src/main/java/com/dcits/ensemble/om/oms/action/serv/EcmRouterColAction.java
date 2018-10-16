package com.dcits.ensemble.om.oms.action.serv;

import com.alibaba.fastjson.JSON;
import com.dcits.dynamic.web.mapper.PkList;
import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.dynamic.web.util.dao.PkServiceUtil;
import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.ensemble.om.oms.module.serv.EcmRouterCol;
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
 * EcmRouterColAction* 
 * @author LuoLang
 * @date 2016-03-8
 */
@Controller
public class EcmRouterColAction {
	
	private  final Logger log = LoggerFactory.getLogger(this.getClass());
 
	private int max_req_no = 0;
	
	@Resource
	ParamComboxService  paramComboxService;	
	@Resource
	PkServiceUtil  serviceUtil;
	@Resource
	EcmServManagerService servManagerService;
	@Resource
	IService  omsBaseService;
	
	@RequestMapping("saveEcmRouterCol")
	public void save(HttpServletRequest request, PrintWriter printWriter,EcmRouterCol routerCol) {	
		log.info("" + routerCol);
		try {
			int routerColId=serviceUtil.getMaxReqID(max_req_no,"ECM_ROUTER_COL","ROUTER_COL_ID");
			routerCol.setRouterColId(routerColId);
			omsBaseService.insert(routerCol);
			//ActionResultWrite.setsuccessfulResult(printWriter);
			HashMap result = new HashMap();
			result.put("success", "success");
			result.put("id", routerColId);
			String jsonStr = JSON.toJSONString(result);
			printWriter.print(jsonStr);
			printWriter.flush();
			printWriter.close();

		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	

	@RequestMapping("updateEcmRouterCol")
	public void update(HttpServletRequest request, PrintWriter printWriter,EcmRouterCol routerCol,String routerColId) {
		try {
			if(servManagerService.toEcmRouterCol(routerCol.getRouterColId())) {
				routerCol.setRouterColId(Integer.parseInt(routerColId));
				omsBaseService.updateByPrimaryKey(routerCol);
			}else {
				throw new GalaxyException("存在状态为正在升级的实例在使用此路由字段，禁止修改!");
			}
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	
	
	@RequestMapping("deleteEcmRouterCol")
	public void delete(HttpServletRequest request, PrintWriter printWriter,EcmRouterCol routerCol) {
		try {
			if(servManagerService.toEcmRouterCol(routerCol.getRouterColId())) {
				omsBaseService.deleteByPrimaryKey(routerCol);
			}else {
				throw new GalaxyException("存在状态为正在升级的实例在使用此路由字段，禁止删除!");
			}
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	@RequestMapping("findRouterCol")
	public void findRoleCol(HttpServletRequest request, PrintWriter printWriter) {
		try {
			List<EcmRouterCol> infoList = omsBaseService.findListByCond(new EcmRouterCol(), new HashMap<String, Object>());
			List<EcmRouterCol> routerCols  = codeToName(infoList);
			ActionResultWrite.setReadResult(printWriter,codeToName(routerCols));
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	@RequestMapping("findEcmRouterCol")
    public void findRole(HttpServletRequest request, PrintWriter printWriter) {
	   List<EcmRouterCol> infoList = omsBaseService.findListByCond(new EcmRouterCol(),new HashMap<String,Object>() );
	    List<EcmRouterCol> routerCols  = codeToName(infoList);
		List<PkList> pkLists = new ArrayList<>();
		for (int i=0;i<routerCols.size();i++){
			PkList pkList = new PkList();
			pkList.setPkDesc(routerCols.get(i).getRouterColCn());
			pkList.setPkValue(routerCols.get(i).getRouterColId().toString());
			pkLists.add(pkList);
		}
		printWriter.print(JSON.toJSONString(pkLists));
		printWriter.flush();
		printWriter.close();
	}
	
	private List<EcmRouterCol> codeToName(List<EcmRouterCol> infoList) {
		for (EcmRouterCol routerCol :infoList) {
			routerCol.setRouterColTypeName(paramComboxService.getParaName(routerCol.getRouterColType()));
		}
		return infoList;
	}
	
}
