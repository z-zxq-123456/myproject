package com.dcits.orion.stria.model;

import com.dcits.galaxy.base.data.BeanResult;
import com.dcits.galaxy.core.access.ThreadLocalManager;
import com.dcits.galaxy.core.spring.SpringApplicationContext;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

/**
 * Created by Tim on 2016/3/11.
 */
public class ParallelMethodCallable implements Callable<BeanResult> {
    private AbstractServiceModel model;
    private String uuid;
    private Object invokeObject;
    private Method m;
    private Object[] o;

    public ParallelMethodCallable(AbstractServiceModel model, String uuid, Object invokeObject, Method m, Object[] o) {
        this.model = model;
        this.uuid = uuid;
        this.invokeObject = invokeObject;
        this.m = m;
        this.o = o;
    }

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public BeanResult call() throws Exception {
        try {
            ThreadLocalManager.setUID(uuid);
            ParallelMethod parallelMethod = SpringApplicationContext.getContext().getBean(ParallelMethod.class);
            Object out = parallelMethod.methodInvoke(model, invokeObject, m, o);
            if (out instanceof BeanResult) {
                return (BeanResult) out;
            }
            return new BeanResult();
        } finally {
            ThreadLocalManager.remove();
        }
    }
}
