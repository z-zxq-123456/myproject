/**
 * <p>Title: MRJob.java</p>
 * <p>Description: Mapreduce Job定义</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 *
 * @author Tim
 * @update 2014年9月15日 下午2:02:19
 * @version V1.0
 */

package com.dcits.orion.schedule.model;

import com.dcits.orion.schedule.common.RenderParam;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Tim
 * @version V1.0
 * @description Mapreduce Job定义
 * @update 2014年9月15日 下午3:24:10
 */

public class MRJob extends AbstractJob {

    protected String jarByClass;

    protected String mapperInputPatch;

    protected String mapperFormatClass;

    protected String mapperClass;

    protected String mapperOutputKey;

    protected String mapperOutputValue;

    protected String reducerOnputPatch;

    protected String reducerClass;

    protected String reducerNumTask;

    protected String outputFormatClass;

    protected String outputKey;

    protected String outputValue;

    public String getJarByClass() {
        return jarByClass;
    }

    public void setJarByClass(String jarByClass) {
        this.jarByClass = jarByClass;
    }

    /*public String getMapperInputPatch() {
        if (null == mapperInputPatch || "".equals(mapperInputPatch))
            return null;
        return RenderParam.render(mapperInputPatch);
    }*/

    public String getMapperInputPatch(String runDate) {
        if (null == mapperInputPatch || "".equals(mapperInputPatch))
            return null;
        Map<String, Object> param = new HashMap<>();
        param.put("sysdate", runDate);
        return RenderParam.render(mapperInputPatch, param);
    }

    public void setMapperInputPatch(String mapperInputPatch) {
        this.mapperInputPatch = mapperInputPatch;
    }

    public String getMapperFormatClass() {
        return this.mapperFormatClass;
    }

    // @SuppressWarnings({ "rawtypes", "unchecked" })
    // public Class<? extends InputFormat> getMapperFormatClazz()
    // throws ClassNotFoundException {
    // if (null == mapperFormatClass || "".equals(mapperFormatClass))
    // return null;
    // return (Class<? extends InputFormat>) ClassLoaderUtils
    // .loadClass(mapperFormatClass);
    // }

    public void setMapperFormatClass(String mapperFormatClass) {
        this.mapperFormatClass = mapperFormatClass;
    }

    public String getMapperClass() {
        return this.mapperClass;
    }

    // @SuppressWarnings({ "rawtypes", "unchecked" })
    // public Class<? extends Mapper> getMapperClazz()
    // throws ClassNotFoundException {
    // if (null == mapperClass || "".equals(mapperClass))
    // return null;
    // return (Class<? extends Mapper>) ClassLoaderUtils
    // .loadClass(mapperClass);
    // }

    public void setMapperClass(String mapperClass) {
        this.mapperClass = mapperClass;
    }

    public String getMapperOutputKey() {
        return this.mapperOutputKey;
    }

    // @SuppressWarnings({ "unchecked" })
    // public Class<? extends Writable> getMapperOutputKeyClazz()
    // throws ClassNotFoundException {
    // if (null == mapperOutputKey || "".equals(mapperOutputKey))
    // return null;
    // return (Class<? extends Writable>) ClassLoaderUtils
    // .loadClass(mapperOutputKey);
    // }

    public void setMapperOutputKey(String mapperOutputKey) {
        this.mapperOutputKey = mapperOutputKey;
    }

    public String getMapperOutputValue() {
        return this.mapperOutputValue;
    }

    // @SuppressWarnings({ "unchecked" })
    // public Class<? extends Writable> getMapperOutputValueClazz()
    // throws ClassNotFoundException {
    // if (null == mapperOutputValue || "".equals(mapperOutputValue))
    // return null;
    // return (Class<? extends Writable>) ClassLoaderUtils
    // .loadClass(mapperOutputValue);
    // }

    public void setMapperOutputValue(String mapperOutputValue) {
        this.mapperOutputValue = mapperOutputValue;
    }

    /*public String getReducerOnputPatch() {
        if (null == reducerOnputPatch || "".equals(reducerOnputPatch))
            return null;
        return RenderParam.render(reducerOnputPatch);
    }*/

    public String getReducerOnputPatch(String runDate) {
        if (null == reducerOnputPatch || "".equals(reducerOnputPatch))
            return null;
        Map<String, Object> param = new HashMap<>();
        param.put("sysdate", runDate);
        return RenderParam.render(reducerOnputPatch, param);
    }

    public void setReducerOnputPatch(String reducerOnputPatch) {
        this.reducerOnputPatch = reducerOnputPatch;
    }

    public String getReducerClass() {
        return this.reducerClass;
    }

    // @SuppressWarnings({ "rawtypes", "unchecked" })
    // public Class<? extends Reducer> getReducerClazz()
    // throws ClassNotFoundException {
    // if (null == reducerClass || "".equals(reducerClass))
    // return null;
    // return (Class<? extends Reducer>) ClassLoaderUtils
    // .loadClass(reducerClass);
    // }

    public void setReducerClass(String reducerClass) {
        this.reducerClass = reducerClass;
    }

    public int getReducerNumTask() {
        if (null == reducerNumTask || "".equals(reducerNumTask))
            return 0;
        return Integer.parseInt(reducerNumTask);
    }

    public void setReducerNumTask(String reducerNumTask) {
        this.reducerNumTask = reducerNumTask;
    }

    public String getOutputFormatClass() {
        return this.outputFormatClass;
    }

    // @SuppressWarnings({ "rawtypes", "unchecked" })
    // public Class<? extends OutputFormat> getOutputFormatClazz()
    // throws ClassNotFoundException {
    // if (null == outputFormatClass || "".equals(outputFormatClass))
    // return null;
    // return (Class<? extends OutputFormat>) Class.forName(outputFormatClass);
    // }

    public void setOutputFormatClass(String outputFormatClass) {
        this.outputFormatClass = outputFormatClass;
    }

    public String getOutputKey() {
        return this.outputKey;
    }

    // @SuppressWarnings("unchecked")
    // public Class<? extends Writable> getOutputKeyClazz()
    // throws ClassNotFoundException {
    // if (null == outputKey || "".equals(outputKey))
    // return null;
    // return (Class<? extends Writable>) ClassLoaderUtils
    // .loadClass(outputKey);
    // }

    public void setOutputKey(String outputKey) {
        this.outputKey = outputKey;
    }

    public String getOutputValue() {
        return this.outputValue;
    }

    // @SuppressWarnings("unchecked")
    // public Class<? extends Writable> getOutputValueClazz()
    // throws ClassNotFoundException {
    // if (null == outputValue || "".equals(outputValue))
    // return null;
    // return (Class<? extends Writable>) ClassLoaderUtils
    // .loadClass(outputValue);
    // }

    public void setOutputValue(String outputValue) {
        this.outputValue = outputValue;
    }

}
