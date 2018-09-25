package com.dcits.galaxy.sequences.impl;

import com.dcits.galaxy.sequences.SequencesCacheManager;
import com.dcits.galaxy.sequences.SequencesConfiguration;
import com.dcits.galaxy.sequences.dao.SequencesDao;
import com.dcits.galaxy.sequences.model.Sequences;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 序列缓存管理器
 * 每个序列初始化时，将开启缓存的数据进行缓存，根据缓存大小一次从序列生成器中获取相应个数据的缓存数据。
 * 每次从序列管理中获取序列时，首先会从缓存去，缓存中全后，在向序列生成器获取一次。
 * <p/>
 * Created by Tim on 2016/4/3.
 */

public class ServiceShardSequencesCacheManager implements SequencesCacheManager {

    private static Logger logger = LoggerFactory.getLogger(ServiceShardSequencesCacheManager.class);

    private Map<String, Sequences> manager = new ConcurrentHashMap<>();

    private SequencesDao sequencesDao;

    private SequencesConfiguration sequencesConfiguration;

    private long persistentPeriod = 500; // 异步同步持久化序列周期，默认500ms

    private String persistentMode = "async";  //"async：异步方式同步；sync：同步方式更新"

    /**
     * 初始化序列管理器
     */
    @Override
    public void init() {
        /**
         List<Sequences> cacheList = sequencesDao.getCacheSequences();
         manager.clear();
         for (Sequences sequences : cacheList) {
         init(sequences);
         }

         if (logger.isInfoEnabled()) {
         logger.info("ShardSequences 缓存管理器初始化完成！");
         }
         */
    }

    /**
     * 用于初始化缓存管理器缓存
     *
     * @param sequences
     */
    /**
     public void init(Sequences sequences) {
     //缓存序列为0或者负数，不进行任何处理
     if (sequences.getSeqCache() <= 0)
     return;
     // seqCycle是否周期序列
     boolean cycle = sequences.getSeqCycle().equals("Y") ? true : false;
     // 更新下次序列号
     // 更新序列当前值，缓存个数乘以步长
     long skipSeq = sequences.getSeqCache() * sequences.getSeqIncrementBy();
     // 更新下次序列号
     long nextSeq = sequences.getSeqCurrentValue() + skipSeq;

     //如果下一批序号生成大于等于了最大值
     if (nextSeq > sequences.getSeqMaxValue()) {
     //缓存个数重定义，当前缓存数-跳号后超出的缓存个数/步长，进位取整
     sequences.setCacheCount(sequences.getSeqCache() - (nextSeq - sequences.getSeqMaxValue()) / sequences.getSeqIncrementBy());
     // 周期序列
     if (cycle) {
     //从起始号开始，重置为最小序列减步长
     sequences.setSeqCurrentValue(sequences.getSeqMinValue() - sequences.getSeqIncrementBy());
     } else {
     //将序列置为最大数
     sequences.setSeqCurrentValue(sequences.getSeqMaxValue());
     }
     // 将下次序列更新为缓存计数序列
     sequences.setSeqCount(sequences.getSeqCurrentValue());
     } else {
     // 将跳号后的序列更新到当前序列
     sequences.setSeqCurrentValue(nextSeq);
     }
     if (logger.isInfoEnabled()) {
     logger.info(String.format("序列缓存器初始化[%s][%s][%s]序列！", sequences.getSeqName(), sequences.getSeqCurrentValue(), sequences.getSeqCache()));
     }
     //同步更新处理
     if (persistentMode.equals("sync")) {
     sequencesDao.setNextSequence(sequences);
     }
     manager.put(sequences.getSeqName(), sequences);
     }
     */

    /**
     * 向序列管理器中添加一个序列
     *
     * @param seqName
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addSequence(String seqName) {
        //防止并发再次判断
        Sequences sequences = sequencesDao.getSequenceNoWait(seqName);
        if (null == sequences) {
            if (logger.isDebugEnabled()) {
                logger.debug(String.format("新增[%s]序列！", seqName));
            }
            sequences = sequencesConfiguration.getSequencesConfiguration(seqName);
            sequencesDao.insertSequence(sequences);
        }

        // 序列跳号更新到数据库
        sequences.setCacheCount(sequences.getSeqCache());
        sequences.setSeqCount(sequences.getSeqCurrentValue());
        sequences.setSeqCurrentValue(sequences.getSeqCurrentValue() + sequences.getCacheCount() * sequences.getSeqIncrementBy());
        sequencesDao.setNextForLocalSequence(sequences);
        // 序列添加到缓存计数器
        addSequence(sequences);
    }

    /**
     * 向序列管理器中添加一个序列
     *
     * @param sequences
     */
    @Override
    public void addSequence(Sequences sequences) {
        manager.put(sequences.getSeqName(), sequences);
    }

    /**
     * 判断是否存在序列
     *
     * @param seqName
     * @return
     */
    @Override
    public boolean containsSequence(String seqName) {
        return manager.containsKey(seqName);
    }

