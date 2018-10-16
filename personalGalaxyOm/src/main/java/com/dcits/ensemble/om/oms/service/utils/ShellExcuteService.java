package com.dcits.ensemble.om.oms.service.utils;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dcits.ensemble.om.oms.action.utils.OMSPassWordBase64;
import com.dcits.ensemble.om.oms.common.DataUtil;
import com.dcits.ensemble.om.oms.dao.utils.ShellExcuteDao;
import com.dcits.ensemble.om.oms.module.server.EcmServerInfo;
import com.dcits.ensemble.om.oms.module.utils.ShellResult;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;

/**
 * shell执行Service* 
 * @author tangxlf
 * @date 2015-08-07
 */
@Service
public class ShellExcuteService {
	private  final Logger log = LoggerFactory.getLogger(this.getClass());
	@Resource
	IService  omsBaseService;
	@Resource
	ParamComboxService  paramComboxService;
   /**
    * shell命令执行
    * @param  String serId  服务器ID        	
    * @param  String cmd    shell执行命令   
    * @return ShellResult   shell执行结果
    */		
	public ShellResult excuteCmd(String serId,String cmd){
		return doCmd(getSerInfo(serId),cmd);
	}

	/**
	 * 执行参数编码对应的shell命令	 
	 * @param EcmServerInfoAction info    服务器信息 
	 * @param String    cmdParaCode 参数编码
	 * @param String    cmdArgs     命令参数
	 * @return ShellResult shell执行结果
	 */
	public ShellResult excuteParaCmd(EcmServerInfo info, String cmdParaCode,String cmdArgs) {
		String cmd = paramComboxService.getParaRemark1(cmdParaCode) + " "+DataUtil.changeNull(cmdArgs);
		return doCmd(info,cmd);
	}
	
	/**
	 * 执行参数编码对应的shell命令	 
	 * @param String    serId       服务器ID 
	 * @param String    cmdParaCode 参数编码
	 * @param String    cmdArgs     命令参数
	 * @return ShellResult shell执行结果
	 */
	public ShellResult excuteParaCmd(String serId, String cmdParaCode,String cmdArgs) {
		String cmd = paramComboxService.getParaRemark1(cmdParaCode) + " "+DataUtil.changeNull(cmdArgs);
		return doCmd(getSerInfo(serId),cmd);
	}
	/**
	 * 执行参数编码对应的shell命令	 
	 * @param String    serId       服务器ID 
	 * @param String    preCmd      预处理命令
	 * @param String    cmdParaCode 参数编码
	 * @param String    cmdArgs     命令参数
	 * @return ShellResult shell执行结果
	 */
	public ShellResult excuteParaCmd(String serId, String preCmd,String cmdParaCode,String cmdArgs) {		
		String cmd = checkPreCmd(preCmd) + paramComboxService.getParaRemark1(cmdParaCode) + " "+DataUtil.changeNull(cmdArgs);
		return doCmd(getSerInfo(serId),cmd);
	}
	/**
	 * 生成参数编码对应shell命令 
	 * @param String    cmdParaCode cmdParaCode
	 * @param String    cmdArgs  命令参数
	 * @return String   执行命令
	 */
	public String createParaCmd(String cmdParaCode,String cmdArgs) {	
		return paramComboxService.getParaRemark1(cmdParaCode) + " "+DataUtil.changeNull(cmdArgs);
	}
	/**
	 * 执行操作系统对应的shell命令	 
	 * @param EcmServerInfoAction info   服务器信息 
	 * @param String    os         操作系统
	 * @param String    cmdSubcode 命令子码
	 * @param String    cmdArgs  命令参数
	 * @return ShellResult shell执行结果
	 */
	public ShellResult excuteOSCmd(EcmServerInfo info, String os,String cmdSubcode,String cmdArgs) {
		String cmdParaCode = paramComboxService.getParaRemark1(os)+cmdSubcode;
		String cmd = paramComboxService.getParaRemark1(cmdParaCode) + " "+DataUtil.changeNull(cmdArgs);
		return doCmd(info,cmd);
	}
	
	/**
	 * 生成shell命令	 
	 * @param String    os         操作系统
	 * @param String    cmdSubcode 命令子码
	 * @param String    cmdArgs  命令参数
	 * @return String   执行命令
	 */
	public String createOsCmd(String os,String cmdSubcode,String cmdArgs) {
		String cmdParaCode = paramComboxService.getParaRemark1(os)+cmdSubcode;
		return paramComboxService.getParaRemark1(cmdParaCode) + " "+DataUtil.changeNull(cmdArgs);
	}
	
