package com.dcits.galaxy.sequences.model;

import com.dcits.galaxy.base.bean.AbstractBean;

/**
 * 序列模型
 * <p/>
 * Created by Tim on 2016/4/3.
 */
public class Sequences extends AbstractBean {

	private static final long serialVersionUID = 1L;
	
	private String seqName;//序列名称
    private volatile long seqCurrentValue;//数据库序列值
    private volatile long seqCount;//当前缓存值
    private volatile long seqMaxValue;//最大序列值
    private volatile long seqMinValue;//最小序列值
    private volatile long seqIncrementBy;//序列增长步长
    private String seqCycle;//是否循环序列 Y-是；N-不是；
    private volatile long seqCache;//缓存序列数
    private volatile long cacheCount;//缓存序列计数

    public Sequences() {
    }

    public Sequences(String seqName) {
        this.seqName = seqName;
        this.seqCurrentValue = 0;
        this.seqMaxValue = Long.MAX_VALUE;
        this.seqMinValue = 1;
        this.seqIncrementBy = 1;
        this.seqCycle = "Y";
        this.seqCache = 200;
        this.cacheCount = 200;
    }

    public String getSeqName() {
        return seqName;
    }

    public void setSeqName(String seqName) {
        this.seqName = seqName;
    }

    public long getSeqCurrentValue() {
        return seqCurrentValue;
    }

    public void setSeqCurrentValue(long seqCurrentValue) {
        this.seqCurrentValue = seqCurrentValue;
    }

    public long getSeqMaxValue() {
        return seqMaxValue;
    }

    public void setSeqMaxValue(long seqMaxValue) {
        this.seqMaxValue = seqMaxValue;
    }

    public long getSeqMinValue() {
        return seqMinValue;
    }

    public void setSeqMinValue(long seqMinValue) {
        this.seqMinValue = seqMinValue;
    }

    public long getSeqIncrementBy() {
        return seqIncrementBy;
    }

    public void setSeqIncrementBy(long seqIncrementBy) {
        this.seqIncrementBy = seqIncrementBy;
    }

    public String getSeqCycle() {
        return seqCycle == null ? "Y" : seqCycle.toUpperCase();
    }

    public void setSeqCycle(String seqCycle) {
        this.seqCycle = seqCycle;
    }

    public long getSeqCache() {
        return seqCache;
    }

    public void setSeqCache(long seqCache) {
        // 设置缓存计数
    	this.seqCache = seqCache;
        this.cacheCount = seqCache;
    }

    public long getCacheCount() {
        return cacheCount;
    }

    public void setCacheCount(long cacheCount) {
        this.cacheCount = cacheCount;
    }

    public long getSeqCount() {
        return seqCount;
    }

    public void setSeqCount(long seqCount) {
        this.seqCount = seqCount;
    }


    public long getNextVal(long value)
    {
        synchronized (this)
        {
            return seqCount;
        }

    }

}
