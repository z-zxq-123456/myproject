package com.dcits.orion.batch.common;

import com.dcits.orion.batch.api.IBatchManage;
import com.dcits.orion.batch.api.IRunTask;
import com.dcits.orion.batch.api.bean.ITaskParam;
import com.dcits.galaxy.core.client.ServiceProxy;
import com.dcits.galaxy.core.client.builder.AttributesBuilderSupport;
import com.dcits.galaxy.core.client.builder.ServiceAttributesBuilder;

import java.util.List;

/**
 * Created by lixbb on 2015/11/10.
 */
public class TestRunTask {

    public static boolean runTasks(List<ITaskParam> taskParams) {
        try {

            if (taskParams != null) {
                for (ITaskParam taskParam : taskParams) {
                    ServiceAttributesBuilder builder = new ServiceAttributesBuilder()
                            .setLoadbalance(AttributesBuilderSupport.LOADBALANCE_RANDOM)
                            .setCheck(true)
                            .setGroup(taskParam.getSystemId());
                    try{
                        //IRunTask iRunTask = ServiceProxy.getInstance().getService(IRunTask.class);
                        IRunTask iRunTask = ServiceProxy.getInstance().getService(IRunTask.class, builder.build());
                        iRunTask.runTask(taskParam);

                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                }
                return true;
            } else
                return false;
        } catch (Throwable t) {
            t.printStackTrace();
            return false;
        }
    }
}
