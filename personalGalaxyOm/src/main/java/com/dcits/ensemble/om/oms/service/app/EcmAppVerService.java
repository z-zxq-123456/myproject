package com.dcits.ensemble.om.oms.service.app;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.ensemble.om.oms.common.CommonPathHelp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dcits.galaxy.base.util.DateUtils;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.dao.utils.OMSIDao;
import com.dcits.ensemble.om.oms.manager.app.AppPathHelp;
import com.dcits.ensemble.om.oms.manager.app.CommonShellService;
import com.dcits.ensemble.om.oms.manager.app.HandlerSameJar;
import com.dcits.ensemble.om.oms.module.app.EcmAppInfo;
import com.dcits.ensemble.om.oms.module.app.EcmAppIntant;
import com.dcits.ensemble.om.oms.module.app.EcmAppVer;
import com.dcits.ensemble.om.oms.module.server.EcmServerInfo;
import com.dcits.ensemble.om.oms.module.utils.ShellResult;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import com.dcits.dynamic.web.util.dao.PkServiceUtil;
import com.dcits.ensemble.om.oms.service.utils.FileOperService;
import com.dcits.ensemble.om.oms.service.utils.ShellExcuteService;
import com.dcits.ensemble.om.oms.shellcmd.CmdFactory;
import com.dcits.ensemble.om.oms.shellcmd.ICmd;


/**
 * 应用版本管理Service* 
 * @author tangxlf
 * @date 2015-11-26
 */
@Service
public class EcmAppVerService {

	private  final Logger log = LoggerFactory.getLogger(this.getClass());
	@Resource
	private OMSIDao omsBaseDao;
	@Resource
	PkServiceUtil  serviceUtil;
	@Resource
	FileOperService fileOperService;
	@Resource
	ParamComboxService  paramComboxService;
	@Resource
	CommonShellService commonShellService;
	@Resource
	ShellExcuteService shellService;
	@Resource
	CmdFactory  cmdFactory;

	private static final String ADD_PATH="add";//增量保存路径

	private static final String TOTAL_PATH="all";//全量量保存路径


	/**
     * 应用版本的上传及记录保存
     * @param EcmAppVer  appVer       应用版本对象
     * @param int    userId           用户ID
     */
	public void save(EcmAppVer  appVer,String userId) {
        try {
            int appVerId = serviceUtil.getMaxReqID(0, "ECM_APP_VER", "APP_VER_ID");
            int appVerNum = serviceUtil.getMaxByCon("ECM_APP_VER", "APP_VER_NUM", "APP_ID=" + appVer.getAppId());
            EcmAppInfo appInfo = new EcmAppInfo();
            appInfo.setAppId(appVer.getAppId());
            appInfo = omsBaseDao.selectByPrimaryKey(appInfo);
            appVer.setAppName(appInfo.getAppName());
            appVer.setAppSimpenNm(appInfo.getAppSimpenNm());
            appVer.setAppVerId(appVerId);
            appVer.setAppVerUserid(userId);
            appVer.setAppVerDate(DateUtils.getDate());
            appVer.setAppVerNum(appVerNum);
            if (appVer.getAppVerType().equals(SysConfigConstants.APP_ASSEMTYPE_ADD)) {
                addVersionSave(appVer);
            } else {
                allVersionSave(appVer);
            }
            omsBaseDao.insert(appVer);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GalaxyException(e.getMessage());
        }
    }
    //全量量版本保存
    private void allVersionSave(EcmAppVer  appVer) {
        try {
            String fileUrl = createFileUrl(appVer, TOTAL_PATH);
            appVer.setAppVerPath(fileUrl);
            fileOperService.singleFileUpload(appVer.getSourceFile(), fileUrl);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GalaxyException(e.getMessage());
        }
    }
    
    //增量版本保存
    private void addVersionSave(EcmAppVer  appVer) {
        try {
            String fileUrl = createFileUrl(appVer, ADD_PATH);
            fileOperService.singleFileUpload(appVer.getSourceFile(), fileUrl);
            appVer.setAppVerPath(fileUrl);
            mergeAddVersionToTotal(appVer);
            appVer.setAppVerPath(createFileUrl(appVer, TOTAL_PATH));
        } catch (Exception e) {
            e.printStackTrace();
            throw new GalaxyException(e.getMessage());
        }
    }
    
