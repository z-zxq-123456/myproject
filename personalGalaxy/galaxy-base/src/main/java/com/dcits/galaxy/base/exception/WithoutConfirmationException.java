package com.dcits.galaxy.base.exception;

import com.dcits.galaxy.base.data.Result;
import com.dcits.galaxy.base.data.Results;

/**
 * 未经确认异常
 * 
 * @author xuecy
 *
 */

public class WithoutConfirmationException extends BusinessException {

	private static final long serialVersionUID = 3177880168761190422L;

	public WithoutConfirmationException() {
		super();
	}

	public WithoutConfirmationException(Result ret) {
		super(ret);
	}

	public WithoutConfirmationException(Results rets) {
		super(rets);
	}

	public WithoutConfirmationException(String code, String msg) {
		super(code, msg);
	}

	public WithoutConfirmationException(Results rets, Throwable cause) {
		super(rets, cause);
	}

	public WithoutConfirmationException(Result ret, Throwable cause) {
		super(ret, cause);
	}

	public WithoutConfirmationException(String code, String msg, Throwable cause) {
		super(code, msg, cause);
	}

}
