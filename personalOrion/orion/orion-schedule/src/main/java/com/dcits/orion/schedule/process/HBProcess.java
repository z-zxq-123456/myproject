/**
 * <p>Title: HBASEProcess.java</p>
 * <p>Description: Hbase Job处理</p>
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
import com.dcits.orion.schedule.job.JobType;
import com.dcits.orion.schedule.model.AbstractJob;
import com.dcits.orion.schedule.model.HBJob;
import com.dcits.galaxy.base.util.ExceptionUtils;
import com.dcits.galaxy.base.util.StringUtils;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.Job;
import org.mortbay.log.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FilenameFilter;
import java.net.MalformedURLException;

/**
 * @author Tim
 * @version V1.0
 * @description Hbase Job处理
 * @update 2014年9月15日 下午3:29:27
 */

public class HBProcess extends AbstractProcess {

    private static Logger logger = LoggerFactory.getLogger(HBProcess.class);

    public HBProcess(String jobId, AbstractJob job) {
        super(jobId, job);
    }

    @Override
    public void init() {
        logger.debug("jobId[" + jobId + "]\n" + job);
        if (!job.getType().equals(JobType.HBASE)) {
            throw new JobException(JobError.JOB_005, "JobType define["
                    + job.getType() + "],expect[" + JobType.HBASE + "]");
        }
        loadHbaseConfiguration();
        loadHadoopConfiguration();
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void process() {
        try {
            HBJob hj = (HBJob) job;
            Job j = getDefaultJob();

            Log.debug(hj.toString());
            if (null != hj.getMapperTable()) {
                Scan scan = new Scan();
                if (StringUtils.isEmpty(hj.getMapperScanCaching()))
                    scan.setCaching(500);
                else
                    scan.setCaching(Integer.parseInt(hj.getMapperScanCaching()));

                if (StringUtils.isEmpty(hj.getMapperScanCacheBlocks()))
                    scan.setCacheBlocks(false);
                else
                    scan.setCacheBlocks(Boolean.parseBoolean(hj.getMapperScanCacheBlocks()));

                //前缀过滤器
                if (!StringUtils.isEmpty(hj.getMapperScanPrefixFilter())) {
                    scan.setFilter(new PrefixFilter(
                            hj.getMapperScanPrefixFilter(
                                    wm.getJob(jobId).getRunDate()).getBytes()));
                }

                TableMapReduceUtil.initTableMapperJob(hj.getMapperTable(),
                        scan, new SubClazz<TableMapper>().getClazz(hj
                                .getMapperClass()), new SubClazz<Writable>()
                                .getClazz(hj.getMapperOutputKey()),
                        new SubClazz<Writable>().getClazz(hj
                                .getMapperOutputValue()), j);
            }

            if (null != hj.getReducerTable())
                TableMapReduceUtil.initTableReducerJob(hj.getReducerTable(),
                        null, j);
            j.submit();
            String applicationId = j.getJobID().toString();
            logger.debug("ApplicationId[" + applicationId + "]");
            setApplicationId(applicationId);
        } catch (Exception e) {
            throw new JobException(JobError.JOB_004,
                    ExceptionUtils.getStackTrace(e));
        }
    }

    private void loadHbaseConfiguration() {
        conf = HBaseConfiguration.create();
        String configDirectory = jc
                .getJobPro(GlobalConfiguration.HBAE_CONF_CONFIG_DIR);
        File dir = new File(configDirectory);
        String[] files = dir.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.equals("hbase-site.xml");
            }
        });

        if (files == null) {
            throw new JobException(
                    JobError.JOB_006,
                    "Invalid Hbase configuration directory (not a directory or permission issues): "
                            + configDirectory);
        }

        for (String file : files) {
            logger.info("Found hadoop configuration file " + file);
            try {
                conf.addResource(new File(configDirectory, file).toURI()
                        .toURL());
            } catch (MalformedURLException e) {
                logger.error("Can't load configuration file: " + file, e);
            }
        }
    }
}
