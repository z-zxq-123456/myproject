package com.zxq.learn.vars;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/6/19
 */
public class Test {

    public  static void main(String[]args){

        NodeObject object = new NodeObject();
        object.setName("test");
        modify(object);
        System.out.println(object);


    }

    static void modify(NodeObject object){
        NodeObject nodeObject = object;
        nodeObject.setLevel("level");
    }

    static class NodeObject{
         NodeObject() {
        }

        private String name;
        private String level;

        public String getName() {
            return name;
        }

        void setName(String name) {
            this.name = name;
        }

        public String getLevel() {
            return level;
        }

        void setLevel(String level) {
            this.level = level;
        }

        @Override
        public String toString() {
            return "NodeObject{" +
                    "name='" + name + '\'' +
                    ", level='" + level + '\'' +
                    '}';
        }
    }
}
