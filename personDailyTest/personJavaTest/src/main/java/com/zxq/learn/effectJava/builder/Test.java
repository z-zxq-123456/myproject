package com.zxq.learn.effectJava.builder;


/**
 * Created{ by zhouxqh} on 2017/10/19.
 */
public class Test {
    public static void main1(String[] args){
        Test test = new Test();
        Father father = new Father.Builder(10,2)
                .calories(3)
                .carbom(4)
                .fat(6)
                .sodium(7).build();

        /*try{
            BeanInfo bf = BeanInfo.class.newInstance();
            PropertyDescriptor[] pds = Introspector.getBeanInfo(BeanInfo.class).getPropertyDescriptors();
            for (PropertyDescriptor p:pds){
                if (p.getName().equals("name")){
                    Method wr = p.getWriteMethod();
                    wr.invoke(bf,"zhang");
                    Method re = p.getReadMethod();
                    System.out.println(re.invoke(bf));
                }
            }
        }catch (Exception e){}*/

        System.out.println("print: = = \n"+
                father.toString());
    }
   }
