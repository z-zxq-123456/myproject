package com.dcits.galaxy.dtp.exception;

import com.dcits.galaxy.base.exception.GalaxyException;

/**
 * 事务异常类
 * @author Yin.Weicai
 *
 */
public class DTPException extends GalaxyException {
	
	private static final long serialVersionUID = 8692943374750475107L;
	
	public DTPException() {
        super();
    }
	
	public DTPException(String message) {
		super(message);
	}
	
	public DTPException(String message, Throwable cause) {
        super(message, cause);
    }
	
	public DTPException(Throwable cause) {
        super(cause);
    }
	
}