    //增量版本与前一个全量版本合并生成新的全量版本
    private void mergeAddVersionToTotal(EcmAppVer  appVer){
        try{
    	//获取前一个全量版本
    	EcmAppVer  maxAppVer = new EcmAppVer();
    	maxAppVer.setAppVerId(appVer.getAppVerId()-1);
    	maxAppVer =	omsBaseDao.selectByPrimaryKey(maxAppVer);
    	//获取当前服务器信息
    	EcmServerInfo serverInfo =commonShellService.getOmsServerInfo();
    	
    	String currentVerFileName = appVer.getSourceFile().getOriginalFilename();//当前版本文件名
    	String forwardVerFileName = getFileName(maxAppVer.getAppVerPath());//上一个版本文件名
    	
    	
    	EcmAppIntant intant = new EcmAppIntant();
    	intant.setSerOs(serverInfo.getSerOs());
    	intant.setSerIp(serverInfo.getSerIp());    	
    	intant.setSerUser(serverInfo.getSerUser());
    	intant.setSerPwd(serverInfo.getSerPwd());
    	intant.setAppPath(createAppUrl(appVer)+"/"+TOTAL_PATH);
    	ICmd cmd = cmdFactory.getCmd(intant.getSerOs());
    	String cdAppVerPath = cmd.cdCmd(createAppUrl(appVer));// cd到应用版本目录 例如:/home/galaxy/omsversion/NA/2 
    	String mkDirCmd = cmd.mkdirCmd(TOTAL_PATH);//创建全量目录
    	String cdTotalPath = cmd.cdCmd(TOTAL_PATH);//cd到全量目录
    	String cpToAppCmd = cmd.cpCmd(maxAppVer.getAppVerPath()+ " .");//拷贝上一个版本全量版本到当前版本全量目录下    	
		String tarForwardVerCmd = cmd.tarCmd(forwardVerFileName);//解压缩上一个版本全量版本应用安装文件
    	
    	
    	
		//拷贝上一个版本全量版本到当前版本全量目录下
		ShellResult result=shellService.doCmd(intant.getSerIp(),intant.getSerUser(),intant.getSerPwd(),
				    cdAppVerPath+SysConfigConstants.SHELL_LINK_SIGN 
				    +mkDirCmd+SysConfigConstants.SHELL_LINK_SIGN 
				    +cdTotalPath+SysConfigConstants.SHELL_LINK_SIGN 
				    +cpToAppCmd+SysConfigConstants.SHELL_LINK_SIGN 
				    +tarForwardVerCmd); 	
		String forwardWorkName = parseAppName(result.getOutList().get(0));//上一个版本工作目录
		intant.setAppWork(forwardWorkName);
		String cdAppLibCmd = cmd.cdCmd(AppPathHelp.createAppLibPath(intant, paramComboxService));
		
		//拷贝增量版本文件到临时目录
		String delTarFileCmd = cmd.rmCmd(forwardVerFileName);//删除上一个版本全量版本应用安装文件
		String mkDirTempWorkCmd = cmd.mkdirCmd(createTempWork());//创建临时目录
    	String cdTempWorkPathCmd = cmd.cdCmd(createTempWork());//cd到临时目录
    	String cpToTempWorkCmd = cmd.cpCmd(appVer.getAppVerPath()+ " .");//拷贝增量版本文件到临时目录
		shellService.doCmd(intant.getSerIp(),intant.getSerUser(),intant.getSerPwd(),
			    cdAppVerPath+SysConfigConstants.SHELL_LINK_SIGN 			    
			    +cdTotalPath+SysConfigConstants.SHELL_LINK_SIGN 
			    +delTarFileCmd+SysConfigConstants.SHELL_LINK_SIGN 
			    +mkDirTempWorkCmd+SysConfigConstants.SHELL_LINK_SIGN 
			    +cdTempWorkPathCmd+SysConfigConstants.SHELL_LINK_SIGN 
			    +cpToTempWorkCmd); 	
		String delCmds=createDelJarNames(intant,currentVerFileName);//待替换JAR包的删除命令
		String cdAppPathCmd=cmd.cdCmd(intant.getAppPath());	//cd到应用安装路径
		String mvCmd = "" ;
		if(!forwardWorkName.equals(intant.getAppWork())){
			mvCmd = cmd.mvCmd(forwardWorkName+" "+intant.getAppWork())+SysConfigConstants.SHELL_LINK_SIGN;
		}
		cpToAppCmd = cmd.cpCmd(" ./"+createTempWork()+"/"+intant.getAppWork()+ "  .");//拷贝应用临时目录到应用目录
		String delTempAppCmd = cmd.rmrCmd(createTempWork());//删除应用临时目录命令
		String tarcCmd = cmd.tarcCmd(" "+ currentVerFileName+" "+intant.getAppWork());//tar压缩合并后的全量版本生成最新安装文件
		String rmFowardWorkDirCmd=cmd.rmrCmd(intant.getAppWork());//删除上一个版本全量版本应用安装文件
		shellService.doCmd(intant.getSerIp(),intant.getSerUser(),intant.getSerPwd(),
				cdAppLibCmd+SysConfigConstants.SHELL_LINK_SIGN
			   +delCmds+" "+cdAppPathCmd+SysConfigConstants.SHELL_LINK_SIGN
			   +mvCmd
			   +cpToAppCmd+SysConfigConstants.SHELL_LINK_SIGN			 
			   +delTempAppCmd+SysConfigConstants.SHELL_LINK_SIGN
			   +tarcCmd+SysConfigConstants.SHELL_LINK_SIGN			 
			   +rmFowardWorkDirCmd);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GalaxyException(e.getMessage());
        }
    }
    //获取版本文件名
    private String getFileName(String pathName){    	
    	return pathName.substring(pathName.lastIndexOf("/")+1);
    }
    
