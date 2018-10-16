package com.dcits.ensemble.om.oms.action.middleware;

import com.alibaba.fastjson.JSON;
import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.dynamic.web.util.dao.PkServiceUtil;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareInfo;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareKafkaint;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import com.dcits.ensemble.om.oms.service.utils.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * EcmMidwareKafkaintConfigAction*
 * @author luolang
 * @date 2016-12-12
 */
@Controller
public class EcmMidwareKafkaintConfigAction {
	
	private  final Logger log = LoggerFactory.getLogger(this.getClass());
	private int max_req_no = 0;
	@Resource
	IService omsBaseService;

	@Resource
	ParamComboxService paramComboxService;
	@Resource
	PkServiceUtil  serviceUtil;
	@RequestMapping("saveMidwareKafkaint")
	public void save(HttpServletRequest request, PrintWriter printWriter,EcmMidwareKafkaint midwareKafkaint) {
		try{
			int kafkaintId= serviceUtil.getMaxReqID(max_req_no,"ecm_midware_kafkaint","kafkaint_id");
			midwareKafkaint.setKafkaintId(kafkaintId);
			midwareKafkaint.setKafkaintStatus(SysConfigConstants.APP_INTANTSTATUS_NODEPLOY);
			omsBaseService.insert(midwareKafkaint);
			HashMap result = new HashMap();
			result.put("success", "success");
			result.put("id", kafkaintId);
			String jsonStr = JSON.toJSONString(result);
			printWriter.print(jsonStr);
			printWriter.flush();
			printWriter.close();
		}catch (Exception e) {
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	 
	@RequestMapping("updateMidwareKafkaint")
	public void update(HttpServletRequest request, PrintWriter printWriter,EcmMidwareKafkaint midwareKafkaint) {
		try {
			 omsBaseService.updateByPrimaryKey(midwareKafkaint);
			 ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e) {
			 e.printStackTrace();
			 ActionResultWrite.setErrorResult(printWriter,e.getMessage());
	    }
			
	}
	
	@RequestMapping("deleteMidwareKafkaint")
	public void delete(HttpServletRequest request, PrintWriter printWriter,EcmMidwareKafkaint midwareKafkaint) {
		try {
			 omsBaseService.deleteByPrimaryKey(midwareKafkaint);
			 ActionResultWrite.setsuccessfulResult(printWriter);
        }catch (Exception e) {
			 e.printStackTrace();
			 ActionResultWrite.setErrorResult(printWriter,e.getMessage());
        }
	}
	@RequestMapping("findMidwareKafkaint")
	public void find(HttpServletRequest request,PrintWriter printWriter,EcmMidwareKafkaint midwareKafkaint) {
		try {
			 List<EcmMidwareKafkaint> kafkaintList =omsBaseService.findListByCond(midwareKafkaint);
		 	 ActionResultWrite.setReadResult(printWriter, magicList(kafkaintList));
		}catch (Exception e){
			 e.printStackTrace();
			 ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	private List<EcmMidwareKafkaint> magicList(List<EcmMidwareKafkaint> list){
		List<EcmMidwareInfo> midwareList = omsBaseService.findListByCond(new EcmMidwareInfo(), "findMidwareList", new HashMap<String, Object>());
		Map<Integer, String> midwareMap = new HashMap<Integer, String>();
		for (EcmMidwareInfo midware : midwareList) {
			midwareMap.put(midware.getMidwareId(), midware.getMidwareName());
		}
        for(EcmMidwareKafkaint midwareKafkaint : list) {
			midwareKafkaint.setKafkaintStatusName(paramComboxService.getParaName(midwareKafkaint.getKafkaintStatus()));
			midwareKafkaint.setZkMidwareName(midwareMap.get(midwareKafkaint.getZkMidwareId()));
		}
		return list;
	}


}
