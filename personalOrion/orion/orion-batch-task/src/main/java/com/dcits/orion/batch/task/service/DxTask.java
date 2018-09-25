package com.dcits.orion.batch.task.service;

import com.alibaba.druid.pool.DruidDataSource;
import com.dcits.orion.batch.api.bean.ITaskParam;
import com.dcits.orion.batch.common.BatchUtils;
import com.dcits.orion.schedule.common.GlobalConfiguration;
import com.dcits.orion.schedule.common.JobError;
import com.dcits.orion.schedule.exception.JobException;
import com.dcits.orion.schedule.jdbc.JDBCPartitioner;
import com.dcits.orion.schedule.model.DXJob;
import com.dcits.orion.schedule.model.Partition;
import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.galaxy.dx.client.ClientTools;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.sqoop.model.MConnection;
import org.mortbay.log.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by lixbb on 2015/11/17.
 */
public class DxTask extends AbstractTask {

    private static Logger logger = LoggerFactory.getLogger(DxTask.class);

    private ClientTools ct;

    private DXJob sj;

    //private JBDCConnection sjc;

    private MConnection mc;

    private long xid;

    private String jobName;

    private long execJobid;
    public  void runTask(ITaskParam parm) {
        if (!init(parm))
            return;

        loadHadoopConfiguration();

        sj = (DXJob) job;
        logger.debug(jc.getJobPro(GlobalConfiguration.SQOOP_SERVER_URL));
        ct = new ClientTools(jc.getJobPro(GlobalConfiguration.SQOOP_SERVER_URL));
        mc = ct.getConnection(parm.getShard().getId());
        //sjc = jc.getConnectionContainer().getConnection(sj.getConnection_name());

        //Log.debug(sjc.toString());
        DruidDataSource dataSource = (DruidDataSource)parm.getShard().getDataSource();
        ct.dxParam.setParam(ct.dxParam.CONNECTION_NAME,
                parm.getSchemaId());
        ct.dxParam.setParam(ct.dxParam.CONNECTION_URL, dataSource.getUrl());
        ct.dxParam.setParam(ct.dxParam.JDBC_DRIVER, dataSource.getDriverClassName());
        ct.dxParam.setParam(ct.dxParam.USER_NAME, dataSource.getUsername());
        ct.dxParam.setParam(ct.dxParam.PASS_WORD, dataSource.getPassword());
        ct.dxParam.setParam(ct.dxParam.MAX_CONNECTIONS, "0");
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
                    sj.getOutput_directory(BatchUtils.getRunDate(),parm.getShard().getId()));

            try {
                // 删除输出文件夹
                Path out = new Path(sj.getOutput_directory(BatchUtils.getRunDate(),param.getSchemaId()));

                FileSystem fs = out.getFileSystem(conf);

                if (fs.exists(out))
                    fs.delete(out, true);
            } catch (IOException e) {
                throw new JobException(JobError.JOB_004, e.getMessage());
            }

            // 根据记录数获取分段数
            JDBCPartitioner part = new JDBCPartitioner();
            Partition p = null;
            try {
                p = part.getPartitions(parm.getShard().getDataSource().getConnection(),
                        sj.getBoundary_query(), Long.valueOf(sj.getRows()));
            } catch (SQLException e) {
                throw new GalaxyException(e);
            }
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
                    sj.getInput_directory(BatchUtils.getRunDate()));
            ct.dxParam.setParam(ct.dxParam.EXTRACTORS, sj.getExtractors());
            Log.debug("EXTRACTORS[" + sj.getExtractors() + "]");
            ct.dxParam.setParam(ct.dxParam.LOADERS, sj.getLoaders());
            Log.debug("LOADERS[" + sj.getLoaders() + "]");

            execJobid = ct.forceCreateExportJob(xid);
            ct.descrbJob(execJobid);
        }

        // submit job
        setAppId(ct.jobSubmission(execJobid).replace("job",
                "application"),parm);

    }


}
