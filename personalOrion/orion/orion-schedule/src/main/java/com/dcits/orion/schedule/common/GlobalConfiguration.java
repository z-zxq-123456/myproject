/**   
 * <p>Title: GlobalConfiguration.java</p>
 * <p>Description: 全局静态参数配置</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 * @author Tim
 * @update 2014年9月15日 下午2:02:19
 * @version V1.0   
 */

package com.dcits.orion.schedule.common;

/**
 * @description 全局静态参数配置
 * @version V1.0
 * @author Tim
 * @update 2014年9月15日 下午2:46:52
 */

/**
 * @description 
 * @version V1.0
 * @author Tim
 * @update 2015年3月12日 上午10:53:43 
 */

public class GlobalConfiguration {

	public static final String ETL_PROCESS = "com.dcist.job.etl.process";

	public static final String MR_PROCESS = "com.dcist.job.mr.process";

	public static final String HBASE_PROCESS = "com.dcist.job.hbase.process";

	public static final String GALAXY_PROCESS = "com.dcist.job.galaxy.process";

	public static final String JOB_HADOOP_LISTENNER = "com.dcist.job.hadoop.listenner";

	public static final String JOB_GALAXY_LISTENNER = "com.dcist.job.galaxy.listenner";

	public static final String SQOOP_SERVER_URL = "com.dcist.job.sqoop.server.url";

	public static final String CONF_CONFIG_DIR = "com.dcist.job.hadoop.configuration.directory";

	public static final String HBAE_CONF_CONFIG_DIR = "com.dcist.job.hbase.configuration.directory";

	public static final String HADOOP_AM_URL = "com.dcist.job.hadoop.am.url";

	public static final String JOB_PERSIST_IMPL = "com.dcist.job.persist.impl";

	public static final String JOB_RESULTS_PATH = "jobResults";

	/**
	 * 获取结果间隔
	 *  
	 * @fields BITAIN_RESULT 
	 */ 
	public static final String INTERVAL_BITAIN_RESULT = "com.dcist.job.interval.obtain.result";

	public static final String DES_KEY = "schedule";

}
