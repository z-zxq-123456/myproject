package com.dcits.orion.batch.quartz;

import com.dcits.orion.batch.api.bean.ITaskParam;
import com.dcits.orion.batch.common.bean.CommTableObj;
import com.dcits.orion.batch.common.bean.TaskParam;
import com.dcits.orion.batch.common.dao.AutoMapperDao;
import com.dcits.galaxy.core.spring.SpringApplicationContext;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;

/**
 * Created by lixbb on 2016/7/31.
 */
@Repository
public class TimerStart {
    @Resource
    QuartzManager quartzManager;
    @Resource
    AutoMapperDao autoMapperDao;
    public void initTimer()
    {
        CommTableObj commTableObj = new CommTableObj("BATCH_TIMER_DEF");
        commTableObj.setCondition("STATUS", "Y");
        List<Map> loopTimers = autoMapperDao.selectList(commTableObj);
        quartzManager.startTimer(loopTimers);
    }


}
