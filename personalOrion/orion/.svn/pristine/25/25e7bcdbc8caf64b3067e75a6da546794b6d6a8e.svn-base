package com.dcits.orion.stria.model;

import com.dcits.orion.stria.core.Runner;
import com.dcits.orion.stria.exception.StriaException;
import com.dcits.galaxy.base.util.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Tim on 2015/5/19.
 */
public class DecisionModel extends NodeModel {
    private static final Logger log = LoggerFactory.getLogger(DecisionModel.class);
    /**
     *
     */
    private static final long serialVersionUID = -806621814645169999L;

    public void exec(Runner runner) {
        boolean isFound = false;
        for (TransitionModel tm : getOutputs()) {
            String expr = tm.getExpr();
            if (StringUtils.isNotEmpty(expr) && runner.eval(Boolean.class, expr)) {
                tm.setEnabled(true);
                tm.execute(runner);
                isFound = true;
                break; //增加执行决策后，就不在执行后续操作。抉择只有一条路可以选择
            }
        }
        if (!isFound) throw new StriaException(runner.getFlow().getFlowid() + "->decision节点无法确定下一步执行路线");
    }
}
