package com.dcits.ensemble.om.oms.manager.log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 初始化调度方法*
 * @author wangbinaf
 * @date 2016-10-28
 */
//------方法一：必须要等到所有的bean都被处理完成之后再进行
//@Component
//public class InitQuartz implements ApplicationListener<ContextRefreshedEvent> {
//    @Resource
//    LogbackQuartz  logbackQuartz;
//
//    private  final Logger log = LoggerFactory.getLogger(this.getClass());
//    /**
//     * 初始化调度方法*
//     * @author wangbinaf
//     * @date 2016-10-28
//     */
//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent event) {
//        logbackQuartz.startTimer();//开始执行调度方法
//    }
//}
@Component
public class InitQuartz implements InitializingBean {
    @Resource
    LogbackQuartz  logbackQuartz;
    @Resource
    DeleteLogbackQuartz  deletelogbackQuartz;
    private  final Logger log = LoggerFactory.getLogger(this.getClass());
    @Override
    public void afterPropertiesSet()  {
        try {
            // System.out.println("test code start..."+new Date());
            //logbackQuartz.startTimer();//开始执行调度方法,调用连统计分析和调用环统计分析
            //deletelogbackQuartz.startTimer();//定时删除过期的日志信息
        }catch (Exception e){
            log.info("oms 定时统计日志出错");
        }
    }
}