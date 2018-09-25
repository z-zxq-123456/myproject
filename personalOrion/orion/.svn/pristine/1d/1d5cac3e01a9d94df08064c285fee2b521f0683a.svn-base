/**
 * Title: Galaxy(Distributed service platform)
 * File: NoRollBackException.java
 * Copyright: Copyright (c) 2014-2017
 * Company: 神州数码融信软件有限公司
 */
package com.dcits.orion.base.exception;

import com.dcits.galaxy.base.data.Result;
import com.dcits.galaxy.base.data.Results;
import com.dcits.galaxy.base.exception.BusinessException;

/**
 * 九鼎问题修改增加强制事务提交异常 2017/04/06 for Tim
 * <p>Created on 2017/4/6.</p>
 *
 * @author Tim <guotao@dcits.com>
 * @version 1.0.0
 * @since 1.7
 */
public class NoRollBackException extends BusinessException {

    private static final long serialVersionUID = -5783270369609809127L;

    public NoRollBackException() {
        super();
    }

    public NoRollBackException(Results rets) {
        super(rets);
    }

    public NoRollBackException(Result ret) {
        super(ret);
    }

    public NoRollBackException(String code, String msg) {
        super(code, msg);
    }

    public NoRollBackException(Results rets, Throwable cause) {
        super(rets, cause);
    }

    public NoRollBackException(Result ret, Throwable cause) {
        super(ret, cause);
    }

    public NoRollBackException(String code, String msg, Throwable cause) {
        super(code, msg, cause);
    }
}
