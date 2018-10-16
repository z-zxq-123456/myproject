package com.dcits.ensemble.om.pm.module.paraSetting;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by maruie on 2016/04/05 18:38:19.
 */
public class ParaTableFileds extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is PARA_TABLE_FIELDS.TABLE_NAME 表名
     */
    @TablePk(index = 1)
    private String tableName;

    /**
     * This field is PARA_TABLE_FIELDS.COLUMN_NAME 列名
     */
    @TablePk(index = 2)
    private String columnName;

    /**
     * This field is PARA_TABLE_FIELDS.IS_NULL  是否空
     */
    private String isNull;

    /**
     * This field is PARA_TABLE_FIELDS.IS_PRIMARY 是否主键
     */
    private String isPrimary;

    /**
     * @return the value of  PARA_TABLE_FIELDS.TABLE_NAME 表名
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * @param tableName the value for PARA_TABLE_FIELDS.TABLE_NAME 表名
     */
    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    /**
     * @return the value of  PARA_TABLE_FIELDS.COLUMN_NAME 列名
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     * @param columnName the value for PARA_TABLE_FIELDS.COLUMN_NAME 列名
     */
    public void setColumnName(String columnName) {
        this.columnName = columnName == null ? null : columnName.trim();
    }

    /**
     * @return the value of  PARA_TABLE_FIELDS.IS_NULL  是否空
     */
    public String getIsNull() {
        return isNull;
    }

    /**
     * @param isNull the value for PARA_TABLE_FIELDS.IS_NULL  是否空
     */
    public void setIsNull(String isNull) {
        this.isNull = isNull == null ? null : isNull.trim();
    }

    /**
     * @return the value of  PARA_TABLE_FIELDS.IS_PRIMARY 是否主键
     */
    public String getIsPrimary() {
        return isPrimary;
    }

    /**
     * @param isPrimary the value for PARA_TABLE_FIELDS.IS_PRIMARY 是否主键
     */
    public void setIsPrimary(String isPrimary) {
        this.isPrimary = isPrimary == null ? null : isPrimary.trim();
    }
}