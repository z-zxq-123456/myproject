package com.dcits.ensemble.om.oms.service.middleware;


import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.dao.utils.OMSIDao;
import com.dcits.ensemble.om.oms.manager.zookeeper.ZkIContainer;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareInfo;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareZkint;
import com.dcits.ensemble.om.oms.module.utils.ContainerCheckResult;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * 全量Zookeeper实例部署Service* 
 * @author tangxlf
 * @date 2016-05-17
 */
@Service
public class FullZookeeperIntantService {
   
    
	@Resource
	private OMSIDao omsBaseDao;
	@Resource
	ParamComboxService paramComboxService;
	@Resource
	ZookeeperInfoService zookeeperInfoService;
	@Resource
	MiddlewareInfoService middlewareInfoService;
	@Resource
	ZkIContainer zkIContainer;
	/**
     * 全量部署时查询全量的Zookeeper集群和Zookeeper实例	
     * @param	  String userId  用户ID
     * @return	  List<EcmMidwareRedisint>   
     */    
	public List<EcmMidwareZkint> findFullZookeeperAndIntantList(String userId) {
		List<EcmMidwareInfo> redisGroupList = middlewareInfoService.findMidwareList(SysConfigConstants.ZOOKEEPER_MIDWARE);
    	List<EcmMidwareZkint>  redisIntantList=findFullZookeeperIntant();
    	return mergeZookeeperAndIntant(redisGroupList,redisIntantList);
    }
	/**
     *查询所有Zookeeper实例	     
     * @return	  List<EcmMidwareRedisint>   
     */ 
	public List<EcmMidwareZkint>  findFullZookeeperIntant(){
		return zookeeperInfoService.findByMid(null);
	}
	
	//合并Zookeeper集合和ZookeeperIntant集合到一个集合中返回页面
	private List<EcmMidwareZkint> mergeZookeeperAndIntant(List<EcmMidwareInfo> redisGroupList,List<EcmMidwareZkint>  zkIntantList){
		List<EcmMidwareZkint>  resultList = new ArrayList<EcmMidwareZkint>();
		for(EcmMidwareInfo group:redisGroupList){
			EcmMidwareZkint intant = new EcmMidwareZkint();
			intant.setMidwareVerNo(group.getMidwareVerNo());			
			intant.setMidwareName(group.getMidwareName());
			resultList.add(intant);
			for(EcmMidwareZkint midwareZkint:zkIntantList){
				if(midwareZkint.getMidwareId()==group.getMidwareId()){
					midwareZkint.setZkintStatusName(paramComboxService.getParaName(midwareZkint.getZkintStatus()));					
					ContainerCheckResult result = zkIContainer.checkContainerStatus(midwareZkint,null);
					midwareZkint.setCurrentZkIntantStatus(result.getCheckStatusName());
					midwareZkint.setHealthMessage(result.getMessage());					
					midwareZkint.setMidwareName(null);
					midwareZkint.setMidwareVerNo(null);
					resultList.add(midwareZkint);
				}
			}
		}
		return  resultList;
	}
	/**
     * 全量部署所有Zookeeper集群	
     * @param	  String userId  用户ID
     *      
     */   
	public void deployFullZookeeperIntant(String userId){
		List<EcmMidwareInfo> infoList = middlewareInfoService.findMidwareList(SysConfigConstants.ZOOKEEPER_MIDWARE);
    	for(EcmMidwareInfo midwareInfo:infoList){
    		zookeeperInfoService.saveAllZkIntant(midwareInfo.getMidwareId(),userId); 
    	}
	}
	
	/**
     * 全量启动所有Zookeeper集群	
     * @param	  String userId  用户ID
     */   
	public void startFullZookeeperIntant(String userId){
		List<EcmMidwareInfo> infoList = middlewareInfoService.findMidwareList(SysConfigConstants.ZOOKEEPER_MIDWARE);
    	for(EcmMidwareInfo midwareInfo:infoList){
    		zookeeperInfoService.startAllZkIntant(midwareInfo.getMidwareId(),userId); 
    	}
	}
	
	/**
     * 全量停止所有Zookeeper集群	
     * @param	  String userId  用户ID
     */   
	public void stopFullZookeeperIntant(String userId){
		List<EcmMidwareInfo> infoList = middlewareInfoService.findMidwareList(SysConfigConstants.ZOOKEEPER_MIDWARE);
    	for(EcmMidwareInfo midwareInfo:infoList){
    		zookeeperInfoService.stopAllZkIntant(midwareInfo.getMidwareId(),userId); 
    	}
	}
	
	/**
     * 全量重启所有Zookeeper集群	
     * @param	  String userId  用户ID
     */   
	public void reStartFullZookeeperIntant(String userId){
		List<EcmMidwareInfo> infoList = middlewareInfoService.findMidwareList(SysConfigConstants.ZOOKEEPER_MIDWARE);
    	for(EcmMidwareInfo midwareInfo:infoList){
    		zookeeperInfoService.reStartAllZkIntant(midwareInfo.getMidwareId(), userId);
    	}
	}
	/**
     * 全量清理所有Zookeeper集群	
     * @param	  String userId  用户ID
     */   
	public void clearFullZookeeperIntant(String userId){
		List<EcmMidwareInfo> infoList = middlewareInfoService.findMidwareList(SysConfigConstants.ZOOKEEPER_MIDWARE);
    	for(EcmMidwareInfo midwareInfo:infoList){
    		zookeeperInfoService.cleanAllZkIntant(midwareInfo.getMidwareId(),userId); 
    	}
	}
	
}
