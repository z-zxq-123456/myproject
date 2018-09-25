package com.dcits.galaxy.sequences.dao;

import com.dcits.galaxy.sequences.model.Sequences;
import com.dcits.galaxy.dal.mybatis.ShardSqlSessionTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Tim on 2016/4/2.
 */
public class SequencesDao {

    private final String nameSpace = "com.dcits.galaxy.sequences.dao.SequencesDao";

    private ShardSqlSessionTemplate shardSqlSessionTemplate;

    /**
     * 插入自增序列
     *
     * @param sequences
     *         序列模型
     * @return
     */
    public int insertSequence(Sequences sequences) {
        return shardSqlSessionTemplate.insert(nameSpace, "insertSequence", sequences);
    }

    /**
     * 更新自增序列
     *
     * @param seqName
     *         序列名称
     * @param sequences
     *         序列模型
     * @return
     */
    public int updateSequence(String seqName, Sequences sequences) {
        return shardSqlSessionTemplate.update(nameSpace, "updateSequence", sequences);
    }

    /**
     * 设置下一序列
     *
     * @param sequences
     *         序列模型
     * @return
     */

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int setNextSequence(Sequences sequences) {
        return shardSqlSessionTemplate.update(nameSpace, "setNextSequence", sequences);
    }

    public int setNextForLocalSequence(Sequences sequences) {
        return shardSqlSessionTemplate.update(nameSpace, "setNextSequence", sequences);
    }

    /**
     * 设置下一序列
     *
     * @param sequences
     *         序列模型
     * @return
     */
    public int setNextSequence(List<Sequences> sequences) {
        return shardSqlSessionTemplate.updateAddBatch(nameSpace, "setNextSequence", sequences);
    }

    /**
     * 获取序列
     * 数据序列查询过程有行级锁，保证多个节点同时查询序列获取的安全问题
     * 发并发下会有性能影响
     *
     * @param seqName
     *         序列名称
     * @return
     */
    public Sequences getSequence(String seqName) {
        Sequences sequences = new Sequences();
        sequences.setSeqName(seqName);
        return (Sequences) shardSqlSessionTemplate.selectOne(nameSpace, "getSequence", sequences);
    }

    /**
     * 获取序列
     *
     * @param seqName
     *         序列名称
     * @return
     */
    public Sequences getSequenceNoWait(String seqName) {
        Sequences sequences = new Sequences();
        sequences.setSeqName(seqName);
        return (Sequences) shardSqlSessionTemplate.selectOne(nameSpace, "getSequenceNoWait", sequences);
    }

    /**
     * 获取缓存序列集合
     *
     * @return
     */
    public List<Sequences> getCacheSequences() {
        return shardSqlSessionTemplate.selectList(nameSpace, "getCacheSequences");
    }

    public void setShardSqlSessionTemplate(ShardSqlSessionTemplate shardSqlSessionTemplate) {
        this.shardSqlSessionTemplate = shardSqlSessionTemplate;
    }

    public ShardSqlSessionTemplate getShardSqlSessionTemplate() {
        return shardSqlSessionTemplate;
    }
}
