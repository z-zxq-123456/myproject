package com.dcits.orion.batch.api;


import com.dcits.galaxy.base.data.BeanResult;

import java.util.Map;

/**
 * Created by lixbb on 2015/12/1.
 */
public interface IBatchLocal {
    /*
         获取系统参数：
         返回Map必须包括 : lastRunDate 上一运行日期
                            runDate    当前运行日期
                            nextRunDate 下一运行日期

     */
    public Map getSystemParam();
    /*
           task运行前
     */
    public void taskInit();
    /*
         task运行后
   */
    public void taskRelease();

    /*
            更新系统日期，跳跑用，当lastRunDate，runDate，nextRunDate为空时，则不更新相应日期字段
     */
    public BeanResult updateSystemDate(String lastRunDate,String runDate,String nextRunDate);
}
