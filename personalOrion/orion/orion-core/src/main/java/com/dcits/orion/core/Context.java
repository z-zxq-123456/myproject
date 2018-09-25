/**
 * <p>Title: Context.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 *
 * @author Tim
 * @update 2014年11月13日 下午4:36:49
 * @version V1.0
 */
package com.dcits.orion.core;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcits.galaxy.base.data.IAppHead;
import com.dcits.galaxy.base.data.ISysHead;
import com.dcits.galaxy.base.util.DateUtils;
import com.dcits.galaxy.core.access.ThreadLocalManager;
import com.dcits.galaxy.core.spring.SpringApplicationContext;

/**
 * @author Tim
 * @version V1.0
 * @description 基于统一缓存实现的交易全局上下文
 * @update 2014年11月13日 下午4:36:49
 * @modify 2016年09月20日 下午13:07:22 修改本地缓存存储上下文
 */

public class Context implements Serializable {

    private static final long serialVersionUID = -1L;

    public static final String BATCH_THREAD_NAME = "GalaxyBatchTask";
    public static final String ADD_BATCH_KEY = "GalaxyBatchAddBatch";
    public static final String ADD_BATCH_INSERT_KEY = "addInsertBatchMap";
    public static final String ADD_BATCH_UPDATE_KEY = "addUpdateBatchMap";
    public static final String ADD_BATCH_DELETE_KEY = "addDeleteBatchMap";

    private ISysHead sysHead;

    private IAppHead appHead;

    private String runDate;

    private String lastRunDate;

    private String nextRunDate;

    /**
     * 全局事务控制开关
     */
    private String dtpFlag;

    /**
     * Spring事务控制开关
     */
    private String txFlag;

    private boolean isBatch;

    /**
     * 业务节点类型
     */
    private String currentNodeType;

    /**
     * 百信项目要求，上下文增加数据路由关键字段 20170410
     */
    private String routerKey;

    /**
     * @fields platformId
     */
    private String platformId = ThreadLocalManager.getUID();

    /**
     * @fields properties
     */
    private Properties properties = new Properties();



    /**
     * @fields map
     */
    private Map<String, Object> map = new HashMap<>();

    public static Context getInstance() {
        if (ThreadLocalManager.getTranContext() == null) {
            ThreadLocalManager.setTranContext(new Context());
        }
        return ThreadLocalManager.getTranContext();
    }

    public ISysHead getSysHead() {
        return sysHead;
    }

    public void setSysHead(ISysHead sysHead) {
        this.sysHead = sysHead;
    }

    public String getThreadNo() {
        ISysHead sysHead = getSysHead();
        if (sysHead == null)
            return null;
        return sysHead.getThreadNo();
    }

    public String getServiceCode() {
        ISysHead sysHead = getSysHead();
        if (sysHead == null)
            return null;
        return sysHead.getServiceCode();
    }

    public String getTranMode() {
        ISysHead sysHead = getSysHead();
        if (sysHead == null)
            return null;
        return sysHead.getTranMode();
    }

    public String getReference() {
        ISysHead sysHead = getSysHead();
        if (sysHead == null)
            return null;
        return sysHead.getReference();
    }

    public String getSourceType() {
        ISysHead sysHead = getSysHead();
        if (sysHead == null)
            return null;
        return sysHead.getSourceType();
    }

    public String getBranchId() {
        ISysHead sysHead = getSysHead();
        if (sysHead == null)
            return null;
        return sysHead.getBranchId();
    }

    public String getUserId() {
        ISysHead sysHead = getSysHead();
        if (sysHead == null)
            return null;
        return sysHead.getUserId();
    }

    public String getTranDate() {
        ISysHead sysHead = getSysHead();
        if (sysHead == null)
            return null;
        return sysHead.getTranDate();
    }

    public Date getTranDateParse() {
        String tranDate = getTranDate();
        if (null != tranDate)
            return DateUtils.parse(tranDate, DateUtils.DEFAULT_DATE_FORMAT);
        return null;
    }

