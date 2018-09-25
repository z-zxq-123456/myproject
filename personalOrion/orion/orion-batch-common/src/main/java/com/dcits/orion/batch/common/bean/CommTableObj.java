package com.dcits.orion.batch.common.bean;

/**
 * Created by lixbb on 2016/2/18.
 */
public class CommTableObj extends AbstractTableObj {

    public void setTableName(String tableName) {
        if (tableName != null) {
            this.tableName = tableName.toLowerCase();
        }
    }
    public CommTableObj()
    {

    }
    public CommTableObj(String tableName)
    {
        setTableName(tableName);
    }
    private String tableName;
    @Override
    public String tableName() {
        return this.tableName;
    }

}
