package com.dcits.orion.stria.model;

import com.dcits.orion.stria.Action;
import com.dcits.orion.stria.core.Runner;

/**
 * Created by Tim on 2015/5/19.
 */
public class TransitionModel extends BaseModel implements Action {

    /**
     * 变迁的源节点引用
     */
    private NodeModel source;
    /**
     * 变迁的目标节点引用
     */
    private NodeModel target;
    /**
     * 变迁的目标节点name名称
     */
    private String to;
    /**
     * 变迁的源节点name名称
     */
    private String from;
    /**
     * 变迁的条件表达式，用于decision
     */
    private String expr;
    /**
     * 转折点图形数据
     */
    private String g;
    /**
     * 描述便宜位置
     */
    private String offset;
    /**
     * 当前变迁路径是否可用
     */
    private boolean enabled = false;

    public void execute(Runner runner) {
        if (!enabled) return;
        target.execute(runner);
    }

    public NodeModel getSource() {
        return source;
    }

    public void setSource(NodeModel source) {
        this.source = source;
    }

    public NodeModel getTarget() {
        return target;
    }

    public void setTarget(NodeModel target) {
        this.target = target;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getExpr() {
        return expr;
    }

    public void setExpr(String expr) {
        this.expr = expr;
    }

    public String getG() {
        return g;
    }

    public void setG(String g) {
        this.g = g;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
