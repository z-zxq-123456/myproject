package com.zxq.learn.handwrite.ioc.model;

/**
 * @description
 * @author: zhouxqh
 * @create: 2020-03-23 22:47
 **/
public class Robot {

    private Hand hand;
    private Mouth mouth;

    public void  show(){

        hand.waveHand();
        mouth.speak();
    }
}
