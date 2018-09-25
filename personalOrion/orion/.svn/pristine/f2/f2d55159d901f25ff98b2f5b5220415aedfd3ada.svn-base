package com.dcits.orion.batch.task.service;

import com.dcits.orion.batch.api.bean.ITaskParam;
import com.dcits.orion.batch.common.BatchUtils;
import com.dcits.orion.schedule.common.GlobalConfiguration;
import com.dcits.orion.schedule.common.JobConfiguration;
import com.dcits.orion.schedule.common.JobError;
import com.dcits.orion.schedule.exception.JobException;
import com.dcits.orion.schedule.model.HBJob;
import com.dcits.orion.schedule.process.SubClazz;
import com.dcits.galaxy.base.exception.GalaxyException;
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
 * Created by lixbb on 2015/11/16.
 */
public class HbTask extends AbstractTask{

    private static Logger logger = LoggerFactory.getLogger(HbTask.class);
    public  void runTask(ITaskParam parm)
    {
        if (!init(parm))
            return;
        loadHbaseConfiguration();
        loadHadoopConfiguration();
        HBJob hj = (HBJob) job;
        try {
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
                                    BatchUtils.getRunDate()).getBytes()));
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
            setAppId(j.getJobID().toString(),parm);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new GalaxyException(e.getMessage());
        }
    }

    private void loadHbaseConfiguration() {
        conf = HBaseConfiguration.create();
        String configDirectory = JobConfiguration.getInstance()
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
