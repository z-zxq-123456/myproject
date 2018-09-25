package com.dcits.galaxy.sequences.impl;

import com.dcits.galaxy.core.spring.SpringApplicationContext;
import com.dcits.galaxy.junit.TestBase;

/**
 * Created by Tim on 2016/4/2.
 */
public class ServiceShardSequencesTest extends TestBase {
    ServiceShardSequences shardSequences;
    ServiceShardSequences localSequences;

    protected void setUp() throws Exception {
        shardSequences = (ServiceShardSequences) SpringApplicationContext.getContext().getBean("shardSequences");
        localSequences = (ServiceShardSequences) SpringApplicationContext.getContext().getBean("localSequences");
    }

    public void testGetSeqNo() throws Exception {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 156; i++) {
            long seq = shardSequences.getSeqNo("t1");
            System.out.println(seq);
        }
        System.out.println(System.currentTimeMillis() - start);
    }

    public void testGetLocalSeqNo() throws Exception {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 156; i++) {
            long seq = localSequences.getLocalSeqNo("t1");
            System.out.println(seq);
        }
        System.out.println(System.currentTimeMillis() - start);
    }

}