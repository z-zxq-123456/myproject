package com.dcits.orion.scp.flow.parse;

/**
 *@desc 节点属性接口
 * @author qiqingshan
 */
public interface INodeParser {
    /**
     * 节点属性名称
     */
    public static final String  FLOW_NAME ="title";
    public static final String  CREATETIME = "createtime";
    public static final String  CREATOR = "creator";
    public static final String  RESUME_TYPE = "resume_type";
    public static final String  SCRIPT = "script";
    public static final String  REVERSAL_TYPE = "reversal_type";
    public static final String  TIME_OUT = "time_out";
    public static final String  TIMEOUT_DEAL =  "timeout_deal";
    public static final String  KEY_FIELDS ="key_fields";
    public static final String NODE_ID = "node_id";
    public static final String NODE_DESC = "name";
    public static final String NODE_TYPE = "type";
    public static final String NODE_TOP = "top";
    public static final String NODE_LEFT = "left";
    public static final String NODE_WIDTH = "width";
    public static final String NODE_HEIGHT = "height";
    public static final String EXEC_TYPE = "exec_type";
    public static final String SYTEM_ID = "sytem_id";
    public static final String ARG_NAME = "arg_name";
    public static final String UNKNOW_DEAL = "unknow_deal";
    public static final String RETRY_CNT = "retry_cnt";
    public static final String REVERSAL_ARG = "reversal_arg";
    public static final String CONFIRM_ARG = "confirm_arg";
    public static final String BEFORE_SCRIPT = "before_script";
    public static final String AFTER_SCRIPT = "after_script";
    public static final String FLOW_ID = "flowid";
    public static final String FROM = "from";
    public static final String TO = "to";
    public static final String LINEID = "lineid";
    public static final String EXPR = "expr";
    public static final String NODES = "nodes";
    public static final String LINES = "lines";
    public static final String ERROR_DEAL = "error_deal";
    public static final String ID = "id";
    public static final String LINE_TYPE = "type";
    public static final String FROM_NODE = "from_node";
    public static final String TO_NODE = "to_node";
    public static final String INITNUM = "initNum";
    public static final String LOG_FLAG ="log_flag";
    public static final String MAPPER="mapper";
    public static final String LOOP_IDX="loop_idx";
    public static final String LOOP_EXPRE = "loop_expr";
}
