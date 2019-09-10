package com.zxq.learn.tree.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description :遍历服务类
 * @Author :zhouxqh
 * @Date : Create on 2018/5/22
 */
public class NodeService {

    static List<BinTreeNode> visitors;

    /**前序遍历*/
    public static void forward(BinTreeNode node, List<BinTreeNode> nodes){
        if (node!=null){
            printCurrentNode(node);
            forward(node.getLeftNode(),nodes);
            forward(node.getRigthNode(),nodes);
        }
    }
    /**中序遍历*/
    public static void middle(BinTreeNode node, List<BinTreeNode> nodes){
        if (node!=null){
            middle(node.getLeftNode(),nodes);
            printCurrentNode(node);
            middle(node.getRigthNode(),nodes);
        }
    }
    /**后续遍历*/
    public static void back(BinTreeNode node, List<BinTreeNode> nodes){
        if (node!=null){
            back(node.getLeftNode(),nodes);
            back(node.getRigthNode(),nodes);
            printCurrentNode(node);
        }
    }
    /**访问当前节点*/
    private static void printCurrentNode(BinTreeNode node){
        System.out.print("<->");
        System.out.print(node.getValue());
    }

    static {
        visitors = new ArrayList<>();
        BinTreeNode root = new BinTreeNode();
        root.setValue("-");
        BinTreeNode t11 = new BinTreeNode();
        t11.setValue("+");
        BinTreeNode t12 = new BinTreeNode();
        t12.setValue("/");
        root.setLeftNode(t11);
        root.setRigthNode(t12);

        BinTreeNode t21 = new BinTreeNode();
        t21.setValue("a");
        BinTreeNode t22 = new BinTreeNode();
        t22.setValue("*");
        BinTreeNode t23 = new BinTreeNode();
        t23.setValue("e");
        BinTreeNode t24 = new BinTreeNode();
        t24.setValue("f");
        t11.setLeftNode(t21);
        t11.setRigthNode(t22);
        t12.setLeftNode(t23);
        t12.setRigthNode(t24);

        BinTreeNode t31 = new BinTreeNode();
        t31.setValue("b");
        BinTreeNode t32 = new BinTreeNode();
        t32.setValue("-");
        t22.setLeftNode(t31);
        t22.setRigthNode(t32);

        BinTreeNode t41 = new BinTreeNode();
        t41.setValue("c");
        BinTreeNode t42 = new BinTreeNode();
        t42.setValue("d");
        t32.setLeftNode(t41);
        t32.setRigthNode(t42);

        visitors.add(root);visitors.add(t11);
        visitors.add(t12);visitors.add(t21);
        visitors.add(t22);visitors.add(t23);
        visitors.add(t24);visitors.add(t31);
        visitors.add(t32);visitors.add(t41);
        visitors.add(t42);
    }
}
