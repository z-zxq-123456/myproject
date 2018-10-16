package com.dcits.ensemble.om.cmc.service;

import com.alibaba.fastjson.JSONObject;
import com.dcits.baixin.msoa.support.MsoaConstants;
import com.dcits.ensemble.om.cmc.constant.CmcConstant;
import com.dcits.ensemble.om.cmc.dataTable.CmcCardTableUsedInfoModel;
import com.dcits.ensemble.om.cmc.util.BeanConvertUtils;
import com.dcits.ensemble.om.cmc.util.DataPostUtil;
import com.dcits.ensemble.om.pm.common.adapter.HttpAdapter;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Description :
 * @Author :admin
 * @Date : Create on 2018/4/27
 */
@Service
public class CmcCardTableUsedInfoService {


    /**
     * 获取开卡记录
     * @param requestMapping
     * @return
     */
    public CmcCardTableUsedInfoModel qryTableUsedInfo(Map requestMapping){

        JSONObject request = new JSONObject();
        request.putAll(requestMapping);
        DataPostUtil.genCommonArgs(request);
        DataPostUtil.genMosaHead(request,"0404","1400","MbsdCard","QryTableUsedInfoAPI.qryTableUsedInfo");
        //请求服务
        String result = sendToServer(CmcConstant.ADAPTER_CACHE_KEY_PM,request);
        //结果响应
        JSONObject resultJson = JSONObject.parseObject(result);
        JSONObject resSysHead = resultJson.getJSONObject(MsoaConstants.SYS_HEAD);
        JSONObject resBody = resultJson.getJSONObject(MsoaConstants.BODY);
        DataPostUtil.dealWithResultStatus(resSysHead);
        return (CmcCardTableUsedInfoModel) BeanConvertUtils.getInstance().convertToResult(resBody,CmcCardTableUsedInfoModel.class);
    }

    /**
     * 发布到服务器
     * @param url
     * @param requestMapping
     * @return
     */
    private String sendToServer(String url, JSONObject requestMapping){

        return HttpAdapter.doPostMsg(url,JSONObject.toJSONString(requestMapping));

    }
}
