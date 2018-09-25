package com.dcits.orion.stria.parse;

import com.alibaba.fastjson.JSONObject;
import com.dcits.orion.stria.model.NodeModel;

import org.w3c.dom.Element;

/**
 * Created by Tim on 2015/5/20.
 */
public interface INodeParser {
    /**
     * 变迁节点名称
     */
    public static final String NODE_TRANSITION = "transition";

    /**
     * 节点属性名称
     */
    public static final String DISPLAYNAME = "displayName";
    public static final String INSTANCENOCLASS = "instanceNoClass";
    public static final String EXPIRETIME = "expireTime";
    public static final String LAYOUT = "layout";
    public static final String G = "g";
    public static final String OFFSET = "offset";
    public static final String FLOWID = "flowid";
    public static final String TITLE = "title";
    public static final String INITNUM = "initNum";
    public static final String STATE = "state";
    public static final String NAME = "name";
    public static final String TYPE = "type";
    public static final String FROM = "from";
    public static final String TO = "to";
    public static final String EXPR = "expr";
    public static final String SOURCETYPE = "sourceType";
    public static final String SERVICETYPE = "serviceType";
    public static final String CLAZZ = "clazz";
    public static final String EVENTID = "eventId";
    public static final String METHODNAME = "methodName";
    public static final String ARGS = "args";
    public static final String ARGSCLAZZ = "argsClazz";
    public static final String VAR = "alias";
    public static final String NODES = "nodes";
    public static final String LINES = "lines";
    public static final String INLINES = "inLines";
    public static final String OUTLINES = "outLines";
    public static final String NODEID = "nodeid";
    public static final String LINEID = "lineid";
    public static final String TOP = "top";
    public static final String LEFT = "left";
    public static final String WIDTH = "width";
    public static final String HEIGHT = "height";
    public static final String CREATOR = "creator";
    public static final String DTP_FLAG = "dtpFlag";
    public static final String TX_FLAG = "txFlag";
    public static final String FLOW_TYPE = "flowType";
    public static final String CHARSET = "UTF-8";
    public static final String VERSION = "version";
    public static final String CONDITION = "condition";
    public static final String REQUEST = "request";
    public static final String SERVICECODE = "serviceCode";
    public static final String MESSAGETYPE = "messageType";
    public static final String MESSAGECODE = "messageCode";
    public static final String TIMEOUT = "timeOut";
    public static final String PRODTYPE = "prodType";
    public static final String EVENTTYPE = "eventType";

    /**
     * 节点dom元素解析方法，由实现类完成解析
     *
     * @param element dom元素
     */
    void parse(Element element);

    /**
     * 节点JSONObject解析方法，由实现类完成解析
     *
     * @param node JSONObject
     */
    void parse(JSONObject node);

    /**
     * 解析成功后，提供返回NodeModel模型对象
     *
     * @return 节点模型
     */
    NodeModel getModel();
}
