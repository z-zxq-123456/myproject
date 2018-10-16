package com.dcits.ensemble.om.oms.module.log;

/**
 * Created by luolang on 2016/11/23.
 */
public class EcmChartSeries {

    private String name;

    private Object[] data;

    private Object[] date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Object[] getData() {
        return data;
    }

    public void setData(Object[] data) {
        this.data = data;
    }

    public Object[] getDate() {
        return date;
    }

    public void setDate(Object[] date) {
        this.date = date;
    }
}
