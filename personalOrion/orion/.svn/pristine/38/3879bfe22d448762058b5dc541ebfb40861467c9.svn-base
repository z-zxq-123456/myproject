package com.dcits.orion.stria.model;


import com.dcits.orion.stria.core.Runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

/**
 * Created by Tim on 2015/5/19.
 */
public class StartModel extends NodeModel {

    private static final Logger log = LoggerFactory
            .getLogger(StartModel.class);

    private static final long serialVersionUID = -880958437568488644L;

    /**
     * 开始节点无输入变迁
     */
    public List<TransitionModel> getInputs() {
        return Collections.emptyList();
    }

    protected void exec(Runner runner) {
        if (log.isInfoEnabled())
            log.info("主服务 [" + !runner.isSubService() + "] 子服务 [" + runner.isSubService() + "] 检查执行 [" + runner.isDoCheck() + "] 授权、确认检查执行 [" + runner.isDoACCheck() + "] 提交执行 [" + runner.isDoSubmit() + "]");
        runOutTransition(runner);
    }
}
