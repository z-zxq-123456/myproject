package com.dcits.orion.base;

import com.dcits.orion.api.BusinessTraceNo;
import com.dcits.orion.api.IProcess;
import com.dcits.orion.base.common.ServiceHandler;
import com.dcits.orion.base.util.ConvertUtils;
import com.dcits.galaxy.base.data.BaseRequest;
import com.dcits.galaxy.base.util.ExceptionUtils;
import com.dcits.galaxy.base.util.StringUtils;
import com.dcits.galaxy.core.access.ThreadLocalManager;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by lixbb on 2016/1/19.
 */
public abstract class AbstractConvert {
    private static final Logger logger = LoggerFactory
            .getLogger(AbstractConvert.class);

    public AbstractConvert()
    {
        putDefaultParse();
    }

    protected Map<String, String> parseConfig;

    protected org.springframework.core.io.Resource configLocation;

    public Class<? extends BaseRequest> getModelClazz(String serviceCode, String messageType, String messageCode) {
        IProcess process = ServiceHandler.getProcess(ConvertUtils.inServiceCode(serviceCode), messageType, messageCode);
        return process.getRequestModel();
    }

    public void afterUnpack(BaseRequest req) {
        // 设置Uid
        if (StringUtils.isEmpty(req.getUid()) && null != ThreadLocalManager.getUID())
            req.setUid(ThreadLocalManager.getUID());
        traceNo(req);
    }

    private void traceNo(BaseRequest req) {
        long start = System.currentTimeMillis();
        try {
            BusinessTraceNo businessTraceNo = ServiceHandler.getBusinessTraceNo();
            if (null == businessTraceNo) {
                if (logger.isWarnEnabled())
                    logger.warn("BusinessTraceNo Service not exist, skip afterService process!");
            } else {
                req.getSysHead().setReference(businessTraceNo.generator());
            }
        } catch (Throwable t) {
            if (logger.isWarnEnabled())
                logger.warn(ExceptionUtils.getStackTrace(t));
        } finally {
            long end = System.currentTimeMillis();
            if (logger.isInfoEnabled())
                logger.info("BusinessTraceNo execute time:" + (end - start));
        }
    }
    private void putDefaultParse() {
        this.parseConfig = new HashMap<>();
        this.parseConfig.put(Boolean.TYPE.getName(), "parseString");
        this.parseConfig.put(Boolean.class.getName(), "parseString");
        this.parseConfig.put(Character.TYPE.getName(), "parseString");
        this.parseConfig.put(Character.class.getName(), "parseString");
        this.parseConfig.put(Byte.TYPE.getName(), "parseString");
        this.parseConfig.put(Byte.class.getName(), "parseString");
        this.parseConfig.put(Short.TYPE.getName(), "parseString");
        this.parseConfig.put(Short.class.getName(), "parseString");
        this.parseConfig.put(Integer.TYPE.getName(), "parseString");
        this.parseConfig.put(Integer.class.getName(), "parseString");
        this.parseConfig.put(Long.TYPE.getName(), "parseString");
        this.parseConfig.put(Long.class.getName(), "parseString");
        this.parseConfig.put(Float.TYPE.getName(), "parseString");
        this.parseConfig.put(Float.class.getName(), "parseString");
        this.parseConfig.put(Double.TYPE.getName(), "parseString");
        this.parseConfig.put(Double.class.getName(), "parseString");
        this.parseConfig.put(BigInteger.class.getName(), "parseString");
        this.parseConfig.put(BigDecimal.class.getName(), "parseString");
        this.parseConfig.put(String.class.getName(), "parseString");
    }

    public void afterPropertiesSet() throws Exception {

        if (logger.isDebugEnabled())
            logger.debug("load parseStringCfg.xml!");
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(configLocation.getInputStream());
            Element root = document.getRootElement();
            for (Iterator i = root.elementIterator(); i.hasNext(); ) {
                Element type = (Element) i.next();
                String name = (String) type.attribute("name").getData();
                String beanId = (String) type.attribute("beanId").getData();
                this.parseConfig.put(name, beanId);
            }
        } catch (Exception e) {
            if (logger.isWarnEnabled())
                logger.warn("load parseStringCfg.xml failed! " + e.getMessage());
        }

    }
}
