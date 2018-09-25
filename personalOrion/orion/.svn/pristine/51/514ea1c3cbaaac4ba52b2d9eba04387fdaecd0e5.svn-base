package com.dcits.orion.core.reversal;

import com.dcits.galaxy.base.data.Result;
import com.dcits.galaxy.core.spring.SpringApplicationContext;
import com.dcits.orion.api.IReversal;
import com.dcits.orion.api.IZkThread;
import com.dcits.orion.core.util.BusinessUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lixbb on 2016/2/17.
 */

public class AutoReversalThread implements IZkThread {

    private static Logger logger = LoggerFactory.getLogger(AutoReversalThread.class);
    //线程等待时间
    private int sleepTime = 5000;

    //每次取出的最大条数
    private int limit = 100;
    @Override
    public void setArgs(String[] args) {
        if (args != null && args.length >= 1)
        {
            sleepTime = Integer.parseInt(args[0]);
            if (args.length >= 2)
            {
                limit = Integer.parseInt(args[1]);
            }
        }
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p/>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        if (logger.isInfoEnabled())
            logger.info("AutoReversalThread 启动！");
        while (true)
        {
            int count = 0;
            try
            {
                AutoReversalDao autoReversalDao = SpringApplicationContext.getContext().getBean(AutoReversalDao.class);
                List<Map> reversals = autoReversalDao.getReversals(limit);
                if (reversals != null)
                {
                    count = reversals.size();
                    for (Map reversal : reversals)
                    {
                        String reversalId = (String)reversal.get("REVERSAL_ID");
                        String reversalBeanIdId =(String)reversal.get("REVERSAL_BEAN_ID");
                        Object[] args = BusinessUtils.deserialize(reversal.get("REVERSAL_ARGS"));
                        int reversalCnt = Integer.parseInt(reversal.get("REVERSAL_CNT").toString());
                        int maxReversalCnt= Integer.parseInt(reversal.get("MAX_REVERSAL_CNT").toString());
                        IReversal iReversal = (IReversal)SpringApplicationContext.getContext().getBean(reversalBeanIdId);
                        Result result;
                        try
                        {
                            result = iReversal.reversal(args);

                        }
                        catch (Exception e)
                        {
                            result = new Result("999999","调用冲正接口失败！");
                        }
                        Map reversalTran = new HashMap();
                        reversalTran.put("REVERSAL_ID",reversalId);
                        reversalTran.put("REVERSAL_DATE",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                        reversalCnt = reversalCnt + 1;
                        reversalTran.put("REVERSAL_CNT",reversalCnt);
                        reversalTran.put("RET_CODE",result.getRetCode());
                        reversalTran.put("RET_MSG",result.getRetMsg());
                        if ("000000".equals(result.getRetCode()))
                        {
                            reversalTran.put("STATUS","S");
                        }
                        else if ("666666".equals(result.getRetCode()))
                        {
                            reversalTran.put("STATUS","F");
                        }
                        else
                        {
                            if (reversalCnt >= maxReversalCnt)
                            {
                                reversalTran.put("STATUS","F");
                            }
                        }
                        autoReversalDao.updateReversal(reversalTran);
                    }
                }

            }
            catch (Exception e)
            {

            }
            try
            {
                if (count < limit)
                    Thread.sleep(sleepTime);
            } catch (InterruptedException e) {

            }
        }

    }
}
