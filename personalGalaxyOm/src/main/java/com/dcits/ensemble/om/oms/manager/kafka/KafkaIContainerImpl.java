package com.dcits.ensemble.om.oms.manager.kafka;

import com.dcits.ensemble.om.oms.common.CommonPathHelp;
import com.dcits.ensemble.om.oms.common.DataUtil;
import com.dcits.ensemble.om.oms.common.ProgressMessageUtil;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.manager.app.CommonShellService;
import com.dcits.ensemble.om.oms.manager.zookeeper.UpdateZkConf;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareInfo;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareKafkaint;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareZkint;
import com.dcits.ensemble.om.oms.module.server.EcmServerInfo;
import com.dcits.ensemble.om.oms.module.utils.ContainerCheckResult;
import com.dcits.ensemble.om.oms.module.utils.ShellResult;
import com.dcits.ensemble.om.oms.service.middleware.ZookeeperInfoService;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import com.dcits.ensemble.om.oms.service.utils.IService;
import com.dcits.ensemble.om.oms.service.utils.ShellExcuteService;
import com.dcits.ensemble.om.oms.shellcmd.CmdFactory;
import com.dcits.ensemble.om.oms.shellcmd.ICmd;
import com.dcits.galaxy.base.exception.GalaxyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * Kafka容器实现
 * @author luolang
 * @date 2016-12-12
 */
@Component
public class KafkaIContainerImpl implements KafkaIContainer {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private static final String TRANS_FILE ="远程传输安装文件";
	private static final String KAFKA_INSTALL="kafka实例安装";
	private static final String BACK_NEXT_DIR="../";//返回上级目录
	private static final String BACK_CURRENT_DIR="./";//当前目录
	private static final String START_KAFKA ="启动kafka实例";
	private static final String STOP_KAFKA ="停止kafka实例";
	public static final String PLACE_HOLDER = "{0}";//占位符

	private static final String CHECK_KAFKASTATUS="检查Kafka实例状态";//返回上级目录
	private static final String DELETE_FILE_OPERATION ="删除文件操作";

	//config
	private static final String BROKER_ID= "broker.id";//节点代理ID
	private static final String ZOOKEEPER_CONNECT = "zookeeper.connect=";//zk地址
	private static final String LOG_DIRS = "log.dirs";//日志存储地址
	private static final String LOG_DIRS_DIR = "../kafka_log_{0}";//日志存储地址
	private static final String SERVER_PROPERTIES_OLD = "server.properties";//broker初始配置文件
	private static final String SERVER_PROPERTIES_NEW = "server{0}.properties";//broker配置文件
	private static Map<Integer,String> serverPropertiesMap =new HashMap<Integer,String>();//key为brokerID，value为配置文件名称
	private static final String BROKER_START_SH= "0040014";//broker启动脚本
	private static final String BROKER_STOP_SH= "0040015";//broker停止脚本
	private static final String DAEON_PRAME= "-daemon";//守护进程参数

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
	KafkaPathHandler kafkaPathHandler;
	@Resource
	KafkaStatusCheck KafkaStatusCheck;
	@Resource
	ZookeeperInfoService zookeeperInfoService;
	@Resource
	UpdateZkConf updateZkConf;
	@Resource
	IService omsBaseService;
	/**
	 * 检查容器实例状态 
	 * @param EcmMidwareKafkaint  midwareKafkaint 容器实例对象	 
	 * @param String        userId 用户ID	
	 * @return  ContainerCheckResult    容器状态检查结果对象
	 */
	@Override
	public ContainerCheckResult checkContainerStatus(EcmMidwareKafkaint midwareKafkaint, String userId) {
		ContainerCheckResult checkResult = new ContainerCheckResult();
		if (midwareKafkaint != null) {
			if (midwareKafkaint.getKafkaintStatus().equals(SysConfigConstants.APP_INTANTSTATUS_NODEPLOY)) {//如果实例尚未部署
				return checkResult;
			}
			KafkaStatusCheck.checkKafkaConnection(midwareKafkaint, checkResult);
		    KafkaStatusCheck.checkKafkaIDS(midwareKafkaint, checkResult);
			String statusCode = "";
			if (checkResult.getResultNum() > 0) {
				statusCode = SysConfigConstants.APP_INTANTSTATUS_STOP;
			} else {
				statusCode = SysConfigConstants.APP_INTANTSTATUS_START;
			}
			checkResult.setCheckStatus(statusCode);
			checkResult.setCheckStatusName(paramComboxService.getParaName(statusCode));
			return checkResult;
		}else {
			log.error(CHECK_KAFKASTATUS+"出错!");
			throw new GalaxyException(CHECK_KAFKASTATUS+"出错!");
		}
	}



