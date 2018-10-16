package com.dcits.ensemble.om.pf.tools.metadata;

import com.dcits.orion.core.util.BusinessUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tim on 2015/6/16.
 */
public class TableMetaData {

    private String tableSchem;

    private String tableName;

    private String remark;

    private List<String> primaryKeys;

    private List<ColumnsMetaData> cols;

    public List<String> getPrimaryKeys() {
        if (null == primaryKeys) return new ArrayList<>();
        return primaryKeys;
    }

    public void setPrimaryKeys(List<String> primaryKeys) {
        this.primaryKeys = primaryKeys;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName.toUpperCase();
    }

    public List<ColumnsMetaData> getCols() {
        if (null == cols) return new ArrayList<>();
        return cols;
    }

    public String getTableSchem() {
        return tableSchem;
    }

    public void setTableSchem(String tableSchem) {
        this.tableSchem = tableSchem;
    }

    public void setCols(List<ColumnsMetaData> cols) {
        this.cols = cols;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public ColumnsMetaData getColumsMetaData(String colName) {
        if (BusinessUtils.isNull(colName))
            return null;
        for (ColumnsMetaData col : cols) {
            if (colName.equalsIgnoreCase(col.getColumnName())) {
                return col;
            }
        }
        return null;
    }

    public boolean isPkColums(String colName) {
        if (BusinessUtils.isNull(colName))
            return false;
        for (String pk : primaryKeys) {
            if (colName.equalsIgnoreCase(pk)) {
                return true;
            }
        }
        return false;
    }
}
