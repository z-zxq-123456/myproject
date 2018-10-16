package com.dcits.ensemble.om.pm.action.common;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dcits.dynamic.web.mapper.PkList;
import com.dcits.dynamic.web.util.ResourcesUtils;
import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.dynamic.web.util.dao.PkServiceUtil;
import com.dcits.ensemble.om.pm.dao.paraSetting.*;
import com.dcits.ensemble.om.pm.dao.utils.WebBaseDao;
import com.dcits.ensemble.om.pm.module.paraSetting.*;
import com.dcits.ensemble.om.pm.service.CheckAmtExpressionServiceForTableExpressField;
import com.dcits.ensemble.om.pm.util.ParaMeterUtils;
import com.dcits.ensemble.om.pm.util.dao.PkserviceInUtil;
import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.galaxy.core.spring.SpringApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/baseCommon")
public class BaseCommonAction {
    private static final Logger log = LoggerFactory
            .getLogger(BaseCommonAction.class);
    @Resource
    ParaFieldsTableDao paraFieldsTableDao;
    @Resource
    WebBaseDao webBaseDao;
    @Resource
    ParaNamespaceOrgDao paraNamespaceOrgDao;
    @Resource
    ParaDifferenceCheckPublishDao paraDifferenceCheckPublishDao;
    @Resource
    PkServiceUtil serviceUtil;
    @Resource
    PkserviceInUtil pkserviceInUtil;
@Resource
    ParaUserAuthorityDao paraUserAuthorityDao;


