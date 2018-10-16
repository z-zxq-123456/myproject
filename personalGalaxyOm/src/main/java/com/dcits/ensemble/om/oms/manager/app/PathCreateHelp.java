package com.dcits.ensemble.om.oms.manager.app;

import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.ensemble.om.oms.common.CommonPathHelp;
import com.dcits.ensemble.om.oms.module.app.EcmAppIntant;
import com.dcits.ensemble.om.oms.module.utils.ShellResult;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import com.dcits.ensemble.om.oms.service.utils.ShellExcuteService;
import com.dcits.ensemble.om.oms.shellcmd.CmdFactory;
import com.dcits.ensemble.om.oms.shellcmd.ICmd;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 目录辅助生成类* 
 * @author tangxlf
 * @date 2015-11-25 
 */
@Component
public class PathCreateHelp {	
	@Resource
	ShellExcuteService shellService;
	@Resource
	ParamComboxService paramComboxService;
	@Resource
	CmdFactory cmdFactory;
	@Resource
	CommonShellService commonShellService;
   /**
    * 创建操作系统路径
    * @param  EcmAppIntant        intant              应用实例对象
    * @param  String              path                待创建的目录路径
    * @return String  应用实例临时目录名
    */ 
	public void createOsPath(EcmAppIntant intant,String path){
		    ICmd cmd = cmdFactory.getCmd(intant.getSerOs());
		    String userPath = CommonPathHelp.handlePath(getOsUserHomePath(intant));
				if(path.indexOf(userPath)==0){
					 shellService.doCmd(intant.getSerIp(),intant.getSerUser(),intant.getSerPwd(),
								cmd.mkdirCmd(path));
				}else{
					throw new GalaxyException("用户路径为:"+userPath + " 传入路径为:"+path + "  传入路径有误,请检查!");
				}
	}
	
	
   /**
	* 获取操作系统用户路径
	* @param EcmAppIntant        intant              应用实例对象	  
	* @return String  操作系统用户路径
	*/ 	
	public String  getOsUserHomePath(EcmAppIntant intant){
		ICmd cmd = cmdFactory.getCmd(intant.getSerOs());
		ShellResult result=shellService.doCmd(intant.getSerIp(),intant.getSerUser(),intant.getSerPwd(),
				cmd.pwdCmd(""));
		return result.getOutList().get(0);
	}
	
	public static void main(String[] args){
		//String path ="/a/b/c/d";
		//String path ="/a/b/c/d/";
		//String path ="a/b/c/d";
		String path ="a/b/c/d/";
		String[] array = path.split("/");
		for(int i=0;i<array.length;i++){
			System.out.println("this is "+ i + " value is "+array[i]);
		}
	}
	
	
	
  
}
