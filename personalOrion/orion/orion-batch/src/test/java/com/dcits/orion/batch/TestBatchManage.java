package com.dcits.orion.batch;

import com.dcits.orion.batch.api.IBatchManage;
import com.dcits.galaxy.core.client.ServiceProxy;
import com.dcits.galaxy.core.spring.SpringApplicationContext;

import junit.framework.TestCase;

import java.util.List;

/**
 * Created by lixbb on 2015/11/13.
 */
public class TestBatchManage extends TestCase {

    IBatchManage bm;

    @Override
       public void setUp() {
        bm = ServiceProxy.getInstance().getService(IBatchManage.class);
    }

    public void testStartBatch() throws InterruptedException {
        List failTasks = bm.getFailTasks("MAIN");
        System.out.println(failTasks);
    }
    //public void testrsStartBatch() throws InterruptedException {
      //  bm.restartBatch("MAIN");
   // }
}

