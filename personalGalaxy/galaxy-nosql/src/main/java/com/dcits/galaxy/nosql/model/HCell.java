/**
 * <p>Title: HCell.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 *
 * @author Liang
 * @update 2015年3月9日 下午3:46:01
 * @version V1.0
 */
package com.dcits.galaxy.nosql.model;

/**
 * @author Liang
 * @version V1.0
 * @description
 * @update 2015年3月9日 下午3:46:01
 */

public class HCell {
    private String rowName;
    private String familyName;
    private String column;
    private String value;

    public String getRowName() {
        return rowName;
    }

    public void setRowName(String rowName) {
        this.rowName = rowName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String toString() {
        return "{\"rowName\"=" + rowName + ",\"familyName\"=" + familyName + ",\"column\"=" + column + ",\"value\"=" + value + "}";
    }

}
