package com.dcits.ensemble.om.pf.zTreeModel;
/**
 * @author maruie
 * @date   2016-01-12
 * AttrModel
 * zookeeper 操作
 */

public class AttrModel {
    private String id;
    private String pId;
    private String name;
    private String attrKey;
    private String valueMethod;
    private String setValueFlag;
    private String attrValue;
    private String partType;
    private String flag;

    public String getReqNum() {
        return reqNum;
    }

    public void setReqNum(String reqNum) {
        this.reqNum = reqNum;
    }

    private String reqNum;
    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getPartType() {
        return partType;
    }

    public void setPartType(String partType) {
        this.partType = partType;
    }

    public String getAttrKey() {
        return attrKey;
    }

    public void setAttrKey(String attrKey) {
        this.attrKey = attrKey;
    }

    public String getValueMethod() {
        return valueMethod;
    }

    public void setValueMethod(String valueMethod) {
        this.valueMethod = valueMethod;
    }

    public String getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue;
    }

    public String getPId() {
        return pId;
    }

    public void setPId(String pId) {
        this.pId = pId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSetValueFlag() {
        return setValueFlag;
    }

    public void setSetValueFlag(String setValueFlag) {
        this.setValueFlag = setValueFlag;
    }


}
