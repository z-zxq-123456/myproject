package com.dcits.galaxy.sequences.impl;

import com.alibaba.dubbo.common.utils.ConfigUtils;
import com.dcits.galaxy.sequences.LocalSequences;
import com.dcits.galaxy.sequences.SequencesConfiguration;
import com.dcits.galaxy.sequences.SequencesHandler;
import com.dcits.galaxy.sequences.SequencesNameManager;
import com.dcits.galaxy.sequences.ShardSequences;
import com.dcits.galaxy.sequences.dao.SequencesDao;
import com.dcits.galaxy.sequences.model.Sequences;
import com.dcits.galaxy.base.exception.BusinessException;
import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.galaxy.base.util.ErrorUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 基于服务的分布式序列实现
 * 集群中只有一个节点的服务提供服务，节点宕掉后，其它可用节点负责接替序列服务生成工作。
 * 集群中的zk协调者全部宕机后，每个节点通过访问本地数据库，读取序列后并更新序列。
 * 当zk集群恢复后，不在实时读取数据库序列
 * Created by Tim on 2016/4/2.
 */
public class ServiceShardSequences implements ShardSequences, LocalSequences {

    private static Logger logger = LoggerFactory.getLogger(ServiceShardSequences.class);

    private SequencesDao sequencesDao;

    private SequencesConfiguration sequencesConfiguration;

    private ServiceShardSequencesCacheManager shardSequencesCacheManager;

    private byte[] lock = new byte[0];

//    private Set<String> initSeq = new HashSet<>();

    /**
     * 默认序列集合最大可取数量，默认为20000
     */
    private long sequenceListLimitOver = 20000;

