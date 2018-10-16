package com.dcits.ensemble.om.oms.action.serv;

import com.alibaba.fastjson.JSON;
import com.dcits.dynamic.web.mapper.PkList;
import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.dynamic.web.util.dao.PkServiceUtil;
import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.ensemble.om.oms.module.serv.EcmSerMtdinfo;
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
 * EcmSerMtdinfoAction* 
 * @author LuoLang
 * @date 2016-03-8
 */
@Controller
public class EcmSerMtdinfoAction {
	

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
	
	@RequestMapping("saveEcmSerMtdinfo")
	public void save(HttpServletRequest request, PrintWriter printWriter,EcmSerMtdinfo serMtdinfo,String appSerId) {
		try {
			int serMtdId=serviceUtil.getMaxReqID(max_req_no,"ECM_SER_MTDINFO","SER_MTD_ID");
			serMtdinfo.setSerMtdId(serMtdId);
			serMtdinfo.setAppSerId(Integer.parseInt(appSerId));
			omsBaseService.insert(serMtdinfo);
		//	ActionResultWrite.setsuccessfulResult(printWriter);
			HashMap result = new HashMap();
			result.put("success", "success");
			result.put("id", serMtdId);
			String jsonStr = JSON.toJSONString(result);
			printWriter.print(jsonStr);
			printWriter.flush();
			printWriter.close();

		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	
	@RequestMapping("updateEcmSerMtdinfo")
	public void update(HttpServletRequest request, PrintWriter printWriter,EcmSerMtdinfo serMtdinfo,String serMtdId) {
		try {
			serMtdinfo.setSerMtdId(Integer.parseInt(serMtdId));
			if(servManagerService.toEcmSerMtdinfo(serMtdinfo.getSerMtdId())) {
				omsBaseService.updateByPrimaryKey(serMtdinfo);
			}else {
				throw new GalaxyException("存在状态为正在升级的实例在使用此条服务方法，禁止修改!");
			}
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	
	@RequestMapping("deleteEcmSerMtdinfo")
	public void delete(HttpServletRequest request, PrintWriter printWriter,EcmSerMtdinfo serMtdinfo) {
		try {
			if(servManagerService.toEcmSerMtdinfo(serMtdinfo.getSerMtdId())) {
				omsBaseService.deleteByPrimaryKey(serMtdinfo);
			}else {
				throw new GalaxyException("存在状态为正在升级的实例在使用此条服务方法，禁止删除!");
			}
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	
	@RequestMapping("findEcmSerMtdinfo")
    public void findRole(HttpServletRequest request, PrintWriter printWriter ,EcmSerMtdinfo serMtdinfo) {
		try {
			int pageNo = ServletRequestUtils.getIntParameter(request, "page", 1);
			int pageSize = ServletRequestUtils.getIntParameter(request, "rows",10);
			log.info("pageNo ="+pageNo +"pageSize ="+ pageSize);
			PageData<EcmSerMtdinfo> pageData =omsBaseService.findPageByCond(serMtdinfo,pageNo,pageSize);
			ActionResultWrite.setReadResult(printWriter,pageData.getList());
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	 @RequestMapping("findEcmSerMtdinfoCombox") 
		public void findAppSer(HttpServletRequest request, PrintWriter printWriter) {
			List<EcmSerMtdinfo> infoList = omsBaseService.findListByCond(new EcmSerMtdinfo(),"findComboxList" ,new HashMap<String,Object>());
		    List<PkList> pkLists = new ArrayList<>();
		    for (int i=0;i<infoList.size();i++){
				PkList pkList = new PkList();
				pkList.setPkDesc(infoList.get(i).getSerMtdCnm());
				pkList.setPkValue(infoList.get(i).getSerMtdId().toString());
				pkLists.add(pkList);
			}
			 printWriter.print(JSON.toJSONString(pkLists));
			 printWriter.flush();
			 printWriter.close();
		}
}
