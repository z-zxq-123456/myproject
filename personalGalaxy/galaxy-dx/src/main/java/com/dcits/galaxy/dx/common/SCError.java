/**   
 * <p>Title: SCError.java</p>
 * <p>Description: Dx客户端异常相关错误信息定义</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 * @author Tim
 * @update 2014年9月15日 下午2:02:19
 * @version V1.0   
 */
package com.dcits.galaxy.dx.common;

import org.apache.sqoop.common.ErrorCode;

/**
 * @description Dx客户端异常相关错误信息定义
 * @version V1.0
 * @author Tim
 * @update 2014年9月15日 下午2:29:49
 */

public enum SCError implements ErrorCode {
	SC_001("Invalid parameter key."), SC_002("Undefined parameters."), SC_003(
			"Connection  Name already exists."), SC_004(
			"Job  Name already exists."), SC_005(
			"Check for status and forms error."), SC_006(
			"Submission Job error."), ;

	private final String message;

	private SCError(String message) {
		this.message = message;
	}

	public String getCode() {
		return name();
	}

	public String getMessage() {
		return this.message;
	}
}
