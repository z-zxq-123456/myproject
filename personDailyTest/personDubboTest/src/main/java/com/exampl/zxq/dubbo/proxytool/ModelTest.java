package com.exampl.zxq.dubbo.proxytool;


public class ModelTest {

    private String userName;
    private String age;
    private String sex;
    private String occupation;

    public ModelTest() {
    }

    public ModelTest(String userName, String age, String sex, String occupation) {
        this.userName = userName;
        this.age = age;
        this.sex = sex;
        this.occupation = occupation;
    }

    public void method1(){

        System.out.println("method1 start!");
    }

    public void method2(){

        System.out.println("method2 start! userName = "+userName+ " age = "+age +" occupation" + occupation);
    }

    public void method3(String userName,String age){

        System.out.println("method3 start! userName = "+ userName + " age = "+age);
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
}
