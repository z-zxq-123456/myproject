package com.dcits.ensemble.om.oms.shellcmd;

/**
 * shell命令接口* 
 * @author tangxlf
 * @date 2015-09-22 
 */
public interface ICmd {    
	
     public  final static String App_ISOK_SUBCODE = "001";//应用是否正常子码 ，需要和操作系统对应码组成参数编码	
	
	 public  final static String App_SCP_SUBCODE = "002";//文件传输子码 ，需要和操作系统对应码组成参数编码
	
	 public  final static String App_TARX_SUBCODE = "003";//tar 文件解压缩
	
	 public  final static String App_MKDIR_SUBCODE = "004";//创建目录
	
	 public  final static String App_RENAME_SUBCODE = "005";//重命名	
	
	 public  final static String App_RMR_SUBCODE = "006";//删除目录
	
	 public  final static String App_CD_SUBCODE = "007";//CD到指定目录	
	
	 public  final static String App_LS_SUBCODE = "008";//列出文件和目录
	
	 public  final static String App_RM_SUBCODE = "009";//删除文件
	
	 public  final static String App_CPA_SUBCODE = "010";//拷贝目录
	 
	 public  final static String App_CAT_SUBCODE = "011";//查询文件
	 
	 public  final static String App_SED_SUBCODE = "012";//流编辑器
	 
	 public  final static String App_PWD_SUBCODE = "013";//查看当前工作目录
	
	 public  final static String App_TARC_SUBCODE = "014";//tar 文件压缩
	 
	 public  final static String App_CHMODX_SUBCODE = "015";//chmod +x 文件授执行权限
	 
	 public  final static String App_TAIL_SUBCODE = "016";//查询日志
	 
	 public  final static String ECHO_SOMETING = "017";//显示文字
	 
	 public  final static String CPU_MESSAGE = "018";//使命令延时执行
	 
	 public  final static String SLEEP_COMMAND = "019";//查询日志
	 
	 public  final static String IO_MESSAGE = "020";//获取磁盘IO使用情况
	 
	 public  final static String TIME_COMMAND = "021";//测量命令执行时间
	 
	 public  final static String MEMORY_MESSAGE  = "022";//获取内存使用情况
	 
	 public  final static String NET_MESSAGE = "023";//获取宽带使用情况
	 
     public  final static String OUTPUT_REDIRECT_FILE  = "024";//重新定向输出到文件
	 
	 public  final static String OUTPUT_ADDDIRECT_FILE = "025";//追加定向输出到文件
	 
	 public  final static String ECHO_RUOK = "026";//ZK的健康检查
	 
	/**
	 * 生成netstat-shell命令	
	 * @param String    cmdArgs  命令参数
	 * @return String   执行命令
	 */
	public String netstatCmd(String cmdArgs);
	/**
	 * 生成scp-shell命令	
	 * @param String    cmdArgs  命令参数
	 * @return String   执行命令
	 */
	public String scpCmd(String cmdArgs);
	/**
	 * 生成tar-shell命令	
	 * @param String    cmdArgs  命令参数
	 * @return String   执行命令
	 */
	public String tarCmd(String cmdArgs);
	/**
	 * 生成mkdir-shell命令	
	 * @param String    cmdArgs  命令参数
	 * @return String   执行命令
	 */
	public String mkdirCmd(String cmdArgs);
	/**
	 * 生成mv  -shell命令	
	 * @param String    cmdArgs  命令参数
	 * @return String   执行命令
	 */
	public String mvCmd(String cmdArgs);	
	/**
	 * 生成rm -r -shell命令	
	 * @param String    cmdArgs  命令参数
	 * @return String   执行命令
	 */
	public String rmrCmd(String cmdArgs);
	/**
	 * 生成cd-shell命令	
	 * @param String    cmdArgs  命令参数
	 * @return String   执行命令
	 */
	public String cdCmd(String cmdArgs);
	/**
	 * 生成ls  -shell命令	
	 * @param String    cmdArgs  命令参数
	 * @return String   执行命令
	 */
	public String lsCmd(String cmdArgs);
	
	/**
	 * 生成rm  -shell命令	
	 * @param String    cmdArgs  命令参数
	 * @return String   执行命令
	 */
	public String rmCmd(String cmdArgs);	
	/**
	 * 生成cp  -shell命令	
	 * @param String    cmdArgs  命令参数
	 * @return String   执行命令
	 */
	public String cpCmd(String cmdArgs);
	/**
	 * 生成cat  -shell命令	
	 * @param String    cmdArgs  命令参数
	 * @return String   执行命令
	 */
	public String catCmd(String cmdArgs);
	/**
	 * 生成sed  -shell命令	
	 * @param String    cmdArgs   命令参数
	 * @param String    fileName  文件名
	 * @return String   执行命令
	 */
	public String sedCmd(String cmdArgs,String fileName);
	/**
	 * 生成pwd  -shell命令	
	 * @param String    cmdArgs  命令参数
	 * @return String   执行命令
	 */
	public String pwdCmd(String cmdArgs);
	/**
	 * 生成tar压缩  -shell命令	
	 * @param String    cmdArgs  命令参数
	 * @return String   执行命令
	 */
	public String tarcCmd(String cmdArgs);
	
	/**
	 * 生成文件授执行权限  -shell命令	
	 * @param String    cmdArgs  命令参数
	 * @return String   执行命令
	 */
	public String chmodxCmd(String cmdArgs);
	
	/**
	 * 生成从尾部查阅文本  -shell命令	
	 * @param String    cmdArgs  命令参数
	 * @return String   执行命令
	 */
	public String tailCmd(String cmdArgs);
	/**
	 * 生成显示文本  -shell命令	
	 * @param String    cmdArgs  命令参数
	 * @return String   执行命令
	 */
	public String echoCmd(String cmdArgs);
	/**
	 * 查阅cpu  -shell命令	
	 * @param String    cmdArgs  命令参数
	 * @return String   执行命令
	 */
	public String cpuCmd(String cmdArgs);
	
	/**
	 * sleep命令  -shell命令	
	 * @param String    cmdArgs  命令参数
	 * @return String   执行命令
	 */
	public String sleepCmd(String cmdArgs);
	/**
	 * 生成查阅磁盘io -shell命令	
	 * @param String    cmdArgs  命令参数
	 * @return String   执行命令
	 */
	public String ioCmd(String cmdArgs);
	/**
	 * time -shell命令	
	 * @param String    cmdArgs  命令参数
	 * @return String   执行命令
	 */
	public String timeCmd(String cmdArgs);
	/**
	 * 查阅内存 -shell命令	
	 * @param String    cmdArgs  命令参数
	 * @return String   执行命令
	 */
	public String memoryCmd(String cmdArgs);
	/**
	 * 查阅网络宽带 -shell命令	
	 * @param String    cmdArgs  命令参数
	 * @return String   执行命令
	 */
	public String netCmd(String cmdArgs);
	/**
	 * 重新定向输出到文件 -shell命令	
	 * @param String    cmdArgs  文件名	 
	 * @return String   执行命令
	 */
	public String outputRedirectFileCmd(String cmdArgs);
	/**
	 * 追加定向输出到文件 -shell命令	
	 * @param String    cmdArgs  文件名	 
	 * @return String   执行命令
	 */
	public String outputAddDirectFileCmd(String cmdArgs);
	/**
	 * 生成echo-ruok|nc -shell命令	
	 * @param String    cmdArgs  文件名	 
	 * @return String   执行命令
	 */
	public String echoruokCmd(String cmdArgs);
	
}
