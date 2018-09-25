package com.dcits.orion.stria;

import com.dcits.orion.stria.core.Runner;

/**
 * 所有的模型对象需要实现的接口，需要实现execute方法，每个节点的执行方式不一样
 *
 * Created by Tim on 2015/5/19.
 */
public interface Action {

    /**
     * 根据当前的执行对象所维持的process、order、model、args对所属流程实例进行执行
     * @param runner 执行对象
     */
    void execute(Runner runner);
}
