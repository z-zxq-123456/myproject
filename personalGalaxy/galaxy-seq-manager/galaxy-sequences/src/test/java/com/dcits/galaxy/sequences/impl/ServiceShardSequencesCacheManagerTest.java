package com.dcits.galaxy.sequences.impl;

import com.dcits.galaxy.core.spring.SpringApplicationContext;
import com.dcits.galaxy.core.threadpool.Executors;
import com.dcits.galaxy.junit.TestBase;
import com.dcits.galaxy.sequences.SequencesConfiguration;
import com.dcits.galaxy.sequences.dao.SequencesDao;
import com.dcits.galaxy.sequences.model.Sequences;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * Created by Tim on 2016/4/3.
 */
public class ServiceShardSequencesCacheManagerTest extends TestBase {
    private ServiceShardSequencesCacheManager serviceShardSequencesCacheManager;
    private SequencesDao sequencesDao;
    private SequencesConfiguration sequencesConfiguration;
    private String seqName = "TEST1";

    protected void setUp() throws Exception {
        sequencesDao = SpringApplicationContext.getContext().getBean(SequencesDao.class);
        sequencesConfiguration = SpringApplicationContext.getContext().getBean(SequencesConfiguration.class);
        serviceShardSequencesCacheManager = SpringApplicationContext.getContext().getBean(ServiceShardSequencesCacheManager.class);
        Sequences sequences = sequencesDao.getSequence(seqName);
        if (null == sequences) {
            sequences = sequencesConfiguration.getSequencesConfiguration(seqName);
            sequencesDao.insertSequence(sequences);
        }
    }

    public void testInit() throws Exception {
        serviceShardSequencesCacheManager.init();
    }

    public void testInit1() throws Exception {
//        Sequences sequences = sequencesDao.getSequence(seqName);
//        serviceShardSequencesCacheManager.init(sequences);
    }

    public void testAddSequence() throws Exception {
        Sequences sequences = sequencesDao.getSequence(seqName);
        serviceShardSequencesCacheManager.addSequence(sequences);
    }

    public void testContainsSequence() throws Exception {
        // 初始化
        serviceShardSequencesCacheManager.init();
        boolean f = serviceShardSequencesCacheManager.containsSequence(seqName);
    }

    public void testGetSequence() throws Exception {
        // 初始化
        serviceShardSequencesCacheManager.init();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 2000; i++) {
            long seq = serviceShardSequencesCacheManager.getSequence(seqName,-1L);
            System.out.println(seq);
        }
        System.out.println(System.currentTimeMillis() - start);
    }

    public void testGetSequence1() throws Exception {
        // 初始化
        serviceShardSequencesCacheManager.init();
        int threads = 50;
        ExecutorService executorService = Executors.newFixedThreadPool("Sequences", threads);
        long start = System.currentTimeMillis();
        List<Future> futures = new ArrayList<>();
        for (int j = 0; j < threads; j++) {
            futures.add(executorService.submit(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 500; i++) {
                        long seq = serviceShardSequencesCacheManager.getSequence(seqName,-1);
                        System.out.println(seq);
                    }
                }
            }));
        }
        for (Future future : futures) {
            future.get();
        }
        System.out.println(System.currentTimeMillis() - start);
        executorService.shutdown();
    }
}