package com.dcits.orion.base.map.mapping;

import com.dcits.galaxy.base.util.BeanUtils;
import com.dcits.galaxy.base.util.ExceptionUtils;
import com.dcits.galaxy.base.util.StringUtils;
import com.dcits.galaxy.core.access.ThreadLocalManager;
import com.dcits.galaxy.core.serializer.SerializationUtils;
import com.dcits.orion.base.map.mapping.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lixiaobin on 2017/6/13.
 */
public class ParserMapping implements InitializingBean {

    private static final Logger log = LoggerFactory
            .getLogger(ParserMapping.class);

    public static final String WORK_MAP_BAK_IN = "bakIn";

    public static final String WORK_MAP_IN = "in";

    public static final String WORK_MAP_OUT = "out";

    private Map<String, Service> serviceMapping;

    private Map<String, BaseDefine> baseDefineMapping;

    private Resource[] mappingLocations;

    private String cacheKey = "com.dcits.orion.base.map.mapping.ParserMapping.cacheKey";

    private String cachedServiceId = "com.dcits.orion.base.map.mapping.ParserMapping.cachedServiceId";

    public Map getMapping(Map map, String parserId, String inOut) {
        log.debug("parser mapping start! parserId[{}] inOut[{}]", parserId, inOut);
        Map workMap;
        if (WORK_MAP_IN.equals(inOut)) {
            workMap = new HashMap();
            workMap.put(inOut, map);
            Map bakIn = (Map)SerializationUtils.deserializeObj(SerializationUtils.serializeObj(map));
            workMap.put(WORK_MAP_BAK_IN,bakIn);
            ThreadLocalManager.put(cacheKey, workMap);
        }
        else {
            workMap = (Map) ThreadLocalManager.get(cacheKey);
            if (workMap == null)
                workMap = new HashMap();
            workMap.put(inOut, map);
        }

        BaseDefine baseDefine = baseDefineMapping.get(parserId);
        if (baseDefine != null)
        {
            if (!StringUtils.isBlank(baseDefine.getGlobalMappingId()))
            {
                Map mapGlobal = getServiceMapping(baseDefine.getGlobalMappingId(),workMap,inOut);
                workMap.put(inOut,mapGlobal);
            }
            if (!StringUtils.isBlank(baseDefine.getMappingIdSpel()))
            {
                String serviceId = null;
                if (WORK_MAP_IN.equals(inOut))
                {
                    String expr = baseDefine.getMappingIdSpel();
                    ExpressionParser parser = new SpelExpressionParser();
                    StandardEvaluationContext context = new StandardEvaluationContext(workMap);
                    try{
                        serviceId = parser.parseExpression(expr).getValue(context, String.class);
                        ThreadLocalManager.put(cachedServiceId,serviceId);
                    }
                    catch (Exception e)
                    {
                        if (log.isWarnEnabled()) {
                            log.warn("baseDefine.mappingIdSpel 数据映射失败！表达式为：" + expr);
                            log.warn(ExceptionUtils.getStackTrace(e));
                        }
                    }
                }
                else {
                    serviceId = (String) ThreadLocalManager.get(cachedServiceId);
                }
                if (null == serviceId) {
                    log.warn("{} mapping, serviceId is null, Ignore parser mapping!", inOut);
                    // 原值返回
                    return (Map)workMap.get(inOut);
                }
                return getServiceMapping(serviceId,workMap,inOut);
            }
            else
                return (Map)workMap.get(inOut);
        }
        else
            return (Map)workMap.get(inOut);
    }


    private Map getServiceMapping(String serviceId,Map workMap,String inOut)
    {
        Service service = serviceMapping.get(serviceId);
        if (service != null)
            return getServiceMapping(service, workMap, inOut);
        else return (Map)workMap.get(inOut);

    }

