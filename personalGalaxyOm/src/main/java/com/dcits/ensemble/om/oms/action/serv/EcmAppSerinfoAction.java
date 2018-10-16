package com.dcits.ensemble.om.oms.action.serv;

import com.alibaba.fastjson.JSON;
import com.dcits.dynamic.web.mapper.PkList;
import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.dynamic.web.util.dao.PkServiceUtil;
import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.ensemble.om.oms.module.serv.EcmAppSerinfo;
import com.dcits.ensemble.om.oms.module.utils.PageData;
import com.dcits.ensemble.om.oms.service.serv.EcmServManagerService;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * EcmRoleAction* 
 * @author LuoLang
 * @date 2016-03-1
 */
@Controller
public class EcmAppSerinfoAction {
	

	private int max_req_no = 0;
	
	private  final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	PkServiceUtil  serviceUtil;
	@Resource
	EcmServManagerService servManagerService;
	@Resource
	ParamComboxService  paramComboxService;	
	@Resource
	IService  omsBaseService;
	
	@RequestMapping("saveEcmAppSer")
	public void save(HttpServletRequest request, PrintWriter printWriter,EcmAppSerinfo appSerinfo) {
		  try {
			  int appSerId=serviceUtil.getMaxReqID(max_req_no,"ECM_APP_SERINFO","APP_SER_ID");
			  appSerinfo.setAppSerId(appSerId);
			  omsBaseService.insert(appSerinfo);
			//  ActionResultWrite.setsuccessfulResult(printWriter);
			  HashMap result = new HashMap();
			  result.put("success", "success");
			  result.put("id", appSerId);
			  String jsonStr = JSON.toJSONString(result);
			  printWriter.print(jsonStr);
			  printWriter.flush();
			  printWriter.close();

		  }catch (Exception e){
			  e.printStackTrace();
			  ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		  }
	}
	
	@RequestMapping("updateEcmAppSer")
	public void update(HttpServletRequest request, PrintWriter printWriter,EcmAppSerinfo appSerinfo,String appSerId) {
		try {
			if(servManagerService.toEcmAppSerinfo(appSerinfo.getAppSerId())) {
				appSerinfo.setAppSerId(Integer.parseInt(appSerId));
				omsBaseService.updateByPrimaryKey(appSerinfo);
			}else {
				throw new GalaxyException("存在状态为正在升级的实例在使用此条应用服务信息，禁止修改!");
			}
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	
	
	@RequestMapping("deleteEcmAppSer")
	public void delete(HttpServletRequest request, PrintWriter printWriter,EcmAppSerinfo appSerinfo) {
		try {
			if(servManagerService.toEcmAppSerinfo(appSerinfo.getAppSerId())) {
				omsBaseService.deleteByPrimaryKey(appSerinfo);
			}else {
				throw new GalaxyException("存在状态为正在升级的实例在使用此条应用服务信息，禁止删除!");
			}
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	
	@RequestMapping("findEcmAppSer")
    public void findRole(HttpServletRequest request, PrintWriter printWriter,EcmAppSerinfo appSerinfo) {
		try {
			int pageNo = ServletRequestUtils.getIntParameter(request, "page", 1);
			int pageSize = ServletRequestUtils.getIntParameter(request, "rows",10);
			log.info("pageNo ="+pageNo +"pageSize ="+ pageSize);
			PageData<EcmAppSerinfo> pageData =omsBaseService.findPageByCond(appSerinfo,pageNo,pageSize);
			ActionResultWrite.setReadResult(printWriter,magicList(pageData.getList()));
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	
	
    private List<EcmAppSerinfo>	magicList(List<EcmAppSerinfo> list ) {
    	for(EcmAppSerinfo appSer:list){
    		appSer.setAppSerTypeName(paramComboxService.getParaName(appSer.getAppSerType()));
		}
		return list;
    }
    
    @RequestMapping("findAppSerCombox") 
	public void findAppSer(HttpServletRequest request, PrintWriter printWriter) {
		List<EcmAppSerinfo> infoList = omsBaseService.findListByCond(new EcmAppSerinfo(),"findComboxList", new HashMap<String,Object>());
		List<PkList> pkLists = new ArrayList<>();
		for (int i=0;i<infoList.size();i++){
			PkList pkList = new PkList();
			pkList.setPkDesc(infoList.get(i).getAppSerName());
			pkList.setPkValue(infoList.get(i).getAppSerId().toString());
			pkLists.add(pkList);
		}
		printWriter.print(JSON.toJSONString(pkLists));
		printWriter.flush();
		printWriter.close();
	}
}
