package com.dcits.ensemble.om.pm.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dcits.dynamic.web.util.*;
import com.dcits.dynamic.web.util.tools.ToolsConstants;
import com.dcits.ensemble.om.cmc.dao.CmcProductTypeDao;
import com.dcits.ensemble.om.cmc.dbModel.CmcProductType;
import com.dcits.ensemble.om.pm.common.adapter.HttpAdapter;
import com.dcits.ensemble.om.pm.dao.paraSetting.*;
import com.dcits.ensemble.om.pm.dao.utils.WebBaseDao;
import com.dcits.ensemble.om.pm.module.paraSetting.*;
import com.dcits.ensemble.om.pm.dao.paraSetting.ParaUserAuthorityDao;
import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.galaxy.base.util.JsonUtils;
import com.dcits.galaxy.base.util.StringUtils;
import com.dcits.galaxy.core.spring.SpringApplicationContext;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by maruie on 2016/4/1.
 */
public class ParaMeterUtils {

    private static final Logger log = LoggerFactory.getLogger(ParaMeterUtils.class);

    private static String OPERATE = null;
    private static final String TABLES = "CMC_PRODUCT_INFO";

    public static String uploadExcelParaFile(HttpServletRequest request, PrintWriter printWriter) {
        FileItemStream fileStream = null;
        String retState;
        InputStream is = null;
        boolean isAjaxUpload = request.getHeader("X_Requested_With") != null;
        if (!ServletFileUpload.isMultipartContent(request)) {
            retState = new BaseState(false, AppInfo.NOT_MULTIPART_CONTENT).toJSONString();
            printWriter.print(retState);
            printWriter.flush();
            printWriter.close();
            return null;
        }
        ServletFileUpload upload = new ServletFileUpload(
                new DiskFileItemFactory());
        if (isAjaxUpload) {
            upload.setHeaderEncoding("UTF-8");
        }
        try {
            FileItemIterator iterator = upload.getItemIterator(request);
            while (iterator.hasNext()) {
                fileStream = iterator.next();
                if (!fileStream.isFormField())
                    break;
                fileStream = null;
            }
            if (fileStream == null) {
                retState = new BaseState(false, AppInfo.NOTFOUND_UPLOAD_DATA).toJSONString();
                printWriter.print(retState);
                printWriter.flush();
                printWriter.close();
                return null;
            }
            String originFileName = fileStream.getName();
            String fileExt = originFileName.substring(originFileName.lastIndexOf(".") + 1);
            if (!("xls".equals(fileExt))) {
                retState = new BaseState(false, AppInfo.NOT_ALLOW_FILE_TYPE).toJSONString();
                printWriter.print(retState);
                printWriter.flush();
                printWriter.close();
                return null;
            }
            String tmpPath = request.getSession().getServletContext().getRealPath("/") + "/" + ToolsConstants.uploadFolderName + "/tmp";
            String savePath = request.getSession().getServletContext().getRealPath("/") + "/" + ToolsConstants.uploadFolderName + "/" + originFileName;
            if (log.isDebugEnabled())
                log.debug("\n" + savePath);
            // 调用存储类来处理文件存储
            is = fileStream.openStream();
            State storageState = StorageManager.saveFileByInputStream(is, savePath, tmpPath);
            is.close();
            if (storageState.isSuccess()) {
                return savePath;
            }
            State state = new BaseState(false, AppInfo.NOTFOUND_UPLOAD_DATA);
            retState = state.toJSONString();
            printWriter.print(retState);
            printWriter.flush();
            printWriter.close();
            return null;
        } catch (FileUploadException e) {
            retState = new BaseState(false, AppInfo.PARSE_REQUEST_ERROR).toJSONString();
            printWriter.print(retState);
            printWriter.flush();
            printWriter.close();
            e.printStackTrace();
        } catch (IOException e) {
            retState = new BaseState(false, AppInfo.INVALID_ACTION).toJSONString();
            printWriter.print(retState);
            printWriter.flush();
            printWriter.close();
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static String getNumberSeq() {
        String date = DateUtil.formatDate(Calendar.getInstance(), DateUtil.PATTERN_SIMPLE_DATE);
        StringBuilder sb = new StringBuilder();
        sb.append(date);
        sb.append(System.nanoTime());
        int length = 32 - sb.length();
        String randNum = ResourcesUtils.getRandomNumber(length);
        sb.append(randNum);
        return sb.toString();
    }

    public static String getWorkFlowForUser(HttpServletRequest request) {
        String operator = "system";
        String userRight = "4";
        if (request.getSession().getAttribute("UserName") != null) {
            ParaUserAuthorityDao rightDao = SpringApplicationContext.getContext().getBean(ParaUserAuthorityDao.class);
            operator = (String) request.getSession().getAttribute("UserName");
            ParaUserAuthority paraUserAuthority = rightDao.selectRightByuserId(operator);
            if (null == paraUserAuthority) {
                return userRight;
            }
            if (paraUserAuthority.getAuthEntering().equals("Y")) {
                userRight = "5";
            }
            /*if (paraUserAuthority.getAuthCheck().equals("Y")) {
                userRight = "6";
            }
            if (paraUserAuthority.getAuthPublish().equals("Y")) {
                userRight = "7";
            }*/
        }
        return userRight;
    }


    private static boolean addFlowLogByAppNo(ParaCircleFlow info, String operateType, String userId, String ipAddress, String transactionDesc) {
        try {
            String appNo = info.getReqNo();
            ParaApplyCheckPublishDao paraApplyCheckPublishDao = SpringApplicationContext.getContext().getBean(ParaApplyCheckPublishDao.class);
            ParaApplyCheckPublish paraApplyCheckPublish = new ParaApplyCheckPublish();
            paraApplyCheckPublish.setReqNo(appNo);
            paraApplyCheckPublish.setApprove("Y");
            if ("S".equals(operateType) && StringUtils.isNotEmpty(transactionDesc)) {
                paraApplyCheckPublish.setCheckText(info.getTransactionId() + "附属交易的主交易为:" + transactionDesc);
                paraApplyCheckPublish.setApprove("S");
            } else {
                switch (operateType) {
                    case "A":
                        paraApplyCheckPublish.setCheckText("[" + info.getTransactionDesc() + "]交易：已经申请,未提交参数。");
                        break;
                    case "C":
                        paraApplyCheckPublish.setCheckText("[" + info.getTransactionDesc() + "]交易：已经复核（录入复核是同一人）");
                        break;
                    case "F":
                        paraApplyCheckPublish.setCheckText("[" + info.getTransactionDesc() + "]交易：已经作废。");
                        paraApplyCheckPublish.setApprove("C");
                        break;
                    case "E":
                        paraApplyCheckPublish.setCheckText("[" + info.getTransactionDesc() + "交易：已经录入、提交。");
                        paraApplyCheckPublish.setApprove("Y");
                        break;
                }
            }
            paraApplyCheckPublish.setTranTimestamp(ResourcesUtils.getTimeStampt());
            paraApplyCheckPublish.setOperatorType(operateType);
            paraApplyCheckPublish.setOperatorId(userId);
            paraApplyCheckPublish.setClientIp(ipAddress);
            paraApplyCheckPublish.setCurrentsystemTime(ResourcesUtils.getCurrentSystemTime());
            if (log.isDebugEnabled()) {
                log.debug(paraApplyCheckPublish.getReqNo() + "/type：" + paraApplyCheckPublish.getOperatorType() + " comment：" + paraApplyCheckPublish.getCheckText() + " 当前user：" + paraApplyCheckPublish.getOperatorId());
            }
            if (1 == paraApplyCheckPublishDao.insert(paraApplyCheckPublish)) {
                if (log.isDebugEnabled()) {
                    log.debug(info.getTransactionId() + "交易，插入流程日志数据库成功！" + paraApplyCheckPublish.getReqNo());
                }
                return true;
            } else {
                if (log.isErrorEnabled()) {
                    log.error(info.getTransactionId() + "交易：\r\n流程日志数据库操作异常退出!无法插入数据,请改正错误后，重新插入:" + paraApplyCheckPublish.getReqNo());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean addFlowLogByAppNo(ParaApplyCheckPublish info) {
        try {
            if (log.isDebugEnabled()) {
                log.debug(info.getReqNo() + " type：" + info.getOperatorType() + " comment：" + info.getCheckText() + " 当前user：" + info.getOperatorId());
            }
            ParaApplyCheckPublishDao paraApplyCheckPublishDao = SpringApplicationContext.getContext().getBean(ParaApplyCheckPublishDao.class);
            if (1 == paraApplyCheckPublishDao.insert(info)) {
                if (log.isDebugEnabled()) {
                    log.debug("插入流程日志数据库成功！" + info.getReqNo());
                }
                return true;
            } else {
                if (log.isErrorEnabled()) {
                    log.error("\r\n流程日志数据库操作异常退出!无法插入数据,请改正错误后，重新插入:" + info.getReqNo());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean checkIfSamePublisher(ParaApplyCheckPublish info) {
        ParaApplyCheckPublishDao paraApplyCheckPublishDao = SpringApplicationContext.getContext().getBean(ParaApplyCheckPublishDao.class);
        String operaterId = paraApplyCheckPublishDao.checkIfAnotherPublisher(info);
        if (operaterId != null && operaterId.equals(info.getOperatorId())) {
            System.out.print("发布不能与复核是同一人");
            return false;
        } else {
            return true;
        }
    }

    public static boolean checkStatusByAppNo(String appNo) {
        ParaCircleFlowDao paraCircleFlowDao = SpringApplicationContext.getContext().getBean(ParaCircleFlowDao.class);
        ParaCircleFlow info = paraCircleFlowDao.getDataByPrimaryKey(appNo);
        if (info == null) {
            return false;
        }
        String fullTableName = info.getTransactionId();
        String systemId = fullTableName.substring(0, fullTableName.indexOf("_"));
        String tableName = fullTableName.substring(fullTableName.indexOf("_") + 1);
        if (getOperateNumberByTable(tableName, systemId) <= 0) {
            if (log.isErrorEnabled()) {
                log.error("交易数据异常!");
            }
            return false;
        }
        return true;
    }

    public static ParaCircleFlow setStatusByAppNo(String appNo, String apply, boolean checked) {
        try {
            ParaCircleFlowDao paraCircleFlowDao = SpringApplicationContext.getContext().getBean(ParaCircleFlowDao.class);
            ParaCircleFlow info = paraCircleFlowDao.getDataByPrimaryKey(appNo);
            if (info == null) {
                return null;
            }
            String fullTableName = info.getTransactionId();
            String systemId = fullTableName.substring(0, fullTableName.indexOf("_"));
            String tableName = fullTableName.substring(fullTableName.indexOf("_") + 1);
            if (checked && getOperateNumberByTable(tableName, systemId) <= 0) {
                if (log.isErrorEnabled()) {
                    log.error("交易数据异常!");
                }
                return null;
            }

            if ("checkSuccess".equals(apply)) {
                if ("3".equals(info.getCurrentStatus())) {
                    return info;
                }
                info.setCurrentStatus("3");
                if (tableName.equals("CMC_PRODUCT_TYPE")){
                    CmcProductTypeDao productTypeDao = SpringApplicationContext.getContext().getBean(CmcProductTypeDao.class);
                    CmcProductType productType = new CmcProductType();
                    productType.setColumnStatus("W");
                    productType.setReqNum(appNo);
                    productTypeDao.updateByPrimaryKey(productType);
                }
            } else if ("rejectForWrongData".equals(apply)) {
                if ("4".equals(info.getCurrentStatus()) || "5".equals(info.getCurrentStatus())) {
                    if (log.isErrorEnabled()) {
                        log.error(info.getTransactionId() + "交易,不能驳回数据!");
                    }
                    return null;
                }
                if ("6".equals(info.getCurrentStatus())) {
                    return info;
                }
                info.setCurrentStatus("6");
                rollBackForReject(appNo,tableName,fullTableName);
                if (tableName.equals("CMC_PRODUCT_TYPE")){
                    CmcProductTypeDao productTypeDao = SpringApplicationContext.getContext().getBean(CmcProductTypeDao.class);
                    CmcProductType productType = new CmcProductType();
                    productType.setReqNum(appNo);
                    List<CmcProductType> productTypes = productTypeDao.selectByReqNum(productType);
                    if (!(productTypes != null && productTypes.get(0).getColumnStatus().equals("Y"))){
                        productTypeDao.deleteByPrimaryKey(productType);
                    }
                }
            } else if ("publisSuccess".equals(apply)) {
                if ("1".equals(info.getCurrentStatus()) || "5".equals(info.getCurrentStatus())) {
                    if (log.isErrorEnabled()) {
                        log.error(info.getTransactionId() + "交易,不能发布数据!");
                    }
                    return null;
                }
                if ("4".equals(info.getCurrentStatus())) {
                    return info;
                }
                info.setCurrentStatus("4");
                if (tableName.equals("CMC_PRODUCT_TYPE")){
                    CmcProductTypeDao productTypeDao = SpringApplicationContext.getContext().getBean(CmcProductTypeDao.class);
                    CmcProductType productType = new CmcProductType();
                    productType.setReqNum(appNo);
                    if (OPERATE != null && OPERATE.equals("D")){
                        productTypeDao.deleteByPrimaryKey(productType);
                        OPERATE = null;
                    }else {
                        productType.setColumnStatus("Y");
                        productTypeDao.updateByPrimaryKey(productType);
                    }
                }
            } else {
                return null;
            }
            int updateNum = paraCircleFlowDao.updateByPrimaryKey(info);
            if (1 == updateNum) {
                if (log.isDebugEnabled()) {
                    log.debug("更新流程状态数据库成功！" + info.getReqNo());
                }
            } else if (updateNum == 0) {
                if (log.isErrorEnabled()) {
                    log.error("状态:" + info.getCurrentStatus() + "\r\n流程状态数据库无需更新数据，单号：" + info.getReqNo());
                }
                return null;
            } else {
                if (log.isErrorEnabled()) {
                    log.error("\r\n流程状态数据库更新操作异常退出!无法更新状态数据,请改正错误后，重新更新:" + info.getReqNo());
                }
                return null;
            }
            return info;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void  rollBackForReject(String appNo,String tableName,String fullTableName){

        ParaDifferenceCheckPublishDao differenceCheckPublishDao = SpringApplicationContext.getContext().getBean(ParaDifferenceCheckPublishDao.class);
        ParaDifferenceCheckPublish publish = new ParaDifferenceCheckPublish();
        publish.setReqNo(appNo);
        publish.setTableFullName(fullTableName);
        List<ParaDifferenceCheckPublish> paraDifferenceCheckPublishes =  differenceCheckPublishDao.selectAllDataByTbReq(publish);
        if (paraDifferenceCheckPublishes != null && paraDifferenceCheckPublishes.size()>0){
            ParaDifferenceCheckPublish differenceCheckPublish = paraDifferenceCheckPublishes.get(0);
            String operateType = differenceCheckPublish.getOperateType();
            Map<String,Object> params = new HashMap<>();
            params.put("tableName",tableName);
            params.put("baseSpace","com.dcits.ensemble.om.pm.dao.utils.WebBaseDao");
            WebBaseDao webBaseDao = SpringApplicationContext.getContext().getBean(WebBaseDao.class);
            switch (operateType){
                case "I":
                    webBaseDao.deleteForCancel(params);
                    break;
                case "U":
                    webBaseDao.deleteForCancel(params);
                    webBaseDao.updateForCancel(params);
                    break;
                case "D":
                    webBaseDao.updateForCancel(params);
                    break;
                default:
                    break;
            }
        }
    }

    public static ParaCircleFlow getAppNoByTable(String tableName, String apply, String userId, String ipAddress, String status, String transactionDesc, String reqNum) {
        String appNo;
        ParaCircleFlow info;
        ParaCircleFlowDao paraCircleFlowDao = SpringApplicationContext.getContext().getBean(ParaCircleFlowDao.class);
        if (StringUtils.isEmpty(reqNum)) {
            info = paraCircleFlowDao.getTableFinishStatue(tableName);
        } else {
            info = paraCircleFlowDao.getDataByPrimaryKey(reqNum);
        }
        if (info != null) {
            if (apply.isEmpty()) {
                if ((null == userId) && (null == ipAddress) && StringUtils.isNotEmpty(status)) {
                    info.setCurrentStatus(status);
                    paraCircleFlowDao.updateByPrimaryKey(info);
                }
                return info;
            } else if ("cancelTheAction".equals(apply)) {
                info.setCurrentStatus("5");
                paraCircleFlowDao.updateByPrimaryKey(info);
                if (!addFlowLogByAppNo(info, "F", userId, ipAddress, transactionDesc)) {
                    return null;
                }
            } else if ("add".equals(apply)) {
                info.setCurrentStatus("0");
                paraCircleFlowDao.updateByPrimaryKey(info);
                if (!addFlowLogByAppNo(info, "S", userId, ipAddress, transactionDesc)) {
                    return null;
                }
            }
        } else if ("apply".equals(apply)) {
            info = new ParaCircleFlow();
            info.setTransactionDesc(transactionDesc);
            info.setTransactionId(tableName);
            appNo = ResourcesUtils.getDateTimeUuId();
            info.setReqNo(appNo);
            info.setCurrentStatus("1");
            if (1 == paraCircleFlowDao.insert(info)) {
                if (log.isDebugEnabled()) {
                    log.debug(tableName + "，插入流程日志数据库成功！" + info.getReqNo());
                }
            } else {
                if (log.isErrorEnabled()) {
                    log.error(tableName + "交易：\r\n流程日志数据库操作异常退出!无法插入数据,请改正错误后，重新插入:" + info.getReqNo());
                }
                return null;
            }
            addFlowLogByAppNo(info, "A", userId, ipAddress, transactionDesc);
        }
        return info;
    }

    public static List<String> getPKCulumnsByTableName(String tableName) {
        ParaFieldsTableDao paraFieldsTableDao = SpringApplicationContext.getContext().getBean(ParaFieldsTableDao.class);
        return paraFieldsTableDao.getPKCulumnsByTableName(tableName);
    }

    public static List<String> getNonPkCulumnsByTableName(String tableName) {
        ParaFieldsTableDao paraFieldsTableDao = SpringApplicationContext.getContext().getBean(ParaFieldsTableDao.class);
        return paraFieldsTableDao.getNonPkCulumnsByTableName(tableName);
    }

    public static String updateDataForCommon(JSONObject jsonObject, int addFlag, String userId, String ipAddress) {
        String tableName = (String) jsonObject.get("tableName");
        String systemId = (String) jsonObject.get("systemId");
        String reqNum = (String) jsonObject.get("reqNum");
        String operateType = (String) jsonObject.get("status");

        JSONObject keyFieldsobj = (JSONObject) jsonObject.get("key");
        JSONObject generalFieldsobj = (JSONObject) jsonObject.get("general");

        return addModifySingleRecord(tableName, systemId, reqNum, keyFieldsobj, generalFieldsobj, addFlag, operateType, userId, ipAddress);
    }


    public static String updateTableAndFlowStatus(String reqNum, JSONObject keyFieldsobj, JSONObject generalFieldsobj, String nameSpace, String systemId, String tableName, int addFlag, String operateType) {
        WebBaseDao webBaseDao = SpringApplicationContext.getContext().getBean(WebBaseDao.class);
        String fullTableName = systemId + "_" + tableName;
        Map<String, Object> keyMap = new HashMap<>();
        keyMap.putAll(keyFieldsobj);

        Map<String, Object> generalMap = new HashMap<>();
        if (generalFieldsobj.size() > 0) {
            generalMap.putAll(generalFieldsobj);
        }

        Map<String, Object> fieldsMap = new HashMap<>();
        fieldsMap.putAll(keyFieldsobj);
        fieldsMap.putAll(generalFieldsobj);
        fieldsMap.put("tableName", tableName);
        fieldsMap.put("baseSpace", nameSpace);

        String[] primaryfieldsName = new String[keyMap.size()];
        Set<Map.Entry<String, Object>> arrayFieldName;
        Iterator<Map.Entry<String, Object>> iteArrayField;
        int i;

        String keyValue = ParaMeterUtils.checkPrimaryKeyFields(tableName, keyMap, primaryfieldsName);
        if (keyValue == null) {
            return systemId + "系统中字段错误：" + tableName + "交易,主键值设置错误!";
        }

        fieldsMap.put("pks", primaryfieldsName);
        fieldsMap.put("pksV", primaryfieldsName);
        if (generalMap.size() > 0) {
            List<String> generalFields = ParaMeterUtils.getNonPkCulumnsByTableName(tableName);
            String generalStr = generalFields.toString();
            String[] generalFieldsName = new String[generalMap.size()];
            String[] generalFieldsValues = new String[generalMap.size()];
            arrayFieldName = generalMap.entrySet();
            iteArrayField = arrayFieldName.iterator();
            i = 0;
            while (iteArrayField.hasNext()) {
                Map.Entry<String, Object> arrayFiledObject = iteArrayField.next();
                String strKey = arrayFiledObject.getKey();
                if (!generalStr.contains(strKey)) {
                    System.out.println(fullTableName + "交易字段设置错误!" + generalStr + "，未包含字段：" + strKey);
                    //return systemId + "系统中字段错误：" + tableName + "交易字段设置错误!";
                }

                generalFieldsName[i] = strKey;
                generalFieldsValues[i++] = strKey;
            }
            for (int a = 0; a < generalFieldsName.length; a++) {
                if ("".equals(fieldsMap.get(generalFieldsName[a])) || fieldsMap.get(generalFieldsName[a]) == null) {
                    generalFieldsValues[a] = "";
                }
            }
            fieldsMap.put("col", generalFieldsName);
            fieldsMap.put("colV", generalFieldsValues);
        }

        ParaDifferenceCheckPublishDao paraDifferenceCheckPublishDao = SpringApplicationContext.getContext().getBean(ParaDifferenceCheckPublishDao.class);
        ParaDifferenceCheckPublish paraDifferenceCheckPublish = new ParaDifferenceCheckPublish();

        paraDifferenceCheckPublish.setTableFullName(fullTableName);
        paraDifferenceCheckPublish.setTranTimestamp(ResourcesUtils.getTimeStampt());
        byte[] tmp;
        try {
            tmp = keyFieldsobj.toJSONString().getBytes("UTF-8");
            paraDifferenceCheckPublish.setKeyValue(tmp);
            tmp = generalFieldsobj.toJSONString().getBytes("UTF-8");
            paraDifferenceCheckPublish.setDataDui(tmp);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        paraDifferenceCheckPublish.setSeriesNum(BigDecimalUtil.toBigDecimal(1));
        paraDifferenceCheckPublish.setReqNo(reqNum);
        paraDifferenceCheckPublish.setPrimaryKeyvalue(keyValue);
        Map<String, Object> dataMap = webBaseDao.selectDataByPrimaryKey(fieldsMap);
        Map<String, Object> changedData = webBaseDao.selectDataByPKForW(fieldsMap);
        if (addFlag >= 2) {
            if (dataMap != null || changedData != null) {
                //System.out.println(fullTableName + "交易，数据重复");
                return systemId + "系统中," + tableName + "交易，数据重复";
            }
            paraDifferenceCheckPublish.setOperateType("I");
            tmp = new byte[0];
            paraDifferenceCheckPublish.setOlddataUpd(tmp);
            ParaDifferenceCheckPublish oldParaDifference = paraDifferenceCheckPublishDao.selectDifferenceByTbReq(paraDifferenceCheckPublish);
            if (oldParaDifference != null) {
                paraDifferenceCheckPublishDao.removeDataByTbReq(paraDifferenceCheckPublish);
            }
            paraDifferenceCheckPublishDao.insert(paraDifferenceCheckPublish);
            if (generalFieldsobj.size() > 0) {
                webBaseDao.insertNewDataToW(fieldsMap);
            } else {
                webBaseDao.insertForNewDataOnlyKeyValue(fieldsMap);
            }
        } else {
            Map<String, Object> databeforeModify = webBaseDao.selectDataByPrimaryKeyForN(fieldsMap);
            paraDifferenceCheckPublish.setOperateType("U");
            if (databeforeModify == null && changedData != null) {
                tmp = new byte[0];
                paraDifferenceCheckPublish.setOlddataUpd(tmp);
                paraDifferenceCheckPublish.setOperateType("I");
            }
            if (operateType.contains("Y") && dataMap != null) {
                try {
                    String dataStr = JSON.toJSONString(dataMap);
                    System.out.print("JSON DATA:" + JsonUtils.formatJson(dataStr));
                    tmp = dataStr.getBytes("UTF-8");
                    paraDifferenceCheckPublish.setOlddataUpd(tmp);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                paraDifferenceCheckPublishDao.insert(paraDifferenceCheckPublish);
            } else if (operateType.contains("W")||changedData!=null) {
                ParaDifferenceCheckPublish oldParaDifference = paraDifferenceCheckPublishDao.selectDifferenceByTbReq(paraDifferenceCheckPublish);
                if (oldParaDifference == null) {
                    paraDifferenceCheckPublishDao.insert(paraDifferenceCheckPublish);
                } else {
                    paraDifferenceCheckPublish.setTranTimestamp(oldParaDifference.getTranTimestamp());
                    paraDifferenceCheckPublish.setOlddataUpd(oldParaDifference.getOlddataUpd());
                    paraDifferenceCheckPublishDao.updateByPrimaryKey(paraDifferenceCheckPublish);
                }
            }
            if (operateType.contains("W")||changedData!=null) {
                webBaseDao.updateNotCheckDataForW(fieldsMap);
            } else {
                webBaseDao.updateForNewDataToN(fieldsMap);
                if (generalFieldsobj.size() > 0) {
                    webBaseDao.insertNewDataToW(fieldsMap);
                } else {
                    webBaseDao.insertForNewDataOnlyKeyValue(fieldsMap);
                }
            }
        }
        return "Success";
    }


    public static Integer getOperateNumberByTable(String tableName, String systemId) {
        WebBaseDao webBaseDao = SpringApplicationContext.getContext().getBean(WebBaseDao.class);
        ParaNamespaceOrgDao paraNamespaceOrgDao = SpringApplicationContext.getContext().getBean(ParaNamespaceOrgDao.class);
        Map<String, Object> map = new HashMap<>();
        ParaNamespaceOrg paraNamespaceOrg = new ParaNamespaceOrg();
        paraNamespaceOrg.setTransactionId(tableName);
        paraNamespaceOrg.setIsTbname("Y");
        paraNamespaceOrg.setSystemId(systemId);
        paraNamespaceOrg = paraNamespaceOrgDao.selectDataByDbPrimaryKey(paraNamespaceOrg);
        if (tableName.isEmpty() || paraNamespaceOrg.getNamespaceName().isEmpty()) {
            return -1;
        }
        map.put("tableName", tableName);
        map.put("baseSpace", paraNamespaceOrg.getNamespaceName());
        Integer numModify = webBaseDao.selectNumberRyTb(map);
        Integer numDelete = webBaseDao.getDeleteDataByTableName(map);
        if (log.isDebugEnabled())
            log.debug(tableName + " table operate number: " + numModify + " delete number: " + numDelete);
        if (numModify == 1) {
            if (numDelete > 0) {
                numModify = 3;
            }
        }
        if (log.isDebugEnabled()) {
            log.debug(tableName + " table operate number: " + numModify);
        }
        return numModify;
    }

    public static JSONObject sendToServer(String url, JSONObject reqJson) {
        String sendMsg = reqJson.toJSONString();
        if (log.isDebugEnabled())
            log.debug("sendMsg\r\n" + JsonUtils.formatJson(sendMsg));
        String resp = HttpAdapter.doPostMsg(url, sendMsg);
        if (resp.substring(0, 7).contains("ERROR:")) {
            if (log.isErrorEnabled()) {
                log.error("\r\n发送报文错误。" + resp + "\r\n发送的报文是：" + JsonUtils.formatJson(sendMsg));
            }
        }
        if (log.isDebugEnabled())
            log.debug("receiveMsg\r\n" + JsonUtils.formatJson(resp));
        // 回执报文处理
        return JSON.parseObject(resp);
    }


    public static ParaNamespaceOrg getSqlExecuteInfo(String transactionId, String systemId) {
        ParaNamespaceOrgDao paraNamespaceOrgDao = SpringApplicationContext.getContext().getBean(ParaNamespaceOrgDao.class);
        ParaNamespaceOrg paraNamespaceOrg = new ParaNamespaceOrg();
        if (StringUtils.isEmpty(transactionId)) {
            return null;
        }
        if (StringUtils.isNotEmpty(systemId)) {
            paraNamespaceOrg.setSystemId(systemId);
        }
        paraNamespaceOrg.setTransactionId(transactionId);
        paraNamespaceOrg = paraNamespaceOrgDao.selectDataByDbPrimaryKey(paraNamespaceOrg);
        return paraNamespaceOrg;
    }

    public static JSONObject getSysHeadForPublish(HttpServletRequest request) {
        JSONObject sysHeadMsg = new JSONObject();
        //头部数据
        sysHeadMsg.put("AUTH_FLAG", "N");
        sysHeadMsg.put("BRANCH_ID", "900001");
        sysHeadMsg.put("DEST_BRANCH_NO", "900001");
        sysHeadMsg.put("MESSAGE_CODE", request.getParameter("messageCode"));
        sysHeadMsg.put("MESSAGE_TYPE", request.getParameter("messageType"));
        sysHeadMsg.put("SERVICE_CODE", request.getParameter("serviceCode"));
        sysHeadMsg.put("MODULE_ID", request.getParameter("module"));
        sysHeadMsg.put("COMPANY", request.getParameter("legalentity"));
        sysHeadMsg.put("PROGRAM_ID", "LiM8A");
        sysHeadMsg.put("SEQ_NO", "20160330-091459.868");
        sysHeadMsg.put("SERVER_ID", "127.0.0.1");
        sysHeadMsg.put("SOURCE_BRANCH_NO", "0001");
        sysHeadMsg.put("SYSTEM_ID", "114001ABcbsazx20171013");
        sysHeadMsg.put("SOURCE_TYPE", "1003");
        sysHeadMsg.put("TRAN_DATE", "29991018");
        sysHeadMsg.put("TRAN_MODE", "ONLINE");
        sysHeadMsg.put("TRAN_TIMESTAMP", "193713760");
        sysHeadMsg.put("USER_ID", "ADMIN");
        sysHeadMsg.put("USER_LANG", "CHINESE");
        sysHeadMsg.put("WS_ID", "05");
        return sysHeadMsg;
    }


    public static StringBuffer getPkFieldsForPublish(String tableName) {
        //获取主键
        List<String> fieldsPkName = ParaMeterUtils.getPKCulumnsByTableName(tableName);
        if (fieldsPkName == null) {
            return null;
        }
        StringBuffer pkString = new StringBuffer();
        for (String pkName : fieldsPkName) {
            if (pkString.length() > 0) {
                pkString.append("|");
            }
            pkString.append(pkName);
        }
        return pkString;
    }


    public static JSONArray getDataFieldsForPublish(String fullTableName, String reqNo) {
        JSONArray models = new JSONArray();
        ParaDifferenceCheckPublishDao paraDifferenceCheckPublishDao = SpringApplicationContext.getContext().getBean(ParaDifferenceCheckPublishDao.class);
        ParaDifferenceCheckPublish paraDifferenceCheckPublish = new ParaDifferenceCheckPublish();
        paraDifferenceCheckPublish.setReqNo(reqNo);
        paraDifferenceCheckPublish.setTableFullName(fullTableName);
        List<ParaDifferenceCheckPublish> dataList = paraDifferenceCheckPublishDao.selectAllDataByTbReq(paraDifferenceCheckPublish);
        if (dataList != null) {
            byte[] jsonMess, sendJson;
            for (ParaDifferenceCheckPublish publishData : dataList) {
                sendJson = publishData.getDataDui();
                jsonMess = publishData.getKeyValue();
                if (jsonMess == null) {
                    return models;
                }
                try {
                    String jsonStr = new String(jsonMess, "UTF-8");
                    String publishfieldsStr = jsonStr;
                    if (sendJson != null && sendJson.length != 0) {
                        String jsonDataStr = new String(sendJson, "UTF-8");

                        publishfieldsStr = jsonDataStr.substring(0, jsonDataStr.length() - 1)
                                + "," + jsonStr.substring(1);
                    }
                    System.out.print("JSON DATA:" + JsonUtils.formatJson(publishfieldsStr) + "\r\nJSON Key:"
                            + JsonUtils.formatJson(jsonStr));
                    JSONObject jsonData = JSONObject.parseObject(publishfieldsStr);
                    switch (publishData.getOperateType()) {
                        case "I":
                            jsonData.put("OPERATE_TYPE", "SAVE");
                            break;
                        case "U":
                            jsonData.put("OPERATE_TYPE", "UPDATE");
                            break;
                        case "D":
                            if (TABLES.equals(fullTableName.substring(5))){
                                OPERATE = "D";
                            }
                            jsonData.put("OPERATE_TYPE", "DELETE");
                            break;
                        default:
                            jsonData.put("OPERATE_TYPE", "");
                    }
                    models.add(jsonData);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return models;
    }


    public static List<Map<String, Object>> getDataFieldsForComparing(String fullTableName, String reqNo) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        ParaDifferenceCheckPublishDao paraDifferenceCheckPublishDao = SpringApplicationContext.getContext().getBean(ParaDifferenceCheckPublishDao.class);
        ParaDifferenceCheckPublish paraDifferenceCheckPublish = new ParaDifferenceCheckPublish();
        paraDifferenceCheckPublish.setReqNo(reqNo);
        paraDifferenceCheckPublish.setTableFullName(fullTableName);
        List<ParaDifferenceCheckPublish> dataList = paraDifferenceCheckPublishDao.selectAllDataByTbReq(paraDifferenceCheckPublish);
        byte[] jsonMess, sendJson, oldData;
        if (dataList != null) {
            for (ParaDifferenceCheckPublish publishData : dataList) {
                sendJson = publishData.getDataDui();
                jsonMess = publishData.getKeyValue();
                oldData = publishData.getOlddataUpd();
                if (jsonMess == null) {
                    return mapList;
                }
                try {
                    String jsonStr = new String(jsonMess, "UTF-8");
                    String publishfieldsStr = jsonStr;
                    if (sendJson != null) {
                        String jsonDataStr = new String(sendJson, "UTF-8");
                        if (jsonDataStr.length() > 5) {
                            publishfieldsStr = jsonDataStr.substring(0, jsonDataStr.length() - 1)
                                    + "," + jsonStr.substring(1);
                            System.out.print("Record DATA:" + JsonUtils.formatJson(publishfieldsStr));
                        }
                    }

                    System.out.print("\r\nRecord:" + JsonUtils.formatJson(publishfieldsStr) + "\r\nJSON Key:" + JsonUtils.formatJson(jsonStr));
                    JSONObject jsonData = null, oldRecordObj = null;
                    JSONObject newRecordObj = JSONObject.parseObject(publishfieldsStr);
                    if (oldData != null) {
                        String oldRecord = new String(oldData, "UTF-8");
                        System.out.print("OLD DATA:" + JsonUtils.formatJson(oldRecord));
                        oldRecordObj = JSONObject.parseObject(oldRecord);
                    }
                    switch (publishData.getOperateType()) {
                        case "I":
                            jsonData = newRecordObj;
                            jsonData.put("OPERATE_TYPE", "1");
                            break;
                        case "U":
                            if (oldRecordObj != null) {
                                jsonData = getDifferenceForUpdateRecord(oldRecordObj, newRecordObj);
                                jsonData.put("OPERATE_TYPE", "2");
                            }
                            break;
                        case "D":
                            if (oldRecordObj != null) {
                                jsonData = oldRecordObj;
                                jsonData.put("OPERATE_TYPE", "3");
                            }
                            break;
                    }
                    if (jsonData != null) {
                        Map<String, Object> dataMap = new HashMap<>();
                        dataMap.putAll(jsonData);
                        mapList.add(dataMap);
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return mapList;
    }

    public static JSONObject getDifferenceForUpdateRecord(JSONObject oldJson, JSONObject newJson) {
        JSONObject differenceObj = new JSONObject();

        Set<Map.Entry<String, Object>> arrayFieldName = newJson.entrySet();
        if (arrayFieldName.size() > 0) {
            for (Map.Entry<String, Object> arrayFiledObject : arrayFieldName) {
                String strKey = arrayFiledObject.getKey();
                String oldValue = oldJson.getString(strKey);
                StringBuffer newValue = new StringBuffer(arrayFiledObject.getValue().toString());
                if (newValue.length()<=0 && StringUtils.isEmpty(oldValue)) {
                    continue;
                } else {
                    if (newValue.length()<=0 && StringUtils.isNotEmpty(oldValue)) {
                        newValue.append("[").append(oldValue).append("]");
                    } else if (StringUtils.isEmpty(oldValue) && newValue.length()>0) {
                        newValue.append("[ ]");
                    } else if (StringUtils.isNotEmpty(oldValue) && newValue.length()>0 && !newValue.toString().equals(oldValue)) {
                        newValue.append( "[").append(oldValue).append("]");
                    } else if (newValue.length()<=0) {
                        continue;
                    }
                }
                differenceObj.put(strKey, newValue);
            }
        }
        return differenceObj;
    }

    @Transactional
    public static boolean changeFlowToEditComplete(String userId, String ipAddress, String reqNum, String transactionId, String systemId, List<String> talbeNameLists) {
        if (changeFlowToEditComplete(userId, ipAddress, reqNum, transactionId, systemId)) {
            for (String subTransactionId : talbeNameLists) {
                if (!updateCircleFlowAndLog(subTransactionId, systemId, "", userId, ipAddress, transactionId))
                    throw new GalaxyException("更新参数流程数据失败！");
            }
        } else {
            throw new GalaxyException("不能提交参数数据！");
        }
        return true;
    }

    private static boolean updateCircleFlowAndLog(String transactionId, String systemId, String reqNum, String userId, String ipAddress, String mainTransaction) {
        String[] retStrList = verifyDataValid(transactionId, systemId, reqNum, userId, ipAddress);
        //{reqNum, systemId,paraNamespaceOrg.getNamespaceName(),paraNamespaceOrg.getDeleteAuth(),paraNamespaceOrg.getBandEnteringcheck(),paraNamespaceOrg.getIsTbname()};
        reqNum = retStrList[0];
        systemId = retStrList[1];
        String bandEnterCheck = retStrList[4];
        String isTable = retStrList[5];
        if ("Y".equals(isTable)) {
            if (ParaMeterUtils.getOperateNumberByTable(transactionId, systemId) <= 0) {
                System.out.print(transactionId + "交易,未操作数据!");
                throw new GalaxyException(transactionId + "交易,未操作数据!");
            }
        }
        String fullTransactionId = systemId + "_" + transactionId;
        ParaUserAuthorityDao paraUserAuthorityDao = SpringApplicationContext.getContext().getBean(ParaUserAuthorityDao.class);
        ParaUserAuthority paraUserAuthority = new ParaUserAuthority();
        paraUserAuthority.setUserId(userId);
        String authCheck = paraUserAuthorityDao.selectDataByDbPrimaryKey(paraUserAuthority).getAuthCheck();
        if (authCheck == null) {
            throw new GalaxyException("无用户信息，请添加用户！");
        }

        ParaCircleFlow paraCircleFlow;
        if (StringUtils.isEmpty(mainTransaction)) {
            if ("Y".equals(authCheck) && "Y".equals(bandEnterCheck)) {
                paraCircleFlow = getAppNoByTable(fullTransactionId, "", null, null, "3", "", reqNum);
                if (paraCircleFlow != null) {
                    return addFlowLogByAppNo(paraCircleFlow, "C", userId, ipAddress, "");
                }
            } else {
                paraCircleFlow = getAppNoByTable(fullTransactionId, "", null, null, "2", "", reqNum);
                if (paraCircleFlow != null) {
                    return addFlowLogByAppNo(paraCircleFlow, "E", userId, ipAddress, "");
                }
            }
        } else {
            paraCircleFlow = getAppNoByTable(fullTransactionId, "", null, null, "0", "", reqNum);
            if (paraCircleFlow != null) {
                return addFlowLogByAppNo(paraCircleFlow, "S", userId, ipAddress, mainTransaction);
            }
        }
        throw new GalaxyException("无参数流程数据，请重新申请！");
    }

    @Transactional
    public static boolean changeFlowToEditComplete(String userId, String ipAddress, String reqNum, String transactionId, String systemId) {
        if (StringUtils.isEmpty(transactionId) && StringUtils.isEmpty(reqNum)) {
            throw new GalaxyException("交易流程数据不能为空！");
        }
        if (StringUtils.isNotEmpty(reqNum) && StringUtils.isEmpty(transactionId)) {
            ParaCircleFlowDao paraCircleFlowDao = SpringApplicationContext.getContext().getBean(ParaCircleFlowDao.class);
            ParaCircleFlow info = paraCircleFlowDao.getDataByPrimaryKey(reqNum);
            if (info == null) {
                throw new GalaxyException("交易流程数据非法！");
            }
            transactionId = info.getTransactionId();
            transactionId = transactionId.substring(transactionId.indexOf("_") + 1);
        }

        if (updateCircleFlowAndLog(transactionId, systemId, reqNum, userId, ipAddress, ""))
            return true;
        throw new GalaxyException("无流程信息！请重试或重新申请！");
    }


    public static String checkPrimaryKeyFields(String tableName, Map<String, Object> keyMap, String[] primaryfieldsName) {
        List<String> keyFields = ParaMeterUtils.getPKCulumnsByTableName(tableName);
        if (keyFields == null || keyFields.size() != keyMap.size()) {
            System.out.println(tableName + "交易主键值设置错误!");
            return null;//"字段错误："+tableName + "交易主键值设置错误!";
        }
        String pksy = keyFields.toString();

        if (StringUtils.isEmpty(pksy)) {
            System.out.println(tableName + "交易主键值设置错误!");
            return null;//"字段错误："+tableName + "交易主键值设置错误!";
        }
        Set<Map.Entry<String, Object>> arrayFiledName = keyMap.entrySet();
        Iterator<Map.Entry<String, Object>> iteArrayField = arrayFiledName.iterator();
        int i = 0;
        StringBuffer keyValue = new StringBuffer();
        while (iteArrayField.hasNext()) {
            Map.Entry<String, Object> arrayFiledObject = iteArrayField.next();
            String strKey = arrayFiledObject.getKey();
            primaryfieldsName[i++] = strKey;
            keyValue.append(arrayFiledObject.getValue().toString());
            if (!pksy.contains(strKey)) {
                System.out.println(tableName + "交易主键值设置错误!");
                return null;//"字段错误："+tableName + "交易主键值设置错误!";
            }
        }
        return keyValue.toString();
    }

    private static String getTransactionIdBySeqFromFlow(String reqNum) {
        ParaCircleFlowDao paraCircleFlowDao = SpringApplicationContext.getContext().getBean(ParaCircleFlowDao.class);
        return paraCircleFlowDao.selectTransctionByReqNo(reqNum);
    }

    private static String[] verifyDataValid(String tableName, String systemId, String reqNum, String userId, String ipAddress) {
        ParaNamespaceOrg paraNamespaceOrg = ParaMeterUtils.getSqlExecuteInfo(tableName, systemId);
        if (null == paraNamespaceOrg) {
            throw new GalaxyException("数据异常,不能操作数据!交易ID:" + tableName);
        }
        systemId = paraNamespaceOrg.getSystemId();
        String fullTableName = systemId + "_" + tableName;
        ParaNamespaceOrgDao paraNamespaceOrgDao = SpringApplicationContext.getContext().getBean(ParaNamespaceOrgDao.class);
        if (StringUtils.isEmpty(reqNum)) {
            Map<String, String> result = new HashMap<String, String>();
            ParaMeterUtils.getApplicationApproval(result, fullTableName, "view", userId, ipAddress, paraNamespaceOrg.getTransactionDesc(), "");
            if (result.get("retStatus").equals("S")) {
                if (result.get("tableStatus").equals("已复核") || result.get("tableStatus").equals("已录入")) {
                    throw new GalaxyException("请先处理[" + paraNamespaceOrg.getTransactionDesc() + "]交易数据！" + fullTableName);
                }
                if (result.get("tableStatus").equals("未申请")) {
                    getApplicationApproval(result, fullTableName, "apply", userId, ipAddress, paraNamespaceOrg.getTransactionDesc(), "");
                }
            }
        } else {
            String transactionId = getTransactionIdBySeqFromFlow(reqNum);
            if (StringUtils.isNotEmpty(transactionId)) {
                ParaNamespaceOrg overallNameSpace = ParaMeterUtils.getSqlExecuteInfo(transactionId.substring(transactionId.indexOf("_") + 1), transactionId.substring(0, transactionId.indexOf("_")));
                if (null == overallNameSpace) {
                    throw new GalaxyException("数据异常,不能操作数据!交易ID:" + reqNum);
                }
                if (overallNameSpace.getIsTbname().equals("N")) {
                    System.out.print("判断此交易是否能够执行：ID(" + overallNameSpace.getSystemId() + "," + overallNameSpace.getTransactionId() + ") 说明：" + paraNamespaceOrg.getTransactionDesc());
                    if (paraNamespaceOrgDao.getTableForVerifyingToApplication(overallNameSpace) != null) {
                        System.out.print(overallNameSpace.getTransactionId() + "此交易不能执行：ID为" + overallNameSpace.getTransactionId());
                        throw new GalaxyException("不能操作数据！请先发布或作废交易:" + overallNameSpace.getTransactionDesc() + " ID:" + overallNameSpace.getTransactionId());
                    }
                    ParaTransactionTableOrgDao paraTransactionTableOrgDao = SpringApplicationContext.getContext().getBean(ParaTransactionTableOrgDao.class);
                    ParaTransactionTableOrg paraTransactionTableOrg = new ParaTransactionTableOrg();
                    paraTransactionTableOrg.setTransactionId(systemId + "_" + tableName);
                    paraTransactionTableOrg = paraTransactionTableOrgDao.selectDataByDbPrimaryKey(paraTransactionTableOrg);
                    if(paraTransactionTableOrg!=null) {
                        reqNum = paraTransactionTableOrg.getSubReqNo();
                    }
                }
            } else {
                throw new GalaxyException("流程数据异常,不能操作数据!交易ID:" + reqNum);
            }
        }
        System.out.print("判断此交易是否能够执行：ID(" + paraNamespaceOrg.getSystemId() + "," + paraNamespaceOrg.getTransactionId() + ") 说明：" + paraNamespaceOrg.getTransactionDesc());
        if (paraNamespaceOrg.getIsTbname().equals("Y") && paraNamespaceOrgDao.getTableForVerifyingToApplication(paraNamespaceOrg) != null) {
            System.out.print(paraNamespaceOrg.getTransactionId() + "此交易不能执行：ID为" + tableName);
            throw new GalaxyException("不能操作数据！请先发布或作废交易:" + paraNamespaceOrg.getTransactionDesc() + " ID:" + paraNamespaceOrg.getTransactionId());
        }
        systemId = paraNamespaceOrg.getSystemId();
        fullTableName = systemId + "_" + tableName;
        if (StringUtils.isEmpty(reqNum)) {
            ParaCircleFlow paraCircleFlow = ParaMeterUtils.getAppNoByTable(fullTableName, "getReq", null, null, "", "", "");
            if (paraCircleFlow == null) {
                System.out.println(fullTableName + "交易,数据操作错误!");
                throw new GalaxyException(fullTableName + "交易,数据操作错误");
            }
            reqNum = paraCircleFlow.getReqNo();
        }
        return new String[]{reqNum, systemId, paraNamespaceOrg.getNamespaceName(), paraNamespaceOrg.getDeleteAuth(), paraNamespaceOrg.getBandEnteringcheck(), paraNamespaceOrg.getIsTbname()};
    }

    private static String addModifySingleRecord(String tableName, String systemId, String reqNum, JSONObject keyFieldsobj, JSONObject generalFieldsobj, int addFlag, String operateType, String userId, String ipAddress) {
        if (addFlag <= 1) {
            if (StringUtils.isEmpty(operateType)) {
                operateType = "Y";
            }
        } else {
            operateType = "";
        }
        String[] retStrList = verifyDataValid(tableName, systemId, reqNum, userId, ipAddress);
        reqNum = retStrList[0];
        systemId = retStrList[1];
        return updateTableAndFlowStatus(reqNum, keyFieldsobj, generalFieldsobj, retStrList[2], systemId, tableName, addFlag, operateType);
    }


    public static String deleteTableDataAndFlowStatus(String tableName, String systemId, String reqNum, int addFlag, String operateType, JSONObject keyFieldsobj, String userId, String ipAddress) {
        WebBaseDao webBaseDao = SpringApplicationContext.getContext().getBean(WebBaseDao.class);
        ParaDifferenceCheckPublishDao paraDifferenceCheckPublishDao = SpringApplicationContext.getContext().getBean(ParaDifferenceCheckPublishDao.class);
        try {
            String[] retStrList = verifyDataValid(tableName, systemId, reqNum, userId, ipAddress);
            //{reqNum, systemId,paraNamespaceOrg.getNamespaceName(),paraNamespaceOrg.getDeleteAuth(),paraNamespaceOrg.getBandEnteringcheck(),paraNamespaceOrg.getIsTbname()};
            reqNum = retStrList[0];
            systemId = retStrList[1];
            String fullTableName = systemId + "_" + tableName;
            if ("N".equals(retStrList[3])) {
                System.out.println(fullTableName + "交易不能删除数据!");
                return fullTableName + "交易不能删除数据!";
            }
            Map<String, Object> keyMap = new HashMap<>();
            keyMap.putAll(keyFieldsobj);
            String[] primaryfieldsName = new String[keyMap.size()];
            String keyValue = ParaMeterUtils.checkPrimaryKeyFields(tableName, keyMap, primaryfieldsName);
            if (keyValue == null) {
                return fullTableName + "交易,主键值设置错误!";
            }
            keyMap.put("baseSpace", retStrList[2]);
            keyMap.put("tableName", tableName);
            keyMap.put("pks", primaryfieldsName);
            ParaDifferenceCheckPublish paraDifferenceCheckPublish = new ParaDifferenceCheckPublish();
            paraDifferenceCheckPublish.setTableFullName(fullTableName);
            paraDifferenceCheckPublish.setPrimaryKeyvalue(keyValue);
            paraDifferenceCheckPublish.setTranTimestamp(ResourcesUtils.getTimeStampt());
            paraDifferenceCheckPublish.setOperateType("D");
            paraDifferenceCheckPublish.setSeriesNum(BigDecimalUtil.toBigDecimal(1));
            paraDifferenceCheckPublish.setReqNo(reqNum);
            byte[] tmp = keyFieldsobj.toJSONString().getBytes("UTF-8");
            paraDifferenceCheckPublish.setKeyValue(tmp);
            Map<String, Object> dataMap = webBaseDao.selectDataByPrimaryKey(keyMap);
            String dataStr = JSON.toJSONString(dataMap);
            System.out.print("JSON DATA:" + JsonUtils.formatJson(dataStr));
            tmp = dataStr.getBytes("UTF-8");
            paraDifferenceCheckPublish.setOlddataUpd(tmp);
            paraDifferenceCheckPublish.setDataDui(new byte[0]);
            if (operateType.contains("Y")&&dataMap!=null) {
                webBaseDao.updateForNewDataToN(keyMap);
                paraDifferenceCheckPublishDao.insert(paraDifferenceCheckPublish);
            } else {
                webBaseDao.deleteBasebyPk(keyMap);
                paraDifferenceCheckPublishDao.removeDataByTbReq(paraDifferenceCheckPublish);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return e.getLocalizedMessage();
        }
        return "Success";
    }

    @Transactional
    public static boolean addModifyDeleteMutiRecords(JSONObject jsonObject, String userId, String ipAddress) {
        ParaTransactionTableOrgDao paraTransactionTableOrgDao = SpringApplicationContext.getContext().getBean(ParaTransactionTableOrgDao.class);
        ParaCircleFlowDao paraCircleFlowDao = SpringApplicationContext.getContext().getBean(ParaCircleFlowDao.class);

        String tableName, tableFullName;
        try {
            String reqNum = (String) jsonObject.get("reqNum");
            if (StringUtils.isEmpty(reqNum)) {
                throw new GalaxyException("申请流水号不能为空!");
            }
            ParaTransactionTableOrg paraTransactionTableOrg = new ParaTransactionTableOrg();
            ParaCircleFlow paraCircleFlow = new ParaCircleFlow();
            paraTransactionTableOrg.setReqNo(reqNum);
            String systemId = (String) jsonObject.get("systemId");
            JSONArray paraRecordData = (JSONArray) jsonObject.get("paraData");
            if (null == paraRecordData) {
                throw new GalaxyException("JSON数据错误：无参数数据！");
            }
            List<String> talbeNameLists = new ArrayList<>();

            byte[] tmp = jsonObject.toJSONString().getBytes("UTF-8");
            paraCircleFlow.setReqNo(reqNum);
            paraCircleFlow.setMultitbdata(tmp);
            paraCircleFlowDao.updateByPrimaryKey(paraCircleFlow);
            for (Object objPara : paraRecordData) {
                JSONArray recordData;
                JSONObject paraDataObj = (JSONObject) objPara;
                tableName = paraDataObj.getString("tableName");
                Map<String, String> result = new HashMap<String, String>();
                tableFullName = systemId + "_" + tableName;
                ParaNamespaceOrg paraNamespaceOrg = getSqlExecuteInfo(tableName, systemId);
                if (paraNamespaceOrg == null) {
                    throw new GalaxyException("JSON数据错误：交易数据错误！");
                }
                if (StringUtils.isEmpty(tableName)) {
                    throw new GalaxyException("JSON数据错误：交易为空！");
                }
                if (!talbeNameLists.contains(tableName)) {
                    talbeNameLists.add(tableName);
                    paraTransactionTableOrg.setTransactionId(tableFullName);
                    ParaTransactionTableOrg tranTableData = paraTransactionTableOrgDao.selectDataByDbPrimaryKey(paraTransactionTableOrg);
                    if (tranTableData != null) {
                        String retMsg = revertDataForTb(tranTableData.getTransactionId(), tranTableData.getSubReqNo(), "com.dcits.ensemble.om.pm.dao.utils.WebBaseDao", "N", "system", "1.1.1.1");
                        if (retMsg.equals("Success")) {
                            paraTransactionTableOrgDao.deleteByPrimaryKey(tranTableData);
                        } else {
                            throw new GalaxyException(tranTableData.getTransactionId() + "参数数据错误！申请单号：" + reqNum);
                        }
                    }
                    getApplicationApproval(result, tableFullName, "view", userId, ipAddress, paraNamespaceOrg.getTransactionDesc(), "");
                    if (result.get("retStatus").equals("S")) {
                        if (result.get("tableStatus").equals("已复核") || result.get("tableStatus").equals("已录入")) {
                            throw new GalaxyException("请先处理[" + paraNamespaceOrg.getTransactionDesc() + "]交易数据！" + tableFullName);
                        }
                        paraTransactionTableOrg.setTransactionId(tableFullName);
                        if (result.get("tableStatus").equals("未申请")) {
                            getApplicationApproval(result, tableFullName, "apply", "system", "1.1.1.1", paraNamespaceOrg.getTransactionDesc(), "");
                            paraTransactionTableOrg.setReqNo(reqNum);
                            paraTransactionTableOrg.setSubReqNo(result.get("appNo"));
                            paraTransactionTableOrgDao.insert(paraTransactionTableOrg);
                        } else {
                            if (paraTransactionTableOrgDao.selectDataByDbPrimaryKey(paraTransactionTableOrg) == null) {
                                throw new GalaxyException(tableFullName + "参数数据错误！申请单号：" + reqNum);
                            }
                        }
                    }

                    recordData = paraDataObj.getJSONArray("recordData");
                    for (Object objRecordData : recordData) {
                        JSONObject recordDataObj = (JSONObject) objRecordData;
                        String operType = recordDataObj.getString("operType");
                        JSONObject recordKeyFieldsValue, recordGenaralFieldsValue;
                        recordKeyFieldsValue = recordDataObj.getJSONObject("key");
                        if (null == recordKeyFieldsValue) {
                            throw new GalaxyException("JSON数据错误：无参数主键数据！");
                        }
                        String retMsg;
                        switch (operType) {
                            case "Insert":
                                recordGenaralFieldsValue = recordDataObj.getJSONObject("general");
                                retMsg = addModifySingleRecord(tableName, systemId, reqNum, recordKeyFieldsValue, recordGenaralFieldsValue, 2, "", userId, ipAddress);
                                break;
                            case "Modify":
                                recordGenaralFieldsValue = recordDataObj.getJSONObject("general");
                                if (null == recordGenaralFieldsValue) {
                                    throw new GalaxyException("JSON数据错误：无参数数据！");
                                }
                                retMsg = addModifySingleRecord(tableName, systemId, reqNum, recordKeyFieldsValue, recordGenaralFieldsValue, 0, "Y", userId, ipAddress);
                                break;
                            case "Delete":
                                retMsg = ParaMeterUtils.deleteTableDataAndFlowStatus(tableName, systemId, reqNum, 0, "Y", recordKeyFieldsValue, userId, ipAddress);
                                break;
                            default:
                                throw new GalaxyException("无效的操作类型");
                        }
                        if (!retMsg.equals("Success")) {
                            throw new GalaxyException(retMsg);
                        }
                    }
                }
            }
            if (changeFlowToEditComplete(userId, ipAddress, reqNum, "", systemId, talbeNameLists)) {
                return true;
            }
        } catch (GalaxyException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new GalaxyException("JSON数据错误：操作异常！");
    }

    public static String publishSingleTransactionData(String fullTableName, String url, ParaApplyCheckPublish info, JSONObject sysHead, String nameSpace) {
        WebBaseDao webBaseDao = SpringApplicationContext.getContext().getBean(WebBaseDao.class);
        StringBuffer publishLog = new StringBuffer("发布数据成功");
        String tableName = fullTableName.substring(fullTableName.indexOf("_") + 1);
        String reqNo = info.getReqNo();
        JSONObject message = new JSONObject();
        JSONObject bodyMsg = new JSONObject();
        bodyMsg.put("PK_NAME", ParaMeterUtils.getPkFieldsForPublish(tableName));
        bodyMsg.put("TABLE_NAME", tableName);
        bodyMsg.put("MODELS", ParaMeterUtils.getDataFieldsForPublish(fullTableName, reqNo));
        message.put("BODY", bodyMsg);
        message.put("SYS_HEAD", sysHead);

        if (!ParaMeterUtils.checkStatusByAppNo(reqNo)) {
            //ActionResultWrite.setErrorResult(printWriter, "发布数据到服务器失败,无需要发布的参数！");
            return "发布数据到服务器失败,无需要发布的参数！";
        }
        ParaCircleFlow paraCircleFlow = ParaMeterUtils.setStatusByAppNo(reqNo, "publisSuccess", true);
        if (paraCircleFlow == null) {
            //ActionResultWrite.setErrorResult(printWriter, "发布数据到服务器失败,无法更新状态数据,不能发布数据");
            return "发布数据到服务器失败,无法更新状态数据,不能发布数据";
        }

        JSONObject msg = ParaMeterUtils.sendToServer(url, message);
        //报文返还操作
        String status = msg.getJSONObject("SYS_HEAD").getJSONArray("RET").get(0).toString();
        JSONObject statusMsg = JSON.parseObject(status);
        //以下是本地操作
        if ("000000".equals(statusMsg.get("RET_CODE"))) {
            System.out.print("发布数据到服务器成功");
            HashMap<String, Object> map = new HashMap<>();
            map.put("tableName", tableName);
            map.put("baseSpace", nameSpace);
            paraCircleFlow = ParaMeterUtils.setStatusByAppNo(reqNo, "publisSuccess", true);
            if (paraCircleFlow == null) {
                if (log.isErrorEnabled()) {
                    log.error(tableName + "交易,本地不能更新发布数据的状态,请手工更新本地数据库表的状态为已发布。发布数据到服务器成功!");
                }
                info.setCheckText(info.getCheckText() + "\r\n此数据已经完成发布！不能更新本地发布数据的状态,\r\n已经发布数据到远程服务器，无需发布数据!");
                publishLog.append("\r\n无法更新本地数据库发布数据的状态!");
            } else {
                webBaseDao.deleteForSuccess(map);
                webBaseDao.updateForSuccess(map);
            }
            if (!ParaMeterUtils.addFlowLogByAppNo(info)) {
                publishLog.append("\r\n无法插入本地流程日志数据库");
            }
        } else {
            if (log.isErrorEnabled()) {
                log.error(tableName + "交易,发布数据到服务器失败!请重新发布数据!" + statusMsg.toJSONString());
            }
            if (ParaMeterUtils.setStatusByAppNo(info.getReqNo(), "checkSuccess", true) == null) {
                    /*ActionResultWrite.setErrorResult(printWriter, "发布数据到服务器失败!不能更新本地数据状态!");
                    return;*/
                publishLog.append(tableName).append(",发布数据到服务器失败!\r\n").append("不能更新本地数据状态!");
            } else {
                /*ActionResultWrite.setErrorResult(printWriter, tableName + ",发布数据到服务器失败!\r\n"+statusMsg.toJSONString() + "\r\n请重新发布数据!");
                return;*/
                publishLog.append(tableName).append(",发布数据到服务器失败!\r\n").append(statusMsg.toJSONString()).append("\r\n请重新发布数据!");
            }
        }
        return publishLog.toString();
    }

    public static void getApplicationApproval(Map<String, String> result, String tableName, String apply, String userId, String ipAddress, String desc, String reqNo) {
        String appNo = "";
        String tableStatus = "";
        String retMsg = "申请成功";

        ParaCircleFlow info = getAppNoByTable(tableName, apply, userId, ipAddress, "", desc, reqNo);
        if (info == null) {
            appNo = "无申请单号";
            result.put("tableStatus", "未申请");
        } else {
            appNo = info.getReqNo();
            tableStatus = info.getCurrentStatus();
            //申请1，录入2，复核3，发布4，作废5
            switch (tableStatus) {
                case "1":
                    result.put("tableStatus", "已申请");
                    break;
                case "2":
                    result.put("tableStatus", "已录入");
                    break;
                case "3":
                    result.put("tableStatus", "已复核");
                    break;
                case "4":
                    result.put("tableStatus", "已发布");
                    break;
                case "5":
                    result.put("tableStatus", "已作废");
                    break;
                case "6":
                    result.put("tableStatus", "已驳回");
                    break;
                default:
                    result.put("tableStatus", "未申请");
            }
        }
        result.put("retStatus", "S");
        result.put("retMsg", retMsg);
        result.put("appNo", appNo);
        if (log.isDebugEnabled()) {
            log.debug("获取申请号成功！");
        }
    }

    public static boolean changeFlowStatusForMultipleTable(String status, ParaApplyCheckPublish info) {
        ParaTransactionTableOrgDao paraTransactionTableOrgDao = SpringApplicationContext.getContext().getBean(ParaTransactionTableOrgDao.class);
        List<ParaTransactionTableOrg> paraTransactionTableOrgList = paraTransactionTableOrgDao.selectListByBusiness(info.getReqNo());
        boolean checked = true;
        String seqNo = info.getReqNo();
        if (paraTransactionTableOrgList.size() > 0) {
            for (ParaTransactionTableOrg paraTransactionTableOrg : paraTransactionTableOrgList) {
                if (ParaMeterUtils.setStatusByAppNo(paraTransactionTableOrg.getSubReqNo(), status, true) == null) {
                    return false;
                }
                info.setReqNo(paraTransactionTableOrg.getSubReqNo());
                ParaMeterUtils.addFlowLogByAppNo(info);
                resetTransactionTableOrg(null,paraTransactionTableOrg.getSubReqNo());
            }
            checked = false;
        }
        if (ParaMeterUtils.setStatusByAppNo(seqNo, status, checked) == null) {
            return false;
        }
        info.setReqNo(seqNo);
        resetTransactionTableOrg(seqNo,null);
        return ParaMeterUtils.addFlowLogByAppNo(info);
    }

    private static void resetTransactionTableOrg(String reqNum,String subReqNum){

        if (reqNum == null && subReqNum == null){
            throw new RuntimeException("reqNo,subReqNo至少一个不为空！");
        }

        ParaTransactionTableOrg transactionTableOrg = new ParaTransactionTableOrg();
        transactionTableOrg.setReqNo(reqNum);
        transactionTableOrg.setSubReqNo(subReqNum);
        ParaTransactionTableOrgDao tableOrgDao =
                (ParaTransactionTableOrgDao)SpringApplicationContext.getContext().getBean("paraTransactionTableOrgDao");
        tableOrgDao.deleteByBusiness(transactionTableOrg);
    }

    public static String nullifyDataFromTbs(String tableName, String reqNum, String systemId, String userId, String ipAddress) {
        ParaNamespaceOrg paraNamespaceOrg = ParaMeterUtils.getSqlExecuteInfo(tableName, systemId);
        if (paraNamespaceOrg == null) {
            //ActionResultWrite.setErrorResult(printWriter, tableName + "交易,操作异常!");
            return tableName + "交易,操作异常!";
        }
        systemId = paraNamespaceOrg.getSystemId();
        String fullTableName = systemId + "_" + tableName;
        String nameSpace = paraNamespaceOrg.getNamespaceName();
        String retMsg = "";
        if (paraNamespaceOrg.getIsTbname().equals("Y")) {
            retMsg = revertDataForTb(fullTableName, reqNum, nameSpace, "N", userId, ipAddress);
        } else if (paraNamespaceOrg.getIsTbname().equals("N")) {
            retMsg = revertDataForTb(fullTableName, reqNum, nameSpace, "Y", userId, ipAddress);
            ParaTransactionTableOrgDao paraTransactionTableOrgDao = SpringApplicationContext.getContext().getBean(ParaTransactionTableOrgDao.class);
            List<ParaTransactionTableOrg> paraTransactionTableOrgList = paraTransactionTableOrgDao.selectListByBusiness(reqNum);
            if (paraTransactionTableOrgList.size() <= 0) {
                return "Success";
            }
            for (ParaTransactionTableOrg paraTransactionTableOrg : paraTransactionTableOrgList) {
                StringBuffer errorMsg = new StringBuffer();
                retMsg = revertDataForTb(paraTransactionTableOrg.getTransactionId(), paraTransactionTableOrg.getSubReqNo(), nameSpace, "N", userId, ipAddress);
                if (retMsg.equals("Success")) {
                    paraTransactionTableOrgDao.deleteByPrimaryKey(paraTransactionTableOrg);
                } else {
                    errorMsg.append(retMsg);
                }
                if (errorMsg.length() > 0) {
                    retMsg = errorMsg.toString();
                }
            }
        }

        if (!retMsg.equals("Success")) {
            return retMsg;
        }
        return "Success";
    }

    private static String revertDataForTb(String fullTableName, String reqNum, String nameSpace, String revertDb, String userId, String ipAddress) {
        if (StringUtils.isEmpty(reqNum)) {
            ParaCircleFlow paraCircleFlow = ParaMeterUtils.getAppNoByTable(fullTableName, "getReq", null, null, "", "", "");
            if (paraCircleFlow == null) {
                //ActionResultWrite.setErrorResult(printWriter, "数据操作错误");
                return "数据操作错误";
            }
            reqNum = paraCircleFlow.getReqNo();
        }

        if (ParaMeterUtils.getAppNoByTable(fullTableName, "cancelTheAction", userId, ipAddress, "", "", reqNum) == null) {
            System.out.println(reqNum + "交易,不能作废的数据!");
            //ActionResultWrite.setErrorResult(printWriter, tableName + "交易,不能作废数据!");
            return reqNum + "交易,不能作废数据!";
        }
        if (revertDb.equals("N")) {
            String tableName = fullTableName.substring(fullTableName.indexOf("_") + 1);
            ParaDifferenceCheckPublishDao paraDifferenceCheckPublishDao = SpringApplicationContext.getContext().getBean(ParaDifferenceCheckPublishDao.class);
            WebBaseDao webBaseDao = SpringApplicationContext.getContext().getBean(WebBaseDao.class);

            HashMap<String, Object> map = new HashMap<>();
            map.put("baseSpace", nameSpace);
            map.put("tableName", tableName);
            webBaseDao.deleteForCancel(map);
            webBaseDao.updateForCancel(map);
            paraDifferenceCheckPublishDao.deleteByTableReqNo(reqNum, fullTableName);
        }
        return "Success";
    }
}
