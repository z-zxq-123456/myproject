package com.dcits.ensemble.om.oms.constants;


/**
 * 系统配置常量*
 * @author tangxlf
 * @date   2015-8-11
 */
public class SysConfigConstants {

	public static final String STATUS_OK  = "0001001";//状态--有效
	public static final String STATUS_FAIL  = "0001002";//状态--无效

	public static final String MENU_PAGETYPE_OPEN  = "0002001";//页面类型--打开URL对应的页面
	public static final String MENU_PAGETYPE_TEMPLETE  = "0002002";//页面类型--打开模板对应的页面
	public static final String MENU_PAGETYPE_CLOSE  = "0002003";//页面类型--对应的菜单节点被点击时，不能打开页面

	public static final String HEALTH_STATUS_GREEN  = "0004001";//健康状态--健康
	public static final String HEALTH_STATUS_YELLOW  = "0004002";//健康状态--不健康
	public static final String HEALTH_STATUS_RED  = "0004003";//健康状态--停止

	public static final String INTANT_TYPE_SERVER  = "0005001";//实例类型--服务器
	public static final String INTANT_TYPE_APP  = "0005002";//实例类型--应用
	public static final String INTANT_TYPE_REDIS  = "0005003";//实例类型--Redis
	public static final String INTANT_TYPE_ZOOKEEPER = "0005004";//实例类型--Zookeeper
	public static final String INTANT_TYPE_DATABASE  = "0005005";//实例类型--Database
	public static final String INTANT_TYPE_KAFKA  = "0005006";//实例类型--KAFKA

	public static final String REDIS_MIDWARE = "0006001";//redis中间件
	public static final String ZOOKEEPER_MIDWARE = "0006002";//zk中间件
	public static final String DB_MIDWARE = "0006003";//DB中间件
	public static final String KAFKA_MIDWARE = "0006004";//Kafka中间件

	public static final String REDIS_MASTER ="0007001";//redis主master
	public static final String REDIS_SLAVE = "0007002";//redis备slave
	public static final String REDIS_SENTINEL="0007003";//redis哨兵sentinel

	public static final String DB_MYSQL ="0008001";//数据库类型--mysql
	public static final String DB_ORACLE ="0008002";//数据库类型--oracle

	public static final String IS_DEFAULT ="0009001";//设为默认
	public static final String NOT_DEFAULT ="0009002";//不设为默认


	public static final String APPCONF_UPDATE_INTANT  = "0003001";//应用配置修改方式--应用单实例修改
	public static final String APPCONF_UPDATE_APP  = "0003002";//应用配置修改方式--应用修改
	public static final String APPCONF_UPDATE_TOTAL  = "0003003";//应用配置修改方式--全量修改

	public static final String SYS_SERVER_ID = "0100002";//系统所在服务器
	public static final String SYS_BIN_WORK = "0100003";//应用命令目录
	public static final String SYS_LIB_WORK = "0100004";//应用LIB目录
	public static final String SYS_APP_ADD_TEMP_WORK = "0100005";//应用增量临时目录
	public static final String SYS_CONF_WORK = "0100006";//应用配置文件保存目录
	public static final String APP_START_DIFF_DEPLOY_CONFIG= "0100007";//应用差异配置开始标志
	public static final String APP_END_DIFF_DEPLOY_CONFIG = "0100008";//应用差异配置结束标志
	public static final String APP_BACK_SUFF_NAME = "0100009";//应用备份后缀名
	public static final String GALAXY_CONNECTOR_NAME = "0100010";//galaxy-connector访问名
	public static final String GALAXY_APP_LOG_DIRCTION="0100011";//应用日志目录
	public static final String GALAXY_APP_LOG_NAME="0100012";//启动日志文件名
	public static final String SYS_ZK_DATE = "0100015";//zk数据保存目录
	public static final String SYS_ZK_LOGS = "0100016";//zk日志保存目录
	public static final String SYS_ZK_BIN = "0100017";//zk命令目录
	public static final String SYS_ZK_CONF  = "0100018";//zk配置文件
	public static final String MIDWARE_CONFIG_DIR  = "0100020";//zk配置文件

	public static final String APPCONF_EARLY_INTANT = "0010001";//先升级配置方式--应用单实例修改
	public static final String APPCONF_EARLY_APP  = "0010002";//先升级配置方式--先升级全修改
	public static final String APPCONF_LATE_INTANT = "0011001";//后升级配置方式--应用单实例修改
	public static final String APPCONF_LATE_APP  = "0011002";//后升级配置方式--后升级全修改

