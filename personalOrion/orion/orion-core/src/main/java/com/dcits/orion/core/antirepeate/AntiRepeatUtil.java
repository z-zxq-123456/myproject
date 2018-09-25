package com.dcits.orion.core.antirepeate;

import com.alibaba.dubbo.common.utils.ConfigUtils;
import com.alibaba.fastjson.JSON;
import com.dcits.galaxy.base.GalaxyConstants;
import com.dcits.galaxy.base.data.BaseRequest;
import com.dcits.galaxy.base.data.BeanResult;
import com.dcits.galaxy.base.data.Result;
import com.dcits.galaxy.base.tuple.TwoTuple;
import com.dcits.galaxy.base.util.DateUtils;
import com.dcits.galaxy.base.util.ExceptionUtils;
import com.dcits.galaxy.core.access.ThreadLocalManager;
import com.dcits.galaxy.core.serializer.SerializationUtils;
import com.dcits.galaxy.core.spring.SpringApplicationContext;
import com.dcits.orion.api.IAntiRepeat;
import com.dcits.orion.base.common.ServiceHandler;
import com.dcits.orion.core.dao.FwDao;
import com.dcits.orion.core.util.BusinessUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lixbb on 2016/1/11.
 */

@Repository
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class AntiRepeatUtil implements InitializingBean,ApplicationListener<ContextRefreshedEvent> {
    private static final Logger logger = LoggerFactory
            .getLogger(AntiRepeatUtil.class);
    @Resource
    FwDao fwDao;
    IAntiRepeat iAntiRepeat;


    public org.springframework.core.io.Resource getConfigLocation() {
        return configLocation;
    }

    public void setConfigLocation(org.springframework.core.io.Resource configLocation) {
        this.configLocation = configLocation;
    }

    private org.springframework.core.io.Resource configLocation;

    //防重交易按服务ID配置
    private Map<String, AntiRepeatService> antiRepeatServices;
    //冲正交易
    private Map<String, AntiRepeatService> reversalServices;
    //按条件防重
    private List<Filter> filters;

    private String switch_on_off;

    private boolean switchOn = false;

    public boolean switchOn() {
        return switchOn;
    }

    private String[] getKeysByFilter(BaseRequest request) {
        if (filters != null) {
            for (Filter filter : filters) {
                if (filter.accept(request)) {
                    return filter.getKeys();
                }

            }
        }
        return null;
    }

    private String getKeyValue(BaseRequest request, String[] keys) {

        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext(request);
        StringBuffer keyValues = new StringBuffer();
        int index = 0;
        if (keys != null) {
            for (String key : keys) {
                Object keyObj = null;
                try {
                    //keyObj = request.readValue(key);
                    keyObj = parser.parseExpression(key).getValue(context);
                } catch (Exception e) {
                    if (logger.isWarnEnabled()) {
                        logger.warn("Request中取不到指定域值！key=" + key + " Request=" + request);
                    }
                }
                String keyValue = "";
                if (keyObj != null) {
                    keyValue = keyObj.toString();
                }
                if (index == 0) {
                    keyValues.append(keyValue);
                } else {
                    keyValues.append("-").append(keyValue);
                }
                index++;

            }
        }
        return keyValues.toString();
    }

    private TwoTuple<String, String> getKeyValues(BaseRequest request) {
        String tranKey = null;
        String reversalKey = null;
        String serviceId = ServiceHandler.getServiceName(request);
        AntiRepeatService tranService = antiRepeatServices.get(serviceId);
        if (tranService != null) {
            tranKey = getKeyValue(request, tranService.getKeys());
        } else {
            String[] keys = getKeysByFilter(request);
            if (keys != null)
                tranKey = getKeyValue(request, keys);
        }
        AntiRepeatService reversalService = reversalServices.get(serviceId);
        if (reversalService != null) {
            reversalKey = getKeyValue(request, reversalService.getKeys());
        }
        TwoTuple<String, String> keyValues = new TwoTuple(tranKey, reversalKey);
        return keyValues;
    }

    //正交易防重处理
    private BeanResult tran(BaseRequest request, String keys, String inMsg, String reversalKey) throws Exception {
        BeanResult beanResult = null;
        Map tranInfo = fwDao.getTranInfo(keys);
        if (tranInfo == null) {
            String localIP = "UnknownHost";
            try {
                localIP = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
            }
            try {
                tranInfo = new HashMap();
                tranInfo.put("SERVICE_ID", ServiceHandler.getServiceName(request));
                tranInfo.put("KEY_VALUE", keys);
                tranInfo.put("TRAN_DATE", request.getSysHead().getTranDate());
                tranInfo.put("TRAN_TIME", DateUtils.getDateTime("yyyy-MM-dd HH:mm:ss SSS"));
                tranInfo.put("IN_MSG", inMsg);
                tranInfo.put("SOURCE_TYPE", request.getSysHead().getSourceType());
                tranInfo.put("SEQ_NO", request.getSysHead().getSeqNo());
                tranInfo.put("PROGRAM_ID", request.getSysHead().getProgramId());
                tranInfo.put("STATUS", "N");
                tranInfo.put("REFERENCE", request.getSysHead().getReference());
                tranInfo.put("PLATFORM_ID", ThreadLocalManager.getUID());
                tranInfo.put("BRANCH_ID", request.getSysHead().getBranchId());
                tranInfo.put("IP_ADDRESS", localIP);
                tranInfo.put("USER_ID", request.getSysHead().getUserId());
                tranInfo.put("REVERSAL_KEY_VALUE", reversalKey);
                if (iAntiRepeat == null)
                    fwDao.insertTranInfo(tranInfo);
                else {
                    iAntiRepeat.toTranInfo(request, tranInfo);
                    iAntiRepeat.insertTranInfo(tranInfo);
                }
            } catch (Exception e) {
                if (logger.isErrorEnabled())
                    logger.error(ExceptionUtils.getStackTrace(e));
                tranInfo = fwDao.getTranInfo(keys);
                if (tranInfo == null) {
                    beanResult = new BeanResult("100408", BusinessUtils.getErrorMsg("100408"));
                } else {
                    beanResult = getBeanResult(tranInfo);
                    if (GalaxyConstants.STATUS_SUCCESS.equals(beanResult.getRetStatus())){
                        request.getSysHead().setReference((String) tranInfo.get("REFERENCE"));
                    }
                }
            }
        } else {
            beanResult = getBeanResult(tranInfo);
            if (GalaxyConstants.STATUS_SUCCESS.equals(beanResult.getRetStatus())){
                request.getSysHead().setReference((String) tranInfo.get("REFERENCE"));
            }
        }
        return beanResult;
    }

    //冲正交易
    private BeanResult reversalTran(BaseRequest request, String reversalKey, String tranKey) {
        BeanResult beanResult = null;

        Map tranInfo = fwDao.getTranInfo(reversalKey);
        String localIP = "UnknownHost";
        try {
            localIP = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
        }
        if (tranInfo == null) {
            beanResult = new BeanResult("100400", BusinessUtils.getErrorMsg("100400"));

            tranInfo = new HashMap();

            tranInfo.put("SERVICE_ID", "unknown");
            tranInfo.put("KEY_VALUE", reversalKey);
            tranInfo.put("TRAN_DATE", request.getSysHead().getTranDate());
            tranInfo.put("TRAN_TIME", DateUtils.getDateTime("yyyy-MM-dd HH:mm:ss SSS"));
            tranInfo.put("IN_MSG", null);
            tranInfo.put("SOURCE_TYPE", request.getSysHead().getSourceType());
            tranInfo.put("SEQ_NO", null);
            tranInfo.put("PROGRAM_ID", request.getSysHead().getProgramId());
            tranInfo.put("STATUS", "P");
            tranInfo.put("REFERENCE", null);
            tranInfo.put("PLATFORM_ID", ThreadLocalManager.getUID());
            tranInfo.put("BRANCH_ID", request.getSysHead().getBranchId());
            tranInfo.put("IP_ADDRESS", localIP);
            tranInfo.put("USER_ID", request.getSysHead().getUserId());
            if (iAntiRepeat == null)
                fwDao.insertTranInfo(tranInfo);
            else
            {
                iAntiRepeat.toTranInfo(request,tranInfo);
                iAntiRepeat.insertTranInfo(tranInfo);
            }
        } else {
            String status = (String) tranInfo.get("STATUS");
            switch (status) {
                case "N":
                    beanResult = new BeanResult("100401", BusinessUtils.getErrorMsg("100401"));
                    break;
                case "R":
                    beanResult = new BeanResult("100404", BusinessUtils.getErrorMsg("100404"));
                    break;
                case "P":
                    beanResult = new BeanResult("100403", BusinessUtils.getErrorMsg("100403"));
                    break;
                case "Q":
                    beanResult = new BeanResult("100405", BusinessUtils.getErrorMsg("100405"));
                    break;
                case "F":
                    beanResult = new BeanResult("100402", BusinessUtils.getErrorMsg("100402"));
                    break;
                case "S":
                    tranInfo.put("STATUS", "Q");
                    fwDao.updateTranInfo(tranInfo);
                    break;
            }
        }
        if (beanResult != null && tranKey != null) {
            Map tran = new HashMap();
            tran.put("KEY_VALUE", tranKey);
            tran.put("STATUS", beanResult.getRetStatus());
            tran.put("END_TIME", DateUtils.getDateTime("yyyy-MM-dd HH:mm:ss SSS"));
            tran.put("BEAN_RESULT", getBytesByBeanResult(beanResult));
            fwDao.updateTranInfo(tran);
        }
        return beanResult;
    }

    //防重交易入口
    public BeanResult process(BaseRequest request) {

        BeanResult beanResult = null;
        if (switchOn) {
            TwoTuple<String, String> keyValues = getKeyValues(request);
            String tranKey = keyValues.first;

            String reversalKey = keyValues.second;
            //正交易防重
            if (tranKey != null) {
                ThreadLocalManager.put("TRAN_KEY",tranKey);
                try {
                    beanResult = tran(request, tranKey, JSON.toJSONString(request), reversalKey);
                } catch (Exception e) {
                    beanResult = new BeanResult("100407", BusinessUtils.getErrorMsg("100407"));
                }
            }
            //正交易OK，判断是不是冲正交易
            if (beanResult == null && reversalKey != null) {
                beanResult = reversalTran(request, reversalKey, tranKey);
            }
        }
        return beanResult;
    }


    //更新交易信息
    public void updateTranInfo(BaseRequest request, BeanResult beanResult) {
        try {
            if (switchOn) {

                TwoTuple<String, String> keyValues = getKeyValues(request);
                String tranKey = keyValues.first;
                String reversalKey = keyValues.second;
                if (tranKey != null) {
                    String outMsg = JSON.toJSONString(beanResult);
                    Map tranInfo = new HashMap();
                    tranInfo.put("KEY_VALUE", tranKey);
                    tranInfo.put("STATUS", beanResult.getRetStatus());
                    tranInfo.put("END_TIME", DateUtils.getDateTime("yyyy-MM-dd HH:mm:ss SSS"));
                    tranInfo.put("OUT_MSG", outMsg);
                    tranInfo.put("BEAN_RESULT", getBytesByBeanResult(beanResult));
                    fwDao.updateTranInfo(tranInfo);
                }
                //冲正交易成功完成
                if (reversalKey != null && "S".equals(beanResult.getRetStatus())) {
                    Map tranInfo = new HashMap();
                    tranInfo.put("KEY_VALUE", reversalKey);
                    tranInfo.put("STATUS", "R");
                    fwDao.updateTranInfo(tranInfo);
                }

            }
        } catch (Throwable t) {
            if (logger.isWarnEnabled())
                logger.warn("更新交易状态失败！" + ExceptionUtils.getStackTrace(t));
        }
    }

    private BeanResult getBeanResult(Map tranInfo) throws Exception {
        BeanResult beanResult = null;

        String status = (String) tranInfo.get("STATUS");
        switch (status) {
            case "N":
                return new BeanResult("100401", BusinessUtils.getErrorMsg("100401"));
            case "R":
                return new BeanResult("100404", BusinessUtils.getErrorMsg("100404"));
            case "P":
                return new BeanResult("100403", BusinessUtils.getErrorMsg("100403"));
            case "Q":
                return new BeanResult("100405", BusinessUtils.getErrorMsg("100405"));
        }
        Object obj = tranInfo.get("BEAN_RESULT");
        if (obj == null)
            beanResult = new BeanResult("100401", "原交易正在执行中");
        else {
            try {
                beanResult = getBeanResult(obj);
            }
            catch (Exception e) {
                beanResult = new BeanResult("100406", "交易已完成，获取交易返回信息失败！");
            }
        }
        return beanResult;
    }

    public BeanResult getBeanResult(Object obj) throws Exception {
        return BusinessUtils.deserialize(obj);
    }

    public byte[] getBytesByBeanResult(BeanResult beanResult){
        return SerializationUtils.serializeObj(beanResult);
    }


    private List<Condition> getConditions(Element element) {
        List<Condition> conditions = new ArrayList<>();
        List<Element> conditionElements = element.selectNodes("filter");
        if (conditionElements != null) {
            for (Element conditionElement : conditionElements) {
                Condition condition = new Condition();
                condition.setContents(conditionElement.attributeValue("condition"));
                conditions.add(condition);
            }
        }
        return conditions;
    }

    private void readFilters(Element filterElement) {
        if (filterElement != null) {
            filters = new ArrayList<>();
            List<Element> keys = filterElement.selectNodes("key");
            if (keys != null) {
                for (Element key : keys) {
                    String keyValue = key.attributeValue("keys");
                    Filter filter = new Filter(keyValue);
                    Element includeEclement = (Element) key.selectSingleNode("include");
                    Element excludeEclement = (Element) key.selectSingleNode("exclude");
                    List includes = getConditions(includeEclement);
                    List excludes = getConditions(excludeEclement);
                    filter.setIncludes(includes);
                    filter.setExcludes(excludes);
                    filters.add(filter);
                }
            }
        }

    }

    private Map<String, AntiRepeatService> readService(Element serviceElement) {
        Map<String, AntiRepeatService> services = new HashMap<>();
        if (serviceElement != null) {
            List<Element> keyElements = serviceElement.selectNodes("key");
            if (keyElements != null) {
                for (Element keyElement : keyElements) {
                    String keys[] = keyElement.attributeValue("keys").split(",");
                    List<Element> serviceElements = keyElement.selectNodes("service");
                    if (serviceElements != null) {
                        for (Element serviceEle : serviceElements) {
                            String serviceId = serviceEle.attributeValue("id");
                            AntiRepeatService antiRepeatService = new AntiRepeatService(serviceId, keys);
                            services.put(serviceId, antiRepeatService);
                        }
                    }
                }
            }


        }
        return services;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        if (logger.isDebugEnabled())
            logger.debug("load antiRepeatCfg.xml!");
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(configLocation.getInputStream());
            Element root = document.getRootElement();
            switch_on_off = (String) root.attribute("switch").getData();
            if (switch_on_off.equals("on")) {
                switchOn = true;
                Element filterElement = (Element) document.selectSingleNode("/AntiRepeat/Filters");
                Element serviceElement = (Element) document.selectSingleNode("/AntiRepeat/Services");
                Element reversalServiceElement = (Element) document.selectSingleNode("/AntiRepeat/ReversalServices");
                readFilters(filterElement);
                antiRepeatServices = readService(serviceElement);
                reversalServices = readService(reversalServiceElement);
            }
        } catch (Exception e) {
            if (logger.isWarnEnabled())
                logger.warn("load antiRepeatCfg.xml failed! " + e.getMessage());
        }
        String addRetCode = ConfigUtils.getProperty("galaxy.business.addRetCode");
        if (addRetCode != null)
        {
            addRetCode = addRetCode.toLowerCase().trim();
            if ("true".equals(addRetCode))
                Result.addRetCode = true;
        }

    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        try
        {
            iAntiRepeat = (IAntiRepeat)SpringApplicationContext.getContext().getBean("iAntiRepeat");
        }
        catch (Exception e)
        {

        }
    }
}
