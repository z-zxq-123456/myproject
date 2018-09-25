package com.dcits.galaxy.base.exception;

/**
 * 没有服务提供者异常
 * 
 * @author xuecy
 * 
 */
public class NoProviderException extends GalaxyException {

	private static final long serialVersionUID = -2194488465052083272L;

	public NoProviderException() {
	}

	public NoProviderException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoProviderException(String message) {
		super(message);
	}

	public NoProviderException(Throwable cause) {
		super(cause);
	}

}
