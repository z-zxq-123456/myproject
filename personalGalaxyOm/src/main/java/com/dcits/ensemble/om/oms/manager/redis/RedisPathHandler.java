package com.dcits.ensemble.om.oms.manager.redis;


import com.dcits.ensemble.om.oms.dao.utils.OMSIDao;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareRedisint;
import com.dcits.ensemble.om.oms.service.utils.ShellExcuteService;
import com.dcits.ensemble.om.oms.shellcmd.CmdFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * redis路径处理类* 
 * @author luolang
 * @date 2016-4-27 
 */
@Component
public class RedisPathHandler {
	@Resource
	RedisPathCreateHelp redisPathCreateHelp;
	@Resource
	private OMSIDao omsBaseDao;
	@Resource
	CmdFactory cmdFactory;
	@Resource
	ShellExcuteService shellService;
   /**
    * 处理redis安装路径
    * @param EcmMidwareRedisint  midwareRedisint             redis实例对象       
    */ 
	public void handlerOsPath(EcmMidwareRedisint midwareRedisint){
		   redisPathCreateHelp.createOsPath(midwareRedisint);
	}
	
  
	
}
