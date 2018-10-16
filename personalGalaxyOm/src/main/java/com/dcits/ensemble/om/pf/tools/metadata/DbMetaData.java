package com.dcits.ensemble.om.pf.tools.metadata;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tim on 2015/6/16.
 */
public class DbMetaData {

    private Map<String, TableMetaData> table = new HashMap<>();

    public Map<String, TableMetaData> getTable() {
        return table;
    }

    public TableMetaData getTable(String tableName) {
        return table.get(tableName.toUpperCase());
    }

    public void setTable(Map<String, TableMetaData> table) {
        this.table = table;
    }

    public void addTable(String tableName, TableMetaData table) {
        this.table.put(tableName.toUpperCase(), table);
    }
}
