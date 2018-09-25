package com.dcits.galaxy.base.exception;

import com.dcits.galaxy.base.data.Result;
import com.dcits.galaxy.base.data.Results;

/**
 * Created by Tim on 2015/8/24.
 */
public class WithoutAuthAndConfirmException extends BusinessException {

    private static final long serialVersionUID = -5151677241626382840L;

    public WithoutAuthAndConfirmException() {
        super();
    }

    public WithoutAuthAndConfirmException(Result ret) {
        super(ret);
    }

    public WithoutAuthAndConfirmException(Results rets) {
        super(rets);
    }

    public WithoutAuthAndConfirmException(String code, String msg) {
        super(code, msg);
    }

    public WithoutAuthAndConfirmException(Results rets, Throwable cause) {
        super(rets, cause);
    }

    public WithoutAuthAndConfirmException(Result ret, Throwable cause) {
        super(ret, cause);
    }

    public WithoutAuthAndConfirmException(String code, String msg,
                                          Throwable cause) {
        super(code, msg, cause);
    }
}
