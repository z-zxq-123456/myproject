package com.dcits.orion.stria.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 并行处理的方法执行，增加局部事务
 * <p/>
 * Created by Tim on 2016/3/11.
 */
public class ParallelMethod {
    private static final Logger log = LoggerFactory
            .getLogger(ParallelMethod.class);

    /**
     * 执行服务
     *
     * @param nodeModel
     * @param invokeObject
     * @param m
     * @param o
     * @return
     * @description
     * @version 1.0
     * @author Tim
     * @update 2014年12月19日 上午10:44:19
     */
    @Transactional
    public Object methodInvoke(AbstractServiceModel nodeModel, Object invokeObject, Method m, Object[] o) {
        if (log.isDebugEnabled()) {
            log.debug("执行服务实例和方法\n服务类型 [" + nodeModel.getServiceType() + "]\n原子服务 [" + nodeModel.getDisplayName() + "]\n服务接口 [" + nodeModel.clazz + "]\n执行方法 [" + nodeModel.methodName + "]");
        }
        Object out = null;
        try {
            out = m.invoke(invokeObject, o);
        } catch (IllegalAccessException | InvocationTargetException e) {
            if (e instanceof IllegalAccessException) {
                throw new RuntimeException(e);
            } else if (e instanceof InvocationTargetException) {
                throw new RuntimeException(((InvocationTargetException) e).getCause());
            }
        }
        return out;
    }
}
