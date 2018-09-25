/**   
 * <p>Title: ApplicationContext.java</p>
 * <p>Description: Hadoop Job上下文<br>
 *              存放hadoop集群application相关信息<br>
 *              由AppliacationListenner同步更新</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 * @author Tim
 * @update 2014年9月15日 下午2:02:19
 * @version V1.0   
 */

package com.dcits.orion.schedule.context;

/**
 * @description Job上下文<br>
 *              存放hadoop集群application相关信息<br>
 *              由AppliacationListenner同步更新<br>
 *              Galaxy Job和Java Job的执行信息
 * @version V1.0
 * @author Tim
 * @update 2014年9月15日 下午3:18:39
 */

public class JobContext extends AbstractContext {

	private String id;

	private String user;

	private String name;

	private String queue;

	private String state;

	private String finalStatus;

	private String progress;

	private String trackingUI;

	private String diagnostics;

	private String clusterId;

	private String startedTime;

	private String finishedTime;

	private String elapsedTime;

	private String amContainerLogs;

	private String amHostHttpAddress;

	private String javaClassName;

	private String exception;

	private String message;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQueue() {
		return queue;
	}

	public void setQueue(String queue) {
		this.queue = queue;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getFinalStatus() {
		return finalStatus;
	}

	public void setFinalStatus(String finalStatus) {
		this.finalStatus = finalStatus;
	}

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}

	public String getTrackingUI() {
		return trackingUI;
	}

	public void setTrackingUI(String trackingUI) {
		this.trackingUI = trackingUI;
	}

	public String getDiagnostics() {
		return diagnostics;
	}

	public void setDiagnostics(String diagnostics) {
		this.diagnostics = diagnostics;
	}

	public String getClusterId() {
		return clusterId;
	}

	public void setClusterId(String clusterId) {
		this.clusterId = clusterId;
	}

	public String getStartedTime() {
		return startedTime;
	}

	public void setStartedTime(String startedTime) {
		this.startedTime = startedTime;
	}

	public String getFinishedTime() {
		return finishedTime;
	}

	public void setFinishedTime(String finishedTime) {
		this.finishedTime = finishedTime;
	}

	public String getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(String elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	public String getAmContainerLogs() {
		return amContainerLogs;
	}

	public void setAmContainerLogs(String amContainerLogs) {
		this.amContainerLogs = amContainerLogs;
	}

	public String getAmHostHttpAddress() {
		return amHostHttpAddress;
	}

	public void setAmHostHttpAddress(String amHostHttpAddress) {
		this.amHostHttpAddress = amHostHttpAddress;
	}

	public String getJavaClassName() {
		return javaClassName;
	}

	public void setJavaClassName(String javaClassName) {
		this.javaClassName = javaClassName;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
