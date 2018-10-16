package com.dcits.ensemble.om.oms.action.middleware;

import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.ensemble.om.oms.common.ProgressMessageUtil;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.dao.utils.OMSIDao;
import com.dcits.ensemble.om.oms.manager.zookeeper.ZkIContainer;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareZkint;
import com.dcits.ensemble.om.oms.module.utils.ContainerCheckResult;
import com.dcits.ensemble.om.oms.module.utils.PageData;
import com.dcits.ensemble.om.oms.service.middleware.ZookeeperInfoService;
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
import java.util.List;

/**
 * EcmMidwareZkInstantAction* 
 * @author wangbinaf
 * @date 2016-04-29
 */
@Controller
public class EcmMidwareZkDeployAction {
	
	private  final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private static final int timeMill=1;//间隔时间
	@Resource
	OMSIDao omsBaseDao;
	@Resource
	IService omsBaseService;
	@Resource
	ZookeeperInfoService zookeeperInfoService;
	@Resource
	ZkIContainer zkContainer;
	@Resource
	ParamComboxService paramComboxService;
	
	@RequestMapping("saveZkIntant")
	public void save(HttpServletRequest request, PrintWriter printWriter,EcmMidwareZkint intant) {
		    log.info("" + intant);
		try {
			String userId = (String)request.getSession().getAttribute("UserName");
			zookeeperInfoService.saveAllZkIntant(intant.getMidwareId(), userId);//部署集群下的所有ZK实例
			ProgressMessageUtil.stopProgress("" + userId);
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}

	}
	@RequestMapping("startZkIntant")
	public void startZkIntant(HttpServletRequest request, PrintWriter printWriter,EcmMidwareZkint intant) {
		try {
			String userId = (String)request.getSession().getAttribute("UserName");
			zookeeperInfoService.startAllZkIntant(intant.getMidwareId(), userId);
			stopProgress("" + userId);
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	
	@RequestMapping("stopZkIntant")
	public void stopZkIntant(HttpServletRequest request, PrintWriter printWriter,EcmMidwareZkint intant) {
		 try {
			 String userId = (String)request.getSession().getAttribute("UserName");
			 zookeeperInfoService.stopAllZkIntant(intant.getMidwareId(), userId);
			 stopProgress("" + userId);
			 ActionResultWrite.setsuccessfulResult(printWriter);
		 }catch (Exception e){
			 e.printStackTrace();
			 ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		 }
	}
	@RequestMapping("reStartZkIntant")
	public void reStartAppIntant(HttpServletRequest request, PrintWriter printWriter,EcmMidwareZkint intant) {
		try {
			String userId = (String)request.getSession().getAttribute("UserName");
			zookeeperInfoService.reStartAllZkIntant(intant.getMidwareId(), userId);
			stopProgress("" + userId);
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}

	}	
	@RequestMapping("cleanZkIntant")
	public void cleanAppIntant(HttpServletRequest request,PrintWriter printWriter,EcmMidwareZkint intant) {
		try {
			String userId = (String)request.getSession().getAttribute("UserName");
			zookeeperInfoService.cleanAllZkIntant(intant.getMidwareId(), userId);
			stopProgress(""+userId);
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	
	@RequestMapping("findZkIntant")
	public void find(HttpServletRequest request, PrintWriter printWriter ,EcmMidwareZkint midwareZkint) {
		try {
			String userId = (String)request.getSession().getAttribute("UserName");
			int pageNo = ServletRequestUtils.getIntParameter(request, "page", 1);
			int pageSize = ServletRequestUtils.getIntParameter(request, "rows",10);
			PageData<EcmMidwareZkint> pageData =omsBaseService.findPageByCond(midwareZkint,pageNo,pageSize);
			ActionResultWrite.setPageReadResult(printWriter, magicList(pageData.getList(), "" + userId), pageData.getTotalNum());
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	private List<EcmMidwareZkint> magicList(List<EcmMidwareZkint> list,String userId){
		for(EcmMidwareZkint midwareRedisint:list){
			midwareRedisint.setZkintStatusName(paramComboxService.getParaName(midwareRedisint.getZkintStatus()));
			if(midwareRedisint.getZkintStatus().equals(SysConfigConstants.APP_INTANTSTATUS_NODEPLOY))
			midwareRedisint.setMidwareVerNo("");	
			ContainerCheckResult result = zkContainer.checkContainerStatus(midwareRedisint,userId);
			midwareRedisint.setCurrentZkIntantStatus(result.getCheckStatusName());
			midwareRedisint.setHealthMessage(result.getMessage());
		}
		return list;
	}
	/**
	 * 停止进度消息机制
	 * @param  String userId   用户ID     	
	 *
     */	
	private void stopProgress(String userId){
		try {
			Thread.sleep(timeMill*1000);
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
        ProgressMessageUtil.stopProgress(userId);
	}	
	
	
}
