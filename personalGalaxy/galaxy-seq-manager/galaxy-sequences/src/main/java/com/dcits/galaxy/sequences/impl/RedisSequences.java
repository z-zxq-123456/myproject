package com.dcits.galaxy.sequences.impl;

import com.dcits.galaxy.sequences.LocalSequences;
import com.dcits.galaxy.sequences.SequencesHandler;
import com.dcits.galaxy.sequences.ShardSequences;
import com.dcits.galaxy.sequences.model.Sequences;
import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.galaxy.cache.utils.JedisUtils;

import java.util.Collection;
import java.util.List;

/**
 * Created by Tim on 2016/6/24.
 */
public class RedisSequences implements ShardSequences, LocalSequences {

    public static final String GALAXY_SEQUENCES = "galaxy.sequences";
    
    /**
     * 默认序列集合最大可取数量，默认为20000
     */
    private long sequenceListLimitOver = 20000;

    /**
     * 获取自增序列
     *
     * @param sequenceName
     * @return
     */
    @Override
    public long getLocalSeqNo(String sequenceName) {
        long seqNo = JedisUtils
                .getResourceIncrBy(GALAXY_SEQUENCES, sequenceName);
        return seqNo;
    }

    /**
     * 获取序列
     *
     * @param sequenceName
     * @param value
     * @return
     */
    @Override
    @Deprecated
    public long getLocalSeqNo(String sequenceName,long value) {
        long seqNo = JedisUtils
                .getResourceIncrBy(GALAXY_SEQUENCES, sequenceName,value);
        return seqNo;
    }
    
    /**
     * 获取自增序列
     *
     * @param sequenceName
     * @param value
     * @return
     */
    @Override
    public List<Long> getLocalSeqNoList(String sequenceName, long value) {
    	if(value<=sequenceListLimitOver){
	        long seqNo = JedisUtils
	                .getResourceIncrBy(GALAXY_SEQUENCES, sequenceName, value);
	        return SequencesHandler.getSeqList(seqNo, value);
    	}else{
    		throw new GalaxyException("The SequenceList Size Must Be Least Than "+sequenceListLimitOver);
    	}
    }


    /**
     * 创建自增序列
     * 不会添加到缓存管理，仅做持久化动作
     *
     * @param sequence
     * @return
     */
    @Override
    public void createLocalSequence(Sequences sequence) {
        JedisUtils.setObjectResource(GALAXY_SEQUENCES, sequence.getSeqName(), 0L);
    }

    /**
     * 判断是否存在的序列
     *
     * @param seqName
     * @return
     */
    @Override
    public boolean containsSequence(String seqName) {
        return JedisUtils.hexistsKey(GALAXY_SEQUENCES, seqName);
    }

    /**
     * 获取自增序列
     *
     * @param sequenceName
     * @return
     */
    @Override
    public long getSeqNo(String sequenceName) {
        return getLocalSeqNo(sequenceName);
    }

    /**
     * 获取自增序列
     *
     * @param sequenceName
     * @param value
     * @return
     */
    @Override
    @Deprecated
    public long getSeqNo(String sequenceName,long value) {
        return getLocalSeqNo(sequenceName,value);
    }
    
    /**
     * 获取自增序列
     *
     * @param sequenceName
     * @param value
     * @return
     */
    @Override
    public List<Long> getSeqNoList(String sequenceName, long value) {
    	if(value<sequenceListLimitOver){
    		return getLocalSeqNoList(sequenceName, value);
    	}else{
    		throw new GalaxyException("The SequenceList Size Must Be Least Than "+sequenceListLimitOver);
    	}
    }

    /**
     * 获取序列列表
     *
     * @return
     */
    @Override
    public Collection<Sequences> getSequences() {
        return null;
    }

    /**
     * 获取序列对象
     *
     * @param seqName
     * @return
     */
    @Override
    public Collection<Sequences> getSequences(String seqName) {
        return null;
    }

    /**
     * 探针服务
     * 为消费者调用使用。可以提供空方法
     */
    @Override
    public void probe() {
    }

    /**
     * 创建序列
     * 并将序列添加到序列管理器
     *
     * @param sequence
     */
    @Override
    public void createSequence(Sequences sequence) {
        createLocalSequence(sequence);
    }

	public long getSequenceListLimitOver() {
		return sequenceListLimitOver;
	}

	public void setSequenceListLimitOver(long sequenceListLimitOver) {
		this.sequenceListLimitOver = sequenceListLimitOver;
	}
    
    
}
