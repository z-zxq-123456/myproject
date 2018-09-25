package com.dcits.orion.batch.task.service;

import com.dcits.orion.batch.api.IBatchManage;
import com.dcits.orion.batch.api.bean.ITaskParam;
import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.galaxy.base.util.StringUtils;
import com.dcits.galaxy.core.client.ServiceProxy;
import com.dcits.galaxy.core.client.builder.AttributesBuilderSupport;
import com.dcits.galaxy.core.client.builder.ServiceAttributesBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @desc: 该JOB作用是暂停批处理业务的执行
 * @author :qiqingshan
 * @date :2016年12月19日10:47:40
 */
@Repository
public class PauseJob {

    private static Logger logger = LoggerFactory.getLogger(PauseJob.class);

    public void pauseJob(ITaskParam taskParam)throws Exception{
        ServiceAttributesBuilder builder = new ServiceAttributesBuilder()
                .setLoadbalance(AttributesBuilderSupport.LOADBALANCE_ROUNDROBIN)
                .setScope(ServiceAttributesBuilder.SCOPE_REMOTE)
                .setCheck(false)
                .setGroup(taskParam.getSendGroup());
        IBatchManage iBatchManage = ServiceProxy.getInstance().getService(IBatchManage.class, builder.build());
        Map task = iBatchManage.getRunTask(taskParam.getTaskId());
        String errorDesc = (String)task.get("ERROR_DESC");
        if (StringUtils.isBlank(errorDesc)) {
            logger.info("批处理JOB[" + taskParam.getJobId() + "]暂停");
            throw new GalaxyException("暂停批处理业务执行！");
        }
    }
}
