package com.dcits.galaxy.sequences;

import com.dcits.galaxy.core.access.ThreadLocalManager;
import com.dcits.galaxy.sequences.model.SeqQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 基于序列服务实现自增Sequence序列号
 *
 * @author Tim
 * @version V1.0
 * @description
 * @update 2016年4月3日 下午2:10:02
 */

public abstract class AbstractGenerateSeq {
    private static final Logger logger = LoggerFactory
            .getLogger(AbstractGenerateSeq.class);

    public static final String GALAXY_SEQUENCES = "galaxy.sequences";

    protected String sequenceName;

    public AbstractGenerateSeq(String sequenceName) {
        this.sequenceName = sequenceName;
    }

    /**
     * 通过序列服务实现Sequence
     *
     * @return
     * @description
     * @version 1.0
     * @author Tim
     * @update 2014年11月19日 下午2:24:25
     */
    protected long getSeqNo() {
        // 分布式序列生成器
    	long seqNo = getSeqNoFormThreadLocal();
    	if(seqNo!=-1){
    		return seqNo;
    	}
        return SequencesHandler.getShardSequence(this.sequenceName);
    }
    
    /**
     * 建议使用getSeqList方法
     *
     * @param value
     * @return
     */
    @Deprecated
    public long getSeqNo(long value) {
        // 分布式序列生成器
        return SequencesHandler.getShardSequence(this.sequenceName,value);
    }
    
    /**
     * 建议使用getSeqList方法
     *
     * @param value
     * @return
     */
    public List<Long> getSeqList(long value) {
        // 分布式序列生成器
        return SequencesHandler.getShardSequenceList(this.sequenceName, value);
    }
    /**
     * 默认直接返回计数器当前序列号<br>
     * 如果需要特殊处理， 子类可覆写改函数
     *
     * @return
     * @description
     * @version 1.0
     * @author Tim
     * @update 2014年11月19日 下午2:25:52
     */
    public long generateSeqNo() {
        return getSeqNo();
    }
    
    /**
     * 添加List序列集合到线程缓存
     * 
     * @param value 序列个数
     */
    public void addSeqListToThreadLocal(long value){
    	List<Long> seqList = getSeqList(value);
    	if(hasContainsTheadLocalSeq()){
    		SeqQueue seqQueue = (SeqQueue) ThreadLocalManager.get(this.sequenceName);
    		seqQueue.getQueue().addAll(seqList);
    	}else{
    		Queue<Long> queue = new LinkedList<Long>();
    		queue.addAll(seqList);
    		SeqQueue seqQueue = new SeqQueue(this.sequenceName,value,queue);
        	ThreadLocalManager.put(this.sequenceName, seqQueue);
    	}
    }
    
    /**
     * 从线程缓存按顺序获得序列号
     * 
     * @return 序列号
     */
    private long getSeqNoFormThreadLocal(){
    	if(hasContainsTheadLocalSeq()){
    		SeqQueue seqQueue = (SeqQueue) ThreadLocalManager.get(this.sequenceName);
    		if(seqQueue.hasQueue()){
    			return seqQueue.getQueue().poll();
    		}else{
    			return -1;
    		}
    	}else{
    		return -1; 
    	}
    }
    
    /**
     * 判断该序列是否存在线程缓存中
     * 
     * @return
     */
    private boolean hasContainsTheadLocalSeq(){
    	boolean flag = true;
    	if(null==ThreadLocalManager.get(this.sequenceName)){
    		flag = false;
    	}
    	return flag;
    }
}
