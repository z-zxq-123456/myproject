package com.dcits.orion.base.util;

import com.alibaba.fastjson.JSONObject;
import com.dcits.galaxy.base.data.*;
import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.galaxy.core.spring.SpringApplicationContext;
import com.dcits.orion.base.xml.XmlConvert;

import java.util.Map;

/**
 * Created by lixbb on 2016/1/21.
 */
public class CdXmlUtils {

    private static XmlConvert getXmlConvert()
    {
        return SpringApplicationContext.getContext().getBean(XmlConvert.class);
    }
    public static String pack(ISysHead sysHead, BeanResult br)
    {
        return getXmlConvert().pack(sysHead,br);
    }
    public static BaseRequest unpack(String msg)
    {
        return getXmlConvert().unpack(msg);
    }
    public static String requestToXml(BaseRequest request)
    {
        return getXmlConvert().requestToXml(request);
    }
    public static BeanResult  xmlToBeanResult(String inMsg,BaseRequest request,Class<? extends BaseResponse> clazz)
    {
        try {
            return getXmlConvert().xmlToBeanResult(inMsg,request,clazz);
        } catch (Exception e) {
            throw new GalaxyException(e);
        }
    }
    public static String jsonObjectToXml(JSONObject jsonObject)
    {
        return getXmlConvert().jsonObjectToXml(jsonObject);
    }
    public static JSONObject xmlToJsonObject(String inMsg)
    {
        return getXmlConvert().xmlToJsonObject(inMsg);
    }
    public static String mapToXml(Map map)
    {
        return getXmlConvert().mapToXml(map);
    }
    public static Map xmlToMap(String inMsg)
    {
        return getXmlConvert().xmlToMap(inMsg);
    }
}
