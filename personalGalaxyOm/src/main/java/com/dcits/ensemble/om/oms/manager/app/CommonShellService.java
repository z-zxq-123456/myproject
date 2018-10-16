package com.dcits.ensemble.om.oms.manager.app;

import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.ensemble.om.oms.common.DataUtil;
import com.dcits.ensemble.om.oms.common.MachineUtil;
import com.dcits.ensemble.om.oms.common.ProgressMessageUtil;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.module.app.EcmAppIntant;
import com.dcits.ensemble.om.oms.module.server.EcmServerInfo;
import com.dcits.ensemble.om.oms.module.utils.ShellResult;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import com.dcits.ensemble.om.oms.service.utils.IService;
import com.dcits.ensemble.om.oms.service.utils.ShellExcuteService;
import com.dcits.ensemble.om.oms.shellcmd.CmdFactory;
import com.dcits.ensemble.om.oms.shellcmd.ICmd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 公共shell操作Service*
 * 
 * @author tangxlf
 * @date 2015-09-23
 */
@Service
public class CommonShellService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private static final String FIND_LIST_BY_IP ="findListByIp";
	
    private static final String TRANS_FILE ="远程传输安装文件";
	
	private static final String BACK_APP ="应用实例备份";	
	
	private static final String GET_APP_NAME ="获取应用实例名称";	
	
	private static final String IS_EXIST_DIRORFILE ="是否存在文件或者目录";
	
	private static final String CHECK_PORT_CONNECTION ="端口连通性检查";
	
	private static final String DELETE_FILE_OPERATION ="删除文件操作";
	
	private static final String DELETE_DIR_OPERATION ="删除目录操作";
	@Resource
	ShellExcuteService shellService;
	@Resource
	IService omsBaseService;
	@Resource
	ParamComboxService paramComboxService;
	@Resource
	CmdFactory cmdFactory;
	
