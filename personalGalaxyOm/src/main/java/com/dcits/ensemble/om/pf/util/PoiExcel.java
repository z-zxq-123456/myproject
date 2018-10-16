package com.dcits.ensemble.om.pf.util;

/**
 * Created by maruie on 2015/11/20.
 */

import com.dcits.ensemble.om.pf.common.ToolsConstants;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;


public class PoiExcel {


    private static final Logger log = LoggerFactory
            .getLogger(PoiExcel.class);
    public static HSSFWorkbook writeExcel(String xlsPath, Map<String, Object[]> data) {
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("result");

            // 设置表格默认列宽度为35个字节
            sheet.setDefaultColumnWidth(30);
            // 生成一个样式
            HSSFCellStyle style = workbook.createCellStyle();
            // 设置这些样式
            style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
            style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            // 生成一个字体
            HSSFFont font = workbook.createFont();
            font.setColor(HSSFColor.VIOLET.index);
            font.setFontHeightInPoints((short) 12);
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            // 把字体应用到当前的样式
            style.setFont(font);
            // 生成并设置另一个样式
            HSSFCellStyle style2 = workbook.createCellStyle();
            style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
            style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            // 生成另一个字体
            HSSFFont font2 = workbook.createFont();
            font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
            // 把字体应用到当前的样式
            style2.setFont(font2);

            HSSFCellStyle style3 = workbook.createCellStyle();
            style3.setFillForegroundColor(HSSFColor.RED.index);
            style3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            style3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            style3.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style3.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style3.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style3.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            style3.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            // 生成另一个字体
            HSSFFont font3 = workbook.createFont();
            font3.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
            // 把字体应用到当前的样式
            style3.setFont(font3);
            HSSFCellStyle style4 = workbook.createCellStyle();
            style4.setFillForegroundColor(HSSFColor.GREEN.index);
            style4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            style4.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            style4.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style4.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style4.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style4.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            style4.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            // 生成另一个字体
            HSSFFont font4 = workbook.createFont();
            font4.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
            // 把字体应用到当前的样式
            style4.setFont(font4);

            Set<String> keyset = data.keySet();
            int rownum = 0;
            for (String key : keyset) {
                HSSFRow row = sheet.createRow(rownum++);
                Object[] objArr = data.get(key);
                int cellnum = 0;
                String isSuccess;
                HSSFCell keycell = row.createCell(cellnum++);
                HSSFRichTextString text;


                if (1 == rownum && "0".equals(key)) {
                    keycell.setCellStyle(style);
                    text = new HSSFRichTextString("序号");
                    keycell.setCellValue(text);
                } else {
                    keycell.setCellValue(key);
                }
                for (Object obj : objArr) {
                    HSSFCell cell = row.createCell(cellnum++);
                    if (rownum <= 1) {
                        cell.setCellStyle(style);
                        text = new HSSFRichTextString((String) obj);
                        cell.setCellValue(text);
                    } else {
                        isSuccess = (String) obj;
                        if (cellnum != 5) {
                            cell.setCellStyle(style2);
                        } else {
                            if ("N".equals(isSuccess) || isSuccess.contains("失败")) {
                                cell.setCellStyle(style3);
                            }
                            if ("Y".equals(isSuccess) || isSuccess.contains("成功")) {
                                cell.setCellStyle(style4);
                            }
                        }
                        cell.setCellValue(isSuccess);
                    }
                }
            }
            try {
                FileOutputStream out = new FileOutputStream(new File(xlsPath));
                workbook.write(out);
                out.close();
                return workbook;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public HSSFFont[] cellFonts;
    public HSSFCellStyle[] cellStyles;

    public void setBookStyle(HSSFWorkbook workbook) {
        cellStyles = new HSSFCellStyle[5];
        cellFonts = new HSSFFont[5];
        cellStyles[0] = workbook.createCellStyle();
        // 设置这些样式
        cellStyles[0].setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
        cellStyles[0].setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cellStyles[0].setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyles[0].setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellStyles[0].setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyles[0].setBorderTop(HSSFCellStyle.BORDER_THIN);
        // 生成一个字体
        cellFonts[0] = workbook.createFont();
        cellFonts[0].setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式
        cellStyles[0].setFont(cellFonts[0]);
        cellStyles[1] = workbook.createCellStyle();
        cellStyles[1].setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
        cellStyles[1].setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cellStyles[1].setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyles[1].setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellStyles[1].setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyles[1].setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellStyles[1].setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 生成另一个字体
        cellFonts[1] = workbook.createFont();
        cellFonts[1].setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        cellFonts[1].setFontHeightInPoints((short) 11);
        // 把字体应用到当前的样式
        cellStyles[1].setFont(cellFonts[1]);

        // 生成并设置另一个样式
        cellStyles[2] = workbook.createCellStyle();
        cellStyles[2].setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
        cellStyles[2].setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cellStyles[2].setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyles[2].setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellStyles[2].setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyles[2].setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellStyles[2].setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 生成另一个字体
        cellFonts[2] = workbook.createFont();
        cellFonts[2].setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        cellFonts[2].setFontHeightInPoints((short) 11);
        // 把字体应用到当前的样式
        cellStyles[2].setFont(cellFonts[2]);

        cellStyles[3] = workbook.createCellStyle();
        cellStyles[3].setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
        cellStyles[3].setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cellStyles[3].setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyles[3].setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellStyles[3].setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyles[3].setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellStyles[3].setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 生成另一个字体
        cellFonts[3] = workbook.createFont();
        cellFonts[3].setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        cellFonts[3].setFontHeightInPoints((short) 12);
        // 把字体应用到当前的样式
        cellStyles[3].setFont(cellFonts[3]);
        cellStyles[4] = workbook.createCellStyle();
        cellStyles[4].setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
        cellStyles[4].setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cellStyles[4].setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyles[4].setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellStyles[4].setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyles[4].setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellStyles[4].setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 生成另一个字体
        cellFonts[4] = workbook.createFont();
        cellFonts[4].setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        cellFonts[4].setFontHeightInPoints((short) 13);
        // 把字体应用到当前的样式
        cellStyles[4].setFont(cellFonts[4]);
    }

    public HSSFWorkbook writeExcel_mbsd(String[] para, Map<Integer, Object[]> data, HSSFWorkbook workbook, HSSFSheet sheet) {
        //合并单元格
        CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 1, 12);
        sheet.addMergedRegion(cellRangeAddress);
        try {
            // 设置表格默认列宽度
            sheet.setDefaultColumnWidth(20);
            Set<Integer> keyset = data.keySet();
            int rownum = 0;
            for (int key : keyset) {
                HSSFRow row = sheet.createRow(rownum++);
                String[] objArr = (String[]) data.get(key);
                int cellnum = 0;
                String isSuccess = "";
                HSSFRichTextString text;
                for (String obj : objArr) {
                    HSSFCell cell = row.createCell(cellnum++);
                    if (cell.getColumnIndex() == 1) {
                        cell.setCellStyle(cellStyles[0]);
                        if ((objArr[1] == "In"||"In".equals(objArr[1])) && cell.getColumnIndex() != 0) {
                            CellRangeAddress cellRangeAddress1 = new CellRangeAddress(rownum - 1, rownum - 1, 1, 12);
                            sheet.addMergedRegion(cellRangeAddress1);
                            cell.setCellStyle(cellStyles[1]);
                        }
                        if ((objArr[1] == "Out"||"Out".equals(objArr[1])) && cell.getColumnIndex() != 0) {
                            CellRangeAddress cellRangeAddress1 = new CellRangeAddress(rownum - 1, rownum - 1, 1, 12);
                            sheet.addMergedRegion(cellRangeAddress1);
                            cell.setCellStyle(cellStyles[2]);
                        }
                    }
                    if (objArr[0].contains("Message_code")) {
                        cell.setCellStyle(cellStyles[4]);
                    }
                    if (objArr[1].equals("序号") && cell.getColumnIndex() != 0) {
                        cell.setCellStyle(cellStyles[3]);
                    }

                    if (rownum <= 1) {
                        text = new HSSFRichTextString(obj);
                        cell.setCellValue(text);
                    } else {
                        isSuccess = obj;
                        cell.setCellValue(isSuccess);
                    }
                }
            }
            return workbook;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getDIr(String path) {
        return path + ToolsConstants.downFolderName + ToolsConstants.FILE_SEPARATOR + ToolsConstants.MBSD_DIR + ToolsConstants.FILE_SEPARATOR;
    }
}
