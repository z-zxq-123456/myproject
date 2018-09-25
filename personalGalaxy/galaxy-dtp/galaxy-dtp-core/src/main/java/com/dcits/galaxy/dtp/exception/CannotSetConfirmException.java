package com.dcits.galaxy.dtp.exception;

public class CannotSetConfirmException extends DTPException {

	private static final long serialVersionUID = 2639909493363973438L;

	public CannotSetConfirmException() {
        super();
    }
	
	public CannotSetConfirmException(String message) {
		super(message);
	}
	
	public CannotSetConfirmException(String message, Throwable cause) {
        super(message, cause);
    }
	
	public CannotSetConfirmException(Throwable cause) {
        super(cause);
    }
}
