package com.dcits.orion.base.map;

import com.alibaba.fastjson.JSON;
import com.dcits.galaxy.base.data.BaseRequest;
import com.dcits.galaxy.base.data.BeanResult;
import com.dcits.galaxy.base.data.ISysHead;
import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.galaxy.base.util.ExceptionUtils;
import com.dcits.galaxy.base.util.JsonUtils;
import com.dcits.galaxy.base.util.StringUtils;
import com.dcits.galaxy.core.spring.SpringApplicationContext;
import com.dcits.orion.api.Convert;
import com.dcits.orion.base.AbstractConvert;
import com.dcits.orion.base.json.JsonConvert;
import com.dcits.orion.base.map.mapping.ParserMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by lixiaobin on 2016/10/31.
 */
@Repository
public class MapConvert extends AbstractConvert implements Convert<Map>, InitializingBean {

    @Resource
    JsonConvert jsonConvert;

    String parserId = null;

    private static final Logger logger = LoggerFactory
            .getLogger(MapConvert.class);

    @Override
    public Map pack(ISysHead sysHead, BeanResult br) {
        String ret = jsonConvert.pack(sysHead, br);
        Map map = JSON.parseObject(ret);
        if (!StringUtils.isBlank(parserId)) {
            ParserMapping parserMapping = SpringApplicationContext.getContext().getBean(ParserMapping.class);
            map = parserMapping.getMapping(map, parserId, ParserMapping.WORK_MAP_OUT);
        }
        return map;
    }


    @Override
    public BaseRequest unpack(Map msg) {

        if (!StringUtils.isBlank(parserId)) {
            ParserMapping parserMapping = SpringApplicationContext.getContext().getBean(ParserMapping.class);
            msg = parserMapping.getMapping(msg, parserId, ParserMapping.WORK_MAP_IN);
        }
        BaseRequest request = null;
        try {
            Map sysHead = (Map) msg.get("SYS_HEAD");
            String serviceCode = (String) sysHead.get("SERVICE_CODE");
            String messageType = (String) sysHead.get("MESSAGE_TYPE");
            String messageCode = (String) sysHead.get("MESSAGE_CODE");
            Class<? extends BaseRequest> modelClazz = getModelClazz(serviceCode, messageType, messageCode);
            String strMsg = JSON.toJSONString(msg);
            strMsg = JsonUtils.convertMsg(strMsg, JsonUtils.HUMP_TYPE);
            request = JSON.parseObject(strMsg, modelClazz);
            afterUnpack(request);
        } catch (Exception e) {
            if (logger.isErrorEnabled())
                logger.error(ExceptionUtils.getStackTrace(e));
            throw new GalaxyException(e);
        }
        return request;
    }

    public String getParserId() {
        return parserId;
    }

    public void setParserId(String parserId) {
        this.parserId = parserId;
    }
}
