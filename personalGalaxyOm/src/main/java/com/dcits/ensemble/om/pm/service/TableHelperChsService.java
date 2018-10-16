package com.dcits.ensemble.om.pm.service;

import com.dcits.ensemble.om.pm.dao.paraSetting.ParaFieldsColumnDao;
import com.dcits.ensemble.om.pm.dao.paraSetting.ParaFieldsTableDao;
import com.dcits.ensemble.om.pm.dao.paraSetting.ParaNamespaceOrgDao;
import com.dcits.ensemble.om.pm.dao.paraSetting.ParaSelectFieldsDao;
import com.dcits.ensemble.om.pm.module.paraSetting.ParaFieldsColumn;
import com.dcits.ensemble.om.pm.module.paraSetting.ParaNamespaceOrg;
import com.dcits.ensemble.om.pm.module.paraSetting.ParaSelectFields;
import com.dcits.ensemble.om.pm.module.paraSetting.ParaTableFileds;
import com.dcits.ensemble.om.pm.util.DataTransByServiceUuid;
import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.galaxy.base.util.FileUtils;
import com.dcits.galaxy.base.util.JsonUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.List;

/**
 * Created by Administrator on 2016/1/22.
 */
@Service
public class TableHelperChsService {
    @Resource
    private ParaNamespaceOrgDao paraNamespaceOrgDao;
    @Resource
    private ParaFieldsColumnDao paraFieldsColumnDao;
    @Resource
    private ParaFieldsTableDao paraTableFiledsDao;
    @Resource
    private ParaSelectFieldsDao paraSelectFieldsDao;
    private static final Logger log = LoggerFactory
            .getLogger(TableHelperChsService.class);

    public String readExcelData(String upLoadFilePath) {

        DataTransByServiceUuid serviceData = new DataTransByServiceUuid();
        try {
            InputStream fs = FileUtils.readFileStream(upLoadFilePath);

            //final WorkbookFactory workbookFactory = new WorkbookFactory();
            Workbook workbook = WorkbookFactory.create(fs);
            Sheet sheet;
            if (workbook instanceof HSSFWorkbook) {
                workbook.close();
                fs = FileUtils.readFileStream(upLoadFilePath);
                workbook = new HSSFWorkbook(fs);
            } else {
                workbook.close();
                fs = FileUtils.readFileStream(upLoadFilePath);
                workbook = new XSSFWorkbook(fs);
            }

            if (fs == null) {
                return "创建对象异常";
            }
            sheet = workbook.getSheetAt(0);
            if (upLoadFilePath.indexOf("_COLUMN") != -1) {
                handleColTable(sheet);
            } else if (upLoadFilePath.indexOf("_REL") != -1) {
                handleRel(sheet);

            } else if (upLoadFilePath.indexOf("SELECT") != -1) {
                handleSelect(sheet);
            } else {
                handleTable(sheet);
            }
        } catch (IOException ex) {
            if (log.isErrorEnabled()) {
                ex.printStackTrace();
                log.error("\r\nI/O错误，服务接口代码为：" + serviceData.getName() + "模块：" + serviceData.getDataModel());
            }
            return serviceData.getName();
        } catch (Exception ex) {
            if (log.isErrorEnabled()) {
                ex.printStackTrace();
                log.error("\r\n数据错误，请检查服务接口代码，服务接口代码为：" + serviceData.getName() + "模块：" + serviceData.getDataModel());
            }
            return serviceData.getName();
        }

        return "导入成功";
    }

