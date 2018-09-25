/**   
 * <p>Title: WorkFlow.java</p>
 * <p>Description: 工作者容器，定义同时刻并行的Worker，以及Worker集合</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 * @author Tim
 * @update 2014年9月15日 下午2:02:19
 * @version V1.0   
 */

package com.dcits.orion.schedule.model;

import java.util.ArrayList;
import java.util.List;

import com.dcits.orion.schedule.common.JobError;
import com.dcits.orion.schedule.exception.JobException;
import com.dcits.galaxy.base.util.BeanUtils;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * @description 工作者容器，定义Worker集合
 * @version V1.0
 * @author Tim
 * @update 2014年9月15日 下午3:27:08
 */

@XStreamAlias("workflow")
public class WorkFlow {

	private String parallel_worker;

	@XStreamImplicit(itemFieldName = "worker")
	private List<Worker> worker = new ArrayList<Worker>();

	public String getParallel_worker() {
		return parallel_worker;
	}

	public void setParallel_worker(String parallel_worker) {
		this.parallel_worker = parallel_worker;
	}

	public List<Worker> getWorker() {
		return worker;
	}

	public Worker getWorker(String workername) {
		Worker wker = null;
		if (null == worker)
			throw new JobException(JobError.JOB_008);
		for (Worker wk : worker) {
			if (workername.equals(wk.getName())) {
				wker = wk;
				break;
			}
		}
		if (null == wker)
			throw new JobException(JobError.JOB_008, "Worker Name ["
					+ workername + "]");

		return wker;
	}

	public void setWorker(List<Worker> worker) {
		this.worker = worker;
	}

	@Override
	public String toString() {
		return BeanUtils.getString(this);
	}
}
