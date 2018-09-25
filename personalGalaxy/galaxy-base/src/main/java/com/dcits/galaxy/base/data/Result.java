package com.dcits.galaxy.base.data;

import com.dcits.galaxy.base.bean.AbstractBean;

/**
 * 面向三方的结果集（单条）
 *
 * @author xuecy
 */
public class Result extends AbstractBean {

    private static final long serialVersionUID = 3441357741666447425L;

    private String retCode;

    private String retMsg;

    public static boolean addRetCode = false;

    public Result() {
    }

    public Result(String retCode, String retMsg) {
        this.retCode = retCode;
        this.retMsg = retMsg;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {

        if (addRetCode) {
            if (retMsg != null && !retMsg.startsWith(retCode))
                return retCode + " " + retMsg;
        }

        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

	@Override
    public String toString() {
        return "{retCode:" + retCode + ", retMsg:" + retMsg + "}";
    }
}
