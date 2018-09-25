package com.dcits.orion.stria.utils;

import com.dcits.orion.api.EventWrapper;
import com.dcits.orion.api.model.EventRequest;
import com.dcits.orion.stria.config.StriaConstants;
import com.dcits.orion.stria.core.Runner;
import com.dcits.orion.stria.engine.StriaEventEngine;
import com.dcits.orion.stria.exception.StriaException;
import com.dcits.orion.stria.rpc.impl.FlowService;
import com.dcits.galaxy.base.GalaxyConstants;
import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.base.bean.ArgumentBean;
import com.dcits.galaxy.base.data.BeanResult;
import com.dcits.galaxy.base.data.BaseRequest;
import com.dcits.galaxy.base.exception.BusinessException;
import com.dcits.galaxy.base.tuple.ThreeTuple;
import com.dcits.galaxy.base.tuple.TwoTuple;
import com.dcits.galaxy.base.util.*;
import com.dcits.galaxy.core.spring.SpringApplicationContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Tim on 2015/11/19.
 */
public class StriaUtil {

    private static final Logger log = LoggerFactory
            .getLogger(StriaUtil.class);

    public static FlowService f;

    /**
     * 获取事件流程ID
     *
     * @param request
     * @return
     */
    public static String getEventFlowId(EventRequest request) {
        request.setEventProdType(request.getProdType());
        String prodType = request.getProdType();
        // 获取请求中的产品类型和事件ID
        String name = new StringBuffer().append(prodType).append("-")
                .append(request.getEventType())
                .append(StriaConstants.FOLW_POSTFIX).toString();
        // 获取产品的基础产品类型，如果基础产品类型存在，使用基础产品类型获取流程。
        EventWrapper wrapper = getWrapper();
        String baseProdType = wrapper.getBaseProdType(prodType);
        // 存在base产品
        if (StringUtils.isNotEmpty(baseProdType)) {
            // 如果产品流程没有定义，取base产品流程定义
            if (null == f.getFlowById(name)) {
                request.setEventProdType(baseProdType);
                name = new StringBuffer().append(baseProdType).append("-")
                        .append(request.getEventType())
                        .append(StriaConstants.FOLW_POSTFIX).toString();
            }
        }
        return name;
    }

    /**
     * 获取事件组件获取器
     *
     * @return
     */
    public static EventWrapper getWrapper() {
        if (!SpringApplicationContext.getContext().containsBean("eventWrapper")) {
            throw new StriaException("事件组件获取器异常！请确认是否定义[eventWrapper] bean");
        }
        return (EventWrapper) SpringApplicationContext.getContext().getBean("eventWrapper");
    }

    /**
     * 获取事件流程处理引擎
     *
     * @return
     */
    public static StriaEventEngine getEventEngine() {
        // 事件流程
        return SpringApplicationContext.getContext().getBean(StriaEventEngine.class);
    }

    /**
     * 创建事件请求
     *
     * @param mbsdReq
     * @param prodType
     * @param eventType
     * @param eventModel
     * @return
     */
    public static EventRequest getEventRequest(BaseRequest mbsdReq, String prodType, String eventType, AbstractBean eventModel) {
        // 组织事件流程请求
        EventRequest eventRequest = new EventRequest();
        BeanUtils.copy(mbsdReq, eventRequest);
        eventRequest.setDoCheck(mbsdReq.isDoCheck());
        eventRequest.setDoSubmit(mbsdReq.isDoSubmit());
        eventRequest.setDoACCheck(mbsdReq.isDoACCheck());
        // 产品类型
        eventRequest.setProdType(prodType);
        // 事件ID
        eventRequest.setEventType(eventType);
        // 产品工厂模型
        eventRequest.setEventModel(eventModel);
        return eventRequest;
    }

    /**
     * 执行事件流程
     *
     * @param request    服务请求
     * @param index      事件处理索引
     * @param threeTuple 事件流程处理三元组
     * @return
     */
    public static Runner runEventFlow(BaseRequest request, int index, ThreeTuple<String, String, AbstractBean> threeTuple) {
        return runEventFlow(request, index, threeTuple, null);
    }

    /**
     * 执行事件流程
     *
     * @param request    服务请求
     * @param index      事件处理索引
     * @param threeTuple 事件流程处理三元组
     * @param prefix     服务ID前缀
     * @return
     */
    public static Runner runEventFlow(BaseRequest request, int index, ThreeTuple<String, String, AbstractBean> threeTuple, String prefix) {
        StriaEventEngine engine = StriaUtil.getEventEngine();
        EventRequest eventRequest = StriaUtil.getEventRequest(request, threeTuple.first, threeTuple.second, threeTuple.three);
        eventRequest.setEventServiceId(new StringBuffer().append(prefix).append("-")
                .append(request.getSysHead().getServiceCode()).append("-")
                .append(request.getSysHead().getMessageType()).append("-")
                .append(request.getSysHead().getMessageCode()).toString());
        eventRequest.setEventIndex(index);
        // 初始化事件流程引擎
        engine.init(eventRequest);
        return engine.execute(eventRequest);
    }

