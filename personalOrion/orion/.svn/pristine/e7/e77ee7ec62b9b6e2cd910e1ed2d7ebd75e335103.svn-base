/* Copyright 2013-2015 www.snakerflow.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dcits.orion.stria.model;

import com.dcits.orion.stria.core.Runner;
import com.dcits.orion.stria.exception.StriaException;
import com.dcits.orion.stria.handlers.impl.MergeHandler;

/**
 * 合并定义join元素
 *
 * @author Tim
 * @version V1.0
 * @description
 * @update 2014年12月17日 上午10:52:26
 */

public class JoinModel extends NodeModel {
    private static final long serialVersionUID = -5794228452349453911L;

    public void exec(Runner runner) {
        // 不做检查跳过合并节点
        if (skipNode(runner)) {
            runOutTransition(runner);
            return;
        }
        fire(new MergeHandler(this), runner);
        if (runner.isMerged()) runOutTransition(runner);
    }

    protected boolean skipNode(Runner runner) {
        boolean skip = !(runner.isDoCheck() || runner.isDoACCheck());
        if (skip) return skip;
        boolean isCheck = false; // 是否检查
        boolean isACCheck = false; // 是否授权、确认检查
        for (TransitionModel transitionModel : this.getInputs()) {
            if (transitionModel.getSource() instanceof CheckServiceModel) {
                isCheck = true;
            } else if (transitionModel.getSource() instanceof AuthServiceModel
                    || transitionModel.getSource() instanceof ConfirmServiceModel) {
                isACCheck = true;
            }
        }
        if (isCheck && isACCheck) {
            throw new StriaException("检查节点不能与授权、确认检查节点混合使用！");
        }
        if (isCheck && runner.isDoACCheck()) skip = true;
        else if (isACCheck && runner.isDoCheck()) skip = true;
        else skip = false;
        return skip;
    }
}
