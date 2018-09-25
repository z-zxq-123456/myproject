package com.dcits.orion.stria.mapping;

import com.dcits.galaxy.base.BaseGenerator;
import com.dcits.galaxy.base.tuple.TwoTuple;
import com.dcits.galaxy.base.util.BeanUtils;
import com.dcits.galaxy.base.util.ClassLoaderUtils;
import com.dcits.galaxy.base.util.StringUtils;
import com.dcits.orion.stria.core.Runner;
import com.dcits.orion.stria.entity.Item;
import com.dcits.orion.stria.entity.ListMapper;
import com.dcits.orion.stria.entity.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.expression.spel.SpelEvaluationException;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Tim on 2015/5/6.
 */
public class RequestMapping implements InitializingBean {
    private static final Logger log = LoggerFactory
            .getLogger(RequestMapping.class);

    private Resource[] mappingLocations;

    private Map<String, List<Mapper>> mapping;

    private Map<String, HashMap<String, Class<?>>> objDataType = new ConcurrentHashMap<>();

    /**
     * 添加mapping映射缓存
     *
     * @param map
     */
    public synchronized void addMapping(Map<String, List<Mapper>> map) {
        if (null == mapping)
            mapping = new HashMap<>();
        for (Entry entry : map.entrySet()) {
            mapping.put((String) entry.getKey(), (List<Mapper>) entry.getValue());
            if (log.isDebugEnabled())
                log.debug("[" + (String) entry.getKey() + "] loading");
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (null != this.mappingLocations && this.mappingLocations.length > 0) {
            Resource[] mapping = this.mappingLocations;
            int count = mapping.length;
            if (log.isDebugEnabled()) {
                log.debug("Location mapping file count [" + count + "]");
            }
            for (int i = 0; i < count; i++) {
                Resource mapXml = mapping[i];
                if (mapXml != null) {
                    if (log.isDebugEnabled()) {
                        log.debug("Parsed mapping file [" + mapXml.getFilename() + "]");
                    }
                    MappingBuilder builder = new MappingBuilder(mapXml);
                    addMapping(builder.parser());
                }
            }
        } else if (log.isDebugEnabled()) {
            log.debug("Property \"mappingLocations\" was not specified or no matching resources found");
        }
    }

    /**
     * 数据映射
     *
     * @param serviceId
     * @param runner
     */
    public void mapper(String serviceId, Runner runner) {
        if (log.isDebugEnabled())
            log.debug("[" + serviceId + "] Request映射开始");
        if (null != mapping && mapping.containsKey(serviceId)) {
            List<Mapper> mappers = mapping.get(serviceId);
            Object o;
            for (Mapper map : mappers) {
                if (map instanceof ListMapper) {
                    // List mapping，增加一个过滤器，对于满足条件的进行mapping
                    List<Object> listO = new ArrayList<>();
                    int size = 0;

                    // 如果没有指定Source，表明新new一个数组
                    if (StringUtils.isEmpty(((ListMapper) map).getSource())) {
                        size = 1;
                        o = mapper(map, runner, -1, new TwoTuple<String, String>(null, null));
                        if (null != o) listO.add(o);
                    } else {
                        try {
                            size = runner.eval(int.class, ((ListMapper) map).getSource() + ".size()");
                        } catch (SpelEvaluationException e) {
                        }

                        for (int i = 0; i < size; i++) {
                            o = mapper(map, runner, i, new TwoTuple<String, String>(null, ((ListMapper) map).getSource() + "[" + i + "]"));
                            if (null != o) listO.add(o);
                        }
                    }
                    if (listO.size() == size)
                        runner.getArgs().put(map.getName(), listO);

                } else if (map instanceof Mapper) {
                    o = mapper(map, runner, -1, new TwoTuple<String, String>(null, null));
                    if (null != o)
                        runner.getArgs().put(map.getName(), o);
                }
            }
        } else {
            if (log.isDebugEnabled())
                log.debug("[" + serviceId + "] 不存在映射");
        }
        if (log.isDebugEnabled())
            log.debug("[" + serviceId + "] Request映射结束");
    }

    /**
     * 数据映射
     *
     * @param model
     * @param runner
     * @param index
     * @param source
     * @return
     */
    private Object mapper(Mapper model, Runner runner, int index, TwoTuple<String, String> source) {
        Object bean = null;
        try {
            // 判断是否满足映射条件
            if (StringUtils.isNotEmpty(model.getCondition()) && !runner.eval(boolean.class, replaceExpr(model, model.getCondition(), index, source))) {
                if (log.isDebugEnabled())
                    log.debug("Condition [" + model.getCondition() + "] 条件不满足，忽略映射！");
                return bean;
            }

            if (log.isDebugEnabled())
                log.debug("开始映射类 [" + model.getClazz() + "] " + (index >= 0 ? "，下标 [" + index + "] " : ""));

            bean = BaseGenerator.create(ClassLoaderUtils.loadClass(model.getClazz())).next();
            HashMap<String, Class<?>> beanMap = getDataType(bean);
            String expr;
            for (Item item : model.getItem()) {
                if (beanMap.containsKey(item.getKey())) {
                    try {
                        Object o = null;
                        if (StringUtils.isNotEmpty(item.getValue())) {
                            expr = replaceExpr(model, item.getValue(), index, source);
                            if (log.isDebugEnabled())
                                log.debug("开始将 [{}] 映射到属性 [{}]", expr, item.getKey());
                            o = runner.eval(beanMap.get(item.getKey()), expr);
                        } else if (null != item.getMapper()) {
                            Mapper map = item.getMapper();
                            if (map instanceof ListMapper) {
                                // 映射数组
                                List<Object> listO = new ArrayList<>();
                                int size = 0;
                                // 如果没有指定Source，表明新new一个数组
                                if (StringUtils.isEmpty(((ListMapper) map).getSource())) {
                                    size = 1;
                                    o = mapper(map, runner, index, new TwoTuple<String, String>(source.second, ((ListMapper) map).getSource(source.second) + "[" + index + "]"));
                                    if (null != o) listO.add(o);
                                } else {
                                    try {
                                        size = runner.eval(int.class, ((ListMapper) map).getSource(source.second) + ".size()");
                                    } catch (SpelEvaluationException e) {
                                    }
                                    for (int i = 0; i < size; i++) {
                                        Object oo = mapper(map, runner, i, new TwoTuple<String, String>(source.second, ((ListMapper) map).getSource(source.second) + "[" + i + "]"));
                                        if (null != oo) listO.add(oo);
                                    }
                                }
                                if (listO.size() == size)
                                    o = listO;
                            } else if (map instanceof Mapper) {
                                // 映射对象
                                o = mapper(map, runner, -1, new TwoTuple<String, String>(null, null));
                            }
                        }

                        if (null != o) {
                            BeanUtils.setProperty(bean, item.getKey(), o);
                        } else {
                            if (log.isDebugEnabled())
                                log.debug("[" + item.getKey() + "] 属性映射值为null,忽略此映射");
                        }
                    } catch (Throwable t) {
                        if (log.isWarnEnabled())
                            log.warn("[" + item.getKey() + "] 属性映射失败,忽略此映射\n" + t.getMessage());
                    } finally {
                        if (log.isDebugEnabled())
                            log.debug("[" + item.getKey() + "] 属性映射结束");
                    }
                } else {
                    if (log.isWarnEnabled())
                        log.warn("[" + item.getKey() + "] 属性不存在,忽略此映射");
                }
            }
        } catch (Throwable t) {
            if (log.isWarnEnabled())
                log.warn("[" + model.getName() + "] 映射失败,忽略此映射\n" + "[" + t.getClass().getSimpleName() + "] " + t.getMessage());
        } finally {
            if (log.isDebugEnabled())
                log.debug("类 [" + model.getClazz() + "] 映射结束");
        }
        return bean;
    }

    /**
     * 获取对象的属性列名与class类型
     *
     * @param obj
     * @return
     * @exception IntrospectionException
     */
    private HashMap<String, Class<?>> getDataType(Object obj) throws IntrospectionException {
        String key = obj.getClass().getName();
        if (objDataType.containsKey(key)) {
            return objDataType.get(key);
        }
        HashMap<String, Class<?>> map = new HashMap();
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            map.put(propertyName, descriptor.getPropertyType());
        }
        objDataType.put(key, map);
        return map;
    }

