package com.dcits.ensemble.om.oms.manager.redis;

import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.ensemble.om.oms.common.CommonPathHelp;
import com.dcits.ensemble.om.oms.common.ProgressMessageUtil;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.manager.app.CommonShellService;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareRedisint;
import com.dcits.ensemble.om.oms.module.server.EcmServerInfo;
import com.dcits.ensemble.om.oms.module.utils.ContainerCheckResult;
import com.dcits.ensemble.om.oms.service.middleware.EcmMidwareRedisintService;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import com.dcits.ensemble.om.oms.service.utils.ShellExcuteService;
import com.dcits.ensemble.om.oms.shellcmd.CmdFactory;
import com.dcits.ensemble.om.oms.shellcmd.ICmd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Redis容器实现
 * @author luolang
 * @date 2016-04-27 
 */
@Component
public class RedisIContainerImpl implements RedisIContainer {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private static final String REDIS_FLAG="redis";//解压缩后文件名称
	private static final String REDIS_VER_FLAG="redis-";//版本号前缀
	private static final String REDIS_CONFIG="Redis_config.csv";//配置文件名称
	private static final String D_FLAG =",";//隔离符号
	private static final String IS_SETUP="Y";//是否安装Y/N
	private static final String ISNOT_SETUP="N";//是否安装Y/N
	private static final String START_SIGNL="./";//从当前目录执行脚本sh
	private static final String INSTALL_SH="install.sh";//脚本名称
	private static final String TRANS_FILE ="远程传输安装文件";
	private static final String REDIS_INSTALL="redis实例安装";
	private static final String BACK_NEXT_DIR="..";//返回上级目录
	private static final String CHECK_REDISSTATUS="检查Redis实例状态";//返回上级目录
	private static final String START_REDIS ="启动redis实例";
	private static final String STOP_REDIS ="停止redis实例";	
	public static final String REDIS_MASTER_START = "0040006";//redis主启动
	public static final String REDIS_SLAVE_START = "0040007";//redis备启动
	public static final String REDIS_SENTINEL_START = "0040008";//redis哨兵启动
	public static final String REDIS_MASTER_STOP = "0040009";//redis主停止
	public static final String REDIS_SLAVE_STOP = "0040010";//redis备停止
	public static final String REDIS_SENTINEL_STOP = "0040011";//redis哨兵停止
	public static final String PLACE_HOLDER = "{0}";//占位符
	
