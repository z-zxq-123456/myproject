package com.dcits.ensemble.om.oms.action.server;

import com.alibaba.fastjson.JSON;
import com.dcits.dynamic.web.mapper.PkList;
import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.dynamic.web.util.dao.PkServiceUtil;
import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.ensemble.om.oms.action.utils.OMSPassWordBase64;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.module.app.EcmAppIntant;
import com.dcits.ensemble.om.oms.module.server.EcmServerInfo;
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
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * EcmAppInfoAction* 
 * @author LuoLang
 * @createdate 2015-9-1
 * @updatetime 2015-9-15 
 */

@Controller
public class EcmServerInfoAction {
	
    @Resource
    PkServiceUtil  serviceUtil;
    
    @Resource
	ParamComboxService  paramComboxService;
    
    @Resource
    IService  omsBaseService;
    
	private  final Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("saveEcmServerInfo")
    public void  save(HttpServletRequest request, PrintWriter printWriter, EcmServerInfo serverInfo)  {
    	int serId=serviceUtil.getMaxReqID(0,"ecm_server_info","ser_id");
    	serverInfo.setSerId(serId);
    	try {
			serverInfo.setSerPwd( OMSPassWordBase64.encode(serverInfo.getSerPwd().getBytes("iso8859-1")));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter, e.getMessage());
		}
    	omsBaseService.insert(serverInfo);
		//ActionResultWrite.setsuccessfulResult(printWriter);
		HashMap result = new HashMap();
		result.put("success", "success");
		result.put("id", serId);
		String jsonStr = JSON.toJSONString(result);
		printWriter.print(jsonStr);
		printWriter.flush();
		printWriter.close();

	}
    
    @RequestMapping("updateEcmServerInfo")
	public void update(HttpServletRequest request, PrintWriter printWriter,EcmServerInfo serverInfo) {	
    	try {
			serverInfo.setSerPwd( OMSPassWordBase64.encode(serverInfo.getSerPwd().getBytes("iso8859-1")));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
    	if(isUpdateSerIp(serverInfo)||isUpdateSerOs(serverInfo)){
	    	  if(isAllAppIntantIsClean(serverInfo)){
	    		  omsBaseService.updateByPrimaryKey(serverInfo);
	    	  }else{
	    		  throw new GalaxyException("该服务器已经部署，禁止修改服务器IP地址、操作系统!");
	    	  }
	    }else{
	    	      omsBaseService.updateByPrimaryKey(serverInfo);
	    }
		ActionResultWrite.setsuccessfulResult(printWriter);
    }		  
    
    @RequestMapping("deleteEcmServerInfo")
	public void delete(HttpServletRequest request, PrintWriter printWriter,EcmServerInfo serverInfo) {
		try {
			if (isAllAppIntantIsClean(serverInfo)) {
				omsBaseService.deleteByPrimaryKey(serverInfo);
			} else {
				throw new GalaxyException("该服务器已部署实例，禁止删除！");
			}
			ActionResultWrite.setsuccessfulResult(printWriter);
		} catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
    @RequestMapping("findEcmServerInfo")
    public void find(HttpServletRequest request, PrintWriter printWriter, EcmServerInfo serverInfo) {
		try {
			int pageNo = ServletRequestUtils.getIntParameter(request, "page", 1);
			int pageSize = ServletRequestUtils.getIntParameter(request, "rows",10);
			log.info("pageNo =" + pageNo + "pageSize =" + pageSize);
			PageData<EcmServerInfo> pageData =omsBaseService.findPageByCond(serverInfo,pageNo,pageSize);
			ActionResultWrite.setReadResult(printWriter, magicList(pageData.getList()));
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}

	private List<EcmServerInfo> magicList(List<EcmServerInfo> list) {
		for(EcmServerInfo serverInfo:list){
			serverInfo.setSerOsName(paramComboxService.getParaName(serverInfo.getSerOs()));
			try {
				String str = new String(OMSPassWordBase64.decode(serverInfo.getSerPwd()), "iso8859-1");
				serverInfo.setSerPwd(str);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
    
    @RequestMapping("findSerCombox")
     public void findService(HttpServletRequest request, PrintWriter printWriter) {
	   Map<String,Object> serverConfig = new HashMap<String,Object>();
	   List<EcmServerInfo> infoList = omsBaseService.findListByCond(new EcmServerInfo(),serverConfig );
		List<PkList> pkLists = new ArrayList<>();
	  for(int i=0;i<infoList.size();i++){
		  PkList pkList = new PkList();
		  pkList.setPkDesc(infoList.get(i).getSerName()+"-"+infoList.get(i).getSerIp());
		  pkList.setPkValue(infoList.get(i).getSerId().toString());
		  pkLists.add(pkList);
	  }
		printWriter.print(JSON.toJSONString(pkLists));
		printWriter.flush();
		printWriter.close();
    }
	@RequestMapping("findSerComboxIp")
	public void findSerComboxIp(HttpServletRequest request, PrintWriter printWriter) {
		Map<String,Object> serverConfig = new HashMap<String,Object>();
		List<EcmServerInfo> infoList = omsBaseService.findListByCond(new EcmServerInfo(),serverConfig );
		List<PkList> pkLists = new ArrayList<>();
		for(int i=0;i<infoList.size();i++){
			PkList pkList = new PkList();
			pkList.setPkDesc(infoList.get(i).getSerIp());
			pkList.setPkValue(infoList.get(i).getSerId().toString());
			pkLists.add(pkList);
		}
		printWriter.print(JSON.toJSONString(pkLists));
		printWriter.flush();
		printWriter.close();
	}
  //判断是否修改了服务器Ip地址true 修改  false 未修改
	private boolean isUpdateSerIp(EcmServerInfo serverInfo){		
		EcmServerInfo serverInfoTmp= omsBaseService.selectByPrimaryKey(serverInfo);
		if(!serverInfo.getSerIp().equals(serverInfoTmp.getSerIp())){
			return true;
		}
		return false;
  	}
  	//判断是否修改了服务器操作系统true 修改  false 未修改
	private boolean isUpdateSerOs(EcmServerInfo serverInfo){		
		EcmServerInfo serverInfoTmp= omsBaseService.selectByPrimaryKey(serverInfo);
		if(!serverInfo.getSerOs().equals(serverInfoTmp.getSerOs())){
			return true;
		}
		return false;
    }
	//判断该服务器是否已经部署有实例 true 未部署  false 已部署
	private boolean isAllAppIntantIsClean(EcmServerInfo serverInfo){		
		EcmAppIntant  intant =new EcmAppIntant();
		intant.setSerId(serverInfo.getSerId());
		List<EcmAppIntant>  intantList=omsBaseService.findListByCond(intant);     	
    	for(EcmAppIntant rowIntant:intantList){    		
    		if(!rowIntant.getAppIntantStatus().equals(SysConfigConstants.APP_INTANTSTATUS_NODEPLOY)){
    				return false;
    		}
    	}
    	return true;
	}
	
}
