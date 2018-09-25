package com.dcits.galaxy.base.exception;

import com.alibaba.fastjson.JSON;
import com.dcits.galaxy.base.data.Result;
import com.dcits.galaxy.base.data.Results;

/**
 * 业务异常抽象类
 *
 * @author xuecy
 */
public class BusinessException extends GalaxyException {

    private Results rets = new Results();

    private static final long serialVersionUID = -5533270369609879192L;

    public BusinessException() {
        super();
    }

    public BusinessException(Results rets) {
        super(rets.toString());
        this.rets = rets;
    }

    public BusinessException(Result ret) {
        super(ret.getRetCode() + ":" + ret.getRetMsg());
        this.rets.addResult(ret);
    }

    public BusinessException(String code, String msg) {
        super(code + ":" + msg);
        this.rets.addResult(new Result(code, msg));
    }

    public BusinessException(Results rets, Throwable cause) {
        super(JSON.toJSONString(rets.getRets()), cause);
        this.rets = rets;
    }

    public BusinessException(Result ret, Throwable cause) {
        super(ret.getRetCode() + ":" + ret.getRetMsg(), cause);
        this.rets.addResult(ret);
    }

    public BusinessException(String code, String msg, Throwable cause) {
        super(code + ":" + msg, cause);
        this.rets.addResult(new Result(code, msg));
    }

    public Results getRets() {
        return rets;
    }

    public void setRets(Results rets) {
        this.rets = rets;
    }
}