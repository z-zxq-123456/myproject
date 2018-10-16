package com.dcits.ensemble.om.pf.util;



import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
* Created by mary on 2015/9/14.
*/
public class DataTransByServiceUuid {
    private ArrayList<ArrayList<String>> table = null;
    private String[] header = null;// 表头信息
    private int colSize = 0; // 表列数
    private int rowSize = 0; // 表行数
    private int maxNumber; //最大行数
    private String name;// 表名称
    private String dataModel;// 模块
    private String messageCode;// code
    private String messageName;// name
    private String sendDirection;// 传输方向
    private String serviceUuid;// 传输方向
    private boolean isFirstTime;// 是否第一次进入，查找messagecode
    private boolean clearExistMessage;// 是否删除存在的messagecode
    private String retMsg;

    public static String CREATER;
    public static String IP;
    public static String ENV_ID;
    public static String SERVICE_CODE;
    public static String MESSAGE_TYPE;
    public static String HEAD_UUID;
    /**
     * Excel HEAD标题栏的列数
     */
    public static final int HEAD_COLUMN_SIZE = 7;
    /**
     * Excel BODY标题栏的列数
     */
    public static final int HEAD_BODYCOLUMN_SIZE = 13;
    /**
     * 单元格中的数据和要查找的串完全相同
     */
    public static final int FIND_EQUALS = 0;
    /**
     * 单元格中的数据包含要查找的串
     */
    public static final int FIND_CONTAINS = 1;

    /**
     * 单元格中的数据以要查找的串开头
     */
    public static final int FIND_STARTS_WITH = 2;
    /**
     * 单元格中的数据以要查找的串结尾
     */
    public static final int FIND_ENDS_WITH = 3;

    public DataTransByServiceUuid() {
        table = new ArrayList<>();
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
     * 返回当前UUID
     *
     * @return UUID
     */
    public String getServiceUuid() {
        return serviceUuid;
    }

    /**
     * 设置UUID
     *
     * @param serviceUuid
     *            service UUID
     */
    public void setServiceUuid(String serviceUuid) {
        this.serviceUuid = serviceUuid;
    }

    /**
     * 返回传输方向
     *
     * @return direction
     */
    public String getSendDirection() {
        return sendDirection;
    }

    /**
     * 设置传输方向
     *
     * @param sendDirection
     *            传输方向
     */
    public void setSendDirection(String sendDirection) {
        this.sendDirection = sendDirection;
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
     * 返回当前表的名字
     *
     * @return 表名字
     */
    public int getMaxNumber() {
        return maxNumber;
    }

    /**
     * 设置表名字
     *
     * @param maxNumber
     *            表名字
     */
    public void setMaxNumber(int maxNumber) {
        this.maxNumber = maxNumber;
    }

    /**
     * 返回当前name
     *
     * @return name
     */
    public String getMessageName() {
        return messageName;
    }

    /**
     * 设置name
     *
     * @param messageName
     *            name
     */
    public void setMessageName(String messageName) {
        this.messageName = messageName;
    }

/**
 * 返回当前code
 *
 * @return code
 */
public String getMessageCode() {
    return messageCode;
}

/**
 * 设置code
 *
 * @param messageCode
 *            code
 */
public void setMessageCode(String messageCode) {
    this.messageCode = messageCode;
}

    /**
     * 返回是否第一次进入
     *
     * @return 状态
     */
    public boolean getIsFirstTime() {
        return isFirstTime;
    }

    /**
     * 设置是否第一次
     *
     * @param isFirstTime
     *
     */
    public void setIsFirstTime(boolean isFirstTime) {
        this.isFirstTime = isFirstTime;
    }

    /**
     * 返回表列数
     *
     * @return 表列数
     */
    public int getColSize() {
        return colSize;
    }

    /**
     * 用于构建表的时候设置表列数，若所给列数小于0则抛出 IllegalArgumentException 异常
     *
     * @param colSize
     *            表要设置为多少列
     * @see IllegalArgumentException
     */
    public void setColSize(int colSize) {
        if (colSize < 0)
            throw new IllegalArgumentException("Wrong column size");
        this.colSize = colSize;
    }

    /**
     * 返回表行数
     *
     * @return 表行数
     */
    public int getRowSize() {
        return rowSize;
    }

    /**
     * 用于构建表的时候设置表行数，若所给行数小于0则抛出 IllegalArgumentException异常
     *
     * @param rowSize
     *            表要设置为多少行
     * @throws IllegalArgumentException
     * @see IllegalArgumentException
     */
    public void setRowSize(int rowSize) {
        if (rowSize < 0) {
            throw new IllegalArgumentException("Wrong row size");
        }
        this.rowSize = rowSize;
    }

    /**
     * 返回表头（列名称）
     *
     * @return 表头{@code String[]}
     */
    public String[] getHeader() {
        return header;
    }

    /**
     * 设置表的列名称
     *
     * @param header
     *            表列名称数组
     */
    public void setHeader(String[] header) {
        this.header = header;
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