	/**
	 * 容器实例部署
	 * @param Map<String,List<EcmMidwareKafkaint>> kafkaMap Kafka实例集合，key为ip，value为ip所对应的实例列表
	 * @param String        userId 用户ID		 
	 */
	@Override
	public void assemContainer(Map<String,List<EcmMidwareKafkaint>> kafkaMap,String ip ,String userId) {
		kafkaPathHandler.handlerOsPath(kafkaMap.get(ip).get(0));//创建中间件安装路径
		scpKafkaFile(kafkaMap.get(ip).get(0), userId);//远程传输文件
		String createBrokercmd = mkCmd(kafkaMap,ip);//组装创建broker命令
		log.info("createBrokercmd=",createBrokercmd);
		String serUser =kafkaMap.get(ip).get(0).getSerUser();
		String serPwd = kafkaMap.get(ip).get(0).getSerPwd();
		ProgressMessageUtil.addProgressAction(userId, KAFKA_INSTALL);
		shellExcuteService.doCmd(ip, serUser, serPwd, createBrokercmd);//执行创建broker命令
		amendProperties(ip, serUser, serPwd, kafkaMap.get(ip));//修改配置文件（目前只修改了port，zookeeper.connect,log.dir）
	}
	/**
	 * 拼命令	 * 
	 * @param Map<String,List<EcmMidwareKafkaint>> kafkaMap Kafka实例集合，key为ip，value为ip所对应的实例列表
	 * @param String        userId 用户ID		 
	 */
	 private String mkCmd(Map<String,List<EcmMidwareKafkaint>> kafkaMap,String ip) {
		 StringBuffer cmdData = new StringBuffer();
		 ICmd cmd = cmdFactory.getCmd(kafkaMap.get(ip).get(0).getSerOs());//默认操作系统都一致
		 cmdData.append(
				 cmd.cdCmd(CommonPathHelp.handlePath(kafkaMap.get(ip).get(0).getMidwarePath())) + SysConfigConstants.SHELL_LINK_SIGN +
						 cmd.tarCmd(getTarName(kafkaMap.get(ip).get(0))) + SysConfigConstants.SHELL_LINK_SIGN + cmd.rmrCmd(getTarName(kafkaMap.get(ip).get(0))) + SysConfigConstants.SHELL_LINK_SIGN +
						 createCDConfigCmd(kafkaMap.get(ip).get(0)) + SysConfigConstants.SHELL_LINK_SIGN
		 );
		// serverPropertiesMap.clear();
		 for(EcmMidwareKafkaint midwareKafkaint :kafkaMap.get(ip)) {
			 cmdData.append(
				 cmd.cpCmd(SERVER_PROPERTIES_OLD+" "+BACK_CURRENT_DIR+exString(SERVER_PROPERTIES_NEW,midwareKafkaint))+ SysConfigConstants.SHELL_LINK_SIGN
			 );
			 serverPropertiesMap.put(midwareKafkaint.getKafkaintId(),exString(SERVER_PROPERTIES_NEW,midwareKafkaint));
		 }
		 cmdData.append(
				 cmd.rmCmd(SERVER_PROPERTIES_OLD)
		 );
		 return cmdData.toString();
	 }
	//获取tar包名称
	private String getTarName(EcmMidwareKafkaint kafkaint) {
		String[]a= kafkaint.getMidwareVerPath().split("/");
		return a[a.length-1];
		
	}
	//获取kafka解压后工程名
	public  String getKafkaWorkName(EcmMidwareKafkaint kafkaint){
		String packageName = getTarName(kafkaint);
        int lastIndex = packageName.lastIndexOf(".");
		return packageName.substring(0,lastIndex);
	}

