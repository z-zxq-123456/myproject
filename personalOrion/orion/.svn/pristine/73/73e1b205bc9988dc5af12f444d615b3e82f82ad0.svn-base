package com.dcits.orion.scp.mapping;

import com.dcits.galaxy.base.util.ExceptionUtils;
import com.dcits.galaxy.base.util.StringUtils;
import com.dcits.orion.batch.common.BatchUtils;
import com.dcits.orion.scp.api.exception.UnknownException;
import com.dcits.orion.scp.engine.ScpEngine;
import com.dcits.orion.scp.mapping.entity.*;
import com.dcits.orion.scp.utils.ScpUtils;
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
 * Created by lixiaobin on 2017/3/15.
 */
public class DataMapping implements InitializingBean {


    private static final Logger log = LoggerFactory
            .getLogger(DataMapping.class);
    Map<String, Map<String, Mapper>> mapping;


    private Resource[] mappingLocations;

    boolean convert = false;//将bean的表达式转化为Map的表达式


    public Map getMappingData(Map cacheMap, String flowId, String mapperName) throws UnknownException {
        Mapper mapper = getMapper(flowId, mapperName);
        if (mapper == null) {
            throw new UnknownException("999999", "数据映射不存在,flowId=" + flowId + ",mapperName=" + mapperName);
        }

        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext(cacheMap);
        Map variables = ScpUtils.getVariables(cacheMap);
        if (variables != null && !variables.isEmpty())
            context.setVariables(variables);
        try {
            return getValue(cacheMap, parser, context, mapper);
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("数据映射失败,flowId=" + flowId + ",mapperName=" + mapperName);
                log.error(ExceptionUtils.getStackTrace(e));
            }
            throw new UnknownException("999999", "数据映射失败,flowId=" + flowId + ",mapperName=" + mapperName);
        }
    }


    public Map getValue(Map cacheMap, ExpressionParser parser, EvaluationContext context, Mapper mapper) {
        Map map;
        if (StringUtils.isBlank(mapper.getRef())) {
            map = new HashMap();
        } else {
            map = (Map) getValue(parser, context, mapper.getRef());
            map = BatchUtils.deserialize(BatchUtils.serialize(map));
            if (mapper.getRemoves() != null)
            {
                String[] removes = mapper.getRemoves();
                for (String remove : removes)
                {
                    map.remove(remove);
                }
            }
        }
        for (Item item : mapper.getItems()) {
            if (item instanceof IfItem) {
                if ((boolean)getValue(parser,context,item.getValue()))
                {
                    merge(map,getValue(cacheMap, parser, context, item.getMapper()));
                }
            }
            else if(item instanceof ForItem)
            {
                int i = 0;
                context.setVariable(((ForItem) item).getIndex(),i);
                while ((boolean)getValue(parser,context,item.getValue()))
                {
                    merge(map,getValue(cacheMap, parser, context, item.getMapper()));
                    i = i + 1;
                    context.setVariable(((ForItem) item).getIndex(),i);
                }

            }
            else {
                String key = item.getKey();
                if (item.getMapper() == null) {
                    map.put(key, getValue(parser, context, item.getValue()));
                } else if (item.getMapper() instanceof ListMapper) {
                    ListMapper listMapper = (ListMapper) item.getMapper();
                    List list = null;
                    if (StringUtils.isBlank(listMapper.getSource())) {
                        list = new ArrayList(1);
                        list.add(getValue(cacheMap, parser, context, listMapper));
                    } else {
                        List<Map> source = (List) getValue( parser, context, listMapper.getSource());
                        if (source != null && !source.isEmpty()) {
                            list = new ArrayList(source.size());
                            int i = 0;
                            for (Map map1 : source) {
                                context.setVariable(listMapper.getAlias(), map1);
                                if (!StringUtils.isBlank(listMapper.getIndex()))
                                    context.setVariable(listMapper.getIndex(), i);
                                list.add(getValue(cacheMap, parser, context, listMapper));
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
                    map.put(key, getValue(cacheMap, parser, context, item.getMapper()));
                }
            }

        }
        return map;
    }

    private void merge(Map<String,Object> map, Map<String,Object> m)
    {
        if (m != null && !m.isEmpty())
        {
            for (Map.Entry<String,Object> entry : m.entrySet())
            {
                merge(map,entry.getKey(),entry.getValue());
            }
        }
    }
    private void merge(Map map,String name,Object o)
    {
        if (o instanceof List)
        {
            List oldList = (List) map.get(name);//同名的list进行合并
            if (oldList != null && !oldList.isEmpty())
                oldList.addAll((List)o);
            else
                map.put(name, o);

        }
        else if(o != null){
            map.put(name,o);
        }
    }

    public Object getValue(ExpressionParser parser, EvaluationContext context, String expr) {
        if (StringUtils.isBlank(expr))
            return expr;
        try
        {
            return parser.parseExpression(expr).getValue(context);
        }
        catch (Exception e)
        {
            if (log.isErrorEnabled())
            {
                log.error("数据映射失败！表达式为：" + expr);
            }
            throw e;
        }

    }

    public Mapper getMapper(String flowId, String mapperName) {
        Map<String, Mapper> map = mapping.get(flowId);
        if (map == null)
            return null;
        return map.get(mapperName);
    }

    private void addMappings(Map map) {
        if (null == mapping)
            mapping = new HashMap<>();
        mapping.putAll(map);
    }


    @Override
    public void afterPropertiesSet() throws Exception {

        if (mappingLocations != null && mappingLocations.length > 0) {
            for (Resource resource : mappingLocations) {
                MappingBuilder mappingBuilder = new MappingBuilder(resource);
                addMappings(mappingBuilder.parser());
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

    public Map<String, Map<String, Mapper>> getMapping() {
        return mapping;
    }

    public void setMapping(Map<String, Map<String, Mapper>> mapping) {
        this.mapping = mapping;
    }

    public boolean isConvert() {
        return convert;
    }

    public void setConvert(boolean convert) {
        this.convert = convert;
    }
}
