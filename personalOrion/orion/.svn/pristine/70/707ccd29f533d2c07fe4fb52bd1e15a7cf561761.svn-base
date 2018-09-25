package com.dcits.orion.base.util;

import com.alibaba.dubbo.common.utils.ConfigUtils;
import com.dcits.galaxy.base.data.ISysHead;
import com.dcits.galaxy.base.util.ClassLoaderUtils;
import com.dcits.galaxy.base.util.StringUtils;
import com.dcits.galaxy.core.access.ThreadLocalManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConvertUtils {

    private static final Logger logger = LoggerFactory
            .getLogger(ConvertUtils.class);

    private static final String CONVERT_SERVICE_CODE = "_convertServiceCode";

    private static Map<String, String> serviceCodeMapping = new HashMap<>(16);

    static {
        try {
            Properties properties = ClassLoaderUtils.getProperties("ext/serviceCodeMapping.properties");
            if (logger.isInfoEnabled()) {
                logger.info("注册ServiceCode映射信息！");
            }
            if (properties != null) {
                for (Object key : properties.keySet()) {
                    logger.info("扩展映射:源服务码[{}] 内部服务码[{}]！", new Object[]{key, properties.get(key)});
                    serviceCodeMapping.put((String) key, (String) properties.get(key));
                }
            }
        } catch (Exception e) {
            //忽略加载
        }
    }

    /**
     * 获取应用组
     *
     * @return
     */
    public static String getAppGroup() {
        return ConfigUtils.getProperty("galaxy.application.group");
    }

    public static String getAppName() {
        return ConfigUtils.getProperty("galaxy.application.name");
    }

    public static String inServiceCode(String serviceCode) {
        String sc = serviceCode;
        if (serviceCodeMapping.containsKey(serviceCode)) {
            sc = serviceCodeMapping.get(serviceCode);
            ThreadLocalManager.put(CONVERT_SERVICE_CODE, serviceCode);
            logger.info("服务码 [{}] 映射为 [{}]！", new Object[]{serviceCode, sc});
        } else {
            ThreadLocalManager.put(CONVERT_SERVICE_CODE, null);
        }
        return sc;
    }

    public static void inServiceCode(ISysHead sysHead) {
        String serviceCode = sysHead.getServiceCode();
        String sc = inServiceCode(serviceCode);
        if (StringUtils.isNotEmpty(sc))
            sysHead.setServiceCode(sc);
    }

    public static void outServiceCode(ISysHead sysHead) {
        String serviceCode = sysHead.getServiceCode();
        //清理不必要的下送
        if (null != sysHead) sysHead.cleanSysHead();
        if (null == serviceCode
                || null == ThreadLocalManager.get(CONVERT_SERVICE_CODE)) {
            return;
        }
        String sc = (String) ThreadLocalManager.get(CONVERT_SERVICE_CODE);
        sysHead.setServiceCode(sc);
        logger.info("服务码 [{}] 映射为 [{}] 返回！", new Object[]{serviceCode, sc});
    }
}