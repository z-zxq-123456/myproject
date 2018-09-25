/**   
 * <p>Title: Worker.java</p>
 * <p>Description: 定义每个工作者每个阶段的Job工作</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 * @author Tim
 * @update 2014年9月15日 下午2:02:19
 * @version V1.0   
 */

package com.dcits.orion.schedule.model;

import java.util.ArrayList;
import java.util.List;

import com.dcits.orion.schedule.common.JobConfiguration;
import com.dcits.orion.schedule.common.JobError;
import com.dcits.orion.schedule.exception.JobException;
import com.dcits.galaxy.base.util.BeanUtils;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * @description 定义每个工作者每个阶段的Job工作
 * @version V1.0
 * @author Tim
 * @update 2014年9月15日 下午3:26:06
 */

public class Worker {

	private String name;

	private String description;

	@XStreamAlias("begin")
	private WorkJob beginJob;

	@XStreamAlias("start")
	private WorkJob startJob;

	@XStreamAlias("error")
	private WorkJob errorJob;

	@XStreamAlias("end")
	private WorkJob endJob;

	private String runDate;

	public WorkJob getBeginJob() {
		return null == beginJob ? new WorkJob() : beginJob;
	}

	public void setBeginJob(WorkJob beginJob) {
		this.beginJob = beginJob;
	}

	public WorkJob getStartJob() {
		return null == startJob ? new WorkJob() : startJob;
	}

	public void setStartJob(WorkJob startJob) {
		this.startJob = startJob;
	}

	public WorkJob getErrorJob() {
		return null == errorJob ? new WorkJob() : errorJob;
	}

	public void setErrorJob(WorkJob errorJob) {
		this.errorJob = errorJob;
	}

	public WorkJob getEndJob() {
		return null == endJob ? new WorkJob() : endJob;
	}

	public void setEndJob(WorkJob endJob) {
		this.endJob = endJob;
	}

	@Override
	public String toString() {
		return BeanUtils.getString(this);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRunDate() {
		return runDate;
	}

	public void setRunDate(String runDate) {
		this.runDate = runDate;
	}
	/**
	 * @description Worker子类， Worker中Job定义集合
	 * @version V1.0
	 * @author Tim
	 * @update 2014年9月15日 下午3:26:15
	 */

	public class WorkJob {
		@XStreamImplicit(itemFieldName = "job")
		private List<String> jobname = new ArrayList<String>();

		public List<List<AbstractJob>> getJob() throws JobException {
			List<List<AbstractJob>> ll = new ArrayList<List<AbstractJob>>();
			if (null != jobname) {
				String[] jobs = null;
				for (String jbname : jobname) {
					jobs = jbname.split(",");
					List<AbstractJob> l = new ArrayList<AbstractJob>();
					for (String j : jobs) {
						AbstractJob aj = JobConfiguration.getInstance()
								.getJobContainer().getJob(j);
						if (null == aj) {
							throw new JobException(JobError.JOB_001,
									"Job Name[" + jbname + "]");
						}
						l.add(aj);
					}
					ll.add(l);
				}
			}
			return ll;
		}

		public void setJob(List<String> jobname) {
			this.jobname = jobname;
		}
	}
}
