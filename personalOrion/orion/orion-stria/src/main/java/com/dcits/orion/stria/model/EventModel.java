package com.dcits.orion.stria.model;

import com.dcits.orion.api.EventWrapper;
import com.dcits.orion.api.model.EventNode;
import com.dcits.orion.api.model.EventRequest;
import com.dcits.orion.stria.core.Runner;
import com.dcits.orion.stria.core.StriaThreadLocal;
import com.dcits.orion.stria.exception.StriaException;
import com.dcits.orion.stria.utils.StriaUtil;
import com.dcits.galaxy.base.GalaxyConstants;
import com.dcits.galaxy.base.data.BeanResult;
import com.dcits.galaxy.base.exception.BusinessException;
import com.dcits.galaxy.base.util.StringUtils;
import com.dcits.galaxy.core.spring.SpringApplicationContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Tim on 2015/11/17.
 */
public class EventModel extends NodeModel {

    private static final Logger log = LoggerFactory
            .getLogger(EventModel.class);


    private String eventId;

    /**
     * 事件组件获取器
     */
    private EventWrapper wrapper;

    /**
     * 获取事件组件获取器
     *
     * @return
     */
    public EventWrapper getWrapper() {
        if (null == this.wrapper) {
            if (!SpringApplicationContext.getContext().containsBean("eventWrapper")) {
                throw new StriaException("事件组件获取器异常！请确认是否定义[eventWrapper] bean");
            }
            this.wrapper = (EventWrapper) SpringApplicationContext.getContext().getBean("eventWrapper");
        }
        return this.wrapper;
    }

    /**
     * 具体节点模型需要完成的执行逻辑
     *
     * @param runner
     */
    @Override
    protected void exec(Runner runner) {
        if (log.isDebugEnabled())
            log.debug("事件节点 [" + this.eventId + "] , 检查 [" + runner.isDoCheck() + "] , 提交 [" + runner.isDoSubmit() + "]");
        EventRequest req = (EventRequest) runner.getIn();
        // 计算和提交都需要执行事件检查
        if (runner.isDoCheck() || runner.isDoSubmit()) {
            //节点名称，使用节点名称将计算节点的BeanResult，存入StriaLocal中。事件服务ID + “-” + 事件流程ID + “-” + 事件节点ID + “-” + 事件索引
            String key = new StringBuffer().append(req.getEventServiceId()).append("-")
                    .append(runner.getFlow().getFlowid()).append("-")
                    .append(this.getName()).append("-")
                    .append(req.getEventIndex()).toString();
            BeanResult out = null;
            EventNode eventNode = this.getWrapper().getEventCalc(req.getEventType(), req.getEventProdType(), getEventId());
            // 检查本地的ThreadLocal是否已经存在计算节点的BeanResult
            if (StriaThreadLocal.containsKey(key)) {
                // 通过ThreadLocal中获取三元组对象
                out = (BeanResult) StriaThreadLocal.get(key);
            } else {
                if (log.isDebugEnabled())
                    log.debug("计算事件\n" + eventNode);
                Object[] argOs = StriaUtil.getArguments(runner, eventNode.getArgumentsClazz(), eventNode.getArgumentsSPEL());
                Object event = SpringApplicationContext.getContext().getBean(eventNode.getEventClazz());
                // 获取执行方法
                Method m = StriaUtil.getMethod(event, eventNode.getMethod(), eventNode.getArgumentsClazz());
                out = this.methodInvoke(event, m, argOs);
                StriaThreadLocal.put(key, out);
            }

            runner.getOut().mergeResult(out);
            //设置参数别名
            if (out.getRetStatus().equals(GalaxyConstants.STATUS_SUCCESS)) {
                if (StringUtils.isNotEmpty(eventNode.getAlias()))
                    runner.getArgs().put(eventNode.getAlias(), out.getResponse());
            } else {
                return;
            }
        }

        // 处理提交操作
        if (runner.isDoSubmit()) {
            // DTP处理
            EventNode eventNode = this.getWrapper().getEventSubmit(req.getEventType(), req.getEventProdType(), getEventId());
            if (log.isDebugEnabled())
                log.debug("提交事件\n" + eventNode);
            Object[] argOs = StriaUtil.getArguments(runner, eventNode.getArgumentsClazz(), eventNode.getArgumentsSPEL());
            Object event = SpringApplicationContext.getContext().getBean(eventNode.getEventClazz());
            // 获取执行方法
            Method m = StriaUtil.getMethod(event, eventNode.getMethod(), eventNode.getArgumentsClazz());
            BeanResult out = this.methodInvoke(event, m, argOs);
            runner.getOut().mergeResult(out);
            //设置参数别名
            if (!out.getRetStatus().equals(GalaxyConstants.STATUS_SUCCESS)) {
                return;
            }
        }
        runOutTransition(runner);
    }

    /**
     * 事件方法执行
     *
     * @param invokeObject
     * @param m
     * @param o
     * @return
     */
    protected BeanResult methodInvoke(Object invokeObject, Method m, Object[] o) {
        BeanResult out = null;
        try {
            out = (BeanResult) m.invoke(invokeObject, o);
        } catch (IllegalAccessException | InvocationTargetException e) {
            if (e instanceof IllegalAccessException) {
                out = new BeanResult(e);
            } else if (e instanceof InvocationTargetException) {
                Throwable t = e.getCause();
                if (t instanceof BusinessException) {
                    out = new BeanResult(t);
                } else {
                    out = new BeanResult(t);
                }
            }
        }
        return out;
    }

    public String getEventId() {
        if (StringUtils.isEmpty(eventId)) {
            throw new StriaException("事件ID未定义，请检查事件流程事件节点[" + this.getDisplayName() + "]属性定义");
        }
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
}
