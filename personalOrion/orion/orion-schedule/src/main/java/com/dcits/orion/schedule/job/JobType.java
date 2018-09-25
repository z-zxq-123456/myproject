/**   
 * <p>Title: JobStatus.java</p>
 * <p>Description: Job类型<br>
 *              MR-mapreduce Job<br>
 *              SQOOP-Sqoop Job<br>
 *              HBASE-hbase job</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 * @author Tim
 * @update 2014年9月15日 下午2:02:19
 * @version V1.0   
 */

package com.dcits.orion.schedule.job;

/**
 * @description Job类型<br>
 *              MR-mapreduce Job<br>
 *              SQOOP-Sqoop Job<br>
 *              HBASE-Hbase job<br>
 *              JAVA-Java job
 * 
 * @version V1.0
 * @author Tim
 * @update 2014年9月15日 下午3:11:24
 */

public enum JobType {
	/**
	 * ETL Job.
	 */
	SQOOP,

	/**
	 * MapReduce Job.
	 */
	MR,

	/**
	 * HBASE Job.
	 */
	HBASE, 

	/**
	 * JAVA Job.
	 */
	JAVA, 

	/**
	 * Galaxy Job.
	 */
	GALAXY,;
}
