package com.dcits.orion.scp.api.exception;

/**
 * Created by lixiaobin on 2017/3/14.
 */
public class UnknownException extends RuntimeException {

    public UnknownException()
    {

    }
   public UnknownException(String retCode,String retMsg)
   {
       this.retCode = retCode;
       this.retMsg = retMsg;
   }

    String retMsg;

    String retCode;

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }
    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }
    @Override
    public String getMessage()
    {
        return retCode + " "  + retMsg;
    }
}
