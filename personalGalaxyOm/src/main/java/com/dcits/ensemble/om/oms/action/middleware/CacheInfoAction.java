package com.dcits.ensemble.om.oms.action.middleware;

import com.alibaba.fastjson.JSON;
import com.dcits.dynamic.web.mapper.PkList;
import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.ensemble.om.oms.constants.RedisTypeConstants;
import com.dcits.ensemble.om.oms.module.middleware.CacheModel;
import com.dcits.ensemble.om.oms.service.middleware.MonitorService;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author maruie
 * @date   2016-01-12
 * CacheInfoAction
 * redis 缓存操作
 */
@Controller
@RequestMapping("cache")
public class CacheInfoAction {
	
	@Resource
	MonitorService service;

	@RequestMapping("findSentinelByMiddlewareId")
	public void findSentinelByPrimaryKey(HttpServletRequest request, PrintWriter printWriter) {
		String middlewareId = request.getParameter("middlewareId");
        Map<String ,String > map = service.getSentinelUrlMasterInfo(Integer.parseInt(middlewareId));
		printWriter.print(JSON.toJSON(map));
		printWriter.flush();
		printWriter.close();
	}

	@RequestMapping("findcache")
	public void findCache(HttpServletRequest request, PrintWriter printWriter) {
		String redisUrl = request.getParameter("redisUrl");
		String masterName = request.getParameter("masterName");
		String cacheType = request.getParameter("cacheType");
		String cacheKey = request.getParameter("cacheKey");
		String isRefresh = request.getParameter("isRefresh");
		if (isRefresh.equals("Y")) {
			service.clearCacheRows(redisUrl);
		}
		List<CacheModel> cacheList = new ArrayList<>();
		if (redisUrl != null && !StringUtils.isEmpty(redisUrl) && masterName != null) {
			cacheList = service.getCacheRows(redisUrl, masterName, cacheType, cacheKey);
		}
		ActionResultWrite.setReadResult(printWriter, cacheList);
	}
	
	@RequestMapping("findCacheType")
	public void findCacheType(HttpServletRequest request, PrintWriter printWriter) {
		List<PkList> cacheList = new ArrayList<PkList>();
		PkList cacheMap1 = new PkList();
		cacheMap1.setPkValue(RedisTypeConstants.HASH_TYPE);
		cacheMap1.setPkDesc(RedisTypeConstants.HASH_TYPE);
        cacheList.add(cacheMap1);
		PkList cacheMap2= new PkList();				
		cacheMap2.setPkValue(RedisTypeConstants.STRNG_TYPE);
		cacheMap2.setPkDesc(RedisTypeConstants.STRNG_TYPE);
		cacheList.add(cacheMap2);	
		PkList cacheMap3 = new PkList();				
		cacheMap3.setPkValue(RedisTypeConstants.SET_TYPE);
		cacheMap3.setPkDesc(RedisTypeConstants.SET_TYPE);
		cacheList.add(cacheMap3);	
		PkList cacheMap4 = new PkList();				
		cacheMap4.setPkValue(RedisTypeConstants.LIST_TYPE);
		cacheMap4.setPkDesc(RedisTypeConstants.LIST_TYPE);
		cacheList.add(cacheMap4);	
		PkList cacheMap5 = new PkList();				
		cacheMap5.setPkValue(RedisTypeConstants.ZSET_TYPE);
		cacheMap5.setPkDesc(RedisTypeConstants.ZSET_TYPE);
		cacheList.add(cacheMap5);
        printWriter.print(JSON.toJSONString(cacheList));
        printWriter.flush();
        printWriter.close();
	}

}
