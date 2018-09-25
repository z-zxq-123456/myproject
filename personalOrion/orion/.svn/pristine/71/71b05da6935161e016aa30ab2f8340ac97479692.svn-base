/**
 * <p>Title: AbstractProcess.java</p>
 * <p>Description: Job处理过程父类，负责加载Hadoop conf配置和生成默认的Mapreduce的Job</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 *
 * @author Tim
 * @update 2014年9月15日 下午2:02:19
 * @version V1.0
 */

package com.dcits.orion.schedule.process;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.OutputFormat;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dcits.orion.schedule.common.GlobalConfiguration;
import com.dcits.orion.schedule.common.JobConfiguration;
import com.dcits.orion.schedule.common.JobError;
import com.dcits.orion.schedule.exception.JobException;
import com.dcits.orion.schedule.job.Process;
import com.dcits.orion.schedule.model.AbstractJob;
import com.dcits.orion.schedule.model.HBJob;
import com.dcits.orion.schedule.model.MRJob;
import com.dcits.orion.schedule.workflow.WorkerManager;
import com.dcits.galaxy.base.util.ClassLoaderUtils;

/**
 * @author Tim
 * @version V1.0
 * @description Job处理过程父类，负责加载Hadoop conf配置和生成默认的Mapreduce的Job
 * @update 2014年9月15日 下午3:27:49
 */

public abstract class AbstractProcess implements Process {

    private static Logger logger = LoggerFactory
            .getLogger(AbstractProcess.class);

    protected String jobId;

    protected AbstractJob job;

    protected WorkerManager wm = WorkerManager.getInstance();

    protected JobConfiguration jc = JobConfiguration.getInstance();

    protected Configuration conf;

    public AbstractProcess(String jobId, AbstractJob job) {
        this.jobId = jobId;
        this.job = job;
    }

    public abstract void init();

    public abstract void process();

    protected void setApplicationId(String applicationId) {
        wm.getJob(jobId).setApplicationId(applicationId);
    }

    protected void loadHadoopConfiguration() {
        if (conf == null)
            conf = new Configuration();
        String configDirectory = jc
                .getJobPro(GlobalConfiguration.CONF_CONFIG_DIR);
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

    /**
     * @param pro
     * @param conf
     * @description 将系统参数，copy到hadoop的系统参数中
     * @version 1.0
     * @author Tim
     * @update 2014年9月29日 下午2:42:59
     */
    protected void copyProperties(Properties pro, Configuration conf) {
        if (null == pro)
            return;
        if (null == conf)
            conf = new Configuration();
        Iterator<Entry<Object, Object>> it = pro.entrySet().iterator();
        while (it.hasNext()) {
            Entry<Object, Object> entry = it.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (key instanceof String && value instanceof String) {
                conf.set((String) key, (String) value);
            }
        }
    }

    @SuppressWarnings({"deprecation", "rawtypes"})
    protected Job getDefaultJob() throws IllegalStateException,
            ClassNotFoundException, IOException {
        Job j = new Job(conf);

        if (job.getName() != null) {
            j.setJobName(job.getName());
        } else {
            j.setJobName(jobId);
        }
        if (null != job.getJob_jars())
            j.setJar(job.getJob_jars());

        if (job instanceof HBJob || job instanceof MRJob) {
            MRJob mj = (MRJob) job;
            String[] inputDirectory = mj.getMapperInputPatch(wm.getJob(jobId).getRunDate()) == null ? null
                    : mj.getMapperInputPatch(wm.getJob(jobId).getRunDate()).split(",");
            if (inputDirectory != null) {
                List<Path> pl = new ArrayList<Path>();
                for (String path : inputDirectory) {
                    pl.add(new Path(path));
                }
                FileInputFormat.setInputPaths(j, pl.toArray(new Path[]{}));
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

            String outputDirectory = mj.getReducerOnputPatch(wm.getJob(jobId).getRunDate());
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
}
