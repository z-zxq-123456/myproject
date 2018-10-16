package com.dcits.ensemble.om.oms.action.utils;

import com.alibaba.fastjson.JSON;
import com.dcits.dynamic.web.mapper.PkList;
import com.dcits.ensemble.om.oms.module.sys.EcmParam;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


@Controller
public class ParaComboxAction {
	@Resource
	ParamComboxService comService;
	private  final Logger log = LoggerFactory.getLogger(this.getClass());
	@RequestMapping("findParaCombox")
	public synchronized void find(HttpServletRequest request,PrintWriter printWriter, @RequestParam("paraParentId") String paraParentId, @RequestParam("isDefault") String isDefault) {
		System.out.println(" findParaCombox paraParentId ="+ paraParentId + " isDefault="+isDefault);
		List<EcmParam> infoList = comService.getParaColls(paraParentId,isDefault);
		List<PkList> pkLists = new ArrayList<>();
		for (int i=0;i<infoList.size();i++){
			PkList pkList = new PkList();
			pkList.setPkDesc(infoList.get(i).getParaName());
			pkList.setPkValue(infoList.get(i).getParaCode());
			pkLists.add(pkList);
		}
		System.out.println(JSON.toJSONString(pkLists));
		printWriter.print(JSON.toJSONString(pkLists));
		printWriter.flush();
		printWriter.close();
	}
}
