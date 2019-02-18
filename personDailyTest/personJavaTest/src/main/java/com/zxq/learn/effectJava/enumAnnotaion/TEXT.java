package com.zxq.learn.effectJava.enumAnnotaion;

/**
 * 如枚举的元素主要用在集合中，一般使用int枚举模式
 * 利用OR位运算将几个常量合并到一个集合中，称作位域（bit field）
 * Created{ by zhouxqh} on 2017/10/25.
 */
public class TEXT {

    public static final int STYLE_BOLD = 1<<0;
    public static final int STYLE_ITALIC = 1<<1;
    public static final int STYLE_UNDERLINE = 1<<2;
    public static final int STYLE_STRIKETHROUGH = 1<<3;

    public void applyStyles(int styles){
        System.out.println(styles);
    }

    public static void main(String[]args){

        TEXT text = new TEXT();
        text.applyStyles(TEXT.STYLE_BOLD|TEXT.STYLE_ITALIC|TEXT.STYLE_STRIKETHROUGH);
    }
}
