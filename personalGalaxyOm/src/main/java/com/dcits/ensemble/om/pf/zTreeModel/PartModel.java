package com.dcits.ensemble.om.pf.zTreeModel;

/**
 * @author maruie
 * @date   2016-01-12
 * PartModel
 * zookeeper 操作
 */
public class PartModel {
    private String id;
    private String pId;
    private String name;
    private String partType;


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
}
