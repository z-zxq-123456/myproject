package com.dcits.ensemble.om.oms.common;
/**
 * 系统中标志生成工具，促使生成统一的使用标志*
 * @author tangxlf
 * @date   2015-10-28
 */
public class MarkUtil {
    private static final String JOIN_MARK = "-oms-";//标志连接符    
   /**
    * 获取应用实例标志
    * @param  String   serverIp          服务器IP  
    * @param  int    appIntantId         应用实例ID
    * @return String   应用实例标志  
    */
	public static String createAppConfigMark(String serverIp,int appIntantId){
		return appIntantId+JOIN_MARK+serverIp;
	}
}
