package com.zxq.learn.effectJava.enumAnnotaion.Annotation;

/**
 * Created{ by zhouxqh} on 2017/10/26.
 */
public class Mode {

    private String name;
    private String year;
    private String sex;

    public String getName() {
        return name;
    }

    @Test
    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    @Test
    public void setYear(String year) {
        this.year = year;
    }

    public String getSex() {
        return sex;
    }

    @Test
    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {

        return this.name+" "+ this.sex;
    }
}
