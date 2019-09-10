package com.zxq.learn.effectJava.builder;

/**
 * Created{ by zhouxqh} on 2017/10/19.
 * 改良类的构造器的参数复杂和javabean的线程不安全的问题
 * builder模式---可以提供可变的参数构造器
 * 适用于类中包含多个参数
 */
public class Father {

    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbom;

    public static class Builder{

        private final int servingSize;
        private final int servings;
        private int calories = 0;
        private int fat = 0;
        private int sodium = 0;
        private int carbom = 0;
        public Builder(int servingSize,int servings){
            this.servingSize = servingSize;
            this.servings = servings;
        }
        public Builder calories(int val){calories = val;return this;}
        public Builder fat(int val){fat = val; throw new IllegalArgumentException("val parameter is not valide!");}
        public Builder sodium(int val){sodium = val;return this;}
        public Builder carbom(int val){carbom = val;return this;}
        public Father build(){
            return new Father(this);
        }
    }

    private Father(Builder builder){
        servingSize = builder.servingSize;
        servings = builder.servings;
        calories = builder.calories;
        carbom = builder.carbom;
        fat = builder.fat;
        sodium = builder.sodium;
    }
}
