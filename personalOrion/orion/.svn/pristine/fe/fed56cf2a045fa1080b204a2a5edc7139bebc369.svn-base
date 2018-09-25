package com.dcits.orion.stria.model;

import com.dcits.orion.stria.NoGenerator;
import com.dcits.orion.stria.impl.DefaultNoGenerator;
import com.dcits.galaxy.base.BaseGenerator;
import com.dcits.galaxy.base.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tim on 2015/5/19.
 */
public class FlowModel extends BaseModel {

    private static final long serialVersionUID = 1296233669450523352L;

    /**
     * 流程类型 01-服务流程；02-事件流程
     */
    private String flowType;

    /**
     * 节点元素集合
     */
    private List<NodeModel> nodes = new ArrayList<>();

    /**
     * 子流程节点
     */
    private List<SubServiceModel> subNodes = new ArrayList<>();

    private List<EventServiceModel> eventNodes = new ArrayList<>();

    /**
     * 期望完成时间
     */
    private String expireTime;
    /**
     * 实例编号生成的class
     */
    private String instanceNoClass;

    /**
     * 实例编号生成器对象
     */
    private NoGenerator generator;

    /**
     * 全局事物控制开关
     */
    private String depFlag;

    /**
     * Spring事物控制开关
     */
    private String txFlag;

    /**
     * 根据指定的节点类型返回流程定义中所有模型对象
     *
     * @param clazz 节点类型
     * @param <T>   泛型
     * @return 节点列表
     */
    public <T> List<T> getModels(Class<T> clazz) {
        List<T> models = new ArrayList<T>();
        buildModels(models, getStart().getNextModels(clazz), clazz);
        return models;
    }

    private <T> void buildModels(List<T> models, List<T> nextModels,
                                 Class<T> clazz) {
        for (T nextModel : nextModels) {
            if (!models.contains(nextModel)) {
                models.add(nextModel);
                buildModels(models,
                        ((NodeModel) nextModel).getNextModels(clazz), clazz);
            }
        }
    }

    /**
     * 获取process定义的start节点模型
     *
     * @return
     */
    public StartModel getStart() {
        for (NodeModel node : nodes) {
            if (node instanceof StartModel) {
                return (StartModel) node;
            }
        }
        return null;
    }

    /**
     * 获取process定义的指定节点名称的节点模型
     *
     * @param nodeName 节点名称
     * @return
     */
    public NodeModel getNode(String nodeName) {
        for (NodeModel node : nodes) {
            if (node.getName().equals(nodeName)) {
                return node;
            }
        }
        return null;
    }

    /**
     * 判断当前模型的节点是否包含给定的节点名称参数
     *
     * @param nodeNames 节点名称数组
     * @return
     */
    public <T> boolean containsNodeNames(Class<T> T, String... nodeNames) {
        for (NodeModel node : nodes) {
            if (!T.isInstance(node)) {
                continue;
            }
            for (String nodeName : nodeNames) {
                if (node.getName().equals(nodeName)) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<NodeModel> getNodes() {
        return nodes;
    }

    public void setNodes(List<NodeModel> nodes) {
        this.nodes = nodes;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public String getInstanceNoClass() {
        return instanceNoClass;
    }

    public void setInstanceNoClass(String instanceNoClass) {
        this.instanceNoClass = instanceNoClass;
        if (StringUtils.isNotEmpty(instanceNoClass)) {
            generator = BaseGenerator.create(NoGenerator.class).next();
        }
    }

    public NoGenerator getGenerator() {
        return generator == null ? new DefaultNoGenerator() : generator;
    }

    public void setGenerator(NoGenerator generator) {
        this.generator = generator;
    }

    public List<SubServiceModel> getSubNodes() {
        return subNodes;
    }

    public void setSubNodes(List<SubServiceModel> subNodes) {
        this.subNodes = subNodes;
    }

    /**
     * 全局提交服务事物管理开关
     *
     * @return
     */
    public String getDepFlag() {
        return depFlag == null ? "N" : depFlag;
    }

    /**
     * 全局提交服务事物管理开关
     *
     * @param depFlag
     */
    public void setDepFlag(String depFlag) {
        this.depFlag = (depFlag == null || depFlag.length() != 1) ? "N" : depFlag;
    }

    public List<EventServiceModel> getEventNodes() {
        return eventNodes;
    }

    public void setEventNodes(List<EventServiceModel> eventNodes) {
        this.eventNodes = eventNodes;
    }

    public String getFlowType() {
        return flowType;
    }

    public void setFlowType(String flowType) {
        this.flowType = flowType;
    }

    public String getTxFlag() {
        return txFlag;
    }

    public void setTxFlag(String txFlag) {
        this.txFlag = txFlag;
    }
}
