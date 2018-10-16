package com.dcits.ensemble.om.oms.service.middleware;


import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.dao.utils.OMSIDao;
import com.dcits.ensemble.om.oms.manager.redis.RedisIContainerImpl;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareInfo;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareRedisint;
import com.dcits.ensemble.om.oms.module.utils.ContainerCheckResult;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * 全量Redis实例部署Service* 
 * @author tangxlf
 * @date 2016-05-12
 */
@Service
public class FullRedisIntantService {
   
    private static final String  FIND_ALL_REDIS_INTANT_SQLID = "findByMid";
	@Resource
	private OMSIDao omsBaseDao;
	@Resource
	ParamComboxService paramComboxService;
	@Resource
	EcmMidwareRedisintService redisintService;
	@Resource
	MiddlewareInfoService middlewareInfoService;
	@Resource
	RedisIContainerImpl redisIContainerImpl;
	/**
     * 全量部署时查询全量的Redis集群和Redis实例	
     * @param	  String userId  用户ID
     * @return	  List<EcmMidwareRedisint>   
     */    
	public List<EcmMidwareRedisint> findFullRedisAndIntantList(String userId) {
		List<EcmMidwareInfo> redisGroupList = middlewareInfoService.findMidwareList(SysConfigConstants.REDIS_MIDWARE);
    	List<EcmMidwareRedisint>  redisIntantList=findFullRedisIntant();
    	return mergeRedisAndIntant(redisGroupList,redisIntantList);
    }
	/**
     *查询所有Redis实例	     
     * @return	  List<EcmMidwareRedisint>   
     */ 
	public List<EcmMidwareRedisint>  findFullRedisIntant(){
		return omsBaseDao.findListByCond(new EcmMidwareRedisint(),FIND_ALL_REDIS_INTANT_SQLID,null);
	}
	
	//合并Redis集合和RedisIntant集合到一个集合中返回页面
	private List<EcmMidwareRedisint> mergeRedisAndIntant(List<EcmMidwareInfo> redisGroupList,List<EcmMidwareRedisint>  redisIntantList){
		List<EcmMidwareRedisint>  resultList = new ArrayList<EcmMidwareRedisint>();
		for(EcmMidwareInfo group:redisGroupList){
			EcmMidwareRedisint intant = new EcmMidwareRedisint();
			intant.setMidwareVerNo(group.getMidwareVerNo());			
			intant.setMidwareName(group.getMidwareName());
			resultList.add(intant);
			for(EcmMidwareRedisint midwareRedisint:redisIntantList){
				if(midwareRedisint.getMidwareId()==group.getMidwareId()){
					midwareRedisint.setRedisintStatusName(paramComboxService.getParaName(midwareRedisint.getRedisintStatus()));
					midwareRedisint.setRedisintTypeName(paramComboxService.getParaName(midwareRedisint.getRedisintType()));
					ContainerCheckResult result = redisIContainerImpl.checkContainerStatus(midwareRedisint, null);
					midwareRedisint.setCurrentRedisintStatus(result.getCheckStatusName());
					midwareRedisint.setHealthMessage(result.getMessage());
					midwareRedisint.setHostAndPort(midwareRedisint.getSerIp()+":"+midwareRedisint.getRedisintPort());
					midwareRedisint.setMidwareName(null);
					resultList.add(midwareRedisint);
				}
			}
		}
		return  resultList;
	}
	/**
     * 全量部署所有Redis集群	
     * @param	  String userId  用户ID
     *      
     */   
	public void deployFullRedisIntant(String userId){
		List<EcmMidwareInfo> infoList = middlewareInfoService.findMidwareList(SysConfigConstants.REDIS_MIDWARE);
    	for(EcmMidwareInfo midwareInfo:infoList){
    		redisintService.dePloyRedis(midwareInfo.getMidwareId(),userId); 
    	}
	}
	
	/**
     * 全量启动所有Redis集群	
     * @param	  String userId  用户ID
     */   
	public void startFullRedisIntant(String userId){
		List<EcmMidwareInfo> infoList = middlewareInfoService.findMidwareList(SysConfigConstants.REDIS_MIDWARE);
    	for(EcmMidwareInfo midwareInfo:infoList){
    		redisintService.startAll(midwareInfo.getMidwareId(),userId); 
    	}
	}
	
	/**
     * 全量停止所有Redis集群	
     * @param	  String userId  用户ID
     */   
	public void stopFullRedisIntant(String userId){
		List<EcmMidwareInfo> infoList = middlewareInfoService.findMidwareList(SysConfigConstants.REDIS_MIDWARE);
    	for(EcmMidwareInfo midwareInfo:infoList){
    		redisintService.stopAll(midwareInfo.getMidwareId(),userId); 
    	}
	}
	
	/**
     * 全量重启所有Redis集群	
     * @param	  String userId  用户ID
     */   
	public void reStartFullRedisIntant(String userId){
		List<EcmMidwareInfo> infoList = middlewareInfoService.findMidwareList(SysConfigConstants.REDIS_MIDWARE);
    	for(EcmMidwareInfo midwareInfo:infoList){
    		redisintService.reStartAll(midwareInfo.getMidwareId(), userId);
    	}
	}
	/**
     * 全量清理所有Redis集群	
     * @param	  String userId  用户ID
     */   
	public void clearFullRedisIntant(String userId){
		List<EcmMidwareInfo> infoList = middlewareInfoService.findMidwareList(SysConfigConstants.REDIS_MIDWARE);
    	for(EcmMidwareInfo midwareInfo:infoList){
    		redisintService.clear(midwareInfo.getMidwareId(),userId); 
    	}
	}
	
}
