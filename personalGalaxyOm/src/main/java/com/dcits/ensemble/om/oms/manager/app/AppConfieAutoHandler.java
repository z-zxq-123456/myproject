package com.dcits.ensemble.om.oms.manager.app;


import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.manager.db.DbConfUpdate;
import com.dcits.ensemble.om.oms.manager.redis.RedisPropertiesAutoRevising;
import com.dcits.ensemble.om.oms.manager.zookeeper.UpdateZkConf;
import com.dcits.ensemble.om.oms.module.app.EcmAppIntant;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 应用实例配置文件自动处理* 
 * @author tangxlf
 * @date 2016-05-19 
 */
@Component
public class AppConfieAutoHandler {
	@Resource
	RedisPropertiesAutoRevising redisPropertiesAutoRevising;
	@Resource
	UpdateZkConf updateZkConf;
	@Resource
	DbConfUpdate dbConfUpdate;
	
   /**
    * 处理应用实例配置文件
    * @param  EcmAppIntant        intant              应用实例对象       
    * @param  Integer             userId              用户ID       
    */ 
	public void handlerAppIntantConfig(EcmAppIntant intant,String userId){
		 if(intant.getAppIntantVer()!=null){//不是首次部署该实例，且选择覆盖配置文件则
         	if(intant.getIsRemainConfig().equals(SysConfigConstants.REMAIN_APP_COVER)) {
         		redisPropertiesAutoRevising.setRedisProperties(intant);
         		updateZkConf.zkConfUpdate(intant);//修改galaxy.properties配置文件的galaxy.registry.address属性的值
         		dbConfUpdate.dbConfUpdate(intant);
         	}
         }else{//首次部署实例，默认覆盖配置文件
         	redisPropertiesAutoRevising.setRedisProperties(intant);
         	updateZkConf.zkConfUpdate(intant);
         	dbConfUpdate.dbConfUpdate(intant);
         }
	}
	
  
   
	

	
}
