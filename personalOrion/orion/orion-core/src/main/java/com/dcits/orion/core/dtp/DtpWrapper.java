package com.dcits.orion.core.dtp;

import com.dcits.orion.core.util.BusinessUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dcits.galaxy.base.util.StringUtils;
import com.dcits.galaxy.core.spring.SpringApplicationContext;
import com.dcits.galaxy.dtp.DtpContext;
import com.dcits.galaxy.dtp.branch.BranchManagerHelper;
import com.dcits.galaxy.dtp.branch.BranchTransaction;
import com.dcits.galaxy.dtp.sequence.BxidSequence;
import com.dcits.galaxy.dtp.trunk.TrunkManager;
import com.dcits.galaxy.dtp.trunk.TrunkManagerFactory;
import com.dcits.galaxy.dtp.trunk.TrunkService;
import com.dcits.galaxy.dtp.trunk.TrunkServiceFactory;
import com.dcits.galaxy.dtp.trunk.TrunkTransaction;

/**
 * 全局事务控制封装
 * <p/>
 * Created by Tim on 2015/11/19.
 */
public class DtpWrapper {

    private static final Logger log = LoggerFactory.getLogger(DtpWrapper.class);

    public static boolean dtpIsOpen() {
        return StringUtils.isNotEmpty(DtpContext.getTxid());
    }

    /**
     * 全局事物开启
     */
    public static void tmOpen() {
        if (dtpIsOpen()) {
            if (log.isInfoEnabled())
                log.info("开启全局事务控制");
            TrunkManager tm = TrunkManagerFactory.getBean();
            // 全局事物开启
            TrunkTransaction trunk = tm.begin(DtpContext.getTxid());
            if (trunk == null) {
                throw BusinessUtils.createBusinessException("007008");
            }
        }
    }

    /**
     * 全局事物取消
     */
    public static void tmCancel() {
        if (dtpIsOpen()) {
            if (log.isInfoEnabled())
                log.info("准备阶段出错，取消全局事务");
            // 全局事物取消
            TrunkManager tm = TrunkManagerFactory.getBean();
            try {
            	tm.cancel(DtpContext.getTxid());
			} catch (Exception e) {
				// ignore
			}
        }
    }

    /**
     * 全局事物确认
     */
    public static void tmConfirm() {
        if (dtpIsOpen()) {
            if (log.isInfoEnabled())
                log.info("准备阶段结束，全局事务确认处理");
            // 全局事物确认
            TrunkManager tm = TrunkManagerFactory.getBean();
            try {
            	tm.confirm(DtpContext.getTxid());
			} catch (Exception e) {
				// ignore
			}
        }
    }
    
    public static void tmSetConfirm() {
        if (dtpIsOpen()) {
            if (log.isInfoEnabled())
                log.info("准备阶段结束，全局事务确认处理");
            // 全局事物确认
            TrunkService trunkService = TrunkServiceFactory.getBean();
            trunkService.setConfirm(DtpContext.getTxid());
        }
    }

    /**
     * 启动分支事务管理器
     *
     * @return 分支事务id
     */
    public static String beginBranchTransaction() {
        if (dtpIsOpen()) {
            BxidSequence bxidSequence = SpringApplicationContext.getContext().getBean(BxidSequence.class);
            String bxId = bxidSequence.nextBxid();
            int indexInTrunk = DtpContext.nextTrunkIndex();
            BranchTransaction branch = BranchManagerHelper.begin(bxId, indexInTrunk, DtpContext.getTxid());
            if (branch == null) {
                throw BusinessUtils.createBusinessException("007003");
            }
            return bxId;
        }
        return null;
    }

    /**
     * 启动分支事务管理器
     *
     * @return 分支事务id
     */
    public static String addBranchTransaction(String bxId) {
        if (dtpIsOpen()) {
            BxidSequence bxidSequence = SpringApplicationContext.getContext().getBean(BxidSequence.class);
            String subBxId = bxidSequence.nextBxid();
            int indexInBranch = DtpContext.nextBranchIndex();
            BranchTransaction branch = BranchManagerHelper.begin(subBxId, indexInBranch, DtpContext.getTxid(), bxId);
            if (branch == null) {
                throw BusinessUtils.createBusinessException("007003");
            }
            return subBxId;
        }
        return null;
    }
}
