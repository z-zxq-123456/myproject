package com.dcits.orion.batch;

import com.dcits.galaxy.base.util.DateUtils;
import com.dcits.galaxy.junit.TestBase;
import com.dcits.orion.schedule.common.JobContextRequest;
import com.dcits.orion.schedule.context.JobContext;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by lixbb on 2016/1/19.
 */
public class TestRun extends TestBase {

    public void testCase1()
    {
        JobContextRequest jcr = new JobContextRequest();
        JobContext ac = jcr.getJobContext("application_1454546932029_0002");
        if (ac != null && null != ac.getState()) {

            System.out.println("ac.getState()="+ ac.getState());

        }
    }
    public static void main(String[] args) throws ParseException {
        DateFormat dateFormat = DateUtils.getDateFormat("yyyyMMdd");
        Date date = dateFormat.parse("20171231");
        System.out.println(DateUtils.getYear(date));
        System.out.println(DateUtils.getMonth(date));
        System.out.println(DateUtils.getDayOfWeek(date));
        Date nextDay = DateUtils.addDays(date,1);
        System.out.println(DateUtils.getYear(nextDay));
        System.out.println(DateUtils.getMonth(nextDay));
        System.out.println(DateUtils.getDayOfWeek(nextDay));
    }
}
