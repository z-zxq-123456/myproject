/**   
 * <p>Title: JobConfiguration.java</p>
 * <p>Description: 工作流平台参数容器 负责加载job配置<br>
 *              connection配置<br>
 *              workflow配置<br>
 *              以及job.properties参数配置</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 * @author Tim
 * @update 2014年9月15日 下午2:02:19
 * @version V1.0   
 */

package com.dcits.orion.schedule.common;

import com.dcits.orion.schedule.exception.JobException;
import com.dcits.orion.schedule.model.ConnectionContainer;
import com.dcits.orion.schedule.model.JobContainer;
import com.dcits.orion.schedule.model.WorkFlow;
import com.dcits.orion.schedule.model.Worker;
import com.dcits.galaxy.base.util.ClassLoaderUtils;
import com.dcits.galaxy.base.util.XmlUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Properties;

/**
 * @description 工作流平台参数容器 负责加载job配置<br>
 *              connection配置<br>
 *              workflow配置<br>
 *              以及job.properties参数配置
 * 
 * @version V1.0
 * @author Tim
 * @update 2014年9月15日 下午2:47:24
 */

public class JobConfiguration {

	private WorkFlow wf;

	private JobContainer jc;

	private ConnectionContainer cc;

	private Properties jpro;

	private static JobConfiguration jcf;

	private static Logger logger = LoggerFactory.getLogger(JobConfiguration.class);

	public static synchronized JobConfiguration getInstance() {
		if (null == jcf) {
			jcf = new JobConfiguration();
			jcf.initParam();
		}
		return jcf;
	}

	private void initParam() {

			try
			{
				jc = XmlUtils.toBeanFromFile("job-define.xml", JobContainer.class);
				jc.init();
			}
			catch (Exception e)
			{
				if (logger.isWarnEnabled())
					logger.warn("job-define.xml load failed!");
			}
			try {
				cc = XmlUtils.toBeanFromFile("connection-define.xml",
						ConnectionContainer.class);
				wf = XmlUtils.toBeanFromFile("job-schedule.xml", WorkFlow.class);
			}
			catch (Exception e)
			{
				if (logger.isWarnEnabled())
					logger.warn("connection-define.xml or job-schedule.xml load failed!");
			}
			try
			{
				jpro = ClassLoaderUtils.getProperties("job.properties");

			}
			catch (Exception e)
			{
				if (logger.isWarnEnabled())
					logger.warn("job.properties load failed!");

			}


	}

	public void refreshParam() {
		initParam();
	}

	public WorkFlow getWorkFolw() {
		return wf;
	}

	public List<Worker> getWorkers() {
		return wf.getWorker();
	}

	public JobContainer getJobContainer() {
		return jc;
	}

	public ConnectionContainer getConnectionContainer() {
		return cc;
	}

	public Properties getJobPro() {
		return jpro;
	}

	public boolean JobProContainsKey(String key) {
		return jpro.containsKey(key);
	}

	public String getJobPro(String key) {
		String pro = jpro.getProperty(key, null);
		if (null == pro)
			throw new JobException(JobError.JOB_003, "Key [" + key + "]");
		return pro;
	}
}
