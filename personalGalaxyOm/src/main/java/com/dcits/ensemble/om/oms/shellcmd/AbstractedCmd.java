package com.dcits.ensemble.om.oms.shellcmd;

import com.dcits.ensemble.om.oms.service.utils.ShellExcuteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * linux shell命令* 
 * @author tangxlf
 * @date 2015-09-22 
 */
@Component
public abstract class AbstractedCmd implements ICmd {
//	public ShellExcuteService getShellExcuteService() {
//		return shellExcuteService;
//	}
//	
//	public void setShellExcuteService(ShellExcuteService shellExcuteService) {		
//		this.shellExcuteService = shellExcuteService;
//		System.out.println(" shellExcuteService="+this.shellExcuteService);
//	}
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Resource
	ShellExcuteService shellExcuteService;
	
	
	public abstract String getOS();
	
	public String netstatCmd(String cmdArgs) {
		log.info("OsCode="+getOS()+" cmdArgs="+cmdArgs+" shellExcuteService="+shellExcuteService);
		return shellExcuteService.createOsCmd(getOS(),App_ISOK_SUBCODE,cmdArgs);
	}

	
	public String scpCmd(String cmdArgs) {
		return shellExcuteService.createOsCmd(getOS(),App_SCP_SUBCODE,cmdArgs);
	}

	
	public String tarCmd(String cmdArgs) {
		return shellExcuteService.createOsCmd(getOS(),App_TARX_SUBCODE,cmdArgs);
	}

	
	public String mkdirCmd(String cmdArgs) {
		return shellExcuteService.createOsCmd(getOS(),App_MKDIR_SUBCODE,cmdArgs);
	}

	
	public String mvCmd(String cmdArgs) {
		return shellExcuteService.createOsCmd(getOS(),App_RENAME_SUBCODE,cmdArgs);
	}

	
	public String rmrCmd(String cmdArgs) {
		return shellExcuteService.createOsCmd(getOS(),App_RMR_SUBCODE,cmdArgs);
	}

	
	public String cdCmd(String cmdArgs) {
		return shellExcuteService.createOsCmd(getOS(),App_CD_SUBCODE,cmdArgs);
	}

	
	public String lsCmd(String cmdArgs) {
		return shellExcuteService.createOsCmd(getOS(),App_LS_SUBCODE,cmdArgs);
	}

	
	public String rmCmd(String cmdArgs) {
		return shellExcuteService.createOsCmd(getOS(),App_RM_SUBCODE,cmdArgs);
	}

	
	public String cpCmd(String cmdArgs) {
		return shellExcuteService.createOsCmd(getOS(),App_CPA_SUBCODE,cmdArgs);
	}
	
	public String catCmd(String cmdArgs) {
		return shellExcuteService.createOsCmd(getOS(),App_CAT_SUBCODE,cmdArgs);
	}
	public String pwdCmd(String cmdArgs) {
		return shellExcuteService.createOsCmd(getOS(),App_PWD_SUBCODE,cmdArgs);
	}
	
	public String tarcCmd(String cmdArgs){
		return shellExcuteService.createOsCmd(getOS(),App_TARC_SUBCODE,cmdArgs);
	}
	
	public String chmodxCmd(String cmdArgs){
		return shellExcuteService.createOsCmd(getOS(),App_CHMODX_SUBCODE,cmdArgs);
	}
	
	public String tailCmd(String cmdArgs){
		return shellExcuteService.createOsCmd(getOS(),App_TAIL_SUBCODE,cmdArgs);
	}
	public String echoCmd(String cmdArgs){
		return shellExcuteService.createOsCmd(getOS(),ECHO_SOMETING,cmdArgs);
	}
	public String cpuCmd(String cmdArgs){
		return shellExcuteService.createOsCmd(getOS(),CPU_MESSAGE,cmdArgs);
	}
	public String sleepCmd(String cmdArgs){
		return shellExcuteService.createOsCmd(getOS(),SLEEP_COMMAND,cmdArgs);
	}
	public String ioCmd(String cmdArgs){
		return shellExcuteService.createOsCmd(getOS(),IO_MESSAGE,cmdArgs);
	}
	public String timeCmd(String cmdArgs){
		return shellExcuteService.createOsCmd(getOS(),TIME_COMMAND,cmdArgs);
	}
	public String memoryCmd(String cmdArgs){
		return shellExcuteService.createOsCmd(getOS(),MEMORY_MESSAGE,cmdArgs);
	}
	public String netCmd(String cmdArgs){
		return shellExcuteService.createOsCmd(getOS(),NET_MESSAGE,cmdArgs);
	}
	public String outputRedirectFileCmd(String cmdArgs){
		return shellExcuteService.createOsCmd(getOS(),OUTPUT_REDIRECT_FILE,cmdArgs);
	}
	
	public String outputAddDirectFileCmd(String cmdArgs){
		return shellExcuteService.createOsCmd(getOS(),OUTPUT_ADDDIRECT_FILE,cmdArgs);
	}
	public String echoruokCmd(String cmdArgs){
		return shellExcuteService.createOsCmd(getOS(),ECHO_RUOK,cmdArgs);
	}
}
