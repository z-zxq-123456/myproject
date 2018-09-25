package com.dcits.galaxy.logcover.config;


import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.logcover.config.ConfigConstants;


/**
 * 日志配置信息* 
 * @author tangxlf
 * @date 2016-10-08 
 */
public class EcmLogconfInfo extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    private String logPlatMode = ConfigConstants.PLAT_MODE_QUEDB;//日志平台模式 0071001：队列+数据库  0071002：kafka+数据库 0071003：kafka+hbase
    
    private String logCollLevel = ConfigConstants.COLL_LEV_NOCOLL;//日志收集级别 0072001：不收集  0072002：收集调用链  0072003：收集调用链+业务日志

    private String logLevel;//日志级别0073001:debug 0073002:info 0073003:warn 0073001:error 0073001:fatal
    
    private String logLevelName = "error";//日志级别的名字  debug info warn error:fatal
    
    private String isScan = ConfigConstants.IS_SCAN_Y;//是否扫描 默认 是   0009001：是 0009002：否
    
    private String isScanName = "true";
    
    private String scanPeriod = "30";//扫描周期 单位秒  默认30秒
    
    private Boolean isCollChain = new Boolean(false);//日志平台采集调用链true  日志平台不采集调用链false
    
    private Boolean isCollLog = new Boolean(false);//日志平台采集业务日志true  日志平台不采集业务日志false
    
    private Integer expiredTime = 60*1000;//超期时间 默认一分钟
    
    /**
	 * 日志输出类全路径名
	 *  开发模式单写：ch.qos.logback.core.rolling.RollingFileAppender
	 *  应用模式单写：com.dcits.galaxy.logcoll.LogPlatWriteAppender
	 *  应用模式双写：com.dcits.galaxy.logcoll.logback.DoubleWriteAppender 
	 */
    private String appenderClass;
    
    private Integer queueLen = 10000;//日志队列容量    
 
   	
   	private Integer queueBatchSize = 300;//入库批量  每次往数据库中存储的数量
   	
    private Integer logconfId;
    
    private Integer midwareId;
    
    private Integer tceinStartLen = 200;// 调用链入库启动容量
    
    private Integer tceinStopLen = 100;// 调用链入库停止容量
    
	private Integer cirStartLen = 1400;// 调用环入库启动容量
	
	private Integer cirStopLen = 700;// 调用环入库停止容量
	
	private Integer annotStartLen = 600;// 注解入库启动容量
	
	private Integer annotStopLen = 300;// 注解入库批量 
    
   	public Integer getLogconfId() {
		return logconfId;
	}

	public void setLogconfId(Integer logconfId) {
		this.logconfId = logconfId;
	}

	public Integer getMidwareId() {
		return midwareId;
	}

	public void setMidwareId(Integer midwareId) {
		this.midwareId = midwareId;
	}
	public String getLogPlatMode() {
		return logPlatMode;
	}

	public void setLogPlatMode(String logPlatMode) {
		this.logPlatMode = logPlatMode;
	}

	public String getLogCollLevel() {
		return logCollLevel;
	}

	public void setLogCollLevel(String logCollLevel) {
		this.logCollLevel = logCollLevel;
	}

	public String getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(String logLevel) {
		this.logLevel = logLevel;
	}

	public String getIsScan() {
		return isScan;
	}

	public void setIsScan(String isScan) {
		this.isScan = isScan;
	}

	public String getScanPeriod() {
		return scanPeriod;
	}

	public void setScanPeriod(String scanPeriod) {
		this.scanPeriod = scanPeriod;
	}

	public String getAppenderClass() {
		return appenderClass;
	}

	public void setAppenderClass(String appenderClass) {
		this.appenderClass = appenderClass;
	}

	public Integer getQueueLen() {
		return queueLen;
	}

	public void setQueueLen(Integer queueLen) {
		this.queueLen = queueLen;
	}

	public String getIsScanName() {
		return isScanName;
	}

	public void setIsScanName(String isScanName) {
		this.isScanName = isScanName;
	}

	public String getLogLevelName() {
		return logLevelName;
	}

	public void setLogLevelName(String logLevelName) {
		this.logLevelName = logLevelName;
	}	

	public Integer getQueueBatchSize() {
		return queueBatchSize;
	}

	public void setQueueBatchSize(Integer queueBatchSize) {
		this.queueBatchSize = queueBatchSize;
	}

	public Integer getTceinStartLen() {
		return tceinStartLen;
	}

	public void setTceinStartLen(Integer tceinStartLen) {
		this.tceinStartLen = tceinStartLen;
	}

	public Integer getCirStartLen() {
		return cirStartLen;
	}

	public void setCirStartLen(Integer cirStartLen) {
		this.cirStartLen = cirStartLen;
	}

	public Integer getAnnotStartLen() {
		return annotStartLen;
	}

	public void setAnnotStartLen(Integer annotStartLen) {
		this.annotStartLen = annotStartLen;
	}
	
	public Boolean getIsCollChain() {
		return isCollChain;
	}

	public void setIsCollChain(Boolean isCollChain) {
		this.isCollChain = isCollChain;
	}

	public Boolean getIsCollLog() {
		return isCollLog;
	}

	public void setIsCollLog(Boolean isCollLog) {
		this.isCollLog = isCollLog;
	}

	
	public Integer getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(Integer expiredTime) {
		this.expiredTime = expiredTime;
	}

	public Integer getTceinStopLen() {
		return tceinStopLen;
	}

	public void setTceinStopLen(Integer tceinStopLen) {
		this.tceinStopLen = tceinStopLen;
	}

	public Integer getCirStopLen() {
		return cirStopLen;
	}

	public void setCirStopLen(Integer cirStopLen) {
		this.cirStopLen = cirStopLen;
	}

	public Integer getAnnotStopLen() {
		return annotStopLen;
	}

	public void setAnnotStopLen(Integer annotStopLen) {
		this.annotStopLen = annotStopLen;
	}

	@Override
	public String toString() {
		return "EcmLogconfInfo [logPlatMode=" + logPlatMode + ", logCollLevel="
				+ logCollLevel + ", logLevel=" + logLevel + ", logLevelName="
				+ logLevelName + ", isScan=" + isScan + ", isScanName="
				+ isScanName + ", scanPeriod=" + scanPeriod + ", isCollChain="
				+ isCollChain + ", isCollLog=" + isCollLog + ", expiredTime="
				+ expiredTime + ", appenderClass=" + appenderClass
				+ ", queueLen=" + queueLen + ", queueBatchSize="
				+ queueBatchSize + ", logconfId=" + logconfId + ", midwareId="
				+ midwareId + ", tceinStartLen=" + tceinStartLen
				+ ", tceinStopLen=" + tceinStopLen + ", cirStartLen="
				+ cirStartLen + ", cirStopLen=" + cirStopLen
				+ ", annotStartLen=" + annotStartLen + ", annotStopLen="
				+ annotStopLen + "]";
	}

	
	
	
}