    //表信息读取
    public void handleTable(Sheet sheet) {

        int maxNumber = sheet.getPhysicalNumberOfRows();
        for (int rowNum = 1; rowNum < maxNumber; rowNum++) {//获取每行
            ParaNamespaceOrg paraNamespaceOrg = new ParaNamespaceOrg();
            Row row;
            row = sheet.getRow(rowNum);
            if (row == null) {
                continue;
            }
            paraNamespaceOrg.setTransactionId(row.getCell(0).getStringCellValue());
            paraNamespaceOrg.setSystemId(row.getCell(1).getStringCellValue());
            paraNamespaceOrg.setModuleId(row.getCell(2).getStringCellValue());
            paraNamespaceOrg.setIsTbname("Y");
            paraNamespaceOrg.setTransactionDesc(row.getCell(3).getStringCellValue());
            paraNamespaceOrg.setNamespaceName(row.getCell(4).getStringCellValue());
            if (row.getCell(5) != null) {
                paraNamespaceOrg.setNamespaceDesc(row.getCell(5).getStringCellValue());
            }
            paraNamespaceOrg.setBandEnteringcheck(row.getCell(6).getStringCellValue());
            String tableName = row.getCell(0).getStringCellValue();
//            paraNamespaceOrg.setJspUrl("app/parameter/"+JsonUtils.convertHumpCase(tableName)+".jsp");

            String url = JsonUtils.convertHumpCase(tableName);
            String jspUrl = url.substring(0, 1).toUpperCase() + url.substring(1);
            paraNamespaceOrg.setJspUrl("app/pm/"+paraNamespaceOrg.getSystemId().toLowerCase()+"/jsp/" + jspUrl + ".jsp");

            if (log.isDebugEnabled()){
                log.debug("\r\n 开始导入" + paraNamespaceOrg.getTransactionId());
            }
            paraNamespaceOrgDao.deleteByPrimaryKey(paraNamespaceOrg);
            paraNamespaceOrgDao.insert(paraNamespaceOrg);
        }
    }

    //列属性读取
    public void handleColTable(Sheet sheet) {

        int maxNumber = sheet.getPhysicalNumberOfRows();
        for (int rowNum = 1; rowNum < maxNumber; rowNum++) {//获取每行
            ParaFieldsColumn paraFieldsColumn = new ParaFieldsColumn();
            Row row = sheet.getRow(rowNum);
            if (row == null) {
                continue;
            }
            paraFieldsColumn.setColumnName(row.getCell(0).getStringCellValue());
            paraFieldsColumn.setColumnType(row.getCell(1).getStringCellValue());
            paraFieldsColumn.setColumnDesc(row.getCell(2).getStringCellValue());
            if (row.getCell(3) != null && row.getCell(3).getCellType() == 0) {
                paraFieldsColumn.setDataLength((int) row.getCell(3).getNumericCellValue());
            } else {
                if (row.getCell(3) != null && !"".equals(row.getCell(3).getStringCellValue().trim())) {

                    paraFieldsColumn.setDataLength(Integer.parseInt(row.getCell(3).getStringCellValue().trim()));
                }
            }
            if (row.getCell(4) != null){
                paraFieldsColumn.setValueMethod(row.getCell(4).getStringCellValue());
            }
            if (row.getCell(5) != null && !"".equals(row.getCell(5).getStringCellValue().trim())){
                paraFieldsColumn.setValueScore(row.getCell(5).getStringCellValue());
            }
            if (row.getCell(6) != null && !"".equals(row.getCell(6).getStringCellValue().trim())){
                paraFieldsColumn.setRefTable(row.getCell(6).getStringCellValue());
            }
            if (row.getCell(7) != null && !"".equals(row.getCell(7).getStringCellValue().trim())){
                paraFieldsColumn.setRefCol(row.getCell(7).getStringCellValue());
            }
            if (log.isDebugEnabled()){
                log.debug("\r\n 开始导入" + paraFieldsColumn.getColumnName());
            }
            paraFieldsColumnDao.deleteByPrimaryKey(paraFieldsColumn);
            paraFieldsColumnDao.insert(paraFieldsColumn);
        }
    }

    // 表列相关读取
    public void handleRel(Sheet sheet) {
        int maxNumber = sheet.getPhysicalNumberOfRows();

        for (int rowNum = 1; rowNum < maxNumber; rowNum++) {//获取每行
            Row row = sheet.getRow(rowNum);
            if (row == null) {
                continue;
            }
            ParaTableFileds ParaTableFileds = new ParaTableFileds();
            ParaTableFileds.setTableName(row.getCell(0).getStringCellValue());
            ParaTableFileds.setColumnName(row.getCell(1).getStringCellValue());
            ParaTableFileds.setIsNull(row.getCell(2).getStringCellValue());
            ParaTableFileds.setIsPrimary(row.getCell(3).getStringCellValue());
            if (log.isDebugEnabled()){
                log.debug("\r\n 开始导入" + ParaTableFileds.getTableName() + "_ref_" + ParaTableFileds.getColumnName());
            }
            paraTableFiledsDao.deleteByPrimaryKey(ParaTableFileds);
            paraTableFiledsDao.insert(ParaTableFileds);

        }
    }

