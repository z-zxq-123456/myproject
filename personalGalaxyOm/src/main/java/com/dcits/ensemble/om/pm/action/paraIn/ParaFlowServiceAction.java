package com.dcits.ensemble.om.pm.action.paraIn;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
//import com.dcits.ensemble.pm.common.ToolsConstants;
import com.dcits.dynamic.web.util.ResourcesUtils;
import com.dcits.dynamic.web.util.action.ShowErrorMsg;
import com.dcits.dynamic.web.util.tools.ToolsConstants;
import com.dcits.ensemble.om.pm.module.paraSetting.ParaCircleFlow;
import com.dcits.ensemble.om.pm.module.paraSetting.ParaUserAuthority;
import com.dcits.ensemble.om.pm.dao.paraSetting.ParaCircleFlowDao;
import com.dcits.ensemble.om.pm.dao.paraSetting.ParaNamespaceOrgDao;
import com.dcits.ensemble.om.pm.dao.paraSetting.ParaUserAuthorityDao;
import com.dcits.ensemble.om.pm.module.paraSetting.ParaNamespaceOrg;
import com.dcits.ensemble.om.pm.module.paraSetting.ParaTableFileds;
import com.dcits.ensemble.om.pm.service.TableHelperChsService;
import com.dcits.ensemble.om.pm.util.ParaMeterUtils;
import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.galaxy.core.spring.SpringApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import com.dcits.ensemble.om.pm.dao.paraSetting.ParaFieldsTableDao;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

@Controller
@RequestMapping("/paraFlowService")
public class ParaFlowServiceAction {
    @Resource
    private ParaCircleFlowDao paraCircleFlowDao;
    @Resource
    private ParaNamespaceOrgDao paraNamespaceOrgDao;
    @Resource
    ParaFieldsTableDao paraFieldsTableDao;
    @Resource
    ParaUserAuthorityDao paraUserAuthorityDao;

    private static final Logger log = LoggerFactory
            .getLogger(ParaFlowServiceAction.class);

