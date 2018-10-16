package com.dcits.ensemble.om.pf.exception;

public class BusiException extends RuntimeException{

	private static final long serialVersionUID = 7102904734006713283L;
	private String errorCode;
	
	public BusiException(){
		super();
	}
	
	public BusiException(String errorCode){
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
