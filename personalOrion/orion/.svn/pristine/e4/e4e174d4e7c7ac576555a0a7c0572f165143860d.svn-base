package com.dcits.orion.batch.task.service;

import com.dcits.orion.batch.api.bean.ITaskParam;
import com.dcits.galaxy.base.exception.GalaxyException;

import org.apache.hadoop.mapreduce.Job;
import org.mortbay.log.Log;
import org.springframework.stereotype.Repository;

/**
 * Created by lixbb on 2015/11/17.
 */
public class MrTask extends AbstractTask{

    public  void runTask(ITaskParam parm) {
        if (!init(parm))
            return;
        try {
            loadHadoopConfiguration();
            Log.debug(job.toString());
            Job j = getDefaultJob();
            j.submit();
            setAppId(j.getJobID().toString(),parm);
        }
        catch (Exception e)
        {
            throw new GalaxyException(e.getMessage());
        }
    }
}
