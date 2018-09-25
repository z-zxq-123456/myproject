/**   
 * <p>Title: ExecutedJob.java</p>
 * <p>Description: 执行Job，存放worker执行中或者已经执行过的Job信息</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 * @author Tim
 * @update 2014年9月15日 下午2:02:19
 * @version V1.0   
 */

package com.dcits.orion.schedule.model;

import com.dcits.orion.schedule.context.JobContext;
import com.dcits.orion.schedule.job.JobRunType;
import com.dcits.orion.schedule.job.JobStatus;
import com.dcits.orion.schedule.job.Listenner;

/**
 * @description 执行Job，存放worker执行中或者已经执行过的Job信息
 * @version V1.0
 * @author Tim
 * @update 2014年9月15日 下午3:21:44
 */

public class ExecutedJob extends AbstractJob {

	/**
	 * Job组序号
	 */
	private String groupId;

	/**
	 * 工作描述
	 */
	private String workDescription;

	/**
	 * 工作名
	 */
	private String workName;

	/**
	 * 执行日期
	 */
	private String runDate;

	/**
	 * 父Job名
	 */
	private String superJobName;

	/**
	 * Job上下文
	 */
	private JobContext ac;

	/**
	 * Job序号
 	 */
	protected String jobId;

	/**
	 * Hadoop 应用Id
	 */
	private String applicationId;

	private Listenner listenner;

	private JobStatus jobStatus = JobStatus.NEVER_EXECUTED;

	private JobRunType jobRunType;

	private long startedTime;

	private long finishedTime;

	private long elapsedTime;

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getSuperJobName() {
		return superJobName;
	}

	public void setSuperJobName(String superJobName) {
		this.superJobName = superJobName;
	}

	public JobContext getAc() {
		return ac;
	}

	public void setAc(JobContext ac) {
		// convert hadoop status to job status
		this.ac = ac;
		if (null != ac && null != ac.getState()) {
			switch (ac.getState()) {
			case "ACCEPTED":
				setJobStatus(JobStatus.RUNNING);
				break;
			case "RUNNING":
				setJobStatus(JobStatus.RUNNING);
				break;
			case "FINISHED":
				switch (ac.getFinalStatus()) {
				case "SUCCEEDED":
					setJobStatus(JobStatus.SUCCEEDED);
					break;
				case "FAILED":
					setJobStatus(JobStatus.FAILED);
					break;
				case "KILLED":
					setJobStatus(JobStatus.FAILED);
					break;
				default:
					setJobStatus(JobStatus.RUNNING);
					break;
				}
				break;
			case "FAILED":
				setJobStatus(JobStatus.FAILED);
				break;
			default:
				setJobStatus(JobStatus.UNKNOWN);
				break;
			}
		}
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public Listenner getListenner() {
		return listenner;
	}

	public void registerListenner(Listenner listenner) {
		this.listenner = listenner;
		listenner.updateJobInfo();
	}

	public JobStatus getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(JobStatus jobStatus) {
		this.jobStatus = jobStatus;
		if (this.jobStatus == JobStatus.BOOTING) {
			this.startedTime = System.currentTimeMillis();
		} else if (!jobStatus.isRunning()) {
			this.finishedTime = System.currentTimeMillis();
			this.elapsedTime = this.finishedTime - this.startedTime;
		}
	}

	public String getRunDate() {
		return runDate;
	}

	public void setRunDate(String runDate) {
		this.runDate = runDate;
	}

	public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

	public JobRunType getJobRunType() {
		return jobRunType;
	}

	public void setJobRunType(JobRunType jobRunType) {
		this.jobRunType = jobRunType;
	}

	public String getWorkDescription() {
		return workDescription;
	}

	public void setWorkDescription(String workDescription) {
		this.workDescription = workDescription;
	}

	/**
	 * @return startedTime : return the property startedTime.
	 */
	public long getStartedTime() {
		return startedTime;
	}

	/**
	 * @return finishedTime : return the property finishedTime.
	 */
	public long getFinishedTime() {
		return finishedTime;
	}

	/**
	 * @return elapsedTime : return the property elapsedTime.
	 */
	public long getElapsedTime() {
		return elapsedTime;
	}
}
