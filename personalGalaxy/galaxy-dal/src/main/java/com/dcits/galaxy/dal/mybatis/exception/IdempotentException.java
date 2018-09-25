package com.dcits.galaxy.dal.mybatis.exception;


public class IdempotentException extends DALException {

	private static final long serialVersionUID = -2747982618282918450L;

	public IdempotentException() {
		super();
	}

	public IdempotentException(String message, Throwable cause) {
		super(message, cause);
	}

	public IdempotentException(String message) {
		super(message);
	}

	public IdempotentException(Throwable cause) {
		super(cause);
	}

}
