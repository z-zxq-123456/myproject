/**   
 * <p>Title: DxParam.java</p>
 * <p>Description: Sqoop Job参数配置</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 * @author Tim
 * @update 2014年9月15日 下午2:02:19
 * @version V1.0   
 */
package com.dcits.galaxy.dx.param;

import java.util.Properties;

import com.dcits.galaxy.dx.common.SCError;
import com.dcits.galaxy.dx.exception.SCException;

/**
 * @description Sqoop Job参数配置
 * @version V1.0
 * @author Tim
 * @update 2014年9月15日 下午2:17:43
 */

public class DxParam {

	/**
	 * The sqoop parameter container
	 */
	private Properties param = new Properties();

	/**
	 * Sqoop Server Url
	 */
	public final String SQOOP_SERVER_URL = "sqoop.url";

	/**
	 * Connection Name
	 */
	public final String CONNECTION_NAME = "connection.connectionName";

	/**
	 * JDBC Connection
	 */
	public final String CONNECTION_URL = "connection.connectionString";

	/**
	 * JDBC Driver
	 */
	public final String JDBC_DRIVER = "connection.jdbcDriver";

	/**
	 * JDBC username
	 */
	public final String USER_NAME = "connection.username";

	/**
	 * JDBC password
	 */
	public final String PASS_WORD = "connection.password";

	/**
	 * max connections
	 */
	public final String MAX_CONNECTIONS = "security.maxConnections";

	/**
	 * import job name
	 */
	public final String IMPORT_JOBNAME = "job.importJobName";

	/**
	 * import job name
	 */
	public final String EXPROT_JOBNAME = "job.exportJobName";

	/**
	 * schema name
	 */
	public final String SCHEMANAME = "table.schemaName";

	/**
	 * 要全量导出一张表，请填写表名，table name 和 table sql statement不能同时配置
	 */
	public final String TABLENAME = "table.tableName";

	/**
	 * 
	 * 如果填写格式必须为 select xxx from table_name where ${CONDITIONS}
	 */
	public final String SQL = "table.sql";

	/**
	 * table columns
	 */
	public final String COLUMNS = "table.columns";

	/**
	 * 使用哪个字段来填充过滤条件 userid
	 */
	public final String PARTITION_COLUMN = "table.partitionColumn";

	/**
	 * 写一个查询语句，返回值需为整形，sqoop运行job时，会自动填充${CONDITIONS} 这个占位符
	 */
	public final String BOUNDARY_QUERY = "table.boundaryQuery";

	public final String ROWS = "table.rows";

	/**
	 * 0 : HDFS
	 */
	public final String STORAGE_TYPE = "output.storageType";

	/**
	 * 0 : TEXT_FILE <br>
	 * 1 : SEQUENCE_FILE
	 */
	public final String OUTPUT_FORMAT = "output.outputFormat";

	/**
	 * 0 : NONE<br>
	 * 1 : DEFAULT<br>
	 * 2 : DEFLATE<br>
	 * 3 : GZIP<br>
	 * 4 : BZIP2<br>
	 * 5 : LZO<br>
	 * 6 : LZ4<br>
	 * 7 : SNAPPY
	 */
	public final String COMPRESSION_FORMAT = "output.compression";

	/**
	 * import HDFS directory
	 */
	public final String OUTPUT_DIRECTORY = "output.outputDirectory";

	/**
	 * export HDFS directory
	 */
	public final String INPUT_DIRECTORY = "input.inputDirectory";

	/**
	 * default 1
	 */
	public final String EXTRACTORS = "throttling.extractors";

	/**
	 * default 1
	 */
	public final String LOADERS = "throttling.loaders";

	public void setParam(String key, String value) {
		param.setProperty(key, value);
	}

	public String getParam(String key) {
		if (null == param.getProperty(key, null)) {
			throw new SCException(SCError.SC_001, "parameter [" + key
					+ "] must be defined!");
		}
		return param.getProperty(key, null);
	}

	public String getParam(String key, String defaultValue) {
		return param.getProperty(key, defaultValue);
	}
}
