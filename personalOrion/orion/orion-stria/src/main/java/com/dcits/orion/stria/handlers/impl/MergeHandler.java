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
package com.dcits.orion.stria.handlers.impl;

import com.dcits.orion.stria.core.Runner;
import com.dcits.orion.stria.handlers.IHandler;
import com.dcits.orion.stria.model.*;
import com.dcits.galaxy.base.GalaxyConstants;
import com.dcits.galaxy.base.data.BeanResult;
import com.dcits.galaxy.base.data.ILocalHead;
import com.dcits.galaxy.base.data.Results;
import com.dcits.galaxy.base.exception.BusinessException;
import com.dcits.galaxy.base.exception.WithoutAuthorizationException;
import com.dcits.galaxy.base.exception.WithoutConfirmationException;
import com.dcits.galaxy.base.util.ExceptionUtils;
import com.dcits.galaxy.base.util.ResultsUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 异步Future合并分支操作的处理器
 *
 * @author Tim
 * @version V1.0
 * @description
 * @update 2014年12月17日 上午10:18:16
 */

public class MergeHandler implements IHandler {

    private static final Logger log = LoggerFactory
            .getLogger(MergeHandler.class);

    private JoinModel model;

    private List<List<Object>> result = new ArrayList<List<Object>>();

    private List<List<Object>> authResult = new ArrayList<List<Object>>();

    private List<List<Object>> confirmResult = new ArrayList<List<Object>>();

    public MergeHandler(JoinModel joinModel) {
        this.model = joinModel;
    }

    /**
     * 对join节点的所有输入变迁进行递归，查找join至fork节点的所有中间task元素
     *
     * @param node
     * @param buffer
     */
    private void findForkTaskNames(NodeModel node, StringBuilder buffer) {
        if (node instanceof ForkModel)
            return;
        List<TransitionModel> inputs = node.getInputs();
        for (TransitionModel tm : inputs) {
            if (tm.getSource() instanceof RunModel) {
                buffer.append(tm.getSource().getName()).append(",");
                continue;
            }
            findForkTaskNames(tm.getSource(), buffer);
        }
    }

    /**
     * 对join节点的所有输入变迁进行递归，查找join至fork节点的所有中间task元素
     */
    private String[] findActiveNodes() {
        StringBuilder buffer = new StringBuilder(20);
        findForkTaskNames(model, buffer);
        String[] taskNames = buffer.toString().split(",");
        return taskNames;
    }

    @Override
    public void handle(Runner runner) {
        FlowModel model = runner.getFlowModel();
        String[] activeNodes = findActiveNodes();
        boolean isServiceMerged = false;
        if (model.containsNodeNames(AbstractServiceModel.class,
                activeNodes)) {
            if (runner.getParallelResult().size() == activeNodes.length) {
                if (log.isDebugEnabled())
                    log.debug("节点结果合并");
                mergeFutureResult(runner);
                if (GalaxyConstants.STATUS_SUCCESS.equals(runner.getOut()
                        .getRetStatus())) {
                    isServiceMerged = true;
                }
            }
        }
        runner.setMerged(isServiceMerged);
    }

    private void mergeFutureResult(Runner runner) {
        long start = System.currentTimeMillis();
        try {
            List<List<Object>> results = runner.getParallelResult();
            for (List<Object> parallelResults : results) {
                Object model = parallelResults.get(1);
                // 远程服务或者检查服务
                if (CheckServiceModel.class.isInstance(model)
                        || AtomServiceModel.class.isInstance(model)) {
                    // 检查节点结果
                    result.add(parallelResults);
                } else if (AuthServiceModel.class.isInstance(model)) {
                    // 授权节点结果
                    authResult.add(parallelResults);
                } else if (ConfirmServiceModel.class.isInstance(model)) {
                    // 确认节点结果
                    confirmResult.add(parallelResults);
                }
            }

            if (result.size() > 0 || authResult.size() > 0 || confirmResult.size() > 0) {
                if (log.isDebugEnabled())
                    log.debug("[检查][授权][确认]服务节点结果合并");
                mergeResult(runner);
            }

            // 清空future
            runner.clearParallelResult();
        } finally {
            long end = System.currentTimeMillis();
            if (log.isDebugEnabled())
                log.debug("结果合并执行时间：" + (end - start));
        }
    }

