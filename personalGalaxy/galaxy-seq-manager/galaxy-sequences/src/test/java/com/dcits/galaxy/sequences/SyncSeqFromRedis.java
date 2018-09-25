package com.dcits.galaxy.sequences;

import com.dcits.galaxy.cache.template.RedisTemplate;
import com.dcits.galaxy.cache.utils.JedisUtils;
import com.dcits.galaxy.core.spring.SpringApplicationContext;
import com.dcits.galaxy.junit.TestBase;
import com.dcits.galaxy.sequences.dao.SequencesDao;
import com.dcits.galaxy.sequences.model.Sequences;

import java.util.Map;

/**
 * Created by Tim on 2016/4/15.
 */
public class SyncSeqFromRedis extends TestBase {

    public static final String GALAXY_SEQUENCES = "galaxy.sequences";
    SequencesDao shardSequences;

    @Override
    public void setUp() {
        shardSequences = SpringApplicationContext.getContext().getBean(SequencesDao.class);
    }

    public void testSeqFromRedis() {
        if (null == JedisUtils.getRedisTemplate())
            return;
        
		RedisTemplate redisTemplate = JedisUtils.getRedisTemplate();
		Map<String, Object> seq = redisTemplate.hgetAll(GALAXY_SEQUENCES);
		for (Map.Entry<String, Object> entry : seq.entrySet()) {
			testCreateSeq(entry.getKey(), (String) entry.getValue());
		}
    }

    private void testCreateSeq(String seqName, String value) {
        Sequences seq = shardSequences.getSequence(seqName);
        if (null != seq) {
            System.out.println("[" + seqName + "] is existed!");
            return;
        }
        // 创建序列
        seq = new Sequences();
        seq.setSeqName(seqName);
        seq.setSeqMaxValue(9999999999L);
        seq.setSeqMinValue(1);
        seq.setSeqIncrementBy(1);
        seq.setSeqCurrentValue(Long.valueOf(value));
        seq.setSeqCache(200);
        seq.setSeqCycle("Y");

        shardSequences.insertSequence(seq);
    }
}
