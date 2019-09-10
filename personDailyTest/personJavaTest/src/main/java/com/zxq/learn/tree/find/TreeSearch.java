package com.zxq.learn.tree.find;

import java.util.Stack;

/**
 * @Description :二叉树查找
 * @Author :zhouxqh
 * @Date : Create on 2018/5/22
 */
public class TreeSearch {

    /**查找在二叉树以node为节点的树上 是否存在val的节点*/
    static BinTreeNode searchTree(BinTreeNode node, int val){
        if (node == null)
            return null;
        if (node.getValue() == val){
            return node;
        }else if (node.getValue() > val){
            if (node.getLeftNode() != null){
              return searchTree(node.getLeftNode(),val);
            }else { return null; }
        }else {
            if (node.getRigthNode() != null){
              return searchTree(node.getRigthNode(),val);
            }else { return null; }
        }
    }
    /**非递归调用*/
    static BinTreeNode searchTree2(BinTreeNode node, int val){
        BinTreeNode current = node;
        while (true){
            if (node.getValue() == val) return node;
            if (node.getValue() > val){
                current = node.getLeftNode();
            }else {
                current = node.getRigthNode();
            }
            if (current == null) return null;
        }
    }
    /**将node插入到以root为根节点的树中*/
    static BinTreeNode insertNode(BinTreeNode root, BinTreeNode node){
        if (root.getValue() <= node.getValue() && root.getRigthNode()!=null){
            insertNode(root.getRigthNode(),node);
        }
        if (root.getValue() >= node.getValue() && root.getLeftNode()!=null){
            insertNode(root.getLeftNode(),node);
        }
        if (root.getValue() < node.getValue() && root.getRigthNode() == null){
            root.setRigthNode(node);
        }
        if (root.getValue() >= node.getValue() && root.getLeftNode() == null){
            root.setLeftNode(node);
        }
        return root;
    }
    /**非递归调用*/
    static BinTreeNode insertNode2(BinTreeNode root, int val){
        BinTreeNode node = new BinTreeNode();
        node.setValue(val);
        if (root == null){
            root = node;
        }else {
            BinTreeNode current = root;
            BinTreeNode parent;
            while(true){
                if (current.getValue() > val){
                    parent = current;
                    current = current.getLeftNode();
                    if (current == null){
                        parent.setLeftNode(node);
                        break;
                    }
                }else if (current.getValue() < val){
                    parent = current;
                    current = current.getRigthNode();
                    if (current == null){
                        parent.setRigthNode(node);
                        break;
                    }
                }else {
                    System.out.println("some value the same as current node!");
                }
            }
        }
        return root;
    }
    /**删除以node为根节点的树*/
    static BinTreeNode deleteTree(BinTreeNode root , int val){
        BinTreeNode current = root ;
        BinTreeNode parent = null;
        if (current == null){ return null; }
        while (true) {
            if (current.getValue() == val) {
                parent.setRigthNode(null);
                parent.setLeftNode(null);
                return root;
            } else if (current.getValue() > val) {
                parent = current;
                if (current.getLeftNode() != null){
                    current = current.getLeftNode();
                }else {
                    return root;
                }
            } else {
                parent = current;
                if (current.getRigthNode() != null){
                    current = current.getRigthNode();
                }else {
                    return root;
                }
            }
        }
    }
    /**递归删除*/
    static BinTreeNode deleteTree2(BinTreeNode root , BinTreeNode current, BinTreeNode parentNode, int val){
        BinTreeNode parent = parentNode;
        BinTreeNode currentNode = current;
        if (root == null){
            return null;
        }
        if (currentNode.getValue() == val){
            parent.setLeftNode(null);
            parent.setRigthNode(null);
            return root;
        }else if (currentNode.getValue() > val){
            if (currentNode.getLeftNode() != null){
                parent = currentNode;
                currentNode = currentNode.getLeftNode();
                return deleteTree2(root,currentNode,parent,val);
            }else {
                return root;
            }
        }else {
            if (currentNode.getRigthNode() != null){
                parent = currentNode;
                currentNode = currentNode.getRigthNode();
                return deleteTree2(root,currentNode,parent,val);
            }else {
                return root;
            }
        }
    }
    /**创建数组，以root为根节点的树*/
    static BinTreeNode createTree(BinTreeNode node, int array[], int size){
        printf(array,size);
        if (node == null){
            node = new BinTreeNode();
        }
        node.setValue(array[0]);
        for (int i = 1; i < size; i++){
            BinTreeNode child = new BinTreeNode();
            child.setValue(array[i]);
            child.setLeftNode(null);
            child.setRigthNode(null);
            insertNode(node,child);
        }
        return node;
    }
    /**创建数组，以root为根节点的树*/
    static BinTreeNode createTree2(BinTreeNode node, int array[], int size){
        printf(array,size);
        if (node == null){
            node = new BinTreeNode();
        }
        node.setValue(array[0]);
        for (int i = 1; i < size; i++){
            insertNode2(node,array[i]);
        }
        return node;
    }
    private static void printf(int array[],int size){
        System.out.println("array info is: ");
        for (int i = 0; i < size; i++){
            System.out.print(" " +array[i]);
        }
    }
    private static void printfNode(BinTreeNode node){
        System.out.print(" "+node.getValue());
    }
    /**前序遍历*/
    public static void before(BinTreeNode node){
        if (node != null){
            printfNode(node);
            before(node.getLeftNode());
            before(node.getRigthNode());
        }
    }
    /**中序遍历*/
    public static void middle(BinTreeNode node){
        if (node != null){
            middle(node.getLeftNode());
            printfNode(node);
            middle(node.getRigthNode());
        }
    }
    /**中序遍历 非递归*/
    public static void middle2(BinTreeNode node){

        Stack<BinTreeNode> stack = new Stack<>();
        BinTreeNode current = node;
        while(current != null || !stack.isEmpty()){

            while(current!=null){
                stack.push(current);
                current = current.getLeftNode();
            }
            if (!stack.isEmpty()){
                current = stack.pop();
                printfNode(current);
                current = current.getRigthNode();
            }
        }
    }
    /**后序遍历*/
    public static void after(BinTreeNode node){
        if (node != null){
            after(node.getLeftNode());
            after(node.getRigthNode());
            printfNode(node);
        }
    }
    /**后序遍历 非递归*/
    public static void after2(BinTreeNode node){
        Stack<BinTreeNode> stack = new Stack<>();
        BinTreeNode current = node;
        BinTreeNode preNode = null;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.getLeftNode();
            }
            if (!stack.isEmpty()) {
                current = stack.peek().getRigthNode();
                if (current == null || current == preNode) {
                    current = stack.pop();
                    printfNode(current);
                    preNode = current;
                    current = null;
                }
            }
        }
    }
}
