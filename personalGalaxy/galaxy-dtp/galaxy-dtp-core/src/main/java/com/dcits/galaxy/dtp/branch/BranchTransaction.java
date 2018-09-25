package com.dcits.galaxy.dtp.branch;

import java.io.Serializable;

/**
 * 分支事务
 *
 * @author Yin.Weicai
 */
public class BranchTransaction implements Serializable {

    private static final long serialVersionUID = -141437061564020365L;

    /**
     * 分支事务标识
     */
    private String bxid;

    /**
     * 父级分支事务标识。在分支事务内再嵌套分支事务的情况下，parentBxid指的是外层分支事务标识。
     */
    private String parentBxid = "unknow";

    /**
     * 用于分支事务存在嵌套的情况,一个分支事务内再包含一个或多个分支事务时，
     * 如果这些嵌套的分支事务有执行顺序要求，indexInBranch标记嵌套分支事务的执行顺序。
     * indexInBranch设置要求：0 =< indexInBranch =< 无穷大 ，
     * -1： 表示无顺序要求,值越小，表示越执行顺序越靠前。
     * 默认值: -1
     */
    private int indexInBranch = -1;

    /**
     * 主事务标识
     */
    private String txid;

    /**
     * 主事务有一个或多个分支事务（注：嵌套在分支事务中的分支事务对主事务透明 ）
     * 如果分支事务有执行顺序要求，indexInTrunk标识分支事务的执行顺序。
     * indexInTrunk设置要求：0 =< indexInTrunk =< 无穷大 ，
     * -1： 表示无顺序要求,值越小，表示越执行顺序越靠前。
     * 默认值: -1
     */
    private int indexInTrunk = -1;

    /**
     * 主事务状态
     */
    private BranchStatus status = BranchStatus.prepare;

    /**
     * 应用名
     *
     * @return
     */
    private String appName = null;


    public String getBxid() {
        return bxid;
    }

    public void setBxid(String bxid) {
        this.bxid = bxid;
    }

    public String getParentBxid() {
        return parentBxid;
    }

    public void setParentBxid(String parentBxid) {
        if (null != parentBxid)
            this.parentBxid = parentBxid;
    }

    public int getIndexInBranch() {
        return indexInBranch;
    }

    public void setIndexInBranch(int indexInBranch) {
        this.indexInBranch = indexInBranch;
    }

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    public int getIndexInTrunk() {
        return indexInTrunk;
    }

    public void setIndexInTrunk(int indexInTrunk) {
        this.indexInTrunk = indexInTrunk;
    }

    public BranchStatus getStatus() {
        return status;
    }

    public void setStatus(BranchStatus status) {
        this.status = status;
    }

    public void setStatus(String status) {
        this.status = BranchStatus.valueOf(status);
    }

    public String getAppGroup() {
        return appName;
    }

    public void setAppGroup(String appGroup) {
        this.appName = appGroup;
    }


}