//	private static final Map<Integer,String> appIntantWorkNames = new HashMap<Integer,String>();//缓存起来的应用实例工作目录
	/**
	 * 远程传输文件	 * 
	 * @param EcmAppIntant  intant 应用实例对象
	 * @param String        userId 用户ID		 
	 */
	public void scpAppFile(EcmAppIntant intant,String userId) {
		ProgressMessageUtil.addProgressAction(userId, TRANS_FILE);
		try {
			log.info("start");
			EcmServerInfo serverInfo = getOmsServerInfo();
			ICmd cmd = cmdFactory.getCmd(intant.getSerOs());
		//	intant.setAppVerPath("/home/oms/omsversion/dmwb/1/all/ensemble-integration-all.tar.gz");
			String scpCmd =cmd.scpCmd(intant.getAppVerPath()+" "+intant.getSerUser()+"@"+intant.getSerIp()+":"+intant.getAppPath());
			shellService.doCmd(serverInfo.getSerIp(),
					serverInfo.getSerUser(), serverInfo.getSerPwd(),scpCmd);				
		} catch (Exception e) {
			log.error(TRANS_FILE+"出错,错误信息为："+e.getMessage());
			throw new GalaxyException(TRANS_FILE+"出错,错误信息为："+e.getMessage());
		}
	}
	//如果参数中配置的系统所在服务器  有值 则 按照这个获取运维服务器信息，如果没有值则按照本地IP反查运维服务器信息
	public EcmServerInfo getOmsServerInfo() {
		EcmServerInfo serverInfo = new EcmServerInfo();
		String serId = paramComboxService.getParaRemark1(SysConfigConstants.SYS_SERVER_ID);
		if(DataUtil.isNull(serId)){
			Map<String,Object> queryMap = new HashMap<String,Object>();			
			try {
				queryMap.put("serIp",InetAddress.getLocalHost().getHostAddress());
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				throw new GalaxyException("获取运维服务器信息是出错,错误信息为："+e.getMessage());
			}			
			List<EcmServerInfo>  omsServerList = omsBaseService.findListByCond(serverInfo,FIND_LIST_BY_IP,queryMap);
			if(omsServerList.size()>0){
				serverInfo = omsServerList.get(0);
			}else{
				throw new GalaxyException("服务器管理尚未配置运维系统服务器!");
			}
		}else{			
			serverInfo.setSerId(Integer.parseInt(serId));
			serverInfo = omsBaseService.selectByPrimaryKey(serverInfo);
			if(serverInfo==null){
				throw new GalaxyException("系统参数--系统所在服务器配置不对!");
			}
		}
		return serverInfo;
	}
	/**
	 * 应用备份
	 * @param EcmAppIntant  intant 应用实例对象	
	 * @param String        userId 用户ID	 	
	 */
	public void backApp(EcmAppIntant intant,String userId) {
		    ProgressMessageUtil.addProgressAction(userId, BACK_APP);
		    log.info("start backApp intant="+intant);
			try {				
				ICmd cmd = cmdFactory.getCmd(intant.getSerOs());
				String cdCmd = cmd.cdCmd(intant.getAppPath());//到应用目录
				String delCmd = cmd.rmrCmd(AppPathHelp.createAppBackName(intant, paramComboxService));//删除旧备份目录
//				if(intant.getAppVerType().equals(SysConfigConstants.APP_ASSEMTYPE_ADD)){//增量版本时，拷贝应用生成备份应用，应用保留
//					String cpCmd = cmd.cpCmd(intant.getAppWork()+" "+AppPathHelp.createAppBackName(intant, paramComboxService));//拷贝应用生成备份应用
//					if(!isExistBackApp(intant,userId)){//尚未有备份目录，不用执行删除旧备份目录
//						shellService.doCmd(intant.getSerIp(),intant.getSerUser(),intant.getSerPwd(),
//								cdCmd+SysConfigConstants.SHELL_LINK_SIGN							  
//							   +cpCmd);
//					}else{
//						shellService.doCmd(intant.getSerIp(),intant.getSerUser(),intant.getSerPwd(),
//								cdCmd+SysConfigConstants.SHELL_LINK_SIGN
//							   +delCmd+SysConfigConstants.SHELL_LINK_SIGN
//							   +cpCmd);
//					}
//					
//			    }else{//全量版本时，MV应用生成备份应用，应用不保留
			    	String backCmd = cmd.mvCmd(intant.getAppWork()+" "+ AppPathHelp.createAppBackName(intant, paramComboxService));//把应用文件夹名称修改成备份名称
					if(!isExistBackApp(intant,userId)){//尚未有备份目录，不用执行删除就备份目录
						shellService.doCmd(intant.getSerIp(),intant.getSerUser(),intant.getSerPwd(),
								cdCmd+ SysConfigConstants.SHELL_LINK_SIGN
							   +backCmd);
					}else{
						shellService.doCmd(intant.getSerIp(),intant.getSerUser(),intant.getSerPwd(),
								cdCmd+ SysConfigConstants.SHELL_LINK_SIGN
							   +delCmd+ SysConfigConstants.SHELL_LINK_SIGN
							   +backCmd);
					}
//			    }					
			} catch (Exception e) {
				log.error(BACK_APP+"出错,错误信息为："+e.getMessage());
				throw new GalaxyException(BACK_APP+"出错!");
			}
	}
	
	/**
	 * 获取应用工作目录	 * 
	 * @param EcmAppIntant  intant 应用实例对象
	 * @param String        userId 用户ID		 
	 */
	public String parseAppIntantWorkName(EcmAppIntant intant,String userId) {
//		try {
			log.info("start");
			if(intant.getAppIntantVer()==null){//如果实例当前版本号为空，也就是尚未部署,此时未有应用工作目录
				log.error(GET_APP_NAME+"出错,该实例尚未安装!");
				throw new GalaxyException(GET_APP_NAME+"出错,,该实例尚未安装!");
			}
			return  intant.getAppSimpenNm()+"_"+ intant.getAppIntantId();
//			if(appIntantWorkNames.get(intant.getAppIntantId())==null){
//				ICmd cmd = cmdFactory.getCmd(intant.getSerOs());
//				String cdCmd = cmd.cdCmd(intant.getAppPath());//到应用目录
//				String lsCmd = cmd.lsCmd("|grep "+SysConfigConstants.DIR_LINK_SIGN+intant.getAppIntantId());//ls带应用实例ID的应用目录名
//				ShellResult result=shellService.doCmd(intant.getSerIp(),intant.getSerUser(),intant.getSerPwd(),
//						cdCmd+SysConfigConstants.SHELL_LINK_SIGN							  
//					   +lsCmd);				
//				for(String rowStr:result.getOutList()){
//					if(rowStr.indexOf("_"+ paramComboxService.getParaRemark1(SysConfigConstants.APP_BACK_SUFF_NAME))<0){
//						//return rowStr.replaceAll("_"+intant.getAppIntantId(),"");
//						appIntantWorkNames.put(intant.getAppIntantId(),rowStr);
//						return rowStr;
//					}
//				}
//				log.error(GET_APP_NAME+"出错,未获得正确的应用实例名称!");
//				throw new GalaxyException(GET_APP_NAME+"出错,未获得正确的应用实例名称!");
//			}else{
//				return appIntantWorkNames.get(intant.getAppIntantId());
//			}
//		} catch (Exception e) {
//			log.error(GET_APP_NAME+"出错,错误信息为："+e.getMessage());
//			throw new GalaxyException(GET_APP_NAME+"出错!");
//		}
	}
	
	/**
	 * 删除缓存应用工作目录--版本安装时，删除	 * 
	 * @param EcmAppIntant  intant 应用实例对象
	 * @param String        userId 用户ID		 
	 */
