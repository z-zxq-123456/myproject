package com.dcits.orion.batch;

import com.dcits.orion.batch.api.IBatchLocal;
import com.dcits.galaxy.base.data.BeanResult;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lixbb on 2015/12/1.
 */
@Repository
public class BatchLocal implements IBatchLocal{


    @Override
    public Map getSystemParam() {
        Map map = new HashMap();
        map.put("lastRunDate","20160102");
        map.put("runDate","20160103");
        map.put("nextRunDate","20160104");
        return map;
    }

    @Override
    public void taskInit() {

    }

    @Override
    public void taskRelease() {
        System.gc();

    }

    @Override
    public BeanResult updateSystemDate(String lastRunDate, String runDate, String nextRunDate) {
            System.out.println("lastRunDate==" + lastRunDate);
            return new BeanResult();
    }

}
