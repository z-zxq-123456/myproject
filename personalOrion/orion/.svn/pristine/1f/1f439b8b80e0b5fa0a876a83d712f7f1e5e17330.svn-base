package com.dcits.orion.core.annotation;

import com.dcits.orion.core.dao.BaseDao;
import com.dcits.galaxy.base.util.BeanUtils;
import com.dcits.galaxy.core.access.ThreadLocalManager;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by lixiaobin on 2016/11/16.
 */
@Aspect //注解定义切面
@Component
public class CacheAspect {

    private static final Logger logger = LoggerFactory
            .getLogger(CacheAspect.class);

    @Around("execution(@com.dcits.orion.core.annotation.BatchTransactional public * *(..))")
    public Object around(ProceedingJoinPoint jp) throws Throwable {
        if (logger.isInfoEnabled())
            logger.info("The method is :<"+jp.getSignature().getName()+"> begin!");

        Object o;
        Stack<Map> stack = (Stack<Map>) ThreadLocalManager.get("cacheStack");
        if (stack == null) {
            stack = new Stack<>();
            ThreadLocalManager.put("cacheStack", stack);
        }
        Map cacheMap = new HashMap();
        stack.push(cacheMap);
        boolean isException = true;
        try {
            o = jp.proceed();
            isException = false;
        }finally {
            if (logger.isInfoEnabled())
                logger.info("The method is :<"+jp.getSignature().getName()+"> end!");
            cacheMap = stack.pop();
            if (stack.empty()) {
                if (!isException) {
                    try
                    {
                        stack.push(cacheMap);
                        BaseDao.batchDbSubmit(cacheMap);
                    }
                    finally {
                        stack.pop();
                        BaseDao.clearQueryData();
                    }
                }
            } else {
                if (!isException) {
                    Map upper = stack.peek();
                    BeanUtils.mapMerge(cacheMap, upper);
                }
            }
        }
        return o;
    }


}