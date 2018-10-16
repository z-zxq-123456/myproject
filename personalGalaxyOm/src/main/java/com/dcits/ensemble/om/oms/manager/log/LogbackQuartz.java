package com.dcits.ensemble.om.oms.manager.log;

import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.service.log.EcmLogbackQuartzService;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;

import com.dcits.galaxy.core.spring.SpringApplicationContext;
import org.quartz.*;
import java.util.*;


import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


import javax.annotation.Resource;

/**
 * 调度调用的类   功能：统计前一天的调用链和调用环
 * Created by wangbinaf on 2016/10/24.
 */
@Component
public class LogbackQuartz implements Job {

    private int max_trace_star_Id = 0;//调用链统计ID

    private int max_cir_star_Id = 0;//调用环统计ID

    private String TIME_UNIT = "ms";//时间单位为毫秒

    @Resource
    ParamComboxService paramComboxService;

    private  final Logger log = LoggerFactory.getLogger(this.getClass());
    /**
     * 统计前一天的调用链和调用环
     */
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {//该接口只有唯一一个方法 execute；当一个任务的触发器启动时，相应的调度器scheduler会调用该任务。
//            saveCinAndCir();//插入调用链，调用环的信息到数据库中
        EcmLogbackQuartzService service = SpringApplicationContext.getContext().getBean(EcmLogbackQuartzService.class);
        service.saveCinAndCir();
        log.info("调度成功！！！"+new Date().toString());
    }
    /**
     * 调度装载的类  Schedule
     * Created by wangbinaf on 2016/10/24.
     */
    public void startTimer()  {
        try {
            SchedulerFactory schedFact=new StdSchedulerFactory();
            Scheduler sched=schedFact.getScheduler();
            JobDetail jobDetail= JobBuilder.newJob(LogbackQuartz.class).withIdentity("countTraceJob","group_1").build();//countTraceJob为JOD的名称
            String startTime = getQuartzStartTime();
            if(startTime.equals("")){
                 return;
            }else {
                Trigger trigger = TriggerBuilder.newTrigger().withIdentity("countTraceTrigger").
                        startNow().withSchedule(CronScheduleBuilder.cronSchedule(startTime)).build();//每天的零点开始执行execute
                //方便测试
//            Trigger trigger= TriggerBuilder.newTrigger().withIdentity("trigger_1","group_1")
//                    .startNow()
//                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
//                            .withIntervalInSeconds(60) //时间间隔
//                            .withRepeatCount(0)        //重复次数(将执行6次)
//                    )
//                    .build();
                sched.scheduleJob(jobDetail, trigger);
                sched.start();//开始执行
                log.info("调度开始执行！！！" + new Date().toString());
            }
        }catch (Exception e){
            log.error("调度出错详细信息："+e);
         //   e.printStackTrace();
        }
    }
    //时间的格式转换  例如：12:50  转换成  "0 50 12 ? * *"
    private String getQuartzStartTime(){
        String   quartzStartTime = paramComboxService.getParaRemark1(SysConfigConstants.QUARTZ_START_TIME);//获取调度时间
        if(null==quartzStartTime||quartzStartTime.equals("")){
            return "";
        }else {
            String hourTime = quartzStartTime.split(":")[0];
            String secondTime = quartzStartTime.split(":")[1];
            if (secondTime.equals("00")) {
                secondTime = "0";
            }
            System.out.println("0 " + secondTime + " " + hourTime + " " + "? * *");
            return "0 " + secondTime + " " + hourTime + " " + "? * *";
        }
    }


}