    public void setRunDate(String runDate) {
        this.runDate = runDate;
    }

    public String getRunDate() {
        return runDate;
    }

    public Date getRunDateParse() {
        String runDate = getRunDate();
        if (null == runDate)
            return null;
        return DateUtils.parse(runDate, DateUtils.DEFAULT_DATE_FORMAT);
    }

    public void setNextRunDate(String nextRunDate) {
        this.nextRunDate = nextRunDate;
    }

    public String getNextRunDate() {
        return nextRunDate;
    }

    public Date getNextRunDateParse() {
        String nextRunDate = getNextRunDate();
        if (null == nextRunDate)
            return null;
        return DateUtils.parse(nextRunDate, DateUtils.DEFAULT_DATE_FORMAT);
    }

    public void setLastRunDate(String lastRunDate) {
        this.lastRunDate = lastRunDate;
    }

    public String getLastRunDate() {
        return lastRunDate;
    }

    public Date getLastRunDateParse() {
        String lastRunDate = getLastRunDate();
        if (null == lastRunDate)
            return null;
        return DateUtils.parse(lastRunDate, DateUtils.DEFAULT_DATE_FORMAT);
    }

    public String getYesterday() {
        Date yesserday = getYesterdayParse();
        if (null == yesserday)
            return null;
        return DateUtils.getDateTime(getYesterdayParse(),
                DateUtils.DEFAULT_DATE_FORMAT);
    }

    public Date getYesterdayParse() {
        Date runDate = getRunDateParse();
        if (null == runDate)
            return null;
        return DateUtils.addDays(runDate, -1);
    }

    public String getTranTimestamp() {
        ISysHead sysHead = getSysHead();
        if (sysHead == null)
            return null;
        return sysHead.getTranTimestamp();
    }

    public String getUserLang() {
        ISysHead sysHead = getSysHead();
        if (sysHead == null)
            return null;
        return sysHead.getUserLang();
    }

    public String getSeqNo() {
        ISysHead sysHead = getSysHead();
        if (sysHead == null)
            return null;
        return sysHead.getSeqNo();
    }

    public String getProgramId() {
        ISysHead sysHead = getSysHead();
        if (sysHead == null)
            return null;
        return sysHead.getProgramId();
    }

    public String getAuthUserId() {
        ISysHead sysHead = getSysHead();
        if (sysHead == null)
            return null;
        return sysHead.getAuthUserId();
    }

    public String getAuthFlag() {
        ISysHead sysHead = getSysHead();
        if (sysHead == null)
            return null;
        return sysHead.getAuthFlag();
    }

    public String getApprUserId() {
        ISysHead sysHead = getSysHead();
        if (sysHead == null)
            return null;
        return sysHead.getApprUserId();
    }

    public String getApprFlag() {
        ISysHead sysHead = getSysHead();
        if (sysHead == null)
            return null;
        return sysHead.getApprFlag();
    }

    public String getSourceBranchNo() {
        ISysHead sysHead = getSysHead();
        if (sysHead == null)
            return null;
        return sysHead.getSourceBranchNo();
    }

    public String getDestBranchNo() {
        ISysHead sysHead = getSysHead();
        if (sysHead == null)
            return null;
        return sysHead.getDestBranchNo();
    }

    public String getMessageType() {
        ISysHead sysHead = getSysHead();
        if (sysHead == null)
            return null;
        return sysHead.getMessageType();
    }

    public String getMessageCode() {
        ISysHead sysHead = getSysHead();
        if (sysHead == null)
            return null;
        return sysHead.getMessageCode();
    }

    public String getFilePath() {
        ISysHead sysHead = getSysHead();
        if (sysHead == null)
            return null;
        return sysHead.getFilePath();
    }

    public String getMacValue() {
        ISysHead sysHead = getSysHead();
        if (sysHead == null)
            return null;
        return sysHead.getMacValue();
    }

    public String getPlatformId() {
        // 获取平台ID
        return platformId;
    }

