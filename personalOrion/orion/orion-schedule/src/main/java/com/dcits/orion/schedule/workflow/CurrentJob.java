/**   
 * <p>Title: CurrentJob.java</p>
 * <p>Description: 存放当前正在执行的Job</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 * @author Tim
 * @update 2014年9月15日 下午2:02:19
 * @version V1.0   
 */

package com.dcits.orion.schedule.workflow;

import java.util.Map;
import java.util.TreeMap;

import com.dcits.orion.schedule.model.ExecutedJob;

/**
 * @description 存放当前正在执行的Job
 * @version V1.0
 * @author Tim
 * @update 2014年9月15日 下午3:30:33 
 */

public class CurrentJob {
	private Map<String, ExecutedJob> cjob = new TreeMap<>();

	public Map<String, ExecutedJob> getJobs() {
		return cjob;
	}

	public ExecutedJob getJob(String jobId) {
		return cjob.get(jobId);
	}

	public void putJob(String jobId, ExecutedJob job) {
		this.cjob.put(jobId, job);
	}

	public void removeJob(String jobId) {
		this.cjob.remove(jobId);
	}
}
