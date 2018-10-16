package com.dcits.ensemble.om.oms.service.middleware;


import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.module.middleware.CacheModel;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareRedisint;
import com.dcits.ensemble.om.oms.module.middleware.SeqInfo;
import com.dcits.ensemble.om.oms.module.middleware.ServerModel;
import com.dcits.ensemble.om.oms.service.utils.IService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author maruie
 * @date   2016-01-12
 * MonitorService
 * redis 操作
 */
@Service
public class MonitorService {

	@Resource
	IService omsBaseService;

	private static final String DIV_SIGN=";";//隔离符号 '/'
	private static final String MASTER_SIGN="master";//master '/'
	private static final String IP_PORT=":";//隔离符号 '/'


	public Map<String ,String> getSentinelUrlMasterInfo(Integer middlewareId) {
        Map<String,String> map =new HashMap<String ,String>();
        map.put("masterName",getMasterName(middlewareId));
		map.put("sentinelUrl",getSentinelUrl(middlewareId));
		return map;
	}

	private String getMasterName(Integer middlewareId) {
		Map<String,Object> redisMap= new HashMap<String,Object>();
		redisMap.put("midwareId", middlewareId);
		redisMap.put("redisintType", SysConfigConstants.REDIS_MASTER);
		List<EcmMidwareRedisint> midList = omsBaseService.findListByCond(new EcmMidwareRedisint(),"findRedisSerList",redisMap);
		StringBuffer masterName = new StringBuffer("") ;
        for(EcmMidwareRedisint midwareRedisint:midList) {
			masterName.append(MASTER_SIGN+midwareRedisint.getRedisintNodeNum()+DIV_SIGN);
		}
		return masterName.toString().substring(0,masterName.length()-1);
	}

	private String getSentinelUrl(Integer middlewareId) {
		Map<String,Object> redisMap= new HashMap<String,Object>();
		redisMap.put("midwareId", middlewareId);
		redisMap.put("redisintType", SysConfigConstants.REDIS_SENTINEL);
		List<EcmMidwareRedisint> sentinelList = omsBaseService.findListByCond(new EcmMidwareRedisint(),"findRedisSerList",redisMap);
		StringBuffer sentinelUrl = new StringBuffer("") ;
		for( EcmMidwareRedisint midwareRedisint :sentinelList) {
			sentinelUrl.append(midwareRedisint.getSerIp()+IP_PORT+midwareRedisint.getRedisintPort()+DIV_SIGN);
		}
		return sentinelUrl.toString().substring(0,sentinelUrl.length()-1);
	}

	public List<CacheModel> getCacheRows(String server, String masterName, String cacheType, String cacheKey) {
		List<CacheModel> filterList = new ArrayList<CacheModel>();
		if (server == null || server.trim().equals("")) {
			return filterList;
		}
		List<CacheModel> kvList = getServerCacheRows(server, masterName);
        if(kvList != null) {
            for (CacheModel model : kvList) {
                if (filterCache(model, cacheType, cacheKey)) {
                    filterList.add(model);
                }
            }
        }
		return filterList;
	}

	private boolean filterCache(CacheModel model, String cacheType, String cacheKey) {
		if (cacheType != null && !cacheType.trim().equals("")) {
			if (!model.getType().equals(cacheType)) {
				return false;
			}
		}
		if (cacheKey != null && !cacheKey.trim().equals("")) {
			if (model.getKey().toLowerCase().indexOf(cacheKey.toLowerCase()) < 0) {
				return false;
			}
		}
		return true;
	}

	private List<CacheModel> getServerCacheRows(String server, String masterName) {
        if (RedisMomService.getCacheInfo(server) == null) {
            List<CacheModel> kvList = RedisServerService.getRedisServerList(server, masterName);
            if (kvList != null) {
                RedisMomService.setCacheInfo(server, kvList);
            } else {
                return null;
            }
            return kvList;
        }
        return RedisMomService.getCacheInfo(server);
    }

	public List<SeqInfo> findReqFromServer(String server, String masterName) {
		return RedisServerService.findReqFromServer(server, masterName);
	}


	public void saveReqIntoServer(SeqInfo seqInfo) {
		RedisServerService.saveReqIntoServer(seqInfo);
	}


	public void deleteReqIntoServer(SeqInfo seqInfo) {
		RedisServerService.deleteReqIntoServer(seqInfo);
		;
	}

	public void clearCacheRowsAndCacheDB(String server, String masterName) {
		RedisServerService.deleteRedisServer(server, masterName);
		RedisMomService.removeCacheInfo(server);
	}

	public void clearServer(String server) {
		RedisMomService.removeServerInfo(server);
	}

	public void clearCacheRows(String server) {
		RedisMomService.removeCacheInfo(server);
	}

	public ServerModel getServerRows(String server, String masterName) {
		if (RedisMomService.getServerInfo(server) == null) {
			ServerModel model = RedisServerService.getRedisServerInfo(server, masterName);
			if (model != null) RedisMomService.setServerInfo(server, model);
			return model;
		}
		return RedisMomService.getServerInfo(server);
	}
}