//	public void deleteAppIntantCacheWorkName(EcmAppIntant intant,String userId) {		
//		appIntantWorkNames.remove(intant.getAppIntantId());
//	}
	
	
	
	//是否存在备份目录
	private boolean isExistBackApp(EcmAppIntant intant,String userId){
		return isExistFileOrDir(intant,intant.getAppPath(), AppPathHelp.createAppBackName(intant, paramComboxService));
	}
	
	/**
	 * 是否存在该文件或者目录 * 
	 * @param EcmAppIntant  intant 应用实例对象
	 * @param String        path   文件或者目录路径	
	 * @param String        FileOrDirName 文件或者目录路径名称	
	 * @return boolean true 存在 false 不存在
	 */	
	public boolean isExistFileOrDir(EcmAppIntant intant,String path,String FileOrDirName){
		try {
			log.info("start");
			ICmd cmd = cmdFactory.getCmd(intant.getSerOs());
			String cdCmd = cmd.cdCmd(path);//到应用目录
			String lsCmd = cmd.lsCmd("");//ls带应用实例ID的应用目录名
			ShellResult result=shellService.doCmd(intant.getSerIp(),intant.getSerUser(),intant.getSerPwd(),
					cdCmd+ SysConfigConstants.SHELL_LINK_SIGN
				   +lsCmd);			
			for(String rowStr:result.getOutList()){
				if(rowStr!=null&&rowStr.equals(FileOrDirName)){
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			log.error(IS_EXIST_DIRORFILE+"出错,错误信息为："+e.getMessage());
			throw new GalaxyException(IS_EXIST_DIRORFILE+"出错!");
		}
	}
	
	/**
	 * 检查端口是否启用 * 
	 * @param EcmAppIntant  intant 应用实例对象
	 * @param String        port   待检查端口		
	 * @return boolean true 启用  false 未启用
	 */	
	public boolean netstatPort(EcmAppIntant intant,String port){
		try {							
			ShellResult result=shellService.doCmd(intant.getSerIp(),intant.getSerUser(),intant.getSerPwd(),
					cmdFactory.getCmd(intant.getSerOs()).netstatCmd(port));				
			return MachineUtil.appStatus(result, port);
		} catch (IllegalStateException e) {
			log.error(CHECK_PORT_CONNECTION+"出错,错误信息为："+e.getMessage());
			throw new GalaxyException(CHECK_PORT_CONNECTION+"出错!");
		} catch(GalaxyException e){
			log.info(CHECK_PORT_CONNECTION+" ,信息为："+e.getMessage());
			return false;
		}	
	}
	
	/**
	 * 检查端口是否启用 * 
	 * @param EcmAppIntant  intant     应用实例对象
	 * @param String        filePath   文件路径
	 * @param String        fileName   含文件名		
	 */	
	public void  deleteFile(EcmAppIntant intant,String filePath,String fileName){
		try {	
			ICmd cmd = cmdFactory.getCmd(intant.getSerOs());
			shellService.doCmd(intant.getSerIp(),intant.getSerUser(),intant.getSerPwd(),
					cmd.cdCmd(filePath)+ SysConfigConstants.SHELL_LINK_SIGN //cd到应用安装路径
							+cmd.rmCmd(fileName)); 
		} catch (IllegalStateException e) {
			log.error(DELETE_FILE_OPERATION+"出错,错误信息为："+e.getMessage());
			throw new GalaxyException(DELETE_FILE_OPERATION+"出错!");
		}
	}
	
	/**
	 * 检查端口是否启用 * 
	 * @param EcmAppIntant  intant     应用实例对象
	 * @param String        dirPath    目录路径
	 * @param String        fileName   目录名		
	 */	
	public void  deleteDir(EcmAppIntant intant,String dirPath,String dirName){
		try {	
			ICmd cmd = cmdFactory.getCmd(intant.getSerOs());
			shellService.doCmd(intant.getSerIp(),intant.getSerUser(),intant.getSerPwd(),
					cmd.cdCmd(dirPath)+ SysConfigConstants.SHELL_LINK_SIGN //cd到应用安装路径
							+cmd.rmrCmd(dirName)); 
		} catch (IllegalStateException e) {
			log.error(DELETE_DIR_OPERATION+"出错,错误信息为："+e.getMessage());
			throw new GalaxyException(DELETE_DIR_OPERATION+"出错!");
		}
	}
	/**
	 * 检查ZK和redis端口是否启用   *
	 * @param EcmServerInfo  intant 服务器信息实例
	 * @param String        port   待检查端口		
	 * @return boolean true 启用  false 未启用
	 */	
	public boolean netZkAndRedisStatPort(EcmServerInfo intant,String port){
		try {							
			ShellResult result=shellService.doCmd(intant.getSerIp(),intant.getSerUser(),intant.getSerPwd(),
					cmdFactory.getCmd(intant.getSerOs()).netstatCmd(port));				
			return MachineUtil.appStatus(result, port);
		} catch (IllegalStateException e) {
			log.error(CHECK_PORT_CONNECTION+"出错,错误信息为："+e.getMessage());
			throw new GalaxyException(CHECK_PORT_CONNECTION+"出错!");
		} catch(GalaxyException e){
			log.info(CHECK_PORT_CONNECTION+" ,信息为："+e.getMessage());
			return false;
		}	
	}
	
}