    private Map getServiceMapping(Service service, Map workMap, String inOut) {
        if (service == null)
            return (Map)workMap.get(inOut);
        Mapper mapper;
        if (WORK_MAP_IN.equals(inOut)) {
            mapper = service.getIn();
        } else
            mapper = service.getOut();

        String[] useMappings = null;
        if (mapper == null)
        {
            useMappings = service.getUseMappings();
        }
        else {
            useMappings = mapper.getUseMappings();
        }
        if (useMappings != null && useMappings.length > 0) {
            for (String useMapping : useMappings) {
                Service mappingService = serviceMapping.get(useMapping);
                if (mappingService != null)
                    getServiceMapping(mappingService, workMap, inOut);
            }
        }
        if (mapper == null)
        {
            return (Map) workMap.get(inOut);
        }
        else {
            Map map = getMapping(workMap, mapper);
            workMap.put(inOut, map);
            return map;
        }

    }

    private Map getMapping(Map workMap, Mapper mapper) {
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext(workMap);
        return getValue(parser, context, mapper);
    }

    public Map getValue(ExpressionParser parser, EvaluationContext context, Mapper mapper) {
        Map map;
        if (StringUtils.isBlank(mapper.getRef())) {
            map = new HashMap();
        } else {
            map = (Map) getValue(parser, context, mapper.getRef());
        }
        for (Item item : mapper.getItems()) {
            if (item instanceof IfItem) {
                if ((boolean) getValue(parser, context, item.getValue())) {
                    map.putAll(getValue(parser, context, item.getMapper()));
                }
            } else {
                String key = item.getKey();
                if (item.getMapper() == null) {
                    map.put(key, getValue(parser, context, item.getValue()));
                } else if (item.getMapper() instanceof ListMapper) {
                    ListMapper listMapper = (ListMapper) item.getMapper();
                    List list = null;
                    if (StringUtils.isBlank(listMapper.getSource())) {
                        list = new ArrayList(1);
                        list.add(getValue(parser, context, listMapper));
                    } else {
                        List<Map> source = (List) getValue(parser, context, listMapper.getSource());
                        if (source != null && !source.isEmpty()) {
                            list = new ArrayList(source.size());
                            int i = 0;
                            for (Map map1 : source) {
                                context.setVariable(listMapper.getAlias(), map1);
                                if (!StringUtils.isBlank(listMapper.getIndex()))
                                    context.setVariable(listMapper.getIndex(), i);
                                list.add(getValue(parser, context, listMapper));
                                i++;
                            }
                        }
                    }
                    List oldList = (List) map.get(key);//同名的list进行合并
                    if (oldList != null && !oldList.isEmpty())
                        oldList.addAll(list);
                    else
                        map.put(key, list);
                } else {
                    map.put(key, getValue(parser, context, item.getMapper()));
                }
            }

        }
        if (mapper.getRemoves() != null) {
            String[] removes = mapper.getRemoves();
            for (String remove : removes) {
                map.remove(remove);
            }
        }
        return map;
    }


    public Object getValue(ExpressionParser parser, EvaluationContext context, String expr) {
        if (StringUtils.isBlank(expr))
            return expr;
        try {
            return parser.parseExpression(expr).getValue(context);
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("数据映射失败！表达式为：" + expr);
            }
            throw e;
        }

    }


    public void addServiceMappings(Map map) {
        if (map == null)
            return;
        if (null == serviceMapping)
            serviceMapping = new HashMap<>();
        serviceMapping.putAll(map);
    }

    public void addBaseDefineMapping(Map map) {
        if (map == null)
            return;
        if (null == baseDefineMapping)
            baseDefineMapping = new HashMap<>();
        baseDefineMapping.putAll(map);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        if (mappingLocations != null && mappingLocations.length > 0) {
            for (Resource resource : mappingLocations) {
                MappingBuilder mappingBuilder = new MappingBuilder(resource);
                addServiceMappings(mappingBuilder.getServiceMapping());
                addBaseDefineMapping(mappingBuilder.getBaseDefineMapping());
            }
        }

    }

    /////////////////get and set  //////////////////////////
    public Resource[] getMappingLocations() {
        return mappingLocations;
    }

    public void setMappingLocations(Resource[] mappingLocations) {
        this.mappingLocations = mappingLocations;
    }
}
