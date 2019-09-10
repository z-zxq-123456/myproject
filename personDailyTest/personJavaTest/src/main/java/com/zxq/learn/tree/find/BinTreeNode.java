package com.zxq.learn.tree.find;

/**
 * @Description :树节点
 * @Author :zhouxqh
 * @Date : Create on 2018/5/22
 */
public class BinTreeNode {

    private int value;
    private BinTreeNode leftNode;
    private BinTreeNode rigthNode;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
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
