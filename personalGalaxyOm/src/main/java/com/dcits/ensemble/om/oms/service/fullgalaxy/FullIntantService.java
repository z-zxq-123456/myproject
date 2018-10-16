package com.dcits.ensemble.om.oms.service.fullgalaxy;


import com.dcits.ensemble.om.oms.service.middleware.FullRedisIntantService;
import com.dcits.ensemble.om.oms.service.middleware.FullZookeeperIntantService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 全量实例管理Service 管理所有应用实例、所有中间件实例* 
 * @author tangxlf
 * @date 2016-05-13
 */
@Service
public class FullIntantService {  	
	@Resource
	FullEcmAppIntantService fullEcmAppIntantService;
	@Resource
	FullRedisIntantService fullRedisIntantService;
	@Resource
	FullCleanAppIntantService fullCleanAppIntantService;
	@Resource
	FullZookeeperIntantService fullZookeeperIntantService;
	/**
     * 全量部署所有有应用实例、所有中间件实例	
     * @param	  String userId  用户ID
     * @param	  String isRemainConfig  是否保留配置文件  
     */   
	public void deployFullIntant(String userId,String isRemainConfig){
		fullEcmAppIntantService.deployFullAppIntant(userId, isRemainConfig);
		fullZookeeperIntantService.deployFullZookeeperIntant(userId);
		fullRedisIntantService.deployFullRedisIntant(userId);
	}
	
	/**
     * 全量启动所有应用实例、所有中间件实例	
     * @param	  String userId  用户ID
     */   
	public void startFullIntant(String userId){
		fullRedisIntantService.startFullRedisIntant(userId);
		fullZookeeperIntantService.startFullZookeeperIntant(userId);
		fullEcmAppIntantService.startFullAppIntant(userId);		
	}
	
	/**
     * 全量停止所有应用实例、所有中间件实例	
     * @param	  String userId  用户ID
     */   
	public void stopFullIntant(String userId){
		fullEcmAppIntantService.stopFullAppIntant(userId);
		fullRedisIntantService.stopFullRedisIntant(userId);
		fullZookeeperIntantService.stopFullZookeeperIntant(userId);
	}
	
	
	/**
     * 全量重启所有应用实例、所有中间件实例	
     * @param	  String userId  用户ID
     */   
	public void reStartFullIntant(String userId){
		fullRedisIntantService.reStartFullRedisIntant(userId);
		fullZookeeperIntantService.reStartFullZookeeperIntant(userId);
		fullEcmAppIntantService.reStartFullAppIntant(userId);
	}
	/**
     * 全量清理所有应用实例、所有中间件实例	
     * @param	  String userId  用户ID
     */   
	public void clearFullIntant(String userId){
		fullCleanAppIntantService.cleanAllAppIntant(userId);
		fullRedisIntantService.clearFullRedisIntant(userId);
		fullZookeeperIntantService.clearFullZookeeperIntant(userId);
	}
	
}
