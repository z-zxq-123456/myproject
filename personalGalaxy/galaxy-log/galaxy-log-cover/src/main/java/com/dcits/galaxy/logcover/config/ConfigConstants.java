package com.dcits.galaxy.logcover.config;

import java.util.HashMap;
import java.util.Map;

/**
 * 日志配置常量* 
 * @author tangxlf
 * @date 2016-10-08 
 */
public class ConfigConstants {
	
	public static final String PLAT_MODE_QUEDB = "0071001";//日志平台模式-- 0071001：队列+数据库

	public static final String PLAT_MODE_KAFKADB = "0071002";//日志平台模式--0071002：kafka+数据库
	
	public static final String PLAT_MODE_KAFKAHBASE = "0071003";//日志平台模式--0071003：kafka+hbase
	
	public static final String COLL_LEV_NOCOLL = "0072001";//日志收集级别 0072001：不收集
	
	public static final String COLL_LEV_CHAIN = "0072002";//日志收集级别 0072002：收集调用链

	public static final String COLL_LEV_CHAIN_AND_LOG = "0072003";//日志收集级别 0072003：收集调用链+业务日志
	
	public static final String IS_SCAN_Y = "0009001";//是否扫描  0009001：是
	
	public static final String IS_SCAN_N = "0009002";//是否扫描  0009002：否
	
	public static final String APPENDER_CLASS_ORIGNAL = "ch.qos.logback.core.rolling.RollingFileAppender";//日志输出类全路径名 --原生
	
	public static final String GALAXY_ZK_PROP = "galaxy.registry.address";//galaxy.properties中zk属性名
	
	public static final String CIR_TYPE_RPC = "0070001";//调用方式 0070001:rpc调用
	
	public static final String CIR_TYPE_INJVM = "0070002";//调用方式 0070002:injvm调用
	
	public static final String ANNOT_TYPE_NORMAL = "0074001";//标注类型 0074001:普通标注
	
	public static final String ANNOT_TYPE_ERROR = "0074002";//标注类型 0074002:异常标注
	
    public static final String INVOKE_STATUS_SUCCEED = "0015001";//执行状态 0015001:成功
	
	public static final String INVOKE_STATUS_FAIL = "0015002";//执行状态 0015002:失败
	
    public static final String ANNOT_LEVEL_TRACE = "0073001";//日志级别 0073001:trace
	
	public static final String ANNOT_LEVEL_DEBUG = "0073002";//日志级别 0073002:debug
	
    public static final String ANNOT_LEVEL_INFO = "0073003";//日志级别 0073003:info
	
	public static final String ANNOT_LEVEL_WARN = "0073004";//日志级别 0073004:warn
	
    public static final String ANNOT_LEVEL_ERROR = "0073005";//日志级别 0073005:error
    
    public final static Map<String,String> EXCLUDE_SERVICE_MAP = new HashMap<String,String>();//要排除不记录的dubbo服务
    
    static{
    	EXCLUDE_SERVICE_MAP.put("com.dcits.galaxy.logcover.api.ILogCollection", "");
    }
	
}