    /**
     * 表达式替换
     *
     * @param model
     *         映射模型
     * @param exprStr
     *         表达式String
     * @param index
     *         数组下标索引
     * @param source
     *         二元组，first：父数组标识，数组标识
     * @return
     */
    private String replaceExpr(Mapper model, String exprStr, int index, TwoTuple<String, String> source) {
        String expr;
        if (model instanceof ListMapper) {
            ListMapper listModel = (ListMapper) model;
            String detailExp = listModel.getSource();
            if (StringUtils.isNotEmpty(source.first) && exprStr.indexOf("${superSource}") > -1) {
                expr = exprStr.replace("${superSource}", source.first);
            } else if (StringUtils.isNotEmpty(source.second)) {
                expr = exprStr.replace("${source}", source.second);
            } else if (exprStr.indexOf("${source}") > -1) {
                expr = exprStr.replace("${source}", detailExp + "[" + index + "]");
            } else if (exprStr.startsWith("$.")) {
                expr = detailExp + "[" + index + "]." + exprStr.substring(2);
            } else {
                expr = exprStr;
            }
        } else {
            expr = exprStr;
        }

        return expr;
    }

    /**
     * 设置mapping资源列表
     *
     * @param mappingLocations
     */
    public void setMappingLocations(Resource[] mappingLocations) {
        this.mappingLocations = mappingLocations;
    }
}
