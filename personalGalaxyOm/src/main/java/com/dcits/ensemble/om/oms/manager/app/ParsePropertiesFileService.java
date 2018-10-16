package com.dcits.ensemble.om.oms.manager.app;

import com.dcits.dynamic.web.mapper.PkList;
import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.ensemble.om.oms.common.DataUtil;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.dao.utils.OMSIDao;
import com.dcits.ensemble.om.oms.module.app.AppConfigInfo;
import com.dcits.ensemble.om.oms.module.app.EcmAppIntant;
import com.dcits.ensemble.om.oms.module.utils.ShellResult;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import com.dcits.ensemble.om.oms.service.utils.ShellExcuteService;
import com.dcits.ensemble.om.oms.shellcmd.CmdFactory;
import com.dcits.ensemble.om.oms.shellcmd.ICmd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 解析属性文件* 
 * @author luolang
 * @date 2015-10-22 
 */
@Service
public class ParsePropertiesFileService {
	private  final Logger log = LoggerFactory.getLogger(this.getClass());
	@Resource
	CmdFactory cmdFactory;
	
	@Resource
	ShellExcuteService shellService;	
	
	@Resource
	ParamComboxService paramComboxService;
	
	@Resource
	private OMSIDao omsBaseDao;
	
	@Resource
	CommonShellService commonShellService;
	@Resource
	PropertiesCacheService propertiesCacheService;
	/**
	 * 获取属性文件列表
	 * @param   appIntantId  应用实例ID
	 * @return  List<String>   属性文件名列表
	 */
	 public List<PkList> getPropertiesFileList(Integer appIntantId) {
		    List<PkList> fileList  = new ArrayList<PkList>();
		    if(appIntantId==null){
		    	 return fileList;
		    }
		    EcmAppIntant queryIntant = new EcmAppIntant();
		    queryIntant.setAppIntantId(appIntantId);
		    EcmAppIntant intant =omsBaseDao.findListByCond(queryIntant).get(0);
			if(intant.getAppIntantVer()==null){//如果实例当前版本号为空，也就是尚未部署,此时未有配置文件
				 return fileList;
			}
		    intant.setAppWork(commonShellService.parseAppIntantWorkName(intant,null));	
		    ICmd  cmd = cmdFactory.getCmd(intant.getSerOs());
		    String cdCmd = cmd.cdCmd(AppPathHelp.createAppConfPath(intant, paramComboxService));
	    	String lsCmd = cmd.lsCmd("galaxy*.properties");	
			ShellResult info = shellService.doCmd(intant.getSerIp(), intant.getSerUser(), intant.getSerPwd(),
					cdCmd+SysConfigConstants.SHELL_LINK_SIGN
					+lsCmd);
			for(String rowStr :info.getOutList()) {				
				if(isConfigFileRow(rowStr)){
					PkList fileMap = new PkList();
					fileMap.setPkDesc(rowStr);
					fileMap.setPkValue(rowStr);
					fileList.add(fileMap);
				}
	        }
	        return fileList;
	 }	
	 
	 /**
	  * 获取属性文件属性及取值 
	  * @param   Integer appIntantId  应用实例ID	 
	  * @param   String        fileName  文件名
	  * @return  List<AppConfigInfo>     文件属性列表
	  */
	  public List<AppConfigInfo> getPropertiesList(Integer appIntantId,String fileName) {
			    List<AppConfigInfo> propertiesList  = new ArrayList<AppConfigInfo>();
			    if(appIntantId==null||fileName==null){
			    	 return propertiesList;
			    }
			    EcmAppIntant queryIntant = new EcmAppIntant();
			    queryIntant.setAppIntantId(appIntantId);
			    EcmAppIntant intant =omsBaseDao.findListByCond(queryIntant).get(0);
			    intant.setAppWork(commonShellService.parseAppIntantWorkName(intant,null));	
			    ICmd cmd = cmdFactory.getCmd(intant.getSerOs());
			    String cdCmd = cmd.cdCmd(AppPathHelp.createAppConfPath(intant, paramComboxService));
			    String catCmd = cmd.catCmd(fileName);//查看指定文件	
				ShellResult info = shellService.doCmd(intant.getSerIp(), intant.getSerUser(), intant.getSerPwd(),
						cdCmd+ SysConfigConstants.SHELL_LINK_SIGN
						+catCmd);
				for(String rowStr :info.getOutList()) {					
					if(isConfigRow(rowStr)){
						AppConfigInfo configInfo = new AppConfigInfo();
						String[] dataArray = rowStr.split("=",2);
						configInfo.setAppIntantId(intant.getAppIntantId());
						configInfo.setAppId(intant.getAppId());
						configInfo.setFileName(fileName);
						configInfo.setConfigKey(DataUtil.checkNull(dataArray[0]));
						configInfo.setConfigValue(DataUtil.changeNull(dataArray[1]));
						propertiesList.add(configInfo);
					}
		        }
		        return propertiesList;
	 }	
	/**
	 * 修改应用实例属性文件属性值 
	 * @param   AppConfigInfo configInfo 属性对象		 
	 */
	 public void updateAppIntantFileProperties(AppConfigInfo configInfo){
		    if(DataUtil.isNull(configInfo.getUpdateConfigValue())) {
               return;
			}
		    EcmAppIntant queryIntant = new EcmAppIntant();
		    queryIntant.setAppIntantId(configInfo.getAppIntantId());
		    EcmAppIntant intant =omsBaseDao.findListByCond(queryIntant).get(0);
		    excuteAppInantSedCmd(intant,configInfo);
	 }
	/**
	 * 修改应用所有实例属性文件属性值 
	 * @param   AppConfigInfo configInfo 属性对象		 
	 */
	 public void updateAppFileProperties(AppConfigInfo configInfo){
		   EcmAppIntant queryIntant = new EcmAppIntant();
		   queryIntant.setAppId(configInfo.getAppId());
		   List<EcmAppIntant> intantList =omsBaseDao.findListByCond(queryIntant);
		   for(EcmAppIntant intant:intantList){
			   excuteAppInantSedCmd(intant,configInfo);
		   }
	}	 
	/**
	 * 修改先后升级应用所有实例属性文件属性值
	 * @param   AppConfigInfo configInfo 属性对象	
	 * @param   List<EcmAppIntant>  intantList 实例对象列表	 
	 */
	public void updateAppConfig(AppConfigInfo configInfo,List<EcmAppIntant>  intantList){
		 for(EcmAppIntant intant:intantList){
		     excuteAppInantSedCmd(intant,configInfo);
		 } 
	}	
	
