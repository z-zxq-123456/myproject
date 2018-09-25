/**   
 * <p>Title: JobException.java</p>
 * <p>Description: 操作数据库异常类，Job执行过程中抛出此异常</p>
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
 * @description 操作数据库异常类，Job执行过程中抛出此异常
 * @version V1.0
 * @author Tim
 * @update 2014年9月15日 下午2:55:42 
 */

@SuppressWarnings("serial")
public class DBException extends GalaxyException {

	private final ErrorCode code;

	public DBException(ErrorCode code) {
		super(code.getCode() + ":" + code.getMessage());
		this.code = code;
	}

	public DBException(ErrorCode code, String extraInfo) {
		super(code.getCode() + ":" + code.getMessage() + " - " + extraInfo);
		this.code = code;
	}

	public DBException(ErrorCode code, Throwable cause) {
		super(code.getCode() + ":" + code.getMessage(), cause);
		this.code = code;
	}

	public DBException(ErrorCode code, String extraInfo,
			Throwable cause) {
		super(code.getCode() + ":" + code.getMessage() + " - " + extraInfo,
				cause);
		this.code = code;
	}

	public ErrorCode getErrorCode() {
		return code;
	}
}