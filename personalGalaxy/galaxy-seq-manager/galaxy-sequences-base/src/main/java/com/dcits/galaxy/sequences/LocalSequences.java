package com.dcits.galaxy.sequences;

import com.dcits.galaxy.sequences.model.Sequences;

import java.util.List;

/**
 * 直接访问方式获取序列
 * <p/>
 * Created by Tim on 2016/4/4.
 */
public interface LocalSequences {

    /**
     * 获取自增序列
     *
     * @param sequenceName
     * @return
     */
    long getLocalSeqNo(String sequenceName);
    
    /**
     * 获取自增序列
     *
     * @param sequenceName
     * @return
     */
    @Deprecated
    long getLocalSeqNo(String sequenceName, long value);
    
    /**
     * 获取自增序列
     *
     * @param sequenceName
     * @return
     */
    List<Long> getLocalSeqNoList(String sequenceName, long value);

    /**
     * 创建自增序列
     * 不会添加到缓存管理，仅做持久化动作
     *
     * @param sequence
     * @return
     */
    void createLocalSequence(Sequences sequence);

    /**
     * 判断是否存在的序列
     *
     * @param seqName
     * @return
     */
    boolean containsSequence(String seqName);
}