    public void setPlatformId(String platformId) {
        // 获取平台ID
        this.platformId = platformId;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public void setProperty(String key, String value) {
        properties.setProperty(key, value);
    }

    public void removeProperty(String key) {
        properties.remove(key);
    }

    public Object getObject(String key) {
        return map.get(key);
    }

    public void setObject(String key, Object value) {
        map.put(key, value);
    }

    /**
     * 获取IOC容器bean实例
     *
     * @param id
     * @return
     * @description
     * @version 1.0
     * @author Tim
     * @update 2014年11月20日 上午9:29:31
     */
    @SuppressWarnings("unchecked")
    public <T> T getBean(String id) {
        T t = null;
        try {
        	t = (T)SpringApplicationContext.getContext().getBean(id);
            //t = (T) SpringContainer.getContext().getBean(id);
        } catch (NoSuchBeanDefinitionException e) {
        }
        return t;
    }

    /**
     * 清空上下文
     *
     * @description
     * @version 1.0
     * @author Tim
     * @update 2015年1月19日 下午3:20:27
     */
    public void cleanResource() {
        // 解除引用
        ThreadLocalManager.setTranContext(new Context());
    }

    /**
     * 获取全局事务控制开关
     *
     * @return
     */
    public String getDtpFlag() {
        return dtpFlag;
    }

    /**
     * 设置全局事务控制开关
     *
     * @param dtpFlag
     */
    public void setDtpFlag(String dtpFlag) {
        this.dtpFlag = dtpFlag;
    }

    public boolean isBatch() {
        return isBatch;
    }

    public void setIsBatch(boolean isBatch) {
        this.isBatch = isBatch;
    }

    public String getCurrentNodeType() {
        return currentNodeType;
    }

    public void setCurrentNodeType(String currentNodeType) {
        this.currentNodeType = currentNodeType;
    }

    /**
     * 将上下文对象序列化为JSON字符串
     *
     * @param context
     * @return
     */
    public static String serializeContext(Context context) {
        JSONObject contextJson = new JSONObject();
        putJSONObjectValue(contextJson, "sysHead", context.sysHead);
        putJSONObjectValue(contextJson, "appHead", context.appHead);
        putJSONObjectValue(contextJson, "dtpFlag", context.dtpFlag);
        putJSONObjectValue(contextJson, "currentNodeType", context.currentNodeType);
        putJSONObjectValue(contextJson, "isBatch", context.isBatch);
        putJSONObjectValue(contextJson, "runDate", context.runDate);
        putJSONObjectValue(contextJson, "lastRunDate", context.lastRunDate);
        putJSONObjectValue(contextJson, "nextRunDate", context.nextRunDate);
        putJSONObjectValue(contextJson, "platformId", context.platformId);
        // 百信项目要求，上下文增加数据路由关键字段 20170410
        putJSONObjectValue(contextJson, "routerKey", context.routerKey);
        return contextJson.toJSONString();
    }

    /**
     * 将JSON字符串反序列化为上下文对象
     *
     * @param contextJson
     * @return
     */
    public static Context deserialize(String contextJson) {
        return JSON.parseObject(contextJson, Context.class);
    }

    private static void putJSONObjectValue(JSONObject jsonObject, String key, Object value) {
        if (value != null) {
            jsonObject.put(key, value);
        }
    }

    public String toString() {
        return Context.serializeContext(this);
    }

    public IAppHead getAppHead() {
        return appHead;
    }

    public void setAppHead(IAppHead appHead) {
        this.appHead = appHead;
    }

    // 百信项目要求，上下文增加数据路由关键字段 20170410
    public String getRouterKey() {
        return routerKey;
    }

    // 百信项目要求，上下文增加数据路由关键字段 20170410
    public void setRouterKey(String routerKey) {
        this.routerKey = routerKey;
    }

    public String getTxFlag() {
        return txFlag;
    }

    public void setTxFlag(String txFlag) {
        this.txFlag = txFlag;
    }

    public boolean txIsOpen() {
        return (this.txFlag == null || "Y".equals(this.txFlag)) ? true : false;
    }
}