    /**
	 * 生成待替换JAR包的删除命令
	 * 先解压缩，然后在相互比较，生成待替换JAR包的删除命令
	 * @param   EcmAppIntant  intant 应用实例对象	 
	 * @return  String       待替换JAR包的删除命令
	 */	
	private String createDelJarNames(EcmAppIntant  intant,String currentVerFileName){
		Map<String,String>  oldJarMap = new HashMap<String,String>();
		Map<String,String>  newJarMap = new HashMap<String,String>();		
		StringBuilder delCmds = new StringBuilder();
		log.info("增量部署筛选替换jar包时  查询旧 jar start");	
			
		ICmd cmd = cmdFactory.getCmd(intant.getSerOs());		
		String lsCmd = cmd.lsCmd("");//ls命令
		//获取应用lib目录jar 建立jar标准名称与jar版本名称关系
		//cd 到应用lib目录				
		String cdAppLibCmd = cmd.cdCmd(AppPathHelp.createAppLibPath(intant, paramComboxService));
		ShellResult result=shellService.doCmd(intant.getSerIp(),intant.getSerUser(),intant.getSerPwd(),
				cdAppLibCmd+SysConfigConstants.SHELL_LINK_SIGN
			   +lsCmd);
		log.info("增量部署筛选替换jar包时  查询旧 jar end  ShellResult result="+result);
		for(String jarName:result.getOutList()){
    		oldJarMap.put(HandlerSameJar.createStdJarName(jarName),jarName);
		}
        log.info("增量部署筛选替换jar包时  查询新 jar start");	
        String tempDirName =createTempWork();//临时目录名	
		//获取临时应用lib目录jar 建立jar标准名称与jar版本名称关系
        String cdAppPathCmd=cmd.cdCmd(intant.getAppPath()+"/"+tempDirName);	//cd到应用安装路径
        //String mkDirApptempCmd = cmd.mkdirCmd(tempDirName);//创建临时应用目录
		//解压缩应用安装文件到临时应用目录
		String  tarToTempDirCmd = cmd.tarCmd(currentVerFileName) ;//解压缩当前增量版本tar包
		
		result=shellService.doCmd(intant.getSerIp(),intant.getSerUser(),intant.getSerPwd(),
				cdAppPathCmd+SysConfigConstants.SHELL_LINK_SIGN
			 //  +mkDirApptempCmd + SysConfigConstants.SHELL_LINK_SIGN
			   +tarToTempDirCmd );
		String currentWorkName = parseAppName(result.getOutList().get(0));//当前工作目录

		//cd 到应用lib目录	
		intant.setAppWork(currentWorkName);
		String cdTempAppLibCmd = cmd.cdCmd(createTempAppLibPath(intant, paramComboxService));
		result=shellService.doCmd(intant.getSerIp(),intant.getSerUser(),intant.getSerPwd(),
				cdTempAppLibCmd+SysConfigConstants.SHELL_LINK_SIGN
			   +lsCmd);
		log.info("增量部署筛选替换jar包时  查询新 jar end  ShellResult result="+result);
		for(String jarName:result.getOutList()){
			newJarMap.put(HandlerSameJar.createStdJarName(jarName),jarName);
		}
		//log.info("oldJarMap="+oldJarMap);
		//log.info("newJarMap="+newJarMap);
		for(Map.Entry<String,String> entry : newJarMap.entrySet()){
			if(oldJarMap.containsKey(entry.getKey())){				
				delCmds.append(cmd.rmCmd(oldJarMap.get(entry.getKey()))+SysConfigConstants.SHELL_LINK_SIGN);
			}
		}		
		log.info("生成待替换JAR包的删除命令   :"+delCmds.toString());
		return delCmds.toString();
	}	
	
	
	//解析出应用安装名
	private String parseAppName(String rowResult){		
			return rowResult.split("/")[0];
	}
    /**
	 * 生成应用版本文件路径
	 * @param  EcmAppVer  appVer   应用版本对象
	 * @param  String addOrTotal   增量路径或者全量路径  	
	 * @return String  应用版本文件路径
     */	
	private String createFileUrl(EcmAppVer  appVer,String addOrTotal){		 
		  String saveUrl = paramComboxService.getParaRemark1(SysConfigConstants.APP_SAVE_URL);		
		  return saveUrl+"/"+appVer.getAppSimpenNm()+"/"+appVer.getAppVerNum()+"/"+ addOrTotal +"/"+appVer.getSourceFile().getOriginalFilename();		
	}
	
