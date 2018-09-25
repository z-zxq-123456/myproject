package com.dcits.galaxy.base.exception;

import com.dcits.galaxy.base.data.Result;
import com.dcits.galaxy.base.data.Results;

/**
 * 未经授权异常
 * 
 * @author xuecy
 * 
 */
public class WithoutAuthorizationException extends BusinessException {

	private static final long serialVersionUID = -5533270369609879192L;

	public WithoutAuthorizationException() {
		super();
	}

	public WithoutAuthorizationException(Result ret) {
		super(ret);
	}

	public WithoutAuthorizationException(Results rets) {
		super(rets);
	}

	public WithoutAuthorizationException(String code, String msg) {
		super(code, msg);
	}

	public WithoutAuthorizationException(Results rets, Throwable cause) {
		super(rets, cause);
	}

	public WithoutAuthorizationException(Result ret, Throwable cause) {
		super(ret, cause);
	}

	public WithoutAuthorizationException(String code, String msg,
			Throwable cause) {
		super(code, msg, cause);
	}

}