	/**
	 * 修改所有应用所有实例属性文件属性值 
	 * @param   AppConfigInfo configInfo 属性对象		 
	 */
	public void updateAllAppFileProperties(AppConfigInfo configInfo){
		  EcmAppIntant queryIntant = new EcmAppIntant();
		  List<EcmAppIntant> intantList =omsBaseDao.findListByCond(queryIntant);
		  for(EcmAppIntant intant:intantList){
			  excuteAppInantSedCmd(intant,configInfo);
		  }
	}
	/**
	 * 修改中间件实例对应的属性时，修改对应的关联中间件的应用实例属性文件属性值
	 * @param   AppConfigInfo configInfo 属性对象	
	 * @param   List<EcmAppIntant>  intantList 实例对象列表	 
	 */
	public void updateOrInsertAppConfig(AppConfigInfo configInfo,List<EcmAppIntant>  intantList){
		 for(EcmAppIntant intant:intantList){
			 excuteAppInantUpdateOrInsertCmd(intant,configInfo);
		 } 
	}	
    /**
	 * 获取属性文件属性及取值 
	 * @param   EcmAppIntant  intant    容器实例对象	 
	 * @param   String        filePath  文件路径
	 * @return  Map<String, String>     属性及取值Map
	 */
     public Map<String, String> parseFileAllProperties(EcmAppIntant intant,String filePath) {
    	Map<String,String> propertiesMap  = new HashMap<String,String>();     	    	
    	String catCmd = cmdFactory.getCmd(intant.getSerOs()).catCmd(filePath);//查看指定文件
    	String path = filePath.substring(0,filePath.lastIndexOf("/"));
    	String fileOrDirName = filePath.substring(filePath.lastIndexOf("/")+1);
    	log.info("path = "+path +" fileOrDirName = "+fileOrDirName);
    	if(commonShellService.isExistFileOrDir(intant, path, fileOrDirName)){
    		ShellResult info = shellService.doCmd(intant.getSerIp(), intant.getSerUser(), intant.getSerPwd(),catCmd);
    		for(String rowStr :info.getOutList()) {
    		   if(isConfigRow(rowStr)){
    			   String[] dataArray = rowStr.split("=",2);
    	           propertiesMap.put(DataUtil.checkNull(dataArray[0]), DataUtil.changeNull(dataArray[1]));
    		   }
            }
    	}
    	return propertiesMap;
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
     
    //是否是需要维护的配置文件
    private boolean isConfigFileRow(String rowStr){    	
    	 if(rowStr.indexOf("error")>0){
    		 return false;
    	 }    	  	 
    	 return true;
    }
    //执行单次替换命令
    private void excuteAppInantSedCmd(EcmAppIntant intant,AppConfigInfo configInfo){
		System.out.print("configInfoexcuteAppInantSedCmd="+configInfo);
    	  intant.setAppWork(commonShellService.parseAppIntantWorkName(intant,null));	
		  String path = AppPathHelp.createAppConfPath(intant, paramComboxService);
		  if(commonShellService.isExistFileOrDir(intant,path,configInfo.getFileName())){
				   ICmd cmd = cmdFactory.getCmd(intant.getSerOs());
				   String cdCmd = cmd.cdCmd(path);		  
				   String sedArgs = createSedStr(cmd,intant,configInfo);
				   String sedCmd = cmd.sedCmd(sedArgs,configInfo.getFileName());
				   shellService.doCmd(intant.getSerIp(), intant.getSerUser(), intant.getSerPwd(),
								cdCmd+ SysConfigConstants.SHELL_LINK_SIGN
							   +sedCmd);
				   propertiesCacheService.removeProperties(intant.getAppIntantId());//删除缓存中存储的该应用实例配置参数
		  }
    }
    //生成SED命令串
    private String createSedStr(ICmd cmd,EcmAppIntant intant,AppConfigInfo configInfo){
    	 return "\"s/"+createOldPropertiesStr(cmd,intant,configInfo)
    			 +"/"+configInfo.getConfigKey()+"="+replaceSpceialChar(configInfo.getUpdateConfigValue())
    			 +"/\""+" "+configInfo.getFileName();
    }
    //属性存在则替换，若不存在则新增属性
    private void excuteAppInantUpdateOrInsertCmd(EcmAppIntant intant,AppConfigInfo configInfo){
    	  intant.setAppWork(commonShellService.parseAppIntantWorkName(intant,null));	
		  String path = AppPathHelp.createAppConfPath(intant, paramComboxService);
		  if(commonShellService.isExistFileOrDir(intant,path,configInfo.getFileName())){
				   ICmd cmd = cmdFactory.getCmd(intant.getSerOs());
				   String cdCmd = cmd.cdCmd(path);					   
				   String updateOrInsertmd = createUpdateOrInsertStr(cmd,intant,configInfo);  				     
				   shellService.doCmd(intant.getSerIp(), intant.getSerUser(), intant.getSerPwd(),
								cdCmd+ SysConfigConstants.SHELL_LINK_SIGN
							   +updateOrInsertmd);
				   propertiesCacheService.removeProperties(intant.getAppIntantId());//删除缓存中存储的该应用实例配置参数
		  }else{
			  throw new GalaxyException("实例名为:"+intant.getAppIntantName()
		              +" IP为:"+intant.getSerIp()
		              +" 不存在属性文件名为:"+configInfo.getFileName()
		              +"文件,请检查!");
		  }
		  
    }
    //替换或者新增SHELL命令
    private String createUpdateOrInsertStr(ICmd cmd,EcmAppIntant intant,AppConfigInfo configInfo){
    	String cdCmd = cmd.cdCmd(AppPathHelp.createAppConfPath(intant, paramComboxService));
	    String catCmd = cmd.catCmd(configInfo.getFileName());//查看指定文件	
		ShellResult info = shellService.doCmd(intant.getSerIp(), intant.getSerUser(), intant.getSerPwd(),
				cdCmd+ SysConfigConstants.SHELL_LINK_SIGN
				+catCmd);
		for(String rowStr :info.getOutList()) {					
			if(isConfigRow(rowStr)){
				if(rowStr.indexOf(configInfo.getConfigKey())>-1){
					String sedArgs = "\"s/"+replaceSpceialChar(rowStr)
			    			 +"/"+configInfo.getConfigKey()+"="+replaceSpceialChar(configInfo.getUpdateConfigValue())
			    			 +"/\""+" ";
					return cmd.sedCmd(sedArgs,configInfo.getFileName());
				}
			}
        }    	
    	return cmd.echoCmd("")+cmd.outputAddDirectFileCmd(configInfo.getFileName())+ SysConfigConstants.SHELL_LINK_SIGN+
    			cmd.echoCmd(configInfo.getConfigKey()+"="+replaceSpceialChar(configInfo.getUpdateConfigValue()))+cmd.outputAddDirectFileCmd(configInfo.getFileName());
    }
    //获取属性文件中待替换的属性串
    private String createOldPropertiesStr(ICmd cmd,EcmAppIntant intant,AppConfigInfo configInfo){
    	String cdCmd = cmd.cdCmd(AppPathHelp.createAppConfPath(intant, paramComboxService));
	    String catCmd = cmd.catCmd(configInfo.getFileName());//查看指定文件	
		ShellResult info = shellService.doCmd(intant.getSerIp(), intant.getSerUser(), intant.getSerPwd(),
				cdCmd+ SysConfigConstants.SHELL_LINK_SIGN
				+catCmd);
		for(String rowStr :info.getOutList()) {					
			if(isConfigRow(rowStr)){
				if(rowStr.indexOf(configInfo.getConfigKey())>-1){
					return replaceSpceialChar(rowStr);
				}
			}
        }
    	throw new GalaxyException("实例名为:"+intant.getAppIntantName()
    			              +" IP为:"+intant.getSerIp()
    			              +" 属性文件名为:"+configInfo.getFileName()
    			              +" 不存在属性:"+ configInfo.getConfigKey() +",请检查!");
    }
    //替换特殊字符
    public static  String replaceSpceialChar(String oldStr){
    	if(oldStr.indexOf("/")>=0){    		
    		oldStr = oldStr.replace("/", "\\/");
    	}
    	if(oldStr.indexOf("&")>=0){    		
    		oldStr = oldStr.replace("&", "\\&");
    	}
    	return oldStr;
    }
    
    public static void main(String[] args){
    	System.out.println(replaceSpceialChar("http://10.10"));
    }
    
}
		
