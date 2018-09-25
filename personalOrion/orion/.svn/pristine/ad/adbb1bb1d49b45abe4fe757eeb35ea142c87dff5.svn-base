package com.dcits.orion.stria.model;

import com.dcits.orion.core.Context;
import com.dcits.orion.stria.core.Runner;

/**
 * Created by Tim on 2015/5/21.
 */
public abstract class RunModel extends NodeModel {

    private static final long serialVersionUID = 7157696342157898439L;

    /**
     * 分支事务Id
     */
    protected String bxId;

    /**
     * 执行逻辑，增加跳过节点检查
     *
     * @param runner
     * @return
     */
    @Override
    public void execute(Runner runner) {
        try {
            Context.getInstance().setCurrentNodeType(this.getType());
            if (skipNode(runner)) {
                runOutTransition(runner);
                return;
            }
//            try {
//            	// 开启新的分支事务,从上下文中的BxId设置私有属性
//            	addBranchTransaction();
            	exec(runner);
//			} finally {
//				// 重置DtpContext上下文中的BxId
//	            reSetBxId();
//			}
        } finally {
            if (null != Context.getInstance().getCurrentNodeType())
                Context.getInstance().setCurrentNodeType(null);
        }
    }

    /**
     * 开启新的分支事务
     * 并获取Dtp上下文中的BxId属性，设置到私有属性中。
     * 新生成的BxId存入Dtp上下文
     *
     * @return
     */
    public abstract void addBranchTransaction();

    /**
     * 恢复私有属性到Dtp上下文
     */
    public abstract void reSetBxId();

    /**
     * 根据runner的docheck和dosubmit来确定是否跳过处理节点。
     *
     * @param runner
     * @return
     */
    protected abstract boolean skipNode(Runner runner);

}
