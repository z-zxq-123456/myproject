package com.dcits.ensemble.om.oms.module.log;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

import java.util.Properties;


public class EcmLogconfInfo extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

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

	public String getOutPrtnMode() {
		return outPrtnMode;
	}

	public void setOutPrtnMode(String outPrtnMode) {
		this.outPrtnMode = outPrtnMode;
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

	public Integer getQueueLen() {
		return queueLen;
	}

	public void setQueueLen(Integer queueLen) {
		this.queueLen = queueLen;
	}

	public void setZkMidwareName(String zkMidwareName) {
		this.zkMidwareName = zkMidwareName;
	}

	public String getZkMidwareName() {
		return zkMidwareName;
	}

	public String getLogPlatModeName() {
		return logPlatModeName;
	}

	public void setLogPlatModeName(String logPlatModeName) {
		this.logPlatModeName = logPlatModeName;
	}

	public String getOutPrtnModeName() {
		return outPrtnModeName;
	}

	public void setOutPrtnModeName(String outPrtnModeName) {
		this.outPrtnModeName = outPrtnModeName;
	}

	public String getLogLevelName() {
		return logLevelName;
	}

	public void setLogLevelName(String logLevelName) {
		this.logLevelName = logLevelName;
	}

	public String getIsScanName() { return isScanName;}

	public void setIsScanName(String isScanName) {this.isScanName = isScanName;}

	public Integer getQueueBatchSize() {
		return queueBatchSize;
	}

	public void setQueueBatchSize(Integer queueBatchSize) {
		this.queueBatchSize = queueBatchSize;
	}

	public Integer getQueueStartLen() {
		return queueStartLen;
	}

	public void setQueueStartLen(Integer queueStartLen) {
		this.queueStartLen = queueStartLen;
	}

	public Integer getQueueStopLen() {
		return queueStopLen;
	}

	public void setQueueStopLen(Integer queueStopLen) {
		this.queueStopLen = queueStopLen;
	}

	public String getAppenderClass() {return appenderClass;}

	public void setAppenderClass(String appenderClass) {this.appenderClass = appenderClass;}

	public Integer getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(Integer expiredTime) {
		this.expiredTime = expiredTime;
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

	public Integer getKkMidwareId() {
		return kkMidwareId;
	}

	public void setKkMidwareId(Integer kkMidwareId) {
		this.kkMidwareId = kkMidwareId;
	}

	public String getKkMidwareIdName() {
		return kkMidwareIdName;
	}

	public void setKkMidwareIdName(String kkMidwareIdName) {
		this.kkMidwareIdName = kkMidwareIdName;
	}

	public Properties getKfkProdProp() {
		return kfkProdProp;
	}

	public void setKfkProdProp(Properties kfkProdProp) {
		this.kfkProdProp = kfkProdProp;
	}

	public Properties getKfkConsumerProp() {
		return kfkConsumerProp;
	}

	public void setKfkConsumerProp(Properties kfkConsumerProp) {
		this.kfkConsumerProp = kfkConsumerProp;
	}

	/**
     * This field is ECM_LOGCONF_INFO.LOG_CONF_ID
     */
    @TablePk(index = 1)
    private Integer logconfId;

    /**
     * This field is ECM_LOGCONF_INFO.MIDWARE_ID
     */
    private Integer midwareId;

	private String  zkMidwareName;
    /**
     * This field is ECM_LOGCONF_INFO.LOG_PLAT_MODE
     */
    private String logPlatMode;

	private String logPlatModeName;
    /**
     * This field is ECM_LOGCONF_INFO.OUT_PRTN_MODE
     */
    private String outPrtnMode;

	private String outPrtnModeName;
    /**
     * This field is ECM_LOGCONF_INFO.LOG_LEVEL
     */
    private String logLevel;

	private String logLevelName;
	/**
	 * This field is ECM_LOGCONF_INFO.IS_SCAN
	 */
	private String isScan;

	private String isScanName;
	/**
	 * This field is ECM_LOGCONF_INFO.SCAN_PERIOD
	 */
	private String scanPeriod;
	/**
	 * This field is ECM_LOGCONF_INFO.QUEUE_LEN
	 */
	private Integer queueLen;
	/**
	 * This field is ECM_LOGCONF_INFO.QUEUE_STARTLEN
	 */
	private Integer queueStartLen;
	/**
	 * This field is ECM_LOGCONF_INFO.QUEUE_STOPLEN
	 */
	private Integer queueStopLen;
	/**
	 * This field is ECM_LOGCONF_INFO.QUEUE_BATCHSIZE
	 */
	private Integer queueBatchSize;
	/**
	 * 方便注册中心上传outPrtnMode字段的appenderClass类名，数据库中没有改字段
	 */
	private String appenderClass;
	/**
	 * This field is ECM_LOGCONF_INFO.EXPIREDTIME  最大等待时间
	 */
	private Integer expiredTime;
	/**
	 * 方便注册中心上传outPrtnMode字段的isCollChain，数据库中没有改字段
	 */
	private Boolean isCollChain ;//日志平台采集调用链true  日志平台不采集调用链false
	/**
	 * 方便注册中心上传outPrtnMode字段的isCollLog，数据库中没有改字段
	 */
	private Boolean isCollLog ;//日志平台采集业务日志true  日志平台不采集业务日志false

	/**
	 * This field is ECM_LOGCONF_INFO.KK_MIDWARE_ID  最大等待时间
	 */
	private Integer kkMidwareId;

	private String kkMidwareIdName;

	private Properties kfkProdProp  = new Properties(); //kafka生产者Properties

	private Properties kfkConsumerProp  = new Properties(); //kafka消费者Properties

}