	@Resource
	ShellExcuteService shellService;
	@Resource
	CommonShellService commonShellService;
	@Resource
	CmdFactory cmdFactory;
	@Resource
	ParamComboxService paramComboxService;
	@Resource
	ShellExcuteService shellExcuteService;
	@Resource
	RedisPathHandler redisPathHandler;
	@Resource
	EcmMidwareRedisintService midwareRedisintService;
	@Resource
	RedisStatusCheck redisStatusCheck;
	/**
	 * 检查容器实例状态 
	 * @param EcmMidwareRedisint  midwareRedisint 容器实例对象	 
	 * @param String        userId 用户ID	
	 * @return  ContainerCheckResult    容器状态检查结果对象
	 */
	@Override
	public ContainerCheckResult checkContainerStatus(EcmMidwareRedisint midwareRedisint, String userId) {
		ContainerCheckResult checkResult = new ContainerCheckResult();
		if (midwareRedisint != null) {
			Set<String> sentinelInfo =redisStatusCheck.sentinelPool(midwareRedisint.getMidwareId());
			if(midwareRedisint.getRedisintStatus().equals(SysConfigConstants.APP_INTANTSTATUS_NODEPLOY)){//如果实例尚未部署
				return checkResult;
			}
			   redisStatusCheck.checkRedisConnection(midwareRedisint, checkResult);
			if(!midwareRedisint.getRedisintType().equals(SysConfigConstants.REDIS_SENTINEL)) {
			   redisStatusCheck.cheakMasterOrSlave(midwareRedisint, checkResult,sentinelInfo);	
			}
			String statusCode = "";
			if(checkResult.getResultNum()>0){
				statusCode = SysConfigConstants.APP_INTANTSTATUS_STOP;
			}else{
				statusCode = SysConfigConstants.APP_INTANTSTATUS_START;
			}
			checkResult.setCheckStatus(statusCode);
			checkResult.setCheckStatusName(paramComboxService.getParaName(statusCode));			
			return checkResult;
		}else {
			log.error(CHECK_REDISSTATUS+"出错!");
			throw new GalaxyException(CHECK_REDISSTATUS+"出错!");
		}
	}
	/**
	 * 容器实例部署
	 * @param Map<String,List<EcmMidwareRedisint>> redisMap Redis实例集合，key为ip，value为ip所对应的实例列表
	 * @param String        userId 用户ID		 
	 */
	@Override
	public void assemContainer(Map<String,List<EcmMidwareRedisint>> redisMap,String ip ,String userId) {
		redisPathHandler.handlerOsPath(redisMap.get(ip).get(0));//创建中间件安装路径
		scpRedisFile(redisMap.get(ip).get(0), userId);//远程传输文件
		String cmd = mkCmd(redisMap,ip);
		log.info("mkCmd=",cmd);
		String serUser =redisMap.get(ip).get(0).getSerUser();
		String serPwd = redisMap.get(ip).get(0).getSerPwd();
		ProgressMessageUtil.addProgressAction(userId, REDIS_INSTALL);
		shellExcuteService.doCmd(ip,serUser,serPwd,cmd);
	}
	/**
	 * 拼命令	 * 
	 * @param Map<String,List<EcmMidwareRedisint>> redisMap Redis实例集合，key为ip，value为ip所对应的实例列表
	 * @param String        userId 用户ID		 
	 */
	 private String mkCmd(Map<String,List<EcmMidwareRedisint>> redisMap,String ip) {
		 StringBuffer cmdData = new StringBuffer();
		 ICmd cmd = cmdFactory.getCmd(redisMap.get(ip).get(0).getSerOs());//默认操作系统都一致
		 String setPath = REDIS_CONFIG;
		 cmdData.append(cmd.cdCmd(CommonPathHelp.handlePath(redisMap.get(ip).get(0).getMidwarePath()))+ SysConfigConstants.SHELL_LINK_SIGN+cmd.tarCmd(CommonPathHelp.handlePath(redisMap.get(ip).get(0).getMidwarePath())+"/"+getTarName(redisMap.get(ip).get(0)))+ SysConfigConstants.SHELL_LINK_SIGN+
					cmd.cdCmd(REDIS_FLAG)+ SysConfigConstants.SHELL_LINK_SIGN+cmd.echoCmd("a,b,c,d,e,f,")+cmd.outputRedirectFileCmd(setPath)
					+ SysConfigConstants.SHELL_LINK_SIGN);
		 for(Map.Entry<String,List<EcmMidwareRedisint>> sMap: redisMap.entrySet() ) {
			 boolean flag =true;   //是否安装，true为不安装
			 String ipTmp = null;  
			 for(EcmMidwareRedisint redisint:sMap.getValue()) {
				 ipTmp=redisint.getSerIp();
				 if(redisint.getRedisintType().equals(SysConfigConstants.REDIS_MASTER)) {
					 flag=false;
					 cmdData.append(cmd.echoCmd(mkCmdArgsY(redisint))+
					 cmd.outputAddDirectFileCmd(setPath)+ SysConfigConstants.SHELL_LINK_SIGN);
				 }
			 }
			 if(flag) {
				 cmdData.append(cmd.echoCmd(mkCmdArgsN(SysConfigConstants.REDIS_MASTER,ipTmp))+
						 cmd.outputAddDirectFileCmd(setPath)+ SysConfigConstants.SHELL_LINK_SIGN);
			 }
			 flag =true;
			 for(EcmMidwareRedisint redisint:sMap.getValue()) {
				 ipTmp=redisint.getSerIp();
				 if(redisint.getRedisintType().equals(SysConfigConstants.REDIS_SLAVE)) {
					 flag=false;
					 cmdData.append(cmd.echoCmd(mkCmdArgsY(redisint))+
							 cmd.outputAddDirectFileCmd(setPath)+ SysConfigConstants.SHELL_LINK_SIGN);
				 }
			 }
			 if(flag) {
				 cmdData.append(cmd.echoCmd(mkCmdArgsN(SysConfigConstants.REDIS_SLAVE,ipTmp))+
						 cmd.outputAddDirectFileCmd(setPath)+ SysConfigConstants.SHELL_LINK_SIGN);
			 }
			 flag =true;
			 for(EcmMidwareRedisint redisint:sMap.getValue()) {
				 ipTmp=redisint.getSerIp();
				 if(redisint.getRedisintType().equals(SysConfigConstants.REDIS_SENTINEL)) {
					 flag=false;
					 cmdData.append(cmd.echoCmd(mkCmdArgsY(redisint))+
					 cmd.outputAddDirectFileCmd(setPath)+ SysConfigConstants.SHELL_LINK_SIGN);
				 }
			 }
			 if(flag) {
				 cmdData.append(cmd.echoCmd(mkCmdArgsN(SysConfigConstants.REDIS_SENTINEL,ipTmp))+
					 cmd.outputAddDirectFileCmd(setPath)+ SysConfigConstants.SHELL_LINK_SIGN);
			 }
		 }
		 //安装的时候传递ip 和 版本号 ip是因为同一个机器可能配有多个IP 版本号 是为了适应redis解压后的目录
		 cmdData.append(START_SIGNL+INSTALL_SH+" "+REDIS_CONFIG+" "+ip+" "+REDIS_VER_FLAG+redisMap.get(ip).get(0).getMidwareVerNo()+
				    SysConfigConstants.SHELL_LINK_SIGN +cmd.cdCmd(BACK_NEXT_DIR)+ SysConfigConstants.SHELL_LINK_SIGN+
				    cmd.rmCmd(getTarName(redisMap.get(ip).get(0)))
				 );
		 return cmdData.toString();
	 }
	//获取tar包名称
	private String getTarName(EcmMidwareRedisint redisint) {
		String[]a= redisint.getMidwareVerPath().split("/");
		return a[a.length-1];
		
	} 
	/**
	 * 获取需要安装的redis命令参数	 * 
	 * @param EcmMidwareRedisint midwareRedisint Redis实例对象
	 */
	private String mkCmdArgsY(EcmMidwareRedisint redisint) {
		String cmdArgs = redisint.getSerIp()+D_FLAG+paramComboxService.getParaName(redisint.getRedisintType())+
				D_FLAG+IS_SETUP+D_FLAG+redisint.getRedisintNodeNum()+D_FLAG+redisint.getRedisintPort()+
				D_FLAG+redisint.getRedisintMemory()
				;
		return cmdArgs ;
		
	}
	/**
	 * 获取不安装的redis命令参数	 * 
	 * @param EcmMidwareRedisint midwareRedisint Redis实例对象
	 */
	private String mkCmdArgsN(String redisintType,String ipTmp) {
		String cmdArgs = ipTmp+D_FLAG+paramComboxService.getParaName(redisintType)+
				D_FLAG+ISNOT_SETUP+D_FLAG+""+D_FLAG+""+
				D_FLAG+""
				;
		return cmdArgs ;
		
	}
	
	
	/**
	 * 远程传输Redis文件	 * 
	 * @param EcmMidwareRedisint midwareRedisint Redis实例对象
	 * @param String        userId 用户ID		 
	 */
	private void scpRedisFile(EcmMidwareRedisint midwareRedisint,String userId) {
		ProgressMessageUtil.addProgressAction(userId, TRANS_FILE);
		try {
			log.info("start");
			EcmServerInfo serverInfo = commonShellService.getOmsServerInfo();
			ICmd cmd = cmdFactory.getCmd(midwareRedisint.getSerOs());
			String scpCmd =cmd.scpCmd(CommonPathHelp.createTotalPath(CommonPathHelp.handlePath(midwareRedisint.getMidwareVerPath()),paramComboxService)
					+" "+midwareRedisint.getSerUser()+"@"+midwareRedisint.getSerIp()+":"+CommonPathHelp.handlePath(midwareRedisint.getMidwarePath()));
			shellExcuteService.doCmd(serverInfo.getSerIp(),
					serverInfo.getSerUser(), serverInfo.getSerPwd(),scpCmd);				
		} catch (Exception e) {
			log.error(TRANS_FILE+"出错,错误信息为："+e.getMessage());
			throw new GalaxyException(TRANS_FILE+"出错,错误信息为："+e.getMessage());
		}
	}
	/**
	 * 启动容器实例
	 * @param EcmMidwareRedisint  midwareRedisintt 容器实例对象
	 * @param String        userId 用户ID			 
	 */
	@Override
	public void startContainer(EcmMidwareRedisint midwareRedisint, String userId) {
		ProgressMessageUtil.addProgressAction(userId, START_REDIS);
		try {	
			ICmd cmd = cmdFactory.getCmd(midwareRedisint.getSerOs());
			String chmodCmd = null;//给命令授予可执行权限
			String startCmd = null;
			if(midwareRedisint.getRedisintType().equals(SysConfigConstants.REDIS_MASTER)) {
				chmodCmd = cmd.chmodxCmd(" "+exCmd(paramComboxService.getParaRemark1(REDIS_MASTER_START),midwareRedisint));
				startCmd = createCDBinCmd(midwareRedisint)+ SysConfigConstants.SHELL_LINK_SIGN+chmodCmd+ SysConfigConstants.SHELL_LINK_SIGN+exCmd(paramComboxService.getParaRemark1(REDIS_MASTER_START),midwareRedisint);
			}
			if(midwareRedisint.getRedisintType().equals(SysConfigConstants.REDIS_SLAVE)) {
				chmodCmd = cmd.chmodxCmd(" "+exCmd(paramComboxService.getParaRemark1(REDIS_SLAVE_START),midwareRedisint));
				startCmd = createCDBinCmd(midwareRedisint)+ SysConfigConstants.SHELL_LINK_SIGN+chmodCmd+ SysConfigConstants.SHELL_LINK_SIGN+exCmd(paramComboxService.getParaRemark1(REDIS_SLAVE_START),midwareRedisint);
			}
			if(midwareRedisint.getRedisintType().equals(SysConfigConstants.REDIS_SENTINEL)) {
				chmodCmd = cmd.chmodxCmd(" "+exCmd(paramComboxService.getParaRemark1(REDIS_SENTINEL_START),midwareRedisint));
				startCmd = createCDBinCmd(midwareRedisint)+ SysConfigConstants.SHELL_LINK_SIGN+chmodCmd+ SysConfigConstants.SHELL_LINK_SIGN+exCmd(paramComboxService.getParaRemark1(REDIS_SENTINEL_START),midwareRedisint);
			}
			shellService.doCmd(midwareRedisint.getSerIp(),midwareRedisint.getSerUser(),midwareRedisint.getSerPwd(),startCmd);
		} catch (Exception e) {
			log.error(START_REDIS+"出错,错误信息为："+e.getMessage());
			throw new GalaxyException(START_REDIS+"出错!");
		}	
	}
	/**
	 * 停止容器实例
	 * @param EcmMidwareRedisint  midwareRedisintt 容器实例对象
	 * @param String        userId 用户ID			 
	 */
	@Override
	public void stopContainer(EcmMidwareRedisint midwareRedisint, String userId) {
		ProgressMessageUtil.addProgressAction(userId, STOP_REDIS);
		try {
			ICmd cmd = cmdFactory.getCmd(midwareRedisint.getSerOs());
			String chmodCmd = null;//给命令授予可执行权限
			String stopCmd = null;
			if(midwareRedisint.getRedisintType().equals(SysConfigConstants.REDIS_MASTER)) {
				chmodCmd = cmd.chmodxCmd(" "+exCmd(paramComboxService.getParaRemark1(REDIS_MASTER_STOP),midwareRedisint));
				stopCmd = createCDBinCmd(midwareRedisint)+ SysConfigConstants.SHELL_LINK_SIGN+chmodCmd+ SysConfigConstants.SHELL_LINK_SIGN+exCmd(paramComboxService.getParaRemark1(REDIS_MASTER_STOP),midwareRedisint);
			}
			if(midwareRedisint.getRedisintType().equals(SysConfigConstants.REDIS_SLAVE)) {
				chmodCmd = cmd.chmodxCmd(" "+exCmd(paramComboxService.getParaRemark1(REDIS_SLAVE_STOP),midwareRedisint));
				stopCmd = createCDBinCmd(midwareRedisint)+ SysConfigConstants.SHELL_LINK_SIGN+chmodCmd+ SysConfigConstants.SHELL_LINK_SIGN+exCmd(paramComboxService.getParaRemark1(REDIS_SLAVE_STOP),midwareRedisint);
			}
			if(midwareRedisint.getRedisintType().equals(SysConfigConstants.REDIS_SENTINEL)) {
				chmodCmd = cmd.chmodxCmd(" "+exCmd(paramComboxService.getParaRemark1(REDIS_SENTINEL_STOP),midwareRedisint));
				stopCmd = createCDBinCmd(midwareRedisint)+ SysConfigConstants.SHELL_LINK_SIGN+chmodCmd+ SysConfigConstants.SHELL_LINK_SIGN+paramComboxService.getParaRemark1(REDIS_SENTINEL_STOP);
			}
			shellService.doCmd(midwareRedisint.getSerIp(),midwareRedisint.getSerUser(),midwareRedisint.getSerPwd(),stopCmd);		
		} catch (Exception e) {
			log.error(STOP_REDIS+"出错,错误信息为："+e.getMessage());
			throw new GalaxyException(STOP_REDIS+"出错!");
		}	
	}
	/**
	 * 重启容器实例
	 * @param EcmMidwareRedisint  midwareRedisintt 容器实例对象
	 * @param String        userId 用户ID			 
	 */
	@Override
	public void reStartContainer(EcmMidwareRedisint midwareRedisint, String userId) {
		stopContainer(midwareRedisint,userId);
		startContainer(midwareRedisint,userId);
	}
	
	//CD到应用BIN目录命令
		private String createCDBinCmd(EcmMidwareRedisint midwareRedisint){
			ICmd cmd = cmdFactory.getCmd(midwareRedisint.getSerOs());
			String cmdArgs =CommonPathHelp.handlePath(midwareRedisint.getMidwarePath())+"/"+REDIS_FLAG+"/"+REDIS_VER_FLAG+midwareRedisint.getMidwareVerNo()+"/"+paramComboxService.getParaRemark1(SysConfigConstants.SYS_BIN_WORK);
			return cmd.cdCmd(cmdArgs);
		}
		
		
	//替换占位符
		private String exCmd(String cmd,EcmMidwareRedisint midwareRedisint) {
			return cmd.replace(PLACE_HOLDER,midwareRedisint.getRedisintNodeNum()+"");
		}
	
		public static void main(String[] args) {
			String a="./start-master{0}.sh";
			String b = a.replace(PLACE_HOLDER,1+"");
			System.out.println(b);
		}
}
