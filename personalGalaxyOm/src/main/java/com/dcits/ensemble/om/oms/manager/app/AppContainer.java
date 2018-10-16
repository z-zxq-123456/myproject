package com.dcits.ensemble.om.oms.manager.app;


import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.ensemble.om.oms.common.CommonPathHelp;
import com.dcits.ensemble.om.oms.common.ProgressMessageUtil;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.manager.redis.RedisPropertiesAutoRevising;
import com.dcits.ensemble.om.oms.manager.zookeeper.UpdateZkConf;
import com.dcits.ensemble.om.oms.module.app.EcmAppIntant;
import com.dcits.ensemble.om.oms.module.utils.ContainerCheckResult;
import com.dcits.ensemble.om.oms.module.utils.ShellResult;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import com.dcits.ensemble.om.oms.service.utils.IService;
import com.dcits.ensemble.om.oms.service.utils.ShellExcuteService;
import com.dcits.ensemble.om.oms.shellcmd.CmdFactory;
import com.dcits.ensemble.om.oms.shellcmd.ICmd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


/**
 * 应用容器* 
 * @author tangxlf
 * @date 2015-09-22 
 */
@Component
public class AppContainer implements IContainer {
	
	private  final Logger log = LoggerFactory.getLogger(this.getClass());
	
    public static String GALAXY_START_SHELL = "0040001";//系统启动Shell
	
	public static String GALAXY_STOP_SHELL  = "0040002";//系统停止Shell
	
	public static String GALAXY_RESTART_SHELL  = "0040003";//系统重启Shell
	
	private static final String START_APP ="启动应用实例";
	
	private static final String STOP_APP ="停止应用实例";	
	
	private static final String RESTART_APP ="重启应用实例";
	
	private static final String CHECK_APPSTATUS ="检查应用实例状态";
	
	private static final String TOTAL_APP_INSTALL ="部署应用实例";	
	
	
	@Resource
	ShellExcuteService shellService;
	@Resource
	ParamComboxService paramComboxService;
	@Resource
	CommonShellService commonShellService;
	@Resource
	PropertiesCacheService propertiesCacheService;
	@Resource
	CmdFactory cmdFactory;
	@Resource
	AppStatusCheck appStatusCheck;
	@Resource
	AppPathHandler appPathHandler;
	@Resource
	ParsePropertiesFileService parsePropertiesFileService;
	@Resource
	IService omsBaseService;
	@Resource
	RedisPropertiesAutoRevising redisPropertiesAutoRevising;
	@Resource
	UpdateZkConf  updateZkConf;
	/**
	 * 检查容器实例状态 
	 * @param EcmAppIntant  intant 容器实例对象	 
	 * @param String        userId 用户ID	
	 * @return  ContainerCheckResult    容器状态检查结果对象
	 */
	@Override
	public ContainerCheckResult checkContainerStatus(EcmAppIntant intant,String userId) {
		ContainerCheckResult checkResult = new ContainerCheckResult();
		if (intant != null) {			
			if(intant.getAppIntantVer()==null){//如果实例当前版本号为空，也就是尚未首次部署
				return checkResult;
			}
			intant.setAppWork(commonShellService.parseAppIntantWorkName(intant, userId));			
			appStatusCheck.checkServeicConnection(intant,checkResult);
			appStatusCheck.checkHttpConnection(intant,checkResult);
			appStatusCheck.checkDependConnection(intant,checkResult);
			String statusCode = "";
			if(checkResult.getResultNum()>0){
				statusCode = SysConfigConstants.APP_INTANTSTATUS_STOP;
			}else{
				statusCode = SysConfigConstants.APP_INTANTSTATUS_START;
			}
			checkResult.setCheckStatus(statusCode);
			checkResult.setCheckStatusName(paramComboxService.getParaName(statusCode));			
			return checkResult;
		} else {
			log.error(CHECK_APPSTATUS+"出错!");
			throw new GalaxyException(CHECK_APPSTATUS+"出错!");
		}
	}
	/**
	 * 容器实例部署
	 * @param EcmAppIntant  intant 容器实例对象	
	 * @param String        userId 用户ID		 
	 */
	@Override
	public void assemContainer(EcmAppIntant intant,String userId) {
		  log.info("intant=" +intant);
		  appPathHandler.handlerOsPath(intant);//若没有安装路径，则创建安装路径
		  commonShellService.scpAppFile(intant,userId);//远程传输文件
		  if(intant.getAppIntantVer()!=null){//如果实例当前版本号为空，也就是尚未首次部署,没有应用名可以获取，也没有应用实例可备份
			  intant.setAppWork(commonShellService.parseAppIntantWorkName(intant, userId));//设置工作目录
			  commonShellService.backApp(intant,userId);//备份应用
		  }
		  ProgressMessageUtil.addProgressAction(userId, TOTAL_APP_INSTALL);
		  assemToalApp(intant,userId);
		  deleteAppFile(intant,userId);//删除安装文件
	}
	
