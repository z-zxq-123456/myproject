package com.zxq.learn.tree.test;


/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/5/22
 */
public class Demo1 {
    public static void main(String[]args)throws Exception{

        System.out.println("forward:");
        NodeService.forward(NodeService.visitors.get(0), NodeService.visitors);

        System.out.println();
        System.out.println("middle:");
        NodeService.middle(NodeService.visitors.get(0), NodeService.visitors);

        System.out.println();
        System.out.println("back:");
        NodeService.back(NodeService.visitors.get(0), NodeService.visitors);
    }
}
