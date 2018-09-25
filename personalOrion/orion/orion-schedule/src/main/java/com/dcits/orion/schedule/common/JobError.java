/**   
 * <p>Title: JobError.java</p>
 * <p>Description: Job相关异常错误信息定义，实现接口</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 * @author Tim
 * @update 2014年9月15日 下午2:02:19
 * @version V1.0   
 */

package com.dcits.orion.schedule.common;

import org.apache.sqoop.common.ErrorCode;


/**
 * @description Job相关异常错误信息定义，实现接口
 * @version V1.0
 * @author Tim
 * @update 2014年9月15日 下午2:49:15 
 */

public enum JobError implements ErrorCode {
	JOB_001("Job is not defined,please check job-define.xml"), 
	JOB_002("Job is in execution,please later execution."), 
	JOB_003("Parameter is not defined,please check job.properties."), 
	JOB_004("Job execution failed."), 
	JOB_005("Job defined error."), 
	JOB_006("Loading Hadoop configuration error."), 
	JOB_007("Parameter is not defined,please check connection-define.xml."), 
	JOB_008("Parameter is not defined,please check job-schedule.xml."), 
	JOB_009("Please input Worker Name."), 
	JOB_010("Insert database error."),
	JOB_011("Update database error."),
	JOB_012("Delete database error."),
	JOB_013("Query database error."),
	JOB_014("Close database connection error."),
	JOB_015("Create database connection error."),
	JOB_016("Database exception."),
	JOB_017("Parameter date pattern must be [yyyyMMdd]"),;

	private final String message;

	private JobError(String message) {
		this.message = message;
	}

	public String getCode() {
		return name();
	}

	public String getMessage() {
		return this.message;
	}
}
