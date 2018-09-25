package com.dcits.galaxy.sequences;

import com.dcits.galaxy.sequences.model.Sequences;
import com.dcits.galaxy.dtp.branch.BranchPropagate;
import com.dcits.galaxy.dtp.branch.BranchPropagate.Propagation;

import java.util.Collection;
import java.util.List;

/**
 * 分布式系统自增序列获取服务
 * <p/>
 * Created by Tim on 2016/4/2.
 */
@BranchPropagate(Propagation.NONE)
public interface ShardSequences {

    /**
     * 获取自增序列
     *
     * @param sequenceName
     * @return
     */
    long getSeqNo(String sequenceName);

    /**
     * 获取自增序列
     *
     * @param sequenceName
     * @return
     */
    @Deprecated
    long getSeqNo(String sequenceName,long value);
    
    /**
     * 获取自增序列
     *
     * @param sequenceName
     * @return
     */
    List<Long> getSeqNoList(String sequenceName, long value);

    /**
     * 获取序列列表
     *
     * @return
     */
    Collection<Sequences> getSequences();

    /**
     * 获取序列对象
     *
     * @return
     */
    Collection<Sequences> getSequences(String seqName);

    /**
     * 探针服务
     * 为消费者调用使用。可以提供空方法
     */
    void probe();

    /**
     * 创建序列
     * 并将序列添加到序列管理器
     */
    void createSequence(Sequences sequence);

}
