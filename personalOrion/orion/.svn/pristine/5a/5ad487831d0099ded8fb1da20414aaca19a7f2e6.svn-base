/**
 * <p>Title: ETLProcess.java</p>
 * <p>Description: 数据抽取Job处理，通过ClientTools完成Sqoop Job执行</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 *
 * @author Tim
 * @update 2014年9月15日 下午2:02:19
 * @version V1.0
 */

package com.dcits.orion.schedule.process;

import com.dcits.orion.schedule.common.GlobalConfiguration;
import com.dcits.orion.schedule.common.JobError;
import com.dcits.orion.schedule.exception.JobException;
import com.dcits.orion.schedule.jdbc.JDBCPartitioner;
import com.dcits.orion.schedule.job.JobType;
import com.dcits.orion.schedule.model.AbstractJob;
import com.dcits.orion.schedule.model.DXJob;
import com.dcits.orion.schedule.model.JBDCConnection;
import com.dcits.orion.schedule.model.Partition;
import com.dcits.galaxy.dx.client.ClientTools;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.sqoop.model.MConnection;
import org.mortbay.log.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author Tim
 * @version V1.0
 * @description 数据抽取Job处理，通过ClientTools完成Sqoop Job执行
 * @update 2014年9月15日 下午3:28:48
 */

public class DXProcess extends AbstractProcess {

    private static Logger logger = LoggerFactory.getLogger(DXProcess.class);

    private ClientTools ct;

    private DXJob sj;

    private JBDCConnection sjc;

    private MConnection mc;

    private long xid;

    private String jobName;

    private long execJobid;

    public DXProcess(String jobId, AbstractJob job) {
        super(jobId, job);
    }

    @Override
    public void init() {
        logger.debug("jobId[" + jobId + "]\n" + job);
        if (!job.getType().equals(JobType.SQOOP)) {
            throw new JobException(JobError.JOB_005, "JobType define["
                    + job.getType() + "],expect[" + JobType.SQOOP + "]");
        }
    }

    @Override
    public void process() {
        loadHadoopConfiguration();
        sj = (DXJob) job;
        logger.debug(jc.getJobPro(GlobalConfiguration.SQOOP_SERVER_URL));
        ct = new ClientTools(jc.getJobPro(GlobalConfiguration.SQOOP_SERVER_URL));
        mc = ct.getConnection(sj.getConnection_name());
        sjc = jc.getConnectionContainer()
                .getConnection(sj.getConnection_name());

        Log.debug(sjc.toString());
        ct.dxParam.setParam(ct.dxParam.CONNECTION_NAME,
                sjc.getConnection_name());
        ct.dxParam.setParam(ct.dxParam.CONNECTION_URL, sjc.getConnection_url());
        ct.dxParam.setParam(ct.dxParam.JDBC_DRIVER, sjc.getJdbc_driver());
        ct.dxParam.setParam(ct.dxParam.USER_NAME, sjc.getUser_name());
        ct.dxParam.setParam(ct.dxParam.PASS_WORD, sjc.getPass_word());
        if (null == sjc.getMax_connections())
            ct.dxParam.setParam(ct.dxParam.MAX_CONNECTIONS, "0");
        else
            ct.dxParam.setParam(ct.dxParam.MAX_CONNECTIONS,
                    sjc.getMax_connections());
        if (null == mc) {
            xid = ct.createConnection();
            if (xid != -1)
                ct.descrbConnection(xid);
        } else {
            xid = ct.updateConnection(mc);
            ct.descrbConnection(xid);
        }

        Log.debug(sj.toString());
        jobName = sj.getName();
        if ("import".equalsIgnoreCase(sj.getOperate_type())) {
            // job1
            ct.dxParam.setParam(ct.dxParam.IMPORT_JOBNAME, jobName);
            ct.dxParam.setParam(ct.dxParam.SQL, sj.getSql());
            ct.dxParam.setParam(ct.dxParam.BOUNDARY_QUERY,
                    sj.getBoundary_query());
            ct.dxParam.setParam(ct.dxParam.PARTITION_COLUMN,
                    sj.getPartition_column());
            ct.dxParam.setParam(ct.dxParam.COMPRESSION_FORMAT, "NONE");
            ct.dxParam.setParam(ct.dxParam.OUTPUT_DIRECTORY,
                    sj.getOutput_directory(wm.getJob(jobId).getRunDate()));

            try {
                // 删除输出文件夹
                Path out = new Path(sj.getOutput_directory(wm.getJob(jobId).getRunDate()));
                FileSystem fs = out.getFileSystem(conf);
                if (fs.exists(out))
                    fs.delete(out, true);
            } catch (IOException e) {
                throw new JobException(JobError.JOB_004, e.getMessage());
            }

            // 根据记录数获取分段数
            JDBCPartitioner part = new JDBCPartitioner();
            Partition p = part.getPartitions(sjc.getConnection_name(),
                    sj.getBoundary_query(), Long.valueOf(sj.getRows()));
            if (null == p) {
                if (null != sj.getExtractors()) {
                    ct.dxParam.setParam(ct.dxParam.EXTRACTORS,
                            sj.getExtractors());
                    Log.debug("EXTRACTORS[" + sj.getExtractors() + "]");
                }
                if (null != sj.getLoaders()) {
                    ct.dxParam.setParam(ct.dxParam.LOADERS, sj.getLoaders());
                    Log.debug("LOADERS[" + sj.getLoaders() + "]");
                }
            } else {
                ct.dxParam.setParam(ct.dxParam.EXTRACTORS, p.getExtractors());
                Log.debug("EXTRACTORS[" + p.getExtractors() + "]");
                ct.dxParam.setParam(ct.dxParam.LOADERS, p.getLoaders());
                Log.debug("LOADERS[" + p.getLoaders() + "]");
            }

            execJobid = ct.forceCreateImportJob(xid);
            ct.descrbJob(execJobid);
        } else if ("export".equalsIgnoreCase(sj.getOperate_type())) {
            // job1
            ct.dxParam.setParam(ct.dxParam.EXPROT_JOBNAME, jobName);
            ct.dxParam.setParam(ct.dxParam.TABLENAME, sj.getTable());
            ct.dxParam.setParam(ct.dxParam.COLUMNS, sj.getColumns());
            ct.dxParam.setParam(ct.dxParam.INPUT_DIRECTORY,
                    sj.getInput_directory(wm.getJob(jobId).getRunDate()));
            ct.dxParam.setParam(ct.dxParam.EXTRACTORS, sj.getExtractors());
            Log.debug("EXTRACTORS[" + sj.getExtractors() + "]");
            ct.dxParam.setParam(ct.dxParam.LOADERS, sj.getLoaders());
            Log.debug("LOADERS[" + sj.getLoaders() + "]");

            execJobid = ct.forceCreateExportJob(xid);
            ct.descrbJob(execJobid);
        }

        // submit job
        setApplicationId(ct.jobSubmission(execJobid).replace("job",
                "application"));
    }
}
