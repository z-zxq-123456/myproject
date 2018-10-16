package com.dcits.ensemble.om.cmc.util;

import com.dcits.ensemble.om.cmc.dbModel.*;
import com.dcits.ensemble.om.pf.util.ProdJsonBundlingAndSend;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/9/10
 */
public class LocalMapUtils {

    private static final Set<String> cmc_prod_pk_field = new HashSet<>();
    private static final Set<String> cmc_card_no_role_pk_field = new HashSet<>();
    private static final Set<String> cmc_card_no_role_ex_pk_field = new HashSet<>();
    private static final Set<String> cmc_card_order_info_pk_field = new HashSet<>();
    private static final Set<String> cmc_card_no_param_pk_field = new HashSet<>();
    private static final Set<String> cmc_card_prod_limit_pk_field = new HashSet<>();
    private static final Set<String> cmc_card_prod_channel_pk_field = new HashSet<>();
    static {
        cmc_prod_pk_field.add("CARD_PRODUCT_CODE");
        cmc_card_no_role_pk_field.add("CARD_NO_ROLE_CODE");
        cmc_card_no_role_ex_pk_field.add("PARAM_NAME");
        cmc_card_order_info_pk_field.add("PRODUCT_RULE_NO");
        cmc_card_no_param_pk_field.add("PRODUCT_RULE_NO");

        cmc_card_prod_limit_pk_field.add("CARD_PRODUCT_CODE");
        cmc_card_prod_limit_pk_field.add("CHANNEL_TYPE");
        cmc_card_prod_limit_pk_field.add("CCY");
        cmc_card_prod_limit_pk_field.add("PERIOD");

        cmc_card_prod_channel_pk_field.add("CARD_PRODUCT_CODE");
        cmc_card_prod_channel_pk_field.add("LIMIT_CHANNEL");
        cmc_card_prod_channel_pk_field.add("AUTH_TRAN_TYPE");
    }

    /**
     * 卡信息
     * @param cmcProductInfo
     * @param operType
     * @param reqNum
     */
    public static void mapProdInfo(CmcProductInfo cmcProductInfo,String operType,String reqNum)
            throws Exception{
        Map dataMap = DataPostUtil.mappingInfo(cmcProductInfo,cmc_prod_pk_field);
        dataMap.put("tableName", "CMC_PRODUCT_INFO");
        dataMap.put("operType", operType);
        ProdJsonBundlingAndSend.ProdJsonBundling(reqNum, mapToJson(dataMap));
    }

    /**
     * mapToJson
     * @param map
     * @return
     */
    private static Map<String,String> mapToJson(Map map){

        Map<String,String> dataMap=new HashMap<>();
        Set keyList = (Set) map.get("keyList");
        Set genList = (Set) map.get("genList");
        StringBuilder recordData=new StringBuilder();
        recordData.append("{\"key\":{");
        for (Object key : keyList) {
            if(map.get(key)!=null) {
                recordData.append("\"" + key + "\":\"" + map.get(key) + "\",");
            }
        }
        if(recordData.toString().endsWith(",")) {
            recordData.replace(0, recordData.length(), recordData.substring(0, recordData.length() - 1));
        }
        recordData.append("},");
        if (genList != null &&!"Delete".equals(map.get("operType"))) {
            recordData.append("\"general\":{");
            for (Object gen : genList) {
                if(map.get(gen)!=null)
                    recordData.append("\"" + gen + "\":\"" + map.get(gen) + "\",");
            }
            if(recordData.toString().endsWith(",")) {
                recordData.replace(0, recordData.length(), recordData.substring(0, recordData.length() - 1));
            }
            recordData.append("},");
        }
        recordData.append("\"operType\":\"" + map.get("operType") + "\"}");
        dataMap.put("recordData",recordData.toString());
        dataMap.put("tableName",map.get("tableName").toString());
        return dataMap;
    }

