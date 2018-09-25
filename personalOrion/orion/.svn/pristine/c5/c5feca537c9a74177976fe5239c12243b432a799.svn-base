package com.dcits.orion.batch.task.service;

import com.dcits.orion.batch.api.bean.ITaskParam;
import com.dcits.galaxy.base.util.BeanUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * Created by lixbb on 2015/11/11.
 */
@Repository
public class JobDemo {
    private static Logger logger = LoggerFactory.getLogger(JobDemo.class);
    public void jobRun(ITaskParam bean)throws Exception
    {
        System.out.println(bean.getJobId() + " Start!");
       // logger.debug(bean.getJobId() + " Start!");
        long sleep = Long.parseLong(bean.getStaticParam());
        logger.debug(bean.getJobId());
        Thread.sleep(sleep);
        //logger.debug(bean.getJobId() + " End!");
        System.out.println(bean.getJobId() + " End!");
    }
}
