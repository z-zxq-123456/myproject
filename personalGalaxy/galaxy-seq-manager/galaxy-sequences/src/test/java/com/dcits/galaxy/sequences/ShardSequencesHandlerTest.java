package com.dcits.galaxy.sequences;

import com.dcits.galaxy.core.threadpool.Executors;
import com.dcits.galaxy.junit.TestBase;
import com.dcits.galaxy.sequences.model.Sequences;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * Created by Tim on 2016/4/6.
 */
public class ShardSequencesHandlerTest extends TestBase {

    public void testGetShardSequence() throws Exception {
        long start = System.currentTimeMillis();
        long seq = SequencesHandler.getShardSequence("TEST1",150);
        System.out.println(seq);
        seq = SequencesHandler.getShardSequence("TEST1",150);
        System.out.println(seq);
        seq = SequencesHandler.getShardSequence("TEST1",150);
        System.out.println(seq);
        seq = SequencesHandler.getShardSequence("TEST1",150);
        System.out.println(seq);
        System.out.println(System.currentTimeMillis() - start);
    }

    public void testGetShardSequence1() throws Exception {
        int threads = 50;
        ExecutorService executorService = Executors.newFixedThreadPool("Sequences", threads);
        long start = System.currentTimeMillis();
        List<Future> futures = new ArrayList<>();
        for (int j = 0; j < threads; j++) {
            futures.add(executorService.submit(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 1; i++) {
                        try {
                            long seq = SequencesHandler.getShardSequence("TEST1");
                            System.out.println(seq);
                        } catch (Throwable t) {
                            t.printStackTrace();
                        }
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

    public void testContainsSequence() {
        // 两次读取，检查序列名称缓存是否生效
        boolean f = SequencesHandler.containsSequence("tt");
        System.out.println(f);
        f = SequencesHandler.containsSequence("tt");
        System.out.println(f);
    }

    public void testCreateLocalSequences() {
        Sequences seq = new Sequences("t1");
        seq.setSeqMinValue(5);
        seq.setSeqMaxValue(100);
        SequencesHandler.createLocalSequences(seq);
    }
}