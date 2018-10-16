package com.dcits.ensemble.om.oms.action.middleware;

import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.ensemble.om.oms.manager.redis.RedisIContainerImpl;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareRedisint;
import com.dcits.ensemble.om.oms.module.utils.ContainerCheckResult;
import com.dcits.ensemble.om.oms.module.utils.PageData;
import com.dcits.ensemble.om.oms.service.middleware.EcmMidwareRedisintService;
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
 * EcmMidwareRedisintAction* 
 * @author luolang
 * @date 2016-04-27
 */
@Controller
public class EcmMidwareRedisintAction {
	private  final Logger log = LoggerFactory.getLogger(this.getClass());
	@Resource
	ParamComboxService paramComboxService;
	@Resource
	IService omsBaseService;
	@Resource
	RedisIContainerImpl redisIContainerImpl;
	@Resource
	EcmMidwareRedisintService midwareRedisintService;
	@RequestMapping("saveRedis")
	public void save(HttpServletRequest request, PrintWriter printWriter,int midwareId) {
		try {
			String userId = (String)request.getSession().getAttribute("UserName");
			midwareRedisintService.dePloyRedis(midwareId, userId);
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	
	@RequestMapping("startRedisints")
	public void startAll(HttpServletRequest request, PrintWriter printWriter,int midwareId){
		try {
			String userId = (String)request.getSession().getAttribute("UserName");
			midwareRedisintService.startAll(midwareId,userId);
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	
	@RequestMapping("stopRedisints")
	public void stopAll(HttpServletRequest request, PrintWriter printWriter, int midwareId ){
		try {
			String userId = (String)request.getSession().getAttribute("UserName");
			midwareRedisintService.stopAll(midwareId,userId);
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	
	@RequestMapping("restartRedisints")
	public void reStartAll(HttpServletRequest request, PrintWriter printWriter,int midwareId){
		try {
			String userId = (String)request.getSession().getAttribute("UserName");
			midwareRedisintService.reStartAll(midwareId,userId);
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	
	@RequestMapping("startRedisint")
	public void start(HttpServletRequest request, PrintWriter printWriter, String redisIdString ){
		try {
			String userId = (String)request.getSession().getAttribute("UserName");
			midwareRedisintService.start(redisIdString,userId);
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}

	}
	
	@RequestMapping("stopRedisint")
	public void stop(HttpServletRequest request, PrintWriter printWriter, String redisIdString ){
		try {
			String userId = (String)request.getSession().getAttribute("UserName");
			midwareRedisintService.stop(redisIdString,userId);
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	@RequestMapping("clearRedisints")
	public void clear(HttpServletRequest request, PrintWriter printWriter, int midwareId ) {
		try {
			String userId = (String)request.getSession().getAttribute("UserName");
			midwareRedisintService.clear(midwareId,userId);
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	@RequestMapping("findRedisint")
	public void find(HttpServletRequest request,PrintWriter printWriter,EcmMidwareRedisint midwareRedisint) {
		try {
			log.info(""+midwareRedisint);
			int pageNo = ServletRequestUtils.getIntParameter(request, "page", 1);
			int pageSize = ServletRequestUtils.getIntParameter(request, "rows",10);
			log.info("pageNo ="+pageNo +"pageSize ="+ pageSize);
			PageData<EcmMidwareRedisint> pageData =omsBaseService.findPageByCond(midwareRedisint,pageNo,pageSize);
			ActionResultWrite.setPageReadResult(printWriter,magicList(pageData.getList()),pageData.getTotalNum());
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	private List<EcmMidwareRedisint> magicList(List<EcmMidwareRedisint> list){
		for(EcmMidwareRedisint midwareRedisint:list){
			Integer redisintMemory = midwareRedisint.getRedisintMemory();
			midwareRedisint.setRedisintMemoryUnit(redisintMemory == null ? "" : redisintMemory+"G");
			midwareRedisint.setRedisintStatusName(paramComboxService.getParaName(midwareRedisint.getRedisintStatus()));
			midwareRedisint.setRedisintTypeName(paramComboxService.getParaName(midwareRedisint.getRedisintType()));
			ContainerCheckResult result = redisIContainerImpl.checkContainerStatus(midwareRedisint, null);
			midwareRedisint.setCurrentRedisintStatus(result.getCheckStatusName());
			midwareRedisint.setHealthMessage(result.getMessage());
			midwareRedisint.setHostAndPort(midwareRedisint.getSerIp()+":"+midwareRedisint.getRedisintPort());
		}
		return list;
	}
}
