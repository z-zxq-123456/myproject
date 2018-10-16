package com.dcits.ensemble.om.oms.manager.zookeeper;

import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.ensemble.om.oms.common.CommonPathHelp;
import com.dcits.ensemble.om.oms.common.ProgressMessageUtil;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.manager.app.CommonShellService;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareZkint;
import com.dcits.ensemble.om.oms.module.server.EcmServerInfo;
import com.dcits.ensemble.om.oms.module.utils.ShellResult;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import com.dcits.ensemble.om.oms.service.utils.ShellExcuteService;
import com.dcits.ensemble.om.oms.shellcmd.CmdFactory;
import com.dcits.ensemble.om.oms.shellcmd.ICmd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 公共shell操作Service*
 * 
 * @author wangbinaf
 * @date 2016-04-29 
 */
@Service
public class ZkCommonShellService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
    private static final String TRANS_FILE ="远程传输安装文件";
    
    private static final String GET_ZK_NAME ="获取应用实例名称";	
    
	private static final String DELETE_FILE_OPERATION ="删除文件操作";
	
	private static final String DELETE_DIR_OPERATION ="删除目录操作";
	
	private static final String IS_EXIST_DIRORFILE ="是否存在文件或者目录";
	
	public static String GALAXY_CLIENT_PORT = "clientPort";//端口号
	
	public static String GALAXY_ZOO_FILE  = "zoo.cfg";//系统配置文件
	
	public static String GALAXY_MYID  = "myid";
	
	@Resource
	ShellExcuteService shellService;
	@Resource
	CmdFactory cmdFactory;
	@Resource
	CommonShellService commonShellService;
	@Resource
	ParamComboxService paramComboxService;
	
	/**
	 * 获取ZK的应用工作目录	  
	 * @param EcmMidwareZkint  intant 应用实例对象
	 * @param String        userId 用户ID		 
	 */
	public String parseAppIntantWorkName(EcmMidwareZkint intant,String userId) {
			log.info("intant.getZkintStatus:"+intant.getZkintStatus());
			if(intant.getZkintStatus().equals(SysConfigConstants.APP_INTANTSTATUS_NODEPLOY)){//如果ZK实例尚未部署,此时未有应用工作目录
				log.error(GET_ZK_NAME+"出错,该ZK实例尚未安装!");
				throw new GalaxyException(GET_ZK_NAME+"出错,,该ZK实例尚未安装!");
			}
			return   CommonPathHelp.handlePath(intant.getMidwarePath()+"/"+"zookeeper"+"-"+intant.getMidwareVerNo()+"/");
	}
	/**
	 * 远程传输文件	 
	 * @param EcmMidwareZkint  intant 应用实例对象
	 * @param String        userId 用户ID		 
	 */
	public void scpZkFile(EcmMidwareZkint intant,String userId) {
		log.info("intant:"+intant);
		//ProgressMessageUtil.addProgressAction(userId,TRANS_FILE);
		ProgressMessageUtil.startProgress("" + userId, intant.getSerIp(), intant.getZkintName());
		try {
			log.info("start");
			EcmServerInfo serverInfo = commonShellService.getOmsServerInfo();
			ICmd cmd = cmdFactory.getCmd(intant.getSerOs());
			String path= CommonPathHelp.createTotalPath(intant.getMidwareVerPath(),paramComboxService);//获取中间件版本的绝对路径
			String scpCmd =cmd.scpCmd(path+" "+intant.getSerUser()+"@"+intant.getSerIp()+":"+intant.getMidwarePath());
			shellService.doCmd(serverInfo.getSerIp(),serverInfo.getSerUser(), serverInfo.getSerPwd(),scpCmd);				
		} catch (Exception e) {
			log.error(TRANS_FILE+"出错,错误信息为："+e.getMessage());
			throw new GalaxyException(TRANS_FILE+"出错,错误信息为："+e.getMessage());
		}
	}
	/**
	 * 删除文件 
	 * @param EcmMidwareZkint  intant     应用实例对象
	 */	
	public void  deleteFile(EcmMidwareZkint intant){
		try {
			String path = intant.getMidwareVerPath();
			String fileName =path.substring(path.lastIndexOf("/")+1);//解压缩应用安装文件
			ICmd cmd = cmdFactory.getCmd(intant.getSerOs());
			shellService.doCmd(intant.getSerIp(),intant.getSerUser(),intant.getSerPwd(),
					cmd.cdCmd(intant.getMidwarePath())+ SysConfigConstants.SHELL_LINK_SIGN //cd到应用安装路径
							+cmd.rmCmd(fileName)); 
		} catch (IllegalStateException e) {
			log.error(DELETE_FILE_OPERATION+"出错,错误信息为："+e.getMessage());
			throw new GalaxyException(DELETE_FILE_OPERATION+"出错!");
		}
	}
	 /**
	  *  应用zk全量部署    删除工作目录 然后解压缩
	  * @param EcmMidwareZkint  intant zk实例对象	 
	  */
	public void assemToalZk(EcmMidwareZkint intant,String userId) {
		try {					
			ICmd cmd = cmdFactory.getCmd(intant.getSerOs());
			String path = intant.getMidwareVerPath();
			String tarCmd = cmd.tarCmd(path.substring(path.lastIndexOf("/")+1));//解压缩应用安装文件
			shellService.doCmd(intant.getSerIp(),intant.getSerUser(),intant.getSerPwd(),cmd.cdCmd(intant.getMidwarePath())+ SysConfigConstants.SHELL_LINK_SIGN //cd到应用安装路径
									+tarCmd); 				
		 } catch (Exception e) {
		    log.error("全量部署zk实例出错,错误信息为："+e.getMessage());
			throw new GalaxyException("全量部署zk实例出错!");
		}
	}
	 /**
	 *  修改配置文件
	 * @param EcmMidwareZkint  intant zk实例对象
	 * @param List<EcmMidwareZkint>  list	zk实例对象 列表
	 */
	public void updateZkConf(EcmMidwareZkint intant,String userId,List<EcmMidwareZkint> list) {
		try {
			 ICmd cmd = cmdFactory.getCmd(intant.getSerOs());
			 String sedCmd = cmd.sedCmd(pingSedCmd(intant),GALAXY_ZOO_FILE);//修改文件
			 shellService.doCmd(intant.getSerIp(),intant.getSerUser(),intant.getSerPwd(),cmd.cdCmd(ZkPathHelp.createZkConfPath(intant,paramComboxService))+ SysConfigConstants.SHELL_LINK_SIGN //cd到应用安装路径
						+sedCmd); //cd到conf目录下并修改zoo.cfg文件	
			 //如果是集群模式则追加zoo.cfg
		     if(list.size()!=1){ 
				 for(EcmMidwareZkint temp:list){
					String echoCmd = cmd.echoCmd(pingechoCmd(temp,cmd));
					shellService.doCmd(intant.getSerIp(),intant.getSerUser(),intant.getSerPwd(),cmd.cdCmd(ZkPathHelp.createZkConfPath(intant,paramComboxService))+ SysConfigConstants.SHELL_LINK_SIGN //cd到应用安装路径
											+echoCmd); //cd到conf目录下并追加zoo.cfg文件	
				 }
		     }
		 } catch (Exception e) {
			log.error("修改ZK配置文件出错,错误信息为："+e.getMessage());
			throw new GalaxyException("修改ZK配置文件出错");
		}
	}
	/**
	 *  在DATEDIR路径下创建myId文件
	 * @param EcmMidwareZkint  intant zk实例对象
	 * @param List<EcmMidwareZkint>  list	zk实例对象  列表
	 */
	public void creatMyId(EcmMidwareZkint intant,List<EcmMidwareZkint> list) {
		try {
			ICmd cmd = cmdFactory.getCmd(intant.getSerOs());
			String mkdirCmd = cmd.mkdirCmd(paramComboxService.getParaRemark1(SysConfigConstants.SYS_ZK_DATE));
			String mkdirCmd1 = cmd.mkdirCmd(paramComboxService.getParaRemark1(SysConfigConstants.SYS_ZK_LOGS));
			String echoCmd = cmd.echoCmd(""+"\""+intant.getZkintNodeNum()+"\""+cmd.outputRedirectFileCmd(GALAXY_MYID));
			shellService.doCmd(intant.getSerIp(),intant.getSerUser(),intant.getSerPwd(),cmd.cdCmd(ZkPathHelp.createZookeeperPath(intant,paramComboxService))+ SysConfigConstants.SHELL_LINK_SIGN //cd到应用安装路径
					+mkdirCmd+ SysConfigConstants.SHELL_LINK_SIGN+mkdirCmd1); //创建zkdata和logs文件
			//如果是集群模式则需要创建Myid文件
			if(list.size()!=1){ 
				shellService.doCmd(intant.getSerIp(),intant.getSerUser(),intant.getSerPwd(),cmd.cdCmd(ZkPathHelp.createZkdataPath(intant,paramComboxService))+ SysConfigConstants.SHELL_LINK_SIGN
						+echoCmd);//CD到DATEDIR下创建myId文件
		    }
		 } catch (Exception e) {
			log.error("创建myid文件出错,错误信息为："+e.getMessage());
			throw new GalaxyException("创建myid文件出错");
		}
	}
	/**
	 * Zookeeper下的bin目录下的命令授权
	 * @param EcmMidwareZkint  intant zk实例对象
	 */
	public void chmodZkBin(EcmMidwareZkint intant){
		try {
			ICmd cmd = cmdFactory.getCmd(intant.getSerOs());
			String chmodxCmd = cmd.chmodxCmd(""+"*.sh");
			shellService.doCmd(intant.getSerIp(),intant.getSerUser(),intant.getSerPwd(),cmd.cdCmd(ZkPathHelp.createZkBinPath(intant,paramComboxService))+ SysConfigConstants.SHELL_LINK_SIGN //cd到应用安装路径
					+chmodxCmd); //CD到bin目录下进行授权
		 } catch (Exception e) {
			log.error("文件授权出错,错误信息为："+e.getMessage());
			throw new GalaxyException("文件授权出错");
		}
	}
	/**
	 * 拼sed命令
	 * @param EcmMidwareZkint  intant zk实例对象
	 */
    public  String pingSedCmd(EcmMidwareZkint intant){
		return  CommonPathHelp.handlePath(""+"\""+"s/"+ GALAXY_CLIENT_PORT + "=.*/" +GALAXY_CLIENT_PORT+"="+intant.getZkintClientPort()+"/"+"\"");
	}
    /**
	 * 拼echo命令
	 * @param EcmMidwareZkint  intant zk实例对象
	 */
	public  String pingechoCmd(EcmMidwareZkint intant,ICmd cmd){
		return  CommonPathHelp.handlePath(""+"server."+intant.getZkintNodeNum()+"="+intant.getSerIp()+":"+intant.getZkintCommPort()+":"+intant.getZkintElectPort()+cmd.outputAddDirectFileCmd(GALAXY_ZOO_FILE));
	}
	/**
	 * 是否存在该文件或者目录 
	 * @param EcmMidwareZkint  intant ZK实例对象
	 * @param String        path   文件或者目录路径	
	 * @param String        FileOrDirName 文件或者目录路径名称	
	 * @return boolean true 存在 false 不存在
	 */	
	public boolean isExistFileOrDir(EcmMidwareZkint intant,String path){
		try {
			ICmd cmd = cmdFactory.getCmd(intant.getSerOs());
			String temp = ZkPathHelp.createZookeeperPath(intant,paramComboxService);
			String fileName = temp.substring(temp.lastIndexOf("/")+1);
			log.info("fileName:"+fileName);
			String cdCmd = cmd.cdCmd(path);//到应用目录
			String lsCmd = cmd.lsCmd("");//LS到ZK实例ID的应用目录名
			ShellResult result=shellService.doCmd(intant.getSerIp(),intant.getSerUser(),intant.getSerPwd(),
					cdCmd+ SysConfigConstants.SHELL_LINK_SIGN
				   +lsCmd);	
			log.info("result:"+result);
			for(String rowStr:result.getOutList()){		
				if(rowStr!=null&&rowStr.equals(fileName)){
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
	 * 执行清理实例时，删除文件
	 * @param EcmMidwareZkint  intant     应用实例对象
	 */	
	public void  deleteDir(EcmMidwareZkint intant){
		try {	
			ICmd cmd = cmdFactory.getCmd(intant.getSerOs());
			String path = intant.getMidwarePath();
			String temp = ZkPathHelp.createZookeeperPath(intant,paramComboxService);
			String fileName = temp.substring(temp.lastIndexOf("/")+1);
			shellService.doCmd(intant.getSerIp(),intant.getSerUser(),intant.getSerPwd(),
					cmd.cdCmd(path)+ SysConfigConstants.SHELL_LINK_SIGN //CD到应用安装路径
					+cmd.rmrCmd(fileName)); 
		} catch (IllegalStateException e) {
			log.error(DELETE_DIR_OPERATION+"出错,错误信息为："+e.getMessage());
			throw new GalaxyException(DELETE_DIR_OPERATION+"出错!");
		}
	}	

}