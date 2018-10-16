package com.dcits.ensemble.om.pm.module.paraSetting;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by maruie on 2016/04/05 18:38:19.
 */
public class ParaFieldsColumn extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is PARA_FIELDS_COLUMN.COLUMN_NAME 列名
     */
    @TablePk(index = 1)
    private String columnName;

    /**
     * This field is PARA_FIELDS_COLUMN.COLUMN_TYPE 字段类型
     */
    private String columnType;

    /**
     * This field is PARA_FIELDS_COLUMN.COLUMN_DESC 字段描述
     */
    private String columnDesc;

    /**
     * This field is PARA_FIELDS_COLUMN.DATA_LENGTH 数据长度
     */
    private Integer dataLength;

    /**
     * This field is PARA_FIELDS_COLUMN.VALUE_METHOD 示显类型
     */
    private String valueMethod;

    /**
     * This field is PARA_FIELDS_COLUMN.VALUE_SCORE 固定备选
     */
    private String valueScore;

    /**
     * This field is PARA_FIELDS_COLUMN.REF_TABLE 自来表
     */
    private String refTable;

    /**
     * This field is PARA_FIELDS_COLUMN.REF_COL 来自列
     */
    private String refCol;

    /**
     * @return the value of  PARA_FIELDS_COLUMN.COLUMN_NAME 列名
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     * @param columnName the value for PARA_FIELDS_COLUMN.COLUMN_NAME 列名
     */
    public void setColumnName(String columnName) {
        this.columnName = columnName == null ? null : columnName.trim();
    }

    /**
     * @return the value of  PARA_FIELDS_COLUMN.COLUMN_TYPE 字段类型
     */
    public String getColumnType() {
        return columnType;
    }

    /**
     * @param columnType the value for PARA_FIELDS_COLUMN.COLUMN_TYPE 字段类型
     */
    public void setColumnType(String columnType) {
        this.columnType = columnType == null ? null : columnType.trim();
    }

    /**
     * @return the value of  PARA_FIELDS_COLUMN.COLUMN_DESC 字段描述
     */
    public String getColumnDesc() {
        return columnDesc;
    }

    /**
     * @param columnDesc the value for PARA_FIELDS_COLUMN.COLUMN_DESC 字段描述
     */
    public void setColumnDesc(String columnDesc) {
        this.columnDesc = columnDesc == null ? null : columnDesc.trim();
    }

    /**
     * @return the value of  PARA_FIELDS_COLUMN.DATA_LENGTH 数据长度
     */
    public Integer getDataLength() {
        return dataLength;
    }

    /**
     * @param dataLength the value for PARA_FIELDS_COLUMN.DATA_LENGTH 数据长度
     */
    public void setDataLength(Integer dataLength) {
        this.dataLength = dataLength;
    }

    /**
     * @return the value of  PARA_FIELDS_COLUMN.VALUE_METHOD 示显类型
     */
    public String getValueMethod() {
        return valueMethod;
    }

    /**
     * @param valueMethod the value for PARA_FIELDS_COLUMN.VALUE_METHOD 示显类型
     */
    public void setValueMethod(String valueMethod) {
        this.valueMethod = valueMethod == null ? null : valueMethod.trim();
    }

    /**
     * @return the value of  PARA_FIELDS_COLUMN.VALUE_SCORE 固定备选
     */
    public String getValueScore() {
        return valueScore;
    }

    /**
     * @param valueScore the value for PARA_FIELDS_COLUMN.VALUE_SCORE 固定备选
     */
    public void setValueScore(String valueScore) {
        this.valueScore = valueScore == null ? null : valueScore.trim();
    }

    /**
     * @return the value of  PARA_FIELDS_COLUMN.REF_TABLE 自来表
     */
    public String getRefTable() {
        return refTable;
    }

    /**
     * @param refTable the value for PARA_FIELDS_COLUMN.REF_TABLE 自来表
     */
    public void setRefTable(String refTable) {
        this.refTable = refTable == null ? null : refTable.trim();
    }

    /**
     * @return the value of  PARA_FIELDS_COLUMN.REF_COL 来自列
     */
    public String getRefCol() {
        return refCol;
    }

    /**
     * @param refCol the value for PARA_FIELDS_COLUMN.REF_COL 来自列
     */
    public void setRefCol(String refCol) {
        this.refCol = refCol == null ? null : refCol.trim();
    }
}