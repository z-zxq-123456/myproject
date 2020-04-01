package com.exampl.zxq.handwirte.rpc;

import java.io.Serializable;

/**
 * @description
 * @author: zhouxqh
 * @create: 2020-03-31 22:41
 **/
public class Response implements Serializable {

    private static final long serialVersionUID = 3933918042687238629L;

    private Object result;


    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
