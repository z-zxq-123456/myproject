package com.zxq.learn.effectJava.enumAnnotaion;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * EnumMap的使用
 * 防止数组和泛型使用时，类型不受编译期检查
 * Created{ by zhouxqh} on 2017/10/25.
 */
public class Herb {

    public enum Type{ANNUAL,PEPENNIAL,BIENNIAL}
    private final String name;
    private final Type type;

    public Herb(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return name;
    }

    public static void main(String[]args){

        Herb[] gards = new Herb[5];
        Herb herb1 = new Herb("rose",Type.ANNUAL);
        Herb herb2 = new Herb("white",Type.BIENNIAL);
        Herb herb3 = new Herb("flower",Type.PEPENNIAL);
        Herb herb4 = new Herb("gong",Type.BIENNIAL);
        Herb herb5 = new Herb("sinke",Type.ANNUAL);
        gards[0] = herb1;
        gards[1] = herb2;
        gards[2] = herb3;
        gards[3] = herb4;
        gards[4] = herb5;

        Map<Type,Set<Herb>> herbsType =
                new EnumMap<Type,Set<Herb>>(Type.class);
        for (Type t: Type.values()){
            herbsType.put(t,new HashSet<Herb>());
        }
        for (Herb h:gards){
            herbsType.get(h.type).add(h);
            System.out.println(herbsType);
        }
        System.out.println((herbsType.get(Type.BIENNIAL)).toArray()[1]);
    }
}
