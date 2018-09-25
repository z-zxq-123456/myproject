package com.dcits.galaxy.base;

/**
 * Connector的常量
 * 
 * @author xuecy
 * 
 */
public class GalaxyConstants {

	// 报文类型
	public static final String JSON_UPPER_UNDERSCORE = "0";
	public static final String JSON_LOWER_CAMEL = "1";

	// 三方报文关键字
	public static final String SYS_HEAD = "sysHead";
	public static final String APP_HEAD = "appHead";
	public static final String LOCAL_HEAD = "localHead";
	public static final String BODY = "body";

	public static final String SERVICE_CODE = "serviceCode";
	public static final String MESSAGE_TYPE = "messageType";
	public static final String MESSAGE_CODE = "messageCode";
	public static final String USER_LANG = "userLang";
	public static final String TRAN_DATE = "tranDate";
	public static final String BRANCH_ID = "branchId";
	public static final String USER_ID = "userId";
	public static final String AUTH_FLAG = "authFlag";
	public static final String AUTH_USER_ID = "authUserId";
	public static final String GRANT_FLAG = "grantFlag";
	public static final String SOURCE_TYPE = "sourceType";
	public static final String PROGRAM_ID = "programId";
	public static final String SERVER_ID = "serverId";
	public static final String MODULE_ID = "moduleId";
	public static final String PLATFORM_ID = "platformId";
	public static final String REFERENCE = "reference";
	public static final String SEQ_NO = "seqNo";

	// 返回报文的字段名称
	public static final String TRAN_TIMESTAMP = "tranTimestamp";
	public static final String RET_STATUS = "retStatus";
	public static final String RETS = "ret";
	public static final String RET_CODE = "retCode";
	public static final String RET_MSG = "retMsg";

	/**
	 * 执行成功时，默认返回的RET_CODE的值
	 */
	public static final String CODE_SUCCESS = "000000";
	/**
	 * 执行失败时，默认返回的RET_CODE的值
	 */
	public static final String CODE_FAILED = "999999";
	/**
	 * RPC服务调用执行超时，默认返回的RET_CODE的值
	 */
	public static final String CODE_TIMEOUT = "888888";

	/**
	 * 执行成功时，默认返回的RET_MSG的值
	 */
	public static final String MESSAGE_SUCCESS = "SUCCESS";

	// RET_STATUS的取值范围
	/**
	 * 执行成功时，返回报文中的RET_STATUS的值
	 */
	public static final String STATUS_SUCCESS = "S";
	/**
	 * 执行失败时，返回报文中的RET_STATUS的值
	 */
	public static final String STATUS_FAILED = "F";
	/**
	 * 需要授权时，返回报文中的RET_STATUS的值
	 */
	public static final String STATUS_AUTH = "O";
	/**
	 * 需要确认时，返回报文中的RET_STATUS的值
	 */
	public static final String STATUS_CONF = "C";
	/**
	 * 需要授权&确认时，返回报文中的RET_STATUS的值
	 */
	public static final String STATUS_AUTH_CONF = "B";

	/**
	 * @fields IN 请求原始数据节点
	 */
	public static final String IN = "in";

	/**
	 * @fields ETC 数据交换节点
	 */
	public static final String ETC = "etc";

	/**
	 * @fields 报文格式
	 */
	public static final String MSG_FORMAT = "msgFormat";

}