	/**
	 * 生成应用版本路径
	 * @param  EcmAppVer  appVer   应用版本对象	 	
	 * @return String  应用版本文件路径
     */	
	private String createAppUrl(EcmAppVer  appVer){		 
		  String saveUrl = paramComboxService.getParaRemark1(SysConfigConstants.APP_SAVE_URL);		
		  return saveUrl+"/"+appVer.getAppSimpenNm()+"/"+appVer.getAppVerNum();		
	}
	
	/**
     * 删除应用版本文件及版本记录   如果是增量版本 同时还要删除增量文件
     * @param EcmAppVer  appVer       应用版本对象     * 	     
     */
	public void delete(EcmAppVer  appVer) {	
		   if(appVer.getAppVerType().equals(SysConfigConstants.APP_ASSEMTYPE_ADD)){
			   fileOperService.deleteFile(appVer.getAppVerPath().replace("/"+TOTAL_PATH+"/","/"+ADD_PATH+"/"));		
		   }
		   fileOperService.deleteFile(appVer.getAppVerPath());
		   omsBaseDao.deleteByPrimaryKey(appVer);
	}
	
	private   String createTempWork(){
		return  ADD_PATH+SysConfigConstants.DIR_LINK_SIGN+ paramComboxService.getParaRemark1(SysConfigConstants.SYS_APP_ADD_TEMP_WORK);
    }  
	
	/**
	* 生成临时应用实例lib路径
	* @param EcmAppIntant        intant              应用实例对象
	* @param ParamComboxService  paramComboxService  参数缓存服务对象 	
	* @return String  临时应用实例lib路径
	*/ 
	public   String createTempAppLibPath(EcmAppIntant  intant,ParamComboxService  paramComboxService){
		   return  CommonPathHelp.handlePath(intant.getAppPath())+"/"+createTempWork()+"/"+intant.getAppWork()+"/"+paramComboxService.getParaRemark1(SysConfigConstants.SYS_LIB_WORK);
	}
}
