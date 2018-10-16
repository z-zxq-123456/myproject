package com.dcits.ensemble.om.pf.tools.metadata;

/**
 * Created by Tim on 2015/6/16.
 */
public class ColumnsMetaData {

    private String columnName;

    private Integer dataType;

    private String remark;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }
}
