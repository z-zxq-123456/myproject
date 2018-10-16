package com.dcits.ensemble.om.oms.shellcmd;

import com.dcits.ensemble.om.oms.common.DataUtil;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * shell命令对象工厂* 
 * @author tangxlf
 * @date 2015-09-22 
 */
@Component
public class CmdFactory {
	
	private  final Map<String,ICmd> cmdMap =new HashMap<String,ICmd>();
	
	@Resource
	LinuxCmd linuxCmd;
	@Resource
	UnixCmd unixCmd;
	
	void setMap(){
		cmdMap.put(SysConfigConstants.LINUX_OS,linuxCmd);
		
		cmdMap.put(SysConfigConstants.UNIX_OS, unixCmd);
	}
	
	public ICmd getCmd(String osCode){
		if(cmdMap.isEmpty()){
			setMap();
		}
		DataUtil.checkNull("操作系统代码", osCode);
		return cmdMap.get(osCode);
	}
}
