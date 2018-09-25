/**
 * <p>Title: HRow.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 *
 * @author Liang
 * @update 2015年3月9日 下午3:52:43
 * @version V1.0
 */
package com.dcits.galaxy.nosql.model;

import java.util.ArrayList;

/**
 * @description
 * @version V1.0
 * @author Liang
 * @update 2015年3月9日 下午3:52:43
 */

public class HRow {

    private String rowkey;

    private ArrayList<HCell> cells;

    public String getRowkey() {
        return rowkey;
    }

    public void setRowkey(String rowkey) {
        this.rowkey = rowkey;
    }

    public ArrayList<HCell> getCell() {
        return cells;
    }

    public void setCell(ArrayList<HCell> cell) {
        this.cells = cell;
    }

    public String toString() {
        return "{\"rowKey\"=" + rowkey + ",\"cells\"=" + cells + "}";
    }
}
