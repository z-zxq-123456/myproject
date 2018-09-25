package com.dcits.orion.core.dtp;

import com.dcits.galaxy.cache.utils.JedisUtils;
import com.dcits.galaxy.base.util.SeqUtils;
import com.dcits.galaxy.core.access.ThreadLocalManager;
import com.dcits.galaxy.dtp.sequence.BxidSequence;
import com.dcits.galaxy.dtp.sequence.LogIdSequence;
import com.dcits.galaxy.dtp.sequence.TxidSequence;

/**
 * Created by Tim on 2016/6/16.
 */
public class DtpSequence implements TxidSequence, BxidSequence, LogIdSequence {

    @Override
    public String nextBxid() {
        return SeqUtils.getStringSeq();
    }

    @Override
    public int nextIndexInBranch(String bxid) {
        int seqNo = (int) JedisUtils
                .getResourceIncrBy(ThreadLocalManager.getUID(), "B" + bxid);
        return seqNo;
    }

    @Override
    public int nextIndexInTrunk(String txid) {
        int seqNo = (int) JedisUtils
                .getResourceIncrBy(ThreadLocalManager.getUID(), txid);
        return seqNo;
    }

    @Override
    public String nextLogid() {
        return SeqUtils.getStringSeq();
    }

    @Override
    public int nextLogIndex(String bxid) {
        int seqNo = (int) JedisUtils
                .getResourceIncrBy(ThreadLocalManager.getUID(), "L" + bxid);
        return seqNo;
    }

    @Override
    public String nextTxid() {
        return null;
    }
}
