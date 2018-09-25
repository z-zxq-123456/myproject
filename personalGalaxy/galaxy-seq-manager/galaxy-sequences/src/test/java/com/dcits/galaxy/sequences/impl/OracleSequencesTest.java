package com.dcits.galaxy.sequences.impl;

import com.dcits.galaxy.core.threadpool.Executors;
import com.dcits.galaxy.junit.TestBase;
import com.dcits.galaxy.sequences.LocalSequences;
import com.dcits.galaxy.sequences.SequencesHandler;
import com.dcits.galaxy.sequences.ShardSequences;
import com.dcits.galaxy.sequences.model.Sequences;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * <p>Created on 2017/6/10.</p>
 *
 * @author Tim <Tim@dcits.com>
 * @version 1.0.0
 * @since 1.7
 */
public class OracleSequencesTest extends TestBase {

    private LocalSequences localSequences = (LocalSequences) context.getBean("localSequences");

    private ShardSequences shardSequences = (ShardSequences) context.getBean("shardSequences");

    private String seqName = "TEST8";
    
    public void testLocalThread()throws Exception{
//    	LocalSeq ls = new LocalSeq("chenchenSeq");
//    	ls.addSeqListToThreadLocal(10);
//    	for(int i=0;i<20;i++){
//    		long seqNo = ls.generateSeqNo();
//    		System.out.println(seqNo);
//    	}
    }

    public void testGetLocalSeqNo() throws Exception {
        System.out.println(localSequences.getLocalSeqNo(seqName));
    }

    public void testGetLocalSeqNo1() throws Exception {
        System.out.println(localSequences.getLocalSeqNo(seqName, 10));
    }

    public void testGetLocalSeqNoList() throws Exception {
        System.out.println(localSequences.getLocalSeqNoList(seqName, 100));
    }

    public void testCreateLocalSequence() throws Exception {
        Sequences sequences = new Sequences("haha12");
        sequences.setSeqIncrementBy(2);
        sequences.setSeqMinValue(10);
        sequences.setSeqMaxValue(1000000);
        localSequences.createLocalSequence(sequences);
    }

    public void testContainsSequence() throws Exception {
        boolean contains = localSequences.containsSequence(seqName);
        assertEquals(true, contains);
    }

    public void testGetSeqNo() throws Exception {
        System.out.println(shardSequences.getSeqNo(seqName));
    }

    public void testGetSeqNo1() throws Exception {
        System.out.println(shardSequences.getSeqNo(seqName, 10));
    }

    public void testGetSeqNoList() throws Exception {
        System.out.println(shardSequences.getSeqNoList(seqName, 100));
    }

    public void testGetSequences() throws Exception {
        System.out.println(shardSequences.getSequences());
    }

    public void testGetSequences1() throws Exception {
        System.out.println(shardSequences.getSequences("TEST"));
    }

    public void testCreateSequence() throws Exception {
        Sequences sequences = new Sequences("haha14");
        sequences.setSeqIncrementBy(2);
        sequences.setSeqMinValue(10);
        sequences.setSeqMaxValue(1000000);
        shardSequences.createSequence(sequences);
    }

    public void testGetSequence1() throws Exception {
        // 初始化
        int threads = 50;
        ExecutorService executorService = Executors.newFixedThreadPool("OracleSequences", threads);
        long start = System.currentTimeMillis();
        List<Future> futures = new ArrayList<>();
        for (int j = 0; j < threads; j++) {
            final String SequencesName = "SEQ" + j;
            futures.add(executorService.submit(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10; i++) {
                        List<Long> seq = SequencesHandler.getShardSequenceList(SequencesName, 10);
                        System.out.println(SequencesName + "--" + seq);
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