	public static final String APPCONF_EARLYRULE_ALL = "0012001";//先升级规则类型--全部
	public static final String APPCONF_EARLYRULE_BACK  = "0012002";//先升级规则类型--上一升级规则

	public static final String APPCONF_LATERULE_ALL = "0013001";//后升级规则类型--全部
	public static final String APPCONF_LATERULE_EARLY  = "0013002";//后升级规则类型	--先升级规则

	public static final String ROUTER_COL_TYPE_STR = "0014001";//路由字段类型--字符
	public static final String ROUTER_COL_TYPE_NUM  = "0014002";//路由字段类型--数字

	public static final String TRACE_STATUS_S = "0015001";//调用成功
	public static final String TRACE_STATUS_F = "0015002";//调用失败

	public static final String APP_INTANTSTATUS_START = "0020001";//应用实例状态--启动
	public static final String APP_INTANTSTATUS_STOP = "0020002";//应用实例状态--停止
	public static final String APP_INTANTSTATUS_NODEPLOY = "0020003";//应用实例状态--未部署
	public static final String APP_INTANTSTATUS_DEPLOYED = "0020004";//应用实例状态--已部署
	public static final String APP_INTANTSTATUS_RESTART = "0020005";//应用实例状态--重启
	public static final String APP_INTANTSTATUS_CLEAN = "0020006";//应用实例状态--清理
	public static final String BUSINESS_APP_TYPE="0021001";//业务应用
	public static final String WEB_APP_TYPE="0021002";//WEB应用
	public static final String APP_ASSEMTYPE_TOTAL = "0022001";//版本类型--全量版本
	public static final String APP_ASSEMTYPE_ADD = "0022002";//版本类型--增量版本

	public static final String REMAIN_APP_CONFIG = "0023001";//配置文件安装类型--保留配置文件
	public static final String REMAIN_APP_COVER = "0023002"; //配置文件安装类型--覆盖配置文件

	public static final String OPERATOR_EQUAL = "0024001";// 操作符--等于

	public static final String ROUTER_ARGS_MAP = "0025001";//路由参数类型--MAP
	public static final String ROUTER_ARGS_JAVABEAN = "0025002";//路由参数类型--JAVABEAN
	public static final String ROUTER_ARGS_BASETYPE = "0025003";//路由参数类型--基本类型


	public static final String AUTO_MAKING = "0026001";//服务路由规则类型--动态生成
	public static final String USER_DEFINED = "0026002";//服务路由规则类型--自定义

	public static final String APP_UPGSTATUS_PROGRESS = "0027001";//应用升级状态--进行中
	public static final String APP_UPGSTATUS_SUCCEED = "0027002";//应用升级状态--正常结束
	public static final String APP_UPGSTATUS_FAIL = "0027003";//应用升级状态--回退结束

	public static final String APP_VALRULE_EARLY = "0028001";//应用升级验证规则类型--先升级验证规则
	public static final String APP_VALRULE_LATE = "0028002";//应用升级验证规则类型--后升级验证规则


	public static final String LINUX_OS = "0030001";//Linux操作系统
	public static final String UNIX_OS = "0030002";//unix操作系统

	public static final String PARAMETER_FILE = "0050";//参数文件
	public static final String GALAXY_PROTOCOL = "0050001";//参数文件--galaxy.protocol
	public static final String GALAXY_PROTOCOL_PORT = "0051001";//参数文件--galaxy.protocol.port
	public static final String GALAXY_REGISTRY_ADDRESS = "0051002";//参数文件--galaxy.registry.address
	public static final String GALAXY_PROTOCOL_NAME = "0051003";//参数文件--galaxy.protocol.name
	public static final String JETTY_PORT = "0052001";//参数文件--jetty.port
	public static final String JETTY_URLPATTERN = "0052002";//参数文件--jetty.urlPattern
	public static final String CACHER_FILE_NAME ="0054";//配置文件名称
	public static final String REDIS_SERVERS_NAME ="0054001";
	public static final String REDIS_SENTINEL_SERVERS="0054002";
	public static final String DB_FILE_NAME ="0055";//DB配置文件名称--galaxy.db.properties
	public static final String DB_FILE_URL ="0055001";//DB配置文件jdbcUrl--db{0}.jdbcUrl
	public static final String DB_FILE_USERNAME="0055002";//DB配置文件username--db{0}.username
	public static final String DB_FILE_PWD="0055003";//DB配置文件password--db{0}.password
	public static final String APP_SER_TYPE_COMMON="0060001";//应用服务类型--普通服务
	public static final String APP_SER_TYPE_CHECKED="0060002";//应用服务类型--需测试服务

