/**   
 * <p>Title: JobException.java</p>
 * <p>Description: Job异常类，Job执行过程中抛出此异常</p>
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
 * @description Job异常类，Job执行过程中抛出此异常
 * @version V1.0
 * @author Tim
 * @update 2014年9月15日 下午2:55:42
 */

@SuppressWarnings("serial")
public class JobException extends GalaxyException {

	private final ErrorCode code;

	public JobException(ErrorCode code) {
		super(code.getCode() + ":" + code.getMessage());
		this.code = code;
	}

	public JobException(ErrorCode code, String extraInfo) {
		super(code.getCode() + ":" + code.getMessage() + " - " + extraInfo);
		this.code = code;
	}

	public JobException(ErrorCode code, Throwable cause) {
		super(code.getCode() + ":" + code.getMessage(), cause);
		this.code = code;
	}

	public JobException(ErrorCode code, String extraInfo, Throwable cause) {
		super(code.getCode() + ":" + code.getMessage() + " - " + extraInfo,
				cause);
		this.code = code;
	}

	public ErrorCode getErrorCode() {
		return code;
	}
}