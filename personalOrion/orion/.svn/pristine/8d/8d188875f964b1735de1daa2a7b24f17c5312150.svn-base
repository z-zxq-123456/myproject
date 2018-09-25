/**   
 * <p>Title: AbstractJob.java</p>
 * <p>Description: 抽象Job父类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 * @author Tim
 * @update 2014年9月15日 下午2:02:19
 * @version V1.0   
 */

package com.dcits.orion.schedule.model;

import com.dcits.orion.schedule.job.JobType;
import com.dcits.galaxy.base.util.BeanUtils;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @description 抽象Job父类
 * @version V1.0
 * @author Tim
 * @update 2014年9月15日 下午3:18:02
 */

@XStreamAlias("job")
public abstract class AbstractJob {

	@XStreamAlias("job_name")
	protected String name;

	@XStreamAlias("job_type")
	protected String type;

	protected String job_jars;

	private String description;

	private Configuration configuration;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public JobType getType() {
		switch (type.toUpperCase()) {
		case "SQOOP":
			return JobType.SQOOP;
		case "MR":
			return JobType.MR;
		case "HBASE":
			return JobType.HBASE;
		case "JAVA":
			return JobType.JAVA;
		case "GALAXY":
			return JobType.GALAXY;
		}
		return null;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getJob_jars() {
		return job_jars;
	}

	public void setJob_jars(String job_jars) {
		this.job_jars = job_jars;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Configuration getConfiguration() {
		return null == configuration ? new Configuration() : configuration;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	@Override
	public String toString() {
		return BeanUtils.getString(this);
	}
}
