/**   
 * <p>Title: JobStatus.java</p>
 * <p>Description: Job执行状态定义<br>
 *              BOOTING-准备<br>
 *              FAILURE_ON_SUBMIT-Hadoop Job提交前失败<br>
 *              RUNNING-运行中<br>
 *              SUCCEEDED-成功<br>
 *              FAILED-失败<br>
 *              UNKNOWN-未知<br>
 *              NEVER_EXECUTED-未执行</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 * @author Tim
 * @update 2014年9月15日 下午2:02:19
 * @version V1.0   
 */

package com.dcits.orion.schedule.job;

/**
 * @description Job执行状态定义<br>
 *              BOOTING-准备<br>
 *              FAILURE_ON_SUBMIT-Hadoop Job提交前失败<br>
 *              RUNNING-运行中<br>
 *              SUCCEEDED-成功<br>
 *              FAILED-失败<br>
 *              UNKNOWN-未知<br>
 *              NEVER_EXECUTED-未执行
 * 
 * @version V1.0
 * @author Tim
 * @update 2014年9月15日 下午3:07:56
 */

public enum JobStatus {
	/**
	 * In the middle of creating new Job.
	 */
	BOOTING,

	/**
	 * We weren't able to submit this submission to remote cluster
	 */
	FAILURE_ON_SUBMIT,

	/**
	 * Job is running.
	 */
	RUNNING,

	/**
	 * Job has finished gracefully
	 */
	SUCCEEDED,

	/**
	 * Job has not finished gracefully, there were issues.
	 */
	FAILED,

	/**
	 * We have no idea in what state the submission actually is.
	 */
	UNKNOWN,

	/**
	 * Special Job type for job that was never executed.
	 */
	NEVER_EXECUTED, ;

	public static JobStatus[] unfinished() {
		return new JobStatus[] { RUNNING, BOOTING };
	}

	public boolean isRunning() {
		return this == RUNNING || this == BOOTING;
	}

	public boolean isFailure() {
		return this == FAILED || this == UNKNOWN || this == FAILURE_ON_SUBMIT;
	}
}