	/**
	 * 远程传输Kafka文件	 * 
	 * @param EcmMidwareKafkaint midwareKafkaint Kafka实例对象
	 * @param String        userId 用户ID		 
	 */
	private void scpKafkaFile(EcmMidwareKafkaint midwareKafkaint,String userId) {
		ProgressMessageUtil.addProgressAction(userId, TRANS_FILE);
		try {
			log.info("start");
			EcmServerInfo serverInfo = commonShellService.getOmsServerInfo();
			ICmd cmd = cmdFactory.getCmd(midwareKafkaint.getSerOs());
			String scpCmd =cmd.scpCmd(CommonPathHelp.createTotalPath(CommonPathHelp.handlePath(midwareKafkaint.getMidwareVerPath()),paramComboxService)
					+" "+midwareKafkaint.getSerUser()+"@"+midwareKafkaint.getSerIp()+":"+CommonPathHelp.handlePath(midwareKafkaint.getMidwarePath()));
			shellExcuteService.doCmd(serverInfo.getSerIp(),
					serverInfo.getSerUser(), serverInfo.getSerPwd(),scpCmd);				
		} catch (Exception e) {
			log.error(TRANS_FILE+"出错,错误信息为："+e.getMessage());
			throw new GalaxyException(TRANS_FILE+"出错,错误信息为："+e.getMessage());
		}
	}
	/**
	 * 启动容器实例
	 * @param EcmMidwareKafkaint  midwareKafkaintt 容器实例对象
	 * @param String        userId 用户ID			 
	 */
	@Override
	public void startContainer(EcmMidwareKafkaint midwareKafkaint, String userId) {
		ProgressMessageUtil.addProgressAction(userId, START_KAFKA);
		try {
			String startCmd = createCDBinCmd(midwareKafkaint)+ SysConfigConstants.SHELL_LINK_SIGN+paramComboxService.getParaRemark1(BROKER_START_SH)+" "+DAEON_PRAME+" "+BACK_NEXT_DIR+paramComboxService.getParaRemark1(SysConfigConstants.MIDWARE_CONFIG_DIR)+"/"+exString(SERVER_PROPERTIES_NEW,midwareKafkaint);
			shellService.doCmd(midwareKafkaint.getSerIp(),midwareKafkaint.getSerUser(),midwareKafkaint.getSerPwd(),startCmd);
		} catch (Exception e) {
			log.error(START_KAFKA+"出错,错误信息为："+e.getMessage());
			throw new GalaxyException(START_KAFKA+"出错!");
		}	
	}
	/**
	 * 停止容器实例
	 * @param EcmMidwareKafkaint  midwareKafkaintt 容器实例对象
	 * @param String        userId 用户ID			 
	 */
	@Override
	public void stopContainer(EcmMidwareKafkaint midwareKafkaint, String userId) {
		ProgressMessageUtil.addProgressAction(userId, midwareKafkaint.getSerIp()+STOP_KAFKA);
		try {
			String stopCmd = createCDBinCmd(midwareKafkaint)+ SysConfigConstants.SHELL_LINK_SIGN+paramComboxService.getParaRemark1(BROKER_STOP_SH);
			shellService.doCmd(midwareKafkaint.getSerIp(),midwareKafkaint.getSerUser(),midwareKafkaint.getSerPwd(),stopCmd);
		} catch (Exception e) {
			log.error(STOP_KAFKA+"出错,错误信息为："+e.getMessage());
			throw new GalaxyException(STOP_KAFKA+"出错!");
		}	
	}
	/**
	 * 重启容器实例
	 * @param EcmMidwareKafkaint  midwareKafkaintt 容器实例对象
	 * @param String        userId 用户ID			 
	 */
	@Override
	public void reStartContainer(EcmMidwareKafkaint midwareKafkaint, String userId) {
		stopContainer(midwareKafkaint,userId);
		startContainer(midwareKafkaint, userId);
	}

	@Override
	public void clearContainer(EcmMidwareKafkaint midwareKafkaint ,String userId) {
		try {
			ICmd cmd = cmdFactory.getCmd(midwareKafkaint.getSerOs());
			shellService.doCmd(midwareKafkaint.getSerIp(),midwareKafkaint.getSerUser(),midwareKafkaint.getSerPwd(),
					cmd.cdCmd(midwareKafkaint.getMidwarePath())+ SysConfigConstants.SHELL_LINK_SIGN //cd到应用安装路径
							+cmd.rmrCmd(getKafkaWorkName(midwareKafkaint)));
		} catch (IllegalStateException e) {
			log.error(DELETE_FILE_OPERATION+"出错,错误信息为："+e.getMessage());
			throw new GalaxyException(DELETE_FILE_OPERATION+"出错!");
		}
	}

