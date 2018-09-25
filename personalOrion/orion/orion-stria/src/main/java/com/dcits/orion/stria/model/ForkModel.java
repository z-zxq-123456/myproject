package com.dcits.orion.stria.model;

import com.dcits.orion.stria.core.Runner;
import com.dcits.orion.stria.exception.StriaException;
import com.dcits.galaxy.base.GalaxyConstants;

/**
 * 分支定义fork元素
 * <p/>
 * Created by Tim on 2015/5/19.
 */
public class ForkModel extends NodeModel {
    private static final long serialVersionUID = -6444257175904821822L;

    protected void exec(Runner runner) {
        runOutTransition(runner);
    }

    /**
     * 运行变迁继续执行
     *
     * @param runner
     */
    @Override
    protected void runOutTransition(Runner runner) {
        if (null != runner.getOut()
                && !GalaxyConstants.STATUS_SUCCESS.equals(runner.getOut()
                .getRetStatus())) {
            return;
        }

        for (TransitionModel tm : getOutputs()) {
            tm.setEnabled(true);
            tm.execute(runner);
            // 跳过检查，就走一条线。执行完以后。退出
            if (skipNode(runner)) {
                break;
            }
        }
    }

    protected boolean skipNode(Runner runner) {
        JoinModel joinModel = searchJoinModel(this);
        if (null == joinModel) {
            throw new StriaException("分派节点后面需要定义合并节点！");
        }
        return joinModel.skipNode(runner);
    }

    private JoinModel searchJoinModel(NodeModel node) {
        JoinModel nodeModel = null;
        JoinModel temp = null;
        if (node instanceof ForkModel && this != node) {
            throw new StriaException("分派节点里面不能嵌套使用分派节点！");
        }

        if (node instanceof JoinModel) {
            return (JoinModel) node;
        }

        for (TransitionModel tm : node.getOutputs()) {
            if (tm.getTarget() instanceof JoinModel) {
                return (JoinModel) tm.getTarget();
            }
            temp = searchJoinModel(tm.getTarget());
            if (null == nodeModel){
                nodeModel = temp;
            } else if (temp != nodeModel){
                throw new StriaException("分派后面不能存在多个合并节点！");
            }
        }
        return nodeModel;
    }
}