    @RequestMapping("/getApplicationNo")
    public void getApplicationNo(HttpServletRequest request, PrintWriter printWriter) {
        String userId = ResourcesUtils.getSystemUser(request);
        String ipAddress = ResourcesUtils.getClientIP(request);
        Map<String, String> result = new HashMap<String, String>();
        try {
            String tableName = request.getParameter("transactionId");
            String apply = request.getParameter("apply");
            String desc = request.getParameter("transactionDesc");
            if (apply == null || apply.isEmpty()) {
                apply = "view";
            }
            if (tableName == null || tableName.isEmpty()) {
                throw new GalaxyException("交易名不能为空！");
            }
            ParaMeterUtils.getApplicationApproval(result, tableName, apply, userId, ipAddress, desc, "");
            printWriter.print(JSON.toJSONString(result));
            printWriter.flush();
            printWriter.close();
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("获取申请号失败！");
            }
            e.printStackTrace();
            result.put("retStatus", "F");
            result.put("retMsg", "获取申请号失败！");
            printWriter.print(JSON.toJSONString(result));
            printWriter.flush();
            printWriter.close();
        }
    }

    @RequestMapping("/getFullInfoForTableOrg")
    public void selectDataByDbPrimaryKey(HttpServletRequest request, PrintWriter printWriter) {
        try {
            String tableName = request.getParameter("transactionId");
            if (StringUtils.isEmpty(tableName)) {
                ActionResultWrite.setErrorResult(printWriter, "交易为空");
                return;
            }
            String systemId = request.getParameter("systemId");
            if (systemId == null || "null".equals(systemId)) {
                systemId = tableName.substring(0, tableName.indexOf("_"));
                tableName = tableName.substring(tableName.indexOf("_") + 1);
            }
            ParaNamespaceOrg paraNamespaceOrg = new ParaNamespaceOrg();
            paraNamespaceOrg.setTransactionId(tableName);
            paraNamespaceOrg.setSystemId(systemId);
            paraNamespaceOrg = paraNamespaceOrgDao.selectDataByDbPrimaryKey(paraNamespaceOrg);
            Map<String, String> result = new HashMap<String, String>();
            result.put("enterOrCheck", "N");
            result.put("deleteAllowed", "N");
            if (paraNamespaceOrg != null) {
                ParaUserAuthority paraUserAuthority = new ParaUserAuthority();
                String roleId = ResourcesUtils.getSystemUser(request);
                paraUserAuthority.setUserId(roleId);
                paraUserAuthority = paraUserAuthorityDao.selectDataByDbPrimaryKey(paraUserAuthority);
                if (paraUserAuthority != null) {
                    String authCheck = paraUserAuthority.getAuthCheck();
                    if (StringUtils.isNotEmpty(authCheck)) {
                        if ("Y".equals(authCheck) && "Y".equals(paraNamespaceOrg.getBandEnteringcheck())) {
                            result.put("enterOrCheck", "Y");
                        }
                    }
                }
                result.put("deleteAllowed", paraNamespaceOrg.getDeleteAuth());
                result.put("jspUrl", paraNamespaceOrg.getJspUrl());
                if (paraNamespaceOrg.getIsTbname().equals("Y")) {
                    result.put("multiDB", "N");
                } else {
                    result.put("multiDB", "Y");
                    result.put("jspViewUrl", paraNamespaceOrg.getJspViewurl());
                }
                result.put("transactionDesc", paraNamespaceOrg.getTransactionDesc());
            }
            if (log.isDebugEnabled()) {
                log.debug("获取权限信息成功！");
            }
            printWriter.print(JSON.toJSONString(result));
            printWriter.flush();
            printWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
            if (log.isErrorEnabled()) {
                log.error("获取权限信息失败！");
            }
            ActionResultWrite.setErrorResult(printWriter, e.getMessage());
        }
    }

    @RequestMapping("/changeStatus")
    public void changeStatus(HttpServletRequest request, PrintWriter printWriter, ParaCircleFlow paraCircleFlow) {
        try {
            paraCircleFlowDao.updateByPrimaryKey(paraCircleFlow);
            ActionResultWrite.setsuccessfulResult(printWriter);
        } catch (Exception e) {
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter, "操作错误，系统异常退出！");
        }
    }

    @RequestMapping("/uploadParaFile")
    @Transactional
    public String uploadFile(HttpServletRequest request, PrintWriter printWriter,
                             MultipartFile sourceFile, String reqNum, String transactionId, String transactionDesc, String systemId) {
        Instant start = Instant.now();
        String errorMsg = "导入失败信息";
        List<Object> titel = new ArrayList<>();
        titel.add(errorMsg);
        List<Object> tableHead = new ArrayList<>();
        List<Object> tableContent = new ArrayList<>();
        tableHead.add(new Object[]{"错误条数", 1});
        tableHead.add(new Object[]{"错误原因", ""});
        int lineNum = -1;

        try {
            ParaUserAuthority paraUserAuthority = paraUserAuthorityDao.selectRightByuserId(ResourcesUtils.getSystemUser(request));
            if (!(paraUserAuthority != null && paraUserAuthority.getAuthEntering().equals("Y"))) {
                ActionResultWrite.setErrorResult(printWriter, "无数据操作权限！");
            }
            String savePath = request.getSession().getServletContext().getRealPath("/") + "/" + ToolsConstants.uploadFolderName + "/" + sourceFile.getOriginalFilename();
            sourceFile.transferTo(new File(savePath));
            JSONObject keyFieldsobj = new JSONObject();
            JSONObject generalFieldsobj = new JSONObject();
            ParaNamespaceOrg paraNamespaceOrg = ParaMeterUtils.getSqlExecuteInfo(transactionId, systemId);
            systemId = paraNamespaceOrg != null ? paraNamespaceOrg.getSystemId() : null;
            String fullTableName = systemId + "_" + transactionId;
            if (StringUtils.isEmpty(reqNum)) {
                ParaCircleFlow paraCircleFlow = ParaMeterUtils.getAppNoByTable(fullTableName, "getReq", null, null, "", "", "");
                reqNum = paraCircleFlow != null ? paraCircleFlow.getReqNo() : null;
            }
            if (StringUtils.isNotEmpty(savePath)) {
                List<List<String>> list = new TableHelperChsService().readExcelDataToPataTable(savePath);
                if (list.size() < 2){
                    throw new GalaxyException(list.get(0).get(0));
                }
                ParaTableFileds paraFieldsTable = new ParaTableFileds();
                paraFieldsTable.setTableName(transactionId);
                List<ParaTableFileds> colList = paraFieldsTableDao.getFieldDataByPKValue(paraFieldsTable);
                List<String> colDescList = paraFieldsTableDao.getCulumnDescByTableName(transactionId);
                if (colList.size() != colDescList.size()) {
                    throw new GalaxyException("数据库中表的字段数量异常！请检查！");
                }
                String headName = list.get(0).get(0);
                String chineseColumnName, englishColumnName;
                boolean withEnglishColumn = false;
                if (headName.contains("(") && headName.contains(")")) {
                    withEnglishColumn = true;
                }
                if (!list.get(list.size() - 1).get(0).equals(transactionDesc)) {
                    throw new GalaxyException("未找到交易:" + list.get(list.size() - 1).get(0) + "！");
                }
                if (list.get(0).size() > colList.size()) {
                    System.out.print(list.get(0).size() + " 超出数据库中表的字段数量 " + colList.size());
                    throw new GalaxyException("数据错误，超出数据库中表的字段数量！请检查！");
                }
                for (int j = 0; j < list.get(0).size(); j++) {
                    headName = list.get(0).get(j);
                    chineseColumnName = headName;
                    if (withEnglishColumn) {
                        if (!(headName.contains("(") && headName.contains(")"))) {
                            System.out.print(list.get(0).get(j) + " 错误！");
                            throw new GalaxyException(list.get(0).get(j) + "   格式错误！");
                        }
                    }
                    if (headName.contains("(") && headName.contains(")")) {
                        chineseColumnName = headName.substring(0, headName.indexOf("("));
                        englishColumnName = headName.substring(headName.indexOf("(") + 1, headName.indexOf(")"));
                        int foundChineseName = 0;
                        int foundEnglishName = 0;
                        for (int columnCount = 0; columnCount < colDescList.size(); columnCount++) {
                            if (chineseColumnName.equals(colDescList.get(columnCount))) {
                                foundChineseName = 1;
                            }
                            if (englishColumnName.equals(colList.get(columnCount).getColumnName())) {
                                foundEnglishName = 1;
                            }
                        }
                        if (foundChineseName <= 0 || foundEnglishName <= 0) {
                            throw new GalaxyException(list.get(0).get(j) + "   格式错误！");
                        }
                    } else {
                        if (!chineseColumnName.equals(colDescList.get(j))) {
                            System.out.print(list.get(0).get(j) + " 字段名称定义错误！" + chineseColumnName);
                            throw new GalaxyException(list.get(0).get(j) + "   字段名称定义错误！");
                        }
                    }
                }
                for (int i = 1; i < list.size() - 1; i++) {
                    int currentIndex;
                    lineNum = i;
                    for (int j = 0; j < list.get(i).size(); j++) {
                        currentIndex = j;
                        if (withEnglishColumn) {
                            headName = list.get(0).get(j);
                            englishColumnName = headName.substring(headName.indexOf("(") + 1, headName.indexOf(")"));
                            for (int columnCount = 0; columnCount < colDescList.size(); columnCount++) {
                                if (englishColumnName.equals(colList.get(columnCount).getColumnName())) {
                                    currentIndex = columnCount;
                                    break;
                                }
                            }
                        }
                        if (colList.get(currentIndex).getIsPrimary().equals("Y")) {
                            keyFieldsobj.put(colList.get(currentIndex).getColumnName(), list.get(i).get(currentIndex));
                        } else {
                            generalFieldsobj.put(colList.get(currentIndex).getColumnName(), list.get(i).get(currentIndex));
                        }
                    }
                    String msg = ParaMeterUtils.updateTableAndFlowStatus(reqNum, keyFieldsobj, generalFieldsobj, paraNamespaceOrg != null ? paraNamespaceOrg.getNamespaceName() : null, systemId, transactionId, 2, "");
                    if (!msg.equals("Success")) {
                        throw new GalaxyException(msg);
                    }
                }
            } else {
                throw new GalaxyException("导入文件保存路径为空！请检查！");
            }
            Instant end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            long millis = timeElapsed.toMillis();
            System.out.println("uploadFile-->excuteTime:" + millis);
            return "redirect:/app/pm/jsp/paraIn/paraImport.jsp?isRedirect=true";
        } catch (Exception e) {
            if (lineNum == -1) {
                tableContent.add(new String[]{"无法解析文件", e.getMessage()});
            } else {
                String errStr = "操作异常";
                if (e.getMessage() != null) {
                    errStr = e.getMessage();
                }
                tableContent.add(new String[]{"第" + lineNum + "行", errStr});
            }
            List<List<Object>> list = new ArrayList<>();
            String mainPath = request.getSession().getServletContext().getRealPath("/");
            String creator = ResourcesUtils.getSystemUser(request);
            String ipAddress = ResourcesUtils.getClientIP(request);
            list.add(tableHead);
            list.add(tableContent);
            list.add(titel);
            ShowErrorMsg.createErrorMsgCode("", list, ipAddress, creator, mainPath);
            e.printStackTrace();
            return "redirect:/app/pm/jsp/paraIn/paraImport.jsp?isErrorMsg=true";
        }
    }
}

