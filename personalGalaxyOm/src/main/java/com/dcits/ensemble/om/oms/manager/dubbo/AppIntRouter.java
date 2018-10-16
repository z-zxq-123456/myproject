package com.dcits.ensemble.om.oms.manager.dubbo;

import com.dcits.ensemble.om.oms.module.app.EcmAppIntant;
import org.springframework.stereotype.Component;

/**
 * 应用实例路由* 
 * @author tangxlf
 * @date 2016-03-14 
 */
@Component
public class AppIntRouter {
  /**
    * 添加允许访问的应用实例路由规则
    * @param EcmAppIntant  intant 容器实例对象	
    * @param String        userId 用户ID		 
    */
   public  void addAppIntRouterRule(EcmAppIntant intant,String userId){
	   
   }
   /**
    * 删除允许访问的应用实例路由规则
    * @param EcmAppIntant  intant 容器实例对象	
    * @param String        userId 用户ID		 
    */	
   public  void removeAppIntRouterRule(EcmAppIntant intant,String userId){
	   
   }
}