	/**
	 * 执行操作系统对应的shell命令	 
	 * @param String    serId      服务器ID 
	 * @param String    os         操作系统
	 * @param String    cmdSubcode 命令子码
	 * @param String    cmdArgs  命令参数
	 * @return ShellResult shell执行结果
	 */
	public ShellResult excuteOSCmd(String serId, String os,String cmdSubcode,String cmdArgs) {
		String cmdParaCode = paramComboxService.getParaRemark1(os)+cmdSubcode;
		String cmd = paramComboxService.getParaRemark1(cmdParaCode) + " "+DataUtil.changeNull(cmdArgs);
		return doCmd(getSerInfo(serId),cmd);
	}	
	/**
	 * 执行操作系统对应的shell命令	 
	 * @param String    serId      服务器ID
	 * @param String    preCmd      预处理命令 
	 * @param String    os         操作系统
	 * @param String    cmdSubcode 命令子码
	 * @param String    cmdArgs  命令参数
	 * @return ShellResult shell执行结果
	 */
	public ShellResult excuteOSCmd(String serId,String preCmd, String os,String cmdSubcode,String cmdArgs) {
		String cmdParaCode = paramComboxService.getParaRemark1(os)+cmdSubcode;
		String cmd = checkPreCmd(preCmd) + paramComboxService.getParaRemark1(cmdParaCode) + " "+ DataUtil.changeNull(cmdArgs);
		return doCmd(getSerInfo(serId),cmd);
	}
	/**
	 * 根据服务器ID获取服务器信息	 
	 * @param String    serId      服务器ID 	 
	 * @return EcmServerInfo 服务器信息
	 */
	private EcmServerInfo getSerInfo(String serId) {
		String loaclSerId = DataUtil.checkNull("系统ID", serId);
		EcmServerInfo info = new EcmServerInfo();
		info.setSerId(Integer.parseInt(loaclSerId));
		return omsBaseService.selectByPrimaryKey(info);
	}
	
	/**
	 * 执行参数编码对应的shell命令	 
	 * @param EcmServerInfoAction info    服务器信息 	 
	 * @param String        cmd     执行命令
	 * @return ShellResult shell执行结果
	 */
	public ShellResult doCmd(EcmServerInfo info, String cmd) {
		String loaclCmd = DataUtil.checkNull("shell命令", cmd);
		ShellExcuteDao  dao = new ShellExcuteDao(info.getSerIp(),info.getSerUser(),decodePwd(info.getSerPwd()));
		//ShellExcuteDao  dao = ShellExcuteDaoFactory.getShellDao(info.getSerIp(),info.getSerUser(),info.getSerPwd());
		return dao.oneExcuteCmd(loaclCmd);		
	}
	
	/**
	 * 执行参数编码对应的shell命令	 
	 * @param EcmServerInfoAction info    服务器信息 	 
	 * @param String        cmd     执行命令
	 * @return ShellResult shell执行结果
	 */
	public ShellResult doCmd(String serIp,String serUser,String serPwd, String cmd) {
		String loaclCmd = DataUtil.checkNull("shell命令", cmd);
		ShellExcuteDao  dao = new ShellExcuteDao(serIp,serUser,decodePwd(serPwd));
		//ShellExcuteDao  dao = ShellExcuteDaoFactory.getShellDao(serIp,serUser,serPwd);
		return dao.oneExcuteCmd(loaclCmd);		
	}
	
	/**
	 * 检查预处理命令，如果不是以分号结束，加上分号	 
	 * @param EcmServerInfoAction info    服务器信息 	 
	 * @param String        cmd     执行命令
	 * @return ShellResult shell执行结果
	 */	
	private String checkPreCmd(String preCmd){
		if(preCmd!=null){
			String preCmdTrim = preCmd.trim();
			if(!preCmdTrim.equals("")){
				if(preCmdTrim.lastIndexOf(";")+1==preCmdTrim.length()){
					return preCmdTrim;
				}else{
					return preCmdTrim+";";
				}
			}
		}
		return "";
	}
	
	
	 //解码密码
    private String decodePwd(String pwd){
		   if(pwd!=null&&!pwd.trim().equals("")){
			 log.info("pwd="+pwd+" decodPwd=" + OMSPassWordBase64.decodeToString(pwd));
			 return  OMSPassWordBase64.decodeToString(pwd);
		   }
		   return "";
	}
	
}
