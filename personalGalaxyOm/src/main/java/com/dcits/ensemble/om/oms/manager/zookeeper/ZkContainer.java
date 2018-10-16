package com.dcits.ensemble.om.oms.manager.zookeeper;


import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.ensemble.om.oms.common.ProgressMessageUtil;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareZkint;
import com.dcits.ensemble.om.oms.module.utils.ContainerCheckResult;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import com.dcits.ensemble.om.oms.service.utils.ShellExcuteService;
import com.dcits.ensemble.om.oms.shellcmd.CmdFactory;
import com.dcits.ensemble.om.oms.shellcmd.ICmd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;


/**
 * ZK应用容器* 
 * @author wangbinaf
 * @date 2016-04-29 
 */
@Component
public class ZkContainer implements ZkIContainer {
	
	private  final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private static final String CHECK_ZKSTATUS ="检查应用实例状态";
	
    private static final String START_ZK ="启动ZK实例";
	
	private static final String STOP_ZK ="停止ZK实例";	
	
	private static final String CLEAN_ZK ="清理ZK实例";	
	
    public static String ZK_START_SHELL = "0040012";//ZK启动Shell
		
	public static String ZK_STOP_SHELL  = "0040013";//ZK停止Shell
	
	@Resource
	ShellExcuteService shellService;
	@Resource
	ParamComboxService paramComboxService;
	@Resource
	ZkCommonShellService zkcommonShellService;
	@Resource
	CmdFactory cmdFactory;
	@Resource
	ZkPathHandler zkPathHandler;
	@Resource
	ZkStatusCheck ZkStatusCheck;
	/**
	 * 容器实例部署
	 * @param EcmMidwareZkint  intant 容器实例对象	
	 * @param String        userId 用户ID		 
	 */
	@Override
	public void assemContainer(EcmMidwareZkint intant,String userId,List<EcmMidwareZkint> list) {
		  log.info("intant=" +intant);
	      zkPathHandler.creatOsPath(intant);//创建工作路径
		  zkcommonShellService.scpZkFile(intant,userId);//远程传输文件
		  zkcommonShellService.assemToalZk(intant,userId);//解压文件
	      zkcommonShellService.deleteFile(intant);//删除安装文件
	      zkcommonShellService.updateZkConf(intant,userId,list); //修改配置文件    
	      zkcommonShellService.creatMyId(intant,list);//创建myid文件
	      zkcommonShellService.chmodZkBin(intant);//给zookeeper下的bin目录下授权
	      
	}
	/**
	 * 检查ZK实例状态 
	 * @param EcmMidwareZkint  intant 容器实例对象	 
	 * @param String        userId 用户ID	
	 * @return  ContainerCheckResult    容器状态检查结果对象
	 */
	@Override
	public ContainerCheckResult checkContainerStatus(EcmMidwareZkint intant,String userId) {
		ContainerCheckResult checkResult = new ContainerCheckResult();
		if (intant != null) {			
			if(intant.getZkintStatus().equals(SysConfigConstants.APP_INTANTSTATUS_NODEPLOY)){//如果ZK尚未首次部署,返回空
			    log.info("intant=" +intant);
				return checkResult;
			}
			ZkStatusCheck.checkPortZk(intant,checkResult);
			String statusCode = "";
			if(checkResult.getResultNum()>0){// 如果不连通，则表明端口没有被占用则它的状态是停止
				statusCode = SysConfigConstants.APP_INTANTSTATUS_STOP;
			}else{
				statusCode = SysConfigConstants.APP_INTANTSTATUS_START;
			}
			checkResult.setCheckStatus(statusCode);
			checkResult.setCheckStatusName(paramComboxService.getParaName(statusCode));			
			return checkResult;
		} else {
			log.error(CHECK_ZKSTATUS+"出错!");
			throw new GalaxyException(CHECK_ZKSTATUS+"出错!");
		}
	}
	/**
	 * 启动容器ZK实例
	 * @param EcmMidwareZkint  intant 容器实例对象	
	 * @param String        userId 用户ID	 	 
	 */
	@Override
	public void startContainer(EcmMidwareZkint intant,String userId) {
		ProgressMessageUtil.startProgress("" + userId, intant.getSerIp(), intant.getZkintName());
		ProgressMessageUtil.addProgressAction(userId, START_ZK);
	try {	
			ICmd cmd = cmdFactory.getCmd(intant.getSerOs());
			shellService.doCmd(intant.getSerIp(),intant.getSerUser(),intant.getSerPwd(),
					cmd.cdCmd(ZkPathHelp.createZkBinPath(intant, paramComboxService))+ SysConfigConstants.SHELL_LINK_SIGN
					   +shellService.createParaCmd(ZK_START_SHELL,""));		
		} catch (Exception e) {
			log.error(START_ZK+"出错,错误信息为："+e.getMessage());
			throw new GalaxyException(START_ZK+"出错!");
		}	
	}
	/**
	 * 停止容器实例
	 * @param EcmMidwareZkint  intant 容器实例对象
	 * @param String        userId 用户ID		 	
	 */
	@Override
	public void stopContainer(EcmMidwareZkint intant,String userId){
		ProgressMessageUtil.startProgress("" + userId, intant.getSerIp(), intant.getZkintName());
		ProgressMessageUtil.addProgressAction(userId, STOP_ZK);
		try {		
			ICmd cmd = cmdFactory.getCmd(intant.getSerOs());
			shellService.doCmd(intant.getSerIp(),intant.getSerUser(),intant.getSerPwd(),
					cmd.cdCmd(ZkPathHelp.createZkBinPath(intant, paramComboxService))+ SysConfigConstants.SHELL_LINK_SIGN
					+shellService.createParaCmd(ZK_STOP_SHELL,""));				
		} catch (Exception e) {
			log.error(STOP_ZK+"出错,错误信息为："+e.getMessage());
			throw new GalaxyException(STOP_ZK+"出错!");
		}
	}
	/**
	 * 清理ZK实例
	 * @param EcmMidwareZkint  intant 容器实例对象
	 * @param String        userId 用户ID		 	
	 */
	@Override
	public void cleanZkIntant(EcmMidwareZkint intant,String userId){
    	ProgressMessageUtil.startProgress("" + userId, intant.getSerIp(), intant.getZkintName());
		ProgressMessageUtil.addProgressAction(userId, CLEAN_ZK);
		String path = intant.getMidwarePath();
		if(zkcommonShellService.isExistFileOrDir(intant, path)){
			zkcommonShellService.deleteDir(intant);
		}
	}
	
}
