package com.dcits.ensemble.om.oms.action.middleware;


import com.alibaba.fastjson.JSON;
import com.dcits.dynamic.web.mapper.PkList;
import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.ensemble.om.oms.manager.redis.RedisIContainerImpl;

import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareRedisint;
import com.dcits.ensemble.om.oms.module.utils.ContainerCheckResult;
import com.dcits.ensemble.om.oms.service.middleware.EcmRedisMaterToslaveService;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import com.dcits.ensemble.om.oms.service.utils.IService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * EcmMidwareRedisintAction* 
 * @author luolang
 * @date 2016-05-10
 */
@Controller
public class EcmRedisMaterToslaveAction {
	@Resource
	IService omsBaseService;
	@Resource
	ParamComboxService paramComboxService;
	@Resource
	RedisIContainerImpl redisIContainerImpl;
	@Resource
	EcmRedisMaterToslaveService redisMaterToslaveService;

	@RequestMapping("findRedisNodes")
	public void find(HttpServletRequest request, PrintWriter printWriter,int midwareId) {
		List<EcmMidwareRedisint> midList = redisMaterToslaveService.findRedisMaster(midwareId);
		List<PkList> pkLists = new ArrayList<>();
		for (int i=0;i<midList.size();i++){
			PkList pkList = new PkList();
			pkList.setPkDesc(midList.get(i).getRedisintNodeNum().toString());
			pkList.setPkValue(midList.get(i).getMidwareId().toString());
			pkLists.add(pkList);
		}
		printWriter.print(JSON.toJSONString(pkLists));
		printWriter.flush();
		printWriter.close();

	}

	@RequestMapping("findRedisByNodes")
	public void findByNode(HttpServletRequest request, PrintWriter printWriter,EcmMidwareRedisint midwareRedisint) {
		try {
			Map<String,Object> redisMap= new HashMap<String,Object>();
			redisMap.put("midwareId", midwareRedisint.getMidwareId());
			redisMap.put("redisintNodeNum", midwareRedisint.getRedisintNodeNum());
			List<EcmMidwareRedisint> midList = omsBaseService.findListByCond(new EcmMidwareRedisint(),"findRedisNodeList",redisMap);
			ActionResultWrite.setReadResult(printWriter,magicList(midList));
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}

	}
	
	private List<EcmMidwareRedisint> magicList(List<EcmMidwareRedisint> list){
		for(EcmMidwareRedisint midwareRedisint:list){
			midwareRedisint.setRedisintStatusName(paramComboxService.getParaName(midwareRedisint.getRedisintStatus()));
			midwareRedisint.setRedisintTypeName(paramComboxService.getParaName(midwareRedisint.getRedisintType()));
			ContainerCheckResult result = redisIContainerImpl.checkContainerStatus(midwareRedisint, null);
			midwareRedisint.setCurrentRedisintStatus(result.getCheckStatusName());
			midwareRedisint.setHealthMessage(result.getMessage());
			midwareRedisint.setHostAndPort(midwareRedisint.getSerIp()+":"+midwareRedisint.getRedisintPort());
		}
		return list;
	}
	@RequestMapping("setMasterToSlave")
	public void set(HttpServletRequest request, PrintWriter printWriter,EcmMidwareRedisint midwareRedisint) {
		try {
			redisMaterToslaveService.randomSetMS(midwareRedisint);
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}

	}
	
}