    //是否需要查询读取
    public void handleSelect(Sheet sheet) {
        int maxNumber = sheet.getPhysicalNumberOfRows();

        for (int rowNum = 1; rowNum < maxNumber; rowNum++) {//获取每行
            Row row = sheet.getRow(rowNum);
            if (row == null) {
                continue;
            }
            ParaSelectFields paraSelectFields = new ParaSelectFields();
            paraSelectFields.setTableName(row.getCell(0).getStringCellValue());
            if (row.getCell(1) != null && !"".equals(row.getCell(1).getStringCellValue().trim())){
                paraSelectFields.setSelect1(row.getCell(1).getStringCellValue());
            }
            if (row.getCell(2) != null && !"".equals(row.getCell(2).getStringCellValue().trim())){
                paraSelectFields.setSelect2(row.getCell(2).getStringCellValue());
            }
            if (row.getCell(3) != null && !"".equals(row.getCell(3).getStringCellValue().trim())){
                paraSelectFields.setSelect3(row.getCell(3).getStringCellValue());
            }
            if (log.isDebugEnabled()){
                log.debug("\r\n 开始导入" + paraSelectFields.getTableName() + "_select");
            }
            paraSelectFieldsDao.deleteByPrimaryKey(paraSelectFields);
            paraSelectFieldsDao.insert(paraSelectFields);
        }
    }


    public List<List<String>> readExcelDataToPataTable(String upLoadFilePath) throws Exception{
        Instant start = Instant.now();
        List<List<String>> list = new ArrayList<List<String>>();
        try {
            InputStream fs = FileUtils.readFileStream(upLoadFilePath);
            Workbook workbook = WorkbookFactory.create(fs);
            Sheet sheet;
            if (workbook instanceof HSSFWorkbook) {
                workbook.close();
                fs = FileUtils.readFileStream(upLoadFilePath);
                workbook = new HSSFWorkbook(fs);
            } else {
                workbook.close();
                fs = FileUtils.readFileStream(upLoadFilePath);
                workbook = new XSSFWorkbook(fs);
            }
            sheet = workbook.getSheetAt(0);

            int maxNumber = sheet.getPhysicalNumberOfRows();
            if (maxNumber > 5001){ //excel超过5000行返回报错信息
                List<String> list1=new ArrayList<>();
                list1.add("批量导入数据不得超过5000行！");
                list.add(list1);
                return list;
            }
            int maxColumnNumber = sheet.getRow(0).getLastCellNum();
            for (int rowNum = 0; rowNum < maxNumber; rowNum++) {//获取每行
                List<String> dataList = new ArrayList<String>();
                Row row = sheet.getRow(rowNum);
                for (int cellNum = 0; cellNum < maxColumnNumber; cellNum++) {
                    //设置单元格的数据类型
                    String value = "";
                    if (null != row.getCell(cellNum)){
                        row.getCell(cellNum).setCellType(Cell.CELL_TYPE_STRING);
                        value = row.getCell(cellNum).getStringCellValue();
                    }
                    dataList.add(value);
                }
                list.add(rowNum , dataList);
            }

            List<String> list1=new ArrayList<>();
            list1.add(sheet.getSheetName());
            list.add(list1);

        } catch (Exception ex) {
            //ex.printStackTrace();
            if (log.isErrorEnabled()) {
                log.error("读取Excel数据失败");
            }
            throw new GalaxyException("读取Excel数据失败");
        }
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        long millis = timeElapsed.toMillis();
        System.out.println("readExcelDataToPataTable-->excuteTime:" + millis);
        return list;
    }
}
