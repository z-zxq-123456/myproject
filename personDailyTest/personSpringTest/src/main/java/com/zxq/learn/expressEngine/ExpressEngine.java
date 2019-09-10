package com.zxq.learn.expressEngine;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;

/**
 * QLExpress 规则引擎使用介绍
 * Created{ by zhouxqh} on 2018/3/15.
 */


public class ExpressEngine {

    private ExpressRunner expressRunner;

    public ExpressEngine() {
        this.expressRunner = new ExpressRunner();
    }

    public static void main(String []args){

      try {
          String rule =  "if (TEST_EXPRESS>1){return \"a\";}else{return \"b\"}";
          String rule2 = "check(\"1\",\"1\")";

          ExpressEngine engine = new ExpressEngine();
          engine.expressRunner.addFunction("check",new CheckOperate("check"));
          DefaultContext<String, Object> rootMap = new DefaultContext<String, Object>();
          rootMap.put("TEST_EXPRESS","3");
          rootMap.put("forNum","3");

          Object o =  engine.expressRunner.execute(rule2,rootMap,null,false,false);

          System.out.println(o.toString());
      }catch (Exception e){
          e.printStackTrace();
      }
    }
}
