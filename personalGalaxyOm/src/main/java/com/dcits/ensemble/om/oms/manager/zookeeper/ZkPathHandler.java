package com.dcits.ensemble.om.oms.manager.zookeeper;

import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.ensemble.om.oms.common.CommonPathHelp;
import com.dcits.ensemble.om.oms.dao.utils.OMSIDao;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareZkint;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import com.dcits.ensemble.om.oms.service.utils.ShellExcuteService;
import com.dcits.ensemble.om.oms.shellcmd.CmdFactory;
import com.dcits.ensemble.om.oms.shellcmd.ICmd;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 应用路径处理类* 
 * @author tangxlf
 * @date 2015-11-25 
 */
@Component
public class ZkPathHandler {
	@Resource
	ZkPathCreateHelp zkpathCreateHelp;
	@Resource
	private OMSIDao omsBaseDao;
	@Resource
	ShellExcuteService shellService;
	@Resource
	ParamComboxService paramComboxService;
	@Resource
	CmdFactory cmdFactory;
	
	 /**
	  * 处理应用安装路径
	  * @param  EcmMidwareZkint        intant              ZK实例对象       
	  */ 
	public void creatOsPath(EcmMidwareZkint intant){
		 ICmd cmd = cmdFactory.getCmd(intant.getSerOs());
		    String userPath = CommonPathHelp.handlePath(zkpathCreateHelp.getOsUserHomePath(intant));
				if(intant.getMidwarePath().indexOf(userPath)==0){
					 shellService.doCmd(intant.getSerIp(),intant.getSerUser(),intant.getSerPwd(),
								cmd.mkdirCmd(intant.getMidwarePath()));
				}else{
					throw new GalaxyException("用户路径为:"+userPath + " 传入路径为:"+intant.getMidwarePath() + "  传入路径有误,请检查!");
				}
	}
	

	
}
