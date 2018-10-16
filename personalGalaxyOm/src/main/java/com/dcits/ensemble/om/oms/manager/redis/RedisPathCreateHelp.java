package com.dcits.ensemble.om.oms.manager.redis;


import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.ensemble.om.oms.common.CommonPathHelp;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareRedisint;
import com.dcits.ensemble.om.oms.module.utils.ShellResult;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import com.dcits.ensemble.om.oms.service.utils.ShellExcuteService;
import com.dcits.ensemble.om.oms.shellcmd.CmdFactory;
import com.dcits.ensemble.om.oms.shellcmd.ICmd;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Redis目录辅助生成类* 
 * @author luolang
 * @date 2016-4-27 
 */
@Component
public class RedisPathCreateHelp {	
	@Resource
	ShellExcuteService shellService;
	@Resource
	ParamComboxService paramComboxService;
	@Resource
	CmdFactory cmdFactory;
   /**
    * 创建操作系统路径
    * @param  EcmAppIntant        intant              应用实例对象
    * @return String  应用实例临时目录名
    */ 
	public void createOsPath(EcmMidwareRedisint midwareRedisint){
	    ICmd cmd = cmdFactory.getCmd(midwareRedisint.getSerOs());
	    String userPath = CommonPathHelp.handlePath(getOsUserHomePath(midwareRedisint));
			if(midwareRedisint.getMidwarePath().indexOf(userPath)==0){
				 shellService.doCmd(midwareRedisint.getSerIp(),midwareRedisint.getSerUser(),midwareRedisint.getSerPwd(),
							cmd.mkdirCmd(midwareRedisint.getMidwarePath()));
			}else{
				throw new GalaxyException("用户路径为:"+userPath + " 传入路径为:"+midwareRedisint.getMidwarePath() + "  传入路径有误,请检查!");
			}
	}
	

   /**
	* 获取操作系统用户路径
	* @param EcmMidwareRedisint  midwareRedisint           redis实例对象	  
	* @return String  操作系统用户路径
	*/ 	
	public String  getOsUserHomePath(EcmMidwareRedisint midwareRedisint){
		ICmd cmd = cmdFactory.getCmd(midwareRedisint.getSerOs());
		ShellResult result=shellService.doCmd(midwareRedisint.getSerIp(),midwareRedisint.getSerUser(),midwareRedisint.getSerPwd(),
				cmd.pwdCmd(""));
		return result.getOutList().get(0);
	}
}
