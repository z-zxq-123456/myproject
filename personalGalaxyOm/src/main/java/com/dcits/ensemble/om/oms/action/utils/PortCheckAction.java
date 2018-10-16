package com.dcits.ensemble.om.oms.action.utils;

import com.dcits.ensemble.om.oms.dao.utils.OMSIDao;
import com.dcits.ensemble.om.oms.manager.app.CommonShellService;
import com.dcits.ensemble.om.oms.module.app.EcmAppIntant;
import com.dcits.ensemble.om.oms.module.server.EcmServerInfo;
import com.dcits.ensemble.om.oms.service.utils.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * EcmAppInfoAction* 
 * @author LuoLang
 * @date 2015-08-27
 */
@Controller
public class PortCheckAction {
	private  final Logger log = LoggerFactory.getLogger(this.getClass());
	@Resource
	IService omsBaseService;
	@Resource
	OMSIDao omsBaseDao;
	@Resource
	CommonShellService commonShellService;

	@RequestMapping("findCheckPort")
	public  void findCheckPort(HttpServletRequest request,PrintWriter printWriter,Integer appIntantId,String port) {
		log.info(" findCheckPort appIntantId ="+ appIntantId + " port="+port);		
		EcmAppIntant intant = new EcmAppIntant();
		intant.setAppIntantId(appIntantId);
		intant = omsBaseService.findListByCond(intant).get(0);
		Map<String,String> map = new HashMap<String,String>();
		map.put("isConnection",""+commonShellService.netstatPort(intant,port));		
		ActionResultWrite.setReadResult(printWriter,map);
	}
	@RequestMapping("findZkAndRedisCheckPort")
	public  void findZkCheckPort(HttpServletRequest request,PrintWriter printWriter,Integer serId,String port) {
		log.info(" findCheckPort serId ="+ serId + " port="+port);
		if(null!=serId&&!port.equals("")){
			EcmServerInfo temp = new EcmServerInfo();
			temp.setSerId(serId);
			temp =omsBaseDao.selectByPrimaryKey(temp);
			Map<String,String> map = new HashMap<String,String>();
			map.put("isConnection",""+commonShellService.netZkAndRedisStatPort(temp,port));	
			ActionResultWrite.setReadResult(printWriter,map);
		}
	}
}
