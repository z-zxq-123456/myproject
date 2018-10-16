package com.dcits.ensemble.om.oms.constants;
/**
 * shell执行常量*
 * @author tangxlf
 * @date   2015-8-6
 */
public class ShellExcuteConstants {	
	
	
    public static String TOMCAT_START_SHELL = "0040004";//tomcat启动Shell
	
	public static String TOMCAT_STOP_SHELL  = "0040005";//tomcat停止Shell		
	
	public static String App_ISOK_SUBCODE = "001";//应用是否正常子码 ，需要和操作系统对应码组成参数编码	
	
	public static String App_SCP_SUBCODE = "002";//文件传输子码 ，需要和操作系统对应码组成参数编码
	
	public static String App_TARX_SUBCODE = "003";//tar 文件解压缩
	
	public static String App_MKDIR_SUBCODE = "004";//创建目录
	
	public static String App_RENAME_SUBCODE = "005";//重命名	
	
	public static String App_RMR_SUBCODE = "006";//删除目录
	
	public static String App_CD_SUBCODE = "007";//CD到指定目录	
	
	public static String App_LS_SUBCODE = "008";//列出文件和目录
	
	public static String App_RM_SUBCODE = "009";//删除文件
	
	public static String App_CPA_SUBCODE = "010";//拷贝目录
	
}