	//CD到应用BIN目录命令
	private String createCDBinCmd(EcmMidwareKafkaint midwareKafkaint){
		ICmd cmd = cmdFactory.getCmd(midwareKafkaint.getSerOs());
		String cmdArgs =CommonPathHelp.handlePath(midwareKafkaint.getMidwarePath())+"/"+getKafkaWorkName(midwareKafkaint)+"/"+paramComboxService.getParaRemark1(SysConfigConstants.SYS_BIN_WORK);
		return cmd.cdCmd(cmdArgs);
	}
	//CD到应用config目录命令
	private String createCDConfigCmd(EcmMidwareKafkaint midwareKafkaint){
		ICmd cmd = cmdFactory.getCmd(midwareKafkaint.getSerOs());
		String cmdArgs =CommonPathHelp.handlePath(midwareKafkaint.getMidwarePath())+"/"+getKafkaWorkName(midwareKafkaint)+"/"+paramComboxService.getParaRemark1(SysConfigConstants.MIDWARE_CONFIG_DIR);
		return cmd.cdCmd(cmdArgs);
	}


	//替换占位符
	private String exString(String str,EcmMidwareKafkaint midwareKafkaint) {
		return str.replace(PLACE_HOLDER, midwareKafkaint.getKafkaintId() + "");
	}
	private void amendProperties(String ip,String serUser , String serPwd ,List<EcmMidwareKafkaint> midwareKafkaintList ) {
		StringBuffer sedCmdData = new StringBuffer();
		if(midwareKafkaintList.size()!=0) {
			sedCmdData.append(createCDConfigCmd(midwareKafkaintList.get(0)) + SysConfigConstants.SHELL_LINK_SIGN);
		  for(EcmMidwareKafkaint midwareKafkaint : midwareKafkaintList){
			  ICmd cmd = cmdFactory.getCmd(midwareKafkaint.getSerOs());
			  String cdCmd = createCDConfigCmd(midwareKafkaint);
			  String catCmd = cmd.catCmd(serverPropertiesMap.get(midwareKafkaint.getKafkaintId()));//查看指定文件
			  ShellResult info = shellService.doCmd(midwareKafkaint.getSerIp(), midwareKafkaint.getSerUser(), midwareKafkaint.getSerPwd(),
					  cdCmd + SysConfigConstants.SHELL_LINK_SIGN + catCmd);
			  //添加port=9092 属性
			  sedCmdData.append(cmd.echoCmd("port=" + midwareKafkaint.getKafkaintPort() + cmd.outputAddDirectFileCmd(" " + serverPropertiesMap.get(midwareKafkaint.getKafkaintId()))) + SysConfigConstants.SHELL_LINK_SIGN);
			  //添加num.partitions=20 属性
			  if(!DataUtil.isNull(paramComboxService.getParaRemark1("0043001")))
			  sedCmdData.append(cmd.echoCmd(paramComboxService.getParaName("0043001")+"=" +paramComboxService.getParaRemark1("0043001")  + cmd.outputAddDirectFileCmd(" " + serverPropertiesMap.get(midwareKafkaint.getKafkaintId()))) + SysConfigConstants.SHELL_LINK_SIGN);
			  //添加default.replication.factor=1 属性
			  if(!DataUtil.isNull(paramComboxService.getParaRemark1("0043002")))
			  sedCmdData.append(cmd.echoCmd(paramComboxService.getParaName("0043002")+"=" + paramComboxService.getParaRemark1("0043002") + cmd.outputAddDirectFileCmd(" " + serverPropertiesMap.get(midwareKafkaint.getKafkaintId()))) + SysConfigConstants.SHELL_LINK_SIGN);
			  //添加log.retention.hours=168 属性
			  if(!DataUtil.isNull(paramComboxService.getParaRemark1("0043003")))
			  sedCmdData.append(cmd.echoCmd(paramComboxService.getParaName("0043003")+"=" +paramComboxService.getParaRemark1("0043003") + cmd.outputAddDirectFileCmd(" " + serverPropertiesMap.get(midwareKafkaint.getKafkaintId()))) + SysConfigConstants.SHELL_LINK_SIGN);
			  //添加zookeeper.connection.timeout.ms=10000 属性
			  if(!DataUtil.isNull(paramComboxService.getParaRemark1("0043004")))
			  sedCmdData.append(cmd.echoCmd(paramComboxService.getParaName("0043004")+"=" +paramComboxService.getParaRemark1("0043004") + cmd.outputAddDirectFileCmd(" " + serverPropertiesMap.get(midwareKafkaint.getKafkaintId()))) + SysConfigConstants.SHELL_LINK_SIGN);
			  //添加offsets.topic.num.partitions=100 属性
			  if(!DataUtil.isNull(paramComboxService.getParaRemark1("0043005")))
			  sedCmdData.append(cmd.echoCmd(paramComboxService.getParaName("0043005") + "=" +paramComboxService.getParaRemark1("0043005") + cmd.outputAddDirectFileCmd(" " + serverPropertiesMap.get(midwareKafkaint.getKafkaintId()))) + SysConfigConstants.SHELL_LINK_SIGN);
			  //修改broker.Id的值
			    for(String rowStr :info.getOutList()) {
				  if(isConfigRow(rowStr)){
					  if(rowStr.indexOf(BROKER_ID)>-1){
						  sedCmdData.append(cmd.sedCmd(
								  "\"s/" + rowStr
										  + "/" + BROKER_ID + "=" + midwareKafkaint.getKafkaintId()
										  + "/\"" + " "
								  , serverPropertiesMap.get(midwareKafkaint.getKafkaintId()))+ SysConfigConstants.SHELL_LINK_SIGN);
						  continue;
					  }
					  //修改zookeeper.connect：的值
					  if(rowStr.indexOf(ZOOKEEPER_CONNECT)>-1){
						  sedCmdData.append(cmd.sedCmd(
								  "\"s+" + rowStr
										  + "+" + ZOOKEEPER_CONNECT + getZKUrl(Integer.valueOf(getZkMidwareId(midwareKafkaint.getMidwareId())),midwareKafkaint.getMidwareId())
										  + "+\"" + " "
								  , serverPropertiesMap.get(midwareKafkaint.getKafkaintId()))+ SysConfigConstants.SHELL_LINK_SIGN);
						  continue;
					  }
					  //修改日志的存放路径log.dirs
					  if(rowStr.indexOf(LOG_DIRS)>-1){
						  sedCmdData.append(cmd.sedCmd(
								  "\"s/" + getNewDir(rowStr)
										  + "/" + LOG_DIRS + "=" + getNewDir(exString(LOG_DIRS_DIR,midwareKafkaint))
										  + "/\"" + " "
								  , serverPropertiesMap.get(midwareKafkaint.getKafkaintId()))+ SysConfigConstants.SHELL_LINK_SIGN);
						  continue;
					  }
				  }
			  }
		  }
			String cmdStr = sedCmdData.toString();

			shellExcuteService.doCmd(ip,serUser,serPwd, cmdStr.substring(0,cmdStr.lastIndexOf(SysConfigConstants.SHELL_LINK_SIGN)));
		}
	}