    /**
     * 获取自增序列
     *
     * @param seqName
     * @param value
     * @return
     */
    @Override
    public long getSeqNo(String seqName, long value) {
        // 缓存序列，如果没有则查询一次数据库，若数据库也不存在，再创建
        if (!shardSequencesCacheManager.containsSequence(seqName)) {
            synchronized (lock) {
                if (!shardSequencesCacheManager.containsSequence(seqName)) {
                    shardSequencesCacheManager.addSequence(seqName);
                }
            }
        }
        long seq = shardSequencesCacheManager.getSequence(seqName, value);
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("%s--service [%s] cache get", seq, seqName));
        }
        return seq;
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
        long start = 0;
        if (logger.isInfoEnabled()) {
            start = System.currentTimeMillis();
        }
        try {
            if (value < sequenceListLimitOver) {
                long seqNo = getSeqNo(sequenceName, value);
                return SequencesHandler.getSeqList(seqNo, value);
            } else {
                throw new GalaxyException("The SequenceList Size Must Be Least Than " + sequenceListLimitOver);
            }
        } finally {
            if (logger.isInfoEnabled()) {
                logger.info("{} get Sequences List [{}] , Execute time [{}] from cache ...", sequenceName, value, System.currentTimeMillis() - start);
            }
        }

    }

    /**
     * 获取自增序列
     *
     * @param sequenceName
     * @return
     */
    @Override
    public long getSeqNo(String sequenceName) {
        return getSeqNo(sequenceName, -1);
    }

    /**
     * 获取序列缓存管理器对象
     *
     * @return
     */
    @Override
    public Collection<Sequences> getSequences() {
        return shardSequencesCacheManager.getSequences();
    }

    /**
     * 获取序列缓存管理器对象
     *
     * @param seqName
     * @return
     */
    @Override
    public Collection<Sequences> getSequences(String seqName) {
        Collection<Sequences> seqs = new ArrayList<>();
        Iterator<Sequences> it = shardSequencesCacheManager.getSequences().iterator();
        for (; it.hasNext(); ) {
            Sequences seq = it.next();
            if (seq.getSeqName().indexOf(seqName) != -1) {
                seqs.add(seq);
            }
        }
        return seqs;
    }

    /**
     * 探测服务
     * 为消费者调用使用。可以提供空方法
     */
    @Override
    public void probe() {
    }

    /**
     * @param sequence
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public synchronized void createSequence(Sequences sequence) {
        sequencesDao.insertSequence(sequence);
        shardSequencesCacheManager.addSequence(sequence);
    }

    /**
     * 本地方式获取序列，每次访问数据库。
     * 如果原来没有序列数据，将插入一条新的序列。
     * 如果原来有序列数据，将在原数据基础上加上步长，并返回
     *
     * @param seqName
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public long getLocalSeqNo(String seqName, long value) {
        // 基于Spring bean的序列生成实现
        Sequences sequences = sequencesDao.getSequence(seqName);
        if (sequences == null) {
            sequences = addNewSequence(seqName);
        }

        if (value == 0) {
            return sequences.getSeqCurrentValue();
        }

        if (value < 0)
            sequences.setSeqCurrentValue(sequences.getSeqCurrentValue() + sequences.getSeqIncrementBy());
        else
            sequences.setSeqCurrentValue(sequences.getSeqCurrentValue() + value * +sequences.getSeqIncrementBy());
        // seqCycle是否周期序列
        boolean cycle = sequences.getSeqCycle().equals("Y") ? true : false;
        // 判断是否超过最大序列
        if (sequences.getSeqCurrentValue() > sequences.getSeqMaxValue()) {
            // 非循环序列，当前序列已经超过最大值
            if (!cycle) {
                if (logger.isInfoEnabled()) {
                    logger.info(String.format("[%s]非循环序列已经超过最大值，无法获取下一序列！", seqName));
                }
                return -1;
            } else {
                if (value < 0) {
                    sequences.setSeqCurrentValue(sequences.getSeqMinValue());
                } else {
                    sequences.setSeqCurrentValue(sequences.getSeqMinValue() + (value - 1) * sequences.getSeqIncrementBy());
                }
            }
        }
        sequencesDao.setNextForLocalSequence(sequences);
        //当前序列值
        long seq = sequences.getSeqCurrentValue();
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("%s--local [%s] get", seq, seqName));
        }
        return seq;

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public long getLocalSeqNo(String sequenceName) {
        return getLocalSeqNo(sequenceName, -1);
    }

    /**
     * 获取自增序列
     *
     * @param sequenceName
     * @param value
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Long> getLocalSeqNoList(String sequenceName, long value) {
        long start = 0;
        if (logger.isInfoEnabled()) {
            start = System.currentTimeMillis();
        }
        try {
            if (value <= sequenceListLimitOver) {
                long seqNo = getLocalSeqNo(sequenceName, value);
                return SequencesHandler.getSeqList(seqNo, value);
            } else {
                throw new GalaxyException("The SequenceList Size Must Be Least Than " + sequenceListLimitOver);
            }
        } finally {
            if (logger.isInfoEnabled()) {
                logger.info("{} get Sequences List [{}] from local ...", sequenceName, System.currentTimeMillis() - start);
            }
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
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createLocalSequence(Sequences sequence) {
        if (null == sequence.getSeqName()) {
            throw new BusinessException("000310", ErrorUtils.getErrorDesc("000310"));
        }
        // 最小序列
        if (0 == sequence.getSeqMinValue())
            sequence.setSeqMinValue(1);
        // 最大序列
        if (0 == sequence.getSeqMaxValue())
            sequence.setSeqMaxValue(9999999999L);
        // 缓存序列数
        if (0 == sequence.getSeqCache())
            sequence.setSeqCache(200);
        else {
            if (sequence.getSeqCache() > 2000)
                // 缓存序列不能超过2000
                sequence.setSeqCache(2000);
            else if (sequence.getSeqCache() < 200)
                // 缓存序列不能低于200
                sequence.setSeqCache(200);
        }
        // 设置步长
        if (0 == sequence.getSeqIncrementBy())
            sequence.setSeqIncrementBy(1);
        // 设置当前值为最小值减去步长
        sequence.setSeqCurrentValue(sequence.getSeqMinValue() - sequence.getSeqIncrementBy());
        if (null == sequence.getSeqCycle())
            sequence.setSeqCycle("Y");
        sequencesDao.insertSequence(sequence);
        // 将名称添加到名称管理器
        SequencesNameManager.getInstance().addName(sequence.getSeqName());
    }

    /**
     * 判断是否存在的序列
     *
     * @param seqName
     * @return
     */
    @Override
    public boolean containsSequence(String seqName) {
        boolean exist = SequencesNameManager.getInstance().contains(seqName);
        // 如果序列名称缓存不存在
        if (!exist) {
            // 查询数据库
            Sequences seq = sequencesDao.getSequenceNoWait(seqName);
            // 数据库存在
            if (seq != null) {
                // 存在，将名称添加到名称管理器
                SequencesNameManager.getInstance().addName(seqName);
                return true;
            }
            // 不存在
            return false;
        }
        // 返回存在
        return true;
    }

    private Sequences addNewSequence(String seqName) {
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("新增[%s]序列！", seqName));
        }
        Sequences sequences = sequencesConfiguration.getSequencesConfiguration(seqName);
        sequencesDao.insertSequence(sequences);
        return sequences;
    }

    private String getProperties(String name, String defaultValue) {
        return null == ConfigUtils.getProperty(name) ? defaultValue : ConfigUtils.getProperty(name);
    }


    public void setSequencesConfiguration(SequencesConfiguration sequencesConfiguration) {
        this.sequencesConfiguration = sequencesConfiguration;
    }

    public void setSequencesDao(SequencesDao sequencesDao) {
        this.sequencesDao = sequencesDao;
    }

    public ServiceShardSequencesCacheManager getShardSequencesCacheManager() {
        return shardSequencesCacheManager;
    }

    public void setShardSequencesCacheManager(ServiceShardSequencesCacheManager shardSequencesCacheManager) {
        this.shardSequencesCacheManager = shardSequencesCacheManager;
    }

    public long getSequenceListLimitOver() {
        return sequenceListLimitOver;
    }

    public void setSequenceListLimitOver(long sequenceListLimitOver) {
        this.sequenceListLimitOver = sequenceListLimitOver;
    }

}
