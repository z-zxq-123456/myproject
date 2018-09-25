/**   
 * <p>Title: ParamError.java</p>
 * <p>Description: 参数相关异常错误信息定义</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 * @author Tim
 * @update 2014年9月15日 下午2:02:19
 * @version V1.0   
 */

package com.dcits.orion.schedule.common;

import org.apache.sqoop.common.ErrorCode;


/**
 * @description 参数相关异常错误信息定义
 * @version V1.0
 * @author Tim
 * @update 2014年9月15日 下午2:52:35 
 */

public enum ParamError implements ErrorCode {
	P_001("Parse Xml File fail."), 
	P_002("Parse Properties File fail."), 
	P_003("Init parameter exception."), 
	P_004("Init velocity exception."),;

	private final String message;

	private ParamError(String message) {
		this.message = message;
	}

	public String getCode() {
		return name();
	}

	public String getMessage() {
		return this.message;
	}
}