	/**
	 * 启动容器实例
	 * @param EcmAppIntant  intant 容器实例对象	
	 * @param String        userId 用户ID	 	 
	 */
	@Override
	public void startContainer(EcmAppIntant intant,String userId) {
		ProgressMessageUtil.addProgressAction(userId, START_APP);
		try {	
			intant.setAppWork(commonShellService.parseAppIntantWorkName(intant, userId));
			ICmd cmd = cmdFactory.getCmd(intant.getSerOs());
			String chmodCmd = cmd.chmodxCmd(" "+paramComboxService.getParaRemark1(GALAXY_START_SHELL));//给命令授予可执行权限
			shellService.doCmd(intant.getSerIp(),intant.getSerUser(),intant.getSerPwd(),
						createCDBinCmd(intant)+ SysConfigConstants.SHELL_LINK_SIGN
					   +chmodCmd+ SysConfigConstants.SHELL_LINK_SIGN
					   +shellService.createParaCmd(GALAXY_START_SHELL,""));		
			propertiesCacheService.removeProperties(intant.getAppIntantId());//删除缓存中存储的该应用实例配置参数
		} catch (Exception e) {
			log.error(START_APP+"出错,错误信息为："+e.getMessage());
			throw new GalaxyException(START_APP+"出错!");
		}	
	}
	/**
	 * 停止容器实例
	 * @param EcmAppIntant  intant 容器实例对象
	 * @param String        userId 用户ID		 	
	 */
	@Override
	public void stopContainer(EcmAppIntant intant,String userId) {
		ProgressMessageUtil.addProgressAction(userId, STOP_APP);
		try {		
			intant.setAppWork(commonShellService.parseAppIntantWorkName(intant, userId));
			ICmd cmd = cmdFactory.getCmd(intant.getSerOs());
			String chmodCmd = cmd.chmodxCmd(" "+paramComboxService.getParaRemark1(GALAXY_STOP_SHELL));//给命令授予可执行权限
			shellService.doCmd(intant.getSerIp(),intant.getSerUser(),intant.getSerPwd(),
					createCDBinCmd(intant)+ SysConfigConstants.SHELL_LINK_SIGN
					+chmodCmd+ SysConfigConstants.SHELL_LINK_SIGN
				   +shellService.createParaCmd(GALAXY_STOP_SHELL,""));				
		} catch (Exception e) {
			log.error(STOP_APP+"出错,错误信息为："+e.getMessage());
			throw new GalaxyException(STOP_APP+"出错!");
		}
	}

	/**
	 * 重启动容器实例
	 * @param EcmAppIntant  intant 容器实例对象
	 * @param String        userId 用户ID		 	
	 */
	@Override
	public void reStartContainer(EcmAppIntant intant,String userId) {
		ProgressMessageUtil.addProgressAction(userId, RESTART_APP);
		try {
			intant.setAppWork(commonShellService.parseAppIntantWorkName(intant, userId));
			ICmd cmd = cmdFactory.getCmd(intant.getSerOs());
			String chmodCmd = cmd.chmodxCmd(" "+paramComboxService.getParaRemark1(GALAXY_RESTART_SHELL));//给命令授予可执行权限
			shellService.doCmd(intant.getSerIp(),intant.getSerUser(),intant.getSerPwd(),
					createCDBinCmd(intant)+ SysConfigConstants.SHELL_LINK_SIGN
					+chmodCmd+ SysConfigConstants.SHELL_LINK_SIGN
				   +shellService.createParaCmd(GALAXY_RESTART_SHELL,""));	
			propertiesCacheService.removeProperties(intant.getAppIntantId());//删除缓存中存储的该应用实例配置参数
		} catch (Exception e) {
			log.error(RESTART_APP+"出错,错误信息为："+e.getMessage());
			throw new GalaxyException(RESTART_APP+"出错!");
		}
	}
	