    /**
     * 将多个事件流程节点的别名合并在一起
     *
     * @param mainArgs
     * @param eventArgs
     */
    public static void mergeEventArgs(Map<String, Object> mainArgs, Map<String, Object> eventArgs) {
        mergeEventArgs(mainArgs, eventArgs, null);
    }

    /**
     * 将多个事件流程节点的别名合并在一起
     *
     * @param mainArgs
     * @param eventArgs
     * @param prefix
     */
    public static void mergeEventArgs(Map<String, Object> mainArgs, Map<String, Object> eventArgs, String prefix) {
        //合并事件流程中的别名参数与主服务
        if (null == mainArgs) {
            mainArgs = new HashMap<>();
        }
        Map<String, Object> temp = new HashMap<>();
        temp.putAll(eventArgs);
        temp.remove(StriaConstants.MSG_KEY);
        if (StringUtils.isNotEmpty(prefix)) {
            Map<String, Object> temp1 = new HashMap<>();
            for (String key : temp.keySet()) {
                // 拼上后缀
                temp1.put(new StringBuffer().append(prefix).append("_").append(key).toString(),
                        temp.get(key));
            }
            temp.clear();
            temp.putAll(temp1);
            temp1.clear();
        }
        for (String k : temp.keySet()) {
            checkContainArgsKey(mainArgs, k);
        }
        mainArgs.putAll(temp);
    }

    /**
     * 将多个相同事件流程节点的别名合并在一起
     *
     * @param mainArgs
     * @param eventArgs
     */
    public static void mergeEventArgsToList(Map<String, Object> mainArgs, Map<String, Object> eventArgs) {
        //合并事件流程中的别名参数与主服务
        if (null == mainArgs) {
            mainArgs = new HashMap<>();
        }
        Map<String, Object> temp = new HashMap<>();
        temp.putAll(eventArgs);
        temp.remove(StriaConstants.MSG_KEY);
        for (String k : temp.keySet()) {
            if (mainArgs.containsKey(k)) {
                Object o = mainArgs.get(k);
                if (o instanceof List) {
                    List<Object> argL = (List<Object>) mainArgs.get(k);
                    argL.add(temp.get(k));
                } else {
                    List<Object> argL = new ArrayList<>();
                    argL.add(o);
                    argL.add(temp.get(k));
                    mainArgs.put(k, argL);
                }
            } else {
                List<Object> argL = new ArrayList<>();
                argL.add(temp.get(k));
                mainArgs.put(k, argL);
            }
        }
    }

    /**
     * 检查别名参数是否已经被定义
     *
     * @param args
     * @param key
     */
    public static void checkContainArgsKey(Map<String, Object> args, String key) {
        if (args.containsKey(key)) {
            throw new BusinessException("100300", ErrorUtils.getParseErrorDesc("100300", new String[]{key}));
        }
    }

    /**
     * 将子服务的错误信息合并到主的结果集
     * 1、F级别的错误，大于O\C\B错误
     * 2、O\C\B级别的错误，大于S错误
     *
     * @param mainResult
     * @param subResult
     */
    public static void mergeSubResult(BeanResult mainResult, BeanResult subResult) {
        String mergeStatus = mainResult.getRetStatus() + subResult.getRetStatus();
        //状态 S F O C B
        switch (mergeStatus) {
            case "SS":
            case "FS":
            case "OS":
            case "CS":
            case "BS":
                break;
            case "FF":
            case "SF":
                mainResult.mergeResult(subResult);
                break;
            case "FO":
            case "FC":
            case "FB":
                break;
            case "SO":
            case "SC":
            case "SB":
            case "OF":
            case "CF":
            case "BF":
                mainResult.setRs(subResult.getRs());
                mainResult.setRetStatus(subResult.getRetStatus());
                break;
            case "OO":
                mainResult.mergeResult(subResult);
                mainResult.setRetStatus(GalaxyConstants.STATUS_AUTH);
                break;
            case "OC":
            case "OB":
            case "CO":
            case "BO":
            case "BC":
            case "CB":
                mainResult.mergeResult(subResult);
                mainResult.setRetStatus(GalaxyConstants.STATUS_AUTH_CONF);
                break;
            case "CC":
            case "BB":
                mainResult.mergeResult(subResult);
                mainResult.setRetStatus(subResult.getRetStatus());
                break;
            default:
                mainResult.mergeResult(subResult);
        }
    }


    /**
     * 获取参数配置
     *
     * @return
     * @description
     * @version 1.0
     * @author Tim
     * @update 2014年12月19日 上午9:29:57
     */
    public static List<ArgumentBean> getArgumentBean(String args, String argsClazz) {
        List<ArgumentBean> argsl = new ArrayList<ArgumentBean>();
        // 空参数
        if (StringUtils.isEmpty(args) && StringUtils.isEmpty(argsClazz))
            return argsl;
        String[] arg = args.split("\\|");
        String[] argClazz = argsClazz.split("\\|");
        for (int i = 0; i < arg.length; i++) {
            ArgumentBean argBean = new ArgumentBean();
            argBean.setArgType(argClazz[i]);
            argBean.setArgValue(arg[i]);
            argsl.add(argBean);
        }
        return argsl;
    }

