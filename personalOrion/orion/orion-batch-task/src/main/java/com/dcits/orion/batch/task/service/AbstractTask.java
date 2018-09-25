package com.dcits.orion.batch.task.service;


import com.dcits.orion.batch.api.IBatchManage;
import com.dcits.orion.batch.api.bean.ITaskParam;
import com.dcits.orion.batch.common.BatchUtils;
import com.dcits.orion.schedule.common.GlobalConfiguration;
import com.dcits.orion.schedule.common.JobConfiguration;
import com.dcits.orion.schedule.common.JobError;
import com.dcits.orion.schedule.exception.JobException;
import com.dcits.orion.schedule.model.AbstractJob;
import com.dcits.orion.schedule.model.HBJob;
import com.dcits.orion.schedule.model.MRJob;
import com.dcits.orion.schedule.process.SubClazz;
import com.dcits.galaxy.base.util.ClassLoaderUtils;
import com.dcits.galaxy.core.client.ServiceProxy;
import com.dcits.galaxy.core.client.builder.AttributesBuilderSupport;
import com.dcits.galaxy.core.client.builder.ServiceAttributesBuilder;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;

/**
 * Created by lixbb on 2015/11/16.
 */
public abstract class AbstractTask {

    private static Logger logger = LoggerFactory
            .getLogger(AbstractTask.class);

    protected Configuration conf;
    protected AbstractJob job;
    protected ITaskParam param;
    protected JobConfiguration jc = JobConfiguration.getInstance();

    public abstract void runTask(ITaskParam parm);

    public boolean init(ITaskParam param) {
        boolean ret = true;
        this.param = param;
        job = jc.getJobContainer().getJob(param.getJobName());
        return ret;
    }

