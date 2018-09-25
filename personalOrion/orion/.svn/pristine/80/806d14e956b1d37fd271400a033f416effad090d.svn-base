package com.dcits.orion.batch.api.data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by lixbb on 2016/6/7.
 */
public class CheckResult implements Serializable {
    private static final long serialVersionUID = -5521527686619235184L;
    private boolean success;
    private String errorMsg;
    private List<Object[]> tableHead;
    private List<Object[]> tableContent;

    public CheckResult()
    {
        success=true;
    }

    public CheckResult(String errorMsg,List<Object[]> tableHead,List<Object[]> tableContent)
    {
        this.success = false;
        this.errorMsg = errorMsg;
        this.tableHead = tableHead;
        this.tableContent =tableContent;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public List<Object[]> getTableHead() {
        return tableHead;
    }

    public void setTableHead(List<Object[]> tableHead) {
        this.tableHead = tableHead;
    }

    public List<Object[]> getTableContent() {
        return tableContent;
    }

    public void setTableContent(List<Object[]> tableContent) {
        this.tableContent = tableContent;
    }
}
