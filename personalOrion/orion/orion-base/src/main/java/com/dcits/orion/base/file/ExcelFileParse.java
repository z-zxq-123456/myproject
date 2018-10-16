package com.dcits.orion.base.file;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Repository;

import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.orion.api.model.FileData;
import com.dcits.orion.base.model.Field;
import com.dcits.orion.base.model.FileConvert;
import com.dcits.orion.base.model.FileRow;


/**
 * Created by lixbb on 2016/3/10.
 */
@Repository
public class ExcelFileParse extends AbstractFileParse {
    /**
     * 获取整个文件数据，
     *
     * @param path
     * @param fileName
     * @param convertName
     * @param pos         文件数据偏移量,文本文件偏移字节位，excel偏移行号
     * @param num         文件行数
     * @return
     */
    @Override
    public FileData getFileData(String path, String fileName, String convertName, long pos, int num) {
        FileConvert fileConvert = FileConvertUtil.getFileConvert(convertName);
        FileData fileData = new FileData();
        FileRow headRow = fileConvert.getFileHead();
        FileRow bodyRow = fileConvert.getFileBody();
        FileRow tailRow = fileConvert.getFileTail();
        Workbook wb;
        FileInputStream is = null;
        try {
            is = new FileInputStream(path + "/" + fileName);
        } catch (FileNotFoundException e) {
            throw new GalaxyException(path + "/" + fileName + "文件不存在");
        }
        try {


            if (fileName.endsWith(".xls")) {
                try {
                    POIFSFileSystem fs = new POIFSFileSystem(is);
                    wb = new HSSFWorkbook(fs);
                } catch (IOException e) {
                    throw new GalaxyException(path + "/" + fileName + "不是可读的Excel文件");
                }
            } else if (fileName.endsWith(".xlsx")) {
                try {
                    wb = new XSSFWorkbook(is);
                } catch (IOException e) {
                    throw new GalaxyException(path + "/" + fileName + "不是可读的Excel文件");
                }
            } else {
                throw new GalaxyException("Excel 文件名后缀应该是xls或xlsx");
            }
            Sheet sheet = null;
            if (fileConvert.getSheetName() != null && fileConvert.getSheetName().trim().length() != 0) {
                sheet = wb.getSheet(fileConvert.getSheetName());
            } else {
                sheet = wb.getSheetAt(0);
            }

            int rowNum = sheet.getLastRowNum();
            List body = new ArrayList();
            fileData.setBodyData(body);
            for (int i = 0; i < num && pos + i <= rowNum; i++) {
                if (i == 0 && pos == 0 && headRow != null) {
                    Object head = getXlsRowData(sheet.getRow((int) pos + i), headRow);
                    fileData.setHeadData(head);
                } else if (pos + i == rowNum && tailRow != null) {
                    Object tail = getXlsRowData(sheet.getRow((int) pos + i), tailRow);
                    fileData.setTailData(tail);
                } else {
                    Row row = sheet.getRow((int) pos + i);
                    if (row != null) {
                        Object bodyObj = getXlsRowData(row, bodyRow);
                        body.add(bodyObj);
                    }
                    else {
                        break;
                    }
                }
            }
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return fileData;
    }

    private Object getXlsRowData(Row row, FileRow fileRow) {
        Object o = newBean(fileRow.getClazz());
        int i = 0;
        for (Field field : fileRow.getFields()) {

            Cell cell = row.getCell(i);
            if (cell != null) {
                String fieldValue = null;

                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_STRING:
                        fieldValue = row.getCell(i).getStringCellValue();
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        fieldValue = String.valueOf(row.getCell(i).getNumericCellValue());
                        break;
                    default:
                        break;

                }
                setValue(o, field, fieldValue);
            }
            i++;
        }
        return o;
    }

    @Override
    public <T> T getHead(String path, String fileName, String convertName) {
        return (T) getFileData(path, fileName, convertName, 0, Integer.MAX_VALUE).getHeadData();
    }

    /**
     * 获取body集合指定偏移量、行数的数据
     *
     * @param path        文件路径
     * @param fileName    文件名称
     * @param convertName 转换名称
     * @return
     */
    @Override
    public <T> T getTail(String path, String fileName, String convertName) {
        return (T) getFileData(path, fileName, convertName, 0, Integer.MAX_VALUE).getTailData();
    }

    /**
     * 写指定格式文件
     *
     * @param path
     * @param fileName
     * @param convertName
     * @param fileData
     * @param append
     */
    @Override
    public <H, B, T> void writeFile(String path, String fileName, String convertName, FileData<H, B, T> fileData, boolean append) {

        if (append == true)
            throw new GalaxyException("EXCEL文件不支持追加方式");
        writeFile(path, fileName, convertName, fileData);

    }

    /**
     * 写指定格式文件
     *
     * @param path
     * @param fileName
     * @param convertName
     * @param fileData
     */
    @Override
    public <H, B, T> void writeFile(String path, String fileName, String convertName, FileData<H, B, T> fileData) {
        Workbook wb;
        if (fileName.endsWith(".xls")) {
            wb = new HSSFWorkbook();
        } else if (fileName.endsWith(".xlsx")) {
            wb = new XSSFWorkbook();
        } else {
            throw new GalaxyException("Excel 文件名后缀应该是xls或xlsx");
        }
        FileConvert fileConvert = FileConvertUtil.getFileConvert(convertName);
        Sheet sheet = null;
        if (fileConvert.getSheetName() != null && fileConvert.getSheetName().trim().length() != 0) {
            sheet = wb.createSheet(fileConvert.getSheetName());
        } else {
            sheet = wb.createSheet();
        }
        FileRow headRow = fileConvert.getFileHead();
        FileRow bodyRow = fileConvert.getFileBody();
        FileRow tailRow = fileConvert.getFileTail();
        int i = 0;
        if (headRow != null && fileData.getHeadData() != null) {
            Row row = sheet.createRow(i);
            writeRow(row, headRow, fileData.getHeadData());
            i++;
        }
        if (bodyRow != null && fileData.getBodyData() != null) {
            List body = fileData.getBodyData();
            for (Object o : body) {
                Row row = sheet.createRow(i);
                writeRow(row, bodyRow, o);
                i++;
            }
        }
        if (tailRow != null && fileData.getTailData() != null) {
            Row row = sheet.createRow(i);
            writeRow(row, tailRow, fileData.getTailData());
            i++;
        }

        FileOutputStream os = null;
        try {
            os = new FileOutputStream(path + "/" + fileName);
            wb.write(os);
        } catch (IOException e) {
            throw new GalaxyException(path + "/" + fileName + "文件创建失败");
        } finally {
            try {
                if (null != os) {
                    os.close();
                }
            } catch (IOException e) {
                throw new GalaxyException(path + "/" + fileName + "文件关闭失败");
            }
        }

    }

    private void writeRow(Row row, FileRow fileRow, Object object) {
        int i = 0;
        for (Field field : fileRow.getFields()) {
            String fieldValue = getValue(object, field);
            Cell cell = row.createCell(i);
            i++;
            cell.setCellValue(fieldValue);
        }
    }


}