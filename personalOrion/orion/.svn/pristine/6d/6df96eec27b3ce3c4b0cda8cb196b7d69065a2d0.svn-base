package com.dcits.orion.stria.model;

import com.dcits.orion.core.Context;
import com.dcits.orion.stria.Action;
import com.dcits.orion.stria.core.Runner;
import com.dcits.galaxy.base.GalaxyConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tim on 2015/5/19.
 */
public abstract class NodeModel extends BaseModel implements Action {

    private static final long serialVersionUID = -4071870950891530875L;

    /**
     * 输入变迁集合
     */
    private List<TransitionModel> inputs = new ArrayList<>();
    /**
     * 输出变迁集合
     */
    private List<TransitionModel> outputs = new ArrayList<>();
    /**
     * layout
     */
    private String layout;
    /**
     * 节点类型
     */
    private String type;

    /**
     * 具体节点模型需要完成的执行逻辑
     *
     * @param runner
     */
    protected abstract void exec(Runner runner);

    /**
     * 对执行逻辑增加前置、后置拦截处理
     *
     * @param runner
     * @return
     */
    public void execute(Runner runner) {
        try {
            Context.getInstance().setCurrentNodeType(this.getType());
            exec(runner);
        } finally {
            if (null != Context.getInstance().getCurrentNodeType())
                Context.getInstance().setCurrentNodeType(null);
        }
    }

    /**
     * 运行变迁继续执行
     *
     * @param runner
     */
    protected void runOutTransition(Runner runner) {
        if (null != runner.getOut()
                && !GalaxyConstants.STATUS_SUCCESS.equals(runner.getOut()
                .getRetStatus())) {
            return;
        }

        for (TransitionModel tm : getOutputs()) {
            tm.setEnabled(true);
            tm.execute(runner);
        }
    }

    public <T> List<T> getNextModels(Class<T> clazz) {
        List<T> models = new ArrayList<T>();
        for (TransitionModel tm : this.getOutputs()) {
            addNextModels(models, tm, clazz);
        }
        return models;
    }

    @SuppressWarnings("unchecked")
    protected <T> void addNextModels(List<T> models, TransitionModel tm, Class<T> clazz) {
        if (clazz.isInstance(tm.getTarget())) {
            models.add((T) tm.getTarget());
        } else {
            for (TransitionModel tm2 : tm.getTarget().getOutputs()) {
                addNextModels(models, tm2, clazz);
            }
        }
    }

    public List<TransitionModel> getInputs() {
        return inputs;
    }

    public void setInputs(List<TransitionModel> inputs) {
        this.inputs = inputs;
    }

    public List<TransitionModel> getOutputs() {
        return outputs;
    }

    public void setOutputs(List<TransitionModel> outputs) {
        this.outputs = outputs;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