    protected void loadHadoopConfiguration() {
        if (conf == null)
            conf = new Configuration();
        String configDirectory = JobConfiguration.getInstance().getJobPro(GlobalConfiguration.CONF_CONFIG_DIR);
        File dir = new File(configDirectory);
        String[] files = dir.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith("-site.xml");
            }
        });

        if (files == null) {
            throw new JobException(
                    JobError.JOB_006,
                    "Invalid Hadoop configuration directory (not a directory or permission issues): "
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

        // 设置回调url
        // conf.set("job.end.notification.url", "");

        // Turn off speculative execution
        conf.setBoolean("mapred.map.tasks.speculative.execution", false);
        conf.setBoolean("mapred.reduce.tasks.speculative.execution", false);

        // 添加job系统参数
        copyProperties(job.getConfiguration().getProperties(), conf);

    }

    protected void copyProperties(Properties pro, Configuration conf) {
        if (null == pro)
            return;
        if (null == conf)
            conf = new Configuration();
        Iterator<Map.Entry<Object, Object>> it = pro.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Object, Object> entry = it.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (key instanceof String && value instanceof String) {
                conf.set((String) key, (String) value);
            }
        }
    }

    protected Job getDefaultJob() throws IllegalStateException,
            ClassNotFoundException, IOException {
        Job j = new Job(conf);

        if (param.getJobName() != null) {
            j.setJobName(param.getJobName());
        } else {
            j.setJobName(param.getJobId());
        }
        if (null != job.getJob_jars())
            j.setJar(job.getJob_jars());

        if (job instanceof HBJob || job instanceof MRJob) {
            MRJob mj = (MRJob) job;



              /*
            String[] inputDirectory = mj.getMapperInputPatch(BatchUtils.getRunDate()) == null ? null
                    : mj.getMapperInputPatch(BatchUtils.getRunDate()).split(",");
            if (inputDirectory != null) {
                List<Path> pl = new ArrayList<Path>();
                for (String path : inputDirectory) {
                    pl.add(new Path(path));
                }
                FileInputFormat.setInputPaths(j, pl.toArray(new Path[]{}));
            }

            */


            String inputDirectory = mj.getMapperInputPatch(BatchUtils.getRunDate());
            if (inputDirectory != null)
            {
                Path inputPath = new Path(inputDirectory);
                FileInputFormat.setInputPaths(j, inputPath);
                /*
                FileSystem fileSystem = inputPath.getFileSystem(conf);
                FileStatus[] fileStatuses = fileSystem.listStatus(inputPath);//获取子文件（夹）
                List<FileStatus> subPaths = new ArrayList<FileStatus>();//子文件夹
                for (FileStatus fileStatus : fileStatuses) {
                    if (fileStatus.isDirectory())
                        subPaths.add(fileStatus);
                }
                if (subPaths.size() > 0) {
                    Path[] paths = FileUtil.stat2Paths(subPaths.toArray(new FileStatus[]{}));
                    FileInputFormat.setInputPaths(j, paths);
                } else {
                    FileInputFormat.setInputPaths(j, inputPath);
                }
                */
            }







            j.setJarByClass(ClassLoaderUtils.loadClass(mj.getJarByClass()));

            Class<? extends InputFormat> inputFormatClass = new SubClazz<InputFormat>()
                    .getClazz(mj.getMapperFormatClass());
            if (null != inputFormatClass)
                j.setInputFormatClass(inputFormatClass);

            Class<? extends Mapper> mapperClass = new SubClazz<Mapper>()
                    .getClazz(mj.getMapperClass());
            if (null != mapperClass)
                j.setMapperClass(mapperClass);

            Class<? extends Writable> mapperOutputKey = new SubClazz<Writable>()
                    .getClazz(mj.getMapperOutputKey());
            if (null != mapperOutputKey)
                j.setMapOutputKeyClass(mapperOutputKey);

            Class<? extends Writable> mapperOutputValue = new SubClazz<Writable>()
                    .getClazz(mj.getMapperOutputValue());
            if (null != mapperOutputValue)
                j.setMapOutputValueClass(mapperOutputValue);

            String outputDirectory = mj.getReducerOnputPatch(BatchUtils.getRunDate());
            if (outputDirectory != null) {
                Path out = new Path(outputDirectory);
                FileSystem fs = out.getFileSystem(conf);
                if (fs.exists(out))
                    fs.delete(out, true);

                FileOutputFormat.setOutputPath(j, out);
            }

            j.setNumReduceTasks(mj.getReducerNumTask());

            Class<? extends Reducer> reducerClass = new SubClazz<Reducer>()
                    .getClazz(mj.getReducerClass());
            if (null != reducerClass)
                j.setReducerClass(reducerClass);

            Class<? extends OutputFormat> outputFormatClass = new SubClazz<OutputFormat>()
                    .getClazz(mj.getOutputFormatClass());

            if (null != outputFormatClass)
                j.setOutputFormatClass(outputFormatClass);

            Class<? extends Writable> outputKey = new SubClazz<Writable>()
                    .getClazz(mj.getOutputKey());
            if (null != outputKey)
                j.setOutputKeyClass(outputKey);

            Class<? extends Writable> outputValue = new SubClazz<Writable>()
                    .getClazz(mj.getOutputValue());
            if (null != outputValue)
                j.setOutputValueClass(outputValue);
        }
        return j;
    }


    public void setAppId(String appId,ITaskParam param) {
        //BatchDao batchDao = SpringApplicationContext.getContext().getBean(BatchDao.class);
        ServiceAttributesBuilder builder = new ServiceAttributesBuilder()
                .setLoadbalance(AttributesBuilderSupport.LOADBALANCE_ROUNDROBIN)
                .setScope(ServiceAttributesBuilder.SCOPE_REMOTE)
                .setCheck(true)
                .setGroup(param.getSendGroup());
        IBatchManage iBatchManage = ServiceProxy.getInstance().getService(IBatchManage.class, builder.build());
        Map task = new HashMap();
        task.put("TASK_ID", param.getTaskId());
        task.put("APP_ID", appId);
        iBatchManage.updateRunTask(task);
    }
}
