package com.zxq.learn.tree.find;

/**
 * @Description :二叉树测试
 * @Author :zhouxqh
 * @Date : Create on 2018/5/23
 */
public class Demo3 {
    public static void main(String []args){

        int array[] = {1,3,5,78,4,2,23,44,35,21,12};
        BinTreeNode root2 = null;
        root2 = TreeSearch.createTree2(root2,array,array.length);
//        root = TreeSearch.createTree(root,array,array.length);
        System.out.println("\nbefore is: ");
        TreeSearch.before(root2);
        System.out.println("\nmiddle is: ");
        TreeSearch.middle(root2);
        System.out.println("\nafter is: ");
        TreeSearch.after2(root2);
//        BinTreeNode binTreeNode = TreeSearch.searchTree(root,40);
//        if (binTreeNode == null){ System.out.println("\nnot found in tree!"); }else { System.out.println("\nfound in tree!"); }
//        BinTreeNode delNode = TreeSearch.deleteTree(root2,78);
        BinTreeNode delNode2 = TreeSearch.deleteTree2(root2,root2,null,354);
        System.out.println("\nbefore is: ");
        TreeSearch.before(delNode2);

    }
}
