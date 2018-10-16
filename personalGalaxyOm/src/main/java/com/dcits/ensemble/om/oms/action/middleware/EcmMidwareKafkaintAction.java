package com.dcits.ensemble.om.oms.action.middleware;

import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.ensemble.om.oms.manager.kafka.KafkaIContainerImpl;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareKafkaint;
import com.dcits.ensemble.om.oms.module.utils.ContainerCheckResult;
import com.dcits.ensemble.om.oms.service.middleware.EcmMidwareKafkaintService;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import com.dcits.ensemble.om.oms.service.utils.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.List;

/**
 * EcmMidwareKafkaintAction*
 * @author luolang
 * @date 2016-12-12
 */
@Controller
public class EcmMidwareKafkaintAction {
	private  final Logger log = LoggerFactory.getLogger(this.getClass());
	@Resource
	ParamComboxService paramComboxService;
	@Resource
	IService omsBaseService;
	@Resource
	EcmMidwareKafkaintService midwareKafkaintService;
	@Resource
	KafkaIContainerImpl kafkaIContainerImpl;

	@RequestMapping("saveKafkaints")
	public void save(HttpServletRequest request, PrintWriter printWriter,int midwareId) {
		try {
			String userId = (String)request.getSession().getAttribute("UserName");
			midwareKafkaintService.dePloyKafka(midwareId, userId);
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	
	@RequestMapping("startKafkaints")
	public void startAll(HttpServletRequest request, PrintWriter printWriter,int midwareId){
		try {
			String userId = (String)request.getSession().getAttribute("UserName");
			midwareKafkaintService.startAll(midwareId, userId);
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	
	@RequestMapping("stopKafkaints")
	public void stopAll(HttpServletRequest request, PrintWriter printWriter, int midwareId ){
		try {
			String userId = (String)request.getSession().getAttribute("UserName");
			midwareKafkaintService.stopAll(midwareId, userId);
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	
	@RequestMapping("restartKafkaints")
	public void reStartAll(HttpServletRequest request, PrintWriter printWriter,int midwareId){
		try {
			String userId = (String)request.getSession().getAttribute("UserName");
			midwareKafkaintService.reStartAll(midwareId, userId);
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	

	@RequestMapping("clearKafkaints")
	public void clear(HttpServletRequest request, PrintWriter printWriter, int midwareId ) {
		try {
			String userId = (String)request.getSession().getAttribute("UserName");
			midwareKafkaintService.clear(midwareId,userId);
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	@RequestMapping("findKafkaint")
	public void find(HttpServletRequest request,PrintWriter printWriter,EcmMidwareKafkaint midwareKafkaint) {
		try {
			log.info(""+midwareKafkaint);
			List<EcmMidwareKafkaint> kafkaintList =omsBaseService.findListByCond(midwareKafkaint);
			ActionResultWrite.setReadResult(printWriter, magicList(kafkaintList));
		} catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	private List<EcmMidwareKafkaint> magicList(List<EcmMidwareKafkaint> list){
		for(EcmMidwareKafkaint midwareKafkaint:list){
			midwareKafkaint.setKafkaintStatusName(paramComboxService.getParaName(midwareKafkaint.getKafkaintStatus()));
			ContainerCheckResult result = kafkaIContainerImpl.checkContainerStatus(midwareKafkaint, null);
			midwareKafkaint.setCurrentKafkaintStatus(result.getCheckStatusName());
			midwareKafkaint.setHealthMessage(result.getMessage());
			midwareKafkaint.setHostAndPort(midwareKafkaint.getSerIp()+":"+midwareKafkaint.getKafkaintPort());
		}
		return list;
	}
}
