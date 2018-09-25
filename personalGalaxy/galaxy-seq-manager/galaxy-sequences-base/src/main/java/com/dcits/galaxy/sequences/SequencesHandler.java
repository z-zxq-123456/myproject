package com.dcits.galaxy.sequences;

import com.alibaba.dubbo.common.utils.ConfigUtils;
import com.dcits.galaxy.base.exception.BusinessException;
import com.dcits.galaxy.base.util.ErrorUtils;
import com.dcits.galaxy.core.client.ServiceProxy;
import com.dcits.galaxy.core.client.builder.Attributes;
import com.dcits.galaxy.core.client.builder.AttributesBuilderSupport;
import com.dcits.galaxy.core.client.builder.ServiceAttributesBuilder;
import com.dcits.galaxy.core.spring.SpringApplicationContext;
import com.dcits.galaxy.sequences.model.Sequences;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 分布式序列生成器助手类
 * <p/>
 * Created by Tim on 2016/4/2.
 */
public class SequencesHandler {

    private static Logger logger = LoggerFactory.getLogger(SequencesHandler.class);

    public static final String LOCAL_SEQUENCES_ID = "localSequences";

    /**
     * 获取RPC序列服务，如果RPC服务不存在
     * 则获取当前IOC容器中获取序列服务
     *
     * @return
     */
    public static long getShardSequence(String seqName) {
        String mode = getProperties("galaxy.application.mode", "produce");
        // 生产模式下。从远程序列服务获取自增序列
        if ("produce".equals(mode)) {
            ServiceProxy serviceProxy = ServiceProxy.getInstance();
            Attributes attributes = new ServiceAttributesBuilder()
                    .setLoadbalance(getProperties("galaxy.business.service.loadBalance",
                            AttributesBuilderSupport.LOADBALANCE_ROUNDROBIN))
                    .setGroup(getProperties("galaxy.business.sequences.group", "shardSequences"))
                    .setCheck(true)
                    .build();
            ShardSequences shardSequences = serviceProxy.getService(ShardSequences.class, attributes);
            return shardSequences.getSeqNo(seqName);
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("从本地获取序列！");
            }
            // 开发模式下从本地获取序列
            LocalSequences localSequences = (LocalSequences) SpringApplicationContext.getContext().getBean(LOCAL_SEQUENCES_ID);
            return localSequences.getLocalSeqNo(seqName);
        }
    }

    /**
     * 获取RPC序列服务，如果RPC服务不存在
     * 则获取当前IOC容器中获取序列服务
     *
     * @return
     */
    public static long getShardSequence(String seqName, long value) {
        String mode = getProperties("galaxy.application.mode", "produce");
        // 生产模式下。从远程序列服务获取自增序列
        if ("produce".equals(mode)) {
            ServiceProxy serviceProxy = ServiceProxy.getInstance();
            Attributes attributes = new ServiceAttributesBuilder()
                    .setLoadbalance(getProperties("galaxy.business.service.loadBalance",
                            AttributesBuilderSupport.LOADBALANCE_ROUNDROBIN))
                    .setGroup(getProperties("galaxy.business.sequences.group", "shardSequences"))
                    .setCheck(true)
                    .build();
            ShardSequences shardSequences = serviceProxy.getService(ShardSequences.class, attributes);
            return shardSequences.getSeqNo(seqName, value);
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("从本地获取序列！");
            }
            // 开发模式下从本地获取序列
            LocalSequences localSequences = (LocalSequences) SpringApplicationContext.getContext().getBean(LOCAL_SEQUENCES_ID);
            return localSequences.getLocalSeqNo(seqName, value);
        }
    }

    /**
     * 获取RPC序列服务，如果RPC服务不存在
     * 则获取当前IOC容器中获取序列服务
     *
     * @return
     */
    public static List<Long> getShardSequenceList(String seqName, long value) {
    	String mode = getProperties("galaxy.application.mode", "produce");
    	// 生产模式下。从远程序列服务获取自增序列
    	if ("produce".equals(mode)) {
    		ServiceProxy serviceProxy = ServiceProxy.getInstance();
    		Attributes attributes = new ServiceAttributesBuilder()
    				.setLoadbalance(getProperties("galaxy.business.service.loadBalance",
    						AttributesBuilderSupport.LOADBALANCE_ROUNDROBIN))
    				.setGroup(getProperties("galaxy.business.sequences.group", "shardSequences"))
    				.setCheck(true)
    				.build();
    		ShardSequences shardSequences = serviceProxy.getService(ShardSequences.class, attributes);
    		return shardSequences.getSeqNoList(seqName, value);
    	} else {
    		if (logger.isDebugEnabled()) {
    			logger.debug("从本地获取序列！");
    		}
    		// 开发模式下从本地获取序列
    		LocalSequences localSequences = (LocalSequences) SpringApplicationContext.getContext().getBean(LOCAL_SEQUENCES_ID);
    		return localSequences.getLocalSeqNoList(seqName, value);
    	}
    }
    
    /**
     * 判断序列是否存在
     *
     * @param seqName
     */
    public static boolean containsSequence(String seqName) {
        // 本地获取序列
        LocalSequences localSequences = (LocalSequences) SpringApplicationContext.getContext().getBean(LOCAL_SEQUENCES_ID);
        return localSequences.containsSequence(seqName);
    }

    /**
     * 创建序列
     *
     * @param seq
     */
    public static void createLocalSequences(Sequences seq) {
        // 本地获取序列
        LocalSequences localSequences = (LocalSequences) SpringApplicationContext.getContext().getBean(LOCAL_SEQUENCES_ID);
        boolean exits = localSequences.containsSequence(seq.getSeqName());
        if (exits) {
            throw new BusinessException("000309", ErrorUtils.getParseErrorDesc("000309", new String[]{seq.getSeqName()}));
        }
        localSequences.createLocalSequence(seq);
    }

    /**
     * 获取galaxy.properties属性
     *
     * @param name
     * @param defaultValue
     * @return
     */
    public static String getProperties(String name, String defaultValue) {
        return null == ConfigUtils.getProperty(name) ? defaultValue : ConfigUtils.getProperty(name);
    }

    /**
     * 通过最大数和序列个数，获取Seq List集合
     *
     * @param maxSeq
     * @param count
     * @return
     */
    public static List<Long> getSeqList(long maxSeq, long count) {
        List<Long> seqList = new ArrayList();
        for (long i = (maxSeq - count); i <= maxSeq; i++) {
            seqList.add(i);
        }
        return seqList;
    }

}
