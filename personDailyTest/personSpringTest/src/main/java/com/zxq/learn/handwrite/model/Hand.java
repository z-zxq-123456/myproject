package com.zxq.learn.handwrite.model;

/**
 * @description
 * @author: zhouxqh
 * @create: 2020-03-23 22:47
 **/
public class Hand {

    private Mouth mouth;

    public void waveHand(){
        System.out.println("-----hand-----");
        mouth.speak();
        System.out.println("-----hand-----");
        System.out.println("挥一挥手");
    }
}