    /**
     * 卡规则信息
     * @param cmcCardNoRoleInfo
     * @param operType
     * @param reqNum
     */
    public static void mapCardNoRoleInfo(CmcCardNoRoleInfo cmcCardNoRoleInfo,String operType,String reqNum){
        Map dataMap = DataPostUtil.mappingInfo(cmcCardNoRoleInfo,cmc_card_no_role_pk_field);
        dataMap.put("tableName", "CMC_CARD_NO_ROLE_INFO");
        dataMap.put("operType", operType);
        ProdJsonBundlingAndSend.ProdJsonBundling(reqNum, mapToJson(dataMap));
    }

    /**
     * 卡渠道
     * @param cmcCardNoRoleEx
     * @param operType
     * @param reqNum
     */
    public static void mapCardNoRoleEx(CmcCardNoRoleEx cmcCardNoRoleEx,String operType,String reqNum){
        Map dataMap = DataPostUtil. mappingInfo(cmcCardNoRoleEx,cmc_card_no_role_ex_pk_field);
        dataMap.put("tableName", "CMC_CARD_NO_ROLE_EX");
        dataMap.put("operType", operType);
        ProdJsonBundlingAndSend.ProdJsonBundling(reqNum, mapToJson(dataMap));
    }

    /**
     * 卡顺序号
     * @param cmcCardOrderNoInfo
     * @param operType
     * @param reqNum
     */
    public static void mapCardOrderNoInfo(CmcCardOrderNoInfo cmcCardOrderNoInfo,String operType,String reqNum){
        Map dataMap =  DataPostUtil.mappingInfo(cmcCardOrderNoInfo,cmc_card_order_info_pk_field);
        dataMap.put("tableName", "CMC_CARD_ORDER_NO_INFO");
        dataMap.put("operType", operType);
        ProdJsonBundlingAndSend.ProdJsonBundling(reqNum, mapToJson(dataMap));
    }

    /**
     * 预制卡
     * @param cmcCardNoParam
     * @param operType
     * @param reqNum
     */
    public static void mapCardNoParam(CmcCardNoParam cmcCardNoParam,String operType,String reqNum){
        Map dataMap = DataPostUtil. mappingInfo(cmcCardNoParam,cmc_card_no_param_pk_field);
        dataMap.put("tableName", "CMC_CARD_NO_PARAM");
        dataMap.put("operType", operType);
        ProdJsonBundlingAndSend.ProdJsonBundling(reqNum, mapToJson(dataMap));
    }

    /**
     * 产品限额
     * @param cmcProductLimit
     * @param operType
     * @param reqNum
     */
    public static void mapCardProdLimit(CmcProductLimit cmcProductLimit,String operType,String reqNum){
        Map dataMap = DataPostUtil.mappingInfo(cmcProductLimit,cmc_card_prod_limit_pk_field);
        dataMap.put("tableName", "CMC_PRODUCT_LIMIT");
        dataMap.put("operType", operType);
        ProdJsonBundlingAndSend.ProdJsonBundling(reqNum, mapToJson(dataMap));
    }

    /**
     * 渠道限制
     * @param cmcCardProductChannel
     * @param operType
     * @param reqNum
     */
    public static void mapCardProdChannel(CmcCardProductChannel cmcCardProductChannel,String operType,String reqNum){
        Map dataMap = DataPostUtil.mappingInfo(cmcCardProductChannel,cmc_card_prod_channel_pk_field);
        dataMap.put("tableName", "CMC_CARD_PRODUCT_CHANNEL");
        dataMap.put("operType", operType);
        ProdJsonBundlingAndSend.ProdJsonBundling(reqNum, mapToJson(dataMap));
    }

    /**
     * 清空数据缓存
     * @param reqNum
     */
    public static void clear(String reqNum){
        List<String> tables = Arrays.asList("CMC_PRODUCT_INFO","CMC_CARD_NO_ROLE_EX","CMC_CARD_PRODUCT_CHANNEL","CMC_PRODUCT_LIMIT");
        ProdJsonBundlingAndSend.clearCache(tables,reqNum);
    }

    /**
     * 校验必输表格信息
     * @param reqNum
     */
    public static void validateTbInfo(String reqNum){
        List<String> tables = Arrays.asList("CMC_PRODUCT_INFO","CMC_CARD_NO_ROLE_EX");
        ProdJsonBundlingAndSend.validate(tables,reqNum);
    }
}