	public static final String DUBBO_ROOT="0061001";//dubbo目录名称--dubbo根目录
	public static final String DUBBO_PROVIDERS="0061002";//dubbo目录名称--提供者目录
	public static final String DUBBO_CONSUMERS="0061003";//dubbo目录名称--消费者目录
	public static final String DUBBO_ROUTERS="0061004";//dubbo目录名称--路由目录

	public static final String DUBBO_TYPE_PROVIDERS="0062001";//dubbo类型--providers
	public static final String DUBBO_TYPE_CONSUMERS="0062002";//dubbo类型--consumers

	public static final String CIR_TYPE_RPC="0070001";//调用方式：RPC调用
	public static final String CIR_TYPE_INJVM="0070002";//调用方式： INJVM调用

	public static final String CPU_ALARM_LEVER = "0080001";//CPU告警阀值
	public static final String MEMORY_ALARM_LEVER = "0080002";//MEMORY告警阀值
	public static final String IO_ALARM_LEVER = "0080003";//IO告警阀值
	public static final String NET_ALARM_LEVER = "0080004";//NET告警阀值

	public static final String ALARM_SIZE = "0081001";//告警次数



	public static final String APPMONI_DIME_TYPE="0090";//应用监控维度--无维度
	public static final String APPMONI_DIME_NODIME="0090001";//应用监控维度--无维度

	public static final String SERVICECODE_MAPPING_CLASSNAME="0099";//服务码与类名映射

	public static final String APP_SAVE_URL  = "0100001";//系统配置--应用版本保存路径
	public static final String MIDDLEWARE_VER_SAVE_URL = "0100013";//系统配置--中间件版本保存路径
	public static final String DEV_MODE = "0100014";//系统配置--开发模式
	public static final String REIDS_CLIENT_TIMEOUT = "0100023";//系统配置--开发模式
	public static final String APPMONI_COL_LEN = "0100024";//系统配置--监控指标页面字段长度

	public static final String QUARTZ_START_TIME = "0100025"; //开始执行调度的时间

	public static final String FULL_GALAXY_REFRESH_TIME = "0100027"; //全量视图刷新时间(秒)

	public static final String QUARTZ_DELETE_LOGINFO_TIME = "0100028"; //开始执行调度删除日志的的时间

	public static final String LOGPLAT_LEVEL = "0100029"; //日志平台日志级别

	public static final String HANDLER_SERVICE_CLASSNAME = "0100030"; //hanler类全名

	public static final String HANDLER_SERVICE_MTDNAME = "0100031"; //hanler类方法名

	public static final String progressName =  "progressName";//文件进度存储SESSION名称
	public static final String fileTotalSize = "fileTotalSize";//文件总大小SESSION名称
	public static final int SHELL_STATUS_SUCCEED = 0;//SHELL执行后返回成功状态
	public static final int SHELL_STATUS_FAIL = 1;//SHELL执行后返回失败状态


	public static final String DEPLOY_PROGRESS= "deployProgress";//应用实例部署进度
	public static final String DEPLOY_MESSAGE= "deployMessage";//应用实例部署消息
	public static final String DEPLOY_STAGE= "deployStage";//应用部署阶段
	public static final String DEPLOY_TIME = "deployTime";//应用部署时间
	//public static final  int DEPLOY_STAGE_FILETRANS = 1;//应用部署阶段--文件传输
	//public static final  int DEPLOY_STAGE_FILEDEP = 2;//应用部署阶段--文件传输
	//public static final  int DEPLOY_STAGE_FINSH = 3;//应用部署阶段--文件传输

	public static final String PROGRESS_NUM= "progressNum";//进度百分比数
	public static final String PROGRESS_MESSAGE= "progressMessage";//进度执行消息

	public static final String SVGVIEW_DATA_NAME= "dataName";//svg图详细信息中的字段名
	public static final String SVGVIEW_DATA_VALUE= "dataValue";//svg图详细信息中的字段值


	public static final int APP_START_OR_STOP_STAGE_START = 1;//应用实例启动/停止--开始
	public static final int APP_START_OR_STOP_STAGE_END = 2;//应用实例启动/停止--结束

	public static final String SHELL_LINK_SIGN = "&&"; //多个shell命令一次执行的连接符号
	public static final String DIR_LINK_SIGN = "_"; //文件名或者目录名连接符号

}
