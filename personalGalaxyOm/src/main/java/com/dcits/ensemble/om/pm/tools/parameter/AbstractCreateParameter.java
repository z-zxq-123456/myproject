package com.dcits.ensemble.om.pm.tools.parameter;

import com.dcits.dynamic.web.util.tools.FreeMakerUtil;
import com.dcits.dynamic.web.util.tools.ToolsConstants;
import com.dcits.ensemble.om.pm.dao.paraSetting.ParaFieldsColumnDao;
import com.dcits.ensemble.om.pm.dao.paraSetting.ParaFieldsTableDao;
import com.dcits.ensemble.om.pm.dao.paraSetting.ParaNamespaceOrgDao;
import com.dcits.ensemble.om.pm.dao.paraSetting.ParaSelectFieldsDao;
import com.dcits.ensemble.om.pm.module.paraSetting.ParaFieldsColumn;
import com.dcits.ensemble.om.pm.module.paraSetting.ParaNamespaceOrg;
import com.dcits.ensemble.om.pm.module.paraSetting.ParaSelectFields;
import com.dcits.ensemble.om.pm.module.paraSetting.ParaTableFileds;
import com.dcits.ensemble.om.pm.tools.CreateParameterCode;
import com.dcits.dynamic.web.util.tools.AbstractCreateCode;
import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.galaxy.core.spring.SpringApplicationContext;
import com.dcits.galaxy.base.util.DateUtils;
import com.dcits.galaxy.base.util.JsonUtils;
import com.dcits.galaxy.base.util.StringUtils;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractCreateParameter extends AbstractCreateCode implements CreateParameterCode {
    protected String jspPackage;

    public AbstractCreateParameter(String jspPackage) {
        this.jspPackage = jspPackage;
    }

    public String createParameterCode(String tableName, String creator, String mainPath, ParaFieldsTableDao paraTableFiledsDao, ParaFieldsColumnDao paraFieldsColumnDao, ParaNamespaceOrgDao paraNamespaceOrgDao, ParaSelectFieldsDao paraSelectFieldsDao) throws IOException, TemplateException {
        try {
            String tableNameBack = tableName;
            tableName = tableName.toUpperCase();
            String tableName1 = tableName.toLowerCase();
            ParaTableFileds paraFieldsTable = new ParaTableFileds();
            paraFieldsTable.setTableName(tableName);
            List<ParaTableFileds> listCol = paraTableFiledsDao.getFieldDataByPKValue(paraFieldsTable);
            int pkNum = paraTableFiledsDao.selectPrimaryNum(paraFieldsTable);
            String humpTableName = JsonUtils.convertHumpCase(tableName);
            String beanName = humpTableName.substring(0, 1).toUpperCase() + humpTableName.substring(1);
            Map<String, Object> inT = new HashMap<>();
            if (null != jspPackage) {
                inT.put("jspPackage", jspPackage);
            }
            ParaNamespaceOrg paraNamespaceOrg = new ParaNamespaceOrg();
            paraNamespaceOrg.setTransactionId(tableNameBack);
            paraNamespaceOrg = paraNamespaceOrgDao.selectDataByDbPrimaryKey(paraNamespaceOrg);
            String systemName = paraNamespaceOrg.getSystemId().toLowerCase();
            String includeJsPath = "<script type=\"text/javascript\" src=\"${ContextPath}/app/pm/" + systemName + "/js/" + beanName + ".js\"></script>";
            String includeAddJsPath = "<script type=\"text/javascript\" src=\"${ContextPath}/app/pm/" + systemName + "/js/add/" + humpTableName + "Add.js\"></script>";
            String includeModJsPath = "<script type=\"text/javascript\" src=\"${ContextPath}/app/pm/" + systemName + "/js/edit/" + humpTableName + "Mod.js\"></script>";
            inT.put("includeJsPath", includeJsPath);
            inT.put("includeAddJsPath", includeAddJsPath);
            inT.put("includeModJsPath", includeModJsPath);
            inT.put("tableNameBack", tableNameBack);//原装表名
            inT.put("TableName", tableName);    //大写表名
            inT.put("tableName", tableName1);   //小写表名
            inT.put("beanName", humpTableName); //Bean对象名
            inT.put("BeanName", beanName);    // Bean名
            inT.put("DaoName", beanName + "Dao"); //dao名称
            inT.put("daoName", humpTableName + "Dao"); //dao对象名
            inT.put("creator", creator);
            inT.put("date", DateUtils.getDateTime("yyyy/MM/dd HH:mm:ss"));
            List<Map<String, Object>> pks = new ArrayList<>();
            // 更新列数组，不含主键
            List<Map<String, Object>> updateCols = new ArrayList<>();
            // 插入列数组，包含所有列
            List<Map<String, Object>> insertCols = new ArrayList<>();
            ParaSelectFields paraSelectFields = new ParaSelectFields();
            paraSelectFields.setTableName(tableName);
            paraSelectFields = paraSelectFieldsDao.selectByPrimaryKey(paraSelectFields, tableName);
            int count = 0;
            for (ParaTableFileds paraTableFileds : listCol) {
                count++;
                Map<String, Object> colM = new HashMap<>();
                String comlumnName = paraTableFileds.getColumnName();
                System.out.print("\r\nCOLUMN" + count + ":" + comlumnName);
                ParaFieldsColumn paraFieldsColumn = paraFieldsColumnDao.getTbColumnsByColumnName(comlumnName);
                if (paraFieldsColumn == null) {
                    System.out.print(tableName + "表未定义字段：" + comlumnName);
                    throw new GalaxyException("未定义字段," + comlumnName);
                }
                String name = paraTableFileds.getColumnName();
                String paramName = JsonUtils.convertHumpCase(name);
                String colName = name.toUpperCase();
                String pkcolumn = "";
                if (pkNum == 1) {
                    if ("Y".equals(paraTableFileds.getIsPrimary()))
                        pkcolumn = paramName;
                }
                colM.put("pkColumn", pkcolumn);
                if (paraSelectFields != null && (name.equals(paraSelectFields.getSelect1()) || name.equals(paraSelectFields.getSelect2()) || name.equals(paraSelectFields.getSelect3()))) {
                    colM.put("YnSelect", "Y");
                    //System.out.print("\r\nYnSelect:Y," +  paraTableFileds.getColumnName());
                } else {
                    colM.put("YnSelect", "N");
                }
                colM.put("paramName", paramName);
                colM.put("ParamName", paramName.substring(0, 1).toUpperCase() + paramName.substring(1));
                colM.put("colName", colName);
                colM.put("colIndex", count);
                colM.put("isPrim", paraTableFileds.getIsPrimary());
                //System.out.print("paramName " + paramName);
                colM.put("remark", paraFieldsColumn.getColumnDesc());
                String javaType = paraFieldsColumn.getColumnType();
                if ("VARCHAR2".equals(javaType.trim()) || "VARCHAR".equals(javaType.trim()) || "DATE".equals(javaType.trim())) {
                    javaType = "String";
                } else {
                    javaType = "int";
                }
                colM.put("javaType", javaType);
                colM.put("dataLength", paraFieldsColumn.getDataLength());
                if ("N".equalsIgnoreCase(paraTableFileds.getIsNull())) {
                    colM.put("nullAble", paraTableFileds.getIsNull());
                }
                colM.put("valueMethod", paraFieldsColumn.getValueMethod());
                if (paraFieldsColumn.getValueMethod() == null) {
                    System.out.print(tableName + "表未定义getValueMethod,字段：" + comlumnName);
                    throw new GalaxyException(tableName + "表未定义getValueMethod," + comlumnName);
                }
                if (paraFieldsColumn.getValueMethod().equals("VL") && paraFieldsColumn.getValueScore() != null) {
                    //System.out.print(paraFieldsColumn.getValueMethod() + "ValueScore:::" + paraFieldsColumn.getValueScore());
                    colM.put("ValueScore", paraFieldsColumn.getValueScore());
                    String valueScore[] = paraFieldsColumn.getValueScore().split(",");

                    List<Map<String, String>> scores = new ArrayList<>();
                    for (int i = 0; i < valueScore.length; i++) {
                        String sc = valueScore[i];
                        Map<String, String> score = new HashMap<>();
                        if (sc.contains("-")) {
                            String scoreValue = sc.substring(0, sc.indexOf("-"));
                            String scoreDesc = sc.substring(sc.indexOf("-") + 1);
                            score.put("ScoreValue", scoreValue);
                            score.put("ScoreDesc", scoreDesc);
                        } else {
                            score.put("ScoreValue", sc);
                            score.put("ScoreDesc", " ");
                        }
                        if (score.size() > 0) {
                            scores.add(i, score);
                        }
                    }
                    if (scores.size() > 0) {
                        colM.put("valueScore", scores);
                    }
                }

                if (paraFieldsColumn.getValueMethod().equals("VL") && paraFieldsColumn.getValueScore() == null) {
                    colM.put("valueMethod", "FD");
                }
                if (paraFieldsColumn.getRefCol() != null) {
                    colM.put("refTable", paraFieldsColumn.getRefTable());
                    colM.put("refCol", paraFieldsColumn.getRefCol());
                }
                if ("Y".equals(paraTableFileds.getIsPrimary())) {
                    pks.add(colM);
                } else {
                    updateCols.add(colM);
                }

                insertCols.add(colM);
            }
            inT.put("pks", pks);
            Map<String, String> selectDataT = new HashMap<>();
            if (paraSelectFields != null) {
                if (paraSelectFields.getSelect1() != null) {
                    selectDataT.put("SELECT1", paraSelectFields.getSelect1().toUpperCase());
                } else
                    selectDataT.put("SELECT1", "");
                if (paraSelectFields.getSelect2() != null) {
                    selectDataT.put("SELECT2", paraSelectFields.getSelect2().toUpperCase());
                } else
                    selectDataT.put("SELECT2", "");
                if (paraSelectFields.getSelect3() != null) {
                    selectDataT.put("SELECT3", paraSelectFields.getSelect3().toUpperCase());
                } else
                    selectDataT.put("SELECT3", "");
                inT.put("selectIs", "Y");
            } else {
                selectDataT.put("SELECT1", "");
                selectDataT.put("SELECT2", "");
                selectDataT.put("SELECT3", "");
                inT.put("selectIs", "N");
            }
            inT.put("selectData", selectDataT);
            inT.put("updateCol", updateCols); //更新列
            if (insertCols.size() > 10) {
                inT.put("fixWidth", "Y");
            } else {
                inT.put("fixWidth", "N");
            }
            inT.put("insertCol", insertCols);// 插入列
            // 设置文件名称
            setFileName(beanName);
            // 根据模板生成内容
            String context = FreeMakerUtil.createTemplate(ToolsConstants.CLAZZ_ROOT_PATH
                    + ToolsConstants.FILE_SEPARATOR + ToolsConstants.TEMPLATE_DIR
                    + ToolsConstants.FILE_SEPARATOR, getTemplateName(), inT);
            // 生成root path
            if (rootPath == null)
                createRootPath(mainPath, ToolsConstants.PARAMETER_PATH + ToolsConstants.FILE_SEPARATOR + systemName);
            // 写文件

            if (fileName.endsWith("jsp")) {
                rootPath += "jsp" + ToolsConstants.FILE_SEPARATOR;
            }
            if (fileName.endsWith("js")) {
                rootPath += "js" + ToolsConstants.FILE_SEPARATOR;

            }
            if (fileName.contains("Add.js")) {
                rootPath += "add" + ToolsConstants.FILE_SEPARATOR;
            }
            if (fileName.contains("Mod.js")) {
                rootPath += "edit" + ToolsConstants.FILE_SEPARATOR;
            }
            //System.out.print("\r\nWrite fileName " + fileName + " rootPath " + rootPath);
            writeFile(rootPath, fileName, context);
            return rootPath;
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new GalaxyException("运行时异常");
        } catch (Exception e) {
            e.printStackTrace();
            throw new GalaxyException("解析表字段数据异常");
        }
    }
}