    /**
     * 获取参数类型和对象
     *
     * @param runner
     * @param argsl
     * @return
     * @throws ClassNotFoundException
     */
    public static TwoTuple<Class<?>[], Object[]> getArguments(Runner runner,
                                                              List<ArgumentBean> argsl) {
        if (null == argsl) return null;
        Object[] args = null;
        Class<?>[] clazzs = null;
        StringBuffer sb = null;
        for (int i = 0; i < argsl.size(); i++) {
            if (null == args) {
                args = new Object[argsl.size()];
            }
            if (null == clazzs) {
                clazzs = new Class<?>[argsl.size()];
            }
            Class<?> c = null;
            try {
                // 获取约定类型配置
                c = ObjectUtils.getBaseDataType(argsl.get(i).getArgType());
                // 通过Class.forName获取
                if (null == c)
                    c = ClassLoaderUtils.loadClass(argsl.get(i).getArgType());

                clazzs[i] = c;
                args[i] = getObjectForSPEL(runner, c, argsl.get(i).getArgValue());
                if (log.isDebugEnabled()) {
                    if (null == sb)
                        sb = new StringBuffer();
                    sb.append("\n入参类型 [" + c.getName() + "] 入参值 [" + args[i] + "] 表达式 [" + argsl.get(i).getArgValue() + "]");
                }
            } catch (ClassNotFoundException e) {
                throw new BusinessException(GalaxyConstants.CODE_FAILED, e
                        .getClass().getSimpleName() + ":" + e.getMessage());
            }
        }

        if (null != sb && log.isDebugEnabled())
            log.debug("执行方法入参类型和值" + sb.toString());
        return new TwoTuple<>(clazzs, args);
    }

    /**
     * 获取参数对象
     *
     * @param runner
     * @param arguamentsSPEL
     * @return
     * @throws ClassNotFoundException
     */
    public static Object[] getArguments(Runner runner, Class<?>[] argumentsClazz, String[] arguamentsSPEL) {
        Object[] args = null;
        StringBuffer sb = null;
        if (argumentsClazz == null || arguamentsSPEL == null || (argumentsClazz.length != arguamentsSPEL.length)) {
            return args;
        }
        args = new Object[argumentsClazz.length];
        for (int i = 0; i < argumentsClazz.length; i++) {
            args[i] = getObjectForSPEL(runner, argumentsClazz[i], arguamentsSPEL[i]);
            if (log.isDebugEnabled()) {
                if (null == sb)
                    sb = new StringBuffer();
                sb.append("\n入参类型 [" + argumentsClazz[i].getName() + "] 入参值 [" + args[i] + "] 表达式 [" + arguamentsSPEL[i] + "]");
            }
        }
        if (null != sb && log.isDebugEnabled())
            log.debug("执行方法入参类型和值" + sb.toString());
        return args;
    }

    /**
     * 获取运行Runner表达式的对象
     *
     * @param runner
     * @param c
     * @param spel
     * @return
     */
    public static Object getObjectForSPEL(Runner runner, Class<?> c, String spel) {
        BaseRequest in = (BaseRequest) runner.getArgs().get(StriaConstants.MSG_KEY);
        Object o = null;
        if ("null".equals(spel)) {// 传入参数获为null对象。
            return o;
        } else if ("*".equals(spel)) {// 传入参数获取原始请求报文。
            // 原始请求保文装载到in的JsonObeject对象中。
            if (c.isInstance(in)) {
                o = in;
            } else {
                // throw 转换参数异常
                throw new BusinessException(
                        GalaxyConstants.CODE_FAILED, "ArgValue ["
                        + spel + "] ArgType ["
                        + c.getName()
                        + "] 转换参数异常, 期望类型 [" + in.getClass().getName() + "]");
            }
        } else {
            try {
                // 使用Spring spel表达式获取入参值
                o = runner.eval(c, spel);
            } catch (Throwable t) {
                if (log.isWarnEnabled())
                    log.warn(t.getMessage());
                o = null;
            }
        }
        return o;
    }

    /**
     * 获取服务方法实例
     *
     * @return
     * @description
     * @version 1.0
     * @author Tim
     * @update 2014年12月19日 上午10:00:00
     */
    public static Method getMethod(Object o, String methodName, Class<?>[] argumentsType) {
        Method m = null;
        try {
            m = o.getClass().getMethod(methodName, argumentsType);
        } catch (NoSuchMethodException e) {
            throw new BusinessException(GalaxyConstants.CODE_FAILED, e
                    .getClass().getSimpleName() + ":" + e.getMessage());
        }
        return m;
    }
}
