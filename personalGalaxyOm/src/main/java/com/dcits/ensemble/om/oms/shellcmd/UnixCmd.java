package com.dcits.ensemble.om.oms.shellcmd;


import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import org.springframework.stereotype.Component;

/**
 * unix shell命令* 
 * @author tangxlf
 * @date 2015-09-22 
 */
@Component
public class UnixCmd extends AbstractedCmd {
	
	

	@Override
	public String getOS() {		
		return SysConfigConstants.UNIX_OS;
	}
	public String sedCmd(String cmdArgs,String fileName) {
		return shellExcuteService.createOsCmd(getOS(),App_SED_SUBCODE,cmdArgs+" "+fileName+" 1<>"+fileName);
	}
}
