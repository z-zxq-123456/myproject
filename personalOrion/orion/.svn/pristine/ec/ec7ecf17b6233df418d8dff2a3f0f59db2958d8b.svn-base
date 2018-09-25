package com.dcits.orion.core.reversal;


import com.dcits.galaxy.base.util.SeqUtils;
import com.dcits.galaxy.core.spring.SpringApplicationContext;
import com.dcits.orion.core.util.BusinessUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lixbb on 2016/2/16.
 */
public class ReversalUtil {

    public static void insertReversal(Object[] args,int maxReversalCnt,String reversalBeanId)
    {
        Map reversalTran = new HashMap();
        reversalTran.put("REVERSAL_ID",getReversalId());
        reversalTran.put("REVERSAL_ARGS", BusinessUtils.serialize(args));
        reversalTran.put("TRAN_DATE",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        reversalTran.put("REVERSAL_CNT",0);
        reversalTran.put("MAX_REVERSAL_CNT",maxReversalCnt);
        reversalTran.put("REVERSAL_BEAN_ID",reversalBeanId);
        reversalTran.put("STATUS","N");
        AutoReversalDao autoReversalDao = SpringApplicationContext.getContext().getBean(AutoReversalDao.class);
        autoReversalDao.insertReversal(reversalTran);
    }
    private static String getReversalId()
    {
        return SeqUtils.getStringSeq();
    }
}
