package com.dcits.galaxy.cache.exception;

public class CommandNotSupportException extends CacheException {

	private static final long serialVersionUID = 1L;

	public CommandNotSupportException() {
		super();
	}

	public CommandNotSupportException(String message, Throwable cause) {
		super(message, cause);
	}

	public CommandNotSupportException(String message) {
		super(message);
	}

	public CommandNotSupportException(Throwable cause) {
		super(cause);
	}
}
