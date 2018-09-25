/**   
 * <p>Title: SCException.java</p>
 * <p>Description: 数据抽取Sqoop客户端异常类，ClientTools会抛出此异常 </p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 * @author Tim
 * @update 2014年9月15日 下午2:02:19
 * @version V1.0   
 */

package com.dcits.galaxy.dx.exception;

import org.apache.sqoop.common.ErrorCode;

import com.dcits.galaxy.base.exception.GalaxyException;

/**
 * @description 数据抽取Sqoop客户端异常类，ClientTools会抛出此异常
 * @version V1.0
 * @author Tim
 * @update 2014年9月15日 下午2:32:04
 */

/**
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class SCException extends GalaxyException {

	private final ErrorCode code;

	public SCException(ErrorCode code) {
		super(code.getCode() + ":" + code.getMessage());
		this.code = code;
	}

	public SCException(ErrorCode code, String extraInfo) {
		super(code.getCode() + ":" + code.getMessage() + " - " + extraInfo);
		this.code = code;
	}

	public SCException(ErrorCode code, Throwable cause) {
		super(code.getCode() + ":" + code.getMessage(), cause);
		this.code = code;
	}

	public SCException(ErrorCode code, String extraInfo,
			Throwable cause) {
		super(code.getCode() + ":" + code.getMessage() + " - " + extraInfo,
				cause);
		this.code = code;
	}

	public ErrorCode getErrorCode() {
		return code;
	}
}