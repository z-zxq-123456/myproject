package com.zxq.learn.tree.test;

/**
 * @Description :树节点
 * @Author :zhouxqh
 * @Date : Create on 2018/5/22
 */
public class BinTreeNode {

    private String value;
    private BinTreeNode leftNode;
    private BinTreeNode rigthNode;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public BinTreeNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(BinTreeNode leftNode) {
        this.leftNode = leftNode;
    }

    public BinTreeNode getRigthNode() {
        return rigthNode;
    }

    public void setRigthNode(BinTreeNode rigthNode) {
        this.rigthNode = rigthNode;
    }
}
