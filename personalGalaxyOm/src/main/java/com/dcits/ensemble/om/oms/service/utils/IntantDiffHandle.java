package com.dcits.ensemble.om.oms.service.utils;


import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.manager.app.AppPathHelp;
import com.dcits.ensemble.om.oms.module.app.EcmAppIntant;
import com.dcits.ensemble.om.oms.module.utils.ShellResult;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import com.dcits.ensemble.om.oms.shellcmd.CmdFactory;
/**
 * 一键式实例差异（端口号）处理* 
 * @author luolang
 * @date 2015-10-27
 */
@Service
public class IntantDiffHandle {
	private  final Logger log = LoggerFactory.getLogger(this.getClass());
    private static final String lsArgs = "*.properties";	
	private static Boolean flag = false;
	@Resource
	CmdFactory cmdFactory;	
	@Resource
	ShellExcuteService shellService;		
	@Resource
	ParamComboxService paramComboxService;
    
	/**
	  * 收集要处理的properties
	  */
	private List<String> allProperties(EcmAppIntant  intant) {		
	   String path = AppPathHelp.createAppConfPath(intant, paramComboxService);  	   
  	   String cdCmd = cmdFactory.getCmd(intant.getSerOs()).cdCmd(path);   	   
  	   String lsCmd = cmdFactory.getCmd(intant.getSerOs()).lsCmd(lsArgs);   	   
  	   ShellResult allPropertiesNames=shellService.doCmd(intant.getSerIp(),intant.getSerUser(),intant.getSerPwd(),  			   
  			   cdCmd+SysConfigConstants.SHELL_LINK_SIGN //cd到指定路径					
			  +lsCmd);
  	   log.info(""+allPropertiesNames);
	   return allPropertiesNames.getOutList();
	} 
	
	/**
	  * 遍历所有要properties,并处理目标对象
	  * @param String args为cat命令处理的对象
	  */
	private void traverseProperties(String args,EcmAppIntant  intant) {		
		String catCmd = cmdFactory.getCmd(intant.getSerOs()).catCmd(args);//从服务器指定properties中获取信息 	   
		ShellResult properties = shellService.doCmd(intant.getSerIp(), intant.getSerUser(), intant.getSerPwd(), catCmd);		
		for(String str:properties.getOutList()) {			
			if(str.indexOf(paramComboxService.getParaRemark1(SysConfigConstants.APP_START_DIFF_DEPLOY_CONFIG))>0){
				flag = true;				
				continue;
			}
			if(str.indexOf(paramComboxService.getParaRemark1(SysConfigConstants.APP_END_DIFF_DEPLOY_CONFIG))>0){
				flag = false;				
				continue;
			}
			if(flag&&str.indexOf("#"+intant.getSerIp())>0){
				sedProperties(str,intant, args);
			}
		}
	}

	/**
	 * 调用sed命令处理目标字段，sed -i "s/destr/sourstr/" filename 为原始Linux命令
	 * @param String str为预备处理的对象
	 * @param String args为sed处理路径
	 */
	private void sedProperties(String str,EcmAppIntant  intant,String args ) {			
		String[] sedArray = str.split("#",3);
		String sourstr = str;
  		String deststr = sedArray[1];
  		//通过sed命令 实现替换操作
  		String sedArgs = "\"s/"+sourstr+"/"+deststr+"/\"";
  		String sedCmd = cmdFactory.getCmd(intant.getSerOs()).sedCmd(sedArgs,args);
		shellService.doCmd(intant.getSerIp(), intant.getSerUser(), intant.getSerPwd(), sedCmd);
	}
		
	/**
	 * 实例差异处理机制
	 * @param  EcmAppIntant intant  	
	 *
     */	
    public void intantDiffHandle(EcmAppIntant intant) {
    	List<String> allPropertiesName = new ArrayList<String> ();    	   
    	allPropertiesName = allProperties(intant); 
    	String confPath = AppPathHelp.createAppConfPath(intant, paramComboxService);
    	for (String str :allPropertiesName) {    		   
    		    String args = confPath+"/"+str;    		   
    		    traverseProperties(args,intant);
    	}
    } 	  
}