	//是否是配置文件中正式的配置行
	private boolean isConfigRow(String rowStr){
		if(DataUtil.isNull(rowStr)){
			return false;
		}
		if(rowStr.indexOf("=")<0){
			return false;
		}
		if(rowStr.startsWith("#")){
			return false;
		}
		return true;
	}
	//根据kafka的中间键查出该kafka集群绑定的zookeeper集群ID
    private  String getZkMidwareId(int midwareId){
		EcmMidwareInfo midware =new EcmMidwareInfo();
		midware.setMidwareId(midwareId);
		return omsBaseService.selectByPrimaryKey(midware).getKfkZksId();
	}
	private String getZKUrl(int midwareZkId,int midwareId) {
		List<EcmMidwareZkint>  zkList = zookeeperInfoService.findByMid(midwareZkId);//通过中间件查找zk实例列表
	//	String cxnStr  = updateZkConf.zkUrl(zkList);//生成zk地址串
		String cxnStr  = updateZkConf.zkNodeUrl(zkList, "kafka"+midwareId);//生成zk地址串  格式为：192.168.165.161：8080，192.168.165.11：8080/wangbinaf
        return cxnStr;
	}

	private static String getNewDir(String oldDir ){
		String newDir = oldDir.replaceAll("/", "\\\\/");
				return newDir;
	}

}
