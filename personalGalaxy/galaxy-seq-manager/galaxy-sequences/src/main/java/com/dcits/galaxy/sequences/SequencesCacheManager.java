package com.dcits.galaxy.sequences;

import com.dcits.galaxy.sequences.model.Sequences;

import java.util.Collection;

/**
 * 序列缓存管理器
 * <p/>
 * Created by Tim on 2016/4/3.
 */
public interface SequencesCacheManager {

    /**
     * 初始化序列管理器
     *
     */
    void init();

    void addSequence(String seqName);

    /**
     * 向缓存管理器中添加一个序列
     *
     * @param sequences
     */
    void addSequence(Sequences sequences);

    /**
     * 判断是否存在序列
     *
     * @param seqName
     * @return
     */
    boolean containsSequence(String seqName);

    /**
     * 获取当前序列
     *
     * @param seqName
     * @return
     */
    long getSequence(String seqName,long value);

    /**
     * 持久化缓存
     */
    void persistentSequences();

    /**
     * 获取异步持久化周期
     *
     * @return
     */
    long getPersistentPeriod();

    /**
     * 获取持久化方式
     *
     * @return async：异步方式
     * sync：同步方式
     */
    String getPersistentMode();

    /**
     * 获取序列列表
     *
     * @return
     */
    Collection<Sequences> getSequences();
}
