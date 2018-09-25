/**   
 * <p>Title: Listenner.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 * @author Tim
 * @update 2014年10月30日 下午2:37:46
 * @version V1.0
 */
package com.dcits.orion.schedule.listenner;

import com.dcits.orion.schedule.context.AbstractContext;
import com.dcits.orion.schedule.job.JobStatus;
import com.dcits.orion.schedule.job.Listenner;
import com.dcits.orion.schedule.model.ExecutedJob;

/**
 * @description Galaxy java服务listenner
 * @version V1.0
 * @author Tim
 * @update 2014年10月30日 下午2:37:46
 */

public class GalaxyListenner implements Listenner {

	private ExecutedJob ej;

	public GalaxyListenner(ExecutedJob job) {
		ej = job;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dcits.galaxy.schedule.job.Listenner#getJobInfo()
	 */
	@Override
	public AbstractContext getJobInfo() {
		return ej.getAc();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dcits.galaxy.schedule.job.Listenner#updateJobInfo()
	 */
	@Override
	public void updateJobInfo() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dcits.galaxy.schedule.job.Listenner#getJobStatus()
	 */
	@Override
	public JobStatus getJobStatus() {
		return ej.getJobStatus();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dcits.galaxy.schedule.job.Listenner#cancel()
	 */
	@Override
	public void cancel() {
	}

}
