/**
 * <p>Title: HDao.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 *
 * @author Liang
 * @update 2015年3月6日 上午11:24:22
 * @version V1.0
 */
package com.dcits.galaxy.nosql.dao;

import com.alibaba.fastjson.JSONObject;
import com.dcits.galaxy.base.util.JsonUtils;
import com.dcits.galaxy.base.util.ObjectUtils;
import com.dcits.galaxy.nosql.Hbase;
import com.dcits.galaxy.nosql.model.HCell;
import com.dcits.galaxy.nosql.model.HColumn;
import com.dcits.galaxy.nosql.model.HRow;

import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.RetriesExhaustedWithDetailsException;
import org.apache.hadoop.hbase.filter.Filter;

import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Liang
 * @version V1.0
 * @description
 * @update 2015年3月6日 上午11:24:22
 */

public abstract class HDao {

    protected Hbase hbase = new Hbase();

    protected abstract String getTableName();

    protected abstract String getFamily();

    protected abstract <T> T getObj();


    public int insert(HColumn hColumn) {
        hbase.put(getTableName(), hColumn.getRowkey(), hColumn.getFamilyName(),
                hColumn.getColumn(), hColumn.getValue());
        return 1;
    }

    public int insert(HTable hTable, Put put) {
        try {
            hTable.put(put);
            hTable.flushCommits();
        } catch (InterruptedIOException e) {
            e.printStackTrace();
        } catch (RetriesExhaustedWithDetailsException e) {
            e.printStackTrace();
        }
        return 1;
    }

    public int updateByRowKey(HColumn hColumn) {
        hbase.update(getTableName(), hColumn.getRowkey(),
                hColumn.getFamilyName(), hColumn.getColumn()[0],
                hColumn.getValue()[0]);
        return 1;
    }

    public int deleteByRowKey(String rowKey) {
        hbase.deleteAllColumn(getTableName(), rowKey);
        return 1;
    }

    public <T> T selectByRowKey(String rowKey) {
        HRow row = hbase.getResult(getTableName(), rowKey);
        return HRow2Bean(getObj(), row);
    }

    public <T> ArrayList<T> selectByFilter() {
        return selectByFilter(null);
    }

    @SuppressWarnings("unchecked")
    public <T> ArrayList<T> selectByFilter(Filter filter) {
        ArrayList<HRow> hrow = hbase.getResultScann(getTableName(), filter);
        List<T> list = new ArrayList<T>();
        for (int i = 0; i < hrow.size(); i++) {
            list.add((T) HRow2Bean(getObj(), hrow.get(i)));
        }
        return (ArrayList<T>) list;
    }

    public <T> HashMap<String, T> selectToMap() {
        return this._selectToMap(null);
    }

    public <T> HashMap<String, T> selectByFilterToMap(Filter filter) {
        return this._selectToMap(filter);
    }

    @SuppressWarnings("unchecked")
    private <T> HashMap<String, T> _selectToMap(Filter filter) {

        ArrayList<HRow> hrow = hbase.getResultScann(getTableName(), filter);
        HashMap<String, T> ret = new HashMap<String, T>();
        for (int i = 0; i < hrow.size(); i++) {
            String rowKey = hrow.get(i).getRowkey();
            ret.put(rowKey, (T) HRow2Bean(getObj(), hrow.get(i)));
        }

        return ret;
    }

    public Result selectListByPrimaryKey(HColumn hColumn) {

        return hbase.getResultByColumn(getTableName(), hColumn.getRowkey(),
                hColumn.getFamilyName(), hColumn.getColumn()[0]);
    }

    @SuppressWarnings("unchecked")
    private <T> T HRow2Bean(Object obj, HRow row) {
        try {
            // rowKey 赋值
            ObjectUtils.executeMethod(obj, "setRowKey", new Object[]{row.getRowkey()});
        } catch (Throwable t) {
        }

        ArrayList<HCell> lists = row.getCell();

        for (int i = 0; i < lists.size(); i++) {
            HCell cell = lists.get(i);

            String method = JsonUtils
                    .convertHumpCase("set_" + cell.getColumn());
            Object[] args = {cell.getValue()};
            try {
                ObjectUtils.executeMethod(obj, method, args);
            } catch (Throwable t) {
            }
        }
        return (T) obj;
    }

    public JSONObject selectToJson() {
        ArrayList<HRow> hrow = hbase.getResultScann(getTableName(), null);
        JSONObject ret = new JSONObject();
        for (int i = 0; i < hrow.size(); i++) {
            String rowKey = hrow.get(i).getRowkey();
            ret.put(rowKey, HRow2Json(hrow.get(i)));
        }
        return ret;
    }

    private JSONObject HRow2Json(HRow row) {
        ArrayList<HCell> lists = row.getCell();
        JSONObject json = new JSONObject();
        for (int i = 0; i < lists.size(); i++) {
            HCell cell = lists.get(i);
            json.put(cell.getColumn(), cell.getValue());
        }
        return json;
    }

    public JSONObject selectToJson(Filter filter) {
        ArrayList<HRow> hrow = hbase.getResultScann(getTableName(), filter);
        JSONObject ret = new JSONObject();
        for (int i = 0; i < hrow.size(); i++) {
            String rowKey = hrow.get(i).getRowkey();
            ret.put(rowKey, HRow2Json(hrow.get(i)));
        }
        return ret;
    }
}
