package com.dcits.ensemble.om.oms.manager.zookeeper;


import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.ensemble.om.oms.common.MachineUtil;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareZkint;
import com.dcits.ensemble.om.oms.module.utils.ContainerCheckResult;
import com.dcits.ensemble.om.oms.module.utils.ShellResult;
import com.dcits.ensemble.om.oms.service.utils.ShellExcuteService;
import com.dcits.ensemble.om.oms.shellcmd.CmdFactory;
import com.dcits.ensemble.om.oms.shellcmd.ICmd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


/**
 * 应用状态检查类* 
 * @author wangbinaf
 * @date 2016-05-05
 */
@Component
public class ZkStatusCheck {
	
	private  final Logger log = LoggerFactory.getLogger(this.getClass());	
	
	private static final String CHECK_ZK_CONNECTION ="ZK连通性检查";
	
	private static final int CHECK_FAIL = 1; //失败
	
	@Resource
	ShellExcuteService shellService;
	
	@Resource
	CmdFactory cmdFactory;
	
	/**
	 * ZK端口连通性检查
	 * @param EcmAppIntant         intant            容器实例对象	 
	 * @param ContainerCheckResult  checkResult       检查结果    
	 * 
	 */ 			
	public void checkPortZk(EcmMidwareZkint intant,ContainerCheckResult checkResult) {
		try {				
			ICmd cmd = cmdFactory.getCmd(intant.getSerOs());
		    String checkPort =intant.getZkintClientPort().toString();
			ShellResult result=shellService.doCmd(intant.getSerIp(),intant.getSerUser(),intant.getSerPwd(),
					cmd.echoruokCmd(" "+intant.getZkintClientPort()));
			if(MachineUtil.appStatus(result, checkPort)){
				checkResult.addMessage(CHECK_ZK_CONNECTION+":连通");
			}else{
				errorCheck(CHECK_ZK_CONNECTION,checkResult);
			}				
		} catch (IllegalStateException e) {
			log.error(CHECK_ZK_CONNECTION+"出错,错误信息为："+e.getMessage());
			throw new GalaxyException(CHECK_ZK_CONNECTION+"出错!");
		} 
	}

	/**
	 * 异常报错
	 * @param String   str  检查对象
	 * @param ContainerCheckResult  checkResult       检查结果
	 * 
	 */
	private void errorCheck(String str ,ContainerCheckResult checkResult) {
	     checkResult.addResultNum(CHECK_FAIL);
	     checkResult.addMessage(str+":不连通");  
	}	
}
