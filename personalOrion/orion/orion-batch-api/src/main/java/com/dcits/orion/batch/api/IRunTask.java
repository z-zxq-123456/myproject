package com.dcits.orion.batch.api;
import com.dcits.orion.batch.api.bean.ITaskParam;

/**
 * Created by lixbb on 2015/11/5.
 */
public interface IRunTask {

    public boolean runTask(ITaskParam bean);


    public boolean runTimerTask(ITaskParam bean);

}