    @SuppressWarnings("unchecked")
    private void mergeResult(Runner runner) {
        // 是否存在授权信息
        boolean authErr = false;
        // 是否存在确认信息
        boolean confirmErr = false;
        ILocalHead localHead = runner.getLocalHead();
        Results authMsg = new Results();
        BeanResult br = new BeanResult();
        BeanResult out = null;
        // 转换前段上送的授权信息
        ResultsUtils.convertRetToResults(localHead, authMsg);
        if (!authMsg.isEmpty()) {
            authErr = true;
        }

        //TODO A类检查服务执行结果
        // A类检查服务NIO执行结果
        if (result.size() > 0) {
            for (List<Object> parallelResult : result) {
                Object rs = parallelResult.get(0);
                try {
                    if (rs instanceof Throwable) {
                        throw (Throwable) rs;
                    } else if (rs instanceof Future) {
                        out = (BeanResult) ((Future) rs).get();
                    } else if (rs instanceof BeanResult) {
                        out = (BeanResult) rs;
                    }
                    // 服务结果不允许为null
                    if (null == out || null == out.getRs()) {
                        throw new BusinessException(
                                GalaxyConstants.CODE_FAILED,
                                "Method return BeanResult and BeanResult.getRs must not be null!");
                    }

                    if (!GalaxyConstants.STATUS_SUCCESS.equals(out.getRetStatus())) {
                        // Result的结果是C设置confirmErr为true,是O设置authErr为true,是B设置confirmErr和authErr为true
                        if (out.getRetStatus().equals(GalaxyConstants.STATUS_CONF)) {
                            ResultsUtils.mergeResults(authMsg, out.getRs());
                            confirmErr = true;
                        } else if (out.getRetStatus().equals(GalaxyConstants.STATUS_AUTH)) {
                            ResultsUtils.mergeResults(authMsg, out.getRs());
                            authErr = true;
                        } else if (out.getRetStatus().equals(GalaxyConstants.STATUS_AUTH_CONF)) {
                            ResultsUtils.mergeResults(authMsg, out.getRs());
                            authErr = true;
                            confirmErr = true;
                            // 其他错误
                        } else {
                            br.mergeResult(out);
                        }
                    }
                } catch (Throwable e) {
                    /*if (log.isErrorEnabled()) {
                        log.error(ExceptionUtils.getStackTrace(e));
                    }*/
                    if (e instanceof InvocationTargetException) {
                        e = e.getCause();
                    }
                    if (e instanceof UndeclaredThrowableException) {
                        e = e.getCause();
                    }
                    if (e instanceof InvocationTargetException) {
                        e = e.getCause();
                    }
                    if (e instanceof ExecutionException) {
                        e = e.getCause();
                    }

                    if (e instanceof BusinessException) {
                        BusinessException bs = (BusinessException) e;
                        if (bs instanceof WithoutConfirmationException) {
                            // 合并授权信息
                            ResultsUtils.mergeResults(authMsg, bs.getRets());
                            confirmErr = true;
                            // 未知异常，终止交易
                        } else if (bs instanceof WithoutAuthorizationException) {
                            // 合并授权信息
                            ResultsUtils.mergeResults(authMsg, bs.getRets());
                            authErr = true;
                            // 未知异常，终止交易
                        } else
                            br.mergeResult(new BeanResult(bs.getRets()));
                    } else if (e instanceof RuntimeException) {
                        BusinessException bs = ExceptionUtils.parseException(e);
                        if (bs instanceof WithoutAuthorizationException) {
                            // 合并授权信息
                            ResultsUtils.mergeResults(authMsg, bs.getRets());
                            confirmErr = true;
                            // 未知异常，终止交易
                        } else if (bs instanceof WithoutAuthorizationException) {
                            // 合并授权信息
                            ResultsUtils.mergeResults(authMsg, bs.getRets());
                            authErr = true;
                            // 未知异常，终止交易
                        } else
                            br.mergeResult(new BeanResult(bs.getRets()));
                    } else {
                        br.mergeResult(new BeanResult(new Results(GalaxyConstants.CODE_FAILED, e.getMessage())));
                    }
                }
            }
        }

        // 如果br错误返回
        if (!GalaxyConstants.STATUS_SUCCESS.equals(br.getRetStatus())) {
            runner.setOut(br);
            return;
        }

        //TODO O类检查服务执行结果
        // O类检查服务NIO执行结果
        if (authResult.size() > 0) {
            for (List<Object> parallelResult : authResult) {
                Object rs = parallelResult.get(0);
                try {
                    if (rs instanceof Throwable) {
                        throw (Throwable) rs;
                    } else if (rs instanceof Future) {
                        out = (BeanResult) ((Future) rs).get();
                    } else if (rs instanceof BeanResult) {
                        out = (BeanResult) rs;
                    }
                    // 服务结果不允许为null
                    if (null == out || null == out.getRs()) {
                        throw new BusinessException(
                                GalaxyConstants.CODE_FAILED,
                                "Method return BeanResult and BeanResult.getRs must not be null!");
                    }

                    if (!GalaxyConstants.STATUS_SUCCESS.equals(out.getRetStatus())) {
                        // Result的结果是O设置authErr为true,如果为B设置confirmErr和authErr为true
                        if (out.getRetStatus().equals(GalaxyConstants.STATUS_AUTH)) {
                            ResultsUtils.mergeResults(authMsg, out.getRs());
                            authErr = true;
                        } else if (out.getRetStatus().equals(GalaxyConstants.STATUS_AUTH_CONF)) {
                            ResultsUtils.mergeResults(authMsg, out.getRs());
                            authErr = true;
                            confirmErr = true;
                            // 其他错误
                        } else {
                            br.mergeResult(out);
                        }
                    }
                } catch (Throwable e) {
                    /*if (log.isErrorEnabled()) {
                        log.error(ExceptionUtils.getStackTrace(e));
                    }*/
                    if (e instanceof InvocationTargetException) {
                        e = e.getCause();
                    }
                    if (e instanceof UndeclaredThrowableException) {
                        e = e.getCause();
                    }
                    if (e instanceof InvocationTargetException) {
                        e = e.getCause();
                    }
                    if (e instanceof ExecutionException) {
                        e = e.getCause();
                    }

                    if (e instanceof BusinessException) {
                        BusinessException bs = (BusinessException) e;
                        if (bs instanceof WithoutAuthorizationException) {
                            // 合并授权信息
                            ResultsUtils.mergeResults(authMsg, bs.getRets());
                            authErr = true;
                            // 未知异常，终止交易
                        } else
                            br.mergeResult(new BeanResult(bs.getRets()));
                    } else if (e instanceof RuntimeException) {
                        BusinessException bs = ExceptionUtils.parseException(e);
                        if (bs instanceof WithoutAuthorizationException) {
                            // 合并授权信息
                            ResultsUtils.mergeResults(authMsg, bs.getRets());
                            authErr = true;
                            // 未知异常，终止交易
                        } else
                            br.mergeResult(new BeanResult(bs.getRets()));
                    } else {
                        br.mergeResult(new BeanResult(new Results(GalaxyConstants.CODE_FAILED, e.getMessage())));
                    }
                }
            }
        }
        //TODO D类检查服务执行结果
        // D类检查服务NIO执行结果
        if (confirmResult.size() > 0) {
            for (List<Object> parallelResult : confirmResult) {
                Object rs = parallelResult.get(0);
                try {
                    if (rs instanceof Throwable) {
                        throw (Throwable) rs;
                    } else if (rs instanceof Future) {
                        out = (BeanResult) ((Future) rs).get();
                    } else if (rs instanceof BeanResult) {
                        out = (BeanResult) rs;
                    }

                    // 服务结果不允许为null
                    if (null == out || null == out.getRs()) {
                        throw new BusinessException(
                                GalaxyConstants.CODE_FAILED,
                                "Method return BeanResult and BeanResult.getRs must not be null!");
                    }

                    if (!GalaxyConstants.STATUS_SUCCESS.equals(out
                            .getRetStatus())) {
                        // Result的结果是C设置confirmErr为true,如果为B设置confirmErr和authErr为true
                        if (out.getRetStatus().equals(GalaxyConstants.STATUS_CONF)) {
                            ResultsUtils.mergeResults(authMsg, out.getRs());
                            confirmErr = true;
                        } else if (out.getRetStatus().equals(GalaxyConstants.STATUS_AUTH_CONF)) {
                            ResultsUtils.mergeResults(authMsg, out.getRs());
                            authErr = true;
                            confirmErr = true;
                            // 其他错误
                        } else {
                            br.mergeResult(out);
                        }
                    }
                } catch (Throwable e) {
                    /*if (log.isErrorEnabled()) {
                        log.error(ExceptionUtils.getStackTrace(e));
                    }*/
                    if (e instanceof InvocationTargetException) {
                        e = e.getCause();
                    }
                    if (e instanceof UndeclaredThrowableException) {
                        e = e.getCause();
                    }
                    if (e instanceof InvocationTargetException) {
                        e = e.getCause();
                    }
                    if (e instanceof ExecutionException) {
                        e = e.getCause();
                    }

                    if (e instanceof BusinessException) {
                        BusinessException bs = (BusinessException) e;
                        if (bs instanceof WithoutConfirmationException) {
                            // 合并授权信息
                            ResultsUtils.mergeResults(authMsg, bs.getRets());
                            confirmErr = true;
                            // 未知异常，终止交易
                        } else
                            br.mergeResult(new BeanResult(bs.getRets()));
                    } else if (e instanceof RuntimeException) {
                        BusinessException bs = ExceptionUtils.parseException(e);
                        if (bs instanceof WithoutConfirmationException) {
                            // 合并授权信息
                            ResultsUtils.mergeResults(authMsg, bs.getRets());
                            confirmErr = true;
                            // 未知异常，终止交易
                        } else
                            br.mergeResult(new BeanResult(bs.getRets()));
                    } else {
                        br.mergeResult(new BeanResult(new Results(GalaxyConstants.CODE_FAILED, e.getMessage())));
                    }
                }
            }
        }

        // 如果br错误返回
        if (!GalaxyConstants.STATUS_SUCCESS.equals(br.getRetStatus())) {
            runner.setOut(br);
            return;
        }

        // 检查是否存在授权信息
        if (!authMsg.isEmpty()) {
            String status = GalaxyConstants.STATUS_FAILED;
            // 既有授权也有确认
            if (authErr && confirmErr)
                status = GalaxyConstants.STATUS_AUTH_CONF;
            // 只有确认
            if (!authErr && confirmErr)
                status = GalaxyConstants.STATUS_CONF;
            // 只有授权
            if (authErr && !confirmErr)
                status = GalaxyConstants.STATUS_AUTH;
            runner.setOut(new BeanResult(authMsg, status));
        }
    }

}