    /**
     * 获取当前序列
     *
     * @param seqName
     * @return
     */
    @Override
    public long getSequence(String seqName, long value) {//同步关键字不应该放在方法上，应该放在Sequences对象上
        Sequences sequences = manager.get(seqName);
        if (null == sequences) {
            if (logger.isInfoEnabled()) {
                logger.info(String.format("缓存序列管理器中无此[%s]序列！", seqName));
            }
            return -1;
        }
        if (value == 0)
            return sequences.getSeqCount();
        synchronized (sequences)//同步关键字应该放在Sequences对象上
        {
            long nextSeq = 0;
            if (value < 0) {
                nextSeq = sequences.getSeqCount() + sequences.getSeqIncrementBy();
            } else {
                nextSeq = sequences.getSeqCount() + value * sequences.getSeqIncrementBy();
            }
            // seqCycle是否周期序列
            boolean cycle = sequences.getSeqCycle().equals("Y") ? true : false;

            if (nextSeq > sequences.getSeqMaxValue()) {//超出最大值了
                if (cycle)//循环序列从最小值开始
                {
                    sequences.setCacheCount(sequences.getSeqCache());
                    if (value < 0) {
                        sequences.setSeqCount(sequences.getSeqMinValue());
                        sequences.setSeqCurrentValue(sequences.getSeqMinValue() + sequences.getSeqCache() * sequences.getSeqIncrementBy());
                    } else {
                        sequences.setSeqCount(sequences.getSeqMinValue() + (value - 1));
                        sequences.setSeqCurrentValue(sequences.getSeqMinValue() + (value - 1) + (sequences.getSeqIncrementBy() * sequences.getCacheCount()));
                    }
                    //同步更新处理
                    if (persistentMode.equals("sync")) {
                        sequencesDao.setNextSequence(sequences);
                    }
                } else {
                    if (logger.isErrorEnabled()) {
                        logger.error(String.format("[%s]非循环序列已经超过最大值，无法获取下一序列！", seqName));
                    }
                    return -1;
                }
                return sequences.getSeqCount();
            }
            if (value < 0)
                sequences.setCacheCount(sequences.getCacheCount() - 1);
            else
                sequences.setCacheCount(sequences.getCacheCount() - value);
            if (sequences.getCacheCount() < 0) {//缓存用完了
                sequences.setCacheCount(sequences.getSeqCache());
                sequences.setSeqCurrentValue(nextSeq + sequences.getSeqCache() * sequences.getSeqIncrementBy());
                if (persistentMode.equals("sync")) {
                    sequencesDao.setNextSequence(sequences);
                }
            }
            sequences.setSeqCount(nextSeq);
            return sequences.getSeqCount();
        }
    }
    /*
    public synchronized long getSequence(String seqName,long value) {
        Sequences sequences;
        sequences = manager.get(seqName);
        if (null == sequences) {
            if (logger.isInfoEnabled()) {
                logger.info(String.format("缓存序列管理器中无此[%s]序列！", seqName));
            }
            return -1;
        }
        // seqCycle是否周期序列
        boolean cycle = sequences.getSeqCycle().equals("Y") ? true : false;
        //检查缓存计数是否可用，并对缓存个数减一处理
        sequences.setCacheCount(sequences.getCacheCount() - value);
        // 内存已经被取空
        if (sequences.getCacheCount() < 0) {
            // 判断是否等于最大序列
            if (sequences.getSeqCount() >= sequences.getSeqMaxValue()) {
                if (!cycle) {
                    if (logger.isErrorEnabled()) {
                        logger.error(String.format("[%s]非循环序列已经超过最大值，无法获取下一序列！", seqName));
                    }
                    return -1;
                }
            }
            // 初始化缓存计数
            sequences.setCacheCount(sequences.getSeqCache());
            init(sequences);
            // 递归取
            return getSequence(seqName,value);
        }
        long nextSeq = 0 ;
        if (value <= 0)
        {
            nextSeq = sequences.getSeqCount() + sequences.getSeqIncrementBy();
        }
        else {
            nextSeq = sequences.getSeqCount() + value;
        }
        if (nextSeq > sequences.getSeqMaxValue()) {
            // 初始化缓存计数
            sequences.setCacheCount(sequences.getSeqCache());
            init(sequences);
            // 递归取
            return getSequence(seqName,value);
        }
        sequences.setSeqCount(nextSeq);
        return sequences.getSeqCount();
    }
    */


    /**
     * 持久化缓存
     */
    @Override
    @Transactional
    public void persistentSequences() {
        List<Sequences> sequences = new ArrayList<>(manager.values());
        sequencesDao.setNextSequence(sequences);
    }

    public SequencesConfiguration getSequencesConfiguration() {
        return sequencesConfiguration;
    }

    public void setSequencesConfiguration(SequencesConfiguration sequencesConfiguration) {
        this.sequencesConfiguration = sequencesConfiguration;
    }

    public SequencesDao getSequencesDao() {
        return sequencesDao;
    }

    public void setSequencesDao(SequencesDao sequencesDao) {
        this.sequencesDao = sequencesDao;
    }

    @Override
    public long getPersistentPeriod() {
        return persistentPeriod;
    }

    public void setPersistentPeriod(long persistentPeriod) {
        this.persistentPeriod = persistentPeriod;
    }

    @Override
    public String getPersistentMode() {
        return persistentMode;
    }

    /**
     * 获取序列列表
     *
     * @return
     */
    @Override
    public Collection<Sequences> getSequences() {
        return manager.values();
    }

    public void setPersistentMode(String persistentMode) {
        this.persistentMode = persistentMode;
    }
}

