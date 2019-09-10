package com.zxq.learn.collections;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.*;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/5/31
 */
public class ListDemo {
    public static void main(String[]args)
            throws Exception {

        List<String> arraylist = new ArrayList<>();
        arraylist.add("1");
        arraylist.add("a");
        arraylist.add("4");
        arraylist.add("5");
        arraylist.add("7");
        arraylist.add("9");
        arraylist.add("y");
        arraylist.add("r");
        arraylist.add("k");
        arraylist.add("d");
        arraylist.add(",");
        arraylist.add("g");
        /*forEach*/
        Date beigin = new Date();
        for (Object i : arraylist) {
            System.out.print(i);
        }
        Date end = new Date();
        System.out.println();
        System.out.println((end.getTime() - beigin.getTime()));

        Date beigin1 = new Date();
        arraylist.forEach((key) -> System.out.print(key));
        Date end1 = new Date();
        System.out.println();
        System.out.println((end1.getTime() - beigin1.getTime()));

        Date beigin2 = new Date();
        arraylist.stream().forEach((key) -> System.out.print(key));
        Date end2 = new Date();
        System.out.println();
        System.out.println((end2.getTime() - beigin2.getTime()));

        Date beigin3 = new Date();
//        arraylist.stream().filter(s->s.contains("y")).findFirst().ifPresent(s -> System.out.print(s));
        arraylist.stream().filter(s -> !s.contains("y")).forEach(s -> System.out.print(s));
        Date end3 = new Date();
        System.out.println();
        System.out.println((end3.getTime() - beigin3.getTime()));

        List<Model> tempList = new ArrayList<>();
        Model m1 = new Model();
        m1.setId(1);
        m1.setName("m1-name");
        m1.setYear("m1-year");
        Model m2 = new Model();
        m2.setId(2);
        m2.setName("m2-name");
        m2.setYear("m2-year");
        Model m3 = new Model();
        m3.setId(3);
        m3.setName("m3-name");
        m3.setYear("m3-year");
        tempList.add(m1);
        tempList.add(m2);
        tempList.add(m3);

        BeanInfo beanInfo = Introspector.getBeanInfo(Model.class);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor p : propertyDescriptors) {
            System.out.println(p.getName());
        }

    /*    Field [] field1 = m1.getClass().getFields();
        for (Field f:field1){
            if (f.getType() == String.class){
                String value = (String) f.get(m1);
                System.out.println(value);
                String newValue = (String) value.replaceAll("m1","m2");
                f.set(m1,newValue);
            }
        }*/

        List<NewModel> newList = new ArrayList<>();

        tempList.forEach(s->{
            try {
                BeanInfo bf = Introspector.getBeanInfo(Model.class);
                PropertyDescriptor [] pros = bf.getPropertyDescriptors();
                List<PropertyDescriptor> listpros = Arrays.asList(pros);
                NewModel newModel = new NewModel();
              Optional<PropertyDescriptor> p
                      =  listpros.stream().filter(f->f.getName().equals("name")).findAny();
              if (p.isPresent()){
                 PropertyDescriptor pp = p.get();
                 PropertyDescriptor pn = new PropertyDescriptor("userName",NewModel.class);
                 pn.getWriteMethod().invoke(newModel,pp.getReadMethod().invoke(s,null));
              }
              listpros.stream().filter(sn->!sn.getName().equals("name") && !sn.getName().equals("class")).
                      forEach(sn->{
                  try {
                      new PropertyDescriptor(sn.getName(),NewModel.class).getWriteMethod().
                              invoke(newModel,sn.getReadMethod().invoke(s,null));
                  }catch (Exception e){
                      e.printStackTrace();
                  }
              });
              newList.add(newModel);
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        System.out.println(newList);
        //classLoader
        System.out.println(m1.getClass().getClassLoader());

    }

 public static class Model {
         Model() {
        }
       private  int id ;
       private  String name;
       private  String year;

     public int getId() {
         return id;
     }

     public void setId(int id) {
         this.id = id;
     }

     public String getName() {
           return name;
       }

       public void setName(String name) {
           this.name = name;
       }

       public String getYear() {
           return year;
       }

       public void setYear(String year) {
           this.year = year;
       }
   }

   static class NewModel{

        int id;
        String userName;
        String year;

       public int getId() {
           return id;
       }

       public void setId(int id) {
           this.id = id;
       }

       public String getUserName() {
           return userName;
       }

       public void setUserName(String userName) {
           this.userName = userName;
       }

       public String getYear() {
           return year;
       }

       public void setYear(String year) {
           this.year = year;
       }
   }

}
