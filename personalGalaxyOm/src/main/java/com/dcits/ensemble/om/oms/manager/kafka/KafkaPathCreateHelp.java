package com.dcits.ensemble.om.oms.manager.kafka;


import com.dcits.ensemble.om.oms.common.CommonPathHelp;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareKafkaint;
import com.dcits.ensemble.om.oms.module.utils.ShellResult;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import com.dcits.ensemble.om.oms.service.utils.ShellExcuteService;
import com.dcits.ensemble.om.oms.shellcmd.CmdFactory;
import com.dcits.ensemble.om.oms.shellcmd.ICmd;
import com.dcits.galaxy.base.exception.GalaxyException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Kafka目录辅助生成类* 
 * @author luolang
 * @date 2016-12-12
 */
@Component
public class KafkaPathCreateHelp {	
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
	public void createOsPath(EcmMidwareKafkaint midwareKafkaint){
	    ICmd cmd = cmdFactory.getCmd(midwareKafkaint.getSerOs());
	    String userPath = CommonPathHelp.handlePath(getOsUserHomePath(midwareKafkaint));
			if(midwareKafkaint.getMidwarePath().indexOf(userPath)==0){
				 shellService.doCmd(midwareKafkaint.getSerIp(),midwareKafkaint.getSerUser(),midwareKafkaint.getSerPwd(),
							cmd.mkdirCmd(midwareKafkaint.getMidwarePath()));
			}else{
				throw new GalaxyException("用户路径为:"+userPath + " 传入路径为:"+midwareKafkaint.getMidwarePath() + "  传入路径有误,请检查!");
			}
	}
	

   /**
	* 获取操作系统用户路径
	* @param EcmMidwareKafkaint  midwareKafkaint           kafka实例对象	  
	* @return String  操作系统用户路径
	*/ 	
	public String  getOsUserHomePath(EcmMidwareKafkaint midwareKafkaint){
		ICmd cmd = cmdFactory.getCmd(midwareKafkaint.getSerOs());
		ShellResult result=shellService.doCmd(midwareKafkaint.getSerIp(),midwareKafkaint.getSerUser(),midwareKafkaint.getSerPwd(),
				cmd.pwdCmd(""));
		return result.getOutList().get(0);
	}
}
