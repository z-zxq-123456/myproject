/**
 * Title: Galaxy(Distributed service platform)
 * File: Oracle.java
 * Copyright: Copyright (c) 2014-2017
 * Company: 神州数码融信软件有限公司
 */
package com.dcits.galaxy.sequences.impl;

import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.galaxy.sequences.LocalSequences;
import com.dcits.galaxy.sequences.ShardSequences;
import com.dcits.galaxy.sequences.dao.OracleSeqDao;
import com.dcits.galaxy.sequences.model.Sequences;

import java.util.Collection;
import java.util.List;



/**
 * 基于Oracle数据库的Sequences序列的自增序列服务
 *
 * <p>Created on 2017/6/10.</p>
 *
 * @author Tim <Tim@dcits.com>
 * @version 1.0.0
 * @since 1.7
 */
public class OracleSequences implements ShardSequences, LocalSequences {

    private OracleSeqDao oracleSeqDao;
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
        return oracleSeqDao.getSeq(new Sequences(sequenceName));
    }

    @Override
    @Deprecated
    public long getLocalSeqNo(String sequenceName, long value) {
    	return -1;
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
    		Sequences sequences = new Sequences(sequenceName);
            sequences.setSeqCount(value);
            return oracleSeqDao.getSeqList(sequences);
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
        oracleSeqDao.createSeq(sequence);
    }

    /**
     * 判断是否存在的序列
     *
     * @param seqName
     * @return
     */
    @Override
    public boolean containsSequence(String seqName) {
        return oracleSeqDao.containsSequence(seqName);
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
    public long getSeqNo(String sequenceName, long value) {
        return getLocalSeqNo(sequenceName, value);
    }

    /**
     * 获取自增序列
     *
     * @param sequenceName
     * @param value
     * @return
     */
    @Override
    public List<Long> getSeqNoList(String sequenceName,long value) {
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
        return oracleSeqDao.getSequneces();
    }

    /**
     * 获取序列对象,根据名称模糊查询
     *
     * @param seqName
     * @return
     */
    @Override
    public Collection<Sequences> getSequences(String seqName) {
        return oracleSeqDao.getSequneces(seqName);
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

    public OracleSeqDao getOracleSeqDao() {
        return oracleSeqDao;
    }

    public void setOracleSeqDao(OracleSeqDao oracleSeqDao) {
        this.oracleSeqDao = oracleSeqDao;
    }

	public long getSequenceListLimitOver() {
		return sequenceListLimitOver;
	}

	public void setSequenceListLimitOver(long sequenceListLimitOver) {
		this.sequenceListLimitOver = sequenceListLimitOver;
	}
    
    
}