    @RequestMapping("saveDataForDelUpdIns")
    @Transactional
    public void saveDataForMultipleTable(HttpServletRequest request, PrintWriter printWriter) {
        try {
            String paraJson = request.getParameter("paraJson");
            if (paraJson == null || paraJson.isEmpty()) {
                System.out.println("JSP传送的数据异常!");
                ActionResultWrite.setErrorResult(printWriter, "传送的数据异常!");
                return;
            }
            JSONObject jsonObject = JSON.parseObject(paraJson);
            String userId = ResourcesUtils.getSystemUser(request);
            String ipAddress = ResourcesUtils.getClientIP(request);
            if (ParaMeterUtils.addModifyDeleteMutiRecords(jsonObject, userId, ipAddress)) {
                ActionResultWrite.setsuccessfulResult(printWriter);
            }
        } catch (GalaxyException e) {
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter, "数据操作异常！");
        }

    }

    @RequestMapping("getList")
    public void find(HttpServletRequest request, PrintWriter printWriter) {
        List<Map<String, Object>> maps;
        Map<String, String> map = new HashMap<String, String>();
        JSONObject resultJson = new JSONObject();
        String tableName = request.getParameter("tableName");
        String reqNum = request.getParameter("reqNum");
        ParaNamespaceOrg paraNamespaceOrg = ParaMeterUtils.getSqlExecuteInfo(tableName, "");
        if (paraNamespaceOrg == null) {
            resultJson.put("data", new JSONArray());
            printWriter.print(resultJson.toJSONString());
            printWriter.flush();
            printWriter.close();
            return;
        }
        Map<String, Integer> appHead = ActionResultWrite.getPaginationNumber(request);
        if ( appHead != null) {//请根据业务编写服务器分页的代码,
            //服务器分页时：设置PGUP_OR_PGDN（上翻/下翻标志）为１，TOTAL_FLAG（汇总标志）为Y.
            int totalNum = appHead.get("TOTAL_NUM"), currentNum = appHead.get("CURRENT_NUM");//获得本页记录总数,当前记录号
            //ActionResultWrite.setPaginationQueryDataResult(request,printWriter,dataList,TOTAL_ROWS);
            return;
        }
        //systemId = paraNamespaceOrg.getSystemId();
        map.put("baseSpace", paraNamespaceOrg.getNamespaceName());
        map.put("tableName", tableName);
        try {
            maps = webBaseDao.selectByTableName(map);
            if (maps == null) {
                resultJson.put("data", new JSONArray());
                printWriter.print(resultJson.toJSONString());
                printWriter.flush();
                printWriter.close();
                return;
            }
            resultJson.put("data", maps);
            String out = JSON.toJSONString(resultJson);
            printWriter.write(out);
            printWriter.flush();
            printWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter, "数据操作错误");
        }
    }

    @RequestMapping("submitParaData")
    @Transactional
    public void submitParaData(HttpServletRequest request, PrintWriter printWriter) {
        String transactionId = request.getParameter("tableName");
        try {
            String reqNum = request.getParameter("reqNum");
            String userId = ResourcesUtils.getSystemUser(request);
            String ipAddress = ResourcesUtils.getClientIP(request);
            String systemId  = request.getParameter("systemId");
            ParaUserAuthority paraUserAuthority = paraUserAuthorityDao.selectRightByuserId(userId);
            if(!(paraUserAuthority!=null && paraUserAuthority.getAuthEntering().equals("Y")))
            {
                ActionResultWrite.setErrorResult(printWriter, "无数据操作权限！");
                return;
            }
            if(systemId==null||"".equals(systemId)){
                systemId = "";
            }
            if (ParaMeterUtils.changeFlowToEditComplete(userId, ipAddress, reqNum, transactionId, systemId)) {
                ActionResultWrite.setsuccessfulResult(printWriter);
            } else {
                ActionResultWrite.setErrorResult(printWriter, "参数数据错误，请检查参数设置信息！");
            }
        } catch (GalaxyException e) {
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter, "操作异常！");
        }

    }

    @RequestMapping("nullifyParaData")
    @Transactional
    public void nullifyParaData(HttpServletRequest request, PrintWriter printWriter) {
        String tableName = request.getParameter("transactionId");
        String reqNum = request.getParameter("reqNum");
        String userId = ResourcesUtils.getSystemUser(request);
        String ipAddress = ResourcesUtils.getClientIP(request);
        try {
            String systemId = request.getParameter("systemId");
            String retMsg = ParaMeterUtils.nullifyDataFromTbs(tableName, reqNum, systemId, userId, ipAddress);
            if (retMsg.equals("Success")) {
                ActionResultWrite.setsuccessfulResult(printWriter);
            } else {
                ActionResultWrite.setErrorResult(printWriter, retMsg);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter, "数据操作错误");
        }
    }

    @RequestMapping("updateAndInsertForSave")
    @Transactional
    public void updateAndInsertForSave(HttpServletRequest request, PrintWriter printWriter) {
        try {
            String paraJson = request.getParameter("paraJson");
            if (paraJson == null || paraJson.isEmpty()) {
                System.out.println("JSP传送的数据异常!");
                ActionResultWrite.setErrorResult(printWriter, "传送的数据异常!");
                return;
            }
            String userId = ResourcesUtils.getSystemUser(request);
            String ipAddress = ResourcesUtils.getClientIP(request);
            ParaUserAuthority paraUserAuthority = paraUserAuthorityDao.selectRightByuserId(userId);
            if(!(paraUserAuthority!=null && paraUserAuthority.getAuthEntering().equals("Y")))
            {
                ActionResultWrite.setErrorResult(printWriter, "无数据操作权限！");
                return;
            }
            JSONObject jsonObject = JSON.parseObject(paraJson);
            if (jsonObject != null) {
                String retMsg = ParaMeterUtils.updateDataForCommon(jsonObject, 2, userId, ipAddress);
                if (retMsg.equals("Success")) {
                    ActionResultWrite.setsuccessfulResult(printWriter);
                } else {
                    ActionResultWrite.setErrorResult(printWriter, retMsg);
                }
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        ActionResultWrite.setErrorResult(printWriter, "数据操作异常退出");
    }


    @RequestMapping("updateAndInsertForUpdate")
    @Transactional
    public void updateAndInsertForUpdate(HttpServletRequest request, PrintWriter printWriter) {
        try {
            String paraJson = request.getParameter("paraJson");
            if (paraJson == null || paraJson.isEmpty()) {
                System.out.println("JSP传送的数据异常!");
                ActionResultWrite.setErrorResult(printWriter, "传送的数据异常!");
                return;
            }
            String userId = ResourcesUtils.getSystemUser(request);
            String ipAddress = ResourcesUtils.getClientIP(request);
            ParaUserAuthority paraUserAuthority = paraUserAuthorityDao.selectRightByuserId(userId);
            if(!(paraUserAuthority!=null && paraUserAuthority.getAuthEntering().equals("Y")))
            {
                ActionResultWrite.setErrorResult(printWriter, "无数据操作权限！");
                return;
            }
            JSONObject jsonObject = JSON.parseObject(paraJson);
            if (jsonObject != null) {
                String retMsg = ParaMeterUtils.updateDataForCommon(jsonObject, 0, userId, ipAddress);
                if (retMsg.equals("Success")) {
                    ActionResultWrite.setsuccessfulResult(printWriter);
                } else {
                    ActionResultWrite.setErrorResult(printWriter, retMsg);
                }
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ActionResultWrite.setErrorResult(printWriter, "数据操作异常退出");
    }

    @RequestMapping("updateForDelete")
    @Transactional
    public void updateForDelete(HttpServletRequest request, PrintWriter printWriter) {
        try {
            String paraJson = request.getParameter("paraJson");
            if (paraJson == null || paraJson.isEmpty()) {
                System.out.println("数据异常!");
                ActionResultWrite.setErrorResult(printWriter, "数据操作错误");
                return;
            }
            JSONObject jsonObject = JSON.parseObject(paraJson);
            String tableName = (String) jsonObject.get("tableName");
            String systemId = (String) jsonObject.get("systemId");
            String reqNum = (String) jsonObject.get("reqNum");
            String userId = ResourcesUtils.getSystemUser(request);
            String ipAddress = ResourcesUtils.getClientIP(request);
            JSONObject keyFieldsobj = (JSONObject) jsonObject.get("key");
            String operateType = jsonObject.get("status").toString();
            ParaUserAuthority paraUserAuthority = paraUserAuthorityDao.selectRightByuserId(userId);
            if(!(paraUserAuthority!=null && paraUserAuthority.getAuthEntering().equals("Y")))
            {
                ActionResultWrite.setErrorResult(printWriter, "无数据操作权限！");
                return;
            }
            String retMsg = ParaMeterUtils.deleteTableDataAndFlowStatus(tableName, systemId, reqNum, 0, operateType, keyFieldsobj, userId, ipAddress);
            if (retMsg.equals("Success")) {
                ActionResultWrite.setsuccessfulResult(printWriter);
            } else {
                ActionResultWrite.setErrorResult(printWriter, retMsg);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter, "数据操作错误");
        }
    }

    //发布
    @RequestMapping("publishSuccess")
    @Transactional
    public void publishSuccess(HttpServletRequest request, PrintWriter printWriter, ParaApplyCheckPublish info) {
        try {
            StringBuffer publishLog = new StringBuffer("发布数据成功");
            String fullTableName = request.getParameter("transactionId");
            if (fullTableName.indexOf("_") <= 0) {
                ActionResultWrite.setErrorResult(printWriter, "交易数据异常,请检查发布的参数！");
                return;
            }
            String systemId = fullTableName.substring(0, fullTableName.indexOf("_"));
            String tableName = fullTableName.substring(fullTableName.indexOf("_") + 1);
            String reqNo = info.getReqNo();
            if (StringUtils.isEmpty(reqNo)) {
                ActionResultWrite.setErrorResult(printWriter, "交易数据异常,请检查发布的参数！");
                return;
            }
            info.setCurrentsystemTime(ResourcesUtils.getCurrentSystemTime());
            info.setOperatorId(ResourcesUtils.getSystemUser(request));
            info.setClientIp(ResourcesUtils.getClientIP(request));
            info.setTranTimestamp(ResourcesUtils.getTimeStampt());
            info.setApprove("Y");
            info.setOperatorType("P");
            if (!ParaMeterUtils.checkIfSamePublisher(info)) {
                ActionResultWrite.setErrorResult(printWriter, "发布不能与复核是同一人,不能发布数据");
                return;
            }
            String url = request.getParameter("envUrl");
            ParaNamespaceOrg paraNamespaceOrg = new ParaNamespaceOrg();
            paraNamespaceOrg.setTransactionId(tableName);
            paraNamespaceOrg.setSystemId(systemId);
            paraNamespaceOrg = paraNamespaceOrgDao.selectDataByDbPrimaryKey(paraNamespaceOrg);
            JSONObject sysHead = ParaMeterUtils.getSysHeadForPublish(request);
            String nameSpace = paraNamespaceOrg.getNamespaceName();
            if (paraNamespaceOrg.getIsTbname().equals("Y")) {
                String logStr = ParaMeterUtils.publishSingleTransactionData(fullTableName, url, info, sysHead, nameSpace);
                publishLog = new StringBuffer(logStr);
            } else {
                ParaTransactionTableOrgDao paraTransactionTableOrgDao = SpringApplicationContext.getContext().getBean(ParaTransactionTableOrgDao.class);
                List<ParaTransactionTableOrg> paraTransactionTableOrgList = paraTransactionTableOrgDao.selectListByBusiness(reqNo);
                if (paraTransactionTableOrgList.size() <= 0) {
                    ActionResultWrite.setErrorResult(printWriter, "交易数据异常,请检查发布的参数！");
                    return;
                }
                publishLog = new StringBuffer();
                for (ParaTransactionTableOrg paraTransactionTableOrg : paraTransactionTableOrgList) {
                    String retMsg = "";
                    info.setReqNo(paraTransactionTableOrg.getSubReqNo());
                    retMsg = ParaMeterUtils.publishSingleTransactionData(paraTransactionTableOrg.getTransactionId(), url, info, sysHead, nameSpace);
                    if (retMsg.contains("发布数据成功")) {
                        if (!retMsg.equals("发布数据成功")) {
                            publishLog.append(retMsg);
                        }
                        if(!retMsg.contains("发布数据到服务器失败")) {
                            paraTransactionTableOrgDao.deleteByPrimaryKey(paraTransactionTableOrg);
                        }
                    } else {
                        publishLog.append(retMsg);
                        break;
                    }
                }
                if (publishLog.length()<=0) {
                    publishLog = new StringBuffer("发布数据成功");
                    ParaCircleFlow paraCircleFlow = ParaMeterUtils.setStatusByAppNo(reqNo, "publisSuccess", false);
                    if (paraCircleFlow == null) {
                        if (log.isErrorEnabled()) {
                            log.error(tableName + "表,本地不能更新发布数据的状态,请手工更新本地数据库表的状态为已发布。发布数据到服务器成功!");
                        }
                        info.setCheckText(info.getCheckText() + "\r\n此数据已经完成发布！不能更新本地发布数据的状态,\r\n已经发布数据到远程服务器，无需发布数据!");
                        publishLog.append( "\r\n无法更新本地数据库发布数据的状态!");
                    }
                    info.setReqNo(reqNo);
                    if (!ParaMeterUtils.addFlowLogByAppNo(info)) {
                        publishLog.append("\r\n无法插入本地流程日志数据库");
                    }
                }
            }
            if (publishLog.toString().contains("发布数据成功")) {
                if (publishLog.toString().equals("发布数据成功")) {
                    ActionResultWrite.setsuccessfulResult(printWriter);
                } else {
                    ActionResultWrite.setsuccessfulResult(printWriter, publishLog.toString());
                }
            } else if (publishLog.toString().contains("发布数据到服务器失败")) {
                ActionResultWrite.setErrorResult(printWriter, publishLog.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter, "操作异常，不能发布数据");
        }
    }

    //驳回
    @RequestMapping("rejectFromCheckOrPublish")
    @Transactional
    public void rejectFromCheckOrPublish(HttpServletRequest request, PrintWriter printWriter, ParaApplyCheckPublish info) {
        info.setCurrentsystemTime(ResourcesUtils.getCurrentSystemTime());
        info.setOperatorId(ResourcesUtils.getSystemUser(request));
        info.setClientIp(ResourcesUtils.getClientIP(request));
        info.setTranTimestamp(ResourcesUtils.getTimeStampt());
        info.setApprove("N");
        try {
            /*if (ParaMeterUtils.setStatusByAppNo(info.getReqNo(), "rejectForWrongData") == null) {
                ActionResultWrite.setErrorResult(printWriter, "不能驳回数据");
                return;
            }
            if (!ParaMeterUtils.addFlowLogByAppNo(info)) {
                ActionResultWrite.setsuccessfulResult(printWriter, "驳回数据成功！\r\n无法插入本地流程日志数据库");
            }*/
            if (ParaMeterUtils.changeFlowStatusForMultipleTable("rejectForWrongData", info)) {
                ActionResultWrite.setsuccessfulResult(printWriter);
            } else {
                ActionResultWrite.setErrorResult(printWriter, "不能驳回数据");
            }
        } catch (Exception e) {
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter, "数据操作错误");
        }
    }

    //复核通过
    @RequestMapping("checkSuccess")
    @Transactional
    public void checkSuccess(HttpServletRequest request, PrintWriter printWriter, ParaApplyCheckPublish info) {
        info.setCurrentsystemTime(ResourcesUtils.getCurrentSystemTime());
        info.setOperatorId(ResourcesUtils.getSystemUser(request));
        info.setClientIp(ResourcesUtils.getClientIP(request));
        info.setTranTimestamp(ResourcesUtils.getTimeStampt());
        info.setApprove("Y");
        info.setOperatorType("C");
        try {
            /*if (ParaMeterUtils.setStatusByAppNo(info.getReqNo(), "checkSuccess") == null) {
                ActionResultWrite.setErrorResult(printWriter, "复核失败：参数或者流程数据异常，\r\n请驳回申请单，重新申请或录入！");
                return;
            }

            if (!ParaMeterUtils.addFlowLogByAppNo(info)) {
                ActionResultWrite.setsuccessfulResult(printWriter, "复核数据成功！\r\n无法插入本地流程日志数据库！");
            }*/
            if (ParaMeterUtils.changeFlowStatusForMultipleTable("checkSuccess", info)) {
                ActionResultWrite.setsuccessfulResult(printWriter);
            } else {
                ActionResultWrite.setErrorResult(printWriter, "不能驳回数据");
            }
        } catch (Exception e) {
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter, "数据操作错误");
        }
    }

    //对比差异
    @RequestMapping("contrastBase")
    public void contrastBase(HttpServletRequest request, PrintWriter printWriter) {
        JSONObject resultJson = new JSONObject();
        String tableName = request.getParameter("tableName");
        String reqNum = request.getParameter("reqNum");
        List<Map<String, Object>> maps;

        if (tableName == null || "null".equals(tableName)) {
            ActionResultWrite.setErrorResult(printWriter, "数据操作错误");
            return;
        }
        String systemId = request.getParameter("systemId");
        try {
            ParaNamespaceOrg paraNamespaceOrg = ParaMeterUtils.getSqlExecuteInfo(tableName, systemId);
            if (paraNamespaceOrg == null) {
                ActionResultWrite.setErrorResult(printWriter, "数据操作错误");
                return;
            }
            String fullTableName = paraNamespaceOrg.getSystemId() + "_" + tableName;
            if (StringUtils.isEmpty(reqNum)) {
                ParaCircleFlow paraCircleFlow = ParaMeterUtils.getAppNoByTable(fullTableName, "getReq", null, null, "", "", "");
                if (paraCircleFlow == null) {
                    ActionResultWrite.setErrorResult(printWriter, "数据操作错误");
                    return;
                }
                reqNum = paraCircleFlow.getReqNo();
            }
            maps = ParaMeterUtils.getDataFieldsForComparing(fullTableName, reqNum);
            resultJson.put("data", maps);
            String out = JSON.toJSONString(resultJson);
            //System.out.println("out = " + out);
            printWriter.write(out);
            printWriter.flush();
            printWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter, "数据操作错误");
        }
    }

    //PKlist
    @RequestMapping("pklistBase")
    public void pklistBase(HttpServletRequest request, PrintWriter printWriter) {
        HashMap<String, String> map = new HashMap<>();
        String value, tableName, systemId;
        JSONObject keyFieldsobj;
        HashMap<String, Object> keyMap = new HashMap<>();
        String[] primaryFieldsName = null;
        try {
            String paraJson = request.getParameter("paraJson");
            if (paraJson == null || paraJson.isEmpty()) {
                value = request.getParameter("tableCol");
                tableName = request.getParameter("tableName");
                systemId = request.getParameter("systemId");
                if (value.contains(",")) {
                    map.put("value", value.substring(0, value.indexOf(",")));
                    map.put("valueDesc", value.substring(value.indexOf(",") + 1));
                } else {
                    map.put("value", value);
                    map.put("valueDesc", value);
                }
            } else {
                JSONObject jsonObject = JSON.parseObject(paraJson);
                tableName = (String) jsonObject.get("tableName");
                systemId = (String) jsonObject.get("systemId");
                value = (String) jsonObject.get("tableCol");
                keyFieldsobj = (JSONObject) jsonObject.get("key");
                if (keyFieldsobj != null) {
                    keyMap.putAll(keyFieldsobj);
                }
                primaryFieldsName = new String[keyMap.size()];
                Set<Map.Entry<String, Object>> arrayFiledName = keyMap.entrySet();
                Iterator<Map.Entry<String, Object>> iteArrayField = arrayFiledName.iterator();
                int i = 0;
                while (iteArrayField.hasNext()) {
                    Map.Entry<String, Object> arrayFiledObject = iteArrayField.next();
                    String strKey = arrayFiledObject.getKey();
                    primaryFieldsName[i++] = strKey;
                }
                keyMap.put("value", value.substring(0, value.indexOf(",")));
                keyMap.put("valueDesc", value.substring(value.indexOf(",") + 1));
            }

            ParaNamespaceOrg paraNamespaceOrg = ParaMeterUtils.getSqlExecuteInfo(tableName, systemId);
            if (null == paraNamespaceOrg) {
                List<PkList> pklist = new ArrayList<>();
                printWriter.print(JSON.toJSONString(pklist));
                printWriter.flush();
                printWriter.close();
                return;
            }
            List<PkList> pklist = new ArrayList<>();
            if (paraJson == null && keyMap.size() <= 0) {
                map.put("baseSpace", paraNamespaceOrg.getNamespaceName());
                map.put("tableName", tableName);
                pklist = webBaseDao.selectParameterPklist(map);
            } else if (keyMap.size() > 0) {
                keyMap.put("baseSpace", paraNamespaceOrg.getNamespaceName());
                keyMap.put("tableName", tableName);
                keyMap.put("pks", primaryFieldsName);
                pklist = webBaseDao.selectParameterPklistWithWhere(keyMap);
            }
            printWriter.print(JSON.toJSONString(pklist));
            printWriter.flush();
            printWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter, "数据操作错误");
        }
    }

    //带条件查询
    @RequestMapping("selectBase")
    public void selectBase(HttpServletRequest request, PrintWriter printWriter) {
        try {
            JSONObject resultJson = new JSONObject();
            HashMap<String, String> map = new HashMap<>();
            String tableName = request.getParameter("tableName");
            if (request.getParameter("col1").trim().length() > 0) {
                map.put("col1", request.getParameter("col1"));
                if (request.getParameter("colV1") != null && request.getParameter("colV1").trim().length() > 0) {
                    map.put("colV1", request.getParameter("colV1"));
                }
            }
            if (request.getParameter("col2").trim().length() > 0) {
                map.put("col2", request.getParameter("col2"));
                if (request.getParameter("colV2") != null && request.getParameter("colV2").trim().length() > 0){
                    map.put("colV2", request.getParameter("colV2"));
                }
            }
            if (request.getParameter("col3").trim().length() > 0) {
                map.put("col3", request.getParameter("col3"));
                if (request.getParameter("colV3") != null && request.getParameter("colV3").trim().length() > 0){
                    map.put("colV3", request.getParameter("colV3"));
                }
            }
            ParaNamespaceOrg paraNamespaceOrg = new ParaNamespaceOrg();
            paraNamespaceOrg.setTransactionId(tableName);
            paraNamespaceOrg = paraNamespaceOrgDao.selectDataByDbPrimaryKey(paraNamespaceOrg);
            map.put("tableName", tableName);
            map.put("baseSpace", paraNamespaceOrg.getNamespaceName());
            List<Map<String, Object>> dataList = webBaseDao.selectTableAndCol(map);
            if (dataList == null) {
                resultJson.put("data", new JSONArray());
                printWriter.print(resultJson.toJSONString());
                printWriter.flush();
                printWriter.close();
                return;
            }
            resultJson.put("data", dataList);
            String out = JSON.toJSONString(resultJson);
            //System.out.println("out = " + out);
            printWriter.write(out);
            printWriter.flush();
            printWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter, "数据操作错误");
        }
    }


    @Resource
    private CheckAmtExpressionServiceForTableExpressField checkAmtExpressionService;

    @RequestMapping("verifyExpressFormat")
    public void parseExpress(HttpServletRequest request, PrintWriter printWriter) {
        HashMap<String, String> result = new HashMap<>();
        try {
            String expression = request.getParameter("param");

            ParaNamespaceOrg paraNamespaceOrg = new ParaNamespaceOrg();
            paraNamespaceOrg.setTransactionId("FM_REF_CODE");
            //paraNamespaceOrg = paraNamespaceOrgDao.selectDataByDbPrimaryKey(paraNamespaceOrg);
            String tableName = "FM_REF_CODE";
            if (checkAmtExpressionService.execute(expression, tableName)) {
                result.put("status", "y");
                result.put("info", "值正确！");
            } else {
                result.put("status", "n");
                result.put("info", "值错误！");
            }
            printWriter.print(JSON.toJSONString(result));
            printWriter.flush();
            printWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
            result.put("status", "n");
            result.put("info", "系统异常");
            printWriter.print(JSON.toJSONString(result));
            printWriter.flush();
            printWriter.close();
        }
    }

    @RequestMapping("/verifyOneKeyValueRepeat")
    public void verifyOneKeyValueRepeat(HttpServletRequest request, PrintWriter printWriter) {
        HashMap<String, String> result = new HashMap<>();
        try {
            String tableName = request.getParameter("name");
            String dataValue = request.getParameter("param");
            Map<String, Object> fieldsMap = new HashMap<>();
            String systemId = request.getParameter("systemId");
            ParaNamespaceOrg paraNamespaceOrg = ParaMeterUtils.getSqlExecuteInfo(tableName, systemId);
            if (paraNamespaceOrg == null) {
                result.put("status", "n");
                result.put("info", "数据异常！");
                printWriter.print(JSON.toJSONString(result));
                printWriter.flush();
                printWriter.close();
                return;
            }
            systemId = paraNamespaceOrg.getSystemId();
            List<String> keyFields = ParaMeterUtils.getPKCulumnsByTableName(tableName);
            if (keyFields.size() > 1) {
                result.put("status", "y");
                result.put("info", "值正确！");
                printWriter.print(JSON.toJSONString(result));
                printWriter.flush();
                printWriter.close();
                return;
            }
            String[] primaryfieldsName = new String[keyFields.size()];
            int i = 0;
            for (String kf : keyFields) {
                primaryfieldsName[i++] = kf;
            }
            fieldsMap.put(keyFields.get(0), dataValue);
            fieldsMap.put("tableName", tableName);
            fieldsMap.put("baseSpace", paraNamespaceOrg.getNamespaceName());
            fieldsMap.put("pks", primaryfieldsName);
            Map<String, Object> map = webBaseDao.selectDataByPrimaryKey(fieldsMap);
            if (map == null) {
                result.put("status", "y");
                result.put("info", "值正确！");
            } else {
                result.put("status", "n");
                result.put("info", "值重复！");
            }
            printWriter.print(JSON.toJSONString(result));
            printWriter.flush();
            printWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
            result.put("status", "n");
            result.put("info", "系统异常");
            printWriter.print(JSON.toJSONString(result));
            printWriter.flush();
            printWriter.close();
        }
    }

    @RequestMapping("getMaxAutoAddID")
    public void getMaxAutoAddID(HttpServletRequest request, PrintWriter printWriter) {
        int max_req_no = 0;
        int maxId = 0;
        try {
            String fieldName = request.getParameter("fieldName");
            String inqueryCondition = request.getParameter("inqueryCondition");
            String tableName = request.getParameter("tableName");
            String systemId = request.getParameter("systemId");
            ParaNamespaceOrg paraNamespaceOrg = ParaMeterUtils.getSqlExecuteInfo(tableName, systemId);
            if (paraNamespaceOrg == null) {
                printWriter.print(maxId);
                return;
            }
            if (inqueryCondition == null || "null".equals(inqueryCondition)) {
                maxId = pkserviceInUtil.getMaxReqID(max_req_no, tableName, fieldName);
            } else if (StringUtils.isNotEmpty(inqueryCondition)) {
                maxId = pkserviceInUtil.getMaxByCon(tableName, fieldName, inqueryCondition);
            }
            printWriter.print(maxId);
        } catch (Exception e) {
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter, e.getMessage());
        }
        printWriter.flush();
        printWriter.close();
    }

    @RequestMapping("getRuleId")
    public void getRuleId(HttpServletRequest request, PrintWriter printWriter) {
        try {
            String ruleId = ParaMeterUtils.getNumberSeq();
            String jsonStr = JSON.toJSONString(ruleId);
            printWriter.print(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter, e.getMessage());
        }
        printWriter.flush();
        printWriter.close();
    }
}