	//CD到应用BIN目录命令
	private String createCDBinCmd(EcmAppIntant intant){
		ICmd cmd = cmdFactory.getCmd(intant.getSerOs());
		return cmd.cdCmd(AppPathHelp.createAppBinPath(intant, paramComboxService));
	}
	
	
	/**
	 *  应用实例全量部署   先备份 再删除工作目录 然后解压缩
	 * @param EcmAppIntant  intant 应用实例对象	 
	 * @return  boolean    文件传输状态 true :成功  false:失败
	 */
	private void assemToalApp(EcmAppIntant intant,String userId) {
				ICmd cmd = cmdFactory.getCmd(intant.getSerOs());
				String path = intant.getAppVerPath();
				String tarCmd = cmd.tarCmd(path.substring(path.lastIndexOf("/")+1));//解压缩应用安装文件
				ShellResult result=shellService.doCmd(intant.getSerIp(),intant.getSerUser(),intant.getSerPwd(),
							cmd.cdCmd(intant.getAppPath())+ SysConfigConstants.SHELL_LINK_SIGN //cd到应用安装路径
									+tarCmd); 				
				String mvCmd = createMVCmd(cmd,result,intant, userId);//把应用文件夹名称修改成带应用实例ID的名称
				shellService.doCmd(intant.getSerIp(),intant.getSerUser(),intant.getSerPwd(),
						cmd.cdCmd(intant.getAppPath())+ SysConfigConstants.SHELL_LINK_SIGN //cd到应用安装路径
								+mvCmd); 
				 if(intant.getAppIntantVer()!=null){
					 if(intant.getIsRemainConfig().equals(SysConfigConstants.REMAIN_APP_CONFIG)) {
					 coverConfig(intant,cmd);
					 }
				 }	 
	}
	
	
	//用老实例安装配置文件覆盖新实例
	private void coverConfig(EcmAppIntant intant,ICmd cmd){
		intant.setAppWork(intant.getAppSimpenNm()+"_"+ intant.getAppIntantId());
		String cdCmd = cmd.cdCmd(CommonPathHelp.handlePath(intant.getAppPath())+"/"+intant.getAppWork());//CD到当前应用实例工作目录下
		String cpCmd = cmd.cpCmd(" "+CommonPathHelp.handlePath(intant.getAppPath())+"/"+ AppPathHelp.createAppBackName(intant, paramComboxService)
				+"/"+paramComboxService.getParaRemark1(SysConfigConstants.SYS_CONF_WORK)
				+" " + " .");//拷贝备份的应用实例CONFIG目录到当前目录
		shellService.doCmd(intant.getSerIp(),intant.getSerUser(),intant.getSerPwd(),
				cdCmd+ SysConfigConstants.SHELL_LINK_SIGN //cd到应用安装路径
				+cpCmd);
	}

	
	
	
	//修改应用工作目录为应用工作目录+应用实例ID
	private String createMVCmd(ICmd cmd,ShellResult result,EcmAppIntant intant,String userId){
		String appName = parseAppName(result.getOutList().get(0)) ;			
		return cmd.mvCmd(appName+" "+intant.getAppSimpenNm()+"_"+ intant.getAppIntantId());//把应用文件夹名称修改成带应用实例ID的名称
	}
	//解析出应用安装名
	private String parseAppName(String rowResult){		
		return rowResult.split("/")[0];
	}
	//删除安装文件
	private void deleteAppFile(EcmAppIntant intant,String userId){
		String path = intant.getAppVerPath();
		String fileName =path.substring(path.lastIndexOf("/")+1);//解压缩应用安装文件
		commonShellService.deleteFile(intant,intant.getAppPath(),fileName);//删除安装文件
	}
}
