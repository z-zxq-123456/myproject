/**   
 * <p>Title: SystemException.java</p>
 * <p>Description: 参数异常类，读取参数执行过程中抛出此异常</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 * @author Tim
 * @update 2014年9月15日 下午2:02:19
 * @version V1.0   
 */

package com.dcits.orion.schedule.exception;

import org.apache.sqoop.common.ErrorCode;

import com.dcits.galaxy.base.exception.GalaxyException;

/**
 * @description 参数异常类，读取参数执行过程中抛出此异常
 * @version V1.0
 * @author Tim
 * @update 2014年9月15日 下午2:56:02
 */

@SuppressWarnings("serial")
public class ParamException extends GalaxyException {

	private final ErrorCode code;

	public ParamException(ErrorCode code) {
		super(code.getCode() + ":" + code.getMessage());
		this.code = code;
	}

	public ParamException(ErrorCode code, String extraInfo) {
		super(code.getCode() + ":" + code.getMessage() + " - " + extraInfo);
		this.code = code;
	}

	public ParamException(ErrorCode code, Throwable cause) {
		super(code.getCode() + ":" + code.getMessage(), cause);
		this.code = code;
	}

	public ParamException(ErrorCode code, String extraInfo, Throwable cause) {
		super(code.getCode() + ":" + code.getMessage() + " - " + extraInfo,
				cause);
		this.code = code;
	}

	public ErrorCode getErrorCode() {
		return code;
	}
}