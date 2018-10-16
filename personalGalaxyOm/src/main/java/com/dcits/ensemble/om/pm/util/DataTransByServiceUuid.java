package com.dcits.ensemble.om.pm.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
* Created by maruie on 2015/9/14.
*/
public class DataTransByServiceUuid {
    private ArrayList<ArrayList<String>> table = null;

    private int colSize = 0; // 表列数
    private int rowSize = 0; // 表行数

    private String name;// 表名称
    private String dataModel;// 模块
    private boolean clearExistMessage;// 是否删除存在的messagecode
    private String retMsg;



    public DataTransByServiceUuid() {
        table = new ArrayList<ArrayList<String>>();
        setClearExistMessage(true);//导入时，删除数据库中重复的接口数据。
    }

    public ArrayList<ArrayList<String>> getList()
    {
        return table;
    }


/**
 * 返回当前UUID
 *
 * @return UUID
 */
public String getRetMsg() {
    return retMsg;
}

/**
 * 设置UUID
 *
 * @param retMsg
 *            service UUID
 */
public void setRetMsg(String retMsg) {
    this.retMsg = retMsg;
}

/**
 * 返回当前clearExistMessage
 *
 * @return clearExistMessage
 */
public boolean getClearExistMessage() {
    return clearExistMessage;
}

/**
 * 设置clearExistMessage
 *
 * @param clearExistMessage
 *            clearExistMessage
 */
public void setClearExistMessage(boolean clearExistMessage) {
    this.clearExistMessage = clearExistMessage;
}


/**
 * 返回当前表的名字
 *
 * @return 表名字
 */
public String getDataModel() {
    return dataModel;
}

/**
 * 设置表名字
 *
 * @param dataModel
 *            表名字
 */
public void setDataModel(String dataModel) {
    this.dataModel = dataModel;
}

    /**
     * 返回当前表的名字
     *
     * @return 表名字
     */
    public String getName() {
        return name;
    }

    /**
     * 设置表名字
     *
     * @param name
     *            表名字
     */
    public void setName(String name) {
        this.name = name;
    }



    /**
     * 通过索引获取表内某一行的数据，若指定索引行不存在，抛出 IndexOutOfBoundsException 异常
     *
     * @param index
     *            行索引
     * @return 指定行数据
     * @throws IndexOutOfBoundsException
     *
     * @see IndexOutOfBoundsException
     */
    public ArrayList<String> getRowAt(int index) {
        if (index < 0 && index > this.rowSize)
            throw new IndexOutOfBoundsException("指定行不存在");
        return new ArrayList<String>(table.get(index));
    }

    /**
     * 通过索引获取表内某一列的数据，若指定索引列不存在，抛出 IndexOutOfBoundsException 异常
     *
     * @param index
     *            列索引
     * @return 指定列数据
     * @throws IndexOutOfBoundsException
     *
     * @see IndexOutOfBoundsException
     */
    public ArrayList<String> getColumn(int index) {
        if (index < 0 && index > this.colSize)
            throw new IndexOutOfBoundsException("指定列不存在");
        ArrayList<String> cols = new ArrayList<String>();
        for (ArrayList<String> a : table) {
            cols.add(a.get(index));
        }
        return cols;
    }

    /**
     * 获取某个指定单元格的数据，若指定索引单元格不存在，抛出 IndexOutOfBoundsException 异常
     *
     * @param row
     *            指定行索引
     * @param col
     *            指定列索引
     * @return 指定索引的单元格数据
     * @throws IndexOutOfBoundsException
     *
     * @see IndexOutOfBoundsException
     */
    public String getCell(int row, int col) {
        if (row < 0 && row > this.rowSize)
            throw new IndexOutOfBoundsException("指定行不存在");
        if (col < 0 && col > this.colSize)
            throw new IndexOutOfBoundsException("指定列不存在");
        String cellText = table.get(row).get(col);
        cellText = cellText.trim();
        cellText = cellText.replaceAll(" ","");
        cellText = cellText.replaceAll("[\\s\\u00A0]+$","");
        return cellText;
    }

    /**
     * 添加 新行到表中
     *
     * @param row
     *            要添加的新行
     */
    public void addRow(ArrayList<String> row) {
        table.add(row);
    }

    /**
     * 清空表中的数据
     *
     */
    public void clearRows() {
        table.clear();
    }

    public String getCellValue(HSSFCell cell) {
        if (cell == null) {
            return "";
        }
        int cellType = cell.getCellType();
        switch (cellType) {
            case 0:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    Date d = cell.getDateCellValue();
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    return df.format(d);
                } else {
                    cell.setCellType(1);
                    return String.valueOf(cell.getStringCellValue().trim());
                }
            case 1:
                return String.valueOf(cell.getStringCellValue().trim());
            case 3:
                return "";
            default:
                try {
                    throw new Exception("Cell Value not found !"
                            + "The cell type is " + cellType);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return "";
        }
    }

    public String getCellNumtoStr(String str) {
        String ret = str;
        if (str.endsWith(".0")) {
            ret = str.replace(".0", "");
        }
        return ret;
    }

}