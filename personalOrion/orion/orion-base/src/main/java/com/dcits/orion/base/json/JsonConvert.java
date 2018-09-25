package com.dcits.orion.base.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.dcits.galaxy.base.GalaxyConstants;
import com.dcits.galaxy.base.data.BaseRequest;
import com.dcits.galaxy.base.data.BeanResult;
import com.dcits.galaxy.base.data.ISysHead;
import com.dcits.galaxy.base.data.SysHead;
import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.galaxy.base.util.ExceptionUtils;
import com.dcits.galaxy.base.util.JsonUtils;
import com.dcits.orion.api.Convert;
import com.dcits.orion.base.AbstractConvert;
import com.dcits.orion.base.util.ConvertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lixbb on 2016/1/19.
 */
@Repository
public class JsonConvert extends AbstractConvert implements Convert<String> {

    private static final Logger logger = LoggerFactory
            .getLogger(JsonConvert.class);

    // 处理小数精度过多的浮点数据转变为科学计数法问题，增加Filter扩展
    private ValueFilter valueFilter;

    // 增加Future的配置接口
    @Resource
    private IJsonSerializerFeature jsonSerializerFeature;

    // 增加jsonConfig的配置接口
    private IJsonConfig jsonConfig;

    /**
     * 初始化FastJson的config属性
     *
     * @exception Exception
     */
    public void initJsonConfig() throws Exception {
        if (null != jsonConfig) {
            jsonConfig.initJsonConfig();
        }
    }

    /**
     * 组包，将公共数据结构中的数据组织为特定的报文结构
     *
     * @param sysHead
     * @param br
     * @return
     */
    @Override
    public String pack(ISysHead sysHead, BeanResult br) {
        JSONObject jsonOutMsg = new JSONObject();
        JSONObject jsonInSysHead;
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmssSSS");
        sysHead = null == sysHead ? new SysHead() : sysHead;
        ConvertUtils.outServiceCode(sysHead);
        sysHead.setTranTimestamp(sdf.format(new Date()));
        // 整体加10
        /**
         String messageType = sysHead.getMessageType();
         sysHead.setMessageType(StringUtils.lfillStr(
         String.valueOf(Integer.valueOf(messageType).intValue() + 10), 4, "0"));*/
        jsonOutMsg.put(GalaxyConstants.SYS_HEAD, sysHead);
        jsonInSysHead = jsonOutMsg.getJSONObject(GalaxyConstants.SYS_HEAD);
        jsonInSysHead.put(GalaxyConstants.RET_STATUS, br.getRetStatus());
        jsonInSysHead.put(GalaxyConstants.RETS, br.getRetJsonObject());
        jsonOutMsg.put(GalaxyConstants.SYS_HEAD, jsonInSysHead);
        if (br.getResponse() != null) {
            jsonOutMsg.put(GalaxyConstants.BODY, br.getResponse());
        }

        if (br.getAppHead() != null) {
            jsonOutMsg.put(GalaxyConstants.APP_HEAD, br.getAppHead());
        }

        try {
            return JsonUtils.convertMsg(
                    valueFilter == null ? JSON.toJSONString(jsonOutMsg, jsonSerializerFeature.getSerializerFeature())
                            : JSON.toJSONString(jsonOutMsg, valueFilter, jsonSerializerFeature.getSerializerFeature()),
                    JsonUtils.UPPER_TYPE);// 将报文转换位大写 +下划线格式，在解析成JSONObject
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("Exception occurred when packing.\n"
                        + ExceptionUtils.getStackTrace(e));
            }
            throw new GalaxyException("Exception occurred when packing.");
        }
    }

    /**
     * 拆包体
     *
     * @param msg
     *         请求报文
     * @return
     */
    @Override
    public BaseRequest unpack(String msg) {
        msg = JsonUtils.convertMsg(msg, JsonUtils.HUMP_TYPE);
        JSONObject jsonObject = JSON.parseObject(msg);
        String serviceCode = jsonObject.getJSONObject("sysHead").getString("serviceCode");
        String messageType = jsonObject.getJSONObject("sysHead").getString("messageType");
        String messageCode = jsonObject.getJSONObject("sysHead").getString("messageCode");
        /*String serviceCode = JsonPath.read(msg, "$.sysHead.serviceCode");
        String messageType = JsonPath.read(msg, "$.sysHead.messageType");
        String messageCode = JsonPath.read(msg, "$.sysHead.messageCode");*/
        Class<? extends BaseRequest> modelClazz = getModelClazz(serviceCode, messageType, messageCode);
        BaseRequest request = JSON.parseObject(msg, modelClazz);
        afterUnpack(request);
        return request;
    }


    public ValueFilter getValueFilter() {
        return valueFilter;
    }

    public void setValueFilter(ValueFilter valueFilter) {
        this.valueFilter = valueFilter;
    }

    public IJsonSerializerFeature getJsonSerializerFeature() {
        return jsonSerializerFeature;
    }

    public void setJsonSerializerFeature(IJsonSerializerFeature jsonSerializerFeature) {
        this.jsonSerializerFeature = jsonSerializerFeature;
    }

    public IJsonConfig getJsonConfig() {
        return jsonConfig;
    }

    public void setJsonConfig(IJsonConfig jsonConfig) {
        this.jsonConfig = jsonConfig;
    }
}
