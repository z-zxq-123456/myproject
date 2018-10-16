package com.dcits.ensemble.om.cmc.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dcits.baixin.msoa.support.MsoaConstants;
import com.dcits.ensemble.om.cmc.constant.CmcConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/4/18
 */
public class DataPostUtil {

    public static final String INSERT = "Insert";
    public static final String UPDATE = "Modify";
    public static final String Delete = "Delete";
    public static final String OPR_ADD = "add";
    public static final String OPR_UPDATE = "update";

    private static Logger logger = LoggerFactory.getLogger(DataPostUtil.class);
    /**
     * 公用参数
     * @param request
     */
    public static void genCommonArgs(JSONObject request){
        request.put("CHECK_OPTION","0000000000");
        request.put("FEE_TYPE","N");
        request.put("FEE_AMT","1");
        request.put("QRY_TYPE",1);//全账户查询
        request.put("CHANNEL_TYPE",9999);//渠道
        request.put("USER_LANG","CHINESE");
    }

    /**
     * 生成金融网关Head
     * @param request
     */
    private static void genHead(JSONObject request){

        Map sysHead = (Map) request.get(MsoaConstants.SYS_HEAD);
        sysHead.put("BRANCH_ID","900001");
        sysHead.put("USER_ID","cac00001");
        sysHead.put("PRIORITY",0);
        sysHead.put("SOURCE_TYPE","5005");
        sysHead.put("SYS_DATE","20220401");
        sysHead.put("SOURCE_SERVICE_ID", CmcConstant.SOURCE_SERVICE_ID);
        sysHead.put("SYS_TIMESTAMP", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        sysHead.put("SUB_TRANS_SEQ","10000000000000000000");
        sysHead.put("TRANS_ID",genTransId("cmc"));
    }

    /**
     * 生成随机transID
     * @return
     */
    private static String genTransId(String system){

       String unique = Long.toString(new SnowFlake(MyNetUtils.getInstance().getMacId()%31,0L).nextId());
       TransIDModel model = new TransIDModel.Builder()
               .prefix(new SimpleDateFormat("yyMMdd").format(new Date()))
               .sysflag(system+randomFlg(3,"1"))
               .uniqueKey(unique)
               .build();
       return model.validate(model.toString());
    }

    /**
     * 产生随机传
     * @param count 随机位数
     * @param kind 0 字母  1 数字
     * @return
     */
    private static String randomFlg(int count,String kind){
       Random random = new Random();
       String str="";
           for(int i=0;i<count;i++){
               int num = random.nextInt(3);
               if ("0".equals(kind)){
                   while(!(num==0 || num==1)){
                       num = random.nextInt(3);
                   }
               }
               if ("1".equals(kind)){
                   while(!(num==2)){
                       num = random.nextInt(3);
                   }
               }
               switch(num){
                   case 0:
                       if ("0".equals(kind)){
                           char c1 = (char)(random.nextInt(26)+'a');//生成随机小写字母
                           str += c1;
                       }
                       break;
                   case 1:
                       if ("0".equals(kind)){
                           char c2 = (char)(random.nextInt(26)+'A');//生成随机大写字母
                           str += c2;
                       }
                       break;
                   case 2:
                       if ("1".equals(kind) || kind == null)
                           str += random.nextInt(10);//生成随机数字
                   default:
                       str += "";
               }
           }
        return str;
    }
    /**
     * 生成金融网关Head
     * @param request
     * @param messageCode
     * @param messageType
     * @param serviceCode
     * @param id
     */
    public static void genMosaHead(JSONObject request,String messageCode,String messageType,String serviceCode,String id){

        Map sysHead = (Map) request.get(MsoaConstants.SYS_HEAD);
        if (sysHead==null){

            sysHead = new HashMap();
        }
        if (id != null){
            sysHead.put("ID",id);
        }
        sysHead.put("MESSAGE_CODE",messageCode);
        sysHead.put("MESSAGE_TYPE",messageType);
        sysHead.put("SERVICE_CODE",serviceCode);
        request.put("SYS_HEAD",sysHead);
        genHead(request);
    }

    /**
     * 处理status
     * @param sysHead
     */
    @SuppressWarnings("all")
    public static void dealWithResultStatus(JSONObject sysHead){
        if (sysHead != null)
        {
            String status = sysHead.getString("RET_STATUS");
            JSONArray retResult = sysHead.getJSONArray("RET");
            JSONObject jsonObject = retResult.getJSONObject(0);
            String retCode = jsonObject.getString("RET_CODE");
            String retMsg = jsonObject.getString("RET_MSG");
            if (status != null && "F".equals(status)){ throwErrorMsg(retCode+"  " +retMsg); }
            if (status != null && "S".equals(status)){
                if (logger.isDebugEnabled()){
                    logger.debug(retCode+retMsg);
                }
            }
        }
    }

    /**
     * 处理status  F不抛错
     * @param sysHead
     */
    public static void dealWithResultStatusWithOutException(JSONObject sysHead){
        if (sysHead != null) {
            String status = sysHead.getString("RET_STATUS");
            JSONArray retResult = sysHead.getJSONArray("RET");
            JSONObject jsonObject = retResult.getJSONObject(0);
            String retCode = jsonObject.getString("RET_CODE");
            String retMsg = jsonObject.getString("RET_MSG");
            if (status != null ){
                if (logger.isDebugEnabled()){
                    logger.debug(retCode+retMsg);
                }
            }
        }
    }

    /**
     * throwErrorMsg
     * @param message
     */
    private static void throwErrorMsg(String message){

        throw new RuntimeException(message);
    }


    /**
     * mappingInfo
     * @param target
     * @param pk_field
     * @return
     */
    public static Map<String,Object> mappingInfo(Object target,Set<String> pk_field){

        Map<String,Object> dataMap =new HashMap<>();
        String prodInfoStr =  JSON.toJSONString(target);
        JSONObject inputArguments = JSON.parseObject(prodInfoStr);
        inputArguments.remove("reqNum");
        inputArguments.remove("operateType");
        JSONObject jsonObject = transferJsonToUpperHump(inputArguments);
        Set<String> genSet = jsonObject.keySet();
        Set<String> non_pk_field  = new HashSet<>();
        non_pk_field.addAll(genSet);
        dataMap.put("genList",non_pk_field);
        if (pk_field != null){
            non_pk_field.removeAll(pk_field);
            dataMap.put("keyList",pk_field);
        }
        dataMap.putAll(jsonObject);
        return dataMap;
    }

    /**
     * transferJsonToUpperHump  "aHc-->A_H_C"
     * @param jsonObject
     */
    private static JSONObject transferJsonToUpperHump(JSONObject jsonObject){
        Set keySet =  jsonObject.keySet();
        JSONObject result = new JSONObject();
        for (Object key : keySet){
            String tmpKey = toUpperHump(key);
            Object obj = jsonObject.get(key);
            result.put(tmpKey,obj);
        }
        return result;
    }

    /**
     * toUpperHump
     * @param keyStr
     */
    private static String toUpperHump(Object keyStr){
        String str = (String)keyStr;
        StringBuilder result = new StringBuilder();
        if (str != null && str.length() > 0) {
            result.append(str.substring(0, 1).toUpperCase());
            for (int i = 1; i < str.length(); i++) {
                String s = str.substring(i, i + 1);
                if (s.equals(s.toUpperCase()) && !Character.isDigit(s.charAt(0))) {
                    result.append("_");
                }
                result.append(s.toUpperCase());
            }
        }
        return result.toString();
    }

    /**
     * fillCloseStr
     * @param str
     * @return
     */
    public static String fillCloseStr(String str){
        if (str!=null){
            return "["+str+"]";
        }
        return null;
    }

    /**
     * fillCloseStr
     * @param str
     * @return
     */
    public static String removeCloseStr(String str){
        if (str!=null){
            return str.substring(1,str.length()-1);
        }
        return null;
    }

    public static String getConlumnStatusByDesc(String COLUMN_STATUS){

        if (COLUMN_STATUS == null || COLUMN_STATUS.trim().equals("")){
            throw new RuntimeException("COLUMN_STATUS is allowed null or empty!");
        }
        switch (COLUMN_STATUS){
            case "已发布":
                return "Y";
            case "已录入":
                return "C";
            case "已复核":
                return "W";
            case "已作废":
                return "N";
                default:return null;
        }
    }
}
