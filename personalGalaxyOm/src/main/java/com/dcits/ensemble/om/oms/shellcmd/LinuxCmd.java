package com.dcits.ensemble.om.oms.shellcmd;

import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import org.springframework.stereotype.Component;

/**
 * linux shell命令* 
 * @author tangxlf
 * @date 2015-09-22 
 */
@Component
public class LinuxCmd extends AbstractedCmd {
	
	
//	public ShellExcuteService getShellExcuteService() {
//		return shellExcuteService;
//	}
//	
//	public void setShellExcuteService(ShellExcuteService shellExcuteService) {		
//		this.shellExcuteService = shellExcuteService;
//		System.out.println(" shellExcuteService="+this.shellExcuteService);
//	}

//	@Resource
//	ShellExcuteService shellExcuteService;

	@Override
    public String getOS() {	
	   return SysConfigConstants.LINUX_OS;
    }

    public String sedCmd(String cmdArgs,String fileName) {
        return shellExcuteService.createOsCmd(getOS(),App_SED_SUBCODE,cmdArgs+" "+fileName);
    }
	
}
